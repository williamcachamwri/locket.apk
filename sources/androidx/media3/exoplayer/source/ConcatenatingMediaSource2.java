package androidx.media3.exoplayer.source;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Pair;
import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.upstream.Allocator;
import com.google.common.collect.ImmutableList;
import java.util.HashMap;
import java.util.IdentityHashMap;

public final class ConcatenatingMediaSource2 extends CompositeMediaSource<Integer> {
    private static final int MSG_UPDATE_TIMELINE = 1;
    private MediaItem mediaItem;
    private final IdentityHashMap<MediaPeriod, MediaSourceHolder> mediaSourceByMediaPeriod;
    private final ImmutableList<MediaSourceHolder> mediaSourceHolders;
    private Handler playbackThreadHandler;
    private boolean timelineUpdateScheduled;

    private static long getChildWindowSequenceNumber(long j, int i, int i2) {
        return (j * ((long) i)) + ((long) i2);
    }

    public boolean canUpdateMediaItem(MediaItem mediaItem2) {
        return true;
    }

    /* access modifiers changed from: protected */
    public void enableInternal() {
    }

    /* access modifiers changed from: protected */
    public int getWindowIndexForChildWindowIndex(Integer num, int i) {
        return 0;
    }

    public static final class Builder {
        private int index;
        private MediaItem mediaItem;
        private MediaSource.Factory mediaSourceFactory;
        private final ImmutableList.Builder<MediaSourceHolder> mediaSourceHoldersBuilder = ImmutableList.builder();

        public Builder useDefaultMediaSourceFactory(Context context) {
            return setMediaSourceFactory(new DefaultMediaSourceFactory(context));
        }

        public Builder setMediaSourceFactory(MediaSource.Factory factory) {
            this.mediaSourceFactory = (MediaSource.Factory) Assertions.checkNotNull(factory);
            return this;
        }

        public Builder setMediaItem(MediaItem mediaItem2) {
            this.mediaItem = mediaItem2;
            return this;
        }

        public Builder add(MediaItem mediaItem2) {
            return add(mediaItem2, (long) C.TIME_UNSET);
        }

        public Builder add(MediaItem mediaItem2, long j) {
            Assertions.checkNotNull(mediaItem2);
            if (j == C.TIME_UNSET && mediaItem2.clippingConfiguration.endPositionMs != Long.MIN_VALUE) {
                j = Util.usToMs(mediaItem2.clippingConfiguration.endPositionUs - mediaItem2.clippingConfiguration.startPositionUs);
            }
            Assertions.checkStateNotNull(this.mediaSourceFactory, "Must use useDefaultMediaSourceFactory or setMediaSourceFactory first.");
            return add(this.mediaSourceFactory.createMediaSource(mediaItem2), j);
        }

        public Builder add(MediaSource mediaSource) {
            return add(mediaSource, (long) C.TIME_UNSET);
        }

        public Builder add(MediaSource mediaSource, long j) {
            Assertions.checkNotNull(mediaSource);
            Assertions.checkState(!(mediaSource instanceof ProgressiveMediaSource) || j != C.TIME_UNSET, "Progressive media source must define an initial placeholder duration.");
            ImmutableList.Builder<MediaSourceHolder> builder = this.mediaSourceHoldersBuilder;
            int i = this.index;
            this.index = i + 1;
            builder.add((Object) new MediaSourceHolder(mediaSource, i, Util.msToUs(j)));
            return this;
        }

        public ConcatenatingMediaSource2 build() {
            Assertions.checkArgument(this.index > 0, "Must add at least one source to the concatenation.");
            if (this.mediaItem == null) {
                this.mediaItem = MediaItem.fromUri(Uri.EMPTY);
            }
            return new ConcatenatingMediaSource2(this.mediaItem, this.mediaSourceHoldersBuilder.build());
        }
    }

    private ConcatenatingMediaSource2(MediaItem mediaItem2, ImmutableList<MediaSourceHolder> immutableList) {
        this.mediaItem = mediaItem2;
        this.mediaSourceHolders = immutableList;
        this.mediaSourceByMediaPeriod = new IdentityHashMap<>();
    }

    public Timeline getInitialTimeline() {
        return maybeCreateConcatenatedTimeline();
    }

    public synchronized MediaItem getMediaItem() {
        return this.mediaItem;
    }

    public synchronized void updateMediaItem(MediaItem mediaItem2) {
        this.mediaItem = mediaItem2;
    }

    /* access modifiers changed from: protected */
    public void prepareSourceInternal(TransferListener transferListener) {
        super.prepareSourceInternal(transferListener);
        this.playbackThreadHandler = new Handler(new ConcatenatingMediaSource2$$ExternalSyntheticLambda0(this));
        for (int i = 0; i < this.mediaSourceHolders.size(); i++) {
            prepareChildSource(Integer.valueOf(i), ((MediaSourceHolder) this.mediaSourceHolders.get(i)).mediaSource);
        }
        scheduleTimelineUpdate();
    }

    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        long j2;
        MediaSourceHolder mediaSourceHolder = (MediaSourceHolder) this.mediaSourceHolders.get(getChildIndex(mediaPeriodId.periodUid));
        MediaSource.MediaPeriodId copyWithWindowSequenceNumber = mediaPeriodId.copyWithPeriodUid(getChildPeriodUid(mediaPeriodId.periodUid)).copyWithWindowSequenceNumber(getChildWindowSequenceNumber(mediaPeriodId.windowSequenceNumber, this.mediaSourceHolders.size(), mediaSourceHolder.index));
        enableChildSource(Integer.valueOf(mediaSourceHolder.index));
        mediaSourceHolder.activeMediaPeriods++;
        if (mediaPeriodId.isAd()) {
            j2 = 0;
        } else {
            j2 = ((Long) Assertions.checkNotNull(mediaSourceHolder.periodTimeOffsetsByUid.get(copyWithWindowSequenceNumber.periodUid))).longValue();
        }
        TimeOffsetMediaPeriod timeOffsetMediaPeriod = new TimeOffsetMediaPeriod(mediaSourceHolder.mediaSource.createPeriod(copyWithWindowSequenceNumber, allocator, j - j2), j2);
        this.mediaSourceByMediaPeriod.put(timeOffsetMediaPeriod, mediaSourceHolder);
        disableUnusedMediaSources();
        return timeOffsetMediaPeriod;
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        MediaSourceHolder mediaSourceHolder = (MediaSourceHolder) Assertions.checkNotNull(this.mediaSourceByMediaPeriod.remove(mediaPeriod));
        mediaSourceHolder.mediaSource.releasePeriod(((TimeOffsetMediaPeriod) mediaPeriod).getWrappedMediaPeriod());
        mediaSourceHolder.activeMediaPeriods--;
        if (!this.mediaSourceByMediaPeriod.isEmpty()) {
            disableUnusedMediaSources();
        }
    }

    /* access modifiers changed from: protected */
    public void releaseSourceInternal() {
        super.releaseSourceInternal();
        Handler handler = this.playbackThreadHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages((Object) null);
            this.playbackThreadHandler = null;
        }
        this.timelineUpdateScheduled = false;
    }

    /* access modifiers changed from: protected */
    public void onChildSourceInfoRefreshed(Integer num, MediaSource mediaSource, Timeline timeline) {
        scheduleTimelineUpdate();
    }

    /* access modifiers changed from: protected */
    public MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(Integer num, MediaSource.MediaPeriodId mediaPeriodId) {
        if (num.intValue() != getChildIndexFromChildWindowSequenceNumber(mediaPeriodId.windowSequenceNumber, this.mediaSourceHolders.size())) {
            return null;
        }
        return mediaPeriodId.copyWithPeriodUid(getPeriodUid(num.intValue(), mediaPeriodId.periodUid)).copyWithWindowSequenceNumber(getWindowSequenceNumberFromChildWindowSequenceNumber(mediaPeriodId.windowSequenceNumber, this.mediaSourceHolders.size()));
    }

    /* access modifiers changed from: protected */
    public long getMediaTimeForChildMediaTime(Integer num, long j, MediaSource.MediaPeriodId mediaPeriodId) {
        Long l;
        if (j == C.TIME_UNSET || mediaPeriodId == null || mediaPeriodId.isAd() || (l = ((MediaSourceHolder) this.mediaSourceHolders.get(num.intValue())).periodTimeOffsetsByUid.get(mediaPeriodId.periodUid)) == null) {
            return j;
        }
        return j + Util.usToMs(l.longValue());
    }

    /* access modifiers changed from: private */
    public boolean handleMessage(Message message) {
        if (message.what == 1) {
            updateTimeline();
        }
        return true;
    }

    private void scheduleTimelineUpdate() {
        if (!this.timelineUpdateScheduled) {
            ((Handler) Assertions.checkNotNull(this.playbackThreadHandler)).obtainMessage(1).sendToTarget();
            this.timelineUpdateScheduled = true;
        }
    }

    private void updateTimeline() {
        this.timelineUpdateScheduled = false;
        ConcatenatedTimeline maybeCreateConcatenatedTimeline = maybeCreateConcatenatedTimeline();
        if (maybeCreateConcatenatedTimeline != null) {
            refreshSourceInfo(maybeCreateConcatenatedTimeline);
        }
    }

    private void disableUnusedMediaSources() {
        for (int i = 0; i < this.mediaSourceHolders.size(); i++) {
            MediaSourceHolder mediaSourceHolder = (MediaSourceHolder) this.mediaSourceHolders.get(i);
            if (mediaSourceHolder.activeMediaPeriods == 0) {
                disableChildSource(Integer.valueOf(mediaSourceHolder.index));
            }
        }
    }

    private ConcatenatedTimeline maybeCreateConcatenatedTimeline() {
        int i;
        boolean z;
        boolean z2;
        Object obj;
        Timeline timeline;
        Object obj2;
        long j;
        Timeline.Period period;
        boolean z3;
        ConcatenatingMediaSource2 concatenatingMediaSource2 = this;
        Timeline.Window window = new Timeline.Window();
        Timeline.Period period2 = new Timeline.Period();
        ImmutableList.Builder builder = ImmutableList.builder();
        ImmutableList.Builder builder2 = ImmutableList.builder();
        ImmutableList.Builder builder3 = ImmutableList.builder();
        int size = concatenatingMediaSource2.mediaSourceHolders.size();
        boolean z4 = true;
        boolean z5 = true;
        boolean z6 = true;
        int i2 = 0;
        Object obj3 = null;
        int i3 = 0;
        boolean z7 = false;
        boolean z8 = false;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        while (i2 < size) {
            MediaSourceHolder mediaSourceHolder = (MediaSourceHolder) concatenatingMediaSource2.mediaSourceHolders.get(i2);
            Timeline timeline2 = mediaSourceHolder.mediaSource.getTimeline();
            Assertions.checkArgument(timeline2.isEmpty() ^ z4, "Can't concatenate empty child Timeline.");
            builder.add((Object) timeline2);
            builder2.add((Object) Integer.valueOf(i3));
            i3 += timeline2.getPeriodCount();
            int i4 = 0;
            while (i4 < timeline2.getWindowCount()) {
                timeline2.getWindow(i4, window);
                if (!z7) {
                    obj3 = window.manifest;
                    z7 = true;
                }
                if (!z5 || !Util.areEqual(obj3, window.manifest)) {
                    i = i2;
                    z = false;
                } else {
                    i = i2;
                    z = true;
                }
                long j5 = window.durationUs;
                if (j5 == C.TIME_UNSET) {
                    if (mediaSourceHolder.initialPlaceholderDurationUs == C.TIME_UNSET) {
                        return null;
                    }
                    j5 = mediaSourceHolder.initialPlaceholderDurationUs;
                }
                j2 += j5;
                if (mediaSourceHolder.index == 0 && i4 == 0) {
                    z2 = z;
                    obj = obj3;
                    j3 = window.defaultPositionUs;
                    j4 = -window.positionInFirstPeriodUs;
                } else {
                    z2 = z;
                    obj = obj3;
                }
                z6 &= window.isSeekable || window.isPlaceholder;
                z8 |= window.isDynamic;
                int i5 = window.firstPeriodIndex;
                while (i5 <= window.lastPeriodIndex) {
                    builder3.add((Object) Long.valueOf(j4));
                    timeline2.getPeriod(i5, period2, true);
                    int i6 = i3;
                    long j6 = period2.durationUs;
                    if (j6 == C.TIME_UNSET) {
                        Assertions.checkArgument(window.firstPeriodIndex == window.lastPeriodIndex, "Can't apply placeholder duration to multiple periods with unknown duration in a single window.");
                        j6 = window.positionInFirstPeriodUs + j5;
                    }
                    if (!(i5 == window.firstPeriodIndex && !(mediaSourceHolder.index == 0 && i4 == 0)) || j6 == C.TIME_UNSET) {
                        obj2 = obj;
                        timeline = timeline2;
                        j = 0;
                    } else {
                        Timeline timeline3 = timeline2;
                        obj2 = obj;
                        j = -window.positionInFirstPeriodUs;
                        j6 += j;
                        timeline = timeline3;
                    }
                    Object checkNotNull = Assertions.checkNotNull(period2.uid);
                    Timeline.Window window2 = window;
                    if (mediaSourceHolder.activeMediaPeriods == 0 || !mediaSourceHolder.periodTimeOffsetsByUid.containsKey(checkNotNull)) {
                        period = period2;
                    } else {
                        period = period2;
                        if (!mediaSourceHolder.periodTimeOffsetsByUid.get(checkNotNull).equals(Long.valueOf(j))) {
                            z3 = false;
                            Assertions.checkArgument(z3, "Can't handle windows with changing offset in first period.");
                            mediaSourceHolder.periodTimeOffsetsByUid.put(checkNotNull, Long.valueOf(j));
                            j4 += j6;
                            i5++;
                            i3 = i6;
                            obj = obj2;
                            timeline2 = timeline;
                            window = window2;
                            period2 = period;
                        }
                    }
                    z3 = true;
                    Assertions.checkArgument(z3, "Can't handle windows with changing offset in first period.");
                    mediaSourceHolder.periodTimeOffsetsByUid.put(checkNotNull, Long.valueOf(j));
                    j4 += j6;
                    i5++;
                    i3 = i6;
                    obj = obj2;
                    timeline2 = timeline;
                    window = window2;
                    period2 = period;
                }
                Timeline.Window window3 = window;
                Timeline.Period period3 = period2;
                Timeline timeline4 = timeline2;
                int i7 = i3;
                i4++;
                i2 = i;
                z5 = z2;
                obj3 = obj;
            }
            Timeline.Window window4 = window;
            Timeline.Period period4 = period2;
            int i8 = i3;
            i2++;
            z4 = true;
            concatenatingMediaSource2 = this;
        }
        return new ConcatenatedTimeline(getMediaItem(), builder.build(), builder2.build(), builder3.build(), z6, z8, j2, j3, z5 ? obj3 : null);
    }

    /* access modifiers changed from: private */
    public static Object getPeriodUid(int i, Object obj) {
        return Pair.create(Integer.valueOf(i), obj);
    }

    /* access modifiers changed from: private */
    public static int getChildIndex(Object obj) {
        return ((Integer) ((Pair) obj).first).intValue();
    }

    /* access modifiers changed from: private */
    public static Object getChildPeriodUid(Object obj) {
        return ((Pair) obj).second;
    }

    private static int getChildIndexFromChildWindowSequenceNumber(long j, int i) {
        return (int) (j % ((long) i));
    }

    private static long getWindowSequenceNumberFromChildWindowSequenceNumber(long j, int i) {
        return j / ((long) i);
    }

    static final class MediaSourceHolder {
        public int activeMediaPeriods;
        public final int index;
        public final long initialPlaceholderDurationUs;
        public final MaskingMediaSource mediaSource;
        public final HashMap<Object, Long> periodTimeOffsetsByUid = new HashMap<>();

        public MediaSourceHolder(MediaSource mediaSource2, int i, long j) {
            this.mediaSource = new MaskingMediaSource(mediaSource2, false);
            this.index = i;
            this.initialPlaceholderDurationUs = j;
        }
    }

    private static final class ConcatenatedTimeline extends Timeline {
        private final long defaultPositionUs;
        private final long durationUs;
        private final ImmutableList<Integer> firstPeriodIndices;
        private final boolean isDynamic;
        private final boolean isSeekable;
        private final Object manifest;
        private final MediaItem mediaItem;
        private final ImmutableList<Long> periodOffsetsInWindowUs;
        private final ImmutableList<Timeline> timelines;

        public int getWindowCount() {
            return 1;
        }

        public ConcatenatedTimeline(MediaItem mediaItem2, ImmutableList<Timeline> immutableList, ImmutableList<Integer> immutableList2, ImmutableList<Long> immutableList3, boolean z, boolean z2, long j, long j2, Object obj) {
            this.mediaItem = mediaItem2;
            this.timelines = immutableList;
            this.firstPeriodIndices = immutableList2;
            this.periodOffsetsInWindowUs = immutableList3;
            this.isSeekable = z;
            this.isDynamic = z2;
            this.durationUs = j;
            this.defaultPositionUs = j2;
            this.manifest = obj;
        }

        public int getPeriodCount() {
            return this.periodOffsetsInWindowUs.size();
        }

        public Timeline.Window getWindow(int i, Timeline.Window window, long j) {
            return window.set(Timeline.Window.SINGLE_WINDOW_UID, this.mediaItem, this.manifest, C.TIME_UNSET, C.TIME_UNSET, C.TIME_UNSET, this.isSeekable, this.isDynamic, (MediaItem.LiveConfiguration) null, this.defaultPositionUs, this.durationUs, 0, getPeriodCount() - 1, -((Long) this.periodOffsetsInWindowUs.get(0)).longValue());
        }

        public Timeline.Period getPeriodByUid(Object obj, Timeline.Period period) {
            int access$100 = ConcatenatingMediaSource2.getChildIndex(obj);
            Object access$200 = ConcatenatingMediaSource2.getChildPeriodUid(obj);
            Timeline timeline = (Timeline) this.timelines.get(access$100);
            int intValue = ((Integer) this.firstPeriodIndices.get(access$100)).intValue() + timeline.getIndexOfPeriod(access$200);
            timeline.getPeriodByUid(access$200, period);
            period.windowIndex = 0;
            period.positionInWindowUs = ((Long) this.periodOffsetsInWindowUs.get(intValue)).longValue();
            period.durationUs = getPeriodDurationUs(period, intValue);
            period.uid = obj;
            return period;
        }

        public Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
            int childIndexByPeriodIndex = getChildIndexByPeriodIndex(i);
            ((Timeline) this.timelines.get(childIndexByPeriodIndex)).getPeriod(i - ((Integer) this.firstPeriodIndices.get(childIndexByPeriodIndex)).intValue(), period, z);
            period.windowIndex = 0;
            period.positionInWindowUs = ((Long) this.periodOffsetsInWindowUs.get(i)).longValue();
            period.durationUs = getPeriodDurationUs(period, i);
            if (z) {
                period.uid = ConcatenatingMediaSource2.getPeriodUid(childIndexByPeriodIndex, Assertions.checkNotNull(period.uid));
            }
            return period;
        }

        public int getIndexOfPeriod(Object obj) {
            if (!(obj instanceof Pair) || !(((Pair) obj).first instanceof Integer)) {
                return -1;
            }
            int access$100 = ConcatenatingMediaSource2.getChildIndex(obj);
            int indexOfPeriod = ((Timeline) this.timelines.get(access$100)).getIndexOfPeriod(ConcatenatingMediaSource2.getChildPeriodUid(obj));
            if (indexOfPeriod == -1) {
                return -1;
            }
            return ((Integer) this.firstPeriodIndices.get(access$100)).intValue() + indexOfPeriod;
        }

        public Object getUidOfPeriod(int i) {
            int childIndexByPeriodIndex = getChildIndexByPeriodIndex(i);
            return ConcatenatingMediaSource2.getPeriodUid(childIndexByPeriodIndex, ((Timeline) this.timelines.get(childIndexByPeriodIndex)).getUidOfPeriod(i - ((Integer) this.firstPeriodIndices.get(childIndexByPeriodIndex)).intValue()));
        }

        private int getChildIndexByPeriodIndex(int i) {
            return Util.binarySearchFloor(this.firstPeriodIndices, Integer.valueOf(i + 1), false, false);
        }

        private long getPeriodDurationUs(Timeline.Period period, int i) {
            long j;
            if (period.durationUs == C.TIME_UNSET) {
                return C.TIME_UNSET;
            }
            long longValue = ((Long) this.periodOffsetsInWindowUs.get(i)).longValue();
            if (i == this.periodOffsetsInWindowUs.size() - 1) {
                j = this.durationUs;
            } else {
                j = ((Long) this.periodOffsetsInWindowUs.get(i + 1)).longValue();
            }
            return j - longValue;
        }
    }
}

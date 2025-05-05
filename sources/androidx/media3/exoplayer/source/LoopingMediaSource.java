package androidx.media3.exoplayer.source;

import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.exoplayer.AbstractConcatenatedTimeline;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.ShuffleOrder;
import androidx.media3.exoplayer.upstream.Allocator;
import java.util.HashMap;
import java.util.Map;

@Deprecated
public final class LoopingMediaSource extends WrappingMediaSource {
    private final Map<MediaSource.MediaPeriodId, MediaSource.MediaPeriodId> childMediaPeriodIdToMediaPeriodId;
    private final int loopCount;
    private final Map<MediaPeriod, MediaSource.MediaPeriodId> mediaPeriodToChildMediaPeriodId;

    public boolean isSingleWindow() {
        return false;
    }

    public LoopingMediaSource(MediaSource mediaSource) {
        this(mediaSource, Integer.MAX_VALUE);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LoopingMediaSource(MediaSource mediaSource, int i) {
        super(new MaskingMediaSource(mediaSource, false));
        boolean z = false;
        Assertions.checkArgument(i > 0 ? true : z);
        this.loopCount = i;
        this.childMediaPeriodIdToMediaPeriodId = new HashMap();
        this.mediaPeriodToChildMediaPeriodId = new HashMap();
    }

    public Timeline getInitialTimeline() {
        MaskingMediaSource maskingMediaSource = (MaskingMediaSource) this.mediaSource;
        if (this.loopCount != Integer.MAX_VALUE) {
            return new LoopingTimeline(maskingMediaSource.getTimeline(), this.loopCount);
        }
        return new InfinitelyLoopingTimeline(maskingMediaSource.getTimeline());
    }

    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        if (this.loopCount == Integer.MAX_VALUE) {
            return this.mediaSource.createPeriod(mediaPeriodId, allocator, j);
        }
        MediaSource.MediaPeriodId copyWithPeriodUid = mediaPeriodId.copyWithPeriodUid(LoopingTimeline.getChildPeriodUidFromConcatenatedUid(mediaPeriodId.periodUid));
        this.childMediaPeriodIdToMediaPeriodId.put(copyWithPeriodUid, mediaPeriodId);
        MediaPeriod createPeriod = this.mediaSource.createPeriod(copyWithPeriodUid, allocator, j);
        this.mediaPeriodToChildMediaPeriodId.put(createPeriod, copyWithPeriodUid);
        return createPeriod;
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        this.mediaSource.releasePeriod(mediaPeriod);
        MediaSource.MediaPeriodId remove = this.mediaPeriodToChildMediaPeriodId.remove(mediaPeriod);
        if (remove != null) {
            this.childMediaPeriodIdToMediaPeriodId.remove(remove);
        }
    }

    /* access modifiers changed from: protected */
    public void onChildSourceInfoRefreshed(Timeline timeline) {
        Timeline timeline2;
        if (this.loopCount != Integer.MAX_VALUE) {
            timeline2 = new LoopingTimeline(timeline, this.loopCount);
        } else {
            timeline2 = new InfinitelyLoopingTimeline(timeline);
        }
        refreshSourceInfo(timeline2);
    }

    /* access modifiers changed from: protected */
    public MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(MediaSource.MediaPeriodId mediaPeriodId) {
        return this.loopCount != Integer.MAX_VALUE ? this.childMediaPeriodIdToMediaPeriodId.get(mediaPeriodId) : mediaPeriodId;
    }

    private static final class LoopingTimeline extends AbstractConcatenatedTimeline {
        private final int childPeriodCount;
        private final Timeline childTimeline;
        private final int childWindowCount;
        private final int loopCount;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LoopingTimeline(Timeline timeline, int i) {
            super(false, new ShuffleOrder.UnshuffledShuffleOrder(i));
            boolean z = false;
            this.childTimeline = timeline;
            int periodCount = timeline.getPeriodCount();
            this.childPeriodCount = periodCount;
            this.childWindowCount = timeline.getWindowCount();
            this.loopCount = i;
            if (periodCount > 0) {
                Assertions.checkState(i <= Integer.MAX_VALUE / periodCount ? true : z, "LoopingMediaSource contains too many periods");
            }
        }

        public int getWindowCount() {
            return this.childWindowCount * this.loopCount;
        }

        public int getPeriodCount() {
            return this.childPeriodCount * this.loopCount;
        }

        /* access modifiers changed from: protected */
        public int getChildIndexByPeriodIndex(int i) {
            return i / this.childPeriodCount;
        }

        /* access modifiers changed from: protected */
        public int getChildIndexByWindowIndex(int i) {
            return i / this.childWindowCount;
        }

        /* access modifiers changed from: protected */
        public int getChildIndexByChildUid(Object obj) {
            if (!(obj instanceof Integer)) {
                return -1;
            }
            return ((Integer) obj).intValue();
        }

        /* access modifiers changed from: protected */
        public Timeline getTimelineByChildIndex(int i) {
            return this.childTimeline;
        }

        /* access modifiers changed from: protected */
        public int getFirstPeriodIndexByChildIndex(int i) {
            return i * this.childPeriodCount;
        }

        /* access modifiers changed from: protected */
        public int getFirstWindowIndexByChildIndex(int i) {
            return i * this.childWindowCount;
        }

        /* access modifiers changed from: protected */
        public Object getChildUidByChildIndex(int i) {
            return Integer.valueOf(i);
        }
    }

    private static final class InfinitelyLoopingTimeline extends ForwardingTimeline {
        public InfinitelyLoopingTimeline(Timeline timeline) {
            super(timeline);
        }

        public int getNextWindowIndex(int i, int i2, boolean z) {
            int nextWindowIndex = this.timeline.getNextWindowIndex(i, i2, z);
            return nextWindowIndex == -1 ? getFirstWindowIndex(z) : nextWindowIndex;
        }

        public int getPreviousWindowIndex(int i, int i2, boolean z) {
            int previousWindowIndex = this.timeline.getPreviousWindowIndex(i, i2, z);
            return previousWindowIndex == -1 ? getLastWindowIndex(z) : previousWindowIndex;
        }
    }
}

package androidx.media3.common;

import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Pair;
import androidx.media3.common.AdPlaybackState;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BundleCollectionUtil;
import androidx.media3.common.util.Util;
import com.facebook.imageutils.JfifUtil;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;

public abstract class Timeline {
    public static final Timeline EMPTY = new Timeline() {
        public int getIndexOfPeriod(Object obj) {
            return -1;
        }

        public int getPeriodCount() {
            return 0;
        }

        public int getWindowCount() {
            return 0;
        }

        public Window getWindow(int i, Window window, long j) {
            throw new IndexOutOfBoundsException();
        }

        public Period getPeriod(int i, Period period, boolean z) {
            throw new IndexOutOfBoundsException();
        }

        public Object getUidOfPeriod(int i) {
            throw new IndexOutOfBoundsException();
        }
    };
    private static final String FIELD_PERIODS = Util.intToStringMaxRadix(1);
    private static final String FIELD_SHUFFLED_WINDOW_INDICES = Util.intToStringMaxRadix(2);
    private static final String FIELD_WINDOWS = Util.intToStringMaxRadix(0);

    public abstract int getIndexOfPeriod(Object obj);

    public abstract Period getPeriod(int i, Period period, boolean z);

    public abstract int getPeriodCount();

    public abstract Object getUidOfPeriod(int i);

    public abstract Window getWindow(int i, Window window, long j);

    public abstract int getWindowCount();

    public static final class Window {
        private static final Object FAKE_WINDOW_UID = new Object();
        private static final String FIELD_DEFAULT_POSITION_US = Util.intToStringMaxRadix(9);
        private static final String FIELD_DURATION_US = Util.intToStringMaxRadix(10);
        private static final String FIELD_ELAPSED_REALTIME_EPOCH_OFFSET_MS = Util.intToStringMaxRadix(4);
        private static final String FIELD_FIRST_PERIOD_INDEX = Util.intToStringMaxRadix(11);
        private static final String FIELD_IS_DYNAMIC = Util.intToStringMaxRadix(6);
        private static final String FIELD_IS_PLACEHOLDER = Util.intToStringMaxRadix(8);
        private static final String FIELD_IS_SEEKABLE = Util.intToStringMaxRadix(5);
        private static final String FIELD_LAST_PERIOD_INDEX = Util.intToStringMaxRadix(12);
        private static final String FIELD_LIVE_CONFIGURATION = Util.intToStringMaxRadix(7);
        private static final String FIELD_MEDIA_ITEM = Util.intToStringMaxRadix(1);
        private static final String FIELD_POSITION_IN_FIRST_PERIOD_US = Util.intToStringMaxRadix(13);
        private static final String FIELD_PRESENTATION_START_TIME_MS = Util.intToStringMaxRadix(2);
        private static final String FIELD_WINDOW_START_TIME_MS = Util.intToStringMaxRadix(3);
        private static final MediaItem PLACEHOLDER_MEDIA_ITEM = new MediaItem.Builder().setMediaId("androidx.media3.common.Timeline").setUri(Uri.EMPTY).build();
        public static final Object SINGLE_WINDOW_UID = new Object();
        public long defaultPositionUs;
        public long durationUs;
        public long elapsedRealtimeEpochOffsetMs;
        public int firstPeriodIndex;
        public boolean isDynamic;
        public boolean isPlaceholder;
        public boolean isSeekable;
        public int lastPeriodIndex;
        public MediaItem.LiveConfiguration liveConfiguration;
        public Object manifest;
        public MediaItem mediaItem = PLACEHOLDER_MEDIA_ITEM;
        public long positionInFirstPeriodUs;
        public long presentationStartTimeMs;
        @Deprecated
        public Object tag;
        public Object uid = SINGLE_WINDOW_UID;
        public long windowStartTimeMs;

        public Window set(Object obj, MediaItem mediaItem2, Object obj2, long j, long j2, long j3, boolean z, boolean z2, MediaItem.LiveConfiguration liveConfiguration2, long j4, long j5, int i, int i2, long j6) {
            MediaItem mediaItem3;
            MediaItem mediaItem4 = mediaItem2;
            this.uid = obj;
            if (mediaItem4 != null) {
                mediaItem3 = mediaItem4;
            } else {
                mediaItem3 = PLACEHOLDER_MEDIA_ITEM;
            }
            this.mediaItem = mediaItem3;
            this.tag = (mediaItem4 == null || mediaItem4.localConfiguration == null) ? null : mediaItem4.localConfiguration.tag;
            this.manifest = obj2;
            this.presentationStartTimeMs = j;
            this.windowStartTimeMs = j2;
            this.elapsedRealtimeEpochOffsetMs = j3;
            this.isSeekable = z;
            this.isDynamic = z2;
            this.liveConfiguration = liveConfiguration2;
            this.defaultPositionUs = j4;
            this.durationUs = j5;
            this.firstPeriodIndex = i;
            this.lastPeriodIndex = i2;
            this.positionInFirstPeriodUs = j6;
            this.isPlaceholder = false;
            return this;
        }

        public long getDefaultPositionMs() {
            return Util.usToMs(this.defaultPositionUs);
        }

        public long getDefaultPositionUs() {
            return this.defaultPositionUs;
        }

        public long getDurationMs() {
            return Util.usToMs(this.durationUs);
        }

        public long getDurationUs() {
            return this.durationUs;
        }

        public long getPositionInFirstPeriodMs() {
            return Util.usToMs(this.positionInFirstPeriodUs);
        }

        public long getPositionInFirstPeriodUs() {
            return this.positionInFirstPeriodUs;
        }

        public long getCurrentUnixTimeMs() {
            return Util.getNowUnixTimeMs(this.elapsedRealtimeEpochOffsetMs);
        }

        public boolean isLive() {
            return this.liveConfiguration != null;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || !getClass().equals(obj.getClass())) {
                return false;
            }
            Window window = (Window) obj;
            if (Util.areEqual(this.uid, window.uid) && Util.areEqual(this.mediaItem, window.mediaItem) && Util.areEqual(this.manifest, window.manifest) && Util.areEqual(this.liveConfiguration, window.liveConfiguration) && this.presentationStartTimeMs == window.presentationStartTimeMs && this.windowStartTimeMs == window.windowStartTimeMs && this.elapsedRealtimeEpochOffsetMs == window.elapsedRealtimeEpochOffsetMs && this.isSeekable == window.isSeekable && this.isDynamic == window.isDynamic && this.isPlaceholder == window.isPlaceholder && this.defaultPositionUs == window.defaultPositionUs && this.durationUs == window.durationUs && this.firstPeriodIndex == window.firstPeriodIndex && this.lastPeriodIndex == window.lastPeriodIndex && this.positionInFirstPeriodUs == window.positionInFirstPeriodUs) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            int hashCode = (((JfifUtil.MARKER_EOI + this.uid.hashCode()) * 31) + this.mediaItem.hashCode()) * 31;
            Object obj = this.manifest;
            int i = 0;
            int hashCode2 = (hashCode + (obj == null ? 0 : obj.hashCode())) * 31;
            MediaItem.LiveConfiguration liveConfiguration2 = this.liveConfiguration;
            if (liveConfiguration2 != null) {
                i = liveConfiguration2.hashCode();
            }
            long j = this.presentationStartTimeMs;
            long j2 = this.windowStartTimeMs;
            long j3 = this.elapsedRealtimeEpochOffsetMs;
            long j4 = this.defaultPositionUs;
            long j5 = this.durationUs;
            long j6 = this.positionInFirstPeriodUs;
            return ((((((((((((((((((((((hashCode2 + i) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + ((int) (j3 ^ (j3 >>> 32)))) * 31) + (this.isSeekable ? 1 : 0)) * 31) + (this.isDynamic ? 1 : 0)) * 31) + (this.isPlaceholder ? 1 : 0)) * 31) + ((int) (j4 ^ (j4 >>> 32)))) * 31) + ((int) (j5 ^ (j5 >>> 32)))) * 31) + this.firstPeriodIndex) * 31) + this.lastPeriodIndex) * 31) + ((int) (j6 ^ (j6 >>> 32)));
        }

        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            if (!MediaItem.EMPTY.equals(this.mediaItem)) {
                bundle.putBundle(FIELD_MEDIA_ITEM, this.mediaItem.toBundle());
            }
            long j = this.presentationStartTimeMs;
            if (j != C.TIME_UNSET) {
                bundle.putLong(FIELD_PRESENTATION_START_TIME_MS, j);
            }
            long j2 = this.windowStartTimeMs;
            if (j2 != C.TIME_UNSET) {
                bundle.putLong(FIELD_WINDOW_START_TIME_MS, j2);
            }
            long j3 = this.elapsedRealtimeEpochOffsetMs;
            if (j3 != C.TIME_UNSET) {
                bundle.putLong(FIELD_ELAPSED_REALTIME_EPOCH_OFFSET_MS, j3);
            }
            boolean z = this.isSeekable;
            if (z) {
                bundle.putBoolean(FIELD_IS_SEEKABLE, z);
            }
            boolean z2 = this.isDynamic;
            if (z2) {
                bundle.putBoolean(FIELD_IS_DYNAMIC, z2);
            }
            MediaItem.LiveConfiguration liveConfiguration2 = this.liveConfiguration;
            if (liveConfiguration2 != null) {
                bundle.putBundle(FIELD_LIVE_CONFIGURATION, liveConfiguration2.toBundle());
            }
            boolean z3 = this.isPlaceholder;
            if (z3) {
                bundle.putBoolean(FIELD_IS_PLACEHOLDER, z3);
            }
            long j4 = this.defaultPositionUs;
            if (j4 != 0) {
                bundle.putLong(FIELD_DEFAULT_POSITION_US, j4);
            }
            long j5 = this.durationUs;
            if (j5 != C.TIME_UNSET) {
                bundle.putLong(FIELD_DURATION_US, j5);
            }
            int i = this.firstPeriodIndex;
            if (i != 0) {
                bundle.putInt(FIELD_FIRST_PERIOD_INDEX, i);
            }
            int i2 = this.lastPeriodIndex;
            if (i2 != 0) {
                bundle.putInt(FIELD_LAST_PERIOD_INDEX, i2);
            }
            long j6 = this.positionInFirstPeriodUs;
            if (j6 != 0) {
                bundle.putLong(FIELD_POSITION_IN_FIRST_PERIOD_US, j6);
            }
            return bundle;
        }

        public static Window fromBundle(Bundle bundle) {
            Bundle bundle2 = bundle;
            Bundle bundle3 = bundle2.getBundle(FIELD_MEDIA_ITEM);
            MediaItem fromBundle = bundle3 != null ? MediaItem.fromBundle(bundle3) : MediaItem.EMPTY;
            long j = bundle2.getLong(FIELD_PRESENTATION_START_TIME_MS, C.TIME_UNSET);
            long j2 = bundle2.getLong(FIELD_WINDOW_START_TIME_MS, C.TIME_UNSET);
            long j3 = bundle2.getLong(FIELD_ELAPSED_REALTIME_EPOCH_OFFSET_MS, C.TIME_UNSET);
            boolean z = bundle2.getBoolean(FIELD_IS_SEEKABLE, false);
            boolean z2 = bundle2.getBoolean(FIELD_IS_DYNAMIC, false);
            Bundle bundle4 = bundle2.getBundle(FIELD_LIVE_CONFIGURATION);
            MediaItem.LiveConfiguration fromBundle2 = bundle4 != null ? MediaItem.LiveConfiguration.fromBundle(bundle4) : null;
            boolean z3 = bundle2.getBoolean(FIELD_IS_PLACEHOLDER, false);
            long j4 = bundle2.getLong(FIELD_DEFAULT_POSITION_US, 0);
            long j5 = bundle2.getLong(FIELD_DURATION_US, C.TIME_UNSET);
            int i = bundle2.getInt(FIELD_FIRST_PERIOD_INDEX, 0);
            int i2 = bundle2.getInt(FIELD_LAST_PERIOD_INDEX, 0);
            long j6 = bundle2.getLong(FIELD_POSITION_IN_FIRST_PERIOD_US, 0);
            Window window = r0;
            Window window2 = new Window();
            window.set(FAKE_WINDOW_UID, fromBundle, (Object) null, j, j2, j3, z, z2, fromBundle2, j4, j5, i, i2, j6);
            window2.isPlaceholder = z3;
            return window2;
        }
    }

    public static final class Period {
        private static final String FIELD_AD_PLAYBACK_STATE = Util.intToStringMaxRadix(4);
        private static final String FIELD_DURATION_US = Util.intToStringMaxRadix(1);
        private static final String FIELD_PLACEHOLDER = Util.intToStringMaxRadix(3);
        private static final String FIELD_POSITION_IN_WINDOW_US = Util.intToStringMaxRadix(2);
        private static final String FIELD_WINDOW_INDEX = Util.intToStringMaxRadix(0);
        public AdPlaybackState adPlaybackState = AdPlaybackState.NONE;
        public long durationUs;
        public Object id;
        public boolean isPlaceholder;
        public long positionInWindowUs;
        public Object uid;
        public int windowIndex;

        public Period set(Object obj, Object obj2, int i, long j, long j2) {
            return set(obj, obj2, i, j, j2, AdPlaybackState.NONE, false);
        }

        public Period set(Object obj, Object obj2, int i, long j, long j2, AdPlaybackState adPlaybackState2, boolean z) {
            this.id = obj;
            this.uid = obj2;
            this.windowIndex = i;
            this.durationUs = j;
            this.positionInWindowUs = j2;
            this.adPlaybackState = adPlaybackState2;
            this.isPlaceholder = z;
            return this;
        }

        public long getDurationMs() {
            return Util.usToMs(this.durationUs);
        }

        public long getDurationUs() {
            return this.durationUs;
        }

        public long getPositionInWindowMs() {
            return Util.usToMs(this.positionInWindowUs);
        }

        public long getPositionInWindowUs() {
            return this.positionInWindowUs;
        }

        public Object getAdsId() {
            return this.adPlaybackState.adsId;
        }

        public int getAdGroupCount() {
            return this.adPlaybackState.adGroupCount;
        }

        public int getRemovedAdGroupCount() {
            return this.adPlaybackState.removedAdGroupCount;
        }

        public long getAdGroupTimeUs(int i) {
            return this.adPlaybackState.getAdGroup(i).timeUs;
        }

        public int getFirstAdIndexToPlay(int i) {
            return this.adPlaybackState.getAdGroup(i).getFirstAdIndexToPlay();
        }

        public int getNextAdIndexToPlay(int i, int i2) {
            return this.adPlaybackState.getAdGroup(i).getNextAdIndexToPlay(i2);
        }

        public boolean hasPlayedAdGroup(int i) {
            return !this.adPlaybackState.getAdGroup(i).hasUnplayedAds();
        }

        public int getAdGroupIndexForPositionUs(long j) {
            return this.adPlaybackState.getAdGroupIndexForPositionUs(j, this.durationUs);
        }

        public int getAdGroupIndexAfterPositionUs(long j) {
            return this.adPlaybackState.getAdGroupIndexAfterPositionUs(j, this.durationUs);
        }

        public int getAdCountInAdGroup(int i) {
            return this.adPlaybackState.getAdGroup(i).count;
        }

        public long getAdDurationUs(int i, int i2) {
            AdPlaybackState.AdGroup adGroup = this.adPlaybackState.getAdGroup(i);
            return adGroup.count != -1 ? adGroup.durationsUs[i2] : C.TIME_UNSET;
        }

        public int getAdState(int i, int i2) {
            AdPlaybackState.AdGroup adGroup = this.adPlaybackState.getAdGroup(i);
            if (adGroup.count != -1) {
                return adGroup.states[i2];
            }
            return 0;
        }

        public boolean isLivePostrollPlaceholder(int i) {
            if (i != getAdGroupCount() - 1 || !this.adPlaybackState.isLivePostrollPlaceholder(i)) {
                return false;
            }
            return true;
        }

        public long getAdResumePositionUs() {
            return this.adPlaybackState.adResumePositionUs;
        }

        public boolean isServerSideInsertedAdGroup(int i) {
            return this.adPlaybackState.getAdGroup(i).isServerSideInserted;
        }

        public long getContentResumeOffsetUs(int i) {
            return this.adPlaybackState.getAdGroup(i).contentResumeOffsetUs;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || !getClass().equals(obj.getClass())) {
                return false;
            }
            Period period = (Period) obj;
            if (Util.areEqual(this.id, period.id) && Util.areEqual(this.uid, period.uid) && this.windowIndex == period.windowIndex && this.durationUs == period.durationUs && this.positionInWindowUs == period.positionInWindowUs && this.isPlaceholder == period.isPlaceholder && Util.areEqual(this.adPlaybackState, period.adPlaybackState)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            Object obj = this.id;
            int i = 0;
            int hashCode = (JfifUtil.MARKER_EOI + (obj == null ? 0 : obj.hashCode())) * 31;
            Object obj2 = this.uid;
            if (obj2 != null) {
                i = obj2.hashCode();
            }
            long j = this.durationUs;
            long j2 = this.positionInWindowUs;
            return ((((((((((hashCode + i) * 31) + this.windowIndex) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + (this.isPlaceholder ? 1 : 0)) * 31) + this.adPlaybackState.hashCode();
        }

        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            int i = this.windowIndex;
            if (i != 0) {
                bundle.putInt(FIELD_WINDOW_INDEX, i);
            }
            long j = this.durationUs;
            if (j != C.TIME_UNSET) {
                bundle.putLong(FIELD_DURATION_US, j);
            }
            long j2 = this.positionInWindowUs;
            if (j2 != 0) {
                bundle.putLong(FIELD_POSITION_IN_WINDOW_US, j2);
            }
            boolean z = this.isPlaceholder;
            if (z) {
                bundle.putBoolean(FIELD_PLACEHOLDER, z);
            }
            if (!this.adPlaybackState.equals(AdPlaybackState.NONE)) {
                bundle.putBundle(FIELD_AD_PLAYBACK_STATE, this.adPlaybackState.toBundle());
            }
            return bundle;
        }

        public static Period fromBundle(Bundle bundle) {
            AdPlaybackState adPlaybackState2;
            int i = bundle.getInt(FIELD_WINDOW_INDEX, 0);
            long j = bundle.getLong(FIELD_DURATION_US, C.TIME_UNSET);
            long j2 = bundle.getLong(FIELD_POSITION_IN_WINDOW_US, 0);
            boolean z = bundle.getBoolean(FIELD_PLACEHOLDER, false);
            Bundle bundle2 = bundle.getBundle(FIELD_AD_PLAYBACK_STATE);
            if (bundle2 != null) {
                adPlaybackState2 = AdPlaybackState.fromBundle(bundle2);
            } else {
                adPlaybackState2 = AdPlaybackState.NONE;
            }
            AdPlaybackState adPlaybackState3 = adPlaybackState2;
            Period period = new Period();
            period.set((Object) null, (Object) null, i, j, j2, adPlaybackState3, z);
            return period;
        }
    }

    protected Timeline() {
    }

    public final boolean isEmpty() {
        return getWindowCount() == 0;
    }

    public int getNextWindowIndex(int i, int i2, boolean z) {
        if (i2 != 0) {
            if (i2 == 1) {
                return i;
            }
            if (i2 == 2) {
                return i == getLastWindowIndex(z) ? getFirstWindowIndex(z) : i + 1;
            }
            throw new IllegalStateException();
        } else if (i == getLastWindowIndex(z)) {
            return -1;
        } else {
            return i + 1;
        }
    }

    public int getPreviousWindowIndex(int i, int i2, boolean z) {
        if (i2 != 0) {
            if (i2 == 1) {
                return i;
            }
            if (i2 == 2) {
                return i == getFirstWindowIndex(z) ? getLastWindowIndex(z) : i - 1;
            }
            throw new IllegalStateException();
        } else if (i == getFirstWindowIndex(z)) {
            return -1;
        } else {
            return i - 1;
        }
    }

    public int getLastWindowIndex(boolean z) {
        if (isEmpty()) {
            return -1;
        }
        return getWindowCount() - 1;
    }

    public int getFirstWindowIndex(boolean z) {
        return isEmpty() ? -1 : 0;
    }

    public final Window getWindow(int i, Window window) {
        return getWindow(i, window, 0);
    }

    public final int getNextPeriodIndex(int i, Period period, Window window, int i2, boolean z) {
        int i3 = getPeriod(i, period).windowIndex;
        if (getWindow(i3, window).lastPeriodIndex != i) {
            return i + 1;
        }
        int nextWindowIndex = getNextWindowIndex(i3, i2, z);
        if (nextWindowIndex == -1) {
            return -1;
        }
        return getWindow(nextWindowIndex, window).firstPeriodIndex;
    }

    public final boolean isLastPeriod(int i, Period period, Window window, int i2, boolean z) {
        return getNextPeriodIndex(i, period, window, i2, z) == -1;
    }

    @Deprecated
    public final Pair<Object, Long> getPeriodPosition(Window window, Period period, int i, long j) {
        return getPeriodPositionUs(window, period, i, j);
    }

    @Deprecated
    public final Pair<Object, Long> getPeriodPosition(Window window, Period period, int i, long j, long j2) {
        return getPeriodPositionUs(window, period, i, j, j2);
    }

    public final Pair<Object, Long> getPeriodPositionUs(Window window, Period period, int i, long j) {
        return (Pair) Assertions.checkNotNull(getPeriodPositionUs(window, period, i, j, 0));
    }

    public final Pair<Object, Long> getPeriodPositionUs(Window window, Period period, int i, long j, long j2) {
        Assertions.checkIndex(i, 0, getWindowCount());
        getWindow(i, window, j2);
        if (j == C.TIME_UNSET) {
            j = window.getDefaultPositionUs();
            if (j == C.TIME_UNSET) {
                return null;
            }
        }
        int i2 = window.firstPeriodIndex;
        getPeriod(i2, period);
        while (i2 < window.lastPeriodIndex && period.positionInWindowUs != j) {
            int i3 = i2 + 1;
            if (getPeriod(i3, period).positionInWindowUs > j) {
                break;
            }
            i2 = i3;
        }
        getPeriod(i2, period, true);
        long j3 = j - period.positionInWindowUs;
        if (period.durationUs != C.TIME_UNSET) {
            j3 = Math.min(j3, period.durationUs - 1);
        }
        return Pair.create(Assertions.checkNotNull(period.uid), Long.valueOf(Math.max(0, j3)));
    }

    public Period getPeriodByUid(Object obj, Period period) {
        return getPeriod(getIndexOfPeriod(obj), period, true);
    }

    public final Period getPeriod(int i, Period period) {
        return getPeriod(i, period, false);
    }

    public boolean equals(Object obj) {
        int lastWindowIndex;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Timeline)) {
            return false;
        }
        Timeline timeline = (Timeline) obj;
        if (timeline.getWindowCount() != getWindowCount() || timeline.getPeriodCount() != getPeriodCount()) {
            return false;
        }
        Window window = new Window();
        Period period = new Period();
        Window window2 = new Window();
        Period period2 = new Period();
        for (int i = 0; i < getWindowCount(); i++) {
            if (!getWindow(i, window).equals(timeline.getWindow(i, window2))) {
                return false;
            }
        }
        for (int i2 = 0; i2 < getPeriodCount(); i2++) {
            if (!getPeriod(i2, period, true).equals(timeline.getPeriod(i2, period2, true))) {
                return false;
            }
        }
        int firstWindowIndex = getFirstWindowIndex(true);
        if (firstWindowIndex != timeline.getFirstWindowIndex(true) || (lastWindowIndex = getLastWindowIndex(true)) != timeline.getLastWindowIndex(true)) {
            return false;
        }
        while (firstWindowIndex != lastWindowIndex) {
            int nextWindowIndex = getNextWindowIndex(firstWindowIndex, 0, true);
            if (nextWindowIndex != timeline.getNextWindowIndex(firstWindowIndex, 0, true)) {
                return false;
            }
            firstWindowIndex = nextWindowIndex;
        }
        return true;
    }

    public int hashCode() {
        Window window = new Window();
        Period period = new Period();
        int windowCount = JfifUtil.MARKER_EOI + getWindowCount();
        for (int i = 0; i < getWindowCount(); i++) {
            windowCount = (windowCount * 31) + getWindow(i, window).hashCode();
        }
        int periodCount = (windowCount * 31) + getPeriodCount();
        for (int i2 = 0; i2 < getPeriodCount(); i2++) {
            periodCount = (periodCount * 31) + getPeriod(i2, period, true).hashCode();
        }
        int firstWindowIndex = getFirstWindowIndex(true);
        while (firstWindowIndex != -1) {
            periodCount = (periodCount * 31) + firstWindowIndex;
            firstWindowIndex = getNextWindowIndex(firstWindowIndex, 0, true);
        }
        return periodCount;
    }

    public final Bundle toBundle() {
        ArrayList arrayList = new ArrayList();
        int windowCount = getWindowCount();
        Window window = new Window();
        for (int i = 0; i < windowCount; i++) {
            arrayList.add(getWindow(i, window, 0).toBundle());
        }
        ArrayList arrayList2 = new ArrayList();
        int periodCount = getPeriodCount();
        Period period = new Period();
        for (int i2 = 0; i2 < periodCount; i2++) {
            arrayList2.add(getPeriod(i2, period, false).toBundle());
        }
        int[] iArr = new int[windowCount];
        if (windowCount > 0) {
            iArr[0] = getFirstWindowIndex(true);
        }
        for (int i3 = 1; i3 < windowCount; i3++) {
            iArr[i3] = getNextWindowIndex(iArr[i3 - 1], 0, true);
        }
        Bundle bundle = new Bundle();
        bundle.putBinder(FIELD_WINDOWS, new BundleListRetriever(arrayList));
        bundle.putBinder(FIELD_PERIODS, new BundleListRetriever(arrayList2));
        bundle.putIntArray(FIELD_SHUFFLED_WINDOW_INDICES, iArr);
        return bundle;
    }

    public final Timeline copyWithSingleWindow(int i) {
        if (getWindowCount() == 1) {
            return this;
        }
        Window window = getWindow(i, new Window(), 0);
        ImmutableList.Builder builder = ImmutableList.builder();
        for (int i2 = window.firstPeriodIndex; i2 <= window.lastPeriodIndex; i2++) {
            Period period = getPeriod(i2, new Period(), true);
            period.windowIndex = 0;
            builder.add((Object) period);
        }
        window.lastPeriodIndex -= window.firstPeriodIndex;
        window.firstPeriodIndex = 0;
        return new RemotableTimeline(ImmutableList.of(window), builder.build(), new int[]{0});
    }

    public static Timeline fromBundle(Bundle bundle) {
        ImmutableList fromBundleListRetriever = fromBundleListRetriever(new Timeline$$ExternalSyntheticLambda0(), bundle.getBinder(FIELD_WINDOWS));
        ImmutableList fromBundleListRetriever2 = fromBundleListRetriever(new Timeline$$ExternalSyntheticLambda1(), bundle.getBinder(FIELD_PERIODS));
        int[] intArray = bundle.getIntArray(FIELD_SHUFFLED_WINDOW_INDICES);
        if (intArray == null) {
            intArray = generateUnshuffledIndices(fromBundleListRetriever.size());
        }
        return new RemotableTimeline(fromBundleListRetriever, fromBundleListRetriever2, intArray);
    }

    private static <T> ImmutableList<T> fromBundleListRetriever(Function<Bundle, T> function, IBinder iBinder) {
        if (iBinder == null) {
            return ImmutableList.of();
        }
        return BundleCollectionUtil.fromBundleList(function, BundleListRetriever.getList(iBinder));
    }

    private static int[] generateUnshuffledIndices(int i) {
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = i2;
        }
        return iArr;
    }

    public static final class RemotableTimeline extends Timeline {
        private final ImmutableList<Period> periods;
        private final int[] shuffledWindowIndices;
        private final int[] windowIndicesInShuffled;
        private final ImmutableList<Window> windows;

        public RemotableTimeline(ImmutableList<Window> immutableList, ImmutableList<Period> immutableList2, int[] iArr) {
            Assertions.checkArgument(immutableList.size() == iArr.length);
            this.windows = immutableList;
            this.periods = immutableList2;
            this.shuffledWindowIndices = iArr;
            this.windowIndicesInShuffled = new int[iArr.length];
            for (int i = 0; i < iArr.length; i++) {
                this.windowIndicesInShuffled[iArr[i]] = i;
            }
        }

        public int getWindowCount() {
            return this.windows.size();
        }

        public Window getWindow(int i, Window window, long j) {
            Window window2 = window;
            Window window3 = (Window) this.windows.get(i);
            Object obj = window3.uid;
            MediaItem mediaItem = window3.mediaItem;
            MediaItem mediaItem2 = mediaItem;
            Window window4 = window3;
            Window window5 = window;
            window5.set(obj, mediaItem2, window3.manifest, window3.presentationStartTimeMs, window3.windowStartTimeMs, window3.elapsedRealtimeEpochOffsetMs, window3.isSeekable, window3.isDynamic, window3.liveConfiguration, window3.defaultPositionUs, window4.durationUs, window4.firstPeriodIndex, window4.lastPeriodIndex, window4.positionInFirstPeriodUs);
            Window window6 = window;
            window6.isPlaceholder = window4.isPlaceholder;
            return window6;
        }

        public int getNextWindowIndex(int i, int i2, boolean z) {
            if (i2 == 1) {
                return i;
            }
            if (i != getLastWindowIndex(z)) {
                return z ? this.shuffledWindowIndices[this.windowIndicesInShuffled[i] + 1] : i + 1;
            }
            if (i2 == 2) {
                return getFirstWindowIndex(z);
            }
            return -1;
        }

        public int getPreviousWindowIndex(int i, int i2, boolean z) {
            if (i2 == 1) {
                return i;
            }
            if (i != getFirstWindowIndex(z)) {
                return z ? this.shuffledWindowIndices[this.windowIndicesInShuffled[i] - 1] : i - 1;
            }
            if (i2 == 2) {
                return getLastWindowIndex(z);
            }
            return -1;
        }

        public int getLastWindowIndex(boolean z) {
            if (isEmpty()) {
                return -1;
            }
            if (z) {
                return this.shuffledWindowIndices[getWindowCount() - 1];
            }
            return getWindowCount() - 1;
        }

        public int getFirstWindowIndex(boolean z) {
            if (isEmpty()) {
                return -1;
            }
            if (z) {
                return this.shuffledWindowIndices[0];
            }
            return 0;
        }

        public int getPeriodCount() {
            return this.periods.size();
        }

        public Period getPeriod(int i, Period period, boolean z) {
            Period period2 = (Period) this.periods.get(i);
            period.set(period2.id, period2.uid, period2.windowIndex, period2.durationUs, period2.positionInWindowUs, period2.adPlaybackState, period2.isPlaceholder);
            return period;
        }

        public int getIndexOfPeriod(Object obj) {
            throw new UnsupportedOperationException();
        }

        public Object getUidOfPeriod(int i) {
            throw new UnsupportedOperationException();
        }
    }
}

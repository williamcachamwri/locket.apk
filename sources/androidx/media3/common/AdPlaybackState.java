package androidx.media3.common;

import android.net.Uri;
import android.os.Bundle;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;

public final class AdPlaybackState {
    public static final int AD_STATE_AVAILABLE = 1;
    public static final int AD_STATE_ERROR = 4;
    public static final int AD_STATE_PLAYED = 3;
    public static final int AD_STATE_SKIPPED = 2;
    public static final int AD_STATE_UNAVAILABLE = 0;
    private static final String FIELD_AD_GROUPS = Util.intToStringMaxRadix(1);
    private static final String FIELD_AD_RESUME_POSITION_US = Util.intToStringMaxRadix(2);
    private static final String FIELD_CONTENT_DURATION_US = Util.intToStringMaxRadix(3);
    private static final String FIELD_REMOVED_AD_GROUP_COUNT = Util.intToStringMaxRadix(4);
    public static final AdPlaybackState NONE = new AdPlaybackState((Object) null, new AdGroup[0], 0, C.TIME_UNSET, 0);
    private static final AdGroup REMOVED_AD_GROUP = new AdGroup(0).withAdCount(0);
    public final int adGroupCount;
    private final AdGroup[] adGroups;
    public final long adResumePositionUs;
    public final Object adsId;
    public final long contentDurationUs;
    public final int removedAdGroupCount;

    @Documented
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface AdState {
    }

    public static final class AdGroup {
        private static final String FIELD_CONTENT_RESUME_OFFSET_US = Util.intToStringMaxRadix(5);
        private static final String FIELD_COUNT = Util.intToStringMaxRadix(1);
        private static final String FIELD_DURATIONS_US = Util.intToStringMaxRadix(4);
        private static final String FIELD_IS_SERVER_SIDE_INSERTED = Util.intToStringMaxRadix(6);
        static final String FIELD_MEDIA_ITEMS = Util.intToStringMaxRadix(8);
        private static final String FIELD_ORIGINAL_COUNT = Util.intToStringMaxRadix(7);
        private static final String FIELD_STATES = Util.intToStringMaxRadix(3);
        private static final String FIELD_TIME_US = Util.intToStringMaxRadix(0);
        private static final String FIELD_URIS = Util.intToStringMaxRadix(2);
        public final long contentResumeOffsetUs;
        public final int count;
        public final long[] durationsUs;
        public final boolean isServerSideInserted;
        public final MediaItem[] mediaItems;
        public final int originalCount;
        public final int[] states;
        public final long timeUs;
        @Deprecated
        public final Uri[] uris;

        public AdGroup(long j) {
            this(j, -1, -1, new int[0], new MediaItem[0], new long[0], 0, false);
        }

        private AdGroup(long j, int i, int i2, int[] iArr, MediaItem[] mediaItemArr, long[] jArr, long j2, boolean z) {
            int i3 = 0;
            Assertions.checkArgument(iArr.length == mediaItemArr.length);
            this.timeUs = j;
            this.count = i;
            this.originalCount = i2;
            this.states = iArr;
            this.mediaItems = mediaItemArr;
            this.durationsUs = jArr;
            this.contentResumeOffsetUs = j2;
            this.isServerSideInserted = z;
            this.uris = new Uri[mediaItemArr.length];
            while (true) {
                Uri[] uriArr = this.uris;
                if (i3 < uriArr.length) {
                    MediaItem mediaItem = mediaItemArr[i3];
                    uriArr[i3] = mediaItem == null ? null : ((MediaItem.LocalConfiguration) Assertions.checkNotNull(mediaItem.localConfiguration)).uri;
                    i3++;
                } else {
                    return;
                }
            }
        }

        public int getFirstAdIndexToPlay() {
            return getNextAdIndexToPlay(-1);
        }

        public int getNextAdIndexToPlay(int i) {
            int i2;
            int i3 = i + 1;
            while (true) {
                int[] iArr = this.states;
                if (i3 >= iArr.length || this.isServerSideInserted || (i2 = iArr[i3]) == 0 || i2 == 1) {
                    return i3;
                }
                i3++;
            }
            return i3;
        }

        public boolean shouldPlayAdGroup() {
            return this.count == -1 || getFirstAdIndexToPlay() < this.count;
        }

        public boolean hasUnplayedAds() {
            if (this.count == -1) {
                return true;
            }
            for (int i = 0; i < this.count; i++) {
                int i2 = this.states[i];
                if (i2 == 0 || i2 == 1) {
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: private */
        public boolean isLivePostrollPlaceholder() {
            return this.isServerSideInserted && this.timeUs == Long.MIN_VALUE && this.count == -1;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            AdGroup adGroup = (AdGroup) obj;
            if (this.timeUs == adGroup.timeUs && this.count == adGroup.count && this.originalCount == adGroup.originalCount && Arrays.equals(this.mediaItems, adGroup.mediaItems) && Arrays.equals(this.states, adGroup.states) && Arrays.equals(this.durationsUs, adGroup.durationsUs) && this.contentResumeOffsetUs == adGroup.contentResumeOffsetUs && this.isServerSideInserted == adGroup.isServerSideInserted) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            long j = this.timeUs;
            long j2 = this.contentResumeOffsetUs;
            return (((((((((((((this.count * 31) + this.originalCount) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + Arrays.hashCode(this.mediaItems)) * 31) + Arrays.hashCode(this.states)) * 31) + Arrays.hashCode(this.durationsUs)) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + (this.isServerSideInserted ? 1 : 0);
        }

        public AdGroup withTimeUs(long j) {
            return new AdGroup(j, this.count, this.originalCount, this.states, this.mediaItems, this.durationsUs, this.contentResumeOffsetUs, this.isServerSideInserted);
        }

        public AdGroup withAdCount(int i) {
            int[] copyStatesWithSpaceForAdCount = copyStatesWithSpaceForAdCount(this.states, i);
            long[] copyDurationsUsWithSpaceForAdCount = copyDurationsUsWithSpaceForAdCount(this.durationsUs, i);
            return new AdGroup(this.timeUs, i, this.originalCount, copyStatesWithSpaceForAdCount, (MediaItem[]) Arrays.copyOf(this.mediaItems, i), copyDurationsUsWithSpaceForAdCount, this.contentResumeOffsetUs, this.isServerSideInserted);
        }

        @Deprecated
        public AdGroup withAdUri(Uri uri, int i) {
            return withAdMediaItem(MediaItem.fromUri(uri), i);
        }

        public AdGroup withAdMediaItem(MediaItem mediaItem, int i) {
            int[] copyStatesWithSpaceForAdCount = copyStatesWithSpaceForAdCount(this.states, i + 1);
            long[] jArr = this.durationsUs;
            if (jArr.length != copyStatesWithSpaceForAdCount.length) {
                jArr = copyDurationsUsWithSpaceForAdCount(jArr, copyStatesWithSpaceForAdCount.length);
            }
            long[] jArr2 = jArr;
            MediaItem[] mediaItemArr = (MediaItem[]) Arrays.copyOf(this.mediaItems, copyStatesWithSpaceForAdCount.length);
            mediaItemArr[i] = mediaItem;
            copyStatesWithSpaceForAdCount[i] = 1;
            return new AdGroup(this.timeUs, this.count, this.originalCount, copyStatesWithSpaceForAdCount, mediaItemArr, jArr2, this.contentResumeOffsetUs, this.isServerSideInserted);
        }

        public AdGroup withAdState(int i, int i2) {
            int i3 = i;
            int i4 = i2;
            int i5 = this.count;
            boolean z = false;
            Assertions.checkArgument(i5 == -1 || i4 < i5);
            int[] copyStatesWithSpaceForAdCount = copyStatesWithSpaceForAdCount(this.states, i4 + 1);
            int i6 = copyStatesWithSpaceForAdCount[i4];
            if (i6 == 0 || i6 == 1 || i6 == i3) {
                z = true;
            }
            Assertions.checkArgument(z);
            long[] jArr = this.durationsUs;
            if (jArr.length != copyStatesWithSpaceForAdCount.length) {
                jArr = copyDurationsUsWithSpaceForAdCount(jArr, copyStatesWithSpaceForAdCount.length);
            }
            long[] jArr2 = jArr;
            MediaItem[] mediaItemArr = this.mediaItems;
            if (mediaItemArr.length != copyStatesWithSpaceForAdCount.length) {
                mediaItemArr = (MediaItem[]) Arrays.copyOf(mediaItemArr, copyStatesWithSpaceForAdCount.length);
            }
            copyStatesWithSpaceForAdCount[i4] = i3;
            return new AdGroup(this.timeUs, this.count, this.originalCount, copyStatesWithSpaceForAdCount, mediaItemArr, jArr2, this.contentResumeOffsetUs, this.isServerSideInserted);
        }

        public AdGroup withAdDurationsUs(long[] jArr) {
            int length = jArr.length;
            MediaItem[] mediaItemArr = this.mediaItems;
            if (length < mediaItemArr.length) {
                jArr = copyDurationsUsWithSpaceForAdCount(jArr, mediaItemArr.length);
            } else if (this.count != -1 && jArr.length > mediaItemArr.length) {
                jArr = Arrays.copyOf(jArr, mediaItemArr.length);
            }
            return new AdGroup(this.timeUs, this.count, this.originalCount, this.states, this.mediaItems, jArr, this.contentResumeOffsetUs, this.isServerSideInserted);
        }

        public AdGroup withContentResumeOffsetUs(long j) {
            return new AdGroup(this.timeUs, this.count, this.originalCount, this.states, this.mediaItems, this.durationsUs, j, this.isServerSideInserted);
        }

        public AdGroup withIsServerSideInserted(boolean z) {
            return new AdGroup(this.timeUs, this.count, this.originalCount, this.states, this.mediaItems, this.durationsUs, this.contentResumeOffsetUs, z);
        }

        public AdGroup withOriginalAdCount(int i) {
            return new AdGroup(this.timeUs, this.count, i, this.states, this.mediaItems, this.durationsUs, this.contentResumeOffsetUs, this.isServerSideInserted);
        }

        public AdGroup withLastAdRemoved() {
            int[] iArr = this.states;
            int length = iArr.length - 1;
            int[] copyOf = Arrays.copyOf(iArr, length);
            MediaItem[] mediaItemArr = (MediaItem[]) Arrays.copyOf(this.mediaItems, length);
            long[] jArr = this.durationsUs;
            if (jArr.length > length) {
                jArr = Arrays.copyOf(jArr, length);
            }
            long[] jArr2 = jArr;
            return new AdGroup(this.timeUs, length, this.originalCount, copyOf, mediaItemArr, jArr2, Util.sum(jArr2), this.isServerSideInserted);
        }

        public AdGroup withAllAdsSkipped() {
            if (this.count == -1) {
                return new AdGroup(this.timeUs, 0, this.originalCount, new int[0], new MediaItem[0], new long[0], this.contentResumeOffsetUs, this.isServerSideInserted);
            }
            int[] iArr = this.states;
            int length = iArr.length;
            int[] copyOf = Arrays.copyOf(iArr, length);
            for (int i = 0; i < length; i++) {
                int i2 = copyOf[i];
                if (i2 == 1 || i2 == 0) {
                    copyOf[i] = 2;
                }
            }
            return new AdGroup(this.timeUs, length, this.originalCount, copyOf, this.mediaItems, this.durationsUs, this.contentResumeOffsetUs, this.isServerSideInserted);
        }

        public AdGroup withAllAdsReset() {
            if (this.count == -1) {
                return this;
            }
            int[] iArr = this.states;
            int length = iArr.length;
            int[] copyOf = Arrays.copyOf(iArr, length);
            for (int i = 0; i < length; i++) {
                int i2 = copyOf[i];
                if (i2 == 3 || i2 == 2 || i2 == 4) {
                    copyOf[i] = this.mediaItems[i] == null ? 0 : 1;
                }
            }
            return new AdGroup(this.timeUs, length, this.originalCount, copyOf, this.mediaItems, this.durationsUs, this.contentResumeOffsetUs, this.isServerSideInserted);
        }

        private static int[] copyStatesWithSpaceForAdCount(int[] iArr, int i) {
            int length = iArr.length;
            int max = Math.max(i, length);
            int[] copyOf = Arrays.copyOf(iArr, max);
            Arrays.fill(copyOf, length, max, 0);
            return copyOf;
        }

        private static long[] copyDurationsUsWithSpaceForAdCount(long[] jArr, int i) {
            int length = jArr.length;
            int max = Math.max(i, length);
            long[] copyOf = Arrays.copyOf(jArr, max);
            Arrays.fill(copyOf, length, max, C.TIME_UNSET);
            return copyOf;
        }

        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putLong(FIELD_TIME_US, this.timeUs);
            bundle.putInt(FIELD_COUNT, this.count);
            bundle.putInt(FIELD_ORIGINAL_COUNT, this.originalCount);
            bundle.putParcelableArrayList(FIELD_URIS, new ArrayList(Arrays.asList(this.uris)));
            bundle.putParcelableArrayList(FIELD_MEDIA_ITEMS, getMediaItemsArrayBundles());
            bundle.putIntArray(FIELD_STATES, this.states);
            bundle.putLongArray(FIELD_DURATIONS_US, this.durationsUs);
            bundle.putLong(FIELD_CONTENT_RESUME_OFFSET_US, this.contentResumeOffsetUs);
            bundle.putBoolean(FIELD_IS_SERVER_SIDE_INSERTED, this.isServerSideInserted);
            return bundle;
        }

        public static AdGroup fromBundle(Bundle bundle) {
            long j = bundle.getLong(FIELD_TIME_US);
            int i = bundle.getInt(FIELD_COUNT);
            int i2 = bundle.getInt(FIELD_ORIGINAL_COUNT);
            ArrayList parcelableArrayList = bundle.getParcelableArrayList(FIELD_URIS);
            ArrayList parcelableArrayList2 = bundle.getParcelableArrayList(FIELD_MEDIA_ITEMS);
            int[] intArray = bundle.getIntArray(FIELD_STATES);
            long[] longArray = bundle.getLongArray(FIELD_DURATIONS_US);
            long j2 = bundle.getLong(FIELD_CONTENT_RESUME_OFFSET_US);
            boolean z = bundle.getBoolean(FIELD_IS_SERVER_SIDE_INSERTED);
            if (intArray == null) {
                intArray = new int[0];
            }
            return new AdGroup(j, i, i2, intArray, getMediaItemsFromBundleArrays(parcelableArrayList2, parcelableArrayList), longArray == null ? new long[0] : longArray, j2, z);
        }

        private ArrayList<Bundle> getMediaItemsArrayBundles() {
            Bundle bundle;
            ArrayList<Bundle> arrayList = new ArrayList<>();
            for (MediaItem mediaItem : this.mediaItems) {
                if (mediaItem == null) {
                    bundle = null;
                } else {
                    bundle = mediaItem.toBundleIncludeLocalConfiguration();
                }
                arrayList.add(bundle);
            }
            return arrayList;
        }

        private static MediaItem[] getMediaItemsFromBundleArrays(ArrayList<Bundle> arrayList, ArrayList<Uri> arrayList2) {
            MediaItem mediaItem;
            MediaItem mediaItem2;
            int i = 0;
            if (arrayList != null) {
                MediaItem[] mediaItemArr = new MediaItem[arrayList.size()];
                while (i < arrayList.size()) {
                    Bundle bundle = arrayList.get(i);
                    if (bundle == null) {
                        mediaItem2 = null;
                    } else {
                        mediaItem2 = MediaItem.fromBundle(bundle);
                    }
                    mediaItemArr[i] = mediaItem2;
                    i++;
                }
                return mediaItemArr;
            } else if (arrayList2 == null) {
                return new MediaItem[0];
            } else {
                MediaItem[] mediaItemArr2 = new MediaItem[arrayList2.size()];
                while (i < arrayList2.size()) {
                    Uri uri = arrayList2.get(i);
                    if (uri == null) {
                        mediaItem = null;
                    } else {
                        mediaItem = MediaItem.fromUri(uri);
                    }
                    mediaItemArr2[i] = mediaItem;
                    i++;
                }
                return mediaItemArr2;
            }
        }
    }

    public AdPlaybackState(Object obj, long... jArr) {
        this(obj, createEmptyAdGroups(jArr), 0, C.TIME_UNSET, 0);
    }

    private AdPlaybackState(Object obj, AdGroup[] adGroupArr, long j, long j2, int i) {
        this.adsId = obj;
        this.adResumePositionUs = j;
        this.contentDurationUs = j2;
        this.adGroupCount = adGroupArr.length + i;
        this.adGroups = adGroupArr;
        this.removedAdGroupCount = i;
    }

    public AdGroup getAdGroup(int i) {
        int i2 = this.removedAdGroupCount;
        if (i < i2) {
            return REMOVED_AD_GROUP;
        }
        return this.adGroups[i - i2];
    }

    public int getAdGroupIndexForPositionUs(long j, long j2) {
        int i = this.adGroupCount - 1;
        int i2 = i - (isLivePostrollPlaceholder(i) ? 1 : 0);
        while (i2 >= 0 && isPositionBeforeAdGroup(j, j2, i2)) {
            i2--;
        }
        if (i2 < 0 || !getAdGroup(i2).hasUnplayedAds()) {
            return -1;
        }
        return i2;
    }

    public int getAdGroupIndexAfterPositionUs(long j, long j2) {
        if (j == Long.MIN_VALUE) {
            return -1;
        }
        if (j2 != C.TIME_UNSET && j >= j2) {
            return -1;
        }
        int i = this.removedAdGroupCount;
        while (i < this.adGroupCount && ((getAdGroup(i).timeUs != Long.MIN_VALUE && getAdGroup(i).timeUs <= j) || !getAdGroup(i).shouldPlayAdGroup())) {
            i++;
        }
        if (i < this.adGroupCount) {
            return i;
        }
        return -1;
    }

    public boolean isAdInErrorState(int i, int i2) {
        if (i >= this.adGroupCount) {
            return false;
        }
        AdGroup adGroup = getAdGroup(i);
        if (adGroup.count == -1 || i2 >= adGroup.count || adGroup.states[i2] != 4) {
            return false;
        }
        return true;
    }

    public AdPlaybackState withAdGroupTimeUs(int i, long j) {
        int i2 = i - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i2] = this.adGroups[i2].withTimeUs(j);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withNewAdGroup(int i, long j) {
        int i2 = i - this.removedAdGroupCount;
        AdGroup adGroup = new AdGroup(j);
        AdGroup[] adGroupArr = (AdGroup[]) Util.nullSafeArrayAppend(this.adGroups, adGroup);
        System.arraycopy(adGroupArr, i2, adGroupArr, i2 + 1, this.adGroups.length - i2);
        adGroupArr[i2] = adGroup;
        return new AdPlaybackState(this.adsId, adGroupArr, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withAdCount(int i, int i2) {
        Assertions.checkArgument(i2 > 0);
        int i3 = i - this.removedAdGroupCount;
        if (this.adGroups[i3].count == i2) {
            return this;
        }
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i3] = this.adGroups[i3].withAdCount(i2);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    @Deprecated
    public AdPlaybackState withAvailableAdUri(int i, int i2, Uri uri) {
        return withAvailableAdMediaItem(i, i2, MediaItem.fromUri(uri));
    }

    public AdPlaybackState withAvailableAdMediaItem(int i, int i2, MediaItem mediaItem) {
        int i3 = i - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        Assertions.checkState(adGroupArr2[i3].isServerSideInserted || (mediaItem.localConfiguration != null && !mediaItem.localConfiguration.uri.equals(Uri.EMPTY)));
        adGroupArr2[i3] = adGroupArr2[i3].withAdMediaItem(mediaItem, i2);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withAvailableAd(int i, int i2) {
        return withAvailableAdMediaItem(i, i2, MediaItem.fromUri(Uri.EMPTY));
    }

    public AdPlaybackState withPlayedAd(int i, int i2) {
        int i3 = i - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i3] = adGroupArr2[i3].withAdState(3, i2);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withSkippedAd(int i, int i2) {
        int i3 = i - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i3] = adGroupArr2[i3].withAdState(2, i2);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withLastAdRemoved(int i) {
        int i2 = i - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i2] = adGroupArr2[i2].withLastAdRemoved();
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withAdLoadError(int i, int i2) {
        int i3 = i - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i3] = adGroupArr2[i3].withAdState(4, i2);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withSkippedAdGroup(int i) {
        int i2 = i - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i2] = adGroupArr2[i2].withAllAdsSkipped();
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withAdDurationsUs(long[][] jArr) {
        Assertions.checkState(this.removedAdGroupCount == 0);
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        for (int i = 0; i < this.adGroupCount; i++) {
            adGroupArr2[i] = adGroupArr2[i].withAdDurationsUs(jArr[i]);
        }
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withAdDurationsUs(int i, long... jArr) {
        int i2 = i - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i2] = adGroupArr2[i2].withAdDurationsUs(jArr);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withAdResumePositionUs(long j) {
        if (this.adResumePositionUs == j) {
            return this;
        }
        return new AdPlaybackState(this.adsId, this.adGroups, j, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withContentDurationUs(long j) {
        if (this.contentDurationUs == j) {
            return this;
        }
        return new AdPlaybackState(this.adsId, this.adGroups, this.adResumePositionUs, j, this.removedAdGroupCount);
    }

    public AdPlaybackState withRemovedAdGroupCount(int i) {
        int i2 = this.removedAdGroupCount;
        if (i2 == i) {
            return this;
        }
        Assertions.checkArgument(i > i2);
        int i3 = this.adGroupCount - i;
        AdGroup[] adGroupArr = new AdGroup[i3];
        System.arraycopy(this.adGroups, i - this.removedAdGroupCount, adGroupArr, 0, i3);
        return new AdPlaybackState(this.adsId, adGroupArr, this.adResumePositionUs, this.contentDurationUs, i);
    }

    public AdPlaybackState withContentResumeOffsetUs(int i, long j) {
        int i2 = i - this.removedAdGroupCount;
        if (this.adGroups[i2].contentResumeOffsetUs == j) {
            return this;
        }
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i2] = adGroupArr2[i2].withContentResumeOffsetUs(j);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withOriginalAdCount(int i, int i2) {
        int i3 = i - this.removedAdGroupCount;
        if (this.adGroups[i3].originalCount == i2) {
            return this;
        }
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i3] = adGroupArr2[i3].withOriginalAdCount(i2);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withIsServerSideInserted(int i, boolean z) {
        int i2 = i - this.removedAdGroupCount;
        if (this.adGroups[i2].isServerSideInserted == z) {
            return this;
        }
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i2] = adGroupArr2[i2].withIsServerSideInserted(z);
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withResetAdGroup(int i) {
        int i2 = i - this.removedAdGroupCount;
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i2] = adGroupArr2[i2].withAllAdsReset();
        return new AdPlaybackState(this.adsId, adGroupArr2, this.adResumePositionUs, this.contentDurationUs, this.removedAdGroupCount);
    }

    public AdPlaybackState withLivePostrollPlaceholderAppended() {
        return withNewAdGroup(this.adGroupCount, Long.MIN_VALUE).withIsServerSideInserted(this.adGroupCount, true);
    }

    public boolean endsWithLivePostrollPlaceHolder() {
        int i = this.adGroupCount - 1;
        if (i < 0 || !isLivePostrollPlaceholder(i)) {
            return false;
        }
        return true;
    }

    public boolean isLivePostrollPlaceholder(int i) {
        return i == this.adGroupCount - 1 && getAdGroup(i).isLivePostrollPlaceholder();
    }

    public static AdPlaybackState fromAdPlaybackState(Object obj, AdPlaybackState adPlaybackState) {
        AdPlaybackState adPlaybackState2 = adPlaybackState;
        int i = adPlaybackState2.adGroupCount - adPlaybackState2.removedAdGroupCount;
        AdGroup[] adGroupArr = new AdGroup[i];
        for (int i2 = 0; i2 < i; i2++) {
            AdGroup adGroup = adPlaybackState2.adGroups[i2];
            adGroupArr[i2] = new AdGroup(adGroup.timeUs, adGroup.count, adGroup.originalCount, Arrays.copyOf(adGroup.states, adGroup.states.length), (MediaItem[]) Arrays.copyOf(adGroup.mediaItems, adGroup.mediaItems.length), Arrays.copyOf(adGroup.durationsUs, adGroup.durationsUs.length), adGroup.contentResumeOffsetUs, adGroup.isServerSideInserted);
        }
        return new AdPlaybackState(obj, adGroupArr, adPlaybackState2.adResumePositionUs, adPlaybackState2.contentDurationUs, adPlaybackState2.removedAdGroupCount);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AdPlaybackState adPlaybackState = (AdPlaybackState) obj;
        if (Util.areEqual(this.adsId, adPlaybackState.adsId) && this.adGroupCount == adPlaybackState.adGroupCount && this.adResumePositionUs == adPlaybackState.adResumePositionUs && this.contentDurationUs == adPlaybackState.contentDurationUs && this.removedAdGroupCount == adPlaybackState.removedAdGroupCount && Arrays.equals(this.adGroups, adPlaybackState.adGroups)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = this.adGroupCount * 31;
        Object obj = this.adsId;
        return ((((((((i + (obj == null ? 0 : obj.hashCode())) * 31) + ((int) this.adResumePositionUs)) * 31) + ((int) this.contentDurationUs)) * 31) + this.removedAdGroupCount) * 31) + Arrays.hashCode(this.adGroups);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("AdPlaybackState(adsId=");
        sb.append(this.adsId);
        sb.append(", adResumePositionUs=");
        sb.append(this.adResumePositionUs);
        sb.append(", adGroups=[");
        for (int i = 0; i < this.adGroups.length; i++) {
            sb.append("adGroup(timeUs=");
            sb.append(this.adGroups[i].timeUs);
            sb.append(", ads=[");
            for (int i2 = 0; i2 < this.adGroups[i].states.length; i2++) {
                sb.append("ad(state=");
                int i3 = this.adGroups[i].states[i2];
                if (i3 == 0) {
                    sb.append('_');
                } else if (i3 == 1) {
                    sb.append('R');
                } else if (i3 == 2) {
                    sb.append('S');
                } else if (i3 == 3) {
                    sb.append('P');
                } else if (i3 != 4) {
                    sb.append('?');
                } else {
                    sb.append('!');
                }
                sb.append(", durationUs=");
                sb.append(this.adGroups[i].durationsUs[i2]);
                sb.append(')');
                if (i2 < this.adGroups[i].states.length - 1) {
                    sb.append(", ");
                }
            }
            sb.append("])");
            if (i < this.adGroups.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("])");
        return sb.toString();
    }

    private boolean isPositionBeforeAdGroup(long j, long j2, int i) {
        if (j == Long.MIN_VALUE) {
            return false;
        }
        AdGroup adGroup = getAdGroup(i);
        long j3 = adGroup.timeUs;
        if (j3 == Long.MIN_VALUE) {
            if (j2 == C.TIME_UNSET || ((adGroup.isServerSideInserted && adGroup.count == -1) || j < j2)) {
                return true;
            }
            return false;
        } else if (j < j3) {
            return true;
        } else {
            return false;
        }
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        ArrayList arrayList = new ArrayList();
        for (AdGroup bundle2 : this.adGroups) {
            arrayList.add(bundle2.toBundle());
        }
        if (!arrayList.isEmpty()) {
            bundle.putParcelableArrayList(FIELD_AD_GROUPS, arrayList);
        }
        long j = this.adResumePositionUs;
        AdPlaybackState adPlaybackState = NONE;
        if (j != adPlaybackState.adResumePositionUs) {
            bundle.putLong(FIELD_AD_RESUME_POSITION_US, j);
        }
        long j2 = this.contentDurationUs;
        if (j2 != adPlaybackState.contentDurationUs) {
            bundle.putLong(FIELD_CONTENT_DURATION_US, j2);
        }
        int i = this.removedAdGroupCount;
        if (i != adPlaybackState.removedAdGroupCount) {
            bundle.putInt(FIELD_REMOVED_AD_GROUP_COUNT, i);
        }
        return bundle;
    }

    public static AdPlaybackState fromBundle(Bundle bundle) {
        AdGroup[] adGroupArr;
        ArrayList parcelableArrayList = bundle.getParcelableArrayList(FIELD_AD_GROUPS);
        if (parcelableArrayList == null) {
            adGroupArr = new AdGroup[0];
        } else {
            AdGroup[] adGroupArr2 = new AdGroup[parcelableArrayList.size()];
            for (int i = 0; i < parcelableArrayList.size(); i++) {
                adGroupArr2[i] = AdGroup.fromBundle((Bundle) parcelableArrayList.get(i));
            }
            adGroupArr = adGroupArr2;
        }
        String str = FIELD_AD_RESUME_POSITION_US;
        AdPlaybackState adPlaybackState = NONE;
        return new AdPlaybackState((Object) null, adGroupArr, bundle.getLong(str, adPlaybackState.adResumePositionUs), bundle.getLong(FIELD_CONTENT_DURATION_US, adPlaybackState.contentDurationUs), bundle.getInt(FIELD_REMOVED_AD_GROUP_COUNT, adPlaybackState.removedAdGroupCount));
    }

    private static AdGroup[] createEmptyAdGroups(long[] jArr) {
        int length = jArr.length;
        AdGroup[] adGroupArr = new AdGroup[length];
        for (int i = 0; i < length; i++) {
            adGroupArr[i] = new AdGroup(jArr[i]);
        }
        return adGroupArr;
    }
}

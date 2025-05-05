package androidx.media3.exoplayer.ima;

import android.content.Context;
import android.os.Looper;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import androidx.media3.common.AdPlaybackState;
import androidx.media3.common.AdViewProvider;
import androidx.media3.common.C;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSchemeDataSource;
import androidx.media3.datasource.DataSourceUtil;
import androidx.media3.datasource.DataSpec;
import androidx.media3.exoplayer.ima.ImaServerSideAdInsertionMediaSource;
import androidx.media3.exoplayer.source.ads.ServerSideAdInsertionUtil;
import com.google.ads.interactivemedia.v3.api.AdDisplayContainer;
import com.google.ads.interactivemedia.v3.api.AdError;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent;
import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.google.ads.interactivemedia.v3.api.AdPodInfo;
import com.google.ads.interactivemedia.v3.api.AdsLoader;
import com.google.ads.interactivemedia.v3.api.AdsRenderingSettings;
import com.google.ads.interactivemedia.v3.api.AdsRequest;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot;
import com.google.ads.interactivemedia.v3.api.FriendlyObstruction;
import com.google.ads.interactivemedia.v3.api.FriendlyObstructionPurpose;
import com.google.ads.interactivemedia.v3.api.ImaSdkSettings;
import com.google.ads.interactivemedia.v3.api.UiElement;
import com.google.ads.interactivemedia.v3.api.player.VideoAdPlayer;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import com.google.common.collect.ImmutableList;
import com.google.common.math.DoubleMath;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

final class ImaUtil {
    public static final int BITRATE_UNSET = -1;
    public static final int TIMEOUT_UNSET = -1;

    public interface ImaFactory {
        AdDisplayContainer createAdDisplayContainer(ViewGroup viewGroup, VideoAdPlayer videoAdPlayer);

        AdsLoader createAdsLoader(Context context, ImaSdkSettings imaSdkSettings, AdDisplayContainer adDisplayContainer);

        AdsRenderingSettings createAdsRenderingSettings();

        AdsRequest createAdsRequest();

        AdDisplayContainer createAudioAdDisplayContainer(Context context, VideoAdPlayer videoAdPlayer);

        FriendlyObstruction createFriendlyObstruction(View view, FriendlyObstructionPurpose friendlyObstructionPurpose, String str);

        ImaSdkSettings createImaSdkSettings();
    }

    public static final class Configuration {
        public final List<String> adMediaMimeTypes;
        public final long adPreloadTimeoutMs;
        public final Set<UiElement> adUiElements;
        public final AdErrorEvent.AdErrorListener applicationAdErrorListener;
        public final AdEvent.AdEventListener applicationAdEventListener;
        public final VideoAdPlayer.VideoAdPlayerCallback applicationVideoAdPlayerCallback;
        public final Collection<CompanionAdSlot> companionAdSlots;
        public final boolean debugModeEnabled;
        public final Boolean enableContinuousPlayback;
        public final boolean focusSkipButtonWhenAvailable;
        public final ImaSdkSettings imaSdkSettings;
        public final int mediaBitrate;
        public final int mediaLoadTimeoutMs;
        public final boolean playAdBeforeStartPosition;
        public final int vastLoadTimeoutMs;

        public Configuration(long j, int i, int i2, boolean z, boolean z2, int i3, Boolean bool, List<String> list, Set<UiElement> set, Collection<CompanionAdSlot> collection, AdErrorEvent.AdErrorListener adErrorListener, AdEvent.AdEventListener adEventListener, VideoAdPlayer.VideoAdPlayerCallback videoAdPlayerCallback, ImaSdkSettings imaSdkSettings2, boolean z3) {
            this.adPreloadTimeoutMs = j;
            this.vastLoadTimeoutMs = i;
            this.mediaLoadTimeoutMs = i2;
            this.focusSkipButtonWhenAvailable = z;
            this.playAdBeforeStartPosition = z2;
            this.mediaBitrate = i3;
            this.enableContinuousPlayback = bool;
            this.adMediaMimeTypes = list;
            this.adUiElements = set;
            this.companionAdSlots = collection;
            this.applicationAdErrorListener = adErrorListener;
            this.applicationAdEventListener = adEventListener;
            this.applicationVideoAdPlayerCallback = videoAdPlayerCallback;
            this.imaSdkSettings = imaSdkSettings2;
            this.debugModeEnabled = z3;
        }
    }

    public static final class ServerSideAdInsertionConfiguration {
        public final AdViewProvider adViewProvider;
        public final AdErrorEvent.AdErrorListener applicationAdErrorListener;
        public final AdEvent.AdEventListener applicationAdEventListener;
        public final ImmutableList<CompanionAdSlot> companionAdSlots;
        public final boolean debugModeEnabled;
        public final boolean focusSkipButtonWhenAvailable;
        public final ImaSdkSettings imaSdkSettings;
        public final ImaServerSideAdInsertionMediaSource.StreamEventListener streamEventListener;

        public ServerSideAdInsertionConfiguration(AdViewProvider adViewProvider2, ImaSdkSettings imaSdkSettings2, ImaServerSideAdInsertionMediaSource.StreamEventListener streamEventListener2, AdEvent.AdEventListener adEventListener, AdErrorEvent.AdErrorListener adErrorListener, List<CompanionAdSlot> list, boolean z, boolean z2) {
            this.imaSdkSettings = imaSdkSettings2;
            this.adViewProvider = adViewProvider2;
            this.streamEventListener = streamEventListener2;
            this.applicationAdEventListener = adEventListener;
            this.applicationAdErrorListener = adErrorListener;
            this.companionAdSlots = ImmutableList.copyOf(list);
            this.focusSkipButtonWhenAvailable = z;
            this.debugModeEnabled = z2;
        }
    }

    public static FriendlyObstructionPurpose getFriendlyObstructionPurpose(int i) {
        if (i == 1) {
            return FriendlyObstructionPurpose.VIDEO_CONTROLS;
        }
        if (i == 2) {
            return FriendlyObstructionPurpose.CLOSE_AD;
        }
        if (i != 4) {
            return FriendlyObstructionPurpose.OTHER;
        }
        return FriendlyObstructionPurpose.NOT_VISIBLE;
    }

    public static long[] getAdGroupTimesUsForCuePoints(List<Float> list) {
        if (list.isEmpty()) {
            return new long[]{0};
        }
        int size = list.size();
        long[] jArr = new long[size];
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            double floatValue = (double) list.get(i2).floatValue();
            if (floatValue == -1.0d) {
                jArr[size - 1] = Long.MIN_VALUE;
            } else {
                jArr[i] = Math.round(floatValue * 1000000.0d);
                i++;
            }
        }
        Arrays.sort(jArr, 0, i);
        return jArr;
    }

    public static AdsRequest getAdsRequestForAdTagDataSpec(ImaFactory imaFactory, DataSpec dataSpec) throws IOException {
        AdsRequest createAdsRequest = imaFactory.createAdsRequest();
        if ("data".equals(dataSpec.uri.getScheme())) {
            DataSchemeDataSource dataSchemeDataSource = new DataSchemeDataSource();
            try {
                dataSchemeDataSource.open(dataSpec);
                createAdsRequest.setAdsResponse(Util.fromUtf8Bytes(DataSourceUtil.readToEnd(dataSchemeDataSource)));
            } finally {
                dataSchemeDataSource.close();
            }
        } else {
            createAdsRequest.setAdTagUrl(dataSpec.uri.toString());
        }
        return createAdsRequest;
    }

    public static boolean isAdGroupLoadError(AdError adError) {
        return adError.getErrorCode() == AdError.AdErrorCode.VAST_LINEAR_ASSET_MISMATCH || adError.getErrorCode() == AdError.AdErrorCode.UNKNOWN_ERROR;
    }

    public static Looper getImaLooper() {
        return Looper.getMainLooper();
    }

    public static String getStringForVideoProgressUpdate(VideoProgressUpdate videoProgressUpdate) {
        if (VideoProgressUpdate.VIDEO_TIME_NOT_READY.equals(videoProgressUpdate)) {
            return "not ready";
        }
        return Util.formatInvariant("%d ms of %d ms", Long.valueOf(videoProgressUpdate.getCurrentTimeMs()), Long.valueOf(videoProgressUpdate.getDurationMs()));
    }

    public static AdPlaybackState expandAdGroupPlaceholder(int i, long j, int i2, long j2, int i3, AdPlaybackState adPlaybackState) {
        Assertions.checkArgument(i2 < i3);
        long[] updateAdDurationAndPropagate = updateAdDurationAndPropagate(new long[i3], i2, j2, j);
        return adPlaybackState.withAdCount(i, updateAdDurationAndPropagate.length).withAdDurationsUs(i, updateAdDurationAndPropagate);
    }

    public static AdPlaybackState updateAdDurationInAdGroup(int i, int i2, long j, AdPlaybackState adPlaybackState) {
        AdPlaybackState.AdGroup adGroup = adPlaybackState.getAdGroup(i);
        Assertions.checkArgument(i2 < adGroup.durationsUs.length);
        return adPlaybackState.withAdDurationsUs(i, updateAdDurationAndPropagate(Arrays.copyOf(adGroup.durationsUs, adGroup.durationsUs.length), i2, j, adGroup.durationsUs[i2]));
    }

    private static long[] updateAdDurationAndPropagate(long[] jArr, int i, long j, long j2) {
        jArr[i] = j;
        int length = (i + 1) % jArr.length;
        if (jArr[length] == 0) {
            jArr[length] = Math.max(0, j2 - j);
        }
        return jArr;
    }

    public static long getWindowStartTimeUs(long j, long j2) {
        return Util.msToUs(j) + (j2 % 1000);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00b7, code lost:
        if ((r22 + r7) > (r10.timeUs + r14)) goto L_0x00bc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0109, code lost:
        if (r4.isLive() == false) goto L_0x0127;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x010b, code lost:
        r17 = r17 + r19;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.common.collect.ImmutableMap<java.lang.Object, androidx.media3.common.AdPlaybackState> splitAdPlaybackStateForPeriods(androidx.media3.common.AdPlaybackState r31, androidx.media3.common.Timeline r32) {
        /*
            r0 = r31
            r1 = r32
            boolean r2 = r32.isEmpty()
            r3 = 1
            r2 = r2 ^ r3
            androidx.media3.common.util.Assertions.checkArgument(r2)
            androidx.media3.common.Timeline$Period r2 = new androidx.media3.common.Timeline$Period
            r2.<init>()
            androidx.media3.common.Timeline$Window r4 = new androidx.media3.common.Timeline$Window
            r4.<init>()
            r5 = 0
            androidx.media3.common.Timeline$Window r4 = r1.getWindow(r5, r4)
            java.lang.Object r6 = r0.adsId
            java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r6)
            androidx.media3.common.AdPlaybackState r7 = new androidx.media3.common.AdPlaybackState
            long[] r8 = new long[r5]
            r7.<init>(r6, r8)
            boolean r8 = r4.isLive()
            if (r8 == 0) goto L_0x0040
            long r8 = r4.windowStartTimeMs
            long r10 = r4.positionInFirstPeriodUs
            long r8 = getWindowStartTimeUs(r8, r10)
            long r10 = r4.positionInFirstPeriodUs
            long r8 = r8 - r10
            androidx.media3.common.AdPlaybackState r7 = r7.withLivePostrollPlaceholderAppended()
            r13 = r7
            goto L_0x0043
        L_0x0040:
            r13 = r7
            r8 = 0
        L_0x0043:
            java.util.HashMap r11 = new java.util.HashMap
            r11.<init>()
            int r7 = r0.removedAdGroupCount
            r12 = r7
            r7 = r5
        L_0x004c:
            int r10 = r0.adGroupCount
            if (r12 >= r10) goto L_0x0138
            androidx.media3.common.AdPlaybackState$AdGroup r10 = r0.getAdGroup(r12)
            long r14 = r10.timeUs
            r16 = -9223372036854775808
            int r14 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r14 != 0) goto L_0x0067
            int r0 = r0.adGroupCount
            int r0 = r0 - r3
            if (r12 != r0) goto L_0x0062
            r5 = r3
        L_0x0062:
            androidx.media3.common.util.Assertions.checkState(r5)
            goto L_0x0138
        L_0x0067:
            long[] r14 = r10.durationsUs
            long r14 = androidx.media3.common.util.Util.sum(r14)
            r21 = r5
            r16 = r7
            r17 = r8
            r19 = 0
            r9 = r16
        L_0x0077:
            int r7 = r32.getPeriodCount()
            if (r9 >= r7) goto L_0x0120
            r1.getPeriod(r9, r2, r3)
            long r7 = r10.timeUs
            int r7 = (r17 > r7 ? 1 : (r17 == r7 ? 0 : -1))
            if (r7 >= 0) goto L_0x00a1
            java.lang.Object r7 = r2.uid
            java.lang.Object r7 = androidx.media3.common.util.Assertions.checkNotNull(r7)
            r11.put(r7, r13)
            long r7 = r2.durationUs
            long r17 = r17 + r7
            int r16 = r16 + 1
            r27 = r6
            r28 = r9
            r3 = r10
            r0 = r11
            r29 = r12
            r30 = r13
            goto L_0x0110
        L_0x00a1:
            long r22 = r17 + r19
            long r7 = r2.durationUs
            r24 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r24 = (r7 > r24 ? 1 : (r7 == r24 ? 0 : -1))
            if (r24 == 0) goto L_0x00ba
            long r25 = r22 + r7
            r27 = r6
            long r5 = r10.timeUs
            long r5 = r5 + r14
            int r5 = (r25 > r5 ? 1 : (r25 == r5 ? 0 : -1))
            if (r5 <= 0) goto L_0x00c9
            goto L_0x00bc
        L_0x00ba:
            r27 = r6
        L_0x00bc:
            if (r24 != 0) goto L_0x0122
            int r5 = (r19 > r14 ? 1 : (r19 == r14 ? 0 : -1))
            if (r5 >= 0) goto L_0x0122
            long r5 = r10.timeUs
            long r5 = r5 + r14
            int r5 = (r22 > r5 ? 1 : (r22 == r5 ? 0 : -1))
            if (r5 >= 0) goto L_0x0122
        L_0x00c9:
            java.lang.Object r5 = r2.uid
            java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r5)
            boolean r6 = r4.isLive()
            r24 = r7
            r7 = r27
            r8 = r10
            r28 = r9
            r3 = r10
            r9 = r22
            r0 = r11
            r29 = r12
            r11 = r24
            r30 = r13
            r13 = r6
            androidx.media3.common.AdPlaybackState r6 = splitAdGroupForPeriod(r7, r8, r9, r11, r13)
            r0.put(r5, r6)
            int r16 = r16 + 1
            int r5 = r21 + 1
            long r19 = r19 + r24
            int r6 = r3.originalCount
            int r7 = r3.count
            if (r6 <= r7) goto L_0x00fc
            int r6 = r3.count
            if (r6 == r5) goto L_0x0105
        L_0x00fc:
            long r22 = r22 + r24
            long r6 = r3.timeUs
            long r6 = r6 + r14
            int r6 = (r22 > r6 ? 1 : (r22 == r6 ? 0 : -1))
            if (r6 != 0) goto L_0x010e
        L_0x0105:
            boolean r3 = r4.isLive()
            if (r3 == 0) goto L_0x0127
            long r17 = r17 + r19
            goto L_0x0127
        L_0x010e:
            r21 = r5
        L_0x0110:
            int r9 = r28 + 1
            r11 = r0
            r10 = r3
            r6 = r27
            r12 = r29
            r13 = r30
            r3 = 1
            r5 = 0
            r0 = r31
            goto L_0x0077
        L_0x0120:
            r27 = r6
        L_0x0122:
            r0 = r11
            r29 = r12
            r30 = r13
        L_0x0127:
            r7 = r16
            r8 = r17
            int r12 = r29 + 1
            r11 = r0
            r6 = r27
            r13 = r30
            r3 = 1
            r5 = 0
            r0 = r31
            goto L_0x004c
        L_0x0138:
            r0 = r11
            r30 = r13
        L_0x013b:
            int r3 = r32.getPeriodCount()
            if (r7 >= r3) goto L_0x0153
            r3 = 1
            r1.getPeriod(r7, r2, r3)
            java.lang.Object r4 = r2.uid
            java.lang.Object r4 = androidx.media3.common.util.Assertions.checkNotNull(r4)
            r5 = r30
            r0.put(r4, r5)
            int r7 = r7 + 1
            goto L_0x013b
        L_0x0153:
            com.google.common.collect.ImmutableMap r0 = com.google.common.collect.ImmutableMap.copyOf(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.ima.ImaUtil.splitAdPlaybackStateForPeriods(androidx.media3.common.AdPlaybackState, androidx.media3.common.Timeline):com.google.common.collect.ImmutableMap");
    }

    private static AdPlaybackState splitAdGroupForPeriod(Object obj, AdPlaybackState.AdGroup adGroup, long j, long j2, boolean z) {
        long j3;
        AdPlaybackState.AdGroup adGroup2 = adGroup;
        long j4 = 0;
        AdPlaybackState withAdCount = new AdPlaybackState(Assertions.checkNotNull(obj), 0).withIsServerSideInserted(0, true).withAdCount(0, 1);
        if (z) {
            withAdCount = withAdCount.withLivePostrollPlaceholderAppended();
        }
        long j5 = 0;
        for (int i = 0; i < adGroup2.count; i++) {
            if (j2 != C.TIME_UNSET) {
                j3 = j2;
            } else {
                j3 = adGroup2.durationsUs[i];
            }
            j5 += adGroup2.durationsUs[i];
            if (j + j3 <= adGroup2.timeUs + j5 + 10000) {
                AdPlaybackState withAdDurationsUs = withAdCount.withAdDurationsUs(0, j3);
                if (z) {
                    j4 = j3;
                }
                AdPlaybackState withContentResumeOffsetUs = withAdDurationsUs.withContentResumeOffsetUs(0, j4);
                int i2 = adGroup2.states[i];
                if (i2 == 1) {
                    return withContentResumeOffsetUs.withAvailableAd(0, 0);
                }
                if (i2 == 2) {
                    return withContentResumeOffsetUs.withSkippedAd(0, 0);
                }
                if (i2 == 3) {
                    return withContentResumeOffsetUs.withPlayedAd(0, 0);
                }
                if (i2 != 4) {
                    return withContentResumeOffsetUs;
                }
                return withContentResumeOffsetUs.withAdLoadError(0, 0);
            }
        }
        return withAdCount;
    }

    public static AdPlaybackState maybeCorrectPreviouslyUnknownAdDurations(Timeline timeline, AdPlaybackState adPlaybackState) {
        int i;
        boolean z = false;
        Timeline.Window window = timeline.getWindow(0, new Timeline.Window());
        if (window.firstPeriodIndex == window.lastPeriodIndex || adPlaybackState.adGroupCount < 2) {
            return adPlaybackState;
        }
        Timeline.Period period = new Timeline.Period();
        int i2 = window.lastPeriodIndex;
        if (timeline.getPeriod(i2, period).durationUs == C.TIME_UNSET) {
            i2--;
            timeline.getPeriod(i2, period);
        }
        long windowStartTimeUs = getWindowStartTimeUs(window.windowStartTimeMs, window.positionInFirstPeriodUs);
        int adGroupIndexForPositionUs = adPlaybackState.getAdGroupIndexForPositionUs(period.positionInWindowUs + windowStartTimeUs, C.TIME_UNSET);
        if (adGroupIndexForPositionUs == -1) {
            return adPlaybackState;
        }
        AdPlaybackState.AdGroup adGroup = adPlaybackState.getAdGroup(adGroupIndexForPositionUs);
        long j = windowStartTimeUs - window.positionInFirstPeriodUs;
        if (adGroup.timeUs + adGroup.contentResumeOffsetUs <= j) {
            return adPlaybackState;
        }
        long j2 = adGroup.timeUs;
        int i3 = 0;
        while (j2 < j) {
            if (adGroup.states[i3] == 1) {
                return adPlaybackState;
            }
            j2 += adGroup.durationsUs[i3];
            i3++;
        }
        int i4 = window.firstPeriodIndex;
        while (true) {
            if (i4 > i2) {
                i4 = -1;
                break;
            } else if (adGroup.timeUs <= j) {
                break;
            } else {
                j += timeline.getPeriod(i4, period).durationUs;
                i4++;
            }
        }
        if (i4 != -1) {
            z = true;
        }
        Assertions.checkState(z);
        int i5 = i3;
        while (i5 < adGroup.durationsUs.length && (i = (i5 - i3) + i4) <= i2) {
            timeline.getPeriod(i, period);
            if (period.durationUs != adGroup.durationsUs[i5]) {
                adPlaybackState = updateAdDurationInAdGroup(adGroupIndexForPositionUs, i5, period.durationUs, adPlaybackState);
            }
            i5++;
        }
        return adPlaybackState.withContentResumeOffsetUs(adGroupIndexForPositionUs, Util.sum(adPlaybackState.getAdGroup(adGroupIndexForPositionUs).durationsUs));
    }

    public static long getAdGroupDurationUsForLiveAdPeriodIndex(Timeline timeline, AdPodInfo adPodInfo, int i, Timeline.Window window, Timeline.Period period) {
        long j;
        timeline.getPeriod(i, period);
        timeline.getWindow(period.windowIndex, window);
        Assertions.checkArgument(window.isLive());
        int adPosition = adPodInfo.getAdPosition() - 1;
        int i2 = i - adPosition;
        int totalAds = i + ((adPodInfo.getTotalAds() - adPosition) - 1);
        if (window.firstPeriodIndex <= i2 && totalAds < window.lastPeriodIndex) {
            Timeline.Period period2 = new Timeline.Period();
            j = 0;
            while (true) {
                if (i2 > totalAds) {
                    break;
                }
                long j2 = timeline.getPeriod(i2, period2).durationUs;
                if (j2 == C.TIME_UNSET) {
                    break;
                }
                j += j2;
                i2++;
            }
        }
        j = -9223372036854775807L;
        return j != C.TIME_UNSET ? j : secToUsRounded(adPodInfo.getMaxDuration());
    }

    public static AdPlaybackState handleAdPeriodRemovedFromTimeline(int i, Timeline timeline, AdPlaybackState adPlaybackState) {
        Timeline timeline2 = timeline;
        AdPlaybackState adPlaybackState2 = adPlaybackState;
        Timeline.Period period = timeline2.getPeriod(i, new Timeline.Period());
        Timeline.Window window = timeline2.getWindow(period.windowIndex, new Timeline.Window());
        long windowStartTimeUs = getWindowStartTimeUs(window.windowStartTimeMs, window.positionInFirstPeriodUs) + period.positionInWindowUs;
        int adGroupIndexForPositionUs = adPlaybackState2.getAdGroupIndexForPositionUs(windowStartTimeUs, C.TIME_UNSET);
        int i2 = -1;
        if (adGroupIndexForPositionUs != -1) {
            AdPlaybackState.AdGroup adGroup = adPlaybackState2.getAdGroup(adGroupIndexForPositionUs);
            if (adGroup.timeUs + adGroup.contentResumeOffsetUs <= windowStartTimeUs) {
                return markAdGroupAsPlayed(adGroupIndexForPositionUs, true, adPlaybackState2);
            }
            long j = 0;
            for (int i3 = 0; i3 < adGroup.states.length; i3++) {
                int i4 = adGroup.states[i3];
                if (i4 == 1) {
                    i2 = i3;
                }
                if (windowStartTimeUs <= adGroup.timeUs + j) {
                    if (windowStartTimeUs == adGroup.timeUs + j) {
                        if (i4 == 1 || i4 == 3) {
                            return adPlaybackState2;
                        }
                        if (i4 == 0 && i2 == i3 - 1) {
                            if (period.durationUs == C.TIME_UNSET) {
                                return adPlaybackState2;
                            }
                            AdPlaybackState updateAdDurationInAdGroup = updateAdDurationInAdGroup(adGroupIndexForPositionUs, i3, period.durationUs, adPlaybackState2);
                            return updateAdDurationInAdGroup.withContentResumeOffsetUs(adGroupIndexForPositionUs, Util.sum(updateAdDurationInAdGroup.getAdGroup(adGroupIndexForPositionUs).durationsUs));
                        }
                    }
                    AdPlaybackState markAdGroupAsPlayed = markAdGroupAsPlayed(adGroupIndexForPositionUs, false, adPlaybackState2);
                    return period.durationUs != C.TIME_UNSET ? addLiveAdBreak(windowStartTimeUs, period.durationUs, 1, period.durationUs, 1, markAdGroupAsPlayed) : markAdGroupAsPlayed;
                }
                if (i4 == 1 || i4 == 0) {
                    adPlaybackState2 = adPlaybackState2.withSkippedAd(adGroupIndexForPositionUs, i3);
                }
                j += adGroup.durationsUs[i3];
            }
        }
        return adPlaybackState2;
    }

    private static AdPlaybackState markAdGroupAsPlayed(int i, boolean z, AdPlaybackState adPlaybackState) {
        AdPlaybackState.AdGroup adGroup = adPlaybackState.getAdGroup(i);
        int length = adGroup.durationsUs.length;
        long[] jArr = new long[length];
        for (int i2 = 0; i2 < length; i2++) {
            jArr[i2] = z ? adGroup.durationsUs[i2] : 0;
            if (adGroup.states[i2] == 1 || adGroup.states[i2] == 0) {
                adPlaybackState = adPlaybackState.withSkippedAd(i, i2);
            }
        }
        return adPlaybackState.withAdDurationsUs(i, jArr).withContentResumeOffsetUs(i, Util.sum(jArr));
    }

    public static Pair<Integer, Integer> getAdGroupAndIndexInLiveMultiPeriodTimeline(int i, AdPlaybackState adPlaybackState, Timeline timeline) {
        Timeline.Window window = timeline.getWindow(0, new Timeline.Window());
        Assertions.checkArgument(window.isLive());
        Timeline.Period period = new Timeline.Period();
        timeline.getPeriod(i, period, true);
        long windowStartTimeUs = getWindowStartTimeUs(window.windowStartTimeMs, window.positionInFirstPeriodUs) + period.positionInWindowUs;
        int adGroupIndexForPositionUs = adPlaybackState.getAdGroupIndexForPositionUs(windowStartTimeUs, C.TIME_UNSET);
        if (adGroupIndexForPositionUs != -1) {
            AdPlaybackState.AdGroup adGroup = adPlaybackState.getAdGroup(adGroupIndexForPositionUs);
            for (int i2 = 0; i2 < adGroup.states.length; i2++) {
                if (adGroup.states[i2] == 1 || adGroup.states[i2] == 0) {
                    return new Pair<>(Integer.valueOf(adGroupIndexForPositionUs), Integer.valueOf(i2));
                }
            }
        }
        throw new IllegalStateException(String.format("No unplayed ad group found before or at the start time us %d of the period with index %d", new Object[]{Long.valueOf(windowStartTimeUs), Integer.valueOf(i)}));
    }

    public static Pair<Integer, Integer> getAdGroupAndIndexInVodMultiPeriodTimeline(int i, AdPlaybackState adPlaybackState, Timeline timeline) {
        int i2;
        int i3 = i;
        AdPlaybackState adPlaybackState2 = adPlaybackState;
        Timeline timeline2 = timeline;
        int i4 = 0;
        Timeline.Window window = timeline2.getWindow(0, new Timeline.Window());
        Assertions.checkArgument(timeline.getWindowCount() == 1);
        long windowStartTimeUs = window.isLive() ? getWindowStartTimeUs(window.windowStartTimeMs, window.positionInFirstPeriodUs) - window.positionInFirstPeriodUs : 0;
        Timeline.Period period = new Timeline.Period();
        int i5 = adPlaybackState2.removedAdGroupCount;
        int i6 = 0;
        while (i5 < adPlaybackState2.adGroupCount) {
            AdPlaybackState.AdGroup adGroup = adPlaybackState2.getAdGroup(i5);
            long sum = Util.sum(adGroup.durationsUs);
            int i7 = i4;
            int i8 = i6;
            long j = 0;
            while (true) {
                if (i6 >= Math.min(timeline.getPeriodCount(), i3 + 1)) {
                    i2 = i5;
                    break;
                }
                timeline2.getPeriod(i6, period, true);
                i2 = i5;
                if (windowStartTimeUs >= adGroup.timeUs) {
                    if (windowStartTimeUs + j + period.durationUs > adGroup.timeUs + sum) {
                        windowStartTimeUs += Math.min(j, adGroup.contentResumeOffsetUs);
                        break;
                    } else if (i6 == i3) {
                        return new Pair<>(Integer.valueOf(i2), Integer.valueOf(i7));
                    } else {
                        j += period.durationUs;
                        i7++;
                    }
                } else {
                    windowStartTimeUs += period.durationUs;
                }
                i8++;
                i6++;
                AdPlaybackState adPlaybackState3 = adPlaybackState;
                timeline2 = timeline;
                i5 = i2;
            }
            i5 = i2 + 1;
            adPlaybackState2 = adPlaybackState;
            timeline2 = timeline;
            i6 = i8;
            i4 = 0;
        }
        throw new IllegalStateException();
    }

    public static AdPlaybackState addLiveAdBreak(long j, long j2, int i, long j3, int i2, AdPlaybackState adPlaybackState) {
        int i3 = i2;
        AdPlaybackState adPlaybackState2 = adPlaybackState;
        Assertions.checkArgument(i > 0);
        long mediaPeriodPositionUsForContent = ServerSideAdInsertionUtil.getMediaPeriodPositionUsForContent(j, -1, adPlaybackState2);
        int adGroupIndexForPositionUs = adPlaybackState2.getAdGroupIndexForPositionUs(mediaPeriodPositionUsForContent, C.TIME_UNSET);
        if (adGroupIndexForPositionUs == -1) {
            long[] updateAdDurationAndPropagate = updateAdDurationAndPropagate(new long[(i3 - (i - 1))], 0, j2, j3);
            AdPlaybackState addAdGroupToAdPlaybackState = ServerSideAdInsertionUtil.addAdGroupToAdPlaybackState(adPlaybackState, j, Util.sum(updateAdDurationAndPropagate), updateAdDurationAndPropagate);
            int adGroupIndexForPositionUs2 = addAdGroupToAdPlaybackState.getAdGroupIndexForPositionUs(mediaPeriodPositionUsForContent, C.TIME_UNSET);
            if (adGroupIndexForPositionUs2 != -1) {
                return addAdGroupToAdPlaybackState.withAvailableAd(adGroupIndexForPositionUs2, 0).withOriginalAdCount(adGroupIndexForPositionUs2, i3);
            }
            return addAdGroupToAdPlaybackState;
        }
        AdPlaybackState.AdGroup adGroup = adPlaybackState2.getAdGroup(adGroupIndexForPositionUs);
        long[] copyOf = Arrays.copyOf(adGroup.durationsUs, adGroup.count);
        int nextUnavailableAdIndex = getNextUnavailableAdIndex(adGroup);
        if (adGroup.originalCount < i3 || nextUnavailableAdIndex == adGroup.count) {
            int i4 = nextUnavailableAdIndex + 1;
            int max = Math.max(i3, i4);
            adPlaybackState2 = adPlaybackState2.withAdCount(adGroupIndexForPositionUs, max).withOriginalAdCount(adGroupIndexForPositionUs, max);
            copyOf = Arrays.copyOf(copyOf, max);
            copyOf[nextUnavailableAdIndex] = j3;
            Arrays.fill(copyOf, i4, max, 0);
        }
        long j4 = j2;
        updateAdDurationAndPropagate(copyOf, nextUnavailableAdIndex, j4, Math.max(j4, copyOf[nextUnavailableAdIndex]));
        return adPlaybackState2.withAdDurationsUs(adGroupIndexForPositionUs, copyOf).withAvailableAd(adGroupIndexForPositionUs, nextUnavailableAdIndex).withContentResumeOffsetUs(adGroupIndexForPositionUs, Util.sum(copyOf));
    }

    public static AdPlaybackState splitAdGroup(AdPlaybackState.AdGroup adGroup, int i, int i2, AdPlaybackState adPlaybackState) {
        AdPlaybackState.AdGroup adGroup2 = adGroup;
        int i3 = i;
        int i4 = i2;
        int i5 = 0;
        Assertions.checkArgument(i4 > 0 && i4 < adGroup2.count);
        AdPlaybackState adPlaybackState2 = adPlaybackState;
        for (int i6 = 0; i6 < adGroup2.count - i4; i6++) {
            adPlaybackState2 = adPlaybackState2.withLastAdRemoved(i3);
        }
        AdPlaybackState.AdGroup adGroup3 = adPlaybackState2.getAdGroup(i3);
        long j = adGroup3.timeUs + adGroup3.contentResumeOffsetUs;
        int[] copyOfRange = Arrays.copyOfRange(adGroup2.states, i4, adGroup2.count);
        long[] copyOfRange2 = Arrays.copyOfRange(adGroup2.durationsUs, i4, adGroup2.count);
        long sum = Util.sum(copyOfRange2);
        AdPlaybackState adPlaybackState3 = adPlaybackState2;
        while (i5 < copyOfRange.length && copyOfRange[i5] == 1) {
            int i7 = i5 + 1;
            adPlaybackState3 = addLiveAdBreak(j, copyOfRange2[i5], i7, sum, copyOfRange2.length, adPlaybackState3);
            sum -= copyOfRange2[i5];
            i5 = i7;
        }
        return adPlaybackState3;
    }

    private static int getNextUnavailableAdIndex(AdPlaybackState.AdGroup adGroup) {
        for (int i = 0; i < adGroup.states.length; i++) {
            if (adGroup.states[i] == 0) {
                return i;
            }
        }
        return adGroup.states.length;
    }

    public static long secToUsRounded(double d) {
        return DoubleMath.roundToLong(BigDecimal.valueOf(d).scaleByPowerOfTen(6).doubleValue(), RoundingMode.HALF_UP);
    }

    public static long secToMsRounded(double d) {
        return DoubleMath.roundToLong(BigDecimal.valueOf(d).scaleByPowerOfTen(3).doubleValue(), RoundingMode.HALF_UP);
    }

    private ImaUtil() {
    }
}

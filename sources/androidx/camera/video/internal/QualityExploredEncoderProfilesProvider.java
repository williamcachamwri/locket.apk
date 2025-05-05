package androidx.camera.video.internal;

import android.util.Size;
import androidx.arch.core.util.Function;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.impl.EncoderProfilesProvider;
import androidx.camera.core.impl.EncoderProfilesProxy;
import androidx.camera.video.CapabilitiesByQuality;
import androidx.camera.video.Quality;
import androidx.camera.video.internal.encoder.VideoEncoderConfig;
import androidx.camera.video.internal.encoder.VideoEncoderInfo;
import androidx.camera.video.internal.utils.DynamicRangeUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class QualityExploredEncoderProfilesProvider implements EncoderProfilesProvider {
    private final EncoderProfilesProvider mBaseEncoderProfilesProvider;
    private final Set<Size> mCameraSupportedResolutions;
    private final Map<DynamicRange, CapabilitiesByQuality> mDynamicRangeToCapabilitiesMap = new HashMap();
    private final Map<Integer, EncoderProfilesProxy> mEncoderProfilesCache = new HashMap();
    private final Set<DynamicRange> mTargetDynamicRanges;
    private final Set<Quality> mTargetQualities;
    private final Function<VideoEncoderConfig, VideoEncoderInfo> mVideoEncoderInfoFinder;

    public QualityExploredEncoderProfilesProvider(EncoderProfilesProvider encoderProfilesProvider, Collection<Quality> collection, Collection<DynamicRange> collection2, Collection<Size> collection3, Function<VideoEncoderConfig, VideoEncoderInfo> function) {
        checkFullySpecifiedOrThrow(collection2);
        this.mBaseEncoderProfilesProvider = encoderProfilesProvider;
        this.mTargetQualities = new HashSet(collection);
        this.mTargetDynamicRanges = new HashSet(collection2);
        this.mCameraSupportedResolutions = new HashSet(collection3);
        this.mVideoEncoderInfoFinder = function;
    }

    public boolean hasProfile(int i) {
        return getProfilesInternal(i) != null;
    }

    public EncoderProfilesProxy getAll(int i) {
        return getProfilesInternal(i);
    }

    private EncoderProfilesProxy getProfilesInternal(int i) {
        if (this.mEncoderProfilesCache.containsKey(Integer.valueOf(i))) {
            return this.mEncoderProfilesCache.get(Integer.valueOf(i));
        }
        EncoderProfilesProxy all = this.mBaseEncoderProfilesProvider.getAll(i);
        Quality.ConstantQuality findQualityInTargetQualities = findQualityInTargetQualities(i);
        if (findQualityInTargetQualities != null && !hasMatchedVideoProfileForAllTargetDynamicRanges(all)) {
            all = mergeEncoderProfiles(all, exploreProfiles(findQualityInTargetQualities));
        }
        this.mEncoderProfilesCache.put(Integer.valueOf(i), all);
        return all;
    }

    private boolean hasMatchedVideoProfileForAllTargetDynamicRanges(EncoderProfilesProxy encoderProfilesProxy) {
        if (encoderProfilesProxy == null) {
            return false;
        }
        for (DynamicRange hasMatchedVideoProfileForDynamicRange : this.mTargetDynamicRanges) {
            if (!hasMatchedVideoProfileForDynamicRange(encoderProfilesProxy, hasMatchedVideoProfileForDynamicRange)) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0063, code lost:
        r6 = r5.getDefaultVideoProfile();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private androidx.camera.core.impl.EncoderProfilesProxy exploreProfiles(androidx.camera.video.Quality.ConstantQuality r12) {
        /*
            r11 = this;
            java.util.Set<androidx.camera.video.Quality> r0 = r11.mTargetQualities
            boolean r0 = r0.contains(r12)
            androidx.core.util.Preconditions.checkArgument(r0)
            androidx.camera.core.impl.EncoderProfilesProvider r0 = r11.mBaseEncoderProfilesProvider
            int r1 = r12.getValue()
            androidx.camera.core.impl.EncoderProfilesProxy r0 = r0.getAll(r1)
            java.util.List r12 = r12.getTypicalSizes()
            java.util.Iterator r12 = r12.iterator()
        L_0x001b:
            boolean r1 = r12.hasNext()
            if (r1 == 0) goto L_0x00c3
            java.lang.Object r1 = r12.next()
            android.util.Size r1 = (android.util.Size) r1
            java.util.Set<android.util.Size> r2 = r11.mCameraSupportedResolutions
            boolean r2 = r2.contains(r1)
            if (r2 != 0) goto L_0x0030
            goto L_0x001b
        L_0x0030:
            java.util.TreeMap r2 = new java.util.TreeMap
            androidx.camera.core.impl.utils.CompareSizesByArea r3 = new androidx.camera.core.impl.utils.CompareSizesByArea
            r3.<init>()
            r2.<init>(r3)
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Set<androidx.camera.core.DynamicRange> r4 = r11.mTargetDynamicRanges
            java.util.Iterator r4 = r4.iterator()
        L_0x0045:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x00a0
            java.lang.Object r5 = r4.next()
            androidx.camera.core.DynamicRange r5 = (androidx.camera.core.DynamicRange) r5
            boolean r6 = hasMatchedVideoProfileForDynamicRange(r0, r5)
            if (r6 == 0) goto L_0x0058
            goto L_0x0045
        L_0x0058:
            androidx.camera.video.CapabilitiesByQuality r5 = r11.getCapabilitiesByQualityFor(r5)
            androidx.camera.video.internal.VideoValidatedEncoderProfilesProxy r5 = r5.findNearestHigherSupportedEncoderProfilesFor(r1)
            if (r5 != 0) goto L_0x0063
            goto L_0x0045
        L_0x0063:
            androidx.camera.core.impl.EncoderProfilesProxy$VideoProfileProxy r6 = r5.getDefaultVideoProfile()
            androidx.camera.video.internal.encoder.VideoEncoderConfig r7 = androidx.camera.video.internal.config.VideoConfigUtil.toVideoEncoderConfig(r6)
            androidx.arch.core.util.Function<androidx.camera.video.internal.encoder.VideoEncoderConfig, androidx.camera.video.internal.encoder.VideoEncoderInfo> r8 = r11.mVideoEncoderInfoFinder
            java.lang.Object r7 = r8.apply(r7)
            androidx.camera.video.internal.encoder.VideoEncoderInfo r7 = (androidx.camera.video.internal.encoder.VideoEncoderInfo) r7
            if (r7 == 0) goto L_0x0045
            int r8 = r1.getWidth()
            int r9 = r1.getHeight()
            boolean r8 = r7.isSizeSupportedAllowSwapping(r8, r9)
            if (r8 != 0) goto L_0x0084
            goto L_0x0045
        L_0x0084:
            android.util.Size r8 = new android.util.Size
            int r9 = r6.getWidth()
            int r10 = r6.getHeight()
            r8.<init>(r9, r10)
            r2.put(r8, r5)
            android.util.Range r5 = r7.getSupportedBitrateRange()
            androidx.camera.core.impl.EncoderProfilesProxy$VideoProfileProxy r5 = androidx.camera.video.internal.utils.EncoderProfilesUtil.deriveVideoProfile(r6, r1, r5)
            r3.add(r5)
            goto L_0x0045
        L_0x00a0:
            boolean r4 = r3.isEmpty()
            if (r4 != 0) goto L_0x001b
            java.lang.Object r12 = androidx.camera.core.internal.utils.SizeUtil.findNearestHigherFor(r1, r2)
            androidx.camera.core.impl.EncoderProfilesProxy r12 = (androidx.camera.core.impl.EncoderProfilesProxy) r12
            java.lang.Object r12 = java.util.Objects.requireNonNull(r12)
            androidx.camera.core.impl.EncoderProfilesProxy r12 = (androidx.camera.core.impl.EncoderProfilesProxy) r12
            int r0 = r12.getDefaultDurationSeconds()
            int r1 = r12.getRecommendedFileFormat()
            java.util.List r12 = r12.getAudioProfiles()
            androidx.camera.core.impl.EncoderProfilesProxy$ImmutableEncoderProfilesProxy r12 = androidx.camera.core.impl.EncoderProfilesProxy.ImmutableEncoderProfilesProxy.create(r0, r1, r12, r3)
            return r12
        L_0x00c3:
            r12 = 0
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.video.internal.QualityExploredEncoderProfilesProvider.exploreProfiles(androidx.camera.video.Quality$ConstantQuality):androidx.camera.core.impl.EncoderProfilesProxy");
    }

    private Quality.ConstantQuality findQualityInTargetQualities(int i) {
        Iterator<Quality> it = this.mTargetQualities.iterator();
        while (it.hasNext()) {
            Quality.ConstantQuality constantQuality = (Quality.ConstantQuality) it.next();
            if (constantQuality.getValue() == i) {
                return constantQuality;
            }
        }
        return null;
    }

    private CapabilitiesByQuality getCapabilitiesByQualityFor(DynamicRange dynamicRange) {
        if (this.mDynamicRangeToCapabilitiesMap.containsKey(dynamicRange)) {
            return (CapabilitiesByQuality) Objects.requireNonNull(this.mDynamicRangeToCapabilitiesMap.get(dynamicRange));
        }
        CapabilitiesByQuality capabilitiesByQuality = new CapabilitiesByQuality(new DynamicRangeMatchedEncoderProfilesProvider(this.mBaseEncoderProfilesProvider, dynamicRange));
        this.mDynamicRangeToCapabilitiesMap.put(dynamicRange, capabilitiesByQuality);
        return capabilitiesByQuality;
    }

    private static EncoderProfilesProxy mergeEncoderProfiles(EncoderProfilesProxy encoderProfilesProxy, EncoderProfilesProxy encoderProfilesProxy2) {
        int i;
        int i2;
        if (encoderProfilesProxy == null && encoderProfilesProxy2 == null) {
            return null;
        }
        if (encoderProfilesProxy != null) {
            i = encoderProfilesProxy.getDefaultDurationSeconds();
        } else {
            i = encoderProfilesProxy2.getDefaultDurationSeconds();
        }
        if (encoderProfilesProxy != null) {
            i2 = encoderProfilesProxy.getRecommendedFileFormat();
        } else {
            i2 = encoderProfilesProxy2.getRecommendedFileFormat();
        }
        List<EncoderProfilesProxy.AudioProfileProxy> audioProfiles = encoderProfilesProxy != null ? encoderProfilesProxy.getAudioProfiles() : encoderProfilesProxy2.getAudioProfiles();
        ArrayList arrayList = new ArrayList();
        if (encoderProfilesProxy != null) {
            arrayList.addAll(encoderProfilesProxy.getVideoProfiles());
        }
        if (encoderProfilesProxy2 != null) {
            arrayList.addAll(encoderProfilesProxy2.getVideoProfiles());
        }
        return EncoderProfilesProxy.ImmutableEncoderProfilesProxy.create(i, i2, audioProfiles, arrayList);
    }

    private static boolean hasMatchedVideoProfileForDynamicRange(EncoderProfilesProxy encoderProfilesProxy, DynamicRange dynamicRange) {
        if (encoderProfilesProxy == null) {
            return false;
        }
        for (EncoderProfilesProxy.VideoProfileProxy isHdrSettingsMatched : encoderProfilesProxy.getVideoProfiles()) {
            if (DynamicRangeUtil.isHdrSettingsMatched(isHdrSettingsMatched, dynamicRange)) {
                return true;
            }
        }
        return false;
    }

    private static void checkFullySpecifiedOrThrow(Collection<DynamicRange> collection) {
        for (DynamicRange next : collection) {
            if (!next.isFullySpecified()) {
                throw new IllegalArgumentException("Contains non-fully specified DynamicRange: " + next);
            }
        }
    }
}

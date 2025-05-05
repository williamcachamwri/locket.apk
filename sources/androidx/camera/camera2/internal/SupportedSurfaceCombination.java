package androidx.camera.camera2.internal;

import android.content.Context;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Build;
import android.util.Pair;
import android.util.Range;
import android.util.Rational;
import android.util.Size;
import androidx.camera.camera2.internal.compat.CameraAccessExceptionCompat;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.camera2.internal.compat.CameraManagerCompat;
import androidx.camera.camera2.internal.compat.workaround.ExtraSupportedSurfaceCombinationsContainer;
import androidx.camera.camera2.internal.compat.workaround.ResolutionCorrector;
import androidx.camera.camera2.internal.compat.workaround.TargetAspectRatio;
import androidx.camera.core.CameraUnavailableException;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.impl.AttachedSurfaceInfo;
import androidx.camera.core.impl.CameraMode;
import androidx.camera.core.impl.StreamSpec;
import androidx.camera.core.impl.SurfaceCombination;
import androidx.camera.core.impl.SurfaceConfig;
import androidx.camera.core.impl.SurfaceSizeDefinition;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.utils.AspectRatioUtil;
import androidx.camera.core.impl.utils.CompareSizesByArea;
import androidx.camera.core.internal.utils.SizeUtil;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class SupportedSurfaceCombination {
    private static final String TAG = "SupportedSurfaceCombination";
    private final CamcorderProfileHelper mCamcorderProfileHelper;
    private final String mCameraId;
    private final CameraCharacteristicsCompat mCharacteristics;
    private final List<SurfaceCombination> mConcurrentSurfaceCombinations = new ArrayList();
    private final DisplayInfoManager mDisplayInfoManager;
    private final DynamicRangeResolver mDynamicRangeResolver;
    private final ExtraSupportedSurfaceCombinationsContainer mExtraSupportedSurfaceCombinationsContainer;
    private final Map<FeatureSettings, List<SurfaceCombination>> mFeatureSettingsToSupportedCombinationsMap = new HashMap();
    private final int mHardwareLevel;
    private boolean mIsBurstCaptureSupported;
    private boolean mIsConcurrentCameraModeSupported;
    private boolean mIsPreviewStabilizationSupported;
    private boolean mIsRawSupported;
    private boolean mIsStreamUseCaseSupported;
    private boolean mIsUltraHighResolutionSensorSupported;
    private final List<SurfaceCombination> mPreviewStabilizationSurfaceCombinations = new ArrayList();
    private final ResolutionCorrector mResolutionCorrector;
    private final List<SurfaceCombination> mSurfaceCombinations = new ArrayList();
    private final List<SurfaceCombination> mSurfaceCombinations10Bit = new ArrayList();
    private final List<SurfaceCombination> mSurfaceCombinationsStreamUseCase = new ArrayList();
    private final List<SurfaceCombination> mSurfaceCombinationsUltraHdr = new ArrayList();
    SurfaceSizeDefinition mSurfaceSizeDefinition;
    List<Integer> mSurfaceSizeDefinitionFormats;
    private final TargetAspectRatio mTargetAspectRatio;
    private final List<SurfaceCombination> mUltraHighSurfaceCombinations = new ArrayList();

    private void checkCustomization() {
    }

    SupportedSurfaceCombination(Context context, String str, CameraManagerCompat cameraManagerCompat, CamcorderProfileHelper camcorderProfileHelper) throws CameraUnavailableException {
        this.mIsRawSupported = false;
        this.mIsBurstCaptureSupported = false;
        this.mIsConcurrentCameraModeSupported = false;
        this.mIsStreamUseCaseSupported = false;
        this.mIsUltraHighResolutionSensorSupported = false;
        this.mIsPreviewStabilizationSupported = false;
        this.mSurfaceSizeDefinitionFormats = new ArrayList();
        this.mTargetAspectRatio = new TargetAspectRatio();
        this.mResolutionCorrector = new ResolutionCorrector();
        String str2 = (String) Preconditions.checkNotNull(str);
        this.mCameraId = str2;
        this.mCamcorderProfileHelper = (CamcorderProfileHelper) Preconditions.checkNotNull(camcorderProfileHelper);
        this.mExtraSupportedSurfaceCombinationsContainer = new ExtraSupportedSurfaceCombinationsContainer();
        this.mDisplayInfoManager = DisplayInfoManager.getInstance(context);
        try {
            CameraCharacteristicsCompat cameraCharacteristicsCompat = cameraManagerCompat.getCameraCharacteristicsCompat(str2);
            this.mCharacteristics = cameraCharacteristicsCompat;
            Integer num = (Integer) cameraCharacteristicsCompat.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
            this.mHardwareLevel = num != null ? num.intValue() : 2;
            int[] iArr = (int[]) cameraCharacteristicsCompat.get(CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES);
            if (iArr != null) {
                for (int i : iArr) {
                    if (i == 3) {
                        this.mIsRawSupported = true;
                    } else if (i == 6) {
                        this.mIsBurstCaptureSupported = true;
                    } else if (Build.VERSION.SDK_INT >= 31 && i == 16) {
                        this.mIsUltraHighResolutionSensorSupported = true;
                    }
                }
            }
            DynamicRangeResolver dynamicRangeResolver = new DynamicRangeResolver(this.mCharacteristics);
            this.mDynamicRangeResolver = dynamicRangeResolver;
            generateSupportedCombinationList();
            if (this.mIsUltraHighResolutionSensorSupported) {
                generateUltraHighSupportedCombinationList();
            }
            boolean hasSystemFeature = context.getPackageManager().hasSystemFeature("android.hardware.camera.concurrent");
            this.mIsConcurrentCameraModeSupported = hasSystemFeature;
            if (hasSystemFeature) {
                generateConcurrentSupportedCombinationList();
            }
            if (dynamicRangeResolver.is10BitDynamicRangeSupported()) {
                generate10BitSupportedCombinationList();
            }
            if (isUltraHdrSupported()) {
                generateUltraHdrSupportedCombinationList();
            }
            boolean isStreamUseCaseSupported = StreamUseCaseUtil.isStreamUseCaseSupported(this.mCharacteristics);
            this.mIsStreamUseCaseSupported = isStreamUseCaseSupported;
            if (isStreamUseCaseSupported) {
                generateStreamUseCaseSupportedCombinationList();
            }
            boolean isPreviewStabilizationSupported = VideoStabilizationUtil.isPreviewStabilizationSupported(this.mCharacteristics);
            this.mIsPreviewStabilizationSupported = isPreviewStabilizationSupported;
            if (isPreviewStabilizationSupported) {
                generatePreviewStabilizationSupportedCombinationList();
            }
            generateSurfaceSizeDefinition();
            checkCustomization();
        } catch (CameraAccessExceptionCompat e) {
            throw CameraUnavailableExceptionHelper.createFrom(e);
        }
    }

    /* access modifiers changed from: package-private */
    public String getCameraId() {
        return this.mCameraId;
    }

    /* access modifiers changed from: package-private */
    public boolean isRawSupported() {
        return this.mIsRawSupported;
    }

    /* access modifiers changed from: package-private */
    public boolean isBurstCaptureSupported() {
        return this.mIsBurstCaptureSupported;
    }

    private boolean isUltraHdrSupported() {
        int[] outputFormats = this.mCharacteristics.getStreamConfigurationMapCompat().getOutputFormats();
        if (outputFormats == null) {
            return false;
        }
        for (int i : outputFormats) {
            if (i == 4101) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean checkSupported(FeatureSettings featureSettings, List<SurfaceConfig> list) {
        boolean z = false;
        for (SurfaceCombination orderedSupportedSurfaceConfigList : getSurfaceCombinationsByFeatureSettings(featureSettings)) {
            if (orderedSupportedSurfaceConfigList.getOrderedSupportedSurfaceConfigList(list) != null) {
                z = true;
                continue;
            } else {
                z = false;
                continue;
            }
            if (z) {
                break;
            }
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public List<SurfaceConfig> getOrderedSupportedStreamUseCaseSurfaceConfigList(FeatureSettings featureSettings, List<SurfaceConfig> list) {
        if (!StreamUseCaseUtil.shouldUseStreamUseCase(featureSettings)) {
            return null;
        }
        for (SurfaceCombination orderedSupportedSurfaceConfigList : this.mSurfaceCombinationsStreamUseCase) {
            List<SurfaceConfig> orderedSupportedSurfaceConfigList2 = orderedSupportedSurfaceConfigList.getOrderedSupportedSurfaceConfigList(list);
            if (orderedSupportedSurfaceConfigList2 != null) {
                return orderedSupportedSurfaceConfigList2;
            }
        }
        return null;
    }

    private List<SurfaceCombination> getSurfaceCombinationsByFeatureSettings(FeatureSettings featureSettings) {
        if (this.mFeatureSettingsToSupportedCombinationsMap.containsKey(featureSettings)) {
            return this.mFeatureSettingsToSupportedCombinationsMap.get(featureSettings);
        }
        List<SurfaceCombination> arrayList = new ArrayList<>();
        if (featureSettings.isUltraHdrOn()) {
            if (featureSettings.getCameraMode() == 0) {
                arrayList.addAll(this.mSurfaceCombinationsUltraHdr);
            }
        } else if (featureSettings.getRequiredMaxBitDepth() == 8) {
            int cameraMode = featureSettings.getCameraMode();
            if (cameraMode == 1) {
                arrayList = this.mConcurrentSurfaceCombinations;
            } else if (cameraMode != 2) {
                arrayList.addAll(featureSettings.isPreviewStabilizationOn() ? this.mPreviewStabilizationSurfaceCombinations : this.mSurfaceCombinations);
            } else {
                arrayList.addAll(this.mUltraHighSurfaceCombinations);
                arrayList.addAll(this.mSurfaceCombinations);
            }
        } else if (featureSettings.getRequiredMaxBitDepth() == 10 && featureSettings.getCameraMode() == 0) {
            arrayList.addAll(this.mSurfaceCombinations10Bit);
        }
        this.mFeatureSettingsToSupportedCombinationsMap.put(featureSettings, arrayList);
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public SurfaceConfig transformSurfaceConfig(int i, int i2, Size size) {
        return SurfaceConfig.transformSurfaceConfig(i, i2, size, getUpdatedSurfaceSizeDefinitionByFormat(i2));
    }

    static int getMaxFrameRate(CameraCharacteristicsCompat cameraCharacteristicsCompat, int i, Size size) {
        try {
            return (int) (1.0E9d / ((double) ((StreamConfigurationMap) cameraCharacteristicsCompat.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)).getOutputMinFrameDuration(i, size)));
        } catch (Exception unused) {
            return 0;
        }
    }

    private static int getRangeLength(Range<Integer> range) {
        return (range.getUpper().intValue() - range.getLower().intValue()) + 1;
    }

    private static int getRangeDistance(Range<Integer> range, Range<Integer> range2) {
        Preconditions.checkState(!range.contains(range2.getUpper()) && !range.contains(range2.getLower()), "Ranges must not intersect");
        if (range.getLower().intValue() > range2.getUpper().intValue()) {
            return range.getLower().intValue() - range2.getUpper().intValue();
        }
        return range2.getLower().intValue() - range.getUpper().intValue();
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0058 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.util.Range<java.lang.Integer> compareIntersectingRanges(android.util.Range<java.lang.Integer> r8, android.util.Range<java.lang.Integer> r9, android.util.Range<java.lang.Integer> r10) {
        /*
            android.util.Range r0 = r9.intersect(r8)
            int r0 = getRangeLength(r0)
            double r0 = (double) r0
            android.util.Range r8 = r10.intersect(r8)
            int r8 = getRangeLength(r8)
            double r2 = (double) r8
            int r8 = getRangeLength(r10)
            double r4 = (double) r8
            double r4 = r2 / r4
            int r8 = getRangeLength(r9)
            double r6 = (double) r8
            double r6 = r0 / r6
            int r8 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            r0 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            if (r8 <= 0) goto L_0x002f
            int r8 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r8 >= 0) goto L_0x002e
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 < 0) goto L_0x0058
        L_0x002e:
            return r10
        L_0x002f:
            if (r8 != 0) goto L_0x004f
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 <= 0) goto L_0x0036
            return r10
        L_0x0036:
            if (r8 != 0) goto L_0x0058
            java.lang.Comparable r8 = r10.getLower()
            java.lang.Integer r8 = (java.lang.Integer) r8
            int r8 = r8.intValue()
            java.lang.Comparable r0 = r9.getLower()
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            if (r8 <= r0) goto L_0x0058
            return r10
        L_0x004f:
            int r8 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r8 >= 0) goto L_0x0058
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 <= 0) goto L_0x0058
            return r10
        L_0x0058:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.camera2.internal.SupportedSurfaceCombination.compareIntersectingRanges(android.util.Range, android.util.Range, android.util.Range):android.util.Range");
    }

    private Range<Integer> getClosestSupportedDeviceFrameRate(Range<Integer> range, int i) {
        if (range == null || range.equals(StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED)) {
            return StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED;
        }
        Range<Integer>[] rangeArr = (Range[]) this.mCharacteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES);
        if (rangeArr == null) {
            return StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED;
        }
        Range range2 = new Range(Integer.valueOf(Math.min(range.getLower().intValue(), i)), Integer.valueOf(Math.min(range.getUpper().intValue(), i)));
        Range<Integer> range3 = StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED;
        int i2 = 0;
        for (Range<Integer> range4 : rangeArr) {
            if (i >= range4.getLower().intValue()) {
                if (range3.equals(StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED)) {
                    range3 = range4;
                }
                if (range4.equals(range2)) {
                    return range4;
                }
                try {
                    int rangeLength = getRangeLength(range4.intersect(range2));
                    if (i2 == 0) {
                        i2 = rangeLength;
                    } else {
                        if (rangeLength >= i2) {
                            range3 = compareIntersectingRanges(range2, range3, range4);
                            i2 = getRangeLength(range2.intersect(range3));
                        }
                        range4 = range3;
                    }
                } catch (IllegalArgumentException unused) {
                    if (i2 == 0) {
                        if (getRangeDistance(range4, range2) >= getRangeDistance(range3, range2)) {
                            if (getRangeDistance(range4, range2) == getRangeDistance(range3, range2)) {
                                if (range4.getLower().intValue() <= range3.getUpper().intValue() && getRangeLength(range4) >= getRangeLength(range3)) {
                                }
                            }
                        }
                    }
                }
                range3 = range4;
            }
        }
        return range3;
    }

    private Range<Integer> getUpdatedTargetFramerate(Range<Integer> range, Range<Integer> range2) {
        if (range2 == null) {
            return range;
        }
        if (range != null) {
            try {
                return range2.intersect(range);
            } catch (IllegalArgumentException unused) {
            }
        }
        return range2;
    }

    private int getUpdatedMaximumFps(int i, int i2, Size size) {
        return Math.min(i, getMaxFrameRate(this.mCharacteristics, i2, size));
    }

    /* access modifiers changed from: package-private */
    public Pair<Map<UseCaseConfig<?>, StreamSpec>, Map<AttachedSurfaceInfo, StreamSpec>> getSuggestedStreamSpecifications(int i, List<AttachedSurfaceInfo> list, Map<UseCaseConfig<?>, List<Size>> map, boolean z, boolean z2) {
        List<Integer> list2;
        int i2;
        Range<Integer> range;
        HashMap hashMap;
        HashMap hashMap2;
        Map<UseCaseConfig<?>, DynamicRange> map2;
        String str;
        String str2;
        List<SurfaceConfig> list3;
        HashMap hashMap3;
        HashMap hashMap4;
        String str3;
        String str4;
        HashMap hashMap5;
        HashMap hashMap6;
        List list4;
        List list5;
        HashMap hashMap7;
        int i3;
        int i4;
        String str5;
        List<AttachedSurfaceInfo> list6 = list;
        Map<UseCaseConfig<?>, List<Size>> map3 = map;
        refreshPreviewSize();
        ArrayList arrayList = new ArrayList(map.keySet());
        List<Integer> useCasesPriorityOrder = getUseCasesPriorityOrder(arrayList);
        Map<UseCaseConfig<?>, DynamicRange> resolveAndValidateDynamicRanges = this.mDynamicRangeResolver.resolveAndValidateDynamicRanges(list6, arrayList, useCasesPriorityOrder);
        FeatureSettings createFeatureSettings = createFeatureSettings(i, resolveAndValidateDynamicRanges, z, isUltraHdrOn(list, map));
        boolean isUseCasesCombinationSupported = isUseCasesCombinationSupported(createFeatureSettings, list6, map3);
        String str6 = ".  May be attempting to bind too many use cases. Existing surfaces: ";
        String str7 = " New configs: ";
        String str8 = "No supported surface combination is found for camera device - Id : ";
        if (isUseCasesCombinationSupported) {
            Range<Integer> targetFpsRange = getTargetFpsRange(list6, arrayList, useCasesPriorityOrder);
            Map<UseCaseConfig<?>, List<Size>> filterSupportedSizes = filterSupportedSizes(map3, createFeatureSettings, targetFpsRange);
            ArrayList arrayList2 = new ArrayList();
            for (Integer intValue : useCasesPriorityOrder) {
                UseCaseConfig useCaseConfig = (UseCaseConfig) arrayList.get(intValue.intValue());
                arrayList2.add(applyResolutionSelectionOrderRelatedWorkarounds(filterSupportedSizes.get(useCaseConfig), useCaseConfig.getInputFormat()));
            }
            List<List<Size>> allPossibleSizeArrangements = getAllPossibleSizeArrangements(arrayList2);
            HashMap hashMap8 = new HashMap();
            HashMap hashMap9 = new HashMap();
            HashMap hashMap10 = new HashMap();
            HashMap hashMap11 = new HashMap();
            boolean containsZslUseCase = StreamUseCaseUtil.containsZslUseCase(list6, arrayList);
            int maxSupportedFpsFromAttachedSurfaces = getMaxSupportedFpsFromAttachedSurfaces(list6);
            HashMap hashMap12 = hashMap11;
            Range<Integer> range2 = null;
            if (!this.mIsStreamUseCaseSupported || containsZslUseCase) {
                hashMap3 = hashMap10;
                hashMap2 = hashMap9;
                hashMap = hashMap8;
                range = targetFpsRange;
                list2 = useCasesPriorityOrder;
                map2 = resolveAndValidateDynamicRanges;
                i2 = maxSupportedFpsFromAttachedSurfaces;
                str2 = str8;
                str = str7;
                hashMap4 = hashMap12;
                list3 = null;
            } else {
                Iterator<List<Size>> it = allPossibleSizeArrangements.iterator();
                List<SurfaceConfig> list7 = null;
                while (true) {
                    if (!it.hasNext()) {
                        hashMap2 = hashMap9;
                        hashMap = hashMap8;
                        range = targetFpsRange;
                        list2 = useCasesPriorityOrder;
                        map2 = resolveAndValidateDynamicRanges;
                        i2 = maxSupportedFpsFromAttachedSurfaces;
                        str2 = str8;
                        str = str7;
                        str5 = str6;
                        hashMap4 = hashMap12;
                        hashMap3 = hashMap10;
                        break;
                    }
                    HashMap hashMap13 = hashMap12;
                    HashMap hashMap14 = hashMap10;
                    hashMap2 = hashMap9;
                    hashMap = hashMap8;
                    map2 = resolveAndValidateDynamicRanges;
                    range = targetFpsRange;
                    str2 = str8;
                    int i5 = maxSupportedFpsFromAttachedSurfaces;
                    i2 = maxSupportedFpsFromAttachedSurfaces;
                    str = str7;
                    list2 = useCasesPriorityOrder;
                    str5 = str6;
                    list7 = getOrderedSupportedStreamUseCaseSurfaceConfigList(createFeatureSettings, (List) getSurfaceConfigListAndFpsCeiling(i, list, it.next(), arrayList, useCasesPriorityOrder, i5, hashMap14, hashMap13).first);
                    hashMap3 = hashMap14;
                    hashMap4 = hashMap13;
                    if (list7 != null && !StreamUseCaseUtil.areCaptureTypesEligible(hashMap3, hashMap4, list7)) {
                        list7 = null;
                    }
                    if (list7 != null) {
                        if (StreamUseCaseUtil.areStreamUseCasesAvailableForSurfaceConfigs(this.mCharacteristics, list7)) {
                            break;
                        }
                        list7 = null;
                    }
                    hashMap3.clear();
                    hashMap4.clear();
                    hashMap12 = hashMap4;
                    hashMap10 = hashMap3;
                    str6 = str5;
                    str8 = str2;
                    str7 = str;
                    resolveAndValidateDynamicRanges = map2;
                    hashMap9 = hashMap2;
                    hashMap8 = hashMap;
                    targetFpsRange = range;
                    maxSupportedFpsFromAttachedSurfaces = i2;
                    useCasesPriorityOrder = list2;
                }
                if (list7 != null || isUseCasesCombinationSupported) {
                    list3 = list7;
                } else {
                    throw new IllegalArgumentException(str2 + this.mCameraId + str5 + list6 + str + arrayList);
                }
            }
            Iterator<List<Size>> it2 = allPossibleSizeArrangements.iterator();
            boolean z3 = false;
            int i6 = Integer.MAX_VALUE;
            int i7 = Integer.MAX_VALUE;
            boolean z4 = false;
            boolean z5 = false;
            List list8 = null;
            List list9 = null;
            while (true) {
                if (!it2.hasNext()) {
                    int i8 = i6;
                    int i9 = i7;
                    str3 = str2;
                    str4 = str;
                    hashMap5 = hashMap4;
                    hashMap6 = hashMap3;
                    list4 = list8;
                    list5 = list9;
                    break;
                }
                List next = it2.next();
                int i10 = i6;
                int i11 = i7;
                str4 = str;
                hashMap5 = hashMap4;
                str3 = str2;
                hashMap6 = hashMap3;
                Pair<List<SurfaceConfig>, Integer> surfaceConfigListAndFpsCeiling = getSurfaceConfigListAndFpsCeiling(i, list, next, arrayList, list2, i2, (Map<Integer, AttachedSurfaceInfo>) null, (Map<Integer, UseCaseConfig<?>>) null);
                List list10 = (List) surfaceConfigListAndFpsCeiling.first;
                i7 = ((Integer) surfaceConfigListAndFpsCeiling.second).intValue();
                int i12 = i2;
                boolean z6 = range == null || i12 <= i7 || i7 >= range.getLower().intValue();
                if (z4 || !checkSupported(createFeatureSettings, list10)) {
                    i3 = i11;
                    i4 = Integer.MAX_VALUE;
                } else {
                    i3 = i11;
                    i4 = Integer.MAX_VALUE;
                    if (i3 == Integer.MAX_VALUE || i3 < i7) {
                        i3 = i7;
                        list8 = next;
                    }
                    if (z6) {
                        if (z5) {
                            list5 = list9;
                            list4 = next;
                            i6 = i10;
                            break;
                        }
                        i3 = i7;
                        z4 = true;
                        list8 = next;
                    }
                }
                if (list3 == null || z5 || getOrderedSupportedStreamUseCaseSurfaceConfigList(createFeatureSettings, list10) == null) {
                    i6 = i10;
                } else {
                    int i13 = i10;
                    if (i13 != i4 && i13 >= i7) {
                        i6 = i13;
                    } else {
                        i6 = i7;
                        list9 = next;
                    }
                    if (z6) {
                        i6 = i7;
                        if (z4) {
                            i7 = i3;
                            list4 = list8;
                            list5 = next;
                            break;
                        }
                        z5 = true;
                        list9 = next;
                    } else {
                        continue;
                    }
                }
                i2 = i12;
                i7 = i3;
                hashMap3 = hashMap6;
                hashMap4 = hashMap5;
                str2 = str3;
                str = str4;
                int i14 = i4;
            }
            if (list4 != null) {
                if (range != null) {
                    range2 = getClosestSupportedDeviceFrameRate(range, i7);
                }
                Range<Integer> range3 = range2;
                Iterator it3 = arrayList.iterator();
                while (it3.hasNext()) {
                    UseCaseConfig useCaseConfig2 = (UseCaseConfig) it3.next();
                    List<Integer> list11 = list2;
                    Map<UseCaseConfig<?>, DynamicRange> map4 = map2;
                    Iterator it4 = it3;
                    StreamSpec.Builder zslDisabled = StreamSpec.builder((Size) list4.get(list11.indexOf(Integer.valueOf(arrayList.indexOf(useCaseConfig2))))).setDynamicRange((DynamicRange) Preconditions.checkNotNull(map4.get(useCaseConfig2))).setImplementationOptions(StreamUseCaseUtil.getStreamSpecImplementationOptions(useCaseConfig2)).setZslDisabled(z2);
                    if (range3 != null) {
                        zslDisabled.setExpectedFrameRateRange(range3);
                    }
                    hashMap2.put(useCaseConfig2, zslDisabled.build());
                    it3 = it4;
                    list2 = list11;
                    map2 = map4;
                    range3 = range3;
                }
                HashMap hashMap15 = hashMap2;
                if (list3 != null && i7 == i6 && list4.size() == list5.size()) {
                    int i15 = 0;
                    while (true) {
                        if (i15 >= list4.size()) {
                            break;
                        } else if (!((Size) list4.get(i15)).equals(list5.get(i15))) {
                            z3 = true;
                            break;
                        } else {
                            i15++;
                        }
                    }
                    if (!z3) {
                        hashMap7 = hashMap;
                        if (!StreamUseCaseUtil.populateStreamUseCaseStreamSpecOptionWithInteropOverride(this.mCharacteristics, list6, hashMap15, hashMap7)) {
                            StreamUseCaseUtil.populateStreamUseCaseStreamSpecOptionWithSupportedSurfaceConfigs(hashMap15, hashMap7, hashMap6, hashMap5, list3);
                        }
                        return new Pair<>(hashMap15, hashMap7);
                    }
                }
                hashMap7 = hashMap;
                return new Pair<>(hashMap15, hashMap7);
            }
            throw new IllegalArgumentException(str3 + this.mCameraId + " and Hardware level: " + this.mHardwareLevel + ". May be the specified resolution is too large and not supported. Existing surfaces: " + list6 + str4 + arrayList);
        }
        throw new IllegalArgumentException(str8 + this.mCameraId + str6 + list6 + str7 + arrayList);
    }

    private static boolean isUltraHdrOn(List<AttachedSurfaceInfo> list, Map<UseCaseConfig<?>, List<Size>> map) {
        for (AttachedSurfaceInfo imageFormat : list) {
            if (imageFormat.getImageFormat() == 4101) {
                return true;
            }
        }
        for (UseCaseConfig<?> inputFormat : map.keySet()) {
            if (inputFormat.getInputFormat() == 4101) {
                return true;
            }
        }
        return false;
    }

    private FeatureSettings createFeatureSettings(int i, Map<UseCaseConfig<?>, DynamicRange> map, boolean z, boolean z2) {
        int requiredMaxBitDepth = getRequiredMaxBitDepth(map);
        if (i != 0 && z2) {
            throw new IllegalArgumentException(String.format("Camera device id is %s. Ultra HDR is not currently supported in %s camera mode.", new Object[]{this.mCameraId, CameraMode.toLabelString(i)}));
        } else if (i == 0 || requiredMaxBitDepth != 10) {
            return FeatureSettings.of(i, requiredMaxBitDepth, z, z2);
        } else {
            throw new IllegalArgumentException(String.format("Camera device id is %s. 10 bit dynamic range is not currently supported in %s camera mode.", new Object[]{this.mCameraId, CameraMode.toLabelString(i)}));
        }
    }

    private boolean isUseCasesCombinationSupported(FeatureSettings featureSettings, List<AttachedSurfaceInfo> list, Map<UseCaseConfig<?>, List<Size>> map) {
        ArrayList arrayList = new ArrayList();
        for (AttachedSurfaceInfo surfaceConfig : list) {
            arrayList.add(surfaceConfig.getSurfaceConfig());
        }
        CompareSizesByArea compareSizesByArea = new CompareSizesByArea();
        for (UseCaseConfig next : map.keySet()) {
            List list2 = map.get(next);
            Preconditions.checkArgument(list2 != null && !list2.isEmpty(), "No available output size is found for " + next + ".");
            int inputFormat = next.getInputFormat();
            arrayList.add(SurfaceConfig.transformSurfaceConfig(featureSettings.getCameraMode(), inputFormat, (Size) Collections.min(list2, compareSizesByArea), getUpdatedSurfaceSizeDefinitionByFormat(inputFormat)));
        }
        return checkSupported(featureSettings, arrayList);
    }

    private Range<Integer> getTargetFpsRange(List<AttachedSurfaceInfo> list, List<UseCaseConfig<?>> list2, List<Integer> list3) {
        Range<Integer> range = null;
        for (AttachedSurfaceInfo targetFrameRate : list) {
            range = getUpdatedTargetFramerate(targetFrameRate.getTargetFrameRate(), range);
        }
        for (Integer intValue : list3) {
            range = getUpdatedTargetFramerate(list2.get(intValue.intValue()).getTargetFrameRate((Range<Integer>) null), range);
        }
        return range;
    }

    private int getMaxSupportedFpsFromAttachedSurfaces(List<AttachedSurfaceInfo> list) {
        int i = Integer.MAX_VALUE;
        for (AttachedSurfaceInfo next : list) {
            i = getUpdatedMaximumFps(i, next.getImageFormat(), next.getSize());
        }
        return i;
    }

    private Map<UseCaseConfig<?>, List<Size>> filterSupportedSizes(Map<UseCaseConfig<?>, List<Size>> map, FeatureSettings featureSettings, Range<Integer> range) {
        HashMap hashMap = new HashMap();
        for (UseCaseConfig next : map.keySet()) {
            ArrayList arrayList = new ArrayList();
            HashMap hashMap2 = new HashMap();
            for (Size size : map.get(next)) {
                int inputFormat = next.getInputFormat();
                SurfaceConfig.ConfigSize configSize = SurfaceConfig.transformSurfaceConfig(featureSettings.getCameraMode(), inputFormat, size, getUpdatedSurfaceSizeDefinitionByFormat(inputFormat)).getConfigSize();
                int maxFrameRate = range != null ? getMaxFrameRate(this.mCharacteristics, inputFormat, size) : Integer.MAX_VALUE;
                Set set = (Set) hashMap2.get(configSize);
                if (set == null) {
                    set = new HashSet();
                    hashMap2.put(configSize, set);
                }
                if (!set.contains(Integer.valueOf(maxFrameRate))) {
                    arrayList.add(size);
                    set.add(Integer.valueOf(maxFrameRate));
                }
            }
            hashMap.put(next, arrayList);
        }
        return hashMap;
    }

    private Pair<List<SurfaceConfig>, Integer> getSurfaceConfigListAndFpsCeiling(int i, List<AttachedSurfaceInfo> list, List<Size> list2, List<UseCaseConfig<?>> list3, List<Integer> list4, int i2, Map<Integer, AttachedSurfaceInfo> map, Map<Integer, UseCaseConfig<?>> map2) {
        ArrayList arrayList = new ArrayList();
        for (AttachedSurfaceInfo next : list) {
            arrayList.add(next.getSurfaceConfig());
            if (map != null) {
                map.put(Integer.valueOf(arrayList.size() - 1), next);
            }
        }
        for (int i3 = 0; i3 < list2.size(); i3++) {
            Size size = list2.get(i3);
            UseCaseConfig useCaseConfig = list3.get(list4.get(i3).intValue());
            int inputFormat = useCaseConfig.getInputFormat();
            arrayList.add(SurfaceConfig.transformSurfaceConfig(i, inputFormat, size, getUpdatedSurfaceSizeDefinitionByFormat(inputFormat)));
            if (map2 != null) {
                map2.put(Integer.valueOf(arrayList.size() - 1), useCaseConfig);
            }
            i2 = getUpdatedMaximumFps(i2, useCaseConfig.getInputFormat(), size);
        }
        return new Pair<>(arrayList, Integer.valueOf(i2));
    }

    /* access modifiers changed from: package-private */
    public List<Size> applyResolutionSelectionOrderRelatedWorkarounds(List<Size> list, int i) {
        Rational rational;
        int i2 = this.mTargetAspectRatio.get(this.mCameraId, this.mCharacteristics);
        if (i2 == 0) {
            rational = AspectRatioUtil.ASPECT_RATIO_4_3;
        } else if (i2 == 1) {
            rational = AspectRatioUtil.ASPECT_RATIO_16_9;
        } else if (i2 != 2) {
            rational = null;
        } else {
            Size maximumSize = getUpdatedSurfaceSizeDefinitionByFormat(256).getMaximumSize(256);
            rational = new Rational(maximumSize.getWidth(), maximumSize.getHeight());
        }
        if (rational != null) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (Size next : list) {
                if (AspectRatioUtil.hasMatchingAspectRatio(next, rational)) {
                    arrayList.add(next);
                } else {
                    arrayList2.add(next);
                }
            }
            arrayList2.addAll(0, arrayList);
            list = arrayList2;
        }
        return this.mResolutionCorrector.insertOrPrioritize(SurfaceConfig.getConfigType(i), list);
    }

    private static int getRequiredMaxBitDepth(Map<UseCaseConfig<?>, DynamicRange> map) {
        for (DynamicRange bitDepth : map.values()) {
            if (bitDepth.getBitDepth() == 10) {
                return 10;
            }
        }
        return 8;
    }

    private static List<Integer> getUseCasesPriorityOrder(List<UseCaseConfig<?>> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        for (UseCaseConfig<?> surfaceOccupancyPriority : list) {
            int surfaceOccupancyPriority2 = surfaceOccupancyPriority.getSurfaceOccupancyPriority(0);
            if (!arrayList2.contains(Integer.valueOf(surfaceOccupancyPriority2))) {
                arrayList2.add(Integer.valueOf(surfaceOccupancyPriority2));
            }
        }
        Collections.sort(arrayList2);
        Collections.reverse(arrayList2);
        for (Integer intValue : arrayList2) {
            int intValue2 = intValue.intValue();
            for (UseCaseConfig next : list) {
                if (intValue2 == next.getSurfaceOccupancyPriority(0)) {
                    arrayList.add(Integer.valueOf(list.indexOf(next)));
                }
            }
        }
        return arrayList;
    }

    private List<List<Size>> getAllPossibleSizeArrangements(List<List<Size>> list) {
        int i = 1;
        for (List<Size> size : list) {
            i *= size.size();
        }
        if (i != 0) {
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < i; i2++) {
                arrayList.add(new ArrayList());
            }
            int size2 = i / list.get(0).size();
            int i3 = i;
            for (int i4 = 0; i4 < list.size(); i4++) {
                List list2 = list.get(i4);
                for (int i5 = 0; i5 < i; i5++) {
                    ((List) arrayList.get(i5)).add((Size) list2.get((i5 % i3) / size2));
                }
                if (i4 < list.size() - 1) {
                    i3 = size2;
                    size2 /= list.get(i4 + 1).size();
                }
            }
            return arrayList;
        }
        throw new IllegalArgumentException("Failed to find supported resolutions.");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: android.util.Size} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.util.Size getMaxOutputSizeByFormat(android.hardware.camera2.params.StreamConfigurationMap r4, int r5, boolean r6) {
        /*
            r3 = this;
            r0 = 34
            if (r5 != r0) goto L_0x000b
            java.lang.Class<android.graphics.SurfaceTexture> r0 = android.graphics.SurfaceTexture.class
            android.util.Size[] r0 = r4.getOutputSizes(r0)
            goto L_0x000f
        L_0x000b:
            android.util.Size[] r0 = r4.getOutputSizes(r5)
        L_0x000f:
            if (r0 == 0) goto L_0x0050
            int r1 = r0.length
            if (r1 != 0) goto L_0x0015
            goto L_0x0050
        L_0x0015:
            androidx.camera.core.impl.utils.CompareSizesByArea r1 = new androidx.camera.core.impl.utils.CompareSizesByArea
            r1.<init>()
            java.util.List r0 = java.util.Arrays.asList(r0)
            java.lang.Object r0 = java.util.Collections.max(r0, r1)
            android.util.Size r0 = (android.util.Size) r0
            android.util.Size r2 = androidx.camera.core.internal.utils.SizeUtil.RESOLUTION_ZERO
            if (r6 == 0) goto L_0x003c
            android.util.Size[] r4 = androidx.camera.camera2.internal.SupportedSurfaceCombination.Api23Impl.getHighResolutionOutputSizes(r4, r5)
            if (r4 == 0) goto L_0x003c
            int r5 = r4.length
            if (r5 <= 0) goto L_0x003c
            java.util.List r4 = java.util.Arrays.asList(r4)
            java.lang.Object r4 = java.util.Collections.max(r4, r1)
            r2 = r4
            android.util.Size r2 = (android.util.Size) r2
        L_0x003c:
            r4 = 2
            android.util.Size[] r4 = new android.util.Size[r4]
            r5 = 0
            r4[r5] = r0
            r5 = 1
            r4[r5] = r2
            java.util.List r4 = java.util.Arrays.asList(r4)
            java.lang.Object r4 = java.util.Collections.max(r4, r1)
            android.util.Size r4 = (android.util.Size) r4
            return r4
        L_0x0050:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.camera2.internal.SupportedSurfaceCombination.getMaxOutputSizeByFormat(android.hardware.camera2.params.StreamConfigurationMap, int, boolean):android.util.Size");
    }

    private void generateSupportedCombinationList() {
        this.mSurfaceCombinations.addAll(GuaranteedConfigurationsUtil.generateSupportedCombinationList(this.mHardwareLevel, this.mIsRawSupported, this.mIsBurstCaptureSupported));
        this.mSurfaceCombinations.addAll(this.mExtraSupportedSurfaceCombinationsContainer.get(this.mCameraId));
    }

    private void generateUltraHighSupportedCombinationList() {
        this.mUltraHighSurfaceCombinations.addAll(GuaranteedConfigurationsUtil.getUltraHighResolutionSupportedCombinationList());
    }

    private void generateConcurrentSupportedCombinationList() {
        this.mConcurrentSurfaceCombinations.addAll(GuaranteedConfigurationsUtil.getConcurrentSupportedCombinationList());
    }

    private void generate10BitSupportedCombinationList() {
        this.mSurfaceCombinations10Bit.addAll(GuaranteedConfigurationsUtil.get10BitSupportedCombinationList());
    }

    private void generateUltraHdrSupportedCombinationList() {
        this.mSurfaceCombinationsUltraHdr.addAll(GuaranteedConfigurationsUtil.getUltraHdrSupportedCombinationList());
    }

    private void generateStreamUseCaseSupportedCombinationList() {
        if (Build.VERSION.SDK_INT >= 33) {
            this.mSurfaceCombinationsStreamUseCase.addAll(GuaranteedConfigurationsUtil.getStreamUseCaseSupportedCombinationList());
        }
    }

    private void generatePreviewStabilizationSupportedCombinationList() {
        if (Build.VERSION.SDK_INT >= 33) {
            this.mPreviewStabilizationSurfaceCombinations.addAll(GuaranteedConfigurationsUtil.getPreviewStabilizationSupportedCombinationList());
        }
    }

    private void generateSurfaceSizeDefinition() {
        this.mSurfaceSizeDefinition = SurfaceSizeDefinition.create(SizeUtil.RESOLUTION_VGA, new HashMap(), this.mDisplayInfoManager.getPreviewSize(), new HashMap(), getRecordSize(), new HashMap(), new HashMap());
    }

    /* access modifiers changed from: package-private */
    public SurfaceSizeDefinition getUpdatedSurfaceSizeDefinitionByFormat(int i) {
        if (!this.mSurfaceSizeDefinitionFormats.contains(Integer.valueOf(i))) {
            updateS720pOrS1440pSizeByFormat(this.mSurfaceSizeDefinition.getS720pSizeMap(), SizeUtil.RESOLUTION_720P, i);
            updateS720pOrS1440pSizeByFormat(this.mSurfaceSizeDefinition.getS1440pSizeMap(), SizeUtil.RESOLUTION_1440P, i);
            updateMaximumSizeByFormat(this.mSurfaceSizeDefinition.getMaximumSizeMap(), i);
            updateUltraMaximumSizeByFormat(this.mSurfaceSizeDefinition.getUltraMaximumSizeMap(), i);
            this.mSurfaceSizeDefinitionFormats.add(Integer.valueOf(i));
        }
        return this.mSurfaceSizeDefinition;
    }

    private void updateS720pOrS1440pSizeByFormat(Map<Integer, Size> map, Size size, int i) {
        if (this.mIsConcurrentCameraModeSupported) {
            Size maxOutputSizeByFormat = getMaxOutputSizeByFormat(this.mCharacteristics.getStreamConfigurationMapCompat().toStreamConfigurationMap(), i, false);
            Integer valueOf = Integer.valueOf(i);
            if (maxOutputSizeByFormat != null) {
                size = (Size) Collections.min(Arrays.asList(new Size[]{size, maxOutputSizeByFormat}), new CompareSizesByArea());
            }
            map.put(valueOf, size);
        }
    }

    private void updateMaximumSizeByFormat(Map<Integer, Size> map, int i) {
        Size maxOutputSizeByFormat = getMaxOutputSizeByFormat(this.mCharacteristics.getStreamConfigurationMapCompat().toStreamConfigurationMap(), i, true);
        if (maxOutputSizeByFormat != null) {
            map.put(Integer.valueOf(i), maxOutputSizeByFormat);
        }
    }

    private void updateUltraMaximumSizeByFormat(Map<Integer, Size> map, int i) {
        StreamConfigurationMap streamConfigurationMap;
        if (Build.VERSION.SDK_INT >= 31 && this.mIsUltraHighResolutionSensorSupported && (streamConfigurationMap = (StreamConfigurationMap) this.mCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP_MAXIMUM_RESOLUTION)) != null) {
            map.put(Integer.valueOf(i), getMaxOutputSizeByFormat(streamConfigurationMap, i, true));
        }
    }

    private void refreshPreviewSize() {
        this.mDisplayInfoManager.refresh();
        if (this.mSurfaceSizeDefinition == null) {
            generateSurfaceSizeDefinition();
            return;
        }
        this.mSurfaceSizeDefinition = SurfaceSizeDefinition.create(this.mSurfaceSizeDefinition.getAnalysisSize(), this.mSurfaceSizeDefinition.getS720pSizeMap(), this.mDisplayInfoManager.getPreviewSize(), this.mSurfaceSizeDefinition.getS1440pSizeMap(), this.mSurfaceSizeDefinition.getRecordSize(), this.mSurfaceSizeDefinition.getMaximumSizeMap(), this.mSurfaceSizeDefinition.getUltraMaximumSizeMap());
    }

    private Size getRecordSize() {
        try {
            int parseInt = Integer.parseInt(this.mCameraId);
            CamcorderProfile camcorderProfile = this.mCamcorderProfileHelper.hasProfile(parseInt, 1) ? this.mCamcorderProfileHelper.get(parseInt, 1) : null;
            if (camcorderProfile != null) {
                return new Size(camcorderProfile.videoFrameWidth, camcorderProfile.videoFrameHeight);
            }
            return getRecordSizeByHasProfile(parseInt);
        } catch (NumberFormatException unused) {
            return getRecordSizeFromStreamConfigurationMap();
        }
    }

    private Size getRecordSizeFromStreamConfigurationMap() {
        Size[] outputSizes = this.mCharacteristics.getStreamConfigurationMapCompat().toStreamConfigurationMap().getOutputSizes(MediaRecorder.class);
        if (outputSizes == null) {
            return SizeUtil.RESOLUTION_480P;
        }
        Arrays.sort(outputSizes, new CompareSizesByArea(true));
        for (Size size : outputSizes) {
            if (size.getWidth() <= SizeUtil.RESOLUTION_1080P.getWidth() && size.getHeight() <= SizeUtil.RESOLUTION_1080P.getHeight()) {
                return size;
            }
        }
        return SizeUtil.RESOLUTION_480P;
    }

    private Size getRecordSizeByHasProfile(int i) {
        CamcorderProfile camcorderProfile;
        Size size = SizeUtil.RESOLUTION_480P;
        if (this.mCamcorderProfileHelper.hasProfile(i, 10)) {
            camcorderProfile = this.mCamcorderProfileHelper.get(i, 10);
        } else if (this.mCamcorderProfileHelper.hasProfile(i, 8)) {
            camcorderProfile = this.mCamcorderProfileHelper.get(i, 8);
        } else if (this.mCamcorderProfileHelper.hasProfile(i, 12)) {
            camcorderProfile = this.mCamcorderProfileHelper.get(i, 12);
        } else if (this.mCamcorderProfileHelper.hasProfile(i, 6)) {
            camcorderProfile = this.mCamcorderProfileHelper.get(i, 6);
        } else if (this.mCamcorderProfileHelper.hasProfile(i, 5)) {
            camcorderProfile = this.mCamcorderProfileHelper.get(i, 5);
        } else {
            camcorderProfile = this.mCamcorderProfileHelper.hasProfile(i, 4) ? this.mCamcorderProfileHelper.get(i, 4) : null;
        }
        return camcorderProfile != null ? new Size(camcorderProfile.videoFrameWidth, camcorderProfile.videoFrameHeight) : size;
    }

    static class Api23Impl {
        private Api23Impl() {
        }

        static Size[] getHighResolutionOutputSizes(StreamConfigurationMap streamConfigurationMap, int i) {
            return streamConfigurationMap.getHighResolutionOutputSizes(i);
        }
    }

    static abstract class FeatureSettings {
        /* access modifiers changed from: package-private */
        public abstract int getCameraMode();

        /* access modifiers changed from: package-private */
        public abstract int getRequiredMaxBitDepth();

        /* access modifiers changed from: package-private */
        public abstract boolean isPreviewStabilizationOn();

        /* access modifiers changed from: package-private */
        public abstract boolean isUltraHdrOn();

        FeatureSettings() {
        }

        static FeatureSettings of(int i, int i2, boolean z, boolean z2) {
            return new AutoValue_SupportedSurfaceCombination_FeatureSettings(i, i2, z, z2);
        }
    }
}

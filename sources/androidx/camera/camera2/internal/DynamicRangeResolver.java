package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraCharacteristics;
import android.os.Build;
import android.text.TextUtils;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.camera2.internal.compat.params.DynamicRangeConversions;
import androidx.camera.camera2.internal.compat.params.DynamicRangesCompat;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.AttachedSurfaceInfo;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

final class DynamicRangeResolver {
    private static final String TAG = "DynamicRangeResolver";
    private final CameraCharacteristicsCompat mCharacteristics;
    private final DynamicRangesCompat mDynamicRangesInfo;
    private final boolean mIs10BitSupported;

    DynamicRangeResolver(CameraCharacteristicsCompat cameraCharacteristicsCompat) {
        this.mCharacteristics = cameraCharacteristicsCompat;
        this.mDynamicRangesInfo = DynamicRangesCompat.fromCameraCharacteristics(cameraCharacteristicsCompat);
        int[] iArr = (int[]) cameraCharacteristicsCompat.get(CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES);
        boolean z = false;
        if (iArr != null) {
            int length = iArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                } else if (iArr[i] == 18) {
                    z = true;
                    break;
                } else {
                    i++;
                }
            }
        }
        this.mIs10BitSupported = z;
    }

    /* access modifiers changed from: package-private */
    public boolean is10BitDynamicRangeSupported() {
        return this.mIs10BitSupported;
    }

    /* access modifiers changed from: package-private */
    public Map<UseCaseConfig<?>, DynamicRange> resolveAndValidateDynamicRanges(List<AttachedSurfaceInfo> list, List<UseCaseConfig<?>> list2, List<Integer> list3) {
        LinkedHashSet<DynamicRange> linkedHashSet = new LinkedHashSet<>();
        for (AttachedSurfaceInfo dynamicRange : list) {
            linkedHashSet.add(dynamicRange.getDynamicRange());
        }
        Set<DynamicRange> supportedDynamicRanges = this.mDynamicRangesInfo.getSupportedDynamicRanges();
        HashSet hashSet = new HashSet(supportedDynamicRanges);
        for (DynamicRange updateConstraints : linkedHashSet) {
            updateConstraints(hashSet, updateConstraints, this.mDynamicRangesInfo);
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (Integer intValue : list3) {
            UseCaseConfig useCaseConfig = list2.get(intValue.intValue());
            DynamicRange dynamicRange2 = useCaseConfig.getDynamicRange();
            if (isFullyUnspecified(dynamicRange2)) {
                arrayList3.add(useCaseConfig);
            } else if (isPartiallySpecified(dynamicRange2)) {
                arrayList2.add(useCaseConfig);
            } else {
                arrayList.add(useCaseConfig);
            }
        }
        HashMap hashMap = new HashMap();
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        ArrayList<UseCaseConfig> arrayList4 = new ArrayList<>();
        arrayList4.addAll(arrayList);
        arrayList4.addAll(arrayList2);
        arrayList4.addAll(arrayList3);
        for (UseCaseConfig useCaseConfig2 : arrayList4) {
            DynamicRange resolveDynamicRangeAndUpdateConstraints = resolveDynamicRangeAndUpdateConstraints(supportedDynamicRanges, linkedHashSet, linkedHashSet2, useCaseConfig2, hashSet);
            hashMap.put(useCaseConfig2, resolveDynamicRangeAndUpdateConstraints);
            if (!linkedHashSet.contains(resolveDynamicRangeAndUpdateConstraints)) {
                linkedHashSet2.add(resolveDynamicRangeAndUpdateConstraints);
            }
        }
        return hashMap;
    }

    private DynamicRange resolveDynamicRangeAndUpdateConstraints(Set<DynamicRange> set, Set<DynamicRange> set2, Set<DynamicRange> set3, UseCaseConfig<?> useCaseConfig, Set<DynamicRange> set4) {
        DynamicRange dynamicRange = useCaseConfig.getDynamicRange();
        DynamicRange resolveDynamicRange = resolveDynamicRange(dynamicRange, set4, set2, set3, useCaseConfig.getTargetName());
        if (resolveDynamicRange != null) {
            updateConstraints(set4, resolveDynamicRange, this.mDynamicRangesInfo);
            return resolveDynamicRange;
        }
        throw new IllegalArgumentException(String.format("Unable to resolve supported dynamic range. The dynamic range may not be supported on the device or may not be allowed concurrently with other attached use cases.\nUse case:\n  %s\nRequested dynamic range:\n  %s\nSupported dynamic ranges:\n  %s\nConstrained set of concurrent dynamic ranges:\n  %s", new Object[]{useCaseConfig.getTargetName(), dynamicRange, TextUtils.join("\n  ", set), TextUtils.join("\n  ", set4)}));
    }

    private DynamicRange resolveDynamicRange(DynamicRange dynamicRange, Set<DynamicRange> set, Set<DynamicRange> set2, Set<DynamicRange> set3, String str) {
        DynamicRange dynamicRange2;
        if (!dynamicRange.isFullySpecified()) {
            int encoding = dynamicRange.getEncoding();
            int bitDepth = dynamicRange.getBitDepth();
            if (encoding != 1 || bitDepth != 0) {
                DynamicRange findSupportedHdrMatch = findSupportedHdrMatch(dynamicRange, set2, set);
                if (findSupportedHdrMatch != null) {
                    Logger.d(TAG, String.format("Resolved dynamic range for use case %s from existing attached surface.\n%s\n->\n%s", new Object[]{str, dynamicRange, findSupportedHdrMatch}));
                    return findSupportedHdrMatch;
                }
                DynamicRange findSupportedHdrMatch2 = findSupportedHdrMatch(dynamicRange, set3, set);
                if (findSupportedHdrMatch2 != null) {
                    Logger.d(TAG, String.format("Resolved dynamic range for use case %s from concurrently bound use case.\n%s\n->\n%s", new Object[]{str, dynamicRange, findSupportedHdrMatch2}));
                    return findSupportedHdrMatch2;
                } else if (canResolveWithinConstraints(dynamicRange, DynamicRange.SDR, set)) {
                    Logger.d(TAG, String.format("Resolved dynamic range for use case %s to no compatible HDR dynamic ranges.\n%s\n->\n%s", new Object[]{str, dynamicRange, DynamicRange.SDR}));
                    return DynamicRange.SDR;
                } else {
                    if (encoding == 2 && (bitDepth == 10 || bitDepth == 0)) {
                        LinkedHashSet linkedHashSet = new LinkedHashSet();
                        if (Build.VERSION.SDK_INT >= 33) {
                            dynamicRange2 = Api33Impl.getRecommended10BitDynamicRange(this.mCharacteristics);
                            if (dynamicRange2 != null) {
                                linkedHashSet.add(dynamicRange2);
                            }
                        } else {
                            dynamicRange2 = null;
                        }
                        linkedHashSet.add(DynamicRange.HLG_10_BIT);
                        DynamicRange findSupportedHdrMatch3 = findSupportedHdrMatch(dynamicRange, linkedHashSet, set);
                        if (findSupportedHdrMatch3 != null) {
                            Object[] objArr = new Object[4];
                            objArr[0] = str;
                            objArr[1] = Objects.equals(findSupportedHdrMatch3, dynamicRange2) ? "recommended" : "required";
                            objArr[2] = dynamicRange;
                            objArr[3] = findSupportedHdrMatch3;
                            Logger.d(TAG, String.format("Resolved dynamic range for use case %s from %s 10-bit supported dynamic range.\n%s\n->\n%s", objArr));
                            return findSupportedHdrMatch3;
                        }
                    }
                    for (DynamicRange next : set) {
                        Preconditions.checkState(next.isFullySpecified(), "Candidate dynamic range must be fully specified.");
                        if (!next.equals(DynamicRange.SDR) && canResolve(dynamicRange, next)) {
                            Logger.d(TAG, String.format("Resolved dynamic range for use case %s from validated dynamic range constraints or supported HDR dynamic ranges.\n%s\n->\n%s", new Object[]{str, dynamicRange, next}));
                            return next;
                        }
                    }
                    return null;
                }
            } else if (set.contains(DynamicRange.SDR)) {
                return DynamicRange.SDR;
            } else {
                return null;
            }
        } else if (set.contains(dynamicRange)) {
            return dynamicRange;
        } else {
            return null;
        }
    }

    private static void updateConstraints(Set<DynamicRange> set, DynamicRange dynamicRange, DynamicRangesCompat dynamicRangesCompat) {
        Preconditions.checkState(!set.isEmpty(), "Cannot update already-empty constraints.");
        Set<DynamicRange> dynamicRangeCaptureRequestConstraints = dynamicRangesCompat.getDynamicRangeCaptureRequestConstraints(dynamicRange);
        if (!dynamicRangeCaptureRequestConstraints.isEmpty()) {
            HashSet hashSet = new HashSet(set);
            set.retainAll(dynamicRangeCaptureRequestConstraints);
            if (set.isEmpty()) {
                throw new IllegalArgumentException(String.format("Constraints of dynamic range cannot be combined with existing constraints.\nDynamic range:\n  %s\nConstraints:\n  %s\nExisting constraints:\n  %s", new Object[]{dynamicRange, TextUtils.join("\n  ", dynamicRangeCaptureRequestConstraints), TextUtils.join("\n  ", hashSet)}));
            }
        }
    }

    private static DynamicRange findSupportedHdrMatch(DynamicRange dynamicRange, Collection<DynamicRange> collection, Set<DynamicRange> set) {
        if (dynamicRange.getEncoding() == 1) {
            return null;
        }
        for (DynamicRange next : collection) {
            Preconditions.checkNotNull(next, "Fully specified DynamicRange cannot be null.");
            int encoding = next.getEncoding();
            Preconditions.checkState(next.isFullySpecified(), "Fully specified DynamicRange must have fully defined encoding.");
            if (encoding != 1 && canResolveWithinConstraints(dynamicRange, next, set)) {
                return next;
            }
        }
        return null;
    }

    static final class Api33Impl {
        private Api33Impl() {
        }

        static DynamicRange getRecommended10BitDynamicRange(CameraCharacteristicsCompat cameraCharacteristicsCompat) {
            Long l = (Long) cameraCharacteristicsCompat.get(CameraCharacteristics.REQUEST_RECOMMENDED_TEN_BIT_DYNAMIC_RANGE_PROFILE);
            if (l != null) {
                return DynamicRangeConversions.profileToDynamicRange(l.longValue());
            }
            return null;
        }
    }

    private static boolean isFullyUnspecified(DynamicRange dynamicRange) {
        return Objects.equals(dynamicRange, DynamicRange.UNSPECIFIED);
    }

    private static boolean isPartiallySpecified(DynamicRange dynamicRange) {
        return dynamicRange.getEncoding() == 2 || (dynamicRange.getEncoding() != 0 && dynamicRange.getBitDepth() == 0) || (dynamicRange.getEncoding() == 0 && dynamicRange.getBitDepth() != 0);
    }

    private static boolean canResolveWithinConstraints(DynamicRange dynamicRange, DynamicRange dynamicRange2, Set<DynamicRange> set) {
        if (set.contains(dynamicRange2)) {
            return canResolve(dynamicRange, dynamicRange2);
        }
        Logger.d(TAG, String.format("Candidate Dynamic range is not within constraints.\nDynamic range to resolve:\n  %s\nCandidate dynamic range:\n  %s", new Object[]{dynamicRange, dynamicRange2}));
        return false;
    }

    private static boolean canResolve(DynamicRange dynamicRange, DynamicRange dynamicRange2) {
        Preconditions.checkState(dynamicRange2.isFullySpecified(), "Fully specified range is not actually fully specified.");
        if (dynamicRange.getEncoding() == 2 && dynamicRange2.getEncoding() == 1) {
            return false;
        }
        if (dynamicRange.getEncoding() != 2 && dynamicRange.getEncoding() != 0 && dynamicRange.getEncoding() != dynamicRange2.getEncoding()) {
            return false;
        }
        if (dynamicRange.getBitDepth() == 0 || dynamicRange.getBitDepth() == dynamicRange2.getBitDepth()) {
            return true;
        }
        return false;
    }
}

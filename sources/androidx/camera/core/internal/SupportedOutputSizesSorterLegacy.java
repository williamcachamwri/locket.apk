package androidx.camera.core.internal;

import android.util.Rational;
import android.util.Size;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.ImageOutputConfig;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.utils.AspectRatioUtil;
import androidx.camera.core.impl.utils.CameraOrientationUtil;
import androidx.camera.core.impl.utils.CompareSizesByArea;
import androidx.camera.core.internal.utils.SizeUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SupportedOutputSizesSorterLegacy {
    private static final String TAG = "SupportedOutputSizesCollector";
    private final Rational mFullFovRatio;
    private final boolean mIsSensorLandscapeResolution;
    private final int mLensFacing;
    private final int mSensorOrientation;

    SupportedOutputSizesSorterLegacy(CameraInfoInternal cameraInfoInternal, Rational rational) {
        this.mSensorOrientation = cameraInfoInternal.getSensorRotationDegrees();
        this.mLensFacing = cameraInfoInternal.getLensFacing();
        this.mFullFovRatio = rational;
        boolean z = true;
        if (rational != null && rational.getNumerator() < rational.getDenominator()) {
            z = false;
        }
        this.mIsSensorLandscapeResolution = z;
    }

    /* access modifiers changed from: package-private */
    public List<Size> sortSupportedOutputSizes(List<Size> list, UseCaseConfig<?> useCaseConfig) {
        if (list.isEmpty()) {
            return list;
        }
        ArrayList<Size> arrayList = new ArrayList<>(list);
        Collections.sort(arrayList, new CompareSizesByArea(true));
        ArrayList arrayList2 = new ArrayList();
        ImageOutputConfig imageOutputConfig = (ImageOutputConfig) useCaseConfig;
        Size maxResolution = imageOutputConfig.getMaxResolution((Size) null);
        Size size = (Size) arrayList.get(0);
        if (maxResolution == null || SizeUtil.getArea(size) < SizeUtil.getArea(maxResolution)) {
            maxResolution = size;
        }
        Size targetSize = getTargetSize(imageOutputConfig);
        Size size2 = SizeUtil.RESOLUTION_VGA;
        int area = SizeUtil.getArea(SizeUtil.RESOLUTION_VGA);
        if (SizeUtil.getArea(maxResolution) < area) {
            size2 = SizeUtil.RESOLUTION_ZERO;
        } else if (targetSize != null && SizeUtil.getArea(targetSize) < area) {
            size2 = targetSize;
        }
        for (Size size3 : arrayList) {
            if (SizeUtil.getArea(size3) <= SizeUtil.getArea(maxResolution) && SizeUtil.getArea(size3) >= SizeUtil.getArea(size2) && !arrayList2.contains(size3)) {
                arrayList2.add(size3);
            }
        }
        if (!arrayList2.isEmpty()) {
            Rational targetAspectRatioByLegacyApi = getTargetAspectRatioByLegacyApi(imageOutputConfig, arrayList2);
            if (targetSize == null) {
                targetSize = imageOutputConfig.getDefaultResolution((Size) null);
            }
            ArrayList arrayList3 = new ArrayList();
            new HashMap();
            if (targetAspectRatioByLegacyApi == null) {
                arrayList3.addAll(arrayList2);
                if (targetSize != null) {
                    SupportedOutputSizesSorter.sortSupportedSizesByFallbackRuleClosestHigherThenLower(arrayList3, targetSize, true);
                }
            } else {
                Map<Rational, List<Size>> groupSizesByAspectRatio = SupportedOutputSizesSorter.groupSizesByAspectRatio(arrayList2);
                if (targetSize != null) {
                    for (Rational rational : groupSizesByAspectRatio.keySet()) {
                        SupportedOutputSizesSorter.sortSupportedSizesByFallbackRuleClosestHigherThenLower(groupSizesByAspectRatio.get(rational), targetSize, true);
                    }
                }
                ArrayList<Rational> arrayList4 = new ArrayList<>(groupSizesByAspectRatio.keySet());
                Collections.sort(arrayList4, new AspectRatioUtil.CompareAspectRatiosByMappingAreaInFullFovAspectRatioSpace(targetAspectRatioByLegacyApi, this.mFullFovRatio));
                for (Rational rational2 : arrayList4) {
                    for (Size size4 : groupSizesByAspectRatio.get(rational2)) {
                        if (!arrayList3.contains(size4)) {
                            arrayList3.add(size4);
                        }
                    }
                }
            }
            return arrayList3;
        }
        throw new IllegalArgumentException("All supported output sizes are filtered out according to current resolution selection settings. \nminSize = " + size2 + "\nmaxSize = " + maxResolution + "\ninitial size list: " + arrayList);
    }

    private Rational getTargetAspectRatioByLegacyApi(ImageOutputConfig imageOutputConfig, List<Size> list) {
        if (imageOutputConfig.hasTargetAspectRatio()) {
            return SupportedOutputSizesSorter.getTargetAspectRatioRationalValue(imageOutputConfig.getTargetAspectRatio(), this.mIsSensorLandscapeResolution);
        }
        Size targetSize = getTargetSize(imageOutputConfig);
        if (targetSize != null) {
            return getAspectRatioGroupKeyOfTargetSize(targetSize, list);
        }
        return null;
    }

    private Size getTargetSize(ImageOutputConfig imageOutputConfig) {
        return flipSizeByRotation(imageOutputConfig.getTargetResolution((Size) null), imageOutputConfig.getTargetRotation(0), this.mLensFacing, this.mSensorOrientation);
    }

    private static Rational getAspectRatioGroupKeyOfTargetSize(Size size, List<Size> list) {
        if (size == null) {
            return null;
        }
        for (Rational next : SupportedOutputSizesSorter.getResolutionListGroupingAspectRatioKeys(list)) {
            if (AspectRatioUtil.hasMatchingAspectRatio(size, next)) {
                return next;
            }
        }
        return new Rational(size.getWidth(), size.getHeight());
    }

    private static Size flipSizeByRotation(Size size, int i, int i2, int i3) {
        return (size == null || !isRotationNeeded(i, i2, i3)) ? size : new Size(size.getHeight(), size.getWidth());
    }

    private static boolean isRotationNeeded(int i, int i2, int i3) {
        int relativeImageRotation = CameraOrientationUtil.getRelativeImageRotation(CameraOrientationUtil.surfaceRotationToDegrees(i), i3, 1 == i2);
        if (relativeImageRotation == 90 || relativeImageRotation == 270) {
            return true;
        }
        return false;
    }
}

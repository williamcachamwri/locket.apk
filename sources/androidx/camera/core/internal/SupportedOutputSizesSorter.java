package androidx.camera.core.internal;

import android.util.Pair;
import android.util.Rational;
import android.util.Size;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.ImageOutputConfig;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.utils.AspectRatioUtil;
import androidx.camera.core.impl.utils.CameraOrientationUtil;
import androidx.camera.core.impl.utils.CompareSizesByArea;
import androidx.camera.core.internal.utils.SizeUtil;
import androidx.camera.core.resolutionselector.AspectRatioStrategy;
import androidx.camera.core.resolutionselector.ResolutionFilter;
import androidx.camera.core.resolutionselector.ResolutionSelector;
import androidx.camera.core.resolutionselector.ResolutionStrategy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SupportedOutputSizesSorter {
    private static final String TAG = "SupportedOutputSizesCollector";
    private final CameraInfoInternal mCameraInfoInternal;
    private final Rational mFullFovRatio;
    private final int mLensFacing;
    private final int mSensorOrientation;
    private final SupportedOutputSizesSorterLegacy mSupportedOutputSizesSorterLegacy;

    public SupportedOutputSizesSorter(CameraInfoInternal cameraInfoInternal, Size size) {
        Rational rational;
        this.mCameraInfoInternal = cameraInfoInternal;
        this.mSensorOrientation = cameraInfoInternal.getSensorRotationDegrees();
        this.mLensFacing = cameraInfoInternal.getLensFacing();
        if (size != null) {
            rational = calculateFullFovRatioFromActiveArraySize(size);
        } else {
            rational = calculateFullFovRatioFromSupportedOutputSizes(cameraInfoInternal);
        }
        this.mFullFovRatio = rational;
        this.mSupportedOutputSizesSorterLegacy = new SupportedOutputSizesSorterLegacy(cameraInfoInternal, rational);
    }

    private Rational calculateFullFovRatioFromActiveArraySize(Size size) {
        return new Rational(size.getWidth(), size.getHeight());
    }

    private Rational calculateFullFovRatioFromSupportedOutputSizes(CameraInfoInternal cameraInfoInternal) {
        List<Size> supportedResolutions = cameraInfoInternal.getSupportedResolutions(256);
        if (supportedResolutions.isEmpty()) {
            return null;
        }
        Size size = (Size) Collections.max(supportedResolutions, new CompareSizesByArea());
        return new Rational(size.getWidth(), size.getHeight());
    }

    public List<Size> getSortedSupportedOutputSizes(UseCaseConfig<?> useCaseConfig) {
        ImageOutputConfig imageOutputConfig = (ImageOutputConfig) useCaseConfig;
        List<Size> customOrderedResolutions = imageOutputConfig.getCustomOrderedResolutions((List<Size>) null);
        if (customOrderedResolutions != null) {
            return customOrderedResolutions;
        }
        ResolutionSelector resolutionSelector = imageOutputConfig.getResolutionSelector((ResolutionSelector) null);
        List<Size> resolutionCandidateList = getResolutionCandidateList(imageOutputConfig.getSupportedResolutions((List<Pair<Integer, Size[]>>) null), useCaseConfig.getInputFormat());
        if (resolutionSelector == null) {
            return this.mSupportedOutputSizesSorterLegacy.sortSupportedOutputSizes(resolutionCandidateList, useCaseConfig);
        }
        Size maxResolution = imageOutputConfig.getMaxResolution((Size) null);
        int targetRotation = imageOutputConfig.getTargetRotation(0);
        if (!useCaseConfig.isHighResolutionDisabled(false)) {
            resolutionCandidateList = applyHighResolutionSettings(resolutionCandidateList, resolutionSelector, useCaseConfig.getInputFormat());
        }
        return sortSupportedOutputSizesByResolutionSelector(imageOutputConfig.getResolutionSelector(), resolutionCandidateList, maxResolution, targetRotation, this.mFullFovRatio, this.mSensorOrientation, this.mLensFacing);
    }

    private List<Size> getSizeListByFormat(List<Pair<Integer, Size[]>> list, int i) {
        Size[] sizeArr;
        if (list != null) {
            Iterator<Pair<Integer, Size[]>> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Pair next = it.next();
                if (((Integer) next.first).intValue() == i) {
                    sizeArr = (Size[]) next.second;
                    break;
                }
            }
        }
        sizeArr = null;
        if (sizeArr == null) {
            return null;
        }
        return Arrays.asList(sizeArr);
    }

    public static List<Size> sortSupportedOutputSizesByResolutionSelector(ResolutionSelector resolutionSelector, List<Size> list, Size size, int i, Rational rational, int i2, int i3) {
        LinkedHashMap<Rational, List<Size>> applyAspectRatioStrategy = applyAspectRatioStrategy(list, resolutionSelector.getAspectRatioStrategy(), rational);
        if (size != null) {
            applyMaxResolutionRestriction(applyAspectRatioStrategy, size);
        }
        applyResolutionStrategy(applyAspectRatioStrategy, resolutionSelector.getResolutionStrategy());
        ArrayList arrayList = new ArrayList();
        for (List<Size> it : applyAspectRatioStrategy.values()) {
            for (Size size2 : it) {
                if (!arrayList.contains(size2)) {
                    arrayList.add(size2);
                }
            }
        }
        return applyResolutionFilter(arrayList, resolutionSelector.getResolutionFilter(), i, i2, i3);
    }

    private List<Size> getResolutionCandidateList(List<Pair<Integer, Size[]>> list, int i) {
        List<Size> sizeListByFormat = getSizeListByFormat(list, i);
        if (sizeListByFormat == null) {
            sizeListByFormat = this.mCameraInfoInternal.getSupportedResolutions(i);
        }
        ArrayList arrayList = new ArrayList(sizeListByFormat);
        Collections.sort(arrayList, new CompareSizesByArea(true));
        if (arrayList.isEmpty()) {
            Logger.w(TAG, "The retrieved supported resolutions from camera info internal is empty. Format is " + i + ".");
        }
        return arrayList;
    }

    private List<Size> applyHighResolutionSettings(List<Size> list, ResolutionSelector resolutionSelector, int i) {
        if (resolutionSelector.getAllowedResolutionMode() != 1) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(list);
        arrayList.addAll(this.mCameraInfoInternal.getSupportedHighResolutions(i));
        Collections.sort(arrayList, new CompareSizesByArea(true));
        return arrayList;
    }

    private static LinkedHashMap<Rational, List<Size>> applyAspectRatioStrategy(List<Size> list, AspectRatioStrategy aspectRatioStrategy, Rational rational) {
        return applyAspectRatioStrategyFallbackRule(groupSizesByAspectRatio(list), aspectRatioStrategy, rational);
    }

    private static LinkedHashMap<Rational, List<Size>> applyAspectRatioStrategyFallbackRule(Map<Rational, List<Size>> map, AspectRatioStrategy aspectRatioStrategy, Rational rational) {
        boolean z = true;
        if (rational != null && rational.getNumerator() < rational.getDenominator()) {
            z = false;
        }
        Rational targetAspectRatioRationalValue = getTargetAspectRatioRationalValue(aspectRatioStrategy.getPreferredAspectRatio(), z);
        if (aspectRatioStrategy.getFallbackRule() == 0) {
            Rational targetAspectRatioRationalValue2 = getTargetAspectRatioRationalValue(aspectRatioStrategy.getPreferredAspectRatio(), z);
            Iterator it = new ArrayList(map.keySet()).iterator();
            while (it.hasNext()) {
                Rational rational2 = (Rational) it.next();
                if (!rational2.equals(targetAspectRatioRationalValue2)) {
                    map.remove(rational2);
                }
            }
        }
        ArrayList<Rational> arrayList = new ArrayList<>(map.keySet());
        Collections.sort(arrayList, new AspectRatioUtil.CompareAspectRatiosByMappingAreaInFullFovAspectRatioSpace(targetAspectRatioRationalValue, rational));
        LinkedHashMap<Rational, List<Size>> linkedHashMap = new LinkedHashMap<>();
        for (Rational rational3 : arrayList) {
            linkedHashMap.put(rational3, map.get(rational3));
        }
        return linkedHashMap;
    }

    private static void applyResolutionStrategy(LinkedHashMap<Rational, List<Size>> linkedHashMap, ResolutionStrategy resolutionStrategy) {
        if (resolutionStrategy != null) {
            for (Rational rational : linkedHashMap.keySet()) {
                applyResolutionStrategyFallbackRule(linkedHashMap.get(rational), resolutionStrategy);
            }
        }
    }

    private static void applyResolutionStrategyFallbackRule(List<Size> list, ResolutionStrategy resolutionStrategy) {
        if (!list.isEmpty()) {
            Integer valueOf = Integer.valueOf(resolutionStrategy.getFallbackRule());
            if (!resolutionStrategy.equals(ResolutionStrategy.HIGHEST_AVAILABLE_STRATEGY)) {
                Size boundSize = resolutionStrategy.getBoundSize();
                int intValue = valueOf.intValue();
                if (intValue == 0) {
                    sortSupportedSizesByFallbackRuleNone(list, boundSize);
                } else if (intValue == 1) {
                    sortSupportedSizesByFallbackRuleClosestHigherThenLower(list, boundSize, true);
                } else if (intValue == 2) {
                    sortSupportedSizesByFallbackRuleClosestHigherThenLower(list, boundSize, false);
                } else if (intValue == 3) {
                    sortSupportedSizesByFallbackRuleClosestLowerThenHigher(list, boundSize, true);
                } else if (intValue == 4) {
                    sortSupportedSizesByFallbackRuleClosestLowerThenHigher(list, boundSize, false);
                }
            }
        }
    }

    private static void applyMaxResolutionRestriction(LinkedHashMap<Rational, List<Size>> linkedHashMap, Size size) {
        int area = SizeUtil.getArea(size);
        for (Rational rational : linkedHashMap.keySet()) {
            List<Size> list = linkedHashMap.get(rational);
            ArrayList arrayList = new ArrayList();
            for (Size size2 : list) {
                if (SizeUtil.getArea(size2) <= area) {
                    arrayList.add(size2);
                }
            }
            list.clear();
            list.addAll(arrayList);
        }
    }

    private static List<Size> applyResolutionFilter(List<Size> list, ResolutionFilter resolutionFilter, int i, int i2, int i3) {
        if (resolutionFilter == null) {
            return list;
        }
        int surfaceRotationToDegrees = CameraOrientationUtil.surfaceRotationToDegrees(i);
        boolean z = true;
        if (i3 != 1) {
            z = false;
        }
        List<Size> filter = resolutionFilter.filter(new ArrayList(list), CameraOrientationUtil.getRelativeImageRotation(surfaceRotationToDegrees, i2, z));
        if (list.containsAll(filter)) {
            return filter;
        }
        throw new IllegalArgumentException("The returned sizes list of the resolution filter must be a subset of the provided sizes list.");
    }

    private static void sortSupportedSizesByFallbackRuleNone(List<Size> list, Size size) {
        boolean contains = list.contains(size);
        list.clear();
        if (contains) {
            list.add(size);
        }
    }

    static void sortSupportedSizesByFallbackRuleClosestHigherThenLower(List<Size> list, Size size, boolean z) {
        ArrayList arrayList = new ArrayList();
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            Size size3 = list.get(size2);
            if (size3.getWidth() >= size.getWidth() && size3.getHeight() >= size.getHeight()) {
                break;
            }
            arrayList.add(0, size3);
        }
        list.removeAll(arrayList);
        Collections.reverse(list);
        if (z) {
            list.addAll(arrayList);
        }
    }

    private static void sortSupportedSizesByFallbackRuleClosestLowerThenHigher(List<Size> list, Size size, boolean z) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Size size2 = list.get(i);
            if (size2.getWidth() <= size.getWidth() && size2.getHeight() <= size.getHeight()) {
                break;
            }
            arrayList.add(0, size2);
        }
        list.removeAll(arrayList);
        if (z) {
            list.addAll(arrayList);
        }
    }

    static Rational getTargetAspectRatioRationalValue(int i, boolean z) {
        if (i != -1) {
            if (i != 0) {
                if (i != 1) {
                    Logger.e(TAG, "Undefined target aspect ratio: " + i);
                } else if (z) {
                    return AspectRatioUtil.ASPECT_RATIO_16_9;
                } else {
                    return AspectRatioUtil.ASPECT_RATIO_9_16;
                }
            } else if (z) {
                return AspectRatioUtil.ASPECT_RATIO_4_3;
            } else {
                return AspectRatioUtil.ASPECT_RATIO_3_4;
            }
        }
        return null;
    }

    static List<Rational> getResolutionListGroupingAspectRatioKeys(List<Size> list) {
        boolean z;
        ArrayList arrayList = new ArrayList();
        arrayList.add(AspectRatioUtil.ASPECT_RATIO_4_3);
        arrayList.add(AspectRatioUtil.ASPECT_RATIO_16_9);
        for (Size next : list) {
            Rational rational = new Rational(next.getWidth(), next.getHeight());
            if (!arrayList.contains(rational)) {
                Iterator it = arrayList.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (AspectRatioUtil.hasMatchingAspectRatio(next, (Rational) it.next())) {
                            z = true;
                            break;
                        }
                    } else {
                        z = false;
                        break;
                    }
                }
                if (!z) {
                    arrayList.add(rational);
                }
            }
        }
        return arrayList;
    }

    static Map<Rational, List<Size>> groupSizesByAspectRatio(List<Size> list) {
        HashMap hashMap = new HashMap();
        for (Rational put : getResolutionListGroupingAspectRatioKeys(list)) {
            hashMap.put(put, new ArrayList());
        }
        for (Size next : list) {
            for (Rational rational : hashMap.keySet()) {
                if (AspectRatioUtil.hasMatchingAspectRatio(next, rational)) {
                    ((List) hashMap.get(rational)).add(next);
                }
            }
        }
        return hashMap;
    }
}

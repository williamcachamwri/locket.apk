package androidx.camera.core.streamsharing;

import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Pair;
import android.util.Rational;
import android.util.Size;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.ImageOutputConfig;
import androidx.camera.core.impl.MutableConfig;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.utils.AspectRatioUtil;
import androidx.camera.core.impl.utils.CompareSizesByArea;
import androidx.camera.core.impl.utils.TransformUtils;
import androidx.camera.core.internal.SupportedOutputSizesSorter;
import androidx.camera.core.resolutionselector.ResolutionSelector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class ResolutionsMerger {
    private static final double SAME_AREA_WIDTH_HEIGHT_RATIO = Math.sqrt(2.3703703703703702d);
    private static final String TAG = "ResolutionsMerger";
    private final CameraInfoInternal mCameraInfo;
    private final Map<UseCaseConfig<?>, List<Size>> mChildSizesCache;
    private final Set<UseCaseConfig<?>> mChildrenConfigs;
    private final Rational mFallbackAspectRatio;
    private final Rational mSensorAspectRatio;
    private final Size mSensorSize;
    private final SupportedOutputSizesSorter mSizeSorter;

    private boolean areCroppingInDifferentDirection(float f, float f2, float f3) {
        int i;
        int i2 = (f > f2 ? 1 : (f == f2 ? 0 : -1));
        if (i2 == 0 || f2 == f3) {
            return false;
        }
        return i2 > 0 ? f2 < f3 : i > 0;
    }

    ResolutionsMerger(CameraInternal cameraInternal, Set<UseCaseConfig<?>> set) {
        this(TransformUtils.rectToSize(cameraInternal.getCameraControlInternal().getSensorRect()), cameraInternal.getCameraInfoInternal(), set);
    }

    private ResolutionsMerger(Size size, CameraInfoInternal cameraInfoInternal, Set<UseCaseConfig<?>> set) {
        this(size, cameraInfoInternal, set, new SupportedOutputSizesSorter(cameraInfoInternal, size));
    }

    ResolutionsMerger(Size size, CameraInfoInternal cameraInfoInternal, Set<UseCaseConfig<?>> set, SupportedOutputSizesSorter supportedOutputSizesSorter) {
        this.mChildSizesCache = new HashMap();
        this.mSensorSize = size;
        Rational sensorAspectRatio = getSensorAspectRatio(size);
        this.mSensorAspectRatio = sensorAspectRatio;
        this.mFallbackAspectRatio = getFallbackAspectRatio(sensorAspectRatio);
        this.mCameraInfo = cameraInfoInternal;
        this.mChildrenConfigs = set;
        this.mSizeSorter = supportedOutputSizesSorter;
    }

    /* access modifiers changed from: package-private */
    public List<Size> getMergedResolutions(MutableConfig mutableConfig) {
        List<Size> cameraSupportedResolutions = getCameraSupportedResolutions();
        if (shouldIncludeHighResolutions()) {
            ArrayList arrayList = new ArrayList(cameraSupportedResolutions);
            arrayList.addAll(getCameraSupportedHighResolutions());
            cameraSupportedResolutions = arrayList;
        }
        List list = (List) mutableConfig.retrieveOption(ImageOutputConfig.OPTION_SUPPORTED_RESOLUTIONS, null);
        if (list != null) {
            cameraSupportedResolutions = getSupportedPrivResolutions(list);
        }
        return selectParentResolutions(cameraSupportedResolutions);
    }

    /* access modifiers changed from: package-private */
    public Pair<Rect, Size> getPreferredChildSizePair(UseCaseConfig<?> useCaseConfig, Rect rect, int i, boolean z) {
        boolean z2;
        if (TransformUtils.is90or270(i)) {
            rect = reverseRect(rect);
            z2 = true;
        } else {
            z2 = false;
        }
        Pair<Rect, Size> preferredChildSizePairInternal = getPreferredChildSizePairInternal(rect, useCaseConfig, z);
        Rect rect2 = (Rect) preferredChildSizePairInternal.first;
        Size size = (Size) preferredChildSizePairInternal.second;
        if (z2) {
            size = TransformUtils.reverseSize(size);
            rect2 = reverseRect(rect2);
        }
        return new Pair<>(rect2, size);
    }

    private Pair<Rect, Size> getPreferredChildSizePairInternal(Rect rect, UseCaseConfig<?> useCaseConfig, boolean z) {
        Size size;
        if (z) {
            size = getPreferredChildSizeForViewport(TransformUtils.rectToSize(rect), useCaseConfig);
        } else {
            Size rectToSize = TransformUtils.rectToSize(rect);
            size = getPreferredChildSize(rectToSize, useCaseConfig);
            rect = getCropRectOfReferenceAspectRatio(rectToSize, size);
        }
        return new Pair<>(rect, size);
    }

    /* access modifiers changed from: package-private */
    public Size getPreferredChildSize(Size size, UseCaseConfig<?> useCaseConfig) {
        List<Size> sortedChildSizes = getSortedChildSizes(useCaseConfig);
        for (Size next : sortedChildSizes) {
            if (!isDoubleCropping(size, next) && !hasUpscaling(next, size)) {
                return next;
            }
        }
        for (Size next2 : sortedChildSizes) {
            if (!hasUpscaling(next2, size)) {
                return next2;
            }
        }
        return size;
    }

    /* access modifiers changed from: package-private */
    public Size getPreferredChildSizeForViewport(Size size, UseCaseConfig<?> useCaseConfig) {
        for (Size cropRectOfReferenceAspectRatio : getSortedChildSizes(useCaseConfig)) {
            Size rectToSize = TransformUtils.rectToSize(getCropRectOfReferenceAspectRatio(cropRectOfReferenceAspectRatio, size));
            if (!hasUpscaling(rectToSize, size)) {
                return rectToSize;
            }
        }
        return size;
    }

    private List<Size> getCameraSupportedResolutions() {
        return this.mCameraInfo.getSupportedResolutions(34);
    }

    private List<Size> getCameraSupportedHighResolutions() {
        return this.mCameraInfo.getSupportedHighResolutions(34);
    }

    private boolean shouldIncludeHighResolutions() {
        boolean z;
        ResolutionSelector resolutionSelector;
        Iterator<UseCaseConfig<?>> it = this.mChildrenConfigs.iterator();
        while (true) {
            z = false;
            if (!it.hasNext()) {
                break;
            }
            UseCaseConfig next = it.next();
            if (!next.isHighResolutionDisabled(false) && (next instanceof ImageOutputConfig) && (resolutionSelector = ((ImageOutputConfig) next).getResolutionSelector((ResolutionSelector) null)) != null) {
                z = true;
                if (resolutionSelector.getAllowedResolutionMode() == 1) {
                    break;
                }
            }
        }
        return z;
    }

    private List<Size> selectParentResolutions(List<Size> list) {
        ArrayList arrayList = new ArrayList();
        if (needToAddSensorResolutions()) {
            arrayList.addAll(selectParentResolutionsByAspectRatio(this.mSensorAspectRatio, list, false));
        }
        arrayList.addAll(selectParentResolutionsByAspectRatio(this.mFallbackAspectRatio, list, false));
        arrayList.addAll(selectOtherAspectRatioParentResolutionsWithFovPriority(list, false));
        if (arrayList.isEmpty()) {
            Logger.w(TAG, "Failed to find a parent resolution that does not result in double-cropping, this might due to camera not supporting 4:3 and 16:9resolutions or a strict ResolutionSelector settings. Starting resolution selection process with resolutions that might have a smaller FOV.");
            arrayList.addAll(selectOtherAspectRatioParentResolutionsWithFovPriority(list, true));
        }
        Logger.d(TAG, "Parent resolutions: " + arrayList);
        return arrayList;
    }

    private List<Size> selectParentResolutionsByAspectRatio(Rational rational, List<Size> list, boolean z) {
        List<Size> filterResolutionsByAspectRatio = filterResolutionsByAspectRatio(rational, list);
        sortInDescendingOrder(filterResolutionsByAspectRatio);
        HashSet hashSet = new HashSet(filterResolutionsByAspectRatio);
        for (UseCaseConfig<?> sortedChildSizes : this.mChildrenConfigs) {
            List<Size> sortedChildSizes2 = getSortedChildSizes(sortedChildSizes);
            if (!z) {
                sortedChildSizes2 = filterOutChildSizesCausingDoubleCropping(rational, sortedChildSizes2);
            }
            if (sortedChildSizes2.isEmpty()) {
                return new ArrayList();
            }
            filterResolutionsByAspectRatio = filterOutParentSizeThatIsTooSmall(sortedChildSizes2, filterResolutionsByAspectRatio);
            hashSet.retainAll(getParentSizesThatAreTooLarge(sortedChildSizes2, filterResolutionsByAspectRatio));
        }
        ArrayList arrayList = new ArrayList();
        for (Size next : filterResolutionsByAspectRatio) {
            if (!hashSet.contains(next)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    private List<Size> selectOtherAspectRatioParentResolutionsWithFovPriority(List<Size> list, boolean z) {
        Map<Rational, List<Size>> groupSizesByAspectRatio = groupSizesByAspectRatio(list);
        ArrayList<Rational> arrayList = new ArrayList<>(groupSizesByAspectRatio.keySet());
        sortByFov(arrayList);
        ArrayList arrayList2 = new ArrayList();
        for (Rational rational : arrayList) {
            if (!rational.equals(AspectRatioUtil.ASPECT_RATIO_16_9) && !rational.equals(AspectRatioUtil.ASPECT_RATIO_4_3)) {
                arrayList2.addAll(selectParentResolutionsByAspectRatio(rational, (List) Objects.requireNonNull(groupSizesByAspectRatio.get(rational)), z));
            }
        }
        return arrayList2;
    }

    private Map<Rational, List<Size>> groupSizesByAspectRatio(List<Size> list) {
        List list2;
        HashMap hashMap = new HashMap();
        hashMap.put(AspectRatioUtil.ASPECT_RATIO_4_3, new ArrayList());
        hashMap.put(AspectRatioUtil.ASPECT_RATIO_16_9, new ArrayList());
        ArrayList arrayList = new ArrayList();
        arrayList.add(AspectRatioUtil.ASPECT_RATIO_4_3);
        arrayList.add(AspectRatioUtil.ASPECT_RATIO_16_9);
        for (Size next : list) {
            if (next.getHeight() > 0) {
                Iterator it = arrayList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        list2 = null;
                        break;
                    }
                    Rational rational = (Rational) it.next();
                    if (AspectRatioUtil.hasMatchingAspectRatio(next, rational)) {
                        list2 = (List) hashMap.get(rational);
                        break;
                    }
                }
                if (list2 == null) {
                    list2 = new ArrayList();
                    Rational rational2 = toRational(next);
                    arrayList.add(rational2);
                    hashMap.put(rational2, list2);
                }
                ((List) Objects.requireNonNull(list2)).add(next);
            }
        }
        return hashMap;
    }

    private List<Size> getSortedChildSizes(UseCaseConfig<?> useCaseConfig) {
        if (!this.mChildrenConfigs.contains(useCaseConfig)) {
            throw new IllegalArgumentException("Invalid child config: " + useCaseConfig);
        } else if (this.mChildSizesCache.containsKey(useCaseConfig)) {
            return (List) Objects.requireNonNull(this.mChildSizesCache.get(useCaseConfig));
        } else {
            List<Size> filterOutChildSizesThatWillNeverBeSelected = filterOutChildSizesThatWillNeverBeSelected(this.mSizeSorter.getSortedSupportedOutputSizes(useCaseConfig));
            this.mChildSizesCache.put(useCaseConfig, filterOutChildSizesThatWillNeverBeSelected);
            return filterOutChildSizesThatWillNeverBeSelected;
        }
    }

    private boolean needToAddSensorResolutions() {
        for (Size hasMatchingAspectRatio : getChildrenRequiredResolutions()) {
            if (!AspectRatioUtil.hasMatchingAspectRatio(hasMatchingAspectRatio, this.mFallbackAspectRatio)) {
                return true;
            }
        }
        return false;
    }

    private Set<Size> getChildrenRequiredResolutions() {
        HashSet hashSet = new HashSet();
        for (UseCaseConfig<?> sortedChildSizes : this.mChildrenConfigs) {
            hashSet.addAll(getSortedChildSizes(sortedChildSizes));
        }
        return hashSet;
    }

    private List<Size> filterOutChildSizesCausingDoubleCropping(Rational rational, List<Size> list) {
        ArrayList arrayList = new ArrayList();
        for (Size next : list) {
            if (!isDoubleCropping(rational, next)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    private boolean isDoubleCropping(Rational rational, Size size) {
        if (this.mSensorAspectRatio.equals(rational) || AspectRatioUtil.hasMatchingAspectRatio(size, rational)) {
            return false;
        }
        return areCroppingInDifferentDirection(this.mSensorAspectRatio.floatValue(), rational.floatValue(), toRationalWithMod16Considered(size).floatValue());
    }

    private boolean isDoubleCropping(Size size, Size size2) {
        return isDoubleCropping(toRationalWithMod16Considered(size), size2);
    }

    private void sortByFov(List<Rational> list) {
        Collections.sort(list, new CompareAspectRatioByOverlappingAreaToReference(toRational(this.mSensorSize), true));
    }

    static Rect getCropRectOfReferenceAspectRatio(Size size, Size size2) {
        return getCenterCroppedRectangle(toRational(size2), size);
    }

    private static List<Size> getSupportedPrivResolutions(List<Pair<Integer, Size[]>> list) {
        for (Pair next : list) {
            if (((Integer) next.first).equals(34)) {
                return Arrays.asList((Size[]) next.second);
            }
        }
        return new ArrayList();
    }

    private static Rational getSensorAspectRatio(Size size) {
        Rational findCloserAspectRatio = findCloserAspectRatio(size);
        Logger.d(TAG, "The closer aspect ratio to the sensor size (" + size + ") is " + findCloserAspectRatio + ".");
        return findCloserAspectRatio;
    }

    private static Rational findCloserAspectRatio(Size size) {
        if (((double) size.getWidth()) / ((double) size.getHeight()) > SAME_AREA_WIDTH_HEIGHT_RATIO) {
            return AspectRatioUtil.ASPECT_RATIO_16_9;
        }
        return AspectRatioUtil.ASPECT_RATIO_4_3;
    }

    static Rect reverseRect(Rect rect) {
        return new Rect(rect.top, rect.left, rect.bottom, rect.right);
    }

    private static Rect getCenterCroppedRectangle(Rational rational, Size size) {
        RectF rectF;
        RectF rectF2;
        int width = size.getWidth();
        int height = size.getHeight();
        Rational rational2 = toRational(size);
        if (rational.floatValue() == rational2.floatValue()) {
            rectF = new RectF(0.0f, 0.0f, (float) width, (float) height);
        } else {
            if (rational.floatValue() > rational2.floatValue()) {
                float f = (float) width;
                float floatValue = f / rational.floatValue();
                float f2 = (((float) height) - floatValue) / 2.0f;
                rectF2 = new RectF(0.0f, f2, f, floatValue + f2);
            } else {
                float f3 = (float) height;
                float floatValue2 = rational.floatValue() * f3;
                float f4 = (((float) width) - floatValue2) / 2.0f;
                rectF2 = new RectF(f4, 0.0f, floatValue2 + f4, f3);
            }
            rectF = rectF2;
        }
        Rect rect = new Rect();
        rectF.round(rect);
        return rect;
    }

    private static Rational getFallbackAspectRatio(Rational rational) {
        if (rational.equals(AspectRatioUtil.ASPECT_RATIO_4_3)) {
            return AspectRatioUtil.ASPECT_RATIO_16_9;
        }
        if (rational.equals(AspectRatioUtil.ASPECT_RATIO_16_9)) {
            return AspectRatioUtil.ASPECT_RATIO_4_3;
        }
        throw new IllegalArgumentException("Invalid sensor aspect-ratio: " + rational);
    }

    static void sortInDescendingOrder(List<Size> list) {
        Collections.sort(list, new CompareSizesByArea(true));
    }

    private static List<Size> removeDuplicates(List<Size> list) {
        if (list.isEmpty()) {
            return list;
        }
        return new ArrayList(new LinkedHashSet(list));
    }

    static List<Size> filterResolutionsByAspectRatio(Rational rational, List<Size> list) {
        ArrayList arrayList = new ArrayList();
        for (Size next : list) {
            if (AspectRatioUtil.hasMatchingAspectRatio(next, rational)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    static List<Size> filterOutParentSizeThatIsTooSmall(Collection<Size> collection, List<Size> list) {
        if (collection.isEmpty() || list.isEmpty()) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (Size next : list) {
            if (isAnyChildSizeCanBeCroppedOutWithoutUpscalingParent(collection, next)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    private static List<Size> filterOutChildSizesThatWillNeverBeSelected(List<Size> list) {
        Rational rational;
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        for (Size next : list) {
            Iterator it = hashMap.keySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    rational = null;
                    break;
                }
                rational = (Rational) it.next();
                if (AspectRatioUtil.hasMatchingAspectRatio(next, rational)) {
                    break;
                }
            }
            if (rational != null) {
                Size size = (Size) Objects.requireNonNull((Size) hashMap.get(rational));
                if (next.getHeight() <= size.getHeight() && next.getWidth() <= size.getWidth()) {
                    if (next.getWidth() == size.getWidth() && next.getHeight() == size.getHeight()) {
                    }
                }
            } else {
                rational = toRational(next);
            }
            arrayList.add(next);
            hashMap.put(rational, next);
        }
        return arrayList;
    }

    private static boolean isAnyChildSizeCanBeCroppedOutWithoutUpscalingParent(Collection<Size> collection, Size size) {
        for (Size hasUpscaling : collection) {
            if (!hasUpscaling(hasUpscaling, size)) {
                return true;
            }
        }
        return false;
    }

    static List<Size> getParentSizesThatAreTooLarge(Collection<Size> collection, List<Size> list) {
        if (collection.isEmpty() || list.isEmpty()) {
            return new ArrayList();
        }
        List<Size> removeDuplicates = removeDuplicates(list);
        ArrayList arrayList = new ArrayList();
        for (Size next : removeDuplicates) {
            if (isAllChildSizesCanBeCroppedOutWithoutUpscalingParent(collection, next)) {
                arrayList.add(next);
            }
        }
        if (!arrayList.isEmpty()) {
            arrayList.remove(arrayList.size() - 1);
        }
        return arrayList;
    }

    private static boolean isAllChildSizesCanBeCroppedOutWithoutUpscalingParent(Collection<Size> collection, Size size) {
        for (Size hasUpscaling : collection) {
            if (hasUpscaling(hasUpscaling, size)) {
                return false;
            }
        }
        return true;
    }

    static boolean hasUpscaling(Size size, Size size2) {
        return size.getHeight() > size2.getHeight() || size.getWidth() > size2.getWidth();
    }

    private static Rational toRationalWithMod16Considered(Size size) {
        if (AspectRatioUtil.hasMatchingAspectRatio(size, AspectRatioUtil.ASPECT_RATIO_4_3)) {
            return AspectRatioUtil.ASPECT_RATIO_4_3;
        }
        if (AspectRatioUtil.hasMatchingAspectRatio(size, AspectRatioUtil.ASPECT_RATIO_16_9)) {
            return AspectRatioUtil.ASPECT_RATIO_16_9;
        }
        return toRational(size);
    }

    private static Rational toRational(Size size) {
        return new Rational(size.getWidth(), size.getHeight());
    }

    /* access modifiers changed from: private */
    public static float computeAreaOverlapping(Rational rational, Rational rational2) {
        float floatValue = rational.floatValue();
        float floatValue2 = rational2.floatValue();
        return floatValue > floatValue2 ? floatValue2 / floatValue : floatValue / floatValue2;
    }

    private static class CompareAspectRatioByOverlappingAreaToReference implements Comparator<Rational> {
        private final Rational mReferenceAspectRatio;
        private final boolean mReverse;

        CompareAspectRatioByOverlappingAreaToReference(Rational rational, boolean z) {
            this.mReferenceAspectRatio = rational;
            this.mReverse = z;
        }

        public int compare(Rational rational, Rational rational2) {
            float access$000 = ResolutionsMerger.computeAreaOverlapping(rational, this.mReferenceAspectRatio);
            float access$0002 = ResolutionsMerger.computeAreaOverlapping(rational2, this.mReferenceAspectRatio);
            if (this.mReverse) {
                return Float.compare(access$0002, access$000);
            }
            return Float.compare(access$000, access$0002);
        }
    }
}

package androidx.camera.video;

import android.util.Range;
import android.util.Rational;
import android.util.Size;
import androidx.camera.core.impl.utils.AspectRatioUtil;
import androidx.camera.core.internal.utils.SizeUtil;
import androidx.media3.exoplayer.trackselection.AdaptiveTrackSelection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

class QualityRatioToResolutionsTable {
    private static final Map<Integer, Rational> sAspectRatioMap;
    private static final Map<Quality, Range<Integer>> sQualityRangeMap;
    private final Map<QualityRatio, List<Size>> mTable = new HashMap();

    static {
        HashMap hashMap = new HashMap();
        sQualityRangeMap = hashMap;
        hashMap.put(Quality.UHD, Range.create(2160, 4319));
        hashMap.put(Quality.FHD, Range.create(1080, 1439));
        hashMap.put(Quality.HD, Range.create(720, 1079));
        hashMap.put(Quality.SD, Range.create(241, Integer.valueOf(AdaptiveTrackSelection.DEFAULT_MAX_HEIGHT_TO_DISCARD)));
        HashMap hashMap2 = new HashMap();
        sAspectRatioMap = hashMap2;
        hashMap2.put(0, AspectRatioUtil.ASPECT_RATIO_4_3);
        hashMap2.put(1, AspectRatioUtil.ASPECT_RATIO_16_9);
    }

    QualityRatioToResolutionsTable(List<Size> list, Map<Quality, Size> map) {
        for (Quality next : sQualityRangeMap.keySet()) {
            this.mTable.put(QualityRatio.of(next, -1), new ArrayList());
            for (Integer intValue : sAspectRatioMap.keySet()) {
                this.mTable.put(QualityRatio.of(next, intValue.intValue()), new ArrayList());
            }
        }
        addProfileSizesToTable(map);
        addResolutionsToTable(list);
        sortQualityRatioRow(map);
    }

    /* access modifiers changed from: package-private */
    public List<Size> getResolutions(Quality quality, int i) {
        ArrayList arrayList;
        List<Size> qualityRatioRow = getQualityRatioRow(quality, i);
        if (qualityRatioRow == null) {
            arrayList = new ArrayList(0);
        }
        return arrayList;
    }

    private void addProfileSizesToTable(Map<Quality, Size> map) {
        for (Map.Entry next : map.entrySet()) {
            ((List) Objects.requireNonNull(getQualityRatioRow((Quality) next.getKey(), -1))).add((Size) next.getValue());
        }
    }

    private void addResolutionsToTable(List<Size> list) {
        Integer findMappedAspectRatio;
        for (Size next : list) {
            Quality findMappedQuality = findMappedQuality(next);
            if (!(findMappedQuality == null || (findMappedAspectRatio = findMappedAspectRatio(next)) == null)) {
                ((List) Objects.requireNonNull(getQualityRatioRow(findMappedQuality, findMappedAspectRatio.intValue()))).add(next);
            }
        }
    }

    private void sortQualityRatioRow(Map<Quality, Size> map) {
        for (Map.Entry next : this.mTable.entrySet()) {
            Size size = map.get(((QualityRatio) next.getKey()).getQuality());
            if (size != null) {
                Collections.sort((List) next.getValue(), new QualityRatioToResolutionsTable$$ExternalSyntheticLambda0(SizeUtil.getArea(size)));
            }
        }
    }

    static /* synthetic */ int lambda$sortQualityRatioRow$0(int i, Size size, Size size2) {
        return Math.abs(SizeUtil.getArea(size) - i) - Math.abs(SizeUtil.getArea(size2) - i);
    }

    private static Quality findMappedQuality(Size size) {
        for (Map.Entry next : sQualityRangeMap.entrySet()) {
            if (((Range) next.getValue()).contains(Integer.valueOf(size.getHeight()))) {
                return (Quality) next.getKey();
            }
        }
        return null;
    }

    private static Integer findMappedAspectRatio(Size size) {
        for (Map.Entry next : sAspectRatioMap.entrySet()) {
            if (AspectRatioUtil.hasMatchingAspectRatio(size, (Rational) next.getValue(), SizeUtil.RESOLUTION_QVGA)) {
                return (Integer) next.getKey();
            }
        }
        return null;
    }

    private List<Size> getQualityRatioRow(Quality quality, int i) {
        return this.mTable.get(QualityRatio.of(quality, i));
    }

    static abstract class QualityRatio {
        /* access modifiers changed from: package-private */
        public abstract int getAspectRatio();

        /* access modifiers changed from: package-private */
        public abstract Quality getQuality();

        QualityRatio() {
        }

        static QualityRatio of(Quality quality, int i) {
            return new AutoValue_QualityRatioToResolutionsTable_QualityRatio(quality, i);
        }
    }
}

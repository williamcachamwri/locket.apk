package androidx.camera.core.streamsharing;

import androidx.camera.core.DynamicRange;
import androidx.camera.core.impl.UseCaseConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DynamicRangeUtils {
    private DynamicRangeUtils() {
    }

    public static DynamicRange resolveDynamicRange(Set<UseCaseConfig<?>> set) {
        ArrayList arrayList = new ArrayList();
        for (UseCaseConfig<?> dynamicRange : set) {
            arrayList.add(dynamicRange.getDynamicRange());
        }
        return intersectDynamicRange(arrayList);
    }

    private static DynamicRange intersectDynamicRange(List<DynamicRange> list) {
        if (list.isEmpty()) {
            return null;
        }
        DynamicRange dynamicRange = list.get(0);
        Integer valueOf = Integer.valueOf(dynamicRange.getEncoding());
        Integer valueOf2 = Integer.valueOf(dynamicRange.getBitDepth());
        for (int i = 1; i < list.size(); i++) {
            DynamicRange dynamicRange2 = list.get(i);
            valueOf = intersectDynamicRangeEncoding(valueOf, Integer.valueOf(dynamicRange2.getEncoding()));
            valueOf2 = intersectDynamicRangeBitDepth(valueOf2, Integer.valueOf(dynamicRange2.getBitDepth()));
            if (valueOf == null || valueOf2 == null) {
                return null;
            }
        }
        return new DynamicRange(valueOf.intValue(), valueOf2.intValue());
    }

    private static Integer intersectDynamicRangeEncoding(Integer num, Integer num2) {
        if (num.equals(0)) {
            return num2;
        }
        if (num2.equals(0)) {
            return num;
        }
        if (num.equals(2) && !num2.equals(1)) {
            return num2;
        }
        if ((!num2.equals(2) || num.equals(1)) && !num.equals(num2)) {
            return null;
        }
        return num;
    }

    private static Integer intersectDynamicRangeBitDepth(Integer num, Integer num2) {
        if (num.equals(0)) {
            return num2;
        }
        if (!num2.equals(0) && !num.equals(num2)) {
            return null;
        }
        return num;
    }
}

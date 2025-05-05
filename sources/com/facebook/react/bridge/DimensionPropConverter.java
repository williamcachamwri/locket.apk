package com.facebook.react.bridge;

import com.facebook.yoga.YogaUnit;
import com.facebook.yoga.YogaValue;

public class DimensionPropConverter {
    public static YogaValue getDimension(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Double) {
            return new YogaValue(((Double) obj).floatValue(), YogaUnit.POINT);
        }
        if (obj instanceof String) {
            return YogaValue.parse((String) obj);
        }
        throw new JSApplicationCausedNativeException("DimensionValue: the value must be a number or string.");
    }
}

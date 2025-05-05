package org.checkerframework.framework.qual;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum LiteralKind {
    NULL,
    INT,
    LONG,
    FLOAT,
    DOUBLE,
    BOOLEAN,
    CHAR,
    STRING,
    ALL,
    PRIMITIVE;

    public static List<LiteralKind> allLiteralKinds() {
        ArrayList arrayList = new ArrayList(Arrays.asList(values()));
        arrayList.remove(ALL);
        arrayList.remove(PRIMITIVE);
        return arrayList;
    }

    public static List<LiteralKind> primitiveLiteralKinds() {
        return Arrays.asList(new LiteralKind[]{INT, LONG, FLOAT, DOUBLE, BOOLEAN, CHAR});
    }
}

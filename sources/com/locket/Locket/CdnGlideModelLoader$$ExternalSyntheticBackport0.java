package com.locket.Locket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CdnGlideModelLoader$$ExternalSyntheticBackport0 {
    public static /* synthetic */ Object m(Object obj, Object obj2) {
        return obj != null ? obj : Objects.requireNonNull(obj2, "defaultObj");
    }

    public static /* synthetic */ List m(Object[] objArr) {
        ArrayList arrayList = new ArrayList(objArr.length);
        for (Object requireNonNull : objArr) {
            arrayList.add(Objects.requireNonNull(requireNonNull));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public static /* synthetic */ Map m(Map.Entry[] entryArr) {
        HashMap hashMap = new HashMap(entryArr.length);
        int length = entryArr.length;
        int i = 0;
        while (i < length) {
            Map.Entry entry = entryArr[i];
            Object requireNonNull = Objects.requireNonNull(entry.getKey());
            if (hashMap.put(requireNonNull, Objects.requireNonNull(entry.getValue())) == null) {
                i++;
            } else {
                throw new IllegalArgumentException("duplicate key: " + requireNonNull);
            }
        }
        return Collections.unmodifiableMap(hashMap);
    }

    public static /* synthetic */ boolean m(String str) {
        int length = str.length();
        int i = 0;
        while (i < length) {
            int codePointAt = str.codePointAt(i);
            if (!Character.isWhitespace(codePointAt)) {
                return false;
            }
            i += Character.charCount(codePointAt);
        }
        return true;
    }
}

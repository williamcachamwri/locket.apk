package com.amplitude.analytics.connector.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u0004\u0018\u00010\u0001H\u0002\u001a\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u0004\u0018\u00010\u0001H\u0002\u001a\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u0004*\b\u0012\u0002\b\u0003\u0018\u00010\u0005H\u0000\u001a\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0007*\f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010\b\u001a\u0014\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005*\u00020\u0004H\u0000\u001a\u001a\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\b*\u00020\u0007H\u0000\u001a$\u0010\f\u001a\u001c\u0012\u0004\u0012\u00020\u000b\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\b0\b*\u00020\u0007¨\u0006\r"}, d2 = {"fromJSON", "", "toJSON", "toJSONArray", "Lorg/json/JSONArray;", "", "toJSONObject", "Lorg/json/JSONObject;", "", "toList", "toMap", "", "toUpdateUserPropertiesMap", "analytics-connector_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* compiled from: JSON.kt */
public final class JSONUtil {
    public static final Map<String, Map<String, Object>> toUpdateUserPropertiesMap(JSONObject jSONObject) {
        Map<String, Object> map;
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        Map<String, Map<String, Object>> linkedHashMap = new LinkedHashMap<>();
        Iterator<String> keys = jSONObject.keys();
        Intrinsics.checkNotNullExpressionValue(keys, "this.keys()");
        while (keys.hasNext()) {
            String next = keys.next();
            Intrinsics.checkNotNullExpressionValue(next, "key");
            Object obj = jSONObject.get(next);
            if (obj instanceof JSONObject) {
                map = toMap((JSONObject) obj);
            } else if (obj instanceof JSONArray) {
                map = MapsKt.emptyMap();
            } else if (Intrinsics.areEqual(obj, JSONObject.NULL)) {
                map = MapsKt.emptyMap();
            } else {
                map = MapsKt.emptyMap();
            }
            linkedHashMap.put(next, map);
        }
        return linkedHashMap;
    }

    public static final JSONObject toJSONObject(Map<?, ?> map) {
        if (map == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry next : map.entrySet()) {
            Object key = next.getKey();
            String str = key instanceof String ? (String) key : null;
            if (str != null) {
                jSONObject.put(str, toJSON(next.getValue()));
            }
        }
        return jSONObject;
    }

    public static final Map<String, Object> toMap(JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        Map<String, Object> linkedHashMap = new LinkedHashMap<>();
        Iterator<String> keys = jSONObject.keys();
        Intrinsics.checkNotNullExpressionValue(keys, "this.keys()");
        while (keys.hasNext()) {
            String next = keys.next();
            Intrinsics.checkNotNullExpressionValue(next, "key");
            linkedHashMap.put(next, fromJSON(jSONObject.get(next)));
        }
        return linkedHashMap;
    }

    public static final List<Object> toList(JSONArray jSONArray) {
        Intrinsics.checkNotNullParameter(jSONArray, "<this>");
        List<Object> arrayList = new ArrayList<>();
        int length = jSONArray.length();
        if (length > 0) {
            int i = 0;
            while (true) {
                int i2 = i + 1;
                arrayList.add(fromJSON(jSONArray.get(i)));
                if (i2 >= length) {
                    break;
                }
                i = i2;
            }
        }
        return arrayList;
    }

    public static final JSONArray toJSONArray(List<?> list) {
        if (list == null) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (Object json : list) {
            jSONArray.put(toJSON(json));
        }
        return jSONArray;
    }

    private static final Object fromJSON(Object obj) {
        if (obj instanceof JSONObject) {
            return toMap((JSONObject) obj);
        }
        if (obj instanceof JSONArray) {
            return toList((JSONArray) obj);
        }
        if (obj instanceof BigDecimal) {
            return Double.valueOf(((BigDecimal) obj).doubleValue());
        }
        if (Intrinsics.areEqual(obj, JSONObject.NULL)) {
            return null;
        }
        return obj;
    }

    private static final Object toJSON(Object obj) {
        if (obj instanceof Map) {
            return toJSONObject((Map) obj);
        }
        return obj instanceof List ? toJSONArray((List) obj) : obj;
    }
}

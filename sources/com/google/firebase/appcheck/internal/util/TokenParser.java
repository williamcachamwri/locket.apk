package com.google.firebase.appcheck.internal.util;

import android.text.TextUtils;
import android.util.Base64;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TokenParser {
    public static Map<String, Object> parseTokenClaims(String str) {
        Preconditions.checkNotEmpty(str);
        String[] split = str.split("\\.", -1);
        if (split.length < 2) {
            Logger.getLogger().e("Invalid token (too few subsections):\n" + str);
            return Collections.emptyMap();
        }
        try {
            Map<String, Object> parseJsonIntoMap = parseJsonIntoMap(new String(Base64.decode(split[1], 11), "UTF-8"));
            return parseJsonIntoMap == null ? Collections.emptyMap() : parseJsonIntoMap;
        } catch (UnsupportedEncodingException e) {
            Logger.getLogger().e("Unable to decode token (charset unknown):\n" + e);
            return Collections.emptyMap();
        }
    }

    private static Map<String, Object> parseJsonIntoMap(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject != JSONObject.NULL) {
                return toMap(jSONObject);
            }
            return null;
        } catch (Exception e) {
            Logger.getLogger().d("Failed to parse JSONObject into Map:\n" + e);
            return Collections.emptyMap();
        }
    }

    private static Map<String, Object> toMap(JSONObject jSONObject) throws JSONException {
        ArrayMap arrayMap = new ArrayMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object obj = jSONObject.get(next);
            if (obj instanceof JSONArray) {
                obj = toList((JSONArray) obj);
            } else if (obj instanceof JSONObject) {
                obj = toMap((JSONObject) obj);
            } else if (obj.equals(JSONObject.NULL)) {
                obj = null;
            }
            arrayMap.put(next, obj);
        }
        return arrayMap;
    }

    private static List<Object> toList(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj = jSONArray.get(i);
            if (obj instanceof JSONArray) {
                obj = toList((JSONArray) obj);
            } else if (obj instanceof JSONObject) {
                obj = toMap((JSONObject) obj);
            }
            arrayList.add(obj);
        }
        return arrayList;
    }
}

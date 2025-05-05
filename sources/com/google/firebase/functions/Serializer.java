package com.google.firebase.functions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class Serializer {
    static final String LONG_TYPE = "type.googleapis.com/google.protobuf.Int64Value";
    static final String UNSIGNED_LONG_TYPE = "type.googleapis.com/google.protobuf.UInt64Value";
    private final DateFormat dateFormat;

    Serializer() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        this.dateFormat = simpleDateFormat;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public Object encode(Object obj) {
        boolean z;
        boolean z2;
        if (obj == null || obj == JSONObject.NULL) {
            return JSONObject.NULL;
        }
        if (obj instanceof Long) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("@type", LONG_TYPE);
                jSONObject.put("value", obj.toString());
                return jSONObject;
            } catch (JSONException e) {
                throw new RuntimeException("Error encoding Long.", e);
            }
        } else if ((obj instanceof Number) || (obj instanceof String) || (obj instanceof Boolean) || ((z = obj instanceof JSONObject)) || ((z2 = obj instanceof JSONArray))) {
            return obj;
        } else {
            if (obj instanceof Map) {
                JSONObject jSONObject2 = new JSONObject();
                Map map = (Map) obj;
                for (Object next : map.keySet()) {
                    if (next instanceof String) {
                        try {
                            jSONObject2.put((String) next, encode(map.get(next)));
                        } catch (JSONException e2) {
                            throw new RuntimeException(e2);
                        }
                    } else {
                        throw new IllegalArgumentException("Object keys must be strings.");
                    }
                }
                return jSONObject2;
            } else if (obj instanceof List) {
                JSONArray jSONArray = new JSONArray();
                for (Object encode : (List) obj) {
                    jSONArray.put(encode(encode));
                }
                return jSONArray;
            } else if (z) {
                JSONObject jSONObject3 = new JSONObject();
                JSONObject jSONObject4 = (JSONObject) obj;
                Iterator<String> keys = jSONObject4.keys();
                while (keys.hasNext()) {
                    String next2 = keys.next();
                    if (next2 != null) {
                        try {
                            jSONObject3.put(next2, encode(jSONObject4.opt(next2)));
                        } catch (JSONException e3) {
                            throw new RuntimeException(e3);
                        }
                    } else {
                        throw new IllegalArgumentException("Object keys cannot be null.");
                    }
                }
                return jSONObject3;
            } else if (z2) {
                JSONArray jSONArray2 = new JSONArray();
                JSONArray jSONArray3 = (JSONArray) obj;
                for (int i = 0; i < jSONArray3.length(); i++) {
                    jSONArray2.put(encode(jSONArray3.opt(i)));
                }
                return jSONArray2;
            } else {
                throw new IllegalArgumentException("Object cannot be encoded in JSON: " + obj);
            }
        }
    }

    public Object decode(Object obj) {
        if ((obj instanceof Number) || (obj instanceof String) || (obj instanceof Boolean)) {
            return obj;
        }
        if (obj instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) obj;
            if (jSONObject.has("@type")) {
                String optString = jSONObject.optString("@type");
                String optString2 = jSONObject.optString("value");
                if (optString.equals(LONG_TYPE)) {
                    try {
                        return Long.valueOf(Long.parseLong(optString2));
                    } catch (NumberFormatException unused) {
                        throw new IllegalArgumentException("Invalid Long format:" + optString2);
                    }
                } else if (optString.equals(UNSIGNED_LONG_TYPE)) {
                    try {
                        return Long.valueOf(Long.parseLong(optString2));
                    } catch (NumberFormatException unused2) {
                        throw new IllegalArgumentException("Invalid Long format:" + optString2);
                    }
                }
            }
            HashMap hashMap = new HashMap();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, decode(jSONObject.opt(next)));
            }
            return hashMap;
        } else if (obj instanceof JSONArray) {
            ArrayList arrayList = new ArrayList();
            int i = 0;
            while (true) {
                JSONArray jSONArray = (JSONArray) obj;
                if (i >= jSONArray.length()) {
                    return arrayList;
                }
                arrayList.add(decode(jSONArray.opt(i)));
                i++;
            }
        } else if (obj == JSONObject.NULL) {
            return null;
        } else {
            throw new IllegalArgumentException("Object cannot be decoded from JSON: " + obj);
        }
    }
}

package com.brentvatne.common.toolbox;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007J$\u0010\t\u001a\u00020\n2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000b\u001a\u00020\nH\u0007J\u001c\u0010\f\u001a\u00020\r2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007J$\u0010\f\u001a\u00020\r2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000b\u001a\u00020\rH\u0007J\u001e\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007J(\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\u000fH\u0007J\u001c\u0010\u0010\u001a\u00020\u00112\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007J$\u0010\u0010\u001a\u00020\u00112\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000b\u001a\u00020\u0011H\u0007J\u001c\u0010\u0012\u001a\u00020\u00132\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007J$\u0010\u0012\u001a\u00020\u00132\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000b\u001a\u00020\u0013H\u0007J\u001e\u0010\u0014\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007J\u001e\u0010\u0015\u001a\u0004\u0018\u00010\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007J(\u0010\u0015\u001a\u0004\u0018\u00010\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\bH\u0007J\u001a\u0010\u0016\u001a\u00020\u00132\b\u0010\u0017\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0018\u001a\u00020\u0013H\u0007J-\u0010\u0019\u001a\u00020\n2\u000e\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u001b2\u000e\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u001bH\u0007¢\u0006\u0002\u0010\u001dJ\u001c\u0010\u001e\u001a\u00020\n2\b\u0010\u001a\u001a\u0004\u0018\u00010\b2\b\u0010\u001c\u001a\u0004\u0018\u00010\bH\u0007J<\u0010\u001f\u001a\u00020\n2\u0018\u0010 \u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010!2\u0018\u0010\"\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010!H\u0007J \u0010#\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u0013\u0018\u00010!2\b\u0010$\u001a\u0004\u0018\u00010\u0006H\u0007J\"\u0010%\u001a\u0012\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010!2\b\u0010$\u001a\u0004\u0018\u00010\u0006H\u0007¨\u0006&"}, d2 = {"Lcom/brentvatne/common/toolbox/ReactBridgeUtils;", "", "()V", "safeGetArray", "Lcom/facebook/react/bridge/ReadableArray;", "map", "Lcom/facebook/react/bridge/ReadableMap;", "key", "", "safeGetBool", "", "fallback", "safeGetDouble", "", "safeGetDynamic", "Lcom/facebook/react/bridge/Dynamic;", "safeGetFloat", "", "safeGetInt", "", "safeGetMap", "safeGetString", "safeParseInt", "value", "default", "safeStringArrayEquals", "str1", "", "str2", "([Ljava/lang/String;[Ljava/lang/String;)Z", "safeStringEquals", "safeStringMapEquals", "first", "", "second", "toIntMap", "readableMap", "toStringMap", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ReactBridgeUtils.kt */
public final class ReactBridgeUtils {
    public static final ReactBridgeUtils INSTANCE = new ReactBridgeUtils();

    private ReactBridgeUtils() {
    }

    @JvmStatic
    public static final String safeGetString(ReadableMap readableMap, String str, String str2) {
        if (readableMap == null) {
            return str2;
        }
        Intrinsics.checkNotNull(str);
        return (!readableMap.hasKey(str) || readableMap.isNull(str)) ? str2 : readableMap.getString(str);
    }

    @JvmStatic
    public static final String safeGetString(ReadableMap readableMap, String str) {
        return safeGetString(readableMap, str, (String) null);
    }

    @JvmStatic
    public static final Dynamic safeGetDynamic(ReadableMap readableMap, String str, Dynamic dynamic) {
        if (readableMap == null) {
            return dynamic;
        }
        Intrinsics.checkNotNull(str);
        return (!readableMap.hasKey(str) || readableMap.isNull(str)) ? dynamic : readableMap.getDynamic(str);
    }

    @JvmStatic
    public static final Dynamic safeGetDynamic(ReadableMap readableMap, String str) {
        return safeGetDynamic(readableMap, str, (Dynamic) null);
    }

    @JvmStatic
    public static final boolean safeGetBool(ReadableMap readableMap, String str, boolean z) {
        if (readableMap == null) {
            return z;
        }
        Intrinsics.checkNotNull(str);
        return (!readableMap.hasKey(str) || readableMap.isNull(str)) ? z : readableMap.getBoolean(str);
    }

    @JvmStatic
    public static final ReadableMap safeGetMap(ReadableMap readableMap, String str) {
        if (readableMap != null) {
            Intrinsics.checkNotNull(str);
            if (readableMap.hasKey(str) && !readableMap.isNull(str)) {
                return readableMap.getMap(str);
            }
        }
        return null;
    }

    @JvmStatic
    public static final ReadableArray safeGetArray(ReadableMap readableMap, String str) {
        if (readableMap != null) {
            Intrinsics.checkNotNull(str);
            if (readableMap.hasKey(str) && !readableMap.isNull(str)) {
                return readableMap.getArray(str);
            }
        }
        return null;
    }

    @JvmStatic
    public static final int safeGetInt(ReadableMap readableMap, String str, int i) {
        if (readableMap == null) {
            return i;
        }
        Intrinsics.checkNotNull(str);
        return (!readableMap.hasKey(str) || readableMap.isNull(str)) ? i : readableMap.getInt(str);
    }

    @JvmStatic
    public static final int safeGetInt(ReadableMap readableMap, String str) {
        return safeGetInt(readableMap, str, 0);
    }

    @JvmStatic
    public static final double safeGetDouble(ReadableMap readableMap, String str, double d) {
        if (readableMap == null) {
            return d;
        }
        Intrinsics.checkNotNull(str);
        return (!readableMap.hasKey(str) || readableMap.isNull(str)) ? d : readableMap.getDouble(str);
    }

    @JvmStatic
    public static final double safeGetDouble(ReadableMap readableMap, String str) {
        return safeGetDouble(readableMap, str, 0.0d);
    }

    @JvmStatic
    public static final float safeGetFloat(ReadableMap readableMap, String str, float f) {
        if (readableMap == null) {
            return f;
        }
        Intrinsics.checkNotNull(str);
        return (!readableMap.hasKey(str) || readableMap.isNull(str)) ? f : (float) readableMap.getDouble(str);
    }

    @JvmStatic
    public static final float safeGetFloat(ReadableMap readableMap, String str) {
        return safeGetFloat(readableMap, str, 0.0f);
    }

    @JvmStatic
    public static final int safeParseInt(String str, int i) {
        if (str == null) {
            return i;
        }
        try {
            return Integer.parseInt(str);
        } catch (Exception unused) {
            return i;
        }
    }

    @JvmStatic
    public static final Map<String, String> toStringMap(ReadableMap readableMap) {
        if (readableMap == null) {
            return null;
        }
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        Intrinsics.checkNotNullExpressionValue(keySetIterator, "keySetIterator(...)");
        if (!keySetIterator.hasNextKey()) {
            return null;
        }
        Map<String, String> hashMap = new HashMap<>();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            Intrinsics.checkNotNull(nextKey);
            hashMap.put(nextKey, readableMap.getString(nextKey));
        }
        return hashMap;
    }

    @JvmStatic
    public static final Map<String, Integer> toIntMap(ReadableMap readableMap) {
        if (readableMap == null) {
            return null;
        }
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        Intrinsics.checkNotNullExpressionValue(keySetIterator, "keySetIterator(...)");
        if (!keySetIterator.hasNextKey()) {
            return null;
        }
        Map<String, Integer> hashMap = new HashMap<>();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            Intrinsics.checkNotNull(nextKey);
            hashMap.put(nextKey, Integer.valueOf(readableMap.getInt(nextKey)));
        }
        return hashMap;
    }

    @JvmStatic
    public static final boolean safeStringEquals(String str, String str2) {
        if (str == null && str2 == null) {
            return true;
        }
        if (str == null || str2 == null) {
            return false;
        }
        return Intrinsics.areEqual((Object) str, (Object) str2);
    }

    @JvmStatic
    public static final boolean safeStringArrayEquals(String[] strArr, String[] strArr2) {
        if (strArr == null && strArr2 == null) {
            return true;
        }
        if (strArr == null || strArr2 == null || strArr.length != strArr2.length) {
            return false;
        }
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            if (Intrinsics.areEqual((Object) strArr[i], (Object) strArr2[i])) {
                return false;
            }
        }
        return true;
    }

    @JvmStatic
    public static final boolean safeStringMapEquals(Map<String, String> map, Map<String, String> map2) {
        if (map == null && map2 == null) {
            return true;
        }
        if (map == null || map2 == null || map.size() != map2.size()) {
            return false;
        }
        for (String next : map.keySet()) {
            if (!safeStringEquals(map.get(next), map2.get(next))) {
                return false;
            }
        }
        return true;
    }
}

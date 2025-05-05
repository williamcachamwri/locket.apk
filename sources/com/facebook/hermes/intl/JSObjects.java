package com.facebook.hermes.intl;

import java.util.HashMap;
import java.util.Map;

public class JSObjects {
    private static Object s_null = new NullObject();
    private static Object s_undefined = new UndefinedObject();

    public static Object newString(String str) {
        return str;
    }

    private static class UndefinedObject {
        private UndefinedObject() {
        }
    }

    private static class NullObject {
        private NullObject() {
        }
    }

    public static Object Undefined() {
        return s_undefined;
    }

    public static boolean isUndefined(Object obj) {
        return obj instanceof UndefinedObject;
    }

    public static Object Null() {
        return s_null;
    }

    public static boolean isNull(Object obj) {
        return obj instanceof NullObject;
    }

    public static boolean isString(Object obj) {
        return obj instanceof String;
    }

    public static Object newString() {
        return new String();
    }

    public static boolean isBoolean(Object obj) {
        return obj instanceof Boolean;
    }

    public static Object newBoolean() {
        return new Boolean(false);
    }

    public static Object newBoolean(boolean z) {
        return new Boolean(z);
    }

    public static Object newBoolean(String str) {
        return Boolean.valueOf(str);
    }

    public static boolean getJavaBoolean(Object obj) {
        return ((Boolean) obj).booleanValue();
    }

    public static boolean isNumber(Object obj) {
        return obj instanceof Double;
    }

    public static Object newNumber(double d) {
        return new Double(d);
    }

    public static double getJavaDouble(Object obj) {
        return ((Double) obj).doubleValue();
    }

    public static String getJavaString(Object obj) {
        return (String) obj;
    }

    public static boolean isArray(Object obj) {
        return obj instanceof Object[];
    }

    public static boolean isObject(Object obj) {
        return obj instanceof HashMap;
    }

    public static Map<String, Object> getJavaMap(Object obj) {
        return (HashMap) obj;
    }

    public static Object newObject() {
        return new HashMap();
    }

    public static Object Get(Object obj, String str) {
        HashMap hashMap = (HashMap) obj;
        if (!hashMap.containsKey(str)) {
            return Undefined();
        }
        Object obj2 = hashMap.get(str);
        return obj2 == null ? Null() : obj2;
    }

    public static void Put(Object obj, String str, Object obj2) {
        ((HashMap) obj).put(str, obj2);
    }
}

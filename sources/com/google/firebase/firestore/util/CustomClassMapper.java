package com.google.firebase.firestore.util;

import android.net.Uri;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.Blob;
import com.google.firebase.firestore.DocumentId;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.PropertyName;
import com.google.firebase.firestore.ServerTimestamp;
import com.google.firebase.firestore.VectorValue;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CustomClassMapper {
    private static final int MAX_DEPTH = 500;
    private static final ConcurrentMap<Class<?>, BeanMapper<?>> mappers = new ConcurrentHashMap();

    private static void hardAssert(boolean z) {
        hardAssert(z, "Internal inconsistency");
    }

    /* access modifiers changed from: private */
    public static void hardAssert(boolean z, String str) {
        if (!z) {
            throw new RuntimeException("Hard assert failed: " + str);
        }
    }

    public static Object convertToPlainJavaTypes(Object obj) {
        return serialize(obj);
    }

    public static Map<String, Object> convertToPlainJavaTypes(Map<?, Object> map) {
        Object serialize = serialize(map);
        hardAssert(serialize instanceof Map);
        return (Map) serialize;
    }

    public static <T> T convertToCustomClass(Object obj, Class<T> cls, DocumentReference documentReference) {
        return deserializeToClass(obj, cls, new DeserializeContext(ErrorPath.EMPTY, documentReference));
    }

    private static <T> Object serialize(T t) {
        return serialize(t, ErrorPath.EMPTY);
    }

    /* access modifiers changed from: private */
    public static <T> Object serialize(T t, ErrorPath errorPath) {
        if (errorPath.getLength() > 500) {
            throw serializeError(errorPath, "Exceeded maximum depth of 500, which likely indicates there's an object cycle");
        } else if (t == null) {
            return null;
        } else {
            if (t instanceof Number) {
                if ((t instanceof Long) || (t instanceof Integer) || (t instanceof Double) || (t instanceof Float)) {
                    return t;
                }
                throw serializeError(errorPath, String.format("Numbers of type %s are not supported, please use an int, long, float or double", new Object[]{t.getClass().getSimpleName()}));
            } else if ((t instanceof String) || (t instanceof Boolean)) {
                return t;
            } else {
                if (t instanceof Character) {
                    throw serializeError(errorPath, "Characters are not supported, please use Strings");
                } else if (t instanceof Map) {
                    HashMap hashMap = new HashMap();
                    for (Map.Entry entry : ((Map) t).entrySet()) {
                        Object key = entry.getKey();
                        if (key instanceof String) {
                            String str = (String) key;
                            hashMap.put(str, serialize(entry.getValue(), errorPath.child(str)));
                        } else {
                            throw serializeError(errorPath, "Maps with non-string keys are not supported");
                        }
                    }
                    return hashMap;
                } else if (t instanceof Collection) {
                    if (t instanceof List) {
                        List list = (List) t;
                        ArrayList arrayList = new ArrayList(list.size());
                        for (int i = 0; i < list.size(); i++) {
                            arrayList.add(serialize(list.get(i), errorPath.child("[" + i + "]")));
                        }
                        return arrayList;
                    }
                    throw serializeError(errorPath, "Serializing Collections is not supported, please use Lists instead");
                } else if (t.getClass().isArray()) {
                    throw serializeError(errorPath, "Serializing Arrays is not supported, please use Lists instead");
                } else if (t instanceof Enum) {
                    String name = ((Enum) t).name();
                    try {
                        return BeanMapper.propertyName(t.getClass().getField(name));
                    } catch (NoSuchFieldException unused) {
                        return name;
                    }
                } else if ((t instanceof Date) || (t instanceof Timestamp) || (t instanceof GeoPoint) || (t instanceof Blob) || (t instanceof DocumentReference) || (t instanceof FieldValue) || (t instanceof VectorValue)) {
                    return t;
                } else {
                    if ((t instanceof Uri) || (t instanceof URI) || (t instanceof URL)) {
                        return t.toString();
                    }
                    return loadOrCreateBeanMapperForClass(t.getClass()).serialize(t, errorPath);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static <T> T deserializeToType(Object obj, Type type, DeserializeContext deserializeContext) {
        if (obj == null) {
            return null;
        }
        if (type instanceof ParameterizedType) {
            return deserializeToParameterizedType(obj, (ParameterizedType) type, deserializeContext);
        }
        if (type instanceof Class) {
            return deserializeToClass(obj, (Class) type, deserializeContext);
        }
        boolean z = true;
        if (type instanceof WildcardType) {
            WildcardType wildcardType = (WildcardType) type;
            if (wildcardType.getLowerBounds().length <= 0) {
                Type[] upperBounds = wildcardType.getUpperBounds();
                if (upperBounds.length <= 0) {
                    z = false;
                }
                hardAssert(z, "Unexpected type bounds on wildcard " + type);
                return deserializeToType(obj, upperBounds[0], deserializeContext);
            }
            throw deserializeError(deserializeContext.errorPath, "Generic lower-bounded wildcard types are not supported");
        } else if (type instanceof TypeVariable) {
            Type[] bounds = ((TypeVariable) type).getBounds();
            if (bounds.length <= 0) {
                z = false;
            }
            hardAssert(z, "Unexpected type bounds on type variable " + type);
            return deserializeToType(obj, bounds[0], deserializeContext);
        } else if (type instanceof GenericArrayType) {
            throw deserializeError(deserializeContext.errorPath, "Generic Arrays are not supported, please use Lists instead");
        } else {
            throw deserializeError(deserializeContext.errorPath, "Unknown type encountered: " + type);
        }
    }

    private static <T> T deserializeToClass(Object obj, Class<T> cls, DeserializeContext deserializeContext) {
        if (obj == null) {
            return null;
        }
        if (cls.isPrimitive() || Number.class.isAssignableFrom(cls) || Boolean.class.isAssignableFrom(cls) || Character.class.isAssignableFrom(cls)) {
            return deserializeToPrimitive(obj, cls, deserializeContext);
        }
        if (String.class.isAssignableFrom(cls)) {
            return convertString(obj, deserializeContext);
        }
        if (Date.class.isAssignableFrom(cls)) {
            return convertDate(obj, deserializeContext);
        }
        if (Timestamp.class.isAssignableFrom(cls)) {
            return convertTimestamp(obj, deserializeContext);
        }
        if (Blob.class.isAssignableFrom(cls)) {
            return convertBlob(obj, deserializeContext);
        }
        if (GeoPoint.class.isAssignableFrom(cls)) {
            return convertGeoPoint(obj, deserializeContext);
        }
        if (DocumentReference.class.isAssignableFrom(cls)) {
            return convertDocumentReference(obj, deserializeContext);
        }
        if (VectorValue.class.isAssignableFrom(cls)) {
            return convertVectorValue(obj, deserializeContext);
        }
        if (cls.isArray()) {
            throw deserializeError(deserializeContext.errorPath, "Converting to Arrays is not supported, please use Lists instead");
        } else if (cls.getTypeParameters().length > 0) {
            throw deserializeError(deserializeContext.errorPath, "Class " + cls.getName() + " has generic type parameters");
        } else if (cls.equals(Object.class)) {
            return obj;
        } else {
            if (cls.isEnum()) {
                return deserializeToEnum(obj, cls, deserializeContext);
            }
            return convertBean(obj, cls, deserializeContext);
        }
    }

    private static <T> T deserializeToParameterizedType(Object obj, ParameterizedType parameterizedType, DeserializeContext deserializeContext) {
        Class cls = (Class) parameterizedType.getRawType();
        int i = 0;
        if (List.class.isAssignableFrom(cls)) {
            Type type = parameterizedType.getActualTypeArguments()[0];
            if (obj instanceof List) {
                List list = (List) obj;
                T arrayList = new ArrayList(list.size());
                while (i < list.size()) {
                    arrayList.add(deserializeToType(list.get(i), type, deserializeContext.newInstanceWithErrorPath(deserializeContext.errorPath.child("[" + i + "]"))));
                    i++;
                }
                return arrayList;
            }
            throw deserializeError(deserializeContext.errorPath, "Expected a List, but got a " + obj.getClass());
        } else if (Map.class.isAssignableFrom(cls)) {
            Type type2 = parameterizedType.getActualTypeArguments()[0];
            Type type3 = parameterizedType.getActualTypeArguments()[1];
            if (type2.equals(String.class)) {
                Map<String, Object> expectMap = expectMap(obj, deserializeContext);
                T hashMap = new HashMap();
                for (Map.Entry next : expectMap.entrySet()) {
                    hashMap.put((String) next.getKey(), deserializeToType(next.getValue(), type3, deserializeContext.newInstanceWithErrorPath(deserializeContext.errorPath.child((String) next.getKey()))));
                }
                return hashMap;
            }
            throw deserializeError(deserializeContext.errorPath, "Only Maps with string keys are supported, but found Map with key type " + type2);
        } else if (!Collection.class.isAssignableFrom(cls)) {
            Map<String, Object> expectMap2 = expectMap(obj, deserializeContext);
            BeanMapper loadOrCreateBeanMapperForClass = loadOrCreateBeanMapperForClass(cls);
            HashMap hashMap2 = new HashMap();
            TypeVariable[] typeParameters = loadOrCreateBeanMapperForClass.clazz.getTypeParameters();
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            if (actualTypeArguments.length == typeParameters.length) {
                while (i < typeParameters.length) {
                    hashMap2.put(typeParameters[i], actualTypeArguments[i]);
                    i++;
                }
                return loadOrCreateBeanMapperForClass.deserialize(expectMap2, hashMap2, deserializeContext);
            }
            throw new IllegalStateException("Mismatched lengths for type variables and actual types");
        } else {
            throw deserializeError(deserializeContext.errorPath, "Collections are not supported, please use Lists instead");
        }
    }

    private static <T> T deserializeToPrimitive(Object obj, Class<T> cls, DeserializeContext deserializeContext) {
        if (Integer.class.isAssignableFrom(cls) || Integer.TYPE.isAssignableFrom(cls)) {
            return convertInteger(obj, deserializeContext);
        }
        if (Boolean.class.isAssignableFrom(cls) || Boolean.TYPE.isAssignableFrom(cls)) {
            return convertBoolean(obj, deserializeContext);
        }
        if (Double.class.isAssignableFrom(cls) || Double.TYPE.isAssignableFrom(cls)) {
            return convertDouble(obj, deserializeContext);
        }
        if (Long.class.isAssignableFrom(cls) || Long.TYPE.isAssignableFrom(cls)) {
            return convertLong(obj, deserializeContext);
        }
        if (Float.class.isAssignableFrom(cls) || Float.TYPE.isAssignableFrom(cls)) {
            return Float.valueOf(convertDouble(obj, deserializeContext).floatValue());
        }
        throw deserializeError(deserializeContext.errorPath, String.format("Deserializing values to %s is not supported", new Object[]{cls.getSimpleName()}));
    }

    private static <T> T deserializeToEnum(Object obj, Class<T> cls, DeserializeContext deserializeContext) {
        if (obj instanceof String) {
            String str = (String) obj;
            Field[] fields = cls.getFields();
            int length = fields.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    Field field = fields[i];
                    if (field.isEnumConstant() && str.equals(BeanMapper.propertyName(field))) {
                        str = field.getName();
                        break;
                    }
                    i++;
                }
            }
            try {
                return Enum.valueOf(cls, str);
            } catch (IllegalArgumentException unused) {
                throw deserializeError(deserializeContext.errorPath, "Could not find enum value of " + cls.getName() + " for value \"" + str + "\"");
            }
        } else {
            throw deserializeError(deserializeContext.errorPath, "Expected a String while deserializing to enum " + cls + " but got a " + obj.getClass());
        }
    }

    private static <T> BeanMapper<T> loadOrCreateBeanMapperForClass(Class<T> cls) {
        ConcurrentMap<Class<?>, BeanMapper<?>> concurrentMap = mappers;
        BeanMapper<T> beanMapper = (BeanMapper) concurrentMap.get(cls);
        if (beanMapper != null) {
            return beanMapper;
        }
        BeanMapper<T> beanMapper2 = new BeanMapper<>(cls);
        concurrentMap.put(cls, beanMapper2);
        return beanMapper2;
    }

    private static Map<String, Object> expectMap(Object obj, DeserializeContext deserializeContext) {
        if (obj instanceof Map) {
            return (Map) obj;
        }
        throw deserializeError(deserializeContext.errorPath, "Expected a Map while deserializing, but got a " + obj.getClass());
    }

    private static Integer convertInteger(Object obj, DeserializeContext deserializeContext) {
        if (obj instanceof Integer) {
            return (Integer) obj;
        }
        if ((obj instanceof Long) || (obj instanceof Double)) {
            Number number = (Number) obj;
            double doubleValue = number.doubleValue();
            if (doubleValue >= -2.147483648E9d && doubleValue <= 2.147483647E9d) {
                return Integer.valueOf(number.intValue());
            }
            throw deserializeError(deserializeContext.errorPath, "Numeric value out of 32-bit integer range: " + doubleValue + ". Did you mean to use a long or double instead of an int?");
        }
        throw deserializeError(deserializeContext.errorPath, "Failed to convert a value of type " + obj.getClass().getName() + " to int");
    }

    private static Long convertLong(Object obj, DeserializeContext deserializeContext) {
        if (obj instanceof Integer) {
            return Long.valueOf(((Integer) obj).longValue());
        }
        if (obj instanceof Long) {
            return (Long) obj;
        }
        if (obj instanceof Double) {
            Double d = (Double) obj;
            if (d.doubleValue() >= -9.223372036854776E18d && d.doubleValue() <= 9.223372036854776E18d) {
                return Long.valueOf(d.longValue());
            }
            throw deserializeError(deserializeContext.errorPath, "Numeric value out of 64-bit long range: " + d + ". Did you mean to use a double instead of a long?");
        }
        throw deserializeError(deserializeContext.errorPath, "Failed to convert a value of type " + obj.getClass().getName() + " to long");
    }

    private static Double convertDouble(Object obj, DeserializeContext deserializeContext) {
        if (obj instanceof Integer) {
            return Double.valueOf(((Integer) obj).doubleValue());
        }
        if (obj instanceof Long) {
            Long l = (Long) obj;
            Double valueOf = Double.valueOf(l.doubleValue());
            if (valueOf.longValue() == l.longValue()) {
                return valueOf;
            }
            throw deserializeError(deserializeContext.errorPath, "Loss of precision while converting number to double: " + obj + ". Did you mean to use a 64-bit long instead?");
        } else if (obj instanceof Double) {
            return (Double) obj;
        } else {
            throw deserializeError(deserializeContext.errorPath, "Failed to convert a value of type " + obj.getClass().getName() + " to double");
        }
    }

    private static Boolean convertBoolean(Object obj, DeserializeContext deserializeContext) {
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        throw deserializeError(deserializeContext.errorPath, "Failed to convert value of type " + obj.getClass().getName() + " to boolean");
    }

    private static String convertString(Object obj, DeserializeContext deserializeContext) {
        if (obj instanceof String) {
            return (String) obj;
        }
        throw deserializeError(deserializeContext.errorPath, "Failed to convert value of type " + obj.getClass().getName() + " to String");
    }

    private static Date convertDate(Object obj, DeserializeContext deserializeContext) {
        if (obj instanceof Date) {
            return (Date) obj;
        }
        if (obj instanceof Timestamp) {
            return ((Timestamp) obj).toDate();
        }
        throw deserializeError(deserializeContext.errorPath, "Failed to convert value of type " + obj.getClass().getName() + " to Date");
    }

    private static Timestamp convertTimestamp(Object obj, DeserializeContext deserializeContext) {
        if (obj instanceof Timestamp) {
            return (Timestamp) obj;
        }
        if (obj instanceof Date) {
            return new Timestamp((Date) obj);
        }
        throw deserializeError(deserializeContext.errorPath, "Failed to convert value of type " + obj.getClass().getName() + " to Timestamp");
    }

    private static Blob convertBlob(Object obj, DeserializeContext deserializeContext) {
        if (obj instanceof Blob) {
            return (Blob) obj;
        }
        throw deserializeError(deserializeContext.errorPath, "Failed to convert value of type " + obj.getClass().getName() + " to Blob");
    }

    private static GeoPoint convertGeoPoint(Object obj, DeserializeContext deserializeContext) {
        if (obj instanceof GeoPoint) {
            return (GeoPoint) obj;
        }
        throw deserializeError(deserializeContext.errorPath, "Failed to convert value of type " + obj.getClass().getName() + " to GeoPoint");
    }

    private static VectorValue convertVectorValue(Object obj, DeserializeContext deserializeContext) {
        if (obj instanceof VectorValue) {
            return (VectorValue) obj;
        }
        throw deserializeError(deserializeContext.errorPath, "Failed to convert value of type " + obj.getClass().getName() + " to VectorValue");
    }

    private static DocumentReference convertDocumentReference(Object obj, DeserializeContext deserializeContext) {
        if (obj instanceof DocumentReference) {
            return (DocumentReference) obj;
        }
        throw deserializeError(deserializeContext.errorPath, "Failed to convert value of type " + obj.getClass().getName() + " to DocumentReference");
    }

    private static <T> T convertBean(Object obj, Class<T> cls, DeserializeContext deserializeContext) {
        BeanMapper<T> loadOrCreateBeanMapperForClass = loadOrCreateBeanMapperForClass(cls);
        if (obj instanceof Map) {
            return loadOrCreateBeanMapperForClass.deserialize(expectMap(obj, deserializeContext), deserializeContext);
        }
        throw deserializeError(deserializeContext.errorPath, "Can't convert object of type " + obj.getClass().getName() + " to type " + cls.getName());
    }

    private static IllegalArgumentException serializeError(ErrorPath errorPath, String str) {
        String str2 = "Could not serialize object. " + str;
        if (errorPath.getLength() > 0) {
            str2 = str2 + " (found in field '" + errorPath.toString() + "')";
        }
        return new IllegalArgumentException(str2);
    }

    /* access modifiers changed from: private */
    public static RuntimeException deserializeError(ErrorPath errorPath, String str) {
        String str2 = "Could not deserialize object. " + str;
        if (errorPath.getLength() > 0) {
            str2 = str2 + " (found in field '" + errorPath.toString() + "')";
        }
        return new RuntimeException(str2);
    }

    private static class BeanMapper<T> {
        /* access modifiers changed from: private */
        public final Class<T> clazz;
        private final Constructor<T> constructor;
        private final HashSet<String> documentIdPropertyNames = new HashSet<>();
        private final Map<String, Field> fields = new HashMap();
        private final Map<String, Method> getters = new HashMap();
        private final Map<String, String> properties = new HashMap();
        private final HashSet<String> serverTimestamps = new HashSet<>();
        private final Map<String, Method> setters = new HashMap();
        private final boolean throwOnUnknownProperties;
        private final boolean warnOnUnknownProperties;

        /* JADX WARNING: Removed duplicated region for block: B:24:0x00cf  */
        /* JADX WARNING: Removed duplicated region for block: B:51:0x01c2  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        BeanMapper(java.lang.Class<T> r12) {
            /*
                r11 = this;
                r11.<init>()
                r11.clazz = r12
                java.lang.Class<com.google.firebase.firestore.ThrowOnExtraProperties> r0 = com.google.firebase.firestore.ThrowOnExtraProperties.class
                boolean r0 = r12.isAnnotationPresent(r0)
                r11.throwOnUnknownProperties = r0
                java.lang.Class<com.google.firebase.firestore.IgnoreExtraProperties> r0 = com.google.firebase.firestore.IgnoreExtraProperties.class
                boolean r0 = r12.isAnnotationPresent(r0)
                r1 = 1
                r0 = r0 ^ r1
                r11.warnOnUnknownProperties = r0
                java.util.HashMap r0 = new java.util.HashMap
                r0.<init>()
                r11.properties = r0
                java.util.HashMap r0 = new java.util.HashMap
                r0.<init>()
                r11.setters = r0
                java.util.HashMap r0 = new java.util.HashMap
                r0.<init>()
                r11.getters = r0
                java.util.HashMap r0 = new java.util.HashMap
                r0.<init>()
                r11.fields = r0
                java.util.HashSet r0 = new java.util.HashSet
                r0.<init>()
                r11.serverTimestamps = r0
                java.util.HashSet r0 = new java.util.HashSet
                r0.<init>()
                r11.documentIdPropertyNames = r0
                r0 = 0
                java.lang.Class[] r2 = new java.lang.Class[r0]     // Catch:{ NoSuchMethodException -> 0x004c }
                java.lang.reflect.Constructor r2 = r12.getDeclaredConstructor(r2)     // Catch:{ NoSuchMethodException -> 0x004c }
                r2.setAccessible(r1)     // Catch:{ NoSuchMethodException -> 0x004c }
                goto L_0x004d
            L_0x004c:
                r2 = 0
            L_0x004d:
                r11.constructor = r2
                java.lang.reflect.Method[] r2 = r12.getMethods()
                int r3 = r2.length
                r4 = r0
            L_0x0055:
                if (r4 >= r3) goto L_0x00a4
                r5 = r2[r4]
                boolean r6 = shouldIncludeGetter(r5)
                if (r6 == 0) goto L_0x00a1
                java.lang.String r6 = propertyName((java.lang.reflect.Method) r5)
                r11.addProperty(r6)
                r5.setAccessible(r1)
                java.util.Map<java.lang.String, java.lang.reflect.Method> r7 = r11.getters
                boolean r7 = r7.containsKey(r6)
                if (r7 != 0) goto L_0x007a
                java.util.Map<java.lang.String, java.lang.reflect.Method> r7 = r11.getters
                r7.put(r6, r5)
                r11.applyGetterAnnotations(r5)
                goto L_0x00a1
            L_0x007a:
                java.lang.RuntimeException r0 = new java.lang.RuntimeException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r2 = "Found conflicting getters for name "
                r1.<init>(r2)
                java.lang.String r2 = r5.getName()
                java.lang.StringBuilder r1 = r1.append(r2)
                java.lang.String r2 = " on class "
                java.lang.StringBuilder r1 = r1.append(r2)
                java.lang.String r12 = r12.getName()
                java.lang.StringBuilder r12 = r1.append(r12)
                java.lang.String r12 = r12.toString()
                r0.<init>(r12)
                throw r0
            L_0x00a1:
                int r4 = r4 + 1
                goto L_0x0055
            L_0x00a4:
                java.lang.reflect.Field[] r2 = r12.getFields()
                int r3 = r2.length
                r4 = r0
            L_0x00aa:
                if (r4 >= r3) goto L_0x00c1
                r5 = r2[r4]
                boolean r6 = shouldIncludeField(r5)
                if (r6 == 0) goto L_0x00be
                java.lang.String r6 = propertyName((java.lang.reflect.Field) r5)
                r11.addProperty(r6)
                r11.applyFieldAnnotations(r5)
            L_0x00be:
                int r4 = r4 + 1
                goto L_0x00aa
            L_0x00c1:
                java.util.HashMap r2 = new java.util.HashMap
                r2.<init>()
                r3 = r12
            L_0x00c7:
                java.lang.reflect.Method[] r4 = r3.getDeclaredMethods()
                int r5 = r4.length
                r6 = r0
            L_0x00cd:
                if (r6 >= r5) goto L_0x01ba
                r7 = r4[r6]
                boolean r8 = shouldIncludeSetter(r7)
                if (r8 == 0) goto L_0x01b6
                java.lang.String r8 = propertyName((java.lang.reflect.Method) r7)
                java.util.Map<java.lang.String, java.lang.String> r9 = r11.properties
                java.util.Locale r10 = java.util.Locale.US
                java.lang.String r10 = r8.toLowerCase(r10)
                java.lang.Object r9 = r9.get(r10)
                java.lang.String r9 = (java.lang.String) r9
                if (r9 == 0) goto L_0x01b6
                boolean r9 = r9.equals(r8)
                if (r9 == 0) goto L_0x018f
                boolean r9 = r7.isBridge()
                if (r9 == 0) goto L_0x00fc
                r2.put(r8, r7)
                goto L_0x01b6
            L_0x00fc:
                java.util.Map<java.lang.String, java.lang.reflect.Method> r9 = r11.setters
                java.lang.Object r9 = r9.get(r8)
                java.lang.reflect.Method r9 = (java.lang.reflect.Method) r9
                java.lang.Object r10 = r2.get(r8)
                java.lang.reflect.Method r10 = (java.lang.reflect.Method) r10
                if (r9 != 0) goto L_0x0119
                r7.setAccessible(r1)
                java.util.Map<java.lang.String, java.lang.reflect.Method> r9 = r11.setters
                r9.put(r8, r7)
                r11.applySetterAnnotations(r7)
                goto L_0x01b6
            L_0x0119:
                boolean r8 = isSetterOverride(r7, r9)
                if (r8 != 0) goto L_0x01b6
                if (r10 == 0) goto L_0x0127
                boolean r8 = isSetterOverride(r7, r10)
                if (r8 != 0) goto L_0x01b6
            L_0x0127:
                if (r3 != r12) goto L_0x0150
                java.lang.RuntimeException r0 = new java.lang.RuntimeException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r2 = "Class "
                r1.<init>(r2)
                java.lang.String r12 = r12.getName()
                java.lang.StringBuilder r12 = r1.append(r12)
                java.lang.String r1 = " has multiple setter overloads with name "
                java.lang.StringBuilder r12 = r12.append(r1)
                java.lang.String r1 = r7.getName()
                java.lang.StringBuilder r12 = r12.append(r1)
                java.lang.String r12 = r12.toString()
                r0.<init>(r12)
                throw r0
            L_0x0150:
                java.lang.RuntimeException r12 = new java.lang.RuntimeException
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                java.lang.String r1 = "Found conflicting setters with name: "
                r0.<init>(r1)
                java.lang.String r1 = r7.getName()
                java.lang.StringBuilder r0 = r0.append(r1)
                java.lang.String r1 = " (conflicts with "
                java.lang.StringBuilder r0 = r0.append(r1)
                java.lang.String r1 = r9.getName()
                java.lang.StringBuilder r0 = r0.append(r1)
                java.lang.String r1 = " defined on "
                java.lang.StringBuilder r0 = r0.append(r1)
                java.lang.Class r1 = r9.getDeclaringClass()
                java.lang.String r1 = r1.getName()
                java.lang.StringBuilder r0 = r0.append(r1)
                java.lang.String r1 = ")"
                java.lang.StringBuilder r0 = r0.append(r1)
                java.lang.String r0 = r0.toString()
                r12.<init>(r0)
                throw r12
            L_0x018f:
                java.lang.RuntimeException r12 = new java.lang.RuntimeException
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                java.lang.String r1 = "Found setter on "
                r0.<init>(r1)
                java.lang.String r1 = r3.getName()
                java.lang.StringBuilder r0 = r0.append(r1)
                java.lang.String r1 = " with invalid case-sensitive name: "
                java.lang.StringBuilder r0 = r0.append(r1)
                java.lang.String r1 = r7.getName()
                java.lang.StringBuilder r0 = r0.append(r1)
                java.lang.String r0 = r0.toString()
                r12.<init>(r0)
                throw r12
            L_0x01b6:
                int r6 = r6 + 1
                goto L_0x00cd
            L_0x01ba:
                java.lang.reflect.Field[] r4 = r3.getDeclaredFields()
                int r5 = r4.length
                r6 = r0
            L_0x01c0:
                if (r6 >= r5) goto L_0x01ec
                r7 = r4[r6]
                java.lang.String r8 = propertyName((java.lang.reflect.Field) r7)
                java.util.Map<java.lang.String, java.lang.String> r9 = r11.properties
                java.util.Locale r10 = java.util.Locale.US
                java.lang.String r10 = r8.toLowerCase(r10)
                boolean r9 = r9.containsKey(r10)
                if (r9 == 0) goto L_0x01e9
                java.util.Map<java.lang.String, java.lang.reflect.Field> r9 = r11.fields
                boolean r9 = r9.containsKey(r8)
                if (r9 != 0) goto L_0x01e9
                r7.setAccessible(r1)
                java.util.Map<java.lang.String, java.lang.reflect.Field> r9 = r11.fields
                r9.put(r8, r7)
                r11.applyFieldAnnotations(r7)
            L_0x01e9:
                int r6 = r6 + 1
                goto L_0x01c0
            L_0x01ec:
                java.lang.Class r3 = r3.getSuperclass()
                if (r3 == 0) goto L_0x01fa
                java.lang.Class<java.lang.Object> r4 = java.lang.Object.class
                boolean r4 = r3.equals(r4)
                if (r4 == 0) goto L_0x00c7
            L_0x01fa:
                java.util.Map<java.lang.String, java.lang.String> r0 = r11.properties
                boolean r0 = r0.isEmpty()
                if (r0 != 0) goto L_0x024f
                java.util.HashSet<java.lang.String> r0 = r11.documentIdPropertyNames
                java.util.Iterator r0 = r0.iterator()
            L_0x0208:
                boolean r1 = r0.hasNext()
                if (r1 == 0) goto L_0x024e
                java.lang.Object r1 = r0.next()
                java.lang.String r1 = (java.lang.String) r1
                java.util.Map<java.lang.String, java.lang.reflect.Method> r2 = r11.setters
                boolean r2 = r2.containsKey(r1)
                if (r2 != 0) goto L_0x0208
                java.util.Map<java.lang.String, java.lang.reflect.Field> r2 = r11.fields
                boolean r2 = r2.containsKey(r1)
                if (r2 == 0) goto L_0x0225
                goto L_0x0208
            L_0x0225:
                java.lang.RuntimeException r0 = new java.lang.RuntimeException
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                java.lang.String r3 = "@DocumentId is annotated on property "
                r2.<init>(r3)
                java.lang.StringBuilder r1 = r2.append(r1)
                java.lang.String r2 = " of class "
                java.lang.StringBuilder r1 = r1.append(r2)
                java.lang.String r12 = r12.getName()
                java.lang.StringBuilder r12 = r1.append(r12)
                java.lang.String r1 = " but no field or public setter was found"
                java.lang.StringBuilder r12 = r12.append(r1)
                java.lang.String r12 = r12.toString()
                r0.<init>(r12)
                throw r0
            L_0x024e:
                return
            L_0x024f:
                java.lang.RuntimeException r0 = new java.lang.RuntimeException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r2 = "No properties to serialize found on class "
                r1.<init>(r2)
                java.lang.String r12 = r12.getName()
                java.lang.StringBuilder r12 = r1.append(r12)
                java.lang.String r12 = r12.toString()
                r0.<init>(r12)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.util.CustomClassMapper.BeanMapper.<init>(java.lang.Class):void");
        }

        private void addProperty(String str) {
            String put = this.properties.put(str.toLowerCase(Locale.US), str);
            if (put != null && !str.equals(put)) {
                throw new RuntimeException("Found two getters or fields with conflicting case sensitivity for property: " + str.toLowerCase(Locale.US));
            }
        }

        /* access modifiers changed from: package-private */
        public T deserialize(Map<String, Object> map, DeserializeContext deserializeContext) {
            return deserialize(map, Collections.emptyMap(), deserializeContext);
        }

        /* access modifiers changed from: package-private */
        public T deserialize(Map<String, Object> map, Map<TypeVariable<Class<T>>, Type> map2, DeserializeContext deserializeContext) {
            Constructor<T> constructor2 = this.constructor;
            if (constructor2 != null) {
                T newInstance = ApiUtil.newInstance(constructor2);
                HashSet hashSet = new HashSet();
                for (Map.Entry next : map.entrySet()) {
                    String str = (String) next.getKey();
                    ErrorPath child = deserializeContext.errorPath.child(str);
                    if (this.setters.containsKey(str)) {
                        Method method = this.setters.get(str);
                        Type[] genericParameterTypes = method.getGenericParameterTypes();
                        if (genericParameterTypes.length == 1) {
                            ApiUtil.invoke(method, newInstance, CustomClassMapper.deserializeToType(next.getValue(), resolveType(genericParameterTypes[0], map2), deserializeContext.newInstanceWithErrorPath(child)));
                            hashSet.add(str);
                        } else {
                            throw CustomClassMapper.deserializeError(child, "Setter does not have exactly one parameter");
                        }
                    } else if (this.fields.containsKey(str)) {
                        Field field = this.fields.get(str);
                        try {
                            field.set(newInstance, CustomClassMapper.deserializeToType(next.getValue(), resolveType(field.getGenericType(), map2), deserializeContext.newInstanceWithErrorPath(child)));
                            hashSet.add(str);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        String str2 = "No setter/field for " + str + " found on class " + this.clazz.getName();
                        if (this.properties.containsKey(str.toLowerCase(Locale.US))) {
                            str2 = str2 + " (fields/setters are case sensitive!)";
                        }
                        if (this.throwOnUnknownProperties) {
                            throw new RuntimeException(str2);
                        } else if (this.warnOnUnknownProperties) {
                            Class<CustomClassMapper> cls = CustomClassMapper.class;
                            Logger.warn("CustomClassMapper", "%s", str2);
                        }
                    }
                }
                populateDocumentIdProperties(map2, deserializeContext, newInstance, hashSet);
                return newInstance;
            }
            throw CustomClassMapper.deserializeError(deserializeContext.errorPath, "Class " + this.clazz.getName() + " does not define a no-argument constructor. If you are using ProGuard, make sure these constructors are not stripped");
        }

        private void populateDocumentIdProperties(Map<TypeVariable<Class<T>>, Type> map, DeserializeContext deserializeContext, T t, HashSet<String> hashSet) {
            Iterator<String> it = this.documentIdPropertyNames.iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (!hashSet.contains(next)) {
                    ErrorPath child = deserializeContext.errorPath.child(next);
                    if (this.setters.containsKey(next)) {
                        Method method = this.setters.get(next);
                        Type[] genericParameterTypes = method.getGenericParameterTypes();
                        if (genericParameterTypes.length != 1) {
                            throw CustomClassMapper.deserializeError(child, "Setter does not have exactly one parameter");
                        } else if (resolveType(genericParameterTypes[0], map) == String.class) {
                            ApiUtil.invoke(method, t, deserializeContext.documentRef.getId());
                        } else {
                            ApiUtil.invoke(method, t, deserializeContext.documentRef);
                        }
                    } else {
                        Field field = this.fields.get(next);
                        try {
                            if (field.getType() == String.class) {
                                field.set(t, deserializeContext.documentRef.getId());
                            } else {
                                field.set(t, deserializeContext.documentRef);
                            }
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else {
                    throw new RuntimeException("'" + next + "' was found from document " + deserializeContext.documentRef.getPath() + ", cannot apply @DocumentId on this property for class " + this.clazz.getName());
                }
            }
        }

        private Type resolveType(Type type, Map<TypeVariable<Class<T>>, Type> map) {
            if (!(type instanceof TypeVariable)) {
                return type;
            }
            Type type2 = map.get(type);
            if (type2 != null) {
                return type2;
            }
            throw new IllegalStateException("Could not resolve type " + type);
        }

        /* access modifiers changed from: package-private */
        public Map<String, Object> serialize(T t, ErrorPath errorPath) {
            Object obj;
            Object obj2;
            if (this.clazz.isAssignableFrom(t.getClass())) {
                HashMap hashMap = new HashMap();
                for (String next : this.properties.values()) {
                    if (!this.documentIdPropertyNames.contains(next)) {
                        if (this.getters.containsKey(next)) {
                            obj = ApiUtil.invoke(this.getters.get(next), t, new Object[0]);
                        } else {
                            Field field = this.fields.get(next);
                            if (field != null) {
                                try {
                                    obj = field.get(t);
                                } catch (IllegalAccessException e) {
                                    throw new RuntimeException(e);
                                }
                            } else {
                                throw new IllegalStateException("Bean property without field or getter: " + next);
                            }
                        }
                        if (!this.serverTimestamps.contains(next) || obj != null) {
                            obj2 = CustomClassMapper.serialize(obj, errorPath.child(next));
                        } else {
                            obj2 = FieldValue.serverTimestamp();
                        }
                        hashMap.put(next, obj2);
                    }
                }
                return hashMap;
            }
            throw new IllegalArgumentException("Can't serialize object of class " + t.getClass() + " with BeanMapper for class " + this.clazz);
        }

        private void applyFieldAnnotations(Field field) {
            if (field.isAnnotationPresent(ServerTimestamp.class)) {
                Class<?> type = field.getType();
                if (type == Date.class || type == Timestamp.class) {
                    this.serverTimestamps.add(propertyName(field));
                } else {
                    throw new IllegalArgumentException("Field " + field.getName() + " is annotated with @ServerTimestamp but is " + type + " instead of Date or Timestamp.");
                }
            }
            if (field.isAnnotationPresent(DocumentId.class)) {
                ensureValidDocumentIdType("Field", "is", field.getType());
                this.documentIdPropertyNames.add(propertyName(field));
            }
        }

        private void applyGetterAnnotations(Method method) {
            if (method.isAnnotationPresent(ServerTimestamp.class)) {
                Class<?> returnType = method.getReturnType();
                if (returnType == Date.class || returnType == Timestamp.class) {
                    this.serverTimestamps.add(propertyName(method));
                } else {
                    throw new IllegalArgumentException("Method " + method.getName() + " is annotated with @ServerTimestamp but returns " + returnType + " instead of Date or Timestamp.");
                }
            }
            if (method.isAnnotationPresent(DocumentId.class)) {
                ensureValidDocumentIdType("Method", "returns", method.getReturnType());
                this.documentIdPropertyNames.add(propertyName(method));
            }
        }

        private void applySetterAnnotations(Method method) {
            if (method.isAnnotationPresent(ServerTimestamp.class)) {
                throw new IllegalArgumentException("Method " + method.getName() + " is annotated with @ServerTimestamp but should not be. @ServerTimestamp can only be applied to fields and getters, not setters.");
            } else if (method.isAnnotationPresent(DocumentId.class)) {
                ensureValidDocumentIdType("Method", "accepts", method.getParameterTypes()[0]);
                this.documentIdPropertyNames.add(propertyName(method));
            }
        }

        private void ensureValidDocumentIdType(String str, String str2, Type type) {
            if (type != String.class && type != DocumentReference.class) {
                throw new IllegalArgumentException(str + " is annotated with @DocumentId but " + str2 + " " + type + " instead of String or DocumentReference.");
            }
        }

        private static boolean shouldIncludeGetter(Method method) {
            if ((method.getName().startsWith("get") || method.getName().startsWith("is")) && !method.getDeclaringClass().equals(Object.class) && Modifier.isPublic(method.getModifiers()) && !Modifier.isStatic(method.getModifiers()) && !method.getReturnType().equals(Void.TYPE) && method.getParameterTypes().length == 0 && !method.isBridge() && !method.isAnnotationPresent(Exclude.class)) {
                return true;
            }
            return false;
        }

        private static boolean shouldIncludeSetter(Method method) {
            if (method.getName().startsWith("set") && !method.getDeclaringClass().equals(Object.class) && !Modifier.isStatic(method.getModifiers()) && method.getReturnType().equals(Void.TYPE) && method.getParameterTypes().length == 1 && !method.isAnnotationPresent(Exclude.class)) {
                return true;
            }
            return false;
        }

        private static boolean shouldIncludeField(Field field) {
            if (!field.getDeclaringClass().equals(Object.class) && Modifier.isPublic(field.getModifiers()) && !Modifier.isStatic(field.getModifiers()) && !Modifier.isTransient(field.getModifiers()) && !field.isAnnotationPresent(Exclude.class)) {
                return true;
            }
            return false;
        }

        private static boolean isSetterOverride(Method method, Method method2) {
            CustomClassMapper.hardAssert(method.getDeclaringClass().isAssignableFrom(method2.getDeclaringClass()), "Expected override from a base class");
            CustomClassMapper.hardAssert(method.getReturnType().equals(Void.TYPE), "Expected void return type");
            CustomClassMapper.hardAssert(method2.getReturnType().equals(Void.TYPE), "Expected void return type");
            Class[] parameterTypes = method.getParameterTypes();
            Class[] parameterTypes2 = method2.getParameterTypes();
            CustomClassMapper.hardAssert(parameterTypes.length == 1, "Expected exactly one parameter");
            CustomClassMapper.hardAssert(parameterTypes2.length == 1, "Expected exactly one parameter");
            if (!method.getName().equals(method2.getName()) || !parameterTypes[0].equals(parameterTypes2[0])) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: private */
        public static String propertyName(Field field) {
            String annotatedName = annotatedName(field);
            return annotatedName != null ? annotatedName : field.getName();
        }

        private static String propertyName(Method method) {
            String annotatedName = annotatedName(method);
            return annotatedName != null ? annotatedName : serializedName(method.getName());
        }

        private static String annotatedName(AccessibleObject accessibleObject) {
            if (accessibleObject.isAnnotationPresent(PropertyName.class)) {
                return ((PropertyName) accessibleObject.getAnnotation(PropertyName.class)).value();
            }
            return null;
        }

        private static String serializedName(String str) {
            String[] strArr = {"get", "set", "is"};
            String str2 = null;
            int i = 0;
            for (int i2 = 0; i2 < 3; i2++) {
                String str3 = strArr[i2];
                if (str.startsWith(str3)) {
                    str2 = str3;
                }
            }
            if (str2 != null) {
                char[] charArray = str.substring(str2.length()).toCharArray();
                while (i < charArray.length && Character.isUpperCase(charArray[i])) {
                    charArray[i] = Character.toLowerCase(charArray[i]);
                    i++;
                }
                return new String(charArray);
            }
            throw new IllegalArgumentException("Unknown Bean prefix for method: " + str);
        }
    }

    static class ErrorPath {
        static final ErrorPath EMPTY = new ErrorPath((ErrorPath) null, (String) null, 0);
        private final int length;
        private final String name;
        private final ErrorPath parent;

        ErrorPath(ErrorPath errorPath, String str, int i) {
            this.parent = errorPath;
            this.name = str;
            this.length = i;
        }

        /* access modifiers changed from: package-private */
        public int getLength() {
            return this.length;
        }

        /* access modifiers changed from: package-private */
        public ErrorPath child(String str) {
            return new ErrorPath(this, str, this.length + 1);
        }

        public String toString() {
            int i = this.length;
            if (i == 0) {
                return "";
            }
            if (i == 1) {
                return this.name;
            }
            return this.parent.toString() + "." + this.name;
        }
    }

    static class DeserializeContext {
        final DocumentReference documentRef;
        final ErrorPath errorPath;

        DeserializeContext(ErrorPath errorPath2, DocumentReference documentReference) {
            this.errorPath = errorPath2;
            this.documentRef = documentReference;
        }

        /* access modifiers changed from: package-private */
        public DeserializeContext newInstanceWithErrorPath(ErrorPath errorPath2) {
            return new DeserializeContext(errorPath2, this.documentRef);
        }
    }
}

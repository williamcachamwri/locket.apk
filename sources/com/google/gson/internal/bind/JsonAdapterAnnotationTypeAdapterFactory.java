package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.reflect.TypeToken;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class JsonAdapterAnnotationTypeAdapterFactory implements TypeAdapterFactory {
    private static final TypeAdapterFactory TREE_TYPE_CLASS_DUMMY_FACTORY = new DummyTypeAdapterFactory();
    private static final TypeAdapterFactory TREE_TYPE_FIELD_DUMMY_FACTORY = new DummyTypeAdapterFactory();
    private final ConcurrentMap<Class<?>, TypeAdapterFactory> adapterFactoryMap = new ConcurrentHashMap();
    private final ConstructorConstructor constructorConstructor;

    private static class DummyTypeAdapterFactory implements TypeAdapterFactory {
        private DummyTypeAdapterFactory() {
        }

        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            throw new AssertionError("Factory should not be used");
        }
    }

    public JsonAdapterAnnotationTypeAdapterFactory(ConstructorConstructor constructorConstructor2) {
        this.constructorConstructor = constructorConstructor2;
    }

    private static JsonAdapter getAnnotation(Class<?> cls) {
        return (JsonAdapter) cls.getAnnotation(JsonAdapter.class);
    }

    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        JsonAdapter annotation = getAnnotation(typeToken.getRawType());
        if (annotation == null) {
            return null;
        }
        return getTypeAdapter(this.constructorConstructor, gson, typeToken, annotation, true);
    }

    private static Object createAdapter(ConstructorConstructor constructorConstructor2, Class<?> cls) {
        return constructorConstructor2.get(TypeToken.get(cls)).construct();
    }

    private TypeAdapterFactory putFactoryAndGetCurrent(Class<?> cls, TypeAdapterFactory typeAdapterFactory) {
        TypeAdapterFactory putIfAbsent = this.adapterFactoryMap.putIfAbsent(cls, typeAdapterFactory);
        return putIfAbsent != null ? putIfAbsent : typeAdapterFactory;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v18, resolved type: com.google.gson.TypeAdapter<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v20, resolved type: com.google.gson.TypeAdapter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: com.google.gson.internal.bind.TreeTypeAdapter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v21, resolved type: com.google.gson.internal.bind.TreeTypeAdapter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v22, resolved type: com.google.gson.internal.bind.TreeTypeAdapter} */
    /* JADX WARNING: type inference failed for: r8v2, types: [com.google.gson.TypeAdapter<?>, com.google.gson.TypeAdapter] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.gson.TypeAdapter<?> getTypeAdapter(com.google.gson.internal.ConstructorConstructor r8, com.google.gson.Gson r9, com.google.gson.reflect.TypeToken<?> r10, com.google.gson.annotations.JsonAdapter r11, boolean r12) {
        /*
            r7 = this;
            java.lang.Class r0 = r11.value()
            java.lang.Object r8 = createAdapter(r8, r0)
            boolean r6 = r11.nullSafe()
            boolean r11 = r8 instanceof com.google.gson.TypeAdapter
            if (r11 == 0) goto L_0x0014
            com.google.gson.TypeAdapter r8 = (com.google.gson.TypeAdapter) r8
            goto L_0x0086
        L_0x0014:
            boolean r11 = r8 instanceof com.google.gson.TypeAdapterFactory
            if (r11 == 0) goto L_0x0029
            com.google.gson.TypeAdapterFactory r8 = (com.google.gson.TypeAdapterFactory) r8
            if (r12 == 0) goto L_0x0024
            java.lang.Class r11 = r10.getRawType()
            com.google.gson.TypeAdapterFactory r8 = r7.putFactoryAndGetCurrent(r11, r8)
        L_0x0024:
            com.google.gson.TypeAdapter r8 = r8.create(r9, r10)
            goto L_0x0086
        L_0x0029:
            boolean r11 = r8 instanceof com.google.gson.JsonSerializer
            if (r11 != 0) goto L_0x0063
            boolean r0 = r8 instanceof com.google.gson.JsonDeserializer
            if (r0 == 0) goto L_0x0032
            goto L_0x0063
        L_0x0032:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r12 = "Invalid attempt to bind an instance of "
            r11.<init>(r12)
            java.lang.Class r8 = r8.getClass()
            java.lang.String r8 = r8.getName()
            java.lang.StringBuilder r8 = r11.append(r8)
            java.lang.String r11 = " as a @JsonAdapter for "
            java.lang.StringBuilder r8 = r8.append(r11)
            java.lang.String r10 = r10.toString()
            java.lang.StringBuilder r8 = r8.append(r10)
            java.lang.String r10 = ". @JsonAdapter value must be a TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer."
            java.lang.StringBuilder r8 = r8.append(r10)
            java.lang.String r8 = r8.toString()
            r9.<init>(r8)
            throw r9
        L_0x0063:
            r0 = 0
            if (r11 == 0) goto L_0x006b
            r11 = r8
            com.google.gson.JsonSerializer r11 = (com.google.gson.JsonSerializer) r11
            r1 = r11
            goto L_0x006c
        L_0x006b:
            r1 = r0
        L_0x006c:
            boolean r11 = r8 instanceof com.google.gson.JsonDeserializer
            if (r11 == 0) goto L_0x0074
            com.google.gson.JsonDeserializer r8 = (com.google.gson.JsonDeserializer) r8
            r2 = r8
            goto L_0x0075
        L_0x0074:
            r2 = r0
        L_0x0075:
            if (r12 == 0) goto L_0x007a
            com.google.gson.TypeAdapterFactory r8 = TREE_TYPE_CLASS_DUMMY_FACTORY
            goto L_0x007c
        L_0x007a:
            com.google.gson.TypeAdapterFactory r8 = TREE_TYPE_FIELD_DUMMY_FACTORY
        L_0x007c:
            r5 = r8
            com.google.gson.internal.bind.TreeTypeAdapter r8 = new com.google.gson.internal.bind.TreeTypeAdapter
            r0 = r8
            r3 = r9
            r4 = r10
            r0.<init>(r1, r2, r3, r4, r5, r6)
            r6 = 0
        L_0x0086:
            if (r8 == 0) goto L_0x008e
            if (r6 == 0) goto L_0x008e
            com.google.gson.TypeAdapter r8 = r8.nullSafe()
        L_0x008e:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(com.google.gson.internal.ConstructorConstructor, com.google.gson.Gson, com.google.gson.reflect.TypeToken, com.google.gson.annotations.JsonAdapter, boolean):com.google.gson.TypeAdapter");
    }

    public boolean isClassJsonAdapterFactory(TypeToken<?> typeToken, TypeAdapterFactory typeAdapterFactory) {
        Objects.requireNonNull(typeToken);
        Objects.requireNonNull(typeAdapterFactory);
        if (typeAdapterFactory == TREE_TYPE_CLASS_DUMMY_FACTORY) {
            return true;
        }
        Class<? super Object> rawType = typeToken.getRawType();
        TypeAdapterFactory typeAdapterFactory2 = (TypeAdapterFactory) this.adapterFactoryMap.get(rawType);
        if (typeAdapterFactory2 == null) {
            JsonAdapter annotation = getAnnotation(rawType);
            if (annotation == null) {
                return false;
            }
            Class<?> value = annotation.value();
            if (!TypeAdapterFactory.class.isAssignableFrom(value)) {
                return false;
            }
            if (putFactoryAndGetCurrent(rawType, (TypeAdapterFactory) createAdapter(this.constructorConstructor, value)) == typeAdapterFactory) {
                return true;
            }
            return false;
        } else if (typeAdapterFactory2 == typeAdapterFactory) {
            return true;
        } else {
            return false;
        }
    }
}

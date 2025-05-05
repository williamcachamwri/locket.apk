package kotlin.reflect.jvm.internal.calls;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.text.Typography;

@Metadata(d1 = {"\u00004\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\u001aI\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00020\u00062\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0000¢\u0006\u0002\u0010\u000b\u001a$\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00072\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0002\u001a\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0002*\u0004\u0018\u00010\u00022\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0002¨\u0006\u0014²\u0006\u0014\u0010\u0015\u001a\u00020\u000f\"\b\b\u0000\u0010\u0001*\u00020\u0002X\u0002²\u0006\u0014\u0010\u0016\u001a\u00020\u0007\"\b\b\u0000\u0010\u0001*\u00020\u0002X\u0002"}, d2 = {"createAnnotationInstance", "T", "", "annotationClass", "Ljava/lang/Class;", "values", "", "", "methods", "", "Ljava/lang/reflect/Method;", "(Ljava/lang/Class;Ljava/util/Map;Ljava/util/List;)Ljava/lang/Object;", "throwIllegalArgumentType", "", "index", "", "name", "expectedJvmType", "transformKotlinToJvm", "expectedType", "kotlin-reflection", "hashCode", "toString"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: AnnotationConstructorCaller.kt */
public final class AnnotationConstructorCallerKt {
    /* access modifiers changed from: private */
    public static final Object transformKotlinToJvm(Object obj, Class<?> cls) {
        if (obj instanceof Class) {
            return null;
        }
        if (obj instanceof KClass) {
            obj = JvmClassMappingKt.getJavaClass((KClass) obj);
        } else if (obj instanceof Object[]) {
            Object[] objArr = (Object[]) obj;
            if (objArr instanceof Class[]) {
                return null;
            }
            if (objArr instanceof KClass[]) {
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.reflect.KClass<*>>");
                KClass[] kClassArr = (KClass[]) obj;
                Collection arrayList = new ArrayList(kClassArr.length);
                for (KClass javaClass : kClassArr) {
                    arrayList.add(JvmClassMappingKt.getJavaClass(javaClass));
                }
                obj = ((List) arrayList).toArray(new Class[0]);
            } else {
                obj = objArr;
            }
        }
        if (cls.isInstance(obj)) {
            return obj;
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static final Void throwIllegalArgumentType(int i, String str, Class<?> cls) {
        KClass<?> kClass;
        String str2;
        if (Intrinsics.areEqual((Object) cls, (Object) Class.class)) {
            kClass = Reflection.getOrCreateKotlinClass(KClass.class);
        } else if (!cls.isArray() || !Intrinsics.areEqual((Object) cls.getComponentType(), (Object) Class.class)) {
            kClass = JvmClassMappingKt.getKotlinClass(cls);
        } else {
            kClass = Reflection.getOrCreateKotlinClass(KClass[].class);
        }
        if (Intrinsics.areEqual((Object) kClass.getQualifiedName(), (Object) Reflection.getOrCreateKotlinClass(Object[].class).getQualifiedName())) {
            StringBuilder append = new StringBuilder().append(kClass.getQualifiedName()).append(Typography.less);
            Class<?> componentType = JvmClassMappingKt.getJavaClass(kClass).getComponentType();
            Intrinsics.checkNotNullExpressionValue(componentType, "getComponentType(...)");
            str2 = append.append(JvmClassMappingKt.getKotlinClass(componentType).getQualifiedName()).append(Typography.greater).toString();
        } else {
            str2 = kClass.getQualifiedName();
        }
        throw new IllegalArgumentException("Argument #" + i + ' ' + str + " is not of the required type " + str2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: java.util.Collection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: java.util.List} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ java.lang.Object createAnnotationInstance$default(java.lang.Class r1, java.util.Map r2, java.util.List r3, int r4, java.lang.Object r5) {
        /*
            r4 = r4 & 4
            if (r4 == 0) goto L_0x0035
            java.util.Set r3 = r2.keySet()
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            java.util.ArrayList r4 = new java.util.ArrayList
            r5 = 10
            int r5 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r3, r5)
            r4.<init>(r5)
            java.util.Collection r4 = (java.util.Collection) r4
            java.util.Iterator r3 = r3.iterator()
        L_0x001b:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L_0x0032
            java.lang.Object r5 = r3.next()
            java.lang.String r5 = (java.lang.String) r5
            r0 = 0
            java.lang.Class[] r0 = new java.lang.Class[r0]
            java.lang.reflect.Method r5 = r1.getDeclaredMethod(r5, r0)
            r4.add(r5)
            goto L_0x001b
        L_0x0032:
            r3 = r4
            java.util.List r3 = (java.util.List) r3
        L_0x0035:
            java.lang.Object r1 = createAnnotationInstance(r1, r2, r3)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.calls.AnnotationConstructorCallerKt.createAnnotationInstance$default(java.lang.Class, java.util.Map, java.util.List, int, java.lang.Object):java.lang.Object");
    }

    private static final <T> boolean createAnnotationInstance$equals(Class<T> cls, List<Method> list, Map<String, ? extends Object> map, Object obj) {
        boolean z;
        boolean z2;
        KClass annotationClass;
        Class cls2 = null;
        Annotation annotation = obj instanceof Annotation ? (Annotation) obj : null;
        if (!(annotation == null || (annotationClass = JvmClassMappingKt.getAnnotationClass(annotation)) == null)) {
            cls2 = JvmClassMappingKt.getJavaClass(annotationClass);
        }
        if (!Intrinsics.areEqual((Object) cls2, (Object) cls)) {
            return false;
        }
        Iterable iterable = list;
        if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
            Iterator it = iterable.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Method method = (Method) it.next();
                Object obj2 = map.get(method.getName());
                Object invoke = method.invoke(obj, new Object[0]);
                if (obj2 instanceof boolean[]) {
                    Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type kotlin.BooleanArray");
                    z2 = Arrays.equals((boolean[]) obj2, (boolean[]) invoke);
                    continue;
                } else if (obj2 instanceof char[]) {
                    Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type kotlin.CharArray");
                    z2 = Arrays.equals((char[]) obj2, (char[]) invoke);
                    continue;
                } else if (obj2 instanceof byte[]) {
                    Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type kotlin.ByteArray");
                    z2 = Arrays.equals((byte[]) obj2, (byte[]) invoke);
                    continue;
                } else if (obj2 instanceof short[]) {
                    Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type kotlin.ShortArray");
                    z2 = Arrays.equals((short[]) obj2, (short[]) invoke);
                    continue;
                } else if (obj2 instanceof int[]) {
                    Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type kotlin.IntArray");
                    z2 = Arrays.equals((int[]) obj2, (int[]) invoke);
                    continue;
                } else if (obj2 instanceof float[]) {
                    Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type kotlin.FloatArray");
                    z2 = Arrays.equals((float[]) obj2, (float[]) invoke);
                    continue;
                } else if (obj2 instanceof long[]) {
                    Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type kotlin.LongArray");
                    z2 = Arrays.equals((long[]) obj2, (long[]) invoke);
                    continue;
                } else if (obj2 instanceof double[]) {
                    Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type kotlin.DoubleArray");
                    z2 = Arrays.equals((double[]) obj2, (double[]) invoke);
                    continue;
                } else if (obj2 instanceof Object[]) {
                    Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type kotlin.Array<*>");
                    z2 = Arrays.equals((Object[]) obj2, (Object[]) invoke);
                    continue;
                } else {
                    z2 = Intrinsics.areEqual((Object) obj2, invoke);
                    continue;
                }
                if (!z2) {
                    z = false;
                    break;
                }
            }
        }
        z = true;
        if (z) {
            return true;
        }
        return false;
    }

    public static final <T> T createAnnotationInstance(Class<T> cls, Map<String, ? extends Object> map, List<Method> list) {
        Intrinsics.checkNotNullParameter(cls, "annotationClass");
        Intrinsics.checkNotNullParameter(map, "values");
        Intrinsics.checkNotNullParameter(list, "methods");
        Lazy lazy = LazyKt.lazy(new AnnotationConstructorCallerKt$createAnnotationInstance$hashCode$2(map));
        Lazy lazy2 = LazyKt.lazy(new AnnotationConstructorCallerKt$createAnnotationInstance$toString$2(cls, map));
        T newProxyInstance = Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new AnnotationConstructorCallerKt$$Lambda$0(cls, map, lazy2, lazy, list));
        Intrinsics.checkNotNull(newProxyInstance, "null cannot be cast to non-null type T of kotlin.reflect.jvm.internal.calls.AnnotationConstructorCallerKt.createAnnotationInstance");
        return newProxyInstance;
    }

    private static final int createAnnotationInstance$lambda$2(Lazy<Integer> lazy) {
        return lazy.getValue().intValue();
    }

    private static final String createAnnotationInstance$lambda$3(Lazy<String> lazy) {
        return lazy.getValue();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0069, code lost:
        if (r9.length == 1) goto L_0x006d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object createAnnotationInstance$lambda$4(java.lang.Class r2, java.util.Map r3, kotlin.Lazy r4, kotlin.Lazy r5, java.util.List r6, java.lang.Object r7, java.lang.reflect.Method r8, java.lang.Object[] r9) {
        /*
            java.lang.String r7 = "$annotationClass"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r7)
            java.lang.String r7 = "$values"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r7)
            java.lang.String r7 = "$toString$delegate"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r7)
            java.lang.String r7 = "$hashCode$delegate"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r7)
            java.lang.String r7 = "$methods"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r7)
            java.lang.String r7 = r8.getName()
            if (r7 == 0) goto L_0x005c
            int r0 = r7.hashCode()
            r1 = -1776922004(0xffffffff9616526c, float:-1.2142911E-25)
            if (r0 == r1) goto L_0x004e
            r4 = 147696667(0x8cdac1b, float:1.23784505E-33)
            if (r0 == r4) goto L_0x003c
            r4 = 1444986633(0x5620bf09, float:4.4185588E13)
            if (r0 == r4) goto L_0x0033
            goto L_0x005c
        L_0x0033:
            java.lang.String r4 = "annotationType"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x0089
            goto L_0x005c
        L_0x003c:
            java.lang.String r4 = "hashCode"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L_0x0045
            goto L_0x005c
        L_0x0045:
            int r2 = createAnnotationInstance$lambda$2(r5)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            goto L_0x0089
        L_0x004e:
            java.lang.String r5 = "toString"
            boolean r5 = r7.equals(r5)
            if (r5 != 0) goto L_0x0057
            goto L_0x005c
        L_0x0057:
            java.lang.String r2 = createAnnotationInstance$lambda$3(r4)
            goto L_0x0089
        L_0x005c:
            java.lang.String r4 = "equals"
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r4)
            r5 = 0
            if (r4 == 0) goto L_0x007f
            if (r9 == 0) goto L_0x006c
            int r4 = r9.length
            r0 = 1
            if (r4 != r0) goto L_0x006c
            goto L_0x006d
        L_0x006c:
            r0 = r5
        L_0x006d:
            if (r0 == 0) goto L_0x007f
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)
            java.lang.Object r4 = kotlin.collections.ArraysKt.single((T[]) r9)
            boolean r2 = createAnnotationInstance$equals(r2, r6, r3, r4)
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)
            goto L_0x0089
        L_0x007f:
            boolean r2 = r3.containsKey(r7)
            if (r2 == 0) goto L_0x008a
            java.lang.Object r2 = r3.get(r7)
        L_0x0089:
            return r2
        L_0x008a:
            kotlin.reflect.jvm.internal.KotlinReflectionInternalError r2 = new kotlin.reflect.jvm.internal.KotlinReflectionInternalError
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Method is not supported: "
            r3.<init>(r4)
            java.lang.StringBuilder r3 = r3.append(r8)
            java.lang.String r4 = " (args: "
            java.lang.StringBuilder r3 = r3.append(r4)
            if (r9 != 0) goto L_0x00a1
            java.lang.Object[] r9 = new java.lang.Object[r5]
        L_0x00a1:
            java.util.List r4 = kotlin.collections.ArraysKt.toList((T[]) r9)
            java.lang.StringBuilder r3 = r3.append(r4)
            r4 = 41
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.calls.AnnotationConstructorCallerKt.createAnnotationInstance$lambda$4(java.lang.Class, java.util.Map, kotlin.Lazy, kotlin.Lazy, java.util.List, java.lang.Object, java.lang.reflect.Method, java.lang.Object[]):java.lang.Object");
    }
}

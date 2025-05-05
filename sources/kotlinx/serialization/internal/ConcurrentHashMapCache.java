package kotlinx.serialization.internal;

import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B%\u0012\u001e\u0010\u0003\u001a\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00060\u0004¢\u0006\u0002\u0010\u0007J\u001e\u0010\f\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00062\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0005H\u0016R$\u0010\b\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000b0\tX\u0004¢\u0006\u0002\n\u0000R&\u0010\u0003\u001a\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00060\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lkotlinx/serialization/internal/ConcurrentHashMapCache;", "T", "Lkotlinx/serialization/internal/SerializerCache;", "compute", "Lkotlin/Function1;", "Lkotlin/reflect/KClass;", "Lkotlinx/serialization/KSerializer;", "(Lkotlin/jvm/functions/Function1;)V", "cache", "Ljava/util/concurrent/ConcurrentHashMap;", "Ljava/lang/Class;", "Lkotlinx/serialization/internal/CacheEntry;", "get", "key", "", "kotlinx-serialization-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Caching.kt */
final class ConcurrentHashMapCache<T> implements SerializerCache<T> {
    private final ConcurrentHashMap<Class<?>, CacheEntry<T>> cache = new ConcurrentHashMap<>();
    private final Function1<KClass<?>, KSerializer<T>> compute;

    public ConcurrentHashMapCache(Function1<? super KClass<?>, ? extends KSerializer<T>> function1) {
        Intrinsics.checkNotNullParameter(function1, "compute");
        this.compute = function1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0013, code lost:
        r2 = new kotlinx.serialization.internal.CacheEntry(r4.compute.invoke(r5));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public kotlinx.serialization.KSerializer<T> get(kotlin.reflect.KClass<java.lang.Object> r5) {
        /*
            r4 = this;
            java.lang.String r0 = "key"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.util.concurrent.ConcurrentHashMap<java.lang.Class<?>, kotlinx.serialization.internal.CacheEntry<T>> r0 = r4.cache
            java.util.concurrent.ConcurrentMap r0 = (java.util.concurrent.ConcurrentMap) r0
            java.lang.Class r1 = kotlin.jvm.JvmClassMappingKt.getJavaClass(r5)
            java.lang.Object r2 = r0.get(r1)
            if (r2 != 0) goto L_0x0028
            kotlinx.serialization.internal.CacheEntry r2 = new kotlinx.serialization.internal.CacheEntry
            kotlin.jvm.functions.Function1<kotlin.reflect.KClass<?>, kotlinx.serialization.KSerializer<T>> r3 = r4.compute
            java.lang.Object r5 = r3.invoke(r5)
            kotlinx.serialization.KSerializer r5 = (kotlinx.serialization.KSerializer) r5
            r2.<init>(r5)
            java.lang.Object r5 = r0.putIfAbsent(r1, r2)
            if (r5 != 0) goto L_0x0027
            goto L_0x0028
        L_0x0027:
            r2 = r5
        L_0x0028:
            kotlinx.serialization.internal.CacheEntry r2 = (kotlinx.serialization.internal.CacheEntry) r2
            kotlinx.serialization.KSerializer<T> r5 = r2.serializer
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.serialization.internal.ConcurrentHashMapCache.get(kotlin.reflect.KClass):kotlinx.serialization.KSerializer");
    }
}

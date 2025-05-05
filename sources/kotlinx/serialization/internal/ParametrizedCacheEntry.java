package kotlinx.serialization.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KType;
import kotlinx.serialization.KSerializer;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003JN\u0010\n\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\t0\b2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00062\u0014\u0010\r\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\t0\u000eH\bø\u0001\u0001ø\u0001\u0002ø\u0001\u0003ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010R1\u0010\u0004\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0012\u0012\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\t0\b0\u0005X\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000\u0002\u0016\n\u0002\b\u0019\n\u0005\b20\u0001\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006\u0011"}, d2 = {"Lkotlinx/serialization/internal/ParametrizedCacheEntry;", "T", "", "()V", "serializers", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lkotlinx/serialization/internal/KTypeWrapper;", "Lkotlin/Result;", "Lkotlinx/serialization/KSerializer;", "computeIfAbsent", "types", "Lkotlin/reflect/KType;", "producer", "Lkotlin/Function0;", "computeIfAbsent-gIAlu-s", "(Ljava/util/List;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "kotlinx-serialization-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Caching.kt */
final class ParametrizedCacheEntry<T> {
    /* access modifiers changed from: private */
    public final ConcurrentHashMap<List<KTypeWrapper>, Result<KSerializer<T>>> serializers = new ConcurrentHashMap<>();

    /* renamed from: computeIfAbsent-gIAlu-s  reason: not valid java name */
    public final Object m1874computeIfAbsentgIAlus(List<? extends KType> list, Function0<? extends KSerializer<T>> function0) {
        Object obj;
        Intrinsics.checkNotNullParameter(list, "types");
        Intrinsics.checkNotNullParameter(function0, "producer");
        Iterable<KType> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (KType kTypeWrapper : iterable) {
            arrayList.add(new KTypeWrapper(kTypeWrapper));
        }
        List list2 = (List) arrayList;
        ConcurrentMap access$getSerializers$p = this.serializers;
        Object obj2 = access$getSerializers$p.get(list2);
        if (obj2 == null) {
            try {
                Result.Companion companion = Result.Companion;
                obj = Result.m2444constructorimpl((KSerializer) function0.invoke());
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                obj = Result.m2444constructorimpl(ResultKt.createFailure(th));
            }
            Result r5 = Result.m2443boximpl(obj);
            Object putIfAbsent = access$getSerializers$p.putIfAbsent(list2, r5);
            obj2 = putIfAbsent == null ? r5 : putIfAbsent;
        }
        Intrinsics.checkNotNullExpressionValue(obj2, "serializers.getOrPut(wra… { producer() }\n        }");
        return ((Result) obj2).m2453unboximpl();
    }
}

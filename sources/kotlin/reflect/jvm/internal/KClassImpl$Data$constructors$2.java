package kotlin.reflect.jvm.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KFunction;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0002 \u0004*\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0002\u0018\u00010\u00010\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "Lkotlin/reflect/KFunction;", "T", "kotlin.jvm.PlatformType", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: KClassImpl.kt */
final class KClassImpl$Data$constructors$2 extends Lambda implements Function0<List<? extends KFunction<? extends T>>> {
    final /* synthetic */ KClassImpl<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    KClassImpl$Data$constructors$2(KClassImpl<T> kClassImpl) {
        super(0);
        this.this$0 = kClassImpl;
    }

    public final List<KFunction<T>> invoke() {
        Iterable<ConstructorDescriptor> constructorDescriptors = this.this$0.getConstructorDescriptors();
        KClassImpl<T> kClassImpl = this.this$0;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(constructorDescriptors, 10));
        for (ConstructorDescriptor kFunctionImpl : constructorDescriptors) {
            arrayList.add(new KFunctionImpl(kClassImpl, kFunctionImpl));
        }
        return (List) arrayList;
    }
}

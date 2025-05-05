package kotlinx.serialization;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;
import kotlinx.serialization.modules.SerializersModuleBuildersKt;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0002\u0018\u00010\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "Lkotlinx/serialization/KSerializer;", "", "clazz", "Lkotlin/reflect/KClass;", "types", "", "Lkotlin/reflect/KType;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: SerializersCache.kt */
final class SerializersCacheKt$PARAMETRIZED_SERIALIZERS_CACHE$1 extends Lambda implements Function2<KClass<Object>, List<? extends KType>, KSerializer<? extends Object>> {
    public static final SerializersCacheKt$PARAMETRIZED_SERIALIZERS_CACHE$1 INSTANCE = new SerializersCacheKt$PARAMETRIZED_SERIALIZERS_CACHE$1();

    SerializersCacheKt$PARAMETRIZED_SERIALIZERS_CACHE$1() {
        super(2);
    }

    public final KSerializer<? extends Object> invoke(KClass<Object> kClass, final List<? extends KType> list) {
        Intrinsics.checkNotNullParameter(kClass, "clazz");
        Intrinsics.checkNotNullParameter(list, "types");
        List<KSerializer<Object>> serializersForParameters = SerializersKt.serializersForParameters(SerializersModuleBuildersKt.EmptySerializersModule(), list, true);
        Intrinsics.checkNotNull(serializersForParameters);
        return SerializersKt.parametrizedSerializerOrNull(kClass, serializersForParameters, new Function0<KClassifier>() {
            public final KClassifier invoke() {
                return list.get(0).getClassifier();
            }
        });
    }
}

package kotlinx.serialization;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KClassifier;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lkotlin/reflect/KClassifier;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: Serializers.kt */
final class SerializersKt__SerializersKt$serializerByKClassImpl$serializer$1 extends Lambda implements Function0<KClassifier> {
    public static final SerializersKt__SerializersKt$serializerByKClassImpl$serializer$1 INSTANCE = new SerializersKt__SerializersKt$serializerByKClassImpl$serializer$1();

    SerializersKt__SerializersKt$serializerByKClassImpl$serializer$1() {
        super(0);
    }

    public final KClassifier invoke() {
        throw new SerializationException("It is not possible to retrieve an array serializer using KClass alone, use KType instead or ArraySerializer factory");
    }
}

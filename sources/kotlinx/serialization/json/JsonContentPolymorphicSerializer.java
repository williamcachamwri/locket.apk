package kotlinx.serialization.json;

import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.text.Typography;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0002\b\u0002\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0002\u0010\u0006J\u0013\u0010\u000b\u001a\u00028\u00002\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00102\u0006\u0010\u0011\u001a\u00020\u0012H$J\u001b\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00028\u0000¢\u0006\u0002\u0010\u0018J \u0010\u0019\u001a\u00020\u001a2\n\u0010\u001b\u001a\u0006\u0012\u0002\b\u00030\u00052\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u001c"}, d2 = {"Lkotlinx/serialization/json/JsonContentPolymorphicSerializer;", "T", "", "Lkotlinx/serialization/KSerializer;", "baseClass", "Lkotlin/reflect/KClass;", "(Lkotlin/reflect/KClass;)V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "(Lkotlinx/serialization/encoding/Decoder;)Ljava/lang/Object;", "selectDeserializer", "Lkotlinx/serialization/DeserializationStrategy;", "element", "Lkotlinx/serialization/json/JsonElement;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "(Lkotlinx/serialization/encoding/Encoder;Ljava/lang/Object;)V", "throwSubtypeNotRegistered", "", "subClass", "kotlinx-serialization-json"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: JsonContentPolymorphicSerializer.kt */
public abstract class JsonContentPolymorphicSerializer<T> implements KSerializer<T> {
    private final KClass<T> baseClass;
    private final SerialDescriptor descriptor;

    /* access modifiers changed from: protected */
    public abstract DeserializationStrategy<T> selectDeserializer(JsonElement jsonElement);

    public JsonContentPolymorphicSerializer(KClass<T> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "baseClass");
        this.baseClass = kClass;
        this.descriptor = SerialDescriptorsKt.buildSerialDescriptor$default("JsonContentPolymorphicSerializer<" + kClass.getSimpleName() + Typography.greater, PolymorphicKind.SEALED.INSTANCE, new SerialDescriptor[0], (Function1) null, 8, (Object) null);
    }

    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    public final void serialize(Encoder encoder, T t) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(t, "value");
        SerializationStrategy<T> polymorphic = encoder.getSerializersModule().getPolymorphic(this.baseClass, t);
        if (polymorphic == null) {
            KSerializer serializerOrNull = SerializersKt.serializerOrNull(Reflection.getOrCreateKotlinClass(t.getClass()));
            if (serializerOrNull != null) {
                polymorphic = serializerOrNull;
            } else {
                throwSubtypeNotRegistered(Reflection.getOrCreateKotlinClass(t.getClass()), this.baseClass);
                throw new KotlinNothingValueException();
            }
        }
        ((KSerializer) polymorphic).serialize(encoder, t);
    }

    public final T deserialize(Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        JsonDecoder asJsonDecoder = JsonElementSerializersKt.asJsonDecoder(decoder);
        JsonElement decodeJsonElement = asJsonDecoder.decodeJsonElement();
        DeserializationStrategy selectDeserializer = selectDeserializer(decodeJsonElement);
        Intrinsics.checkNotNull(selectDeserializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.json.JsonContentPolymorphicSerializer>");
        return asJsonDecoder.getJson().decodeFromJsonElement((KSerializer) selectDeserializer, decodeJsonElement);
    }

    private final Void throwSubtypeNotRegistered(KClass<?> kClass, KClass<?> kClass2) {
        String simpleName = kClass.getSimpleName();
        if (simpleName == null) {
            simpleName = String.valueOf(kClass);
        }
        throw new SerializationException("Class '" + simpleName + "' is not registered for polymorphic serialization " + ("in the scope of '" + kClass2.getSimpleName() + '\'') + ".\nMark the base class as 'sealed' or register the serializer explicitly.");
    }
}

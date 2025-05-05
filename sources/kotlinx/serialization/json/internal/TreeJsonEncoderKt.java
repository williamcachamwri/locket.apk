package kotlinx.serialization.json.internal;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;

@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a*\u0010\u0007\u001a\u0002H\b\"\n\b\u0000\u0010\b\u0018\u0001*\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0004H\b¢\u0006\u0002\u0010\f\u001a-\u0010\r\u001a\u00020\t\"\u0004\b\u0000\u0010\b*\u00020\u000e2\u0006\u0010\n\u001a\u0002H\b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\b0\u0010H\u0007¢\u0006\u0002\u0010\u0011\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u0018\u0010\u0002\u001a\u00020\u0003*\u00020\u00048BX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, d2 = {"PRIMITIVE_TAG", "", "requiresTopLevelTag", "", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getRequiresTopLevelTag", "(Lkotlinx/serialization/descriptors/SerialDescriptor;)Z", "cast", "T", "Lkotlinx/serialization/json/JsonElement;", "value", "descriptor", "(Lkotlinx/serialization/json/JsonElement;Lkotlinx/serialization/descriptors/SerialDescriptor;)Lkotlinx/serialization/json/JsonElement;", "writeJson", "Lkotlinx/serialization/json/Json;", "serializer", "Lkotlinx/serialization/SerializationStrategy;", "(Lkotlinx/serialization/json/Json;Ljava/lang/Object;Lkotlinx/serialization/SerializationStrategy;)Lkotlinx/serialization/json/JsonElement;", "kotlinx-serialization-json"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: TreeJsonEncoder.kt */
public final class TreeJsonEncoderKt {
    public static final String PRIMITIVE_TAG = "primitive";

    @InternalSerializationApi
    public static final <T> JsonElement writeJson(Json json, T t, SerializationStrategy<? super T> serializationStrategy) {
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(serializationStrategy, "serializer");
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        new JsonTreeEncoder(json, new TreeJsonEncoderKt$writeJson$encoder$1(objectRef)).encodeSerializableValue(serializationStrategy, t);
        if (objectRef.element != null) {
            return (JsonElement) objectRef.element;
        }
        Intrinsics.throwUninitializedPropertyAccessException("result");
        return null;
    }

    /* access modifiers changed from: private */
    public static final boolean getRequiresTopLevelTag(SerialDescriptor serialDescriptor) {
        return (serialDescriptor.getKind() instanceof PrimitiveKind) || serialDescriptor.getKind() == SerialKind.ENUM.INSTANCE;
    }

    public static final /* synthetic */ <T extends JsonElement> T cast(JsonElement jsonElement, SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(jsonElement, "value");
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
        if (jsonElement instanceof JsonElement) {
            return jsonElement;
        }
        StringBuilder sb = new StringBuilder("Expected ");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        throw JsonExceptionsKt.JsonDecodingException(-1, sb.append(Reflection.getOrCreateKotlinClass(JsonElement.class)).append(" as the serialized body of ").append(serialDescriptor.getSerialName()).append(", but had ").append(Reflection.getOrCreateKotlinClass(jsonElement.getClass())).toString());
    }
}

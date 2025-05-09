package kotlinx.serialization.json.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.descriptors.ContextAwareKt;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.descriptors.StructureKind;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.modules.SerializersModule;

@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001aS\u0010\u0004\u001a\u0002H\u0005\"\u0004\b\u0000\u0010\u0005\"\b\b\u0001\u0010\u0006*\u0002H\u0005\"\b\b\u0002\u0010\u0007*\u0002H\u0005*\u00020\b2\u0006\u0010\t\u001a\u00020\u00012\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00060\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00070\u000bH\bø\u0001\u0000¢\u0006\u0002\u0010\r\u001a\u0014\u0010\u000e\u001a\u00020\u000f*\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0001H\u0000\u0002\u0007\n\u0005\b20\u0001¨\u0006\u0011"}, d2 = {"carrierDescriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "module", "Lkotlinx/serialization/modules/SerializersModule;", "selectMapMode", "T", "R1", "R2", "Lkotlinx/serialization/json/Json;", "mapDescriptor", "ifMap", "Lkotlin/Function0;", "ifList", "(Lkotlinx/serialization/json/Json;Lkotlinx/serialization/descriptors/SerialDescriptor;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "switchMode", "Lkotlinx/serialization/json/internal/WriteMode;", "desc", "kotlinx-serialization-json"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: WriteMode.kt */
public final class WriteModeKt {
    public static final WriteMode switchMode(Json json, SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(serialDescriptor, "desc");
        SerialKind kind = serialDescriptor.getKind();
        if (kind instanceof PolymorphicKind) {
            return WriteMode.POLY_OBJ;
        }
        if (Intrinsics.areEqual((Object) kind, (Object) StructureKind.LIST.INSTANCE)) {
            return WriteMode.LIST;
        }
        if (!Intrinsics.areEqual((Object) kind, (Object) StructureKind.MAP.INSTANCE)) {
            return WriteMode.OBJ;
        }
        SerialDescriptor carrierDescriptor = carrierDescriptor(serialDescriptor.getElementDescriptor(0), json.getSerializersModule());
        SerialKind kind2 = carrierDescriptor.getKind();
        if ((kind2 instanceof PrimitiveKind) || Intrinsics.areEqual((Object) kind2, (Object) SerialKind.ENUM.INSTANCE)) {
            return WriteMode.MAP;
        }
        if (json.getConfiguration().getAllowStructuredMapKeys()) {
            return WriteMode.LIST;
        }
        throw JsonExceptionsKt.InvalidKeyKindException(carrierDescriptor);
    }

    public static final <T, R1 extends T, R2 extends T> T selectMapMode(Json json, SerialDescriptor serialDescriptor, Function0<? extends R1> function0, Function0<? extends R2> function02) {
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(serialDescriptor, "mapDescriptor");
        Intrinsics.checkNotNullParameter(function0, "ifMap");
        Intrinsics.checkNotNullParameter(function02, "ifList");
        SerialDescriptor carrierDescriptor = carrierDescriptor(serialDescriptor.getElementDescriptor(0), json.getSerializersModule());
        SerialKind kind = carrierDescriptor.getKind();
        if ((kind instanceof PrimitiveKind) || Intrinsics.areEqual((Object) kind, (Object) SerialKind.ENUM.INSTANCE)) {
            return function0.invoke();
        }
        if (json.getConfiguration().getAllowStructuredMapKeys()) {
            return function02.invoke();
        }
        throw JsonExceptionsKt.InvalidKeyKindException(carrierDescriptor);
    }

    public static final SerialDescriptor carrierDescriptor(SerialDescriptor serialDescriptor, SerializersModule serializersModule) {
        SerialDescriptor carrierDescriptor;
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        Intrinsics.checkNotNullParameter(serializersModule, "module");
        if (!Intrinsics.areEqual((Object) serialDescriptor.getKind(), (Object) SerialKind.CONTEXTUAL.INSTANCE)) {
            return serialDescriptor.isInline() ? carrierDescriptor(serialDescriptor.getElementDescriptor(0), serializersModule) : serialDescriptor;
        }
        SerialDescriptor contextualDescriptor = ContextAwareKt.getContextualDescriptor(serializersModule, serialDescriptor);
        if (contextualDescriptor == null || (carrierDescriptor = carrierDescriptor(contextualDescriptor, serializersModule)) == null) {
            return serialDescriptor;
        }
        return carrierDescriptor;
    }
}

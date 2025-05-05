package kotlinx.serialization.json.internal;

import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.descriptors.StructureKind;
import kotlinx.serialization.modules.SerializersModuleCollector;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\fH\u0002J\u001c\u0010\r\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\fH\u0002JQ\u0010\u000e\u001a\u00020\b\"\b\b\u0000\u0010\u000f*\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u000f0\f2/\u0010\u0012\u001a+\u0012\u001d\u0012\u001b\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u0014¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u0013H\u0016JF\u0010\u0019\u001a\u00020\b\"\b\b\u0000\u0010\u001a*\u00020\u0010\"\b\b\u0001\u0010\u001b*\u0002H\u001a2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u001a0\f2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u001b0\f2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u001b0\u0015H\u0016JM\u0010\u001e\u001a\u00020\b\"\b\b\u0000\u0010\u001a*\u00020\u00102\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u001a0\f2+\u0010\u001f\u001a'\u0012\u0015\u0012\u0013\u0018\u00010\u0005¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b( \u0012\f\u0012\n\u0012\u0004\u0012\u0002H\u001a\u0018\u00010!0\u0013H\u0016JK\u0010\"\u001a\u00020\b\"\b\b\u0000\u0010\u001a*\u00020\u00102\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u001a0\f2)\u0010#\u001a%\u0012\u0013\u0012\u0011H\u001a¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b($\u0012\f\u0012\n\u0012\u0004\u0012\u0002H\u001a\u0018\u00010%0\u0013H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lkotlinx/serialization/json/internal/PolymorphismValidator;", "Lkotlinx/serialization/modules/SerializersModuleCollector;", "useArrayPolymorphism", "", "discriminator", "", "(ZLjava/lang/String;)V", "checkDiscriminatorCollisions", "", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "actualClass", "Lkotlin/reflect/KClass;", "checkKind", "contextual", "T", "", "kClass", "provider", "Lkotlin/Function1;", "", "Lkotlinx/serialization/KSerializer;", "Lkotlin/ParameterName;", "name", "typeArgumentsSerializers", "polymorphic", "Base", "Sub", "baseClass", "actualSerializer", "polymorphicDefaultDeserializer", "defaultDeserializerProvider", "className", "Lkotlinx/serialization/DeserializationStrategy;", "polymorphicDefaultSerializer", "defaultSerializerProvider", "value", "Lkotlinx/serialization/SerializationStrategy;", "kotlinx-serialization-json"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: PolymorphismValidator.kt */
public final class PolymorphismValidator implements SerializersModuleCollector {
    private final String discriminator;
    private final boolean useArrayPolymorphism;

    public <T> void contextual(KClass<T> kClass, Function1<? super List<? extends KSerializer<?>>, ? extends KSerializer<?>> function1) {
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        Intrinsics.checkNotNullParameter(function1, "provider");
    }

    public <Base> void polymorphicDefaultDeserializer(KClass<Base> kClass, Function1<? super String, ? extends DeserializationStrategy<? extends Base>> function1) {
        Intrinsics.checkNotNullParameter(kClass, "baseClass");
        Intrinsics.checkNotNullParameter(function1, "defaultDeserializerProvider");
    }

    public <Base> void polymorphicDefaultSerializer(KClass<Base> kClass, Function1<? super Base, ? extends SerializationStrategy<? super Base>> function1) {
        Intrinsics.checkNotNullParameter(kClass, "baseClass");
        Intrinsics.checkNotNullParameter(function1, "defaultSerializerProvider");
    }

    public PolymorphismValidator(boolean z, String str) {
        Intrinsics.checkNotNullParameter(str, "discriminator");
        this.useArrayPolymorphism = z;
        this.discriminator = str;
    }

    public <T> void contextual(KClass<T> kClass, KSerializer<T> kSerializer) {
        SerializersModuleCollector.DefaultImpls.contextual(this, kClass, kSerializer);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Deprecated in favor of function with more precise name: polymorphicDefaultDeserializer", replaceWith = @ReplaceWith(expression = "polymorphicDefaultDeserializer(baseClass, defaultDeserializerProvider)", imports = {}))
    public <Base> void polymorphicDefault(KClass<Base> kClass, Function1<? super String, ? extends DeserializationStrategy<? extends Base>> function1) {
        SerializersModuleCollector.DefaultImpls.polymorphicDefault(this, kClass, function1);
    }

    public <Base, Sub extends Base> void polymorphic(KClass<Base> kClass, KClass<Sub> kClass2, KSerializer<Sub> kSerializer) {
        Intrinsics.checkNotNullParameter(kClass, "baseClass");
        Intrinsics.checkNotNullParameter(kClass2, "actualClass");
        Intrinsics.checkNotNullParameter(kSerializer, "actualSerializer");
        SerialDescriptor descriptor = kSerializer.getDescriptor();
        checkKind(descriptor, kClass2);
        if (!this.useArrayPolymorphism) {
            checkDiscriminatorCollisions(descriptor, kClass2);
        }
    }

    private final void checkKind(SerialDescriptor serialDescriptor, KClass<?> kClass) {
        SerialKind kind = serialDescriptor.getKind();
        if ((kind instanceof PolymorphicKind) || Intrinsics.areEqual((Object) kind, (Object) SerialKind.CONTEXTUAL.INSTANCE)) {
            throw new IllegalArgumentException("Serializer for " + kClass.getSimpleName() + " can't be registered as a subclass for polymorphic serialization because its kind " + kind + " is not concrete. To work with multiple hierarchies, register it as a base class.");
        } else if (!this.useArrayPolymorphism) {
            if (Intrinsics.areEqual((Object) kind, (Object) StructureKind.LIST.INSTANCE) || Intrinsics.areEqual((Object) kind, (Object) StructureKind.MAP.INSTANCE) || (kind instanceof PrimitiveKind) || (kind instanceof SerialKind.ENUM)) {
                throw new IllegalArgumentException("Serializer for " + kClass.getSimpleName() + " of kind " + kind + " cannot be serialized polymorphically with class discriminator.");
            }
        }
    }

    private final void checkDiscriminatorCollisions(SerialDescriptor serialDescriptor, KClass<?> kClass) {
        int elementsCount = serialDescriptor.getElementsCount();
        int i = 0;
        while (i < elementsCount) {
            String elementName = serialDescriptor.getElementName(i);
            if (!Intrinsics.areEqual((Object) elementName, (Object) this.discriminator)) {
                i++;
            } else {
                throw new IllegalArgumentException("Polymorphic serializer for " + kClass + " has property '" + elementName + "' that conflicts with JSON class discriminator. You can either change class discriminator in JsonConfiguration, rename property with @SerialName annotation or fall back to array polymorphism");
            }
        }
    }
}

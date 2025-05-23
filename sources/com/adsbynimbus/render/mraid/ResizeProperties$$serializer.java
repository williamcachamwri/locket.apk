package com.adsbynimbus.render.mraid;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/adsbynimbus/render/mraid/ResizeProperties.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/adsbynimbus/render/mraid/ResizeProperties;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "static_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* compiled from: Properties.kt */
public final class ResizeProperties$$serializer implements GeneratedSerializer<ResizeProperties> {
    public static final ResizeProperties$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        ResizeProperties$$serializer resizeProperties$$serializer = new ResizeProperties$$serializer();
        INSTANCE = resizeProperties$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.adsbynimbus.render.mraid.ResizeProperties", resizeProperties$$serializer, 5);
        pluginGeneratedSerialDescriptor.addElement("width", false);
        pluginGeneratedSerialDescriptor.addElement("height", false);
        pluginGeneratedSerialDescriptor.addElement("offsetX", false);
        pluginGeneratedSerialDescriptor.addElement("offsetY", false);
        pluginGeneratedSerialDescriptor.addElement("allowOffscreen", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private ResizeProperties$$serializer() {
    }

    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{IntSerializer.INSTANCE, IntSerializer.INSTANCE, IntSerializer.INSTANCE, IntSerializer.INSTANCE, BooleanSerializer.INSTANCE};
    }

    public ResizeProperties deserialize(Decoder decoder) {
        boolean z;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        Decoder decoder2 = decoder;
        Intrinsics.checkNotNullParameter(decoder2, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder beginStructure = decoder2.beginStructure(descriptor2);
        if (beginStructure.decodeSequentially()) {
            int decodeIntElement = beginStructure.decodeIntElement(descriptor2, 0);
            int decodeIntElement2 = beginStructure.decodeIntElement(descriptor2, 1);
            int decodeIntElement3 = beginStructure.decodeIntElement(descriptor2, 2);
            i4 = decodeIntElement;
            i = beginStructure.decodeIntElement(descriptor2, 3);
            z = beginStructure.decodeBooleanElement(descriptor2, 4);
            i2 = decodeIntElement3;
            i3 = decodeIntElement2;
            i5 = 31;
        } else {
            boolean z2 = true;
            int i6 = 0;
            int i7 = 0;
            boolean z3 = false;
            int i8 = 0;
            int i9 = 0;
            int i10 = 0;
            while (z2) {
                int decodeElementIndex = beginStructure.decodeElementIndex(descriptor2);
                if (decodeElementIndex == -1) {
                    z2 = false;
                } else if (decodeElementIndex == 0) {
                    i6 = beginStructure.decodeIntElement(descriptor2, 0);
                    i10 |= 1;
                } else if (decodeElementIndex == 1) {
                    i9 = beginStructure.decodeIntElement(descriptor2, 1);
                    i10 |= 2;
                } else if (decodeElementIndex == 2) {
                    i8 = beginStructure.decodeIntElement(descriptor2, 2);
                    i10 |= 4;
                } else if (decodeElementIndex == 3) {
                    i7 = beginStructure.decodeIntElement(descriptor2, 3);
                    i10 |= 8;
                } else if (decodeElementIndex == 4) {
                    z3 = beginStructure.decodeBooleanElement(descriptor2, 4);
                    i10 |= 16;
                } else {
                    throw new UnknownFieldException(decodeElementIndex);
                }
            }
            i4 = i6;
            i = i7;
            z = z3;
            i2 = i8;
            i3 = i9;
            i5 = i10;
        }
        beginStructure.endStructure(descriptor2);
        return new ResizeProperties(i5, i4, i3, i2, i, z, (SerializationConstructorMarker) null);
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, ResizeProperties resizeProperties) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(resizeProperties, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder beginStructure = encoder.beginStructure(descriptor2);
        ResizeProperties.write$Self$static_release(resizeProperties, beginStructure, descriptor2);
        beginStructure.endStructure(descriptor2);
    }

    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}

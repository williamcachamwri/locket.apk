package com.adsbynimbus.openrtb.request;

import com.adsbynimbus.openrtb.request.Asset;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.ByteArraySerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/adsbynimbus/openrtb/request/Asset.VideoObject.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/adsbynimbus/openrtb/request/Asset$VideoObject;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* compiled from: Asset.kt */
public final class Asset$VideoObject$$serializer implements GeneratedSerializer<Asset.VideoObject> {
    public static final Asset$VideoObject$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        Asset$VideoObject$$serializer asset$VideoObject$$serializer = new Asset$VideoObject$$serializer();
        INSTANCE = asset$VideoObject$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.adsbynimbus.openrtb.request.Asset.VideoObject", asset$VideoObject$$serializer, 4);
        pluginGeneratedSerialDescriptor.addElement("mimes", true);
        pluginGeneratedSerialDescriptor.addElement("minduration", true);
        pluginGeneratedSerialDescriptor.addElement("maxduration", true);
        pluginGeneratedSerialDescriptor.addElement("protocols", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private Asset$VideoObject$$serializer() {
    }

    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{BuiltinSerializersKt.getNullable(Asset.VideoObject.$childSerializers[0]), IntSerializer.INSTANCE, IntSerializer.INSTANCE, BuiltinSerializersKt.getNullable(ByteArraySerializer.INSTANCE)};
    }

    public Asset.VideoObject deserialize(Decoder decoder) {
        byte[] bArr;
        int i;
        int i2;
        String[] strArr;
        int i3;
        Decoder decoder2 = decoder;
        Intrinsics.checkNotNullParameter(decoder2, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeDecoder beginStructure = decoder2.beginStructure(descriptor2);
        KSerializer[] access$get$childSerializers$cp = Asset.VideoObject.$childSerializers;
        if (beginStructure.decodeSequentially()) {
            int decodeIntElement = beginStructure.decodeIntElement(descriptor2, 1);
            int decodeIntElement2 = beginStructure.decodeIntElement(descriptor2, 2);
            strArr = (String[]) beginStructure.decodeNullableSerializableElement(descriptor2, 0, access$get$childSerializers$cp[0], null);
            i2 = decodeIntElement;
            bArr = (byte[]) beginStructure.decodeNullableSerializableElement(descriptor2, 3, ByteArraySerializer.INSTANCE, null);
            i = decodeIntElement2;
            i3 = 15;
        } else {
            boolean z = true;
            int i4 = 0;
            int i5 = 0;
            String[] strArr2 = null;
            byte[] bArr2 = null;
            int i6 = 0;
            while (z) {
                int decodeElementIndex = beginStructure.decodeElementIndex(descriptor2);
                if (decodeElementIndex == -1) {
                    z = false;
                } else if (decodeElementIndex == 0) {
                    strArr2 = (String[]) beginStructure.decodeNullableSerializableElement(descriptor2, 0, access$get$childSerializers$cp[0], strArr2);
                    i5 |= 1;
                } else if (decodeElementIndex == 1) {
                    i4 = beginStructure.decodeIntElement(descriptor2, 1);
                    i5 |= 2;
                } else if (decodeElementIndex == 2) {
                    i6 = beginStructure.decodeIntElement(descriptor2, 2);
                    i5 |= 4;
                } else if (decodeElementIndex == 3) {
                    bArr2 = (byte[]) beginStructure.decodeNullableSerializableElement(descriptor2, 3, ByteArraySerializer.INSTANCE, bArr2);
                    i5 |= 8;
                } else {
                    throw new UnknownFieldException(decodeElementIndex);
                }
            }
            i2 = i4;
            i = i6;
            i3 = i5;
            strArr = strArr2;
            bArr = bArr2;
        }
        beginStructure.endStructure(descriptor2);
        return new Asset.VideoObject(i3, strArr, i2, i, bArr, (SerializationConstructorMarker) null);
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, Asset.VideoObject videoObject) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(videoObject, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder beginStructure = encoder.beginStructure(descriptor2);
        Asset.VideoObject.write$Self$kotlin_release(videoObject, beginStructure, descriptor2);
        beginStructure.endStructure(descriptor2);
    }

    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}

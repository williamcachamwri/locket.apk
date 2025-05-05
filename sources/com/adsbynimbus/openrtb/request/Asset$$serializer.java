package com.adsbynimbus.openrtb.request;

import androidx.media3.common.MimeTypes;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.ByteSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/adsbynimbus/openrtb/request/Asset.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/adsbynimbus/openrtb/request/Asset;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* compiled from: Asset.kt */
public final class Asset$$serializer implements GeneratedSerializer<Asset> {
    public static final Asset$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        Asset$$serializer asset$$serializer = new Asset$$serializer();
        INSTANCE = asset$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.adsbynimbus.openrtb.request.Asset", asset$$serializer, 7);
        pluginGeneratedSerialDescriptor.addElement("id", false);
        pluginGeneratedSerialDescriptor.addElement("required", false);
        pluginGeneratedSerialDescriptor.addElement("ext", true);
        pluginGeneratedSerialDescriptor.addElement("title", true);
        pluginGeneratedSerialDescriptor.addElement("img", true);
        pluginGeneratedSerialDescriptor.addElement(MimeTypes.BASE_TYPE_VIDEO, true);
        pluginGeneratedSerialDescriptor.addElement("data", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private Asset$$serializer() {
    }

    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{IntSerializer.INSTANCE, ByteSerializer.INSTANCE, BuiltinSerializersKt.getNullable(Asset.$childSerializers[2]), BuiltinSerializersKt.getNullable(Asset$TitleObject$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(Asset$ImageObject$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(Asset$VideoObject$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(Asset$DataObject$$serializer.INSTANCE)};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v5, resolved type: java.util.Map} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v5, resolved type: com.adsbynimbus.openrtb.request.Asset$TitleObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v5, resolved type: com.adsbynimbus.openrtb.request.Asset$ImageObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v14, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v7, resolved type: com.adsbynimbus.openrtb.request.Asset$VideoObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v17, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v6, resolved type: com.adsbynimbus.openrtb.request.Asset$DataObject} */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x00df, code lost:
        r4 = 6;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.adsbynimbus.openrtb.request.Asset deserialize(kotlinx.serialization.encoding.Decoder r20) {
        /*
            r19 = this;
            r0 = r20
            java.lang.String r1 = "decoder"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            kotlinx.serialization.descriptors.SerialDescriptor r1 = r19.getDescriptor()
            kotlinx.serialization.encoding.CompositeDecoder r0 = r0.beginStructure(r1)
            kotlinx.serialization.KSerializer[] r2 = com.adsbynimbus.openrtb.request.Asset.$childSerializers
            boolean r3 = r0.decodeSequentially()
            r4 = 6
            r5 = 5
            r6 = 3
            r7 = 4
            r8 = 2
            r9 = 1
            r10 = 0
            r11 = 0
            if (r3 == 0) goto L_0x0069
            int r3 = r0.decodeIntElement(r1, r10)
            byte r9 = r0.decodeByteElement(r1, r9)
            r2 = r2[r8]
            kotlinx.serialization.DeserializationStrategy r2 = (kotlinx.serialization.DeserializationStrategy) r2
            java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r8, r2, r11)
            java.util.Map r2 = (java.util.Map) r2
            com.adsbynimbus.openrtb.request.Asset$TitleObject$$serializer r8 = com.adsbynimbus.openrtb.request.Asset$TitleObject$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r8 = (kotlinx.serialization.DeserializationStrategy) r8
            java.lang.Object r6 = r0.decodeNullableSerializableElement(r1, r6, r8, r11)
            com.adsbynimbus.openrtb.request.Asset$TitleObject r6 = (com.adsbynimbus.openrtb.request.Asset.TitleObject) r6
            com.adsbynimbus.openrtb.request.Asset$ImageObject$$serializer r8 = com.adsbynimbus.openrtb.request.Asset$ImageObject$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r8 = (kotlinx.serialization.DeserializationStrategy) r8
            java.lang.Object r7 = r0.decodeNullableSerializableElement(r1, r7, r8, r11)
            com.adsbynimbus.openrtb.request.Asset$ImageObject r7 = (com.adsbynimbus.openrtb.request.Asset.ImageObject) r7
            com.adsbynimbus.openrtb.request.Asset$VideoObject$$serializer r8 = com.adsbynimbus.openrtb.request.Asset$VideoObject$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r8 = (kotlinx.serialization.DeserializationStrategy) r8
            java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r5, r8, r11)
            com.adsbynimbus.openrtb.request.Asset$VideoObject r5 = (com.adsbynimbus.openrtb.request.Asset.VideoObject) r5
            com.adsbynimbus.openrtb.request.Asset$DataObject$$serializer r8 = com.adsbynimbus.openrtb.request.Asset$DataObject$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r8 = (kotlinx.serialization.DeserializationStrategy) r8
            java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r4, r8, r11)
            com.adsbynimbus.openrtb.request.Asset$DataObject r4 = (com.adsbynimbus.openrtb.request.Asset.DataObject) r4
            r8 = 127(0x7f, float:1.78E-43)
            r13 = r2
            r11 = r3
            r17 = r4
            r16 = r5
            r14 = r6
            r15 = r7
            r10 = r8
            r12 = r9
            goto L_0x00ef
        L_0x0069:
            r17 = r9
            r3 = r10
            r12 = r3
            r9 = r11
            r13 = r9
            r14 = r13
            r15 = r14
            r10 = r15
            r11 = r12
        L_0x0073:
            if (r17 == 0) goto L_0x00e9
            int r8 = r0.decodeElementIndex(r1)
            switch(r8) {
                case -1: goto L_0x00e1;
                case 0: goto L_0x00d4;
                case 1: goto L_0x00cb;
                case 2: goto L_0x00ba;
                case 3: goto L_0x00ac;
                case 4: goto L_0x009e;
                case 5: goto L_0x0090;
                case 6: goto L_0x0082;
                default: goto L_0x007c;
            }
        L_0x007c:
            kotlinx.serialization.UnknownFieldException r0 = new kotlinx.serialization.UnknownFieldException
            r0.<init>((int) r8)
            throw r0
        L_0x0082:
            com.adsbynimbus.openrtb.request.Asset$DataObject$$serializer r8 = com.adsbynimbus.openrtb.request.Asset$DataObject$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r8 = (kotlinx.serialization.DeserializationStrategy) r8
            java.lang.Object r8 = r0.decodeNullableSerializableElement(r1, r4, r8, r9)
            r9 = r8
            com.adsbynimbus.openrtb.request.Asset$DataObject r9 = (com.adsbynimbus.openrtb.request.Asset.DataObject) r9
            r11 = r11 | 64
            goto L_0x00e7
        L_0x0090:
            com.adsbynimbus.openrtb.request.Asset$VideoObject$$serializer r8 = com.adsbynimbus.openrtb.request.Asset$VideoObject$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r8 = (kotlinx.serialization.DeserializationStrategy) r8
            java.lang.Object r8 = r0.decodeNullableSerializableElement(r1, r5, r8, r10)
            r10 = r8
            com.adsbynimbus.openrtb.request.Asset$VideoObject r10 = (com.adsbynimbus.openrtb.request.Asset.VideoObject) r10
            r11 = r11 | 32
            goto L_0x00e7
        L_0x009e:
            com.adsbynimbus.openrtb.request.Asset$ImageObject$$serializer r8 = com.adsbynimbus.openrtb.request.Asset$ImageObject$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r8 = (kotlinx.serialization.DeserializationStrategy) r8
            java.lang.Object r8 = r0.decodeNullableSerializableElement(r1, r7, r8, r15)
            r15 = r8
            com.adsbynimbus.openrtb.request.Asset$ImageObject r15 = (com.adsbynimbus.openrtb.request.Asset.ImageObject) r15
            r11 = r11 | 16
            goto L_0x00e7
        L_0x00ac:
            com.adsbynimbus.openrtb.request.Asset$TitleObject$$serializer r8 = com.adsbynimbus.openrtb.request.Asset$TitleObject$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r8 = (kotlinx.serialization.DeserializationStrategy) r8
            java.lang.Object r8 = r0.decodeNullableSerializableElement(r1, r6, r8, r14)
            r14 = r8
            com.adsbynimbus.openrtb.request.Asset$TitleObject r14 = (com.adsbynimbus.openrtb.request.Asset.TitleObject) r14
            r11 = r11 | 8
            goto L_0x00e7
        L_0x00ba:
            r8 = 2
            r18 = r2[r8]
            r4 = r18
            kotlinx.serialization.DeserializationStrategy r4 = (kotlinx.serialization.DeserializationStrategy) r4
            java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r8, r4, r13)
            r13 = r4
            java.util.Map r13 = (java.util.Map) r13
            r11 = r11 | 4
            goto L_0x00df
        L_0x00cb:
            r4 = 1
            r8 = 2
            byte r12 = r0.decodeByteElement(r1, r4)
            r11 = r11 | 2
            goto L_0x00df
        L_0x00d4:
            r3 = 0
            r4 = 1
            r8 = 2
            int r16 = r0.decodeIntElement(r1, r3)
            r11 = r11 | 1
            r3 = r16
        L_0x00df:
            r4 = 6
            goto L_0x0073
        L_0x00e1:
            r4 = 1
            r16 = 0
            r17 = r16
            r4 = 6
        L_0x00e7:
            r8 = 2
            goto L_0x0073
        L_0x00e9:
            r17 = r9
            r16 = r10
            r10 = r11
            r11 = r3
        L_0x00ef:
            r0.endStructure(r1)
            com.adsbynimbus.openrtb.request.Asset r0 = new com.adsbynimbus.openrtb.request.Asset
            r18 = 0
            r9 = r0
            r9.<init>((int) r10, (int) r11, (byte) r12, (java.util.Map) r13, (com.adsbynimbus.openrtb.request.Asset.TitleObject) r14, (com.adsbynimbus.openrtb.request.Asset.ImageObject) r15, (com.adsbynimbus.openrtb.request.Asset.VideoObject) r16, (com.adsbynimbus.openrtb.request.Asset.DataObject) r17, (kotlinx.serialization.internal.SerializationConstructorMarker) r18)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.openrtb.request.Asset$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.adsbynimbus.openrtb.request.Asset");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, Asset asset) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(asset, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder beginStructure = encoder.beginStructure(descriptor2);
        Asset.write$Self$kotlin_release(asset, beginStructure, descriptor2);
        beginStructure.endStructure(descriptor2);
    }

    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}

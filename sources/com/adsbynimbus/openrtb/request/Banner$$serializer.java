package com.adsbynimbus.openrtb.request;

import androidx.media3.exoplayer.upstream.CmcdData;
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
import kotlinx.serialization.internal.ByteArraySerializer;
import kotlinx.serialization.internal.ByteSerializer;
import kotlinx.serialization.internal.FloatSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/adsbynimbus/openrtb/request/Banner.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/adsbynimbus/openrtb/request/Banner;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* compiled from: Banner.kt */
public final class Banner$$serializer implements GeneratedSerializer<Banner> {
    public static final Banner$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        Banner$$serializer banner$$serializer = new Banner$$serializer();
        INSTANCE = banner$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.adsbynimbus.openrtb.request.Banner", banner$$serializer, 8);
        pluginGeneratedSerialDescriptor.addElement("w", false);
        pluginGeneratedSerialDescriptor.addElement(CmcdData.Factory.STREAMING_FORMAT_HLS, false);
        pluginGeneratedSerialDescriptor.addElement("format", true);
        pluginGeneratedSerialDescriptor.addElement("bidfloor", true);
        pluginGeneratedSerialDescriptor.addElement("battr", true);
        pluginGeneratedSerialDescriptor.addElement("pos", true);
        pluginGeneratedSerialDescriptor.addElement("api", true);
        pluginGeneratedSerialDescriptor.addElement("vcm", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private Banner$$serializer() {
    }

    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{IntSerializer.INSTANCE, IntSerializer.INSTANCE, BuiltinSerializersKt.getNullable(Banner.$childSerializers[2]), FloatSerializer.INSTANCE, BuiltinSerializersKt.getNullable(ByteArraySerializer.INSTANCE), ByteSerializer.INSTANCE, BuiltinSerializersKt.getNullable(ByteArraySerializer.INSTANCE), BuiltinSerializersKt.getNullable(ByteSerializer.INSTANCE)};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v9, resolved type: com.adsbynimbus.openrtb.request.Format[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v7, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v14, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v6, resolved type: byte[]} */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x00be, code lost:
        r8 = r4;
        r4 = 7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x00e9, code lost:
        r4 = 7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00f1, code lost:
        r5 = 6;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.adsbynimbus.openrtb.request.Banner deserialize(kotlinx.serialization.encoding.Decoder r21) {
        /*
            r20 = this;
            r0 = r21
            java.lang.String r1 = "decoder"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            kotlinx.serialization.descriptors.SerialDescriptor r1 = r20.getDescriptor()
            kotlinx.serialization.encoding.CompositeDecoder r0 = r0.beginStructure(r1)
            kotlinx.serialization.KSerializer[] r2 = com.adsbynimbus.openrtb.request.Banner.$childSerializers
            boolean r3 = r0.decodeSequentially()
            r4 = 7
            r5 = 6
            r6 = 5
            r7 = 3
            r8 = 4
            r9 = 2
            r10 = 1
            r11 = 0
            r12 = 0
            if (r3 == 0) goto L_0x006a
            int r3 = r0.decodeIntElement(r1, r11)
            int r10 = r0.decodeIntElement(r1, r10)
            r2 = r2[r9]
            kotlinx.serialization.DeserializationStrategy r2 = (kotlinx.serialization.DeserializationStrategy) r2
            java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r9, r2, r12)
            com.adsbynimbus.openrtb.request.Format[] r2 = (com.adsbynimbus.openrtb.request.Format[]) r2
            float r7 = r0.decodeFloatElement(r1, r7)
            kotlinx.serialization.internal.ByteArraySerializer r9 = kotlinx.serialization.internal.ByteArraySerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r9 = (kotlinx.serialization.DeserializationStrategy) r9
            java.lang.Object r8 = r0.decodeNullableSerializableElement(r1, r8, r9, r12)
            byte[] r8 = (byte[]) r8
            byte r6 = r0.decodeByteElement(r1, r6)
            kotlinx.serialization.internal.ByteArraySerializer r9 = kotlinx.serialization.internal.ByteArraySerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r9 = (kotlinx.serialization.DeserializationStrategy) r9
            java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r5, r9, r12)
            byte[] r5 = (byte[]) r5
            kotlinx.serialization.internal.ByteSerializer r9 = kotlinx.serialization.internal.ByteSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r9 = (kotlinx.serialization.DeserializationStrategy) r9
            java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r4, r9, r12)
            java.lang.Byte r4 = (java.lang.Byte) r4
            r9 = 255(0xff, float:3.57E-43)
            r13 = r2
            r11 = r3
            r18 = r4
            r17 = r5
            r16 = r6
            r14 = r7
            r15 = r8
            r12 = r10
            r10 = r9
            goto L_0x0100
        L_0x006a:
            r3 = 0
            r13 = r3
            r19 = r10
            r3 = r11
            r14 = r3
            r15 = r14
            r7 = r12
            r9 = r7
            r10 = r9
            r11 = r10
            r12 = r15
        L_0x0076:
            if (r19 == 0) goto L_0x00f4
            int r8 = r0.decodeElementIndex(r1)
            switch(r8) {
                case -1: goto L_0x00eb;
                case 0: goto L_0x00dd;
                case 1: goto L_0x00d3;
                case 2: goto L_0x00c1;
                case 3: goto L_0x00b6;
                case 4: goto L_0x00a7;
                case 5: goto L_0x00a0;
                case 6: goto L_0x0092;
                case 7: goto L_0x0085;
                default: goto L_0x007f;
            }
        L_0x007f:
            kotlinx.serialization.UnknownFieldException r0 = new kotlinx.serialization.UnknownFieldException
            r0.<init>((int) r8)
            throw r0
        L_0x0085:
            kotlinx.serialization.internal.ByteSerializer r8 = kotlinx.serialization.internal.ByteSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r8 = (kotlinx.serialization.DeserializationStrategy) r8
            java.lang.Object r7 = r0.decodeNullableSerializableElement(r1, r4, r8, r7)
            java.lang.Byte r7 = (java.lang.Byte) r7
            r14 = r14 | 128(0x80, float:1.794E-43)
            goto L_0x00f2
        L_0x0092:
            kotlinx.serialization.internal.ByteArraySerializer r8 = kotlinx.serialization.internal.ByteArraySerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r8 = (kotlinx.serialization.DeserializationStrategy) r8
            java.lang.Object r8 = r0.decodeNullableSerializableElement(r1, r5, r8, r9)
            r9 = r8
            byte[] r9 = (byte[]) r9
            r14 = r14 | 64
            goto L_0x00f2
        L_0x00a0:
            byte r12 = r0.decodeByteElement(r1, r6)
            r14 = r14 | 32
            goto L_0x00f2
        L_0x00a7:
            kotlinx.serialization.internal.ByteArraySerializer r8 = kotlinx.serialization.internal.ByteArraySerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r8 = (kotlinx.serialization.DeserializationStrategy) r8
            r4 = 4
            java.lang.Object r8 = r0.decodeNullableSerializableElement(r1, r4, r8, r10)
            r10 = r8
            byte[] r10 = (byte[]) r10
            r14 = r14 | 16
            goto L_0x00be
        L_0x00b6:
            r4 = 4
            r8 = 3
            float r13 = r0.decodeFloatElement(r1, r8)
            r14 = r14 | 8
        L_0x00be:
            r8 = r4
            r4 = 7
            goto L_0x0076
        L_0x00c1:
            r4 = 2
            r8 = 3
            r17 = r2[r4]
            r5 = r17
            kotlinx.serialization.DeserializationStrategy r5 = (kotlinx.serialization.DeserializationStrategy) r5
            java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r4, r5, r11)
            r11 = r5
            com.adsbynimbus.openrtb.request.Format[] r11 = (com.adsbynimbus.openrtb.request.Format[]) r11
            r14 = r14 | 4
            goto L_0x00e9
        L_0x00d3:
            r4 = 2
            r5 = 1
            r8 = 3
            int r15 = r0.decodeIntElement(r1, r5)
            r14 = r14 | 2
            goto L_0x00e9
        L_0x00dd:
            r3 = 0
            r4 = 2
            r5 = 1
            r8 = 3
            int r16 = r0.decodeIntElement(r1, r3)
            r14 = r14 | 1
            r3 = r16
        L_0x00e9:
            r4 = 7
            goto L_0x00f1
        L_0x00eb:
            r5 = 1
            r8 = 3
            r16 = 0
            r19 = r16
        L_0x00f1:
            r5 = 6
        L_0x00f2:
            r8 = 4
            goto L_0x0076
        L_0x00f4:
            r18 = r7
            r17 = r9
            r16 = r12
            r12 = r15
            r15 = r10
            r10 = r14
            r14 = r13
            r13 = r11
            r11 = r3
        L_0x0100:
            r0.endStructure(r1)
            com.adsbynimbus.openrtb.request.Banner r0 = new com.adsbynimbus.openrtb.request.Banner
            r19 = 0
            r9 = r0
            r9.<init>((int) r10, (int) r11, (int) r12, (com.adsbynimbus.openrtb.request.Format[]) r13, (float) r14, (byte[]) r15, (byte) r16, (byte[]) r17, (java.lang.Byte) r18, (kotlinx.serialization.internal.SerializationConstructorMarker) r19)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.openrtb.request.Banner$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.adsbynimbus.openrtb.request.Banner");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, Banner banner) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(banner, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder beginStructure = encoder.beginStructure(descriptor2);
        Banner.write$Self$kotlin_release(banner, beginStructure, descriptor2);
        beginStructure.endStructure(descriptor2);
    }

    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}

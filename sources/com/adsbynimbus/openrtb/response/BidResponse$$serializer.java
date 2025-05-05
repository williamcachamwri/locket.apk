package com.adsbynimbus.openrtb.response;

import com.facebook.react.uimanager.ViewProps;
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
import kotlinx.serialization.internal.FloatSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/adsbynimbus/openrtb/response/BidResponse.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/adsbynimbus/openrtb/response/BidResponse;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* compiled from: BidResponse.kt */
public final class BidResponse$$serializer implements GeneratedSerializer<BidResponse> {
    public static final BidResponse$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        BidResponse$$serializer bidResponse$$serializer = new BidResponse$$serializer();
        INSTANCE = bidResponse$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.adsbynimbus.openrtb.response.BidResponse", bidResponse$$serializer, 20);
        pluginGeneratedSerialDescriptor.addElement("type", false);
        pluginGeneratedSerialDescriptor.addElement("auction_id", false);
        pluginGeneratedSerialDescriptor.addElement("adomain", true);
        pluginGeneratedSerialDescriptor.addElement("bid_in_cents", true);
        pluginGeneratedSerialDescriptor.addElement("bid_raw", true);
        pluginGeneratedSerialDescriptor.addElement("content_type", true);
        pluginGeneratedSerialDescriptor.addElement("crid", true);
        pluginGeneratedSerialDescriptor.addElement("height", true);
        pluginGeneratedSerialDescriptor.addElement("width", true);
        pluginGeneratedSerialDescriptor.addElement("is_interstitial", true);
        pluginGeneratedSerialDescriptor.addElement("markup", false);
        pluginGeneratedSerialDescriptor.addElement("network", true);
        pluginGeneratedSerialDescriptor.addElement("placement_id", true);
        pluginGeneratedSerialDescriptor.addElement("is_mraid", true);
        pluginGeneratedSerialDescriptor.addElement(ViewProps.POSITION, false);
        pluginGeneratedSerialDescriptor.addElement("trackers", true);
        pluginGeneratedSerialDescriptor.addElement("duration", true);
        pluginGeneratedSerialDescriptor.addElement("exp", true);
        pluginGeneratedSerialDescriptor.addElement("external_notifications", true);
        pluginGeneratedSerialDescriptor.addElement("ext", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private BidResponse$$serializer() {
    }

    public KSerializer<?>[] childSerializers() {
        KSerializer<?>[] access$get$childSerializers$cp = BidResponse.$childSerializers;
        return new KSerializer[]{StringSerializer.INSTANCE, StringSerializer.INSTANCE, BuiltinSerializersKt.getNullable(access$get$childSerializers$cp[2]), IntSerializer.INSTANCE, FloatSerializer.INSTANCE, BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), IntSerializer.INSTANCE, IntSerializer.INSTANCE, ByteSerializer.INSTANCE, StringSerializer.INSTANCE, StringSerializer.INSTANCE, BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), ByteSerializer.INSTANCE, StringSerializer.INSTANCE, access$get$childSerializers$cp[15], IntSerializer.INSTANCE, IntSerializer.INSTANCE, access$get$childSerializers$cp[18], BidResponse$Extension$$serializer.INSTANCE};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v9, resolved type: java.lang.String[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v18, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v8, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v33, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v8, resolved type: java.util.Map} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v29, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v8, resolved type: java.util.Map} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v40, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v7, resolved type: com.adsbynimbus.openrtb.response.BidResponse$Extension} */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x026a, code lost:
        r6 = 19;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.adsbynimbus.openrtb.response.BidResponse deserialize(kotlinx.serialization.encoding.Decoder r40) {
        /*
            r39 = this;
            r0 = r40
            java.lang.String r1 = "decoder"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            kotlinx.serialization.descriptors.SerialDescriptor r1 = r39.getDescriptor()
            kotlinx.serialization.encoding.CompositeDecoder r0 = r0.beginStructure(r1)
            kotlinx.serialization.KSerializer[] r2 = com.adsbynimbus.openrtb.response.BidResponse.$childSerializers
            boolean r3 = r0.decodeSequentially()
            r10 = 10
            r11 = 9
            r12 = 7
            r13 = 6
            r14 = 5
            r15 = 3
            r5 = 8
            r4 = 4
            r6 = 2
            r7 = 1
            r8 = 0
            r9 = 0
            if (r3 == 0) goto L_0x00f6
            java.lang.String r3 = r0.decodeStringElement(r1, r8)
            java.lang.String r7 = r0.decodeStringElement(r1, r7)
            r8 = r2[r6]
            kotlinx.serialization.DeserializationStrategy r8 = (kotlinx.serialization.DeserializationStrategy) r8
            java.lang.Object r6 = r0.decodeNullableSerializableElement(r1, r6, r8, r9)
            java.lang.String[] r6 = (java.lang.String[]) r6
            int r8 = r0.decodeIntElement(r1, r15)
            float r4 = r0.decodeFloatElement(r1, r4)
            kotlinx.serialization.internal.StringSerializer r15 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r15 = (kotlinx.serialization.DeserializationStrategy) r15
            java.lang.Object r14 = r0.decodeNullableSerializableElement(r1, r14, r15, r9)
            java.lang.String r14 = (java.lang.String) r14
            kotlinx.serialization.internal.StringSerializer r15 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r15 = (kotlinx.serialization.DeserializationStrategy) r15
            java.lang.Object r13 = r0.decodeNullableSerializableElement(r1, r13, r15, r9)
            java.lang.String r13 = (java.lang.String) r13
            int r12 = r0.decodeIntElement(r1, r12)
            int r5 = r0.decodeIntElement(r1, r5)
            byte r11 = r0.decodeByteElement(r1, r11)
            java.lang.String r10 = r0.decodeStringElement(r1, r10)
            r15 = 11
            java.lang.String r15 = r0.decodeStringElement(r1, r15)
            kotlinx.serialization.internal.StringSerializer r23 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            r24 = r3
            r3 = r23
            kotlinx.serialization.DeserializationStrategy r3 = (kotlinx.serialization.DeserializationStrategy) r3
            r23 = r4
            r4 = 12
            java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r4, r3, r9)
            java.lang.String r3 = (java.lang.String) r3
            r4 = 13
            byte r4 = r0.decodeByteElement(r1, r4)
            r9 = 14
            java.lang.String r9 = r0.decodeStringElement(r1, r9)
            r18 = r3
            r3 = 15
            r20 = r2[r3]
            r21 = r4
            r4 = r20
            kotlinx.serialization.DeserializationStrategy r4 = (kotlinx.serialization.DeserializationStrategy) r4
            r20 = r8
            r8 = 0
            java.lang.Object r3 = r0.decodeSerializableElement(r1, r3, r4, r8)
            java.util.Map r3 = (java.util.Map) r3
            r4 = 16
            int r4 = r0.decodeIntElement(r1, r4)
            r8 = 17
            int r8 = r0.decodeIntElement(r1, r8)
            r16 = r3
            r3 = 18
            r2 = r2[r3]
            kotlinx.serialization.DeserializationStrategy r2 = (kotlinx.serialization.DeserializationStrategy) r2
            r17 = r7
            r7 = 0
            java.lang.Object r2 = r0.decodeSerializableElement(r1, r3, r2, r7)
            java.util.Map r2 = (java.util.Map) r2
            com.adsbynimbus.openrtb.response.BidResponse$Extension$$serializer r3 = com.adsbynimbus.openrtb.response.BidResponse$Extension$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r3 = (kotlinx.serialization.DeserializationStrategy) r3
            r19 = r6
            r6 = 19
            java.lang.Object r3 = r0.decodeSerializableElement(r1, r6, r3, r7)
            com.adsbynimbus.openrtb.response.BidResponse$Extension r3 = (com.adsbynimbus.openrtb.response.BidResponse.Extension) r3
            r6 = 1048575(0xfffff, float:1.469367E-39)
            r26 = r2
            r27 = r3
            r7 = r6
            r25 = r8
            r22 = r9
            r9 = r17
            r8 = r24
            r24 = r4
            r17 = r11
            r11 = r20
            r20 = r18
            r18 = r10
            r10 = r19
            r19 = r15
            r15 = r12
            r12 = r23
            r23 = r16
            r16 = r5
            r38 = r14
            r14 = r13
            r13 = r38
            goto L_0x029d
        L_0x00f6:
            r3 = r6
            r6 = 19
            r38 = r8
            r8 = r7
            r7 = r9
            r9 = r38
            r24 = 0
            r3 = r7
            r4 = r3
            r12 = r4
            r13 = r12
            r14 = r13
            r15 = r14
            r29 = r15
            r30 = r29
            r32 = r30
            r33 = r32
            r34 = r33
            r37 = r8
            r25 = r9
            r26 = r25
            r27 = r26
            r28 = r27
            r35 = r28
            r36 = r35
            r8 = r34
            r7 = r36
        L_0x0123:
            if (r37 == 0) goto L_0x0277
            int r5 = r0.decodeElementIndex(r1)
            switch(r5) {
                case -1: goto L_0x026e;
                case 0: goto L_0x025e;
                case 1: goto L_0x0253;
                case 2: goto L_0x023f;
                case 3: goto L_0x0234;
                case 4: goto L_0x0229;
                case 5: goto L_0x0218;
                case 6: goto L_0x0202;
                case 7: goto L_0x01f6;
                case 8: goto L_0x01e7;
                case 9: goto L_0x01d9;
                case 10: goto L_0x01cb;
                case 11: goto L_0x01bd;
                case 12: goto L_0x01a5;
                case 13: goto L_0x0195;
                case 14: goto L_0x0189;
                case 15: goto L_0x0172;
                case 16: goto L_0x0164;
                case 17: goto L_0x0156;
                case 18: goto L_0x0142;
                case 19: goto L_0x0132;
                default: goto L_0x012c;
            }
        L_0x012c:
            kotlinx.serialization.UnknownFieldException r0 = new kotlinx.serialization.UnknownFieldException
            r0.<init>((int) r5)
            throw r0
        L_0x0132:
            com.adsbynimbus.openrtb.response.BidResponse$Extension$$serializer r5 = com.adsbynimbus.openrtb.response.BidResponse$Extension$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r5 = (kotlinx.serialization.DeserializationStrategy) r5
            java.lang.Object r5 = r0.decodeSerializableElement(r1, r6, r5, r12)
            r12 = r5
            com.adsbynimbus.openrtb.response.BidResponse$Extension r12 = (com.adsbynimbus.openrtb.response.BidResponse.Extension) r12
            r5 = 524288(0x80000, float:7.34684E-40)
            r9 = r9 | r5
            goto L_0x0273
        L_0x0142:
            r5 = 18
            r19 = r2[r5]
            r6 = r19
            kotlinx.serialization.DeserializationStrategy r6 = (kotlinx.serialization.DeserializationStrategy) r6
            java.lang.Object r6 = r0.decodeSerializableElement(r1, r5, r6, r13)
            r13 = r6
            java.util.Map r13 = (java.util.Map) r13
            r6 = 262144(0x40000, float:3.67342E-40)
            r9 = r9 | r6
            goto L_0x0268
        L_0x0156:
            r5 = 18
            r6 = 17
            int r28 = r0.decodeIntElement(r1, r6)
            r16 = 131072(0x20000, float:1.83671E-40)
            r9 = r9 | r16
            goto L_0x0268
        L_0x0164:
            r5 = 18
            r6 = 16
            int r25 = r0.decodeIntElement(r1, r6)
            r17 = 65536(0x10000, float:9.18355E-41)
            r9 = r9 | r17
            goto L_0x0268
        L_0x0172:
            r5 = 18
            r6 = 15
            r19 = r2[r6]
            r5 = r19
            kotlinx.serialization.DeserializationStrategy r5 = (kotlinx.serialization.DeserializationStrategy) r5
            java.lang.Object r5 = r0.decodeSerializableElement(r1, r6, r5, r14)
            r14 = r5
            java.util.Map r14 = (java.util.Map) r14
            r5 = 32768(0x8000, float:4.5918E-41)
            r9 = r9 | r5
            goto L_0x0268
        L_0x0189:
            r5 = 14
            r6 = 15
            java.lang.String r34 = r0.decodeStringElement(r1, r5)
            r9 = r9 | 16384(0x4000, float:2.2959E-41)
            goto L_0x0268
        L_0x0195:
            r5 = 14
            r6 = 15
            r7 = 13
            byte r18 = r0.decodeByteElement(r1, r7)
            r9 = r9 | 8192(0x2000, float:1.14794E-41)
            r7 = r18
            goto L_0x0268
        L_0x01a5:
            r5 = 14
            r6 = 15
            r18 = 13
            kotlinx.serialization.internal.StringSerializer r19 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            r5 = r19
            kotlinx.serialization.DeserializationStrategy r5 = (kotlinx.serialization.DeserializationStrategy) r5
            r6 = 12
            java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r6, r5, r4)
            java.lang.String r4 = (java.lang.String) r4
            r9 = r9 | 4096(0x1000, float:5.74E-42)
            goto L_0x0268
        L_0x01bd:
            r5 = 11
            r6 = 12
            r18 = 13
            java.lang.String r33 = r0.decodeStringElement(r1, r5)
            r9 = r9 | 2048(0x800, float:2.87E-42)
            goto L_0x0268
        L_0x01cb:
            r5 = 11
            r6 = 12
            r18 = 13
            java.lang.String r32 = r0.decodeStringElement(r1, r10)
            r9 = r9 | 1024(0x400, float:1.435E-42)
            goto L_0x0268
        L_0x01d9:
            r5 = 11
            r6 = 12
            r18 = 13
            byte r35 = r0.decodeByteElement(r1, r11)
            r9 = r9 | 512(0x200, float:7.175E-43)
            goto L_0x0268
        L_0x01e7:
            r5 = 11
            r6 = 8
            r18 = 13
            int r26 = r0.decodeIntElement(r1, r6)
            r9 = r9 | 256(0x100, float:3.59E-43)
            r5 = r6
            goto L_0x026a
        L_0x01f6:
            r5 = 11
            r6 = 7
            r18 = 13
            int r36 = r0.decodeIntElement(r1, r6)
            r9 = r9 | 128(0x80, float:1.794E-43)
            goto L_0x0268
        L_0x0202:
            r5 = 11
            r6 = 7
            r18 = 13
            kotlinx.serialization.internal.StringSerializer r23 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            r5 = r23
            kotlinx.serialization.DeserializationStrategy r5 = (kotlinx.serialization.DeserializationStrategy) r5
            r6 = 6
            java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r6, r5, r15)
            r15 = r5
            java.lang.String r15 = (java.lang.String) r15
            r9 = r9 | 64
            goto L_0x0268
        L_0x0218:
            r6 = 6
            r18 = 13
            kotlinx.serialization.internal.StringSerializer r5 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r5 = (kotlinx.serialization.DeserializationStrategy) r5
            r6 = 5
            java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r6, r5, r3)
            java.lang.String r3 = (java.lang.String) r3
            r9 = r9 | 32
            goto L_0x0268
        L_0x0229:
            r5 = 4
            r6 = 5
            r18 = 13
            float r24 = r0.decodeFloatElement(r1, r5)
            r9 = r9 | 16
            goto L_0x0268
        L_0x0234:
            r5 = 4
            r6 = 3
            r18 = 13
            int r27 = r0.decodeIntElement(r1, r6)
            r9 = r9 | 8
            goto L_0x0268
        L_0x023f:
            r5 = 4
            r6 = 2
            r18 = 13
            r31 = r2[r6]
            r5 = r31
            kotlinx.serialization.DeserializationStrategy r5 = (kotlinx.serialization.DeserializationStrategy) r5
            java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r6, r5, r8)
            r8 = r5
            java.lang.String[] r8 = (java.lang.String[]) r8
            r9 = r9 | 4
            goto L_0x0268
        L_0x0253:
            r5 = 1
            r6 = 2
            r18 = 13
            java.lang.String r30 = r0.decodeStringElement(r1, r5)
            r9 = r9 | 2
            goto L_0x0268
        L_0x025e:
            r5 = 0
            r6 = 2
            r18 = 13
            java.lang.String r29 = r0.decodeStringElement(r1, r5)
            r9 = r9 | 1
        L_0x0268:
            r5 = 8
        L_0x026a:
            r6 = 19
            goto L_0x0123
        L_0x026e:
            r5 = 0
            r18 = 13
            r37 = r5
        L_0x0273:
            r5 = 8
            goto L_0x0123
        L_0x0277:
            r20 = r4
            r21 = r7
            r10 = r8
            r7 = r9
            r23 = r14
            r14 = r15
            r16 = r26
            r11 = r27
            r8 = r29
            r9 = r30
            r18 = r32
            r19 = r33
            r22 = r34
            r17 = r35
            r15 = r36
            r27 = r12
            r26 = r13
            r12 = r24
            r24 = r25
            r25 = r28
            r13 = r3
        L_0x029d:
            r0.endStructure(r1)
            com.adsbynimbus.openrtb.response.BidResponse r0 = new com.adsbynimbus.openrtb.response.BidResponse
            r6 = r0
            r28 = 0
            r6.<init>((int) r7, (java.lang.String) r8, (java.lang.String) r9, (java.lang.String[]) r10, (int) r11, (float) r12, (java.lang.String) r13, (java.lang.String) r14, (int) r15, (int) r16, (byte) r17, (java.lang.String) r18, (java.lang.String) r19, (java.lang.String) r20, (byte) r21, (java.lang.String) r22, (java.util.Map) r23, (int) r24, (int) r25, (java.util.Map) r26, (com.adsbynimbus.openrtb.response.BidResponse.Extension) r27, (kotlinx.serialization.internal.SerializationConstructorMarker) r28)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.openrtb.response.BidResponse$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.adsbynimbus.openrtb.response.BidResponse");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, BidResponse bidResponse) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(bidResponse, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder beginStructure = encoder.beginStructure(descriptor2);
        BidResponse.write$Self$kotlin_release(bidResponse, beginStructure, descriptor2);
        beginStructure.endStructure(descriptor2);
    }

    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}

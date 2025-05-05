package com.adsbynimbus.openrtb.request;

import io.sentry.protocol.App;
import io.sentry.protocol.Device;
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

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/adsbynimbus/openrtb/request/BidRequest.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/adsbynimbus/openrtb/request/BidRequest;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* compiled from: BidRequest.kt */
public final class BidRequest$$serializer implements GeneratedSerializer<BidRequest> {
    public static final BidRequest$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        BidRequest$$serializer bidRequest$$serializer = new BidRequest$$serializer();
        INSTANCE = bidRequest$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.adsbynimbus.openrtb.request.BidRequest", bidRequest$$serializer, 11);
        pluginGeneratedSerialDescriptor.addElement("imp", true);
        pluginGeneratedSerialDescriptor.addElement(App.TYPE, true);
        pluginGeneratedSerialDescriptor.addElement(Device.TYPE, true);
        pluginGeneratedSerialDescriptor.addElement("format", true);
        pluginGeneratedSerialDescriptor.addElement("user", true);
        pluginGeneratedSerialDescriptor.addElement("test", true);
        pluginGeneratedSerialDescriptor.addElement("tmax", true);
        pluginGeneratedSerialDescriptor.addElement("badv", true);
        pluginGeneratedSerialDescriptor.addElement("source", true);
        pluginGeneratedSerialDescriptor.addElement("regs", true);
        pluginGeneratedSerialDescriptor.addElement("ext", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private BidRequest$$serializer() {
    }

    public KSerializer<?>[] childSerializers() {
        KSerializer<?>[] access$get$childSerializers$cp = BidRequest.$childSerializers;
        return new KSerializer[]{access$get$childSerializers$cp[0], BuiltinSerializersKt.getNullable(App$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(Device$$serializer.INSTANCE), Format$$serializer.INSTANCE, BuiltinSerializersKt.getNullable(User$$serializer.INSTANCE), ByteSerializer.INSTANCE, IntSerializer.INSTANCE, BuiltinSerializersKt.getNullable(access$get$childSerializers$cp[7]), BuiltinSerializersKt.getNullable(Source$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(Regs$$serializer.INSTANCE), access$get$childSerializers$cp[10]};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v6, resolved type: com.adsbynimbus.openrtb.request.Impression[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v14, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v6, resolved type: com.adsbynimbus.openrtb.request.Device} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v17, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v7, resolved type: com.adsbynimbus.openrtb.request.Format} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v21, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v5, resolved type: com.adsbynimbus.openrtb.request.User} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v27, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v7, resolved type: java.lang.String[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v22, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v7, resolved type: java.util.Map} */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0185, code lost:
        r4 = 9;
        r11 = 10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x019a, code lost:
        r4 = 9;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.adsbynimbus.openrtb.request.BidRequest deserialize(kotlinx.serialization.encoding.Decoder r28) {
        /*
            r27 = this;
            r0 = r28
            java.lang.String r1 = "decoder"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            kotlinx.serialization.descriptors.SerialDescriptor r1 = r27.getDescriptor()
            kotlinx.serialization.encoding.CompositeDecoder r0 = r0.beginStructure(r1)
            kotlinx.serialization.KSerializer[] r2 = com.adsbynimbus.openrtb.request.BidRequest.$childSerializers
            boolean r3 = r0.decodeSequentially()
            r4 = 9
            r5 = 6
            r6 = 5
            r7 = 3
            r8 = 8
            r9 = 4
            r10 = 2
            r11 = 10
            r12 = 7
            r13 = 1
            r14 = 0
            r15 = 0
            if (r3 == 0) goto L_0x00a0
            r3 = r2[r14]
            kotlinx.serialization.DeserializationStrategy r3 = (kotlinx.serialization.DeserializationStrategy) r3
            java.lang.Object r3 = r0.decodeSerializableElement(r1, r14, r3, r15)
            com.adsbynimbus.openrtb.request.Impression[] r3 = (com.adsbynimbus.openrtb.request.Impression[]) r3
            com.adsbynimbus.openrtb.request.App$$serializer r14 = com.adsbynimbus.openrtb.request.App$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r14 = (kotlinx.serialization.DeserializationStrategy) r14
            java.lang.Object r13 = r0.decodeNullableSerializableElement(r1, r13, r14, r15)
            com.adsbynimbus.openrtb.request.App r13 = (com.adsbynimbus.openrtb.request.App) r13
            com.adsbynimbus.openrtb.request.Device$$serializer r14 = com.adsbynimbus.openrtb.request.Device$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r14 = (kotlinx.serialization.DeserializationStrategy) r14
            java.lang.Object r10 = r0.decodeNullableSerializableElement(r1, r10, r14, r15)
            com.adsbynimbus.openrtb.request.Device r10 = (com.adsbynimbus.openrtb.request.Device) r10
            com.adsbynimbus.openrtb.request.Format$$serializer r14 = com.adsbynimbus.openrtb.request.Format$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r14 = (kotlinx.serialization.DeserializationStrategy) r14
            java.lang.Object r7 = r0.decodeSerializableElement(r1, r7, r14, r15)
            com.adsbynimbus.openrtb.request.Format r7 = (com.adsbynimbus.openrtb.request.Format) r7
            com.adsbynimbus.openrtb.request.User$$serializer r14 = com.adsbynimbus.openrtb.request.User$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r14 = (kotlinx.serialization.DeserializationStrategy) r14
            java.lang.Object r9 = r0.decodeNullableSerializableElement(r1, r9, r14, r15)
            com.adsbynimbus.openrtb.request.User r9 = (com.adsbynimbus.openrtb.request.User) r9
            byte r6 = r0.decodeByteElement(r1, r6)
            int r5 = r0.decodeIntElement(r1, r5)
            r14 = r2[r12]
            kotlinx.serialization.DeserializationStrategy r14 = (kotlinx.serialization.DeserializationStrategy) r14
            java.lang.Object r12 = r0.decodeNullableSerializableElement(r1, r12, r14, r15)
            java.lang.String[] r12 = (java.lang.String[]) r12
            com.adsbynimbus.openrtb.request.Source$$serializer r14 = com.adsbynimbus.openrtb.request.Source$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r14 = (kotlinx.serialization.DeserializationStrategy) r14
            java.lang.Object r8 = r0.decodeNullableSerializableElement(r1, r8, r14, r15)
            com.adsbynimbus.openrtb.request.Source r8 = (com.adsbynimbus.openrtb.request.Source) r8
            com.adsbynimbus.openrtb.request.Regs$$serializer r14 = com.adsbynimbus.openrtb.request.Regs$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r14 = (kotlinx.serialization.DeserializationStrategy) r14
            java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r4, r14, r15)
            com.adsbynimbus.openrtb.request.Regs r4 = (com.adsbynimbus.openrtb.request.Regs) r4
            r2 = r2[r11]
            kotlinx.serialization.DeserializationStrategy r2 = (kotlinx.serialization.DeserializationStrategy) r2
            java.lang.Object r2 = r0.decodeSerializableElement(r1, r11, r2, r15)
            java.util.Map r2 = (java.util.Map) r2
            r11 = 2047(0x7ff, float:2.868E-42)
            r21 = r2
            r20 = r4
            r17 = r5
            r16 = r6
            r14 = r7
            r19 = r8
            r15 = r9
            r18 = r12
            r12 = r13
            r13 = r10
            r10 = r11
            r11 = r3
            goto L_0x01b8
        L_0x00a0:
            r25 = r13
            r3 = r14
            r5 = r15
            r6 = r5
            r7 = r6
            r9 = r7
            r10 = r9
            r12 = r10
            r13 = r12
            r16 = r13
            r17 = r16
            r15 = r3
        L_0x00af:
            if (r25 == 0) goto L_0x01a0
            int r8 = r0.decodeElementIndex(r1)
            switch(r8) {
                case -1: goto L_0x018d;
                case 0: goto L_0x0169;
                case 1: goto L_0x0153;
                case 2: goto L_0x0140;
                case 3: goto L_0x012d;
                case 4: goto L_0x0118;
                case 5: goto L_0x010b;
                case 6: goto L_0x0101;
                case 7: goto L_0x00ed;
                case 8: goto L_0x00db;
                case 9: goto L_0x00cd;
                case 10: goto L_0x00be;
                default: goto L_0x00b8;
            }
        L_0x00b8:
            kotlinx.serialization.UnknownFieldException r0 = new kotlinx.serialization.UnknownFieldException
            r0.<init>((int) r8)
            throw r0
        L_0x00be:
            r8 = r2[r11]
            kotlinx.serialization.DeserializationStrategy r8 = (kotlinx.serialization.DeserializationStrategy) r8
            java.lang.Object r8 = r0.decodeSerializableElement(r1, r11, r8, r12)
            r12 = r8
            java.util.Map r12 = (java.util.Map) r12
            r14 = r14 | 1024(0x400, float:1.435E-42)
            goto L_0x019c
        L_0x00cd:
            com.adsbynimbus.openrtb.request.Regs$$serializer r8 = com.adsbynimbus.openrtb.request.Regs$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r8 = (kotlinx.serialization.DeserializationStrategy) r8
            java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r4, r8, r5)
            com.adsbynimbus.openrtb.request.Regs r5 = (com.adsbynimbus.openrtb.request.Regs) r5
            r14 = r14 | 512(0x200, float:7.175E-43)
            goto L_0x019c
        L_0x00db:
            com.adsbynimbus.openrtb.request.Source$$serializer r8 = com.adsbynimbus.openrtb.request.Source$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r8 = (kotlinx.serialization.DeserializationStrategy) r8
            r4 = 8
            java.lang.Object r6 = r0.decodeNullableSerializableElement(r1, r4, r8, r6)
            com.adsbynimbus.openrtb.request.Source r6 = (com.adsbynimbus.openrtb.request.Source) r6
            r14 = r14 | 256(0x100, float:3.59E-43)
            r8 = r4
            r4 = 9
            goto L_0x00af
        L_0x00ed:
            r4 = 8
            r8 = 7
            r24 = r2[r8]
            r4 = r24
            kotlinx.serialization.DeserializationStrategy r4 = (kotlinx.serialization.DeserializationStrategy) r4
            java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r8, r4, r9)
            r9 = r4
            java.lang.String[] r9 = (java.lang.String[]) r9
            r14 = r14 | 128(0x80, float:1.794E-43)
            goto L_0x019a
        L_0x0101:
            r4 = 6
            r8 = 7
            int r3 = r0.decodeIntElement(r1, r4)
            r14 = r14 | 64
            goto L_0x019a
        L_0x010b:
            r4 = 6
            r8 = 7
            r15 = 5
            byte r22 = r0.decodeByteElement(r1, r15)
            r14 = r14 | 32
            r15 = r22
            goto L_0x019a
        L_0x0118:
            r4 = 6
            r8 = 7
            r22 = 5
            com.adsbynimbus.openrtb.request.User$$serializer r23 = com.adsbynimbus.openrtb.request.User$$serializer.INSTANCE
            r4 = r23
            kotlinx.serialization.DeserializationStrategy r4 = (kotlinx.serialization.DeserializationStrategy) r4
            r11 = 4
            java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r11, r4, r7)
            r7 = r4
            com.adsbynimbus.openrtb.request.User r7 = (com.adsbynimbus.openrtb.request.User) r7
            r14 = r14 | 16
            goto L_0x0185
        L_0x012d:
            r8 = 7
            r11 = 4
            r22 = 5
            com.adsbynimbus.openrtb.request.Format$$serializer r4 = com.adsbynimbus.openrtb.request.Format$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r4 = (kotlinx.serialization.DeserializationStrategy) r4
            r11 = 3
            java.lang.Object r4 = r0.decodeSerializableElement(r1, r11, r4, r10)
            r10 = r4
            com.adsbynimbus.openrtb.request.Format r10 = (com.adsbynimbus.openrtb.request.Format) r10
            r14 = r14 | 8
            goto L_0x0185
        L_0x0140:
            r8 = 7
            r11 = 3
            r22 = 5
            com.adsbynimbus.openrtb.request.Device$$serializer r4 = com.adsbynimbus.openrtb.request.Device$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r4 = (kotlinx.serialization.DeserializationStrategy) r4
            r11 = 2
            java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r11, r4, r13)
            r13 = r4
            com.adsbynimbus.openrtb.request.Device r13 = (com.adsbynimbus.openrtb.request.Device) r13
            r14 = r14 | 4
            goto L_0x0185
        L_0x0153:
            r8 = 7
            r11 = 2
            r22 = 5
            com.adsbynimbus.openrtb.request.App$$serializer r4 = com.adsbynimbus.openrtb.request.App$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r4 = (kotlinx.serialization.DeserializationStrategy) r4
            r8 = r17
            r11 = 1
            java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r11, r4, r8)
            com.adsbynimbus.openrtb.request.App r4 = (com.adsbynimbus.openrtb.request.App) r4
            r14 = r14 | 2
            r17 = r4
            goto L_0x0185
        L_0x0169:
            r8 = r17
            r4 = 0
            r11 = 1
            r22 = 5
            r18 = r2[r4]
            r11 = r18
            kotlinx.serialization.DeserializationStrategy r11 = (kotlinx.serialization.DeserializationStrategy) r11
            r18 = r2
            r2 = r16
            java.lang.Object r2 = r0.decodeSerializableElement(r1, r4, r11, r2)
            r16 = r2
            com.adsbynimbus.openrtb.request.Impression[] r16 = (com.adsbynimbus.openrtb.request.Impression[]) r16
            r14 = r14 | 1
            r2 = r18
        L_0x0185:
            r4 = 9
            r8 = 8
            r11 = 10
            goto L_0x00af
        L_0x018d:
            r18 = r2
            r2 = r16
            r8 = r17
            r4 = 0
            r22 = 5
            r25 = r4
            r2 = r18
        L_0x019a:
            r4 = 9
        L_0x019c:
            r8 = 8
            goto L_0x00af
        L_0x01a0:
            r2 = r16
            r8 = r17
            r11 = r2
            r17 = r3
            r20 = r5
            r19 = r6
            r18 = r9
            r21 = r12
            r16 = r15
            r15 = r7
            r12 = r8
            r26 = r14
            r14 = r10
            r10 = r26
        L_0x01b8:
            r0.endStructure(r1)
            com.adsbynimbus.openrtb.request.BidRequest r0 = new com.adsbynimbus.openrtb.request.BidRequest
            r22 = 0
            r9 = r0
            r9.<init>((int) r10, (com.adsbynimbus.openrtb.request.Impression[]) r11, (com.adsbynimbus.openrtb.request.App) r12, (com.adsbynimbus.openrtb.request.Device) r13, (com.adsbynimbus.openrtb.request.Format) r14, (com.adsbynimbus.openrtb.request.User) r15, (byte) r16, (int) r17, (java.lang.String[]) r18, (com.adsbynimbus.openrtb.request.Source) r19, (com.adsbynimbus.openrtb.request.Regs) r20, (java.util.Map) r21, (kotlinx.serialization.internal.SerializationConstructorMarker) r22)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.openrtb.request.BidRequest$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.adsbynimbus.openrtb.request.BidRequest");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, BidRequest bidRequest) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(bidRequest, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder beginStructure = encoder.beginStructure(descriptor2);
        BidRequest.write$Self$kotlin_release(bidRequest, beginStructure, descriptor2);
        beginStructure.endStructure(descriptor2);
    }

    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}

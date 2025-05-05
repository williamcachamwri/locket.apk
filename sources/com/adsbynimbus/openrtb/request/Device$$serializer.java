package com.adsbynimbus.openrtb.request;

import androidx.media3.exoplayer.upstream.CmcdData;
import com.amplitude.api.Constants;
import io.sentry.protocol.Device;
import io.sentry.protocol.OperatingSystem;
import io.sentry.protocol.User;
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

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/adsbynimbus/openrtb/request/Device.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/adsbynimbus/openrtb/request/Device;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* compiled from: Device.kt */
public final class Device$$serializer implements GeneratedSerializer<Device> {
    public static final Device$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        Device$$serializer device$$serializer = new Device$$serializer();
        INSTANCE = device$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.adsbynimbus.openrtb.request.Device", device$$serializer, 18);
        pluginGeneratedSerialDescriptor.addElement("ua", false);
        pluginGeneratedSerialDescriptor.addElement("ifa", false);
        pluginGeneratedSerialDescriptor.addElement("make", false);
        pluginGeneratedSerialDescriptor.addElement(Device.JsonKeys.MODEL, false);
        pluginGeneratedSerialDescriptor.addElement("hwv", true);
        pluginGeneratedSerialDescriptor.addElement(OperatingSystem.TYPE, false);
        pluginGeneratedSerialDescriptor.addElement("osv", false);
        pluginGeneratedSerialDescriptor.addElement(CmcdData.Factory.STREAMING_FORMAT_HLS, false);
        pluginGeneratedSerialDescriptor.addElement("w", false);
        pluginGeneratedSerialDescriptor.addElement("pxratio", true);
        pluginGeneratedSerialDescriptor.addElement("language", true);
        pluginGeneratedSerialDescriptor.addElement("devicetype", true);
        pluginGeneratedSerialDescriptor.addElement("connectiontype", true);
        pluginGeneratedSerialDescriptor.addElement("dnt", true);
        pluginGeneratedSerialDescriptor.addElement("lmt", true);
        pluginGeneratedSerialDescriptor.addElement(User.JsonKeys.GEO, true);
        pluginGeneratedSerialDescriptor.addElement("ip", true);
        pluginGeneratedSerialDescriptor.addElement(Constants.AMP_TRACKING_OPTION_CARRIER, true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private Device$$serializer() {
    }

    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{StringSerializer.INSTANCE, StringSerializer.INSTANCE, StringSerializer.INSTANCE, StringSerializer.INSTANCE, BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), StringSerializer.INSTANCE, StringSerializer.INSTANCE, IntSerializer.INSTANCE, IntSerializer.INSTANCE, BuiltinSerializersKt.getNullable(FloatSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), ByteSerializer.INSTANCE, ByteSerializer.INSTANCE, ByteSerializer.INSTANCE, ByteSerializer.INSTANCE, BuiltinSerializersKt.getNullable(Geo$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE)};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v22, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v8, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v24, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v9, resolved type: com.adsbynimbus.openrtb.request.Geo} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v28, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v9, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v32, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v6, resolved type: java.lang.String} */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x013d, code lost:
        r7 = r7 | r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x01be, code lost:
        r11 = r5;
        r3 = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x01c1, code lost:
        r5 = 17;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0204, code lost:
        r3 = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0206, code lost:
        r5 = 17;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.adsbynimbus.openrtb.request.Device deserialize(kotlinx.serialization.encoding.Decoder r40) {
        /*
            r39 = this;
            r0 = r40
            java.lang.String r1 = "decoder"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            kotlinx.serialization.descriptors.SerialDescriptor r1 = r39.getDescriptor()
            kotlinx.serialization.encoding.CompositeDecoder r0 = r0.beginStructure(r1)
            boolean r2 = r0.decodeSequentially()
            r8 = 11
            r9 = 10
            r10 = 9
            r11 = 7
            r12 = 6
            r13 = 5
            r14 = 3
            r3 = 8
            r15 = 4
            r4 = 2
            r5 = 1
            r6 = 0
            r7 = 0
            if (r2 == 0) goto L_0x00d5
            java.lang.String r2 = r0.decodeStringElement(r1, r6)
            java.lang.String r5 = r0.decodeStringElement(r1, r5)
            java.lang.String r4 = r0.decodeStringElement(r1, r4)
            java.lang.String r6 = r0.decodeStringElement(r1, r14)
            kotlinx.serialization.internal.StringSerializer r14 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r14 = (kotlinx.serialization.DeserializationStrategy) r14
            java.lang.Object r14 = r0.decodeNullableSerializableElement(r1, r15, r14, r7)
            java.lang.String r14 = (java.lang.String) r14
            java.lang.String r13 = r0.decodeStringElement(r1, r13)
            java.lang.String r12 = r0.decodeStringElement(r1, r12)
            int r11 = r0.decodeIntElement(r1, r11)
            int r3 = r0.decodeIntElement(r1, r3)
            kotlinx.serialization.internal.FloatSerializer r15 = kotlinx.serialization.internal.FloatSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r15 = (kotlinx.serialization.DeserializationStrategy) r15
            java.lang.Object r10 = r0.decodeNullableSerializableElement(r1, r10, r15, r7)
            java.lang.Float r10 = (java.lang.Float) r10
            kotlinx.serialization.internal.StringSerializer r15 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r15 = (kotlinx.serialization.DeserializationStrategy) r15
            java.lang.Object r9 = r0.decodeNullableSerializableElement(r1, r9, r15, r7)
            java.lang.String r9 = (java.lang.String) r9
            byte r8 = r0.decodeByteElement(r1, r8)
            r15 = 12
            byte r15 = r0.decodeByteElement(r1, r15)
            r7 = 13
            byte r7 = r0.decodeByteElement(r1, r7)
            r19 = r2
            r2 = 14
            byte r2 = r0.decodeByteElement(r1, r2)
            com.adsbynimbus.openrtb.request.Geo$$serializer r18 = com.adsbynimbus.openrtb.request.Geo$$serializer.INSTANCE
            r20 = r2
            r2 = r18
            kotlinx.serialization.DeserializationStrategy r2 = (kotlinx.serialization.DeserializationStrategy) r2
            r18 = r3
            r17 = r6
            r3 = 15
            r6 = 0
            java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r3, r2, r6)
            com.adsbynimbus.openrtb.request.Geo r2 = (com.adsbynimbus.openrtb.request.Geo) r2
            kotlinx.serialization.internal.StringSerializer r3 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r3 = (kotlinx.serialization.DeserializationStrategy) r3
            r21 = r2
            r2 = 16
            java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r2, r3, r6)
            java.lang.String r2 = (java.lang.String) r2
            kotlinx.serialization.internal.StringSerializer r3 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r3 = (kotlinx.serialization.DeserializationStrategy) r3
            r16 = r5
            r5 = 17
            java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r5, r3, r6)
            java.lang.String r3 = (java.lang.String) r3
            r5 = 262143(0x3ffff, float:3.6734E-40)
            r30 = r2
            r31 = r3
            r27 = r7
            r25 = r8
            r24 = r9
            r23 = r10
            r26 = r15
            r15 = r16
            r22 = r18
            r28 = r20
            r29 = r21
            r16 = r4
            r21 = r11
            r20 = r12
            r18 = r14
            r14 = r19
            r19 = r13
            r13 = r5
            goto L_0x0238
        L_0x00d5:
            r2 = r5
            r5 = 17
            r38 = r7
            r7 = r6
            r6 = r38
            r37 = r2
            r2 = r6
            r4 = r2
            r12 = r4
            r13 = r12
            r14 = r13
            r15 = r14
            r22 = r15
            r23 = r22
            r24 = r23
            r25 = r24
            r27 = r25
            r28 = r27
            r6 = r7
            r21 = r6
            r31 = r21
            r34 = r31
            r35 = r34
            r36 = r35
        L_0x00fc:
            if (r37 == 0) goto L_0x0213
            int r11 = r0.decodeElementIndex(r1)
            switch(r11) {
                case -1: goto L_0x0209;
                case 0: goto L_0x01fa;
                case 1: goto L_0x01ef;
                case 2: goto L_0x01e4;
                case 3: goto L_0x01d9;
                case 4: goto L_0x01c5;
                case 5: goto L_0x01b4;
                case 6: goto L_0x01a9;
                case 7: goto L_0x019e;
                case 8: goto L_0x0195;
                case 9: goto L_0x0185;
                case 10: goto L_0x0170;
                case 11: goto L_0x0164;
                case 12: goto L_0x0158;
                case 13: goto L_0x014c;
                case 14: goto L_0x0140;
                case 15: goto L_0x012b;
                case 16: goto L_0x011b;
                case 17: goto L_0x010b;
                default: goto L_0x0105;
            }
        L_0x0105:
            kotlinx.serialization.UnknownFieldException r0 = new kotlinx.serialization.UnknownFieldException
            r0.<init>((int) r11)
            throw r0
        L_0x010b:
            kotlinx.serialization.internal.StringSerializer r11 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r11 = (kotlinx.serialization.DeserializationStrategy) r11
            java.lang.Object r11 = r0.decodeNullableSerializableElement(r1, r5, r11, r12)
            r12 = r11
            java.lang.String r12 = (java.lang.String) r12
            r11 = 131072(0x20000, float:1.83671E-40)
            r7 = r7 | r11
            goto L_0x0210
        L_0x011b:
            kotlinx.serialization.internal.StringSerializer r11 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r11 = (kotlinx.serialization.DeserializationStrategy) r11
            r5 = 16
            java.lang.Object r11 = r0.decodeNullableSerializableElement(r1, r5, r11, r13)
            r13 = r11
            java.lang.String r13 = (java.lang.String) r13
            r11 = 65536(0x10000, float:9.18355E-41)
            goto L_0x013d
        L_0x012b:
            r5 = 16
            com.adsbynimbus.openrtb.request.Geo$$serializer r11 = com.adsbynimbus.openrtb.request.Geo$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r11 = (kotlinx.serialization.DeserializationStrategy) r11
            r5 = 15
            java.lang.Object r11 = r0.decodeNullableSerializableElement(r1, r5, r11, r15)
            r15 = r11
            com.adsbynimbus.openrtb.request.Geo r15 = (com.adsbynimbus.openrtb.request.Geo) r15
            r11 = 32768(0x8000, float:4.5918E-41)
        L_0x013d:
            r7 = r7 | r11
            goto L_0x0206
        L_0x0140:
            r5 = 15
            r11 = 14
            byte r6 = r0.decodeByteElement(r1, r11)
            r7 = r7 | 16384(0x4000, float:2.2959E-41)
            goto L_0x0206
        L_0x014c:
            r5 = 13
            r11 = 14
            byte r31 = r0.decodeByteElement(r1, r5)
            r7 = r7 | 8192(0x2000, float:1.14794E-41)
            goto L_0x0206
        L_0x0158:
            r5 = 12
            r11 = 14
            byte r36 = r0.decodeByteElement(r1, r5)
            r7 = r7 | 4096(0x1000, float:5.74E-42)
            goto L_0x0206
        L_0x0164:
            r5 = 12
            r11 = 14
            byte r34 = r0.decodeByteElement(r1, r8)
            r7 = r7 | 2048(0x800, float:2.87E-42)
            goto L_0x0206
        L_0x0170:
            r5 = 12
            r11 = 14
            kotlinx.serialization.internal.StringSerializer r18 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            r5 = r18
            kotlinx.serialization.DeserializationStrategy r5 = (kotlinx.serialization.DeserializationStrategy) r5
            java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r9, r5, r14)
            r14 = r5
            java.lang.String r14 = (java.lang.String) r14
            r7 = r7 | 1024(0x400, float:1.435E-42)
            goto L_0x0206
        L_0x0185:
            r11 = 14
            kotlinx.serialization.internal.FloatSerializer r5 = kotlinx.serialization.internal.FloatSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r5 = (kotlinx.serialization.DeserializationStrategy) r5
            java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r10, r5, r4)
            java.lang.Float r4 = (java.lang.Float) r4
            r7 = r7 | 512(0x200, float:7.175E-43)
            goto L_0x0206
        L_0x0195:
            r11 = 14
            int r21 = r0.decodeIntElement(r1, r3)
            r7 = r7 | 256(0x100, float:3.59E-43)
            goto L_0x0206
        L_0x019e:
            r5 = 7
            r11 = 14
            int r35 = r0.decodeIntElement(r1, r5)
            r7 = r7 | 128(0x80, float:1.794E-43)
            r11 = r5
            goto L_0x01c1
        L_0x01a9:
            r3 = 6
            r5 = 7
            r11 = 14
            java.lang.String r28 = r0.decodeStringElement(r1, r3)
            r7 = r7 | 64
            goto L_0x01be
        L_0x01b4:
            r3 = 5
            r5 = 7
            r11 = 14
            java.lang.String r27 = r0.decodeStringElement(r1, r3)
            r7 = r7 | 32
        L_0x01be:
            r11 = r5
            r3 = 8
        L_0x01c1:
            r5 = 17
            goto L_0x00fc
        L_0x01c5:
            r3 = 5
            r5 = 7
            r11 = 14
            kotlinx.serialization.internal.StringSerializer r33 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            r3 = r33
            kotlinx.serialization.DeserializationStrategy r3 = (kotlinx.serialization.DeserializationStrategy) r3
            r5 = 4
            java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r5, r3, r2)
            java.lang.String r2 = (java.lang.String) r2
            r7 = r7 | 16
            goto L_0x0204
        L_0x01d9:
            r3 = 3
            r5 = 4
            r11 = 14
            java.lang.String r25 = r0.decodeStringElement(r1, r3)
            r7 = r7 | 8
            goto L_0x0204
        L_0x01e4:
            r3 = 2
            r5 = 4
            r11 = 14
            java.lang.String r24 = r0.decodeStringElement(r1, r3)
            r7 = r7 | 4
            goto L_0x0204
        L_0x01ef:
            r3 = 1
            r5 = 4
            r11 = 14
            java.lang.String r23 = r0.decodeStringElement(r1, r3)
            r7 = r7 | 2
            goto L_0x0204
        L_0x01fa:
            r3 = 0
            r5 = 4
            r11 = 14
            java.lang.String r22 = r0.decodeStringElement(r1, r3)
            r7 = r7 | 1
        L_0x0204:
            r3 = 8
        L_0x0206:
            r5 = 17
            goto L_0x0210
        L_0x0209:
            r3 = 0
            r11 = 14
            r37 = r3
            r3 = 8
        L_0x0210:
            r11 = 7
            goto L_0x00fc
        L_0x0213:
            r18 = r2
            r30 = r13
            r29 = r15
            r15 = r23
            r16 = r24
            r17 = r25
            r19 = r27
            r20 = r28
            r27 = r31
            r25 = r34
            r26 = r36
            r23 = r4
            r28 = r6
            r13 = r7
            r31 = r12
            r24 = r14
            r14 = r22
            r22 = r21
            r21 = r35
        L_0x0238:
            r0.endStructure(r1)
            com.adsbynimbus.openrtb.request.Device r0 = new com.adsbynimbus.openrtb.request.Device
            r12 = r0
            r32 = 0
            r12.<init>((int) r13, (java.lang.String) r14, (java.lang.String) r15, (java.lang.String) r16, (java.lang.String) r17, (java.lang.String) r18, (java.lang.String) r19, (java.lang.String) r20, (int) r21, (int) r22, (java.lang.Float) r23, (java.lang.String) r24, (byte) r25, (byte) r26, (byte) r27, (byte) r28, (com.adsbynimbus.openrtb.request.Geo) r29, (java.lang.String) r30, (java.lang.String) r31, (kotlinx.serialization.internal.SerializationConstructorMarker) r32)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.openrtb.request.Device$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.adsbynimbus.openrtb.request.Device");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, Device device) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(device, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder beginStructure = encoder.beginStructure(descriptor2);
        Device.write$Self$kotlin_release(device, beginStructure, descriptor2);
        beginStructure.endStructure(descriptor2);
    }

    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}

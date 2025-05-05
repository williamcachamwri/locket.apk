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

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/adsbynimbus/openrtb/request/Video.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/adsbynimbus/openrtb/request/Video;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* compiled from: Video.kt */
public final class Video$$serializer implements GeneratedSerializer<Video> {
    public static final Video$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        Video$$serializer video$$serializer = new Video$$serializer();
        INSTANCE = video$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.adsbynimbus.openrtb.request.Video", video$$serializer, 22);
        pluginGeneratedSerialDescriptor.addElement("bidfloor", true);
        pluginGeneratedSerialDescriptor.addElement("mimes", true);
        pluginGeneratedSerialDescriptor.addElement("minduration", true);
        pluginGeneratedSerialDescriptor.addElement("maxduration", true);
        pluginGeneratedSerialDescriptor.addElement("protocols", true);
        pluginGeneratedSerialDescriptor.addElement("w", true);
        pluginGeneratedSerialDescriptor.addElement(CmcdData.Factory.STREAMING_FORMAT_HLS, true);
        pluginGeneratedSerialDescriptor.addElement("startdelay", true);
        pluginGeneratedSerialDescriptor.addElement("placement", true);
        pluginGeneratedSerialDescriptor.addElement("linearity", true);
        pluginGeneratedSerialDescriptor.addElement("skip", true);
        pluginGeneratedSerialDescriptor.addElement("delivery", true);
        pluginGeneratedSerialDescriptor.addElement("skipmin", true);
        pluginGeneratedSerialDescriptor.addElement("skipafter", true);
        pluginGeneratedSerialDescriptor.addElement("minbitrate", true);
        pluginGeneratedSerialDescriptor.addElement("maxbitrate", true);
        pluginGeneratedSerialDescriptor.addElement("pos", true);
        pluginGeneratedSerialDescriptor.addElement("playbackmethod", true);
        pluginGeneratedSerialDescriptor.addElement("api", true);
        pluginGeneratedSerialDescriptor.addElement("companionad", true);
        pluginGeneratedSerialDescriptor.addElement("companiontype", true);
        pluginGeneratedSerialDescriptor.addElement("ext", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private Video$$serializer() {
    }

    public KSerializer<?>[] childSerializers() {
        KSerializer<?>[] access$get$childSerializers$cp = Video.$childSerializers;
        return new KSerializer[]{FloatSerializer.INSTANCE, BuiltinSerializersKt.getNullable(access$get$childSerializers$cp[1]), IntSerializer.INSTANCE, IntSerializer.INSTANCE, BuiltinSerializersKt.getNullable(ByteArraySerializer.INSTANCE), IntSerializer.INSTANCE, IntSerializer.INSTANCE, IntSerializer.INSTANCE, ByteSerializer.INSTANCE, ByteSerializer.INSTANCE, ByteSerializer.INSTANCE, BuiltinSerializersKt.getNullable(ByteArraySerializer.INSTANCE), IntSerializer.INSTANCE, IntSerializer.INSTANCE, IntSerializer.INSTANCE, IntSerializer.INSTANCE, ByteSerializer.INSTANCE, BuiltinSerializersKt.getNullable(ByteArraySerializer.INSTANCE), BuiltinSerializersKt.getNullable(ByteArraySerializer.INSTANCE), BuiltinSerializersKt.getNullable(access$get$childSerializers$cp[19]), BuiltinSerializersKt.getNullable(ByteArraySerializer.INSTANCE), access$get$childSerializers$cp[21]};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r32v5, resolved type: java.lang.String[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v14, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v8, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v27, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v8, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v33, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v8, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v37, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v9, resolved type: com.adsbynimbus.openrtb.request.Banner[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v39, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v8, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v43, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v7, resolved type: java.util.Map} */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x019b, code lost:
        r10 = r10 | r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0215, code lost:
        r3 = 21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0257, code lost:
        r3 = 21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x02c7, code lost:
        r3 = 21;
        r11 = 10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x02cd, code lost:
        r12 = 9;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.adsbynimbus.openrtb.request.Video deserialize(kotlinx.serialization.encoding.Decoder r43) {
        /*
            r42 = this;
            r0 = r43
            java.lang.String r1 = "decoder"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            kotlinx.serialization.descriptors.SerialDescriptor r1 = r42.getDescriptor()
            kotlinx.serialization.encoding.CompositeDecoder r0 = r0.beginStructure(r1)
            kotlinx.serialization.KSerializer[] r2 = com.adsbynimbus.openrtb.request.Video.$childSerializers
            boolean r3 = r0.decodeSequentially()
            r11 = 10
            r12 = 9
            r13 = 7
            r14 = 6
            r15 = 5
            r4 = 3
            r5 = 8
            r6 = 4
            r7 = 2
            r8 = 1
            r9 = 0
            r10 = 0
            if (r3 == 0) goto L_0x010b
            float r3 = r0.decodeFloatElement(r1, r9)
            r9 = r2[r8]
            kotlinx.serialization.DeserializationStrategy r9 = (kotlinx.serialization.DeserializationStrategy) r9
            java.lang.Object r8 = r0.decodeNullableSerializableElement(r1, r8, r9, r10)
            java.lang.String[] r8 = (java.lang.String[]) r8
            int r7 = r0.decodeIntElement(r1, r7)
            int r4 = r0.decodeIntElement(r1, r4)
            kotlinx.serialization.internal.ByteArraySerializer r9 = kotlinx.serialization.internal.ByteArraySerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r9 = (kotlinx.serialization.DeserializationStrategy) r9
            java.lang.Object r6 = r0.decodeNullableSerializableElement(r1, r6, r9, r10)
            byte[] r6 = (byte[]) r6
            int r9 = r0.decodeIntElement(r1, r15)
            int r14 = r0.decodeIntElement(r1, r14)
            int r13 = r0.decodeIntElement(r1, r13)
            byte r5 = r0.decodeByteElement(r1, r5)
            byte r12 = r0.decodeByteElement(r1, r12)
            byte r11 = r0.decodeByteElement(r1, r11)
            kotlinx.serialization.internal.ByteArraySerializer r15 = kotlinx.serialization.internal.ByteArraySerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r15 = (kotlinx.serialization.DeserializationStrategy) r15
            r25 = r3
            r3 = 11
            java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r3, r15, r10)
            byte[] r3 = (byte[]) r3
            r15 = 12
            int r15 = r0.decodeIntElement(r1, r15)
            r10 = 13
            int r10 = r0.decodeIntElement(r1, r10)
            r20 = r3
            r3 = 14
            int r3 = r0.decodeIntElement(r1, r3)
            r19 = r3
            r3 = 15
            int r3 = r0.decodeIntElement(r1, r3)
            r18 = r3
            r3 = 16
            byte r3 = r0.decodeByteElement(r1, r3)
            kotlinx.serialization.internal.ByteArraySerializer r17 = kotlinx.serialization.internal.ByteArraySerializer.INSTANCE
            r23 = r3
            r3 = r17
            kotlinx.serialization.DeserializationStrategy r3 = (kotlinx.serialization.DeserializationStrategy) r3
            r17 = r4
            r16 = r9
            r4 = 17
            r9 = 0
            java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r4, r3, r9)
            byte[] r3 = (byte[]) r3
            kotlinx.serialization.internal.ByteArraySerializer r4 = kotlinx.serialization.internal.ByteArraySerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r4 = (kotlinx.serialization.DeserializationStrategy) r4
            r24 = r3
            r3 = 18
            java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r3, r4, r9)
            byte[] r3 = (byte[]) r3
            r4 = 19
            r22 = r2[r4]
            r43 = r3
            r3 = r22
            kotlinx.serialization.DeserializationStrategy r3 = (kotlinx.serialization.DeserializationStrategy) r3
            java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r4, r3, r9)
            com.adsbynimbus.openrtb.request.Banner[] r3 = (com.adsbynimbus.openrtb.request.Banner[]) r3
            kotlinx.serialization.internal.ByteArraySerializer r4 = kotlinx.serialization.internal.ByteArraySerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r4 = (kotlinx.serialization.DeserializationStrategy) r4
            r22 = r3
            r3 = 20
            java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r3, r4, r9)
            byte[] r3 = (byte[]) r3
            r4 = 21
            r2 = r2[r4]
            kotlinx.serialization.DeserializationStrategy r2 = (kotlinx.serialization.DeserializationStrategy) r2
            java.lang.Object r2 = r0.decodeSerializableElement(r1, r4, r2, r9)
            java.util.Map r2 = (java.util.Map) r2
            r4 = 4194303(0x3fffff, float:5.87747E-39)
            r26 = r43
            r29 = r2
            r28 = r3
            r9 = r8
            r21 = r10
            r27 = r22
            r8 = r25
            r10 = r7
            r22 = r19
            r19 = r20
            r25 = r24
            r7 = r4
            r20 = r15
            r24 = r23
            r15 = r13
            r13 = r16
            r23 = r18
            r16 = r5
            r18 = r11
            r11 = r17
            r17 = r12
            r12 = r6
            goto L_0x02fe
        L_0x010b:
            r3 = 21
            r41 = r10
            r10 = r9
            r9 = r41
            r21 = 0
            r40 = r8
            r4 = r9
            r6 = r4
            r7 = r6
            r8 = r7
            r13 = r8
            r14 = r13
            r15 = r14
            r32 = r15
            r9 = r10
            r25 = r9
            r26 = r25
            r28 = r26
            r29 = r28
            r30 = r29
            r31 = r30
            r34 = r31
            r35 = r34
            r36 = r35
            r37 = r36
            r38 = r37
            r39 = r38
        L_0x0138:
            if (r40 == 0) goto L_0x02d1
            int r5 = r0.decodeElementIndex(r1)
            switch(r5) {
                case -1: goto L_0x02bd;
                case 0: goto L_0x02ae;
                case 1: goto L_0x0293;
                case 2: goto L_0x0284;
                case 3: goto L_0x0275;
                case 4: goto L_0x025d;
                case 5: goto L_0x024a;
                case 6: goto L_0x023c;
                case 7: goto L_0x022e;
                case 8: goto L_0x021b;
                case 9: goto L_0x0207;
                case 10: goto L_0x01fa;
                case 11: goto L_0x01e2;
                case 12: goto L_0x01d5;
                case 13: goto L_0x01c8;
                case 14: goto L_0x01b9;
                case 15: goto L_0x01ab;
                case 16: goto L_0x019e;
                case 17: goto L_0x018b;
                case 18: goto L_0x0179;
                case 19: goto L_0x0169;
                case 20: goto L_0x0157;
                case 21: goto L_0x0147;
                default: goto L_0x0141;
            }
        L_0x0141:
            kotlinx.serialization.UnknownFieldException r0 = new kotlinx.serialization.UnknownFieldException
            r0.<init>((int) r5)
            throw r0
        L_0x0147:
            r5 = r2[r3]
            kotlinx.serialization.DeserializationStrategy r5 = (kotlinx.serialization.DeserializationStrategy) r5
            java.lang.Object r5 = r0.decodeSerializableElement(r1, r3, r5, r13)
            r13 = r5
            java.util.Map r13 = (java.util.Map) r13
            r5 = 2097152(0x200000, float:2.938736E-39)
            r10 = r10 | r5
            goto L_0x0217
        L_0x0157:
            kotlinx.serialization.internal.ByteArraySerializer r5 = kotlinx.serialization.internal.ByteArraySerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r5 = (kotlinx.serialization.DeserializationStrategy) r5
            r3 = 20
            java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r3, r5, r14)
            r14 = r3
            byte[] r14 = (byte[]) r14
            r3 = 1048576(0x100000, float:1.469368E-39)
            r10 = r10 | r3
            goto L_0x0215
        L_0x0169:
            r3 = 19
            r5 = r2[r3]
            kotlinx.serialization.DeserializationStrategy r5 = (kotlinx.serialization.DeserializationStrategy) r5
            java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r3, r5, r15)
            r15 = r5
            com.adsbynimbus.openrtb.request.Banner[] r15 = (com.adsbynimbus.openrtb.request.Banner[]) r15
            r5 = 524288(0x80000, float:7.34684E-40)
            goto L_0x019b
        L_0x0179:
            r3 = 19
            kotlinx.serialization.internal.ByteArraySerializer r5 = kotlinx.serialization.internal.ByteArraySerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r5 = (kotlinx.serialization.DeserializationStrategy) r5
            r3 = 18
            java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r3, r5, r6)
            r6 = r5
            byte[] r6 = (byte[]) r6
            r5 = 262144(0x40000, float:3.67342E-40)
            goto L_0x019b
        L_0x018b:
            r3 = 18
            kotlinx.serialization.internal.ByteArraySerializer r5 = kotlinx.serialization.internal.ByteArraySerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r5 = (kotlinx.serialization.DeserializationStrategy) r5
            r3 = 17
            java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r3, r5, r4)
            byte[] r4 = (byte[]) r4
            r5 = 131072(0x20000, float:1.83671E-40)
        L_0x019b:
            r10 = r10 | r5
            goto L_0x0215
        L_0x019e:
            r3 = 17
            r5 = 16
            byte r26 = r0.decodeByteElement(r1, r5)
            r16 = 65536(0x10000, float:9.18355E-41)
            r10 = r10 | r16
            goto L_0x0215
        L_0x01ab:
            r3 = 15
            r5 = 16
            int r25 = r0.decodeIntElement(r1, r3)
            r17 = 32768(0x8000, float:4.5918E-41)
            r10 = r10 | r17
            goto L_0x0215
        L_0x01b9:
            r3 = 15
            r5 = 16
            r9 = 14
            int r17 = r0.decodeIntElement(r1, r9)
            r10 = r10 | 16384(0x4000, float:2.2959E-41)
            r9 = r17
            goto L_0x0215
        L_0x01c8:
            r3 = 13
            r5 = 16
            r17 = 14
            int r34 = r0.decodeIntElement(r1, r3)
            r10 = r10 | 8192(0x2000, float:1.14794E-41)
            goto L_0x0215
        L_0x01d5:
            r3 = 12
            r5 = 16
            r17 = 14
            int r39 = r0.decodeIntElement(r1, r3)
            r10 = r10 | 4096(0x1000, float:5.74E-42)
            goto L_0x0215
        L_0x01e2:
            r3 = 12
            r5 = 16
            r17 = 14
            kotlinx.serialization.internal.ByteArraySerializer r19 = kotlinx.serialization.internal.ByteArraySerializer.INSTANCE
            r3 = r19
            kotlinx.serialization.DeserializationStrategy r3 = (kotlinx.serialization.DeserializationStrategy) r3
            r12 = 11
            java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r12, r3, r7)
            r7 = r3
            byte[] r7 = (byte[]) r7
            r10 = r10 | 2048(0x800, float:2.87E-42)
            goto L_0x0257
        L_0x01fa:
            r5 = 16
            r12 = 11
            r17 = 14
            byte r35 = r0.decodeByteElement(r1, r11)
            r10 = r10 | 1024(0x400, float:1.435E-42)
            goto L_0x0257
        L_0x0207:
            r3 = r12
            r5 = 16
            r12 = 11
            r17 = 14
            byte r36 = r0.decodeByteElement(r1, r3)
            r10 = r10 | 512(0x200, float:7.175E-43)
            r12 = r3
        L_0x0215:
            r3 = 21
        L_0x0217:
            r5 = 8
            goto L_0x0138
        L_0x021b:
            r3 = 8
            r5 = 16
            r12 = 11
            r17 = 14
            byte r29 = r0.decodeByteElement(r1, r3)
            r10 = r10 | 256(0x100, float:3.59E-43)
            r5 = r3
            r3 = 21
            goto L_0x02cd
        L_0x022e:
            r3 = 7
            r5 = 16
            r12 = 11
            r17 = 14
            int r37 = r0.decodeIntElement(r1, r3)
            r10 = r10 | 128(0x80, float:1.794E-43)
            goto L_0x0257
        L_0x023c:
            r3 = 6
            r5 = 16
            r12 = 11
            r17 = 14
            int r38 = r0.decodeIntElement(r1, r3)
            r10 = r10 | 64
            goto L_0x0257
        L_0x024a:
            r3 = 5
            r5 = 16
            r12 = 11
            r17 = 14
            int r31 = r0.decodeIntElement(r1, r3)
            r10 = r10 | 32
        L_0x0257:
            r3 = 21
            r5 = 8
            goto L_0x02cd
        L_0x025d:
            r3 = 5
            r5 = 16
            r12 = 11
            r17 = 14
            kotlinx.serialization.internal.ByteArraySerializer r24 = kotlinx.serialization.internal.ByteArraySerializer.INSTANCE
            r3 = r24
            kotlinx.serialization.DeserializationStrategy r3 = (kotlinx.serialization.DeserializationStrategy) r3
            r11 = 4
            java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r11, r3, r8)
            r8 = r3
            byte[] r8 = (byte[]) r8
            r10 = r10 | 16
            goto L_0x02c7
        L_0x0275:
            r3 = 3
            r5 = 16
            r11 = 4
            r12 = 11
            r17 = 14
            int r28 = r0.decodeIntElement(r1, r3)
            r10 = r10 | 8
            goto L_0x02c7
        L_0x0284:
            r3 = 2
            r5 = 16
            r11 = 4
            r12 = 11
            r17 = 14
            int r30 = r0.decodeIntElement(r1, r3)
            r10 = r10 | 4
            goto L_0x02c7
        L_0x0293:
            r3 = 1
            r5 = 16
            r11 = 4
            r12 = 11
            r17 = 14
            r33 = r2[r3]
            r5 = r33
            kotlinx.serialization.DeserializationStrategy r5 = (kotlinx.serialization.DeserializationStrategy) r5
            r11 = r32
            java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r3, r5, r11)
            r32 = r5
            java.lang.String[] r32 = (java.lang.String[]) r32
            r10 = r10 | 2
            goto L_0x02c7
        L_0x02ae:
            r11 = r32
            r3 = 1
            r5 = 0
            r12 = 11
            r17 = 14
            float r21 = r0.decodeFloatElement(r1, r5)
            r10 = r10 | 1
            goto L_0x02c7
        L_0x02bd:
            r11 = r32
            r3 = 1
            r5 = 0
            r12 = 11
            r17 = 14
            r40 = r5
        L_0x02c7:
            r3 = 21
            r5 = 8
            r11 = 10
        L_0x02cd:
            r12 = 9
            goto L_0x0138
        L_0x02d1:
            r11 = r32
            r19 = r7
            r12 = r8
            r22 = r9
            r7 = r10
            r9 = r11
            r27 = r15
            r8 = r21
            r23 = r25
            r24 = r26
            r11 = r28
            r16 = r29
            r10 = r30
            r21 = r34
            r18 = r35
            r17 = r36
            r15 = r37
            r20 = r39
            r25 = r4
            r26 = r6
            r29 = r13
            r28 = r14
            r13 = r31
            r14 = r38
        L_0x02fe:
            r0.endStructure(r1)
            com.adsbynimbus.openrtb.request.Video r0 = new com.adsbynimbus.openrtb.request.Video
            r6 = r0
            r30 = 0
            r6.<init>((int) r7, (float) r8, (java.lang.String[]) r9, (int) r10, (int) r11, (byte[]) r12, (int) r13, (int) r14, (int) r15, (byte) r16, (byte) r17, (byte) r18, (byte[]) r19, (int) r20, (int) r21, (int) r22, (int) r23, (byte) r24, (byte[]) r25, (byte[]) r26, (com.adsbynimbus.openrtb.request.Banner[]) r27, (byte[]) r28, (java.util.Map) r29, (kotlinx.serialization.internal.SerializationConstructorMarker) r30)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.openrtb.request.Video$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.adsbynimbus.openrtb.request.Video");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, Video video) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(video, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder beginStructure = encoder.beginStructure(descriptor2);
        Video.write$Self$kotlin_release(video, beginStructure, descriptor2);
        beginStructure.endStructure(descriptor2);
    }

    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}

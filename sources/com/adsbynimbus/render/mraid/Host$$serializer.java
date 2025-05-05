package com.adsbynimbus.render.mraid;

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
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/adsbynimbus/render/mraid/Host.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/adsbynimbus/render/mraid/Host;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "static_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* compiled from: Host.kt */
public final class Host$$serializer implements GeneratedSerializer<Host> {
    public static final Host$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        Host$$serializer host$$serializer = new Host$$serializer();
        INSTANCE = host$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.adsbynimbus.render.mraid.Host", host$$serializer, 13);
        pluginGeneratedSerialDescriptor.addElement("CurrentAppOrientation", false);
        pluginGeneratedSerialDescriptor.addElement("CurrentPosition", false);
        pluginGeneratedSerialDescriptor.addElement("isViewable", false);
        pluginGeneratedSerialDescriptor.addElement("PlacementType", false);
        pluginGeneratedSerialDescriptor.addElement("MaxSize", false);
        pluginGeneratedSerialDescriptor.addElement("ScreenSize", false);
        pluginGeneratedSerialDescriptor.addElement("OrientationProperties", true);
        pluginGeneratedSerialDescriptor.addElement("ResizeProperties", true);
        pluginGeneratedSerialDescriptor.addElement("DefaultPosition", false);
        pluginGeneratedSerialDescriptor.addElement("State", false);
        pluginGeneratedSerialDescriptor.addElement("ExpandProperties", false);
        pluginGeneratedSerialDescriptor.addElement("supports", false);
        pluginGeneratedSerialDescriptor.addElement("Version", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private Host$$serializer() {
    }

    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{AppOrientation$$serializer.INSTANCE, Position$$serializer.INSTANCE, BooleanSerializer.INSTANCE, StringSerializer.INSTANCE, Size$$serializer.INSTANCE, Size$$serializer.INSTANCE, BuiltinSerializersKt.getNullable(OrientationProperties$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(ResizeProperties$$serializer.INSTANCE), Position$$serializer.INSTANCE, StringSerializer.INSTANCE, ExpandProperties$$serializer.INSTANCE, Host.$childSerializers[11], StringSerializer.INSTANCE};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v8, resolved type: com.adsbynimbus.render.mraid.AppOrientation} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v13, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v7, resolved type: com.adsbynimbus.render.mraid.Position} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v18, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v9, resolved type: com.adsbynimbus.render.mraid.Size} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v21, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v9, resolved type: com.adsbynimbus.render.mraid.Size} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v24, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v9, resolved type: com.adsbynimbus.render.mraid.OrientationProperties} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v27, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v9, resolved type: com.adsbynimbus.render.mraid.ResizeProperties} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v31, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v9, resolved type: com.adsbynimbus.render.mraid.Position} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v37, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v8, resolved type: com.adsbynimbus.render.mraid.ExpandProperties} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v40, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v7, resolved type: java.util.Map} */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0100, code lost:
        r3 = 12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x01b9, code lost:
        r3 = 12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x01c3, code lost:
        r5 = 10;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.adsbynimbus.render.mraid.Host deserialize(kotlinx.serialization.encoding.Decoder r28) {
        /*
            r27 = this;
            r0 = r28
            java.lang.String r1 = "decoder"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            kotlinx.serialization.descriptors.SerialDescriptor r1 = r27.getDescriptor()
            kotlinx.serialization.encoding.CompositeDecoder r0 = r0.beginStructure(r1)
            kotlinx.serialization.KSerializer[] r2 = com.adsbynimbus.render.mraid.Host.$childSerializers
            boolean r3 = r0.decodeSequentially()
            r5 = 10
            r6 = 9
            r7 = 7
            r8 = 6
            r9 = 5
            r10 = 3
            r11 = 8
            r12 = 4
            r13 = 2
            r15 = 1
            r4 = 0
            r14 = 0
            if (r3 == 0) goto L_0x00b1
            com.adsbynimbus.render.mraid.AppOrientation$$serializer r3 = com.adsbynimbus.render.mraid.AppOrientation$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r3 = (kotlinx.serialization.DeserializationStrategy) r3
            java.lang.Object r3 = r0.decodeSerializableElement(r1, r4, r3, r14)
            com.adsbynimbus.render.mraid.AppOrientation r3 = (com.adsbynimbus.render.mraid.AppOrientation) r3
            com.adsbynimbus.render.mraid.Position$$serializer r4 = com.adsbynimbus.render.mraid.Position$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r4 = (kotlinx.serialization.DeserializationStrategy) r4
            java.lang.Object r4 = r0.decodeSerializableElement(r1, r15, r4, r14)
            com.adsbynimbus.render.mraid.Position r4 = (com.adsbynimbus.render.mraid.Position) r4
            boolean r13 = r0.decodeBooleanElement(r1, r13)
            java.lang.String r10 = r0.decodeStringElement(r1, r10)
            com.adsbynimbus.render.mraid.Size$$serializer r15 = com.adsbynimbus.render.mraid.Size$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r15 = (kotlinx.serialization.DeserializationStrategy) r15
            java.lang.Object r12 = r0.decodeSerializableElement(r1, r12, r15, r14)
            com.adsbynimbus.render.mraid.Size r12 = (com.adsbynimbus.render.mraid.Size) r12
            com.adsbynimbus.render.mraid.Size$$serializer r15 = com.adsbynimbus.render.mraid.Size$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r15 = (kotlinx.serialization.DeserializationStrategy) r15
            java.lang.Object r9 = r0.decodeSerializableElement(r1, r9, r15, r14)
            com.adsbynimbus.render.mraid.Size r9 = (com.adsbynimbus.render.mraid.Size) r9
            com.adsbynimbus.render.mraid.OrientationProperties$$serializer r15 = com.adsbynimbus.render.mraid.OrientationProperties$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r15 = (kotlinx.serialization.DeserializationStrategy) r15
            java.lang.Object r8 = r0.decodeNullableSerializableElement(r1, r8, r15, r14)
            com.adsbynimbus.render.mraid.OrientationProperties r8 = (com.adsbynimbus.render.mraid.OrientationProperties) r8
            com.adsbynimbus.render.mraid.ResizeProperties$$serializer r15 = com.adsbynimbus.render.mraid.ResizeProperties$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r15 = (kotlinx.serialization.DeserializationStrategy) r15
            java.lang.Object r7 = r0.decodeNullableSerializableElement(r1, r7, r15, r14)
            com.adsbynimbus.render.mraid.ResizeProperties r7 = (com.adsbynimbus.render.mraid.ResizeProperties) r7
            com.adsbynimbus.render.mraid.Position$$serializer r15 = com.adsbynimbus.render.mraid.Position$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r15 = (kotlinx.serialization.DeserializationStrategy) r15
            java.lang.Object r11 = r0.decodeSerializableElement(r1, r11, r15, r14)
            com.adsbynimbus.render.mraid.Position r11 = (com.adsbynimbus.render.mraid.Position) r11
            java.lang.String r6 = r0.decodeStringElement(r1, r6)
            com.adsbynimbus.render.mraid.ExpandProperties$$serializer r15 = com.adsbynimbus.render.mraid.ExpandProperties$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r15 = (kotlinx.serialization.DeserializationStrategy) r15
            java.lang.Object r5 = r0.decodeSerializableElement(r1, r5, r15, r14)
            com.adsbynimbus.render.mraid.ExpandProperties r5 = (com.adsbynimbus.render.mraid.ExpandProperties) r5
            r15 = 11
            r2 = r2[r15]
            kotlinx.serialization.DeserializationStrategy r2 = (kotlinx.serialization.DeserializationStrategy) r2
            java.lang.Object r2 = r0.decodeSerializableElement(r1, r15, r2, r14)
            java.util.Map r2 = (java.util.Map) r2
            r14 = 12
            java.lang.String r14 = r0.decodeStringElement(r1, r14)
            r15 = 8191(0x1fff, float:1.1478E-41)
            r20 = r2
            r19 = r5
            r18 = r6
            r16 = r7
            r17 = r11
            r11 = r13
            r21 = r14
            r14 = r9
            r13 = r12
            r9 = r3
            r12 = r10
            r10 = r4
            r26 = r15
            r15 = r8
            r8 = r26
            goto L_0x01e0
        L_0x00b1:
            r3 = 12
            r11 = r4
            r7 = r14
            r8 = r7
            r9 = r8
            r10 = r9
            r12 = r10
            r13 = r12
            r17 = r13
            r18 = r17
            r23 = r18
            r24 = r23
            r25 = r15
            r4 = r24
            r15 = r4
            r14 = r11
        L_0x00c8:
            if (r25 == 0) goto L_0x01c9
            int r6 = r0.decodeElementIndex(r1)
            switch(r6) {
                case -1: goto L_0x01bc;
                case 0: goto L_0x01a3;
                case 1: goto L_0x018c;
                case 2: goto L_0x017e;
                case 3: goto L_0x0173;
                case 4: goto L_0x0161;
                case 5: goto L_0x014f;
                case 6: goto L_0x013d;
                case 7: goto L_0x0129;
                case 8: goto L_0x0112;
                case 9: goto L_0x0104;
                case 10: goto L_0x00f1;
                case 11: goto L_0x00df;
                case 12: goto L_0x00d7;
                default: goto L_0x00d1;
            }
        L_0x00d1:
            kotlinx.serialization.UnknownFieldException r0 = new kotlinx.serialization.UnknownFieldException
            r0.<init>((int) r6)
            throw r0
        L_0x00d7:
            java.lang.String r24 = r0.decodeStringElement(r1, r3)
            r11 = r11 | 4096(0x1000, float:5.74E-42)
            goto L_0x01c5
        L_0x00df:
            r6 = 11
            r16 = r2[r6]
            r3 = r16
            kotlinx.serialization.DeserializationStrategy r3 = (kotlinx.serialization.DeserializationStrategy) r3
            java.lang.Object r3 = r0.decodeSerializableElement(r1, r6, r3, r7)
            r7 = r3
            java.util.Map r7 = (java.util.Map) r7
            r11 = r11 | 2048(0x800, float:2.87E-42)
            goto L_0x0100
        L_0x00f1:
            r6 = 11
            com.adsbynimbus.render.mraid.ExpandProperties$$serializer r3 = com.adsbynimbus.render.mraid.ExpandProperties$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r3 = (kotlinx.serialization.DeserializationStrategy) r3
            java.lang.Object r3 = r0.decodeSerializableElement(r1, r5, r3, r8)
            r8 = r3
            com.adsbynimbus.render.mraid.ExpandProperties r8 = (com.adsbynimbus.render.mraid.ExpandProperties) r8
            r11 = r11 | 1024(0x400, float:1.435E-42)
        L_0x0100:
            r3 = 12
            goto L_0x01c5
        L_0x0104:
            r3 = 9
            r6 = 11
            java.lang.String r23 = r0.decodeStringElement(r1, r3)
            r11 = r11 | 512(0x200, float:7.175E-43)
            r6 = r3
            r3 = 12
            goto L_0x00c8
        L_0x0112:
            r3 = 9
            r6 = 11
            com.adsbynimbus.render.mraid.Position$$serializer r16 = com.adsbynimbus.render.mraid.Position$$serializer.INSTANCE
            r3 = r16
            kotlinx.serialization.DeserializationStrategy r3 = (kotlinx.serialization.DeserializationStrategy) r3
            r5 = 8
            java.lang.Object r3 = r0.decodeSerializableElement(r1, r5, r3, r9)
            r9 = r3
            com.adsbynimbus.render.mraid.Position r9 = (com.adsbynimbus.render.mraid.Position) r9
            r11 = r11 | 256(0x100, float:3.59E-43)
            goto L_0x01b9
        L_0x0129:
            r5 = 8
            r6 = 11
            com.adsbynimbus.render.mraid.ResizeProperties$$serializer r3 = com.adsbynimbus.render.mraid.ResizeProperties$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r3 = (kotlinx.serialization.DeserializationStrategy) r3
            r5 = 7
            java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r5, r3, r12)
            r12 = r3
            com.adsbynimbus.render.mraid.ResizeProperties r12 = (com.adsbynimbus.render.mraid.ResizeProperties) r12
            r11 = r11 | 128(0x80, float:1.794E-43)
            goto L_0x01b9
        L_0x013d:
            r5 = 7
            r6 = 11
            com.adsbynimbus.render.mraid.OrientationProperties$$serializer r3 = com.adsbynimbus.render.mraid.OrientationProperties$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r3 = (kotlinx.serialization.DeserializationStrategy) r3
            r5 = 6
            java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r5, r3, r10)
            r10 = r3
            com.adsbynimbus.render.mraid.OrientationProperties r10 = (com.adsbynimbus.render.mraid.OrientationProperties) r10
            r11 = r11 | 64
            goto L_0x01b9
        L_0x014f:
            r5 = 6
            r6 = 11
            com.adsbynimbus.render.mraid.Size$$serializer r3 = com.adsbynimbus.render.mraid.Size$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r3 = (kotlinx.serialization.DeserializationStrategy) r3
            r5 = 5
            java.lang.Object r3 = r0.decodeSerializableElement(r1, r5, r3, r13)
            r13 = r3
            com.adsbynimbus.render.mraid.Size r13 = (com.adsbynimbus.render.mraid.Size) r13
            r11 = r11 | 32
            goto L_0x01b9
        L_0x0161:
            r5 = 5
            r6 = 11
            com.adsbynimbus.render.mraid.Size$$serializer r3 = com.adsbynimbus.render.mraid.Size$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r3 = (kotlinx.serialization.DeserializationStrategy) r3
            r5 = 4
            java.lang.Object r3 = r0.decodeSerializableElement(r1, r5, r3, r15)
            r15 = r3
            com.adsbynimbus.render.mraid.Size r15 = (com.adsbynimbus.render.mraid.Size) r15
            r11 = r11 | 16
            goto L_0x01b9
        L_0x0173:
            r3 = 3
            r5 = 4
            r6 = 11
            java.lang.String r18 = r0.decodeStringElement(r1, r3)
            r11 = r11 | 8
            goto L_0x01b9
        L_0x017e:
            r3 = 3
            r5 = 4
            r6 = 11
            r14 = 2
            boolean r20 = r0.decodeBooleanElement(r1, r14)
            r11 = r11 | 4
            r14 = r20
            goto L_0x01b9
        L_0x018c:
            r3 = 3
            r5 = 4
            r6 = 11
            r20 = 2
            com.adsbynimbus.render.mraid.Position$$serializer r21 = com.adsbynimbus.render.mraid.Position$$serializer.INSTANCE
            r3 = r21
            kotlinx.serialization.DeserializationStrategy r3 = (kotlinx.serialization.DeserializationStrategy) r3
            r5 = 1
            java.lang.Object r3 = r0.decodeSerializableElement(r1, r5, r3, r4)
            r4 = r3
            com.adsbynimbus.render.mraid.Position r4 = (com.adsbynimbus.render.mraid.Position) r4
            r11 = r11 | 2
            goto L_0x01b9
        L_0x01a3:
            r5 = 1
            r6 = 11
            r20 = 2
            com.adsbynimbus.render.mraid.AppOrientation$$serializer r3 = com.adsbynimbus.render.mraid.AppOrientation$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r3 = (kotlinx.serialization.DeserializationStrategy) r3
            r5 = r17
            r6 = 0
            java.lang.Object r3 = r0.decodeSerializableElement(r1, r6, r3, r5)
            r17 = r3
            com.adsbynimbus.render.mraid.AppOrientation r17 = (com.adsbynimbus.render.mraid.AppOrientation) r17
            r11 = r11 | 1
        L_0x01b9:
            r3 = 12
            goto L_0x01c3
        L_0x01bc:
            r5 = r17
            r6 = 0
            r20 = 2
            r25 = r6
        L_0x01c3:
            r5 = 10
        L_0x01c5:
            r6 = 9
            goto L_0x00c8
        L_0x01c9:
            r5 = r17
            r20 = r7
            r19 = r8
            r17 = r9
            r8 = r11
            r16 = r12
            r11 = r14
            r12 = r18
            r18 = r23
            r21 = r24
            r9 = r5
            r14 = r13
            r13 = r15
            r15 = r10
            r10 = r4
        L_0x01e0:
            r0.endStructure(r1)
            com.adsbynimbus.render.mraid.Host r0 = new com.adsbynimbus.render.mraid.Host
            r22 = 0
            r7 = r0
            r7.<init>((int) r8, (com.adsbynimbus.render.mraid.AppOrientation) r9, (com.adsbynimbus.render.mraid.Position) r10, (boolean) r11, (java.lang.String) r12, (com.adsbynimbus.render.mraid.Size) r13, (com.adsbynimbus.render.mraid.Size) r14, (com.adsbynimbus.render.mraid.OrientationProperties) r15, (com.adsbynimbus.render.mraid.ResizeProperties) r16, (com.adsbynimbus.render.mraid.Position) r17, (java.lang.String) r18, (com.adsbynimbus.render.mraid.ExpandProperties) r19, (java.util.Map) r20, (java.lang.String) r21, (kotlinx.serialization.internal.SerializationConstructorMarker) r22)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.render.mraid.Host$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.adsbynimbus.render.mraid.Host");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, Host host) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(host, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder beginStructure = encoder.beginStructure(descriptor2);
        Host.write$Self$static_release(host, beginStructure, descriptor2);
        beginStructure.endStructure(descriptor2);
    }

    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}

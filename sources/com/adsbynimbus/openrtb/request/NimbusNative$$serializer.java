package com.adsbynimbus.openrtb.request;

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
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/adsbynimbus/openrtb/request/NimbusNative.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/adsbynimbus/openrtb/request/NimbusNative;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* compiled from: Native.kt */
public final class NimbusNative$$serializer implements GeneratedSerializer<NimbusNative> {
    public static final NimbusNative$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        NimbusNative$$serializer nimbusNative$$serializer = new NimbusNative$$serializer();
        INSTANCE = nimbusNative$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.adsbynimbus.openrtb.request.NimbusNative", nimbusNative$$serializer, 5);
        pluginGeneratedSerialDescriptor.addElement("ver", true);
        pluginGeneratedSerialDescriptor.addElement("plcmttype", false);
        pluginGeneratedSerialDescriptor.addElement("context", false);
        pluginGeneratedSerialDescriptor.addElement("contextsubtype", false);
        pluginGeneratedSerialDescriptor.addElement("assets", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private NimbusNative$$serializer() {
    }

    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{StringSerializer.INSTANCE, BuiltinSerializersKt.getNullable(ByteSerializer.INSTANCE), BuiltinSerializersKt.getNullable(ByteSerializer.INSTANCE), BuiltinSerializersKt.getNullable(ByteSerializer.INSTANCE), NimbusNative.$childSerializers[4]};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v4, resolved type: java.lang.Byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v4, resolved type: java.lang.Byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v13, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v4, resolved type: java.lang.Byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v16, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v4, resolved type: java.util.List} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.adsbynimbus.openrtb.request.NimbusNative deserialize(kotlinx.serialization.encoding.Decoder r24) {
        /*
            r23 = this;
            r0 = r24
            java.lang.String r1 = "decoder"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            kotlinx.serialization.descriptors.SerialDescriptor r1 = r23.getDescriptor()
            kotlinx.serialization.encoding.CompositeDecoder r0 = r0.beginStructure(r1)
            kotlinx.serialization.KSerializer[] r2 = com.adsbynimbus.openrtb.request.NimbusNative.$childSerializers
            boolean r3 = r0.decodeSequentially()
            r4 = 3
            r5 = 2
            r6 = 4
            r7 = 1
            r8 = 0
            r9 = 0
            if (r3 == 0) goto L_0x005b
            java.lang.String r3 = r0.decodeStringElement(r1, r8)
            kotlinx.serialization.internal.ByteSerializer r8 = kotlinx.serialization.internal.ByteSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r8 = (kotlinx.serialization.DeserializationStrategy) r8
            java.lang.Object r7 = r0.decodeNullableSerializableElement(r1, r7, r8, r9)
            java.lang.Byte r7 = (java.lang.Byte) r7
            kotlinx.serialization.internal.ByteSerializer r8 = kotlinx.serialization.internal.ByteSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r8 = (kotlinx.serialization.DeserializationStrategy) r8
            java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r5, r8, r9)
            java.lang.Byte r5 = (java.lang.Byte) r5
            kotlinx.serialization.internal.ByteSerializer r8 = kotlinx.serialization.internal.ByteSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r8 = (kotlinx.serialization.DeserializationStrategy) r8
            java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r4, r8, r9)
            java.lang.Byte r4 = (java.lang.Byte) r4
            r2 = r2[r6]
            kotlinx.serialization.DeserializationStrategy r2 = (kotlinx.serialization.DeserializationStrategy) r2
            java.lang.Object r2 = r0.decodeSerializableElement(r1, r6, r2, r9)
            java.util.List r2 = (java.util.List) r2
            r6 = 31
            r21 = r2
            r17 = r3
            r20 = r4
            r19 = r5
            r16 = r6
            r18 = r7
            goto L_0x00ca
        L_0x005b:
            r14 = r7
            r3 = r8
            r10 = r9
            r11 = r10
            r12 = r11
            r13 = r12
        L_0x0061:
            if (r14 == 0) goto L_0x00be
            int r15 = r0.decodeElementIndex(r1)
            r8 = -1
            if (r15 == r8) goto L_0x00bb
            if (r15 == 0) goto L_0x00b3
            if (r15 == r7) goto L_0x00a4
            if (r15 == r5) goto L_0x0096
            if (r15 == r4) goto L_0x0088
            if (r15 != r6) goto L_0x0082
            r8 = r2[r6]
            kotlinx.serialization.DeserializationStrategy r8 = (kotlinx.serialization.DeserializationStrategy) r8
            java.lang.Object r8 = r0.decodeSerializableElement(r1, r6, r8, r13)
            r13 = r8
            java.util.List r13 = (java.util.List) r13
            r3 = r3 | 16
            goto L_0x00b1
        L_0x0082:
            kotlinx.serialization.UnknownFieldException r0 = new kotlinx.serialization.UnknownFieldException
            r0.<init>((int) r15)
            throw r0
        L_0x0088:
            kotlinx.serialization.internal.ByteSerializer r8 = kotlinx.serialization.internal.ByteSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r8 = (kotlinx.serialization.DeserializationStrategy) r8
            java.lang.Object r8 = r0.decodeNullableSerializableElement(r1, r4, r8, r12)
            r12 = r8
            java.lang.Byte r12 = (java.lang.Byte) r12
            r3 = r3 | 8
            goto L_0x00b1
        L_0x0096:
            kotlinx.serialization.internal.ByteSerializer r8 = kotlinx.serialization.internal.ByteSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r8 = (kotlinx.serialization.DeserializationStrategy) r8
            java.lang.Object r8 = r0.decodeNullableSerializableElement(r1, r5, r8, r11)
            r11 = r8
            java.lang.Byte r11 = (java.lang.Byte) r11
            r3 = r3 | 4
            goto L_0x00b1
        L_0x00a4:
            kotlinx.serialization.internal.ByteSerializer r8 = kotlinx.serialization.internal.ByteSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r8 = (kotlinx.serialization.DeserializationStrategy) r8
            java.lang.Object r8 = r0.decodeNullableSerializableElement(r1, r7, r8, r10)
            r10 = r8
            java.lang.Byte r10 = (java.lang.Byte) r10
            r3 = r3 | 2
        L_0x00b1:
            r8 = 0
            goto L_0x0061
        L_0x00b3:
            r8 = 0
            java.lang.String r9 = r0.decodeStringElement(r1, r8)
            r3 = r3 | 1
            goto L_0x0061
        L_0x00bb:
            r8 = 0
            r14 = r8
            goto L_0x0061
        L_0x00be:
            r16 = r3
            r17 = r9
            r18 = r10
            r19 = r11
            r20 = r12
            r21 = r13
        L_0x00ca:
            r0.endStructure(r1)
            com.adsbynimbus.openrtb.request.NimbusNative r0 = new com.adsbynimbus.openrtb.request.NimbusNative
            r22 = 0
            r15 = r0
            r15.<init>((int) r16, (java.lang.String) r17, (java.lang.Byte) r18, (java.lang.Byte) r19, (java.lang.Byte) r20, (java.util.List) r21, (kotlinx.serialization.internal.SerializationConstructorMarker) r22)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.openrtb.request.NimbusNative$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.adsbynimbus.openrtb.request.NimbusNative");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, NimbusNative nimbusNative) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(nimbusNative, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder beginStructure = encoder.beginStructure(descriptor2);
        NimbusNative.write$Self$kotlin_release(nimbusNative, beginStructure, descriptor2);
        beginStructure.endStructure(descriptor2);
    }

    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}

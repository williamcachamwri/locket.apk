package com.adsbynimbus.openrtb.request;

import com.amplitude.api.Constants;
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

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/adsbynimbus/openrtb/request/Geo.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/adsbynimbus/openrtb/request/Geo;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* compiled from: Geo.kt */
public final class Geo$$serializer implements GeneratedSerializer<Geo> {
    public static final Geo$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        Geo$$serializer geo$$serializer = new Geo$$serializer();
        INSTANCE = geo$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.adsbynimbus.openrtb.request.Geo", geo$$serializer, 8);
        pluginGeneratedSerialDescriptor.addElement("lat", true);
        pluginGeneratedSerialDescriptor.addElement("lon", true);
        pluginGeneratedSerialDescriptor.addElement("type", true);
        pluginGeneratedSerialDescriptor.addElement("accuracy", true);
        pluginGeneratedSerialDescriptor.addElement(Constants.AMP_TRACKING_OPTION_COUNTRY, true);
        pluginGeneratedSerialDescriptor.addElement("city", true);
        pluginGeneratedSerialDescriptor.addElement("metro", true);
        pluginGeneratedSerialDescriptor.addElement("state", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private Geo$$serializer() {
    }

    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{BuiltinSerializersKt.getNullable(FloatSerializer.INSTANCE), BuiltinSerializersKt.getNullable(FloatSerializer.INSTANCE), BuiltinSerializersKt.getNullable(ByteSerializer.INSTANCE), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE)};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v7, resolved type: java.lang.Float} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v7, resolved type: java.lang.Byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v14, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v5, resolved type: java.lang.Integer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v18, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v6, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v21, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v7, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v24, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v7, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v27, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v7, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.adsbynimbus.openrtb.request.Geo deserialize(kotlinx.serialization.encoding.Decoder r22) {
        /*
            r21 = this;
            r0 = r22
            java.lang.String r1 = "decoder"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            kotlinx.serialization.descriptors.SerialDescriptor r1 = r21.getDescriptor()
            kotlinx.serialization.encoding.CompositeDecoder r0 = r0.beginStructure(r1)
            boolean r2 = r0.decodeSequentially()
            r3 = 7
            r4 = 6
            r5 = 5
            r6 = 3
            r7 = 4
            r8 = 2
            r9 = 1
            r10 = 0
            r11 = 0
            if (r2 == 0) goto L_0x007c
            kotlinx.serialization.internal.FloatSerializer r2 = kotlinx.serialization.internal.FloatSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r2 = (kotlinx.serialization.DeserializationStrategy) r2
            java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r10, r2, r11)
            java.lang.Float r2 = (java.lang.Float) r2
            kotlinx.serialization.internal.FloatSerializer r10 = kotlinx.serialization.internal.FloatSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r10 = (kotlinx.serialization.DeserializationStrategy) r10
            java.lang.Object r9 = r0.decodeNullableSerializableElement(r1, r9, r10, r11)
            java.lang.Float r9 = (java.lang.Float) r9
            kotlinx.serialization.internal.ByteSerializer r10 = kotlinx.serialization.internal.ByteSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r10 = (kotlinx.serialization.DeserializationStrategy) r10
            java.lang.Object r8 = r0.decodeNullableSerializableElement(r1, r8, r10, r11)
            java.lang.Byte r8 = (java.lang.Byte) r8
            kotlinx.serialization.internal.IntSerializer r10 = kotlinx.serialization.internal.IntSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r10 = (kotlinx.serialization.DeserializationStrategy) r10
            java.lang.Object r6 = r0.decodeNullableSerializableElement(r1, r6, r10, r11)
            java.lang.Integer r6 = (java.lang.Integer) r6
            kotlinx.serialization.internal.StringSerializer r10 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r10 = (kotlinx.serialization.DeserializationStrategy) r10
            java.lang.Object r7 = r0.decodeNullableSerializableElement(r1, r7, r10, r11)
            java.lang.String r7 = (java.lang.String) r7
            kotlinx.serialization.internal.StringSerializer r10 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r10 = (kotlinx.serialization.DeserializationStrategy) r10
            java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r5, r10, r11)
            java.lang.String r5 = (java.lang.String) r5
            kotlinx.serialization.internal.StringSerializer r10 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r10 = (kotlinx.serialization.DeserializationStrategy) r10
            java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r4, r10, r11)
            java.lang.String r4 = (java.lang.String) r4
            kotlinx.serialization.internal.StringSerializer r10 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r10 = (kotlinx.serialization.DeserializationStrategy) r10
            java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r3, r10, r11)
            java.lang.String r3 = (java.lang.String) r3
            r10 = 255(0xff, float:3.57E-43)
            r16 = r3
            r15 = r4
            r14 = r5
            r12 = r6
            r13 = r7
            r11 = r8
            r8 = r10
            r10 = r9
            r9 = r2
            goto L_0x0126
        L_0x007c:
            r18 = r9
            r2 = r10
            r8 = r11
            r9 = r8
            r10 = r9
            r12 = r10
            r13 = r12
            r14 = r13
            r15 = r14
        L_0x0086:
            if (r18 == 0) goto L_0x0116
            int r6 = r0.decodeElementIndex(r1)
            switch(r6) {
                case -1: goto L_0x010f;
                case 0: goto L_0x00ff;
                case 1: goto L_0x00ef;
                case 2: goto L_0x00df;
                case 3: goto L_0x00ce;
                case 4: goto L_0x00c0;
                case 5: goto L_0x00b2;
                case 6: goto L_0x00a4;
                case 7: goto L_0x0095;
                default: goto L_0x008f;
            }
        L_0x008f:
            kotlinx.serialization.UnknownFieldException r0 = new kotlinx.serialization.UnknownFieldException
            r0.<init>((int) r6)
            throw r0
        L_0x0095:
            kotlinx.serialization.internal.StringSerializer r6 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r6 = (kotlinx.serialization.DeserializationStrategy) r6
            java.lang.Object r6 = r0.decodeNullableSerializableElement(r1, r3, r6, r8)
            r8 = r6
            java.lang.String r8 = (java.lang.String) r8
            r2 = r2 | 128(0x80, float:1.794E-43)
            goto L_0x0113
        L_0x00a4:
            kotlinx.serialization.internal.StringSerializer r6 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r6 = (kotlinx.serialization.DeserializationStrategy) r6
            java.lang.Object r6 = r0.decodeNullableSerializableElement(r1, r4, r6, r9)
            r9 = r6
            java.lang.String r9 = (java.lang.String) r9
            r2 = r2 | 64
            goto L_0x0113
        L_0x00b2:
            kotlinx.serialization.internal.StringSerializer r6 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r6 = (kotlinx.serialization.DeserializationStrategy) r6
            java.lang.Object r6 = r0.decodeNullableSerializableElement(r1, r5, r6, r10)
            r10 = r6
            java.lang.String r10 = (java.lang.String) r10
            r2 = r2 | 32
            goto L_0x0113
        L_0x00c0:
            kotlinx.serialization.internal.StringSerializer r6 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r6 = (kotlinx.serialization.DeserializationStrategy) r6
            java.lang.Object r6 = r0.decodeNullableSerializableElement(r1, r7, r6, r15)
            r15 = r6
            java.lang.String r15 = (java.lang.String) r15
            r2 = r2 | 16
            goto L_0x0113
        L_0x00ce:
            kotlinx.serialization.internal.IntSerializer r6 = kotlinx.serialization.internal.IntSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r6 = (kotlinx.serialization.DeserializationStrategy) r6
            r3 = 3
            java.lang.Object r6 = r0.decodeNullableSerializableElement(r1, r3, r6, r14)
            r14 = r6
            java.lang.Integer r14 = (java.lang.Integer) r14
            r2 = r2 | 8
            r6 = r3
            r3 = 7
            goto L_0x0086
        L_0x00df:
            r3 = 3
            kotlinx.serialization.internal.ByteSerializer r6 = kotlinx.serialization.internal.ByteSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r6 = (kotlinx.serialization.DeserializationStrategy) r6
            r3 = 2
            java.lang.Object r6 = r0.decodeNullableSerializableElement(r1, r3, r6, r13)
            r13 = r6
            java.lang.Byte r13 = (java.lang.Byte) r13
            r2 = r2 | 4
            goto L_0x0112
        L_0x00ef:
            r3 = 2
            kotlinx.serialization.internal.FloatSerializer r6 = kotlinx.serialization.internal.FloatSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r6 = (kotlinx.serialization.DeserializationStrategy) r6
            r3 = 1
            java.lang.Object r6 = r0.decodeNullableSerializableElement(r1, r3, r6, r12)
            r12 = r6
            java.lang.Float r12 = (java.lang.Float) r12
            r2 = r2 | 2
            goto L_0x0112
        L_0x00ff:
            r3 = 1
            kotlinx.serialization.internal.FloatSerializer r6 = kotlinx.serialization.internal.FloatSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r6 = (kotlinx.serialization.DeserializationStrategy) r6
            r3 = 0
            java.lang.Object r6 = r0.decodeNullableSerializableElement(r1, r3, r6, r11)
            r11 = r6
            java.lang.Float r11 = (java.lang.Float) r11
            r2 = r2 | 1
            goto L_0x0112
        L_0x010f:
            r3 = 0
            r18 = r3
        L_0x0112:
            r3 = 7
        L_0x0113:
            r6 = 3
            goto L_0x0086
        L_0x0116:
            r16 = r8
            r8 = r2
            r19 = r15
            r15 = r9
            r9 = r11
            r11 = r13
            r13 = r19
            r20 = r14
            r14 = r10
            r10 = r12
            r12 = r20
        L_0x0126:
            r0.endStructure(r1)
            com.adsbynimbus.openrtb.request.Geo r0 = new com.adsbynimbus.openrtb.request.Geo
            r17 = 0
            r7 = r0
            r7.<init>((int) r8, (java.lang.Float) r9, (java.lang.Float) r10, (java.lang.Byte) r11, (java.lang.Integer) r12, (java.lang.String) r13, (java.lang.String) r14, (java.lang.String) r15, (java.lang.String) r16, (kotlinx.serialization.internal.SerializationConstructorMarker) r17)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.openrtb.request.Geo$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.adsbynimbus.openrtb.request.Geo");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, Geo geo) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(geo, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder beginStructure = encoder.beginStructure(descriptor2);
        Geo.write$Self$kotlin_release(geo, beginStructure, descriptor2);
        beginStructure.endStructure(descriptor2);
    }

    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}

package com.adsbynimbus.openrtb.request;

import com.google.firebase.dynamiclinks.DynamicLink;
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

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/adsbynimbus/openrtb/request/App.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/adsbynimbus/openrtb/request/App;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* compiled from: App.kt */
public final class App$$serializer implements GeneratedSerializer<App> {
    public static final App$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        App$$serializer app$$serializer = new App$$serializer();
        INSTANCE = app$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.adsbynimbus.openrtb.request.App", app$$serializer, 12);
        pluginGeneratedSerialDescriptor.addElement("name", true);
        pluginGeneratedSerialDescriptor.addElement("bundle", true);
        pluginGeneratedSerialDescriptor.addElement(DynamicLink.Builder.KEY_DOMAIN, true);
        pluginGeneratedSerialDescriptor.addElement("storeurl", true);
        pluginGeneratedSerialDescriptor.addElement("ver", true);
        pluginGeneratedSerialDescriptor.addElement("keywords", true);
        pluginGeneratedSerialDescriptor.addElement("cat", true);
        pluginGeneratedSerialDescriptor.addElement("sectioncat", true);
        pluginGeneratedSerialDescriptor.addElement("pagecat", true);
        pluginGeneratedSerialDescriptor.addElement("privacypolicy", true);
        pluginGeneratedSerialDescriptor.addElement("paid", true);
        pluginGeneratedSerialDescriptor.addElement("publisher", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private App$$serializer() {
    }

    public KSerializer<?>[] childSerializers() {
        KSerializer[] access$get$childSerializers$cp = App.$childSerializers;
        return new KSerializer[]{BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(access$get$childSerializers$cp[6]), BuiltinSerializersKt.getNullable(access$get$childSerializers$cp[7]), BuiltinSerializersKt.getNullable(access$get$childSerializers$cp[8]), BuiltinSerializersKt.getNullable(ByteSerializer.INSTANCE), BuiltinSerializersKt.getNullable(ByteSerializer.INSTANCE), BuiltinSerializersKt.getNullable(Publisher$$serializer.INSTANCE)};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v13, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v8, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v16, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v8, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v19, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v8, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v22, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v8, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v25, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v8, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v29, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v8, resolved type: java.lang.String[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v24, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v8, resolved type: java.lang.String[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v34, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v8, resolved type: java.lang.String[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v28, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v8, resolved type: java.lang.Byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v32, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v8, resolved type: java.lang.Byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v32, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v38, resolved type: com.adsbynimbus.openrtb.request.Publisher} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.adsbynimbus.openrtb.request.App deserialize(kotlinx.serialization.encoding.Decoder r28) {
        /*
            r27 = this;
            r0 = r28
            java.lang.String r1 = "decoder"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            kotlinx.serialization.descriptors.SerialDescriptor r1 = r27.getDescriptor()
            kotlinx.serialization.encoding.CompositeDecoder r0 = r0.beginStructure(r1)
            kotlinx.serialization.KSerializer[] r2 = com.adsbynimbus.openrtb.request.App.$childSerializers
            boolean r3 = r0.decodeSequentially()
            r5 = 10
            r6 = 9
            r7 = 5
            r8 = 3
            r9 = 4
            r10 = 2
            r11 = 7
            r12 = 6
            r13 = 8
            r14 = 1
            r15 = 0
            r4 = 0
            if (r3 == 0) goto L_0x00b7
            kotlinx.serialization.internal.StringSerializer r3 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r3 = (kotlinx.serialization.DeserializationStrategy) r3
            java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r15, r3, r4)
            java.lang.String r3 = (java.lang.String) r3
            kotlinx.serialization.internal.StringSerializer r15 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r15 = (kotlinx.serialization.DeserializationStrategy) r15
            java.lang.Object r14 = r0.decodeNullableSerializableElement(r1, r14, r15, r4)
            java.lang.String r14 = (java.lang.String) r14
            kotlinx.serialization.internal.StringSerializer r15 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r15 = (kotlinx.serialization.DeserializationStrategy) r15
            java.lang.Object r10 = r0.decodeNullableSerializableElement(r1, r10, r15, r4)
            java.lang.String r10 = (java.lang.String) r10
            kotlinx.serialization.internal.StringSerializer r15 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r15 = (kotlinx.serialization.DeserializationStrategy) r15
            java.lang.Object r8 = r0.decodeNullableSerializableElement(r1, r8, r15, r4)
            java.lang.String r8 = (java.lang.String) r8
            kotlinx.serialization.internal.StringSerializer r15 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r15 = (kotlinx.serialization.DeserializationStrategy) r15
            java.lang.Object r9 = r0.decodeNullableSerializableElement(r1, r9, r15, r4)
            java.lang.String r9 = (java.lang.String) r9
            kotlinx.serialization.internal.StringSerializer r15 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r15 = (kotlinx.serialization.DeserializationStrategy) r15
            java.lang.Object r7 = r0.decodeNullableSerializableElement(r1, r7, r15, r4)
            java.lang.String r7 = (java.lang.String) r7
            r15 = r2[r12]
            kotlinx.serialization.DeserializationStrategy r15 = (kotlinx.serialization.DeserializationStrategy) r15
            java.lang.Object r12 = r0.decodeNullableSerializableElement(r1, r12, r15, r4)
            java.lang.String[] r12 = (java.lang.String[]) r12
            r15 = r2[r11]
            kotlinx.serialization.DeserializationStrategy r15 = (kotlinx.serialization.DeserializationStrategy) r15
            java.lang.Object r11 = r0.decodeNullableSerializableElement(r1, r11, r15, r4)
            java.lang.String[] r11 = (java.lang.String[]) r11
            r2 = r2[r13]
            kotlinx.serialization.DeserializationStrategy r2 = (kotlinx.serialization.DeserializationStrategy) r2
            java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r13, r2, r4)
            java.lang.String[] r2 = (java.lang.String[]) r2
            kotlinx.serialization.internal.ByteSerializer r13 = kotlinx.serialization.internal.ByteSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r13 = (kotlinx.serialization.DeserializationStrategy) r13
            java.lang.Object r6 = r0.decodeNullableSerializableElement(r1, r6, r13, r4)
            java.lang.Byte r6 = (java.lang.Byte) r6
            kotlinx.serialization.internal.ByteSerializer r13 = kotlinx.serialization.internal.ByteSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r13 = (kotlinx.serialization.DeserializationStrategy) r13
            java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r5, r13, r4)
            java.lang.Byte r5 = (java.lang.Byte) r5
            com.adsbynimbus.openrtb.request.Publisher$$serializer r13 = com.adsbynimbus.openrtb.request.Publisher$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r13 = (kotlinx.serialization.DeserializationStrategy) r13
            r15 = 11
            java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r15, r13, r4)
            com.adsbynimbus.openrtb.request.Publisher r4 = (com.adsbynimbus.openrtb.request.Publisher) r4
            r13 = 4095(0xfff, float:5.738E-42)
            r17 = r4
            r16 = r5
            r15 = r6
            r5 = r13
            r6 = r3
            r13 = r11
            r11 = r7
            r7 = r14
            r14 = r2
            r25 = r9
            r9 = r8
            r8 = r10
            r10 = r25
            goto L_0x022b
        L_0x00b7:
            r3 = 11
            r5 = r4
            r6 = r5
            r7 = r6
            r8 = r7
            r9 = r8
            r10 = r9
            r11 = r10
            r12 = r11
            r13 = r12
            r23 = r14
            r22 = r15
            r14 = r13
            r15 = r14
        L_0x00c8:
            if (r23 == 0) goto L_0x0212
            int r3 = r0.decodeElementIndex(r1)
            switch(r3) {
                case -1: goto L_0x0201;
                case 0: goto L_0x01e4;
                case 1: goto L_0x01ca;
                case 2: goto L_0x01b4;
                case 3: goto L_0x019e;
                case 4: goto L_0x0188;
                case 5: goto L_0x0172;
                case 6: goto L_0x0159;
                case 7: goto L_0x013f;
                case 8: goto L_0x0124;
                case 9: goto L_0x010b;
                case 10: goto L_0x00f0;
                case 11: goto L_0x00d7;
                default: goto L_0x00d1;
            }
        L_0x00d1:
            kotlinx.serialization.UnknownFieldException r0 = new kotlinx.serialization.UnknownFieldException
            r0.<init>((int) r3)
            throw r0
        L_0x00d7:
            com.adsbynimbus.openrtb.request.Publisher$$serializer r3 = com.adsbynimbus.openrtb.request.Publisher$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r3 = (kotlinx.serialization.DeserializationStrategy) r3
            r24 = r4
            r4 = 11
            java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r4, r3, r5)
            r5 = r3
            com.adsbynimbus.openrtb.request.Publisher r5 = (com.adsbynimbus.openrtb.request.Publisher) r5
            r3 = r22
            r3 = r3 | 2048(0x800, float:2.87E-42)
            r22 = r3
            r3 = r4
            r4 = r24
            goto L_0x00c8
        L_0x00f0:
            r24 = r4
            r3 = r22
            r4 = 11
            kotlinx.serialization.internal.ByteSerializer r22 = kotlinx.serialization.internal.ByteSerializer.INSTANCE
            r4 = r22
            kotlinx.serialization.DeserializationStrategy r4 = (kotlinx.serialization.DeserializationStrategy) r4
            r22 = r5
            r5 = 10
            java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r5, r4, r6)
            r6 = r4
            java.lang.Byte r6 = (java.lang.Byte) r6
            r3 = r3 | 1024(0x400, float:1.435E-42)
            goto L_0x01df
        L_0x010b:
            r24 = r4
            r3 = r22
            r22 = r5
            r5 = 10
            kotlinx.serialization.internal.ByteSerializer r4 = kotlinx.serialization.internal.ByteSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r4 = (kotlinx.serialization.DeserializationStrategy) r4
            r5 = 9
            java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r5, r4, r13)
            r13 = r4
            java.lang.Byte r13 = (java.lang.Byte) r13
            r3 = r3 | 512(0x200, float:7.175E-43)
            goto L_0x01df
        L_0x0124:
            r24 = r4
            r3 = r22
            r4 = 8
            r22 = r5
            r5 = 9
            r21 = r2[r4]
            r5 = r21
            kotlinx.serialization.DeserializationStrategy r5 = (kotlinx.serialization.DeserializationStrategy) r5
            java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r4, r5, r11)
            r11 = r5
            java.lang.String[] r11 = (java.lang.String[]) r11
            r3 = r3 | 256(0x100, float:3.59E-43)
            goto L_0x01df
        L_0x013f:
            r24 = r4
            r3 = r22
            r4 = 8
            r22 = r5
            r5 = 7
            r20 = r2[r5]
            r4 = r20
            kotlinx.serialization.DeserializationStrategy r4 = (kotlinx.serialization.DeserializationStrategy) r4
            java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r5, r4, r12)
            r12 = r4
            java.lang.String[] r12 = (java.lang.String[]) r12
            r3 = r3 | 128(0x80, float:1.794E-43)
            goto L_0x01df
        L_0x0159:
            r24 = r4
            r3 = r22
            r4 = 6
            r22 = r5
            r5 = 7
            r19 = r2[r4]
            r5 = r19
            kotlinx.serialization.DeserializationStrategy r5 = (kotlinx.serialization.DeserializationStrategy) r5
            java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r4, r5, r7)
            r7 = r5
            java.lang.String[] r7 = (java.lang.String[]) r7
            r3 = r3 | 64
            goto L_0x01df
        L_0x0172:
            r24 = r4
            r3 = r22
            r4 = 6
            r22 = r5
            kotlinx.serialization.internal.StringSerializer r5 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r5 = (kotlinx.serialization.DeserializationStrategy) r5
            r4 = 5
            java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r4, r5, r9)
            r9 = r5
            java.lang.String r9 = (java.lang.String) r9
            r3 = r3 | 32
            goto L_0x01df
        L_0x0188:
            r24 = r4
            r3 = r22
            r4 = 5
            r22 = r5
            kotlinx.serialization.internal.StringSerializer r5 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r5 = (kotlinx.serialization.DeserializationStrategy) r5
            r4 = 4
            java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r4, r5, r8)
            r8 = r5
            java.lang.String r8 = (java.lang.String) r8
            r3 = r3 | 16
            goto L_0x01df
        L_0x019e:
            r24 = r4
            r3 = r22
            r4 = 4
            r22 = r5
            kotlinx.serialization.internal.StringSerializer r5 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r5 = (kotlinx.serialization.DeserializationStrategy) r5
            r4 = 3
            java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r4, r5, r10)
            r10 = r5
            java.lang.String r10 = (java.lang.String) r10
            r3 = r3 | 8
            goto L_0x01df
        L_0x01b4:
            r24 = r4
            r3 = r22
            r4 = 3
            r22 = r5
            kotlinx.serialization.internal.StringSerializer r5 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r5 = (kotlinx.serialization.DeserializationStrategy) r5
            r4 = 2
            java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r4, r5, r14)
            r14 = r5
            java.lang.String r14 = (java.lang.String) r14
            r3 = r3 | 4
            goto L_0x01df
        L_0x01ca:
            r24 = r4
            r3 = r22
            r4 = 2
            r22 = r5
            kotlinx.serialization.internal.StringSerializer r5 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r5 = (kotlinx.serialization.DeserializationStrategy) r5
            r4 = 1
            java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r4, r5, r15)
            r15 = r5
            java.lang.String r15 = (java.lang.String) r15
            r3 = r3 | 2
        L_0x01df:
            r5 = r22
            r4 = r24
            goto L_0x020c
        L_0x01e4:
            r24 = r4
            r3 = r22
            r4 = 1
            r22 = r5
            kotlinx.serialization.internal.StringSerializer r5 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r5 = (kotlinx.serialization.DeserializationStrategy) r5
            r28 = r2
            r4 = r24
            r2 = 0
            java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r2, r5, r4)
            java.lang.String r4 = (java.lang.String) r4
            r3 = r3 | 1
            r2 = r28
            r5 = r22
            goto L_0x020c
        L_0x0201:
            r28 = r2
            r3 = r22
            r2 = 0
            r22 = r5
            r23 = r2
            r2 = r28
        L_0x020c:
            r22 = r3
            r3 = 11
            goto L_0x00c8
        L_0x0212:
            r3 = r22
            r22 = r5
            r5 = r3
            r16 = r6
            r17 = r22
            r6 = r4
            r25 = r12
            r12 = r7
            r7 = r15
            r15 = r13
            r13 = r25
            r26 = r10
            r10 = r8
            r8 = r14
            r14 = r11
            r11 = r9
            r9 = r26
        L_0x022b:
            r0.endStructure(r1)
            com.adsbynimbus.openrtb.request.App r0 = new com.adsbynimbus.openrtb.request.App
            r18 = 0
            r4 = r0
            r4.<init>((int) r5, (java.lang.String) r6, (java.lang.String) r7, (java.lang.String) r8, (java.lang.String) r9, (java.lang.String) r10, (java.lang.String) r11, (java.lang.String[]) r12, (java.lang.String[]) r13, (java.lang.String[]) r14, (java.lang.Byte) r15, (java.lang.Byte) r16, (com.adsbynimbus.openrtb.request.Publisher) r17, (kotlinx.serialization.internal.SerializationConstructorMarker) r18)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.openrtb.request.App$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.adsbynimbus.openrtb.request.App");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, App app) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(app, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder beginStructure = encoder.beginStructure(descriptor2);
        App.write$Self$kotlin_release(app, beginStructure, descriptor2);
        beginStructure.endStructure(descriptor2);
    }

    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}

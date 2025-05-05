package com.adsbynimbus.openrtb.request;

import com.adsbynimbus.openrtb.request.User;
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
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/adsbynimbus/openrtb/request/User.Extension.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/adsbynimbus/openrtb/request/User$Extension;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* compiled from: User.kt */
public final class User$Extension$$serializer implements GeneratedSerializer<User.Extension> {
    public static final User$Extension$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        User$Extension$$serializer user$Extension$$serializer = new User$Extension$$serializer();
        INSTANCE = user$Extension$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.adsbynimbus.openrtb.request.User.Extension", user$Extension$$serializer, 8);
        pluginGeneratedSerialDescriptor.addElement("consent", true);
        pluginGeneratedSerialDescriptor.addElement("admob_gde_signals", true);
        pluginGeneratedSerialDescriptor.addElement("facebook_buyeruid", true);
        pluginGeneratedSerialDescriptor.addElement("unity_buyeruid", true);
        pluginGeneratedSerialDescriptor.addElement("vungle_buyeruid", true);
        pluginGeneratedSerialDescriptor.addElement("eids", true);
        pluginGeneratedSerialDescriptor.addElement("mfx_buyerdata", true);
        pluginGeneratedSerialDescriptor.addElement("mintegral_sdk", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private User$Extension$$serializer() {
    }

    public KSerializer<?>[] childSerializers() {
        KSerializer[] access$get$childSerializers$cp = User.Extension.$childSerializers;
        return new KSerializer[]{BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(access$get$childSerializers$cp[5]), BuiltinSerializersKt.getNullable(access$get$childSerializers$cp[6]), BuiltinSerializersKt.getNullable(access$get$childSerializers$cp[7])};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v7, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v7, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v14, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v7, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v17, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v7, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v22, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v7, resolved type: java.util.Set} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v25, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v6, resolved type: java.util.Map} */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0115, code lost:
        r7 = 7;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.adsbynimbus.openrtb.request.User.Extension deserialize(kotlinx.serialization.encoding.Decoder r19) {
        /*
            r18 = this;
            r0 = r19
            java.lang.String r1 = "decoder"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            kotlinx.serialization.descriptors.SerialDescriptor r1 = r18.getDescriptor()
            kotlinx.serialization.encoding.CompositeDecoder r0 = r0.beginStructure(r1)
            kotlinx.serialization.KSerializer[] r2 = com.adsbynimbus.openrtb.request.User.Extension.$childSerializers
            boolean r3 = r0.decodeSequentially()
            r4 = 3
            r5 = 4
            r6 = 2
            r7 = 7
            r8 = 6
            r9 = 5
            r10 = 1
            r11 = 0
            r12 = 0
            if (r3 == 0) goto L_0x007e
            kotlinx.serialization.internal.StringSerializer r3 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r3 = (kotlinx.serialization.DeserializationStrategy) r3
            java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r11, r3, r12)
            java.lang.String r3 = (java.lang.String) r3
            kotlinx.serialization.internal.StringSerializer r11 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r11 = (kotlinx.serialization.DeserializationStrategy) r11
            java.lang.Object r10 = r0.decodeNullableSerializableElement(r1, r10, r11, r12)
            java.lang.String r10 = (java.lang.String) r10
            kotlinx.serialization.internal.StringSerializer r11 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r11 = (kotlinx.serialization.DeserializationStrategy) r11
            java.lang.Object r6 = r0.decodeNullableSerializableElement(r1, r6, r11, r12)
            java.lang.String r6 = (java.lang.String) r6
            kotlinx.serialization.internal.StringSerializer r11 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r11 = (kotlinx.serialization.DeserializationStrategy) r11
            java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r4, r11, r12)
            java.lang.String r4 = (java.lang.String) r4
            kotlinx.serialization.internal.StringSerializer r11 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r11 = (kotlinx.serialization.DeserializationStrategy) r11
            java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r5, r11, r12)
            java.lang.String r5 = (java.lang.String) r5
            r11 = r2[r9]
            kotlinx.serialization.DeserializationStrategy r11 = (kotlinx.serialization.DeserializationStrategy) r11
            java.lang.Object r9 = r0.decodeNullableSerializableElement(r1, r9, r11, r12)
            java.util.Set r9 = (java.util.Set) r9
            r11 = r2[r8]
            kotlinx.serialization.DeserializationStrategy r11 = (kotlinx.serialization.DeserializationStrategy) r11
            java.lang.Object r8 = r0.decodeNullableSerializableElement(r1, r8, r11, r12)
            java.util.Map r8 = (java.util.Map) r8
            r2 = r2[r7]
            kotlinx.serialization.DeserializationStrategy r2 = (kotlinx.serialization.DeserializationStrategy) r2
            java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r7, r2, r12)
            java.util.Map r2 = (java.util.Map) r2
            r7 = 255(0xff, float:3.57E-43)
            r15 = r2
            r11 = r4
            r12 = r5
            r14 = r8
            r13 = r9
            r9 = r10
            r8 = r3
            r10 = r6
            goto L_0x0121
        L_0x007e:
            r17 = r10
            r3 = r11
            r4 = r12
            r6 = r4
            r10 = r6
            r11 = r10
            r13 = r11
            r14 = r13
            r15 = r14
        L_0x0088:
            if (r17 == 0) goto L_0x0118
            int r5 = r0.decodeElementIndex(r1)
            switch(r5) {
                case -1: goto L_0x0111;
                case 0: goto L_0x0101;
                case 1: goto L_0x00f1;
                case 2: goto L_0x00e1;
                case 3: goto L_0x00d1;
                case 4: goto L_0x00c1;
                case 5: goto L_0x00b2;
                case 6: goto L_0x00a4;
                case 7: goto L_0x0097;
                default: goto L_0x0091;
            }
        L_0x0091:
            kotlinx.serialization.UnknownFieldException r0 = new kotlinx.serialization.UnknownFieldException
            r0.<init>((int) r5)
            throw r0
        L_0x0097:
            r5 = r2[r7]
            kotlinx.serialization.DeserializationStrategy r5 = (kotlinx.serialization.DeserializationStrategy) r5
            java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r7, r5, r4)
            java.util.Map r4 = (java.util.Map) r4
            r3 = r3 | 128(0x80, float:1.794E-43)
            goto L_0x00bf
        L_0x00a4:
            r5 = r2[r8]
            kotlinx.serialization.DeserializationStrategy r5 = (kotlinx.serialization.DeserializationStrategy) r5
            java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r8, r5, r6)
            r6 = r5
            java.util.Map r6 = (java.util.Map) r6
            r3 = r3 | 64
            goto L_0x00bf
        L_0x00b2:
            r5 = r2[r9]
            kotlinx.serialization.DeserializationStrategy r5 = (kotlinx.serialization.DeserializationStrategy) r5
            java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r9, r5, r10)
            r10 = r5
            java.util.Set r10 = (java.util.Set) r10
            r3 = r3 | 32
        L_0x00bf:
            r5 = 4
            goto L_0x0088
        L_0x00c1:
            kotlinx.serialization.internal.StringSerializer r5 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r5 = (kotlinx.serialization.DeserializationStrategy) r5
            r7 = 4
            java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r7, r5, r11)
            r11 = r5
            java.lang.String r11 = (java.lang.String) r11
            r3 = r3 | 16
            r5 = r7
            goto L_0x0115
        L_0x00d1:
            r7 = 4
            kotlinx.serialization.internal.StringSerializer r5 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r5 = (kotlinx.serialization.DeserializationStrategy) r5
            r7 = 3
            java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r7, r5, r15)
            r15 = r5
            java.lang.String r15 = (java.lang.String) r15
            r3 = r3 | 8
            goto L_0x0114
        L_0x00e1:
            r7 = 3
            kotlinx.serialization.internal.StringSerializer r5 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r5 = (kotlinx.serialization.DeserializationStrategy) r5
            r7 = 2
            java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r7, r5, r14)
            r14 = r5
            java.lang.String r14 = (java.lang.String) r14
            r3 = r3 | 4
            goto L_0x0114
        L_0x00f1:
            r7 = 2
            kotlinx.serialization.internal.StringSerializer r5 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r5 = (kotlinx.serialization.DeserializationStrategy) r5
            r7 = 1
            java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r7, r5, r13)
            r13 = r5
            java.lang.String r13 = (java.lang.String) r13
            r3 = r3 | 2
            goto L_0x0114
        L_0x0101:
            r7 = 1
            kotlinx.serialization.internal.StringSerializer r5 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r5 = (kotlinx.serialization.DeserializationStrategy) r5
            r7 = 0
            java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r7, r5, r12)
            r12 = r5
            java.lang.String r12 = (java.lang.String) r12
            r3 = r3 | 1
            goto L_0x0114
        L_0x0111:
            r7 = 0
            r17 = r7
        L_0x0114:
            r5 = 4
        L_0x0115:
            r7 = 7
            goto L_0x0088
        L_0x0118:
            r7 = r3
            r8 = r12
            r9 = r13
            r13 = r10
            r12 = r11
            r10 = r14
            r11 = r15
            r15 = r4
            r14 = r6
        L_0x0121:
            r0.endStructure(r1)
            com.adsbynimbus.openrtb.request.User$Extension r0 = new com.adsbynimbus.openrtb.request.User$Extension
            r16 = 0
            r6 = r0
            r6.<init>((int) r7, (java.lang.String) r8, (java.lang.String) r9, (java.lang.String) r10, (java.lang.String) r11, (java.lang.String) r12, (java.util.Set) r13, (java.util.Map) r14, (java.util.Map) r15, (kotlinx.serialization.internal.SerializationConstructorMarker) r16)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.openrtb.request.User$Extension$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.adsbynimbus.openrtb.request.User$Extension");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, User.Extension extension) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(extension, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder beginStructure = encoder.beginStructure(descriptor2);
        User.Extension.write$Self$kotlin_release(extension, beginStructure, descriptor2);
        beginStructure.endStructure(descriptor2);
    }

    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}

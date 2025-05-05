package com.adsbynimbus.openrtb.request;

import androidx.autofill.HintConstants;
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
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/adsbynimbus/openrtb/request/User.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/adsbynimbus/openrtb/request/User;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* compiled from: User.kt */
public final class User$$serializer implements GeneratedSerializer<User> {
    public static final User$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        User$$serializer user$$serializer = new User$$serializer();
        INSTANCE = user$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.adsbynimbus.openrtb.request.User", user$$serializer, 8);
        pluginGeneratedSerialDescriptor.addElement("age", true);
        pluginGeneratedSerialDescriptor.addElement("buyeruid", true);
        pluginGeneratedSerialDescriptor.addElement("yob", true);
        pluginGeneratedSerialDescriptor.addElement(HintConstants.AUTOFILL_HINT_GENDER, true);
        pluginGeneratedSerialDescriptor.addElement("keywords", true);
        pluginGeneratedSerialDescriptor.addElement("custom_data", true);
        pluginGeneratedSerialDescriptor.addElement("data", true);
        pluginGeneratedSerialDescriptor.addElement("ext", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private User$$serializer() {
    }

    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{IntSerializer.INSTANCE, BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), IntSerializer.INSTANCE, BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(User.$childSerializers[6]), BuiltinSerializersKt.getNullable(User$Extension$$serializer.INSTANCE)};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v7, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v7, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v12, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v6, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v16, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v7, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v19, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v6, resolved type: com.adsbynimbus.openrtb.request.Data[]} */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x00fd, code lost:
        r4 = 7;
        r5 = 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0104, code lost:
        r4 = 7;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.adsbynimbus.openrtb.request.User deserialize(kotlinx.serialization.encoding.Decoder r22) {
        /*
            r21 = this;
            r0 = r22
            java.lang.String r1 = "decoder"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            kotlinx.serialization.descriptors.SerialDescriptor r1 = r21.getDescriptor()
            kotlinx.serialization.encoding.CompositeDecoder r0 = r0.beginStructure(r1)
            kotlinx.serialization.KSerializer[] r2 = com.adsbynimbus.openrtb.request.User.$childSerializers
            boolean r3 = r0.decodeSequentially()
            r4 = 7
            r5 = 5
            r6 = 3
            r7 = 4
            r8 = 2
            r9 = 6
            r10 = 1
            r11 = 0
            r12 = 0
            if (r3 == 0) goto L_0x0074
            int r3 = r0.decodeIntElement(r1, r11)
            kotlinx.serialization.internal.StringSerializer r11 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r11 = (kotlinx.serialization.DeserializationStrategy) r11
            java.lang.Object r10 = r0.decodeNullableSerializableElement(r1, r10, r11, r12)
            java.lang.String r10 = (java.lang.String) r10
            int r8 = r0.decodeIntElement(r1, r8)
            kotlinx.serialization.internal.StringSerializer r11 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r11 = (kotlinx.serialization.DeserializationStrategy) r11
            java.lang.Object r6 = r0.decodeNullableSerializableElement(r1, r6, r11, r12)
            java.lang.String r6 = (java.lang.String) r6
            kotlinx.serialization.internal.StringSerializer r11 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r11 = (kotlinx.serialization.DeserializationStrategy) r11
            java.lang.Object r7 = r0.decodeNullableSerializableElement(r1, r7, r11, r12)
            java.lang.String r7 = (java.lang.String) r7
            kotlinx.serialization.internal.StringSerializer r11 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r11 = (kotlinx.serialization.DeserializationStrategy) r11
            java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r5, r11, r12)
            java.lang.String r5 = (java.lang.String) r5
            r2 = r2[r9]
            kotlinx.serialization.DeserializationStrategy r2 = (kotlinx.serialization.DeserializationStrategy) r2
            java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r9, r2, r12)
            com.adsbynimbus.openrtb.request.Data[] r2 = (com.adsbynimbus.openrtb.request.Data[]) r2
            com.adsbynimbus.openrtb.request.User$Extension$$serializer r9 = com.adsbynimbus.openrtb.request.User$Extension$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r9 = (kotlinx.serialization.DeserializationStrategy) r9
            java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r4, r9, r12)
            com.adsbynimbus.openrtb.request.User$Extension r4 = (com.adsbynimbus.openrtb.request.User.Extension) r4
            r9 = 255(0xff, float:3.57E-43)
            r16 = r2
            r17 = r4
            r15 = r5
            r13 = r6
            r14 = r7
            r12 = r8
            r11 = r10
            r10 = r3
            goto L_0x0115
        L_0x0074:
            r19 = r10
            r3 = r11
            r13 = r3
            r6 = r12
            r8 = r6
            r10 = r8
            r14 = r10
            r15 = r14
            r11 = r15
            r12 = r13
        L_0x007f:
            if (r19 == 0) goto L_0x0108
            int r7 = r0.decodeElementIndex(r1)
            switch(r7) {
                case -1: goto L_0x0100;
                case 0: goto L_0x00f4;
                case 1: goto L_0x00e1;
                case 2: goto L_0x00d8;
                case 3: goto L_0x00c8;
                case 4: goto L_0x00b7;
                case 5: goto L_0x00a9;
                case 6: goto L_0x009b;
                case 7: goto L_0x008e;
                default: goto L_0x0088;
            }
        L_0x0088:
            kotlinx.serialization.UnknownFieldException r0 = new kotlinx.serialization.UnknownFieldException
            r0.<init>((int) r7)
            throw r0
        L_0x008e:
            com.adsbynimbus.openrtb.request.User$Extension$$serializer r7 = com.adsbynimbus.openrtb.request.User$Extension$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r7 = (kotlinx.serialization.DeserializationStrategy) r7
            java.lang.Object r6 = r0.decodeNullableSerializableElement(r1, r4, r7, r6)
            com.adsbynimbus.openrtb.request.User$Extension r6 = (com.adsbynimbus.openrtb.request.User.Extension) r6
            r13 = r13 | 128(0x80, float:1.794E-43)
            goto L_0x0105
        L_0x009b:
            r7 = r2[r9]
            kotlinx.serialization.DeserializationStrategy r7 = (kotlinx.serialization.DeserializationStrategy) r7
            java.lang.Object r7 = r0.decodeNullableSerializableElement(r1, r9, r7, r8)
            r8 = r7
            com.adsbynimbus.openrtb.request.Data[] r8 = (com.adsbynimbus.openrtb.request.Data[]) r8
            r13 = r13 | 64
            goto L_0x0105
        L_0x00a9:
            kotlinx.serialization.internal.StringSerializer r7 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r7 = (kotlinx.serialization.DeserializationStrategy) r7
            java.lang.Object r7 = r0.decodeNullableSerializableElement(r1, r5, r7, r10)
            r10 = r7
            java.lang.String r10 = (java.lang.String) r10
            r13 = r13 | 32
            goto L_0x0105
        L_0x00b7:
            kotlinx.serialization.internal.StringSerializer r7 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r7 = (kotlinx.serialization.DeserializationStrategy) r7
            r4 = 4
            java.lang.Object r7 = r0.decodeNullableSerializableElement(r1, r4, r7, r11)
            r11 = r7
            java.lang.String r11 = (java.lang.String) r11
            r13 = r13 | 16
            r7 = r4
            r4 = 7
            goto L_0x007f
        L_0x00c8:
            r4 = 4
            kotlinx.serialization.internal.StringSerializer r7 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r7 = (kotlinx.serialization.DeserializationStrategy) r7
            r4 = 3
            java.lang.Object r7 = r0.decodeNullableSerializableElement(r1, r4, r7, r15)
            r15 = r7
            java.lang.String r15 = (java.lang.String) r15
            r13 = r13 | 8
            goto L_0x0104
        L_0x00d8:
            r4 = 3
            r7 = 2
            int r12 = r0.decodeIntElement(r1, r7)
            r13 = r13 | 4
            goto L_0x0104
        L_0x00e1:
            r4 = 3
            r7 = 2
            kotlinx.serialization.internal.StringSerializer r17 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            r4 = r17
            kotlinx.serialization.DeserializationStrategy r4 = (kotlinx.serialization.DeserializationStrategy) r4
            r5 = 1
            java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r5, r4, r14)
            r14 = r4
            java.lang.String r14 = (java.lang.String) r14
            r13 = r13 | 2
            goto L_0x00fd
        L_0x00f4:
            r4 = 0
            r5 = 1
            r7 = 2
            int r3 = r0.decodeIntElement(r1, r4)
            r13 = r13 | 1
        L_0x00fd:
            r4 = 7
            r5 = 5
            goto L_0x0105
        L_0x0100:
            r4 = 0
            r7 = 2
            r19 = r4
        L_0x0104:
            r4 = 7
        L_0x0105:
            r7 = 4
            goto L_0x007f
        L_0x0108:
            r17 = r6
            r16 = r8
            r9 = r13
            r13 = r15
            r15 = r10
            r10 = r3
            r20 = r14
            r14 = r11
            r11 = r20
        L_0x0115:
            r0.endStructure(r1)
            com.adsbynimbus.openrtb.request.User r0 = new com.adsbynimbus.openrtb.request.User
            r18 = 0
            r8 = r0
            r8.<init>((int) r9, (int) r10, (java.lang.String) r11, (int) r12, (java.lang.String) r13, (java.lang.String) r14, (java.lang.String) r15, (com.adsbynimbus.openrtb.request.Data[]) r16, (com.adsbynimbus.openrtb.request.User.Extension) r17, (kotlinx.serialization.internal.SerializationConstructorMarker) r18)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.openrtb.request.User$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.adsbynimbus.openrtb.request.User");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, User user) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(user, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder beginStructure = encoder.beginStructure(descriptor2);
        User.write$Self$kotlin_release(user, beginStructure, descriptor2);
        beginStructure.endStructure(descriptor2);
    }

    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}

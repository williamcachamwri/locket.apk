package com.adsbynimbus.openrtb.request;

import io.sentry.SentryBaseEvent;
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
import kotlinx.serialization.internal.FloatSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/adsbynimbus/openrtb/request/Native.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/adsbynimbus/openrtb/request/Native;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* compiled from: Native.kt */
public final class Native$$serializer implements GeneratedSerializer<Native> {
    public static final Native$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        Native$$serializer native$$serializer = new Native$$serializer();
        INSTANCE = native$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.adsbynimbus.openrtb.request.Native", native$$serializer, 6);
        pluginGeneratedSerialDescriptor.addElement("bidfloor", true);
        pluginGeneratedSerialDescriptor.addElement(SentryBaseEvent.JsonKeys.REQUEST, true);
        pluginGeneratedSerialDescriptor.addElement("ver", true);
        pluginGeneratedSerialDescriptor.addElement("api", true);
        pluginGeneratedSerialDescriptor.addElement("battr", true);
        pluginGeneratedSerialDescriptor.addElement("ext", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private Native$$serializer() {
    }

    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{FloatSerializer.INSTANCE, BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(ByteArraySerializer.INSTANCE), BuiltinSerializersKt.getNullable(ByteArraySerializer.INSTANCE), BuiltinSerializersKt.getNullable(Extension$$serializer.INSTANCE)};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v6, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v6, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v13, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v6, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v16, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v6, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v19, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v6, resolved type: com.adsbynimbus.openrtb.request.Extension} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.adsbynimbus.openrtb.request.Native deserialize(kotlinx.serialization.encoding.Decoder r19) {
        /*
            r18 = this;
            r0 = r19
            java.lang.String r1 = "decoder"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            kotlinx.serialization.descriptors.SerialDescriptor r1 = r18.getDescriptor()
            kotlinx.serialization.encoding.CompositeDecoder r0 = r0.beginStructure(r1)
            boolean r2 = r0.decodeSequentially()
            r3 = 5
            r4 = 3
            r5 = 4
            r6 = 2
            r7 = 1
            r8 = 0
            r9 = 0
            if (r2 == 0) goto L_0x005e
            float r2 = r0.decodeFloatElement(r1, r8)
            kotlinx.serialization.internal.StringSerializer r8 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r8 = (kotlinx.serialization.DeserializationStrategy) r8
            java.lang.Object r7 = r0.decodeNullableSerializableElement(r1, r7, r8, r9)
            java.lang.String r7 = (java.lang.String) r7
            kotlinx.serialization.internal.StringSerializer r8 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r8 = (kotlinx.serialization.DeserializationStrategy) r8
            java.lang.Object r6 = r0.decodeNullableSerializableElement(r1, r6, r8, r9)
            java.lang.String r6 = (java.lang.String) r6
            kotlinx.serialization.internal.ByteArraySerializer r8 = kotlinx.serialization.internal.ByteArraySerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r8 = (kotlinx.serialization.DeserializationStrategy) r8
            java.lang.Object r4 = r0.decodeNullableSerializableElement(r1, r4, r8, r9)
            byte[] r4 = (byte[]) r4
            kotlinx.serialization.internal.ByteArraySerializer r8 = kotlinx.serialization.internal.ByteArraySerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r8 = (kotlinx.serialization.DeserializationStrategy) r8
            java.lang.Object r5 = r0.decodeNullableSerializableElement(r1, r5, r8, r9)
            byte[] r5 = (byte[]) r5
            com.adsbynimbus.openrtb.request.Extension$$serializer r8 = com.adsbynimbus.openrtb.request.Extension$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r8 = (kotlinx.serialization.DeserializationStrategy) r8
            java.lang.Object r3 = r0.decodeNullableSerializableElement(r1, r3, r8, r9)
            com.adsbynimbus.openrtb.request.Extension r3 = (com.adsbynimbus.openrtb.request.Extension) r3
            r8 = 63
            r11 = r2
            r16 = r3
            r14 = r4
            r15 = r5
            r13 = r6
            r12 = r7
            r10 = r8
            goto L_0x00cf
        L_0x005e:
            r2 = 0
            r15 = r7
            r10 = r9
            r11 = r10
            r12 = r11
            r13 = r12
            r14 = r13
            r9 = r8
        L_0x0066:
            if (r15 == 0) goto L_0x00c7
            int r8 = r0.decodeElementIndex(r1)
            switch(r8) {
                case -1: goto L_0x00c4;
                case 0: goto L_0x00bc;
                case 1: goto L_0x00ad;
                case 2: goto L_0x009f;
                case 3: goto L_0x0091;
                case 4: goto L_0x0083;
                case 5: goto L_0x0075;
                default: goto L_0x006f;
            }
        L_0x006f:
            kotlinx.serialization.UnknownFieldException r0 = new kotlinx.serialization.UnknownFieldException
            r0.<init>((int) r8)
            throw r0
        L_0x0075:
            com.adsbynimbus.openrtb.request.Extension$$serializer r8 = com.adsbynimbus.openrtb.request.Extension$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r8 = (kotlinx.serialization.DeserializationStrategy) r8
            java.lang.Object r8 = r0.decodeNullableSerializableElement(r1, r3, r8, r14)
            r14 = r8
            com.adsbynimbus.openrtb.request.Extension r14 = (com.adsbynimbus.openrtb.request.Extension) r14
            r9 = r9 | 32
            goto L_0x00ba
        L_0x0083:
            kotlinx.serialization.internal.ByteArraySerializer r8 = kotlinx.serialization.internal.ByteArraySerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r8 = (kotlinx.serialization.DeserializationStrategy) r8
            java.lang.Object r8 = r0.decodeNullableSerializableElement(r1, r5, r8, r13)
            r13 = r8
            byte[] r13 = (byte[]) r13
            r9 = r9 | 16
            goto L_0x00ba
        L_0x0091:
            kotlinx.serialization.internal.ByteArraySerializer r8 = kotlinx.serialization.internal.ByteArraySerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r8 = (kotlinx.serialization.DeserializationStrategy) r8
            java.lang.Object r8 = r0.decodeNullableSerializableElement(r1, r4, r8, r12)
            r12 = r8
            byte[] r12 = (byte[]) r12
            r9 = r9 | 8
            goto L_0x00ba
        L_0x009f:
            kotlinx.serialization.internal.StringSerializer r8 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r8 = (kotlinx.serialization.DeserializationStrategy) r8
            java.lang.Object r8 = r0.decodeNullableSerializableElement(r1, r6, r8, r11)
            r11 = r8
            java.lang.String r11 = (java.lang.String) r11
            r9 = r9 | 4
            goto L_0x00ba
        L_0x00ad:
            kotlinx.serialization.internal.StringSerializer r8 = kotlinx.serialization.internal.StringSerializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r8 = (kotlinx.serialization.DeserializationStrategy) r8
            java.lang.Object r8 = r0.decodeNullableSerializableElement(r1, r7, r8, r10)
            r10 = r8
            java.lang.String r10 = (java.lang.String) r10
            r9 = r9 | 2
        L_0x00ba:
            r8 = 0
            goto L_0x0066
        L_0x00bc:
            r8 = 0
            float r2 = r0.decodeFloatElement(r1, r8)
            r9 = r9 | 1
            goto L_0x0066
        L_0x00c4:
            r8 = 0
            r15 = r8
            goto L_0x0066
        L_0x00c7:
            r15 = r13
            r16 = r14
            r13 = r11
            r14 = r12
            r11 = r2
            r12 = r10
            r10 = r9
        L_0x00cf:
            r0.endStructure(r1)
            com.adsbynimbus.openrtb.request.Native r0 = new com.adsbynimbus.openrtb.request.Native
            r17 = 0
            r9 = r0
            r9.<init>((int) r10, (float) r11, (java.lang.String) r12, (java.lang.String) r13, (byte[]) r14, (byte[]) r15, (com.adsbynimbus.openrtb.request.Extension) r16, (kotlinx.serialization.internal.SerializationConstructorMarker) r17)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.openrtb.request.Native$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.adsbynimbus.openrtb.request.Native");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, Native nativeR) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(nativeR, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder beginStructure = encoder.beginStructure(descriptor2);
        Native.write$Self$kotlin_release(nativeR, beginStructure, descriptor2);
        beginStructure.endStructure(descriptor2);
    }

    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}

package com.adsbynimbus.openrtb.request;

import androidx.media3.common.MimeTypes;
import io.sentry.protocol.SentryStackFrame;
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

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"com/adsbynimbus/openrtb/request/Impression.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/adsbynimbus/openrtb/request/Impression;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* compiled from: Impression.kt */
public final class Impression$$serializer implements GeneratedSerializer<Impression> {
    public static final Impression$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        Impression$$serializer impression$$serializer = new Impression$$serializer();
        INSTANCE = impression$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.adsbynimbus.openrtb.request.Impression", impression$$serializer, 6);
        pluginGeneratedSerialDescriptor.addElement("banner", true);
        pluginGeneratedSerialDescriptor.addElement(MimeTypes.BASE_TYPE_VIDEO, true);
        pluginGeneratedSerialDescriptor.addElement(SentryStackFrame.JsonKeys.NATIVE, true);
        pluginGeneratedSerialDescriptor.addElement("instl", true);
        pluginGeneratedSerialDescriptor.addElement("secure", true);
        pluginGeneratedSerialDescriptor.addElement("ext", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private Impression$$serializer() {
    }

    public KSerializer<?>[] childSerializers() {
        return new KSerializer[]{BuiltinSerializersKt.getNullable(Banner$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(Video$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(Native$$serializer.INSTANCE), ByteSerializer.INSTANCE, ByteSerializer.INSTANCE, Impression$Extension$$serializer.INSTANCE};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v5, resolved type: com.adsbynimbus.openrtb.request.Banner} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v5, resolved type: com.adsbynimbus.openrtb.request.Video} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v13, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v5, resolved type: com.adsbynimbus.openrtb.request.Native} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v16, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v6, resolved type: com.adsbynimbus.openrtb.request.Impression$Extension} */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x00bb, code lost:
        r3 = 5;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.adsbynimbus.openrtb.request.Impression deserialize(kotlinx.serialization.encoding.Decoder r19) {
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
            if (r2 == 0) goto L_0x0058
            com.adsbynimbus.openrtb.request.Banner$$serializer r2 = com.adsbynimbus.openrtb.request.Banner$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r2 = (kotlinx.serialization.DeserializationStrategy) r2
            java.lang.Object r2 = r0.decodeNullableSerializableElement(r1, r8, r2, r9)
            com.adsbynimbus.openrtb.request.Banner r2 = (com.adsbynimbus.openrtb.request.Banner) r2
            com.adsbynimbus.openrtb.request.Video$$serializer r8 = com.adsbynimbus.openrtb.request.Video$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r8 = (kotlinx.serialization.DeserializationStrategy) r8
            java.lang.Object r7 = r0.decodeNullableSerializableElement(r1, r7, r8, r9)
            com.adsbynimbus.openrtb.request.Video r7 = (com.adsbynimbus.openrtb.request.Video) r7
            com.adsbynimbus.openrtb.request.Native$$serializer r8 = com.adsbynimbus.openrtb.request.Native$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r8 = (kotlinx.serialization.DeserializationStrategy) r8
            java.lang.Object r6 = r0.decodeNullableSerializableElement(r1, r6, r8, r9)
            com.adsbynimbus.openrtb.request.Native r6 = (com.adsbynimbus.openrtb.request.Native) r6
            byte r4 = r0.decodeByteElement(r1, r4)
            byte r5 = r0.decodeByteElement(r1, r5)
            com.adsbynimbus.openrtb.request.Impression$Extension$$serializer r8 = com.adsbynimbus.openrtb.request.Impression$Extension$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r8 = (kotlinx.serialization.DeserializationStrategy) r8
            java.lang.Object r3 = r0.decodeSerializableElement(r1, r3, r8, r9)
            com.adsbynimbus.openrtb.request.Impression$Extension r3 = (com.adsbynimbus.openrtb.request.Impression.Extension) r3
            r8 = 63
            r11 = r2
            r16 = r3
            r14 = r4
            r15 = r5
            r13 = r6
            r12 = r7
            r10 = r8
            goto L_0x00c1
        L_0x0058:
            r15 = r7
            r2 = r8
            r10 = r2
            r11 = r9
            r12 = r11
            r13 = r12
            r14 = r13
            r9 = r10
        L_0x0060:
            if (r15 == 0) goto L_0x00bd
            int r8 = r0.decodeElementIndex(r1)
            switch(r8) {
                case -1: goto L_0x00b8;
                case 0: goto L_0x00a8;
                case 1: goto L_0x0099;
                case 2: goto L_0x008b;
                case 3: goto L_0x0084;
                case 4: goto L_0x007d;
                case 5: goto L_0x006f;
                default: goto L_0x0069;
            }
        L_0x0069:
            kotlinx.serialization.UnknownFieldException r0 = new kotlinx.serialization.UnknownFieldException
            r0.<init>((int) r8)
            throw r0
        L_0x006f:
            com.adsbynimbus.openrtb.request.Impression$Extension$$serializer r8 = com.adsbynimbus.openrtb.request.Impression$Extension$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r8 = (kotlinx.serialization.DeserializationStrategy) r8
            java.lang.Object r8 = r0.decodeSerializableElement(r1, r3, r8, r14)
            r14 = r8
            com.adsbynimbus.openrtb.request.Impression$Extension r14 = (com.adsbynimbus.openrtb.request.Impression.Extension) r14
            r10 = r10 | 32
            goto L_0x00a6
        L_0x007d:
            byte r9 = r0.decodeByteElement(r1, r5)
            r10 = r10 | 16
            goto L_0x00a6
        L_0x0084:
            byte r2 = r0.decodeByteElement(r1, r4)
            r10 = r10 | 8
            goto L_0x00a6
        L_0x008b:
            com.adsbynimbus.openrtb.request.Native$$serializer r8 = com.adsbynimbus.openrtb.request.Native$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r8 = (kotlinx.serialization.DeserializationStrategy) r8
            java.lang.Object r8 = r0.decodeNullableSerializableElement(r1, r6, r8, r13)
            r13 = r8
            com.adsbynimbus.openrtb.request.Native r13 = (com.adsbynimbus.openrtb.request.Native) r13
            r10 = r10 | 4
            goto L_0x00a6
        L_0x0099:
            com.adsbynimbus.openrtb.request.Video$$serializer r8 = com.adsbynimbus.openrtb.request.Video$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r8 = (kotlinx.serialization.DeserializationStrategy) r8
            java.lang.Object r8 = r0.decodeNullableSerializableElement(r1, r7, r8, r12)
            r12 = r8
            com.adsbynimbus.openrtb.request.Video r12 = (com.adsbynimbus.openrtb.request.Video) r12
            r10 = r10 | 2
        L_0x00a6:
            r8 = 0
            goto L_0x0060
        L_0x00a8:
            com.adsbynimbus.openrtb.request.Banner$$serializer r8 = com.adsbynimbus.openrtb.request.Banner$$serializer.INSTANCE
            kotlinx.serialization.DeserializationStrategy r8 = (kotlinx.serialization.DeserializationStrategy) r8
            r3 = 0
            java.lang.Object r8 = r0.decodeNullableSerializableElement(r1, r3, r8, r11)
            r11 = r8
            com.adsbynimbus.openrtb.request.Banner r11 = (com.adsbynimbus.openrtb.request.Banner) r11
            r10 = r10 | 1
            r8 = r3
            goto L_0x00bb
        L_0x00b8:
            r3 = 0
            r8 = r3
            r15 = r8
        L_0x00bb:
            r3 = 5
            goto L_0x0060
        L_0x00bd:
            r15 = r9
            r16 = r14
            r14 = r2
        L_0x00c1:
            r0.endStructure(r1)
            com.adsbynimbus.openrtb.request.Impression r0 = new com.adsbynimbus.openrtb.request.Impression
            r17 = 0
            r9 = r0
            r9.<init>((int) r10, (com.adsbynimbus.openrtb.request.Banner) r11, (com.adsbynimbus.openrtb.request.Video) r12, (com.adsbynimbus.openrtb.request.Native) r13, (byte) r14, (byte) r15, (com.adsbynimbus.openrtb.request.Impression.Extension) r16, (kotlinx.serialization.internal.SerializationConstructorMarker) r17)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.openrtb.request.Impression$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.adsbynimbus.openrtb.request.Impression");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, Impression impression) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(impression, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        CompositeEncoder beginStructure = encoder.beginStructure(descriptor2);
        Impression.write$Self$kotlin_release(impression, beginStructure, descriptor2);
        beginStructure.endStructure(descriptor2);
    }

    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}

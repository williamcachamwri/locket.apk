package com.adsbynimbus.openrtb.request;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ByteArraySerializer;
import kotlinx.serialization.internal.ByteSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.ReferenceArraySerializer;
import kotlinx.serialization.internal.SerializationConstructorMarker;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 )2\u00020\u0001:\u0002()Bw\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003\u0012\u0010\b\u0001\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\b\b\u0001\u0010\t\u001a\u00020\n\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\b\u0001\u0010\r\u001a\u00020\u000e\u0012\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010\f\u0012\n\b\u0001\u0010\u0010\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\u0002\u0010\u0013B_\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000e¢\u0006\u0002\u0010\u0014J&\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00002\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&HÁ\u0001¢\u0006\u0002\b'R\u001a\u0010\u000f\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0015\u0010\u0016R\u001a\u0010\u000b\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0017\u0010\u0016R\u0018\u0010\t\u001a\u00020\n8\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0018\u0010\u0016R\"\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00078\u0006@\u0006X\u000e¢\u0006\n\n\u0002\u0010\u001a\u0012\u0004\b\u0019\u0010\u0016R\u0018\u0010\u0005\u001a\u00020\u00038\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u001b\u0010\u0016R\u0018\u0010\r\u001a\u00020\u000e8\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u001c\u0010\u0016R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u000e¢\u0006\n\n\u0002\u0010\u001e\u0012\u0004\b\u001d\u0010\u0016R\u0018\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u001f\u0010\u0016¨\u0006*"}, d2 = {"Lcom/adsbynimbus/openrtb/request/Banner;", "", "seen1", "", "w", "h", "format", "", "Lcom/adsbynimbus/openrtb/request/Format;", "bidfloor", "", "battr", "", "pos", "", "api", "vcm", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(III[Lcom/adsbynimbus/openrtb/request/Format;F[BB[BLjava/lang/Byte;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(II[Lcom/adsbynimbus/openrtb/request/Format;F[BB[BLjava/lang/Byte;)V", "getApi$annotations", "()V", "getBattr$annotations", "getBidfloor$annotations", "getFormat$annotations", "[Lcom/adsbynimbus/openrtb/request/Format;", "getH$annotations", "getPos$annotations", "getVcm$annotations", "Ljava/lang/Byte;", "getW$annotations", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$kotlin_release", "$serializer", "Companion", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
@Serializable
/* compiled from: Banner.kt */
public final class Banner {
    /* access modifiers changed from: private */
    public static final KSerializer<Object>[] $childSerializers = {null, null, new ReferenceArraySerializer(Reflection.getOrCreateKotlinClass(Format.class), Format$$serializer.INSTANCE), null, null, null, null, null};
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public byte[] api;
    public byte[] battr;
    public float bidfloor;
    public Format[] format;
    public int h;
    public byte pos;
    public Byte vcm;
    public int w;

    @SerialName("api")
    public static /* synthetic */ void getApi$annotations() {
    }

    @SerialName("battr")
    public static /* synthetic */ void getBattr$annotations() {
    }

    @SerialName("bidfloor")
    public static /* synthetic */ void getBidfloor$annotations() {
    }

    @SerialName("format")
    public static /* synthetic */ void getFormat$annotations() {
    }

    @SerialName("h")
    public static /* synthetic */ void getH$annotations() {
    }

    @SerialName("pos")
    public static /* synthetic */ void getPos$annotations() {
    }

    @SerialName("vcm")
    public static /* synthetic */ void getVcm$annotations() {
    }

    @SerialName("w")
    public static /* synthetic */ void getW$annotations() {
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/adsbynimbus/openrtb/request/Banner$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/adsbynimbus/openrtb/request/Banner;", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
    /* compiled from: Banner.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<Banner> serializer() {
            return Banner$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ Banner(int i, @SerialName("w") int i2, @SerialName("h") int i3, @SerialName("format") Format[] formatArr, @SerialName("bidfloor") float f, @SerialName("battr") byte[] bArr, @SerialName("pos") byte b, @SerialName("api") byte[] bArr2, @SerialName("vcm") Byte b2, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (i & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i, 3, Banner$$serializer.INSTANCE.getDescriptor());
        }
        this.w = i2;
        this.h = i3;
        if ((i & 4) == 0) {
            this.format = null;
        } else {
            this.format = formatArr;
        }
        if ((i & 8) == 0) {
            this.bidfloor = 0.0f;
        } else {
            this.bidfloor = f;
        }
        if ((i & 16) == 0) {
            this.battr = null;
        } else {
            this.battr = bArr;
        }
        if ((i & 32) == 0) {
            this.pos = 0;
        } else {
            this.pos = b;
        }
        if ((i & 64) == 0) {
            this.api = null;
        } else {
            this.api = bArr2;
        }
        if ((i & 128) == 0) {
            this.vcm = null;
        } else {
            this.vcm = b2;
        }
    }

    public Banner(int i, int i2, Format[] formatArr, float f, byte[] bArr, byte b, byte[] bArr2, Byte b2) {
        this.w = i;
        this.h = i2;
        this.format = formatArr;
        this.bidfloor = f;
        this.battr = bArr;
        this.pos = b;
        this.api = bArr2;
        this.vcm = b2;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$kotlin_release(Banner banner, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        KSerializer<Object>[] kSerializerArr = $childSerializers;
        boolean z = false;
        compositeEncoder.encodeIntElement(serialDescriptor, 0, banner.w);
        compositeEncoder.encodeIntElement(serialDescriptor, 1, banner.h);
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 2) || banner.format != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 2, kSerializerArr[2], banner.format);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 3) || Float.compare(banner.bidfloor, 0.0f) != 0) {
            compositeEncoder.encodeFloatElement(serialDescriptor, 3, banner.bidfloor);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 4) || banner.battr != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 4, ByteArraySerializer.INSTANCE, banner.battr);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 5) || banner.pos != 0) {
            compositeEncoder.encodeByteElement(serialDescriptor, 5, banner.pos);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 6) || banner.api != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 6, ByteArraySerializer.INSTANCE, banner.api);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 7) || banner.vcm != null) {
            z = true;
        }
        if (z) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 7, ByteSerializer.INSTANCE, banner.vcm);
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ Banner(int r13, int r14, com.adsbynimbus.openrtb.request.Format[] r15, float r16, byte[] r17, byte r18, byte[] r19, java.lang.Byte r20, int r21, kotlin.jvm.internal.DefaultConstructorMarker r22) {
        /*
            r12 = this;
            r0 = r21
            r1 = r0 & 4
            r2 = 0
            if (r1 == 0) goto L_0x0009
            r6 = r2
            goto L_0x000a
        L_0x0009:
            r6 = r15
        L_0x000a:
            r1 = r0 & 8
            if (r1 == 0) goto L_0x0011
            r1 = 0
            r7 = r1
            goto L_0x0013
        L_0x0011:
            r7 = r16
        L_0x0013:
            r1 = r0 & 16
            if (r1 == 0) goto L_0x0019
            r8 = r2
            goto L_0x001b
        L_0x0019:
            r8 = r17
        L_0x001b:
            r1 = r0 & 32
            if (r1 == 0) goto L_0x0022
            r1 = 0
            r9 = r1
            goto L_0x0024
        L_0x0022:
            r9 = r18
        L_0x0024:
            r1 = r0 & 64
            if (r1 == 0) goto L_0x002a
            r10 = r2
            goto L_0x002c
        L_0x002a:
            r10 = r19
        L_0x002c:
            r0 = r0 & 128(0x80, float:1.794E-43)
            if (r0 == 0) goto L_0x0032
            r11 = r2
            goto L_0x0034
        L_0x0032:
            r11 = r20
        L_0x0034:
            r3 = r12
            r4 = r13
            r5 = r14
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.openrtb.request.Banner.<init>(int, int, com.adsbynimbus.openrtb.request.Format[], float, byte[], byte, byte[], java.lang.Byte, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }
}

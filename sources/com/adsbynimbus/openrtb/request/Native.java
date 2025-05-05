package com.adsbynimbus.openrtb.request;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ByteArraySerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \"2\u00020\u0001:\u0002!\"B_\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0002\u0010\u0010BK\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u0011J&\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fHÁ\u0001¢\u0006\u0002\b R\u001a\u0010\t\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0012\u0010\u0013R\u001a\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0014\u0010\u0013R\u0018\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0015\u0010\u0013R\u001a\u0010\f\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0016\u0010\u0013R\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0017\u0010\u0013R\u001a\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0018\u0010\u0013¨\u0006#"}, d2 = {"Lcom/adsbynimbus/openrtb/request/Native;", "", "seen1", "", "bidfloor", "", "request", "", "ver", "api", "", "battr", "ext", "Lcom/adsbynimbus/openrtb/request/Extension;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IFLjava/lang/String;Ljava/lang/String;[B[BLcom/adsbynimbus/openrtb/request/Extension;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(FLjava/lang/String;Ljava/lang/String;[B[BLcom/adsbynimbus/openrtb/request/Extension;)V", "getApi$annotations", "()V", "getBattr$annotations", "getBidfloor$annotations", "getExt$annotations", "getRequest$annotations", "getVer$annotations", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$kotlin_release", "$serializer", "Companion", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
@Serializable
/* compiled from: Native.kt */
public final class Native {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public byte[] api;
    public byte[] battr;
    public float bidfloor;
    public Extension ext;
    public String request;
    public String ver;

    public Native() {
        this(0.0f, (String) null, (String) null, (byte[]) null, (byte[]) null, (Extension) null, 63, (DefaultConstructorMarker) null);
    }

    @SerialName("api")
    public static /* synthetic */ void getApi$annotations() {
    }

    @SerialName("battr")
    public static /* synthetic */ void getBattr$annotations() {
    }

    @SerialName("bidfloor")
    public static /* synthetic */ void getBidfloor$annotations() {
    }

    @SerialName("ext")
    public static /* synthetic */ void getExt$annotations() {
    }

    @SerialName("request")
    public static /* synthetic */ void getRequest$annotations() {
    }

    @SerialName("ver")
    public static /* synthetic */ void getVer$annotations() {
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/adsbynimbus/openrtb/request/Native$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/adsbynimbus/openrtb/request/Native;", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
    /* compiled from: Native.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<Native> serializer() {
            return Native$$serializer.INSTANCE;
        }
    }

    public Native(float f, String str, String str2, byte[] bArr, byte[] bArr2, Extension extension) {
        this.bidfloor = f;
        this.request = str;
        this.ver = str2;
        this.api = bArr;
        this.battr = bArr2;
        this.ext = extension;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ Native(int i, @SerialName("bidfloor") float f, @SerialName("request") String str, @SerialName("ver") String str2, @SerialName("api") byte[] bArr, @SerialName("battr") byte[] bArr2, @SerialName("ext") Extension extension, SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 0) != 0) {
            PluginExceptionsKt.throwMissingFieldException(i, 0, Native$$serializer.INSTANCE.getDescriptor());
        }
        this.bidfloor = (i & 1) == 0 ? 0.0f : f;
        if ((i & 2) == 0) {
            this.request = null;
        } else {
            this.request = str;
        }
        if ((i & 4) == 0) {
            this.ver = null;
        } else {
            this.ver = str2;
        }
        if ((i & 8) == 0) {
            this.api = null;
        } else {
            this.api = bArr;
        }
        if ((i & 16) == 0) {
            this.battr = null;
        } else {
            this.battr = bArr2;
        }
        if ((i & 32) == 0) {
            this.ext = null;
        } else {
            this.ext = extension;
        }
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$kotlin_release(Native nativeR, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        boolean z = false;
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 0) || Float.compare(nativeR.bidfloor, 0.0f) != 0) {
            compositeEncoder.encodeFloatElement(serialDescriptor, 0, nativeR.bidfloor);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 1) || nativeR.request != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 1, StringSerializer.INSTANCE, nativeR.request);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 2) || nativeR.ver != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 2, StringSerializer.INSTANCE, nativeR.ver);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 3) || nativeR.api != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 3, ByteArraySerializer.INSTANCE, nativeR.api);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 4) || nativeR.battr != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 4, ByteArraySerializer.INSTANCE, nativeR.battr);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 5) || nativeR.ext != null) {
            z = true;
        }
        if (z) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 5, Extension$$serializer.INSTANCE, nativeR.ext);
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ Native(float r5, java.lang.String r6, java.lang.String r7, byte[] r8, byte[] r9, com.adsbynimbus.openrtb.request.Extension r10, int r11, kotlin.jvm.internal.DefaultConstructorMarker r12) {
        /*
            r4 = this;
            r12 = r11 & 1
            if (r12 == 0) goto L_0x0005
            r5 = 0
        L_0x0005:
            r12 = r11 & 2
            r0 = 0
            if (r12 == 0) goto L_0x000c
            r12 = r0
            goto L_0x000d
        L_0x000c:
            r12 = r6
        L_0x000d:
            r6 = r11 & 4
            if (r6 == 0) goto L_0x0013
            r1 = r0
            goto L_0x0014
        L_0x0013:
            r1 = r7
        L_0x0014:
            r6 = r11 & 8
            if (r6 == 0) goto L_0x001a
            r2 = r0
            goto L_0x001b
        L_0x001a:
            r2 = r8
        L_0x001b:
            r6 = r11 & 16
            if (r6 == 0) goto L_0x0021
            r3 = r0
            goto L_0x0022
        L_0x0021:
            r3 = r9
        L_0x0022:
            r6 = r11 & 32
            if (r6 == 0) goto L_0x0027
            goto L_0x0028
        L_0x0027:
            r0 = r10
        L_0x0028:
            r6 = r4
            r7 = r5
            r8 = r12
            r9 = r1
            r10 = r2
            r11 = r3
            r12 = r0
            r6.<init>(r7, r8, r9, r10, r11, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.openrtb.request.Native.<init>(float, java.lang.String, java.lang.String, byte[], byte[], com.adsbynimbus.openrtb.request.Extension, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }
}

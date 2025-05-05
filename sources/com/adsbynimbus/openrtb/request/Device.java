package com.adsbynimbus.openrtb.request;

import io.sentry.protocol.Device;
import io.sentry.protocol.OperatingSystem;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.FloatSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 ;2\u00020\u0001:\u0002:;Bå\u0001\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0001\u0010\f\u001a\u00020\u0003\u0012\b\b\u0001\u0010\r\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0001\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0001\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0001\u0010\u0013\u001a\u00020\u0012\u0012\b\b\u0001\u0010\u0014\u001a\u00020\u0012\u0012\b\b\u0001\u0010\u0015\u001a\u00020\u0012\u0012\n\b\u0001\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u0012\n\b\u0001\u0010\u0018\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0019\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b¢\u0006\u0002\u0010\u001cBµ\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0012\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u001dJ&\u00102\u001a\u0002032\u0006\u00104\u001a\u00020\u00002\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u000208HÁ\u0001¢\u0006\u0002\b9R\u001a\u0010\u0019\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u001e\u0010\u001fR\u0018\u0010\u0013\u001a\u00020\u00128\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b \u0010\u001fR\u0016\u0010\u0011\u001a\u00020\u00128\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b!\u0010\u001fR\u0018\u0010\u0014\u001a\u00020\u00128\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\"\u0010\u001fR\u001a\u0010\u0016\u001a\u0004\u0018\u00010\u00178\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b#\u0010\u001fR\u0018\u0010\f\u001a\u00020\u00038\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b$\u0010\u001fR\u001a\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b%\u0010\u001fR\u0018\u0010\u0006\u001a\u00020\u00058\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b&\u0010\u001fR\u001a\u0010\u0018\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b'\u0010\u001fR\u001a\u0010\u0010\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b(\u0010\u001fR\u0018\u0010\u0015\u001a\u00020\u00128\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b)\u0010\u001fR\u0016\u0010\u0007\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b*\u0010\u001fR\u0016\u0010\b\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b+\u0010\u001fR\u0016\u0010\n\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b,\u0010\u001fR\u0016\u0010\u000b\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b-\u0010\u001fR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000f8\u0006@\u0006X\u000e¢\u0006\n\n\u0002\u0010/\u0012\u0004\b.\u0010\u001fR\u0018\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b0\u0010\u001fR\u0018\u0010\r\u001a\u00020\u00038\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b1\u0010\u001f¨\u0006<"}, d2 = {"Lcom/adsbynimbus/openrtb/request/Device;", "", "seen1", "", "ua", "", "ifa", "make", "model", "hwv", "os", "osv", "h", "w", "pxratio", "", "language", "devicetype", "", "connectiontype", "dnt", "lmt", "geo", "Lcom/adsbynimbus/openrtb/request/Geo;", "ip", "carrier", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILjava/lang/Float;Ljava/lang/String;BBBBLcom/adsbynimbus/openrtb/request/Geo;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILjava/lang/Float;Ljava/lang/String;BBBBLcom/adsbynimbus/openrtb/request/Geo;Ljava/lang/String;Ljava/lang/String;)V", "getCarrier$annotations", "()V", "getConnectiontype$annotations", "getDevicetype$annotations", "getDnt$annotations", "getGeo$annotations", "getH$annotations", "getHwv$annotations", "getIfa$annotations", "getIp$annotations", "getLanguage$annotations", "getLmt$annotations", "getMake$annotations", "getModel$annotations", "getOs$annotations", "getOsv$annotations", "getPxratio$annotations", "Ljava/lang/Float;", "getUa$annotations", "getW$annotations", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$kotlin_release", "$serializer", "Companion", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
@Serializable
/* compiled from: Device.kt */
public final class Device {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public String carrier;
    public byte connectiontype;
    public final byte devicetype;
    public byte dnt;
    public Geo geo;
    public int h;
    public String hwv;
    public String ifa;
    public String ip;
    public String language;
    public byte lmt;
    public final String make;
    public final String model;
    public final String os;
    public final String osv;
    public Float pxratio;
    public String ua;
    public int w;

    @SerialName("carrier")
    public static /* synthetic */ void getCarrier$annotations() {
    }

    @SerialName("connectiontype")
    public static /* synthetic */ void getConnectiontype$annotations() {
    }

    @SerialName("devicetype")
    public static /* synthetic */ void getDevicetype$annotations() {
    }

    @SerialName("dnt")
    public static /* synthetic */ void getDnt$annotations() {
    }

    @SerialName("geo")
    public static /* synthetic */ void getGeo$annotations() {
    }

    @SerialName("h")
    public static /* synthetic */ void getH$annotations() {
    }

    @SerialName("hwv")
    public static /* synthetic */ void getHwv$annotations() {
    }

    @SerialName("ifa")
    public static /* synthetic */ void getIfa$annotations() {
    }

    @SerialName("ip")
    public static /* synthetic */ void getIp$annotations() {
    }

    @SerialName("language")
    public static /* synthetic */ void getLanguage$annotations() {
    }

    @SerialName("lmt")
    public static /* synthetic */ void getLmt$annotations() {
    }

    @SerialName("make")
    public static /* synthetic */ void getMake$annotations() {
    }

    @SerialName("model")
    public static /* synthetic */ void getModel$annotations() {
    }

    @SerialName("os")
    public static /* synthetic */ void getOs$annotations() {
    }

    @SerialName("osv")
    public static /* synthetic */ void getOsv$annotations() {
    }

    @SerialName("pxratio")
    public static /* synthetic */ void getPxratio$annotations() {
    }

    @SerialName("ua")
    public static /* synthetic */ void getUa$annotations() {
    }

    @SerialName("w")
    public static /* synthetic */ void getW$annotations() {
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/adsbynimbus/openrtb/request/Device$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/adsbynimbus/openrtb/request/Device;", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
    /* compiled from: Device.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<Device> serializer() {
            return Device$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ Device(int i, @SerialName("ua") String str, @SerialName("ifa") String str2, @SerialName("make") String str3, @SerialName("model") String str4, @SerialName("hwv") String str5, @SerialName("os") String str6, @SerialName("osv") String str7, @SerialName("h") int i2, @SerialName("w") int i3, @SerialName("pxratio") Float f, @SerialName("language") String str8, @SerialName("devicetype") byte b, @SerialName("connectiontype") byte b2, @SerialName("dnt") byte b3, @SerialName("lmt") byte b4, @SerialName("geo") Geo geo2, @SerialName("ip") String str9, @SerialName("carrier") String str10, SerializationConstructorMarker serializationConstructorMarker) {
        int i4 = i;
        if (495 != (i4 & 495)) {
            PluginExceptionsKt.throwMissingFieldException(i, 495, Device$$serializer.INSTANCE.getDescriptor());
        }
        this.ua = str;
        this.ifa = str2;
        this.make = str3;
        this.model = str4;
        if ((i4 & 16) == 0) {
            this.hwv = null;
        } else {
            this.hwv = str5;
        }
        this.os = str6;
        this.osv = str7;
        this.h = i2;
        this.w = i3;
        if ((i4 & 512) == 0) {
            this.pxratio = null;
        } else {
            this.pxratio = f;
        }
        if ((i4 & 1024) == 0) {
            this.language = null;
        } else {
            this.language = str8;
        }
        if ((i4 & 2048) == 0) {
            this.devicetype = 0;
        } else {
            this.devicetype = b;
        }
        if ((i4 & 4096) == 0) {
            this.connectiontype = 0;
        } else {
            this.connectiontype = b2;
        }
        if ((i4 & 8192) == 0) {
            this.dnt = 0;
        } else {
            this.dnt = b3;
        }
        if ((i4 & 16384) == 0) {
            this.lmt = 0;
        } else {
            this.lmt = b4;
        }
        if ((32768 & i4) == 0) {
            this.geo = null;
        } else {
            this.geo = geo2;
        }
        if ((65536 & i4) == 0) {
            this.ip = null;
        } else {
            this.ip = str9;
        }
        if ((i4 & 131072) == 0) {
            this.carrier = null;
        } else {
            this.carrier = str10;
        }
    }

    public Device(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, int i2, Float f, String str8, byte b, byte b2, byte b3, byte b4, Geo geo2, String str9, String str10) {
        Intrinsics.checkNotNullParameter(str, "ua");
        Intrinsics.checkNotNullParameter(str2, "ifa");
        Intrinsics.checkNotNullParameter(str3, "make");
        Intrinsics.checkNotNullParameter(str4, Device.JsonKeys.MODEL);
        Intrinsics.checkNotNullParameter(str6, OperatingSystem.TYPE);
        Intrinsics.checkNotNullParameter(str7, "osv");
        this.ua = str;
        this.ifa = str2;
        this.make = str3;
        this.model = str4;
        this.hwv = str5;
        this.os = str6;
        this.osv = str7;
        this.h = i;
        this.w = i2;
        this.pxratio = f;
        this.language = str8;
        this.devicetype = b;
        this.connectiontype = b2;
        this.dnt = b3;
        this.lmt = b4;
        this.geo = geo2;
        this.ip = str9;
        this.carrier = str10;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$kotlin_release(Device device, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        boolean z = false;
        compositeEncoder.encodeStringElement(serialDescriptor, 0, device.ua);
        compositeEncoder.encodeStringElement(serialDescriptor, 1, device.ifa);
        compositeEncoder.encodeStringElement(serialDescriptor, 2, device.make);
        compositeEncoder.encodeStringElement(serialDescriptor, 3, device.model);
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 4) || device.hwv != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 4, StringSerializer.INSTANCE, device.hwv);
        }
        compositeEncoder.encodeStringElement(serialDescriptor, 5, device.os);
        compositeEncoder.encodeStringElement(serialDescriptor, 6, device.osv);
        compositeEncoder.encodeIntElement(serialDescriptor, 7, device.h);
        compositeEncoder.encodeIntElement(serialDescriptor, 8, device.w);
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 9) || device.pxratio != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 9, FloatSerializer.INSTANCE, device.pxratio);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 10) || device.language != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 10, StringSerializer.INSTANCE, device.language);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 11) || device.devicetype != 0) {
            compositeEncoder.encodeByteElement(serialDescriptor, 11, device.devicetype);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 12) || device.connectiontype != 0) {
            compositeEncoder.encodeByteElement(serialDescriptor, 12, device.connectiontype);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 13) || device.dnt != 0) {
            compositeEncoder.encodeByteElement(serialDescriptor, 13, device.dnt);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 14) || device.lmt != 0) {
            compositeEncoder.encodeByteElement(serialDescriptor, 14, device.lmt);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 15) || device.geo != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 15, Geo$$serializer.INSTANCE, device.geo);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 16) || device.ip != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 16, StringSerializer.INSTANCE, device.ip);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 17) || device.carrier != null) {
            z = true;
        }
        if (z) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 17, StringSerializer.INSTANCE, device.carrier);
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ Device(java.lang.String r23, java.lang.String r24, java.lang.String r25, java.lang.String r26, java.lang.String r27, java.lang.String r28, java.lang.String r29, int r30, int r31, java.lang.Float r32, java.lang.String r33, byte r34, byte r35, byte r36, byte r37, com.adsbynimbus.openrtb.request.Geo r38, java.lang.String r39, java.lang.String r40, int r41, kotlin.jvm.internal.DefaultConstructorMarker r42) {
        /*
            r22 = this;
            r0 = r41
            r1 = r0 & 16
            r2 = 0
            if (r1 == 0) goto L_0x0009
            r8 = r2
            goto L_0x000b
        L_0x0009:
            r8 = r27
        L_0x000b:
            r1 = r0 & 512(0x200, float:7.175E-43)
            if (r1 == 0) goto L_0x0011
            r13 = r2
            goto L_0x0013
        L_0x0011:
            r13 = r32
        L_0x0013:
            r1 = r0 & 1024(0x400, float:1.435E-42)
            if (r1 == 0) goto L_0x0019
            r14 = r2
            goto L_0x001b
        L_0x0019:
            r14 = r33
        L_0x001b:
            r1 = r0 & 2048(0x800, float:2.87E-42)
            r3 = 0
            if (r1 == 0) goto L_0x0022
            r15 = r3
            goto L_0x0024
        L_0x0022:
            r15 = r34
        L_0x0024:
            r1 = r0 & 4096(0x1000, float:5.74E-42)
            if (r1 == 0) goto L_0x002b
            r16 = r3
            goto L_0x002d
        L_0x002b:
            r16 = r35
        L_0x002d:
            r1 = r0 & 8192(0x2000, float:1.14794E-41)
            if (r1 == 0) goto L_0x0034
            r17 = r3
            goto L_0x0036
        L_0x0034:
            r17 = r36
        L_0x0036:
            r1 = r0 & 16384(0x4000, float:2.2959E-41)
            if (r1 == 0) goto L_0x003d
            r18 = r3
            goto L_0x003f
        L_0x003d:
            r18 = r37
        L_0x003f:
            r1 = 32768(0x8000, float:4.5918E-41)
            r1 = r1 & r0
            if (r1 == 0) goto L_0x0048
            r19 = r2
            goto L_0x004a
        L_0x0048:
            r19 = r38
        L_0x004a:
            r1 = 65536(0x10000, float:9.18355E-41)
            r1 = r1 & r0
            if (r1 == 0) goto L_0x0052
            r20 = r2
            goto L_0x0054
        L_0x0052:
            r20 = r39
        L_0x0054:
            r1 = 131072(0x20000, float:1.83671E-40)
            r0 = r0 & r1
            if (r0 == 0) goto L_0x005c
            r21 = r2
            goto L_0x005e
        L_0x005c:
            r21 = r40
        L_0x005e:
            r3 = r22
            r4 = r23
            r5 = r24
            r6 = r25
            r7 = r26
            r9 = r28
            r10 = r29
            r11 = r30
            r12 = r31
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.openrtb.request.Device.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, int, java.lang.Float, java.lang.String, byte, byte, byte, byte, com.adsbynimbus.openrtb.request.Geo, java.lang.String, java.lang.String, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }
}

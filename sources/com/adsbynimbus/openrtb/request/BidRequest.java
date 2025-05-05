package com.adsbynimbus.openrtb.request;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.ReferenceArraySerializer;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonKt;

@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 ;2\u00020\u0001:\u0002:;B±\u0001\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0010\b\u0001\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\b\b\u0001\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0001\u0010\u0011\u001a\u00020\u0003\u0012\u0010\b\u0001\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u0012\n\b\u0001\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u0012\u0016\b\u0001\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0019\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b¢\u0006\u0002\u0010\u001cB\u0001\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u0012\u0014\b\u0002\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00130\u0019¢\u0006\u0002\u0010\u001dJ&\u00102\u001a\u0002032\u0006\u00104\u001a\u00020\u00002\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u000208HÁ\u0001¢\u0006\u0002\b9R\u001a\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u001e\u0010\u001fR\"\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00058\u0006@\u0006X\u000e¢\u0006\n\n\u0002\u0010!\u0012\u0004\b \u0010\u001fR\u001a\u0010\t\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\"\u0010\u001fR\"\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00130\u00198\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b#\u0010\u001fR\u0018\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b$\u0010\u001fR \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006@\u0006X\u000e¢\u0006\n\n\u0002\u0010&\u0012\u0004\b%\u0010\u001fR\u001a\u0010\u0016\u001a\u0004\u0018\u00010\u00178\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b'\u0010\u001fR&\u0010)\u001a\u00020\u00132\u0006\u0010(\u001a\u00020\u00138Æ\u0002@Æ\u0002X\u000e¢\u0006\f\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001a\u0010\u0014\u001a\u0004\u0018\u00010\u00158\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b.\u0010\u001fR\u0018\u0010\u000f\u001a\u00020\u00108\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b/\u0010\u001fR\u0018\u0010\u0011\u001a\u00020\u00038\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b0\u0010\u001fR\u001a\u0010\r\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b1\u0010\u001f¨\u0006<"}, d2 = {"Lcom/adsbynimbus/openrtb/request/BidRequest;", "", "seen1", "", "imp", "", "Lcom/adsbynimbus/openrtb/request/Impression;", "app", "Lcom/adsbynimbus/openrtb/request/App;", "device", "Lcom/adsbynimbus/openrtb/request/Device;", "format", "Lcom/adsbynimbus/openrtb/request/Format;", "user", "Lcom/adsbynimbus/openrtb/request/User;", "test", "", "tmax", "badv", "", "source", "Lcom/adsbynimbus/openrtb/request/Source;", "regs", "Lcom/adsbynimbus/openrtb/request/Regs;", "ext", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(I[Lcom/adsbynimbus/openrtb/request/Impression;Lcom/adsbynimbus/openrtb/request/App;Lcom/adsbynimbus/openrtb/request/Device;Lcom/adsbynimbus/openrtb/request/Format;Lcom/adsbynimbus/openrtb/request/User;BI[Ljava/lang/String;Lcom/adsbynimbus/openrtb/request/Source;Lcom/adsbynimbus/openrtb/request/Regs;Ljava/util/Map;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "([Lcom/adsbynimbus/openrtb/request/Impression;Lcom/adsbynimbus/openrtb/request/App;Lcom/adsbynimbus/openrtb/request/Device;Lcom/adsbynimbus/openrtb/request/Format;Lcom/adsbynimbus/openrtb/request/User;BI[Ljava/lang/String;Lcom/adsbynimbus/openrtb/request/Source;Lcom/adsbynimbus/openrtb/request/Regs;Ljava/util/Map;)V", "getApp$annotations", "()V", "getBadv$annotations", "[Ljava/lang/String;", "getDevice$annotations", "getExt$annotations", "getFormat$annotations", "getImp$annotations", "[Lcom/adsbynimbus/openrtb/request/Impression;", "getRegs$annotations", "value", "session_id", "getSession_id", "()Ljava/lang/String;", "setSession_id", "(Ljava/lang/String;)V", "getSource$annotations", "getTest$annotations", "getTmax$annotations", "getUser$annotations", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$kotlin_release", "$serializer", "Companion", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
@Serializable
/* compiled from: BidRequest.kt */
public final class BidRequest {
    /* access modifiers changed from: private */
    public static final KSerializer<Object>[] $childSerializers = {new ReferenceArraySerializer(Reflection.getOrCreateKotlinClass(Impression.class), Impression$$serializer.INSTANCE), null, null, null, null, null, null, new ReferenceArraySerializer(Reflection.getOrCreateKotlinClass(String.class), StringSerializer.INSTANCE), null, null, new LinkedHashMapSerializer(StringSerializer.INSTANCE, StringSerializer.INSTANCE)};
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String OPENRTB_HEADER = "x-openrtb-version";
    public static final String OPENRTB_VERSION = "2.5";
    public static final Json lenientSerializer = JsonKt.Json$default((Json) null, BidRequest$Companion$lenientSerializer$1.INSTANCE, 1, (Object) null);
    public App app;
    public String[] badv;
    public Device device;
    public final Map<String, String> ext;
    public Format format;
    public Impression[] imp;
    public Regs regs;
    public Source source;
    public byte test;
    public int tmax;
    public User user;

    public BidRequest() {
        this((Impression[]) null, (App) null, (Device) null, (Format) null, (User) null, (byte) 0, 0, (String[]) null, (Source) null, (Regs) null, (Map) null, 2047, (DefaultConstructorMarker) null);
    }

    @JvmStatic
    public static final BidRequest fromJson(String str) {
        return Companion.fromJson(str);
    }

    @JvmStatic
    public static final BidRequest fromJson(String str, Json json) {
        return Companion.fromJson(str, json);
    }

    @SerialName("app")
    public static /* synthetic */ void getApp$annotations() {
    }

    @SerialName("badv")
    public static /* synthetic */ void getBadv$annotations() {
    }

    @SerialName("device")
    public static /* synthetic */ void getDevice$annotations() {
    }

    @SerialName("ext")
    public static /* synthetic */ void getExt$annotations() {
    }

    @SerialName("format")
    public static /* synthetic */ void getFormat$annotations() {
    }

    @SerialName("imp")
    public static /* synthetic */ void getImp$annotations() {
    }

    @SerialName("regs")
    public static /* synthetic */ void getRegs$annotations() {
    }

    @SerialName("source")
    public static /* synthetic */ void getSource$annotations() {
    }

    @SerialName("test")
    public static /* synthetic */ void getTest$annotations() {
    }

    @SerialName("tmax")
    public static /* synthetic */ void getTmax$annotations() {
    }

    @SerialName("user")
    public static /* synthetic */ void getUser$annotations() {
    }

    @JvmStatic
    public static final String toJson(BidRequest bidRequest) {
        return Companion.toJson(bidRequest);
    }

    @JvmStatic
    public static final String toJson(BidRequest bidRequest, Json json) {
        return Companion.toJson(bidRequest, json);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ BidRequest(int i, @SerialName("imp") Impression[] impressionArr, @SerialName("app") App app2, @SerialName("device") Device device2, @SerialName("format") Format format2, @SerialName("user") User user2, @SerialName("test") byte b, @SerialName("tmax") int i2, @SerialName("badv") String[] strArr, @SerialName("source") Source source2, @SerialName("regs") Regs regs2, @SerialName("ext") Map map, SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 0) != 0) {
            PluginExceptionsKt.throwMissingFieldException(i, 0, BidRequest$$serializer.INSTANCE.getDescriptor());
        }
        if ((i & 1) == 0) {
            this.imp = new Impression[0];
        } else {
            this.imp = impressionArr;
        }
        if ((i & 2) == 0) {
            this.app = null;
        } else {
            this.app = app2;
        }
        if ((i & 4) == 0) {
            this.device = null;
        } else {
            this.device = device2;
        }
        if ((i & 8) == 0) {
            this.format = new Format(0, 0);
        } else {
            this.format = format2;
        }
        if ((i & 16) == 0) {
            this.user = null;
        } else {
            this.user = user2;
        }
        if ((i & 32) == 0) {
            this.test = 0;
        } else {
            this.test = b;
        }
        if ((i & 64) == 0) {
            this.tmax = 500;
        } else {
            this.tmax = i2;
        }
        if ((i & 128) == 0) {
            this.badv = null;
        } else {
            this.badv = strArr;
        }
        if ((i & 256) == 0) {
            this.source = null;
        } else {
            this.source = source2;
        }
        if ((i & 512) == 0) {
            this.regs = null;
        } else {
            this.regs = regs2;
        }
        if ((i & 1024) == 0) {
            this.ext = new LinkedHashMap();
        } else {
            this.ext = map;
        }
    }

    public BidRequest(Impression[] impressionArr, App app2, Device device2, Format format2, User user2, byte b, int i, String[] strArr, Source source2, Regs regs2, Map<String, String> map) {
        Intrinsics.checkNotNullParameter(impressionArr, "imp");
        Intrinsics.checkNotNullParameter(format2, "format");
        Intrinsics.checkNotNullParameter(map, "ext");
        this.imp = impressionArr;
        this.app = app2;
        this.device = device2;
        this.format = format2;
        this.user = user2;
        this.test = b;
        this.tmax = i;
        this.badv = strArr;
        this.source = source2;
        this.regs = regs2;
        this.ext = map;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$kotlin_release(BidRequest bidRequest, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        boolean z;
        KSerializer<Object>[] kSerializerArr = $childSerializers;
        boolean z2 = false;
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 0) || !Intrinsics.areEqual((Object) bidRequest.imp, (Object) new Impression[0])) {
            compositeEncoder.encodeSerializableElement(serialDescriptor, 0, kSerializerArr[0], bidRequest.imp);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 1) || bidRequest.app != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 1, App$$serializer.INSTANCE, bidRequest.app);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 2) || bidRequest.device != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 2, Device$$serializer.INSTANCE, bidRequest.device);
        }
        if (!compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 3) && Intrinsics.areEqual((Object) bidRequest.format, (Object) new Format(0, 0))) {
            z = false;
        } else {
            z = true;
        }
        if (z) {
            compositeEncoder.encodeSerializableElement(serialDescriptor, 3, Format$$serializer.INSTANCE, bidRequest.format);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 4) || bidRequest.user != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 4, User$$serializer.INSTANCE, bidRequest.user);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 5) || bidRequest.test != 0) {
            compositeEncoder.encodeByteElement(serialDescriptor, 5, bidRequest.test);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 6) || bidRequest.tmax != 500) {
            compositeEncoder.encodeIntElement(serialDescriptor, 6, bidRequest.tmax);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 7) || bidRequest.badv != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 7, kSerializerArr[7], bidRequest.badv);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 8) || bidRequest.source != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 8, Source$$serializer.INSTANCE, bidRequest.source);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 9) || bidRequest.regs != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 9, Regs$$serializer.INSTANCE, bidRequest.regs);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 10) || !Intrinsics.areEqual((Object) bidRequest.ext, (Object) new LinkedHashMap())) {
            z2 = true;
        }
        if (z2) {
            compositeEncoder.encodeSerializableElement(serialDescriptor, 10, kSerializerArr[10], bidRequest.ext);
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ BidRequest(com.adsbynimbus.openrtb.request.Impression[] r13, com.adsbynimbus.openrtb.request.App r14, com.adsbynimbus.openrtb.request.Device r15, com.adsbynimbus.openrtb.request.Format r16, com.adsbynimbus.openrtb.request.User r17, byte r18, int r19, java.lang.String[] r20, com.adsbynimbus.openrtb.request.Source r21, com.adsbynimbus.openrtb.request.Regs r22, java.util.Map r23, int r24, kotlin.jvm.internal.DefaultConstructorMarker r25) {
        /*
            r12 = this;
            r0 = r24
            r1 = r0 & 1
            r2 = 0
            if (r1 == 0) goto L_0x000a
            com.adsbynimbus.openrtb.request.Impression[] r1 = new com.adsbynimbus.openrtb.request.Impression[r2]
            goto L_0x000b
        L_0x000a:
            r1 = r13
        L_0x000b:
            r3 = r0 & 2
            r4 = 0
            if (r3 == 0) goto L_0x0012
            r3 = r4
            goto L_0x0013
        L_0x0012:
            r3 = r14
        L_0x0013:
            r5 = r0 & 4
            if (r5 == 0) goto L_0x0019
            r5 = r4
            goto L_0x001a
        L_0x0019:
            r5 = r15
        L_0x001a:
            r6 = r0 & 8
            if (r6 == 0) goto L_0x0024
            com.adsbynimbus.openrtb.request.Format r6 = new com.adsbynimbus.openrtb.request.Format
            r6.<init>(r2, r2)
            goto L_0x0026
        L_0x0024:
            r6 = r16
        L_0x0026:
            r7 = r0 & 16
            if (r7 == 0) goto L_0x002c
            r7 = r4
            goto L_0x002e
        L_0x002c:
            r7 = r17
        L_0x002e:
            r8 = r0 & 32
            if (r8 == 0) goto L_0x0033
            goto L_0x0035
        L_0x0033:
            r2 = r18
        L_0x0035:
            r8 = r0 & 64
            if (r8 == 0) goto L_0x003c
            r8 = 500(0x1f4, float:7.0E-43)
            goto L_0x003e
        L_0x003c:
            r8 = r19
        L_0x003e:
            r9 = r0 & 128(0x80, float:1.794E-43)
            if (r9 == 0) goto L_0x0044
            r9 = r4
            goto L_0x0046
        L_0x0044:
            r9 = r20
        L_0x0046:
            r10 = r0 & 256(0x100, float:3.59E-43)
            if (r10 == 0) goto L_0x004c
            r10 = r4
            goto L_0x004e
        L_0x004c:
            r10 = r21
        L_0x004e:
            r11 = r0 & 512(0x200, float:7.175E-43)
            if (r11 == 0) goto L_0x0053
            goto L_0x0055
        L_0x0053:
            r4 = r22
        L_0x0055:
            r0 = r0 & 1024(0x400, float:1.435E-42)
            if (r0 == 0) goto L_0x0061
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            r0.<init>()
            java.util.Map r0 = (java.util.Map) r0
            goto L_0x0063
        L_0x0061:
            r0 = r23
        L_0x0063:
            r13 = r12
            r14 = r1
            r15 = r3
            r16 = r5
            r17 = r6
            r18 = r7
            r19 = r2
            r20 = r8
            r21 = r9
            r22 = r10
            r23 = r4
            r24 = r0
            r13.<init>(r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.openrtb.request.BidRequest.<init>(com.adsbynimbus.openrtb.request.Impression[], com.adsbynimbus.openrtb.request.App, com.adsbynimbus.openrtb.request.Device, com.adsbynimbus.openrtb.request.Format, com.adsbynimbus.openrtb.request.User, byte, int, java.lang.String[], com.adsbynimbus.openrtb.request.Source, com.adsbynimbus.openrtb.request.Regs, java.util.Map, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final String getSession_id() {
        String str = this.ext.get("session_id");
        return str == null ? "" : str;
    }

    public final void setSession_id(String str) {
        Intrinsics.checkNotNullParameter(str, "value");
        this.ext.put("session_id", str);
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\u0007H\u0007J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\rHÆ\u0001J\u0016\u0010\u000e\u001a\u00020\u0004*\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\u0007H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00078\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/adsbynimbus/openrtb/request/BidRequest$Companion;", "", "()V", "OPENRTB_HEADER", "", "OPENRTB_VERSION", "lenientSerializer", "Lkotlinx/serialization/json/Json;", "fromJson", "Lcom/adsbynimbus/openrtb/request/BidRequest;", "json", "jsonSerializer", "serializer", "Lkotlinx/serialization/KSerializer;", "toJson", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
    /* compiled from: BidRequest.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final BidRequest fromJson(String str) {
            Intrinsics.checkNotNullParameter(str, "json");
            return fromJson$default(this, str, (Json) null, 2, (Object) null);
        }

        @JvmStatic
        public final String toJson(BidRequest bidRequest) {
            Intrinsics.checkNotNullParameter(bidRequest, "<this>");
            return toJson$default(this, bidRequest, (Json) null, 1, (Object) null);
        }

        private Companion() {
        }

        public final KSerializer<BidRequest> serializer() {
            return BidRequest$$serializer.INSTANCE;
        }

        public static /* synthetic */ String toJson$default(Companion companion, BidRequest bidRequest, Json json, int i, Object obj) {
            if ((i & 1) != 0) {
                json = BidRequest.lenientSerializer;
            }
            return companion.toJson(bidRequest, json);
        }

        @JvmStatic
        public final String toJson(BidRequest bidRequest, Json json) {
            Intrinsics.checkNotNullParameter(bidRequest, "<this>");
            Intrinsics.checkNotNullParameter(json, "jsonSerializer");
            return json.encodeToString(serializer(), bidRequest);
        }

        public static /* synthetic */ BidRequest fromJson$default(Companion companion, String str, Json json, int i, Object obj) {
            if ((i & 2) != 0) {
                json = BidRequest.lenientSerializer;
            }
            return companion.fromJson(str, json);
        }

        @JvmStatic
        public final BidRequest fromJson(String str, Json json) {
            Intrinsics.checkNotNullParameter(str, "json");
            Intrinsics.checkNotNullParameter(json, "jsonSerializer");
            return (BidRequest) json.decodeFromString(serializer(), str);
        }
    }
}

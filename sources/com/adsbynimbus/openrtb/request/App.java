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
import kotlinx.serialization.internal.ByteSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.ReferenceArraySerializer;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 02\u00020\u0001:\u0002/0B»\u0001\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\u0010\b\u0001\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\f\u0012\u0010\b\u0001\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\f\u0012\u0010\b\u0001\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\f\u0012\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0001\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015¢\u0006\u0002\u0010\u0016B§\u0001\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\f\u0012\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\f\u0012\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\f\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\u0002\u0010\u0017J&\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u00002\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-HÁ\u0001¢\u0006\u0002\b.R\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0018\u0010\u0019R\"\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\f8\u0006@\u0006X\u000e¢\u0006\n\n\u0002\u0010\u001b\u0012\u0004\b\u001a\u0010\u0019R\u001a\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u001c\u0010\u0019R\u001a\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u001d\u0010\u0019R\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u001e\u0010\u0019R\"\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\f8\u0006@\u0006X\u000e¢\u0006\n\n\u0002\u0010\u001b\u0012\u0004\b\u001f\u0010\u0019R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0006@\u0006X\u000e¢\u0006\n\n\u0002\u0010!\u0012\u0004\b \u0010\u0019R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u00108\u0006@\u0006X\u000e¢\u0006\n\n\u0002\u0010!\u0012\u0004\b\"\u0010\u0019R\u001a\u0010\u0012\u001a\u0004\u0018\u00010\u00138\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b#\u0010\u0019R\"\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\f8\u0006@\u0006X\u000e¢\u0006\n\n\u0002\u0010\u001b\u0012\u0004\b$\u0010\u0019R\u001a\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b%\u0010\u0019R\u001a\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b&\u0010\u0019¨\u00061"}, d2 = {"Lcom/adsbynimbus/openrtb/request/App;", "", "seen1", "", "name", "", "bundle", "domain", "storeurl", "ver", "keywords", "cat", "", "sectioncat", "pagecat", "privacypolicy", "", "paid", "publisher", "Lcom/adsbynimbus/openrtb/request/Publisher;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;Ljava/lang/Byte;Ljava/lang/Byte;Lcom/adsbynimbus/openrtb/request/Publisher;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;Ljava/lang/Byte;Ljava/lang/Byte;Lcom/adsbynimbus/openrtb/request/Publisher;)V", "getBundle$annotations", "()V", "getCat$annotations", "[Ljava/lang/String;", "getDomain$annotations", "getKeywords$annotations", "getName$annotations", "getPagecat$annotations", "getPaid$annotations", "Ljava/lang/Byte;", "getPrivacypolicy$annotations", "getPublisher$annotations", "getSectioncat$annotations", "getStoreurl$annotations", "getVer$annotations", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$kotlin_release", "$serializer", "Companion", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
@Serializable
/* compiled from: App.kt */
public final class App {
    /* access modifiers changed from: private */
    public static final KSerializer<Object>[] $childSerializers = {null, null, null, null, null, null, new ReferenceArraySerializer(Reflection.getOrCreateKotlinClass(String.class), StringSerializer.INSTANCE), new ReferenceArraySerializer(Reflection.getOrCreateKotlinClass(String.class), StringSerializer.INSTANCE), new ReferenceArraySerializer(Reflection.getOrCreateKotlinClass(String.class), StringSerializer.INSTANCE), null, null, null};
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public String bundle;
    public String[] cat;
    public String domain;
    public String keywords;
    public String name;
    public String[] pagecat;
    public Byte paid;
    public Byte privacypolicy;
    public Publisher publisher;
    public String[] sectioncat;
    public String storeurl;
    public String ver;

    public App() {
        this((String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String[]) null, (String[]) null, (String[]) null, (Byte) null, (Byte) null, (Publisher) null, 4095, (DefaultConstructorMarker) null);
    }

    @SerialName("bundle")
    public static /* synthetic */ void getBundle$annotations() {
    }

    @SerialName("cat")
    public static /* synthetic */ void getCat$annotations() {
    }

    @SerialName("domain")
    public static /* synthetic */ void getDomain$annotations() {
    }

    @SerialName("keywords")
    public static /* synthetic */ void getKeywords$annotations() {
    }

    @SerialName("name")
    public static /* synthetic */ void getName$annotations() {
    }

    @SerialName("pagecat")
    public static /* synthetic */ void getPagecat$annotations() {
    }

    @SerialName("paid")
    public static /* synthetic */ void getPaid$annotations() {
    }

    @SerialName("privacypolicy")
    public static /* synthetic */ void getPrivacypolicy$annotations() {
    }

    @SerialName("publisher")
    public static /* synthetic */ void getPublisher$annotations() {
    }

    @SerialName("sectioncat")
    public static /* synthetic */ void getSectioncat$annotations() {
    }

    @SerialName("storeurl")
    public static /* synthetic */ void getStoreurl$annotations() {
    }

    @SerialName("ver")
    public static /* synthetic */ void getVer$annotations() {
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/adsbynimbus/openrtb/request/App$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/adsbynimbus/openrtb/request/App;", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
    /* compiled from: App.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<App> serializer() {
            return App$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ App(int i, @SerialName("name") String str, @SerialName("bundle") String str2, @SerialName("domain") String str3, @SerialName("storeurl") String str4, @SerialName("ver") String str5, @SerialName("keywords") String str6, @SerialName("cat") String[] strArr, @SerialName("sectioncat") String[] strArr2, @SerialName("pagecat") String[] strArr3, @SerialName("privacypolicy") Byte b, @SerialName("paid") Byte b2, @SerialName("publisher") Publisher publisher2, SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 0) != 0) {
            PluginExceptionsKt.throwMissingFieldException(i, 0, App$$serializer.INSTANCE.getDescriptor());
        }
        if ((i & 1) == 0) {
            this.name = null;
        } else {
            this.name = str;
        }
        if ((i & 2) == 0) {
            this.bundle = null;
        } else {
            this.bundle = str2;
        }
        if ((i & 4) == 0) {
            this.domain = null;
        } else {
            this.domain = str3;
        }
        if ((i & 8) == 0) {
            this.storeurl = null;
        } else {
            this.storeurl = str4;
        }
        if ((i & 16) == 0) {
            this.ver = null;
        } else {
            this.ver = str5;
        }
        if ((i & 32) == 0) {
            this.keywords = null;
        } else {
            this.keywords = str6;
        }
        if ((i & 64) == 0) {
            this.cat = null;
        } else {
            this.cat = strArr;
        }
        if ((i & 128) == 0) {
            this.sectioncat = null;
        } else {
            this.sectioncat = strArr2;
        }
        if ((i & 256) == 0) {
            this.pagecat = null;
        } else {
            this.pagecat = strArr3;
        }
        if ((i & 512) == 0) {
            this.privacypolicy = null;
        } else {
            this.privacypolicy = b;
        }
        if ((i & 1024) == 0) {
            this.paid = null;
        } else {
            this.paid = b2;
        }
        if ((i & 2048) == 0) {
            this.publisher = null;
        } else {
            this.publisher = publisher2;
        }
    }

    public App(String str, String str2, String str3, String str4, String str5, String str6, String[] strArr, String[] strArr2, String[] strArr3, Byte b, Byte b2, Publisher publisher2) {
        this.name = str;
        this.bundle = str2;
        this.domain = str3;
        this.storeurl = str4;
        this.ver = str5;
        this.keywords = str6;
        this.cat = strArr;
        this.sectioncat = strArr2;
        this.pagecat = strArr3;
        this.privacypolicy = b;
        this.paid = b2;
        this.publisher = publisher2;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$kotlin_release(App app, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        KSerializer<Object>[] kSerializerArr = $childSerializers;
        boolean z = false;
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 0) || app.name != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 0, StringSerializer.INSTANCE, app.name);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 1) || app.bundle != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 1, StringSerializer.INSTANCE, app.bundle);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 2) || app.domain != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 2, StringSerializer.INSTANCE, app.domain);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 3) || app.storeurl != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 3, StringSerializer.INSTANCE, app.storeurl);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 4) || app.ver != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 4, StringSerializer.INSTANCE, app.ver);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 5) || app.keywords != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 5, StringSerializer.INSTANCE, app.keywords);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 6) || app.cat != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 6, kSerializerArr[6], app.cat);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 7) || app.sectioncat != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 7, kSerializerArr[7], app.sectioncat);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 8) || app.pagecat != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 8, kSerializerArr[8], app.pagecat);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 9) || app.privacypolicy != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 9, ByteSerializer.INSTANCE, app.privacypolicy);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 10) || app.paid != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 10, ByteSerializer.INSTANCE, app.paid);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 11) || app.publisher != null) {
            z = true;
        }
        if (z) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 11, Publisher$$serializer.INSTANCE, app.publisher);
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ App(java.lang.String r14, java.lang.String r15, java.lang.String r16, java.lang.String r17, java.lang.String r18, java.lang.String r19, java.lang.String[] r20, java.lang.String[] r21, java.lang.String[] r22, java.lang.Byte r23, java.lang.Byte r24, com.adsbynimbus.openrtb.request.Publisher r25, int r26, kotlin.jvm.internal.DefaultConstructorMarker r27) {
        /*
            r13 = this;
            r0 = r26
            r1 = r0 & 1
            r2 = 0
            if (r1 == 0) goto L_0x0009
            r1 = r2
            goto L_0x000a
        L_0x0009:
            r1 = r14
        L_0x000a:
            r3 = r0 & 2
            if (r3 == 0) goto L_0x0010
            r3 = r2
            goto L_0x0011
        L_0x0010:
            r3 = r15
        L_0x0011:
            r4 = r0 & 4
            if (r4 == 0) goto L_0x0017
            r4 = r2
            goto L_0x0019
        L_0x0017:
            r4 = r16
        L_0x0019:
            r5 = r0 & 8
            if (r5 == 0) goto L_0x001f
            r5 = r2
            goto L_0x0021
        L_0x001f:
            r5 = r17
        L_0x0021:
            r6 = r0 & 16
            if (r6 == 0) goto L_0x0027
            r6 = r2
            goto L_0x0029
        L_0x0027:
            r6 = r18
        L_0x0029:
            r7 = r0 & 32
            if (r7 == 0) goto L_0x002f
            r7 = r2
            goto L_0x0031
        L_0x002f:
            r7 = r19
        L_0x0031:
            r8 = r0 & 64
            if (r8 == 0) goto L_0x0037
            r8 = r2
            goto L_0x0039
        L_0x0037:
            r8 = r20
        L_0x0039:
            r9 = r0 & 128(0x80, float:1.794E-43)
            if (r9 == 0) goto L_0x003f
            r9 = r2
            goto L_0x0041
        L_0x003f:
            r9 = r21
        L_0x0041:
            r10 = r0 & 256(0x100, float:3.59E-43)
            if (r10 == 0) goto L_0x0047
            r10 = r2
            goto L_0x0049
        L_0x0047:
            r10 = r22
        L_0x0049:
            r11 = r0 & 512(0x200, float:7.175E-43)
            if (r11 == 0) goto L_0x004f
            r11 = r2
            goto L_0x0051
        L_0x004f:
            r11 = r23
        L_0x0051:
            r12 = r0 & 1024(0x400, float:1.435E-42)
            if (r12 == 0) goto L_0x0057
            r12 = r2
            goto L_0x0059
        L_0x0057:
            r12 = r24
        L_0x0059:
            r0 = r0 & 2048(0x800, float:2.87E-42)
            if (r0 == 0) goto L_0x005e
            goto L_0x0060
        L_0x005e:
            r2 = r25
        L_0x0060:
            r14 = r13
            r15 = r1
            r16 = r3
            r17 = r4
            r18 = r5
            r19 = r6
            r20 = r7
            r21 = r8
            r22 = r9
            r23 = r10
            r24 = r11
            r25 = r12
            r26 = r2
            r14.<init>(r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.openrtb.request.App.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String[], java.lang.String[], java.lang.String[], java.lang.Byte, java.lang.Byte, com.adsbynimbus.openrtb.request.Publisher, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }
}

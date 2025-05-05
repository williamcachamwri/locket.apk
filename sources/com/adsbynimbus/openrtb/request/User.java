package com.adsbynimbus.openrtb.request;

import java.util.Map;
import java.util.Set;
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
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.LinkedHashSetSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.ReferenceArraySerializer;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 '2\u00020\u0001:\u0003&'(B{\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0003\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0006\u0012\u0010\b\u0001\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f\u0012\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010\u0012Bg\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0006\u0012\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0002\u0010\u0013J&\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$HÁ\u0001¢\u0006\u0002\b%R\u0018\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0016\u0010\u0015R\u001a\u0010\n\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0017\u0010\u0015R\"\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f8\u0006@\u0006X\u000e¢\u0006\n\n\u0002\u0010\u0019\u0012\u0004\b\u0018\u0010\u0015R\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u000f8\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u001a\u0010\u0015R\u001a\u0010\b\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u001b\u0010\u0015R\u001a\u0010\t\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u001c\u0010\u0015R\u0018\u0010\u0007\u001a\u00020\u00038\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u001d\u0010\u0015¨\u0006)"}, d2 = {"Lcom/adsbynimbus/openrtb/request/User;", "", "seen1", "", "age", "buyeruid", "", "yob", "gender", "keywords", "custom_data", "data", "", "Lcom/adsbynimbus/openrtb/request/Data;", "ext", "Lcom/adsbynimbus/openrtb/request/User$Extension;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IILjava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;[Lcom/adsbynimbus/openrtb/request/Data;Lcom/adsbynimbus/openrtb/request/User$Extension;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;[Lcom/adsbynimbus/openrtb/request/Data;Lcom/adsbynimbus/openrtb/request/User$Extension;)V", "getAge$annotations", "()V", "getBuyeruid$annotations", "getCustom_data$annotations", "getData$annotations", "[Lcom/adsbynimbus/openrtb/request/Data;", "getExt$annotations", "getGender$annotations", "getKeywords$annotations", "getYob$annotations", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$kotlin_release", "$serializer", "Companion", "Extension", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
@Serializable
/* compiled from: User.kt */
public final class User {
    /* access modifiers changed from: private */
    public static final KSerializer<Object>[] $childSerializers = {null, null, null, null, null, null, new ReferenceArraySerializer(Reflection.getOrCreateKotlinClass(Data.class), Data$$serializer.INSTANCE), null};
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public int age;
    public String buyeruid;
    public String custom_data;
    public Data[] data;
    public Extension ext;
    public String gender;
    public String keywords;
    public int yob;

    public User() {
        this(0, (String) null, 0, (String) null, (String) null, (String) null, (Data[]) null, (Extension) null, 255, (DefaultConstructorMarker) null);
    }

    @SerialName("age")
    public static /* synthetic */ void getAge$annotations() {
    }

    @SerialName("buyeruid")
    public static /* synthetic */ void getBuyeruid$annotations() {
    }

    @SerialName("custom_data")
    public static /* synthetic */ void getCustom_data$annotations() {
    }

    @SerialName("data")
    public static /* synthetic */ void getData$annotations() {
    }

    @SerialName("ext")
    public static /* synthetic */ void getExt$annotations() {
    }

    @SerialName("gender")
    public static /* synthetic */ void getGender$annotations() {
    }

    @SerialName("keywords")
    public static /* synthetic */ void getKeywords$annotations() {
    }

    @SerialName("yob")
    public static /* synthetic */ void getYob$annotations() {
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/adsbynimbus/openrtb/request/User$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/adsbynimbus/openrtb/request/User;", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
    /* compiled from: User.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<User> serializer() {
            return User$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ User(int i, @SerialName("age") int i2, @SerialName("buyeruid") String str, @SerialName("yob") int i3, @SerialName("gender") String str2, @SerialName("keywords") String str3, @SerialName("custom_data") String str4, @SerialName("data") Data[] dataArr, @SerialName("ext") Extension extension, SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 0) != 0) {
            PluginExceptionsKt.throwMissingFieldException(i, 0, User$$serializer.INSTANCE.getDescriptor());
        }
        if ((i & 1) == 0) {
            this.age = 0;
        } else {
            this.age = i2;
        }
        if ((i & 2) == 0) {
            this.buyeruid = null;
        } else {
            this.buyeruid = str;
        }
        if ((i & 4) == 0) {
            this.yob = 0;
        } else {
            this.yob = i3;
        }
        if ((i & 8) == 0) {
            this.gender = null;
        } else {
            this.gender = str2;
        }
        if ((i & 16) == 0) {
            this.keywords = null;
        } else {
            this.keywords = str3;
        }
        if ((i & 32) == 0) {
            this.custom_data = null;
        } else {
            this.custom_data = str4;
        }
        if ((i & 64) == 0) {
            this.data = null;
        } else {
            this.data = dataArr;
        }
        if ((i & 128) == 0) {
            this.ext = null;
        } else {
            this.ext = extension;
        }
    }

    public User(int i, String str, int i2, String str2, String str3, String str4, Data[] dataArr, Extension extension) {
        this.age = i;
        this.buyeruid = str;
        this.yob = i2;
        this.gender = str2;
        this.keywords = str3;
        this.custom_data = str4;
        this.data = dataArr;
        this.ext = extension;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$kotlin_release(User user, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        KSerializer<Object>[] kSerializerArr = $childSerializers;
        boolean z = false;
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 0) || user.age != 0) {
            compositeEncoder.encodeIntElement(serialDescriptor, 0, user.age);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 1) || user.buyeruid != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 1, StringSerializer.INSTANCE, user.buyeruid);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 2) || user.yob != 0) {
            compositeEncoder.encodeIntElement(serialDescriptor, 2, user.yob);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 3) || user.gender != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 3, StringSerializer.INSTANCE, user.gender);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 4) || user.keywords != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 4, StringSerializer.INSTANCE, user.keywords);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 5) || user.custom_data != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 5, StringSerializer.INSTANCE, user.custom_data);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 6) || user.data != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 6, kSerializerArr[6], user.data);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 7) || user.ext != null) {
            z = true;
        }
        if (z) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 7, User$Extension$$serializer.INSTANCE, user.ext);
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ User(int r10, java.lang.String r11, int r12, java.lang.String r13, java.lang.String r14, java.lang.String r15, com.adsbynimbus.openrtb.request.Data[] r16, com.adsbynimbus.openrtb.request.User.Extension r17, int r18, kotlin.jvm.internal.DefaultConstructorMarker r19) {
        /*
            r9 = this;
            r0 = r18
            r1 = r0 & 1
            r2 = 0
            if (r1 == 0) goto L_0x0009
            r1 = r2
            goto L_0x000a
        L_0x0009:
            r1 = r10
        L_0x000a:
            r3 = r0 & 2
            r4 = 0
            if (r3 == 0) goto L_0x0011
            r3 = r4
            goto L_0x0012
        L_0x0011:
            r3 = r11
        L_0x0012:
            r5 = r0 & 4
            if (r5 == 0) goto L_0x0017
            goto L_0x0018
        L_0x0017:
            r2 = r12
        L_0x0018:
            r5 = r0 & 8
            if (r5 == 0) goto L_0x001e
            r5 = r4
            goto L_0x001f
        L_0x001e:
            r5 = r13
        L_0x001f:
            r6 = r0 & 16
            if (r6 == 0) goto L_0x0025
            r6 = r4
            goto L_0x0026
        L_0x0025:
            r6 = r14
        L_0x0026:
            r7 = r0 & 32
            if (r7 == 0) goto L_0x002c
            r7 = r4
            goto L_0x002d
        L_0x002c:
            r7 = r15
        L_0x002d:
            r8 = r0 & 64
            if (r8 == 0) goto L_0x0033
            r8 = r4
            goto L_0x0035
        L_0x0033:
            r8 = r16
        L_0x0035:
            r0 = r0 & 128(0x80, float:1.794E-43)
            if (r0 == 0) goto L_0x003a
            goto L_0x003c
        L_0x003a:
            r4 = r17
        L_0x003c:
            r10 = r9
            r11 = r1
            r12 = r3
            r13 = r2
            r14 = r5
            r15 = r6
            r16 = r7
            r17 = r8
            r18 = r4
            r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.openrtb.request.User.<init>(int, java.lang.String, int, java.lang.String, java.lang.String, java.lang.String, com.adsbynimbus.openrtb.request.Data[], com.adsbynimbus.openrtb.request.User$Extension, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 &2\u00020\u0001:\u0002%&B\u0001\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\u0010\b\u0001\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b\u0012\u0016\b\u0001\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000e\u0012\u0016\b\u0001\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000e\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010\u0012B\u0001\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b\u0012\u0016\b\u0002\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000e\u0012\u0016\b\u0002\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000e¢\u0006\u0002\u0010\u0013J&\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00002\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#HÁ\u0001¢\u0006\u0002\b$R\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0016\u0010\u0015R \u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b8\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0017\u0010\u0015R\u001a\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0018\u0010\u0015R&\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000e8\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0019\u0010\u0015R&\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000e8\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u001a\u0010\u0015R\u001a\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u001b\u0010\u0015R\u001a\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u001c\u0010\u0015¨\u0006'"}, d2 = {"Lcom/adsbynimbus/openrtb/request/User$Extension;", "", "seen1", "", "consent", "", "admob_gde_signals", "facebook_buyeruid", "unity_buyeruid", "vungle_buyeruid", "eids", "", "Lcom/adsbynimbus/openrtb/request/EID;", "mfx_buyerdata", "", "mintegral_sdk", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Set;Ljava/util/Map;Ljava/util/Map;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Set;Ljava/util/Map;Ljava/util/Map;)V", "getAdmob_gde_signals$annotations", "()V", "getConsent$annotations", "getEids$annotations", "getFacebook_buyeruid$annotations", "getMfx_buyerdata$annotations", "getMintegral_sdk$annotations", "getUnity_buyeruid$annotations", "getVungle_buyeruid$annotations", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$kotlin_release", "$serializer", "Companion", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
    @Serializable
    /* compiled from: User.kt */
    public static final class Extension {
        /* access modifiers changed from: private */
        public static final KSerializer<Object>[] $childSerializers = {null, null, null, null, null, new LinkedHashSetSerializer(EID$$serializer.INSTANCE), new LinkedHashMapSerializer(StringSerializer.INSTANCE, StringSerializer.INSTANCE), new LinkedHashMapSerializer(StringSerializer.INSTANCE, StringSerializer.INSTANCE)};
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        public String admob_gde_signals;
        public String consent;
        public Set<EID> eids;
        public String facebook_buyeruid;
        public Map<String, String> mfx_buyerdata;
        public Map<String, String> mintegral_sdk;
        public String unity_buyeruid;
        public String vungle_buyeruid;

        public Extension() {
            this((String) null, (String) null, (String) null, (String) null, (String) null, (Set) null, (Map) null, (Map) null, 255, (DefaultConstructorMarker) null);
        }

        @SerialName("admob_gde_signals")
        public static /* synthetic */ void getAdmob_gde_signals$annotations() {
        }

        @SerialName("consent")
        public static /* synthetic */ void getConsent$annotations() {
        }

        @SerialName("eids")
        public static /* synthetic */ void getEids$annotations() {
        }

        @SerialName("facebook_buyeruid")
        public static /* synthetic */ void getFacebook_buyeruid$annotations() {
        }

        @SerialName("mfx_buyerdata")
        public static /* synthetic */ void getMfx_buyerdata$annotations() {
        }

        @SerialName("mintegral_sdk")
        public static /* synthetic */ void getMintegral_sdk$annotations() {
        }

        @SerialName("unity_buyeruid")
        public static /* synthetic */ void getUnity_buyeruid$annotations() {
        }

        @SerialName("vungle_buyeruid")
        public static /* synthetic */ void getVungle_buyeruid$annotations() {
        }

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/adsbynimbus/openrtb/request/User$Extension$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/adsbynimbus/openrtb/request/User$Extension;", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
        /* compiled from: User.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Extension> serializer() {
                return User$Extension$$serializer.INSTANCE;
            }
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ Extension(int i, @SerialName("consent") String str, @SerialName("admob_gde_signals") String str2, @SerialName("facebook_buyeruid") String str3, @SerialName("unity_buyeruid") String str4, @SerialName("vungle_buyeruid") String str5, @SerialName("eids") Set set, @SerialName("mfx_buyerdata") Map map, @SerialName("mintegral_sdk") Map map2, SerializationConstructorMarker serializationConstructorMarker) {
            if ((i & 0) != 0) {
                PluginExceptionsKt.throwMissingFieldException(i, 0, User$Extension$$serializer.INSTANCE.getDescriptor());
            }
            if ((i & 1) == 0) {
                this.consent = null;
            } else {
                this.consent = str;
            }
            if ((i & 2) == 0) {
                this.admob_gde_signals = null;
            } else {
                this.admob_gde_signals = str2;
            }
            if ((i & 4) == 0) {
                this.facebook_buyeruid = null;
            } else {
                this.facebook_buyeruid = str3;
            }
            if ((i & 8) == 0) {
                this.unity_buyeruid = null;
            } else {
                this.unity_buyeruid = str4;
            }
            if ((i & 16) == 0) {
                this.vungle_buyeruid = null;
            } else {
                this.vungle_buyeruid = str5;
            }
            if ((i & 32) == 0) {
                this.eids = null;
            } else {
                this.eids = set;
            }
            if ((i & 64) == 0) {
                this.mfx_buyerdata = null;
            } else {
                this.mfx_buyerdata = map;
            }
            if ((i & 128) == 0) {
                this.mintegral_sdk = null;
            } else {
                this.mintegral_sdk = map2;
            }
        }

        public Extension(String str, String str2, String str3, String str4, String str5, Set<EID> set, Map<String, String> map, Map<String, String> map2) {
            this.consent = str;
            this.admob_gde_signals = str2;
            this.facebook_buyeruid = str3;
            this.unity_buyeruid = str4;
            this.vungle_buyeruid = str5;
            this.eids = set;
            this.mfx_buyerdata = map;
            this.mintegral_sdk = map2;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$kotlin_release(Extension extension, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
            KSerializer<Object>[] kSerializerArr = $childSerializers;
            boolean z = false;
            if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 0) || extension.consent != null) {
                compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 0, StringSerializer.INSTANCE, extension.consent);
            }
            if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 1) || extension.admob_gde_signals != null) {
                compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 1, StringSerializer.INSTANCE, extension.admob_gde_signals);
            }
            if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 2) || extension.facebook_buyeruid != null) {
                compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 2, StringSerializer.INSTANCE, extension.facebook_buyeruid);
            }
            if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 3) || extension.unity_buyeruid != null) {
                compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 3, StringSerializer.INSTANCE, extension.unity_buyeruid);
            }
            if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 4) || extension.vungle_buyeruid != null) {
                compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 4, StringSerializer.INSTANCE, extension.vungle_buyeruid);
            }
            if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 5) || extension.eids != null) {
                compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 5, kSerializerArr[5], extension.eids);
            }
            if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 6) || extension.mfx_buyerdata != null) {
                compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 6, kSerializerArr[6], extension.mfx_buyerdata);
            }
            if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 7) || extension.mintegral_sdk != null) {
                z = true;
            }
            if (z) {
                compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 7, kSerializerArr[7], extension.mintegral_sdk);
            }
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public /* synthetic */ Extension(java.lang.String r10, java.lang.String r11, java.lang.String r12, java.lang.String r13, java.lang.String r14, java.util.Set r15, java.util.Map r16, java.util.Map r17, int r18, kotlin.jvm.internal.DefaultConstructorMarker r19) {
            /*
                r9 = this;
                r0 = r18
                r1 = r0 & 1
                r2 = 0
                if (r1 == 0) goto L_0x0009
                r1 = r2
                goto L_0x000a
            L_0x0009:
                r1 = r10
            L_0x000a:
                r3 = r0 & 2
                if (r3 == 0) goto L_0x0010
                r3 = r2
                goto L_0x0011
            L_0x0010:
                r3 = r11
            L_0x0011:
                r4 = r0 & 4
                if (r4 == 0) goto L_0x0017
                r4 = r2
                goto L_0x0018
            L_0x0017:
                r4 = r12
            L_0x0018:
                r5 = r0 & 8
                if (r5 == 0) goto L_0x001e
                r5 = r2
                goto L_0x001f
            L_0x001e:
                r5 = r13
            L_0x001f:
                r6 = r0 & 16
                if (r6 == 0) goto L_0x0025
                r6 = r2
                goto L_0x0026
            L_0x0025:
                r6 = r14
            L_0x0026:
                r7 = r0 & 32
                if (r7 == 0) goto L_0x002c
                r7 = r2
                goto L_0x002d
            L_0x002c:
                r7 = r15
            L_0x002d:
                r8 = r0 & 64
                if (r8 == 0) goto L_0x0033
                r8 = r2
                goto L_0x0035
            L_0x0033:
                r8 = r16
            L_0x0035:
                r0 = r0 & 128(0x80, float:1.794E-43)
                if (r0 == 0) goto L_0x003a
                goto L_0x003c
            L_0x003a:
                r2 = r17
            L_0x003c:
                r10 = r9
                r11 = r1
                r12 = r3
                r13 = r4
                r14 = r5
                r15 = r6
                r16 = r7
                r17 = r8
                r18 = r2
                r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.openrtb.request.User.Extension.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Set, java.util.Map, java.util.Map, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
        }
    }
}

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
import kotlinx.serialization.internal.ByteSerializer;
import kotlinx.serialization.internal.FloatSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 (2\u00020\u0001:\u0002'(By\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\u0011Be\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\u0012J&\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%HÁ\u0001¢\u0006\u0002\b&R\u001c\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u000e¢\u0006\n\n\u0002\u0010\u0015\u0012\u0004\b\u0013\u0010\u0014R\u001a\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0016\u0010\u0014R\u001a\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0017\u0010\u0014R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u000e¢\u0006\n\n\u0002\u0010\u0019\u0012\u0004\b\u0018\u0010\u0014R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u000e¢\u0006\n\n\u0002\u0010\u0019\u0012\u0004\b\u001a\u0010\u0014R\u001a\u0010\r\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u001b\u0010\u0014R\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u001c\u0010\u0014R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006@\u0006X\u000e¢\u0006\n\n\u0002\u0010\u001e\u0012\u0004\b\u001d\u0010\u0014¨\u0006)"}, d2 = {"Lcom/adsbynimbus/openrtb/request/Geo;", "", "seen1", "", "lat", "", "lon", "type", "", "accuracy", "country", "", "city", "metro", "state", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Float;Ljava/lang/Float;Ljava/lang/Byte;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/Byte;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAccuracy$annotations", "()V", "Ljava/lang/Integer;", "getCity$annotations", "getCountry$annotations", "getLat$annotations", "Ljava/lang/Float;", "getLon$annotations", "getMetro$annotations", "getState$annotations", "getType$annotations", "Ljava/lang/Byte;", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$kotlin_release", "$serializer", "Companion", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
@Serializable
/* compiled from: Geo.kt */
public final class Geo {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public Integer accuracy;
    public String city;
    public String country;
    public Float lat;
    public Float lon;
    public String metro;
    public String state;
    public Byte type;

    public Geo() {
        this((Float) null, (Float) null, (Byte) null, (Integer) null, (String) null, (String) null, (String) null, (String) null, 255, (DefaultConstructorMarker) null);
    }

    @SerialName("accuracy")
    public static /* synthetic */ void getAccuracy$annotations() {
    }

    @SerialName("city")
    public static /* synthetic */ void getCity$annotations() {
    }

    @SerialName("country")
    public static /* synthetic */ void getCountry$annotations() {
    }

    @SerialName("lat")
    public static /* synthetic */ void getLat$annotations() {
    }

    @SerialName("lon")
    public static /* synthetic */ void getLon$annotations() {
    }

    @SerialName("metro")
    public static /* synthetic */ void getMetro$annotations() {
    }

    @SerialName("state")
    public static /* synthetic */ void getState$annotations() {
    }

    @SerialName("type")
    public static /* synthetic */ void getType$annotations() {
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/adsbynimbus/openrtb/request/Geo$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/adsbynimbus/openrtb/request/Geo;", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
    /* compiled from: Geo.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<Geo> serializer() {
            return Geo$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ Geo(int i, @SerialName("lat") Float f, @SerialName("lon") Float f2, @SerialName("type") Byte b, @SerialName("accuracy") Integer num, @SerialName("country") String str, @SerialName("city") String str2, @SerialName("metro") String str3, @SerialName("state") String str4, SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 0) != 0) {
            PluginExceptionsKt.throwMissingFieldException(i, 0, Geo$$serializer.INSTANCE.getDescriptor());
        }
        if ((i & 1) == 0) {
            this.lat = null;
        } else {
            this.lat = f;
        }
        if ((i & 2) == 0) {
            this.lon = null;
        } else {
            this.lon = f2;
        }
        if ((i & 4) == 0) {
            this.type = null;
        } else {
            this.type = b;
        }
        if ((i & 8) == 0) {
            this.accuracy = null;
        } else {
            this.accuracy = num;
        }
        if ((i & 16) == 0) {
            this.country = null;
        } else {
            this.country = str;
        }
        if ((i & 32) == 0) {
            this.city = null;
        } else {
            this.city = str2;
        }
        if ((i & 64) == 0) {
            this.metro = null;
        } else {
            this.metro = str3;
        }
        if ((i & 128) == 0) {
            this.state = null;
        } else {
            this.state = str4;
        }
    }

    public Geo(Float f, Float f2, Byte b, Integer num, String str, String str2, String str3, String str4) {
        this.lat = f;
        this.lon = f2;
        this.type = b;
        this.accuracy = num;
        this.country = str;
        this.city = str2;
        this.metro = str3;
        this.state = str4;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$kotlin_release(Geo geo, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        boolean z = false;
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 0) || geo.lat != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 0, FloatSerializer.INSTANCE, geo.lat);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 1) || geo.lon != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 1, FloatSerializer.INSTANCE, geo.lon);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 2) || geo.type != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 2, ByteSerializer.INSTANCE, geo.type);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 3) || geo.accuracy != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 3, IntSerializer.INSTANCE, geo.accuracy);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 4) || geo.country != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 4, StringSerializer.INSTANCE, geo.country);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 5) || geo.city != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 5, StringSerializer.INSTANCE, geo.city);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 6) || geo.metro != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 6, StringSerializer.INSTANCE, geo.metro);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 7) || geo.state != null) {
            z = true;
        }
        if (z) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 7, StringSerializer.INSTANCE, geo.state);
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ Geo(java.lang.Float r10, java.lang.Float r11, java.lang.Byte r12, java.lang.Integer r13, java.lang.String r14, java.lang.String r15, java.lang.String r16, java.lang.String r17, int r18, kotlin.jvm.internal.DefaultConstructorMarker r19) {
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
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.openrtb.request.Geo.<init>(java.lang.Float, java.lang.Float, java.lang.Byte, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }
}

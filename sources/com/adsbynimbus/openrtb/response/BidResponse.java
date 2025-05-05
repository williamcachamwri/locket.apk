package com.adsbynimbus.openrtb.response;

import com.adsbynimbus.openrtb.request.BidRequest;
import com.facebook.react.uimanager.ViewProps;
import io.sentry.protocol.Response;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.MapsKt;
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

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u0005\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 K2\u00020\u0001:\u0003JKLB\u0002\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0010\b\u0001\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\b\u0012\b\b\u0001\u0010\t\u001a\u00020\u0003\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0001\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u000f\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0010\u001a\u00020\u0011\u0012\n\b\u0001\u0010\u0012\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0013\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0014\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0001\u0010\u0015\u001a\u00020\u0011\u0012\n\b\u0001\u0010\u0016\u001a\u0004\u0018\u00010\u0005\u0012\u001c\b\u0001\u0010\u0017\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\b\u0018\u00010\u0018\u0012\b\b\u0001\u0010\u0019\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u001a\u001a\u00020\u0003\u0012\u0016\b\u0001\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0018\u0012\n\b\u0001\u0010\u001c\u001a\u0004\u0018\u00010\u001d\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f¢\u0006\u0002\u0010 Bñ\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0011\u0012\u0006\u0010\u0016\u001a\u00020\u0005\u0012\u001a\b\u0002\u0010\u0017\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\b0\u0018\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u0003\u0012\u0014\b\u0002\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0018\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u001d¢\u0006\u0002\u0010!J&\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020\u00002\u0006\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020HHÁ\u0001¢\u0006\u0002\bIR \u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\b8\u0006X\u0004¢\u0006\n\n\u0002\u0010$\u0012\u0004\b\"\u0010#R\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b%\u0010#R\u0016\u0010\t\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b&\u0010#R\u0016\u0010\n\u001a\u00020\u000b8\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b'\u0010#R\u0019\u0010(\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\b8F¢\u0006\u0006\u001a\u0004\b)\u0010*R\u0018\u0010\f\u001a\u0004\u0018\u00010\u00058\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b+\u0010#R\u0018\u0010\r\u001a\u0004\u0018\u00010\u00058\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b,\u0010#R\u0016\u0010\u0019\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b-\u0010#R\u0016\u0010\u001a\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b.\u0010#R\u0016\u0010\u001c\u001a\u00020\u001d8\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b/\u0010#R\"\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00188\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b0\u0010#R\u0016\u0010\u000e\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b1\u0010#R\u0019\u00102\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\b8F¢\u0006\u0006\u001a\u0004\b3\u0010*R\u0016\u0010\u0010\u001a\u00020\u00118\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b4\u0010#R\u0016\u0010\u0015\u001a\u00020\u00118\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b5\u0010#R\u0013\u00106\u001a\u0004\u0018\u00010\u00058F¢\u0006\u0006\u001a\u0004\b7\u00108R\u0016\u0010\u0012\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b9\u0010#R\u0016\u0010\u0013\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b:\u0010#R\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00058\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b;\u0010#R\u0016\u0010\u0016\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b<\u0010#R(\u0010\u0017\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\b0\u00188\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b=\u0010#R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b>\u0010#R\u0016\u0010\u000f\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b?\u0010#R\u0013\u0010@\u001a\u0004\u0018\u00010\u00058F¢\u0006\u0006\u001a\u0004\bA\u00108¨\u0006M"}, d2 = {"Lcom/adsbynimbus/openrtb/response/BidResponse;", "", "seen1", "", "type", "", "auction_id", "adomain", "", "bid_in_cents", "bid_raw", "", "content_type", "crid", "height", "width", "is_interstitial", "", "markup", "network", "placement_id", "is_mraid", "position", "trackers", "", "duration", "exp", "external_notifications", "ext", "Lcom/adsbynimbus/openrtb/response/BidResponse$Extension;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;[Ljava/lang/String;IFLjava/lang/String;Ljava/lang/String;IIBLjava/lang/String;Ljava/lang/String;Ljava/lang/String;BLjava/lang/String;Ljava/util/Map;IILjava/util/Map;Lcom/adsbynimbus/openrtb/response/BidResponse$Extension;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;IFLjava/lang/String;Ljava/lang/String;IIBLjava/lang/String;Ljava/lang/String;Ljava/lang/String;BLjava/lang/String;Ljava/util/Map;IILjava/util/Map;Lcom/adsbynimbus/openrtb/response/BidResponse$Extension;)V", "getAdomain$annotations", "()V", "[Ljava/lang/String;", "getAuction_id$annotations", "getBid_in_cents$annotations", "getBid_raw$annotations", "click_trackers", "getClick_trackers", "()[Ljava/lang/String;", "getContent_type$annotations", "getCrid$annotations", "getDuration$annotations", "getExp$annotations", "getExt$annotations", "getExternal_notifications$annotations", "getHeight$annotations", "impression_trackers", "getImpression_trackers", "is_interstitial$annotations", "is_mraid$annotations", "loss_response", "getLoss_response", "()Ljava/lang/String;", "getMarkup$annotations", "getNetwork$annotations", "getPlacement_id$annotations", "getPosition$annotations", "getTrackers$annotations", "getType$annotations", "getWidth$annotations", "win_response", "getWin_response", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$kotlin_release", "$serializer", "Companion", "Extension", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
@Serializable
/* compiled from: BidResponse.kt */
public final class BidResponse {
    /* access modifiers changed from: private */
    public static final KSerializer<Object>[] $childSerializers = {null, null, new ReferenceArraySerializer(Reflection.getOrCreateKotlinClass(String.class), StringSerializer.INSTANCE), null, null, null, null, null, null, null, null, null, null, null, null, new LinkedHashMapSerializer(StringSerializer.INSTANCE, new ReferenceArraySerializer(Reflection.getOrCreateKotlinClass(String.class), StringSerializer.INSTANCE)), null, null, new LinkedHashMapSerializer(StringSerializer.INSTANCE, StringSerializer.INSTANCE), null};
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public final String[] adomain;
    public final String auction_id;
    public final int bid_in_cents;
    public final float bid_raw;
    public final String content_type;
    public final String crid;
    public final int duration;
    public final int exp;
    public final Extension ext;
    public final Map<String, String> external_notifications;
    public final int height;
    public final byte is_interstitial;
    public final byte is_mraid;
    public final String markup;
    public final String network;
    public final String placement_id;
    public final String position;
    public final Map<String, String[]> trackers;
    public final String type;
    public final int width;

    @JvmStatic
    public static final BidResponse fromJson(String str) {
        return Companion.fromJson(str);
    }

    @JvmStatic
    public static final BidResponse fromJson(String str, Json json) {
        return Companion.fromJson(str, json);
    }

    @SerialName("adomain")
    public static /* synthetic */ void getAdomain$annotations() {
    }

    @SerialName("auction_id")
    public static /* synthetic */ void getAuction_id$annotations() {
    }

    @SerialName("bid_in_cents")
    public static /* synthetic */ void getBid_in_cents$annotations() {
    }

    @SerialName("bid_raw")
    public static /* synthetic */ void getBid_raw$annotations() {
    }

    @SerialName("content_type")
    public static /* synthetic */ void getContent_type$annotations() {
    }

    @SerialName("crid")
    public static /* synthetic */ void getCrid$annotations() {
    }

    @SerialName("duration")
    public static /* synthetic */ void getDuration$annotations() {
    }

    @SerialName("exp")
    public static /* synthetic */ void getExp$annotations() {
    }

    @SerialName("ext")
    public static /* synthetic */ void getExt$annotations() {
    }

    @SerialName("external_notifications")
    public static /* synthetic */ void getExternal_notifications$annotations() {
    }

    @SerialName("height")
    public static /* synthetic */ void getHeight$annotations() {
    }

    @SerialName("markup")
    public static /* synthetic */ void getMarkup$annotations() {
    }

    @SerialName("network")
    public static /* synthetic */ void getNetwork$annotations() {
    }

    @SerialName("placement_id")
    public static /* synthetic */ void getPlacement_id$annotations() {
    }

    @SerialName("position")
    public static /* synthetic */ void getPosition$annotations() {
    }

    @SerialName("trackers")
    public static /* synthetic */ void getTrackers$annotations() {
    }

    @SerialName("type")
    public static /* synthetic */ void getType$annotations() {
    }

    @SerialName("width")
    public static /* synthetic */ void getWidth$annotations() {
    }

    @SerialName("is_interstitial")
    public static /* synthetic */ void is_interstitial$annotations() {
    }

    @SerialName("is_mraid")
    public static /* synthetic */ void is_mraid$annotations() {
    }

    @JvmStatic
    public static final String toJson(BidResponse bidResponse) {
        return Companion.toJson(bidResponse);
    }

    @JvmStatic
    public static final String toJson(BidResponse bidResponse, Json json) {
        return Companion.toJson(bidResponse, json);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ BidResponse(int i, @SerialName("type") String str, @SerialName("auction_id") String str2, @SerialName("adomain") String[] strArr, @SerialName("bid_in_cents") int i2, @SerialName("bid_raw") float f, @SerialName("content_type") String str3, @SerialName("crid") String str4, @SerialName("height") int i3, @SerialName("width") int i4, @SerialName("is_interstitial") byte b, @SerialName("markup") String str5, @SerialName("network") String str6, @SerialName("placement_id") String str7, @SerialName("is_mraid") byte b2, @SerialName("position") String str8, @SerialName("trackers") Map map, @SerialName("duration") int i5, @SerialName("exp") int i6, @SerialName("external_notifications") Map map2, @SerialName("ext") Extension extension, SerializationConstructorMarker serializationConstructorMarker) {
        Map map3;
        Map map4;
        int i7 = i;
        if (17411 != (i7 & 17411)) {
            PluginExceptionsKt.throwMissingFieldException(i, 17411, BidResponse$$serializer.INSTANCE.getDescriptor());
        }
        this.type = str;
        this.auction_id = str2;
        if ((i7 & 4) == 0) {
            this.adomain = null;
        } else {
            this.adomain = strArr;
        }
        if ((i7 & 8) == 0) {
            this.bid_in_cents = 0;
        } else {
            this.bid_in_cents = i2;
        }
        this.bid_raw = (i7 & 16) == 0 ? 0.0f : f;
        if ((i7 & 32) == 0) {
            this.content_type = null;
        } else {
            this.content_type = str3;
        }
        if ((i7 & 64) == 0) {
            this.crid = null;
        } else {
            this.crid = str4;
        }
        if ((i7 & 128) == 0) {
            this.height = 0;
        } else {
            this.height = i3;
        }
        if ((i7 & 256) == 0) {
            this.width = 0;
        } else {
            this.width = i4;
        }
        if ((i7 & 512) == 0) {
            this.is_interstitial = 0;
        } else {
            this.is_interstitial = b;
        }
        this.markup = str5;
        this.network = (i7 & 2048) == 0 ? "" : str6;
        if ((i7 & 4096) == 0) {
            this.placement_id = null;
        } else {
            this.placement_id = str7;
        }
        if ((i7 & 8192) == 0) {
            this.is_mraid = 0;
        } else {
            this.is_mraid = b2;
        }
        this.position = str8;
        if ((32768 & i7) == 0) {
            map3 = MapsKt.emptyMap();
        } else {
            map3 = map;
        }
        this.trackers = map3;
        if ((65536 & i7) == 0) {
            this.duration = 0;
        } else {
            this.duration = i5;
        }
        this.exp = (131072 & i7) == 0 ? -1 : i6;
        if ((262144 & i7) == 0) {
            map4 = MapsKt.emptyMap();
        } else {
            map4 = map2;
        }
        this.external_notifications = map4;
        this.ext = (i7 & 524288) == 0 ? new Extension(false, 1, (DefaultConstructorMarker) null) : extension;
    }

    public BidResponse(String str, String str2, String[] strArr, int i, float f, String str3, String str4, int i2, int i3, byte b, String str5, String str6, String str7, byte b2, String str8, Map<String, String[]> map, int i4, int i5, Map<String, String> map2, Extension extension) {
        String str9 = str5;
        String str10 = str6;
        String str11 = str8;
        Map<String, String[]> map3 = map;
        Map<String, String> map4 = map2;
        Extension extension2 = extension;
        Intrinsics.checkNotNullParameter(str, "type");
        Intrinsics.checkNotNullParameter(str2, "auction_id");
        Intrinsics.checkNotNullParameter(str9, "markup");
        Intrinsics.checkNotNullParameter(str10, "network");
        Intrinsics.checkNotNullParameter(str11, ViewProps.POSITION);
        Intrinsics.checkNotNullParameter(map3, "trackers");
        Intrinsics.checkNotNullParameter(map4, "external_notifications");
        Intrinsics.checkNotNullParameter(extension2, "ext");
        this.type = str;
        this.auction_id = str2;
        this.adomain = strArr;
        this.bid_in_cents = i;
        this.bid_raw = f;
        this.content_type = str3;
        this.crid = str4;
        this.height = i2;
        this.width = i3;
        this.is_interstitial = b;
        this.markup = str9;
        this.network = str10;
        this.placement_id = str7;
        this.is_mraid = b2;
        this.position = str11;
        this.trackers = map3;
        this.duration = i4;
        this.exp = i5;
        this.external_notifications = map4;
        this.ext = extension2;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$kotlin_release(BidResponse bidResponse, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        boolean z;
        boolean z2;
        KSerializer<Object>[] kSerializerArr = $childSerializers;
        boolean z3 = false;
        compositeEncoder.encodeStringElement(serialDescriptor, 0, bidResponse.type);
        compositeEncoder.encodeStringElement(serialDescriptor, 1, bidResponse.auction_id);
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 2) || bidResponse.adomain != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 2, kSerializerArr[2], bidResponse.adomain);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 3) || bidResponse.bid_in_cents != 0) {
            compositeEncoder.encodeIntElement(serialDescriptor, 3, bidResponse.bid_in_cents);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 4) || Float.compare(bidResponse.bid_raw, 0.0f) != 0) {
            compositeEncoder.encodeFloatElement(serialDescriptor, 4, bidResponse.bid_raw);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 5) || bidResponse.content_type != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 5, StringSerializer.INSTANCE, bidResponse.content_type);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 6) || bidResponse.crid != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 6, StringSerializer.INSTANCE, bidResponse.crid);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 7) || bidResponse.height != 0) {
            compositeEncoder.encodeIntElement(serialDescriptor, 7, bidResponse.height);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 8) || bidResponse.width != 0) {
            compositeEncoder.encodeIntElement(serialDescriptor, 8, bidResponse.width);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 9) || bidResponse.is_interstitial != 0) {
            compositeEncoder.encodeByteElement(serialDescriptor, 9, bidResponse.is_interstitial);
        }
        compositeEncoder.encodeStringElement(serialDescriptor, 10, bidResponse.markup);
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 11) || !Intrinsics.areEqual((Object) bidResponse.network, (Object) "")) {
            compositeEncoder.encodeStringElement(serialDescriptor, 11, bidResponse.network);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 12) || bidResponse.placement_id != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 12, StringSerializer.INSTANCE, bidResponse.placement_id);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 13) || bidResponse.is_mraid != 0) {
            compositeEncoder.encodeByteElement(serialDescriptor, 13, bidResponse.is_mraid);
        }
        compositeEncoder.encodeStringElement(serialDescriptor, 14, bidResponse.position);
        if (!compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 15) && Intrinsics.areEqual((Object) bidResponse.trackers, (Object) MapsKt.emptyMap())) {
            z = false;
        } else {
            z = true;
        }
        if (z) {
            compositeEncoder.encodeSerializableElement(serialDescriptor, 15, kSerializerArr[15], bidResponse.trackers);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 16) || bidResponse.duration != 0) {
            compositeEncoder.encodeIntElement(serialDescriptor, 16, bidResponse.duration);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 17) || bidResponse.exp != -1) {
            compositeEncoder.encodeIntElement(serialDescriptor, 17, bidResponse.exp);
        }
        if (!compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 18) && Intrinsics.areEqual((Object) bidResponse.external_notifications, (Object) MapsKt.emptyMap())) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (z2) {
            compositeEncoder.encodeSerializableElement(serialDescriptor, 18, kSerializerArr[18], bidResponse.external_notifications);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 19) || !Intrinsics.areEqual((Object) bidResponse.ext, (Object) new Extension(false, 1, (DefaultConstructorMarker) null))) {
            z3 = true;
        }
        if (z3) {
            compositeEncoder.encodeSerializableElement(serialDescriptor, 19, BidResponse$Extension$$serializer.INSTANCE, bidResponse.ext);
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ BidResponse(java.lang.String r25, java.lang.String r26, java.lang.String[] r27, int r28, float r29, java.lang.String r30, java.lang.String r31, int r32, int r33, byte r34, java.lang.String r35, java.lang.String r36, java.lang.String r37, byte r38, java.lang.String r39, java.util.Map r40, int r41, int r42, java.util.Map r43, com.adsbynimbus.openrtb.response.BidResponse.Extension r44, int r45, kotlin.jvm.internal.DefaultConstructorMarker r46) {
        /*
            r24 = this;
            r0 = r45
            r1 = r0 & 4
            r2 = 0
            if (r1 == 0) goto L_0x0009
            r6 = r2
            goto L_0x000b
        L_0x0009:
            r6 = r27
        L_0x000b:
            r1 = r0 & 8
            r3 = 0
            if (r1 == 0) goto L_0x0012
            r7 = r3
            goto L_0x0014
        L_0x0012:
            r7 = r28
        L_0x0014:
            r1 = r0 & 16
            if (r1 == 0) goto L_0x001b
            r1 = 0
            r8 = r1
            goto L_0x001d
        L_0x001b:
            r8 = r29
        L_0x001d:
            r1 = r0 & 32
            if (r1 == 0) goto L_0x0023
            r9 = r2
            goto L_0x0025
        L_0x0023:
            r9 = r30
        L_0x0025:
            r1 = r0 & 64
            if (r1 == 0) goto L_0x002b
            r10 = r2
            goto L_0x002d
        L_0x002b:
            r10 = r31
        L_0x002d:
            r1 = r0 & 128(0x80, float:1.794E-43)
            if (r1 == 0) goto L_0x0033
            r11 = r3
            goto L_0x0035
        L_0x0033:
            r11 = r32
        L_0x0035:
            r1 = r0 & 256(0x100, float:3.59E-43)
            if (r1 == 0) goto L_0x003b
            r12 = r3
            goto L_0x003d
        L_0x003b:
            r12 = r33
        L_0x003d:
            r1 = r0 & 512(0x200, float:7.175E-43)
            if (r1 == 0) goto L_0x0043
            r13 = r3
            goto L_0x0045
        L_0x0043:
            r13 = r34
        L_0x0045:
            r1 = r0 & 2048(0x800, float:2.87E-42)
            if (r1 == 0) goto L_0x004d
            java.lang.String r1 = ""
            r15 = r1
            goto L_0x004f
        L_0x004d:
            r15 = r36
        L_0x004f:
            r1 = r0 & 4096(0x1000, float:5.74E-42)
            if (r1 == 0) goto L_0x0056
            r16 = r2
            goto L_0x0058
        L_0x0056:
            r16 = r37
        L_0x0058:
            r1 = r0 & 8192(0x2000, float:1.14794E-41)
            if (r1 == 0) goto L_0x005f
            r17 = r3
            goto L_0x0061
        L_0x005f:
            r17 = r38
        L_0x0061:
            r1 = 32768(0x8000, float:4.5918E-41)
            r1 = r1 & r0
            if (r1 == 0) goto L_0x006e
            java.util.Map r1 = kotlin.collections.MapsKt.emptyMap()
            r19 = r1
            goto L_0x0070
        L_0x006e:
            r19 = r40
        L_0x0070:
            r1 = 65536(0x10000, float:9.18355E-41)
            r1 = r1 & r0
            if (r1 == 0) goto L_0x0078
            r20 = r3
            goto L_0x007a
        L_0x0078:
            r20 = r41
        L_0x007a:
            r1 = 131072(0x20000, float:1.83671E-40)
            r1 = r1 & r0
            if (r1 == 0) goto L_0x0083
            r1 = -1
            r21 = r1
            goto L_0x0085
        L_0x0083:
            r21 = r42
        L_0x0085:
            r1 = 262144(0x40000, float:3.67342E-40)
            r1 = r1 & r0
            if (r1 == 0) goto L_0x0091
            java.util.Map r1 = kotlin.collections.MapsKt.emptyMap()
            r22 = r1
            goto L_0x0093
        L_0x0091:
            r22 = r43
        L_0x0093:
            r1 = 524288(0x80000, float:7.34684E-40)
            r0 = r0 & r1
            if (r0 == 0) goto L_0x00a1
            com.adsbynimbus.openrtb.response.BidResponse$Extension r0 = new com.adsbynimbus.openrtb.response.BidResponse$Extension
            r1 = 1
            r0.<init>((boolean) r3, (int) r1, (kotlin.jvm.internal.DefaultConstructorMarker) r2)
            r23 = r0
            goto L_0x00a3
        L_0x00a1:
            r23 = r44
        L_0x00a3:
            r3 = r24
            r4 = r25
            r5 = r26
            r14 = r35
            r18 = r39
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.openrtb.response.BidResponse.<init>(java.lang.String, java.lang.String, java.lang.String[], int, float, java.lang.String, java.lang.String, int, int, byte, java.lang.String, java.lang.String, java.lang.String, byte, java.lang.String, java.util.Map, int, int, java.util.Map, com.adsbynimbus.openrtb.response.BidResponse$Extension, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final String[] getImpression_trackers() {
        return this.trackers.get("impression_trackers");
    }

    public final String[] getClick_trackers() {
        return this.trackers.get("click_trackers");
    }

    public final String getWin_response() {
        return this.external_notifications.get("win_response");
    }

    public final String getLoss_response() {
        return this.external_notifications.get("loss_response");
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007J\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\nHÆ\u0001J\u001a\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\r"}, d2 = {"Lcom/adsbynimbus/openrtb/response/BidResponse$Companion;", "", "()V", "fromJson", "Lcom/adsbynimbus/openrtb/response/BidResponse;", "json", "", "jsonSerializer", "Lkotlinx/serialization/json/Json;", "serializer", "Lkotlinx/serialization/KSerializer;", "toJson", "response", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
    /* compiled from: BidResponse.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final BidResponse fromJson(String str) {
            Intrinsics.checkNotNullParameter(str, "json");
            return fromJson$default(this, str, (Json) null, 2, (Object) null);
        }

        @JvmStatic
        public final String toJson(BidResponse bidResponse) {
            Intrinsics.checkNotNullParameter(bidResponse, Response.TYPE);
            return toJson$default(this, bidResponse, (Json) null, 2, (Object) null);
        }

        private Companion() {
        }

        public final KSerializer<BidResponse> serializer() {
            return BidResponse$$serializer.INSTANCE;
        }

        public static /* synthetic */ BidResponse fromJson$default(Companion companion, String str, Json json, int i, Object obj) {
            if ((i & 2) != 0) {
                json = BidRequest.lenientSerializer;
            }
            return companion.fromJson(str, json);
        }

        @JvmStatic
        public final BidResponse fromJson(String str, Json json) {
            Intrinsics.checkNotNullParameter(str, "json");
            Intrinsics.checkNotNullParameter(json, "jsonSerializer");
            return (BidResponse) json.decodeFromString(serializer(), str);
        }

        public static /* synthetic */ String toJson$default(Companion companion, BidResponse bidResponse, Json json, int i, Object obj) {
            if ((i & 2) != 0) {
                json = BidRequest.lenientSerializer;
            }
            return companion.toJson(bidResponse, json);
        }

        @JvmStatic
        public final String toJson(BidResponse bidResponse, Json json) {
            Intrinsics.checkNotNullParameter(bidResponse, Response.TYPE);
            Intrinsics.checkNotNullParameter(json, "jsonSerializer");
            return json.encodeToString(serializer(), bidResponse);
        }
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00152\u00020\u0001:\u0002\u0014\u0015B#\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bB\u000f\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ&\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012HÁ\u0001¢\u0006\u0002\b\u0013R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/adsbynimbus/openrtb/response/BidResponse$Extension;", "", "seen1", "", "use_new_renderer", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IZLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Z)V", "getUse_new_renderer$annotations", "()V", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$kotlin_release", "$serializer", "Companion", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
    @Serializable
    /* compiled from: BidResponse.kt */
    public static final class Extension {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        public final boolean use_new_renderer;

        public Extension() {
            this(false, 1, (DefaultConstructorMarker) null);
        }

        @SerialName("use_new_renderer")
        public static /* synthetic */ void getUse_new_renderer$annotations() {
        }

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/adsbynimbus/openrtb/response/BidResponse$Extension$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/adsbynimbus/openrtb/response/BidResponse$Extension;", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
        /* compiled from: BidResponse.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Extension> serializer() {
                return BidResponse$Extension$$serializer.INSTANCE;
            }
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ Extension(int i, @SerialName("use_new_renderer") boolean z, SerializationConstructorMarker serializationConstructorMarker) {
            if ((i & 0) != 0) {
                PluginExceptionsKt.throwMissingFieldException(i, 0, BidResponse$Extension$$serializer.INSTANCE.getDescriptor());
            }
            if ((i & 1) == 0) {
                this.use_new_renderer = false;
            } else {
                this.use_new_renderer = z;
            }
        }

        public Extension(boolean z) {
            this.use_new_renderer = z;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$kotlin_release(Extension extension, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
            boolean z = true;
            if (!compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 0) && !extension.use_new_renderer) {
                z = false;
            }
            if (z) {
                compositeEncoder.encodeBooleanElement(serialDescriptor, 0, extension.use_new_renderer);
            }
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Extension(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? false : z);
        }
    }
}

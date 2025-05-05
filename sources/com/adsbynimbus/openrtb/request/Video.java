package com.adsbynimbus.openrtb.request;

import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.internal.ByteSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.ReferenceArraySerializer;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 L2\u00020\u0001:\u0002KLB\u0002\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0010\b\u0001\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\b\b\u0001\u0010\t\u001a\u00020\u0003\u0012\b\b\u0001\u0010\n\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\b\u0001\u0010\r\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u000f\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0001\u0010\u0012\u001a\u00020\u0011\u0012\b\b\u0001\u0010\u0013\u001a\u00020\u0011\u0012\n\b\u0001\u0010\u0014\u001a\u0004\u0018\u00010\f\u0012\b\b\u0001\u0010\u0015\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0016\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0017\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0018\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0019\u001a\u00020\u0011\u0012\n\b\u0001\u0010\u001a\u001a\u0004\u0018\u00010\f\u0012\n\b\u0001\u0010\u001b\u001a\u0004\u0018\u00010\f\u0012\u0010\b\u0001\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u0007\u0012\n\b\u0001\u0010\u001e\u001a\u0004\u0018\u00010\f\u0012\u0016\b\u0001\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u0011\u0018\u00010 \u0012\b\u0010!\u001a\u0004\u0018\u00010\"¢\u0006\u0002\u0010#B\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0011\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\f\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0011\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\f\u0012\u0010\b\u0002\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\f\u0012\u0014\b\u0002\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00110 ¢\u0006\u0002\u0010$J&\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020\u00002\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020IHÁ\u0001¢\u0006\u0002\bJR\u001a\u0010\u001b\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b%\u0010&R\u0018\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b'\u0010&R\"\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u00078\u0006@\u0006X\u000e¢\u0006\n\n\u0002\u0010)\u0012\u0004\b(\u0010&R\u001a\u0010\u001e\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b*\u0010&R\u001a\u0010\u0014\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b+\u0010&R$\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00110 8\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b,\u0010&R\u0018\u0010\u000e\u001a\u00020\u00038\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b-\u0010&R&\u0010/\u001a\u00020\u00112\u0006\u0010.\u001a\u00020\u00118Æ\u0002@Æ\u0002X\u000e¢\u0006\f\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u0018\u0010\u0012\u001a\u00020\u00118\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b3\u0010&R\u0018\u0010\u0018\u001a\u00020\u00038\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b4\u0010&R\u0018\u0010\n\u001a\u00020\u00038\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b5\u0010&R\"\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00078\u0006@\u0006X\u000e¢\u0006\n\n\u0002\u00107\u0012\u0004\b6\u0010&R\u0018\u0010\u0017\u001a\u00020\u00038\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b8\u0010&R\u0018\u0010\t\u001a\u00020\u00038\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b9\u0010&R\u0018\u0010\u0010\u001a\u00020\u00118\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b:\u0010&R\u001a\u0010\u001a\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b;\u0010&R\u0018\u0010\u0019\u001a\u00020\u00118\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b<\u0010&R\u001a\u0010\u000b\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b=\u0010&R\u0018\u0010\u0013\u001a\u00020\u00118\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b>\u0010&R\u0018\u0010\u0016\u001a\u00020\u00038\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b?\u0010&R\u0018\u0010\u0015\u001a\u00020\u00038\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b@\u0010&R\u0018\u0010\u000f\u001a\u00020\u00038\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\bA\u0010&R\u0018\u0010\r\u001a\u00020\u00038\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\bB\u0010&¨\u0006M"}, d2 = {"Lcom/adsbynimbus/openrtb/request/Video;", "", "seen1", "", "bidfloor", "", "mimes", "", "", "minduration", "maxduration", "protocols", "", "w", "h", "startdelay", "placement", "", "linearity", "skip", "delivery", "skipmin", "skipafter", "minbitrate", "maxbitrate", "pos", "playbackmethod", "api", "companionad", "Lcom/adsbynimbus/openrtb/request/Banner;", "companiontype", "ext", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IF[Ljava/lang/String;II[BIIIBBB[BIIIIB[B[B[Lcom/adsbynimbus/openrtb/request/Banner;[BLjava/util/Map;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(F[Ljava/lang/String;II[BIIIBBB[BIIIIB[B[B[Lcom/adsbynimbus/openrtb/request/Banner;[BLjava/util/Map;)V", "getApi$annotations", "()V", "getBidfloor$annotations", "getCompanionad$annotations", "[Lcom/adsbynimbus/openrtb/request/Banner;", "getCompaniontype$annotations", "getDelivery$annotations", "getExt$annotations", "getH$annotations", "value", "is_rewarded", "()B", "set_rewarded", "(B)V", "getLinearity$annotations", "getMaxbitrate$annotations", "getMaxduration$annotations", "getMimes$annotations", "[Ljava/lang/String;", "getMinbitrate$annotations", "getMinduration$annotations", "getPlacement$annotations", "getPlaybackmethod$annotations", "getPos$annotations", "getProtocols$annotations", "getSkip$annotations", "getSkipafter$annotations", "getSkipmin$annotations", "getStartdelay$annotations", "getW$annotations", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$kotlin_release", "$serializer", "Companion", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
@Serializable
/* compiled from: Video.kt */
public final class Video {
    /* access modifiers changed from: private */
    public static final KSerializer<Object>[] $childSerializers = {null, new ReferenceArraySerializer(Reflection.getOrCreateKotlinClass(String.class), StringSerializer.INSTANCE), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, new ReferenceArraySerializer(Reflection.getOrCreateKotlinClass(Banner.class), Banner$$serializer.INSTANCE), null, new LinkedHashMapSerializer(StringSerializer.INSTANCE, ByteSerializer.INSTANCE)};
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public byte[] api;
    public float bidfloor;
    public Banner[] companionad;
    public byte[] companiontype;
    public byte[] delivery;
    public Map<String, Byte> ext;
    public int h;
    public byte linearity;
    public int maxbitrate;
    public int maxduration;
    public String[] mimes;
    public int minbitrate;
    public int minduration;
    public byte placement;
    public byte[] playbackmethod;
    public byte pos;
    public byte[] protocols;
    public byte skip;
    public int skipafter;
    public int skipmin;
    public int startdelay;
    public int w;

    public Video() {
        this(0.0f, (String[]) null, 0, 0, (byte[]) null, 0, 0, 0, (byte) 0, (byte) 0, (byte) 0, (byte[]) null, 0, 0, 0, 0, (byte) 0, (byte[]) null, (byte[]) null, (Banner[]) null, (byte[]) null, (Map) null, 4194303, (DefaultConstructorMarker) null);
    }

    @SerialName("api")
    public static /* synthetic */ void getApi$annotations() {
    }

    @SerialName("bidfloor")
    public static /* synthetic */ void getBidfloor$annotations() {
    }

    @SerialName("companionad")
    public static /* synthetic */ void getCompanionad$annotations() {
    }

    @SerialName("companiontype")
    public static /* synthetic */ void getCompaniontype$annotations() {
    }

    @SerialName("delivery")
    public static /* synthetic */ void getDelivery$annotations() {
    }

    @SerialName("ext")
    public static /* synthetic */ void getExt$annotations() {
    }

    @SerialName("h")
    public static /* synthetic */ void getH$annotations() {
    }

    @SerialName("linearity")
    public static /* synthetic */ void getLinearity$annotations() {
    }

    @SerialName("maxbitrate")
    public static /* synthetic */ void getMaxbitrate$annotations() {
    }

    @SerialName("maxduration")
    public static /* synthetic */ void getMaxduration$annotations() {
    }

    @SerialName("mimes")
    public static /* synthetic */ void getMimes$annotations() {
    }

    @SerialName("minbitrate")
    public static /* synthetic */ void getMinbitrate$annotations() {
    }

    @SerialName("minduration")
    public static /* synthetic */ void getMinduration$annotations() {
    }

    @SerialName("placement")
    public static /* synthetic */ void getPlacement$annotations() {
    }

    @SerialName("playbackmethod")
    public static /* synthetic */ void getPlaybackmethod$annotations() {
    }

    @SerialName("pos")
    public static /* synthetic */ void getPos$annotations() {
    }

    @SerialName("protocols")
    public static /* synthetic */ void getProtocols$annotations() {
    }

    @SerialName("skip")
    public static /* synthetic */ void getSkip$annotations() {
    }

    @SerialName("skipafter")
    public static /* synthetic */ void getSkipafter$annotations() {
    }

    @SerialName("skipmin")
    public static /* synthetic */ void getSkipmin$annotations() {
    }

    @SerialName("startdelay")
    public static /* synthetic */ void getStartdelay$annotations() {
    }

    @SerialName("w")
    public static /* synthetic */ void getW$annotations() {
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/adsbynimbus/openrtb/request/Video$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/adsbynimbus/openrtb/request/Video;", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
    /* compiled from: Video.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<Video> serializer() {
            return Video$$serializer.INSTANCE;
        }
    }

    public Video(float f, String[] strArr, int i, int i2, byte[] bArr, int i3, int i4, int i5, byte b, byte b2, byte b3, byte[] bArr2, int i6, int i7, int i8, int i9, byte b4, byte[] bArr3, byte[] bArr4, Banner[] bannerArr, byte[] bArr5, Map<String, Byte> map) {
        Map<String, Byte> map2 = map;
        Intrinsics.checkNotNullParameter(map2, "ext");
        this.bidfloor = f;
        this.mimes = strArr;
        this.minduration = i;
        this.maxduration = i2;
        this.protocols = bArr;
        this.w = i3;
        this.h = i4;
        this.startdelay = i5;
        this.placement = b;
        this.linearity = b2;
        this.skip = b3;
        this.delivery = bArr2;
        this.skipmin = i6;
        this.skipafter = i7;
        this.minbitrate = i8;
        this.maxbitrate = i9;
        this.pos = b4;
        this.playbackmethod = bArr3;
        this.api = bArr4;
        this.companionad = bannerArr;
        this.companiontype = bArr5;
        this.ext = map2;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ Video(int i, @SerialName("bidfloor") float f, @SerialName("mimes") String[] strArr, @SerialName("minduration") int i2, @SerialName("maxduration") int i3, @SerialName("protocols") byte[] bArr, @SerialName("w") int i4, @SerialName("h") int i5, @SerialName("startdelay") int i6, @SerialName("placement") byte b, @SerialName("linearity") byte b2, @SerialName("skip") byte b3, @SerialName("delivery") byte[] bArr2, @SerialName("skipmin") int i7, @SerialName("skipafter") int i8, @SerialName("minbitrate") int i9, @SerialName("maxbitrate") int i10, @SerialName("pos") byte b4, @SerialName("playbackmethod") byte[] bArr3, @SerialName("api") byte[] bArr4, @SerialName("companionad") Banner[] bannerArr, @SerialName("companiontype") byte[] bArr5, @SerialName("ext") Map map, SerializationConstructorMarker serializationConstructorMarker) {
        Map map2;
        int i11 = i;
        if ((i11 & 0) != 0) {
            PluginExceptionsKt.throwMissingFieldException(i, 0, Video$$serializer.INSTANCE.getDescriptor());
        }
        this.bidfloor = (i11 & 1) == 0 ? 0.0f : f;
        if ((i11 & 2) == 0) {
            this.mimes = null;
        } else {
            this.mimes = strArr;
        }
        if ((i11 & 4) == 0) {
            this.minduration = 0;
        } else {
            this.minduration = i2;
        }
        this.maxduration = (i11 & 8) == 0 ? 60 : i3;
        if ((i11 & 16) == 0) {
            this.protocols = null;
        } else {
            this.protocols = bArr;
        }
        if ((i11 & 32) == 0) {
            this.w = 0;
        } else {
            this.w = i4;
        }
        if ((i11 & 64) == 0) {
            this.h = 0;
        } else {
            this.h = i5;
        }
        if ((i11 & 128) == 0) {
            this.startdelay = 0;
        } else {
            this.startdelay = i6;
        }
        if ((i11 & 256) == 0) {
            this.placement = 0;
        } else {
            this.placement = b;
        }
        if ((i11 & 512) == 0) {
            this.linearity = 0;
        } else {
            this.linearity = b2;
        }
        if ((i11 & 1024) == 0) {
            this.skip = 0;
        } else {
            this.skip = b3;
        }
        if ((i11 & 2048) == 0) {
            this.delivery = null;
        } else {
            this.delivery = bArr2;
        }
        if ((i11 & 4096) == 0) {
            this.skipmin = 0;
        } else {
            this.skipmin = i7;
        }
        if ((i11 & 8192) == 0) {
            this.skipafter = 0;
        } else {
            this.skipafter = i8;
        }
        if ((i11 & 16384) == 0) {
            this.minbitrate = 0;
        } else {
            this.minbitrate = i9;
        }
        if ((32768 & i11) == 0) {
            this.maxbitrate = 0;
        } else {
            this.maxbitrate = i10;
        }
        if ((65536 & i11) == 0) {
            this.pos = 0;
        } else {
            this.pos = b4;
        }
        if ((131072 & i11) == 0) {
            this.playbackmethod = null;
        } else {
            this.playbackmethod = bArr3;
        }
        if ((262144 & i11) == 0) {
            this.api = null;
        } else {
            this.api = bArr4;
        }
        if ((524288 & i11) == 0) {
            this.companionad = null;
        } else {
            this.companionad = bannerArr;
        }
        if ((1048576 & i11) == 0) {
            this.companiontype = null;
        } else {
            this.companiontype = bArr5;
        }
        if ((i11 & 2097152) == 0) {
            map2 = MapsKt.mutableMapOf(TuplesKt.to("is_rewarded", (byte) 0));
        } else {
            map2 = map;
        }
        this.ext = map2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:172:0x0222, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8.ext, (java.lang.Object) kotlin.collections.MapsKt.mutableMapOf(kotlin.TuplesKt.to("is_rewarded", (byte) 0))) == false) goto L_0x0208;
     */
    @kotlin.jvm.JvmStatic
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final /* synthetic */ void write$Self$kotlin_release(com.adsbynimbus.openrtb.request.Video r8, kotlinx.serialization.encoding.CompositeEncoder r9, kotlinx.serialization.descriptors.SerialDescriptor r10) {
        /*
            kotlinx.serialization.KSerializer<java.lang.Object>[] r0 = $childSerializers
            r1 = 0
            boolean r2 = r9.shouldEncodeElementDefault(r10, r1)
            r3 = 1
            if (r2 == 0) goto L_0x000c
        L_0x000a:
            r2 = r3
            goto L_0x0017
        L_0x000c:
            float r2 = r8.bidfloor
            r4 = 0
            int r2 = java.lang.Float.compare(r2, r4)
            if (r2 == 0) goto L_0x0016
            goto L_0x000a
        L_0x0016:
            r2 = r1
        L_0x0017:
            if (r2 == 0) goto L_0x001e
            float r2 = r8.bidfloor
            r9.encodeFloatElement(r10, r1, r2)
        L_0x001e:
            boolean r2 = r9.shouldEncodeElementDefault(r10, r3)
            if (r2 == 0) goto L_0x0026
        L_0x0024:
            r2 = r3
            goto L_0x002c
        L_0x0026:
            java.lang.String[] r2 = r8.mimes
            if (r2 == 0) goto L_0x002b
            goto L_0x0024
        L_0x002b:
            r2 = r1
        L_0x002c:
            if (r2 == 0) goto L_0x0037
            r2 = r0[r3]
            kotlinx.serialization.SerializationStrategy r2 = (kotlinx.serialization.SerializationStrategy) r2
            java.lang.String[] r4 = r8.mimes
            r9.encodeNullableSerializableElement(r10, r3, r2, r4)
        L_0x0037:
            r2 = 2
            boolean r4 = r9.shouldEncodeElementDefault(r10, r2)
            if (r4 == 0) goto L_0x0040
        L_0x003e:
            r4 = r3
            goto L_0x0046
        L_0x0040:
            int r4 = r8.minduration
            if (r4 == 0) goto L_0x0045
            goto L_0x003e
        L_0x0045:
            r4 = r1
        L_0x0046:
            if (r4 == 0) goto L_0x004d
            int r4 = r8.minduration
            r9.encodeIntElement(r10, r2, r4)
        L_0x004d:
            r2 = 3
            boolean r4 = r9.shouldEncodeElementDefault(r10, r2)
            if (r4 == 0) goto L_0x0056
        L_0x0054:
            r4 = r3
            goto L_0x005e
        L_0x0056:
            int r4 = r8.maxduration
            r5 = 60
            if (r4 == r5) goto L_0x005d
            goto L_0x0054
        L_0x005d:
            r4 = r1
        L_0x005e:
            if (r4 == 0) goto L_0x0065
            int r4 = r8.maxduration
            r9.encodeIntElement(r10, r2, r4)
        L_0x0065:
            r2 = 4
            boolean r4 = r9.shouldEncodeElementDefault(r10, r2)
            if (r4 == 0) goto L_0x006e
        L_0x006c:
            r4 = r3
            goto L_0x0074
        L_0x006e:
            byte[] r4 = r8.protocols
            if (r4 == 0) goto L_0x0073
            goto L_0x006c
        L_0x0073:
            r4 = r1
        L_0x0074:
            if (r4 == 0) goto L_0x007f
            kotlinx.serialization.internal.ByteArraySerializer r4 = kotlinx.serialization.internal.ByteArraySerializer.INSTANCE
            kotlinx.serialization.SerializationStrategy r4 = (kotlinx.serialization.SerializationStrategy) r4
            byte[] r5 = r8.protocols
            r9.encodeNullableSerializableElement(r10, r2, r4, r5)
        L_0x007f:
            r2 = 5
            boolean r4 = r9.shouldEncodeElementDefault(r10, r2)
            if (r4 == 0) goto L_0x0088
        L_0x0086:
            r4 = r3
            goto L_0x008e
        L_0x0088:
            int r4 = r8.w
            if (r4 == 0) goto L_0x008d
            goto L_0x0086
        L_0x008d:
            r4 = r1
        L_0x008e:
            if (r4 == 0) goto L_0x0095
            int r4 = r8.w
            r9.encodeIntElement(r10, r2, r4)
        L_0x0095:
            r2 = 6
            boolean r4 = r9.shouldEncodeElementDefault(r10, r2)
            if (r4 == 0) goto L_0x009e
        L_0x009c:
            r4 = r3
            goto L_0x00a4
        L_0x009e:
            int r4 = r8.h
            if (r4 == 0) goto L_0x00a3
            goto L_0x009c
        L_0x00a3:
            r4 = r1
        L_0x00a4:
            if (r4 == 0) goto L_0x00ab
            int r4 = r8.h
            r9.encodeIntElement(r10, r2, r4)
        L_0x00ab:
            r2 = 7
            boolean r4 = r9.shouldEncodeElementDefault(r10, r2)
            if (r4 == 0) goto L_0x00b4
        L_0x00b2:
            r4 = r3
            goto L_0x00ba
        L_0x00b4:
            int r4 = r8.startdelay
            if (r4 == 0) goto L_0x00b9
            goto L_0x00b2
        L_0x00b9:
            r4 = r1
        L_0x00ba:
            if (r4 == 0) goto L_0x00c1
            int r4 = r8.startdelay
            r9.encodeIntElement(r10, r2, r4)
        L_0x00c1:
            r2 = 8
            boolean r4 = r9.shouldEncodeElementDefault(r10, r2)
            if (r4 == 0) goto L_0x00cb
        L_0x00c9:
            r4 = r3
            goto L_0x00d1
        L_0x00cb:
            byte r4 = r8.placement
            if (r4 == 0) goto L_0x00d0
            goto L_0x00c9
        L_0x00d0:
            r4 = r1
        L_0x00d1:
            if (r4 == 0) goto L_0x00d8
            byte r4 = r8.placement
            r9.encodeByteElement(r10, r2, r4)
        L_0x00d8:
            r2 = 9
            boolean r4 = r9.shouldEncodeElementDefault(r10, r2)
            if (r4 == 0) goto L_0x00e2
        L_0x00e0:
            r4 = r3
            goto L_0x00e8
        L_0x00e2:
            byte r4 = r8.linearity
            if (r4 == 0) goto L_0x00e7
            goto L_0x00e0
        L_0x00e7:
            r4 = r1
        L_0x00e8:
            if (r4 == 0) goto L_0x00ef
            byte r4 = r8.linearity
            r9.encodeByteElement(r10, r2, r4)
        L_0x00ef:
            r2 = 10
            boolean r4 = r9.shouldEncodeElementDefault(r10, r2)
            if (r4 == 0) goto L_0x00f9
        L_0x00f7:
            r4 = r3
            goto L_0x00ff
        L_0x00f9:
            byte r4 = r8.skip
            if (r4 == 0) goto L_0x00fe
            goto L_0x00f7
        L_0x00fe:
            r4 = r1
        L_0x00ff:
            if (r4 == 0) goto L_0x0106
            byte r4 = r8.skip
            r9.encodeByteElement(r10, r2, r4)
        L_0x0106:
            r2 = 11
            boolean r4 = r9.shouldEncodeElementDefault(r10, r2)
            if (r4 == 0) goto L_0x0110
        L_0x010e:
            r4 = r3
            goto L_0x0116
        L_0x0110:
            byte[] r4 = r8.delivery
            if (r4 == 0) goto L_0x0115
            goto L_0x010e
        L_0x0115:
            r4 = r1
        L_0x0116:
            if (r4 == 0) goto L_0x0121
            kotlinx.serialization.internal.ByteArraySerializer r4 = kotlinx.serialization.internal.ByteArraySerializer.INSTANCE
            kotlinx.serialization.SerializationStrategy r4 = (kotlinx.serialization.SerializationStrategy) r4
            byte[] r5 = r8.delivery
            r9.encodeNullableSerializableElement(r10, r2, r4, r5)
        L_0x0121:
            r2 = 12
            boolean r4 = r9.shouldEncodeElementDefault(r10, r2)
            if (r4 == 0) goto L_0x012b
        L_0x0129:
            r4 = r3
            goto L_0x0131
        L_0x012b:
            int r4 = r8.skipmin
            if (r4 == 0) goto L_0x0130
            goto L_0x0129
        L_0x0130:
            r4 = r1
        L_0x0131:
            if (r4 == 0) goto L_0x0138
            int r4 = r8.skipmin
            r9.encodeIntElement(r10, r2, r4)
        L_0x0138:
            r2 = 13
            boolean r4 = r9.shouldEncodeElementDefault(r10, r2)
            if (r4 == 0) goto L_0x0142
        L_0x0140:
            r4 = r3
            goto L_0x0148
        L_0x0142:
            int r4 = r8.skipafter
            if (r4 == 0) goto L_0x0147
            goto L_0x0140
        L_0x0147:
            r4 = r1
        L_0x0148:
            if (r4 == 0) goto L_0x014f
            int r4 = r8.skipafter
            r9.encodeIntElement(r10, r2, r4)
        L_0x014f:
            r2 = 14
            boolean r4 = r9.shouldEncodeElementDefault(r10, r2)
            if (r4 == 0) goto L_0x0159
        L_0x0157:
            r4 = r3
            goto L_0x015f
        L_0x0159:
            int r4 = r8.minbitrate
            if (r4 == 0) goto L_0x015e
            goto L_0x0157
        L_0x015e:
            r4 = r1
        L_0x015f:
            if (r4 == 0) goto L_0x0166
            int r4 = r8.minbitrate
            r9.encodeIntElement(r10, r2, r4)
        L_0x0166:
            r2 = 15
            boolean r4 = r9.shouldEncodeElementDefault(r10, r2)
            if (r4 == 0) goto L_0x0170
        L_0x016e:
            r4 = r3
            goto L_0x0176
        L_0x0170:
            int r4 = r8.maxbitrate
            if (r4 == 0) goto L_0x0175
            goto L_0x016e
        L_0x0175:
            r4 = r1
        L_0x0176:
            if (r4 == 0) goto L_0x017d
            int r4 = r8.maxbitrate
            r9.encodeIntElement(r10, r2, r4)
        L_0x017d:
            r2 = 16
            boolean r4 = r9.shouldEncodeElementDefault(r10, r2)
            if (r4 == 0) goto L_0x0187
        L_0x0185:
            r4 = r3
            goto L_0x018d
        L_0x0187:
            byte r4 = r8.pos
            if (r4 == 0) goto L_0x018c
            goto L_0x0185
        L_0x018c:
            r4 = r1
        L_0x018d:
            if (r4 == 0) goto L_0x0194
            byte r4 = r8.pos
            r9.encodeByteElement(r10, r2, r4)
        L_0x0194:
            r2 = 17
            boolean r4 = r9.shouldEncodeElementDefault(r10, r2)
            if (r4 == 0) goto L_0x019e
        L_0x019c:
            r4 = r3
            goto L_0x01a4
        L_0x019e:
            byte[] r4 = r8.playbackmethod
            if (r4 == 0) goto L_0x01a3
            goto L_0x019c
        L_0x01a3:
            r4 = r1
        L_0x01a4:
            if (r4 == 0) goto L_0x01af
            kotlinx.serialization.internal.ByteArraySerializer r4 = kotlinx.serialization.internal.ByteArraySerializer.INSTANCE
            kotlinx.serialization.SerializationStrategy r4 = (kotlinx.serialization.SerializationStrategy) r4
            byte[] r5 = r8.playbackmethod
            r9.encodeNullableSerializableElement(r10, r2, r4, r5)
        L_0x01af:
            r2 = 18
            boolean r4 = r9.shouldEncodeElementDefault(r10, r2)
            if (r4 == 0) goto L_0x01b9
        L_0x01b7:
            r4 = r3
            goto L_0x01bf
        L_0x01b9:
            byte[] r4 = r8.api
            if (r4 == 0) goto L_0x01be
            goto L_0x01b7
        L_0x01be:
            r4 = r1
        L_0x01bf:
            if (r4 == 0) goto L_0x01ca
            kotlinx.serialization.internal.ByteArraySerializer r4 = kotlinx.serialization.internal.ByteArraySerializer.INSTANCE
            kotlinx.serialization.SerializationStrategy r4 = (kotlinx.serialization.SerializationStrategy) r4
            byte[] r5 = r8.api
            r9.encodeNullableSerializableElement(r10, r2, r4, r5)
        L_0x01ca:
            r2 = 19
            boolean r4 = r9.shouldEncodeElementDefault(r10, r2)
            if (r4 == 0) goto L_0x01d4
        L_0x01d2:
            r4 = r3
            goto L_0x01da
        L_0x01d4:
            com.adsbynimbus.openrtb.request.Banner[] r4 = r8.companionad
            if (r4 == 0) goto L_0x01d9
            goto L_0x01d2
        L_0x01d9:
            r4 = r1
        L_0x01da:
            if (r4 == 0) goto L_0x01e5
            r4 = r0[r2]
            kotlinx.serialization.SerializationStrategy r4 = (kotlinx.serialization.SerializationStrategy) r4
            com.adsbynimbus.openrtb.request.Banner[] r5 = r8.companionad
            r9.encodeNullableSerializableElement(r10, r2, r4, r5)
        L_0x01e5:
            r2 = 20
            boolean r4 = r9.shouldEncodeElementDefault(r10, r2)
            if (r4 == 0) goto L_0x01ef
        L_0x01ed:
            r4 = r3
            goto L_0x01f5
        L_0x01ef:
            byte[] r4 = r8.companiontype
            if (r4 == 0) goto L_0x01f4
            goto L_0x01ed
        L_0x01f4:
            r4 = r1
        L_0x01f5:
            if (r4 == 0) goto L_0x0200
            kotlinx.serialization.internal.ByteArraySerializer r4 = kotlinx.serialization.internal.ByteArraySerializer.INSTANCE
            kotlinx.serialization.SerializationStrategy r4 = (kotlinx.serialization.SerializationStrategy) r4
            byte[] r5 = r8.companiontype
            r9.encodeNullableSerializableElement(r10, r2, r4, r5)
        L_0x0200:
            r2 = 21
            boolean r4 = r9.shouldEncodeElementDefault(r10, r2)
            if (r4 == 0) goto L_0x020a
        L_0x0208:
            r1 = r3
            goto L_0x0225
        L_0x020a:
            java.util.Map<java.lang.String, java.lang.Byte> r4 = r8.ext
            kotlin.Pair[] r5 = new kotlin.Pair[r3]
            java.lang.String r6 = "is_rewarded"
            java.lang.Byte r7 = java.lang.Byte.valueOf(r1)
            kotlin.Pair r6 = kotlin.TuplesKt.to(r6, r7)
            r5[r1] = r6
            java.util.Map r5 = kotlin.collections.MapsKt.mutableMapOf(r5)
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r4 != 0) goto L_0x0225
            goto L_0x0208
        L_0x0225:
            if (r1 == 0) goto L_0x0230
            r0 = r0[r2]
            kotlinx.serialization.SerializationStrategy r0 = (kotlinx.serialization.SerializationStrategy) r0
            java.util.Map<java.lang.String, java.lang.Byte> r8 = r8.ext
            r9.encodeSerializableElement(r10, r2, r0, r8)
        L_0x0230:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.openrtb.request.Video.write$Self$kotlin_release(com.adsbynimbus.openrtb.request.Video, kotlinx.serialization.encoding.CompositeEncoder, kotlinx.serialization.descriptors.SerialDescriptor):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ Video(float r25, java.lang.String[] r26, int r27, int r28, byte[] r29, int r30, int r31, int r32, byte r33, byte r34, byte r35, byte[] r36, int r37, int r38, int r39, int r40, byte r41, byte[] r42, byte[] r43, com.adsbynimbus.openrtb.request.Banner[] r44, byte[] r45, java.util.Map r46, int r47, kotlin.jvm.internal.DefaultConstructorMarker r48) {
        /*
            r24 = this;
            r0 = r47
            r1 = r0 & 1
            if (r1 == 0) goto L_0x0008
            r1 = 0
            goto L_0x000a
        L_0x0008:
            r1 = r25
        L_0x000a:
            r2 = r0 & 2
            if (r2 == 0) goto L_0x0010
            r2 = 0
            goto L_0x0012
        L_0x0010:
            r2 = r26
        L_0x0012:
            r4 = r0 & 4
            if (r4 == 0) goto L_0x0018
            r4 = 0
            goto L_0x001a
        L_0x0018:
            r4 = r27
        L_0x001a:
            r6 = r0 & 8
            if (r6 == 0) goto L_0x0021
            r6 = 60
            goto L_0x0023
        L_0x0021:
            r6 = r28
        L_0x0023:
            r7 = r0 & 16
            if (r7 == 0) goto L_0x0029
            r7 = 0
            goto L_0x002b
        L_0x0029:
            r7 = r29
        L_0x002b:
            r8 = r0 & 32
            if (r8 == 0) goto L_0x0031
            r8 = 0
            goto L_0x0033
        L_0x0031:
            r8 = r30
        L_0x0033:
            r9 = r0 & 64
            if (r9 == 0) goto L_0x0039
            r9 = 0
            goto L_0x003b
        L_0x0039:
            r9 = r31
        L_0x003b:
            r10 = r0 & 128(0x80, float:1.794E-43)
            if (r10 == 0) goto L_0x0041
            r10 = 0
            goto L_0x0043
        L_0x0041:
            r10 = r32
        L_0x0043:
            r11 = r0 & 256(0x100, float:3.59E-43)
            if (r11 == 0) goto L_0x0049
            r11 = 0
            goto L_0x004b
        L_0x0049:
            r11 = r33
        L_0x004b:
            r12 = r0 & 512(0x200, float:7.175E-43)
            if (r12 == 0) goto L_0x0051
            r12 = 0
            goto L_0x0053
        L_0x0051:
            r12 = r34
        L_0x0053:
            r13 = r0 & 1024(0x400, float:1.435E-42)
            if (r13 == 0) goto L_0x0059
            r13 = 0
            goto L_0x005b
        L_0x0059:
            r13 = r35
        L_0x005b:
            r14 = r0 & 2048(0x800, float:2.87E-42)
            if (r14 == 0) goto L_0x0061
            r14 = 0
            goto L_0x0063
        L_0x0061:
            r14 = r36
        L_0x0063:
            r15 = r0 & 4096(0x1000, float:5.74E-42)
            if (r15 == 0) goto L_0x0069
            r15 = 0
            goto L_0x006b
        L_0x0069:
            r15 = r37
        L_0x006b:
            r3 = r0 & 8192(0x2000, float:1.14794E-41)
            if (r3 == 0) goto L_0x0071
            r3 = 0
            goto L_0x0073
        L_0x0071:
            r3 = r38
        L_0x0073:
            r5 = r0 & 16384(0x4000, float:2.2959E-41)
            if (r5 == 0) goto L_0x0079
            r5 = 0
            goto L_0x007b
        L_0x0079:
            r5 = r39
        L_0x007b:
            r16 = 32768(0x8000, float:4.5918E-41)
            r16 = r0 & r16
            if (r16 == 0) goto L_0x0085
            r16 = 0
            goto L_0x0087
        L_0x0085:
            r16 = r40
        L_0x0087:
            r17 = 65536(0x10000, float:9.18355E-41)
            r17 = r0 & r17
            if (r17 == 0) goto L_0x0090
            r17 = 0
            goto L_0x0092
        L_0x0090:
            r17 = r41
        L_0x0092:
            r18 = 131072(0x20000, float:1.83671E-40)
            r18 = r0 & r18
            if (r18 == 0) goto L_0x009b
            r18 = 0
            goto L_0x009d
        L_0x009b:
            r18 = r42
        L_0x009d:
            r19 = 262144(0x40000, float:3.67342E-40)
            r19 = r0 & r19
            if (r19 == 0) goto L_0x00a6
            r19 = 0
            goto L_0x00a8
        L_0x00a6:
            r19 = r43
        L_0x00a8:
            r20 = 524288(0x80000, float:7.34684E-40)
            r20 = r0 & r20
            if (r20 == 0) goto L_0x00b1
            r20 = 0
            goto L_0x00b3
        L_0x00b1:
            r20 = r44
        L_0x00b3:
            r21 = 1048576(0x100000, float:1.469368E-39)
            r21 = r0 & r21
            if (r21 == 0) goto L_0x00bc
            r21 = 0
            goto L_0x00be
        L_0x00bc:
            r21 = r45
        L_0x00be:
            r22 = 2097152(0x200000, float:2.938736E-39)
            r0 = r0 & r22
            if (r0 == 0) goto L_0x00de
            r0 = 1
            kotlin.Pair[] r0 = new kotlin.Pair[r0]
            r48 = r5
            java.lang.String r5 = "is_rewarded"
            r23 = r3
            r22 = 0
            java.lang.Byte r3 = java.lang.Byte.valueOf(r22)
            kotlin.Pair r3 = kotlin.TuplesKt.to(r5, r3)
            r0[r22] = r3
            java.util.Map r0 = kotlin.collections.MapsKt.mutableMapOf(r0)
            goto L_0x00e4
        L_0x00de:
            r23 = r3
            r48 = r5
            r0 = r46
        L_0x00e4:
            r25 = r24
            r26 = r1
            r27 = r2
            r28 = r4
            r29 = r6
            r30 = r7
            r31 = r8
            r32 = r9
            r33 = r10
            r34 = r11
            r35 = r12
            r36 = r13
            r37 = r14
            r38 = r15
            r39 = r23
            r40 = r48
            r41 = r16
            r42 = r17
            r43 = r18
            r44 = r19
            r45 = r20
            r46 = r21
            r47 = r0
            r25.<init>(r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.openrtb.request.Video.<init>(float, java.lang.String[], int, int, byte[], int, int, int, byte, byte, byte, byte[], int, int, int, int, byte, byte[], byte[], com.adsbynimbus.openrtb.request.Banner[], byte[], java.util.Map, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final byte is_rewarded() {
        Byte b = this.ext.get("is_rewarded");
        if (b != null) {
            return b.byteValue();
        }
        return 0;
    }

    public final void set_rewarded(byte b) {
        this.ext.put("is_rewarded", Byte.valueOf(b));
    }
}

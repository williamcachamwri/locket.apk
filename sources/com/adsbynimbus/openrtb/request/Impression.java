package com.adsbynimbus.openrtb.request;

import com.facebook.react.uimanager.ViewProps;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.LinkedHashSetSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 #2\u00020\u0001:\u0003\"#$B]\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b\u0012\b\b\u0001\u0010\f\u001a\u00020\u000b\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\u0011BE\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u0012J&\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 HÁ\u0001¢\u0006\u0002\b!R\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0013\u0010\u0014R\u0018\u0010\r\u001a\u00020\u000e8\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0015\u0010\u0014R\u0018\u0010\n\u001a\u00020\u000b8\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0016\u0010\u0014R\u001a\u0010\b\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0017\u0010\u0014R\u0018\u0010\f\u001a\u00020\u000b8\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0018\u0010\u0014R\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0019\u0010\u0014¨\u0006%"}, d2 = {"Lcom/adsbynimbus/openrtb/request/Impression;", "", "seen1", "", "banner", "Lcom/adsbynimbus/openrtb/request/Banner;", "video", "Lcom/adsbynimbus/openrtb/request/Video;", "native", "Lcom/adsbynimbus/openrtb/request/Native;", "instl", "", "secure", "ext", "Lcom/adsbynimbus/openrtb/request/Impression$Extension;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/adsbynimbus/openrtb/request/Banner;Lcom/adsbynimbus/openrtb/request/Video;Lcom/adsbynimbus/openrtb/request/Native;BBLcom/adsbynimbus/openrtb/request/Impression$Extension;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Lcom/adsbynimbus/openrtb/request/Banner;Lcom/adsbynimbus/openrtb/request/Video;Lcom/adsbynimbus/openrtb/request/Native;BBLcom/adsbynimbus/openrtb/request/Impression$Extension;)V", "getBanner$annotations", "()V", "getExt$annotations", "getInstl$annotations", "getNative$annotations", "getSecure$annotations", "getVideo$annotations", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$kotlin_release", "$serializer", "Companion", "Extension", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
@Serializable
/* compiled from: Impression.kt */
public final class Impression {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public Banner banner;
    public Extension ext;
    public byte instl;

    /* renamed from: native  reason: not valid java name */
    public Native f0native;
    public byte secure;
    public Video video;

    @SerialName("banner")
    public static /* synthetic */ void getBanner$annotations() {
    }

    @SerialName("ext")
    public static /* synthetic */ void getExt$annotations() {
    }

    @SerialName("instl")
    public static /* synthetic */ void getInstl$annotations() {
    }

    @SerialName("native")
    public static /* synthetic */ void getNative$annotations() {
    }

    @SerialName("secure")
    public static /* synthetic */ void getSecure$annotations() {
    }

    @SerialName("video")
    public static /* synthetic */ void getVideo$annotations() {
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/adsbynimbus/openrtb/request/Impression$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/adsbynimbus/openrtb/request/Impression;", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
    /* compiled from: Impression.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<Impression> serializer() {
            return Impression$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ Impression(int i, @SerialName("banner") Banner banner2, @SerialName("video") Video video2, @SerialName("native") Native nativeR, @SerialName("instl") byte b, @SerialName("secure") byte b2, @SerialName("ext") Extension extension, SerializationConstructorMarker serializationConstructorMarker) {
        if (32 != (i & 32)) {
            PluginExceptionsKt.throwMissingFieldException(i, 32, Impression$$serializer.INSTANCE.getDescriptor());
        }
        if ((i & 1) == 0) {
            this.banner = null;
        } else {
            this.banner = banner2;
        }
        if ((i & 2) == 0) {
            this.video = null;
        } else {
            this.video = video2;
        }
        if ((i & 4) == 0) {
            this.f0native = null;
        } else {
            this.f0native = nativeR;
        }
        if ((i & 8) == 0) {
            this.instl = 0;
        } else {
            this.instl = b;
        }
        if ((i & 16) == 0) {
            this.secure = 1;
        } else {
            this.secure = b2;
        }
        this.ext = extension;
    }

    public Impression(Banner banner2, Video video2, Native nativeR, byte b, byte b2, Extension extension) {
        Intrinsics.checkNotNullParameter(extension, "ext");
        this.banner = banner2;
        this.video = video2;
        this.f0native = nativeR;
        this.instl = b;
        this.secure = b2;
        this.ext = extension;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$kotlin_release(Impression impression, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        boolean z = false;
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 0) || impression.banner != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 0, Banner$$serializer.INSTANCE, impression.banner);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 1) || impression.video != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 1, Video$$serializer.INSTANCE, impression.video);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 2) || impression.f0native != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 2, Native$$serializer.INSTANCE, impression.f0native);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 3) || impression.instl != 0) {
            compositeEncoder.encodeByteElement(serialDescriptor, 3, impression.instl);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 4) || impression.secure != 1) {
            z = true;
        }
        if (z) {
            compositeEncoder.encodeByteElement(serialDescriptor, 4, impression.secure);
        }
        compositeEncoder.encodeSerializableElement(serialDescriptor, 5, Impression$Extension$$serializer.INSTANCE, impression.ext);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Impression(Banner banner2, Video video2, Native nativeR, byte b, byte b2, Extension extension, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : banner2, (i & 2) != 0 ? null : video2, (i & 4) != 0 ? null : nativeR, (i & 8) != 0 ? 0 : b, (i & 16) != 0 ? 1 : b2, extension);
    }

    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\u0010$\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u001e2\u00020\u0001:\u0002\u001d\u001eBa\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\"\b\u0001\u0010\u0006\u001a\u001c\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\b\u0018\u00010\u0007\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eBG\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012 \b\u0002\u0010\u0006\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\b0\u0007\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u000fJ&\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bHÁ\u0001¢\u0006\u0002\b\u001cR0\u0010\u0006\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\b0\u00078\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0010\u0010\u0011R\u001a\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0012\u0010\u0011R\u001a\u0010\u000b\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0013\u0010\u0011R\u0018\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0014\u0010\u0011¨\u0006\u001f"}, d2 = {"Lcom/adsbynimbus/openrtb/request/Impression$Extension;", "", "seen1", "", "position", "", "aps", "", "", "", "facebook_app_id", "facebook_test_ad_type", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/util/Set;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/util/Set;Ljava/lang/String;Ljava/lang/String;)V", "getAps$annotations", "()V", "getFacebook_app_id$annotations", "getFacebook_test_ad_type$annotations", "getPosition$annotations", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$kotlin_release", "$serializer", "Companion", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
    @Serializable
    /* compiled from: Impression.kt */
    public static final class Extension {
        /* access modifiers changed from: private */
        public static final KSerializer<Object>[] $childSerializers = {null, new LinkedHashSetSerializer(new LinkedHashMapSerializer(StringSerializer.INSTANCE, new ArrayListSerializer(StringSerializer.INSTANCE))), null, null};
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        public Set<? extends Map<String, ? extends List<String>>> aps;
        public String facebook_app_id;
        public String facebook_test_ad_type;
        public String position;

        @SerialName("aps")
        public static /* synthetic */ void getAps$annotations() {
        }

        @SerialName("facebook_app_id")
        public static /* synthetic */ void getFacebook_app_id$annotations() {
        }

        @SerialName("facebook_test_ad_type")
        public static /* synthetic */ void getFacebook_test_ad_type$annotations() {
        }

        @SerialName("position")
        public static /* synthetic */ void getPosition$annotations() {
        }

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/adsbynimbus/openrtb/request/Impression$Extension$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/adsbynimbus/openrtb/request/Impression$Extension;", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
        /* compiled from: Impression.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Extension> serializer() {
                return Impression$Extension$$serializer.INSTANCE;
            }
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ Extension(int i, @SerialName("position") String str, @SerialName("aps") Set set, @SerialName("facebook_app_id") String str2, @SerialName("facebook_test_ad_type") String str3, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, Impression$Extension$$serializer.INSTANCE.getDescriptor());
            }
            this.position = str;
            if ((i & 2) == 0) {
                this.aps = SetsKt.emptySet();
            } else {
                this.aps = set;
            }
            if ((i & 4) == 0) {
                this.facebook_app_id = null;
            } else {
                this.facebook_app_id = str2;
            }
            if ((i & 8) == 0) {
                this.facebook_test_ad_type = null;
            } else {
                this.facebook_test_ad_type = str3;
            }
        }

        public Extension(String str, Set<? extends Map<String, ? extends List<String>>> set, String str2, String str3) {
            Intrinsics.checkNotNullParameter(str, ViewProps.POSITION);
            Intrinsics.checkNotNullParameter(set, "aps");
            this.position = str;
            this.aps = set;
            this.facebook_app_id = str2;
            this.facebook_test_ad_type = str3;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$kotlin_release(Extension extension, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
            KSerializer<Object>[] kSerializerArr = $childSerializers;
            boolean z = false;
            compositeEncoder.encodeStringElement(serialDescriptor, 0, extension.position);
            if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 1) || !Intrinsics.areEqual((Object) extension.aps, (Object) SetsKt.emptySet())) {
                compositeEncoder.encodeSerializableElement(serialDescriptor, 1, kSerializerArr[1], extension.aps);
            }
            if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 2) || extension.facebook_app_id != null) {
                compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 2, StringSerializer.INSTANCE, extension.facebook_app_id);
            }
            if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 3) || extension.facebook_test_ad_type != null) {
                z = true;
            }
            if (z) {
                compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 3, StringSerializer.INSTANCE, extension.facebook_test_ad_type);
            }
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Extension(String str, Set set, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? SetsKt.emptySet() : set, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3);
        }
    }
}

package com.adsbynimbus.openrtb.request;

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
import kotlinx.serialization.internal.ByteSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 \u00182\u00020\u0001:\u0003\u0017\u0018\u0019B/\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nB\u0019\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\u000bJ&\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015HÁ\u0001¢\u0006\u0002\b\u0016R\u0018\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\rR\u0018\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u000e\u0010\r¨\u0006\u001a"}, d2 = {"Lcom/adsbynimbus/openrtb/request/Regs;", "", "seen1", "", "coppa", "", "ext", "Lcom/adsbynimbus/openrtb/request/Regs$Extension;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IBLcom/adsbynimbus/openrtb/request/Regs$Extension;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(BLcom/adsbynimbus/openrtb/request/Regs$Extension;)V", "getCoppa$annotations", "()V", "getExt$annotations", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$kotlin_release", "$serializer", "Companion", "Extension", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
@Serializable
/* compiled from: Regs.kt */
public final class Regs {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public byte coppa;
    public Extension ext;

    public Regs() {
        this((byte) 0, (Extension) null, 3, (DefaultConstructorMarker) null);
    }

    @SerialName("coppa")
    public static /* synthetic */ void getCoppa$annotations() {
    }

    @SerialName("ext")
    public static /* synthetic */ void getExt$annotations() {
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/adsbynimbus/openrtb/request/Regs$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/adsbynimbus/openrtb/request/Regs;", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
    /* compiled from: Regs.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<Regs> serializer() {
            return Regs$$serializer.INSTANCE;
        }
    }

    public Regs(byte b, Extension extension) {
        Intrinsics.checkNotNullParameter(extension, "ext");
        this.coppa = b;
        this.ext = extension;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ Regs(int i, @SerialName("coppa") byte b, @SerialName("ext") Extension extension, SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 0) != 0) {
            PluginExceptionsKt.throwMissingFieldException(i, 0, Regs$$serializer.INSTANCE.getDescriptor());
        }
        if ((i & 1) == 0) {
            this.coppa = 0;
        } else {
            this.coppa = b;
        }
        if ((i & 2) == 0) {
            this.ext = new Extension((Byte) null, (String) null, (String) null, (String) null, 15, (DefaultConstructorMarker) null);
        } else {
            this.ext = extension;
        }
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$kotlin_release(Regs regs, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        boolean z = false;
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 0) || regs.coppa != 0) {
            compositeEncoder.encodeByteElement(serialDescriptor, 0, regs.coppa);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 1) || !Intrinsics.areEqual((Object) regs.ext, (Object) new Extension((Byte) null, (String) null, (String) null, (String) null, 15, (DefaultConstructorMarker) null))) {
            z = true;
        }
        if (z) {
            compositeEncoder.encodeSerializableElement(serialDescriptor, 1, Regs$Extension$$serializer.INSTANCE, regs.ext);
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Regs(byte b, Extension extension, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 0 : b, (i & 2) != 0 ? new Extension((Byte) null, (String) null, (String) null, (String) null, 15, (DefaultConstructorMarker) null) : extension);
    }

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u001d2\u00020\u0001:\u0002\u001c\u001dBI\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fB5\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\rJ&\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aHÁ\u0001¢\u0006\u0002\b\u001bR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u000e¢\u0006\n\n\u0002\u0010\u0010\u0012\u0004\b\u000e\u0010\u000fR\u001a\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0011\u0010\u000fR\u001a\u0010\t\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0012\u0010\u000fR\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0013\u0010\u000f¨\u0006\u001e"}, d2 = {"Lcom/adsbynimbus/openrtb/request/Regs$Extension;", "", "seen1", "", "gdpr", "", "us_privacy", "", "gpp", "gpp_sids", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Byte;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/Byte;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getGdpr$annotations", "()V", "Ljava/lang/Byte;", "getGpp$annotations", "getGpp_sids$annotations", "getUs_privacy$annotations", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$kotlin_release", "$serializer", "Companion", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
    @Serializable
    /* compiled from: Regs.kt */
    public static final class Extension {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        public Byte gdpr;
        public String gpp;
        public String gpp_sids;
        public String us_privacy;

        public Extension() {
            this((Byte) null, (String) null, (String) null, (String) null, 15, (DefaultConstructorMarker) null);
        }

        @SerialName("gdpr")
        public static /* synthetic */ void getGdpr$annotations() {
        }

        @SerialName("gpp")
        public static /* synthetic */ void getGpp$annotations() {
        }

        @SerialName("gpp_sid")
        public static /* synthetic */ void getGpp_sids$annotations() {
        }

        @SerialName("us_privacy")
        public static /* synthetic */ void getUs_privacy$annotations() {
        }

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/adsbynimbus/openrtb/request/Regs$Extension$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/adsbynimbus/openrtb/request/Regs$Extension;", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
        /* compiled from: Regs.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Extension> serializer() {
                return Regs$Extension$$serializer.INSTANCE;
            }
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ Extension(int i, @SerialName("gdpr") Byte b, @SerialName("us_privacy") String str, @SerialName("gpp") String str2, @SerialName("gpp_sid") String str3, SerializationConstructorMarker serializationConstructorMarker) {
            if ((i & 0) != 0) {
                PluginExceptionsKt.throwMissingFieldException(i, 0, Regs$Extension$$serializer.INSTANCE.getDescriptor());
            }
            if ((i & 1) == 0) {
                this.gdpr = null;
            } else {
                this.gdpr = b;
            }
            if ((i & 2) == 0) {
                this.us_privacy = null;
            } else {
                this.us_privacy = str;
            }
            if ((i & 4) == 0) {
                this.gpp = null;
            } else {
                this.gpp = str2;
            }
            if ((i & 8) == 0) {
                this.gpp_sids = null;
            } else {
                this.gpp_sids = str3;
            }
        }

        public Extension(Byte b, String str, String str2, String str3) {
            this.gdpr = b;
            this.us_privacy = str;
            this.gpp = str2;
            this.gpp_sids = str3;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$kotlin_release(Extension extension, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
            boolean z = false;
            if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 0) || extension.gdpr != null) {
                compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 0, ByteSerializer.INSTANCE, extension.gdpr);
            }
            if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 1) || extension.us_privacy != null) {
                compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 1, StringSerializer.INSTANCE, extension.us_privacy);
            }
            if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 2) || extension.gpp != null) {
                compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 2, StringSerializer.INSTANCE, extension.gpp);
            }
            if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 3) || extension.gpp_sids != null) {
                z = true;
            }
            if (z) {
                compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 3, StringSerializer.INSTANCE, extension.gpp_sids);
            }
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Extension(Byte b, String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : b, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3);
        }
    }
}

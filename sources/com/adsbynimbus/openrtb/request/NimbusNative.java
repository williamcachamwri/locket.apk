package com.adsbynimbus.openrtb.request;

import java.util.List;
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
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.ByteSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 !2\u00020\u0001:\u0002 !B[\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\u0010\b\u0001\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0002\u0010\u000fB;\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0002\u0010\u0010J&\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eHÁ\u0001¢\u0006\u0002\b\u001fR\u001e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0011\u0010\u0012R\u001c\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u000e¢\u0006\n\n\u0002\u0010\u0014\u0012\u0004\b\u0013\u0010\u0012R\u001c\u0010\t\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u000e¢\u0006\n\n\u0002\u0010\u0014\u0012\u0004\b\u0015\u0010\u0012R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u000e¢\u0006\n\n\u0002\u0010\u0014\u0012\u0004\b\u0016\u0010\u0012R\u0018\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0017\u0010\u0012¨\u0006\""}, d2 = {"Lcom/adsbynimbus/openrtb/request/NimbusNative;", "", "seen1", "", "ver", "", "plcmttype", "", "context", "contextsubtype", "assets", "", "Lcom/adsbynimbus/openrtb/request/Asset;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/Byte;Ljava/lang/Byte;Ljava/lang/Byte;Ljava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/Byte;Ljava/lang/Byte;Ljava/lang/Byte;Ljava/util/List;)V", "getAssets$annotations", "()V", "getContext$annotations", "Ljava/lang/Byte;", "getContextsubtype$annotations", "getPlcmttype$annotations", "getVer$annotations", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$kotlin_release", "$serializer", "Companion", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
@Serializable
/* compiled from: Native.kt */
public final class NimbusNative {
    /* access modifiers changed from: private */
    public static final KSerializer<Object>[] $childSerializers = {null, null, null, null, new ArrayListSerializer(Asset$$serializer.INSTANCE)};
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public List<Asset> assets;
    public Byte context;
    public Byte contextsubtype;
    public Byte plcmttype;
    public String ver;

    @SerialName("assets")
    public static /* synthetic */ void getAssets$annotations() {
    }

    @SerialName("context")
    public static /* synthetic */ void getContext$annotations() {
    }

    @SerialName("contextsubtype")
    public static /* synthetic */ void getContextsubtype$annotations() {
    }

    @SerialName("plcmttype")
    public static /* synthetic */ void getPlcmttype$annotations() {
    }

    @SerialName("ver")
    public static /* synthetic */ void getVer$annotations() {
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/adsbynimbus/openrtb/request/NimbusNative$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/adsbynimbus/openrtb/request/NimbusNative;", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
    /* compiled from: Native.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<NimbusNative> serializer() {
            return NimbusNative$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ NimbusNative(int i, @SerialName("ver") String str, @SerialName("plcmttype") Byte b, @SerialName("context") Byte b2, @SerialName("contextsubtype") Byte b3, @SerialName("assets") List list, SerializationConstructorMarker serializationConstructorMarker) {
        if (30 != (i & 30)) {
            PluginExceptionsKt.throwMissingFieldException(i, 30, NimbusNative$$serializer.INSTANCE.getDescriptor());
        }
        if ((i & 1) == 0) {
            this.ver = "1.2";
        } else {
            this.ver = str;
        }
        this.plcmttype = b;
        this.context = b2;
        this.contextsubtype = b3;
        this.assets = list;
    }

    public NimbusNative(String str, Byte b, Byte b2, Byte b3, List<Asset> list) {
        Intrinsics.checkNotNullParameter(str, "ver");
        Intrinsics.checkNotNullParameter(list, "assets");
        this.ver = str;
        this.plcmttype = b;
        this.context = b2;
        this.contextsubtype = b3;
        this.assets = list;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$kotlin_release(NimbusNative nimbusNative, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        KSerializer<Object>[] kSerializerArr = $childSerializers;
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 0) || !Intrinsics.areEqual((Object) nimbusNative.ver, (Object) "1.2")) {
            compositeEncoder.encodeStringElement(serialDescriptor, 0, nimbusNative.ver);
        }
        compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 1, ByteSerializer.INSTANCE, nimbusNative.plcmttype);
        compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 2, ByteSerializer.INSTANCE, nimbusNative.context);
        compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 3, ByteSerializer.INSTANCE, nimbusNative.contextsubtype);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 4, kSerializerArr[4], nimbusNative.assets);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ NimbusNative(String str, Byte b, Byte b2, Byte b3, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "1.2" : str, b, b2, b3, list);
    }
}

package com.adsbynimbus.openrtb.request;

import java.util.Map;
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
import kotlinx.serialization.internal.ByteArraySerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.ReferenceArraySerializer;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u0000 '2\u00020\u0001:\u0006&'()*+Bu\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006\u0012\u0016\b\u0001\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0001\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\u0002\u0010\u0014B]\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010\u0015J&\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$HÁ\u0001¢\u0006\u0002\b%R\u001a\u0010\u0010\u001a\u0004\u0018\u00010\u00118\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0016\u0010\u0017R&\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u00010\b8\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0018\u0010\u0017R\u0018\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0019\u0010\u0017R\u001a\u0010\f\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u001a\u0010\u0017R\u0018\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u001b\u0010\u0017R\u001a\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u001c\u0010\u0017R\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u000f8\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u001d\u0010\u0017¨\u0006,"}, d2 = {"Lcom/adsbynimbus/openrtb/request/Asset;", "", "seen1", "", "id", "required", "", "ext", "", "", "title", "Lcom/adsbynimbus/openrtb/request/Asset$TitleObject;", "img", "Lcom/adsbynimbus/openrtb/request/Asset$ImageObject;", "video", "Lcom/adsbynimbus/openrtb/request/Asset$VideoObject;", "data", "Lcom/adsbynimbus/openrtb/request/Asset$DataObject;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IIBLjava/util/Map;Lcom/adsbynimbus/openrtb/request/Asset$TitleObject;Lcom/adsbynimbus/openrtb/request/Asset$ImageObject;Lcom/adsbynimbus/openrtb/request/Asset$VideoObject;Lcom/adsbynimbus/openrtb/request/Asset$DataObject;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(IBLjava/util/Map;Lcom/adsbynimbus/openrtb/request/Asset$TitleObject;Lcom/adsbynimbus/openrtb/request/Asset$ImageObject;Lcom/adsbynimbus/openrtb/request/Asset$VideoObject;Lcom/adsbynimbus/openrtb/request/Asset$DataObject;)V", "getData$annotations", "()V", "getExt$annotations", "getId$annotations", "getImg$annotations", "getRequired$annotations", "getTitle$annotations", "getVideo$annotations", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$kotlin_release", "$serializer", "Companion", "DataObject", "ImageObject", "TitleObject", "VideoObject", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
@Serializable
/* compiled from: Asset.kt */
public final class Asset {
    /* access modifiers changed from: private */
    public static final KSerializer<Object>[] $childSerializers = {null, null, new LinkedHashMapSerializer(StringSerializer.INSTANCE, StringSerializer.INSTANCE), null, null, null, null};
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public DataObject data;
    public Map<String, String> ext;
    public int id;
    public ImageObject img;
    public byte required;
    public TitleObject title;
    public VideoObject video;

    @SerialName("data")
    public static /* synthetic */ void getData$annotations() {
    }

    @SerialName("ext")
    public static /* synthetic */ void getExt$annotations() {
    }

    @SerialName("id")
    public static /* synthetic */ void getId$annotations() {
    }

    @SerialName("img")
    public static /* synthetic */ void getImg$annotations() {
    }

    @SerialName("required")
    public static /* synthetic */ void getRequired$annotations() {
    }

    @SerialName("title")
    public static /* synthetic */ void getTitle$annotations() {
    }

    @SerialName("video")
    public static /* synthetic */ void getVideo$annotations() {
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/adsbynimbus/openrtb/request/Asset$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/adsbynimbus/openrtb/request/Asset;", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
    /* compiled from: Asset.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<Asset> serializer() {
            return Asset$$serializer.INSTANCE;
        }
    }

    public Asset(int i, byte b, Map<String, String> map, TitleObject titleObject, ImageObject imageObject, VideoObject videoObject, DataObject dataObject) {
        this.id = i;
        this.required = b;
        this.ext = map;
        this.title = titleObject;
        this.img = imageObject;
        this.video = videoObject;
        this.data = dataObject;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ Asset(int i, @SerialName("id") int i2, @SerialName("required") byte b, @SerialName("ext") Map map, @SerialName("title") TitleObject titleObject, @SerialName("img") ImageObject imageObject, @SerialName("video") VideoObject videoObject, @SerialName("data") DataObject dataObject, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (i & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i, 3, Asset$$serializer.INSTANCE.getDescriptor());
        }
        this.id = i2;
        this.required = b;
        if ((i & 4) == 0) {
            this.ext = null;
        } else {
            this.ext = map;
        }
        if ((i & 8) == 0) {
            this.title = null;
        } else {
            this.title = titleObject;
        }
        if ((i & 16) == 0) {
            this.img = null;
        } else {
            this.img = imageObject;
        }
        if ((i & 32) == 0) {
            this.video = null;
        } else {
            this.video = videoObject;
        }
        if ((i & 64) == 0) {
            this.data = null;
        } else {
            this.data = dataObject;
        }
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$kotlin_release(Asset asset, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        KSerializer<Object>[] kSerializerArr = $childSerializers;
        boolean z = false;
        compositeEncoder.encodeIntElement(serialDescriptor, 0, asset.id);
        compositeEncoder.encodeByteElement(serialDescriptor, 1, asset.required);
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 2) || asset.ext != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 2, kSerializerArr[2], asset.ext);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 3) || asset.title != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 3, Asset$TitleObject$$serializer.INSTANCE, asset.title);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 4) || asset.img != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 4, Asset$ImageObject$$serializer.INSTANCE, asset.img);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 5) || asset.video != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 5, Asset$VideoObject$$serializer.INSTANCE, asset.video);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 6) || asset.data != null) {
            z = true;
        }
        if (z) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 6, Asset$DataObject$$serializer.INSTANCE, asset.data);
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Asset(int i, byte b, Map map, TitleObject titleObject, ImageObject imageObject, VideoObject videoObject, DataObject dataObject, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, b, (i2 & 4) != 0 ? null : map, (i2 & 8) != 0 ? null : titleObject, (i2 & 16) != 0 ? null : imageObject, (i2 & 32) != 0 ? null : videoObject, (i2 & 64) != 0 ? null : dataObject);
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00142\u00020\u0001:\u0002\u0013\u0014B#\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ&\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011HÁ\u0001¢\u0006\u0002\b\u0012R\u0018\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/adsbynimbus/openrtb/request/Asset$TitleObject;", "", "seen1", "", "length", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IILkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(I)V", "getLength$annotations", "()V", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$kotlin_release", "$serializer", "Companion", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
    @Serializable
    /* compiled from: Asset.kt */
    public static final class TitleObject {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        public int length;

        @SerialName("len")
        public static /* synthetic */ void getLength$annotations() {
        }

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/adsbynimbus/openrtb/request/Asset$TitleObject$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/adsbynimbus/openrtb/request/Asset$TitleObject;", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
        /* compiled from: Asset.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<TitleObject> serializer() {
                return Asset$TitleObject$$serializer.INSTANCE;
            }
        }

        public TitleObject(int i) {
            this.length = i;
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ TitleObject(int i, @SerialName("len") int i2, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, Asset$TitleObject$$serializer.INSTANCE.getDescriptor());
            }
            this.length = i2;
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u001e2\u00020\u0001:\u0002\u001d\u001eBS\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fB=\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\rJ&\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bHÁ\u0001¢\u0006\u0002\b\u001cR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u000e¢\u0006\n\n\u0002\u0010\u0010\u0012\u0004\b\u000e\u0010\u000fR\u001c\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u000e¢\u0006\n\n\u0002\u0010\u0010\u0012\u0004\b\u0011\u0010\u000fR\u0018\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0012\u0010\u000fR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u000e¢\u0006\n\n\u0002\u0010\u0010\u0012\u0004\b\u0013\u0010\u000fR\u001c\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u000e¢\u0006\n\n\u0002\u0010\u0010\u0012\u0004\b\u0014\u0010\u000f¨\u0006\u001f"}, d2 = {"Lcom/adsbynimbus/openrtb/request/Asset$ImageObject;", "", "seen1", "", "type", "", "w", "h", "hmin", "wmin", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IBLjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(BLjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getH$annotations", "()V", "Ljava/lang/Integer;", "getHmin$annotations", "getType$annotations", "getW$annotations", "getWmin$annotations", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$kotlin_release", "$serializer", "Companion", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
    @Serializable
    /* compiled from: Asset.kt */
    public static final class ImageObject {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        public Integer h;
        public Integer hmin;
        public byte type;
        public Integer w;
        public Integer wmin;

        @SerialName("h")
        public static /* synthetic */ void getH$annotations() {
        }

        @SerialName("hmin")
        public static /* synthetic */ void getHmin$annotations() {
        }

        @SerialName("type")
        public static /* synthetic */ void getType$annotations() {
        }

        @SerialName("w")
        public static /* synthetic */ void getW$annotations() {
        }

        @SerialName("wmin")
        public static /* synthetic */ void getWmin$annotations() {
        }

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/adsbynimbus/openrtb/request/Asset$ImageObject$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/adsbynimbus/openrtb/request/Asset$ImageObject;", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
        /* compiled from: Asset.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<ImageObject> serializer() {
                return Asset$ImageObject$$serializer.INSTANCE;
            }
        }

        public ImageObject(byte b, Integer num, Integer num2, Integer num3, Integer num4) {
            this.type = b;
            this.w = num;
            this.h = num2;
            this.hmin = num3;
            this.wmin = num4;
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ ImageObject(int i, @SerialName("type") byte b, @SerialName("w") Integer num, @SerialName("h") Integer num2, @SerialName("hmin") Integer num3, @SerialName("wmin") Integer num4, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, Asset$ImageObject$$serializer.INSTANCE.getDescriptor());
            }
            this.type = b;
            if ((i & 2) == 0) {
                this.w = null;
            } else {
                this.w = num;
            }
            if ((i & 4) == 0) {
                this.h = null;
            } else {
                this.h = num2;
            }
            if ((i & 8) == 0) {
                this.hmin = null;
            } else {
                this.hmin = num3;
            }
            if ((i & 16) == 0) {
                this.wmin = null;
            } else {
                this.wmin = num4;
            }
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$kotlin_release(ImageObject imageObject, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
            boolean z = false;
            compositeEncoder.encodeByteElement(serialDescriptor, 0, imageObject.type);
            if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 1) || imageObject.w != null) {
                compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 1, IntSerializer.INSTANCE, imageObject.w);
            }
            if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 2) || imageObject.h != null) {
                compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 2, IntSerializer.INSTANCE, imageObject.h);
            }
            if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 3) || imageObject.hmin != null) {
                compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 3, IntSerializer.INSTANCE, imageObject.hmin);
            }
            if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 4) || imageObject.wmin != null) {
                z = true;
            }
            if (z) {
                compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 4, IntSerializer.INSTANCE, imageObject.wmin);
            }
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ ImageObject(byte b, Integer num, Integer num2, Integer num3, Integer num4, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(b, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : num2, (i & 8) != 0 ? null : num3, (i & 16) != 0 ? null : num4);
        }
    }

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u001e2\u00020\u0001:\u0002\u001d\u001eBK\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0010\b\u0001\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0001\u0010\b\u001a\u00020\u0003\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\rB7\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000eJ&\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bHÁ\u0001¢\u0006\u0002\b\u001cR\u0018\u0010\b\u001a\u00020\u00038\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u000f\u0010\u0010R\"\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00058\u0006@\u0006X\u000e¢\u0006\n\n\u0002\u0010\u0012\u0012\u0004\b\u0011\u0010\u0010R\u0018\u0010\u0007\u001a\u00020\u00038\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0013\u0010\u0010R\u001a\u0010\t\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0014\u0010\u0010¨\u0006\u001f"}, d2 = {"Lcom/adsbynimbus/openrtb/request/Asset$VideoObject;", "", "seen1", "", "mimes", "", "", "minduration", "maxduration", "protocols", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(I[Ljava/lang/String;II[BLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "([Ljava/lang/String;II[B)V", "getMaxduration$annotations", "()V", "getMimes$annotations", "[Ljava/lang/String;", "getMinduration$annotations", "getProtocols$annotations", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$kotlin_release", "$serializer", "Companion", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
    @Serializable
    /* compiled from: Asset.kt */
    public static final class VideoObject {
        /* access modifiers changed from: private */
        public static final KSerializer<Object>[] $childSerializers = {new ReferenceArraySerializer(Reflection.getOrCreateKotlinClass(String.class), StringSerializer.INSTANCE), null, null, null};
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        public int maxduration;
        public String[] mimes;
        public int minduration;
        public byte[] protocols;

        public VideoObject() {
            this((String[]) null, 0, 0, (byte[]) null, 15, (DefaultConstructorMarker) null);
        }

        @SerialName("maxduration")
        public static /* synthetic */ void getMaxduration$annotations() {
        }

        @SerialName("mimes")
        public static /* synthetic */ void getMimes$annotations() {
        }

        @SerialName("minduration")
        public static /* synthetic */ void getMinduration$annotations() {
        }

        @SerialName("protocols")
        public static /* synthetic */ void getProtocols$annotations() {
        }

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/adsbynimbus/openrtb/request/Asset$VideoObject$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/adsbynimbus/openrtb/request/Asset$VideoObject;", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
        /* compiled from: Asset.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<VideoObject> serializer() {
                return Asset$VideoObject$$serializer.INSTANCE;
            }
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ VideoObject(int i, @SerialName("mimes") String[] strArr, @SerialName("minduration") int i2, @SerialName("maxduration") int i3, @SerialName("protocols") byte[] bArr, SerializationConstructorMarker serializationConstructorMarker) {
            if ((i & 0) != 0) {
                PluginExceptionsKt.throwMissingFieldException(i, 0, Asset$VideoObject$$serializer.INSTANCE.getDescriptor());
            }
            if ((i & 1) == 0) {
                this.mimes = null;
            } else {
                this.mimes = strArr;
            }
            if ((i & 2) == 0) {
                this.minduration = 0;
            } else {
                this.minduration = i2;
            }
            if ((i & 4) == 0) {
                this.maxduration = 60;
            } else {
                this.maxduration = i3;
            }
            if ((i & 8) == 0) {
                this.protocols = null;
            } else {
                this.protocols = bArr;
            }
        }

        public VideoObject(String[] strArr, int i, int i2, byte[] bArr) {
            this.mimes = strArr;
            this.minduration = i;
            this.maxduration = i2;
            this.protocols = bArr;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$kotlin_release(VideoObject videoObject, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
            KSerializer<Object>[] kSerializerArr = $childSerializers;
            boolean z = false;
            if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 0) || videoObject.mimes != null) {
                compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 0, kSerializerArr[0], videoObject.mimes);
            }
            if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 1) || videoObject.minduration != 0) {
                compositeEncoder.encodeIntElement(serialDescriptor, 1, videoObject.minduration);
            }
            if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 2) || videoObject.maxduration != 60) {
                compositeEncoder.encodeIntElement(serialDescriptor, 2, videoObject.maxduration);
            }
            if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 3) || videoObject.protocols != null) {
                z = true;
            }
            if (z) {
                compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 3, ByteArraySerializer.INSTANCE, videoObject.protocols);
            }
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ VideoObject(String[] strArr, int i, int i2, byte[] bArr, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? null : strArr, (i3 & 2) != 0 ? 0 : i, (i3 & 4) != 0 ? 60 : i2, (i3 & 8) != 0 ? null : bArr);
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00172\u00020\u0001:\u0002\u0016\u0017B-\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tB\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ&\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014HÁ\u0001¢\u0006\u0002\b\u0015R\u0018\u0010\u0006\u001a\u00020\u00038\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u000b\u0010\fR\u0018\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\r\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/adsbynimbus/openrtb/request/Asset$DataObject;", "", "seen1", "", "type", "", "len", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IBILkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(BI)V", "getLen$annotations", "()V", "getType$annotations", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$kotlin_release", "$serializer", "Companion", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
    @Serializable
    /* compiled from: Asset.kt */
    public static final class DataObject {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        public int len;
        public byte type;

        @SerialName("len")
        public static /* synthetic */ void getLen$annotations() {
        }

        @SerialName("type")
        public static /* synthetic */ void getType$annotations() {
        }

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/adsbynimbus/openrtb/request/Asset$DataObject$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/adsbynimbus/openrtb/request/Asset$DataObject;", "kotlin_release"}, k = 1, mv = {1, 7, 0}, xi = 48)
        /* compiled from: Asset.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<DataObject> serializer() {
                return Asset$DataObject$$serializer.INSTANCE;
            }
        }

        public DataObject(byte b, int i) {
            this.type = b;
            this.len = i;
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ DataObject(int i, @SerialName("type") byte b, @SerialName("len") int i2, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, Asset$DataObject$$serializer.INSTANCE.getDescriptor());
            }
            this.type = b;
            this.len = i2;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$kotlin_release(DataObject dataObject, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
            compositeEncoder.encodeByteElement(serialDescriptor, 0, dataObject.type);
            compositeEncoder.encodeIntElement(serialDescriptor, 1, dataObject.len);
        }
    }
}

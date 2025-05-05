package com.adsbynimbus.render.mraid;

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
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u0000 \u00172\u00020\u0001:\u0002\u0016\u0017B%\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bB\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ&\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014HÁ\u0001¢\u0006\u0002\b\u0015R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lcom/adsbynimbus/render/mraid/SetResizeProperties;", "Lcom/adsbynimbus/render/mraid/Command;", "seen1", "", "properties", "Lcom/adsbynimbus/render/mraid/ResizeProperties;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/adsbynimbus/render/mraid/ResizeProperties;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Lcom/adsbynimbus/render/mraid/ResizeProperties;)V", "getProperties$annotations", "()V", "getProperties", "()Lcom/adsbynimbus/render/mraid/ResizeProperties;", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$static_release", "$serializer", "Companion", "static_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Serializable
@SerialName("ResizeProperties")
/* compiled from: Command.kt */
public final class SetResizeProperties extends Command {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final ResizeProperties properties;

    @SerialName("data")
    public static /* synthetic */ void getProperties$annotations() {
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/adsbynimbus/render/mraid/SetResizeProperties$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/adsbynimbus/render/mraid/SetResizeProperties;", "static_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: Command.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<SetResizeProperties> serializer() {
            return SetResizeProperties$$serializer.INSTANCE;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ SetResizeProperties(int i, @SerialName("data") ResizeProperties resizeProperties, SerializationConstructorMarker serializationConstructorMarker) {
        super(i, serializationConstructorMarker);
        if (1 != (i & 1)) {
            PluginExceptionsKt.throwMissingFieldException(i, 1, SetResizeProperties$$serializer.INSTANCE.getDescriptor());
        }
        this.properties = resizeProperties;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$static_release(SetResizeProperties setResizeProperties, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        Command.write$Self(setResizeProperties, compositeEncoder, serialDescriptor);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 0, ResizeProperties$$serializer.INSTANCE, setResizeProperties.properties);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SetResizeProperties(ResizeProperties resizeProperties) {
        super((DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(resizeProperties, "properties");
        this.properties = resizeProperties;
    }

    public final ResizeProperties getProperties() {
        return this.properties;
    }
}

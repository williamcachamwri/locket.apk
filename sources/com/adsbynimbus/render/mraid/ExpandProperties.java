package com.adsbynimbus.render.mraid;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u001d2\u00020\u0001:\u0002\u001c\u001dB;\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0001\u0010\b\u001a\u00020\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bB)\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\fJ&\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aHÁ\u0001¢\u0006\u0002\b\u001bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000fR\u001c\u0010\b\u001a\u00020\u00078\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000e¨\u0006\u001e"}, d2 = {"Lcom/adsbynimbus/render/mraid/ExpandProperties;", "", "seen1", "", "width", "height", "isModal", "", "useCustomClose", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IIIZZLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(IIZZ)V", "getHeight", "()I", "()Z", "getUseCustomClose$annotations", "()V", "getUseCustomClose", "getWidth", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$static_release", "$serializer", "Companion", "static_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Serializable
/* compiled from: Properties.kt */
public final class ExpandProperties {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final int height;
    private final boolean isModal;
    private final boolean useCustomClose;
    private final int width;

    @Deprecated(message = "Deprecated in MRAID 3")
    public static /* synthetic */ void getUseCustomClose$annotations() {
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/adsbynimbus/render/mraid/ExpandProperties$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/adsbynimbus/render/mraid/ExpandProperties;", "static_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: Properties.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<ExpandProperties> serializer() {
            return ExpandProperties$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ ExpandProperties(int i, int i2, int i3, boolean z, @Deprecated(message = "Deprecated in MRAID 3") boolean z2, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (i & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i, 3, ExpandProperties$$serializer.INSTANCE.getDescriptor());
        }
        this.width = i2;
        this.height = i3;
        if ((i & 4) == 0) {
            this.isModal = false;
        } else {
            this.isModal = z;
        }
        if ((i & 8) == 0) {
            this.useCustomClose = false;
        } else {
            this.useCustomClose = z2;
        }
    }

    public ExpandProperties(int i, int i2, boolean z, boolean z2) {
        this.width = i;
        this.height = i2;
        this.isModal = z;
        this.useCustomClose = z2;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$static_release(ExpandProperties expandProperties, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        boolean z = false;
        compositeEncoder.encodeIntElement(serialDescriptor, 0, expandProperties.width);
        compositeEncoder.encodeIntElement(serialDescriptor, 1, expandProperties.height);
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 2) || expandProperties.isModal) {
            compositeEncoder.encodeBooleanElement(serialDescriptor, 2, expandProperties.isModal);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 3) || expandProperties.useCustomClose) {
            z = true;
        }
        if (z) {
            compositeEncoder.encodeBooleanElement(serialDescriptor, 3, expandProperties.useCustomClose);
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ExpandProperties(int i, int i2, boolean z, boolean z2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, (i3 & 4) != 0 ? false : z, (i3 & 8) != 0 ? false : z2);
    }

    public final int getWidth() {
        return this.width;
    }

    public final int getHeight() {
        return this.height;
    }

    public final boolean isModal() {
        return this.isModal;
    }

    public final boolean getUseCustomClose() {
        return this.useCustomClose;
    }
}

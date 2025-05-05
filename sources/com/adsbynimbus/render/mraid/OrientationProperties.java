package com.adsbynimbus.render.mraid;

import expo.modules.devlauncher.launcher.manifest.DevLauncherOrientation;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00192\u00020\u0001:\u0002\u0018\u0019B+\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nB\u0019\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\u000bJ&\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016HÁ\u0001¢\u0006\u0002\b\u0017R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/adsbynimbus/render/mraid/OrientationProperties;", "", "seen1", "", "allowOrientationChange", "", "forceOrientation", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IZLjava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(ZLjava/lang/String;)V", "getAllowOrientationChange", "()Z", "getForceOrientation", "()Ljava/lang/String;", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$static_release", "$serializer", "Companion", "static_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Serializable
/* compiled from: Properties.kt */
public final class OrientationProperties {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final boolean allowOrientationChange;
    private final String forceOrientation;

    public OrientationProperties() {
        this(false, (String) null, 3, (DefaultConstructorMarker) null);
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/adsbynimbus/render/mraid/OrientationProperties$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/adsbynimbus/render/mraid/OrientationProperties;", "static_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: Properties.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<OrientationProperties> serializer() {
            return OrientationProperties$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ OrientationProperties(int i, boolean z, String str, SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 0) != 0) {
            PluginExceptionsKt.throwMissingFieldException(i, 0, OrientationProperties$$serializer.INSTANCE.getDescriptor());
        }
        this.allowOrientationChange = (i & 1) == 0 ? true : z;
        if ((i & 2) == 0) {
            this.forceOrientation = "none";
        } else {
            this.forceOrientation = str;
        }
        if (!SetsKt.setOf("none", DevLauncherOrientation.LANDSCAPE, DevLauncherOrientation.PORTRAIT).contains(this.forceOrientation)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public OrientationProperties(boolean z, String str) {
        Intrinsics.checkNotNullParameter(str, "forceOrientation");
        this.allowOrientationChange = z;
        this.forceOrientation = str;
        if (!SetsKt.setOf("none", DevLauncherOrientation.LANDSCAPE, DevLauncherOrientation.PORTRAIT).contains(str)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$static_release(OrientationProperties orientationProperties, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        boolean z = false;
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 0) || !orientationProperties.allowOrientationChange) {
            compositeEncoder.encodeBooleanElement(serialDescriptor, 0, orientationProperties.allowOrientationChange);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 1) || !Intrinsics.areEqual((Object) orientationProperties.forceOrientation, (Object) "none")) {
            z = true;
        }
        if (z) {
            compositeEncoder.encodeStringElement(serialDescriptor, 1, orientationProperties.forceOrientation);
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ OrientationProperties(boolean z, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z, (i & 2) != 0 ? "none" : str);
    }

    public final boolean getAllowOrientationChange() {
        return this.allowOrientationChange;
    }

    public final String getForceOrientation() {
        return this.forceOrientation;
    }
}

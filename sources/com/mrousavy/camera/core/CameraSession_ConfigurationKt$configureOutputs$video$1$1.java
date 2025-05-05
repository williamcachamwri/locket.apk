package com.mrousavy.camera.core;

import com.mrousavy.camera.core.types.CameraDeviceFormat;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/mrousavy/camera/core/types/CameraDeviceFormat;", "invoke", "(Lcom/mrousavy/camera/core/types/CameraDeviceFormat;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraSession+Configuration.kt */
final class CameraSession_ConfigurationKt$configureOutputs$video$1$1 extends Lambda implements Function1<CameraDeviceFormat, Boolean> {
    final /* synthetic */ CameraConfiguration $configuration;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CameraSession_ConfigurationKt$configureOutputs$video$1$1(CameraConfiguration cameraConfiguration) {
        super(1);
        this.$configuration = cameraConfiguration;
    }

    public final Boolean invoke(CameraDeviceFormat cameraDeviceFormat) {
        Intrinsics.checkNotNullParameter(cameraDeviceFormat, "it");
        return Boolean.valueOf(cameraDeviceFormat.getVideoStabilizationModes().contains(this.$configuration.getVideoStabilizationMode()));
    }
}

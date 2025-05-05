package com.mrousavy.camera.core;

import android.util.Range;
import com.mrousavy.camera.core.types.CameraDeviceFormat;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/mrousavy/camera/core/types/CameraDeviceFormat;", "invoke", "(Lcom/mrousavy/camera/core/types/CameraDeviceFormat;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraSession+Configuration.kt */
final class CameraSession_ConfigurationKt$configureOutputs$analyzer$1$1 extends Lambda implements Function1<CameraDeviceFormat, Boolean> {
    final /* synthetic */ Range<Integer> $fpsRange;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CameraSession_ConfigurationKt$configureOutputs$analyzer$1$1(Range<Integer> range) {
        super(1);
        this.$fpsRange = range;
    }

    public final Boolean invoke(CameraDeviceFormat cameraDeviceFormat) {
        boolean z;
        Intrinsics.checkNotNullParameter(cameraDeviceFormat, "it");
        Integer lower = this.$fpsRange.getLower();
        Double d = null;
        if ((lower != null ? Double.valueOf((double) lower.intValue()) : null).doubleValue() >= cameraDeviceFormat.getMinFps()) {
            Integer upper = this.$fpsRange.getUpper();
            if (upper != null) {
                d = Double.valueOf((double) upper.intValue());
            }
            if (d.doubleValue() <= cameraDeviceFormat.getMaxFps()) {
                z = true;
                return Boolean.valueOf(z);
            }
        }
        z = false;
        return Boolean.valueOf(z);
    }
}

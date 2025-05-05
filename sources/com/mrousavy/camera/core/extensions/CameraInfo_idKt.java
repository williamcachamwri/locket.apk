package com.mrousavy.camera.core.extensions;

import androidx.camera.core.CameraInfo;
import androidx.camera.core.impl.CameraInfoInternal;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0017\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028G¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"id", "", "Landroidx/camera/core/CameraInfo;", "getId", "(Landroidx/camera/core/CameraInfo;)Ljava/lang/String;", "react-native-vision-camera_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraInfo+id.kt */
public final class CameraInfo_idKt {
    public static final String getId(CameraInfo cameraInfo) {
        Intrinsics.checkNotNullParameter(cameraInfo, "<this>");
        CameraInfoInternal cameraInfoInternal = cameraInfo instanceof CameraInfoInternal ? (CameraInfoInternal) cameraInfo : null;
        if (cameraInfoInternal != null) {
            return cameraInfoInternal.getCameraId();
        }
        return null;
    }
}

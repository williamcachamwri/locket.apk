package com.mrousavy.camera.core;

import androidx.camera.core.MeteringPoint;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroidx/camera/core/MeteringPoint;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraSession+Focus.kt */
final class CameraSession_FocusKt$focus$2 extends Lambda implements Function1<MeteringPoint, CharSequence> {
    public static final CameraSession_FocusKt$focus$2 INSTANCE = new CameraSession_FocusKt$focus$2();

    CameraSession_FocusKt$focus$2() {
        super(1);
    }

    public final CharSequence invoke(MeteringPoint meteringPoint) {
        float x = meteringPoint.getX();
        return "(" + x + ", " + meteringPoint.getY() + ")";
    }
}

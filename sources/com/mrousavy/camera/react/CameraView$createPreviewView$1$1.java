package com.mrousavy.camera.react;

import android.util.Log;
import androidx.camera.view.PreviewView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "state", "Landroidx/camera/view/PreviewView$StreamState;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraView.kt */
final class CameraView$createPreviewView$1$1 extends Lambda implements Function1<PreviewView.StreamState, Unit> {
    final /* synthetic */ Ref.BooleanRef $lastIsPreviewing;
    final /* synthetic */ CameraView this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CameraView$createPreviewView$1$1(Ref.BooleanRef booleanRef, CameraView cameraView) {
        super(1);
        this.$lastIsPreviewing = booleanRef;
        this.this$0 = cameraView;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((PreviewView.StreamState) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(PreviewView.StreamState streamState) {
        Log.i("CameraView", "PreviewView Stream State changed to " + streamState);
        boolean z = streamState == PreviewView.StreamState.STREAMING;
        if (z != this.$lastIsPreviewing.element) {
            if (z) {
                CameraView_EventsKt.invokeOnPreviewStarted(this.this$0);
            } else {
                CameraView_EventsKt.invokeOnPreviewStopped(this.this$0);
            }
            this.$lastIsPreviewing.element = z;
        }
    }
}

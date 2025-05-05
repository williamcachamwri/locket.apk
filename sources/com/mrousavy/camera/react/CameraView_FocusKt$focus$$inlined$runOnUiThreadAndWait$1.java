package com.mrousavy.camera.react;

import android.content.res.Resources;
import androidx.camera.core.MeteringPoint;
import androidx.camera.view.PreviewView;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Result;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\n¢\u0006\u0002\b\u0003¨\u0006\u0004"}, d2 = {"<anonymous>", "", "T", "run", "com/mrousavy/camera/core/utils/RunOnUiThreadKt$runOnUiThreadAndWait$2$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: runOnUiThread.kt */
public final class CameraView_FocusKt$focus$$inlined$runOnUiThreadAndWait$1 implements Runnable {
    final /* synthetic */ CancellableContinuation $continuation;
    final /* synthetic */ PreviewView $previewView$inlined;
    final /* synthetic */ double $x$inlined;
    final /* synthetic */ double $y$inlined;

    public CameraView_FocusKt$focus$$inlined$runOnUiThreadAndWait$1(CancellableContinuation cancellableContinuation, PreviewView previewView, double d, double d2) {
        this.$continuation = cancellableContinuation;
        this.$previewView$inlined = previewView;
        this.$x$inlined = d;
        this.$y$inlined = d2;
    }

    public final void run() {
        if (!this.$continuation.isCancelled()) {
            float f = Resources.getSystem().getDisplayMetrics().density;
            MeteringPoint createPoint = this.$previewView$inlined.getMeteringPointFactory().createPoint(((float) this.$x$inlined) * f, ((float) this.$y$inlined) * f);
            Result.Companion companion = Result.Companion;
            this.$continuation.resumeWith(Result.m2444constructorimpl(createPoint));
            return;
        }
        throw new CancellationException();
    }
}

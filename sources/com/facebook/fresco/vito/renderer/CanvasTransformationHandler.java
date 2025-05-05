package com.facebook.fresco.vito.renderer;

import android.graphics.Matrix;
import android.graphics.Rect;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u001e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010J\b\u0010\u0012\u001a\u0004\u0018\u00010\tR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/facebook/fresco/vito/renderer/CanvasTransformationHandler;", "", "canvasTransformation", "Lcom/facebook/fresco/vito/renderer/CanvasTransformation;", "(Lcom/facebook/fresco/vito/renderer/CanvasTransformation;)V", "getCanvasTransformation", "()Lcom/facebook/fresco/vito/renderer/CanvasTransformation;", "setCanvasTransformation", "drawMatrix", "Landroid/graphics/Matrix;", "tempMatrix", "configure", "", "bounds", "Landroid/graphics/Rect;", "childWidth", "", "childHeight", "getMatrix", "renderer_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CanvasTransformationHandler.kt */
public final class CanvasTransformationHandler {
    private CanvasTransformation canvasTransformation;
    private Matrix drawMatrix;
    private final Matrix tempMatrix;

    public CanvasTransformationHandler() {
        this((CanvasTransformation) null, 1, (DefaultConstructorMarker) null);
    }

    public CanvasTransformationHandler(CanvasTransformation canvasTransformation2) {
        this.canvasTransformation = canvasTransformation2;
        this.tempMatrix = new Matrix();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ CanvasTransformationHandler(CanvasTransformation canvasTransformation2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : canvasTransformation2);
    }

    public final CanvasTransformation getCanvasTransformation() {
        return this.canvasTransformation;
    }

    public final void setCanvasTransformation(CanvasTransformation canvasTransformation2) {
        this.canvasTransformation = canvasTransformation2;
    }

    public final Matrix getMatrix() {
        return this.drawMatrix;
    }

    public final void configure(Rect rect, int i, int i2) {
        Intrinsics.checkNotNullParameter(rect, "bounds");
        Matrix matrix = null;
        if (i <= 0 || i2 <= 0) {
            this.drawMatrix = null;
            return;
        }
        CanvasTransformation canvasTransformation2 = this.canvasTransformation;
        if (canvasTransformation2 != null) {
            matrix = canvasTransformation2.calculateTransformation(this.tempMatrix, rect, i, i2);
        }
        this.drawMatrix = matrix;
    }
}

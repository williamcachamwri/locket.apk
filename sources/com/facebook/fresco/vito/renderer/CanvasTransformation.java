package com.facebook.fresco.vito.renderer;

import android.graphics.Matrix;
import android.graphics.Rect;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J*\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH&¨\u0006\n"}, d2 = {"Lcom/facebook/fresco/vito/renderer/CanvasTransformation;", "", "calculateTransformation", "Landroid/graphics/Matrix;", "outTransform", "parentBounds", "Landroid/graphics/Rect;", "childWidth", "", "childHeight", "renderer_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CanvasTransformation.kt */
public interface CanvasTransformation {
    Matrix calculateTransformation(Matrix matrix, Rect rect, int i, int i2);
}

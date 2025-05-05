package com.facebook.fresco.vito.renderer;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "canvas", "Landroid/graphics/Canvas;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
/* compiled from: ImageRenderer.kt */
public final class ImageRenderer$createRenderCommand$1 extends Lambda implements Function1<Canvas, Unit> {
    final /* synthetic */ Matrix $imageTransformation;
    final /* synthetic */ Paint $paint;
    final /* synthetic */ Shape $shape;
    final /* synthetic */ DrawableImageDataModel $this_createRenderCommand;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ImageRenderer$createRenderCommand$1(DrawableImageDataModel drawableImageDataModel, Matrix matrix, Shape shape, Paint paint) {
        super(1);
        this.$this_createRenderCommand = drawableImageDataModel;
        this.$imageTransformation = matrix;
        this.$shape = shape;
        this.$paint = paint;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Canvas) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        if (this.$this_createRenderCommand.getWidth() < 0 || this.$this_createRenderCommand.getHeight() < 0) {
            this.$this_createRenderCommand.getDrawable().setBounds((int) ((RectShape) this.$shape).getRect().left, (int) ((RectShape) this.$shape).getRect().top, (int) ((RectShape) this.$shape).getRect().right, (int) ((RectShape) this.$shape).getRect().bottom);
        } else {
            this.$this_createRenderCommand.getDrawable().setBounds(0, 0, this.$this_createRenderCommand.getWidth(), this.$this_createRenderCommand.getHeight());
            canvas.concat(this.$imageTransformation);
        }
        this.$this_createRenderCommand.getDrawable().setColorFilter(this.$paint.getColorFilter());
        this.$this_createRenderCommand.getDrawable().setAlpha(this.$paint.getAlpha());
        this.$this_createRenderCommand.getDrawable().draw(canvas);
    }
}

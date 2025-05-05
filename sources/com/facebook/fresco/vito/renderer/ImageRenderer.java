package com.facebook.fresco.vito.renderer;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import com.facebook.fresco.vito.renderer.util.ColorUtils;
import io.sentry.protocol.Device;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J3\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u0002`\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\bJ:\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u0002`\u00072\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\rJ)\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u0002`\u00072\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\tH\bJ9\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u0002`\u0007*\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\rH\bJ-\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u0002`\u0007*\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\tH\bJ9\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u0002`\u0007*\u00020\u00172\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\rH\bJ!\u0010\u0018\u001a\u00020\t*\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\rH\b¨\u0006\u001a"}, d2 = {"Lcom/facebook/fresco/vito/renderer/ImageRenderer;", "", "()V", "bitmapRenderCommand", "Lkotlin/Function1;", "Landroid/graphics/Canvas;", "", "Lcom/facebook/fresco/vito/renderer/RenderCommand;", "paint", "Landroid/graphics/Paint;", "bitmap", "Landroid/graphics/Bitmap;", "imageTransformation", "Landroid/graphics/Matrix;", "createImageDataModelRenderCommand", "model", "Lcom/facebook/fresco/vito/renderer/ImageDataModel;", "shape", "Lcom/facebook/fresco/vito/renderer/Shape;", "paintRenderCommand", "createRenderCommand", "Lcom/facebook/fresco/vito/renderer/BitmapImageDataModel;", "Lcom/facebook/fresco/vito/renderer/ColorIntImageDataModel;", "Lcom/facebook/fresco/vito/renderer/DrawableImageDataModel;", "setBitmap", "shaderTransformation", "renderer_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ImageRenderer.kt */
public final class ImageRenderer {
    public static final ImageRenderer INSTANCE = new ImageRenderer();

    private ImageRenderer() {
    }

    public static /* synthetic */ Function1 createImageDataModelRenderCommand$default(ImageRenderer imageRenderer, ImageDataModel imageDataModel, Shape shape, Paint paint, Matrix matrix, int i, Object obj) {
        if ((i & 8) != 0) {
            matrix = null;
        }
        return imageRenderer.createImageDataModelRenderCommand(imageDataModel, shape, paint, matrix);
    }

    public final Function1<Canvas, Unit> createImageDataModelRenderCommand(ImageDataModel imageDataModel, Shape shape, Paint paint, Matrix matrix) {
        Intrinsics.checkNotNullParameter(imageDataModel, Device.JsonKeys.MODEL);
        Intrinsics.checkNotNullParameter(shape, "shape");
        Intrinsics.checkNotNullParameter(paint, "paint");
        if (imageDataModel instanceof BitmapImageDataModel) {
            BitmapImageDataModel bitmapImageDataModel = (BitmapImageDataModel) imageDataModel;
            if (shape instanceof RectShape) {
                return new ImageRenderer$bitmapRenderCommand$1(matrix, bitmapImageDataModel.getBitmap(), paint);
            }
            if (!(shape instanceof CircleShape)) {
                paint.setShader(new BitmapShader(bitmapImageDataModel.getBitmap(), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
                paint.getShader().setLocalMatrix(matrix);
                return new ImageRenderer$paintRenderCommand$1(shape, paint);
            } else if (bitmapImageDataModel.isBitmapCircular()) {
                return new ImageRenderer$bitmapRenderCommand$1(matrix, bitmapImageDataModel.getBitmap(), paint);
            } else {
                paint.setShader(new BitmapShader(bitmapImageDataModel.getBitmap(), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
                paint.getShader().setLocalMatrix(matrix);
                return new ImageRenderer$paintRenderCommand$1(shape, paint);
            }
        } else if (imageDataModel instanceof ColorIntImageDataModel) {
            paint.setColor(ColorUtils.Companion.multiplyColorAlpha(((ColorIntImageDataModel) imageDataModel).getColorInt(), paint.getAlpha()));
            return new ImageRenderer$paintRenderCommand$1(shape, paint);
        } else if (imageDataModel instanceof DrawableImageDataModel) {
            DrawableImageDataModel drawableImageDataModel = (DrawableImageDataModel) imageDataModel;
            if (shape instanceof RectShape) {
                return new ImageRenderer$createRenderCommand$1(drawableImageDataModel, matrix, shape, paint);
            }
            return new ImageRenderer$createRenderCommand$2(drawableImageDataModel, paint, matrix, shape);
        } else {
            throw new NoWhenBranchMatchedException();
        }
    }

    public static /* synthetic */ Function1 createRenderCommand$default(ImageRenderer imageRenderer, BitmapImageDataModel bitmapImageDataModel, Shape shape, Paint paint, Matrix matrix, int i, Object obj) {
        if ((i & 4) != 0) {
            matrix = null;
        }
        Intrinsics.checkNotNullParameter(bitmapImageDataModel, "<this>");
        Intrinsics.checkNotNullParameter(shape, "shape");
        Intrinsics.checkNotNullParameter(paint, "paint");
        if (shape instanceof RectShape) {
            return new ImageRenderer$bitmapRenderCommand$1(matrix, bitmapImageDataModel.getBitmap(), paint);
        }
        if (!(shape instanceof CircleShape)) {
            paint.setShader(new BitmapShader(bitmapImageDataModel.getBitmap(), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
            paint.getShader().setLocalMatrix(matrix);
            return new ImageRenderer$paintRenderCommand$1(shape, paint);
        } else if (bitmapImageDataModel.isBitmapCircular()) {
            return new ImageRenderer$bitmapRenderCommand$1(matrix, bitmapImageDataModel.getBitmap(), paint);
        } else {
            paint.setShader(new BitmapShader(bitmapImageDataModel.getBitmap(), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
            paint.getShader().setLocalMatrix(matrix);
            return new ImageRenderer$paintRenderCommand$1(shape, paint);
        }
    }

    public final Function1<Canvas, Unit> createRenderCommand(BitmapImageDataModel bitmapImageDataModel, Shape shape, Paint paint, Matrix matrix) {
        Intrinsics.checkNotNullParameter(bitmapImageDataModel, "<this>");
        Intrinsics.checkNotNullParameter(shape, "shape");
        Intrinsics.checkNotNullParameter(paint, "paint");
        if (shape instanceof RectShape) {
            return new ImageRenderer$bitmapRenderCommand$1(matrix, bitmapImageDataModel.getBitmap(), paint);
        }
        if (!(shape instanceof CircleShape)) {
            paint.setShader(new BitmapShader(bitmapImageDataModel.getBitmap(), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
            paint.getShader().setLocalMatrix(matrix);
            return new ImageRenderer$paintRenderCommand$1(shape, paint);
        } else if (bitmapImageDataModel.isBitmapCircular()) {
            return new ImageRenderer$bitmapRenderCommand$1(matrix, bitmapImageDataModel.getBitmap(), paint);
        } else {
            paint.setShader(new BitmapShader(bitmapImageDataModel.getBitmap(), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
            paint.getShader().setLocalMatrix(matrix);
            return new ImageRenderer$paintRenderCommand$1(shape, paint);
        }
    }

    public final Function1<Canvas, Unit> createRenderCommand(ColorIntImageDataModel colorIntImageDataModel, Shape shape, Paint paint) {
        Intrinsics.checkNotNullParameter(colorIntImageDataModel, "<this>");
        Intrinsics.checkNotNullParameter(shape, "shape");
        Intrinsics.checkNotNullParameter(paint, "paint");
        paint.setColor(ColorUtils.Companion.multiplyColorAlpha(colorIntImageDataModel.getColorInt(), paint.getAlpha()));
        return new ImageRenderer$paintRenderCommand$1(shape, paint);
    }

    public static /* synthetic */ Function1 createRenderCommand$default(ImageRenderer imageRenderer, DrawableImageDataModel drawableImageDataModel, Shape shape, Paint paint, Matrix matrix, int i, Object obj) {
        if ((i & 4) != 0) {
            matrix = null;
        }
        Intrinsics.checkNotNullParameter(drawableImageDataModel, "<this>");
        Intrinsics.checkNotNullParameter(shape, "shape");
        Intrinsics.checkNotNullParameter(paint, "paint");
        return shape instanceof RectShape ? new ImageRenderer$createRenderCommand$1(drawableImageDataModel, matrix, shape, paint) : new ImageRenderer$createRenderCommand$2(drawableImageDataModel, paint, matrix, shape);
    }

    public final Function1<Canvas, Unit> createRenderCommand(DrawableImageDataModel drawableImageDataModel, Shape shape, Paint paint, Matrix matrix) {
        Intrinsics.checkNotNullParameter(drawableImageDataModel, "<this>");
        Intrinsics.checkNotNullParameter(shape, "shape");
        Intrinsics.checkNotNullParameter(paint, "paint");
        return shape instanceof RectShape ? new ImageRenderer$createRenderCommand$1(drawableImageDataModel, matrix, shape, paint) : new ImageRenderer$createRenderCommand$2(drawableImageDataModel, paint, matrix, shape);
    }

    public final Function1<Canvas, Unit> bitmapRenderCommand(Paint paint, Bitmap bitmap, Matrix matrix) {
        Intrinsics.checkNotNullParameter(paint, "paint");
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        return new ImageRenderer$bitmapRenderCommand$1(matrix, bitmap, paint);
    }

    public final Function1<Canvas, Unit> paintRenderCommand(Shape shape, Paint paint) {
        Intrinsics.checkNotNullParameter(shape, "shape");
        Intrinsics.checkNotNullParameter(paint, "paint");
        return new ImageRenderer$paintRenderCommand$1(shape, paint);
    }

    public static /* synthetic */ Paint setBitmap$default(ImageRenderer imageRenderer, Paint paint, Bitmap bitmap, Matrix matrix, int i, Object obj) {
        if ((i & 2) != 0) {
            matrix = null;
        }
        Intrinsics.checkNotNullParameter(paint, "<this>");
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        paint.getShader().setLocalMatrix(matrix);
        return paint;
    }

    public final Paint setBitmap(Paint paint, Bitmap bitmap, Matrix matrix) {
        Intrinsics.checkNotNullParameter(paint, "<this>");
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        paint.getShader().setLocalMatrix(matrix);
        return paint;
    }
}

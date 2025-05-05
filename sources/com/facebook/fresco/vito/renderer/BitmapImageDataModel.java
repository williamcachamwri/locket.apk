package com.facebook.fresco.vito.renderer;

import android.graphics.Bitmap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\nXD¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\nX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\nX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f¨\u0006\u0012"}, d2 = {"Lcom/facebook/fresco/vito/renderer/BitmapImageDataModel;", "Lcom/facebook/fresco/vito/renderer/ImageDataModel;", "bitmap", "Landroid/graphics/Bitmap;", "isBitmapCircular", "", "(Landroid/graphics/Bitmap;Z)V", "getBitmap", "()Landroid/graphics/Bitmap;", "defaultPaintFlags", "", "getDefaultPaintFlags", "()I", "height", "getHeight", "()Z", "width", "getWidth", "renderer_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ImageDataModel.kt */
public final class BitmapImageDataModel extends ImageDataModel {
    private final Bitmap bitmap;
    private final int defaultPaintFlags;
    private final int height;
    private final boolean isBitmapCircular;
    private final int width;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ BitmapImageDataModel(Bitmap bitmap2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(bitmap2, (i & 2) != 0 ? false : z);
    }

    public final Bitmap getBitmap() {
        return this.bitmap;
    }

    public final boolean isBitmapCircular() {
        return this.isBitmapCircular;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BitmapImageDataModel(Bitmap bitmap2, boolean z) {
        super((DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(bitmap2, "bitmap");
        this.bitmap = bitmap2;
        this.isBitmapCircular = z;
        this.width = bitmap2.getWidth();
        this.height = bitmap2.getHeight();
        this.defaultPaintFlags = 6;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getDefaultPaintFlags() {
        return this.defaultPaintFlags;
    }
}

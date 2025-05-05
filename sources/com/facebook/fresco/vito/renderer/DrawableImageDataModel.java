package com.facebook.fresco.vito.renderer;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n¨\u0006\u0011"}, d2 = {"Lcom/facebook/fresco/vito/renderer/DrawableImageDataModel;", "Lcom/facebook/fresco/vito/renderer/ImageDataModel;", "drawable", "Landroid/graphics/drawable/Drawable;", "(Landroid/graphics/drawable/Drawable;)V", "getDrawable", "()Landroid/graphics/drawable/Drawable;", "height", "", "getHeight", "()I", "width", "getWidth", "setCallback", "", "callback", "Landroid/graphics/drawable/Drawable$Callback;", "renderer_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ImageDataModel.kt */
public class DrawableImageDataModel extends ImageDataModel {
    private final Drawable drawable;
    private final int height;
    private final int width;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DrawableImageDataModel(Drawable drawable2) {
        super((DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(drawable2, "drawable");
        this.drawable = drawable2;
        int i = -1;
        this.width = drawable2 instanceof NinePatchDrawable ? -1 : drawable2.getIntrinsicWidth();
        this.height = !(drawable2 instanceof NinePatchDrawable) ? drawable2.getIntrinsicHeight() : i;
    }

    public final Drawable getDrawable() {
        return this.drawable;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setCallback(Drawable.Callback callback) {
        this.drawable.setCallback(callback);
    }
}

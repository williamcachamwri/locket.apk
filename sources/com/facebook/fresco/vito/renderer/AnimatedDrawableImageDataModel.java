package com.facebook.fresco.vito.renderer;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\rH\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000b¨\u0006\u000f"}, d2 = {"Lcom/facebook/fresco/vito/renderer/AnimatedDrawableImageDataModel;", "Lcom/facebook/fresco/vito/renderer/DrawableImageDataModel;", "drawable", "Landroid/graphics/drawable/Drawable;", "animatable", "Landroid/graphics/drawable/Animatable;", "isAutoPlay", "", "(Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Animatable;Z)V", "getAnimatable", "()Landroid/graphics/drawable/Animatable;", "()Z", "onAttach", "", "onDetach", "renderer_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ImageDataModel.kt */
public final class AnimatedDrawableImageDataModel extends DrawableImageDataModel {
    private final Animatable animatable;
    private final boolean isAutoPlay;

    public final Animatable getAnimatable() {
        return this.animatable;
    }

    public final boolean isAutoPlay() {
        return this.isAutoPlay;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnimatedDrawableImageDataModel(Drawable drawable, Animatable animatable2, boolean z) {
        super(drawable);
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        Intrinsics.checkNotNullParameter(animatable2, "animatable");
        this.animatable = animatable2;
        this.isAutoPlay = z;
    }

    public void onAttach() {
        if (this.isAutoPlay) {
            this.animatable.start();
        }
    }

    public void onDetach() {
        if (this.isAutoPlay) {
            this.animatable.stop();
        }
    }
}

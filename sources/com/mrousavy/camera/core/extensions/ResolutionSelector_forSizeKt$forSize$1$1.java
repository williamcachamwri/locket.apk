package com.mrousavy.camera.core.extensions;

import android.util.Size;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/util/Size;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ResolutionSelector+forSize.kt */
final class ResolutionSelector_forSizeKt$forSize$1$1 extends Lambda implements Function1<Size, Comparable<?>> {
    final /* synthetic */ Size $size;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ResolutionSelector_forSizeKt$forSize$1$1(Size size) {
        super(1);
        this.$size = size;
    }

    public final Comparable<?> invoke(Size size) {
        Intrinsics.checkNotNull(size);
        return Float.valueOf(ResolutionSelector_forSizeKt.aspectRatioDifference(size, this.$size));
    }
}

package com.mrousavy.camera.core.extensions;

import android.util.Size;
import androidx.camera.core.resolutionselector.ResolutionSelector;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0002\u001a\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0002\u001a\u0012\u0010\u0007\u001a\u00020\b*\u00020\b2\u0006\u0010\t\u001a\u00020\u0003¨\u0006\n"}, d2 = {"aspectRatioDifference", "", "left", "Landroid/util/Size;", "right", "sizeDifference", "", "forSize", "Landroidx/camera/core/resolutionselector/ResolutionSelector$Builder;", "size", "react-native-vision-camera_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: ResolutionSelector+forSize.kt */
public final class ResolutionSelector_forSizeKt {
    /* access modifiers changed from: private */
    public static final int sizeDifference(Size size, Size size2) {
        return Math.abs((size.getWidth() * size.getHeight()) - (size2.getWidth() * size2.getHeight()));
    }

    /* access modifiers changed from: private */
    public static final float aspectRatioDifference(Size size, Size size2) {
        return Math.abs(Size_aspectRatioKt.getAspectRatio(size) - Size_aspectRatioKt.getAspectRatio(size2));
    }

    public static final ResolutionSelector.Builder forSize(ResolutionSelector.Builder builder, Size size) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(size, "size");
        ResolutionSelector.Builder resolutionFilter = builder.setResolutionFilter(new ResolutionSelector_forSizeKt$$ExternalSyntheticLambda0(size));
        Intrinsics.checkNotNullExpressionValue(resolutionFilter, "setResolutionFilter(...)");
        return resolutionFilter;
    }

    /* access modifiers changed from: private */
    public static final List forSize$lambda$0(Size size, List list, int i) {
        Intrinsics.checkNotNullParameter(size, "$size");
        Intrinsics.checkNotNullParameter(list, "supportedSizes");
        return CollectionsKt.sortedWith(list, ComparisonsKt.compareBy((Function1<? super T, ? extends Comparable<?>>[]) new Function1[]{new ResolutionSelector_forSizeKt$forSize$1$1(size), new ResolutionSelector_forSizeKt$forSize$1$2(size)}));
    }
}

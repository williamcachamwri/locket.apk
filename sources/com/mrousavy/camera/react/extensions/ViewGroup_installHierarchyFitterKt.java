package com.mrousavy.camera.react.extensions;

import android.view.ViewGroup;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"installHierarchyFitter", "", "Landroid/view/ViewGroup;", "react-native-vision-camera_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: ViewGroup+installHierarchyFitter.kt */
public final class ViewGroup_installHierarchyFitterKt {
    public static final void installHierarchyFitter(ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(viewGroup, "<this>");
        viewGroup.setOnHierarchyChangeListener(new ViewGroup_installHierarchyFitterKt$installHierarchyFitter$1(viewGroup));
    }
}

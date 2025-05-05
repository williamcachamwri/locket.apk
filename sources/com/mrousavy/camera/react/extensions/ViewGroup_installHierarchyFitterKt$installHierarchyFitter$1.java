package com.mrousavy.camera.react.extensions;

import android.view.View;
import android.view.ViewGroup;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0016J\u001c\u0010\u0007\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\b"}, d2 = {"com/mrousavy/camera/react/extensions/ViewGroup_installHierarchyFitterKt$installHierarchyFitter$1", "Landroid/view/ViewGroup$OnHierarchyChangeListener;", "onChildViewAdded", "", "parent", "Landroid/view/View;", "child", "onChildViewRemoved", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ViewGroup+installHierarchyFitter.kt */
public final class ViewGroup_installHierarchyFitterKt$installHierarchyFitter$1 implements ViewGroup.OnHierarchyChangeListener {
    final /* synthetic */ ViewGroup $this_installHierarchyFitter;

    public void onChildViewRemoved(View view, View view2) {
    }

    ViewGroup_installHierarchyFitterKt$installHierarchyFitter$1(ViewGroup viewGroup) {
        this.$this_installHierarchyFitter = viewGroup;
    }

    public void onChildViewAdded(View view, View view2) {
        if (view != null) {
            view.measure(View.MeasureSpec.makeMeasureSpec(this.$this_installHierarchyFitter.getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(this.$this_installHierarchyFitter.getMeasuredHeight(), 1073741824));
        }
        if (view != null) {
            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        }
    }
}

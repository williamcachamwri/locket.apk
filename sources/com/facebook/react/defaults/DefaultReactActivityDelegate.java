package com.facebook.react.defaults;

import android.os.Bundle;
import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactRootView;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B'\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tB\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\nJ\b\u0010\u000b\u001a\u00020\fH\u0014J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014J\b\u0010\u000f\u001a\u00020\u0007H\u0014R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/facebook/react/defaults/DefaultReactActivityDelegate;", "Lcom/facebook/react/ReactActivityDelegate;", "activity", "Lcom/facebook/react/ReactActivity;", "mainComponentName", "", "fabricEnabled", "", "concurrentReactEnabled", "(Lcom/facebook/react/ReactActivity;Ljava/lang/String;ZZ)V", "(Lcom/facebook/react/ReactActivity;Ljava/lang/String;Z)V", "createRootView", "Lcom/facebook/react/ReactRootView;", "bundle", "Landroid/os/Bundle;", "isFabricEnabled", "ReactAndroid_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: DefaultReactActivityDelegate.kt */
public class DefaultReactActivityDelegate extends ReactActivityDelegate {
    private final boolean fabricEnabled;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ DefaultReactActivityDelegate(ReactActivity reactActivity, String str, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(reactActivity, str, (i & 4) != 0 ? false : z);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DefaultReactActivityDelegate(ReactActivity reactActivity, String str, boolean z) {
        super(reactActivity, str);
        Intrinsics.checkNotNullParameter(reactActivity, "activity");
        Intrinsics.checkNotNullParameter(str, "mainComponentName");
        this.fabricEnabled = z;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated(level = DeprecationLevel.WARNING, message = "Creating DefaultReactActivityDelegate with both fabricEnabled and concurrentReactEnabled is deprecated. Please pass only one boolean value that will be used for both flags", replaceWith = @ReplaceWith(expression = "DefaultReactActivityDelegate(activity, mainComponentName, fabricEnabled)", imports = {}))
    public DefaultReactActivityDelegate(ReactActivity reactActivity, String str, boolean z, boolean z2) {
        this(reactActivity, str, z);
        Intrinsics.checkNotNullParameter(reactActivity, "activity");
        Intrinsics.checkNotNullParameter(str, "mainComponentName");
    }

    /* access modifiers changed from: protected */
    public boolean isFabricEnabled() {
        return this.fabricEnabled;
    }

    /* access modifiers changed from: protected */
    public ReactRootView createRootView() {
        ReactRootView reactRootView = new ReactRootView(getContext());
        reactRootView.setIsFabric(this.fabricEnabled);
        return reactRootView;
    }

    /* access modifiers changed from: protected */
    public ReactRootView createRootView(Bundle bundle) {
        ReactRootView reactRootView = new ReactRootView(getContext());
        reactRootView.setIsFabric(this.fabricEnabled);
        return reactRootView;
    }
}

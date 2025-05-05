package com.reactnativekeyboardcontroller.views;

import android.view.View;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.WindowInsetsCompat;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EdgeToEdgeReactViewGroup$$ExternalSyntheticLambda1 implements OnApplyWindowInsetsListener {
    public final /* synthetic */ EdgeToEdgeReactViewGroup f$0;

    public /* synthetic */ EdgeToEdgeReactViewGroup$$ExternalSyntheticLambda1(EdgeToEdgeReactViewGroup edgeToEdgeReactViewGroup) {
        this.f$0 = edgeToEdgeReactViewGroup;
    }

    public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        return EdgeToEdgeReactViewGroup.setupWindowInsets$lambda$0(this.f$0, view, windowInsetsCompat);
    }
}

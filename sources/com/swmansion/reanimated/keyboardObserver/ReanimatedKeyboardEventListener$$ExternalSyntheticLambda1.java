package com.swmansion.reanimated.keyboardObserver;

import android.view.View;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.WindowInsetsCompat;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReanimatedKeyboardEventListener$$ExternalSyntheticLambda1 implements OnApplyWindowInsetsListener {
    public final /* synthetic */ ReanimatedKeyboardEventListener f$0;
    public final /* synthetic */ View f$1;

    public /* synthetic */ ReanimatedKeyboardEventListener$$ExternalSyntheticLambda1(ReanimatedKeyboardEventListener reanimatedKeyboardEventListener, View view) {
        this.f$0 = reanimatedKeyboardEventListener;
        this.f$1 = view;
    }

    public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        return this.f$0.lambda$setupWindowInsets$0(this.f$1, view, windowInsetsCompat);
    }
}

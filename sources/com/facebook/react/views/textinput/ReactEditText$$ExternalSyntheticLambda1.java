package com.facebook.react.views.textinput;

import androidx.core.util.Predicate;
import com.facebook.react.views.text.ReactBackgroundColorSpan;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactEditText$$ExternalSyntheticLambda1 implements Predicate {
    public final /* synthetic */ ReactEditText f$0;

    public /* synthetic */ ReactEditText$$ExternalSyntheticLambda1(ReactEditText reactEditText) {
        this.f$0 = reactEditText;
    }

    public final boolean test(Object obj) {
        return this.f$0.lambda$stripStyleEquivalentSpans$1((ReactBackgroundColorSpan) obj);
    }
}

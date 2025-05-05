package com.reactnativekeyboardcontroller.listeners;

import com.reactnativekeyboardcontroller.events.FocusedInputTextChangedEvent;
import com.reactnativekeyboardcontroller.extensions.ThemedReactContextKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "text", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: FocusedInputObserver.kt */
final class FocusedInputObserver$textListener$1 extends Lambda implements Function1<String, Unit> {
    final /* synthetic */ FocusedInputObserver this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FocusedInputObserver$textListener$1(FocusedInputObserver focusedInputObserver) {
        super(1);
        this.this$0 = focusedInputObserver;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((String) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(String str) {
        Intrinsics.checkNotNullParameter(str, "text");
        this.this$0.syncUpLayout();
        ThemedReactContextKt.dispatchEvent(this.this$0.context, this.this$0.eventPropagationView.getId(), new FocusedInputTextChangedEvent(this.this$0.surfaceId, this.this$0.eventPropagationView.getId(), str));
    }
}

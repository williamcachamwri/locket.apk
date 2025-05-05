package com.reactnativekeyboardcontroller.listeners;

import android.widget.EditText;
import com.reactnativekeyboardcontroller.events.FocusedInputSelectionChangedEvent;
import com.reactnativekeyboardcontroller.events.FocusedInputSelectionChangedEventData;
import com.reactnativekeyboardcontroller.extensions.ThemedReactContextKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\n¢\u0006\u0002\b\n"}, d2 = {"<anonymous>", "", "start", "", "end", "startX", "", "startY", "endX", "endY", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: FocusedInputObserver.kt */
final class FocusedInputObserver$selectionListener$1 extends Lambda implements Function6<Integer, Integer, Double, Double, Double, Double, Unit> {
    final /* synthetic */ FocusedInputObserver this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FocusedInputObserver$selectionListener$1(FocusedInputObserver focusedInputObserver) {
        super(6);
        this.this$0 = focusedInputObserver;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        invoke(((Number) obj).intValue(), ((Number) obj2).intValue(), ((Number) obj3).doubleValue(), ((Number) obj4).doubleValue(), ((Number) obj5).doubleValue(), ((Number) obj6).doubleValue());
        return Unit.INSTANCE;
    }

    public final void invoke(int i, int i2, double d, double d2, double d3, double d4) {
        EditText access$getLastFocusedInput$p = this.this$0.lastFocusedInput;
        if (access$getLastFocusedInput$p != null) {
            this.this$0.syncUpLayout();
            ThemedReactContextKt.dispatchEvent(this.this$0.context, this.this$0.eventPropagationView.getId(), new FocusedInputSelectionChangedEvent(this.this$0.surfaceId, this.this$0.eventPropagationView.getId(), new FocusedInputSelectionChangedEventData(access$getLastFocusedInput$p.getId(), d, d2, d3, d4, i, i2)));
        }
    }
}

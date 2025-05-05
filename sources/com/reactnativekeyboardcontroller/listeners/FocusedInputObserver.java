package com.reactnativekeyboardcontroller.listeners;

import android.text.TextWatcher;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.views.view.ReactViewGroup;
import com.reactnativekeyboardcontroller.events.FocusedInputLayoutChangedEvent;
import com.reactnativekeyboardcontroller.events.FocusedInputLayoutChangedEventData;
import com.reactnativekeyboardcontroller.extensions.EditTextKt;
import com.reactnativekeyboardcontroller.extensions.FloatKt;
import com.reactnativekeyboardcontroller.extensions.ReactContextKt;
import com.reactnativekeyboardcontroller.extensions.ThemedReactContextKt;
import com.reactnativekeyboardcontroller.extensions.ViewKt;
import com.reactnativekeyboardcontroller.traversal.FocusedInputHolder;
import com.reactnativekeyboardcontroller.traversal.ViewHierarchyNavigator;
import io.sentry.protocol.SentryThread;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.Intrinsics;
import me.leolin.shortcutbadger.impl.NewHtcHomeBadger;

@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010(\u001a\u00020\u001dJ\u0010\u0010)\u001a\u00020\u001d2\u0006\u0010*\u001a\u00020\fH\u0002J\u0006\u0010+\u001a\u00020\u001dR\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u0001\u0010\u0011\u001a\u0001\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0017\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u001a\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u001d0\u0012X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010!\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u001d0\"X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'¨\u0006,"}, d2 = {"Lcom/reactnativekeyboardcontroller/listeners/FocusedInputObserver;", "", "view", "Landroid/view/View;", "eventPropagationView", "Lcom/facebook/react/views/view/ReactViewGroup;", "context", "Lcom/facebook/react/uimanager/ThemedReactContext;", "(Landroid/view/View;Lcom/facebook/react/views/view/ReactViewGroup;Lcom/facebook/react/uimanager/ThemedReactContext;)V", "focusListener", "Landroid/view/ViewTreeObserver$OnGlobalFocusChangeListener;", "lastEventDispatched", "Lcom/reactnativekeyboardcontroller/events/FocusedInputLayoutChangedEventData;", "lastFocusedInput", "Landroid/widget/EditText;", "layoutListener", "Landroid/view/View$OnLayoutChangeListener;", "selectionListener", "Lkotlin/Function6;", "", "Lkotlin/ParameterName;", "name", "start", "end", "", "startX", "startY", "endX", "endY", "", "selectionSubscription", "Lkotlin/Function0;", "surfaceId", "textListener", "Lkotlin/Function1;", "", "textWatcher", "Landroid/text/TextWatcher;", "getView", "()Landroid/view/View;", "destroy", "dispatchEventToJS", "event", "syncUpLayout", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FocusedInputObserver.kt */
public final class FocusedInputObserver {
    /* access modifiers changed from: private */
    public final ThemedReactContext context;
    /* access modifiers changed from: private */
    public final ReactViewGroup eventPropagationView;
    private final ViewTreeObserver.OnGlobalFocusChangeListener focusListener;
    private FocusedInputLayoutChangedEventData lastEventDispatched = FocusedInputObserverKt.getNoFocusedInputEvent();
    /* access modifiers changed from: private */
    public EditText lastFocusedInput;
    private final View.OnLayoutChangeListener layoutListener = new FocusedInputObserver$$ExternalSyntheticLambda1(this);
    private final Function6<Integer, Integer, Double, Double, Double, Double, Unit> selectionListener = new FocusedInputObserver$selectionListener$1(this);
    private Function0<Unit> selectionSubscription;
    /* access modifiers changed from: private */
    public final int surfaceId;
    private final Function1<String, Unit> textListener = new FocusedInputObserver$textListener$1(this);
    private TextWatcher textWatcher;
    private final View view;

    public FocusedInputObserver(View view2, ReactViewGroup reactViewGroup, ThemedReactContext themedReactContext) {
        Intrinsics.checkNotNullParameter(view2, "view");
        Intrinsics.checkNotNullParameter(reactViewGroup, "eventPropagationView");
        this.view = view2;
        this.eventPropagationView = reactViewGroup;
        this.context = themedReactContext;
        this.surfaceId = UIManagerHelper.getSurfaceId(view2);
        FocusedInputObserver$$ExternalSyntheticLambda2 focusedInputObserver$$ExternalSyntheticLambda2 = new FocusedInputObserver$$ExternalSyntheticLambda2(this);
        this.focusListener = focusedInputObserver$$ExternalSyntheticLambda2;
        view2.getViewTreeObserver().addOnGlobalFocusChangeListener(focusedInputObserver$$ExternalSyntheticLambda2);
    }

    public final View getView() {
        return this.view;
    }

    /* access modifiers changed from: private */
    public static final void layoutListener$lambda$0(FocusedInputObserver focusedInputObserver, View view2, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        Intrinsics.checkNotNullParameter(focusedInputObserver, "this$0");
        focusedInputObserver.syncUpLayout();
    }

    /* access modifiers changed from: private */
    public static final void focusListener$lambda$4(FocusedInputObserver focusedInputObserver, View view2, View view3) {
        Intrinsics.checkNotNullParameter(focusedInputObserver, "this$0");
        View view4 = null;
        if (view3 == null || view2 != null) {
            EditText editText = focusedInputObserver.lastFocusedInput;
            if (editText != null) {
                editText.removeOnLayoutChangeListener(focusedInputObserver.layoutListener);
            }
            EditText editText2 = focusedInputObserver.lastFocusedInput;
            if (editText2 != null) {
                editText2.post(new FocusedInputObserver$$ExternalSyntheticLambda0(editText2, focusedInputObserver.textWatcher));
            }
            Function0<Unit> function0 = focusedInputObserver.selectionSubscription;
            if (function0 != null) {
                function0.invoke();
            }
            focusedInputObserver.lastFocusedInput = null;
        }
        if (view3 instanceof EditText) {
            EditText editText3 = (EditText) view3;
            focusedInputObserver.lastFocusedInput = editText3;
            view3.addOnLayoutChangeListener(focusedInputObserver.layoutListener);
            focusedInputObserver.syncUpLayout();
            focusedInputObserver.textWatcher = EditTextKt.addOnTextChangedListener(editText3, focusedInputObserver.textListener);
            focusedInputObserver.selectionSubscription = EditTextKt.addOnSelectionChangedListener(editText3, focusedInputObserver.selectionListener);
            FocusedInputHolder.INSTANCE.set(editText3);
            ViewHierarchyNavigator viewHierarchyNavigator = ViewHierarchyNavigator.INSTANCE;
            ThemedReactContext themedReactContext = focusedInputObserver.context;
            if (themedReactContext != null) {
                view4 = ReactContextKt.getRootView(themedReactContext);
            }
            List<EditText> allInputFields = viewHierarchyNavigator.getAllInputFields(view4);
            int indexOf = allInputFields.indexOf(view3);
            ThemedReactContext themedReactContext2 = focusedInputObserver.context;
            WritableMap createMap = Arguments.createMap();
            createMap.putInt(SentryThread.JsonKeys.CURRENT, indexOf);
            createMap.putInt(NewHtcHomeBadger.COUNT, allInputFields.size());
            Unit unit = Unit.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(createMap, "apply(...)");
            ThemedReactContextKt.emitEvent(themedReactContext2, "KeyboardController::focusDidSet", createMap);
        }
        if (view3 == null) {
            focusedInputObserver.dispatchEventToJS(FocusedInputObserverKt.getNoFocusedInputEvent());
        }
    }

    /* access modifiers changed from: private */
    public static final void focusListener$lambda$4$lambda$2$lambda$1(EditText editText, TextWatcher textWatcher2) {
        Intrinsics.checkNotNullParameter(editText, "$input");
        editText.removeTextChangedListener(textWatcher2);
    }

    public final void syncUpLayout() {
        EditText editText = this.lastFocusedInput;
        if (editText != null) {
            int[] screenLocation = ViewKt.getScreenLocation(editText);
            int i = screenLocation[0];
            int i2 = screenLocation[1];
            double dp = FloatKt.getDp(editText.getX());
            double dp2 = FloatKt.getDp(editText.getY());
            double dp3 = FloatKt.getDp((float) editText.getWidth());
            double dp4 = FloatKt.getDp((float) editText.getHeight());
            double dp5 = FloatKt.getDp((float) i);
            double dp6 = FloatKt.getDp((float) i2);
            int id = editText.getId();
            int parentScrollViewTarget = EditTextKt.getParentScrollViewTarget(editText);
            FocusedInputLayoutChangedEventData focusedInputLayoutChangedEventData = r4;
            FocusedInputLayoutChangedEventData focusedInputLayoutChangedEventData2 = new FocusedInputLayoutChangedEventData(dp, dp2, dp3, dp4, dp5, dp6, id, parentScrollViewTarget);
            dispatchEventToJS(focusedInputLayoutChangedEventData);
        }
    }

    public final void destroy() {
        this.view.getViewTreeObserver().removeOnGlobalFocusChangeListener(this.focusListener);
    }

    private final void dispatchEventToJS(FocusedInputLayoutChangedEventData focusedInputLayoutChangedEventData) {
        if (!Intrinsics.areEqual((Object) focusedInputLayoutChangedEventData, (Object) this.lastEventDispatched)) {
            this.lastEventDispatched = focusedInputLayoutChangedEventData;
            ThemedReactContextKt.dispatchEvent(this.context, this.eventPropagationView.getId(), new FocusedInputLayoutChangedEvent(this.surfaceId, this.eventPropagationView.getId(), focusedInputLayoutChangedEventData));
        }
    }
}

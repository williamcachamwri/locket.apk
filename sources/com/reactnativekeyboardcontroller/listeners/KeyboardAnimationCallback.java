package com.reactnativekeyboardcontroller.listeners;

import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsAnimationCompat;
import androidx.core.view.WindowInsetsCompat;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.views.view.ReactViewGroup;
import com.reactnativekeyboardcontroller.events.KeyboardTransitionEvent;
import com.reactnativekeyboardcontroller.extensions.EditTextKt;
import com.reactnativekeyboardcontroller.extensions.FloatKt;
import com.reactnativekeyboardcontroller.extensions.ThemedReactContextKt;
import com.reactnativekeyboardcontroller.extensions.WindowInsetsAnimationCompatKt;
import com.reactnativekeyboardcontroller.interactive.InteractiveKeyboardProvider;
import com.reactnativekeyboardcontroller.listeners.Suspendable;
import com.reactnativekeyboardcontroller.log.Logger;
import com.reactnativekeyboardcontroller.traversal.FocusedInputHolder;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B'\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0006\u0010*\u001a\u00020+J\b\u0010,\u001a\u00020$H\u0002J\u0010\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020$H\u0002J\b\u0010\u001c\u001a\u00020\u001aH\u0002J\u0018\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\u00072\u0006\u00103\u001a\u000201H\u0016J\u0010\u00104\u001a\u00020+2\u0006\u00105\u001a\u00020\u000fH\u0016J\u0010\u00106\u001a\u00020+2\u0006\u00107\u001a\u00020$H\u0002J\u001e\u00108\u001a\u0002012\u0006\u00103\u001a\u0002012\f\u00109\u001a\b\u0012\u0004\u0012\u00020\u000f0:H\u0016J\u0018\u0010;\u001a\u00020<2\u0006\u00105\u001a\u00020\u000f2\u0006\u0010=\u001a\u00020<H\u0016J#\u0010>\u001a\u00020+2\n\b\u0002\u0010/\u001a\u0004\u0018\u00010$2\n\b\u0002\u0010?\u001a\u0004\u0018\u00010\u001a¢\u0006\u0002\u0010@R\u001e\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u0010X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\u00020\u001a8BX\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\u001aX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001d\u001a\u00020\u001aX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001b\"\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020\u001aX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020$X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0014X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u000e\u0010)\u001a\u00020\u0014X\u000e¢\u0006\u0002\n\u0000¨\u0006A"}, d2 = {"Lcom/reactnativekeyboardcontroller/listeners/KeyboardAnimationCallback;", "Landroidx/core/view/WindowInsetsAnimationCompat$Callback;", "Landroidx/core/view/OnApplyWindowInsetsListener;", "Lcom/reactnativekeyboardcontroller/listeners/Suspendable;", "eventPropagationView", "Lcom/facebook/react/views/view/ReactViewGroup;", "view", "Landroid/view/View;", "context", "Lcom/facebook/react/uimanager/ThemedReactContext;", "config", "Lcom/reactnativekeyboardcontroller/listeners/KeyboardAnimationCallbackConfig;", "(Lcom/facebook/react/views/view/ReactViewGroup;Landroid/view/View;Lcom/facebook/react/uimanager/ThemedReactContext;Lcom/reactnativekeyboardcontroller/listeners/KeyboardAnimationCallbackConfig;)V", "animationsToSkip", "Ljava/util/HashSet;", "Landroidx/core/view/WindowInsetsAnimationCompat;", "Lkotlin/collections/HashSet;", "getContext", "()Lcom/facebook/react/uimanager/ThemedReactContext;", "duration", "", "getEventPropagationView", "()Lcom/facebook/react/views/view/ReactViewGroup;", "focusListener", "Landroid/view/ViewTreeObserver$OnGlobalFocusChangeListener;", "isKeyboardInteractive", "", "()Z", "isKeyboardVisible", "isSuspended", "setSuspended", "(Z)V", "isTransitioning", "layoutObserver", "Lcom/reactnativekeyboardcontroller/listeners/FocusedInputObserver;", "persistentKeyboardHeight", "", "prevKeyboardHeight", "surfaceId", "getView", "()Landroid/view/View;", "viewTagFocused", "destroy", "", "getCurrentKeyboardHeight", "getEventParams", "Lcom/facebook/react/bridge/WritableMap;", "height", "onApplyWindowInsets", "Landroidx/core/view/WindowInsetsCompat;", "v", "insets", "onEnd", "animation", "onKeyboardResized", "keyboardHeight", "onProgress", "runningAnimations", "", "onStart", "Landroidx/core/view/WindowInsetsAnimationCompat$BoundsCompat;", "bounds", "syncKeyboardPosition", "isVisible", "(Ljava/lang/Double;Ljava/lang/Boolean;)V", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: KeyboardAnimationCallback.kt */
public final class KeyboardAnimationCallback extends WindowInsetsAnimationCompat.Callback implements OnApplyWindowInsetsListener, Suspendable {
    private HashSet<WindowInsetsAnimationCompat> animationsToSkip = new HashSet<>();
    private final KeyboardAnimationCallbackConfig config;
    private final ThemedReactContext context;
    private int duration;
    private final ReactViewGroup eventPropagationView;
    private final ViewTreeObserver.OnGlobalFocusChangeListener focusListener;
    private boolean isKeyboardVisible;
    private boolean isSuspended;
    private boolean isTransitioning;
    private FocusedInputObserver layoutObserver;
    private double persistentKeyboardHeight;
    private double prevKeyboardHeight;
    private final int surfaceId;
    private final View view;
    private int viewTagFocused = -1;

    public void suspend(boolean z) {
        Suspendable.DefaultImpls.suspend(this, z);
    }

    public final ReactViewGroup getEventPropagationView() {
        return this.eventPropagationView;
    }

    public final View getView() {
        return this.view;
    }

    public final ThemedReactContext getContext() {
        return this.context;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public KeyboardAnimationCallback(ReactViewGroup reactViewGroup, View view2, ThemedReactContext themedReactContext, KeyboardAnimationCallbackConfig keyboardAnimationCallbackConfig) {
        super(keyboardAnimationCallbackConfig.getDispatchMode());
        Intrinsics.checkNotNullParameter(reactViewGroup, "eventPropagationView");
        Intrinsics.checkNotNullParameter(view2, "view");
        Intrinsics.checkNotNullParameter(keyboardAnimationCallbackConfig, "config");
        this.eventPropagationView = reactViewGroup;
        this.view = view2;
        this.context = themedReactContext;
        this.config = keyboardAnimationCallbackConfig;
        this.surfaceId = UIManagerHelper.getSurfaceId((View) reactViewGroup);
        KeyboardAnimationCallback$$ExternalSyntheticLambda0 keyboardAnimationCallback$$ExternalSyntheticLambda0 = new KeyboardAnimationCallback$$ExternalSyntheticLambda0(this);
        this.focusListener = keyboardAnimationCallback$$ExternalSyntheticLambda0;
        if ((keyboardAnimationCallbackConfig.getDeferredInsetTypes() & keyboardAnimationCallbackConfig.getPersistentInsetTypes()) == 0) {
            this.layoutObserver = new FocusedInputObserver(view2, reactViewGroup, themedReactContext);
            view2.getViewTreeObserver().addOnGlobalFocusChangeListener(keyboardAnimationCallback$$ExternalSyntheticLambda0);
            return;
        }
        throw new IllegalArgumentException("persistentInsetTypes and deferredInsetTypes can not contain any of  same WindowInsetsCompat.Type values".toString());
    }

    private final boolean isKeyboardInteractive() {
        return this.duration == -1;
    }

    public boolean isSuspended() {
        return this.isSuspended;
    }

    public void setSuspended(boolean z) {
        this.isSuspended = z;
    }

    /* access modifiers changed from: private */
    public static final void focusListener$lambda$0(KeyboardAnimationCallback keyboardAnimationCallback, View view2, View view3) {
        Intrinsics.checkNotNullParameter(keyboardAnimationCallback, "this$0");
        if (view3 instanceof EditText) {
            keyboardAnimationCallback.viewTagFocused = ((EditText) view3).getId();
            if (keyboardAnimationCallback.isKeyboardVisible && view2 != null) {
                ThemedReactContextKt.dispatchEvent(keyboardAnimationCallback.context, keyboardAnimationCallback.eventPropagationView.getId(), new KeyboardTransitionEvent(keyboardAnimationCallback.surfaceId, keyboardAnimationCallback.eventPropagationView.getId(), KeyboardTransitionEvent.Companion.getStart(), keyboardAnimationCallback.persistentKeyboardHeight, 1.0d, 0, keyboardAnimationCallback.viewTagFocused));
                ThemedReactContextKt.dispatchEvent(keyboardAnimationCallback.context, keyboardAnimationCallback.eventPropagationView.getId(), new KeyboardTransitionEvent(keyboardAnimationCallback.surfaceId, keyboardAnimationCallback.eventPropagationView.getId(), KeyboardTransitionEvent.Companion.getEnd(), keyboardAnimationCallback.persistentKeyboardHeight, 1.0d, 0, keyboardAnimationCallback.viewTagFocused));
                ThemedReactContextKt.emitEvent(keyboardAnimationCallback.context, "KeyboardController::keyboardWillShow", keyboardAnimationCallback.getEventParams(keyboardAnimationCallback.persistentKeyboardHeight));
                ThemedReactContextKt.emitEvent(keyboardAnimationCallback.context, "KeyboardController::keyboardDidShow", keyboardAnimationCallback.getEventParams(keyboardAnimationCallback.persistentKeyboardHeight));
            }
        }
    }

    public WindowInsetsCompat onApplyWindowInsets(View view2, WindowInsetsCompat windowInsetsCompat) {
        Intrinsics.checkNotNullParameter(view2, "v");
        Intrinsics.checkNotNullParameter(windowInsetsCompat, "insets");
        double currentKeyboardHeight = getCurrentKeyboardHeight();
        boolean z = true;
        boolean z2 = (this.isKeyboardVisible && isKeyboardVisible()) && !(this.isTransitioning || InteractiveKeyboardProvider.INSTANCE.isInteractive());
        if (this.persistentKeyboardHeight != currentKeyboardHeight) {
            z = false;
        }
        if (z2 && !z && !KeyboardAnimationCallbackKt.isResizeHandledInCallbackMethods) {
            Logger.i$default(Logger.INSTANCE, KeyboardAnimationCallbackKt.TAG, "onApplyWindowInsets: " + this.persistentKeyboardHeight + " -> " + currentKeyboardHeight, (Throwable) null, 4, (Object) null);
            FocusedInputObserver focusedInputObserver = this.layoutObserver;
            if (focusedInputObserver != null) {
                focusedInputObserver.syncUpLayout();
            }
            onKeyboardResized(currentKeyboardHeight);
        }
        return windowInsetsCompat;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0066, code lost:
        if ((r0.prevKeyboardHeight == 0.0d) == false) goto L_0x006a;
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00da  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.core.view.WindowInsetsAnimationCompat.BoundsCompat onStart(androidx.core.view.WindowInsetsAnimationCompat r18, androidx.core.view.WindowInsetsAnimationCompat.BoundsCompat r19) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            java.lang.String r3 = "animation"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r3)
            java.lang.String r3 = "bounds"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r3)
            boolean r3 = com.reactnativekeyboardcontroller.extensions.WindowInsetsAnimationCompatKt.isKeyboardAnimation(r18)
            if (r3 == 0) goto L_0x00f7
            boolean r3 = r17.isSuspended()
            if (r3 == 0) goto L_0x001e
            goto L_0x00f7
        L_0x001e:
            r3 = 1
            r0.isTransitioning = r3
            boolean r4 = r17.isKeyboardVisible()
            r0.isKeyboardVisible = r4
            long r4 = r18.getDurationMillis()
            int r4 = (int) r4
            r0.duration = r4
            double r9 = r17.getCurrentKeyboardHeight()
            boolean r4 = r0.isKeyboardVisible
            if (r4 == 0) goto L_0x0038
            r0.persistentKeyboardHeight = r9
        L_0x0038:
            com.reactnativekeyboardcontroller.listeners.FocusedInputObserver r4 = r0.layoutObserver
            if (r4 == 0) goto L_0x003f
            r4.syncUpLayout()
        L_0x003f:
            r4 = 0
            int r6 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            r7 = 0
            if (r6 != 0) goto L_0x0048
            r6 = r3
            goto L_0x0049
        L_0x0048:
            r6 = r7
        L_0x0049:
            if (r6 != 0) goto L_0x0058
            double r11 = r0.prevKeyboardHeight
            int r6 = (r11 > r9 ? 1 : (r11 == r9 ? 0 : -1))
            if (r6 != 0) goto L_0x0053
            r6 = r3
            goto L_0x0054
        L_0x0053:
            r6 = r7
        L_0x0054:
            if (r6 != 0) goto L_0x0058
            r6 = r3
            goto L_0x0059
        L_0x0058:
            r6 = r7
        L_0x0059:
            boolean r8 = r0.isKeyboardVisible
            if (r8 == 0) goto L_0x0069
            double r11 = r0.prevKeyboardHeight
            int r8 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
            if (r8 != 0) goto L_0x0065
            r8 = r3
            goto L_0x0066
        L_0x0065:
            r8 = r7
        L_0x0066:
            if (r8 != 0) goto L_0x0069
            goto L_0x006a
        L_0x0069:
            r3 = r7
        L_0x006a:
            if (r6 == 0) goto L_0x007d
            if (r3 == 0) goto L_0x007d
            boolean r3 = com.reactnativekeyboardcontroller.listeners.KeyboardAnimationCallbackKt.isResizeHandledInCallbackMethods
            if (r3 == 0) goto L_0x007d
            r0.onKeyboardResized(r9)
            java.util.HashSet<androidx.core.view.WindowInsetsAnimationCompat> r3 = r0.animationsToSkip
            r3.add(r1)
            return r2
        L_0x007d:
            com.facebook.react.uimanager.ThemedReactContext r3 = r0.context
            boolean r6 = r0.isKeyboardVisible
            if (r6 != 0) goto L_0x0086
            java.lang.String r6 = "keyboardWillHide"
            goto L_0x0088
        L_0x0086:
            java.lang.String r6 = "keyboardWillShow"
        L_0x0088:
            java.lang.String r7 = "KeyboardController::"
            java.lang.String r6 = r7.concat(r6)
            com.facebook.react.bridge.WritableMap r7 = r0.getEventParams(r9)
            com.reactnativekeyboardcontroller.extensions.ThemedReactContextKt.emitEvent(r3, r6, r7)
            com.reactnativekeyboardcontroller.log.Logger r11 = com.reactnativekeyboardcontroller.log.Logger.INSTANCE
            java.lang.String r12 = com.reactnativekeyboardcontroller.listeners.KeyboardAnimationCallbackKt.TAG
            int r3 = r0.viewTagFocused
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "HEIGHT:: "
            r6.<init>(r7)
            java.lang.StringBuilder r6 = r6.append(r9)
            java.lang.String r7 = " TAG:: "
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.StringBuilder r3 = r6.append(r3)
            java.lang.String r13 = r3.toString()
            r14 = 0
            r15 = 4
            r16 = 0
            com.reactnativekeyboardcontroller.log.Logger.i$default(r11, r12, r13, r14, r15, r16)
            com.facebook.react.uimanager.ThemedReactContext r3 = r0.context
            com.facebook.react.views.view.ReactViewGroup r6 = r0.eventPropagationView
            int r15 = r6.getId()
            com.reactnativekeyboardcontroller.events.KeyboardTransitionEvent r16 = new com.reactnativekeyboardcontroller.events.KeyboardTransitionEvent
            int r6 = r0.surfaceId
            com.facebook.react.views.view.ReactViewGroup r7 = r0.eventPropagationView
            int r7 = r7.getId()
            com.reactnativekeyboardcontroller.events.KeyboardTransitionEvent$Companion r8 = com.reactnativekeyboardcontroller.events.KeyboardTransitionEvent.Companion
            com.reactnativekeyboardcontroller.events.KeyboardTransitionEvent$Companion$EventName r8 = r8.getStart()
            boolean r11 = r0.isKeyboardVisible
            if (r11 != 0) goto L_0x00da
            goto L_0x00dc
        L_0x00da:
            r4 = 4607182418800017408(0x3ff0000000000000, double:1.0)
        L_0x00dc:
            r11 = r4
            int r13 = r0.duration
            int r14 = r0.viewTagFocused
            r5 = r16
            r5.<init>(r6, r7, r8, r9, r11, r13, r14)
            r4 = r16
            com.facebook.react.uimanager.events.Event r4 = (com.facebook.react.uimanager.events.Event) r4
            com.reactnativekeyboardcontroller.extensions.ThemedReactContextKt.dispatchEvent(r3, r15, r4)
            androidx.core.view.WindowInsetsAnimationCompat$BoundsCompat r1 = super.onStart(r18, r19)
            java.lang.String r2 = "onStart(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            return r1
        L_0x00f7:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reactnativekeyboardcontroller.listeners.KeyboardAnimationCallback.onStart(androidx.core.view.WindowInsetsAnimationCompat, androidx.core.view.WindowInsetsAnimationCompat$BoundsCompat):androidx.core.view.WindowInsetsAnimationCompat$BoundsCompat");
    }

    public WindowInsetsCompat onProgress(WindowInsetsCompat windowInsetsCompat, List<WindowInsetsAnimationCompat> list) {
        boolean z;
        Object obj;
        KeyboardTransitionEvent.Companion.EventName eventName;
        boolean z2;
        WindowInsetsCompat windowInsetsCompat2 = windowInsetsCompat;
        List<WindowInsetsAnimationCompat> list2 = list;
        Intrinsics.checkNotNullParameter(windowInsetsCompat2, "insets");
        Intrinsics.checkNotNullParameter(list2, "runningAnimations");
        Iterator it = list2.iterator();
        while (true) {
            z = true;
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            WindowInsetsAnimationCompat windowInsetsAnimationCompat = (WindowInsetsAnimationCompat) obj;
            if (!WindowInsetsAnimationCompatKt.isKeyboardAnimation(windowInsetsAnimationCompat) || this.animationsToSkip.contains(windowInsetsAnimationCompat)) {
                z2 = false;
                continue;
            } else {
                z2 = true;
                continue;
            }
            if (z2) {
                break;
            }
        }
        if (obj != null) {
            z = false;
        }
        if (!isSuspended() && !z) {
            Insets insets = windowInsetsCompat2.getInsets(this.config.getDeferredInsetTypes());
            Intrinsics.checkNotNullExpressionValue(insets, "getInsets(...)");
            Insets insets2 = windowInsetsCompat2.getInsets(this.config.getPersistentInsetTypes());
            Intrinsics.checkNotNullExpressionValue(insets2, "getInsets(...)");
            if (this.config.getHasTranslucentNavigationBar()) {
                insets2 = Insets.NONE;
                Intrinsics.checkNotNullExpressionValue(insets2, "NONE");
            }
            Insets max = Insets.max(Insets.subtract(insets, insets2), Insets.NONE);
            Intrinsics.checkNotNullExpressionValue(max, "let(...)");
            float f = (float) (max.bottom - max.top);
            double dp = FloatKt.getDp(f);
            double d = 0.0d;
            try {
                double abs = Math.abs(dp / this.persistentKeyboardHeight);
                if (!Double.isNaN(abs) && !Double.isInfinite(abs)) {
                    d = abs;
                }
            } catch (ArithmeticException e) {
                Logger.w$default(Logger.INSTANCE, KeyboardAnimationCallbackKt.TAG, "Caught arithmetic exception during `progress` calculation: " + e, (Throwable) null, 4, (Object) null);
            }
            double d2 = d;
            Logger.i$default(Logger.INSTANCE, KeyboardAnimationCallbackKt.TAG, "DiffY: " + f + " " + dp + " " + d2 + " " + InteractiveKeyboardProvider.INSTANCE.isInteractive() + " " + this.viewTagFocused, (Throwable) null, 4, (Object) null);
            if (InteractiveKeyboardProvider.INSTANCE.isInteractive()) {
                eventName = KeyboardTransitionEvent.Companion.getInteractive();
            } else {
                eventName = KeyboardTransitionEvent.Companion.getMove();
            }
            ThemedReactContextKt.dispatchEvent(this.context, this.eventPropagationView.getId(), new KeyboardTransitionEvent(this.surfaceId, this.eventPropagationView.getId(), eventName, dp, d2, this.duration, this.viewTagFocused));
        }
        return windowInsetsCompat2;
    }

    public void onEnd(WindowInsetsAnimationCompat windowInsetsAnimationCompat) {
        Intrinsics.checkNotNullParameter(windowInsetsAnimationCompat, "animation");
        super.onEnd(windowInsetsAnimationCompat);
        if (WindowInsetsAnimationCompatKt.isKeyboardAnimation(windowInsetsAnimationCompat) && !isSuspended()) {
            this.isTransitioning = false;
            this.duration = (int) windowInsetsAnimationCompat.getDurationMillis();
            KeyboardAnimationCallback$$ExternalSyntheticLambda1 keyboardAnimationCallback$$ExternalSyntheticLambda1 = new KeyboardAnimationCallback$$ExternalSyntheticLambda1(this, windowInsetsAnimationCompat);
            if (isKeyboardInteractive()) {
                this.view.post(keyboardAnimationCallback$$ExternalSyntheticLambda1);
            } else {
                keyboardAnimationCallback$$ExternalSyntheticLambda1.run();
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void onEnd$lambda$5(KeyboardAnimationCallback keyboardAnimationCallback, WindowInsetsAnimationCompat windowInsetsAnimationCompat) {
        Intrinsics.checkNotNullParameter(keyboardAnimationCallback, "this$0");
        Intrinsics.checkNotNullParameter(windowInsetsAnimationCompat, "$animation");
        double currentKeyboardHeight = keyboardAnimationCallback.getCurrentKeyboardHeight();
        keyboardAnimationCallback.isKeyboardVisible = keyboardAnimationCallback.isKeyboardVisible();
        keyboardAnimationCallback.prevKeyboardHeight = currentKeyboardHeight;
        if (keyboardAnimationCallback.animationsToSkip.contains(windowInsetsAnimationCompat)) {
            keyboardAnimationCallback.duration = 0;
            keyboardAnimationCallback.animationsToSkip.remove(windowInsetsAnimationCompat);
            return;
        }
        ThemedReactContextKt.emitEvent(keyboardAnimationCallback.context, "KeyboardController::".concat(!keyboardAnimationCallback.isKeyboardVisible ? "keyboardDidHide" : "keyboardDidShow"), keyboardAnimationCallback.getEventParams(currentKeyboardHeight));
        ThemedReactContextKt.dispatchEvent(keyboardAnimationCallback.context, keyboardAnimationCallback.eventPropagationView.getId(), new KeyboardTransitionEvent(keyboardAnimationCallback.surfaceId, keyboardAnimationCallback.eventPropagationView.getId(), KeyboardTransitionEvent.Companion.getEnd(), currentKeyboardHeight, !keyboardAnimationCallback.isKeyboardVisible ? 0.0d : 1.0d, keyboardAnimationCallback.duration, keyboardAnimationCallback.viewTagFocused));
        keyboardAnimationCallback.duration = 0;
    }

    public static /* synthetic */ void syncKeyboardPosition$default(KeyboardAnimationCallback keyboardAnimationCallback, Double d, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            d = null;
        }
        if ((i & 2) != 0) {
            bool = null;
        }
        keyboardAnimationCallback.syncKeyboardPosition(d, bool);
    }

    public final void syncKeyboardPosition(Double d, Boolean bool) {
        double doubleValue = d != null ? d.doubleValue() : getCurrentKeyboardHeight();
        boolean booleanValue = bool != null ? bool.booleanValue() : isKeyboardVisible();
        this.isKeyboardVisible = booleanValue;
        this.prevKeyboardHeight = doubleValue;
        this.isTransitioning = false;
        this.duration = 0;
        ThemedReactContextKt.emitEvent(this.context, "KeyboardController::".concat(!booleanValue ? "keyboardDidHide" : "keyboardDidShow"), getEventParams(doubleValue));
        for (KeyboardTransitionEvent.Companion.EventName keyboardTransitionEvent : CollectionsKt.listOf(KeyboardTransitionEvent.Companion.getMove(), KeyboardTransitionEvent.Companion.getEnd())) {
            ThemedReactContextKt.dispatchEvent(this.context, this.eventPropagationView.getId(), new KeyboardTransitionEvent(this.surfaceId, this.eventPropagationView.getId(), keyboardTransitionEvent, doubleValue, !this.isKeyboardVisible ? 0.0d : 1.0d, this.duration, this.viewTagFocused));
        }
    }

    public final void destroy() {
        this.view.getViewTreeObserver().removeOnGlobalFocusChangeListener(this.focusListener);
        FocusedInputObserver focusedInputObserver = this.layoutObserver;
        if (focusedInputObserver != null) {
            focusedInputObserver.destroy();
        }
    }

    private final void onKeyboardResized(double d) {
        this.duration = 0;
        ThemedReactContextKt.emitEvent(this.context, "KeyboardController::keyboardWillShow", getEventParams(d));
        for (KeyboardTransitionEvent.Companion.EventName keyboardTransitionEvent : CollectionsKt.listOf(KeyboardTransitionEvent.Companion.getStart(), KeyboardTransitionEvent.Companion.getMove(), KeyboardTransitionEvent.Companion.getEnd())) {
            ThemedReactContextKt.dispatchEvent(this.context, this.eventPropagationView.getId(), new KeyboardTransitionEvent(this.surfaceId, this.eventPropagationView.getId(), keyboardTransitionEvent, d, 1.0d, 0, this.viewTagFocused));
        }
        ThemedReactContextKt.emitEvent(this.context, "KeyboardController::keyboardDidShow", getEventParams(d));
        this.persistentKeyboardHeight = d;
    }

    private final boolean isKeyboardVisible() {
        WindowInsetsCompat rootWindowInsets = ViewCompat.getRootWindowInsets(this.view);
        if (rootWindowInsets != null) {
            return rootWindowInsets.isVisible(WindowInsetsCompat.Type.ime());
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0009, code lost:
        r2 = r0.getInsets(androidx.core.view.WindowInsetsCompat.Type.ime());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final double getCurrentKeyboardHeight() {
        /*
            r4 = this;
            android.view.View r0 = r4.view
            androidx.core.view.WindowInsetsCompat r0 = androidx.core.view.ViewCompat.getRootWindowInsets(r0)
            r1 = 0
            if (r0 == 0) goto L_0x0016
            int r2 = androidx.core.view.WindowInsetsCompat.Type.ime()
            androidx.core.graphics.Insets r2 = r0.getInsets(r2)
            if (r2 == 0) goto L_0x0016
            int r2 = r2.bottom
            goto L_0x0017
        L_0x0016:
            r2 = r1
        L_0x0017:
            com.reactnativekeyboardcontroller.listeners.KeyboardAnimationCallbackConfig r3 = r4.config
            boolean r3 = r3.getHasTranslucentNavigationBar()
            if (r3 == 0) goto L_0x0020
            goto L_0x002e
        L_0x0020:
            if (r0 == 0) goto L_0x002e
            int r3 = androidx.core.view.WindowInsetsCompat.Type.navigationBars()
            androidx.core.graphics.Insets r0 = r0.getInsets(r3)
            if (r0 == 0) goto L_0x002e
            int r1 = r0.bottom
        L_0x002e:
            int r2 = r2 - r1
            float r0 = (float) r2
            double r0 = com.reactnativekeyboardcontroller.extensions.FloatKt.getDp(r0)
            r2 = 0
            double r0 = kotlin.ranges.RangesKt.coerceAtLeast((double) r0, (double) r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reactnativekeyboardcontroller.listeners.KeyboardAnimationCallback.getCurrentKeyboardHeight():double");
    }

    private final WritableMap getEventParams(double d) {
        WritableMap createMap = Arguments.createMap();
        Intrinsics.checkNotNullExpressionValue(createMap, "createMap(...)");
        createMap.putDouble("height", d);
        createMap.putInt("duration", this.duration);
        createMap.putDouble("timestamp", (double) System.currentTimeMillis());
        createMap.putInt("target", this.viewTagFocused);
        EditText editText = FocusedInputHolder.INSTANCE.get();
        createMap.putString("type", editText != null ? EditTextKt.getKeyboardType(editText) : null);
        createMap.putString("appearance", ThemedReactContextKt.getAppearance(this.context));
        return createMap;
    }
}

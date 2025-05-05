package com.reactnativekeyboardcontroller.modal;

import android.content.DialogInterface;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.uimanager.events.EventDispatcherListener;
import com.facebook.react.views.view.ReactViewGroup;
import com.reactnativekeyboardcontroller.extensions.ViewGroupKt;
import com.reactnativekeyboardcontroller.listeners.KeyboardAnimationCallback;
import com.reactnativekeyboardcontroller.listeners.KeyboardAnimationCallbackConfig;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u000e\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t¢\u0006\u0002\u0010\u000bJ\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0013J\u0014\u0010\u0015\u001a\u00020\u00132\n\u0010\u0016\u001a\u0006\u0012\u0002\b\u00030\u0017H\u0016R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/reactnativekeyboardcontroller/modal/ModalAttachedWatcher;", "Lcom/facebook/react/uimanager/events/EventDispatcherListener;", "view", "Lcom/facebook/react/views/view/ReactViewGroup;", "reactContext", "Lcom/facebook/react/uimanager/ThemedReactContext;", "config", "Lcom/reactnativekeyboardcontroller/listeners/KeyboardAnimationCallbackConfig;", "callback", "Lkotlin/Function0;", "Lcom/reactnativekeyboardcontroller/listeners/KeyboardAnimationCallback;", "(Lcom/facebook/react/views/view/ReactViewGroup;Lcom/facebook/react/uimanager/ThemedReactContext;Lcom/reactnativekeyboardcontroller/listeners/KeyboardAnimationCallbackConfig;Lkotlin/jvm/functions/Function0;)V", "archType", "", "eventDispatcher", "Lcom/facebook/react/uimanager/events/EventDispatcher;", "uiManager", "Lcom/facebook/react/bridge/UIManager;", "disable", "", "enable", "onEventDispatch", "event", "Lcom/facebook/react/uimanager/events/Event;", "Companion", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ModalAttachedWatcher.kt */
public final class ModalAttachedWatcher implements EventDispatcherListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String MODAL_SHOW_EVENT = "topShow";
    private final int archType = 1;
    private Function0<KeyboardAnimationCallback> callback;
    private final KeyboardAnimationCallbackConfig config;
    private final EventDispatcher eventDispatcher;
    private final ThemedReactContext reactContext;
    private final UIManager uiManager;
    private final ReactViewGroup view;

    public ModalAttachedWatcher(ReactViewGroup reactViewGroup, ThemedReactContext themedReactContext, KeyboardAnimationCallbackConfig keyboardAnimationCallbackConfig, Function0<KeyboardAnimationCallback> function0) {
        Intrinsics.checkNotNullParameter(reactViewGroup, "view");
        Intrinsics.checkNotNullParameter(themedReactContext, "reactContext");
        Intrinsics.checkNotNullParameter(keyboardAnimationCallbackConfig, "config");
        Intrinsics.checkNotNullParameter(function0, "callback");
        this.view = reactViewGroup;
        this.reactContext = themedReactContext;
        this.config = keyboardAnimationCallbackConfig;
        this.callback = function0;
        this.uiManager = UIManagerHelper.getUIManager(themedReactContext.getReactApplicationContext(), 1);
        this.eventDispatcher = UIManagerHelper.getEventDispatcher(themedReactContext.getReactApplicationContext(), 1);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x004a A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x004b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onEventDispatch(com.facebook.react.uimanager.events.Event<?> r10) {
        /*
            r9 = this;
            java.lang.String r0 = "event"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            java.lang.String r0 = r10.getEventName()
            java.lang.String r1 = "topShow"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r1)
            if (r0 != 0) goto L_0x0012
            return
        L_0x0012:
            r0 = 0
            com.facebook.react.bridge.UIManager r1 = r9.uiManager     // Catch:{ Exception -> 0x0028 }
            if (r1 == 0) goto L_0x0020
            int r2 = r10.getViewTag()     // Catch:{ Exception -> 0x0028 }
            android.view.View r1 = r1.resolveView(r2)     // Catch:{ Exception -> 0x0028 }
            goto L_0x0021
        L_0x0020:
            r1 = r0
        L_0x0021:
            boolean r2 = r1 instanceof com.facebook.react.views.modal.ReactModalHostView     // Catch:{ Exception -> 0x0028 }
            if (r2 == 0) goto L_0x0047
            com.facebook.react.views.modal.ReactModalHostView r1 = (com.facebook.react.views.modal.ReactModalHostView) r1     // Catch:{ Exception -> 0x0028 }
            goto L_0x0048
        L_0x0028:
            r1 = move-exception
            com.reactnativekeyboardcontroller.log.Logger r2 = com.reactnativekeyboardcontroller.log.Logger.INSTANCE
            java.lang.String r3 = com.reactnativekeyboardcontroller.modal.ModalAttachedWatcherKt.TAG
            int r10 = r10.getViewTag()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Can not resolve view for Modal#"
            r4.<init>(r5)
            java.lang.StringBuilder r10 = r4.append(r10)
            java.lang.String r10 = r10.toString()
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            r2.w(r3, r10, r1)
        L_0x0047:
            r1 = r0
        L_0x0048:
            if (r1 != 0) goto L_0x004b
            return
        L_0x004b:
            android.app.Dialog r10 = r1.getDialog()
            if (r10 == 0) goto L_0x0056
            android.view.Window r1 = r10.getWindow()
            goto L_0x0057
        L_0x0056:
            r1 = r0
        L_0x0057:
            if (r1 == 0) goto L_0x0063
            android.view.View r2 = r1.getDecorView()
            if (r2 == 0) goto L_0x0063
            android.view.View r0 = r2.getRootView()
        L_0x0063:
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            if (r0 == 0) goto L_0x00cb
            com.facebook.react.views.view.ReactViewGroup r2 = new com.facebook.react.views.view.ReactViewGroup
            com.facebook.react.uimanager.ThemedReactContext r3 = r9.reactContext
            android.content.Context r3 = (android.content.Context) r3
            r2.<init>(r3)
            android.view.ViewGroup$LayoutParams r3 = new android.view.ViewGroup$LayoutParams
            r4 = 0
            r3.<init>(r4, r4)
            r2.setLayoutParams(r3)
            com.facebook.react.views.view.ReactViewGroup r3 = r9.view
            com.facebook.react.uimanager.ThemedReactContext r5 = r9.reactContext
            com.reactnativekeyboardcontroller.listeners.KeyboardAnimationCallbackConfig r6 = r9.config
            com.reactnativekeyboardcontroller.listeners.KeyboardAnimationCallback r7 = new com.reactnativekeyboardcontroller.listeners.KeyboardAnimationCallback
            r8 = r0
            android.view.View r8 = (android.view.View) r8
            r7.<init>(r3, r8, r5, r6)
            r3 = r2
            android.view.View r3 = (android.view.View) r3
            r0.addView(r3)
            boolean r0 = com.reactnativekeyboardcontroller.modal.ModalAttachedWatcherKt.areEventsComingFromOwnWindow
            if (r0 == 0) goto L_0x00ba
            kotlin.jvm.functions.Function0<com.reactnativekeyboardcontroller.listeners.KeyboardAnimationCallback> r0 = r9.callback
            java.lang.Object r0 = r0.invoke()
            com.reactnativekeyboardcontroller.listeners.KeyboardAnimationCallback r0 = (com.reactnativekeyboardcontroller.listeners.KeyboardAnimationCallback) r0
            if (r0 == 0) goto L_0x00a1
            r5 = 1
            r0.suspend(r5)
        L_0x00a1:
            r0 = r7
            androidx.core.view.WindowInsetsAnimationCompat$Callback r0 = (androidx.core.view.WindowInsetsAnimationCompat.Callback) r0
            androidx.core.view.ViewCompat.setWindowInsetsAnimationCallback(r8, r0)
            r0 = r7
            androidx.core.view.OnApplyWindowInsetsListener r0 = (androidx.core.view.OnApplyWindowInsetsListener) r0
            androidx.core.view.ViewCompat.setOnApplyWindowInsetsListener(r3, r0)
            r5 = 0
            java.lang.Double r0 = java.lang.Double.valueOf(r5)
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r4)
            r7.syncKeyboardPosition(r0, r3)
        L_0x00ba:
            if (r10 == 0) goto L_0x00c4
            com.reactnativekeyboardcontroller.modal.ModalAttachedWatcher$$ExternalSyntheticLambda0 r0 = new com.reactnativekeyboardcontroller.modal.ModalAttachedWatcher$$ExternalSyntheticLambda0
            r0.<init>(r7, r2, r9)
            r10.setOnDismissListener(r0)
        L_0x00c4:
            if (r1 == 0) goto L_0x00cb
            r10 = 48
            r1.setSoftInputMode(r10)
        L_0x00cb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reactnativekeyboardcontroller.modal.ModalAttachedWatcher.onEventDispatch(com.facebook.react.uimanager.events.Event):void");
    }

    /* access modifiers changed from: private */
    public static final void onEventDispatch$lambda$1(KeyboardAnimationCallback keyboardAnimationCallback, ReactViewGroup reactViewGroup, ModalAttachedWatcher modalAttachedWatcher, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(keyboardAnimationCallback, "$callback");
        Intrinsics.checkNotNullParameter(reactViewGroup, "$eventView");
        Intrinsics.checkNotNullParameter(modalAttachedWatcher, "this$0");
        KeyboardAnimationCallback.syncKeyboardPosition$default(keyboardAnimationCallback, (Double) null, (Boolean) null, 3, (Object) null);
        keyboardAnimationCallback.destroy();
        ViewGroupKt.removeSelf(reactViewGroup);
        KeyboardAnimationCallback invoke = modalAttachedWatcher.callback.invoke();
        if (invoke != null) {
            invoke.suspend(false);
        }
    }

    public final void enable() {
        EventDispatcher eventDispatcher2 = this.eventDispatcher;
        if (eventDispatcher2 != null) {
            eventDispatcher2.addListener(this);
        }
    }

    public final void disable() {
        EventDispatcher eventDispatcher2 = this.eventDispatcher;
        if (eventDispatcher2 != null) {
            eventDispatcher2.removeListener(this);
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/reactnativekeyboardcontroller/modal/ModalAttachedWatcher$Companion;", "", "()V", "MODAL_SHOW_EVENT", "", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ModalAttachedWatcher.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}

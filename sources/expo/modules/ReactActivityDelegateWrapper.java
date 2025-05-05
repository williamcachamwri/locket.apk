package expo.modules;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.collection.ArrayMap;
import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactDelegate;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactRootView;
import com.facebook.react.modules.core.PermissionListener;
import expo.modules.core.interfaces.Package;
import expo.modules.core.interfaces.ReactActivityHandler;
import expo.modules.core.interfaces.ReactActivityLifecycleListener;
import expo.modules.kotlin.Utils;
import expo.modules.kotlin.exception.Exceptions;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;

@Metadata(d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0015\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001¢\u0006\u0002\u0010\u0005B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0001¢\u0006\u0002\u0010\bJ\b\u0010\u001a\u001a\u00020\u001bH\u0014J\b\u0010\u001c\u001a\u00020\u001dH\u0014J\n\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0014J\n\u0010 \u001a\u0004\u0018\u00010\u0011H\u0016J\b\u0010!\u001a\u00020\"H\u0014J\b\u0010#\u001a\u00020$H\u0016J\b\u0010%\u001a\u00020\nH\u0014J\u001b\u0010&\u001a\u0002H'\"\u0004\b\u0000\u0010'2\u0006\u0010(\u001a\u00020\u0011H\u0002¢\u0006\u0002\u0010)JA\u0010&\u001a\u0002H'\"\u0004\b\u0000\u0010'\"\u0004\b\u0001\u0010*2\u0006\u0010(\u001a\u00020\u00112\u0010\u0010+\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030-0,2\f\u0010.\u001a\b\u0012\u0004\u0012\u0002H*0,H\u0002¢\u0006\u0002\u0010/J\b\u00100\u001a\u00020\u0007H\u0014J\u0012\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u00010\u0011H\u0014J\"\u00104\u001a\u0002022\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u0002062\b\u00108\u001a\u0004\u0018\u000109H\u0016J\b\u0010:\u001a\u00020\u0007H\u0016J\u0012\u0010;\u001a\u0002022\b\u0010<\u001a\u0004\u0018\u00010\u001fH\u0014J\b\u0010=\u001a\u000202H\u0014J\u001a\u0010>\u001a\u00020\u00072\u0006\u0010?\u001a\u0002062\b\u0010@\u001a\u0004\u0018\u00010AH\u0016J\u001a\u0010B\u001a\u00020\u00072\u0006\u0010?\u001a\u0002062\b\u0010@\u001a\u0004\u0018\u00010AH\u0016J\u001a\u0010C\u001a\u00020\u00072\u0006\u0010?\u001a\u0002062\b\u0010@\u001a\u0004\u0018\u00010AH\u0016J\u0012\u0010D\u001a\u00020\u00072\b\u0010E\u001a\u0004\u0018\u000109H\u0016J\b\u0010F\u001a\u000202H\u0014J1\u0010G\u001a\u0002022\u0006\u00105\u001a\u0002062\u0010\u0010H\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0011\u0018\u00010,2\b\u0010I\u001a\u0004\u0018\u00010JH\u0016¢\u0006\u0002\u0010KJ\b\u0010L\u001a\u000202H\u0014J\u0010\u0010M\u001a\u0002022\u0006\u0010N\u001a\u00020\u0007H\u0016J1\u0010O\u001a\u0002022\u0010\u0010H\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0011\u0018\u00010,2\u0006\u00105\u001a\u0002062\b\u0010P\u001a\u0004\u0018\u00010QH\u0016¢\u0006\u0002\u0010RR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0001X\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0010X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0013\u001a\u0010\u0012\f\u0012\n \u0016*\u0004\u0018\u00010\u00150\u00150\u0014X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0017\u001a\u0010\u0012\f\u0012\n \u0016*\u0004\u0018\u00010\u00180\u00180\u0014X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000¨\u0006S"}, d2 = {"Lexpo/modules/ReactActivityDelegateWrapper;", "Lcom/facebook/react/ReactActivityDelegate;", "activity", "Lcom/facebook/react/ReactActivity;", "delegate", "(Lcom/facebook/react/ReactActivity;Lcom/facebook/react/ReactActivityDelegate;)V", "isNewArchitectureEnabled", "", "(Lcom/facebook/react/ReactActivity;ZLcom/facebook/react/ReactActivityDelegate;)V", "host", "Lcom/facebook/react/ReactNativeHost;", "getHost", "()Lcom/facebook/react/ReactNativeHost;", "host$delegate", "Lkotlin/Lazy;", "methodMap", "Landroidx/collection/ArrayMap;", "", "Ljava/lang/reflect/Method;", "reactActivityHandlers", "", "Lexpo/modules/core/interfaces/ReactActivityHandler;", "kotlin.jvm.PlatformType", "reactActivityLifecycleListeners", "Lexpo/modules/core/interfaces/ReactActivityLifecycleListener;", "shouldEmitPendingResume", "createRootView", "Lcom/facebook/react/ReactRootView;", "getContext", "Landroid/content/Context;", "getLaunchOptions", "Landroid/os/Bundle;", "getMainComponentName", "getPlainActivity", "Landroid/app/Activity;", "getReactInstanceManager", "Lcom/facebook/react/ReactInstanceManager;", "getReactNativeHost", "invokeDelegateMethod", "T", "name", "(Ljava/lang/String;)Ljava/lang/Object;", "A", "argTypes", "", "Ljava/lang/Class;", "args", "(Ljava/lang/String;[Ljava/lang/Class;[Ljava/lang/Object;)Ljava/lang/Object;", "isFabricEnabled", "loadApp", "", "appKey", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onBackPressed", "onCreate", "savedInstanceState", "onDestroy", "onKeyDown", "keyCode", "event", "Landroid/view/KeyEvent;", "onKeyLongPress", "onKeyUp", "onNewIntent", "intent", "onPause", "onRequestPermissionsResult", "permissions", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onResume", "onWindowFocusChanged", "hasFocus", "requestPermissions", "listener", "Lcom/facebook/react/modules/core/PermissionListener;", "([Ljava/lang/String;ILcom/facebook/react/modules/core/PermissionListener;)V", "expo_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ReactActivityDelegateWrapper.kt */
public final class ReactActivityDelegateWrapper extends ReactActivityDelegate {
    /* access modifiers changed from: private */
    public final ReactActivity activity;
    /* access modifiers changed from: private */
    public ReactActivityDelegate delegate;
    private final Lazy host$delegate;
    private final boolean isNewArchitectureEnabled;
    private final ArrayMap<String, Method> methodMap;
    private final List<ReactActivityHandler> reactActivityHandlers;
    private final List<ReactActivityLifecycleListener> reactActivityLifecycleListeners;
    private boolean shouldEmitPendingResume;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ReactActivityDelegateWrapper(ReactActivity reactActivity, boolean z, ReactActivityDelegate reactActivityDelegate) {
        super(reactActivity, (String) null);
        Intrinsics.checkNotNullParameter(reactActivity, "activity");
        Intrinsics.checkNotNullParameter(reactActivityDelegate, "delegate");
        this.activity = reactActivity;
        this.isNewArchitectureEnabled = z;
        this.delegate = reactActivityDelegate;
        Collection arrayList = new ArrayList();
        for (Package createReactActivityLifecycleListeners : ExpoModulesPackage.Companion.getPackageList()) {
            List<? extends ReactActivityLifecycleListener> createReactActivityLifecycleListeners2 = createReactActivityLifecycleListeners.createReactActivityLifecycleListeners(this.activity);
            Intrinsics.checkNotNullExpressionValue(createReactActivityLifecycleListeners2, "createReactActivityLifecycleListeners(...)");
            CollectionsKt.addAll(arrayList, createReactActivityLifecycleListeners2);
        }
        this.reactActivityLifecycleListeners = (List) arrayList;
        Collection arrayList2 = new ArrayList();
        for (Package createReactActivityHandlers : ExpoModulesPackage.Companion.getPackageList()) {
            List<? extends ReactActivityHandler> createReactActivityHandlers2 = createReactActivityHandlers.createReactActivityHandlers(this.activity);
            Intrinsics.checkNotNullExpressionValue(createReactActivityHandlers2, "createReactActivityHandlers(...)");
            CollectionsKt.addAll(arrayList2, createReactActivityHandlers2);
        }
        this.reactActivityHandlers = (List) arrayList2;
        this.methodMap = new ArrayMap<>();
        this.host$delegate = LazyKt.lazy(new ReactActivityDelegateWrapper$host$2(this));
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ReactActivityDelegateWrapper(ReactActivity reactActivity, ReactActivityDelegate reactActivityDelegate) {
        this(reactActivity, false, reactActivityDelegate);
        Intrinsics.checkNotNullParameter(reactActivity, "activity");
        Intrinsics.checkNotNullParameter(reactActivityDelegate, "delegate");
    }

    /* access modifiers changed from: private */
    public final ReactNativeHost getHost() {
        return (ReactNativeHost) this.host$delegate.getValue();
    }

    /* access modifiers changed from: protected */
    public Bundle getLaunchOptions() {
        return (Bundle) invokeDelegateMethod("getLaunchOptions");
    }

    /* access modifiers changed from: protected */
    public ReactRootView createRootView() {
        ReactRootView reactRootView = (ReactRootView) SequencesKt.firstOrNull(SequencesKt.mapNotNull(CollectionsKt.asSequence(this.reactActivityHandlers), new ReactActivityDelegateWrapper$createRootView$rootView$1(this)));
        if (reactRootView == null) {
            reactRootView = (ReactRootView) invokeDelegateMethod("createRootView");
        }
        Intrinsics.checkNotNull(reactRootView);
        reactRootView.setIsFabric(this.isNewArchitectureEnabled);
        return reactRootView;
    }

    /* access modifiers changed from: protected */
    public ReactNativeHost getReactNativeHost() {
        return getHost();
    }

    public ReactInstanceManager getReactInstanceManager() {
        ReactInstanceManager reactInstanceManager = this.delegate.getReactInstanceManager();
        Intrinsics.checkNotNullExpressionValue(reactInstanceManager, "getReactInstanceManager(...)");
        return reactInstanceManager;
    }

    public String getMainComponentName() {
        return this.delegate.getMainComponentName();
    }

    /* access modifiers changed from: protected */
    public void loadApp(String str) {
        ViewGroup viewGroup = (ViewGroup) SequencesKt.firstOrNull(SequencesKt.mapNotNull(CollectionsKt.asSequence(this.reactActivityHandlers), new ReactActivityDelegateWrapper$loadApp$rootViewContainer$1(this)));
        if (viewGroup != null) {
            Field declaredField = ReactActivityDelegate.class.getDeclaredField("mReactDelegate");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(this.delegate);
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.facebook.react.ReactDelegate");
            ReactDelegate reactDelegate = (ReactDelegate) obj;
            reactDelegate.loadApp(str);
            viewGroup.addView(reactDelegate.getReactRootView(), -1);
            this.activity.setContentView((View) viewGroup);
            for (ReactActivityLifecycleListener onContentChanged : this.reactActivityLifecycleListeners) {
                onContentChanged.onContentChanged(this.activity);
            }
            return;
        }
        ReactActivityHandler.DelayLoadAppHandler delayLoadAppHandler = (ReactActivityHandler.DelayLoadAppHandler) SequencesKt.firstOrNull(SequencesKt.mapNotNull(CollectionsKt.asSequence(this.reactActivityHandlers), new ReactActivityDelegateWrapper$loadApp$delayLoadAppHandler$1(this)));
        if (delayLoadAppHandler != null) {
            delayLoadAppHandler.whenReady(new ReactActivityDelegateWrapper$$ExternalSyntheticLambda0(this, str));
            return;
        }
        invokeDelegateMethod("loadApp", new Class[]{String.class}, new String[]{str});
        for (ReactActivityLifecycleListener onContentChanged2 : this.reactActivityLifecycleListeners) {
            onContentChanged2.onContentChanged(this.activity);
        }
    }

    /* access modifiers changed from: private */
    public static final void loadApp$lambda$4(ReactActivityDelegateWrapper reactActivityDelegateWrapper, String str) {
        Intrinsics.checkNotNullParameter(reactActivityDelegateWrapper, "this$0");
        Utils utils = Utils.INSTANCE;
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            reactActivityDelegateWrapper.invokeDelegateMethod("loadApp", new Class[]{String.class}, new String[]{str});
            for (ReactActivityLifecycleListener onContentChanged : reactActivityDelegateWrapper.reactActivityLifecycleListeners) {
                onContentChanged.onContentChanged(reactActivityDelegateWrapper.activity);
            }
            if (reactActivityDelegateWrapper.shouldEmitPendingResume) {
                reactActivityDelegateWrapper.onResume();
                return;
            }
            return;
        }
        String name = Thread.currentThread().getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        String name2 = Looper.getMainLooper().getThread().getName();
        Intrinsics.checkNotNullExpressionValue(name2, "getName(...)");
        throw new Exceptions.IncorrectThreadException(name, name2);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        ReactActivityDelegate reactActivityDelegate = (ReactActivityDelegate) SequencesKt.firstOrNull(SequencesKt.mapNotNull(CollectionsKt.asSequence(this.reactActivityHandlers), new ReactActivityDelegateWrapper$onCreate$newDelegate$1(this)));
        if (reactActivityDelegate == null || Intrinsics.areEqual((Object) reactActivityDelegate, (Object) this)) {
            ReactActivityDelegateWrapper$onCreate$reactDelegate$1 reactActivityDelegateWrapper$onCreate$reactDelegate$1 = new ReactActivityDelegateWrapper$onCreate$reactDelegate$1(composeLaunchOptions(), this, getPlainActivity(), getReactNativeHost(), getMainComponentName());
            Field declaredField = ReactActivityDelegate.class.getDeclaredField("mReactDelegate");
            declaredField.setAccessible(true);
            declaredField.set(this.delegate, reactActivityDelegateWrapper$onCreate$reactDelegate$1);
            if (getMainComponentName() != null) {
                loadApp(getMainComponentName());
            }
        } else {
            Field declaredField2 = ReactActivity.class.getDeclaredField("mDelegate");
            declaredField2.setAccessible(true);
            Field declaredField3 = Field.class.getDeclaredField("accessFlags");
            declaredField3.setAccessible(true);
            declaredField3.setInt(declaredField2, declaredField2.getModifiers() & -17);
            declaredField2.set(this.activity, reactActivityDelegate);
            this.delegate = reactActivityDelegate;
            invokeDelegateMethod("onCreate", new Class[]{Bundle.class}, new Bundle[]{bundle});
        }
        for (ReactActivityLifecycleListener onCreate : this.reactActivityLifecycleListeners) {
            onCreate.onCreate(this.activity, bundle);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        if (!getHost().hasInstance()) {
            this.shouldEmitPendingResume = true;
            return;
        }
        invokeDelegateMethod("onResume");
        for (ReactActivityLifecycleListener onResume : this.reactActivityLifecycleListeners) {
            onResume.onResume(this.activity);
        }
        this.shouldEmitPendingResume = false;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        this.shouldEmitPendingResume = false;
        if (getHost().hasInstance()) {
            for (ReactActivityLifecycleListener onPause : this.reactActivityLifecycleListeners) {
                onPause.onPause(this.activity);
            }
            invokeDelegateMethod("onPause");
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.shouldEmitPendingResume = false;
        if (getHost().hasInstance()) {
            for (ReactActivityLifecycleListener onDestroy : this.reactActivityLifecycleListeners) {
                onDestroy.onDestroy(this.activity);
            }
            invokeDelegateMethod("onDestroy");
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (this.delegate.getReactInstanceManager().getCurrentReactContext() == null) {
            this.delegate.getReactInstanceManager().addReactInstanceEventListener(new ReactActivityDelegateWrapper$onActivityResult$reactContextListener$1(this, i, i2, intent));
            return;
        }
        this.delegate.onActivityResult(i, i2, intent);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return this.delegate.onKeyDown(i, keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        boolean z;
        Iterable<ReactActivityHandler> iterable = this.reactActivityHandlers;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (ReactActivityHandler onKeyUp : iterable) {
            arrayList.add(Boolean.valueOf(onKeyUp.onKeyUp(i, keyEvent)));
        }
        Iterator it = ((List) arrayList).iterator();
        loop1:
        while (true) {
            z = false;
            while (true) {
                if (!it.hasNext()) {
                    break loop1;
                }
                boolean booleanValue = ((Boolean) it.next()).booleanValue();
                if (z || booleanValue) {
                    z = true;
                }
            }
        }
        return z || this.delegate.onKeyUp(i, keyEvent);
    }

    public boolean onKeyLongPress(int i, KeyEvent keyEvent) {
        return this.delegate.onKeyLongPress(i, keyEvent);
    }

    public boolean onBackPressed() {
        boolean z;
        Iterable<ReactActivityLifecycleListener> iterable = this.reactActivityLifecycleListeners;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (ReactActivityLifecycleListener onBackPressed : iterable) {
            arrayList.add(Boolean.valueOf(onBackPressed.onBackPressed()));
        }
        Iterator it = ((List) arrayList).iterator();
        loop1:
        while (true) {
            z = false;
            while (true) {
                if (!it.hasNext()) {
                    break loop1;
                }
                boolean booleanValue = ((Boolean) it.next()).booleanValue();
                if (z || booleanValue) {
                    z = true;
                }
            }
        }
        return z || this.delegate.onBackPressed();
    }

    public boolean onNewIntent(Intent intent) {
        boolean z;
        Iterable<ReactActivityLifecycleListener> iterable = this.reactActivityLifecycleListeners;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (ReactActivityLifecycleListener onNewIntent : iterable) {
            arrayList.add(Boolean.valueOf(onNewIntent.onNewIntent(intent)));
        }
        Iterator it = ((List) arrayList).iterator();
        loop1:
        while (true) {
            z = false;
            while (true) {
                if (!it.hasNext()) {
                    break loop1;
                }
                boolean booleanValue = ((Boolean) it.next()).booleanValue();
                if (z || booleanValue) {
                    z = true;
                }
            }
        }
        return z || this.delegate.onNewIntent(intent);
    }

    public void onWindowFocusChanged(boolean z) {
        this.delegate.onWindowFocusChanged(z);
    }

    public void requestPermissions(String[] strArr, int i, PermissionListener permissionListener) {
        this.delegate.requestPermissions(strArr, i, permissionListener);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.delegate.onRequestPermissionsResult(i, strArr, iArr);
    }

    /* access modifiers changed from: protected */
    public Context getContext() {
        return (Context) invokeDelegateMethod("getContext");
    }

    /* access modifiers changed from: protected */
    public Activity getPlainActivity() {
        return (Activity) invokeDelegateMethod("getPlainActivity");
    }

    /* access modifiers changed from: protected */
    public boolean isFabricEnabled() {
        return ((Boolean) invokeDelegateMethod("isFabricEnabled")).booleanValue();
    }

    /* access modifiers changed from: private */
    public final <T> T invokeDelegateMethod(String str) {
        Method method = this.methodMap.get(str);
        if (method == null) {
            method = ReactActivityDelegate.class.getDeclaredMethod(str, new Class[0]);
            method.setAccessible(true);
            this.methodMap.put(str, method);
        }
        Intrinsics.checkNotNull(method);
        return method.invoke(this.delegate, new Object[0]);
    }

    private final <T, A> T invokeDelegateMethod(String str, Class<?>[] clsArr, A[] aArr) {
        Method method = this.methodMap.get(str);
        if (method == null) {
            method = ReactActivityDelegate.class.getDeclaredMethod(str, (Class[]) Arrays.copyOf(clsArr, clsArr.length));
            method.setAccessible(true);
            this.methodMap.put(str, method);
        }
        Intrinsics.checkNotNull(method);
        return method.invoke(this.delegate, Arrays.copyOf(aArr, aArr.length));
    }
}

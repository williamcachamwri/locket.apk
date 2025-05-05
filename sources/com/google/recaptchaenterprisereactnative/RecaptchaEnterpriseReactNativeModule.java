package com.google.recaptchaenterprisereactnative;

import android.app.Application;
import android.content.Context;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.google.android.recaptcha.RecaptchaAction;
import com.google.android.recaptcha.RecaptchaClient;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\u0018\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\b\u0010\u0013\u001a\u00020\fH\u0016J \u0010\u0014\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX.¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/google/recaptchaenterprisereactnative/RecaptchaEnterpriseReactNativeModule;", "Lcom/facebook/react/bridge/ReactContextBaseJavaModule;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "application", "Landroid/app/Application;", "recaptchaClient", "Lcom/google/android/recaptcha/RecaptchaClient;", "execute", "", "actionStr", "", "arguments", "Lcom/facebook/react/bridge/ReadableMap;", "promise", "Lcom/facebook/react/bridge/Promise;", "fetchClient", "siteKey", "getName", "initClient", "mapAction", "Lcom/google/android/recaptcha/RecaptchaAction;", "Companion", "google-cloud_recaptcha-enterprise-react-native_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: RecaptchaEnterpriseReactNativeModule.kt */
public final class RecaptchaEnterpriseReactNativeModule extends ReactContextBaseJavaModule {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String NAME = "RecaptchaEnterpriseReactNative";
    /* access modifiers changed from: private */
    public final Application application;
    /* access modifiers changed from: private */
    public RecaptchaClient recaptchaClient;

    public String getName() {
        return NAME;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RecaptchaEnterpriseReactNativeModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        Context applicationContext = reactApplicationContext.getApplicationContext();
        Intrinsics.checkNotNull(applicationContext, "null cannot be cast to non-null type android.app.Application");
        this.application = (Application) applicationContext;
    }

    public final RecaptchaAction mapAction(String str) {
        Intrinsics.checkNotNullParameter(str, "actionStr");
        if (StringsKt.equals(str, FirebaseAnalytics.Event.LOGIN, false)) {
            return RecaptchaAction.LOGIN;
        }
        if (StringsKt.equals(str, "signup", false)) {
            return RecaptchaAction.SIGNUP;
        }
        return RecaptchaAction.Companion.custom(str);
    }

    @ReactMethod
    public final void fetchClient(String str, Promise promise) {
        Intrinsics.checkNotNullParameter(str, "siteKey");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        Job unused = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, (CoroutineContext) null, (CoroutineStart) null, new RecaptchaEnterpriseReactNativeModule$fetchClient$1(this, str, promise, (Continuation<? super RecaptchaEnterpriseReactNativeModule$fetchClient$1>) null), 3, (Object) null);
    }

    @ReactMethod
    public final void initClient(String str, ReadableMap readableMap, Promise promise) {
        Intrinsics.checkNotNullParameter(str, "siteKey");
        Intrinsics.checkNotNullParameter(readableMap, "arguments");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        Job unused = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, (CoroutineContext) null, (CoroutineStart) null, new RecaptchaEnterpriseReactNativeModule$initClient$1(readableMap, this, str, promise, (Continuation<? super RecaptchaEnterpriseReactNativeModule$initClient$1>) null), 3, (Object) null);
    }

    @ReactMethod
    public final void execute(String str, ReadableMap readableMap, Promise promise) {
        Intrinsics.checkNotNullParameter(str, "actionStr");
        Intrinsics.checkNotNullParameter(readableMap, "arguments");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        if (this.recaptchaClient == null) {
            promise.reject("RN_EXECUTE_FAILED", "Initialize client first", (Throwable) null);
            return;
        }
        RecaptchaAction mapAction = mapAction(str);
        Job unused = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, (CoroutineContext) null, (CoroutineStart) null, new RecaptchaEnterpriseReactNativeModule$execute$1(this, readableMap, mapAction, promise, (Continuation<? super RecaptchaEnterpriseReactNativeModule$execute$1>) null), 3, (Object) null);
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/google/recaptchaenterprisereactnative/RecaptchaEnterpriseReactNativeModule$Companion;", "", "()V", "NAME", "", "google-cloud_recaptcha-enterprise-react-native_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: RecaptchaEnterpriseReactNativeModule.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}

package com.adsbynimbus.internal;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.adsbynimbus.internal.DefaultActivityListener;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import java.lang.ref.WeakReference;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010#\u001a\u00020\u00152\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00150\u0014J\u0010\u0010%\u001a\u00020\u00152\u0006\u0010&\u001a\u00020\tH\u0016R\u0012\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R \u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001b\u0010\u000e\u001a\u00020\u00068FX\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010R(\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0012\u0010\u001a\u001a\u00020\u00068\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u001b\u001a\u00020\u001c8FX\u0002¢\u0006\f\n\u0004\b\u001f\u0010\u0012\u001a\u0004\b\u001d\u0010\u001eR\u001b\u0010 \u001a\u00020\u00068FX\u0002¢\u0006\f\n\u0004\b\"\u0010\u0012\u001a\u0004\b!\u0010\u0010¨\u0006'"}, d2 = {"Lcom/adsbynimbus/internal/Platform;", "Lcom/adsbynimbus/internal/DefaultActivityListener;", "()V", "adInfo", "Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$Info;", "apiKey", "", "currentActivity", "Ljava/lang/ref/WeakReference;", "Landroid/app/Activity;", "getCurrentActivity", "()Ljava/lang/ref/WeakReference;", "setCurrentActivity", "(Ljava/lang/ref/WeakReference;)V", "iid", "getIid", "()Ljava/lang/String;", "iid$delegate", "Lkotlin/Lazy;", "onNextActivity", "Lkotlin/Function1;", "", "getOnNextActivity", "()Lkotlin/jvm/functions/Function1;", "setOnNextActivity", "(Lkotlin/jvm/functions/Function1;)V", "publisherKey", "sharedPreferences", "Landroid/content/SharedPreferences;", "getSharedPreferences", "()Landroid/content/SharedPreferences;", "sharedPreferences$delegate", "userAgent", "getUserAgent", "userAgent$delegate", "doOnNextActivity", "block", "onActivityResumed", "activity", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Platform.kt */
public final class Platform implements DefaultActivityListener {
    public static final Platform INSTANCE = new Platform();
    public static AdvertisingIdClient.Info adInfo = PlatformKt.getDefaultAdInfo();
    public static String apiKey = "";
    private static WeakReference<Activity> currentActivity = new WeakReference<>((Object) null);
    private static final Lazy iid$delegate = LazyKt.lazy(Platform$iid$2.INSTANCE);
    private static Function1<? super Activity, Unit> onNextActivity;
    public static String publisherKey = "";
    private static final Lazy sharedPreferences$delegate = LazyKt.lazy(Platform$sharedPreferences$2.INSTANCE);
    private static final Lazy userAgent$delegate = LazyKt.lazy(Platform$userAgent$2.INSTANCE);

    private Platform() {
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        DefaultActivityListener.DefaultImpls.onActivityCreated(this, activity, bundle);
    }

    public void onActivityDestroyed(Activity activity) {
        DefaultActivityListener.DefaultImpls.onActivityDestroyed(this, activity);
    }

    public void onActivityPaused(Activity activity) {
        DefaultActivityListener.DefaultImpls.onActivityPaused(this, activity);
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        DefaultActivityListener.DefaultImpls.onActivitySaveInstanceState(this, activity, bundle);
    }

    public void onActivityStarted(Activity activity) {
        DefaultActivityListener.DefaultImpls.onActivityStarted(this, activity);
    }

    public void onActivityStopped(Activity activity) {
        DefaultActivityListener.DefaultImpls.onActivityStopped(this, activity);
    }

    public final WeakReference<Activity> getCurrentActivity() {
        return currentActivity;
    }

    public final void setCurrentActivity(WeakReference<Activity> weakReference) {
        Intrinsics.checkNotNullParameter(weakReference, "<set-?>");
        currentActivity = weakReference;
    }

    public final String getIid() {
        return (String) iid$delegate.getValue();
    }

    public final SharedPreferences getSharedPreferences() {
        Object value = sharedPreferences$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-sharedPreferences>(...)");
        return (SharedPreferences) value;
    }

    public final String getUserAgent() {
        Object value = userAgent$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-userAgent>(...)");
        return (String) value;
    }

    public final Function1<Activity, Unit> getOnNextActivity() {
        return onNextActivity;
    }

    public final void setOnNextActivity(Function1<? super Activity, Unit> function1) {
        onNextActivity = function1;
    }

    public void onActivityResumed(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        currentActivity = new WeakReference<>(activity);
        Function1<? super Activity, Unit> function1 = onNextActivity;
        if (function1 != null) {
            function1.invoke(activity);
        }
        onNextActivity = null;
    }

    public final void doOnNextActivity(Function1<? super Activity, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "block");
        onNextActivity = function1;
    }
}

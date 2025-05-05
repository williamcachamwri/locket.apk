package com.adsbynimbus.internal;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0007H\u0016J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u000e"}, d2 = {"Lcom/adsbynimbus/internal/DefaultActivityListener;", "Landroid/app/Application$ActivityLifecycleCallbacks;", "onActivityCreated", "", "activity", "Landroid/app/Activity;", "savedInstanceState", "Landroid/os/Bundle;", "onActivityDestroyed", "onActivityPaused", "onActivitySaveInstanceState", "outState", "onActivityStarted", "onActivityStopped", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Platform.kt */
public interface DefaultActivityListener extends Application.ActivityLifecycleCallbacks {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: Platform.kt */
    public static final class DefaultImpls {
        public static void onActivityCreated(DefaultActivityListener defaultActivityListener, Activity activity, Bundle bundle) {
            Intrinsics.checkNotNullParameter(activity, "activity");
        }

        public static void onActivityDestroyed(DefaultActivityListener defaultActivityListener, Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
        }

        public static void onActivityPaused(DefaultActivityListener defaultActivityListener, Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
        }

        public static void onActivitySaveInstanceState(DefaultActivityListener defaultActivityListener, Activity activity, Bundle bundle) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(bundle, "outState");
        }

        public static void onActivityStarted(DefaultActivityListener defaultActivityListener, Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
        }

        public static void onActivityStopped(DefaultActivityListener defaultActivityListener, Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
        }
    }

    void onActivityCreated(Activity activity, Bundle bundle);

    void onActivityDestroyed(Activity activity);

    void onActivityPaused(Activity activity);

    void onActivitySaveInstanceState(Activity activity, Bundle bundle);

    void onActivityStarted(Activity activity);

    void onActivityStopped(Activity activity);
}

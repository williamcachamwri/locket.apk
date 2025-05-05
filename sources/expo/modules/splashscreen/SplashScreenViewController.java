package expo.modules.splashscreen;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import expo.modules.splashscreen.exceptions.NoContentViewException;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0015\u001a\u00020\bH\u0002J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0015\u001a\u00020\u0006H\u0002JR\u0010\u0018\u001a\u00020\u00172#\b\u0002\u0010\u0019\u001a\u001d\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u00170\u001a2#\b\u0002\u0010\u001e\u001a\u001d\u0012\u0013\u0012\u00110\u001f¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u00170\u001aH\u0016JL\u0010!\u001a\u00020\u00172!\u0010\u0019\u001a\u001d\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u00170\u001a2!\u0010\u001e\u001a\u001d\u0012\u0013\u0012\u00110\u001f¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u00170\u001aJ\b\u0010\"\u001a\u00020\u0017H\u0002J\u0018\u0010#\u001a\u00020\u00172\u000e\b\u0002\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00170$H\u0016R\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0011\u001a\u0010\u0012\f\u0012\n \u0013*\u0004\u0018\u00010\u00030\u00030\u0012X\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lexpo/modules/splashscreen/SplashScreenViewController;", "", "activity", "Landroid/app/Activity;", "rootViewClass", "Ljava/lang/Class;", "Landroid/view/ViewGroup;", "splashScreenView", "Landroid/view/View;", "(Landroid/app/Activity;Ljava/lang/Class;Landroid/view/View;)V", "autoHideEnabled", "", "contentView", "handler", "Landroid/os/Handler;", "rootView", "splashScreenShown", "weakActivity", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "findRootView", "view", "handleRootView", "", "hideSplashScreen", "successCallback", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "hasEffect", "failureCallback", "", "reason", "preventAutoHide", "searchForRootView", "showSplashScreen", "Lkotlin/Function0;", "expo-splash-screen_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SplashScreenViewController.kt */
public class SplashScreenViewController {
    /* access modifiers changed from: private */
    public boolean autoHideEnabled;
    private final ViewGroup contentView;
    private final Handler handler;
    /* access modifiers changed from: private */
    public ViewGroup rootView;
    private final Class<? extends ViewGroup> rootViewClass;
    private boolean splashScreenShown;
    private final View splashScreenView;
    private final WeakReference<Activity> weakActivity;

    public SplashScreenViewController(Activity activity, Class<? extends ViewGroup> cls, View view) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(cls, "rootViewClass");
        Intrinsics.checkNotNullParameter(view, "splashScreenView");
        this.rootViewClass = cls;
        this.splashScreenView = view;
        this.weakActivity = new WeakReference<>(activity);
        ViewGroup viewGroup = (ViewGroup) activity.findViewById(16908290);
        if (viewGroup != null) {
            this.contentView = viewGroup;
            this.handler = new Handler(Looper.getMainLooper());
            this.autoHideEnabled = true;
            return;
        }
        throw new NoContentViewException();
    }

    public static /* synthetic */ void showSplashScreen$default(SplashScreenViewController splashScreenViewController, Function0 function0, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                function0 = SplashScreenViewController$showSplashScreen$1.INSTANCE;
            }
            splashScreenViewController.showSplashScreen(function0);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: showSplashScreen");
    }

    public void showSplashScreen(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "successCallback");
        Activity activity = (Activity) this.weakActivity.get();
        if (activity != null) {
            activity.runOnUiThread(new SplashScreenViewController$$ExternalSyntheticLambda1(this, function0));
        }
    }

    /* access modifiers changed from: private */
    public static final void showSplashScreen$lambda$0(SplashScreenViewController splashScreenViewController, Function0 function0) {
        Intrinsics.checkNotNullParameter(splashScreenViewController, "this$0");
        Intrinsics.checkNotNullParameter(function0, "$successCallback");
        ViewParent parent = splashScreenViewController.splashScreenView.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup != null) {
            viewGroup.removeView(splashScreenViewController.splashScreenView);
        }
        splashScreenViewController.contentView.addView(splashScreenViewController.splashScreenView);
        splashScreenViewController.splashScreenShown = true;
        function0.invoke();
        splashScreenViewController.searchForRootView();
    }

    public final void preventAutoHide(Function1<? super Boolean, Unit> function1, Function1<? super String, Unit> function12) {
        Intrinsics.checkNotNullParameter(function1, "successCallback");
        Intrinsics.checkNotNullParameter(function12, "failureCallback");
        if (!this.autoHideEnabled || !this.splashScreenShown) {
            function1.invoke(false);
            return;
        }
        this.autoHideEnabled = false;
        function1.invoke(true);
    }

    public static /* synthetic */ void hideSplashScreen$default(SplashScreenViewController splashScreenViewController, Function1 function1, Function1 function12, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                function1 = SplashScreenViewController$hideSplashScreen$1.INSTANCE;
            }
            if ((i & 2) != 0) {
                function12 = SplashScreenViewController$hideSplashScreen$2.INSTANCE;
            }
            splashScreenViewController.hideSplashScreen(function1, function12);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: hideSplashScreen");
    }

    public void hideSplashScreen(Function1<? super Boolean, Unit> function1, Function1<? super String, Unit> function12) {
        Intrinsics.checkNotNullParameter(function1, "successCallback");
        Intrinsics.checkNotNullParameter(function12, "failureCallback");
        if (!this.splashScreenShown) {
            function1.invoke(false);
            return;
        }
        Activity activity = (Activity) this.weakActivity.get();
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            function12.invoke("Cannot hide native splash screen on activity that is already destroyed (application is already closed).");
        } else {
            new Handler(activity.getMainLooper()).post(new SplashScreenViewController$$ExternalSyntheticLambda0(this, function1));
        }
    }

    /* access modifiers changed from: private */
    public static final void hideSplashScreen$lambda$1(SplashScreenViewController splashScreenViewController, Function1 function1) {
        Intrinsics.checkNotNullParameter(splashScreenViewController, "this$0");
        Intrinsics.checkNotNullParameter(function1, "$successCallback");
        splashScreenViewController.contentView.removeView(splashScreenViewController.splashScreenView);
        splashScreenViewController.autoHideEnabled = true;
        splashScreenViewController.splashScreenShown = false;
        function1.invoke(true);
    }

    private final void searchForRootView() {
        if (this.rootView == null) {
            ViewGroup findRootView = findRootView(this.contentView);
            if (findRootView != null) {
                handleRootView(findRootView);
            } else {
                this.handler.postDelayed(new SplashScreenViewController$$ExternalSyntheticLambda2(this), 20);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void searchForRootView$lambda$3(SplashScreenViewController splashScreenViewController) {
        Intrinsics.checkNotNullParameter(splashScreenViewController, "this$0");
        splashScreenViewController.searchForRootView();
    }

    private final ViewGroup findRootView(View view) {
        if (this.rootViewClass.isInstance(view)) {
            Intrinsics.checkNotNull(view, "null cannot be cast to non-null type android.view.ViewGroup");
            return (ViewGroup) view;
        } else if (Intrinsics.areEqual((Object) view, (Object) this.splashScreenView) || !(view instanceof ViewGroup)) {
            return null;
        } else {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                Intrinsics.checkNotNullExpressionValue(childAt, "getChildAt(...)");
                ViewGroup findRootView = findRootView(childAt);
                if (findRootView != null) {
                    return findRootView;
                }
            }
            return null;
        }
    }

    private final void handleRootView(ViewGroup viewGroup) {
        this.rootView = viewGroup;
        if ((viewGroup != null ? viewGroup.getChildCount() : 0) > 0 && this.autoHideEnabled) {
            hideSplashScreen$default(this, (Function1) null, (Function1) null, 3, (Object) null);
        }
        viewGroup.setOnHierarchyChangeListener(new SplashScreenViewController$handleRootView$1(this));
    }
}

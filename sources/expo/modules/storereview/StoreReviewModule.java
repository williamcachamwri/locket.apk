package expo.modules.storereview;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.tracing.Trace;
import com.google.android.gms.tasks.Task;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import expo.modules.core.interfaces.ActivityProvider;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.exception.Exceptions;
import expo.modules.kotlin.functions.AsyncFunction;
import expo.modules.kotlin.functions.AsyncFunctionComponent;
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.objects.ObjectDefinitionBuilder;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.LazyKType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\b8BX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lexpo/modules/storereview/StoreReviewModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "currentActivity", "Landroid/app/Activity;", "getCurrentActivity", "()Landroid/app/Activity;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "isPlayStoreInstalled", "", "requestReview", "", "promise", "Lexpo/modules/kotlin/Promise;", "expo-store-review_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: StoreReviewModule.kt */
public final class StoreReviewModule extends Module {
    private final Context getContext() {
        Context reactContext = getAppContext().getReactContext();
        if (reactContext != null) {
            return reactContext;
        }
        throw new Exceptions.ReactContextLost();
    }

    private final Activity getCurrentActivity() {
        ActivityProvider activityProvider = getAppContext().getActivityProvider();
        Activity currentActivity = activityProvider != null ? activityProvider.getCurrentActivity() : null;
        if (currentActivity != null) {
            return currentActivity;
        }
        throw new MissingCurrentActivityException();
    }

    public ModuleDefinitionData definition() {
        AsyncFunction asyncFunction;
        Module module = this;
        Trace.beginSection("[ExpoModulesCore] " + (module.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(module);
            moduleDefinitionBuilder.Name("ExpoStoreReview");
            AsyncFunctionComponent asyncFunctionComponent = new AsyncFunctionComponent("isAvailableAsync", new AnyType[0], new StoreReviewModule$definition$lambda$2$$inlined$AsyncFunctionWithoutArgs$1(this));
            moduleDefinitionBuilder.getAsyncFunctions().put("isAvailableAsync", asyncFunctionComponent);
            AsyncFunction asyncFunction2 = asyncFunctionComponent;
            ObjectDefinitionBuilder objectDefinitionBuilder = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction = new AsyncFunctionWithPromiseComponent("requestReview", new AnyType[0], new StoreReviewModule$definition$lambda$2$$inlined$AsyncFunction$1(this));
            } else {
                asyncFunction = new AsyncFunctionComponent("requestReview", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, StoreReviewModule$definition$lambda$2$$inlined$AsyncFunction$2.INSTANCE))}, new StoreReviewModule$definition$lambda$2$$inlined$AsyncFunction$3(this));
            }
            objectDefinitionBuilder.getAsyncFunctions().put("requestReview", asyncFunction);
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    /* access modifiers changed from: private */
    public final void requestReview(Promise promise) {
        ReviewManager create = ReviewManagerFactory.create(getContext());
        Intrinsics.checkNotNullExpressionValue(create, "create(...)");
        Task<ReviewInfo> requestReviewFlow = create.requestReviewFlow();
        Intrinsics.checkNotNullExpressionValue(requestReviewFlow, "requestReviewFlow(...)");
        requestReviewFlow.addOnCompleteListener(new StoreReviewModule$$ExternalSyntheticLambda0(promise, create, this));
    }

    /* access modifiers changed from: private */
    public static final void requestReview$lambda$5(Promise promise, ReviewManager reviewManager, StoreReviewModule storeReviewModule, Task task) {
        Task<Void> task2;
        Intrinsics.checkNotNullParameter(promise, "$promise");
        Intrinsics.checkNotNullParameter(reviewManager, "$manager");
        Intrinsics.checkNotNullParameter(storeReviewModule, "this$0");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            ReviewInfo reviewInfo = (ReviewInfo) task.getResult();
            if (reviewInfo != null) {
                Task<Void> launchReviewFlow = reviewManager.launchReviewFlow(storeReviewModule.getCurrentActivity(), reviewInfo);
                Intrinsics.checkNotNullExpressionValue(launchReviewFlow, "launchReviewFlow(...)");
                task2 = launchReviewFlow.addOnCompleteListener(new StoreReviewModule$$ExternalSyntheticLambda1(promise));
            } else {
                task2 = null;
            }
            if (task2 == null) {
                promise.reject(new RMTaskException());
                return;
            }
            return;
        }
        promise.reject(new RMUnsuccessfulTaskException());
    }

    /* access modifiers changed from: private */
    public static final void requestReview$lambda$5$lambda$4$lambda$3(Promise promise, Task task) {
        Intrinsics.checkNotNullParameter(promise, "$promise");
        Intrinsics.checkNotNullParameter(task, "result");
        if (task.isSuccessful()) {
            promise.resolve((Object) null);
        } else {
            promise.reject(new RMTaskException());
        }
    }

    /* access modifiers changed from: private */
    public final boolean isPlayStoreInstalled() {
        try {
            getContext().getPackageManager().getPackageInfo("com.android.vending", 0);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }
}

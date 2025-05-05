package expo.modules.kotlin.activityresult;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004JR\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\b0\u0006\"\b\b\u0000\u0010\u0007*\u00020\t\"\u0004\b\u0001\u0010\b2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\b0\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\b0\rH@¢\u0006\u0002\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lexpo/modules/kotlin/activityresult/DefaultAppContextActivityResultCaller;", "Lexpo/modules/kotlin/activityresult/AppContextActivityResultCaller;", "activityResultsManager", "Lexpo/modules/kotlin/activityresult/ActivityResultsManager;", "(Lexpo/modules/kotlin/activityresult/ActivityResultsManager;)V", "registerForActivityResult", "Lexpo/modules/kotlin/activityresult/AppContextActivityResultLauncher;", "I", "O", "Ljava/io/Serializable;", "contract", "Lexpo/modules/kotlin/activityresult/AppContextActivityResultContract;", "fallbackCallback", "Lexpo/modules/kotlin/activityresult/AppContextActivityResultFallbackCallback;", "(Lexpo/modules/kotlin/activityresult/AppContextActivityResultContract;Lexpo/modules/kotlin/activityresult/AppContextActivityResultFallbackCallback;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AppContextActivityResultCaller.kt */
public final class DefaultAppContextActivityResultCaller implements AppContextActivityResultCaller {
    private final ActivityResultsManager activityResultsManager;

    public DefaultAppContextActivityResultCaller(ActivityResultsManager activityResultsManager2) {
        Intrinsics.checkNotNullParameter(activityResultsManager2, "activityResultsManager");
        this.activityResultsManager = activityResultsManager2;
    }

    public <I extends Serializable, O> Object registerForActivityResult(AppContextActivityResultContract<I, O> appContextActivityResultContract, AppContextActivityResultFallbackCallback<I, O> appContextActivityResultFallbackCallback, Continuation<? super AppContextActivityResultLauncher<I, O>> continuation) {
        return this.activityResultsManager.registerForActivityResult(appContextActivityResultContract, appContextActivityResultFallbackCallback, continuation);
    }
}

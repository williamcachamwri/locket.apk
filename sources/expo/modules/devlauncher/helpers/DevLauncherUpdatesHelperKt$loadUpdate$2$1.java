package expo.modules.devlauncher.helpers;

import expo.modules.updatesinterface.UpdatesInterface;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(d1 = {"\u00003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J \u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0016J\u0012\u0010\u000f\u001a\u00020\u00032\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016¨\u0006\u0012"}, d2 = {"expo/modules/devlauncher/helpers/DevLauncherUpdatesHelperKt$loadUpdate$2$1", "Lexpo/modules/updatesinterface/UpdatesInterface$UpdateCallback;", "onFailure", "", "e", "Ljava/lang/Exception;", "onManifestLoaded", "", "manifest", "Lorg/json/JSONObject;", "onProgress", "successfulAssetCount", "", "failedAssetCount", "totalAssetCount", "onSuccess", "update", "Lexpo/modules/updatesinterface/UpdatesInterface$Update;", "expo-dev-launcher_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevLauncherUpdatesHelper.kt */
public final class DevLauncherUpdatesHelperKt$loadUpdate$2$1 implements UpdatesInterface.UpdateCallback {
    final /* synthetic */ Continuation<UpdatesInterface.Update> $cont;
    final /* synthetic */ Function1<JSONObject, Boolean> $shouldContinue;

    public void onProgress(int i, int i2, int i3) {
    }

    DevLauncherUpdatesHelperKt$loadUpdate$2$1(Continuation<? super UpdatesInterface.Update> continuation, Function1<? super JSONObject, Boolean> function1) {
        this.$cont = continuation;
        this.$shouldContinue = function1;
    }

    public void onSuccess(UpdatesInterface.Update update) {
        if (update != null) {
            Continuation<UpdatesInterface.Update> continuation = this.$cont;
            Result.Companion companion = Result.Companion;
            continuation.resumeWith(Result.m2444constructorimpl(update));
        }
    }

    public void onFailure(Exception exc) {
        Continuation<UpdatesInterface.Update> continuation = this.$cont;
        Result.Companion companion = Result.Companion;
        if (exc == null) {
            exc = new Exception("There was an unexpected error loading the update.");
        }
        continuation.resumeWith(Result.m2444constructorimpl(ResultKt.createFailure(exc)));
    }

    public boolean onManifestLoaded(JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(jSONObject, "manifest");
        if (this.$shouldContinue.invoke(jSONObject).booleanValue()) {
            return true;
        }
        Continuation<UpdatesInterface.Update> continuation = this.$cont;
        Result.Companion companion = Result.Companion;
        continuation.resumeWith(Result.m2444constructorimpl(new DevLauncherUpdatesHelperKt$loadUpdate$2$1$onManifestLoaded$1(jSONObject)));
        return false;
    }
}

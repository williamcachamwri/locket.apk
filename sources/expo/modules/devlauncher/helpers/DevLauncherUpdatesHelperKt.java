package expo.modules.devlauncher.helpers;

import android.content.Context;
import android.net.Uri;
import com.facebook.react.uimanager.ViewProps;
import expo.modules.updatesinterface.UpdatesInterface;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.SafeContinuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a<\u0010\u0000\u001a\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001j\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u0002\u001aa\u0010\t\u001a\u00020\n*\u00020\u000b2\"\u0010\f\u001a\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001j\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\u00042\u0006\u0010\r\u001a\u00020\u000e2!\u0010\u000f\u001a\u001d\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00150\u0010H@¢\u0006\u0002\u0010\u0016¨\u0006\u0017"}, d2 = {"createUpdatesConfigurationWithUrl", "Ljava/util/HashMap;", "", "", "Lkotlin/collections/HashMap;", "url", "Landroid/net/Uri;", "projectUrl", "installationID", "loadUpdate", "Lexpo/modules/updatesinterface/UpdatesInterface$Update;", "Lexpo/modules/updatesinterface/UpdatesInterface;", "configuration", "context", "Landroid/content/Context;", "shouldContinue", "Lkotlin/Function1;", "Lorg/json/JSONObject;", "Lkotlin/ParameterName;", "name", "manifest", "", "(Lexpo/modules/updatesinterface/UpdatesInterface;Ljava/util/HashMap;Landroid/content/Context;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expo-dev-launcher_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevLauncherUpdatesHelper.kt */
public final class DevLauncherUpdatesHelperKt {
    public static final Object loadUpdate(UpdatesInterface updatesInterface, HashMap<String, Object> hashMap, Context context, Function1<? super JSONObject, Boolean> function1, Continuation<? super UpdatesInterface.Update> continuation) {
        SafeContinuation safeContinuation = new SafeContinuation(IntrinsicsKt.intercepted(continuation));
        updatesInterface.fetchUpdateWithConfiguration(hashMap, context, new DevLauncherUpdatesHelperKt$loadUpdate$2$1(safeContinuation, function1));
        Object orThrow = safeContinuation.getOrThrow();
        if (orThrow == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return orThrow;
    }

    public static final HashMap<String, Object> createUpdatesConfigurationWithUrl(Uri uri, Uri uri2, String str) {
        Intrinsics.checkNotNullParameter(uri, "url");
        Intrinsics.checkNotNullParameter(uri2, "projectUrl");
        HashMap hashMapOf = MapsKt.hashMapOf(TuplesKt.to("Expo-Updates-Environment", "DEVELOPMENT"));
        if (str != null) {
            hashMapOf.put("Expo-Dev-Client-ID", str);
        }
        return MapsKt.hashMapOf(TuplesKt.to("updateUrl", uri), TuplesKt.to("scopeKey", uri2.toString()), TuplesKt.to("hasEmbeddedUpdate", false), TuplesKt.to("launchWaitMs", 60000), TuplesKt.to("checkOnLaunch", "ALWAYS"), TuplesKt.to(ViewProps.ENABLED, true), TuplesKt.to("requestHeaders", hashMapOf), TuplesKt.to("expectsSignedManifest", false));
    }
}

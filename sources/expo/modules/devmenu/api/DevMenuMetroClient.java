package expo.modules.devmenu.api;

import android.net.Uri;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import expo.modules.devmenu.helpers.DevMenuOkHttpExtensionKt$await$2$1;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH@¢\u0006\u0002\u0010\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lexpo/modules/devmenu/api/DevMenuMetroClient;", "", "()V", "httpClient", "Lokhttp3/OkHttpClient;", "openJSInspector", "", "metroHost", "", "applicationId", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expo-dev-menu_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuMetroClient.kt */
public final class DevMenuMetroClient {
    private final OkHttpClient httpClient = new OkHttpClient();

    public final Object openJSInspector(String str, String str2, Continuation<? super Unit> continuation) {
        Uri build = Uri.parse(str + "/_expo/debugger").buildUpon().appendQueryParameter("applicationId", str2).build();
        Request.Builder put = new Request.Builder().put(RequestBody.Companion.create("", (MediaType) null));
        String uri = build.toString();
        Intrinsics.checkNotNullExpressionValue(uri, "toString(...)");
        Request build2 = put.url(uri).build();
        OkHttpClient okHttpClient = this.httpClient;
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        FirebasePerfOkHttpClient.enqueue(okHttpClient.newCall(build2), new DevMenuOkHttpExtensionKt$await$2$1(cancellableContinuationImpl));
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return result;
        }
        return Unit.INSTANCE;
    }
}

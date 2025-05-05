package expo.modules.devmenu.helpers;

import android.net.Uri;
import com.google.android.gms.actions.SearchIntents;
import com.google.common.net.HttpHeaders;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CancellableContinuationImpl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a&\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0016\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u001a.\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00062\u0016\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u001a\u001a\u0010\t\u001a\u00020\n*\u00020\u00012\u0006\u0010\u000b\u001a\u00020\fHH¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"fetch", "Lokhttp3/Request;", "url", "Landroid/net/Uri;", "authHeader", "Lkotlin/Pair;", "", "fetchGraphQL", "query", "await", "Lokhttp3/Response;", "okHttpClient", "Lokhttp3/OkHttpClient;", "(Lokhttp3/Request;Lokhttp3/OkHttpClient;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expo-dev-menu_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuOkHttpExtension.kt */
public final class DevMenuOkHttpExtensionKt {
    public static /* synthetic */ Request fetch$default(Uri uri, Pair pair, int i, Object obj) {
        if ((i & 2) != 0) {
            pair = null;
        }
        return fetch(uri, pair);
    }

    public static final Request fetch(Uri uri, Pair<String, String> pair) {
        Intrinsics.checkNotNullParameter(uri, "url");
        Request.Builder method = new Request.Builder().method("GET", (RequestBody) null);
        String uri2 = uri.toString();
        Intrinsics.checkNotNullExpressionValue(uri2, "toString(...)");
        Request.Builder url = method.url(uri2);
        if (pair != null) {
            url.addHeader(pair.getFirst(), pair.getSecond());
        }
        return url.build();
    }

    public static /* synthetic */ Request fetchGraphQL$default(Uri uri, String str, Pair pair, int i, Object obj) {
        if ((i & 4) != 0) {
            pair = null;
        }
        return fetchGraphQL(uri, str, pair);
    }

    public static final Request fetchGraphQL(Uri uri, String str, Pair<String, String> pair) {
        Intrinsics.checkNotNullParameter(uri, "url");
        Intrinsics.checkNotNullParameter(str, SearchIntents.EXTRA_QUERY);
        Request.Builder builder = new Request.Builder();
        String uri2 = uri.toString();
        Intrinsics.checkNotNullExpressionValue(uri2, "toString(...)");
        Request.Builder addHeader = builder.url(uri2).method("POST", RequestBody.Companion.create(MediaType.Companion.m1991deprecated_parse("application/json"), "{\"query\": \"" + StringsKt.replace$default(StringsKt.replace$default(StringsKt.trimIndent(str), "\n", "\\n", false, 4, (Object) null), "\"", "\\\"", false, 4, (Object) null) + "\"}")).addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        if (pair != null) {
            addHeader.addHeader(pair.getFirst(), pair.getSecond());
        }
        return addHeader.build();
    }

    private static final Object await$$forInline(Request request, OkHttpClient okHttpClient, Continuation<? super Response> continuation) {
        InlineMarker.mark(0);
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        FirebasePerfOkHttpClient.enqueue(okHttpClient.newCall(request), new DevMenuOkHttpExtensionKt$await$2$1(cancellableContinuationImpl));
        Unit unit = Unit.INSTANCE;
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        InlineMarker.mark(1);
        return result;
    }

    public static final Object await(Request request, OkHttpClient okHttpClient, Continuation<? super Response> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        FirebasePerfOkHttpClient.enqueue(okHttpClient.newCall(request), new DevMenuOkHttpExtensionKt$await$2$1(cancellableContinuationImpl));
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}

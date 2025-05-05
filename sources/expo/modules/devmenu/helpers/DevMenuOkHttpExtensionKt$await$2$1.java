package expo.modules.devmenu.helpers;

import androidx.core.app.NotificationCompat;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"expo/modules/devmenu/helpers/DevMenuOkHttpExtensionKt$await$2$1", "Lokhttp3/Callback;", "onFailure", "", "call", "Lokhttp3/Call;", "e", "Ljava/io/IOException;", "onResponse", "response", "Lokhttp3/Response;", "expo-dev-menu_release"}, k = 1, mv = {1, 9, 0}, xi = 176)
/* compiled from: DevMenuOkHttpExtension.kt */
public final class DevMenuOkHttpExtensionKt$await$2$1 implements Callback {
    final /* synthetic */ CancellableContinuation<Response> $callback;

    public DevMenuOkHttpExtensionKt$await$2$1(CancellableContinuation<? super Response> cancellableContinuation) {
        this.$callback = cancellableContinuation;
    }

    public void onResponse(Call call, Response response) {
        Intrinsics.checkNotNullParameter(call, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkNotNullParameter(response, io.sentry.protocol.Response.TYPE);
        Result.Companion companion = Result.Companion;
        this.$callback.resumeWith(Result.m2444constructorimpl(response));
    }

    public void onFailure(Call call, IOException iOException) {
        Intrinsics.checkNotNullParameter(call, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkNotNullParameter(iOException, "e");
        if (!this.$callback.isCancelled()) {
            Result.Companion companion = Result.Companion;
            this.$callback.resumeWith(Result.m2444constructorimpl(ResultKt.createFailure(iOException)));
        }
    }
}

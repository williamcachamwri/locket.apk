package expo.modules.filesystem;

import android.net.Uri;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import expo.modules.kotlin.Promise;
import io.sentry.android.core.SentryLogcatAdapter;
import java.io.File;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.Okio;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"expo/modules/filesystem/FileSystemModule$definition$1$19$4", "Lokhttp3/Callback;", "onFailure", "", "call", "Lokhttp3/Call;", "e", "Ljava/io/IOException;", "onResponse", "response", "Lokhttp3/Response;", "expo-file-system_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FileSystemModule.kt */
public final class FileSystemModule$definition$1$19$4 implements Callback {
    final /* synthetic */ DownloadOptions $options;
    final /* synthetic */ Promise $promise;
    final /* synthetic */ Uri $uri;
    final /* synthetic */ FileSystemModule this$0;

    FileSystemModule$definition$1$19$4(Promise promise, FileSystemModule fileSystemModule, Uri uri, DownloadOptions downloadOptions) {
        this.$promise = promise;
        this.this$0 = fileSystemModule;
        this.$uri = uri;
        this.$options = downloadOptions;
    }

    public void onFailure(Call call, IOException iOException) {
        Intrinsics.checkNotNullParameter(call, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkNotNullParameter(iOException, "e");
        SentryLogcatAdapter.e(FileSystemModuleKt.TAG, String.valueOf(iOException.getMessage()));
        Promise promise = this.$promise;
        String access$getTAG$p = FileSystemModuleKt.TAG;
        Intrinsics.checkNotNullExpressionValue(access$getTAG$p, "access$getTAG$p(...)");
        promise.reject(access$getTAG$p, iOException.getMessage(), iOException);
    }

    public void onResponse(Call call, Response response) throws IOException {
        Intrinsics.checkNotNullParameter(call, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkNotNullParameter(response, io.sentry.protocol.Response.TYPE);
        FileSystemModule fileSystemModule = this.this$0;
        Uri uri = this.$uri;
        Intrinsics.checkNotNullExpressionValue(uri, "$uri");
        File access$toFile = fileSystemModule.toFile(uri);
        access$toFile.delete();
        BufferedSink buffer = Okio.buffer(Okio__JvmOkioKt.sink$default(access$toFile, false, 1, (Object) null));
        ResponseBody body = response.body();
        Intrinsics.checkNotNull(body);
        buffer.writeAll(body.source());
        buffer.close();
        Bundle bundle = new Bundle();
        FileSystemModule fileSystemModule2 = this.this$0;
        DownloadOptions downloadOptions = this.$options;
        bundle.putString("uri", Uri.fromFile(access$toFile).toString());
        bundle.putInt("status", response.code());
        bundle.putBundle("headers", fileSystemModule2.translateHeaders(response.headers()));
        if (downloadOptions.getMd5()) {
            bundle.putString("md5", fileSystemModule2.md5(access$toFile));
        }
        response.close();
        this.$promise.resolve(bundle);
    }
}

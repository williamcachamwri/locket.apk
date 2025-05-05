package expo.modules.filesystem;

import android.net.Uri;
import android.os.Bundle;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import expo.modules.filesystem.FileSystemModule;
import expo.modules.kotlin.Promise;
import io.sentry.android.core.SentryLogcatAdapter;
import io.sentry.instrumentation.file.SentryFileOutputStream;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0001\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.filesystem.FileSystemModule$downloadResumableTask$2", f = "FileSystemModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: FileSystemModule.kt */
final class FileSystemModule$downloadResumableTask$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
    final /* synthetic */ FileSystemModule.DownloadResumableTaskParams $params;
    int label;
    final /* synthetic */ FileSystemModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FileSystemModule$downloadResumableTask$2(FileSystemModule.DownloadResumableTaskParams downloadResumableTaskParams, FileSystemModule fileSystemModule, Continuation<? super FileSystemModule$downloadResumableTask$2> continuation) {
        super(2, continuation);
        this.$params = downloadResumableTaskParams;
        this.this$0 = fileSystemModule;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FileSystemModule$downloadResumableTask$2(this.$params, this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((FileSystemModule$downloadResumableTask$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            FileSystemModule.DownloadResumableTaskParams downloadResumableTaskParams = this.$params;
            DownloadOptions component1 = downloadResumableTaskParams.component1();
            Call component2 = downloadResumableTaskParams.component2();
            File component3 = downloadResumableTaskParams.component3();
            boolean component4 = downloadResumableTaskParams.component4();
            Promise component5 = downloadResumableTaskParams.component5();
            try {
                Response execute = FirebasePerfOkHttpClient.execute(component2);
                ResponseBody body = execute.body();
                Intrinsics.checkNotNull(body);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(body.byteStream());
                boolean z = component4;
                FileOutputStream create = SentryFileOutputStream.Factory.create(new FileOutputStream(component3, z), component3, z);
                byte[] bArr = new byte[1024];
                Ref.IntRef intRef = new Ref.IntRef();
                while (true) {
                    int read = bufferedInputStream.read(bArr);
                    intRef.element = read;
                    if (read == -1) {
                        break;
                    }
                    create.write(bArr, 0, intRef.element);
                }
                Bundle bundle = new Bundle();
                FileSystemModule fileSystemModule = this.this$0;
                bundle.putString("uri", Uri.fromFile(component3).toString());
                bundle.putInt("status", execute.code());
                bundle.putBundle("headers", fileSystemModule.translateHeaders(execute.headers()));
                Boolean boxBoolean = Boxing.boxBoolean(component1.getMd5());
                if (!boxBoolean.booleanValue()) {
                    boxBoolean = null;
                }
                if (boxBoolean != null) {
                    boxBoolean.booleanValue();
                    bundle.putString("md5", fileSystemModule.md5(component3));
                }
                execute.close();
                component5.resolve(bundle);
            } catch (Exception e) {
                if (component2.isCanceled()) {
                    component5.resolve((Object) null);
                    return null;
                }
                String message = e.getMessage();
                if (message != null) {
                    Boxing.boxInt(SentryLogcatAdapter.e(FileSystemModuleKt.TAG, message));
                }
                String access$getTAG$p = FileSystemModuleKt.TAG;
                Intrinsics.checkNotNullExpressionValue(access$getTAG$p, "access$getTAG$p(...)");
                component5.reject(access$getTAG$p, e.getMessage(), e);
            }
            return null;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}

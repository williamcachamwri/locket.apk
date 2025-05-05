package expo.modules.filesystem;

import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import okhttp3.OkHttpClient;
import okhttp3.Request;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\u0006\b\u0002\u0010\u0004\u0018\u0001\"\u0006\b\u0003\u0010\u0005\u0018\u0001\"\u0006\b\u0004\u0010\u0006\u0018\u00012\u0010\u0010\u0007\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\bH\n¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"<anonymous>", "", "R", "P0", "P1", "P2", "P3", "it", "", "invoke", "([Ljava/lang/Object;)Ljava/lang/Object;", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$31"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$68 extends Lambda implements Function1<Object[], Object> {
    final /* synthetic */ ModuleDefinitionBuilder $this_ModuleDefinition$inlined;
    final /* synthetic */ FileSystemModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$68(FileSystemModule fileSystemModule, ModuleDefinitionBuilder moduleDefinitionBuilder) {
        super(1);
        this.this$0 = fileSystemModule;
        this.$this_ModuleDefinition$inlined = moduleDefinitionBuilder;
    }

    public final Object invoke(Object[] objArr) {
        Unit unit;
        Intrinsics.checkNotNullParameter(objArr, "it");
        String str = objArr[0];
        if (str != null) {
            String str2 = str;
            String str3 = objArr[1];
            if (str3 != null) {
                String str4 = str3;
                FileSystemUploadOptions fileSystemUploadOptions = objArr[2];
                if (fileSystemUploadOptions != null) {
                    FileSystemUploadOptions fileSystemUploadOptions2 = fileSystemUploadOptions;
                    Promise promise = objArr[3];
                    if (promise != null) {
                        Promise promise2 = promise;
                        Request access$createUploadRequest = this.this$0.createUploadRequest(str2, str4, fileSystemUploadOptions2, FileSystemModule$definition$1$17$request$1.INSTANCE);
                        OkHttpClient access$getOkHttpClient = this.this$0.getOkHttpClient();
                        if (access$getOkHttpClient != null) {
                            FirebasePerfOkHttpClient.enqueue(access$getOkHttpClient.newCall(access$createUploadRequest), new FileSystemModule$definition$1$17$1$1(promise2, this.this$0));
                            unit = Unit.INSTANCE;
                        } else {
                            unit = null;
                        }
                        if (unit == null) {
                            promise2.reject(new FileSystemOkHttpNullException());
                        }
                        return Unit.INSTANCE;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type expo.modules.kotlin.Promise");
                }
                throw new NullPointerException("null cannot be cast to non-null type expo.modules.filesystem.FileSystemUploadOptions");
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
    }
}

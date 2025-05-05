package expo.modules.filesystem;

import android.net.Uri;
import androidx.documentfile.provider.DocumentFile;
import com.facebook.react.bridge.BaseJavaModule;
import expo.modules.interfaces.filesystem.Permission;
import expo.modules.kotlin.Promise;
import java.io.File;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.apache.commons.io.FileUtils;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\u0006\b\u0002\u0010\u0004\u0018\u00012\u0010\u0010\u0005\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\n¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"<anonymous>", "", "R", "P0", "P1", "args", "", "", "promise", "Lexpo/modules/kotlin/Promise;", "invoke", "([Ljava/lang/Object;Lexpo/modules/kotlin/Promise;)V", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$10"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$19 extends Lambda implements Function2<Object[], Promise, Unit> {
    final /* synthetic */ FileSystemModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$19(FileSystemModule fileSystemModule) {
        super(2);
        this.this$0 = fileSystemModule;
    }

    public final void invoke(Object[] objArr, Promise promise) {
        Intrinsics.checkNotNullParameter(objArr, "args");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        String str = objArr[0];
        if (str != null) {
            DeletingOptions deletingOptions = (DeletingOptions) promise;
            Uri parse = Uri.parse(FileSystemModuleKt.slashifyFilePath(str));
            Uri withAppendedPath = Uri.withAppendedPath(parse, "..");
            FileSystemModule fileSystemModule = this.this$0;
            Intrinsics.checkNotNull(withAppendedPath);
            fileSystemModule.ensurePermission(withAppendedPath, Permission.WRITE, "Location '" + parse + "' isn't deletable.");
            if (Intrinsics.areEqual((Object) parse.getScheme(), (Object) "file")) {
                FileSystemModule fileSystemModule2 = this.this$0;
                Intrinsics.checkNotNull(parse);
                File access$toFile = fileSystemModule2.toFile(parse);
                if (access$toFile.exists()) {
                    FileUtils.forceDelete(access$toFile);
                } else if (!deletingOptions.getIdempotent()) {
                    throw new FileSystemFileNotFoundException(parse);
                }
            } else {
                FileSystemModule fileSystemModule3 = this.this$0;
                Intrinsics.checkNotNull(parse);
                if (fileSystemModule3.isSAFUri(parse)) {
                    DocumentFile access$getNearestSAFFile = this.this$0.getNearestSAFFile(parse);
                    if (access$getNearestSAFFile != null && access$getNearestSAFFile.exists()) {
                        access$getNearestSAFFile.delete();
                    } else if (!deletingOptions.getIdempotent()) {
                        throw new FileSystemFileNotFoundException(parse);
                    }
                } else {
                    throw new IOException("Unsupported scheme for location '" + parse + "'.");
                }
            }
        } else {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Object[]) obj, (Promise) obj2);
        return Unit.INSTANCE;
    }
}

package expo.modules.filesystem;

import android.net.Uri;
import androidx.documentfile.provider.DocumentFile;
import expo.modules.interfaces.filesystem.Permission;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\u0006\b\u0002\u0010\u0004\u0018\u0001\"\u0006\b\u0003\u0010\u0005\u0018\u00012\u0010\u0010\u0006\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0007H\n¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"<anonymous>", "", "R", "P0", "P1", "P2", "it", "", "invoke", "([Ljava/lang/Object;)Ljava/lang/Object;", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$21"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$54 extends Lambda implements Function1<Object[], Object> {
    final /* synthetic */ FileSystemModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$54(FileSystemModule fileSystemModule) {
        super(1);
        this.this$0 = fileSystemModule;
    }

    public final Object invoke(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "it");
        String str = objArr[0];
        if (str != null) {
            String str2 = str;
            String str3 = objArr[1];
            if (str3 != null) {
                String str4 = str3;
                String str5 = objArr[2];
                if (str5 != null) {
                    String str6 = str5;
                    Uri parse = Uri.parse(FileSystemModuleKt.slashifyFilePath(str2));
                    FileSystemModule fileSystemModule = this.this$0;
                    Intrinsics.checkNotNull(parse);
                    fileSystemModule.ensurePermission(parse, Permission.WRITE);
                    if (this.this$0.isSAFUri(parse)) {
                        DocumentFile access$getNearestSAFFile = this.this$0.getNearestSAFFile(parse);
                        if (access$getNearestSAFFile == null || !access$getNearestSAFFile.isDirectory()) {
                            throw new FileSystemCannotCreateFileException(parse);
                        }
                        DocumentFile createFile = access$getNearestSAFFile.createFile(str6, str4);
                        if (createFile != null) {
                            Intrinsics.checkNotNull(createFile);
                            return createFile.getUri().toString();
                        }
                        throw new FileSystemCannotCreateFileException((Uri) null);
                    }
                    throw new IOException("The URI '" + parse + "' is not a Storage Access Framework URI.");
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
    }
}

package expo.modules.filesystem;

import android.net.Uri;
import expo.modules.interfaces.filesystem.Permission;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u00012\u0010\u0010\u0004\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", "R", "P0", "it", "", "invoke", "([Ljava/lang/Object;)Ljava/lang/Object;", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$7"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$36 extends Lambda implements Function1<Object[], Object> {
    final /* synthetic */ FileSystemModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$36(FileSystemModule fileSystemModule) {
        super(1);
        this.this$0 = fileSystemModule;
    }

    public final Object invoke(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "it");
        Uri parse = Uri.parse(FileSystemModuleKt.slashifyFilePath(objArr[0]));
        FileSystemModule fileSystemModule = this.this$0;
        Intrinsics.checkNotNull(parse);
        fileSystemModule.ensurePermission(parse, Permission.READ);
        if (Intrinsics.areEqual((Object) parse.getScheme(), (Object) "file")) {
            File[] listFiles = this.this$0.toFile(parse).listFiles();
            if (listFiles != null) {
                Collection arrayList = new ArrayList(listFiles.length);
                int length = listFiles.length;
                for (int i = 0; i < length; i++) {
                    File file = listFiles[i];
                    arrayList.add(file != null ? file.getName() : null);
                }
                return (List) arrayList;
            }
            throw new FileSystemCannotReadDirectoryException(parse);
        } else if (this.this$0.isSAFUri(parse)) {
            throw new FileSystemUnsupportedSchemeException();
        } else {
            throw new IOException("Unsupported scheme for location '" + parse + "'.");
        }
    }
}

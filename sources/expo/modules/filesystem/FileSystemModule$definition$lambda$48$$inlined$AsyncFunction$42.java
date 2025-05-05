package expo.modules.filesystem;

import android.net.Uri;
import androidx.documentfile.provider.DocumentFile;
import expo.modules.interfaces.filesystem.Permission;
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
public final class FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$42 extends Lambda implements Function1<Object[], Object> {
    final /* synthetic */ FileSystemModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$42(FileSystemModule fileSystemModule) {
        super(1);
        this.this$0 = fileSystemModule;
    }

    public final Object invoke(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "it");
        String str = objArr[0];
        if (str != null) {
            Uri parse = Uri.parse(FileSystemModuleKt.slashifyFilePath(str));
            FileSystemModule fileSystemModule = this.this$0;
            Intrinsics.checkNotNull(parse);
            fileSystemModule.ensurePermission(parse, Permission.READ);
            if (this.this$0.isSAFUri(parse)) {
                DocumentFile fromTreeUri = DocumentFile.fromTreeUri(this.this$0.getContext(), parse);
                if (fromTreeUri == null || !fromTreeUri.exists() || !fromTreeUri.isDirectory()) {
                    throw new FileSystemCannotReadDirectoryException(parse);
                }
                DocumentFile[] listFiles = fromTreeUri.listFiles();
                Intrinsics.checkNotNullExpressionValue(listFiles, "listFiles(...)");
                Collection arrayList = new ArrayList(listFiles.length);
                for (DocumentFile uri : listFiles) {
                    arrayList.add(uri.getUri().toString());
                }
                return (List) arrayList;
            }
            throw new IOException("The URI '" + parse + "' is not a Storage Access Framework URI. Try using FileSystem.readDirectoryAsync instead.");
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
    }
}

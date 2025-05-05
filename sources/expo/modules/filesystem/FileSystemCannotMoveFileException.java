package expo.modules.filesystem;

import android.net.Uri;
import expo.modules.kotlin.exception.CodedException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lexpo/modules/filesystem/FileSystemCannotMoveFileException;", "Lexpo/modules/kotlin/exception/CodedException;", "fromUri", "Landroid/net/Uri;", "toUri", "(Landroid/net/Uri;Landroid/net/Uri;)V", "expo-file-system_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FileSystemExceptions.kt */
public final class FileSystemCannotMoveFileException extends CodedException {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FileSystemCannotMoveFileException(Uri uri, Uri uri2) {
        super("File '" + uri + "' could not be moved to '" + uri2 + "'", (Throwable) null, 2, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(uri, "fromUri");
        Intrinsics.checkNotNullParameter(uri2, "toUri");
    }
}

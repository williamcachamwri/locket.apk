package expo.modules.filesystem;

import expo.modules.kotlin.exception.CodedException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lexpo/modules/filesystem/FileSystemUnreadableDirectoryException;", "Lexpo/modules/kotlin/exception/CodedException;", "uri", "", "(Ljava/lang/String;)V", "expo-file-system_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FileSystemExceptions.kt */
public final class FileSystemUnreadableDirectoryException extends CodedException {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FileSystemUnreadableDirectoryException(String str) {
        super("No readable files with the uri '" + str + "'. Please use other uri", (Throwable) null, 2, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "uri");
    }
}

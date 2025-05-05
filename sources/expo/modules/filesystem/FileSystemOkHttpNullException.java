package expo.modules.filesystem;

import expo.modules.kotlin.exception.CodedException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lexpo/modules/filesystem/FileSystemOkHttpNullException;", "Lexpo/modules/kotlin/exception/CodedException;", "()V", "expo-file-system_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FileSystemExceptions.kt */
public final class FileSystemOkHttpNullException extends CodedException {
    public FileSystemOkHttpNullException() {
        super("okHttpClient is null", (Throwable) null, 2, (DefaultConstructorMarker) null);
    }
}

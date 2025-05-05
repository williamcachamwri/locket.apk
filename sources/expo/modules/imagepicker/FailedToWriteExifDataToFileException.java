package expo.modules.imagepicker;

import android.net.Uri;
import expo.modules.kotlin.exception.CodedException;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lexpo/modules/imagepicker/FailedToWriteExifDataToFileException;", "Lexpo/modules/kotlin/exception/CodedException;", "file", "Ljava/io/File;", "cause", "", "(Ljava/io/File;Ljava/lang/Throwable;)V", "expo-image-picker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ImagePickerExceptions.kt */
public final class FailedToWriteExifDataToFileException extends CodedException {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FailedToWriteExifDataToFileException(File file, Throwable th) {
        super("Failed to write EXIF data to file '" + Uri.fromFile(file), th);
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(th, "cause");
    }
}

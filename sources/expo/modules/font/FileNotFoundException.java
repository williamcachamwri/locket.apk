package expo.modules.font;

import expo.modules.kotlin.exception.CodedException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lexpo/modules/font/FileNotFoundException;", "Lexpo/modules/kotlin/exception/CodedException;", "uri", "", "(Ljava/lang/String;)V", "expo-font_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FontLoaderModule.kt */
final class FileNotFoundException extends CodedException {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FileNotFoundException(String str) {
        super("File '" + str + "' doesn't exist", (Throwable) null, 2, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "uri");
    }
}

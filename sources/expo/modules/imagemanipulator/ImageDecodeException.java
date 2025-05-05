package expo.modules.imagemanipulator;

import expo.modules.kotlin.exception.CodedException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lexpo/modules/imagemanipulator/ImageDecodeException;", "Lexpo/modules/kotlin/exception/CodedException;", "uri", "", "e", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "expo-image-manipulator_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Exceptions.kt */
public final class ImageDecodeException extends CodedException {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ImageDecodeException(String str, Throwable th) {
        super("Could not get decoded bitmap of " + str, th);
        Intrinsics.checkNotNullParameter(str, "uri");
    }
}

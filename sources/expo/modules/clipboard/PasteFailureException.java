package expo.modules.clipboard;

import expo.modules.kotlin.exception.CodedException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lexpo/modules/clipboard/PasteFailureException;", "Lexpo/modules/kotlin/exception/CodedException;", "cause", "", "kind", "", "(Ljava/lang/Throwable;Ljava/lang/String;)V", "expo-clipboard_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ClipboardExceptions.kt */
public final class PasteFailureException extends CodedException {
    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ PasteFailureException(Throwable th, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(th, (i & 2) != 0 ? "item" : str);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PasteFailureException(Throwable th, String str) {
        super("Failed to get " + str + " from clipboard", th);
        Intrinsics.checkNotNullParameter(str, "kind");
    }
}

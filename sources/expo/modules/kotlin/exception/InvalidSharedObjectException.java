package expo.modules.kotlin.exception;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KType;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lexpo/modules/kotlin/exception/InvalidSharedObjectException;", "Lexpo/modules/kotlin/exception/CodedException;", "sharedType", "Lkotlin/reflect/KType;", "(Lkotlin/reflect/KType;)V", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CodedException.kt */
public final class InvalidSharedObjectException extends CodedException {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public InvalidSharedObjectException(KType kType) {
        super("Cannot convert provided JavaScriptObject to the '" + kType + "', because it doesn't contain valid id", (Throwable) null, 2, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(kType, "sharedType");
    }
}

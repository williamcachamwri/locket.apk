package expo.modules.keepawake;

import expo.modules.kotlin.exception.CodedException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lexpo/modules/keepawake/DeactivateKeepAwakeException;", "Lexpo/modules/kotlin/exception/CodedException;", "()V", "expo-keep-awake_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: KeepAwakeExceptions.kt */
public final class DeactivateKeepAwakeException extends CodedException {
    public DeactivateKeepAwakeException() {
        super("Unable to deactivate keep awake. However, it probably is deactivated already", (Throwable) null, 2, (DefaultConstructorMarker) null);
    }
}

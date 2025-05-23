package expo.modules.brightness;

import expo.modules.kotlin.exception.CodedException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lexpo/modules/brightness/BrightnessPermissionsException;", "Lexpo/modules/kotlin/exception/CodedException;", "()V", "expo-brightness_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Exceptions.kt */
public final class BrightnessPermissionsException extends CodedException {
    public BrightnessPermissionsException() {
        super("WRITE_SETTINGS permission has not been granted", (Throwable) null, 2, (DefaultConstructorMarker) null);
    }
}

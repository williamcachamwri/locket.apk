package expo.modules.intentlauncher.exceptions;

import expo.modules.core.errors.CodedException;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lexpo/modules/intentlauncher/exceptions/ActivityAlreadyStartedException;", "Lexpo/modules/core/errors/CodedException;", "()V", "getCode", "", "expo-intent-launcher_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ActivityAlreadyStartedException.kt */
public final class ActivityAlreadyStartedException extends CodedException {
    public String getCode() {
        return "E_ACTIVITY_ALREADY_STARTED";
    }

    public ActivityAlreadyStartedException() {
        super("IntentLauncher activity is already started. You need to wait for its result before starting another activity.");
    }
}

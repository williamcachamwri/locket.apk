package expo.modules.notifications.notifications.channels;

import expo.modules.core.errors.CodedRuntimeException;

public class InvalidVibrationPatternException extends CodedRuntimeException {
    public String getCode() {
        return "ERR_INVALID_VIBRATION_PATTERN";
    }

    public InvalidVibrationPatternException(int i, Object obj) {
        super("Invalid value in vibration pattern, expected all elements to be numbers, got: " + obj + " under " + i);
    }
}

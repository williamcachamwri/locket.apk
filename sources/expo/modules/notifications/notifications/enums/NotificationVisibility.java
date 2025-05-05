package expo.modules.notifications.notifications.enums;

public enum NotificationVisibility {
    PUBLIC(1, 1),
    PRIVATE(0, 2),
    SECRET(-1, 3),
    UNKNOWN(1, 0);
    
    private final int mEnumValue;
    private final int mNativeVisibility;

    private NotificationVisibility(int i, int i2) {
        this.mNativeVisibility = i;
        this.mEnumValue = i2;
    }

    public int getNativeValue() {
        return this.mNativeVisibility;
    }

    public int getEnumValue() {
        return this.mEnumValue;
    }

    public static NotificationVisibility fromEnumValue(int i) {
        for (NotificationVisibility notificationVisibility : values()) {
            if (notificationVisibility.getEnumValue() == i) {
                return notificationVisibility;
            }
        }
        return UNKNOWN;
    }

    public static NotificationVisibility fromNativeValue(int i) {
        for (NotificationVisibility notificationVisibility : values()) {
            if (notificationVisibility.getNativeValue() == i) {
                return notificationVisibility;
            }
        }
        return UNKNOWN;
    }
}

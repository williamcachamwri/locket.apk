package expo.modules.notifications.notifications.enums;

public enum NotificationImportance {
    UNSPECIFIED(-1000, 1),
    NONE(0, 2),
    MIN(1, 3),
    LOW(2, 4),
    DEFAULT(3, 5),
    HIGH(4, 6),
    MAX(5, 7),
    UNKNOWN(3, 0);
    
    private final int mEnumValue;
    private final int mNativeImportance;

    private NotificationImportance(int i, int i2) {
        this.mNativeImportance = i;
        this.mEnumValue = i2;
    }

    public int getNativeValue() {
        return this.mNativeImportance;
    }

    public int getEnumValue() {
        return this.mEnumValue;
    }

    public static NotificationImportance fromEnumValue(int i) {
        for (NotificationImportance notificationImportance : values()) {
            if (notificationImportance.getEnumValue() == i) {
                return notificationImportance;
            }
        }
        return UNKNOWN;
    }

    public static NotificationImportance fromNativeValue(int i) {
        for (NotificationImportance notificationImportance : values()) {
            if (notificationImportance.getNativeValue() == i) {
                return notificationImportance;
            }
        }
        return UNKNOWN;
    }
}

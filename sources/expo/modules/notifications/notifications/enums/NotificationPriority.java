package expo.modules.notifications.notifications.enums;

public enum NotificationPriority {
    MIN(-2, "min"),
    LOW(-1, "low"),
    DEFAULT(0, "default"),
    HIGH(1, "high"),
    MAX(2, "max");
    
    private final String mEnumValue;
    private final int mNativePriority;

    private NotificationPriority(int i, String str) {
        this.mNativePriority = i;
        this.mEnumValue = str;
    }

    public int getNativeValue() {
        return this.mNativePriority;
    }

    public String getEnumValue() {
        return this.mEnumValue;
    }

    public static NotificationPriority fromEnumValue(String str) {
        for (NotificationPriority notificationPriority : values()) {
            if (notificationPriority.getEnumValue().equalsIgnoreCase(str)) {
                return notificationPriority;
            }
        }
        return null;
    }

    public static NotificationPriority fromNativeValue(int i) {
        for (NotificationPriority notificationPriority : values()) {
            if (notificationPriority.getNativeValue() == i) {
                return notificationPriority;
            }
        }
        return null;
    }
}

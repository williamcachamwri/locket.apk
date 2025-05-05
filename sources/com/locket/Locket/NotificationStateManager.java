package com.locket.Locket;

public class NotificationStateManager {
    private static NotificationStateManager instance;
    private String suppressedConversationId = "";

    private NotificationStateManager() {
    }

    public static synchronized NotificationStateManager getInstance() {
        NotificationStateManager notificationStateManager;
        synchronized (NotificationStateManager.class) {
            if (instance == null) {
                instance = new NotificationStateManager();
            }
            notificationStateManager = instance;
        }
        return notificationStateManager;
    }

    public void setSuppressedConversationId(String str) {
        this.suppressedConversationId = str;
    }

    public String getSuppressedConversationId() {
        return this.suppressedConversationId;
    }
}

package expo.modules.notifications.notifications.presentation.builders;

import android.content.Context;
import expo.modules.notifications.notifications.interfaces.NotificationBuilder;
import expo.modules.notifications.notifications.model.Notification;
import expo.modules.notifications.notifications.model.NotificationBehavior;
import expo.modules.notifications.notifications.model.NotificationContent;

public abstract class BaseNotificationBuilder implements NotificationBuilder {
    private Context mContext;
    private Notification mNotification;
    private NotificationBehavior mNotificationBehavior;

    protected BaseNotificationBuilder(Context context) {
        this.mContext = context;
    }

    public NotificationBuilder setNotification(Notification notification) {
        this.mNotification = notification;
        return this;
    }

    public NotificationBuilder setAllowedBehavior(NotificationBehavior notificationBehavior) {
        this.mNotificationBehavior = notificationBehavior;
        return this;
    }

    /* access modifiers changed from: protected */
    public Context getContext() {
        return this.mContext;
    }

    /* access modifiers changed from: protected */
    public Notification getNotification() {
        return this.mNotification;
    }

    /* access modifiers changed from: protected */
    public NotificationContent getNotificationContent() {
        return getNotification().getNotificationRequest().getContent();
    }

    /* access modifiers changed from: protected */
    public NotificationBehavior getNotificationBehavior() {
        return this.mNotificationBehavior;
    }
}

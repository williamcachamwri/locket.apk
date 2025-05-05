package expo.modules.notifications.notifications.interfaces;

public interface NotificationPresentationEffectsManager extends NotificationPresentationEffect {
    void addEffect(NotificationPresentationEffect notificationPresentationEffect);

    void removeEffect(NotificationPresentationEffect notificationPresentationEffect);
}

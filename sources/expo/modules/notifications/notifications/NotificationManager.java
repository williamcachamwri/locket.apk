package expo.modules.notifications.notifications;

import android.os.Bundle;
import expo.modules.core.interfaces.SingletonModule;
import expo.modules.notifications.notifications.interfaces.NotificationListener;
import expo.modules.notifications.notifications.model.Notification;
import expo.modules.notifications.notifications.model.NotificationResponse;
import expo.modules.notifications.service.delegates.ExpoHandlingDelegate;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.WeakHashMap;

public class NotificationManager implements SingletonModule, expo.modules.notifications.notifications.interfaces.NotificationManager {
    private static final String SINGLETON_NAME = "NotificationManager";
    private WeakHashMap<NotificationListener, WeakReference<NotificationListener>> mListenerReferenceMap = new WeakHashMap<>();
    private Collection<NotificationResponse> mPendingNotificationResponses = new ArrayList();
    private Collection<Bundle> mPendingNotificationResponsesFromExtras = new ArrayList();

    public String getName() {
        return SINGLETON_NAME;
    }

    public NotificationManager() {
        ExpoHandlingDelegate.Companion.addListener(this);
    }

    public void addListener(NotificationListener notificationListener) {
        if (!this.mListenerReferenceMap.containsKey(notificationListener)) {
            this.mListenerReferenceMap.put(notificationListener, new WeakReference(notificationListener));
            if (!this.mPendingNotificationResponses.isEmpty()) {
                for (NotificationResponse onNotificationResponseReceived : this.mPendingNotificationResponses) {
                    notificationListener.onNotificationResponseReceived(onNotificationResponseReceived);
                }
            }
            if (!this.mPendingNotificationResponsesFromExtras.isEmpty()) {
                for (Bundle onNotificationResponseIntentReceived : this.mPendingNotificationResponsesFromExtras) {
                    notificationListener.onNotificationResponseIntentReceived(onNotificationResponseIntentReceived);
                }
            }
        }
    }

    public void removeListener(NotificationListener notificationListener) {
        this.mListenerReferenceMap.remove(notificationListener);
    }

    public void onNotificationReceived(Notification notification) {
        for (WeakReference<NotificationListener> weakReference : this.mListenerReferenceMap.values()) {
            NotificationListener notificationListener = (NotificationListener) weakReference.get();
            if (notificationListener != null) {
                notificationListener.onNotificationReceived(notification);
            }
        }
    }

    public void onNotificationResponseReceived(NotificationResponse notificationResponse) {
        if (this.mListenerReferenceMap.isEmpty()) {
            this.mPendingNotificationResponses.add(notificationResponse);
            return;
        }
        for (WeakReference<NotificationListener> weakReference : this.mListenerReferenceMap.values()) {
            NotificationListener notificationListener = (NotificationListener) weakReference.get();
            if (notificationListener != null) {
                notificationListener.onNotificationResponseReceived(notificationResponse);
            }
        }
    }

    public void onNotificationsDropped() {
        for (WeakReference<NotificationListener> weakReference : this.mListenerReferenceMap.values()) {
            NotificationListener notificationListener = (NotificationListener) weakReference.get();
            if (notificationListener != null) {
                notificationListener.onNotificationsDropped();
            }
        }
    }

    public void onNotificationResponseFromExtras(Bundle bundle) {
        if (!this.mListenerReferenceMap.isEmpty()) {
            for (WeakReference<NotificationListener> weakReference : this.mListenerReferenceMap.values()) {
                NotificationListener notificationListener = (NotificationListener) weakReference.get();
                if (notificationListener != null) {
                    notificationListener.onNotificationResponseIntentReceived(bundle);
                }
            }
        } else if (this.mPendingNotificationResponsesFromExtras.isEmpty()) {
            this.mPendingNotificationResponsesFromExtras.add(bundle);
        }
    }
}

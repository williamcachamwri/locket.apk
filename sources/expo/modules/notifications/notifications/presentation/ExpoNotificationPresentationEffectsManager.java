package expo.modules.notifications.notifications.presentation;

import android.app.Notification;
import expo.modules.core.interfaces.InternalModule;
import expo.modules.notifications.notifications.interfaces.NotificationPresentationEffect;
import expo.modules.notifications.notifications.interfaces.NotificationPresentationEffectsManager;
import io.sentry.android.core.SentryLogcatAdapter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ExpoNotificationPresentationEffectsManager implements InternalModule, NotificationPresentationEffectsManager {
    private Collection<NotificationPresentationEffect> mEffects = new ArrayList();

    public List<? extends Class> getExportedInterfaces() {
        return Collections.singletonList(NotificationPresentationEffectsManager.class);
    }

    public void addEffect(NotificationPresentationEffect notificationPresentationEffect) {
        removeEffect(notificationPresentationEffect);
        this.mEffects.add(notificationPresentationEffect);
    }

    public void removeEffect(NotificationPresentationEffect notificationPresentationEffect) {
        this.mEffects.remove(notificationPresentationEffect);
    }

    public boolean onNotificationPresented(String str, int i, Notification notification) {
        Iterator<NotificationPresentationEffect> it = this.mEffects.iterator();
        while (true) {
            boolean z = false;
            while (true) {
                if (!it.hasNext()) {
                    return z;
                }
                NotificationPresentationEffect next = it.next();
                try {
                    if (next.onNotificationPresented(str, i, notification) || z) {
                        z = true;
                    }
                } catch (Exception e) {
                    SentryLogcatAdapter.w("expo-notifications", String.format("Notification presentation effector %s threw an exception for notification (%s, %d): %s", new Object[]{next.getClass(), str, Integer.valueOf(i), e.getMessage()}));
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean onNotificationPresentationFailed(String str, int i, Notification notification) {
        Iterator<NotificationPresentationEffect> it = this.mEffects.iterator();
        while (true) {
            boolean z = false;
            while (true) {
                if (!it.hasNext()) {
                    return z;
                }
                NotificationPresentationEffect next = it.next();
                try {
                    if (next.onNotificationPresentationFailed(str, i, notification) || z) {
                        z = true;
                    }
                } catch (Exception e) {
                    SentryLogcatAdapter.w("expo-notifications", String.format("Notification presentation effector %s threw an exception for notification (%s, %d): %s", new Object[]{next.getClass(), str, Integer.valueOf(i), e.getMessage()}));
                    e.printStackTrace();
                }
            }
        }
    }
}

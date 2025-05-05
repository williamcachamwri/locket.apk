package expo.modules.notifications.notifications.presentation.effects;

import android.app.Notification;
import android.content.Context;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.interfaces.InternalModule;
import expo.modules.notifications.notifications.interfaces.NotificationPresentationEffect;
import expo.modules.notifications.notifications.interfaces.NotificationPresentationEffectsManager;
import java.util.Collections;
import java.util.List;

public abstract class BaseNotificationEffect implements InternalModule, NotificationPresentationEffect {
    private Context mContext;
    private NotificationPresentationEffectsManager mManager;

    public boolean onNotificationPresentationFailed(String str, int i, Notification notification) {
        return false;
    }

    public boolean onNotificationPresented(String str, int i, Notification notification) {
        return false;
    }

    public BaseNotificationEffect(Context context) {
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    public Context getContext() {
        return this.mContext;
    }

    public List<? extends Class> getExportedInterfaces() {
        return Collections.singletonList(getClass());
    }

    public void onCreate(ModuleRegistry moduleRegistry) {
        NotificationPresentationEffectsManager notificationPresentationEffectsManager = (NotificationPresentationEffectsManager) moduleRegistry.getModule(NotificationPresentationEffectsManager.class);
        this.mManager = notificationPresentationEffectsManager;
        notificationPresentationEffectsManager.addEffect(this);
    }

    public void onDestroy() {
        this.mManager.removeEffect(this);
    }
}

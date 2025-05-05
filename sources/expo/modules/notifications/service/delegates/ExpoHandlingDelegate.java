package expo.modules.notifications.service.delegates;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.ResultReceiver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.media3.common.C;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import expo.modules.notifications.notifications.NotificationManager;
import expo.modules.notifications.notifications.model.Notification;
import expo.modules.notifications.notifications.model.NotificationBehavior;
import expo.modules.notifications.notifications.model.NotificationResponse;
import expo.modules.notifications.service.NotificationForwarderActivity;
import expo.modules.notifications.service.NotificationsService;
import expo.modules.notifications.service.interfaces.HandlingDelegate;
import io.sentry.android.core.SentryLogcatAdapter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u000bH\u0016J\u0006\u0010\u0012\u001a\u00020\u0013J\f\u0010\u0014\u001a\u00020\u0013*\u00020\rH\u0002R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0016"}, d2 = {"Lexpo/modules/notifications/service/delegates/ExpoHandlingDelegate;", "Lexpo/modules/notifications/service/interfaces/HandlingDelegate;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "getListeners", "", "Lexpo/modules/notifications/notifications/NotificationManager;", "handleNotification", "", "notification", "Lexpo/modules/notifications/notifications/model/Notification;", "handleNotificationResponse", "notificationResponse", "Lexpo/modules/notifications/notifications/model/NotificationResponse;", "handleNotificationsDropped", "isAppInForeground", "", "shouldPresent", "Companion", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ExpoHandlingDelegate.kt */
public final class ExpoHandlingDelegate implements HandlingDelegate {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String OPEN_APP_INTENT_ACTION = "expo.modules.notifications.OPEN_APP_ACTION";
    /* access modifiers changed from: private */
    public static WeakHashMap<NotificationManager, WeakReference<NotificationManager>> sListenersReferences = new WeakHashMap<>();
    /* access modifiers changed from: private */
    public static Collection<NotificationResponse> sPendingNotificationResponses = new ArrayList();
    private final Context context;

    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u001f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0007J\u001e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u000fJ\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0012\u0010\u001f\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0016\u0010 \u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u000fR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R,\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\b0\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR \u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006!"}, d2 = {"Lexpo/modules/notifications/service/delegates/ExpoHandlingDelegate$Companion;", "", "()V", "OPEN_APP_INTENT_ACTION", "", "sListenersReferences", "Ljava/util/WeakHashMap;", "Lexpo/modules/notifications/notifications/NotificationManager;", "Ljava/lang/ref/WeakReference;", "getSListenersReferences", "()Ljava/util/WeakHashMap;", "setSListenersReferences", "(Ljava/util/WeakHashMap;)V", "sPendingNotificationResponses", "", "Lexpo/modules/notifications/notifications/model/NotificationResponse;", "getSPendingNotificationResponses", "()Ljava/util/Collection;", "setSPendingNotificationResponses", "(Ljava/util/Collection;)V", "addListener", "", "listener", "createPendingIntentForOpeningApp", "Landroid/app/PendingIntent;", "context", "Landroid/content/Context;", "broadcastIntent", "Landroid/content/Intent;", "notificationResponse", "getMainActivityLauncher", "getNotificationActionLauncher", "openAppToForeground", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ExpoHandlingDelegate.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: protected */
        public final Collection<NotificationResponse> getSPendingNotificationResponses() {
            return ExpoHandlingDelegate.sPendingNotificationResponses;
        }

        /* access modifiers changed from: protected */
        public final void setSPendingNotificationResponses(Collection<NotificationResponse> collection) {
            Intrinsics.checkNotNullParameter(collection, "<set-?>");
            ExpoHandlingDelegate.sPendingNotificationResponses = collection;
        }

        /* access modifiers changed from: protected */
        public final WeakHashMap<NotificationManager, WeakReference<NotificationManager>> getSListenersReferences() {
            return ExpoHandlingDelegate.sListenersReferences;
        }

        /* access modifiers changed from: protected */
        public final void setSListenersReferences(WeakHashMap<NotificationManager, WeakReference<NotificationManager>> weakHashMap) {
            Intrinsics.checkNotNullParameter(weakHashMap, "<set-?>");
            ExpoHandlingDelegate.sListenersReferences = weakHashMap;
        }

        public final void addListener(NotificationManager notificationManager) {
            Intrinsics.checkNotNullParameter(notificationManager, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            if (!getSListenersReferences().containsKey(notificationManager)) {
                getSListenersReferences().put(notificationManager, new WeakReference(notificationManager));
                if (!getSPendingNotificationResponses().isEmpty()) {
                    Iterator<NotificationResponse> it = getSPendingNotificationResponses().iterator();
                    while (it.hasNext()) {
                        notificationManager.onNotificationResponseReceived(it.next());
                        it.remove();
                    }
                }
            }
        }

        public final PendingIntent createPendingIntentForOpeningApp(Context context, Intent intent, NotificationResponse notificationResponse) {
            String className;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(intent, "broadcastIntent");
            Intrinsics.checkNotNullParameter(notificationResponse, NotificationsService.NOTIFICATION_RESPONSE_KEY);
            int i = Build.VERSION.SDK_INT >= 31 ? 167772160 : C.BUFFER_FLAG_FIRST_SAMPLE;
            Intent intent2 = new Intent(context, NotificationForwarderActivity.class);
            intent2.setData(intent.getData());
            intent2.setFlags(402653184);
            intent2.putExtras(intent);
            ComponentName component = intent.getComponent();
            PendingIntent activity = PendingIntent.getActivity(context, (component == null || (className = component.getClassName()) == null) ? NotificationsService.class.hashCode() : className.hashCode(), intent2, i);
            Intrinsics.checkNotNullExpressionValue(activity, "getActivity(...)");
            return activity;
        }

        public final void openAppToForeground(Context context, NotificationResponse notificationResponse) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(notificationResponse, NotificationsService.NOTIFICATION_RESPONSE_KEY);
            Intent notificationActionLauncher = getNotificationActionLauncher(context);
            if (notificationActionLauncher == null) {
                notificationActionLauncher = getMainActivityLauncher(context);
            }
            if (notificationActionLauncher != null) {
                NotificationsService.Companion.setNotificationResponseToIntent(notificationActionLauncher, notificationResponse);
                context.startActivity(notificationActionLauncher);
                return;
            }
            SentryLogcatAdapter.w("expo-notifications", "No launch intent found for application. Interacting with the notification won't open the app. The implementation uses `getLaunchIntentForPackage` to find appropriate activity.");
        }

        private final Intent getNotificationActionLauncher(Context context) {
            Intent intent = new Intent(ExpoHandlingDelegate.OPEN_APP_INTENT_ACTION);
            intent.addFlags(268435456);
            intent.setPackage(context.getApplicationContext().getPackageName());
            if (context.getPackageManager().resolveActivity(intent, 0) != null) {
                return intent;
            }
            return null;
        }

        private final Intent getMainActivityLauncher(Context context) {
            return context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        }
    }

    public ExpoHandlingDelegate(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
    }

    /* access modifiers changed from: protected */
    public final Context getContext() {
        return this.context;
    }

    public final boolean isAppInForeground() {
        return ProcessLifecycleOwner.get().getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED);
    }

    public final List<NotificationManager> getListeners() {
        Collection<WeakReference<NotificationManager>> values = sListenersReferences.values();
        Intrinsics.checkNotNullExpressionValue(values, "<get-values>(...)");
        Collection arrayList = new ArrayList();
        for (WeakReference weakReference : values) {
            NotificationManager notificationManager = (NotificationManager) weakReference.get();
            if (notificationManager != null) {
                arrayList.add(notificationManager);
            }
        }
        return (List) arrayList;
    }

    public void handleNotification(Notification notification) {
        Intrinsics.checkNotNullParameter(notification, NotificationsService.NOTIFICATION_KEY);
        if (isAppInForeground()) {
            for (NotificationManager onNotificationReceived : getListeners()) {
                onNotificationReceived.onNotificationReceived(notification);
            }
        } else if (shouldPresent(notification)) {
            NotificationsService.Companion.present$default(NotificationsService.Companion, this.context, notification, (NotificationBehavior) null, (ResultReceiver) null, 12, (Object) null);
        }
    }

    private final boolean shouldPresent(Notification notification) {
        CharSequence title = notification.getNotificationRequest().getContent().getTitle();
        if (title == null || title.length() == 0) {
            CharSequence text = notification.getNotificationRequest().getContent().getText();
            if (text == null || text.length() == 0) {
                return false;
            }
        }
        return true;
    }

    public void handleNotificationResponse(NotificationResponse notificationResponse) {
        Intrinsics.checkNotNullParameter(notificationResponse, NotificationsService.NOTIFICATION_RESPONSE_KEY);
        if (notificationResponse.getAction().opensAppToForeground()) {
            Companion.openAppToForeground(this.context, notificationResponse);
        }
        if (getListeners().isEmpty()) {
            sPendingNotificationResponses.add(notificationResponse);
            return;
        }
        for (NotificationManager onNotificationResponseReceived : getListeners()) {
            onNotificationResponseReceived.onNotificationResponseReceived(notificationResponse);
        }
    }

    public void handleNotificationsDropped() {
        for (NotificationManager onNotificationsDropped : getListeners()) {
            onNotificationsDropped.onNotificationsDropped();
        }
    }
}

package expo.modules.notifications.notifications.presentation.builders;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import androidx.core.app.NotificationCompat;
import expo.modules.notifications.R;
import expo.modules.notifications.notifications.channels.managers.AndroidXNotificationsChannelGroupManager;
import expo.modules.notifications.notifications.channels.managers.AndroidXNotificationsChannelManager;
import expo.modules.notifications.notifications.channels.managers.NotificationsChannelManager;
import expo.modules.notifications.notifications.interfaces.NotificationTrigger;
import expo.modules.notifications.notifications.model.NotificationRequest;
import expo.modules.notifications.service.NotificationsService;
import io.sentry.android.core.SentryLogcatAdapter;

public abstract class ChannelAwareNotificationBuilder extends BaseNotificationBuilder {
    private static final String FALLBACK_CHANNEL_ID = "expo_notifications_fallback_notification_channel";
    private static final int FALLBACK_CHANNEL_IMPORTANCE = 4;

    public ChannelAwareNotificationBuilder(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public NotificationCompat.Builder createBuilder() {
        return new NotificationCompat.Builder(getContext(), getChannelId());
    }

    /* access modifiers changed from: protected */
    public String getChannelId() {
        NotificationTrigger trigger = getTrigger();
        if (trigger == null) {
            SentryLogcatAdapter.e(NotificationsService.NOTIFICATIONS_KEY, String.format("Couldn't get channel for the notifications - trigger is 'null'. Fallback to '%s' channel", new Object[]{FALLBACK_CHANNEL_ID}));
            return getFallbackNotificationChannel().getId();
        }
        String notificationChannel = trigger.getNotificationChannel();
        if (notificationChannel == null) {
            return getFallbackNotificationChannel().getId();
        }
        NotificationChannel notificationChannel2 = getNotificationsChannelManager().getNotificationChannel(notificationChannel);
        if (notificationChannel2 != null) {
            return notificationChannel2.getId();
        }
        SentryLogcatAdapter.e(NotificationsService.NOTIFICATIONS_KEY, String.format("Channel '%s' doesn't exists. Fallback to '%s' channel", new Object[]{notificationChannel, FALLBACK_CHANNEL_ID}));
        return getFallbackNotificationChannel().getId();
    }

    /* access modifiers changed from: protected */
    public NotificationsChannelManager getNotificationsChannelManager() {
        return new AndroidXNotificationsChannelManager(getContext(), new AndroidXNotificationsChannelGroupManager(getContext()));
    }

    public NotificationChannel getFallbackNotificationChannel() {
        NotificationChannel notificationChannel = getNotificationManager().getNotificationChannel(FALLBACK_CHANNEL_ID);
        if (notificationChannel != null) {
            return notificationChannel;
        }
        return createFallbackChannel();
    }

    /* access modifiers changed from: protected */
    public NotificationChannel createFallbackChannel() {
        NotificationChannel notificationChannel = new NotificationChannel(FALLBACK_CHANNEL_ID, getFallbackChannelName(), 4);
        notificationChannel.setShowBadge(true);
        notificationChannel.enableVibration(true);
        getNotificationManager().createNotificationChannel(notificationChannel);
        return notificationChannel;
    }

    /* access modifiers changed from: protected */
    public String getFallbackChannelName() {
        return getContext().getString(R.string.expo_notifications_fallback_channel_name);
    }

    private NotificationManager getNotificationManager() {
        return (NotificationManager) getContext().getSystemService(NotificationsService.NOTIFICATION_KEY);
    }

    private NotificationTrigger getTrigger() {
        NotificationRequest notificationRequest = getNotification().getNotificationRequest();
        if (notificationRequest == null) {
            return null;
        }
        return notificationRequest.getTrigger();
    }
}

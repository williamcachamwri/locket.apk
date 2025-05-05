package expo.modules.notifications.notifications.presentation.builders;

import android.app.Notification;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcel;
import android.provider.Settings;
import androidx.core.app.NotificationCompat;
import expo.modules.notifications.notifications.enums.NotificationPriority;
import expo.modules.notifications.notifications.model.NotificationAction;
import expo.modules.notifications.notifications.model.NotificationContent;
import expo.modules.notifications.notifications.model.NotificationRequest;
import expo.modules.notifications.notifications.model.NotificationResponse;
import expo.modules.notifications.service.NotificationsService;
import io.sentry.android.core.SentryLogcatAdapter;

public class ExpoNotificationBuilder extends ChannelAwareNotificationBuilder {
    private static final String EXTRAS_BODY_KEY = "body";
    public static final String EXTRAS_MARSHALLED_NOTIFICATION_REQUEST_KEY = "expo.notification_request";
    public static final String META_DATA_DEFAULT_COLOR_KEY = "expo.modules.notifications.default_notification_color";
    public static final String META_DATA_DEFAULT_ICON_KEY = "expo.modules.notifications.default_notification_icon";
    public static final String META_DATA_LARGE_ICON_KEY = "expo.modules.notifications.large_notification_icon";
    private static final long[] NO_VIBRATE_PATTERN = {0, 0};

    public ExpoNotificationBuilder(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public NotificationCompat.Builder createBuilder() {
        NotificationCompat.Builder createBuilder = super.createBuilder();
        createBuilder.setSmallIcon(getIcon());
        createBuilder.setLargeIcon(getLargeIcon());
        createBuilder.setPriority(getPriority());
        NotificationContent notificationContent = getNotificationContent();
        createBuilder.setAutoCancel(notificationContent.isAutoDismiss());
        createBuilder.setOngoing(notificationContent.isSticky());
        createBuilder.setContentTitle(notificationContent.getTitle());
        createBuilder.setContentText(notificationContent.getText());
        createBuilder.setSubText(notificationContent.getSubtitle());
        createBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(notificationContent.getText()));
        Number color = getColor();
        if (color != null) {
            createBuilder.setColor(color.intValue());
        }
        boolean z = shouldPlaySound() && notificationContent.shouldPlayDefaultSound();
        if (z && shouldVibrate()) {
            createBuilder.setDefaults(-1);
        } else if (shouldVibrate()) {
            createBuilder.setDefaults(2);
        } else if (z) {
            createBuilder.setDefaults(1);
        } else {
            createBuilder.setSilent(true);
        }
        if (shouldPlaySound() && notificationContent.getSound() != null) {
            createBuilder.setSound(notificationContent.getSound());
        } else if (z) {
            createBuilder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
        }
        long[] vibrationPattern = notificationContent.getVibrationPattern();
        if (shouldVibrate() && vibrationPattern != null) {
            createBuilder.setVibrate(vibrationPattern);
        }
        if (notificationContent.getBody() != null) {
            Bundle extras = createBuilder.getExtras();
            extras.putString("body", notificationContent.getBody().toString());
            createBuilder.setExtras(extras);
        }
        Bundle bundle = new Bundle();
        bundle.putByteArray(EXTRAS_MARSHALLED_NOTIFICATION_REQUEST_KEY, marshallNotificationRequest(getNotification().getNotificationRequest()));
        createBuilder.addExtras(bundle);
        createBuilder.setContentIntent(NotificationsService.Companion.createNotificationResponseIntent(getContext(), getNotification(), new NotificationAction(NotificationResponse.DEFAULT_ACTION_IDENTIFIER, (String) null, true)));
        return createBuilder;
    }

    public Notification build() {
        return createBuilder().build();
    }

    /* access modifiers changed from: protected */
    public byte[] marshallNotificationRequest(NotificationRequest notificationRequest) {
        try {
            Parcel obtain = Parcel.obtain();
            notificationRequest.writeToParcel(obtain, 0);
            byte[] marshall = obtain.marshall();
            obtain.recycle();
            return marshall;
        } catch (Exception e) {
            SentryLogcatAdapter.e("expo-notifications", String.format("Could not marshalled notification request: %s.", new Object[]{notificationRequest.getIdentifier()}));
            e.printStackTrace();
            return null;
        }
    }

    private boolean shouldPlaySound() {
        boolean z = getNotificationBehavior() == null || getNotificationBehavior().shouldPlaySound();
        NotificationContent notificationContent = getNotificationContent();
        boolean z2 = notificationContent.shouldPlayDefaultSound() || notificationContent.getSound() != null;
        if (!z || !z2) {
            return false;
        }
        return true;
    }

    private boolean shouldVibrate() {
        boolean z = getNotificationBehavior() == null || getNotificationBehavior().shouldPlaySound();
        NotificationContent notificationContent = getNotificationContent();
        boolean z2 = notificationContent.shouldUseDefaultVibrationPattern() || notificationContent.getVibrationPattern() != null;
        if (!z || !z2) {
            return false;
        }
        return true;
    }

    private int getPriority() {
        int i;
        NotificationPriority priority = getNotificationContent().getPriority();
        if (getNotificationBehavior() != null) {
            NotificationPriority priorityOverride = getNotificationBehavior().getPriorityOverride();
            if (priorityOverride != null) {
                return priorityOverride.getNativeValue();
            }
            if (priority != null) {
                i = priority.getNativeValue();
            } else {
                i = NotificationPriority.DEFAULT.getNativeValue();
            }
            if (getNotificationBehavior().shouldShowAlert()) {
                return Math.max(1, i);
            }
            return Math.min(0, i);
        } else if (priority != null) {
            return priority.getNativeValue();
        } else {
            return 1;
        }
    }

    /* access modifiers changed from: protected */
    public Bitmap getLargeIcon() {
        try {
            ApplicationInfo applicationInfo = getContext().getPackageManager().getApplicationInfo(getContext().getPackageName(), 128);
            if (!applicationInfo.metaData.containsKey(META_DATA_LARGE_ICON_KEY)) {
                return null;
            }
            return BitmapFactory.decodeResource(getContext().getResources(), applicationInfo.metaData.getInt(META_DATA_LARGE_ICON_KEY));
        } catch (PackageManager.NameNotFoundException | ClassCastException unused) {
            SentryLogcatAdapter.e("expo-notifications", "Could not have fetched large notification icon.");
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public int getIcon() {
        try {
            ApplicationInfo applicationInfo = getContext().getPackageManager().getApplicationInfo(getContext().getPackageName(), 128);
            if (applicationInfo.metaData.containsKey(META_DATA_DEFAULT_ICON_KEY)) {
                return applicationInfo.metaData.getInt(META_DATA_DEFAULT_ICON_KEY);
            }
        } catch (PackageManager.NameNotFoundException | ClassCastException unused) {
            SentryLogcatAdapter.e("expo-notifications", "Could not have fetched default notification icon.");
        }
        return getContext().getApplicationInfo().icon;
    }

    /* access modifiers changed from: protected */
    public Number getColor() {
        if (getNotificationContent().getColor() != null) {
            return getNotificationContent().getColor();
        }
        try {
            ApplicationInfo applicationInfo = getContext().getPackageManager().getApplicationInfo(getContext().getPackageName(), 128);
            if (applicationInfo.metaData.containsKey(META_DATA_DEFAULT_COLOR_KEY)) {
                return Integer.valueOf(getContext().getResources().getColor(applicationInfo.metaData.getInt(META_DATA_DEFAULT_COLOR_KEY), (Resources.Theme) null));
            }
        } catch (PackageManager.NameNotFoundException | Resources.NotFoundException | ClassCastException unused) {
            SentryLogcatAdapter.e("expo-notifications", "Could not have fetched default notification color.");
        }
        return null;
    }
}

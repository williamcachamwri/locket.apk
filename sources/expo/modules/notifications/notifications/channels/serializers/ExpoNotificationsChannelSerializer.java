package expo.modules.notifications.notifications.channels.serializers;

import android.app.NotificationChannel;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import expo.modules.notifications.notifications.enums.AudioContentType;
import expo.modules.notifications.notifications.enums.AudioUsage;
import expo.modules.notifications.notifications.enums.NotificationImportance;
import expo.modules.notifications.notifications.enums.NotificationVisibility;

public class ExpoNotificationsChannelSerializer implements NotificationsChannelSerializer {
    public Bundle toBundle(NotificationChannel notificationChannel) {
        if (notificationChannel == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("id", getChannelId(notificationChannel));
        bundle.putString("name", notificationChannel.getName().toString());
        bundle.putInt(NotificationsChannelSerializer.IMPORTANCE_KEY, NotificationImportance.fromNativeValue(notificationChannel.getImportance()).getEnumValue());
        bundle.putBoolean(NotificationsChannelSerializer.BYPASS_DND_KEY, notificationChannel.canBypassDnd());
        bundle.putString("description", notificationChannel.getDescription());
        bundle.putString(NotificationsChannelSerializer.GROUP_ID_KEY, getGroupId(notificationChannel));
        bundle.putString(NotificationsChannelSerializer.LIGHT_COLOR_KEY, String.format("#%08x", new Object[]{Integer.valueOf(Color.valueOf(notificationChannel.getLightColor()).toArgb())}).toUpperCase());
        bundle.putInt(NotificationsChannelSerializer.LOCKSCREEN_VISIBILITY_KEY, NotificationVisibility.fromNativeValue(notificationChannel.getLockscreenVisibility()).getEnumValue());
        bundle.putBoolean(NotificationsChannelSerializer.SHOW_BADGE_KEY, notificationChannel.canShowBadge());
        bundle.putString(NotificationsChannelSerializer.SOUND_KEY, toString(notificationChannel.getSound()));
        bundle.putBundle(NotificationsChannelSerializer.SOUND_AUDIO_ATTRIBUTES_KEY, toBundle(notificationChannel.getAudioAttributes()));
        bundle.putDoubleArray(NotificationsChannelSerializer.VIBRATION_PATTERN_KEY, toArray(notificationChannel.getVibrationPattern()));
        bundle.putBoolean(NotificationsChannelSerializer.ENABLE_LIGHTS_KEY, notificationChannel.shouldShowLights());
        bundle.putBoolean(NotificationsChannelSerializer.ENABLE_VIBRATE_KEY, notificationChannel.shouldVibrate());
        return bundle;
    }

    /* access modifiers changed from: protected */
    public String getChannelId(NotificationChannel notificationChannel) {
        return notificationChannel.getId();
    }

    /* access modifiers changed from: protected */
    public String getGroupId(NotificationChannel notificationChannel) {
        return notificationChannel.getGroup();
    }

    private String toString(Uri uri) {
        if (uri == null) {
            return null;
        }
        return Settings.System.DEFAULT_NOTIFICATION_URI.equals(uri) ? "default" : "custom";
    }

    private Bundle toBundle(AudioAttributes audioAttributes) {
        if (audioAttributes == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("usage", AudioUsage.fromNativeValue(audioAttributes.getUsage()).getEnumValue());
        bundle.putInt(NotificationsChannelSerializer.AUDIO_ATTRIBUTES_CONTENT_TYPE_KEY, AudioContentType.fromNativeValue(audioAttributes.getContentType()).getEnumValue());
        Bundle bundle2 = new Bundle();
        boolean z = false;
        bundle2.putBoolean(NotificationsChannelSerializer.AUDIO_ATTRIBUTES_FLAGS_HW_AV_SYNC_KEY, (audioAttributes.getFlags() & 16) > 0);
        if ((audioAttributes.getFlags() & 1) > 0) {
            z = true;
        }
        bundle2.putBoolean(NotificationsChannelSerializer.AUDIO_ATTRIBUTES_FLAGS_ENFORCE_AUDIBILITY_KEY, z);
        bundle.putBundle(NotificationsChannelSerializer.AUDIO_ATTRIBUTES_FLAGS_KEY, bundle2);
        return bundle;
    }

    private double[] toArray(long[] jArr) {
        if (jArr == null) {
            return null;
        }
        double[] dArr = new double[jArr.length];
        for (int i = 0; i < jArr.length; i++) {
            dArr[i] = (double) jArr[i];
        }
        return dArr;
    }
}

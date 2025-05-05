package expo.modules.notifications.notifications.channels.serializers;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.os.Build;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

public class ExpoNotificationsChannelGroupSerializer implements NotificationsChannelGroupSerializer {
    private NotificationsChannelSerializer mChannelSerializer;

    public ExpoNotificationsChannelGroupSerializer(NotificationsChannelSerializer notificationsChannelSerializer) {
        this.mChannelSerializer = notificationsChannelSerializer;
    }

    public Bundle toBundle(NotificationChannelGroup notificationChannelGroup) {
        if (notificationChannelGroup == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("id", getId(notificationChannelGroup));
        bundle.putString("name", notificationChannelGroup.getName().toString());
        if (Build.VERSION.SDK_INT >= 28) {
            bundle.putString("description", notificationChannelGroup.getDescription());
            bundle.putBoolean(NotificationsChannelGroupSerializer.IS_BLOCKED_KEY, notificationChannelGroup.isBlocked());
        }
        bundle.putParcelableArrayList(NotificationsChannelGroupSerializer.CHANNELS_KEY, toList(notificationChannelGroup.getChannels()));
        return bundle;
    }

    /* access modifiers changed from: protected */
    public String getId(NotificationChannelGroup notificationChannelGroup) {
        return notificationChannelGroup.getId();
    }

    private ArrayList<Bundle> toList(List<NotificationChannel> list) {
        ArrayList<Bundle> arrayList = new ArrayList<>(list.size());
        for (NotificationChannel bundle : list) {
            arrayList.add(this.mChannelSerializer.toBundle(bundle));
        }
        return arrayList;
    }
}

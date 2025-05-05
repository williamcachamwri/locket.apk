package expo.modules.notifications.notifications.channels;

import android.content.Context;
import expo.modules.notifications.notifications.channels.managers.AndroidXNotificationsChannelGroupManager;
import expo.modules.notifications.notifications.channels.managers.AndroidXNotificationsChannelManager;
import expo.modules.notifications.notifications.channels.managers.NotificationsChannelGroupManager;
import expo.modules.notifications.notifications.channels.managers.NotificationsChannelManager;
import expo.modules.notifications.notifications.channels.serializers.ExpoNotificationsChannelGroupSerializer;
import expo.modules.notifications.notifications.channels.serializers.ExpoNotificationsChannelSerializer;
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelGroupSerializer;
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelSerializer;

public class AndroidXNotificationsChannelsProvider extends AbstractNotificationsChannelsProvider {
    public AndroidXNotificationsChannelsProvider(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public NotificationsChannelManager createChannelManager() {
        return new AndroidXNotificationsChannelManager(this.mContext, getGroupManager());
    }

    /* access modifiers changed from: protected */
    public NotificationsChannelGroupManager createChannelGroupManager() {
        return new AndroidXNotificationsChannelGroupManager(this.mContext);
    }

    /* access modifiers changed from: protected */
    public NotificationsChannelSerializer createChannelSerializer() {
        return new ExpoNotificationsChannelSerializer();
    }

    /* access modifiers changed from: protected */
    public NotificationsChannelGroupSerializer createChannelGroupSerializer() {
        return new ExpoNotificationsChannelGroupSerializer(getChannelSerializer());
    }
}

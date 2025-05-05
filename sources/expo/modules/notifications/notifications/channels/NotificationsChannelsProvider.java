package expo.modules.notifications.notifications.channels;

import expo.modules.notifications.notifications.channels.managers.NotificationsChannelGroupManager;
import expo.modules.notifications.notifications.channels.managers.NotificationsChannelManager;
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelGroupSerializer;
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelSerializer;

public interface NotificationsChannelsProvider {
    NotificationsChannelManager getChannelManager();

    NotificationsChannelSerializer getChannelSerializer();

    NotificationsChannelGroupManager getGroupManager();

    NotificationsChannelGroupSerializer getGroupSerializer();
}

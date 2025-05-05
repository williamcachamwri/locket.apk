package expo.modules.notifications.notifications.channels;

import android.content.Context;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.interfaces.InternalModule;
import expo.modules.notifications.notifications.channels.managers.NotificationsChannelGroupManager;
import expo.modules.notifications.notifications.channels.managers.NotificationsChannelManager;
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelGroupSerializer;
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelSerializer;
import java.util.Collections;
import java.util.List;

public abstract class AbstractNotificationsChannelsProvider implements NotificationsChannelsProvider, InternalModule {
    private NotificationsChannelGroupManager mChannelGroupManager;
    private NotificationsChannelGroupSerializer mChannelGroupSerializer;
    private NotificationsChannelManager mChannelManager;
    private NotificationsChannelSerializer mChannelSerializer;
    protected final Context mContext;
    private ModuleRegistry mModuleRegistry;

    /* access modifiers changed from: protected */
    public abstract NotificationsChannelGroupManager createChannelGroupManager();

    /* access modifiers changed from: protected */
    public abstract NotificationsChannelGroupSerializer createChannelGroupSerializer();

    /* access modifiers changed from: protected */
    public abstract NotificationsChannelManager createChannelManager();

    /* access modifiers changed from: protected */
    public abstract NotificationsChannelSerializer createChannelSerializer();

    public AbstractNotificationsChannelsProvider(Context context) {
        this.mContext = context;
    }

    public List<? extends Class> getExportedInterfaces() {
        return Collections.singletonList(NotificationsChannelsProvider.class);
    }

    public void onCreate(ModuleRegistry moduleRegistry) {
        this.mModuleRegistry = moduleRegistry;
    }

    public final ModuleRegistry getModuleRegistry() {
        return this.mModuleRegistry;
    }

    public final NotificationsChannelManager getChannelManager() {
        if (this.mChannelManager == null) {
            this.mChannelManager = createChannelManager();
        }
        return this.mChannelManager;
    }

    public final NotificationsChannelGroupManager getGroupManager() {
        if (this.mChannelGroupManager == null) {
            this.mChannelGroupManager = createChannelGroupManager();
        }
        return this.mChannelGroupManager;
    }

    public final NotificationsChannelSerializer getChannelSerializer() {
        if (this.mChannelSerializer == null) {
            this.mChannelSerializer = createChannelSerializer();
        }
        return this.mChannelSerializer;
    }

    public final NotificationsChannelGroupSerializer getGroupSerializer() {
        if (this.mChannelGroupSerializer == null) {
            this.mChannelGroupSerializer = createChannelGroupSerializer();
        }
        return this.mChannelGroupSerializer;
    }
}

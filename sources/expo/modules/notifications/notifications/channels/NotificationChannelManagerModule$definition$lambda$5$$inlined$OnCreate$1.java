package expo.modules.notifications.notifications.channels;

import expo.modules.notifications.ModuleNotFoundException;
import expo.modules.notifications.notifications.channels.managers.NotificationsChannelManager;
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelSerializer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, d2 = {"<anonymous>", "", "invoke", "expo/modules/kotlin/modules/ModuleDefinitionBuilder$OnCreate$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ModuleDefinitionBuilder.kt */
public final class NotificationChannelManagerModule$definition$lambda$5$$inlined$OnCreate$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ NotificationChannelManagerModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NotificationChannelManagerModule$definition$lambda$5$$inlined$OnCreate$1(NotificationChannelManagerModule notificationChannelManagerModule) {
        super(0);
        this.this$0 = notificationChannelManagerModule;
    }

    public final void invoke() {
        Object obj;
        try {
            obj = this.this$0.getAppContext().getLegacyModuleRegistry().getModule(NotificationsChannelsProvider.class);
        } catch (Exception unused) {
            obj = null;
        }
        NotificationsChannelsProvider notificationsChannelsProvider = (NotificationsChannelsProvider) obj;
        if (notificationsChannelsProvider != null) {
            NotificationChannelManagerModule notificationChannelManagerModule = this.this$0;
            NotificationsChannelManager channelManager = notificationsChannelsProvider.getChannelManager();
            Intrinsics.checkNotNullExpressionValue(channelManager, "getChannelManager(...)");
            notificationChannelManagerModule.channelManager = channelManager;
            NotificationChannelManagerModule notificationChannelManagerModule2 = this.this$0;
            NotificationsChannelSerializer channelSerializer = notificationsChannelsProvider.getChannelSerializer();
            Intrinsics.checkNotNullExpressionValue(channelSerializer, "getChannelSerializer(...)");
            notificationChannelManagerModule2.channelSerializer = channelSerializer;
            return;
        }
        throw new ModuleNotFoundException(Reflection.getOrCreateKotlinClass(NotificationsChannelsProvider.class));
    }
}

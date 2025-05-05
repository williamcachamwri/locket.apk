package expo.modules.notifications.notifications.channels;

import android.app.NotificationChannel;
import expo.modules.notifications.notifications.channels.managers.NotificationsChannelManager;
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0010\u0010\u0002\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "it", "", "invoke", "([Ljava/lang/Object;)Ljava/lang/Object;", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class NotificationChannelManagerModule$definition$lambda$5$$inlined$AsyncFunctionWithoutArgs$1 extends Lambda implements Function1<Object[], Object> {
    final /* synthetic */ NotificationChannelManagerModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NotificationChannelManagerModule$definition$lambda$5$$inlined$AsyncFunctionWithoutArgs$1(NotificationChannelManagerModule notificationChannelManagerModule) {
        super(1);
        this.this$0 = notificationChannelManagerModule;
    }

    public final Object invoke(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "it");
        NotificationsChannelManager access$getChannelManager$p = this.this$0.channelManager;
        NotificationsChannelSerializer notificationsChannelSerializer = null;
        if (access$getChannelManager$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("channelManager");
            access$getChannelManager$p = null;
        }
        List<NotificationChannel> notificationChannels = access$getChannelManager$p.getNotificationChannels();
        Intrinsics.checkNotNullExpressionValue(notificationChannels, "getNotificationChannels(...)");
        Iterable<NotificationChannel> iterable = notificationChannels;
        NotificationsChannelSerializer access$getChannelSerializer$p = this.this$0.channelSerializer;
        if (access$getChannelSerializer$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("channelSerializer");
        } else {
            notificationsChannelSerializer = access$getChannelSerializer$p;
        }
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (NotificationChannel bundle : iterable) {
            arrayList.add(notificationsChannelSerializer.toBundle(bundle));
        }
        return (List) arrayList;
    }
}

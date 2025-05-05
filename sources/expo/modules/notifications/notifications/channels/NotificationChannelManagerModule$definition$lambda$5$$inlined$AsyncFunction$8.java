package expo.modules.notifications.notifications.channels;

import android.app.NotificationChannel;
import expo.modules.core.arguments.ReadableArguments;
import expo.modules.notifications.notifications.channels.managers.NotificationsChannelManager;
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelSerializer;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\u0006\b\u0002\u0010\u0004\u0018\u00012\u0010\u0010\u0005\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0006H\n¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"<anonymous>", "", "R", "P0", "P1", "it", "", "invoke", "([Ljava/lang/Object;)Ljava/lang/Object;", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$13"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class NotificationChannelManagerModule$definition$lambda$5$$inlined$AsyncFunction$8 extends Lambda implements Function1<Object[], Object> {
    final /* synthetic */ NotificationChannelManagerModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NotificationChannelManagerModule$definition$lambda$5$$inlined$AsyncFunction$8(NotificationChannelManagerModule notificationChannelManagerModule) {
        super(1);
        this.this$0 = notificationChannelManagerModule;
    }

    public final Object invoke(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "it");
        String str = objArr[0];
        if (str != null) {
            String str2 = str;
            ReadableArguments readableArguments = objArr[1];
            if (readableArguments != null) {
                ReadableArguments readableArguments2 = readableArguments;
                NotificationsChannelManager access$getChannelManager$p = this.this$0.channelManager;
                NotificationsChannelSerializer notificationsChannelSerializer = null;
                if (access$getChannelManager$p == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("channelManager");
                    access$getChannelManager$p = null;
                }
                NotificationChannel createNotificationChannel = access$getChannelManager$p.createNotificationChannel(str2, this.this$0.getNameFromOptions(readableArguments2), this.this$0.getImportanceFromOptions(readableArguments2), readableArguments2);
                NotificationsChannelSerializer access$getChannelSerializer$p = this.this$0.channelSerializer;
                if (access$getChannelSerializer$p == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("channelSerializer");
                } else {
                    notificationsChannelSerializer = access$getChannelSerializer$p;
                }
                return notificationsChannelSerializer.toBundle(createNotificationChannel);
            }
            throw new NullPointerException("null cannot be cast to non-null type expo.modules.core.arguments.ReadableArguments");
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
    }
}

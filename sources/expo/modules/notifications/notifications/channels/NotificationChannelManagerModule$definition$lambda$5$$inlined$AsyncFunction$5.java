package expo.modules.notifications.notifications.channels;

import android.app.NotificationChannel;
import com.facebook.react.bridge.BaseJavaModule;
import expo.modules.core.arguments.ReadableArguments;
import expo.modules.kotlin.Promise;
import expo.modules.notifications.notifications.channels.managers.NotificationsChannelManager;
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelSerializer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\u0006\b\u0002\u0010\u0004\u0018\u00012\u0010\u0010\u0005\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\n¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"<anonymous>", "", "R", "P0", "P1", "args", "", "", "promise", "Lexpo/modules/kotlin/Promise;", "invoke", "([Ljava/lang/Object;Lexpo/modules/kotlin/Promise;)V", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$10"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class NotificationChannelManagerModule$definition$lambda$5$$inlined$AsyncFunction$5 extends Lambda implements Function2<Object[], Promise, Unit> {
    final /* synthetic */ NotificationChannelManagerModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NotificationChannelManagerModule$definition$lambda$5$$inlined$AsyncFunction$5(NotificationChannelManagerModule notificationChannelManagerModule) {
        super(2);
        this.this$0 = notificationChannelManagerModule;
    }

    public final void invoke(Object[] objArr, Promise promise) {
        Intrinsics.checkNotNullParameter(objArr, "args");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        String str = objArr[0];
        if (str != null) {
            String str2 = str;
            ReadableArguments readableArguments = (ReadableArguments) promise;
            NotificationsChannelManager access$getChannelManager$p = this.this$0.channelManager;
            NotificationsChannelSerializer notificationsChannelSerializer = null;
            if (access$getChannelManager$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("channelManager");
                access$getChannelManager$p = null;
            }
            NotificationChannel createNotificationChannel = access$getChannelManager$p.createNotificationChannel(str2, this.this$0.getNameFromOptions(readableArguments), this.this$0.getImportanceFromOptions(readableArguments), readableArguments);
            NotificationsChannelSerializer access$getChannelSerializer$p = this.this$0.channelSerializer;
            if (access$getChannelSerializer$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("channelSerializer");
            } else {
                notificationsChannelSerializer = access$getChannelSerializer$p;
            }
            notificationsChannelSerializer.toBundle(createNotificationChannel);
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Object[]) obj, (Promise) obj2);
        return Unit.INSTANCE;
    }
}

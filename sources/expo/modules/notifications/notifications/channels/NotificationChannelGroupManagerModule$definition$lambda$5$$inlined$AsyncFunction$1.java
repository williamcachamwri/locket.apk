package expo.modules.notifications.notifications.channels;

import android.app.NotificationChannelGroup;
import com.facebook.react.bridge.BaseJavaModule;
import expo.modules.kotlin.Promise;
import expo.modules.notifications.notifications.channels.managers.NotificationsChannelGroupManager;
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelGroupSerializer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u00012\u0010\u0010\u0004\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\n¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"<anonymous>", "", "R", "P0", "<anonymous parameter 0>", "", "", "promise", "Lexpo/modules/kotlin/Promise;", "invoke", "([Ljava/lang/Object;Lexpo/modules/kotlin/Promise;)V", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$5"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class NotificationChannelGroupManagerModule$definition$lambda$5$$inlined$AsyncFunction$1 extends Lambda implements Function2<Object[], Promise, Unit> {
    final /* synthetic */ NotificationChannelGroupManagerModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NotificationChannelGroupManagerModule$definition$lambda$5$$inlined$AsyncFunction$1(NotificationChannelGroupManagerModule notificationChannelGroupManagerModule) {
        super(2);
        this.this$0 = notificationChannelGroupManagerModule;
    }

    public final void invoke(Object[] objArr, Promise promise) {
        Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        String str = (String) promise;
        NotificationsChannelGroupManager access$getGroupManager$p = this.this$0.groupManager;
        NotificationsChannelGroupSerializer notificationsChannelGroupSerializer = null;
        if (access$getGroupManager$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("groupManager");
            access$getGroupManager$p = null;
        }
        NotificationChannelGroup notificationChannelGroup = access$getGroupManager$p.getNotificationChannelGroup(str);
        NotificationsChannelGroupSerializer access$getGroupSerializer$p = this.this$0.groupSerializer;
        if (access$getGroupSerializer$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("groupSerializer");
        } else {
            notificationsChannelGroupSerializer = access$getGroupSerializer$p;
        }
        notificationsChannelGroupSerializer.toBundle(notificationChannelGroup);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Object[]) obj, (Promise) obj2);
        return Unit.INSTANCE;
    }
}

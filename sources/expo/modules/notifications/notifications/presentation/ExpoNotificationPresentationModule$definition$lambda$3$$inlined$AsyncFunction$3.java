package expo.modules.notifications.notifications.presentation;

import com.facebook.react.bridge.BaseJavaModule;
import expo.modules.core.arguments.ReadableArguments;
import expo.modules.kotlin.Promise;
import expo.modules.notifications.notifications.ArgumentsNotificationContentBuilder;
import expo.modules.notifications.notifications.interfaces.NotificationTrigger;
import expo.modules.notifications.notifications.model.Notification;
import expo.modules.notifications.notifications.model.NotificationBehavior;
import expo.modules.notifications.notifications.model.NotificationContent;
import expo.modules.notifications.service.NotificationsService;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\u0006\b\u0002\u0010\u0004\u0018\u0001\"\u0006\b\u0003\u0010\u0005\u0018\u00012\u0010\u0010\u0006\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\b0\u00072\u0006\u0010\t\u001a\u00020\nH\n¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"<anonymous>", "", "R", "P0", "P1", "P2", "args", "", "", "promise", "Lexpo/modules/kotlin/Promise;", "invoke", "([Ljava/lang/Object;Lexpo/modules/kotlin/Promise;)V", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$17"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class ExpoNotificationPresentationModule$definition$lambda$3$$inlined$AsyncFunction$3 extends Lambda implements Function2<Object[], Promise, Unit> {
    final /* synthetic */ ExpoNotificationPresentationModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExpoNotificationPresentationModule$definition$lambda$3$$inlined$AsyncFunction$3(ExpoNotificationPresentationModule expoNotificationPresentationModule) {
        super(2);
        this.this$0 = expoNotificationPresentationModule;
    }

    public final void invoke(Object[] objArr, Promise promise) {
        Intrinsics.checkNotNullParameter(objArr, "args");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        String str = objArr[0];
        if (str != null) {
            String str2 = str;
            ReadableArguments readableArguments = objArr[1];
            if (readableArguments != null) {
                NotificationContent build = new ArgumentsNotificationContentBuilder(this.this$0.getContext()).setPayload(readableArguments).build();
                ExpoNotificationPresentationModule expoNotificationPresentationModule = this.this$0;
                Intrinsics.checkNotNull(build);
                NotificationsService.Companion.present(this.this$0.getContext(), new Notification(expoNotificationPresentationModule.createNotificationRequest(str2, build, (NotificationTrigger) null)), (NotificationBehavior) null, this.this$0.createResultReceiver(new ExpoNotificationPresentationModule$definition$1$1$1(promise, str2)));
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type expo.modules.core.arguments.ReadableArguments");
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Object[]) obj, (Promise) obj2);
        return Unit.INSTANCE;
    }
}

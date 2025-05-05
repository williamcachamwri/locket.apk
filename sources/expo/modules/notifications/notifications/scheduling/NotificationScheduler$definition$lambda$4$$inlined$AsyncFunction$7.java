package expo.modules.notifications.notifications.scheduling;

import com.facebook.react.bridge.BaseJavaModule;
import expo.modules.core.arguments.ReadableArguments;
import expo.modules.core.errors.InvalidArgumentException;
import expo.modules.kotlin.Promise;
import expo.modules.notifications.notifications.ArgumentsNotificationContentBuilder;
import expo.modules.notifications.notifications.model.NotificationContent;
import expo.modules.notifications.service.NotificationsService;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\u0006\b\u0002\u0010\u0004\u0018\u0001\"\u0006\b\u0003\u0010\u0005\u0018\u0001\"\u0006\b\u0004\u0010\u0006\u0018\u00012\u0010\u0010\u0007\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\n¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"<anonymous>", "", "R", "P0", "P1", "P2", "P3", "args", "", "", "promise", "Lexpo/modules/kotlin/Promise;", "invoke", "([Ljava/lang/Object;Lexpo/modules/kotlin/Promise;)V", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$26"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class NotificationScheduler$definition$lambda$4$$inlined$AsyncFunction$7 extends Lambda implements Function2<Object[], Promise, Unit> {
    final /* synthetic */ NotificationScheduler this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NotificationScheduler$definition$lambda$4$$inlined$AsyncFunction$7(NotificationScheduler notificationScheduler) {
        super(2);
        this.this$0 = notificationScheduler;
    }

    public final void invoke(Object[] objArr, Promise promise) {
        Intrinsics.checkNotNullParameter(objArr, "args");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        String str = objArr[0];
        if (str != null) {
            String str2 = str;
            ReadableArguments readableArguments = objArr[1];
            if (readableArguments != null) {
                ReadableArguments readableArguments2 = readableArguments;
                ReadableArguments readableArguments3 = objArr[2];
                try {
                    NotificationContent build = new ArgumentsNotificationContentBuilder(this.this$0.getSchedulingContext()).setPayload(readableArguments2).build();
                    NotificationScheduler notificationScheduler = this.this$0;
                    Intrinsics.checkNotNull(build);
                    NotificationsService.Companion.schedule(this.this$0.getSchedulingContext(), notificationScheduler.createNotificationRequest(str2, build, this.this$0.triggerFromParams(readableArguments3)), this.this$0.createResultReceiver(new NotificationScheduler$definition$1$2$1(promise, str2)));
                } catch (InvalidArgumentException e) {
                    promise.reject("ERR_NOTIFICATIONS_FAILED_TO_SCHEDULE", "Failed to schedule the notification. " + e.getMessage(), e);
                } catch (NullPointerException e2) {
                    promise.reject("ERR_NOTIFICATIONS_FAILED_TO_SCHEDULE", "Failed to schedule the notification. Encountered unexpected null value. " + e2.getMessage(), e2);
                }
            } else {
                throw new NullPointerException("null cannot be cast to non-null type expo.modules.core.arguments.ReadableArguments");
            }
        } else {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Object[]) obj, (Promise) obj2);
        return Unit.INSTANCE;
    }
}

package expo.modules.notifications.notifications.scheduling;

import expo.modules.core.arguments.ReadableArguments;
import expo.modules.core.errors.InvalidArgumentException;
import expo.modules.kotlin.Promise;
import expo.modules.notifications.notifications.ArgumentsNotificationContentBuilder;
import expo.modules.notifications.notifications.model.NotificationContent;
import expo.modules.notifications.service.NotificationsService;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\u0006\b\u0002\u0010\u0004\u0018\u0001\"\u0006\b\u0003\u0010\u0005\u0018\u0001\"\u0006\b\u0004\u0010\u0006\u0018\u00012\u0010\u0010\u0007\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\bH\n¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"<anonymous>", "", "R", "P0", "P1", "P2", "P3", "it", "", "invoke", "([Ljava/lang/Object;)Ljava/lang/Object;", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$31"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class NotificationScheduler$definition$lambda$4$$inlined$AsyncFunction$12 extends Lambda implements Function1<Object[], Object> {
    final /* synthetic */ NotificationScheduler this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NotificationScheduler$definition$lambda$4$$inlined$AsyncFunction$12(NotificationScheduler notificationScheduler) {
        super(1);
        this.this$0 = notificationScheduler;
    }

    public final Object invoke(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "it");
        String str = objArr[0];
        if (str != null) {
            String str2 = str;
            ReadableArguments readableArguments = objArr[1];
            if (readableArguments != null) {
                ReadableArguments readableArguments2 = readableArguments;
                ReadableArguments readableArguments3 = objArr[2];
                Promise promise = objArr[3];
                if (promise != null) {
                    Promise promise2 = promise;
                    try {
                        NotificationContent build = new ArgumentsNotificationContentBuilder(this.this$0.getSchedulingContext()).setPayload(readableArguments2).build();
                        NotificationScheduler notificationScheduler = this.this$0;
                        Intrinsics.checkNotNull(build);
                        NotificationsService.Companion.schedule(this.this$0.getSchedulingContext(), notificationScheduler.createNotificationRequest(str2, build, this.this$0.triggerFromParams(readableArguments3)), this.this$0.createResultReceiver(new NotificationScheduler$definition$1$2$1(promise2, str2)));
                    } catch (InvalidArgumentException e) {
                        promise2.reject("ERR_NOTIFICATIONS_FAILED_TO_SCHEDULE", "Failed to schedule the notification. " + e.getMessage(), e);
                    } catch (NullPointerException e2) {
                        promise2.reject("ERR_NOTIFICATIONS_FAILED_TO_SCHEDULE", "Failed to schedule the notification. Encountered unexpected null value. " + e2.getMessage(), e2);
                    }
                    return Unit.INSTANCE;
                }
                throw new NullPointerException("null cannot be cast to non-null type expo.modules.kotlin.Promise");
            }
            throw new NullPointerException("null cannot be cast to non-null type expo.modules.core.arguments.ReadableArguments");
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
    }
}

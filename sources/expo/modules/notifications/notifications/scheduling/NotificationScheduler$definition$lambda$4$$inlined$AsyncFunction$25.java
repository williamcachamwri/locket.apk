package expo.modules.notifications.notifications.scheduling;

import expo.modules.core.arguments.ReadableArguments;
import expo.modules.core.errors.InvalidArgumentException;
import expo.modules.kotlin.Promise;
import expo.modules.notifications.notifications.interfaces.NotificationTrigger;
import expo.modules.notifications.notifications.interfaces.SchedulableNotificationTrigger;
import java.util.Arrays;
import java.util.Date;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.StringCompanionObject;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\u0006\b\u0002\u0010\u0004\u0018\u00012\u0010\u0010\u0005\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0006H\n¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"<anonymous>", "", "R", "P0", "P1", "it", "", "invoke", "([Ljava/lang/Object;)Ljava/lang/Object;", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$13"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class NotificationScheduler$definition$lambda$4$$inlined$AsyncFunction$25 extends Lambda implements Function1<Object[], Object> {
    final /* synthetic */ NotificationScheduler this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NotificationScheduler$definition$lambda$4$$inlined$AsyncFunction$25(NotificationScheduler notificationScheduler) {
        super(1);
        this.this$0 = notificationScheduler;
    }

    public final Object invoke(Object[] objArr) {
        String str;
        Intrinsics.checkNotNullParameter(objArr, "it");
        ReadableArguments readableArguments = objArr[0];
        Promise promise = objArr[1];
        if (promise != null) {
            Promise promise2 = promise;
            try {
                NotificationTrigger triggerFromParams = this.this$0.triggerFromParams(readableArguments);
                if (triggerFromParams instanceof SchedulableNotificationTrigger) {
                    Date nextTriggerDate = ((SchedulableNotificationTrigger) triggerFromParams).nextTriggerDate();
                    if (nextTriggerDate == null) {
                        promise2.resolve((Object) null);
                    } else {
                        promise2.resolve(Double.valueOf((double) nextTriggerDate.getTime()));
                    }
                } else {
                    if (triggerFromParams == null) {
                        str = "null";
                    } else {
                        str = triggerFromParams.getClass().getName();
                    }
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String format = String.format("It is not possible to get next trigger date for triggers other than calendar-based. Provided trigger resulted in %s trigger.", Arrays.copyOf(new Object[]{str}, 1));
                    Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                    promise2.reject("ERR_NOTIFICATIONS_INVALID_CALENDAR_TRIGGER", format, (Throwable) null);
                }
            } catch (InvalidArgumentException e) {
                promise2.reject("ERR_NOTIFICATIONS_FAILED_TO_GET_NEXT_TRIGGER_DATE", "Failed to get next trigger date for the trigger. " + e.getMessage(), e);
            } catch (NullPointerException e2) {
                promise2.reject("ERR_NOTIFICATIONS_FAILED_TO_GET_NEXT_TRIGGER_DATE", "Failed to get next trigger date for the trigger. Encountered unexpected null value. " + e2.getMessage(), e2);
            }
            return Unit.INSTANCE;
        }
        throw new NullPointerException("null cannot be cast to non-null type expo.modules.kotlin.Promise");
    }
}

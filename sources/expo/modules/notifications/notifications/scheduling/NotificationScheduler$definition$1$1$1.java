package expo.modules.notifications.notifications.scheduling;

import android.os.Bundle;
import expo.modules.kotlin.Promise;
import expo.modules.notifications.service.NotificationsService;
import java.io.Serializable;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "resultCode", "", "resultData", "Landroid/os/Bundle;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: NotificationScheduler.kt */
final class NotificationScheduler$definition$1$1$1 extends Lambda implements Function2<Integer, Bundle, Unit> {
    final /* synthetic */ Promise $promise;
    final /* synthetic */ NotificationScheduler this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    NotificationScheduler$definition$1$1$1(Promise promise, NotificationScheduler notificationScheduler) {
        super(2);
        this.$promise = promise;
        this.this$0 = notificationScheduler;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke(((Number) obj).intValue(), (Bundle) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(int i, Bundle bundle) {
        Serializable serializable = null;
        if (i == 0) {
            ArrayList parcelableArrayList = bundle != null ? bundle.getParcelableArrayList(NotificationsService.NOTIFICATION_REQUESTS_KEY) : null;
            if (parcelableArrayList == null) {
                this.$promise.reject("ERR_NOTIFICATIONS_FAILED_TO_FETCH", "Failed to fetch scheduled notifications.", (Throwable) null);
            } else {
                this.$promise.resolve(this.this$0.serializeScheduledNotificationRequests(parcelableArrayList));
            }
        } else {
            if (bundle != null) {
                serializable = bundle.getSerializable("exception");
            }
            Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type java.lang.Exception{ kotlin.TypeAliasesKt.Exception }");
            this.$promise.reject("ERR_NOTIFICATIONS_FAILED_TO_FETCH", "Failed to fetch scheduled notifications.", (Exception) serializable);
        }
    }
}

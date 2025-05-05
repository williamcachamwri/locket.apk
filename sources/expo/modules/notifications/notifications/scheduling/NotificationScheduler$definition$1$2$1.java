package expo.modules.notifications.notifications.scheduling;

import android.os.Bundle;
import expo.modules.kotlin.Promise;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "resultCode", "", "resultData", "Landroid/os/Bundle;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: NotificationScheduler.kt */
final class NotificationScheduler$definition$1$2$1 extends Lambda implements Function2<Integer, Bundle, Unit> {
    final /* synthetic */ String $identifier;
    final /* synthetic */ Promise $promise;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    NotificationScheduler$definition$1$2$1(Promise promise, String str) {
        super(2);
        this.$promise = promise;
        this.$identifier = str;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke(((Number) obj).intValue(), (Bundle) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(int i, Bundle bundle) {
        if (i == 0) {
            this.$promise.resolve(this.$identifier);
            return;
        }
        String str = null;
        Serializable serializable = bundle != null ? bundle.getSerializable("exception") : null;
        Exception exc = serializable instanceof Exception ? (Exception) serializable : null;
        Promise promise = this.$promise;
        if (exc != null) {
            str = exc.getMessage();
        }
        promise.reject("ERR_NOTIFICATIONS_FAILED_TO_SCHEDULE", "Failed to schedule the notification. " + str, exc);
    }
}

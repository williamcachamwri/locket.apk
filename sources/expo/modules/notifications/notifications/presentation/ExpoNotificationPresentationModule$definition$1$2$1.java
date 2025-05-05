package expo.modules.notifications.notifications.presentation;

import android.os.Bundle;
import expo.modules.kotlin.Promise;
import expo.modules.notifications.service.NotificationsService;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "resultCode", "", "resultData", "Landroid/os/Bundle;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ExpoNotificationPresentationModule.kt */
final class ExpoNotificationPresentationModule$definition$1$2$1 extends Lambda implements Function2<Integer, Bundle, Unit> {
    final /* synthetic */ Promise $promise;
    final /* synthetic */ ExpoNotificationPresentationModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ExpoNotificationPresentationModule$definition$1$2$1(Promise promise, ExpoNotificationPresentationModule expoNotificationPresentationModule) {
        super(2);
        this.$promise = promise;
        this.this$0 = expoNotificationPresentationModule;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke(((Number) obj).intValue(), (Bundle) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(int i, Bundle bundle) {
        Exception exc = null;
        ArrayList parcelableArrayList = bundle != null ? bundle.getParcelableArrayList(NotificationsService.NOTIFICATIONS_KEY) : null;
        if (i != 0 || parcelableArrayList == null) {
            Object serializable = bundle != null ? bundle.getSerializable("exception") : null;
            if (serializable instanceof Exception) {
                exc = (Exception) serializable;
            }
            this.$promise.reject("ERR_NOTIFICATIONS_FETCH_FAILED", "A list of displayed notifications could not be fetched.", exc);
            return;
        }
        this.$promise.resolve(this.this$0.serializeNotifications(parcelableArrayList));
    }
}

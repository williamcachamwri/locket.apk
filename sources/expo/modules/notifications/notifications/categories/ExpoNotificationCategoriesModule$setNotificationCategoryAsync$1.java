package expo.modules.notifications.notifications.categories;

import android.os.Bundle;
import expo.modules.kotlin.Promise;
import expo.modules.notifications.notifications.model.NotificationCategory;
import expo.modules.notifications.service.NotificationsService;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "resultCode", "", "resultData", "Landroid/os/Bundle;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ExpoNotificationCategoriesModule.kt */
final class ExpoNotificationCategoriesModule$setNotificationCategoryAsync$1 extends Lambda implements Function2<Integer, Bundle, Unit> {
    final /* synthetic */ Promise $promise;
    final /* synthetic */ ExpoNotificationCategoriesModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ExpoNotificationCategoriesModule$setNotificationCategoryAsync$1(Promise promise, ExpoNotificationCategoriesModule expoNotificationCategoriesModule) {
        super(2);
        this.$promise = promise;
        this.this$0 = expoNotificationCategoriesModule;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke(((Number) obj).intValue(), (Bundle) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(int i, Bundle bundle) {
        NotificationCategory notificationCategory = bundle != null ? (NotificationCategory) bundle.getParcelable(NotificationsService.NOTIFICATION_CATEGORY_KEY) : null;
        if (i != 0 || notificationCategory == null) {
            this.$promise.reject("ERR_CATEGORY_SET_FAILED", "The provided category could not be set.", (Throwable) null);
        } else {
            this.$promise.resolve(this.this$0.getSerializer().toBundle(notificationCategory));
        }
    }
}

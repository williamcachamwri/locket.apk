package expo.modules.notifications.notifications.categories;

import android.os.Bundle;
import expo.modules.kotlin.Promise;
import expo.modules.notifications.service.NotificationsService;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "resultCode", "", "resultData", "Landroid/os/Bundle;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ExpoNotificationCategoriesModule.kt */
final class ExpoNotificationCategoriesModule$deleteNotificationCategoryAsync$1 extends Lambda implements Function2<Integer, Bundle, Unit> {
    final /* synthetic */ Promise $promise;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ExpoNotificationCategoriesModule$deleteNotificationCategoryAsync$1(Promise promise) {
        super(2);
        this.$promise = promise;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke(((Number) obj).intValue(), (Bundle) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(int i, Bundle bundle) {
        Boolean bool = null;
        if (i == 0) {
            Promise promise = this.$promise;
            if (bundle != null) {
                bool = Boolean.valueOf(bundle.getBoolean(NotificationsService.SUCCEEDED_KEY));
            }
            promise.resolve(bool);
            return;
        }
        this.$promise.reject("ERR_CATEGORY_DELETE_FAILED", "The category could not be deleted.", (Throwable) null);
    }
}

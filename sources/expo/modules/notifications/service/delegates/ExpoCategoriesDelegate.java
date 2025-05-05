package expo.modules.notifications.service.delegates;

import android.content.Context;
import expo.modules.notifications.notifications.model.NotificationCategory;
import expo.modules.notifications.service.interfaces.CategoriesDelegate;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0016J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0011\u001a\u00020\u000fH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lexpo/modules/notifications/service/delegates/ExpoCategoriesDelegate;", "Lexpo/modules/notifications/service/interfaces/CategoriesDelegate;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "mStore", "Lexpo/modules/notifications/service/delegates/SharedPreferencesNotificationCategoriesStore;", "deleteCategory", "", "identifier", "", "getCategories", "", "Lexpo/modules/notifications/notifications/model/NotificationCategory;", "setCategory", "category", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ExpoCategoriesDelegate.kt */
public final class ExpoCategoriesDelegate implements CategoriesDelegate {
    private final Context context;
    private final SharedPreferencesNotificationCategoriesStore mStore;

    public ExpoCategoriesDelegate(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
        this.mStore = new SharedPreferencesNotificationCategoriesStore(context2);
    }

    /* access modifiers changed from: protected */
    public final Context getContext() {
        return this.context;
    }

    public Collection<NotificationCategory> getCategories() {
        return this.mStore.getAllNotificationCategories();
    }

    public NotificationCategory setCategory(NotificationCategory notificationCategory) {
        Intrinsics.checkNotNullParameter(notificationCategory, "category");
        return this.mStore.saveNotificationCategory(notificationCategory);
    }

    public boolean deleteCategory(String str) {
        Intrinsics.checkNotNullParameter(str, "identifier");
        return this.mStore.removeNotificationCategory(str);
    }
}

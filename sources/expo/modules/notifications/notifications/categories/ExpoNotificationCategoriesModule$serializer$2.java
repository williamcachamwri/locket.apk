package expo.modules.notifications.notifications.categories;

import expo.modules.notifications.ModuleNotFoundException;
import expo.modules.notifications.notifications.categories.serializers.NotificationsCategoriesSerializer;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lexpo/modules/notifications/notifications/categories/serializers/NotificationsCategoriesSerializer;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ExpoNotificationCategoriesModule.kt */
final class ExpoNotificationCategoriesModule$serializer$2 extends Lambda implements Function0<NotificationsCategoriesSerializer> {
    final /* synthetic */ ExpoNotificationCategoriesModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ExpoNotificationCategoriesModule$serializer$2(ExpoNotificationCategoriesModule expoNotificationCategoriesModule) {
        super(0);
        this.this$0 = expoNotificationCategoriesModule;
    }

    public final NotificationsCategoriesSerializer invoke() {
        Object obj;
        try {
            obj = this.this$0.getAppContext().getLegacyModuleRegistry().getModule(NotificationsCategoriesSerializer.class);
        } catch (Exception unused) {
            obj = null;
        }
        NotificationsCategoriesSerializer notificationsCategoriesSerializer = (NotificationsCategoriesSerializer) obj;
        if (notificationsCategoriesSerializer != null) {
            return notificationsCategoriesSerializer;
        }
        throw new ModuleNotFoundException(Reflection.getOrCreateKotlinClass(NotificationsCategoriesSerializer.class));
    }
}

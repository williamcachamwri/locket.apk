package expo.modules.notifications.notifications.categories;

import expo.modules.kotlin.Promise;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\u0006\b\u0002\u0010\u0004\u0018\u0001\"\u0006\b\u0003\u0010\u0005\u0018\u0001\"\u0006\b\u0004\u0010\u0006\u0018\u00012\u0010\u0010\u0007\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\bH\n¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"<anonymous>", "", "R", "P0", "P1", "P2", "P3", "it", "", "invoke", "([Ljava/lang/Object;)Ljava/lang/Object;", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$31"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class ExpoNotificationCategoriesModule$definition$lambda$2$$inlined$AsyncFunction$12 extends Lambda implements Function1<Object[], Object> {
    final /* synthetic */ ExpoNotificationCategoriesModule $receiver$inlined;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExpoNotificationCategoriesModule$definition$lambda$2$$inlined$AsyncFunction$12(ExpoNotificationCategoriesModule expoNotificationCategoriesModule) {
        super(1);
        this.$receiver$inlined = expoNotificationCategoriesModule;
    }

    public final Object invoke(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "it");
        String str = objArr[0];
        if (str != null) {
            String str2 = str;
            List list = objArr[1];
            if (list != null) {
                List list2 = list;
                Map map = objArr[2];
                Promise promise = objArr[3];
                if (promise != null) {
                    this.$receiver$inlined.setNotificationCategoryAsync(str2, list2, map, promise);
                    return Unit.INSTANCE;
                }
                throw new NullPointerException("null cannot be cast to non-null type expo.modules.kotlin.Promise");
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.List<expo.modules.notifications.notifications.categories.NotificationActionRecord>");
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
    }
}

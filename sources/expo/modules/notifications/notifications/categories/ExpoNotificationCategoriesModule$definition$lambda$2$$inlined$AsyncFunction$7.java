package expo.modules.notifications.notifications.categories;

import com.facebook.react.bridge.BaseJavaModule;
import expo.modules.kotlin.Promise;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\u0006\b\u0002\u0010\u0004\u0018\u0001\"\u0006\b\u0003\u0010\u0005\u0018\u0001\"\u0006\b\u0004\u0010\u0006\u0018\u00012\u0010\u0010\u0007\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\n¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"<anonymous>", "", "R", "P0", "P1", "P2", "P3", "args", "", "", "promise", "Lexpo/modules/kotlin/Promise;", "invoke", "([Ljava/lang/Object;Lexpo/modules/kotlin/Promise;)V", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$26"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class ExpoNotificationCategoriesModule$definition$lambda$2$$inlined$AsyncFunction$7 extends Lambda implements Function2<Object[], Promise, Unit> {
    final /* synthetic */ ExpoNotificationCategoriesModule $receiver$inlined;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExpoNotificationCategoriesModule$definition$lambda$2$$inlined$AsyncFunction$7(ExpoNotificationCategoriesModule expoNotificationCategoriesModule) {
        super(2);
        this.$receiver$inlined = expoNotificationCategoriesModule;
    }

    public final void invoke(Object[] objArr, Promise promise) {
        Intrinsics.checkNotNullParameter(objArr, "args");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        String str = objArr[0];
        if (str != null) {
            String str2 = str;
            List list = objArr[1];
            if (list != null) {
                this.$receiver$inlined.setNotificationCategoryAsync(str2, list, objArr[2], promise);
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.List<expo.modules.notifications.notifications.categories.NotificationActionRecord>");
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Object[]) obj, (Promise) obj2);
        return Unit.INSTANCE;
    }
}

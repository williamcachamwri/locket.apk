package expo.modules.notifications.permissions;

import android.os.Build;
import expo.modules.kotlin.Promise;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u00012\u0010\u0010\u0004\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", "R", "P0", "it", "", "invoke", "([Ljava/lang/Object;)Ljava/lang/Object;", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$7"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class NotificationPermissionsModule$definition$lambda$2$$inlined$AsyncFunction$3 extends Lambda implements Function1<Object[], Object> {
    final /* synthetic */ NotificationPermissionsModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NotificationPermissionsModule$definition$lambda$2$$inlined$AsyncFunction$3(NotificationPermissionsModule notificationPermissionsModule) {
        super(1);
        this.this$0 = notificationPermissionsModule;
    }

    public final Object invoke(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "it");
        Promise promise = objArr[0];
        if (promise != null) {
            Promise promise2 = promise;
            if (this.this$0.getContext().getApplicationContext().getApplicationInfo().targetSdkVersion < 33 || Build.VERSION.SDK_INT < 33) {
                this.this$0.getPermissionsWithPromiseImplClassic(promise2);
            } else {
                this.this$0.getPermissionsWithPromiseImplApi33(promise2);
            }
            return Unit.INSTANCE;
        }
        throw new NullPointerException("null cannot be cast to non-null type expo.modules.kotlin.Promise");
    }
}

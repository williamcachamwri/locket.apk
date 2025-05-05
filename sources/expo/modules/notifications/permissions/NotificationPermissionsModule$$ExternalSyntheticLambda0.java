package expo.modules.notifications.permissions;

import expo.modules.interfaces.permissions.PermissionsResponseListener;
import expo.modules.kotlin.Promise;
import java.util.Map;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class NotificationPermissionsModule$$ExternalSyntheticLambda0 implements PermissionsResponseListener {
    public final /* synthetic */ NotificationPermissionsModule f$0;
    public final /* synthetic */ Promise f$1;

    public /* synthetic */ NotificationPermissionsModule$$ExternalSyntheticLambda0(NotificationPermissionsModule notificationPermissionsModule, Promise promise) {
        this.f$0 = notificationPermissionsModule;
        this.f$1 = promise;
    }

    public final void onResult(Map map) {
        NotificationPermissionsModule.getPermissionsWithPromiseImplApi33$lambda$7(this.f$0, this.f$1, map);
    }
}

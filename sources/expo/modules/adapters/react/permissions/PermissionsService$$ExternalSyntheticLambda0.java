package expo.modules.adapters.react.permissions;

import com.facebook.react.modules.core.PermissionListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PermissionsService$$ExternalSyntheticLambda0 implements PermissionListener {
    public final /* synthetic */ PermissionsService f$0;

    public /* synthetic */ PermissionsService$$ExternalSyntheticLambda0(PermissionsService permissionsService) {
        this.f$0 = permissionsService;
    }

    public final boolean onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        return PermissionsService.createListenerWithPendingPermissionsRequest$lambda$21(this.f$0, i, strArr, iArr);
    }
}

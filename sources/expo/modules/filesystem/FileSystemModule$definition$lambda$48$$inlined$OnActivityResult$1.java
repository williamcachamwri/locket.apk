package expo.modules.filesystem;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import expo.modules.interfaces.permissions.PermissionsResponse;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.events.OnActivityResultPayload;
import expo.modules.kotlin.exception.Exceptions;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006¨\u0006\u0007"}, d2 = {"<anonymous>", "", "sender", "Landroid/app/Activity;", "payload", "Lexpo/modules/kotlin/events/OnActivityResultPayload;", "invoke", "expo/modules/kotlin/modules/ModuleDefinitionBuilder$OnActivityResult$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ModuleDefinitionBuilder.kt */
public final class FileSystemModule$definition$lambda$48$$inlined$OnActivityResult$1 extends Lambda implements Function2<Activity, OnActivityResultPayload, Unit> {
    final /* synthetic */ FileSystemModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FileSystemModule$definition$lambda$48$$inlined$OnActivityResult$1(FileSystemModule fileSystemModule) {
        super(2);
        this.this$0 = fileSystemModule;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Activity) obj, (OnActivityResultPayload) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(Activity activity, OnActivityResultPayload onActivityResultPayload) {
        Intrinsics.checkNotNullParameter(activity, "sender");
        Intrinsics.checkNotNullParameter(onActivityResultPayload, "payload");
        int component1 = onActivityResultPayload.component1();
        int component2 = onActivityResultPayload.component2();
        Intent component3 = onActivityResultPayload.component3();
        if (component1 == 5394 && this.this$0.dirPermissionsRequest != null) {
            Activity currentActivity = this.this$0.getAppContext().getCurrentActivity();
            if (currentActivity != null) {
                Bundle bundle = new Bundle();
                if (component2 != -1 || component3 == null) {
                    bundle.putBoolean(PermissionsResponse.GRANTED_KEY, false);
                } else {
                    Uri data = component3.getData();
                    int flags = component3.getFlags() & 3;
                    if (data != null) {
                        currentActivity.getContentResolver().takePersistableUriPermission(data, flags);
                    }
                    bundle.putBoolean(PermissionsResponse.GRANTED_KEY, true);
                    bundle.putString("directoryUri", String.valueOf(data));
                }
                Promise access$getDirPermissionsRequest$p = this.this$0.dirPermissionsRequest;
                if (access$getDirPermissionsRequest$p != null) {
                    access$getDirPermissionsRequest$p.resolve(bundle);
                }
                this.this$0.dirPermissionsRequest = null;
                return;
            }
            throw new Exceptions.MissingActivity();
        }
    }
}

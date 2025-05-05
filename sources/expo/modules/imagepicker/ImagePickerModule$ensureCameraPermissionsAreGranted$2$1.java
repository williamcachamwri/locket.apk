package expo.modules.imagepicker;

import android.os.Build;
import expo.modules.interfaces.permissions.PermissionsResponse;
import expo.modules.interfaces.permissions.PermissionsResponseListener;
import expo.modules.interfaces.permissions.PermissionsStatus;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0000\u0010\u0000\u001a\u00020\u00012F\u0010\u0002\u001aB\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00060\u0006 \u0005* \u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00060\u0006\u0018\u00010\u00070\u0003H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "permissionsResponse", "", "", "kotlin.jvm.PlatformType", "Lexpo/modules/interfaces/permissions/PermissionsResponse;", "", "onResult"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ImagePickerModule.kt */
final class ImagePickerModule$ensureCameraPermissionsAreGranted$2$1 implements PermissionsResponseListener {
    final /* synthetic */ CancellableContinuation<Unit> $continuation;

    ImagePickerModule$ensureCameraPermissionsAreGranted$2$1(CancellableContinuation<? super Unit> cancellableContinuation) {
        this.$continuation = cancellableContinuation;
    }

    public final void onResult(Map<String, PermissionsResponse> map) {
        PermissionsStatus permissionsStatus = null;
        if (Build.VERSION.SDK_INT >= 33) {
            PermissionsResponse permissionsResponse = map.get("android.permission.CAMERA");
            if (permissionsResponse != null) {
                permissionsStatus = permissionsResponse.getStatus();
            }
            if (permissionsStatus == PermissionsStatus.GRANTED) {
                Result.Companion companion = Result.Companion;
                this.$continuation.resumeWith(Result.m2444constructorimpl(Unit.INSTANCE));
                return;
            }
            Result.Companion companion2 = Result.Companion;
            this.$continuation.resumeWith(Result.m2444constructorimpl(ResultKt.createFailure(new UserRejectedPermissionsException())));
            return;
        }
        PermissionsResponse permissionsResponse2 = map.get("android.permission.WRITE_EXTERNAL_STORAGE");
        if ((permissionsResponse2 != null ? permissionsResponse2.getStatus() : null) == PermissionsStatus.GRANTED) {
            PermissionsResponse permissionsResponse3 = map.get("android.permission.CAMERA");
            if (permissionsResponse3 != null) {
                permissionsStatus = permissionsResponse3.getStatus();
            }
            if (permissionsStatus == PermissionsStatus.GRANTED) {
                Result.Companion companion3 = Result.Companion;
                this.$continuation.resumeWith(Result.m2444constructorimpl(Unit.INSTANCE));
                return;
            }
        }
        Result.Companion companion4 = Result.Companion;
        this.$continuation.resumeWith(Result.m2444constructorimpl(ResultKt.createFailure(new UserRejectedPermissionsException())));
    }
}

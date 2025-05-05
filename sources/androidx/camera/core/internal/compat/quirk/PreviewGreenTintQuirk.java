package androidx.camera.core.internal.compat.quirk;

import android.os.Build;
import androidx.camera.core.Preview;
import androidx.camera.core.UseCase;
import androidx.camera.core.impl.Quirk;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0006\u001a\u00020\u0004H\u0007J\u001e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0007J\u001e\u0010\r\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0002R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0005¨\u0006\u000e"}, d2 = {"Landroidx/camera/core/internal/compat/quirk/PreviewGreenTintQuirk;", "Landroidx/camera/core/impl/Quirk;", "()V", "isMotoE20", "", "()Z", "load", "shouldForceEnableStreamSharing", "cameraId", "", "appUseCases", "", "Landroidx/camera/core/UseCase;", "shouldForceEnableStreamSharingForMotoE20", "camera-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: PreviewGreenTintQuirk.kt */
public final class PreviewGreenTintQuirk implements Quirk {
    public static final PreviewGreenTintQuirk INSTANCE = new PreviewGreenTintQuirk();

    private PreviewGreenTintQuirk() {
    }

    private final boolean isMotoE20() {
        if (!StringsKt.equals("motorola", Build.BRAND, true) || !StringsKt.equals("moto e20", Build.MODEL, true)) {
            return false;
        }
        return true;
    }

    @JvmStatic
    public static final boolean load() {
        return INSTANCE.isMotoE20();
    }

    @JvmStatic
    public static final boolean shouldForceEnableStreamSharing(String str, Collection<? extends UseCase> collection) {
        Intrinsics.checkNotNullParameter(str, "cameraId");
        Intrinsics.checkNotNullParameter(collection, "appUseCases");
        PreviewGreenTintQuirk previewGreenTintQuirk = INSTANCE;
        if (previewGreenTintQuirk.isMotoE20()) {
            return previewGreenTintQuirk.shouldForceEnableStreamSharingForMotoE20(str, collection);
        }
        return false;
    }

    private final boolean shouldForceEnableStreamSharingForMotoE20(String str, Collection<? extends UseCase> collection) {
        boolean z;
        boolean z2;
        boolean z3;
        if (!Intrinsics.areEqual((Object) str, (Object) "0") || collection.size() != 2) {
            return false;
        }
        Iterable iterable = collection;
        boolean z4 = iterable instanceof Collection;
        if (!z4 || !((Collection) iterable).isEmpty()) {
            Iterator it = iterable.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (((UseCase) it.next()) instanceof Preview) {
                        z = true;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        z = false;
        if (!z4 || !((Collection) iterable).isEmpty()) {
            Iterator it2 = iterable.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                UseCase useCase = (UseCase) it2.next();
                if (!useCase.getCurrentConfig().containsOption(UseCaseConfig.OPTION_CAPTURE_TYPE) || useCase.getCurrentConfig().getCaptureType() != UseCaseConfigFactory.CaptureType.VIDEO_CAPTURE) {
                    z3 = false;
                    continue;
                } else {
                    z3 = true;
                    continue;
                }
                if (z3) {
                    z2 = true;
                    break;
                }
            }
        }
        z2 = false;
        if (!z || !z2) {
            return false;
        }
        return true;
    }
}

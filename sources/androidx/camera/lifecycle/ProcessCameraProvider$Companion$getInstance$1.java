package androidx.camera.lifecycle;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u00012\u000e\u0010\u0003\u001a\n \u0002*\u0004\u0018\u00010\u00040\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Landroidx/camera/lifecycle/ProcessCameraProvider;", "kotlin.jvm.PlatformType", "it", "Ljava/lang/Void;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: ProcessCameraProvider.kt */
final class ProcessCameraProvider$Companion$getInstance$1 extends Lambda implements Function1<Void, ProcessCameraProvider> {
    public static final ProcessCameraProvider$Companion$getInstance$1 INSTANCE = new ProcessCameraProvider$Companion$getInstance$1();

    ProcessCameraProvider$Companion$getInstance$1() {
        super(1);
    }

    public final ProcessCameraProvider invoke(Void voidR) {
        return ProcessCameraProvider.sAppInstance;
    }
}

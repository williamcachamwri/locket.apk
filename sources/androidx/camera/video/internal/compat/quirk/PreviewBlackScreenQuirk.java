package androidx.camera.video.internal.compat.quirk;

import android.os.Build;
import androidx.camera.core.internal.compat.quirk.SurfaceProcessingQuirk;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Landroidx/camera/video/internal/compat/quirk/PreviewBlackScreenQuirk;", "Landroidx/camera/core/internal/compat/quirk/SurfaceProcessingQuirk;", "()V", "Companion", "camera-video_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: PreviewBlackScreenQuirk.kt */
public final class PreviewBlackScreenQuirk implements SurfaceProcessingQuirk {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final boolean isMotorolaEdge20Fusion;

    @JvmStatic
    public static final boolean load() {
        return Companion.load();
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/camera/video/internal/compat/quirk/PreviewBlackScreenQuirk$Companion;", "", "()V", "isMotorolaEdge20Fusion", "", "load", "camera-video_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: PreviewBlackScreenQuirk.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final boolean load() {
            return PreviewBlackScreenQuirk.isMotorolaEdge20Fusion;
        }
    }

    static {
        boolean z = true;
        if (!StringsKt.equals(Build.BRAND, "motorola", true) || !StringsKt.equals(Build.MODEL, "motorola edge 20 fusion", true)) {
            z = false;
        }
        isMotorolaEdge20Fusion = z;
    }
}

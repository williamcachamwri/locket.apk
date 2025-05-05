package androidx.media3.common;

import android.view.SurfaceView;

public interface DebugViewProvider {
    public static final DebugViewProvider NONE = new DebugViewProvider$$ExternalSyntheticLambda0();

    static /* synthetic */ SurfaceView lambda$static$0(int i, int i2) {
        return null;
    }

    SurfaceView getDebugPreviewSurfaceView(int i, int i2);
}

package androidx.media3.effect;

import androidx.media3.common.util.Size;
import androidx.media3.effect.OverlaySettings;
import java.util.List;

public interface VideoCompositorSettings {
    public static final VideoCompositorSettings DEFAULT = new VideoCompositorSettings() {
        public Size getOutputSize(List<Size> list) {
            return list.get(0);
        }

        public OverlaySettings getOverlaySettings(int i, long j) {
            return new OverlaySettings.Builder().build();
        }
    };

    Size getOutputSize(List<Size> list);

    OverlaySettings getOverlaySettings(int i, long j);
}

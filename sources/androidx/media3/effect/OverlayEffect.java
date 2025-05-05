package androidx.media3.effect;

import android.content.Context;
import androidx.media3.common.VideoFrameProcessingException;
import com.google.common.collect.ImmutableList;
import java.util.List;

public final class OverlayEffect implements GlEffect {
    private final ImmutableList<TextureOverlay> overlays;

    public OverlayEffect(List<TextureOverlay> list) {
        this.overlays = ImmutableList.copyOf(list);
    }

    public BaseGlShaderProgram toGlShaderProgram(Context context, boolean z) throws VideoFrameProcessingException {
        return new OverlayShaderProgram(context, z, this.overlays);
    }
}

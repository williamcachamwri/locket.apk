package androidx.media3.effect;

import androidx.media3.common.GlTextureInfo;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.GlUtil;

public interface GlTextureProducer {

    public interface Listener {
        void onTextureRendered(GlTextureProducer glTextureProducer, GlTextureInfo glTextureInfo, long j, long j2) throws VideoFrameProcessingException, GlUtil.GlException;
    }

    void releaseOutputTexture(long j);
}

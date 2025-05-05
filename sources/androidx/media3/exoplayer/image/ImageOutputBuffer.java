package androidx.media3.exoplayer.image;

import android.graphics.Bitmap;
import androidx.media3.decoder.DecoderOutputBuffer;

public abstract class ImageOutputBuffer extends DecoderOutputBuffer {
    public Bitmap bitmap;

    public void clear() {
        this.bitmap = null;
        super.clear();
    }
}

package androidx.media3.exoplayer.image;

import android.graphics.Bitmap;

public interface ImageOutput {
    public static final ImageOutput NO_OP = new ImageOutput() {
        public void onDisabled() {
        }

        public void onImageAvailable(long j, Bitmap bitmap) {
        }
    };

    void onDisabled();

    void onImageAvailable(long j, Bitmap bitmap);
}

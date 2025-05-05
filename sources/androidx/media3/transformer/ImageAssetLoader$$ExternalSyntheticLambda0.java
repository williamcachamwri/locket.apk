package androidx.media3.transformer;

import android.graphics.Bitmap;
import androidx.media3.common.Format;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ImageAssetLoader$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ ImageAssetLoader f$0;
    public final /* synthetic */ Bitmap f$1;
    public final /* synthetic */ Format f$2;

    public /* synthetic */ ImageAssetLoader$$ExternalSyntheticLambda0(ImageAssetLoader imageAssetLoader, Bitmap bitmap, Format format) {
        this.f$0 = imageAssetLoader;
        this.f$1 = bitmap;
        this.f$2 = format;
    }

    public final void run() {
        this.f$0.m1125lambda$queueBitmapInternal$0$androidxmedia3transformerImageAssetLoader(this.f$1, this.f$2);
    }
}

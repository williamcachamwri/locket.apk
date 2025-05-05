package androidx.media3.transformer;

import android.graphics.Bitmap;
import androidx.media3.common.Format;
import androidx.media3.transformer.ImageAssetLoader;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ImageAssetLoader$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ ImageAssetLoader.AnonymousClass1 f$0;
    public final /* synthetic */ Bitmap f$1;
    public final /* synthetic */ Format f$2;

    public /* synthetic */ ImageAssetLoader$1$$ExternalSyntheticLambda0(ImageAssetLoader.AnonymousClass1 r1, Bitmap bitmap, Format format) {
        this.f$0 = r1;
        this.f$1 = bitmap;
        this.f$2 = format;
    }

    public final void run() {
        this.f$0.m1127lambda$onSuccess$0$androidxmedia3transformerImageAssetLoader$1(this.f$1, this.f$2);
    }
}

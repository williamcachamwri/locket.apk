package androidx.media3.effect;

import android.graphics.Bitmap;
import androidx.media3.common.FrameInfo;
import androidx.media3.common.util.TimestampIterator;
import androidx.media3.effect.VideoFrameProcessingTaskExecutor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BitmapTextureManager$$ExternalSyntheticLambda3 implements VideoFrameProcessingTaskExecutor.Task {
    public final /* synthetic */ BitmapTextureManager f$0;
    public final /* synthetic */ Bitmap f$1;
    public final /* synthetic */ FrameInfo f$2;
    public final /* synthetic */ TimestampIterator f$3;

    public /* synthetic */ BitmapTextureManager$$ExternalSyntheticLambda3(BitmapTextureManager bitmapTextureManager, Bitmap bitmap, FrameInfo frameInfo, TimestampIterator timestampIterator) {
        this.f$0 = bitmapTextureManager;
        this.f$1 = bitmap;
        this.f$2 = frameInfo;
        this.f$3 = timestampIterator;
    }

    public final void run() {
        this.f$0.m410lambda$queueInputBitmap$1$androidxmedia3effectBitmapTextureManager(this.f$1, this.f$2, this.f$3);
    }
}

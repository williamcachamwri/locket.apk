package com.locket.Locket;

import android.graphics.Bitmap;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ImageDownloaderTask$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ ImageDownloaderTask f$0;
    public final /* synthetic */ Bitmap f$1;

    public /* synthetic */ ImageDownloaderTask$$ExternalSyntheticLambda0(ImageDownloaderTask imageDownloaderTask, Bitmap bitmap) {
        this.f$0 = imageDownloaderTask;
        this.f$1 = bitmap;
    }

    public final void run() {
        this.f$0.lambda$onPostExecute$0(this.f$1);
    }
}

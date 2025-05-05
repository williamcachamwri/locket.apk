package com.facebook.imagepipeline.image;

import android.graphics.Bitmap;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.ResourceReleaser;

class DefaultCloseableStaticBitmap extends BaseCloseableStaticBitmap {
    private static final String TAG = "DefaultCloseableStaticBitmap";

    protected DefaultCloseableStaticBitmap(CloseableReference<Bitmap> closeableReference, QualityInfo qualityInfo, int i, int i2) {
        super(closeableReference, qualityInfo, i, i2);
    }

    protected DefaultCloseableStaticBitmap(Bitmap bitmap, ResourceReleaser<Bitmap> resourceReleaser, QualityInfo qualityInfo, int i, int i2) {
        super(bitmap, resourceReleaser, qualityInfo, i, i2);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        if (!isClosed()) {
            FLog.w(TAG, "finalize: %s %x still open.", getClass().getSimpleName(), Integer.valueOf(System.identityHashCode(this)));
            try {
                close();
            } finally {
                super.finalize();
            }
        }
    }
}

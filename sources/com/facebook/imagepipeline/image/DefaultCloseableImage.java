package com.facebook.imagepipeline.image;

import com.facebook.common.logging.FLog;

public abstract class DefaultCloseableImage extends BaseCloseableImage {
    private static final String TAG = "CloseableImage";

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

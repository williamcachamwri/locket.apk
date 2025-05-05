package com.facebook.imagepipeline.datasource;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class BaseBitmapReferenceDataSubscriber extends BaseDataSubscriber<CloseableReference<CloseableImage>> {
    /* access modifiers changed from: protected */
    public abstract void onNewResultImpl(@Nullable CloseableReference<Bitmap> closeableReference);

    public void onNewResultImpl(@Nonnull DataSource<CloseableReference<CloseableImage>> dataSource) {
        if (dataSource.isFinished()) {
            CloseableReference result = dataSource.getResult();
            CloseableReference<Bitmap> cloneUnderlyingBitmapReference = (result == null || !(result.get() instanceof CloseableStaticBitmap)) ? null : ((CloseableStaticBitmap) result.get()).cloneUnderlyingBitmapReference();
            try {
                onNewResultImpl(cloneUnderlyingBitmapReference);
            } finally {
                CloseableReference.closeSafely((CloseableReference<?>) cloneUnderlyingBitmapReference);
                CloseableReference.closeSafely((CloseableReference<?>) result);
            }
        }
    }
}

package com.facebook.imagepipeline.memory;

import android.graphics.Bitmap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.ResourceReleaser;
import com.facebook.imageutils.BitmapUtil;

public class BitmapCounter {
    private int mCount;
    private final int mMaxCount;
    private final int mMaxSize;
    private long mSize;
    private final ResourceReleaser<Bitmap> mUnpooledBitmapsReleaser;

    public BitmapCounter(int i, int i2) {
        boolean z = true;
        Preconditions.checkArgument(Boolean.valueOf(i > 0));
        Preconditions.checkArgument(Boolean.valueOf(i2 <= 0 ? false : z));
        this.mMaxCount = i;
        this.mMaxSize = i2;
        this.mUnpooledBitmapsReleaser = new ResourceReleaser<Bitmap>() {
            public void release(Bitmap bitmap) {
                try {
                    BitmapCounter.this.decrease(bitmap);
                } finally {
                    bitmap.recycle();
                }
            }
        };
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0022, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean increase(android.graphics.Bitmap r10) {
        /*
            r9 = this;
            monitor-enter(r9)
            int r10 = com.facebook.imageutils.BitmapUtil.getSizeInBytes(r10)     // Catch:{ all -> 0x0024 }
            int r0 = r9.mCount     // Catch:{ all -> 0x0024 }
            int r1 = r9.mMaxCount     // Catch:{ all -> 0x0024 }
            if (r0 >= r1) goto L_0x0021
            long r1 = r9.mSize     // Catch:{ all -> 0x0024 }
            long r3 = (long) r10     // Catch:{ all -> 0x0024 }
            long r5 = r1 + r3
            int r10 = r9.mMaxSize     // Catch:{ all -> 0x0024 }
            long r7 = (long) r10     // Catch:{ all -> 0x0024 }
            int r10 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r10 <= 0) goto L_0x0018
            goto L_0x0021
        L_0x0018:
            r10 = 1
            int r0 = r0 + r10
            r9.mCount = r0     // Catch:{ all -> 0x0024 }
            long r1 = r1 + r3
            r9.mSize = r1     // Catch:{ all -> 0x0024 }
            monitor-exit(r9)
            return r10
        L_0x0021:
            monitor-exit(r9)
            r10 = 0
            return r10
        L_0x0024:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.memory.BitmapCounter.increase(android.graphics.Bitmap):boolean");
    }

    public synchronized void decrease(Bitmap bitmap) {
        int sizeInBytes = BitmapUtil.getSizeInBytes(bitmap);
        Preconditions.checkArgument(this.mCount > 0, "No bitmaps registered.");
        long j = (long) sizeInBytes;
        Preconditions.checkArgument(j <= this.mSize, "Bitmap size bigger than the total registered size: %d, %d", Integer.valueOf(sizeInBytes), Long.valueOf(this.mSize));
        this.mSize -= j;
        this.mCount--;
    }

    public synchronized int getCount() {
        return this.mCount;
    }

    public synchronized long getSize() {
        return this.mSize;
    }

    public synchronized int getMaxCount() {
        return this.mMaxCount;
    }

    public synchronized int getMaxSize() {
        return this.mMaxSize;
    }

    public ResourceReleaser<Bitmap> getReleaser() {
        return this.mUnpooledBitmapsReleaser;
    }
}

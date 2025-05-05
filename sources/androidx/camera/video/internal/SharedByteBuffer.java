package androidx.camera.video.internal;

import androidx.camera.core.Logger;
import androidx.core.util.Pair;
import androidx.core.util.Preconditions;
import com.squareup.kotlinpoet.FunSpec;
import java.io.Closeable;
import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

public final class SharedByteBuffer implements Closeable {
    private static final String TAG = "SharedByteBuffer";
    private final Object mCloseLock = new Object();
    private boolean mClosed = false;
    private final Pair<Executor, Runnable> mFinalCloseAction;
    private final int mShareId;
    private final ByteBuffer mSharedByteBuffer;
    private final AtomicInteger mSharedRefCount;

    static SharedByteBuffer newSharedInstance(ByteBuffer byteBuffer, Executor executor, Runnable runnable) {
        return new SharedByteBuffer(((ByteBuffer) Preconditions.checkNotNull(byteBuffer)).asReadOnlyBuffer(), new AtomicInteger(1), new Pair((Executor) Preconditions.checkNotNull(executor), (Runnable) Preconditions.checkNotNull(runnable)), System.identityHashCode(byteBuffer));
    }

    private SharedByteBuffer(ByteBuffer byteBuffer, AtomicInteger atomicInteger, Pair<Executor, Runnable> pair, int i) {
        int i2;
        this.mSharedByteBuffer = byteBuffer;
        this.mSharedRefCount = atomicInteger;
        this.mFinalCloseAction = pair;
        this.mShareId = i;
        if (Logger.isDebugEnabled(TAG) && (i2 = atomicInteger.get()) < 1) {
            throw new AssertionError(String.format(Locale.US, "Cannot create new instance of SharedByteBuffer with invalid ref count %d. Ref count must be >= 1. [%s]", new Object[]{Integer.valueOf(i2), toString()}));
        }
    }

    /* access modifiers changed from: package-private */
    public SharedByteBuffer share() {
        int incrementAndGet;
        AtomicInteger atomicInteger;
        synchronized (this.mCloseLock) {
            checkNotClosed("share()");
            incrementAndGet = this.mSharedRefCount.incrementAndGet();
            atomicInteger = this.mSharedRefCount;
        }
        if (Logger.isDebugEnabled(TAG)) {
            if (incrementAndGet > 1) {
                Logger.d(TAG, String.format(Locale.US, "Ref count incremented: %d [%s]", new Object[]{Integer.valueOf(incrementAndGet), toString()}));
            } else {
                throw new AssertionError("Invalid ref count. share() should always produce a ref count of 2 or more.");
            }
        }
        return new SharedByteBuffer(this.mSharedByteBuffer.asReadOnlyBuffer(), atomicInteger, this.mFinalCloseAction, this.mShareId);
    }

    public void close() {
        closeInternal();
    }

    public ByteBuffer get() {
        ByteBuffer byteBuffer;
        synchronized (this.mCloseLock) {
            checkNotClosed(FunSpec.GETTER);
            byteBuffer = this.mSharedByteBuffer;
        }
        return byteBuffer;
    }

    private void checkNotClosed(String str) {
        if (this.mClosed) {
            throw new IllegalStateException("Cannot call " + str + " on a closed SharedByteBuffer.");
        }
    }

    public String toString() {
        return String.format(Locale.US, "SharedByteBuffer[buf: %s, shareId: 0x%x, instanceId:0x%x]", new Object[]{this.mSharedByteBuffer, Integer.valueOf(this.mShareId), Integer.valueOf(System.identityHashCode(this))});
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        try {
            if (closeInternal()) {
                Logger.w(TAG, String.format(Locale.US, "SharedByteBuffer closed by finalizer, but should have been closed manually with SharedByteBuffer.close() [%s]", new Object[]{toString()}));
            }
        } finally {
            super.finalize();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001a, code lost:
        if (androidx.camera.core.Logger.isDebugEnabled(TAG) == false) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r2 < 0) goto L_0x0038;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        androidx.camera.core.Logger.d(TAG, java.lang.String.format(java.util.Locale.US, "Ref count decremented: %d [%s]", new java.lang.Object[]{java.lang.Integer.valueOf(r2), toString()}));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003f, code lost:
        throw new java.lang.AssertionError("Invalid ref count. close() should never produce a ref count below 0");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0040, code lost:
        if (r2 != 0) goto L_0x0091;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0048, code lost:
        if (androidx.camera.core.Logger.isDebugEnabled(TAG) == false) goto L_0x005f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004a, code lost:
        androidx.camera.core.Logger.d(TAG, java.lang.String.format(java.util.Locale.US, "Final reference released. Running final close action. [%s]", new java.lang.Object[]{toString()}));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        ((java.util.concurrent.Executor) androidx.core.util.Preconditions.checkNotNull((java.util.concurrent.Executor) r7.mFinalCloseAction.first)).execute((java.lang.Runnable) androidx.core.util.Preconditions.checkNotNull((java.lang.Runnable) r7.mFinalCloseAction.second));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x007b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x007c, code lost:
        androidx.camera.core.Logger.e(TAG, java.lang.String.format(java.util.Locale.US, "Unable to execute final close action. [%s]", new java.lang.Object[]{toString()}), r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean closeInternal() {
        /*
            r7 = this;
            java.lang.Object r0 = r7.mCloseLock
            monitor-enter(r0)
            boolean r1 = r7.mClosed     // Catch:{ all -> 0x0092 }
            if (r1 == 0) goto L_0x000a
            monitor-exit(r0)     // Catch:{ all -> 0x0092 }
            r0 = 0
            return r0
        L_0x000a:
            r1 = 1
            r7.mClosed = r1     // Catch:{ all -> 0x0092 }
            java.util.concurrent.atomic.AtomicInteger r2 = r7.mSharedRefCount     // Catch:{ all -> 0x0092 }
            int r2 = r2.decrementAndGet()     // Catch:{ all -> 0x0092 }
            monitor-exit(r0)     // Catch:{ all -> 0x0092 }
            java.lang.String r0 = "SharedByteBuffer"
            boolean r0 = androidx.camera.core.Logger.isDebugEnabled(r0)
            if (r0 == 0) goto L_0x0040
            if (r2 < 0) goto L_0x0038
            java.lang.String r0 = "SharedByteBuffer"
            java.util.Locale r3 = java.util.Locale.US
            java.lang.String r4 = "Ref count decremented: %d [%s]"
            java.lang.Integer r5 = java.lang.Integer.valueOf(r2)
            java.lang.String r6 = r7.toString()
            java.lang.Object[] r5 = new java.lang.Object[]{r5, r6}
            java.lang.String r3 = java.lang.String.format(r3, r4, r5)
            androidx.camera.core.Logger.d(r0, r3)
            goto L_0x0040
        L_0x0038:
            java.lang.AssertionError r0 = new java.lang.AssertionError
            java.lang.String r1 = "Invalid ref count. close() should never produce a ref count below 0"
            r0.<init>(r1)
            throw r0
        L_0x0040:
            if (r2 != 0) goto L_0x0091
            java.lang.String r0 = "SharedByteBuffer"
            boolean r0 = androidx.camera.core.Logger.isDebugEnabled(r0)
            if (r0 == 0) goto L_0x005f
            java.lang.String r0 = "SharedByteBuffer"
            java.util.Locale r2 = java.util.Locale.US
            java.lang.String r3 = "Final reference released. Running final close action. [%s]"
            java.lang.String r4 = r7.toString()
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            java.lang.String r2 = java.lang.String.format(r2, r3, r4)
            androidx.camera.core.Logger.d(r0, r2)
        L_0x005f:
            androidx.core.util.Pair<java.util.concurrent.Executor, java.lang.Runnable> r0 = r7.mFinalCloseAction     // Catch:{ RejectedExecutionException -> 0x007b }
            F r0 = r0.first     // Catch:{ RejectedExecutionException -> 0x007b }
            java.util.concurrent.Executor r0 = (java.util.concurrent.Executor) r0     // Catch:{ RejectedExecutionException -> 0x007b }
            java.lang.Object r0 = androidx.core.util.Preconditions.checkNotNull(r0)     // Catch:{ RejectedExecutionException -> 0x007b }
            java.util.concurrent.Executor r0 = (java.util.concurrent.Executor) r0     // Catch:{ RejectedExecutionException -> 0x007b }
            androidx.core.util.Pair<java.util.concurrent.Executor, java.lang.Runnable> r2 = r7.mFinalCloseAction     // Catch:{ RejectedExecutionException -> 0x007b }
            S r2 = r2.second     // Catch:{ RejectedExecutionException -> 0x007b }
            java.lang.Runnable r2 = (java.lang.Runnable) r2     // Catch:{ RejectedExecutionException -> 0x007b }
            java.lang.Object r2 = androidx.core.util.Preconditions.checkNotNull(r2)     // Catch:{ RejectedExecutionException -> 0x007b }
            java.lang.Runnable r2 = (java.lang.Runnable) r2     // Catch:{ RejectedExecutionException -> 0x007b }
            r0.execute(r2)     // Catch:{ RejectedExecutionException -> 0x007b }
            goto L_0x0091
        L_0x007b:
            r0 = move-exception
            java.lang.String r2 = "SharedByteBuffer"
            java.util.Locale r3 = java.util.Locale.US
            java.lang.String r4 = "Unable to execute final close action. [%s]"
            java.lang.String r5 = r7.toString()
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            java.lang.String r3 = java.lang.String.format(r3, r4, r5)
            androidx.camera.core.Logger.e(r2, r3, r0)
        L_0x0091:
            return r1
        L_0x0092:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0092 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.video.internal.SharedByteBuffer.closeInternal():boolean");
    }
}

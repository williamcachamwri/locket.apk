package com.facebook.common.references;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;
import javax.annotation.Nullable;

public class DefaultCloseableReference<T> extends CloseableReference<T> {
    private static final String TAG = "DefaultCloseableReference";

    private DefaultCloseableReference(SharedReference<T> sharedReference, @Nullable CloseableReference.LeakHandler leakHandler, @Nullable Throwable th) {
        super(sharedReference, leakHandler, th);
    }

    DefaultCloseableReference(T t, ResourceReleaser<T> resourceReleaser, CloseableReference.LeakHandler leakHandler, @Nullable Throwable th) {
        super(t, resourceReleaser, leakHandler, th, true);
    }

    public CloseableReference<T> clone() {
        Preconditions.checkState(isValid());
        return new DefaultCloseableReference(this.mSharedReference, this.mLeakHandler, this.mStacktrace != null ? new Throwable() : null);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r0 = r6.mSharedReference.get();
        r3 = new java.lang.Object[3];
        r3[0] = java.lang.Integer.valueOf(java.lang.System.identityHashCode(r6));
        r3[1] = java.lang.Integer.valueOf(java.lang.System.identityHashCode(r6.mSharedReference));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0030, code lost:
        if (r0 != null) goto L_0x0034;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0032, code lost:
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0034, code lost:
        r0 = r0.getClass().getName();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003c, code lost:
        r3[2] = r0;
        com.facebook.common.logging.FLog.w(TAG, "Finalized without closing: %x %x (type = %s)", r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0044, code lost:
        if (r6.mLeakHandler == null) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0046, code lost:
        r6.mLeakHandler.reportLeak(r6.mSharedReference, r6.mStacktrace);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004f, code lost:
        close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0052, code lost:
        super.finalize();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0055, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void finalize() throws java.lang.Throwable {
        /*
            r6 = this;
            monitor-enter(r6)     // Catch:{ all -> 0x0059 }
            boolean r0 = r6.mIsClosed     // Catch:{ all -> 0x0056 }
            if (r0 == 0) goto L_0x000a
            monitor-exit(r6)     // Catch:{ all -> 0x0056 }
            super.finalize()
            return
        L_0x000a:
            monitor-exit(r6)     // Catch:{ all -> 0x0056 }
            com.facebook.common.references.SharedReference r0 = r6.mSharedReference     // Catch:{ all -> 0x0059 }
            java.lang.Object r0 = r0.get()     // Catch:{ all -> 0x0059 }
            java.lang.String r1 = "DefaultCloseableReference"
            java.lang.String r2 = "Finalized without closing: %x %x (type = %s)"
            r3 = 3
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0059 }
            int r4 = java.lang.System.identityHashCode(r6)     // Catch:{ all -> 0x0059 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x0059 }
            r5 = 0
            r3[r5] = r4     // Catch:{ all -> 0x0059 }
            com.facebook.common.references.SharedReference r4 = r6.mSharedReference     // Catch:{ all -> 0x0059 }
            int r4 = java.lang.System.identityHashCode(r4)     // Catch:{ all -> 0x0059 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x0059 }
            r5 = 1
            r3[r5] = r4     // Catch:{ all -> 0x0059 }
            if (r0 != 0) goto L_0x0034
            r0 = 0
            goto L_0x003c
        L_0x0034:
            java.lang.Class r0 = r0.getClass()     // Catch:{ all -> 0x0059 }
            java.lang.String r0 = r0.getName()     // Catch:{ all -> 0x0059 }
        L_0x003c:
            r4 = 2
            r3[r4] = r0     // Catch:{ all -> 0x0059 }
            com.facebook.common.logging.FLog.w((java.lang.String) r1, (java.lang.String) r2, (java.lang.Object[]) r3)     // Catch:{ all -> 0x0059 }
            com.facebook.common.references.CloseableReference$LeakHandler r0 = r6.mLeakHandler     // Catch:{ all -> 0x0059 }
            if (r0 == 0) goto L_0x004f
            com.facebook.common.references.CloseableReference$LeakHandler r0 = r6.mLeakHandler     // Catch:{ all -> 0x0059 }
            com.facebook.common.references.SharedReference r1 = r6.mSharedReference     // Catch:{ all -> 0x0059 }
            java.lang.Throwable r2 = r6.mStacktrace     // Catch:{ all -> 0x0059 }
            r0.reportLeak(r1, r2)     // Catch:{ all -> 0x0059 }
        L_0x004f:
            r6.close()     // Catch:{ all -> 0x0059 }
            super.finalize()
            return
        L_0x0056:
            r0 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x0056 }
            throw r0     // Catch:{ all -> 0x0059 }
        L_0x0059:
            r0 = move-exception
            super.finalize()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.common.references.DefaultCloseableReference.finalize():void");
    }
}

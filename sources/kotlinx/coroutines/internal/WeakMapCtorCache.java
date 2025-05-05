package kotlinx.coroutines.internal;

import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J/\u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\t0\u000bj\u0002`\n2\u000e\u0010\r\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\bH\u0016¢\u0006\u0002\u0010\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R4\u0010\u0006\u001a(\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\t0\b\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\t0\u000bj\u0002`\n0\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lkotlinx/coroutines/internal/WeakMapCtorCache;", "Lkotlinx/coroutines/internal/CtorCache;", "<init>", "()V", "cacheLock", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "exceptionCtors", "Ljava/util/WeakHashMap;", "Ljava/lang/Class;", "", "Lkotlinx/coroutines/internal/Ctor;", "Lkotlin/Function1;", "get", "key", "(Ljava/lang/Class;)Lkotlin/jvm/functions/Function1;", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: ExceptionsConstructor.kt */
final class WeakMapCtorCache extends CtorCache {
    public static final WeakMapCtorCache INSTANCE = new WeakMapCtorCache();
    private static final ReentrantReadWriteLock cacheLock = new ReentrantReadWriteLock();
    private static final WeakHashMap<Class<? extends Throwable>, Function1<Throwable, Throwable>> exceptionCtors = new WeakHashMap<>();

    private WeakMapCtorCache() {
    }

    /*  JADX ERROR: StackOverflow in pass: MarkFinallyVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public kotlin.jvm.functions.Function1<java.lang.Throwable, java.lang.Throwable> get(java.lang.Class<? extends java.lang.Throwable> r7) {
        /*
            r6 = this;
            java.util.concurrent.locks.ReentrantReadWriteLock r0 = cacheLock
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r1 = r0.readLock()
            r1.lock()
            java.util.WeakHashMap<java.lang.Class<? extends java.lang.Throwable>, kotlin.jvm.functions.Function1<java.lang.Throwable, java.lang.Throwable>> r2 = exceptionCtors     // Catch:{ all -> 0x0070 }
            java.lang.Object r2 = r2.get(r7)     // Catch:{ all -> 0x0070 }
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2     // Catch:{ all -> 0x0070 }
            r1.unlock()
            if (r2 == 0) goto L_0x0017
            return r2
        L_0x0017:
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r1 = r0.readLock()
            int r2 = r0.getWriteHoldCount()
            r3 = 0
            if (r2 != 0) goto L_0x0027
            int r2 = r0.getReadHoldCount()
            goto L_0x0028
        L_0x0027:
            r2 = r3
        L_0x0028:
            r4 = r3
        L_0x0029:
            if (r4 >= r2) goto L_0x0031
            r1.unlock()
            int r4 = r4 + 1
            goto L_0x0029
        L_0x0031:
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r0 = r0.writeLock()
            r0.lock()
            java.util.WeakHashMap<java.lang.Class<? extends java.lang.Throwable>, kotlin.jvm.functions.Function1<java.lang.Throwable, java.lang.Throwable>> r4 = exceptionCtors     // Catch:{ all -> 0x0063 }
            java.lang.Object r5 = r4.get(r7)     // Catch:{ all -> 0x0063 }
            kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5     // Catch:{ all -> 0x0063 }
            if (r5 == 0) goto L_0x004e
        L_0x0042:
            if (r3 >= r2) goto L_0x004a
            r1.lock()
            int r3 = r3 + 1
            goto L_0x0042
        L_0x004a:
            r0.unlock()
            return r5
        L_0x004e:
            kotlin.jvm.functions.Function1 r5 = kotlinx.coroutines.internal.ExceptionsConstructorKt.createConstructor(r7)     // Catch:{ all -> 0x0063 }
            java.util.Map r4 = (java.util.Map) r4     // Catch:{ all -> 0x0063 }
            r4.put(r7, r5)     // Catch:{ all -> 0x0063 }
        L_0x0057:
            if (r3 >= r2) goto L_0x005f
            r1.lock()
            int r3 = r3 + 1
            goto L_0x0057
        L_0x005f:
            r0.unlock()
            return r5
        L_0x0063:
            r7 = move-exception
        L_0x0064:
            if (r3 >= r2) goto L_0x006c
            r1.lock()
            int r3 = r3 + 1
            goto L_0x0064
        L_0x006c:
            r0.unlock()
            throw r7
        L_0x0070:
            r7 = move-exception
            r1.unlock()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.WeakMapCtorCache.get(java.lang.Class):kotlin.jvm.functions.Function1");
    }
}

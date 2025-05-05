package com.facebook.react.runtime;

import com.facebook.infer.annotation.Assertions;

class BridgelessAtomicRef<T> {
    private volatile String failureMessage;
    T mInitialValue;
    volatile T mValue;
    private volatile State state;

    interface Provider<T> {
        T get();
    }

    enum State {
        Init,
        Creating,
        Success,
        Failure
    }

    public BridgelessAtomicRef(T t) {
        this.mValue = t;
        this.mInitialValue = t;
        this.state = State.Init;
        this.failureMessage = "";
    }

    public BridgelessAtomicRef() {
        this((Object) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0025, code lost:
        if (r0 == false) goto L_0x005f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r4.mValue = r5.get();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002d, code lost:
        monitor-enter(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r4.state = com.facebook.react.runtime.BridgelessAtomicRef.State.Success;
        notifyAll();
        r5 = get();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0039, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003a, code lost:
        return r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x003e, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x003f, code lost:
        monitor-enter(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        r4.state = com.facebook.react.runtime.BridgelessAtomicRef.State.Failure;
        r4.failureMessage = java.util.Objects.toString(r5.getMessage(), "null");
        notifyAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x005b, code lost:
        throw new java.lang.RuntimeException("BridgelessAtomicRef: Failed to create object.", r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x005f, code lost:
        monitor-enter(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0064, code lost:
        if (r4.state != com.facebook.react.runtime.BridgelessAtomicRef.State.Creating) goto L_0x006c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        wait();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x006a, code lost:
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x006c, code lost:
        if (r3 == false) goto L_0x0075;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        java.lang.Thread.currentThread().interrupt();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0079, code lost:
        if (r4.state == com.facebook.react.runtime.BridgelessAtomicRef.State.Failure) goto L_0x0081;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x007b, code lost:
        r5 = get();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x007f, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0080, code lost:
        return r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x009b, code lost:
        throw new java.lang.RuntimeException("BridgelessAtomicRef: Failed to create object. Reason: " + r4.failureMessage);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public T getOrCreate(com.facebook.react.runtime.BridgelessAtomicRef.Provider<T> r5) {
        /*
            r4 = this;
            java.lang.String r0 = "BridgelessAtomicRef: Failed to create object. Reason: "
            monitor-enter(r4)
            com.facebook.react.runtime.BridgelessAtomicRef$State r1 = r4.state     // Catch:{ all -> 0x00b4 }
            com.facebook.react.runtime.BridgelessAtomicRef$State r2 = com.facebook.react.runtime.BridgelessAtomicRef.State.Success     // Catch:{ all -> 0x00b4 }
            if (r1 != r2) goto L_0x000f
            java.lang.Object r5 = r4.get()     // Catch:{ all -> 0x00b4 }
            monitor-exit(r4)     // Catch:{ all -> 0x00b4 }
            return r5
        L_0x000f:
            com.facebook.react.runtime.BridgelessAtomicRef$State r1 = r4.state     // Catch:{ all -> 0x00b4 }
            com.facebook.react.runtime.BridgelessAtomicRef$State r2 = com.facebook.react.runtime.BridgelessAtomicRef.State.Failure     // Catch:{ all -> 0x00b4 }
            if (r1 == r2) goto L_0x009f
            com.facebook.react.runtime.BridgelessAtomicRef$State r0 = r4.state     // Catch:{ all -> 0x00b4 }
            com.facebook.react.runtime.BridgelessAtomicRef$State r1 = com.facebook.react.runtime.BridgelessAtomicRef.State.Creating     // Catch:{ all -> 0x00b4 }
            r2 = 1
            r3 = 0
            if (r0 == r1) goto L_0x0023
            com.facebook.react.runtime.BridgelessAtomicRef$State r0 = com.facebook.react.runtime.BridgelessAtomicRef.State.Creating     // Catch:{ all -> 0x00b4 }
            r4.state = r0     // Catch:{ all -> 0x00b4 }
            r0 = r2
            goto L_0x0024
        L_0x0023:
            r0 = r3
        L_0x0024:
            monitor-exit(r4)     // Catch:{ all -> 0x00b4 }
            if (r0 == 0) goto L_0x005f
            java.lang.Object r5 = r5.get()     // Catch:{ RuntimeException -> 0x003e }
            r4.mValue = r5     // Catch:{ RuntimeException -> 0x003e }
            monitor-enter(r4)     // Catch:{ RuntimeException -> 0x003e }
            com.facebook.react.runtime.BridgelessAtomicRef$State r5 = com.facebook.react.runtime.BridgelessAtomicRef.State.Success     // Catch:{ all -> 0x003b }
            r4.state = r5     // Catch:{ all -> 0x003b }
            r4.notifyAll()     // Catch:{ all -> 0x003b }
            java.lang.Object r5 = r4.get()     // Catch:{ all -> 0x003b }
            monitor-exit(r4)     // Catch:{ all -> 0x003b }
            return r5
        L_0x003b:
            r5 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x003b }
            throw r5     // Catch:{ RuntimeException -> 0x003e }
        L_0x003e:
            r5 = move-exception
            monitor-enter(r4)
            com.facebook.react.runtime.BridgelessAtomicRef$State r0 = com.facebook.react.runtime.BridgelessAtomicRef.State.Failure     // Catch:{ all -> 0x005c }
            r4.state = r0     // Catch:{ all -> 0x005c }
            java.lang.String r0 = r5.getMessage()     // Catch:{ all -> 0x005c }
            java.lang.String r1 = "null"
            java.lang.String r0 = java.util.Objects.toString(r0, r1)     // Catch:{ all -> 0x005c }
            r4.failureMessage = r0     // Catch:{ all -> 0x005c }
            r4.notifyAll()     // Catch:{ all -> 0x005c }
            monitor-exit(r4)     // Catch:{ all -> 0x005c }
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.String r1 = "BridgelessAtomicRef: Failed to create object."
            r0.<init>(r1, r5)
            throw r0
        L_0x005c:
            r5 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x005c }
            throw r5
        L_0x005f:
            monitor-enter(r4)
        L_0x0060:
            com.facebook.react.runtime.BridgelessAtomicRef$State r5 = r4.state     // Catch:{ all -> 0x009c }
            com.facebook.react.runtime.BridgelessAtomicRef$State r0 = com.facebook.react.runtime.BridgelessAtomicRef.State.Creating     // Catch:{ all -> 0x009c }
            if (r5 != r0) goto L_0x006c
            r4.wait()     // Catch:{ InterruptedException -> 0x006a }
            goto L_0x0060
        L_0x006a:
            r3 = r2
            goto L_0x0060
        L_0x006c:
            if (r3 == 0) goto L_0x0075
            java.lang.Thread r5 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x009c }
            r5.interrupt()     // Catch:{ all -> 0x009c }
        L_0x0075:
            com.facebook.react.runtime.BridgelessAtomicRef$State r5 = r4.state     // Catch:{ all -> 0x009c }
            com.facebook.react.runtime.BridgelessAtomicRef$State r0 = com.facebook.react.runtime.BridgelessAtomicRef.State.Failure     // Catch:{ all -> 0x009c }
            if (r5 == r0) goto L_0x0081
            java.lang.Object r5 = r4.get()     // Catch:{ all -> 0x009c }
            monitor-exit(r4)     // Catch:{ all -> 0x009c }
            return r5
        L_0x0081:
            java.lang.RuntimeException r5 = new java.lang.RuntimeException     // Catch:{ all -> 0x009c }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x009c }
            r0.<init>()     // Catch:{ all -> 0x009c }
            java.lang.String r1 = "BridgelessAtomicRef: Failed to create object. Reason: "
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x009c }
            java.lang.String r1 = r4.failureMessage     // Catch:{ all -> 0x009c }
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x009c }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x009c }
            r5.<init>(r0)     // Catch:{ all -> 0x009c }
            throw r5     // Catch:{ all -> 0x009c }
        L_0x009c:
            r5 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x009c }
            throw r5
        L_0x009f:
            java.lang.RuntimeException r5 = new java.lang.RuntimeException     // Catch:{ all -> 0x00b4 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b4 }
            r1.<init>(r0)     // Catch:{ all -> 0x00b4 }
            java.lang.String r0 = r4.failureMessage     // Catch:{ all -> 0x00b4 }
            java.lang.StringBuilder r0 = r1.append(r0)     // Catch:{ all -> 0x00b4 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00b4 }
            r5.<init>(r0)     // Catch:{ all -> 0x00b4 }
            throw r5     // Catch:{ all -> 0x00b4 }
        L_0x00b4:
            r5 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x00b4 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.runtime.BridgelessAtomicRef.getOrCreate(com.facebook.react.runtime.BridgelessAtomicRef$Provider):java.lang.Object");
    }

    public synchronized T getAndReset() {
        T t;
        t = get();
        reset();
        return t;
    }

    public synchronized void reset() {
        this.mValue = this.mInitialValue;
        this.state = State.Init;
        this.failureMessage = "";
    }

    public synchronized T get() {
        return Assertions.assertNotNull(this.mValue);
    }

    public synchronized T getNullable() {
        return this.mValue;
    }
}

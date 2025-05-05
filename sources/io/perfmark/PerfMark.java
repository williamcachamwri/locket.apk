package io.perfmark;

public final class PerfMark {
    private static final Impl impl;

    public static boolean setEnabled(boolean z) {
        return impl.setEnabled(z, false);
    }

    public static void startTask(String str, Tag tag) {
        impl.startTask(str, tag);
    }

    public static void startTask(String str) {
        impl.startTask(str);
    }

    public static <T> void startTask(T t, StringFunction<? super T> stringFunction) {
        impl.startTask(t, stringFunction);
    }

    public static void startTask(String str, String str2) {
        impl.startTask(str, str2);
    }

    public static TaskCloseable traceTask(String str) {
        impl.startTask(str);
        return TaskCloseable.INSTANCE;
    }

    public static <T> TaskCloseable traceTask(T t, StringFunction<? super T> stringFunction) {
        impl.startTask(t, stringFunction);
        return TaskCloseable.INSTANCE;
    }

    public static void event(String str, Tag tag) {
        impl.event(str, tag);
    }

    public static void event(String str) {
        impl.event(str);
    }

    public static void event(String str, String str2) {
        impl.event(str, str2);
    }

    public static void stopTask() {
        impl.stopTask();
    }

    public static void stopTask(String str, Tag tag) {
        impl.stopTask(str, tag);
    }

    public static void stopTask(String str) {
        impl.stopTask(str);
    }

    public static void stopTask(String str, String str2) {
        impl.stopTask(str, str2);
    }

    public static Tag createTag() {
        return Impl.NO_TAG;
    }

    public static Tag createTag(long j) {
        return impl.createTag("", j);
    }

    public static Tag createTag(String str) {
        return impl.createTag(str, Long.MIN_VALUE);
    }

    public static Tag createTag(String str, long j) {
        return impl.createTag(str, j);
    }

    @Deprecated
    public static Link link() {
        return Impl.NO_LINK;
    }

    public static Link linkOut() {
        return impl.linkOut();
    }

    public static void linkIn(Link link) {
        impl.linkIn(link);
    }

    public static void attachTag(Tag tag) {
        impl.attachTag(tag);
    }

    public static void attachTag(String str, String str2) {
        impl.attachTag(str, str2);
    }

    public static void attachTag(String str, long j) {
        impl.attachTag(str, j);
    }

    public static void attachTag(String str, long j, long j2) {
        impl.attachTag(str, j, j2);
    }

    public static <T> void attachTag(String str, T t, StringFunction<? super T> stringFunction) {
        impl.attachTag(str, t, stringFunction);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    static {
        /*
            r0 = 0
            java.lang.String r1 = "io.perfmark.impl.SecretPerfMarkImpl$PerfMarkImpl"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ all -> 0x0009 }
            r2 = r0
            goto L_0x000c
        L_0x0009:
            r1 = move-exception
            r2 = r1
            r1 = r0
        L_0x000c:
            r3 = 0
            r4 = 1
            if (r1 == 0) goto L_0x002f
            java.lang.Class<io.perfmark.Impl> r5 = io.perfmark.Impl.class
            java.lang.Class r1 = r1.asSubclass(r5)     // Catch:{ all -> 0x002d }
            java.lang.Class[] r5 = new java.lang.Class[r4]     // Catch:{ all -> 0x002d }
            java.lang.Class<io.perfmark.Tag> r6 = io.perfmark.Tag.class
            r5[r3] = r6     // Catch:{ all -> 0x002d }
            java.lang.reflect.Constructor r1 = r1.getConstructor(r5)     // Catch:{ all -> 0x002d }
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ all -> 0x002d }
            io.perfmark.Tag r6 = io.perfmark.Impl.NO_TAG     // Catch:{ all -> 0x002d }
            r5[r3] = r6     // Catch:{ all -> 0x002d }
            java.lang.Object r1 = r1.newInstance(r5)     // Catch:{ all -> 0x002d }
            io.perfmark.Impl r1 = (io.perfmark.Impl) r1     // Catch:{ all -> 0x002d }
            goto L_0x0030
        L_0x002d:
            r1 = move-exception
            r2 = r1
        L_0x002f:
            r1 = r0
        L_0x0030:
            if (r1 == 0) goto L_0x0035
            impl = r1
            goto L_0x003e
        L_0x0035:
            io.perfmark.Impl r1 = new io.perfmark.Impl
            io.perfmark.Tag r5 = io.perfmark.Impl.NO_TAG
            r1.<init>(r5)
            impl = r1
        L_0x003e:
            if (r2 == 0) goto L_0x0099
            java.lang.String r1 = "io.perfmark.PerfMark.debug"
            boolean r1 = java.lang.Boolean.getBoolean(r1)     // Catch:{ all -> 0x0099 }
            if (r1 == 0) goto L_0x0099
            java.lang.String r1 = "java.util.logging.Logger"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ all -> 0x0099 }
            java.lang.String r5 = "getLogger"
            java.lang.Class[] r6 = new java.lang.Class[r4]     // Catch:{ all -> 0x0099 }
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r6[r3] = r7     // Catch:{ all -> 0x0099 }
            java.lang.reflect.Method r5 = r1.getMethod(r5, r6)     // Catch:{ all -> 0x0099 }
            java.lang.Object[] r6 = new java.lang.Object[r4]     // Catch:{ all -> 0x0099 }
            java.lang.Class<io.perfmark.PerfMark> r7 = io.perfmark.PerfMark.class
            java.lang.String r7 = r7.getName()     // Catch:{ all -> 0x0099 }
            r6[r3] = r7     // Catch:{ all -> 0x0099 }
            java.lang.Object r5 = r5.invoke(r0, r6)     // Catch:{ all -> 0x0099 }
            java.lang.String r6 = "java.util.logging.Level"
            java.lang.Class r6 = java.lang.Class.forName(r6)     // Catch:{ all -> 0x0099 }
            java.lang.String r7 = "FINE"
            java.lang.reflect.Field r7 = r6.getField(r7)     // Catch:{ all -> 0x0099 }
            java.lang.Object r0 = r7.get(r0)     // Catch:{ all -> 0x0099 }
            java.lang.String r7 = "log"
            r8 = 3
            java.lang.Class[] r9 = new java.lang.Class[r8]     // Catch:{ all -> 0x0099 }
            r9[r3] = r6     // Catch:{ all -> 0x0099 }
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            r9[r4] = r6     // Catch:{ all -> 0x0099 }
            java.lang.Class<java.lang.Throwable> r6 = java.lang.Throwable.class
            r10 = 2
            r9[r10] = r6     // Catch:{ all -> 0x0099 }
            java.lang.reflect.Method r1 = r1.getMethod(r7, r9)     // Catch:{ all -> 0x0099 }
            java.lang.Object[] r6 = new java.lang.Object[r8]     // Catch:{ all -> 0x0099 }
            r6[r3] = r0     // Catch:{ all -> 0x0099 }
            java.lang.String r0 = "Error during PerfMark.<clinit>"
            r6[r4] = r0     // Catch:{ all -> 0x0099 }
            r6[r10] = r2     // Catch:{ all -> 0x0099 }
            r1.invoke(r5, r6)     // Catch:{ all -> 0x0099 }
        L_0x0099:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.perfmark.PerfMark.<clinit>():void");
    }

    private PerfMark() {
    }
}

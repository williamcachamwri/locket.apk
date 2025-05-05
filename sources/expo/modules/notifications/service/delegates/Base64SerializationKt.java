package expo.modules.notifications.service.delegates;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0012\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002H\b¢\u0006\u0002\u0010\u0003\u001a\n\u0010\u0004\u001a\u00020\u0002*\u00020\u0005¨\u0006\u0006"}, d2 = {"asBase64EncodedObject", "T", "", "(Ljava/lang/String;)Ljava/lang/Object;", "encodedInBase64", "Ljava/io/Serializable;", "expo-notifications_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: Base64Serialization.kt */
public final class Base64SerializationKt {
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0037, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r2, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003b, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003e, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003f, code lost:
        kotlin.io.CloseableKt.closeFinally(r0, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0042, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.String encodedInBase64(java.io.Serializable r4) throws java.io.IOException {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            java.io.Closeable r0 = (java.io.Closeable) r0
            r1 = r0
            java.io.ByteArrayOutputStream r1 = (java.io.ByteArrayOutputStream) r1     // Catch:{ all -> 0x003c }
            java.io.ObjectOutputStream r2 = new java.io.ObjectOutputStream     // Catch:{ all -> 0x003c }
            r3 = r1
            java.io.OutputStream r3 = (java.io.OutputStream) r3     // Catch:{ all -> 0x003c }
            r2.<init>(r3)     // Catch:{ all -> 0x003c }
            java.io.Closeable r2 = (java.io.Closeable) r2     // Catch:{ all -> 0x003c }
            r3 = r2
            java.io.ObjectOutputStream r3 = (java.io.ObjectOutputStream) r3     // Catch:{ all -> 0x0035 }
            r3.writeObject(r4)     // Catch:{ all -> 0x0035 }
            byte[] r4 = r1.toByteArray()     // Catch:{ all -> 0x0035 }
            r1 = 2
            java.lang.String r4 = android.util.Base64.encodeToString(r4, r1)     // Catch:{ all -> 0x0035 }
            r1 = 0
            kotlin.io.CloseableKt.closeFinally(r2, r1)     // Catch:{ all -> 0x003c }
            kotlin.io.CloseableKt.closeFinally(r0, r1)
            java.lang.String r0 = "use(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r0)
            return r4
        L_0x0035:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0037 }
        L_0x0037:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r2, r4)     // Catch:{ all -> 0x003c }
            throw r1     // Catch:{ all -> 0x003c }
        L_0x003c:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x003e }
        L_0x003e:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r4)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.notifications.service.delegates.Base64SerializationKt.encodedInBase64(java.io.Serializable):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x006a, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        kotlin.io.CloseableKt.closeFinally(r5, r0);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0074, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0077, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0078, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        kotlin.io.CloseableKt.closeFinally(r2, r0);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0081, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final /* synthetic */ <T> T asBase64EncodedObject(java.lang.String r7) throws java.io.IOException, java.lang.ClassNotFoundException, java.io.InvalidClassException {
        /*
            java.lang.String r0 = "T"
            java.lang.String r1 = "Expected serialized object to be an instance of "
            java.lang.String r2 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r2)
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream
            r3 = 2
            byte[] r7 = android.util.Base64.decode(r7, r3)
            r2.<init>(r7)
            java.io.Closeable r2 = (java.io.Closeable) r2
            r7 = 1
            r4 = r2
            java.io.ByteArrayInputStream r4 = (java.io.ByteArrayInputStream) r4     // Catch:{ all -> 0x0075 }
            java.io.ObjectInputStream r5 = new java.io.ObjectInputStream     // Catch:{ all -> 0x0075 }
            java.io.InputStream r4 = (java.io.InputStream) r4     // Catch:{ all -> 0x0075 }
            r5.<init>(r4)     // Catch:{ all -> 0x0075 }
            java.io.Closeable r5 = (java.io.Closeable) r5     // Catch:{ all -> 0x0075 }
            r4 = r5
            java.io.ObjectInputStream r4 = (java.io.ObjectInputStream) r4     // Catch:{ all -> 0x0068 }
            java.lang.Object r4 = r4.readObject()     // Catch:{ all -> 0x0068 }
            r6 = 3
            kotlin.jvm.internal.Intrinsics.reifiedOperationMarker(r6, r0)     // Catch:{ all -> 0x0068 }
            boolean r6 = r4 instanceof java.lang.Object     // Catch:{ all -> 0x0068 }
            if (r6 == 0) goto L_0x0045
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)     // Catch:{ all -> 0x0075 }
            r0 = 0
            kotlin.io.CloseableKt.closeFinally(r5, r0)     // Catch:{ all -> 0x0075 }
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)     // Catch:{ all -> 0x0075 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlin.io.CloseableKt.closeFinally(r2, r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            return r4
        L_0x0045:
            java.io.InvalidClassException r3 = new java.io.InvalidClassException     // Catch:{ all -> 0x0068 }
            r6 = 4
            kotlin.jvm.internal.Intrinsics.reifiedOperationMarker(r6, r0)     // Catch:{ all -> 0x0068 }
            java.lang.Class<java.lang.Object> r0 = java.lang.Object.class
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0068 }
            r6.<init>(r1)     // Catch:{ all -> 0x0068 }
            java.lang.StringBuilder r0 = r6.append(r0)     // Catch:{ all -> 0x0068 }
            java.lang.String r1 = ". Found: "
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x0068 }
            java.lang.StringBuilder r0 = r0.append(r4)     // Catch:{ all -> 0x0068 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0068 }
            r3.<init>(r0)     // Catch:{ all -> 0x0068 }
            throw r3     // Catch:{ all -> 0x0068 }
        L_0x0068:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x006a }
        L_0x006a:
            r1 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r7)     // Catch:{ all -> 0x0075 }
            kotlin.io.CloseableKt.closeFinally(r5, r0)     // Catch:{ all -> 0x0075 }
            kotlin.jvm.internal.InlineMarker.finallyEnd(r7)     // Catch:{ all -> 0x0075 }
            throw r1     // Catch:{ all -> 0x0075 }
        L_0x0075:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0077 }
        L_0x0077:
            r1 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r7)
            kotlin.io.CloseableKt.closeFinally(r2, r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r7)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.notifications.service.delegates.Base64SerializationKt.asBase64EncodedObject(java.lang.String):java.lang.Object");
    }
}

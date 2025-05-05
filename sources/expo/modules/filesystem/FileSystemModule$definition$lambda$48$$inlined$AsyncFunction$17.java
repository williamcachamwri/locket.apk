package expo.modules.filesystem;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\u0006\b\u0002\u0010\u0004\u0018\u0001\"\u0006\b\u0003\u0010\u0005\u0018\u00012\u0010\u0010\u0006\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0007H\n¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"<anonymous>", "", "R", "P0", "P1", "P2", "it", "", "invoke", "([Ljava/lang/Object;)Ljava/lang/Object;", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$21"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$17 extends Lambda implements Function1<Object[], Object> {
    final /* synthetic */ FileSystemModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$17(FileSystemModule fileSystemModule) {
        super(1);
        this.this$0 = fileSystemModule;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0066, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r7, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x006a, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x006d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x006e, code lost:
        kotlin.io.CloseableKt.closeFinally(r1, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0071, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invoke(java.lang.Object[] r7) {
        /*
            r6 = this;
            java.lang.String r0 = "it"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            r0 = 0
            r1 = r7[r0]
            java.lang.String r2 = "null cannot be cast to non-null type kotlin.String"
            if (r1 == 0) goto L_0x0080
            java.lang.String r1 = (java.lang.String) r1
            r3 = 1
            r3 = r7[r3]
            if (r3 == 0) goto L_0x007a
            java.lang.String r3 = (java.lang.String) r3
            r2 = 2
            r7 = r7[r2]
            if (r7 == 0) goto L_0x0072
            expo.modules.filesystem.WritingOptions r7 = (expo.modules.filesystem.WritingOptions) r7
            java.lang.String r1 = expo.modules.filesystem.FileSystemModuleKt.slashifyFilePath(r1)
            android.net.Uri r1 = android.net.Uri.parse(r1)
            expo.modules.filesystem.FileSystemModule r2 = r6.this$0
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            expo.modules.interfaces.filesystem.Permission r4 = expo.modules.interfaces.filesystem.Permission.WRITE
            r2.ensurePermission(r1, r4)
            expo.modules.filesystem.EncodingType r7 = r7.getEncoding()
            expo.modules.filesystem.FileSystemModule r2 = r6.this$0
            java.io.OutputStream r1 = r2.getOutputStream(r1)
            java.io.Closeable r1 = (java.io.Closeable) r1
            r2 = r1
            java.io.OutputStream r2 = (java.io.OutputStream) r2     // Catch:{ all -> 0x006b }
            expo.modules.filesystem.EncodingType r4 = expo.modules.filesystem.EncodingType.BASE64     // Catch:{ all -> 0x006b }
            r5 = 0
            if (r7 != r4) goto L_0x004a
            byte[] r7 = android.util.Base64.decode(r3, r0)     // Catch:{ all -> 0x006b }
            r2.write(r7)     // Catch:{ all -> 0x006b }
            goto L_0x005c
        L_0x004a:
            java.io.OutputStreamWriter r7 = new java.io.OutputStreamWriter     // Catch:{ all -> 0x006b }
            r7.<init>(r2)     // Catch:{ all -> 0x006b }
            java.io.Closeable r7 = (java.io.Closeable) r7     // Catch:{ all -> 0x006b }
            r0 = r7
            java.io.OutputStreamWriter r0 = (java.io.OutputStreamWriter) r0     // Catch:{ all -> 0x0064 }
            r0.write(r3)     // Catch:{ all -> 0x0064 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0064 }
            kotlin.io.CloseableKt.closeFinally(r7, r5)     // Catch:{ all -> 0x006b }
        L_0x005c:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x006b }
            kotlin.io.CloseableKt.closeFinally(r1, r5)
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L_0x0064:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0066 }
        L_0x0066:
            r2 = move-exception
            kotlin.io.CloseableKt.closeFinally(r7, r0)     // Catch:{ all -> 0x006b }
            throw r2     // Catch:{ all -> 0x006b }
        L_0x006b:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x006d }
        L_0x006d:
            r0 = move-exception
            kotlin.io.CloseableKt.closeFinally(r1, r7)
            throw r0
        L_0x0072:
            java.lang.NullPointerException r7 = new java.lang.NullPointerException
            java.lang.String r0 = "null cannot be cast to non-null type expo.modules.filesystem.WritingOptions"
            r7.<init>(r0)
            throw r7
        L_0x007a:
            java.lang.NullPointerException r7 = new java.lang.NullPointerException
            r7.<init>(r2)
            throw r7
        L_0x0080:
            java.lang.NullPointerException r7 = new java.lang.NullPointerException
            r7.<init>(r2)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$17.invoke(java.lang.Object[]):java.lang.Object");
    }
}

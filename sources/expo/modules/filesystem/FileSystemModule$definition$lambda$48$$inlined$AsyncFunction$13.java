package expo.modules.filesystem;

import expo.modules.kotlin.Promise;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\u0006\b\u0002\u0010\u0004\u0018\u0001\"\u0006\b\u0003\u0010\u0005\u0018\u00012\u0010\u0010\u0006\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\b0\u00072\u0006\u0010\t\u001a\u00020\nH\n¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"<anonymous>", "", "R", "P0", "P1", "P2", "args", "", "", "promise", "Lexpo/modules/kotlin/Promise;", "invoke", "([Ljava/lang/Object;Lexpo/modules/kotlin/Promise;)V", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$17"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$13 extends Lambda implements Function2<Object[], Promise, Unit> {
    final /* synthetic */ FileSystemModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$13(FileSystemModule fileSystemModule) {
        super(2);
        this.this$0 = fileSystemModule;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0064, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r7, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0068, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x006b, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x006c, code lost:
        kotlin.io.CloseableKt.closeFinally(r1, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x006f, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void invoke(java.lang.Object[] r6, expo.modules.kotlin.Promise r7) {
        /*
            r5 = this;
            java.lang.String r0 = "args"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.String r0 = "promise"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            r0 = 0
            r1 = r6[r0]
            java.lang.String r2 = "null cannot be cast to non-null type kotlin.String"
            if (r1 == 0) goto L_0x0076
            java.lang.String r1 = (java.lang.String) r1
            r3 = 1
            r6 = r6[r3]
            if (r6 == 0) goto L_0x0070
            java.lang.String r6 = (java.lang.String) r6
            expo.modules.filesystem.WritingOptions r7 = (expo.modules.filesystem.WritingOptions) r7
            java.lang.String r1 = expo.modules.filesystem.FileSystemModuleKt.slashifyFilePath(r1)
            android.net.Uri r1 = android.net.Uri.parse(r1)
            expo.modules.filesystem.FileSystemModule r2 = r5.this$0
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            expo.modules.interfaces.filesystem.Permission r3 = expo.modules.interfaces.filesystem.Permission.WRITE
            r2.ensurePermission(r1, r3)
            expo.modules.filesystem.EncodingType r7 = r7.getEncoding()
            expo.modules.filesystem.FileSystemModule r2 = r5.this$0
            java.io.OutputStream r1 = r2.getOutputStream(r1)
            java.io.Closeable r1 = (java.io.Closeable) r1
            r2 = r1
            java.io.OutputStream r2 = (java.io.OutputStream) r2     // Catch:{ all -> 0x0069 }
            expo.modules.filesystem.EncodingType r3 = expo.modules.filesystem.EncodingType.BASE64     // Catch:{ all -> 0x0069 }
            r4 = 0
            if (r7 != r3) goto L_0x004a
            byte[] r6 = android.util.Base64.decode(r6, r0)     // Catch:{ all -> 0x0069 }
            r2.write(r6)     // Catch:{ all -> 0x0069 }
            goto L_0x005c
        L_0x004a:
            java.io.OutputStreamWriter r7 = new java.io.OutputStreamWriter     // Catch:{ all -> 0x0069 }
            r7.<init>(r2)     // Catch:{ all -> 0x0069 }
            java.io.Closeable r7 = (java.io.Closeable) r7     // Catch:{ all -> 0x0069 }
            r0 = r7
            java.io.OutputStreamWriter r0 = (java.io.OutputStreamWriter) r0     // Catch:{ all -> 0x0062 }
            r0.write(r6)     // Catch:{ all -> 0x0062 }
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0062 }
            kotlin.io.CloseableKt.closeFinally(r7, r4)     // Catch:{ all -> 0x0069 }
        L_0x005c:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0069 }
            kotlin.io.CloseableKt.closeFinally(r1, r4)
            return
        L_0x0062:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0064 }
        L_0x0064:
            r0 = move-exception
            kotlin.io.CloseableKt.closeFinally(r7, r6)     // Catch:{ all -> 0x0069 }
            throw r0     // Catch:{ all -> 0x0069 }
        L_0x0069:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x006b }
        L_0x006b:
            r7 = move-exception
            kotlin.io.CloseableKt.closeFinally(r1, r6)
            throw r7
        L_0x0070:
            java.lang.NullPointerException r6 = new java.lang.NullPointerException
            r6.<init>(r2)
            throw r6
        L_0x0076:
            java.lang.NullPointerException r6 = new java.lang.NullPointerException
            r6.<init>(r2)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$13.invoke(java.lang.Object[], expo.modules.kotlin.Promise):void");
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Object[]) obj, (Promise) obj2);
        return Unit.INSTANCE;
    }
}

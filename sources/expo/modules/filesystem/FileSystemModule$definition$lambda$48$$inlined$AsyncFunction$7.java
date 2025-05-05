package expo.modules.filesystem;

import expo.modules.kotlin.Promise;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\u0006\b\u0002\u0010\u0004\u0018\u00012\u0010\u0010\u0005\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\n¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"<anonymous>", "", "R", "P0", "P1", "args", "", "", "promise", "Lexpo/modules/kotlin/Promise;", "invoke", "([Ljava/lang/Object;Lexpo/modules/kotlin/Promise;)V", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$10"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$7 extends Lambda implements Function2<Object[], Promise, Unit> {
    final /* synthetic */ FileSystemModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$7(FileSystemModule fileSystemModule) {
        super(2);
        this.this$0 = fileSystemModule;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x007d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x007e, code lost:
        kotlin.io.CloseableKt.closeFinally(r7, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0081, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void invoke(java.lang.Object[] r7, expo.modules.kotlin.Promise r8) {
        /*
            r6 = this;
            java.lang.String r0 = "args"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.lang.String r0 = "promise"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            r0 = 0
            r7 = r7[r0]
            if (r7 == 0) goto L_0x00fe
            java.lang.String r7 = (java.lang.String) r7
            expo.modules.filesystem.ReadingOptions r8 = (expo.modules.filesystem.ReadingOptions) r8
            java.lang.String r1 = expo.modules.filesystem.FileSystemModuleKt.slashifyFilePath(r7)
            android.net.Uri r1 = android.net.Uri.parse(r1)
            expo.modules.filesystem.FileSystemModule r2 = r6.this$0
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            expo.modules.interfaces.filesystem.Permission r3 = expo.modules.interfaces.filesystem.Permission.READ
            r2.ensurePermission(r1, r3)
            expo.modules.filesystem.EncodingType r2 = r8.getEncoding()
            expo.modules.filesystem.EncodingType r3 = expo.modules.filesystem.EncodingType.BASE64
            if (r2 != r3) goto L_0x0082
            expo.modules.filesystem.FileSystemModule r7 = r6.this$0
            java.io.InputStream r7 = r7.getInputStream(r1)
            java.io.Closeable r7 = (java.io.Closeable) r7
            r1 = r7
            java.io.InputStream r1 = (java.io.InputStream) r1     // Catch:{ all -> 0x007b }
            java.lang.Integer r2 = r8.getLength()     // Catch:{ all -> 0x007b }
            r3 = 2
            if (r2 == 0) goto L_0x006b
            java.lang.Integer r2 = r8.getPosition()     // Catch:{ all -> 0x007b }
            if (r2 == 0) goto L_0x006b
            java.lang.Integer r2 = r8.getLength()     // Catch:{ all -> 0x007b }
            int r2 = r2.intValue()     // Catch:{ all -> 0x007b }
            byte[] r2 = new byte[r2]     // Catch:{ all -> 0x007b }
            java.lang.Integer r4 = r8.getPosition()     // Catch:{ all -> 0x007b }
            int r4 = r4.intValue()     // Catch:{ all -> 0x007b }
            long r4 = (long) r4     // Catch:{ all -> 0x007b }
            r1.skip(r4)     // Catch:{ all -> 0x007b }
            java.lang.Integer r8 = r8.getLength()     // Catch:{ all -> 0x007b }
            int r8 = r8.intValue()     // Catch:{ all -> 0x007b }
            int r8 = r1.read(r2, r0, r8)     // Catch:{ all -> 0x007b }
            android.util.Base64.encodeToString(r2, r0, r8, r3)     // Catch:{ all -> 0x007b }
            goto L_0x0074
        L_0x006b:
            expo.modules.filesystem.FileSystemModule r8 = r6.this$0     // Catch:{ all -> 0x007b }
            byte[] r8 = r8.getInputStreamBytes(r1)     // Catch:{ all -> 0x007b }
            android.util.Base64.encodeToString(r8, r3)     // Catch:{ all -> 0x007b }
        L_0x0074:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x007b }
            r8 = 0
            kotlin.io.CloseableKt.closeFinally(r7, r8)
            goto L_0x00e2
        L_0x007b:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x007d }
        L_0x007d:
            r0 = move-exception
            kotlin.io.CloseableKt.closeFinally(r7, r8)
            throw r0
        L_0x0082:
            java.lang.String r8 = r1.getScheme()
            java.lang.String r0 = "file"
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r0)
            if (r8 == 0) goto L_0x00a3
            java.io.FileInputStream r7 = new java.io.FileInputStream
            expo.modules.filesystem.FileSystemModule r8 = r6.this$0
            java.io.File r8 = r8.toFile(r1)
            r7.<init>(r8)
            java.io.FileInputStream r7 = io.sentry.instrumentation.file.SentryFileInputStream.Factory.create((java.io.FileInputStream) r7, (java.io.File) r8)
            java.io.InputStream r7 = (java.io.InputStream) r7
            org.apache.commons.io.IOUtils.toString((java.io.InputStream) r7)
            goto L_0x00e2
        L_0x00a3:
            java.lang.String r8 = r1.getScheme()
            java.lang.String r0 = "asset"
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r0)
            if (r8 == 0) goto L_0x00b9
            expo.modules.filesystem.FileSystemModule r7 = r6.this$0
            java.io.InputStream r7 = r7.openAssetInputStream(r1)
            org.apache.commons.io.IOUtils.toString((java.io.InputStream) r7)
            goto L_0x00e2
        L_0x00b9:
            java.lang.String r8 = r1.getScheme()
            if (r8 != 0) goto L_0x00c9
            expo.modules.filesystem.FileSystemModule r8 = r6.this$0
            java.io.InputStream r7 = r8.openResourceInputStream(r7)
            org.apache.commons.io.IOUtils.toString((java.io.InputStream) r7)
            goto L_0x00e2
        L_0x00c9:
            expo.modules.filesystem.FileSystemModule r7 = r6.this$0
            boolean r7 = r7.isSAFUri(r1)
            if (r7 == 0) goto L_0x00e3
            expo.modules.filesystem.FileSystemModule r7 = r6.this$0
            android.content.Context r7 = r7.getContext()
            android.content.ContentResolver r7 = r7.getContentResolver()
            java.io.InputStream r7 = r7.openInputStream(r1)
            org.apache.commons.io.IOUtils.toString((java.io.InputStream) r7)
        L_0x00e2:
            return
        L_0x00e3:
            java.io.IOException r7 = new java.io.IOException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r0 = "Unsupported scheme for location '"
            r8.<init>(r0)
            java.lang.StringBuilder r8 = r8.append(r1)
            java.lang.String r0 = "'."
            java.lang.StringBuilder r8 = r8.append(r0)
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            throw r7
        L_0x00fe:
            java.lang.NullPointerException r7 = new java.lang.NullPointerException
            java.lang.String r8 = "null cannot be cast to non-null type kotlin.String"
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$7.invoke(java.lang.Object[], expo.modules.kotlin.Promise):void");
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Object[]) obj, (Promise) obj2);
        return Unit.INSTANCE;
    }
}

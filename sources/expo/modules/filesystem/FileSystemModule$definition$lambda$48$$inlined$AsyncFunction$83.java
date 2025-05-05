package expo.modules.filesystem;

import expo.modules.kotlin.Promise;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\u0006\b\u0002\u0010\u0004\u0018\u0001\"\u0006\b\u0003\u0010\u0005\u0018\u0001\"\u0006\b\u0004\u0010\u0006\u0018\u00012\u0010\u0010\u0007\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\n¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"<anonymous>", "", "R", "P0", "P1", "P2", "P3", "args", "", "", "promise", "Lexpo/modules/kotlin/Promise;", "invoke", "([Ljava/lang/Object;Lexpo/modules/kotlin/Promise;)V", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$26"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$83 extends Lambda implements Function2<Object[], Promise, Unit> {
    final /* synthetic */ FileSystemModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$83(FileSystemModule fileSystemModule) {
        super(2);
        this.this$0 = fileSystemModule;
    }

    /* JADX WARNING: type inference failed for: r7v2, types: [kotlin.Unit] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void invoke(java.lang.Object[] r10, expo.modules.kotlin.Promise r11) {
        /*
            r9 = this;
            java.lang.String r0 = "args"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            java.lang.String r0 = "promise"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            r0 = 0
            r1 = r10[r0]
            if (r1 == 0) goto L_0x014c
            java.lang.String r1 = (java.lang.String) r1
            r2 = 1
            r3 = r10[r2]
            java.lang.String r3 = (java.lang.String) r3
            r4 = 2
            r10 = r10[r4]
            if (r10 == 0) goto L_0x0144
            expo.modules.filesystem.DownloadOptions r10 = (expo.modules.filesystem.DownloadOptions) r10
            java.lang.String r3 = expo.modules.filesystem.FileSystemModuleKt.slashifyFilePath(r3)
            android.net.Uri r3 = android.net.Uri.parse(r3)
            expo.modules.filesystem.FileSystemModule r5 = r9.this$0
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            expo.modules.interfaces.filesystem.Permission r6 = expo.modules.interfaces.filesystem.Permission.WRITE
            r5.ensurePermission(r3, r6)
            expo.modules.filesystem.FileSystemModule r5 = r9.this$0
            r5.checkIfFileDirExists(r3)
            r5 = r1
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            java.lang.String r6 = ":"
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            r7 = 0
            boolean r4 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r5, (java.lang.CharSequence) r6, (boolean) r0, (int) r4, (java.lang.Object) r7)
            if (r4 != 0) goto L_0x00b9
            expo.modules.filesystem.FileSystemModule r4 = r9.this$0
            android.content.Context r4 = r4.getContext()
            android.content.res.Resources r5 = r4.getResources()
            java.lang.String r6 = r4.getPackageName()
            java.lang.String r8 = "raw"
            int r1 = r5.getIdentifier(r1, r8, r6)
            android.content.res.Resources r4 = r4.getResources()
            java.io.InputStream r1 = r4.openRawResource(r1)
            java.lang.String r4 = "openRawResource(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r4)
            okio.Source r1 = okio.Okio.source((java.io.InputStream) r1)
            okio.BufferedSource r1 = okio.Okio.buffer((okio.Source) r1)
            expo.modules.filesystem.FileSystemModule r4 = r9.this$0
            java.io.File r3 = r4.toFile(r3)
            r3.delete()
            okio.Sink r0 = okio.Okio__JvmOkioKt.sink$default(r3, r0, r2, r7)
            okio.BufferedSink r0 = okio.Okio.buffer((okio.Sink) r0)
            okio.Source r1 = (okio.Source) r1
            r0.writeAll(r1)
            r0.close()
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            android.net.Uri r1 = android.net.Uri.fromFile(r3)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "uri"
            r0.putString(r2, r1)
            boolean r10 = r10.getMd5()
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r10)
            boolean r1 = r10.booleanValue()
            if (r1 == 0) goto L_0x00a5
            r7 = r10
        L_0x00a5:
            if (r7 == 0) goto L_0x00b5
            r7.booleanValue()
            expo.modules.filesystem.FileSystemModule r10 = r9.this$0
            java.lang.String r10 = r10.md5(r3)
            java.lang.String r1 = "md5"
            r0.putString(r1, r10)
        L_0x00b5:
            r11.resolve(r0)
            goto L_0x0128
        L_0x00b9:
            java.lang.String r0 = "file"
            java.lang.String r2 = r3.getScheme()
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r2)
            if (r0 == 0) goto L_0x0129
            okhttp3.Request$Builder r0 = new okhttp3.Request$Builder
            r0.<init>()
            okhttp3.Request$Builder r0 = r0.url((java.lang.String) r1)
            java.util.Map r1 = r10.getHeaders()
            if (r1 == 0) goto L_0x00fc
            java.util.Map r1 = r10.getHeaders()
            java.util.Set r1 = r1.entrySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x00e0:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x00fc
            java.lang.Object r2 = r1.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r4 = r2.getKey()
            java.lang.String r4 = (java.lang.String) r4
            java.lang.Object r2 = r2.getValue()
            java.lang.String r2 = (java.lang.String) r2
            r0.addHeader(r4, r2)
            goto L_0x00e0
        L_0x00fc:
            expo.modules.filesystem.FileSystemModule r1 = r9.this$0
            okhttp3.OkHttpClient r1 = r1.getOkHttpClient()
            if (r1 == 0) goto L_0x011c
            okhttp3.Request r0 = r0.build()
            okhttp3.Call r0 = r1.newCall(r0)
            if (r0 == 0) goto L_0x011c
            expo.modules.filesystem.FileSystemModule$definition$1$19$4 r1 = new expo.modules.filesystem.FileSystemModule$definition$1$19$4
            expo.modules.filesystem.FileSystemModule r2 = r9.this$0
            r1.<init>(r11, r2, r3, r10)
            okhttp3.Callback r1 = (okhttp3.Callback) r1
            com.google.firebase.perf.network.FirebasePerfOkHttpClient.enqueue(r0, r1)
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
        L_0x011c:
            if (r7 != 0) goto L_0x0128
            expo.modules.filesystem.FileSystemOkHttpNullException r10 = new expo.modules.filesystem.FileSystemOkHttpNullException
            r10.<init>()
            expo.modules.kotlin.exception.CodedException r10 = (expo.modules.kotlin.exception.CodedException) r10
            r11.reject(r10)
        L_0x0128:
            return
        L_0x0129:
            java.io.IOException r10 = new java.io.IOException
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r0 = "Unsupported scheme for location '"
            r11.<init>(r0)
            java.lang.StringBuilder r11 = r11.append(r3)
            java.lang.String r0 = "'."
            java.lang.StringBuilder r11 = r11.append(r0)
            java.lang.String r11 = r11.toString()
            r10.<init>(r11)
            throw r10
        L_0x0144:
            java.lang.NullPointerException r10 = new java.lang.NullPointerException
            java.lang.String r11 = "null cannot be cast to non-null type expo.modules.filesystem.DownloadOptions"
            r10.<init>(r11)
            throw r10
        L_0x014c:
            java.lang.NullPointerException r10 = new java.lang.NullPointerException
            java.lang.String r11 = "null cannot be cast to non-null type kotlin.String"
            r10.<init>(r11)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$83.invoke(java.lang.Object[], expo.modules.kotlin.Promise):void");
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Object[]) obj, (Promise) obj2);
        return Unit.INSTANCE;
    }
}

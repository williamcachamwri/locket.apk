package expo.modules.filesystem;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\u0006\b\u0002\u0010\u0004\u0018\u0001\"\u0006\b\u0003\u0010\u0005\u0018\u0001\"\u0006\b\u0004\u0010\u0006\u0018\u00012\u0010\u0010\u0007\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\bH\n¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"<anonymous>", "", "R", "P0", "P1", "P2", "P3", "it", "", "invoke", "([Ljava/lang/Object;)Ljava/lang/Object;", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$31"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$88 extends Lambda implements Function1<Object[], Object> {
    final /* synthetic */ FileSystemModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$88(FileSystemModule fileSystemModule) {
        super(1);
        this.this$0 = fileSystemModule;
    }

    /* JADX WARNING: type inference failed for: r8v2, types: [kotlin.Unit] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invoke(java.lang.Object[] r11) {
        /*
            r10 = this;
            java.lang.String r0 = "it"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            r0 = 0
            r1 = r11[r0]
            if (r1 == 0) goto L_0x0158
            java.lang.String r1 = (java.lang.String) r1
            r2 = 1
            r3 = r11[r2]
            java.lang.String r3 = (java.lang.String) r3
            r4 = 2
            r5 = r11[r4]
            if (r5 == 0) goto L_0x0150
            expo.modules.filesystem.DownloadOptions r5 = (expo.modules.filesystem.DownloadOptions) r5
            r6 = 3
            r11 = r11[r6]
            if (r11 == 0) goto L_0x0148
            expo.modules.kotlin.Promise r11 = (expo.modules.kotlin.Promise) r11
            java.lang.String r3 = expo.modules.filesystem.FileSystemModuleKt.slashifyFilePath(r3)
            android.net.Uri r3 = android.net.Uri.parse(r3)
            expo.modules.filesystem.FileSystemModule r6 = r10.this$0
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            expo.modules.interfaces.filesystem.Permission r7 = expo.modules.interfaces.filesystem.Permission.WRITE
            r6.ensurePermission(r3, r7)
            expo.modules.filesystem.FileSystemModule r6 = r10.this$0
            r6.checkIfFileDirExists(r3)
            r6 = r1
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            java.lang.String r7 = ":"
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            r8 = 0
            boolean r4 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r6, (java.lang.CharSequence) r7, (boolean) r0, (int) r4, (java.lang.Object) r8)
            if (r4 != 0) goto L_0x00bb
            expo.modules.filesystem.FileSystemModule r4 = r10.this$0
            android.content.Context r4 = r4.getContext()
            android.content.res.Resources r6 = r4.getResources()
            java.lang.String r7 = r4.getPackageName()
            java.lang.String r9 = "raw"
            int r1 = r6.getIdentifier(r1, r9, r7)
            android.content.res.Resources r4 = r4.getResources()
            java.io.InputStream r1 = r4.openRawResource(r1)
            java.lang.String r4 = "openRawResource(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r4)
            okio.Source r1 = okio.Okio.source((java.io.InputStream) r1)
            okio.BufferedSource r1 = okio.Okio.buffer((okio.Source) r1)
            expo.modules.filesystem.FileSystemModule r4 = r10.this$0
            java.io.File r3 = r4.toFile(r3)
            r3.delete()
            okio.Sink r0 = okio.Okio__JvmOkioKt.sink$default(r3, r0, r2, r8)
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
            boolean r1 = r5.getMd5()
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            boolean r2 = r1.booleanValue()
            if (r2 == 0) goto L_0x00a7
            r8 = r1
        L_0x00a7:
            if (r8 == 0) goto L_0x00b7
            r8.booleanValue()
            expo.modules.filesystem.FileSystemModule r1 = r10.this$0
            java.lang.String r1 = r1.md5(r3)
            java.lang.String r2 = "md5"
            r0.putString(r2, r1)
        L_0x00b7:
            r11.resolve(r0)
            goto L_0x012a
        L_0x00bb:
            java.lang.String r0 = "file"
            java.lang.String r2 = r3.getScheme()
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r2)
            if (r0 == 0) goto L_0x012d
            okhttp3.Request$Builder r0 = new okhttp3.Request$Builder
            r0.<init>()
            okhttp3.Request$Builder r0 = r0.url((java.lang.String) r1)
            java.util.Map r1 = r5.getHeaders()
            if (r1 == 0) goto L_0x00fe
            java.util.Map r1 = r5.getHeaders()
            java.util.Set r1 = r1.entrySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x00e2:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x00fe
            java.lang.Object r2 = r1.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r4 = r2.getKey()
            java.lang.String r4 = (java.lang.String) r4
            java.lang.Object r2 = r2.getValue()
            java.lang.String r2 = (java.lang.String) r2
            r0.addHeader(r4, r2)
            goto L_0x00e2
        L_0x00fe:
            expo.modules.filesystem.FileSystemModule r1 = r10.this$0
            okhttp3.OkHttpClient r1 = r1.getOkHttpClient()
            if (r1 == 0) goto L_0x011e
            okhttp3.Request r0 = r0.build()
            okhttp3.Call r0 = r1.newCall(r0)
            if (r0 == 0) goto L_0x011e
            expo.modules.filesystem.FileSystemModule$definition$1$19$4 r1 = new expo.modules.filesystem.FileSystemModule$definition$1$19$4
            expo.modules.filesystem.FileSystemModule r2 = r10.this$0
            r1.<init>(r11, r2, r3, r5)
            okhttp3.Callback r1 = (okhttp3.Callback) r1
            com.google.firebase.perf.network.FirebasePerfOkHttpClient.enqueue(r0, r1)
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
        L_0x011e:
            if (r8 != 0) goto L_0x012a
            expo.modules.filesystem.FileSystemOkHttpNullException r0 = new expo.modules.filesystem.FileSystemOkHttpNullException
            r0.<init>()
            expo.modules.kotlin.exception.CodedException r0 = (expo.modules.kotlin.exception.CodedException) r0
            r11.reject(r0)
        L_0x012a:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L_0x012d:
            java.io.IOException r11 = new java.io.IOException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Unsupported scheme for location '"
            r0.<init>(r1)
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.String r1 = "'."
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            r11.<init>(r0)
            throw r11
        L_0x0148:
            java.lang.NullPointerException r11 = new java.lang.NullPointerException
            java.lang.String r0 = "null cannot be cast to non-null type expo.modules.kotlin.Promise"
            r11.<init>(r0)
            throw r11
        L_0x0150:
            java.lang.NullPointerException r11 = new java.lang.NullPointerException
            java.lang.String r0 = "null cannot be cast to non-null type expo.modules.filesystem.DownloadOptions"
            r11.<init>(r0)
            throw r11
        L_0x0158:
            java.lang.NullPointerException r11 = new java.lang.NullPointerException
            java.lang.String r0 = "null cannot be cast to non-null type kotlin.String"
            r11.<init>(r0)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$88.invoke(java.lang.Object[]):java.lang.Object");
    }
}

package expo.modules.filesystem;

import expo.modules.kotlin.Promise;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\u0006\b\u0002\u0010\u0004\u0018\u0001\"\u0006\b\u0003\u0010\u0005\u0018\u0001\"\u0006\b\u0004\u0010\u0006\u0018\u0001\"\u0006\b\u0005\u0010\u0007\u0018\u0001\"\u0006\b\u0006\u0010\b\u0018\u00012\u0010\u0010\t\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u000b0\n2\u0006\u0010\f\u001a\u00020\rH\n¢\u0006\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"<anonymous>", "", "R", "P0", "P1", "P2", "P3", "P4", "P5", "args", "", "", "promise", "Lexpo/modules/kotlin/Promise;", "invoke", "([Ljava/lang/Object;Lexpo/modules/kotlin/Promise;)V", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$50"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$97 extends Lambda implements Function2<Object[], Promise, Unit> {
    final /* synthetic */ FileSystemModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$97(FileSystemModule fileSystemModule) {
        super(2);
        this.this$0 = fileSystemModule;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0062, code lost:
        r4 = (r6 = r6.newBuilder()).addInterceptor(new expo.modules.filesystem.FileSystemModule$definition$lambda$48$lambda$42$$inlined$addInterceptor$1(r4));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void invoke(java.lang.Object[] r14, expo.modules.kotlin.Promise r15) {
        /*
            r13 = this;
            java.lang.String r0 = "args"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
            java.lang.String r0 = "promise"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r0)
            r0 = 0
            r1 = r14[r0]
            java.lang.String r2 = "null cannot be cast to non-null type kotlin.String"
            if (r1 == 0) goto L_0x0145
            java.lang.String r1 = (java.lang.String) r1
            r3 = 1
            r4 = r14[r3]
            if (r4 == 0) goto L_0x013f
            java.lang.String r4 = (java.lang.String) r4
            r5 = 2
            r5 = r14[r5]
            if (r5 == 0) goto L_0x0139
            java.lang.String r5 = (java.lang.String) r5
            r2 = 3
            r2 = r14[r2]
            if (r2 == 0) goto L_0x0131
            r7 = r2
            expo.modules.filesystem.DownloadOptions r7 = (expo.modules.filesystem.DownloadOptions) r7
            r2 = 4
            r14 = r14[r2]
            java.lang.String r14 = (java.lang.String) r14
            java.lang.String r2 = expo.modules.filesystem.FileSystemModuleKt.slashifyFilePath(r4)
            android.net.Uri r2 = android.net.Uri.parse(r2)
            expo.modules.filesystem.FileSystemModule r4 = r13.this$0
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            r4.checkIfFileDirExists(r2)
            java.lang.String r4 = r2.getScheme()
            java.lang.String r6 = "file"
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r6)
            if (r4 == 0) goto L_0x0116
            expo.modules.filesystem.FileSystemModule$definition$1$21$progressListener$1 r4 = new expo.modules.filesystem.FileSystemModule$definition$1$21$progressListener$1
            expo.modules.filesystem.FileSystemModule r6 = r13.this$0
            r4.<init>(r14, r5, r6)
            expo.modules.filesystem.FileSystemModule$ProgressListener r4 = (expo.modules.filesystem.FileSystemModule.ProgressListener) r4
            expo.modules.filesystem.FileSystemModule r6 = r13.this$0
            okhttp3.OkHttpClient r6 = r6.getOkHttpClient()
            r12 = 0
            if (r6 == 0) goto L_0x0074
            okhttp3.OkHttpClient$Builder r6 = r6.newBuilder()
            if (r6 == 0) goto L_0x0074
            expo.modules.filesystem.FileSystemModule$definition$lambda$48$lambda$42$$inlined$-addInterceptor$1 r8 = new expo.modules.filesystem.FileSystemModule$definition$lambda$48$lambda$42$$inlined$-addInterceptor$1
            r8.<init>(r4)
            okhttp3.Interceptor r8 = (okhttp3.Interceptor) r8
            okhttp3.OkHttpClient$Builder r4 = r6.addInterceptor(r8)
            if (r4 == 0) goto L_0x0074
            okhttp3.OkHttpClient r4 = r4.build()
            goto L_0x0075
        L_0x0074:
            r4 = r12
        L_0x0075:
            if (r4 != 0) goto L_0x0083
            expo.modules.filesystem.FileSystemOkHttpNullException r14 = new expo.modules.filesystem.FileSystemOkHttpNullException
            r14.<init>()
            expo.modules.kotlin.exception.CodedException r14 = (expo.modules.kotlin.exception.CodedException) r14
            r15.reject(r14)
            goto L_0x0115
        L_0x0083:
            okhttp3.Request$Builder r6 = new okhttp3.Request$Builder
            r6.<init>()
            if (r14 == 0) goto L_0x00a4
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "bytes="
            r8.<init>(r9)
            java.lang.StringBuilder r8 = r8.append(r14)
            java.lang.String r9 = "-"
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r8 = r8.toString()
            java.lang.String r9 = "Range"
            r6.addHeader(r9, r8)
        L_0x00a4:
            java.util.Map r8 = r7.getHeaders()
            if (r8 == 0) goto L_0x00d2
            java.util.Map r8 = r7.getHeaders()
            java.util.Set r8 = r8.entrySet()
            java.util.Iterator r8 = r8.iterator()
        L_0x00b6:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L_0x00d2
            java.lang.Object r9 = r8.next()
            java.util.Map$Entry r9 = (java.util.Map.Entry) r9
            java.lang.Object r10 = r9.getKey()
            java.lang.String r10 = (java.lang.String) r10
            java.lang.Object r9 = r9.getValue()
            java.lang.String r9 = (java.lang.String) r9
            r6.addHeader(r10, r9)
            goto L_0x00b6
        L_0x00d2:
            okhttp3.Request$Builder r1 = r6.url((java.lang.String) r1)
            okhttp3.Request r1 = r1.build()
            okhttp3.Call r8 = r4.newCall(r1)
            expo.modules.filesystem.FileSystemModule r1 = r13.this$0
            java.util.Map r1 = r1.taskHandlers
            expo.modules.filesystem.FileSystemModule$DownloadTaskHandler r4 = new expo.modules.filesystem.FileSystemModule$DownloadTaskHandler
            r4.<init>(r2, r8)
            r1.put(r5, r4)
            expo.modules.filesystem.FileSystemModule$DownloadResumableTaskParams r1 = new expo.modules.filesystem.FileSystemModule$DownloadResumableTaskParams
            expo.modules.filesystem.FileSystemModule r4 = r13.this$0
            java.io.File r9 = r4.toFile(r2)
            if (r14 == 0) goto L_0x00f8
            r10 = r3
            goto L_0x00f9
        L_0x00f8:
            r10 = r0
        L_0x00f9:
            r6 = r1
            r11 = r15
            r6.<init>(r7, r8, r9, r10, r11)
            expo.modules.filesystem.FileSystemModule r14 = r13.this$0
            kotlinx.coroutines.CoroutineScope r2 = r14.moduleCoroutineScope
            r3 = 0
            r4 = 0
            expo.modules.filesystem.FileSystemModule$definition$1$21$3 r14 = new expo.modules.filesystem.FileSystemModule$definition$1$21$3
            expo.modules.filesystem.FileSystemModule r15 = r13.this$0
            r14.<init>(r15, r1, r12)
            r5 = r14
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            r6 = 3
            r7 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r2, r3, r4, r5, r6, r7)
        L_0x0115:
            return
        L_0x0116:
            java.io.IOException r14 = new java.io.IOException
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            java.lang.String r0 = "Unsupported scheme for location '"
            r15.<init>(r0)
            java.lang.StringBuilder r15 = r15.append(r2)
            java.lang.String r0 = "'."
            java.lang.StringBuilder r15 = r15.append(r0)
            java.lang.String r15 = r15.toString()
            r14.<init>(r15)
            throw r14
        L_0x0131:
            java.lang.NullPointerException r14 = new java.lang.NullPointerException
            java.lang.String r15 = "null cannot be cast to non-null type expo.modules.filesystem.DownloadOptions"
            r14.<init>(r15)
            throw r14
        L_0x0139:
            java.lang.NullPointerException r14 = new java.lang.NullPointerException
            r14.<init>(r2)
            throw r14
        L_0x013f:
            java.lang.NullPointerException r14 = new java.lang.NullPointerException
            r14.<init>(r2)
            throw r14
        L_0x0145:
            java.lang.NullPointerException r14 = new java.lang.NullPointerException
            r14.<init>(r2)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$97.invoke(java.lang.Object[], expo.modules.kotlin.Promise):void");
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Object[]) obj, (Promise) obj2);
        return Unit.INSTANCE;
    }
}

package expo.modules.filesystem;

import expo.modules.kotlin.Promise;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\u0006\b\u0002\u0010\u0004\u0018\u00012\u0010\u0010\u0005\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\n¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"<anonymous>", "", "R", "P0", "P1", "args", "", "", "promise", "Lexpo/modules/kotlin/Promise;", "invoke", "([Ljava/lang/Object;Lexpo/modules/kotlin/Promise;)V", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$10"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$2 extends Lambda implements Function2<Object[], Promise, Unit> {
    final /* synthetic */ FileSystemModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$2(FileSystemModule fileSystemModule) {
        super(2);
        this.this$0 = fileSystemModule;
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x0144 A[Catch:{ FileNotFoundException -> 0x0188 }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0182 A[Catch:{ FileNotFoundException -> 0x0188 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void invoke(java.lang.Object[] r14, expo.modules.kotlin.Promise r15) {
        /*
            r13 = this;
            java.lang.String r0 = "args"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
            java.lang.String r0 = "promise"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r0)
            r0 = 0
            r14 = r14[r0]
            java.lang.String r1 = "null cannot be cast to non-null type kotlin.String"
            if (r14 == 0) goto L_0x0194
            java.lang.String r14 = (java.lang.String) r14
            expo.modules.filesystem.InfoOptions r15 = (expo.modules.filesystem.InfoOptions) r15
            java.lang.String r14 = expo.modules.filesystem.FileSystemModuleKt.slashifyFilePath(r14)
            android.net.Uri r2 = android.net.Uri.parse(r14)
            java.lang.String r3 = r2.getScheme()
            java.lang.String r4 = "file"
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)
            if (r3 == 0) goto L_0x0037
            expo.modules.filesystem.FileSystemModule r3 = r13.this$0
            kotlin.jvm.internal.Intrinsics.checkNotNull(r14, r1)
            java.lang.String r14 = r3.parseFileUri(r14)
            android.net.Uri r1 = android.net.Uri.parse(r14)
            goto L_0x0038
        L_0x0037:
            r1 = r2
        L_0x0038:
            expo.modules.filesystem.FileSystemModule r3 = r13.this$0
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            expo.modules.interfaces.filesystem.Permission r5 = expo.modules.interfaces.filesystem.Permission.READ
            r3.ensurePermission(r1, r5)
            java.lang.String r3 = r2.getScheme()
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)
            java.lang.String r4 = "md5"
            java.lang.String r5 = "size"
            java.lang.String r6 = "uri"
            r7 = 1
            java.lang.String r8 = "isDirectory"
            java.lang.String r9 = "exists"
            if (r3 == 0) goto L_0x00c7
            expo.modules.filesystem.FileSystemModule r14 = r13.this$0
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            java.io.File r14 = r14.toFile(r1)
            boolean r1 = r14.exists()
            if (r1 == 0) goto L_0x00ba
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            r0.putBoolean(r9, r7)
            boolean r1 = r14.isDirectory()
            r0.putBoolean(r8, r1)
            android.net.Uri r1 = android.net.Uri.fromFile(r14)
            java.lang.String r1 = r1.toString()
            r0.putString(r6, r1)
            expo.modules.filesystem.FileSystemModule r1 = r13.this$0
            long r1 = r1.getFileSize(r14)
            double r1 = (double) r1
            r0.putDouble(r5, r1)
            long r1 = r14.lastModified()
            double r1 = (double) r1
            r5 = 4562254508917369340(0x3f50624dd2f1a9fc, double:0.001)
            double r1 = r1 * r5
            java.lang.String r3 = "modificationTime"
            r0.putDouble(r3, r1)
            java.lang.Boolean r15 = r15.getMd5()
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r7)
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r15, (java.lang.Object) r1)
            if (r1 == 0) goto L_0x00a9
            goto L_0x00aa
        L_0x00a9:
            r15 = 0
        L_0x00aa:
            if (r15 == 0) goto L_0x0193
            r15.booleanValue()
            expo.modules.filesystem.FileSystemModule r15 = r13.this$0
            java.lang.String r14 = r15.md5(r14)
            r0.putString(r4, r14)
            goto L_0x0193
        L_0x00ba:
            android.os.Bundle r14 = new android.os.Bundle
            r14.<init>()
            r14.putBoolean(r9, r0)
            r14.putBoolean(r8, r0)
            goto L_0x0193
        L_0x00c7:
            java.lang.String r1 = r2.getScheme()
            java.lang.String r3 = "content"
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r3)
            java.lang.String r10 = "asset"
            if (r1 != 0) goto L_0x0101
            java.lang.String r1 = r2.getScheme()
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r10)
            if (r1 != 0) goto L_0x0101
            java.lang.String r1 = r2.getScheme()
            if (r1 != 0) goto L_0x00e6
            goto L_0x0101
        L_0x00e6:
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
        L_0x0101:
            java.lang.String r1 = r2.getScheme()     // Catch:{ FileNotFoundException -> 0x0188 }
            if (r1 == 0) goto L_0x013c
            int r11 = r1.hashCode()     // Catch:{ FileNotFoundException -> 0x0188 }
            r12 = 93121264(0x58ceaf0, float:1.3251839E-35)
            if (r11 == r12) goto L_0x012b
            r10 = 951530617(0x38b73479, float:8.735894E-5)
            if (r11 == r10) goto L_0x0116
            goto L_0x013c
        L_0x0116:
            boolean r1 = r1.equals(r3)     // Catch:{ FileNotFoundException -> 0x0188 }
            if (r1 == 0) goto L_0x013c
            expo.modules.filesystem.FileSystemModule r14 = r13.this$0     // Catch:{ FileNotFoundException -> 0x0188 }
            android.content.Context r14 = r14.getContext()     // Catch:{ FileNotFoundException -> 0x0188 }
            android.content.ContentResolver r14 = r14.getContentResolver()     // Catch:{ FileNotFoundException -> 0x0188 }
            java.io.InputStream r14 = r14.openInputStream(r2)     // Catch:{ FileNotFoundException -> 0x0188 }
            goto L_0x0142
        L_0x012b:
            boolean r1 = r1.equals(r10)     // Catch:{ FileNotFoundException -> 0x0188 }
            if (r1 != 0) goto L_0x0132
            goto L_0x013c
        L_0x0132:
            expo.modules.filesystem.FileSystemModule r14 = r13.this$0     // Catch:{ FileNotFoundException -> 0x0188 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)     // Catch:{ FileNotFoundException -> 0x0188 }
            java.io.InputStream r14 = r14.openAssetInputStream(r2)     // Catch:{ FileNotFoundException -> 0x0188 }
            goto L_0x0142
        L_0x013c:
            expo.modules.filesystem.FileSystemModule r1 = r13.this$0     // Catch:{ FileNotFoundException -> 0x0188 }
            java.io.InputStream r14 = r1.openResourceInputStream(r14)     // Catch:{ FileNotFoundException -> 0x0188 }
        L_0x0142:
            if (r14 == 0) goto L_0x0182
            android.os.Bundle r1 = new android.os.Bundle     // Catch:{ FileNotFoundException -> 0x0188 }
            r1.<init>()     // Catch:{ FileNotFoundException -> 0x0188 }
            r1.putBoolean(r9, r7)     // Catch:{ FileNotFoundException -> 0x0188 }
            r1.putBoolean(r8, r0)     // Catch:{ FileNotFoundException -> 0x0188 }
            java.lang.String r2 = r2.toString()     // Catch:{ FileNotFoundException -> 0x0188 }
            r1.putString(r6, r2)     // Catch:{ FileNotFoundException -> 0x0188 }
            int r2 = r14.available()     // Catch:{ FileNotFoundException -> 0x0188 }
            double r2 = (double) r2     // Catch:{ FileNotFoundException -> 0x0188 }
            r1.putDouble(r5, r2)     // Catch:{ FileNotFoundException -> 0x0188 }
            java.lang.Boolean r15 = r15.getMd5()     // Catch:{ FileNotFoundException -> 0x0188 }
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r7)     // Catch:{ FileNotFoundException -> 0x0188 }
            boolean r15 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r15, (java.lang.Object) r2)     // Catch:{ FileNotFoundException -> 0x0188 }
            if (r15 == 0) goto L_0x0193
            byte[] r14 = org.apache.commons.codec.digest.DigestUtils.md5((java.io.InputStream) r14)     // Catch:{ FileNotFoundException -> 0x0188 }
            char[] r14 = org.apache.commons.codec.binary.Hex.encodeHex(r14)     // Catch:{ FileNotFoundException -> 0x0188 }
            java.lang.String r15 = "encodeHex(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r14, r15)     // Catch:{ FileNotFoundException -> 0x0188 }
            java.lang.String r15 = new java.lang.String     // Catch:{ FileNotFoundException -> 0x0188 }
            r15.<init>(r14)     // Catch:{ FileNotFoundException -> 0x0188 }
            r1.putString(r4, r15)     // Catch:{ FileNotFoundException -> 0x0188 }
            goto L_0x0193
        L_0x0182:
            java.io.FileNotFoundException r14 = new java.io.FileNotFoundException     // Catch:{ FileNotFoundException -> 0x0188 }
            r14.<init>()     // Catch:{ FileNotFoundException -> 0x0188 }
            throw r14     // Catch:{ FileNotFoundException -> 0x0188 }
        L_0x0188:
            android.os.Bundle r14 = new android.os.Bundle
            r14.<init>()
            r14.putBoolean(r9, r0)
            r14.putBoolean(r8, r0)
        L_0x0193:
            return
        L_0x0194:
            java.lang.NullPointerException r14 = new java.lang.NullPointerException
            r14.<init>(r1)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$2.invoke(java.lang.Object[], expo.modules.kotlin.Promise):void");
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Object[]) obj, (Promise) obj2);
        return Unit.INSTANCE;
    }
}

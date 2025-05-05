package expo.modules.filesystem;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\u0006\b\u0002\u0010\u0004\u0018\u00012\u0010\u0010\u0005\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0006H\n¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"<anonymous>", "", "R", "P0", "P1", "it", "", "invoke", "([Ljava/lang/Object;)Ljava/lang/Object;", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$13"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$5 extends Lambda implements Function1<Object[], Object> {
    final /* synthetic */ FileSystemModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$5(FileSystemModule fileSystemModule) {
        super(1);
        this.this$0 = fileSystemModule;
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x0144 A[Catch:{ FileNotFoundException -> 0x0189 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0183 A[Catch:{ FileNotFoundException -> 0x0189 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invoke(java.lang.Object[] r15) {
        /*
            r14 = this;
            java.lang.String r0 = "it"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r0)
            r0 = 0
            r1 = r15[r0]
            java.lang.String r2 = "null cannot be cast to non-null type kotlin.String"
            if (r1 == 0) goto L_0x019f
            java.lang.String r1 = (java.lang.String) r1
            r3 = 1
            r15 = r15[r3]
            if (r15 == 0) goto L_0x0197
            expo.modules.filesystem.InfoOptions r15 = (expo.modules.filesystem.InfoOptions) r15
            java.lang.String r1 = expo.modules.filesystem.FileSystemModuleKt.slashifyFilePath(r1)
            android.net.Uri r4 = android.net.Uri.parse(r1)
            java.lang.String r5 = r4.getScheme()
            java.lang.String r6 = "file"
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)
            if (r5 == 0) goto L_0x0037
            expo.modules.filesystem.FileSystemModule r5 = r14.this$0
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1, r2)
            java.lang.String r1 = r5.parseFileUri(r1)
            android.net.Uri r2 = android.net.Uri.parse(r1)
            goto L_0x0038
        L_0x0037:
            r2 = r4
        L_0x0038:
            expo.modules.filesystem.FileSystemModule r5 = r14.this$0
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            expo.modules.interfaces.filesystem.Permission r7 = expo.modules.interfaces.filesystem.Permission.READ
            r5.ensurePermission(r2, r7)
            java.lang.String r5 = r4.getScheme()
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)
            java.lang.String r6 = "md5"
            java.lang.String r7 = "size"
            java.lang.String r8 = "uri"
            java.lang.String r9 = "isDirectory"
            java.lang.String r10 = "exists"
            if (r5 == 0) goto L_0x00c7
            expo.modules.filesystem.FileSystemModule r1 = r14.this$0
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            java.io.File r1 = r1.toFile(r2)
            boolean r2 = r1.exists()
            if (r2 == 0) goto L_0x00b9
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            r0.putBoolean(r10, r3)
            boolean r2 = r1.isDirectory()
            r0.putBoolean(r9, r2)
            android.net.Uri r2 = android.net.Uri.fromFile(r1)
            java.lang.String r2 = r2.toString()
            r0.putString(r8, r2)
            expo.modules.filesystem.FileSystemModule r2 = r14.this$0
            long r4 = r2.getFileSize(r1)
            double r4 = (double) r4
            r0.putDouble(r7, r4)
            long r4 = r1.lastModified()
            double r4 = (double) r4
            r7 = 4562254508917369340(0x3f50624dd2f1a9fc, double:0.001)
            double r4 = r4 * r7
            java.lang.String r2 = "modificationTime"
            r0.putDouble(r2, r4)
            java.lang.Boolean r15 = r15.getMd5()
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r3)
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r15, (java.lang.Object) r2)
            if (r2 == 0) goto L_0x00a8
            goto L_0x00a9
        L_0x00a8:
            r15 = 0
        L_0x00a9:
            if (r15 == 0) goto L_0x0196
            r15.booleanValue()
            expo.modules.filesystem.FileSystemModule r15 = r14.this$0
            java.lang.String r15 = r15.md5(r1)
            r0.putString(r6, r15)
            goto L_0x0196
        L_0x00b9:
            android.os.Bundle r15 = new android.os.Bundle
            r15.<init>()
            r15.putBoolean(r10, r0)
            r15.putBoolean(r9, r0)
        L_0x00c4:
            r0 = r15
            goto L_0x0196
        L_0x00c7:
            java.lang.String r2 = r4.getScheme()
            java.lang.String r5 = "content"
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r5)
            java.lang.String r11 = "asset"
            if (r2 != 0) goto L_0x0101
            java.lang.String r2 = r4.getScheme()
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r11)
            if (r2 != 0) goto L_0x0101
            java.lang.String r2 = r4.getScheme()
            if (r2 != 0) goto L_0x00e6
            goto L_0x0101
        L_0x00e6:
            java.io.IOException r15 = new java.io.IOException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Unsupported scheme for location '"
            r0.<init>(r1)
            java.lang.StringBuilder r0 = r0.append(r4)
            java.lang.String r1 = "'."
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            r15.<init>(r0)
            throw r15
        L_0x0101:
            java.lang.String r2 = r4.getScheme()     // Catch:{ FileNotFoundException -> 0x0189 }
            if (r2 == 0) goto L_0x013c
            int r12 = r2.hashCode()     // Catch:{ FileNotFoundException -> 0x0189 }
            r13 = 93121264(0x58ceaf0, float:1.3251839E-35)
            if (r12 == r13) goto L_0x012b
            r11 = 951530617(0x38b73479, float:8.735894E-5)
            if (r12 == r11) goto L_0x0116
            goto L_0x013c
        L_0x0116:
            boolean r2 = r2.equals(r5)     // Catch:{ FileNotFoundException -> 0x0189 }
            if (r2 == 0) goto L_0x013c
            expo.modules.filesystem.FileSystemModule r1 = r14.this$0     // Catch:{ FileNotFoundException -> 0x0189 }
            android.content.Context r1 = r1.getContext()     // Catch:{ FileNotFoundException -> 0x0189 }
            android.content.ContentResolver r1 = r1.getContentResolver()     // Catch:{ FileNotFoundException -> 0x0189 }
            java.io.InputStream r1 = r1.openInputStream(r4)     // Catch:{ FileNotFoundException -> 0x0189 }
            goto L_0x0142
        L_0x012b:
            boolean r2 = r2.equals(r11)     // Catch:{ FileNotFoundException -> 0x0189 }
            if (r2 != 0) goto L_0x0132
            goto L_0x013c
        L_0x0132:
            expo.modules.filesystem.FileSystemModule r1 = r14.this$0     // Catch:{ FileNotFoundException -> 0x0189 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)     // Catch:{ FileNotFoundException -> 0x0189 }
            java.io.InputStream r1 = r1.openAssetInputStream(r4)     // Catch:{ FileNotFoundException -> 0x0189 }
            goto L_0x0142
        L_0x013c:
            expo.modules.filesystem.FileSystemModule r2 = r14.this$0     // Catch:{ FileNotFoundException -> 0x0189 }
            java.io.InputStream r1 = r2.openResourceInputStream(r1)     // Catch:{ FileNotFoundException -> 0x0189 }
        L_0x0142:
            if (r1 == 0) goto L_0x0183
            android.os.Bundle r2 = new android.os.Bundle     // Catch:{ FileNotFoundException -> 0x0189 }
            r2.<init>()     // Catch:{ FileNotFoundException -> 0x0189 }
            r2.putBoolean(r10, r3)     // Catch:{ FileNotFoundException -> 0x0189 }
            r2.putBoolean(r9, r0)     // Catch:{ FileNotFoundException -> 0x0189 }
            java.lang.String r4 = r4.toString()     // Catch:{ FileNotFoundException -> 0x0189 }
            r2.putString(r8, r4)     // Catch:{ FileNotFoundException -> 0x0189 }
            int r4 = r1.available()     // Catch:{ FileNotFoundException -> 0x0189 }
            double r4 = (double) r4     // Catch:{ FileNotFoundException -> 0x0189 }
            r2.putDouble(r7, r4)     // Catch:{ FileNotFoundException -> 0x0189 }
            java.lang.Boolean r15 = r15.getMd5()     // Catch:{ FileNotFoundException -> 0x0189 }
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ FileNotFoundException -> 0x0189 }
            boolean r15 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r15, (java.lang.Object) r3)     // Catch:{ FileNotFoundException -> 0x0189 }
            if (r15 == 0) goto L_0x0181
            byte[] r15 = org.apache.commons.codec.digest.DigestUtils.md5((java.io.InputStream) r1)     // Catch:{ FileNotFoundException -> 0x0189 }
            char[] r15 = org.apache.commons.codec.binary.Hex.encodeHex(r15)     // Catch:{ FileNotFoundException -> 0x0189 }
            java.lang.String r1 = "encodeHex(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r15, r1)     // Catch:{ FileNotFoundException -> 0x0189 }
            java.lang.String r1 = new java.lang.String     // Catch:{ FileNotFoundException -> 0x0189 }
            r1.<init>(r15)     // Catch:{ FileNotFoundException -> 0x0189 }
            r2.putString(r6, r1)     // Catch:{ FileNotFoundException -> 0x0189 }
        L_0x0181:
            r0 = r2
            goto L_0x0196
        L_0x0183:
            java.io.FileNotFoundException r15 = new java.io.FileNotFoundException     // Catch:{ FileNotFoundException -> 0x0189 }
            r15.<init>()     // Catch:{ FileNotFoundException -> 0x0189 }
            throw r15     // Catch:{ FileNotFoundException -> 0x0189 }
        L_0x0189:
            android.os.Bundle r15 = new android.os.Bundle
            r15.<init>()
            r15.putBoolean(r10, r0)
            r15.putBoolean(r9, r0)
            goto L_0x00c4
        L_0x0196:
            return r0
        L_0x0197:
            java.lang.NullPointerException r15 = new java.lang.NullPointerException
            java.lang.String r0 = "null cannot be cast to non-null type expo.modules.filesystem.InfoOptions"
            r15.<init>(r0)
            throw r15
        L_0x019f:
            java.lang.NullPointerException r15 = new java.lang.NullPointerException
            r15.<init>(r2)
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.filesystem.FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$5.invoke(java.lang.Object[]):java.lang.Object");
    }
}

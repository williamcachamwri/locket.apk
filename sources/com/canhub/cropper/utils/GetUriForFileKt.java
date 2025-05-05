package com.canhub.cropper.utils;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000¨\u0006\u0006"}, d2 = {"getUriForFile", "Landroid/net/Uri;", "context", "Landroid/content/Context;", "file", "Ljava/io/File;", "cropper_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* compiled from: GetUriForFile.kt */
public final class GetUriForFileKt {
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00c7 A[Catch:{ all -> 0x010a }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00d3 A[Catch:{ all -> 0x010a }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0101 A[SYNTHETIC, Splitter:B:39:0x0101] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0106 A[Catch:{ Exception -> 0x0116 }] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x010d A[Catch:{ Exception -> 0x0116 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0112 A[Catch:{ Exception -> 0x0116 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final android.net.Uri getUriForFile(android.content.Context r9, java.io.File r10) {
        /*
            java.lang.String r0 = "AIC"
            java.lang.String r1 = "content://"
            java.lang.String r2 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r2)
            java.lang.String r2 = "file"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r2)
            java.lang.String r2 = r9.getPackageName()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.StringBuilder r2 = r3.append(r2)
            java.lang.String r3 = ".cropper.fileprovider"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            java.lang.String r3 = "Try get URI for scope storage - content://"
            android.util.Log.i(r0, r3)     // Catch:{ Exception -> 0x0034 }
            android.net.Uri r3 = androidx.core.content.FileProvider.getUriForFile(r9, r2, r10)     // Catch:{ Exception -> 0x0034 }
            java.lang.String r4 = "getUriForFile(context, authority, file)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)     // Catch:{ Exception -> 0x0034 }
            return r3
        L_0x0034:
            r3 = move-exception
            java.lang.String r3 = r3.getMessage()     // Catch:{ Exception -> 0x0116 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ Exception -> 0x0116 }
            io.sentry.android.core.SentryLogcatAdapter.e(r0, r3)     // Catch:{ Exception -> 0x0116 }
            java.lang.String r3 = "ANR Risk -- Copying the file the location cache to avoid 'external-files-path' bug for N+ devices"
            io.sentry.android.core.SentryLogcatAdapter.w((java.lang.String) r0, (java.lang.String) r3)     // Catch:{ Exception -> 0x0116 }
            java.io.File r3 = new java.io.File     // Catch:{ Exception -> 0x0116 }
            java.io.File r4 = r9.getCacheDir()     // Catch:{ Exception -> 0x0116 }
            java.lang.String r5 = "CROP_LIB_CACHE"
            r3.<init>(r4, r5)     // Catch:{ Exception -> 0x0116 }
            java.io.File r4 = new java.io.File     // Catch:{ Exception -> 0x0116 }
            java.lang.String r5 = r10.getName()     // Catch:{ Exception -> 0x0116 }
            r4.<init>(r3, r5)     // Catch:{ Exception -> 0x0116 }
            r3 = 0
            r5 = 0
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch:{ Exception -> 0x009a, all -> 0x0097 }
            r6.<init>(r10)     // Catch:{ Exception -> 0x009a, all -> 0x0097 }
            java.io.FileInputStream r6 = io.sentry.instrumentation.file.SentryFileInputStream.Factory.create((java.io.FileInputStream) r6, (java.io.File) r10)     // Catch:{ Exception -> 0x009a, all -> 0x0097 }
            java.io.InputStream r6 = (java.io.InputStream) r6     // Catch:{ Exception -> 0x009a, all -> 0x0097 }
            java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0093, all -> 0x008e }
            r7.<init>(r4)     // Catch:{ Exception -> 0x0093, all -> 0x008e }
            java.io.FileOutputStream r7 = io.sentry.instrumentation.file.SentryFileOutputStream.Factory.create((java.io.FileOutputStream) r7, (java.io.File) r4)     // Catch:{ Exception -> 0x0093, all -> 0x008e }
            java.io.OutputStream r7 = (java.io.OutputStream) r7     // Catch:{ Exception -> 0x0093, all -> 0x008e }
            r8 = 2
            kotlin.io.ByteStreamsKt.copyTo$default(r6, r7, r3, r8, r5)     // Catch:{ Exception -> 0x008c, all -> 0x008a }
            java.lang.String r5 = "Completed Android N+ file copy. Attempting to return the cached file"
            android.util.Log.i(r0, r5)     // Catch:{ Exception -> 0x008c, all -> 0x008a }
            android.net.Uri r4 = androidx.core.content.FileProvider.getUriForFile(r9, r2, r4)     // Catch:{ Exception -> 0x008c, all -> 0x008a }
            java.lang.String r5 = "getUriForFile(context, authority, cacheLocation)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)     // Catch:{ Exception -> 0x008c, all -> 0x008a }
            r6.close()     // Catch:{ Exception -> 0x0116 }
            r7.close()     // Catch:{ Exception -> 0x0116 }
            return r4
        L_0x008a:
            r1 = move-exception
            goto L_0x0090
        L_0x008c:
            r4 = move-exception
            goto L_0x0095
        L_0x008e:
            r1 = move-exception
            r7 = r5
        L_0x0090:
            r5 = r6
            goto L_0x010b
        L_0x0093:
            r4 = move-exception
            r7 = r5
        L_0x0095:
            r5 = r6
            goto L_0x009c
        L_0x0097:
            r1 = move-exception
            r7 = r5
            goto L_0x010b
        L_0x009a:
            r4 = move-exception
            r7 = r5
        L_0x009c:
            java.lang.String r4 = r4.getMessage()     // Catch:{ all -> 0x010a }
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x010a }
            io.sentry.android.core.SentryLogcatAdapter.e(r0, r4)     // Catch:{ all -> 0x010a }
            java.lang.String r4 = "Trying to provide URI manually"
            android.util.Log.i(r0, r4)     // Catch:{ all -> 0x010a }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x010a }
            r4.<init>(r1)     // Catch:{ all -> 0x010a }
            java.lang.StringBuilder r1 = r4.append(r2)     // Catch:{ all -> 0x010a }
            java.lang.String r2 = "/files/my_images/"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x010a }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x010a }
            com.canhub.cropper.common.CommonVersionCheck r2 = com.canhub.cropper.common.CommonVersionCheck.INSTANCE     // Catch:{ all -> 0x010a }
            boolean r2 = r2.isAtLeastO26()     // Catch:{ all -> 0x010a }
            if (r2 == 0) goto L_0x00d3
            java.lang.String[] r2 = new java.lang.String[r3]     // Catch:{ all -> 0x010a }
            java.nio.file.Path r2 = java.nio.file.Paths.get(r1, r2)     // Catch:{ all -> 0x010a }
            java.nio.file.attribute.FileAttribute[] r3 = new java.nio.file.attribute.FileAttribute[r3]     // Catch:{ all -> 0x010a }
            java.nio.file.Files.createDirectories(r2, r3)     // Catch:{ all -> 0x010a }
            goto L_0x00e1
        L_0x00d3:
            java.io.File r2 = new java.io.File     // Catch:{ all -> 0x010a }
            r2.<init>(r1)     // Catch:{ all -> 0x010a }
            boolean r3 = r2.exists()     // Catch:{ all -> 0x010a }
            if (r3 != 0) goto L_0x00e1
            r2.mkdirs()     // Catch:{ all -> 0x010a }
        L_0x00e1:
            java.lang.String r2 = r10.getName()     // Catch:{ all -> 0x010a }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x010a }
            r3.<init>()     // Catch:{ all -> 0x010a }
            java.lang.StringBuilder r1 = r3.append(r1)     // Catch:{ all -> 0x010a }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x010a }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x010a }
            android.net.Uri r1 = android.net.Uri.parse(r1)     // Catch:{ all -> 0x010a }
            java.lang.String r2 = "parse(\"$path${file.name}\")"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)     // Catch:{ all -> 0x010a }
            if (r5 == 0) goto L_0x0104
            r5.close()     // Catch:{ Exception -> 0x0116 }
        L_0x0104:
            if (r7 == 0) goto L_0x0109
            r7.close()     // Catch:{ Exception -> 0x0116 }
        L_0x0109:
            return r1
        L_0x010a:
            r1 = move-exception
        L_0x010b:
            if (r5 == 0) goto L_0x0110
            r5.close()     // Catch:{ Exception -> 0x0116 }
        L_0x0110:
            if (r7 == 0) goto L_0x0115
            r7.close()     // Catch:{ Exception -> 0x0116 }
        L_0x0115:
            throw r1     // Catch:{ Exception -> 0x0116 }
        L_0x0116:
            r1 = move-exception
            java.lang.String r1 = r1.getMessage()
            java.lang.String r1 = java.lang.String.valueOf(r1)
            io.sentry.android.core.SentryLogcatAdapter.e(r0, r1)
            com.canhub.cropper.common.CommonVersionCheck r1 = com.canhub.cropper.common.CommonVersionCheck.INSTANCE
            boolean r1 = r1.isAtLeastQ29()
            if (r1 != 0) goto L_0x0158
            java.io.File r9 = r9.getExternalCacheDir()
            if (r9 == 0) goto L_0x0158
            java.lang.String r1 = "Use External storage, do not work for OS 29 and above"
            android.util.Log.i(r0, r1)     // Catch:{ Exception -> 0x014c }
            java.io.File r1 = new java.io.File     // Catch:{ Exception -> 0x014c }
            java.lang.String r9 = r9.getPath()     // Catch:{ Exception -> 0x014c }
            java.lang.String r2 = r10.getAbsolutePath()     // Catch:{ Exception -> 0x014c }
            r1.<init>(r9, r2)     // Catch:{ Exception -> 0x014c }
            android.net.Uri r9 = android.net.Uri.fromFile(r1)     // Catch:{ Exception -> 0x014c }
            java.lang.String r1 = "fromFile(File(cacheDir.path, file.absolutePath))"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r1)     // Catch:{ Exception -> 0x014c }
            return r9
        L_0x014c:
            r9 = move-exception
            java.lang.String r9 = r9.getMessage()
            java.lang.String r9 = java.lang.String.valueOf(r9)
            io.sentry.android.core.SentryLogcatAdapter.e(r0, r9)
        L_0x0158:
            java.lang.String r9 = "Try get URI using file://"
            android.util.Log.i(r0, r9)
            android.net.Uri r9 = android.net.Uri.fromFile(r10)
            java.lang.String r10 = "fromFile(file)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r10)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.canhub.cropper.utils.GetUriForFileKt.getUriForFile(android.content.Context, java.io.File):android.net.Uri");
    }
}

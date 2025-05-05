package androidx.camera.video.internal.utils;

import java.io.File;

public final class OutputUtil {
    private static final String TAG = "OutputUtil";

    private OutputUtil() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0053  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getAbsolutePathFromUri(android.content.ContentResolver r7, android.net.Uri r8, java.lang.String r9) {
        /*
            r0 = 0
            java.lang.String[] r3 = new java.lang.String[]{r9}     // Catch:{ RuntimeException -> 0x002b, all -> 0x0029 }
            r4 = 0
            r5 = 0
            r6 = 0
            r1 = r7
            r2 = r8
            android.database.Cursor r7 = r1.query(r2, r3, r4, r5, r6)     // Catch:{ RuntimeException -> 0x002b, all -> 0x0029 }
            if (r7 != 0) goto L_0x0016
            if (r7 == 0) goto L_0x0015
            r7.close()
        L_0x0015:
            return r0
        L_0x0016:
            int r9 = r7.getColumnIndexOrThrow(r9)     // Catch:{ RuntimeException -> 0x0027 }
            r7.moveToFirst()     // Catch:{ RuntimeException -> 0x0027 }
            java.lang.String r8 = r7.getString(r9)     // Catch:{ RuntimeException -> 0x0027 }
            if (r7 == 0) goto L_0x0026
            r7.close()
        L_0x0026:
            return r8
        L_0x0027:
            r9 = move-exception
            goto L_0x002d
        L_0x0029:
            r8 = move-exception
            goto L_0x0051
        L_0x002b:
            r9 = move-exception
            r7 = r0
        L_0x002d:
            java.lang.String r1 = "OutputUtil"
            java.lang.String r2 = "Failed in getting absolute path for Uri %s with Exception %s"
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x004f }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x004f }
            r4 = 0
            r3[r4] = r8     // Catch:{ all -> 0x004f }
            java.lang.String r8 = r9.toString()     // Catch:{ all -> 0x004f }
            r9 = 1
            r3[r9] = r8     // Catch:{ all -> 0x004f }
            java.lang.String r8 = java.lang.String.format(r2, r3)     // Catch:{ all -> 0x004f }
            androidx.camera.core.Logger.e(r1, r8)     // Catch:{ all -> 0x004f }
            if (r7 == 0) goto L_0x004e
            r7.close()
        L_0x004e:
            return r0
        L_0x004f:
            r8 = move-exception
            r0 = r7
        L_0x0051:
            if (r0 == 0) goto L_0x0056
            r0.close()
        L_0x0056:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.video.internal.utils.OutputUtil.getAbsolutePathFromUri(android.content.ContentResolver, android.net.Uri, java.lang.String):java.lang.String");
    }

    public static boolean createParentFolder(File file) {
        File parentFile = file.getParentFile();
        if (parentFile == null) {
            return false;
        }
        return parentFile.exists() ? parentFile.isDirectory() : parentFile.mkdirs();
    }
}

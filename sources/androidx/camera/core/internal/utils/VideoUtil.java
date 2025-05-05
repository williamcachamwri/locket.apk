package androidx.camera.core.internal.utils;

public final class VideoUtil {
    private static final String TAG = "VideoUtil";

    private VideoUtil() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0056  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getAbsolutePathFromUri(android.content.ContentResolver r10, android.net.Uri r11) {
        /*
            java.lang.String r0 = "_data"
            r1 = 0
            r2 = 1
            r3 = 0
            java.lang.String[] r6 = new java.lang.String[r2]     // Catch:{ RuntimeException -> 0x0031 }
            r6[r1] = r0     // Catch:{ RuntimeException -> 0x0031 }
            r7 = 0
            r8 = 0
            r9 = 0
            r4 = r10
            r5 = r11
            android.database.Cursor r3 = r4.query(r5, r6, r7, r8, r9)     // Catch:{ RuntimeException -> 0x0031 }
            java.lang.Object r10 = androidx.core.util.Preconditions.checkNotNull(r3)     // Catch:{ RuntimeException -> 0x0031 }
            android.database.Cursor r10 = (android.database.Cursor) r10     // Catch:{ RuntimeException -> 0x0031 }
            int r0 = r10.getColumnIndexOrThrow(r0)     // Catch:{ RuntimeException -> 0x002c, all -> 0x0029 }
            r10.moveToFirst()     // Catch:{ RuntimeException -> 0x002c, all -> 0x0029 }
            java.lang.String r11 = r10.getString(r0)     // Catch:{ RuntimeException -> 0x002c, all -> 0x0029 }
            if (r10 == 0) goto L_0x0028
            r10.close()
        L_0x0028:
            return r11
        L_0x0029:
            r11 = move-exception
            r3 = r10
            goto L_0x0054
        L_0x002c:
            r0 = move-exception
            r3 = r10
            goto L_0x0032
        L_0x002f:
            r11 = move-exception
            goto L_0x0054
        L_0x0031:
            r0 = move-exception
        L_0x0032:
            java.lang.String r10 = "VideoUtil"
            java.lang.String r4 = "Failed in getting absolute path for Uri %s with Exception %s"
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ all -> 0x002f }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x002f }
            r5[r1] = r11     // Catch:{ all -> 0x002f }
            java.lang.String r11 = r0.toString()     // Catch:{ all -> 0x002f }
            r5[r2] = r11     // Catch:{ all -> 0x002f }
            java.lang.String r11 = java.lang.String.format(r4, r5)     // Catch:{ all -> 0x002f }
            androidx.camera.core.Logger.e(r10, r11)     // Catch:{ all -> 0x002f }
            java.lang.String r10 = ""
            if (r3 == 0) goto L_0x0053
            r3.close()
        L_0x0053:
            return r10
        L_0x0054:
            if (r3 == 0) goto L_0x0059
            r3.close()
        L_0x0059:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.internal.utils.VideoUtil.getAbsolutePathFromUri(android.content.ContentResolver, android.net.Uri):java.lang.String");
    }
}

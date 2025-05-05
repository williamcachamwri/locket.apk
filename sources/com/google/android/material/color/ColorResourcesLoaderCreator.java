package com.google.android.material.color;

final class ColorResourcesLoaderCreator {
    private static final String TAG = "ColorResourcesLoaderCreator";

    private ColorResourcesLoaderCreator() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x006b A[SYNTHETIC, Splitter:B:42:0x006b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static android.content.res.loader.ResourcesLoader create(android.content.Context r4, java.util.Map<java.lang.Integer, java.lang.Integer> r5) {
        /*
            java.lang.String r0 = "Table created, length: "
            r1 = 0
            byte[] r4 = com.google.android.material.color.ColorResourcesTableCreator.create(r4, r5)     // Catch:{ Exception -> 0x006f }
            java.lang.String r5 = TAG     // Catch:{ Exception -> 0x006f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x006f }
            r2.<init>(r0)     // Catch:{ Exception -> 0x006f }
            int r0 = r4.length     // Catch:{ Exception -> 0x006f }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ Exception -> 0x006f }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x006f }
            android.util.Log.i(r5, r0)     // Catch:{ Exception -> 0x006f }
            int r5 = r4.length     // Catch:{ Exception -> 0x006f }
            if (r5 != 0) goto L_0x001e
            return r1
        L_0x001e:
            java.lang.String r5 = "temp.arsc"
            r0 = 0
            java.io.FileDescriptor r5 = android.system.Os.memfd_create(r5, r0)     // Catch:{ all -> 0x0067 }
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch:{ all -> 0x0065 }
            r0.<init>(r5)     // Catch:{ all -> 0x0065 }
            java.io.FileOutputStream r0 = io.sentry.instrumentation.file.SentryFileOutputStream.Factory.create((java.io.FileOutputStream) r0, (java.io.FileDescriptor) r5)     // Catch:{ all -> 0x0065 }
            r0.write(r4)     // Catch:{ all -> 0x005b }
            android.os.ParcelFileDescriptor r4 = android.os.ParcelFileDescriptor.dup(r5)     // Catch:{ all -> 0x005b }
            android.content.res.loader.ResourcesLoader r2 = new android.content.res.loader.ResourcesLoader     // Catch:{ all -> 0x004f }
            r2.<init>()     // Catch:{ all -> 0x004f }
            android.content.res.loader.ResourcesProvider r3 = android.content.res.loader.ResourcesProvider.loadFromTable(r4, r1)     // Catch:{ all -> 0x004f }
            r2.addProvider(r3)     // Catch:{ all -> 0x004f }
            if (r4 == 0) goto L_0x0046
            r4.close()     // Catch:{ all -> 0x005b }
        L_0x0046:
            r0.close()     // Catch:{ all -> 0x0065 }
            if (r5 == 0) goto L_0x004e
            android.system.Os.close(r5)     // Catch:{ Exception -> 0x006f }
        L_0x004e:
            return r2
        L_0x004f:
            r2 = move-exception
            if (r4 == 0) goto L_0x005a
            r4.close()     // Catch:{ all -> 0x0056 }
            goto L_0x005a
        L_0x0056:
            r4 = move-exception
            r2.addSuppressed(r4)     // Catch:{ all -> 0x005b }
        L_0x005a:
            throw r2     // Catch:{ all -> 0x005b }
        L_0x005b:
            r4 = move-exception
            r0.close()     // Catch:{ all -> 0x0060 }
            goto L_0x0064
        L_0x0060:
            r0 = move-exception
            r4.addSuppressed(r0)     // Catch:{ all -> 0x0065 }
        L_0x0064:
            throw r4     // Catch:{ all -> 0x0065 }
        L_0x0065:
            r4 = move-exception
            goto L_0x0069
        L_0x0067:
            r4 = move-exception
            r5 = r1
        L_0x0069:
            if (r5 == 0) goto L_0x006e
            android.system.Os.close(r5)     // Catch:{ Exception -> 0x006f }
        L_0x006e:
            throw r4     // Catch:{ Exception -> 0x006f }
        L_0x006f:
            r4 = move-exception
            java.lang.String r5 = TAG
            java.lang.String r0 = "Failed to create the ColorResourcesTableCreator."
            io.sentry.android.core.SentryLogcatAdapter.e(r5, r0, r4)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.color.ColorResourcesLoaderCreator.create(android.content.Context, java.util.Map):android.content.res.loader.ResourcesLoader");
    }
}

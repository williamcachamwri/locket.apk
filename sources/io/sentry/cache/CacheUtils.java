package io.sentry.cache;

import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

final class CacheUtils {
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    CacheUtils() {
    }

    static <T> void store(SentryOptions sentryOptions, T t, String str, String str2) {
        BufferedWriter bufferedWriter;
        File ensureCacheDir = ensureCacheDir(sentryOptions, str);
        if (ensureCacheDir == null) {
            sentryOptions.getLogger().log(SentryLevel.INFO, "Cache dir is not set, cannot store in scope cache", new Object[0]);
            return;
        }
        File file = new File(ensureCacheDir, str2);
        if (file.exists()) {
            sentryOptions.getLogger().log(SentryLevel.DEBUG, "Overwriting %s in scope cache", str2);
            if (!file.delete()) {
                sentryOptions.getLogger().log(SentryLevel.ERROR, "Failed to delete: %s", file.getAbsolutePath());
            }
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, UTF_8));
                sentryOptions.getSerializer().serialize(t, (Writer) bufferedWriter);
                bufferedWriter.close();
                fileOutputStream.close();
                return;
            } catch (Throwable th) {
                fileOutputStream.close();
                throw th;
            }
        } catch (Throwable th2) {
            sentryOptions.getLogger().log(SentryLevel.ERROR, th2, "Error persisting entity: %s", str2);
            return;
        }
        throw th;
    }

    static void delete(SentryOptions sentryOptions, String str, String str2) {
        File ensureCacheDir = ensureCacheDir(sentryOptions, str);
        if (ensureCacheDir == null) {
            sentryOptions.getLogger().log(SentryLevel.INFO, "Cache dir is not set, cannot delete from scope cache", new Object[0]);
            return;
        }
        File file = new File(ensureCacheDir, str2);
        if (file.exists()) {
            sentryOptions.getLogger().log(SentryLevel.DEBUG, "Deleting %s from scope cache", str2);
            if (!file.delete()) {
                sentryOptions.getLogger().log(SentryLevel.ERROR, "Failed to delete: %s", file.getAbsolutePath());
            }
        }
    }

    /* JADX WARNING: type inference failed for: r8v0, types: [io.sentry.JsonDeserializer, io.sentry.JsonDeserializer<R>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static <T, R> T read(io.sentry.SentryOptions r4, java.lang.String r5, java.lang.String r6, java.lang.Class<T> r7, io.sentry.JsonDeserializer<R> r8) {
        /*
            java.io.File r5 = ensureCacheDir(r4, r5)
            r0 = 0
            if (r5 != 0) goto L_0x0016
            io.sentry.ILogger r4 = r4.getLogger()
            io.sentry.SentryLevel r5 = io.sentry.SentryLevel.INFO
            r6 = 0
            java.lang.Object[] r6 = new java.lang.Object[r6]
            java.lang.String r7 = "Cache dir is not set, cannot read from scope cache"
            r4.log((io.sentry.SentryLevel) r5, (java.lang.String) r7, (java.lang.Object[]) r6)
            return r0
        L_0x0016:
            java.io.File r1 = new java.io.File
            r1.<init>(r5, r6)
            boolean r5 = r1.exists()
            if (r5 == 0) goto L_0x0067
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch:{ all -> 0x0056 }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ all -> 0x0056 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ all -> 0x0056 }
            r3.<init>(r1)     // Catch:{ all -> 0x0056 }
            java.nio.charset.Charset r1 = UTF_8     // Catch:{ all -> 0x0056 }
            r2.<init>(r3, r1)     // Catch:{ all -> 0x0056 }
            r5.<init>(r2)     // Catch:{ all -> 0x0056 }
            if (r8 != 0) goto L_0x0040
            io.sentry.ISerializer r8 = r4.getSerializer()     // Catch:{ all -> 0x004c }
            java.lang.Object r7 = r8.deserialize(r5, r7)     // Catch:{ all -> 0x004c }
            r5.close()     // Catch:{ all -> 0x0056 }
            return r7
        L_0x0040:
            io.sentry.ISerializer r1 = r4.getSerializer()     // Catch:{ all -> 0x004c }
            java.lang.Object r7 = r1.deserializeCollection(r5, r7, r8)     // Catch:{ all -> 0x004c }
            r5.close()     // Catch:{ all -> 0x0056 }
            return r7
        L_0x004c:
            r7 = move-exception
            r5.close()     // Catch:{ all -> 0x0051 }
            goto L_0x0055
        L_0x0051:
            r5 = move-exception
            r7.addSuppressed(r5)     // Catch:{ all -> 0x0056 }
        L_0x0055:
            throw r7     // Catch:{ all -> 0x0056 }
        L_0x0056:
            r5 = move-exception
            io.sentry.ILogger r4 = r4.getLogger()
            io.sentry.SentryLevel r7 = io.sentry.SentryLevel.ERROR
            java.lang.String r8 = "Error reading entity from scope cache: %s"
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            r4.log(r7, r5, r8, r6)
            goto L_0x0076
        L_0x0067:
            io.sentry.ILogger r4 = r4.getLogger()
            io.sentry.SentryLevel r5 = io.sentry.SentryLevel.DEBUG
            java.lang.String r7 = "No entry stored for %s"
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            r4.log((io.sentry.SentryLevel) r5, (java.lang.String) r7, (java.lang.Object[]) r6)
        L_0x0076:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.cache.CacheUtils.read(io.sentry.SentryOptions, java.lang.String, java.lang.String, java.lang.Class, io.sentry.JsonDeserializer):java.lang.Object");
    }

    private static File ensureCacheDir(SentryOptions sentryOptions, String str) {
        String cacheDirPath = sentryOptions.getCacheDirPath();
        if (cacheDirPath == null) {
            return null;
        }
        File file = new File(cacheDirPath, str);
        file.mkdirs();
        return file;
    }
}

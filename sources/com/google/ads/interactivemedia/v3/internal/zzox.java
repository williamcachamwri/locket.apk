package com.google.ads.interactivemedia.v3.internal;

import android.os.Build;
import android.text.TextUtils;
import com.google.android.gms.common.util.IOUtils;
import io.sentry.instrumentation.file.SentryFileOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzox {
    public static File zza(File file, boolean z) {
        if (z && file.exists() && !file.isDirectory()) {
            file.delete();
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static File zzb(String str, String str2, File file) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        return new File(zzc(str, file), str2);
    }

    public static File zzc(String str, File file) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file2 = new File(file, str);
        zza(file2, false);
        return file2;
    }

    public static boolean zzd(File file) {
        boolean z;
        if (!file.exists()) {
            return true;
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            z = true;
            for (int i = 0; i < listFiles.length; i++) {
                File file2 = listFiles[i];
                z = file2 != null && zzd(file2) && z;
            }
        } else {
            z = true;
        }
        if (!file.delete() || !z) {
            return false;
        }
        return true;
    }

    public static boolean zze(File file, byte[] bArr) {
        try {
            FileOutputStream create = SentryFileOutputStream.Factory.create(new FileOutputStream(file), file);
            if (Build.VERSION.SDK_INT >= 34) {
                file.setReadOnly();
            }
            create.write(bArr);
            create.flush();
            IOUtils.closeQuietly((Closeable) create);
            return true;
        } catch (IOException unused) {
            IOUtils.closeQuietly((Closeable) null);
            return false;
        } catch (Throwable th) {
            IOUtils.closeQuietly((Closeable) null);
            throw th;
        }
    }
}

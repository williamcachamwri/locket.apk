package com.google.android.gms.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import androidx.core.content.ContextCompat;
import com.google.android.gms.internal.gcm.zzq;
import io.sentry.android.core.SentryLogcatAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

final class zzn {
    zzn() {
    }

    /* access modifiers changed from: package-private */
    public final zzo zze(Context context, String str) throws zzp {
        zzo zzh = zzh(context, str);
        if (zzh != null) {
            return zzh;
        }
        return zzf(context, str);
    }

    /* access modifiers changed from: package-private */
    public final zzo zzf(Context context, String str) {
        zzo zzo = new zzo(zzd.zzl(), System.currentTimeMillis());
        try {
            zzo zzh = zzh(context, str);
            if (zzh != null) {
                if (Log.isLoggable("InstanceID", 3)) {
                    Log.d("InstanceID", "Loaded key after generating new one, using loaded one");
                }
                return zzh;
            }
        } catch (zzp unused) {
        }
        if (Log.isLoggable("InstanceID", 3)) {
            Log.d("InstanceID", "Generated new key");
        }
        zzd(context, str, zzo);
        zze(context, str, zzo);
        return zzo;
    }

    static void zzg(Context context, String str) {
        File zzj = zzj(context, str);
        if (zzj.exists()) {
            zzj.delete();
        }
    }

    static void zzi(Context context) {
        for (File file : zzj(context).listFiles()) {
            if (file.getName().startsWith("com.google.InstanceId")) {
                file.delete();
            }
        }
    }

    private final zzo zzh(Context context, String str) throws zzp {
        try {
            zzo zzi = zzi(context, str);
            if (zzi != null) {
                zze(context, str, zzi);
                return zzi;
            }
            e = null;
            try {
                zzo zzd = zzd(context.getSharedPreferences("com.google.android.gms.appid", 0), str);
                if (zzd != null) {
                    zzd(context, str, zzd);
                    return zzd;
                }
            } catch (zzp e) {
                e = e;
            }
            if (e == null) {
                return null;
            }
            throw e;
        } catch (zzp e2) {
            e = e2;
        }
    }

    private static KeyPair zzg(String str, String str2) throws zzp {
        try {
            byte[] decode = Base64.decode(str, 8);
            byte[] decode2 = Base64.decode(str2, 8);
            try {
                KeyFactory instance = KeyFactory.getInstance("RSA");
                return new KeyPair(instance.generatePublic(new X509EncodedKeySpec(decode)), instance.generatePrivate(new PKCS8EncodedKeySpec(decode2)));
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                String valueOf = String.valueOf(e);
                SentryLogcatAdapter.w("InstanceID", new StringBuilder(String.valueOf(valueOf).length() + 19).append("Invalid key stored ").append(valueOf).toString());
                throw new zzp(e);
            }
        } catch (IllegalArgumentException e2) {
            throw new zzp(e2);
        }
    }

    private final zzo zzi(Context context, String str) throws zzp {
        File zzj = zzj(context, str);
        if (!zzj.exists()) {
            return null;
        }
        try {
            return zzd(zzj);
        } catch (IOException e) {
            if (Log.isLoggable("InstanceID", 3)) {
                String valueOf = String.valueOf(e);
                Log.d("InstanceID", new StringBuilder(String.valueOf(valueOf).length() + 40).append("Failed to read key from file, retrying: ").append(valueOf).toString());
            }
            try {
                return zzd(zzj);
            } catch (IOException e2) {
                String valueOf2 = String.valueOf(e2);
                SentryLogcatAdapter.w("InstanceID", new StringBuilder(String.valueOf(valueOf2).length() + 45).append("IID file exists, but failed to read from it: ").append(valueOf2).toString());
                throw new zzp(e2);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004c, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        zzd(r5, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0050, code lost:
        throw r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void zzd(android.content.Context r4, java.lang.String r5, com.google.android.gms.iid.zzo r6) {
        /*
            java.lang.String r0 = "InstanceID"
            r1 = 3
            boolean r1 = android.util.Log.isLoggable(r0, r1)     // Catch:{ IOException -> 0x0051 }
            if (r1 == 0) goto L_0x000e
            java.lang.String r1 = "Writing key to properties file"
            android.util.Log.d(r0, r1)     // Catch:{ IOException -> 0x0051 }
        L_0x000e:
            java.io.File r4 = zzj(r4, r5)     // Catch:{ IOException -> 0x0051 }
            r4.createNewFile()     // Catch:{ IOException -> 0x0051 }
            java.util.Properties r5 = new java.util.Properties     // Catch:{ IOException -> 0x0051 }
            r5.<init>()     // Catch:{ IOException -> 0x0051 }
            java.lang.String r1 = "pub"
            java.lang.String r2 = r6.zzq()     // Catch:{ IOException -> 0x0051 }
            r5.setProperty(r1, r2)     // Catch:{ IOException -> 0x0051 }
            java.lang.String r1 = "pri"
            java.lang.String r2 = r6.zzr()     // Catch:{ IOException -> 0x0051 }
            r5.setProperty(r1, r2)     // Catch:{ IOException -> 0x0051 }
            java.lang.String r1 = "cre"
            long r2 = r6.zzcc     // Catch:{ IOException -> 0x0051 }
            java.lang.String r6 = java.lang.String.valueOf(r2)     // Catch:{ IOException -> 0x0051 }
            r5.setProperty(r1, r6)     // Catch:{ IOException -> 0x0051 }
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0051 }
            r6.<init>(r4)     // Catch:{ IOException -> 0x0051 }
            java.io.FileOutputStream r4 = io.sentry.instrumentation.file.SentryFileOutputStream.Factory.create((java.io.FileOutputStream) r6, (java.io.File) r4)     // Catch:{ IOException -> 0x0051 }
            r6 = 0
            r5.store(r4, r6)     // Catch:{ all -> 0x004a }
            zzd((java.lang.Throwable) r6, (java.io.FileOutputStream) r4)     // Catch:{ IOException -> 0x0051 }
            return
        L_0x004a:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x004c }
        L_0x004c:
            r6 = move-exception
            zzd((java.lang.Throwable) r5, (java.io.FileOutputStream) r4)     // Catch:{ IOException -> 0x0051 }
            throw r6     // Catch:{ IOException -> 0x0051 }
        L_0x0051:
            r4 = move-exception
            java.lang.String r4 = java.lang.String.valueOf(r4)
            java.lang.String r5 = java.lang.String.valueOf(r4)
            int r5 = r5.length()
            int r5 = r5 + 21
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>(r5)
            java.lang.String r5 = "Failed to write key: "
            java.lang.StringBuilder r5 = r6.append(r5)
            java.lang.StringBuilder r4 = r5.append(r4)
            java.lang.String r4 = r4.toString()
            io.sentry.android.core.SentryLogcatAdapter.w((java.lang.String) r0, (java.lang.String) r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.iid.zzn.zzd(android.content.Context, java.lang.String, com.google.android.gms.iid.zzo):void");
    }

    private static File zzj(Context context) {
        File noBackupFilesDir = ContextCompat.getNoBackupFilesDir(context);
        if (noBackupFilesDir != null && noBackupFilesDir.isDirectory()) {
            return noBackupFilesDir;
        }
        SentryLogcatAdapter.w("InstanceID", "noBackupFilesDir doesn't exist, using regular files directory instead");
        return context.getFilesDir();
    }

    private static File zzj(Context context, String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            str2 = "com.google.InstanceId.properties";
        } else {
            try {
                String encodeToString = Base64.encodeToString(str.getBytes("UTF-8"), 11);
                str2 = new StringBuilder(String.valueOf(encodeToString).length() + 33).append("com.google.InstanceId_").append(encodeToString).append(".properties").toString();
            } catch (UnsupportedEncodingException e) {
                throw new AssertionError(e);
            }
        }
        return new File(zzj(context), str2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0047, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0048, code lost:
        zzd(r0, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004b, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.gms.iid.zzo zzd(java.io.File r6) throws com.google.android.gms.iid.zzp, java.io.IOException {
        /*
            java.io.FileInputStream r0 = new java.io.FileInputStream
            r0.<init>(r6)
            java.io.FileInputStream r6 = io.sentry.instrumentation.file.SentryFileInputStream.Factory.create((java.io.FileInputStream) r0, (java.io.File) r6)
            java.util.Properties r0 = new java.util.Properties     // Catch:{ all -> 0x0045 }
            r0.<init>()     // Catch:{ all -> 0x0045 }
            r0.load(r6)     // Catch:{ all -> 0x0045 }
            java.lang.String r1 = "pub"
            java.lang.String r1 = r0.getProperty(r1)     // Catch:{ all -> 0x0045 }
            java.lang.String r2 = "pri"
            java.lang.String r2 = r0.getProperty(r2)     // Catch:{ all -> 0x0045 }
            r3 = 0
            if (r1 == 0) goto L_0x0041
            if (r2 != 0) goto L_0x0023
            goto L_0x0041
        L_0x0023:
            java.security.KeyPair r1 = zzg((java.lang.String) r1, (java.lang.String) r2)     // Catch:{ all -> 0x0045 }
            java.lang.String r2 = "cre"
            java.lang.String r0 = r0.getProperty(r2)     // Catch:{ NumberFormatException -> 0x003a }
            long r4 = java.lang.Long.parseLong(r0)     // Catch:{ NumberFormatException -> 0x003a }
            com.google.android.gms.iid.zzo r0 = new com.google.android.gms.iid.zzo     // Catch:{ all -> 0x0045 }
            r0.<init>(r1, r4)     // Catch:{ all -> 0x0045 }
            zzd((java.lang.Throwable) r3, (java.io.FileInputStream) r6)
            return r0
        L_0x003a:
            r0 = move-exception
            com.google.android.gms.iid.zzp r1 = new com.google.android.gms.iid.zzp     // Catch:{ all -> 0x0045 }
            r1.<init>(r0)     // Catch:{ all -> 0x0045 }
            throw r1     // Catch:{ all -> 0x0045 }
        L_0x0041:
            zzd((java.lang.Throwable) r3, (java.io.FileInputStream) r6)
            return r3
        L_0x0045:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0047 }
        L_0x0047:
            r1 = move-exception
            zzd((java.lang.Throwable) r0, (java.io.FileInputStream) r6)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.iid.zzn.zzd(java.io.File):com.google.android.gms.iid.zzo");
    }

    private static zzo zzd(SharedPreferences sharedPreferences, String str) throws zzp {
        String string = sharedPreferences.getString(zzak.zzh(str, "|P|"), (String) null);
        String string2 = sharedPreferences.getString(zzak.zzh(str, "|K|"), (String) null);
        if (string == null || string2 == null) {
            return null;
        }
        return new zzo(zzg(string, string2), zze(sharedPreferences, str));
    }

    private final void zze(Context context, String str, zzo zzo) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.google.android.gms.appid", 0);
        try {
            if (zzo.equals(zzd(sharedPreferences, str))) {
                return;
            }
        } catch (zzp unused) {
        }
        if (Log.isLoggable("InstanceID", 3)) {
            Log.d("InstanceID", "Writing key to shared preferences");
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(zzak.zzh(str, "|P|"), zzo.zzq());
        edit.putString(zzak.zzh(str, "|K|"), zzo.zzr());
        edit.putString(zzak.zzh(str, "cre"), String.valueOf(zzo.zzcc));
        edit.commit();
    }

    private static long zze(SharedPreferences sharedPreferences, String str) {
        String string = sharedPreferences.getString(zzak.zzh(str, "cre"), (String) null);
        if (string == null) {
            return 0;
        }
        try {
            return Long.parseLong(string);
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    private static /* synthetic */ void zzd(Throwable th, FileOutputStream fileOutputStream) {
        if (th != null) {
            try {
                fileOutputStream.close();
            } catch (Throwable th2) {
                zzq.zzd(th, th2);
            }
        } else {
            fileOutputStream.close();
        }
    }

    private static /* synthetic */ void zzd(Throwable th, FileInputStream fileInputStream) {
        if (th != null) {
            try {
                fileInputStream.close();
            } catch (Throwable th2) {
                zzq.zzd(th, th2);
            }
        } else {
            fileInputStream.close();
        }
    }
}

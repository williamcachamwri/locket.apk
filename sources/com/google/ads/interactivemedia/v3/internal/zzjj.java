package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzjj {
    private static final String zzd = "zzjj";
    protected final Context zza;
    protected boolean zzb;
    protected boolean zzc;
    private ExecutorService zze;
    private DexClassLoader zzf;
    private zzio zzg;
    private byte[] zzh;
    private volatile AdvertisingIdClient zzi = null;
    private volatile boolean zzj;
    private Future zzk;
    private final boolean zzl;
    /* access modifiers changed from: private */
    public volatile zzbp zzm;
    private Future zzn;
    private zzhz zzo;
    private final Map zzp;
    private boolean zzq;
    private zzjc zzr;

    private zzjj(Context context) {
        boolean z = false;
        this.zzj = false;
        this.zzk = null;
        this.zzm = null;
        this.zzn = null;
        this.zzb = false;
        this.zzc = false;
        this.zzq = false;
        Context applicationContext = context.getApplicationContext();
        this.zzl = applicationContext != null ? true : z;
        context = applicationContext != null ? applicationContext : context;
        this.zza = context;
        this.zzp = new HashMap();
        if (this.zzr == null) {
            this.zzr = new zzjc(context);
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(17:1|2|(1:4)|5|6|7|8|(1:10)(1:11)|12|(1:14)(1:15)|16|17|18|(2:20|(1:22)(2:23|24))|25|26|(3:27|28|(17:30|(2:32|33)|34|35|36|37|(2:39|(1:41)(2:42|43))|44|(3:46|(1:48)|49)|50|51|52|53|54|55|56|89)(3:78|79|80))) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x004f */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0058 A[Catch:{ zziz -> 0x016f }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0086 A[Catch:{ IllegalArgumentException -> 0x0161, zzin -> 0x0168 }] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x015b A[SYNTHETIC, Splitter:B:78:0x015b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.ads.interactivemedia.v3.internal.zzjj zzg(android.content.Context r8, java.lang.String r9, java.lang.String r10, boolean r11) {
        /*
            java.lang.String r9 = "%s/%s.dex"
            java.lang.String r10 = "1724504256809"
            com.google.ads.interactivemedia.v3.internal.zzjj r0 = new com.google.ads.interactivemedia.v3.internal.zzjj
            r0.<init>(r8)
            com.google.ads.interactivemedia.v3.internal.zzjf r8 = new com.google.ads.interactivemedia.v3.internal.zzjf     // Catch:{ zziz -> 0x016f }
            r8.<init>()     // Catch:{ zziz -> 0x016f }
            java.util.concurrent.ExecutorService r8 = java.util.concurrent.Executors.newCachedThreadPool(r8)     // Catch:{ zziz -> 0x016f }
            r0.zze = r8     // Catch:{ zziz -> 0x016f }
            r0.zzj = r11     // Catch:{ zziz -> 0x016f }
            if (r11 == 0) goto L_0x0025
            java.util.concurrent.ExecutorService r8 = r0.zze     // Catch:{ zziz -> 0x016f }
            com.google.ads.interactivemedia.v3.internal.zzjg r11 = new com.google.ads.interactivemedia.v3.internal.zzjg     // Catch:{ zziz -> 0x016f }
            r11.<init>(r0)     // Catch:{ zziz -> 0x016f }
            java.util.concurrent.Future r8 = r8.submit(r11)     // Catch:{ zziz -> 0x016f }
            r0.zzk = r8     // Catch:{ zziz -> 0x016f }
        L_0x0025:
            java.util.concurrent.ExecutorService r8 = r0.zze     // Catch:{ zziz -> 0x016f }
            com.google.ads.interactivemedia.v3.internal.zzji r11 = new com.google.ads.interactivemedia.v3.internal.zzji     // Catch:{ zziz -> 0x016f }
            r11.<init>(r0)     // Catch:{ zziz -> 0x016f }
            r8.execute(r11)     // Catch:{ zziz -> 0x016f }
            r8 = 1
            r11 = 0
            com.google.android.gms.common.GoogleApiAvailabilityLight r1 = com.google.android.gms.common.GoogleApiAvailabilityLight.getInstance()     // Catch:{ all -> 0x004f }
            android.content.Context r2 = r0.zza     // Catch:{ all -> 0x004f }
            int r2 = r1.getApkVersion(r2)     // Catch:{ all -> 0x004f }
            if (r2 <= 0) goto L_0x003f
            r2 = r8
            goto L_0x0040
        L_0x003f:
            r2 = r11
        L_0x0040:
            r0.zzb = r2     // Catch:{ all -> 0x004f }
            android.content.Context r2 = r0.zza     // Catch:{ all -> 0x004f }
            int r1 = r1.isGooglePlayServicesAvailable(r2)     // Catch:{ all -> 0x004f }
            if (r1 != 0) goto L_0x004c
            r1 = r8
            goto L_0x004d
        L_0x004c:
            r1 = r11
        L_0x004d:
            r0.zzc = r1     // Catch:{ all -> 0x004f }
        L_0x004f:
            r0.zzo(r11, r8)     // Catch:{ zziz -> 0x016f }
            boolean r1 = com.google.ads.interactivemedia.v3.internal.zzjm.zzc()     // Catch:{ zziz -> 0x016f }
            if (r1 == 0) goto L_0x0073
            com.google.ads.interactivemedia.v3.internal.zzma r1 = com.google.ads.interactivemedia.v3.internal.zzmj.zzv     // Catch:{ zziz -> 0x016f }
            com.google.ads.interactivemedia.v3.internal.zzmh r2 = com.google.ads.interactivemedia.v3.internal.zzls.zzc()     // Catch:{ zziz -> 0x016f }
            java.lang.Object r1 = r2.zza(r1)     // Catch:{ zziz -> 0x016f }
            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch:{ zziz -> 0x016f }
            boolean r1 = r1.booleanValue()     // Catch:{ zziz -> 0x016f }
            if (r1 != 0) goto L_0x006b
            goto L_0x0073
        L_0x006b:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException     // Catch:{ zziz -> 0x016f }
            java.lang.String r9 = "Task Context initialization must not be called from the UI thread."
            r8.<init>(r9)     // Catch:{ zziz -> 0x016f }
            throw r8     // Catch:{ zziz -> 0x016f }
        L_0x0073:
            com.google.ads.interactivemedia.v3.internal.zzio r1 = new com.google.ads.interactivemedia.v3.internal.zzio     // Catch:{ zziz -> 0x016f }
            r2 = 0
            r1.<init>(r2)     // Catch:{ zziz -> 0x016f }
            r0.zzg = r1     // Catch:{ zziz -> 0x016f }
            java.lang.String r3 = "vAadW7BThSFPyAUr+LckGPrtEsNR8zU6BVbFcyRFULk="
            byte[] r3 = com.google.ads.interactivemedia.v3.internal.zzgl.zzb(r3, r11)     // Catch:{ IllegalArgumentException -> 0x0161 }
            int r4 = r3.length     // Catch:{ IllegalArgumentException -> 0x0161 }
            r5 = 32
            if (r4 != r5) goto L_0x015b
            r4 = 4
            r5 = 16
            java.nio.ByteBuffer r3 = java.nio.ByteBuffer.wrap(r3, r4, r5)     // Catch:{ IllegalArgumentException -> 0x0161 }
            byte[] r4 = new byte[r5]     // Catch:{ IllegalArgumentException -> 0x0161 }
            r3.get(r4)     // Catch:{ IllegalArgumentException -> 0x0161 }
            r3 = r11
        L_0x0093:
            if (r3 >= r5) goto L_0x009f
            byte r6 = r4[r3]     // Catch:{ IllegalArgumentException -> 0x0161 }
            r6 = r6 ^ 68
            byte r6 = (byte) r6     // Catch:{ IllegalArgumentException -> 0x0161 }
            r4[r3] = r6     // Catch:{ IllegalArgumentException -> 0x0161 }
            int r3 = r3 + 1
            goto L_0x0093
        L_0x009f:
            r0.zzh = r4     // Catch:{ zzin -> 0x0168 }
            android.content.Context r1 = r0.zza     // Catch:{ FileNotFoundException -> 0x0154, IOException -> 0x014d, zzin -> 0x0146, NullPointerException -> 0x013f }
            java.io.File r1 = r1.getCacheDir()     // Catch:{ FileNotFoundException -> 0x0154, IOException -> 0x014d, zzin -> 0x0146, NullPointerException -> 0x013f }
            if (r1 != 0) goto L_0x00ba
            android.content.Context r1 = r0.zza     // Catch:{ FileNotFoundException -> 0x0154, IOException -> 0x014d, zzin -> 0x0146, NullPointerException -> 0x013f }
            java.lang.String r3 = "dex"
            java.io.File r1 = r1.getDir(r3, r11)     // Catch:{ FileNotFoundException -> 0x0154, IOException -> 0x014d, zzin -> 0x0146, NullPointerException -> 0x013f }
            if (r1 == 0) goto L_0x00b4
            goto L_0x00ba
        L_0x00b4:
            com.google.ads.interactivemedia.v3.internal.zziz r8 = new com.google.ads.interactivemedia.v3.internal.zziz     // Catch:{ FileNotFoundException -> 0x0154, IOException -> 0x014d, zzin -> 0x0146, NullPointerException -> 0x013f }
            r8.<init>()     // Catch:{ FileNotFoundException -> 0x0154, IOException -> 0x014d, zzin -> 0x0146, NullPointerException -> 0x013f }
            throw r8     // Catch:{ FileNotFoundException -> 0x0154, IOException -> 0x014d, zzin -> 0x0146, NullPointerException -> 0x013f }
        L_0x00ba:
            java.lang.String r3 = "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"
            java.io.File r4 = new java.io.File     // Catch:{ FileNotFoundException -> 0x0154, IOException -> 0x014d, zzin -> 0x0146, NullPointerException -> 0x013f }
            java.lang.String r5 = "%s/%s.jar"
            java.lang.Object[] r6 = new java.lang.Object[]{r1, r10}     // Catch:{ FileNotFoundException -> 0x0154, IOException -> 0x014d, zzin -> 0x0146, NullPointerException -> 0x013f }
            java.lang.String r5 = java.lang.String.format(r5, r6)     // Catch:{ FileNotFoundException -> 0x0154, IOException -> 0x014d, zzin -> 0x0146, NullPointerException -> 0x013f }
            r4.<init>(r5)     // Catch:{ FileNotFoundException -> 0x0154, IOException -> 0x014d, zzin -> 0x0146, NullPointerException -> 0x013f }
            boolean r5 = r4.exists()     // Catch:{ FileNotFoundException -> 0x0154, IOException -> 0x014d, zzin -> 0x0146, NullPointerException -> 0x013f }
            if (r5 != 0) goto L_0x00f1
            com.google.ads.interactivemedia.v3.internal.zzio r5 = r0.zzg     // Catch:{ FileNotFoundException -> 0x0154, IOException -> 0x014d, zzin -> 0x0146, NullPointerException -> 0x013f }
            byte[] r6 = r0.zzh     // Catch:{ FileNotFoundException -> 0x0154, IOException -> 0x014d, zzin -> 0x0146, NullPointerException -> 0x013f }
            byte[] r3 = r5.zzb(r6, r3)     // Catch:{ FileNotFoundException -> 0x0154, IOException -> 0x014d, zzin -> 0x0146, NullPointerException -> 0x013f }
            r4.createNewFile()     // Catch:{ FileNotFoundException -> 0x0154, IOException -> 0x014d, zzin -> 0x0146, NullPointerException -> 0x013f }
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x0154, IOException -> 0x014d, zzin -> 0x0146, NullPointerException -> 0x013f }
            r5.<init>(r4)     // Catch:{ FileNotFoundException -> 0x0154, IOException -> 0x014d, zzin -> 0x0146, NullPointerException -> 0x013f }
            int r6 = android.os.Build.VERSION.SDK_INT     // Catch:{ FileNotFoundException -> 0x0154, IOException -> 0x014d, zzin -> 0x0146, NullPointerException -> 0x013f }
            r7 = 33
            if (r6 < r7) goto L_0x00ea
            r4.setReadOnly()     // Catch:{ FileNotFoundException -> 0x0154, IOException -> 0x014d, zzin -> 0x0146, NullPointerException -> 0x013f }
        L_0x00ea:
            int r6 = r3.length     // Catch:{ FileNotFoundException -> 0x0154, IOException -> 0x014d, zzin -> 0x0146, NullPointerException -> 0x013f }
            r5.write(r3, r11, r6)     // Catch:{ FileNotFoundException -> 0x0154, IOException -> 0x014d, zzin -> 0x0146, NullPointerException -> 0x013f }
            r5.close()     // Catch:{ FileNotFoundException -> 0x0154, IOException -> 0x014d, zzin -> 0x0146, NullPointerException -> 0x013f }
        L_0x00f1:
            r0.zzx(r1, r10)     // Catch:{ FileNotFoundException -> 0x0154, IOException -> 0x014d, zzin -> 0x0146, NullPointerException -> 0x013f }
            dalvik.system.DexClassLoader r11 = new dalvik.system.DexClassLoader     // Catch:{ SecurityException -> 0x0126 }
            java.lang.String r3 = r4.getAbsolutePath()     // Catch:{ SecurityException -> 0x0126 }
            java.lang.String r5 = r1.getAbsolutePath()     // Catch:{ SecurityException -> 0x0126 }
            android.content.Context r6 = r0.zza     // Catch:{ SecurityException -> 0x0126 }
            java.lang.ClassLoader r6 = r6.getClassLoader()     // Catch:{ SecurityException -> 0x0126 }
            r11.<init>(r3, r5, r2, r6)     // Catch:{ SecurityException -> 0x0126 }
            r0.zzf = r11     // Catch:{ SecurityException -> 0x0126 }
            zzy(r4)     // Catch:{ FileNotFoundException -> 0x0154, IOException -> 0x014d, zzin -> 0x0146, NullPointerException -> 0x013f }
            r0.zzw(r1, r10)     // Catch:{ FileNotFoundException -> 0x0154, IOException -> 0x014d, zzin -> 0x0146, NullPointerException -> 0x013f }
            java.lang.Object[] r10 = new java.lang.Object[]{r1, r10}     // Catch:{ FileNotFoundException -> 0x0154, IOException -> 0x014d, zzin -> 0x0146, NullPointerException -> 0x013f }
            java.lang.String r9 = java.lang.String.format(r9, r10)     // Catch:{ FileNotFoundException -> 0x0154, IOException -> 0x014d, zzin -> 0x0146, NullPointerException -> 0x013f }
            zzz(r9)     // Catch:{ FileNotFoundException -> 0x0154, IOException -> 0x014d, zzin -> 0x0146, NullPointerException -> 0x013f }
            com.google.ads.interactivemedia.v3.internal.zzhz r9 = new com.google.ads.interactivemedia.v3.internal.zzhz     // Catch:{ zziz -> 0x016f }
            r9.<init>(r0)     // Catch:{ zziz -> 0x016f }
            r0.zzo = r9     // Catch:{ zziz -> 0x016f }
            r0.zzq = r8     // Catch:{ zziz -> 0x016f }
            goto L_0x016f
        L_0x0124:
            r8 = move-exception
            goto L_0x012d
        L_0x0126:
            r8 = move-exception
            com.google.ads.interactivemedia.v3.internal.zziz r11 = new com.google.ads.interactivemedia.v3.internal.zziz     // Catch:{ all -> 0x0124 }
            r11.<init>(r8)     // Catch:{ all -> 0x0124 }
            throw r11     // Catch:{ all -> 0x0124 }
        L_0x012d:
            zzy(r4)     // Catch:{ FileNotFoundException -> 0x0154, IOException -> 0x014d, zzin -> 0x0146, NullPointerException -> 0x013f }
            r0.zzw(r1, r10)     // Catch:{ FileNotFoundException -> 0x0154, IOException -> 0x014d, zzin -> 0x0146, NullPointerException -> 0x013f }
            java.lang.Object[] r10 = new java.lang.Object[]{r1, r10}     // Catch:{ FileNotFoundException -> 0x0154, IOException -> 0x014d, zzin -> 0x0146, NullPointerException -> 0x013f }
            java.lang.String r9 = java.lang.String.format(r9, r10)     // Catch:{ FileNotFoundException -> 0x0154, IOException -> 0x014d, zzin -> 0x0146, NullPointerException -> 0x013f }
            zzz(r9)     // Catch:{ FileNotFoundException -> 0x0154, IOException -> 0x014d, zzin -> 0x0146, NullPointerException -> 0x013f }
            throw r8     // Catch:{ FileNotFoundException -> 0x0154, IOException -> 0x014d, zzin -> 0x0146, NullPointerException -> 0x013f }
        L_0x013f:
            r8 = move-exception
            com.google.ads.interactivemedia.v3.internal.zziz r9 = new com.google.ads.interactivemedia.v3.internal.zziz     // Catch:{ zziz -> 0x016f }
            r9.<init>(r8)     // Catch:{ zziz -> 0x016f }
            throw r9     // Catch:{ zziz -> 0x016f }
        L_0x0146:
            r8 = move-exception
            com.google.ads.interactivemedia.v3.internal.zziz r9 = new com.google.ads.interactivemedia.v3.internal.zziz     // Catch:{ zziz -> 0x016f }
            r9.<init>(r8)     // Catch:{ zziz -> 0x016f }
            throw r9     // Catch:{ zziz -> 0x016f }
        L_0x014d:
            r8 = move-exception
            com.google.ads.interactivemedia.v3.internal.zziz r9 = new com.google.ads.interactivemedia.v3.internal.zziz     // Catch:{ zziz -> 0x016f }
            r9.<init>(r8)     // Catch:{ zziz -> 0x016f }
            throw r9     // Catch:{ zziz -> 0x016f }
        L_0x0154:
            r8 = move-exception
            com.google.ads.interactivemedia.v3.internal.zziz r9 = new com.google.ads.interactivemedia.v3.internal.zziz     // Catch:{ zziz -> 0x016f }
            r9.<init>(r8)     // Catch:{ zziz -> 0x016f }
            throw r9     // Catch:{ zziz -> 0x016f }
        L_0x015b:
            com.google.ads.interactivemedia.v3.internal.zzin r8 = new com.google.ads.interactivemedia.v3.internal.zzin     // Catch:{ IllegalArgumentException -> 0x0161 }
            r8.<init>(r1)     // Catch:{ IllegalArgumentException -> 0x0161 }
            throw r8     // Catch:{ IllegalArgumentException -> 0x0161 }
        L_0x0161:
            r8 = move-exception
            com.google.ads.interactivemedia.v3.internal.zzin r9 = new com.google.ads.interactivemedia.v3.internal.zzin     // Catch:{ zzin -> 0x0168 }
            r9.<init>(r1, r8)     // Catch:{ zzin -> 0x0168 }
            throw r9     // Catch:{ zzin -> 0x0168 }
        L_0x0168:
            r8 = move-exception
            com.google.ads.interactivemedia.v3.internal.zziz r9 = new com.google.ads.interactivemedia.v3.internal.zziz     // Catch:{ zziz -> 0x016f }
            r9.<init>(r8)     // Catch:{ zziz -> 0x016f }
            throw r9     // Catch:{ zziz -> 0x016f }
        L_0x016f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.zzjj.zzg(android.content.Context, java.lang.String, java.lang.String, boolean):com.google.ads.interactivemedia.v3.internal.zzjj");
    }

    /* access modifiers changed from: private */
    public final void zzv() {
        try {
            if (this.zzi == null && this.zzl) {
                AdvertisingIdClient advertisingIdClient = new AdvertisingIdClient(this.zza);
                advertisingIdClient.start();
                this.zzi = advertisingIdClient;
            }
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException unused) {
            this.zzi = null;
        }
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x00bc */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d2 A[SYNTHETIC, Splitter:B:39:0x00d2] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00d7 A[SYNTHETIC, Splitter:B:43:0x00d7] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00e1 A[SYNTHETIC, Splitter:B:51:0x00e1] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00e6 A[SYNTHETIC, Splitter:B:55:0x00e6] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzw(java.io.File r9, java.lang.String r10) {
        /*
            r8 = this;
            java.lang.String r10 = "test"
            java.io.File r0 = new java.io.File
            java.lang.String r1 = "1724504256809"
            java.lang.Object[] r2 = new java.lang.Object[]{r9, r1}
            java.lang.String r3 = "%s/%s.tmp"
            java.lang.String r2 = java.lang.String.format(r3, r2)
            r0.<init>(r2)
            boolean r2 = r0.exists()
            if (r2 == 0) goto L_0x001b
            goto L_0x00ec
        L_0x001b:
            java.io.File r2 = new java.io.File
            java.lang.Object[] r9 = new java.lang.Object[]{r9, r1}
            java.lang.String r3 = "%s/%s.dex"
            java.lang.String r9 = java.lang.String.format(r3, r9)
            r2.<init>(r9)
            boolean r9 = r2.exists()
            if (r9 == 0) goto L_0x00ec
            long r3 = r2.length()
            r5 = 0
            int r9 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r9 <= 0) goto L_0x00ec
            int r9 = (int) r3
            byte[] r9 = new byte[r9]
            r3 = 0
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00de, all -> 0x00cd }
            r4.<init>(r2)     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00de, all -> 0x00cd }
            int r5 = r4.read(r9)     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00c5 }
            if (r5 > 0) goto L_0x0050
            r4.close()     // Catch:{ IOException -> 0x004c }
        L_0x004c:
            zzy(r2)
            return
        L_0x0050:
            java.io.PrintStream r5 = java.lang.System.out     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00c5 }
            r5.print(r10)     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00c5 }
            java.io.PrintStream r5 = java.lang.System.out     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00c5 }
            r5.print(r10)     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00c5 }
            java.io.PrintStream r5 = java.lang.System.out     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00c5 }
            r5.print(r10)     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00c5 }
            com.google.ads.interactivemedia.v3.internal.zzbu r10 = com.google.ads.interactivemedia.v3.internal.zzbv.zza()     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00c5 }
            java.lang.String r5 = android.os.Build.VERSION.SDK     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00c5 }
            byte[] r5 = r5.getBytes()     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00c5 }
            com.google.ads.interactivemedia.v3.internal.zzacw r6 = com.google.ads.interactivemedia.v3.internal.zzacw.zzb     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00c5 }
            int r6 = r5.length     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00c5 }
            r7 = 0
            com.google.ads.interactivemedia.v3.internal.zzacw r5 = com.google.ads.interactivemedia.v3.internal.zzacw.zzp(r5, r7, r6)     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00c5 }
            r10.zzc(r5)     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00c5 }
            byte[] r1 = r1.getBytes()     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00c5 }
            int r5 = r1.length     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00c5 }
            com.google.ads.interactivemedia.v3.internal.zzacw r1 = com.google.ads.interactivemedia.v3.internal.zzacw.zzp(r1, r7, r5)     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00c5 }
            r10.zzd(r1)     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00c5 }
            com.google.ads.interactivemedia.v3.internal.zzio r1 = r8.zzg     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00c5 }
            byte[] r5 = r8.zzh     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00c5 }
            java.lang.String r9 = r1.zza(r5, r9)     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00c5 }
            byte[] r9 = r9.getBytes()     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00c5 }
            int r1 = r9.length     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00c5 }
            com.google.ads.interactivemedia.v3.internal.zzacw r1 = com.google.ads.interactivemedia.v3.internal.zzacw.zzp(r9, r7, r1)     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00c5 }
            r10.zza(r1)     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00c5 }
            byte[] r9 = com.google.ads.interactivemedia.v3.internal.zzgt.zze(r9)     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00c5 }
            int r1 = r9.length     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00c5 }
            com.google.ads.interactivemedia.v3.internal.zzacw r9 = com.google.ads.interactivemedia.v3.internal.zzacw.zzp(r9, r7, r1)     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00c5 }
            r10.zzb(r9)     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00c5 }
            r0.createNewFile()     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00c5 }
            java.io.FileOutputStream r9 = new java.io.FileOutputStream     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00c5 }
            r9.<init>(r0)     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00ca, all -> 0x00c5 }
            com.google.ads.interactivemedia.v3.internal.zzady r10 = r10.zzal()     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00cb, all -> 0x00c3 }
            com.google.ads.interactivemedia.v3.internal.zzbv r10 = (com.google.ads.interactivemedia.v3.internal.zzbv) r10     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00cb, all -> 0x00c3 }
            byte[] r10 = r10.zzav()     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00cb, all -> 0x00c3 }
            int r0 = r10.length     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00cb, all -> 0x00c3 }
            r9.write(r10, r7, r0)     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00cb, all -> 0x00c3 }
            r9.close()     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00cb, all -> 0x00c3 }
            r4.close()     // Catch:{ IOException -> 0x00bc }
        L_0x00bc:
            r9.close()     // Catch:{ IOException -> 0x00bf }
        L_0x00bf:
            zzy(r2)
            return
        L_0x00c3:
            r10 = move-exception
            goto L_0x00c8
        L_0x00c5:
            r9 = move-exception
            r10 = r9
            r9 = r3
        L_0x00c8:
            r3 = r4
            goto L_0x00d0
        L_0x00ca:
            r9 = r3
        L_0x00cb:
            r3 = r4
            goto L_0x00df
        L_0x00cd:
            r9 = move-exception
            r10 = r9
            r9 = r3
        L_0x00d0:
            if (r3 == 0) goto L_0x00d5
            r3.close()     // Catch:{ IOException -> 0x00d5 }
        L_0x00d5:
            if (r9 == 0) goto L_0x00da
            r9.close()     // Catch:{ IOException -> 0x00da }
        L_0x00da:
            zzy(r2)
            throw r10
        L_0x00de:
            r9 = r3
        L_0x00df:
            if (r3 == 0) goto L_0x00e4
            r3.close()     // Catch:{ IOException -> 0x00e4 }
        L_0x00e4:
            if (r9 == 0) goto L_0x00e9
            r9.close()     // Catch:{ IOException -> 0x00e9 }
        L_0x00e9:
            zzy(r2)
        L_0x00ec:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.zzjj.zzw(java.io.File, java.lang.String):void");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:31|32|33|34|35|36|79) */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00da, code lost:
        r9 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00db, code lost:
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00de, code lost:
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:?, code lost:
        r8.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:?, code lost:
        r8.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:?, code lost:
        return true;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x00c7 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:46:0x00d6 */
    /* JADX WARNING: Removed duplicated region for block: B:54:? A[ExcHandler: zzin | IOException | NoSuchAlgorithmException (unused java.lang.Throwable), SYNTHETIC, Splitter:B:12:0x0045] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00e5 A[SYNTHETIC, Splitter:B:60:0x00e5] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00ea A[SYNTHETIC, Splitter:B:64:0x00ea] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00f1 A[SYNTHETIC, Splitter:B:71:0x00f1] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x00f6 A[SYNTHETIC, Splitter:B:75:0x00f6] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zzx(java.io.File r8, java.lang.String r9) {
        /*
            r7 = this;
            java.io.File r9 = new java.io.File
            java.lang.String r0 = "1724504256809"
            java.lang.Object[] r1 = new java.lang.Object[]{r8, r0}
            java.lang.String r2 = "%s/%s.tmp"
            java.lang.String r1 = java.lang.String.format(r2, r1)
            r9.<init>(r1)
            boolean r1 = r9.exists()
            r2 = 0
            if (r1 != 0) goto L_0x0019
            return r2
        L_0x0019:
            java.io.File r1 = new java.io.File
            java.lang.Object[] r8 = new java.lang.Object[]{r8, r0}
            java.lang.String r3 = "%s/%s.dex"
            java.lang.String r8 = java.lang.String.format(r3, r8)
            r1.<init>(r8)
            boolean r8 = r1.exists()
            if (r8 != 0) goto L_0x00f9
            r8 = 0
            long r3 = r9.length()     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00ee, all -> 0x00e1 }
            r5 = 0
            int r5 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r5 > 0) goto L_0x003d
            zzy(r9)     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00ee, all -> 0x00e1 }
            return r2
        L_0x003d:
            int r3 = (int) r3     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00ee, all -> 0x00e1 }
            byte[] r3 = new byte[r3]     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00ee, all -> 0x00e1 }
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00ee, all -> 0x00e1 }
            r4.<init>(r9)     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00ee, all -> 0x00e1 }
            int r5 = r4.read(r3)     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00de, all -> 0x00da }
            if (r5 > 0) goto L_0x0059
            java.lang.String r0 = zzd     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00de, all -> 0x00da }
            java.lang.String r1 = "Cannot read the cache data."
            android.util.Log.d(r0, r1)     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00de, all -> 0x00da }
            zzy(r9)     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00de, all -> 0x00da }
            r4.close()     // Catch:{ IOException -> 0x0058 }
        L_0x0058:
            return r2
        L_0x0059:
            com.google.ads.interactivemedia.v3.internal.zzadk r5 = com.google.ads.interactivemedia.v3.internal.zzadk.zza()     // Catch:{ NullPointerException -> 0x00d6, zzin | IOException | NoSuchAlgorithmException -> 0x00de, zzin | IOException | NoSuchAlgorithmException -> 0x00de }
            com.google.ads.interactivemedia.v3.internal.zzbv r3 = com.google.ads.interactivemedia.v3.internal.zzbv.zzc(r3, r5)     // Catch:{ NullPointerException -> 0x00d6, zzin | IOException | NoSuchAlgorithmException -> 0x00de, zzin | IOException | NoSuchAlgorithmException -> 0x00de }
            java.lang.String r5 = new java.lang.String     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00de, all -> 0x00da }
            com.google.ads.interactivemedia.v3.internal.zzacw r6 = r3.zzg()     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00de, all -> 0x00da }
            byte[] r6 = r6.zzt()     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00de, all -> 0x00da }
            r5.<init>(r6)     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00de, all -> 0x00da }
            boolean r0 = r0.equals(r5)     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00de, all -> 0x00da }
            if (r0 == 0) goto L_0x00cf
            com.google.ads.interactivemedia.v3.internal.zzacw r0 = r3.zze()     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00de, all -> 0x00da }
            byte[] r0 = r0.zzt()     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00de, all -> 0x00da }
            com.google.ads.interactivemedia.v3.internal.zzacw r5 = r3.zzd()     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00de, all -> 0x00da }
            byte[] r5 = r5.zzt()     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00de, all -> 0x00da }
            byte[] r5 = com.google.ads.interactivemedia.v3.internal.zzgt.zze(r5)     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00de, all -> 0x00da }
            boolean r0 = java.util.Arrays.equals(r0, r5)     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00de, all -> 0x00da }
            if (r0 == 0) goto L_0x00cf
            com.google.ads.interactivemedia.v3.internal.zzacw r0 = r3.zzf()     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00de, all -> 0x00da }
            byte[] r0 = r0.zzt()     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00de, all -> 0x00da }
            java.lang.String r5 = android.os.Build.VERSION.SDK     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00de, all -> 0x00da }
            byte[] r5 = r5.getBytes()     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00de, all -> 0x00da }
            boolean r0 = java.util.Arrays.equals(r0, r5)     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00de, all -> 0x00da }
            if (r0 != 0) goto L_0x00a3
            goto L_0x00cf
        L_0x00a3:
            com.google.ads.interactivemedia.v3.internal.zzio r9 = r7.zzg     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00de, all -> 0x00da }
            byte[] r0 = r7.zzh     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00de, all -> 0x00da }
            java.lang.String r5 = new java.lang.String     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00de, all -> 0x00da }
            com.google.ads.interactivemedia.v3.internal.zzacw r3 = r3.zzd()     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00de, all -> 0x00da }
            byte[] r3 = r3.zzt()     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00de, all -> 0x00da }
            r5.<init>(r3)     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00de, all -> 0x00da }
            byte[] r9 = r9.zzb(r0, r5)     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00de, all -> 0x00da }
            r1.createNewFile()     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00de, all -> 0x00da }
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00de, all -> 0x00da }
            r0.<init>(r1)     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00de, all -> 0x00da }
            int r8 = r9.length     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00df, all -> 0x00cc }
            r0.write(r9, r2, r8)     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00df, all -> 0x00cc }
            r4.close()     // Catch:{ IOException -> 0x00c7 }
        L_0x00c7:
            r0.close()     // Catch:{ IOException -> 0x00ca }
        L_0x00ca:
            r8 = 1
            return r8
        L_0x00cc:
            r8 = move-exception
            r9 = r8
            goto L_0x00dc
        L_0x00cf:
            zzy(r9)     // Catch:{ zzin | IOException | NoSuchAlgorithmException -> 0x00de, all -> 0x00da }
            r4.close()     // Catch:{ IOException -> 0x00d5 }
        L_0x00d5:
            return r2
        L_0x00d6:
            r4.close()     // Catch:{ IOException -> 0x00d9 }
        L_0x00d9:
            return r2
        L_0x00da:
            r9 = move-exception
            r0 = r8
        L_0x00dc:
            r8 = r4
            goto L_0x00e3
        L_0x00de:
            r0 = r8
        L_0x00df:
            r8 = r4
            goto L_0x00ef
        L_0x00e1:
            r9 = move-exception
            r0 = r8
        L_0x00e3:
            if (r8 == 0) goto L_0x00e8
            r8.close()     // Catch:{ IOException -> 0x00e8 }
        L_0x00e8:
            if (r0 == 0) goto L_0x00ed
            r0.close()     // Catch:{ IOException -> 0x00ed }
        L_0x00ed:
            throw r9
        L_0x00ee:
            r0 = r8
        L_0x00ef:
            if (r8 == 0) goto L_0x00f4
            r8.close()     // Catch:{ IOException -> 0x00f4 }
        L_0x00f4:
            if (r0 == 0) goto L_0x00f9
            r0.close()     // Catch:{ IOException -> 0x00f9 }
        L_0x00f9:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.zzjj.zzx(java.io.File, java.lang.String):boolean");
    }

    private static final void zzy(File file) {
        if (!file.exists()) {
            Log.d(zzd, String.format("File %s not found. No need for deletion", new Object[]{file.getAbsolutePath()}));
        } else {
            file.delete();
        }
    }

    private static final void zzz(String str) {
        zzy(new File(str));
    }

    public final int zza() {
        if (this.zzo != null) {
            return zzhz.zzd();
        }
        return Integer.MIN_VALUE;
    }

    public final Context zzb() {
        return this.zza;
    }

    public final zzbp zzc() {
        return this.zzm;
    }

    public final zzhz zzd() {
        return this.zzo;
    }

    public final zzio zze() {
        return this.zzg;
    }

    /* access modifiers changed from: package-private */
    public final zzjc zzf() {
        return this.zzr;
    }

    public final AdvertisingIdClient zzh() {
        Future future;
        if (!this.zzj) {
            return null;
        }
        if (this.zzi == null && (future = this.zzk) != null) {
            try {
                future.get(2000, TimeUnit.MILLISECONDS);
                this.zzk = null;
            } catch (InterruptedException | ExecutionException unused) {
            } catch (TimeoutException unused2) {
                this.zzk.cancel(true);
            }
        }
        return this.zzi;
    }

    public final DexClassLoader zzi() {
        return this.zzf;
    }

    public final Method zzj(String str, String str2) {
        zzkw zzkw = (zzkw) this.zzp.get(new Pair(str, str2));
        if (zzkw == null) {
            return null;
        }
        return zzkw.zza();
    }

    public final ExecutorService zzk() {
        return this.zze;
    }

    public final Future zzl() {
        return this.zzn;
    }

    /* access modifiers changed from: package-private */
    public final void zzo(int i, boolean z) {
        if (this.zzc) {
            Future<?> submit = this.zze.submit(new zzjh(this, i, true));
            if (i == 0) {
                this.zzn = submit;
            }
        }
    }

    public final boolean zzp() {
        return this.zzc;
    }

    public final boolean zzq() {
        return this.zzb;
    }

    public final boolean zzr() {
        return this.zzq;
    }

    public final boolean zzs() {
        return this.zzr.zza();
    }

    public final boolean zzt(String str, String str2, Class... clsArr) {
        Pair pair = new Pair(str, str2);
        if (this.zzp.containsKey(pair)) {
            return false;
        }
        this.zzp.put(pair, new zzkw(this, str, str2, clsArr));
        return true;
    }

    public final byte[] zzu() {
        return this.zzh;
    }
}

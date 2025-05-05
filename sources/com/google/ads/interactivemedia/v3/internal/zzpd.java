package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.common.util.Hex;
import java.io.File;
import java.util.HashSet;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzpd {
    private static final Object zza = new Object();
    private final Context zzb;
    private final SharedPreferences zzc;
    private final String zzd;
    private final zzom zze;
    private boolean zzf = false;

    public zzpd(Context context, int i, zzom zzom, boolean z) {
        this.zzb = context;
        this.zzd = Integer.toString(i - 1);
        this.zzc = context.getSharedPreferences("pcvmspf", 0);
        this.zze = zzom;
        this.zzf = z;
    }

    private final File zze(String str) {
        return new File(new File(this.zzb.getDir("pccache", 0), this.zzd), str);
    }

    private static String zzf(zzlc zzlc) {
        zzle zzd2 = zzlf.zzd();
        zzd2.zze(zzlc.zzc().zzj());
        zzd2.zza(zzlc.zzc().zzi());
        zzd2.zzb(zzlc.zzc().zza());
        zzd2.zzd(zzlc.zzc().zzc());
        zzd2.zzc(zzlc.zzc().zzb());
        return Hex.bytesToStringLowercase(((zzlf) zzd2.zzal()).zzav());
    }

    private final String zzg() {
        return "FBAMTD".concat(String.valueOf(this.zzd));
    }

    private final String zzh() {
        return "LATMTD".concat(String.valueOf(this.zzd));
    }

    private final void zzi(int i, long j) {
        this.zze.zza(i, j);
    }

    private final void zzj(int i, long j, String str) {
        this.zze.zzb(i, j, str);
    }

    private final zzlf zzk(int i) {
        String str;
        zzadk zzadk;
        if (i == 1) {
            str = this.zzc.getString(zzh(), (String) null);
        } else {
            str = this.zzc.getString(zzg(), (String) null);
        }
        if (str == null) {
            return null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        try {
            byte[] stringToBytes = Hex.stringToBytes(str);
            zzacw zzacw = zzacw.zzb;
            zzacw zzp = zzacw.zzp(stringToBytes, 0, stringToBytes.length);
            if (this.zzf) {
                zzadk = zzadk.zza();
            } else {
                zzadk = zzadk.zzb();
            }
            return zzlf.zzh(zzp, zzadk);
        } catch (zzaeg unused) {
            return null;
        } catch (NullPointerException unused2) {
            zzi(2029, currentTimeMillis);
            return null;
        } catch (RuntimeException unused3) {
            zzi(2032, currentTimeMillis);
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0053, code lost:
        return r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(com.google.ads.interactivemedia.v3.internal.zzlc r7) {
        /*
            r6 = this;
            long r0 = java.lang.System.currentTimeMillis()
            java.lang.Object r2 = zza
            monitor-enter(r2)
            com.google.ads.interactivemedia.v3.internal.zzlf r3 = r7.zzc()     // Catch:{ all -> 0x0054 }
            java.lang.String r3 = r3.zzj()     // Catch:{ all -> 0x0054 }
            java.io.File r3 = r6.zze(r3)     // Catch:{ all -> 0x0054 }
            java.io.File r4 = new java.io.File     // Catch:{ all -> 0x0054 }
            java.lang.String r5 = "pcbc"
            r4.<init>(r3, r5)     // Catch:{ all -> 0x0054 }
            com.google.ads.interactivemedia.v3.internal.zzacw r3 = r7.zzd()     // Catch:{ all -> 0x0054 }
            byte[] r3 = r3.zzt()     // Catch:{ all -> 0x0054 }
            boolean r3 = com.google.ads.interactivemedia.v3.internal.zzox.zze(r4, r3)     // Catch:{ all -> 0x0054 }
            if (r3 != 0) goto L_0x0030
            r7 = 4020(0xfb4, float:5.633E-42)
            r6.zzi(r7, r0)     // Catch:{ all -> 0x0054 }
            monitor-exit(r2)     // Catch:{ all -> 0x0054 }
            r7 = 0
            return r7
        L_0x0030:
            java.lang.String r7 = zzf(r7)     // Catch:{ all -> 0x0054 }
            android.content.SharedPreferences r3 = r6.zzc     // Catch:{ all -> 0x0054 }
            android.content.SharedPreferences$Editor r3 = r3.edit()     // Catch:{ all -> 0x0054 }
            java.lang.String r4 = r6.zzh()     // Catch:{ all -> 0x0054 }
            r3.putString(r4, r7)     // Catch:{ all -> 0x0054 }
            boolean r7 = r3.commit()     // Catch:{ all -> 0x0054 }
            if (r7 == 0) goto L_0x004d
            r3 = 5015(0x1397, float:7.028E-42)
            r6.zzi(r3, r0)     // Catch:{ all -> 0x0054 }
            goto L_0x0052
        L_0x004d:
            r3 = 4021(0xfb5, float:5.635E-42)
            r6.zzi(r3, r0)     // Catch:{ all -> 0x0054 }
        L_0x0052:
            monitor-exit(r2)     // Catch:{ all -> 0x0054 }
            return r7
        L_0x0054:
            r7 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0054 }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.zzpd.zza(com.google.ads.interactivemedia.v3.internal.zzlc):boolean");
    }

    public final boolean zzb(zzlc zzlc, zzpc zzpc) {
        zzpc zzpc2 = zzpc;
        long currentTimeMillis = System.currentTimeMillis();
        synchronized (zza) {
            zzlf zzk = zzk(1);
            String zzj = zzlc.zzc().zzj();
            if (zzk == null || !zzk.zzj().equals(zzj)) {
                long currentTimeMillis2 = System.currentTimeMillis();
                File zze2 = zze(zzj);
                if (zze2.exists()) {
                    String str = "1";
                    if (true != zze2.isDirectory()) {
                        str = "0";
                    }
                    String str2 = "1";
                    if (true != zze2.isFile()) {
                        str2 = "0";
                    }
                    zzj(4023, currentTimeMillis2, "d:" + str + ",f:" + str2);
                    zzi(4015, currentTimeMillis2);
                } else if (!zze2.mkdirs()) {
                    String str3 = "1";
                    if (true != zze2.canWrite()) {
                        str3 = "0";
                    }
                    zzj(4024, currentTimeMillis2, "cw:".concat(str3));
                    zzi(4015, currentTimeMillis2);
                    return false;
                }
                File zze3 = zze(zzj);
                File file = new File(zze3, "pcam.jar");
                File file2 = new File(zze3, "pcbc");
                if (!zzox.zze(file, zzlc.zze().zzt())) {
                    zzi(4016, currentTimeMillis);
                    return false;
                } else if (!zzox.zze(file2, zzlc.zzd().zzt())) {
                    zzi(4017, currentTimeMillis);
                    return false;
                } else if (zzpc2 == null || zzpc2.zza(file)) {
                    String zzf2 = zzf(zzlc);
                    long currentTimeMillis3 = System.currentTimeMillis();
                    String string = this.zzc.getString(zzh(), (String) null);
                    SharedPreferences.Editor edit = this.zzc.edit();
                    edit.putString(zzh(), zzf2);
                    if (string != null) {
                        edit.putString(zzg(), string);
                    }
                    if (!edit.commit()) {
                        zzi(4019, currentTimeMillis3);
                        return false;
                    }
                    HashSet hashSet = new HashSet();
                    zzlf zzk2 = zzk(1);
                    if (zzk2 != null) {
                        hashSet.add(zzk2.zzj());
                    }
                    zzlf zzk3 = zzk(2);
                    if (zzk3 != null) {
                        hashSet.add(zzk3.zzj());
                    }
                    for (File file3 : new File(this.zzb.getDir("pccache", 0), this.zzd).listFiles()) {
                        if (!hashSet.contains(file3.getName())) {
                            zzox.zzd(file3);
                        }
                    }
                    zzi(5014, currentTimeMillis);
                    return true;
                } else {
                    zzi(4018, currentTimeMillis);
                    zzox.zzd(zze3);
                    return false;
                }
            } else {
                zzi(4014, currentTimeMillis);
                return false;
            }
        }
    }

    public final zzov zzc(int i) {
        long currentTimeMillis = System.currentTimeMillis();
        synchronized (zza) {
            zzlf zzk = zzk(1);
            if (zzk == null) {
                zzi(4022, currentTimeMillis);
                return null;
            }
            File zze2 = zze(zzk.zzj());
            File file = new File(zze2, "pcam.jar");
            if (!file.exists()) {
                file = new File(zze2, "pcam");
            }
            File file2 = new File(zze2, "pcbc");
            File file3 = new File(zze2, "pcopt");
            zzi(5016, currentTimeMillis);
            zzov zzov = new zzov(zzk, file, file2, file3);
            return zzov;
        }
    }

    public final boolean zzd(int i) {
        long currentTimeMillis = System.currentTimeMillis();
        synchronized (zza) {
            zzlf zzk = zzk(1);
            if (zzk == null) {
                zzi(4025, currentTimeMillis);
                return false;
            }
            File zze2 = zze(zzk.zzj());
            if (!new File(zze2, "pcam.jar").exists()) {
                zzi(4026, currentTimeMillis);
                return false;
            } else if (!new File(zze2, "pcbc").exists()) {
                zzi(4027, currentTimeMillis);
                return false;
            } else {
                zzi(5019, currentTimeMillis);
                return true;
            }
        }
    }
}

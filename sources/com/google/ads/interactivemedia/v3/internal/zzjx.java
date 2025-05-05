package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.exifinterface.media.ExifInterface;
import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzjx extends zzkx {
    private static final zzky zzh = new zzky();
    private final zzai zzi;
    private final Context zzj;
    private final zzgq zzk;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzjx(zzjj zzjj, String str, String str2, zzan zzan, int i, int i2, Context context, zzy zzy, zzai zzai, zzgq zzgq) {
        super(zzjj, "tJmUdMX6gqvtYlGKWrIbrrzb8XPfGATZoLaUzDKGLsbQDYlTX2kjiVwbkwxCBzrp", "/TGj8+Sp8IdKBz9y8bC3H0KHpnJRg9DGCA85aF22WXc=", zzan, i, 27);
        this.zzj = context;
        this.zzi = zzai;
        this.zzk = zzgq;
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0096 A[SYNTHETIC, Splitter:B:25:0x0096] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.ads.interactivemedia.v3.internal.zzgm zzc() throws java.lang.IllegalAccessException, java.lang.reflect.InvocationTargetException {
        /*
            r7 = this;
            com.google.ads.interactivemedia.v3.internal.zzma r0 = com.google.ads.interactivemedia.v3.internal.zzmj.zzq
            com.google.ads.interactivemedia.v3.internal.zzmh r1 = com.google.ads.interactivemedia.v3.internal.zzls.zzc()
            java.lang.Object r0 = r1.zza(r0)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            java.lang.String r1 = "E"
            if (r0 == 0) goto L_0x0025
            com.google.ads.interactivemedia.v3.internal.zzma r0 = com.google.ads.interactivemedia.v3.internal.zzmj.zzr
            com.google.ads.interactivemedia.v3.internal.zzmh r2 = com.google.ads.interactivemedia.v3.internal.zzls.zzc()
            java.lang.Object r0 = r2.zza(r0)
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            goto L_0x002b
        L_0x0025:
            com.google.ads.interactivemedia.v3.internal.zzai r0 = r7.zzi
            int r0 = r0.zza()
        L_0x002b:
            java.lang.reflect.Method r2 = r7.zze
            android.content.Context r3 = r7.zzj
            r4 = 0
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
            java.lang.String r5 = ""
            java.lang.Object[] r3 = new java.lang.Object[]{r3, r4, r5}
            r4 = 0
            java.lang.Object r2 = r2.invoke(r4, r3)
            java.lang.String r2 = (java.lang.String) r2
            com.google.ads.interactivemedia.v3.internal.zzgm r3 = new com.google.ads.interactivemedia.v3.internal.zzgm
            r3.<init>(r2)
            com.google.ads.interactivemedia.v3.internal.zzjj r2 = r7.zza     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008f }
            boolean r2 = r2.zzp()     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008f }
            if (r2 == 0) goto L_0x008f
            com.google.ads.interactivemedia.v3.internal.zzjj r2 = r7.zza     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008f }
            java.util.concurrent.Future r2 = r2.zzl()     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008f }
            if (r2 == 0) goto L_0x008f
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008f }
            r4 = 31
            if (r2 < r4) goto L_0x0068
            com.google.ads.interactivemedia.v3.internal.zzjj r2 = r7.zza     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008f }
            java.util.concurrent.Future r2 = r2.zzl()     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008f }
            boolean r2 = r2.isDone()     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008f }
            if (r2 == 0) goto L_0x008f
        L_0x0068:
            com.google.ads.interactivemedia.v3.internal.zzai r2 = r7.zzi     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008f }
            boolean r2 = r2.zzg()     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008f }
            if (r2 != 0) goto L_0x008f
            com.google.ads.interactivemedia.v3.internal.zzjj r2 = r7.zza     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008f }
            java.util.concurrent.Future r2 = r2.zzl()     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008f }
            long r4 = (long) r0     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008f }
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008f }
            r2.get(r4, r6)     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008f }
            com.google.ads.interactivemedia.v3.internal.zzjj r2 = r7.zza     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008f }
            com.google.ads.interactivemedia.v3.internal.zzbp r2 = r2.zzc()     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008f }
            if (r2 == 0) goto L_0x008f
            boolean r4 = r2.zzaj()     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008f }
            if (r4 == 0) goto L_0x008f
            java.lang.String r2 = r2.zzg()     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x008f }
            goto L_0x0090
        L_0x008f:
            r2 = r1
        L_0x0090:
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L_0x00ad
            com.google.ads.interactivemedia.v3.internal.zzgq r1 = r7.zzk     // Catch:{ ClassCastException | InterruptedException | ExecutionException | TimeoutException -> 0x00ad }
            com.google.ads.interactivemedia.v3.internal.zzuu r1 = r1.zza()     // Catch:{ ClassCastException | InterruptedException | ExecutionException | TimeoutException -> 0x00ad }
            long r4 = (long) r0     // Catch:{ ClassCastException | InterruptedException | ExecutionException | TimeoutException -> 0x00ad }
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ ClassCastException | InterruptedException | ExecutionException | TimeoutException -> 0x00ad }
            java.lang.Object r0 = r1.get(r4, r0)     // Catch:{ ClassCastException | InterruptedException | ExecutionException | TimeoutException -> 0x00ad }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ ClassCastException | InterruptedException | ExecutionException | TimeoutException -> 0x00ad }
            boolean r1 = com.google.ads.interactivemedia.v3.internal.zzjm.zzd(r0)     // Catch:{ ClassCastException | InterruptedException | ExecutionException | TimeoutException -> 0x00ad }
            r4 = 1
            if (r4 == r1) goto L_0x00ad
            r2 = r0
        L_0x00ad:
            r3.zza = r2
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.zzjx.zzc():com.google.ads.interactivemedia.v3.internal.zzgm");
    }

    private final String zzd() {
        try {
            if (this.zza.zzl() != null) {
                this.zza.zzl().get();
            }
            zzbp zzc = this.zza.zzc();
            if (zzc == null || !zzc.zzaj()) {
                return null;
            }
            return zzc.zzg();
        } catch (InterruptedException | ExecutionException unused) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public final void zza() throws IllegalAccessException, InvocationTargetException {
        zzgm zzgm;
        int i;
        zzgm zzgm2;
        Boolean bool;
        AtomicReference zza = zzh.zza(this.zzj.getPackageName());
        synchronized (zza) {
            zzgm zzgm3 = (zzgm) zza.get();
            if (zzgm3 == null || zzjm.zzd(zzgm3.zza) || zzgm3.zza.equals(ExifInterface.LONGITUDE_EAST) || zzgm3.zza.equals("0000000000000000000000000000000000000000000000000000000000000000")) {
                if (!zzjm.zzd((String) null)) {
                    i = 5;
                } else {
                    if (!zzjm.zzd((String) null)) {
                        bool = false;
                    } else {
                        bool = false;
                    }
                    bool.booleanValue();
                    i = 3;
                }
                if (this.zzk != null) {
                    zzgm2 = zzc();
                } else {
                    Boolean valueOf = Boolean.valueOf(i == 3 && !this.zzi.zzf());
                    Boolean bool2 = (Boolean) zzls.zzc().zza(zzmj.zze);
                    String zzb = ((Boolean) zzls.zzc().zza(zzmj.zzd)).booleanValue() ? zzb() : null;
                    if (bool2.booleanValue() && this.zza.zzp() && zzjm.zzd(zzb)) {
                        zzb = zzd();
                    }
                    zzgm zzgm4 = new zzgm((String) this.zze.invoke((Object) null, new Object[]{this.zzj, valueOf, zzb}));
                    if (zzjm.zzd(zzgm4.zza) || zzgm4.zza.equals(ExifInterface.LONGITUDE_EAST)) {
                        int i2 = i - 1;
                        if (i2 == 3) {
                            String zzd = zzd();
                            if (!zzjm.zzd(zzd)) {
                                zzgm4.zza = zzd;
                            }
                        } else if (i2 == 4) {
                            throw null;
                        }
                    }
                    zzgm2 = zzgm4;
                }
                zza.set(zzgm2);
            }
            zzgm = (zzgm) zza.get();
        }
        synchronized (this.zzd) {
            if (zzgm != null) {
                this.zzd.zzx(zzgm.zza);
                this.zzd.zzX(zzgm.zzb);
                this.zzd.zzZ(zzgm.zzc);
                this.zzd.zzi(zzgm.zzd);
                this.zzd.zzw(zzgm.zze);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final String zzb() {
        try {
            CertificateFactory instance = CertificateFactory.getInstance("X.509");
            byte[] zzf = zzjm.zzf((String) zzls.zzc().zza(zzmj.zzf));
            ArrayList arrayList = new ArrayList();
            arrayList.add(instance.generateCertificate(new ByteArrayInputStream(zzf)));
            if (!Build.TYPE.equals("user")) {
                arrayList.add(instance.generateCertificate(new ByteArrayInputStream(zzjm.zzf((String) zzls.zzc().zza(zzmj.zzg)))));
            }
            Context context = this.zzj;
            String packageName = context.getPackageName();
            this.zza.zzk();
            if (Build.VERSION.SDK_INT <= 30 && !Build.VERSION.CODENAME.equals(ExifInterface.LATITUDE_SOUTH)) {
                return null;
            }
            zzvd zzs = zzvd.zzs();
            context.getPackageManager().requestChecksums(packageName, false, 8, arrayList, new zzkz(zzs));
            return (String) zzs.get();
        } catch (PackageManager.NameNotFoundException | InterruptedException | NoClassDefFoundError | CertificateEncodingException | CertificateException | ExecutionException unused) {
            return null;
        }
    }
}

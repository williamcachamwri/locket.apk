package com.google.android.gms.internal.pal;

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

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzei extends zzfg {
    private static final zzfh zzi = new zzfh();
    private final Context zzj;
    private final zzi zzk;

    public zzei(zzdu zzdu, String str, String str2, zzr zzr, int i, int i2, Context context, zzi zzi2) {
        super(zzdu, "hhtrMjcGMTQSGdrv1+l2gakNTe0Pfchc8VT5kRHtsehlafuJ8JEE4iewNV4y5I/U", "o5W1eROpLyVNcsDGW3Y0lGc2x/V+mDPvMXouv3gbW6M=", zzr, i, 27);
        this.zzj = context;
        this.zzk = zzi2;
    }

    public static String zzc(zzi zzi2) {
        if (zzi2 == null || !zzi2.zzg() || zzdx.zzg(zzi2.zze().zzd())) {
            return null;
        }
        return zzi2.zze().zzd();
    }

    private final String zzd() {
        try {
            if (this.zzb.zzl() != null) {
                this.zzb.zzl().get();
            }
            zzaf zzc = this.zzb.zzc();
            if (zzc == null || !zzc.zzah()) {
                return null;
            }
            return zzc.zzf();
        } catch (InterruptedException | ExecutionException unused) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public final void zza() throws IllegalAccessException, InvocationTargetException {
        zzbk zzbk;
        int i;
        Boolean bool;
        AtomicReference zza = zzi.zza(this.zzj.getPackageName());
        synchronized (zza) {
            zzbk zzbk2 = (zzbk) zza.get();
            if (zzbk2 == null || zzdx.zzg(zzbk2.zza) || zzbk2.zza.equals(ExifInterface.LONGITUDE_EAST) || zzbk2.zza.equals("0000000000000000000000000000000000000000000000000000000000000000")) {
                if (!zzdx.zzg(zzc(this.zzk))) {
                    i = 5;
                } else {
                    zzi zzi2 = this.zzk;
                    if (!zzdx.zzg(zzc(zzi2))) {
                        bool = false;
                    } else {
                        bool = Boolean.valueOf(zzi2 != null && zzi2.zzf() && zzi2.zzd().zzd() == 4);
                    }
                    i = (!bool.booleanValue() || !this.zzb.zzp()) ? 3 : 4;
                }
                Boolean valueOf = Boolean.valueOf(i == 3);
                Boolean bool2 = (Boolean) zzfv.zzc().zzb(zzgk.zzbY);
                String zzb = ((Boolean) zzfv.zzc().zzb(zzgk.zzbX)).booleanValue() ? zzb() : null;
                if (bool2.booleanValue() && this.zzb.zzp() && zzdx.zzg(zzb)) {
                    zzb = zzd();
                }
                zzbk zzbk3 = new zzbk((String) this.zzf.invoke((Object) null, new Object[]{this.zzj, valueOf, zzb}));
                if (zzdx.zzg(zzbk3.zza) || zzbk3.zza.equals(ExifInterface.LONGITUDE_EAST)) {
                    int i2 = i - 1;
                    if (i2 == 3) {
                        String zzd = zzd();
                        if (!zzdx.zzg(zzd)) {
                            zzbk3.zza = zzd;
                        }
                    } else if (i2 == 4) {
                        zzbk3.zza = this.zzk.zze().zzd();
                    }
                }
                zza.set(zzbk3);
            }
            zzbk = (zzbk) zza.get();
        }
        synchronized (this.zze) {
            if (zzbk != null) {
                this.zze.zzx(zzbk.zza);
                this.zze.zzY(zzbk.zzb);
                this.zze.zzaa(zzbk.zzc);
                this.zze.zzi(zzbk.zzd);
                this.zze.zzw(zzbk.zze);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final String zzb() {
        try {
            CertificateFactory instance = CertificateFactory.getInstance("X.509");
            byte[] zzi2 = zzdx.zzi((String) zzfv.zzc().zzb(zzgk.zzbZ));
            ArrayList arrayList = new ArrayList();
            arrayList.add(instance.generateCertificate(new ByteArrayInputStream(zzi2)));
            if (!Build.TYPE.equals("user")) {
                arrayList.add(instance.generateCertificate(new ByteArrayInputStream(zzdx.zzi((String) zzfv.zzc().zzb(zzgk.zzca)))));
            }
            Context context = this.zzj;
            String packageName = context.getPackageName();
            this.zzb.zzk();
            if (Build.VERSION.SDK_INT <= 30 && !Build.VERSION.CODENAME.equals(ExifInterface.LATITUDE_SOUTH)) {
                return null;
            }
            zzjr zzj2 = zzjr.zzj();
            context.getPackageManager().requestChecksums(packageName, false, 8, arrayList, new zzfi(zzj2));
            return (String) zzj2.get();
        } catch (PackageManager.NameNotFoundException | InterruptedException | NoClassDefFoundError | CertificateEncodingException | CertificateException | ExecutionException unused) {
            return null;
        }
    }
}

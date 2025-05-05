package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzpj {
    private static final zzpj zza = new zzpj();
    private final AtomicReference zzb = new AtomicReference(new zzqb(new zzpv(), (zzqa) null));

    public static zzpj zzb() {
        return zza;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:3|4|5) */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0012, code lost:
        return new com.google.android.gms.internal.pal.zzpc(r2, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0013, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001b, code lost:
        throw new com.google.android.gms.internal.pal.zzqc("Creating a LegacyProtoKey failed", r2);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x000d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.pal.zzka zza(com.google.android.gms.internal.pal.zzps r2, com.google.android.gms.internal.pal.zzlg r3) {
        /*
            r1 = this;
            java.util.concurrent.atomic.AtomicReference r0 = r1.zzb     // Catch:{ GeneralSecurityException -> 0x000d }
            java.lang.Object r0 = r0.get()     // Catch:{ GeneralSecurityException -> 0x000d }
            com.google.android.gms.internal.pal.zzqb r0 = (com.google.android.gms.internal.pal.zzqb) r0     // Catch:{ GeneralSecurityException -> 0x000d }
            com.google.android.gms.internal.pal.zzka r2 = r0.zza(r2, r3)     // Catch:{ GeneralSecurityException -> 0x000d }
            return r2
        L_0x000d:
            com.google.android.gms.internal.pal.zzpc r0 = new com.google.android.gms.internal.pal.zzpc     // Catch:{ GeneralSecurityException -> 0x0013 }
            r0.<init>(r2, r3)     // Catch:{ GeneralSecurityException -> 0x0013 }
            return r0
        L_0x0013:
            r2 = move-exception
            com.google.android.gms.internal.pal.zzqc r3 = new com.google.android.gms.internal.pal.zzqc
            java.lang.String r0 = "Creating a LegacyProtoKey failed"
            r3.<init>(r0, r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.pal.zzpj.zza(com.google.android.gms.internal.pal.zzps, com.google.android.gms.internal.pal.zzlg):com.google.android.gms.internal.pal.zzka");
    }

    public final synchronized void zzc(zzou zzou) throws GeneralSecurityException {
        zzpv zzpv = new zzpv((zzqb) this.zzb.get());
        zzpv.zza(zzou);
        this.zzb.set(new zzqb(zzpv, (zzqa) null));
    }

    public final synchronized void zzd(zzox zzox) throws GeneralSecurityException {
        zzpv zzpv = new zzpv((zzqb) this.zzb.get());
        zzpv.zzb(zzox);
        this.zzb.set(new zzqb(zzpv, (zzqa) null));
    }

    public final synchronized void zze(zzpm zzpm) throws GeneralSecurityException {
        zzpv zzpv = new zzpv((zzqb) this.zzb.get());
        zzpv.zzc(zzpm);
        this.zzb.set(new zzqb(zzpv, (zzqa) null));
    }

    public final synchronized void zzf(zzpp zzpp) throws GeneralSecurityException {
        zzpv zzpv = new zzpv((zzqb) this.zzb.get());
        zzpv.zzd(zzpp);
        this.zzb.set(new zzqb(zzpv, (zzqa) null));
    }
}

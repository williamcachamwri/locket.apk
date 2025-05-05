package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
final class zzmm extends zzmk<zzmj, zzmj> {
    /* access modifiers changed from: package-private */
    public final /* synthetic */ int zza(Object obj) {
        return ((zzmj) obj).zza();
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(zzlr zzlr) {
        return false;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ int zzb(Object obj) {
        return ((zzmj) obj).zzb();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzc(Object obj) {
        zzmj zzmj = ((zzjt) obj).zzb;
        if (zzmj != zzmj.zzc()) {
            return zzmj;
        }
        zzmj zzd = zzmj.zzd();
        zza(obj, zzd);
        return zzd;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzd(Object obj) {
        return ((zzjt) obj).zzb;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zza(Object obj, Object obj2) {
        zzmj zzmj = (zzmj) obj;
        zzmj zzmj2 = (zzmj) obj2;
        if (zzmj.zzc().equals(zzmj2)) {
            return zzmj;
        }
        if (zzmj.zzc().equals(zzmj)) {
            return zzmj.zza(zzmj, zzmj2);
        }
        return zzmj.zza(zzmj2);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zza() {
        return zzmj.zzd();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zze(Object obj) {
        zzmj zzmj = (zzmj) obj;
        zzmj.zze();
        return zzmj;
    }

    zzmm() {
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Object obj, int i, int i2) {
        ((zzmj) obj).zza((i << 3) | 5, (Object) Integer.valueOf(i2));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Object obj, int i, long j) {
        ((zzmj) obj).zza((i << 3) | 1, (Object) Long.valueOf(j));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Object obj, int i, Object obj2) {
        ((zzmj) obj).zza((i << 3) | 3, (Object) (zzmj) obj2);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Object obj, int i, zzik zzik) {
        ((zzmj) obj).zza((i << 3) | 2, (Object) zzik);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(Object obj, int i, long j) {
        ((zzmj) obj).zza(i << 3, (Object) Long.valueOf(j));
    }

    /* access modifiers changed from: package-private */
    public final void zzf(Object obj) {
        ((zzjt) obj).zzb.zze();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(Object obj, Object obj2) {
        zza(obj, (zzmj) obj2);
    }

    private static void zza(Object obj, zzmj zzmj) {
        ((zzjt) obj).zzb = zzmj;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(Object obj, Object obj2) {
        zza(obj, (zzmj) obj2);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Object obj, zznb zznb) throws IOException {
        ((zzmj) obj).zza(zznb);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(Object obj, zznb zznb) throws IOException {
        ((zzmj) obj).zzb(zznb);
    }
}

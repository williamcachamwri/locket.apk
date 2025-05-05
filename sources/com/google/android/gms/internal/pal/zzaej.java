package com.google.android.gms.internal.pal;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzaej implements zzaer {
    private final zzaef zza;
    private final zzafi zzb;
    private final boolean zzc;
    private final zzacn zzd;

    private zzaej(zzafi zzafi, zzacn zzacn, zzaef zzaef) {
        this.zzb = zzafi;
        this.zzc = zzacn.zzh(zzaef);
        this.zzd = zzacn;
        this.zza = zzaef;
    }

    static zzaej zzc(zzafi zzafi, zzacn zzacn, zzaef zzaef) {
        return new zzaej(zzafi, zzacn, zzaef);
    }

    public final int zza(Object obj) {
        zzafi zzafi = this.zzb;
        int zzb2 = zzafi.zzb(zzafi.zzd(obj));
        if (!this.zzc) {
            return zzb2;
        }
        this.zzd.zza(obj);
        throw null;
    }

    public final int zzb(Object obj) {
        int hashCode = this.zzb.zzd(obj).hashCode();
        if (!this.zzc) {
            return hashCode;
        }
        this.zzd.zza(obj);
        throw null;
    }

    public final Object zze() {
        return this.zza.zzaB().zzap();
    }

    public final void zzf(Object obj) {
        this.zzb.zzm(obj);
        this.zzd.zze(obj);
    }

    public final void zzg(Object obj, Object obj2) {
        zzaet.zzF(this.zzb, obj, obj2);
        if (this.zzc) {
            zzaet.zzE(this.zzd, obj, obj2);
        }
    }

    public final void zzh(Object obj, zzaeq zzaeq, zzacm zzacm) throws IOException {
        boolean z;
        zzafi zzafi = this.zzb;
        zzacn zzacn = this.zzd;
        Object zzc2 = zzafi.zzc(obj);
        zzacr zzb2 = zzacn.zzb(obj);
        while (zzaeq.zzc() != Integer.MAX_VALUE) {
            int zzd2 = zzaeq.zzd();
            if (zzd2 != 11) {
                if ((zzd2 & 7) == 2) {
                    Object zzc3 = zzacn.zzc(zzacm, this.zza, zzd2 >>> 3);
                    if (zzc3 != null) {
                        zzacn.zzf(zzaeq, zzc3, zzacm, zzb2);
                    } else {
                        z = zzafi.zzq(zzc2, zzaeq);
                    }
                } else {
                    z = zzaeq.zzO();
                }
                if (!z) {
                    zzafi.zzn(obj, zzc2);
                    return;
                }
            } else {
                Object obj2 = null;
                int i = 0;
                zzaby zzaby = null;
                while (true) {
                    try {
                        if (zzaeq.zzc() == Integer.MAX_VALUE) {
                            break;
                        }
                        int zzd3 = zzaeq.zzd();
                        if (zzd3 == 16) {
                            i = zzaeq.zzj();
                            obj2 = zzacn.zzc(zzacm, this.zza, i);
                        } else if (zzd3 == 26) {
                            if (obj2 != null) {
                                zzacn.zzf(zzaeq, obj2, zzacm, zzb2);
                            } else {
                                zzaby = zzaeq.zzp();
                            }
                        } else if (!zzaeq.zzO()) {
                            break;
                        }
                    } catch (Throwable th) {
                        zzafi.zzn(obj, zzc2);
                        throw th;
                    }
                }
                if (zzaeq.zzd() != 12) {
                    throw zzadi.zzb();
                } else if (zzaby != null) {
                    if (obj2 != null) {
                        zzacn.zzg(zzaby, obj2, zzacm, zzb2);
                    } else {
                        zzafi.zzk(zzc2, i, zzaby);
                    }
                }
            }
        }
        zzafi.zzn(obj, zzc2);
    }

    public final void zzi(Object obj, byte[] bArr, int i, int i2, zzabl zzabl) throws IOException {
        zzacz zzacz = (zzacz) obj;
        if (zzacz.zzc == zzafj.zzc()) {
            zzacz.zzc = zzafj.zze();
        }
        zzacw zzacw = (zzacw) obj;
        throw null;
    }

    public final void zzj(Object obj, zzaga zzaga) throws IOException {
        this.zzd.zza(obj);
        throw null;
    }

    public final boolean zzk(Object obj, Object obj2) {
        if (!this.zzb.zzd(obj).equals(this.zzb.zzd(obj2))) {
            return false;
        }
        if (!this.zzc) {
            return true;
        }
        this.zzd.zza(obj);
        this.zzd.zza(obj2);
        throw null;
    }

    public final boolean zzl(Object obj) {
        this.zzd.zza(obj);
        throw null;
    }
}

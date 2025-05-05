package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzali  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzali<T> implements zzalv<T> {
    private final zzalc zza;
    private final zzamo<?, ?> zzb;
    private final boolean zzc;
    private final zzajm<?> zzd;

    public final int zza(T t) {
        zzamo<?, ?> zzamo = this.zzb;
        int zzb2 = zzamo.zzb(zzamo.zzd(t)) + 0;
        return this.zzc ? zzb2 + this.zzd.zza((Object) t).zza() : zzb2;
    }

    public final int zzb(T t) {
        int hashCode = this.zzb.zzd(t).hashCode();
        return this.zzc ? (hashCode * 53) + this.zzd.zza((Object) t).hashCode() : hashCode;
    }

    static <T> zzali<T> zza(zzamo<?, ?> zzamo, zzajm<?> zzajm, zzalc zzalc) {
        return new zzali<>(zzamo, zzajm, zzalc);
    }

    public final T zza() {
        zzalc zzalc = this.zza;
        if (zzalc instanceof zzajy) {
            return ((zzajy) zzalc).zzo();
        }
        return zzalc.zzq().zzf();
    }

    private zzali(zzamo<?, ?> zzamo, zzajm<?> zzajm, zzalc zzalc) {
        this.zzb = zzamo;
        this.zzc = zzajm.zza(zzalc);
        this.zzd = zzajm;
        this.zza = zzalc;
    }

    public final void zzd(T t) {
        this.zzb.zzf(t);
        this.zzd.zzc(t);
    }

    public final void zza(T t, T t2) {
        zzalx.zza(this.zzb, t, t2);
        if (this.zzc) {
            zzalx.zza(this.zzd, t, t2);
        }
    }

    public final void zza(T t, zzals zzals, zzajk zzajk) throws IOException {
        boolean z;
        zzamo<?, ?> zzamo = this.zzb;
        zzajm<?> zzajm = this.zzd;
        Object zzc2 = zzamo.zzc(t);
        zzajr<?> zzb2 = zzajm.zzb(t);
        do {
            try {
                if (zzals.zzc() == Integer.MAX_VALUE) {
                    zzamo.zzb((Object) t, zzc2);
                    return;
                }
                int zzd2 = zzals.zzd();
                int i = 0;
                if (zzd2 == 11) {
                    Object obj = null;
                    zzaip zzaip = null;
                    while (zzals.zzc() != Integer.MAX_VALUE) {
                        int zzd3 = zzals.zzd();
                        if (zzd3 == 16) {
                            i = zzals.zzj();
                            obj = zzajm.zza(zzajk, this.zza, i);
                        } else if (zzd3 == 26) {
                            if (obj != null) {
                                zzajm.zza(zzals, obj, zzajk, zzb2);
                            } else {
                                zzaip = zzals.zzp();
                            }
                        } else if (!zzals.zzt()) {
                            break;
                        }
                    }
                    if (zzals.zzd() != 12) {
                        throw zzakf.zzb();
                    } else if (zzaip != null) {
                        if (obj != null) {
                            zzajm.zza(zzaip, obj, zzajk, zzb2);
                        } else {
                            zzamo.zza(zzc2, i, zzaip);
                        }
                    }
                } else if ((zzd2 & 7) == 2) {
                    Object zza2 = zzajm.zza(zzajk, this.zza, zzd2 >>> 3);
                    if (zza2 != null) {
                        zzajm.zza(zzals, zza2, zzajk, zzb2);
                    } else {
                        z = zzamo.zza(zzc2, zzals, 0);
                        continue;
                    }
                } else {
                    z = zzals.zzt();
                    continue;
                }
                z = true;
                continue;
            } finally {
                zzamo.zzb((Object) t, zzc2);
            }
        } while (z);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v14, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: com.google.android.gms.internal.firebase-auth-api.zzajy$zzd} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r10, byte[] r11, int r12, int r13, com.google.android.gms.internal.p002firebaseauthapi.zzaik r14) throws java.io.IOException {
        /*
            r9 = this;
            r0 = r10
            com.google.android.gms.internal.firebase-auth-api.zzajy r0 = (com.google.android.gms.internal.p002firebaseauthapi.zzajy) r0
            com.google.android.gms.internal.firebase-auth-api.zzamn r1 = r0.zzb
            com.google.android.gms.internal.firebase-auth-api.zzamn r2 = com.google.android.gms.internal.p002firebaseauthapi.zzamn.zzc()
            if (r1 != r2) goto L_0x0011
            com.google.android.gms.internal.firebase-auth-api.zzamn r1 = com.google.android.gms.internal.p002firebaseauthapi.zzamn.zzd()
            r0.zzb = r1
        L_0x0011:
            com.google.android.gms.internal.firebase-auth-api.zzajy$zzb r10 = (com.google.android.gms.internal.p002firebaseauthapi.zzajy.zzb) r10
            r10.zza()
            r10 = 0
            r0 = r10
        L_0x0018:
            if (r12 >= r13) goto L_0x00a4
            int r4 = com.google.android.gms.internal.p002firebaseauthapi.zzail.zzc(r11, r12, r14)
            int r2 = r14.zza
            r12 = 11
            r3 = 2
            if (r2 == r12) goto L_0x0051
            r12 = r2 & 7
            if (r12 != r3) goto L_0x004c
            com.google.android.gms.internal.firebase-auth-api.zzajm<?> r12 = r9.zzd
            com.google.android.gms.internal.firebase-auth-api.zzajk r0 = r14.zzd
            com.google.android.gms.internal.firebase-auth-api.zzalc r3 = r9.zza
            int r5 = r2 >>> 3
            java.lang.Object r12 = r12.zza(r0, r3, r5)
            r0 = r12
            com.google.android.gms.internal.firebase-auth-api.zzajy$zzd r0 = (com.google.android.gms.internal.p002firebaseauthapi.zzajy.zzd) r0
            if (r0 != 0) goto L_0x0043
            r3 = r11
            r5 = r13
            r6 = r1
            r7 = r14
            int r12 = com.google.android.gms.internal.p002firebaseauthapi.zzail.zza((int) r2, (byte[]) r3, (int) r4, (int) r5, (com.google.android.gms.internal.p002firebaseauthapi.zzamn) r6, (com.google.android.gms.internal.p002firebaseauthapi.zzaik) r7)
            goto L_0x0018
        L_0x0043:
            com.google.android.gms.internal.p002firebaseauthapi.zzalr.zza()
            java.lang.NoSuchMethodError r10 = new java.lang.NoSuchMethodError
            r10.<init>()
            throw r10
        L_0x004c:
            int r12 = com.google.android.gms.internal.p002firebaseauthapi.zzail.zza((int) r2, (byte[]) r11, (int) r4, (int) r13, (com.google.android.gms.internal.p002firebaseauthapi.zzaik) r14)
            goto L_0x0018
        L_0x0051:
            r12 = 0
            r2 = r10
        L_0x0053:
            if (r4 >= r13) goto L_0x0099
            int r4 = com.google.android.gms.internal.p002firebaseauthapi.zzail.zzc(r11, r4, r14)
            int r5 = r14.zza
            int r6 = r5 >>> 3
            r7 = r5 & 7
            if (r6 == r3) goto L_0x007b
            r8 = 3
            if (r6 == r8) goto L_0x0065
            goto L_0x0090
        L_0x0065:
            if (r0 != 0) goto L_0x0072
            if (r7 != r3) goto L_0x0090
            int r4 = com.google.android.gms.internal.p002firebaseauthapi.zzail.zza(r11, r4, r14)
            java.lang.Object r2 = r14.zzc
            com.google.android.gms.internal.firebase-auth-api.zzaip r2 = (com.google.android.gms.internal.p002firebaseauthapi.zzaip) r2
            goto L_0x0053
        L_0x0072:
            com.google.android.gms.internal.p002firebaseauthapi.zzalr.zza()
            java.lang.NoSuchMethodError r10 = new java.lang.NoSuchMethodError
            r10.<init>()
            throw r10
        L_0x007b:
            if (r7 != 0) goto L_0x0090
            int r4 = com.google.android.gms.internal.p002firebaseauthapi.zzail.zzc(r11, r4, r14)
            int r12 = r14.zza
            com.google.android.gms.internal.firebase-auth-api.zzajm<?> r0 = r9.zzd
            com.google.android.gms.internal.firebase-auth-api.zzajk r5 = r14.zzd
            com.google.android.gms.internal.firebase-auth-api.zzalc r6 = r9.zza
            java.lang.Object r0 = r0.zza(r5, r6, r12)
            com.google.android.gms.internal.firebase-auth-api.zzajy$zzd r0 = (com.google.android.gms.internal.p002firebaseauthapi.zzajy.zzd) r0
            goto L_0x0053
        L_0x0090:
            r6 = 12
            if (r5 == r6) goto L_0x0099
            int r4 = com.google.android.gms.internal.p002firebaseauthapi.zzail.zza((int) r5, (byte[]) r11, (int) r4, (int) r13, (com.google.android.gms.internal.p002firebaseauthapi.zzaik) r14)
            goto L_0x0053
        L_0x0099:
            if (r2 == 0) goto L_0x00a1
            int r12 = r12 << 3
            r12 = r12 | r3
            r1.zza((int) r12, (java.lang.Object) r2)
        L_0x00a1:
            r12 = r4
            goto L_0x0018
        L_0x00a4:
            if (r12 != r13) goto L_0x00a7
            return
        L_0x00a7:
            com.google.android.gms.internal.firebase-auth-api.zzakf r10 = com.google.android.gms.internal.p002firebaseauthapi.zzakf.zzg()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzali.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.firebase-auth-api.zzaik):void");
    }

    public final void zza(T t, zzanf zzanf) throws IOException {
        Iterator<Map.Entry<?, Object>> zzd2 = this.zzd.zza((Object) t).zzd();
        while (zzd2.hasNext()) {
            Map.Entry next = zzd2.next();
            zzajt zzajt = (zzajt) next.getKey();
            if (zzajt.zzc() != zzand.MESSAGE || zzajt.zze() || zzajt.zzd()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            } else if (next instanceof zzakj) {
                zzanf.zza(zzajt.zza(), (Object) ((zzakj) next).zza().zzb());
            } else {
                zzanf.zza(zzajt.zza(), next.getValue());
            }
        }
        zzamo<?, ?> zzamo = this.zzb;
        zzamo.zza(zzamo.zzd(t), zzanf);
    }

    public final boolean zzb(T t, T t2) {
        if (!this.zzb.zzd(t).equals(this.zzb.zzd(t2))) {
            return false;
        }
        if (this.zzc) {
            return this.zzd.zza((Object) t).equals(this.zzd.zza((Object) t2));
        }
        return true;
    }

    public final boolean zze(T t) {
        return this.zzd.zza((Object) t).zzg();
    }
}

package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;
import com.google.android.gms.internal.p002firebaseauthapi.zzvq;
import com.google.android.gms.internal.p002firebaseauthapi.zzwa;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbt  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzbt {
    private final zzwa zza;
    private final List<zzca> zzb;
    private final zzng zzc;

    private static zzbp zza(zzwa.zzb zzb2) throws GeneralSecurityException {
        zzpc zza2 = zzpc.zza(zzb2.zzb().zzf(), zzb2.zzb().zze(), zzb2.zzb().zzb(), zzb2.zzf(), zzb2.zzf() == zzws.RAW ? null : Integer.valueOf(zzb2.zza()));
        zzof zza3 = zzof.zza();
        zzcn zza4 = zzcn.zza();
        if (!zza3.zzb(zza2)) {
            return new zznc(zza2, zza4);
        }
        return zza3.zza(zza2, zza4);
    }

    private static zzbr zza(zzvv zzvv) throws GeneralSecurityException {
        int i = zzbw.zza[zzvv.ordinal()];
        if (i == 1) {
            return zzbr.zza;
        }
        if (i == 2) {
            return zzbr.zzb;
        }
        if (i == 3) {
            return zzbr.zzc;
        }
        throw new GeneralSecurityException("Unknown key status");
    }

    static final zzbt zza(zzwa zzwa) throws GeneralSecurityException {
        zzd(zzwa);
        return new zzbt(zzwa, zzc(zzwa));
    }

    public static final zzbt zza(zzbu zzbu) throws GeneralSecurityException {
        return new zzbv().zza(new zzby(zzbu.zza()).zzb().zza()).zza();
    }

    public final zzbt zza() throws GeneralSecurityException {
        zzca zzca;
        zzwa.zzb zzb2;
        if (this.zza != null) {
            zzwa.zza zzc2 = zzwa.zzc();
            ArrayList arrayList = new ArrayList(this.zzb.size());
            int i = 0;
            for (zzca next : this.zzb) {
                if (next == null || !(next.zzb() instanceof zzck)) {
                    zzwa.zzb zza2 = this.zza.zza(i);
                    zzvq zzb3 = zza2.zzb();
                    if (zzb3.zzb() == zzvq.zzb.ASYMMETRIC_PRIVATE) {
                        zzvq zza3 = zzco.zza(zzb3.zzf(), zzb3.zze());
                        zzajy.zza zzn = zza2.zzn();
                        zzajy.zza zza4 = zzn;
                        zzb2 = (zzwa.zzb) ((zzajy) ((zzwa.zzb.zza) zzn).zza(zza3).zze());
                        try {
                            zzbp zza5 = zza(zzb2);
                            int zza6 = zzb2.zza();
                            zzca = new zzca(zza5, zza(zzb2.zzc()), zza6, zza6 == this.zza.zzb());
                        } catch (GeneralSecurityException unused) {
                            zzca = null;
                        }
                    } else {
                        throw new GeneralSecurityException("The keyset contains a non-private key");
                    }
                } else {
                    zzbp zzb4 = ((zzck) next.zzb()).zzb();
                    zzca = new zzca(zzb4, next.zzc(), next.zza(), next.zzd());
                    zzb2 = zzb(zzb4, next.zzc(), next.zza());
                }
                zzc2.zza(zzb2);
                arrayList.add(zzca);
                i++;
            }
            zzc2.zza(this.zza.zzb());
            return new zzbt((zzwa) ((zzajy) zzc2.zze()), arrayList, this.zzc);
        }
        throw new GeneralSecurityException("cleartext keyset is not available");
    }

    public static final zzbt zza(zzcb zzcb, zzbg zzbg, byte[] bArr) throws GeneralSecurityException, IOException {
        zzuo zza2 = zzcb.zza();
        if (zza2 != null && zza2.zzc().zzb() != 0) {
            return zza(zza(zza2, zzbg, bArr));
        }
        throw new GeneralSecurityException("empty keyset");
    }

    /* access modifiers changed from: private */
    public static zzwa.zzb zzb(zzbp zzbp, zzbr zzbr, int i) throws GeneralSecurityException {
        zzvv zzvv;
        zzpc zzpc = (zzpc) zzof.zza().zza(zzbp, zzpc.class, zzcn.zza());
        Integer zze = zzpc.zze();
        if (zze == null || zze.intValue() == i) {
            if (zzbr.zza.equals(zzbr)) {
                zzvv = zzvv.ENABLED;
            } else if (zzbr.zzb.equals(zzbr)) {
                zzvv = zzvv.DISABLED;
            } else if (zzbr.zzc.equals(zzbr)) {
                zzvv = zzvv.DESTROYED;
            } else {
                throw new IllegalStateException("Unknown key status");
            }
            return (zzwa.zzb) ((zzajy) zzwa.zzb.zzd().zza(zzvq.zza().zza(zzpc.zzf()).zza(zzpc.zzd()).zza(zzpc.zza())).zza(zzvv).zza(i).zza(zzpc.zzb()).zze());
        }
        throw new GeneralSecurityException("Wrong ID set for key with ID requirement");
    }

    private static zzwa zza(zzuo zzuo, zzbg zzbg, byte[] bArr) throws GeneralSecurityException {
        try {
            zzwa zza2 = zzwa.zza(zzbg.zza(zzuo.zzc().zzd(), bArr), zzajk.zza());
            zzd(zza2);
            return zza2;
        } catch (zzakf unused) {
            throw new GeneralSecurityException("invalid keyset, corrupted key material");
        }
    }

    /* access modifiers changed from: package-private */
    public final zzwa zzb() {
        return this.zza;
    }

    public final <P> P zza(zzbk zzbk, Class<P> cls) throws GeneralSecurityException {
        if (zzbk instanceof zzmq) {
            zzmq zzmq = (zzmq) zzbk;
            Class<?> zza2 = zzmq.zza(cls);
            if (zza2 != null) {
                return zza(zzmq, cls, zza2);
            }
            throw new GeneralSecurityException("No wrapper found for " + cls.getName());
        }
        throw new GeneralSecurityException("Currently only subclasses of InternalConfiguration are accepted");
    }

    private final <B, P> P zza(zzmq zzmq, Class<P> cls, Class<B> cls2) throws GeneralSecurityException {
        zzcs.zzb(this.zza);
        zzoy<P> zza2 = zzoz.zza(cls2);
        zza2.zza(this.zzc);
        for (int i = 0; i < this.zzb.size(); i++) {
            zzwa.zzb zza3 = this.zza.zza(i);
            if (zza3.zzc().equals(zzvv.ENABLED)) {
                zzca zzca = this.zzb.get(i);
                if (zzca != null) {
                    zzbp zzb2 = zzca.zzb();
                    try {
                        Object zza4 = zzmq.zza(zzb2, cls2);
                        if (zza3.zza() == this.zza.zzb()) {
                            zza2.zzb(zza4, zzb2, zza3);
                        } else {
                            zza2.zza(zza4, zzb2, zza3);
                        }
                    } catch (GeneralSecurityException e) {
                        String valueOf = String.valueOf(cls2);
                        throw new GeneralSecurityException("Unable to get primitive " + valueOf + " for key of type " + zza3.zzb().zzf() + ", see https://developers.google.com/tink/faq/registration_errors", e);
                    }
                } else {
                    throw new GeneralSecurityException("Key parsing of key with index " + i + " and type_url " + zza3.zzb().zzf() + " failed, unable to get primitive");
                }
            }
        }
        return zzmq.zza(zza2.zza(), cls);
    }

    public final String toString() {
        return zzcs.zza(this.zza).toString();
    }

    private static List<zzca> zzc(zzwa zzwa) {
        ArrayList arrayList = new ArrayList(zzwa.zza());
        for (zzwa.zzb next : zzwa.zze()) {
            int zza2 = next.zza();
            try {
                arrayList.add(new zzca(zza(next), zza(next.zzc()), zza2, zza2 == zzwa.zzb()));
            } catch (GeneralSecurityException unused) {
                arrayList.add((Object) null);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    private zzbt(zzwa zzwa, List<zzca> list) {
        this.zza = zzwa;
        this.zzb = list;
        this.zzc = zzng.zza;
    }

    private zzbt(zzwa zzwa, List<zzca> list, zzng zzng) {
        this.zza = zzwa;
        this.zzb = list;
        this.zzc = zzng;
    }

    /* access modifiers changed from: private */
    public static void zzd(zzwa zzwa) throws GeneralSecurityException {
        if (zzwa == null || zzwa.zza() <= 0) {
            throw new GeneralSecurityException("empty keyset");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0010  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.p002firebaseauthapi.zzce r5) throws java.security.GeneralSecurityException, java.io.IOException {
        /*
            r4 = this;
            com.google.android.gms.internal.firebase-auth-api.zzwa r0 = r4.zza
            java.util.List r0 = r0.zze()
            java.util.Iterator r0 = r0.iterator()
        L_0x000a:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x005f
            java.lang.Object r1 = r0.next()
            com.google.android.gms.internal.firebase-auth-api.zzwa$zzb r1 = (com.google.android.gms.internal.p002firebaseauthapi.zzwa.zzb) r1
            com.google.android.gms.internal.firebase-auth-api.zzvq r2 = r1.zzb()
            com.google.android.gms.internal.firebase-auth-api.zzvq$zzb r2 = r2.zzb()
            com.google.android.gms.internal.firebase-auth-api.zzvq$zzb r3 = com.google.android.gms.internal.p002firebaseauthapi.zzvq.zzb.UNKNOWN_KEYMATERIAL
            if (r2 == r3) goto L_0x003b
            com.google.android.gms.internal.firebase-auth-api.zzvq r2 = r1.zzb()
            com.google.android.gms.internal.firebase-auth-api.zzvq$zzb r2 = r2.zzb()
            com.google.android.gms.internal.firebase-auth-api.zzvq$zzb r3 = com.google.android.gms.internal.p002firebaseauthapi.zzvq.zzb.SYMMETRIC
            if (r2 == r3) goto L_0x003b
            com.google.android.gms.internal.firebase-auth-api.zzvq r2 = r1.zzb()
            com.google.android.gms.internal.firebase-auth-api.zzvq$zzb r2 = r2.zzb()
            com.google.android.gms.internal.firebase-auth-api.zzvq$zzb r3 = com.google.android.gms.internal.p002firebaseauthapi.zzvq.zzb.ASYMMETRIC_PRIVATE
            if (r2 == r3) goto L_0x003b
            goto L_0x000a
        L_0x003b:
            java.security.GeneralSecurityException r5 = new java.security.GeneralSecurityException
            com.google.android.gms.internal.firebase-auth-api.zzvq r0 = r1.zzb()
            com.google.android.gms.internal.firebase-auth-api.zzvq$zzb r0 = r0.zzb()
            java.lang.String r0 = r0.name()
            com.google.android.gms.internal.firebase-auth-api.zzvq r1 = r1.zzb()
            java.lang.String r1 = r1.zzf()
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r1}
            java.lang.String r1 = "keyset contains key material of type %s for type url %s"
            java.lang.String r0 = java.lang.String.format(r1, r0)
            r5.<init>(r0)
            throw r5
        L_0x005f:
            com.google.android.gms.internal.firebase-auth-api.zzwa r0 = r4.zza
            r5.zza((com.google.android.gms.internal.p002firebaseauthapi.zzwa) r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzbt.zza(com.google.android.gms.internal.firebase-auth-api.zzce):void");
    }

    public final void zza(zzce zzce, zzbg zzbg, byte[] bArr) throws GeneralSecurityException, IOException {
        zzwa zzwa = this.zza;
        zzce.zza((zzuo) ((zzajy) zzuo.zza().zza(zzaip.zza(zzbg.zzb(zzwa.zzk(), bArr))).zza(zzcs.zza(zzwa)).zze()));
    }
}

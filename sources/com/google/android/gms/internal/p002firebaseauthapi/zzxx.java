package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.interfaces.ECPrivateKey;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxx  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzxx implements zzbo {
    private final ECPrivateKey zza;
    private final zzxz zzb;
    private final String zzc;
    private final byte[] zzd;
    private final zzyd zze;
    private final zzkw zzf;
    private final byte[] zzg;

    public static zzbo zza(zzjs zzjs) throws GeneralSecurityException {
        ECPrivateKey zza2 = zzyb.zza(zzxw.zza.zza(zzjs.zzc().zzd()), zzmj.zza(zzjs.zze().zza(zzbq.zza())));
        byte[] bArr = new byte[0];
        if (zzjs.zzc().zzh() != null) {
            bArr = zzjs.zzc().zzh().zzb();
        }
        return new zzxx(zza2, bArr, zzxw.zza(zzjs.zzc().zze()), zzxw.zzb.zza(zzjs.zzc().zzf()), zzks.zza(zzjs.zzc()), zzjs.zzg().zzb());
    }

    private zzxx(ECPrivateKey eCPrivateKey, byte[] bArr, String str, zzyd zzyd, zzkw zzkw, byte[] bArr2) {
        this.zza = eCPrivateKey;
        this.zzb = new zzxz(eCPrivateKey);
        this.zzd = bArr;
        this.zzc = str;
        this.zze = zzyd;
        this.zzf = zzkw;
        this.zzg = bArr2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0055  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final byte[] zza(byte[] r11, byte[] r12) throws java.security.GeneralSecurityException {
        /*
            r10 = this;
            byte[] r0 = r10.zzg
            boolean r0 = com.google.android.gms.internal.p002firebaseauthapi.zzpr.zza(r0, r11)
            if (r0 == 0) goto L_0x005d
            byte[] r0 = r10.zzg
            int r0 = r0.length
            java.security.interfaces.ECPrivateKey r1 = r10.zza
            java.security.spec.ECParameterSpec r1 = r1.getParams()
            java.security.spec.EllipticCurve r1 = r1.getCurve()
            com.google.android.gms.internal.firebase-auth-api.zzyd r2 = r10.zze
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzyb.zza((java.security.spec.EllipticCurve) r1)
            int r2 = r2.ordinal()
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0031
            if (r2 == r4) goto L_0x0032
            if (r2 != r3) goto L_0x0029
            int r1 = r1 * r3
            goto L_0x0033
        L_0x0029:
            java.security.GeneralSecurityException r11 = new java.security.GeneralSecurityException
            java.lang.String r12 = "unknown EC point format"
            r11.<init>(r12)
            throw r11
        L_0x0031:
            int r1 = r1 * r3
        L_0x0032:
            int r1 = r1 + r4
        L_0x0033:
            int r2 = r11.length
            int r1 = r1 + r0
            if (r2 < r1) goto L_0x0055
            byte[] r4 = java.util.Arrays.copyOfRange(r11, r0, r1)
            com.google.android.gms.internal.firebase-auth-api.zzxz r3 = r10.zzb
            java.lang.String r5 = r10.zzc
            byte[] r6 = r10.zzd
            com.google.android.gms.internal.firebase-auth-api.zzkw r0 = r10.zzf
            int r8 = r0.zza()
            com.google.android.gms.internal.firebase-auth-api.zzyd r9 = r10.zze
            r7 = r12
            byte[] r12 = r3.zza(r4, r5, r6, r7, r8, r9)
            com.google.android.gms.internal.firebase-auth-api.zzkw r0 = r10.zzf
            byte[] r11 = r0.zza(r12, r11, r1)
            return r11
        L_0x0055:
            java.security.GeneralSecurityException r11 = new java.security.GeneralSecurityException
            java.lang.String r12 = "ciphertext too short"
            r11.<init>(r12)
            throw r11
        L_0x005d:
            java.security.GeneralSecurityException r11 = new java.security.GeneralSecurityException
            java.lang.String r12 = "Invalid ciphertext (output prefix mismatch)"
            r11.<init>(r12)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzxx.zza(byte[], byte[]):byte[]");
    }
}

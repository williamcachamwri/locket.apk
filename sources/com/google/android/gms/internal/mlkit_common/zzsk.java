package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import java.io.UnsupportedEncodingException;

/* compiled from: com.google.mlkit:common@@18.11.0 */
public final class zzsk implements zzry {
    private final zzmw zza;
    private zzqt zzb = new zzqt();

    private zzsk(zzmw zzmw, int i) {
        this.zza = zzmw;
        zzsv.zza();
    }

    public static zzry zzf(zzmw zzmw) {
        return new zzsk(zzmw, 0);
    }

    public static zzry zzg() {
        return new zzsk(new zzmw(), 0);
    }

    public final zzry zza(zzmv zzmv) {
        this.zza.zzf(zzmv);
        return this;
    }

    public final zzry zzb(zznc zznc) {
        this.zza.zzi(zznc);
        return this;
    }

    public final zzry zzc(zzqt zzqt) {
        this.zzb = zzqt;
        return this;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000c, code lost:
        r1 = r0.zzk();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zzd() {
        /*
            r2 = this;
            com.google.android.gms.internal.mlkit_common.zzmw r0 = r2.zza
            com.google.android.gms.internal.mlkit_common.zzmy r0 = r0.zzk()
            com.google.android.gms.internal.mlkit_common.zzqv r0 = r0.zzf()
            if (r0 == 0) goto L_0x0024
            java.lang.String r1 = r0.zzk()
            if (r1 == 0) goto L_0x0024
            boolean r1 = r1.isEmpty()
            if (r1 == 0) goto L_0x0019
            goto L_0x0024
        L_0x0019:
            java.lang.String r0 = r0.zzk()
            java.lang.Object r0 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r0)
            java.lang.String r0 = (java.lang.String) r0
            return r0
        L_0x0024:
            java.lang.String r0 = "NA"
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_common.zzsk.zzd():java.lang.String");
    }

    public final byte[] zze(int i, boolean z) {
        this.zzb.zzf(Boolean.valueOf(1 == (i ^ 1)));
        this.zzb.zze(false);
        this.zza.zzj(this.zzb.zzm());
        try {
            zzsv.zza();
            if (i == 0) {
                return new JsonDataEncoderBuilder().configureWith(zzkr.zza).ignoreNullValues(true).build().encode(this.zza.zzk()).getBytes("utf-8");
            }
            zzmy zzk = this.zza.zzk();
            zzbg zzbg = new zzbg();
            zzkr.zza.configure(zzbg);
            return zzbg.zza().zza(zzk);
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException("Failed to covert logging to UTF-8 byte array", e);
        }
    }
}

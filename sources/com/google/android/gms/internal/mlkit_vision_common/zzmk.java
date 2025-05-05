package com.google.android.gms.internal.mlkit_vision_common;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import java.io.UnsupportedEncodingException;

/* compiled from: com.google.mlkit:vision-common@@17.3.0 */
public final class zzmk implements zzmb {
    private final zziw zza;
    private zzky zzb = new zzky();

    private zzmk(zziw zziw, int i) {
        this.zza = zziw;
        zzmw.zza();
    }

    public static zzmb zze(zziw zziw) {
        return new zzmk(zziw, 0);
    }

    public final zzmb zza(zziv zziv) {
        this.zza.zzc(zziv);
        return this;
    }

    public final zzmb zzb(zzky zzky) {
        this.zzb = zzky;
        return this;
    }

    public final String zzc() {
        zzla zzc = this.zza.zzf().zzc();
        return (zzc == null || zzg.zzb(zzc.zzk())) ? "NA" : (String) Preconditions.checkNotNull(zzc.zzk());
    }

    public final byte[] zzd(int i, boolean z) {
        this.zzb.zzf(Boolean.valueOf(1 == (i ^ 1)));
        this.zzb.zze(false);
        this.zza.zze(this.zzb.zzm());
        try {
            zzmw.zza();
            if (i == 0) {
                return new JsonDataEncoderBuilder().configureWith(zzhe.zza).ignoreNullValues(true).build().encode(this.zza.zzf()).getBytes("utf-8");
            }
            zziy zzf = this.zza.zzf();
            zzam zzam = new zzam();
            zzhe.zza.configure(zzam);
            return zzam.zza().zza(zzf);
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException("Failed to covert logging to UTF-8 byte array", e);
        }
    }
}

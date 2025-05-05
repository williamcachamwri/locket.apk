package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public class zznb extends zzmz implements zzol {
    protected zznb(zznc zznc) {
        super(zznc);
    }

    /* renamed from: zze */
    public final zznc zzl() {
        if (!((zznc) this.zza).zzL()) {
            return (zznc) this.zza;
        }
        ((zznc) this.zza).zzb.zzg();
        return (zznc) super.zzl();
    }

    /* access modifiers changed from: protected */
    public final void zzo() {
        super.zzo();
        if (((zznc) this.zza).zzb != zzmv.zzd()) {
            zznc zznc = (zznc) this.zza;
            zznc.zzb = zznc.zzb.clone();
        }
    }
}

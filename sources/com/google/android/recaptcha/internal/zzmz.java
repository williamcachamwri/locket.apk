package com.google.android.recaptcha.internal;

import com.google.android.recaptcha.internal.zzmz;
import com.google.android.recaptcha.internal.zznf;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public class zzmz<MessageType extends zznf<MessageType, BuilderType>, BuilderType extends zzmz<MessageType, BuilderType>> extends zzkp<MessageType, BuilderType> {
    protected zznf zza;
    private final zznf zzb;

    protected zzmz(MessageType messagetype) {
        this.zzb = messagetype;
        if (!messagetype.zzL()) {
            this.zza = messagetype.zzv();
            return;
        }
        throw new IllegalArgumentException("Default instance must be immutable.");
    }

    private static void zze(Object obj, Object obj2) {
        zzou.zza().zzb(obj.getClass()).zzg(obj, obj2);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ zzkp zzb(zzkq zzkq) {
        zzh((zznf) zzkq);
        return this;
    }

    /* renamed from: zzg */
    public final zzmz zza() {
        zzmz zzmz = (zzmz) this.zzb.zzh(5, (Object) null, (Object) null);
        zzmz.zza = zzl();
        return zzmz;
    }

    public final zzmz zzh(zznf zznf) {
        if (!this.zzb.equals(zznf)) {
            if (!this.zza.zzL()) {
                zzo();
            }
            zze(this.zza, zznf);
        }
        return this;
    }

    /* renamed from: zzi */
    public final MessageType zzk() {
        MessageType zzj = zzl();
        if (zznf.zzK(zzj, true)) {
            return zzj;
        }
        throw new zzpm(zzj);
    }

    /* renamed from: zzj */
    public MessageType zzl() {
        if (!this.zza.zzL()) {
            return this.zza;
        }
        this.zza.zzG();
        return this.zza;
    }

    public final /* synthetic */ zzok zzm() {
        return this.zzb;
    }

    /* access modifiers changed from: protected */
    public final void zzn() {
        if (!this.zza.zzL()) {
            zzo();
        }
    }

    /* access modifiers changed from: protected */
    public void zzo() {
        zznf zzv = this.zzb.zzv();
        zze(zzv, this.zza);
        this.zza = zzv;
    }

    public final boolean zzp() {
        return zznf.zzK(this.zza, false);
    }
}

package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdx;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzed;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public class zzdx<MessageType extends zzed<MessageType, BuilderType>, BuilderType extends zzdx<MessageType, BuilderType>> extends zzcj<MessageType, BuilderType> {
    protected zzed zza;
    private final zzed zzb;

    protected zzdx(MessageType messagetype) {
        this.zzb = messagetype;
        if (!messagetype.zzX()) {
            this.zza = messagetype.zzJ();
            return;
        }
        throw new IllegalArgumentException("Default instance must be immutable.");
    }

    private static void zza(Object obj, Object obj2) {
        zzfx.zza().zzb(obj.getClass()).zzg(obj, obj2);
    }

    public final /* bridge */ /* synthetic */ zzfo zzab() {
        throw null;
    }

    public final boolean zzac() {
        return zzed.zzW(this.zza, false);
    }

    /* renamed from: zzf */
    public final zzdx zze() {
        zzdx zzdx = (zzdx) this.zzb.zzg(5, (Object) null, (Object) null);
        zzdx.zza = zzk();
        return zzdx;
    }

    public final zzdx zzg(zzed zzed) {
        if (!this.zzb.equals(zzed)) {
            if (!this.zza.zzX()) {
                zzn();
            }
            zza(this.zza, zzed);
        }
        return this;
    }

    /* renamed from: zzh */
    public final MessageType zzj() {
        MessageType zzi = zzk();
        if (zzi.zzac()) {
            return zzi;
        }
        throw new zzgx(zzi);
    }

    /* renamed from: zzi */
    public MessageType zzk() {
        if (!this.zza.zzX()) {
            return this.zza;
        }
        this.zza.zzS();
        return this.zza;
    }

    /* access modifiers changed from: protected */
    public final void zzm() {
        if (!this.zza.zzX()) {
            zzn();
        }
    }

    /* access modifiers changed from: protected */
    public void zzn() {
        zzed zzJ = this.zzb.zzJ();
        zza(zzJ, this.zza);
        this.zza = zzJ;
    }
}

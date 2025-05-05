package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
public abstract class zzcv extends zzcn implements Set {
    @CheckForNull
    private transient zzcs zza;

    zzcv() {
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        return zzds.zzb(this, obj);
    }

    public final int hashCode() {
        return zzds.zza(this);
    }

    /* renamed from: zzd */
    public abstract zzdu iterator();

    public final zzcs zzf() {
        zzcs zzcs = this.zza;
        if (zzcs != null) {
            return zzcs;
        }
        zzcs zzg = zzg();
        this.zza = zzg;
        return zzg;
    }

    /* access modifiers changed from: package-private */
    public zzcs zzg() {
        Object[] array = toArray();
        int i = zzcs.zzd;
        return zzcs.zzg(array, array.length);
    }
}

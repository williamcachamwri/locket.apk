package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Iterator;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
final class zzdn extends zzcv {
    private final transient zzcu zza;
    private final transient zzcs zzb;

    zzdn(zzcu zzcu, zzcs zzcs) {
        this.zza = zzcu;
        this.zzb = zzcs;
    }

    public final boolean contains(@CheckForNull Object obj) {
        return this.zza.get(obj) != null;
    }

    public final /* synthetic */ Iterator iterator() {
        return this.zzb.listIterator(0);
    }

    public final int size() {
        return 1;
    }

    /* access modifiers changed from: package-private */
    public final int zza(Object[] objArr, int i) {
        return this.zzb.zza(objArr, i);
    }

    public final zzdu zzd() {
        return this.zzb.listIterator(0);
    }
}

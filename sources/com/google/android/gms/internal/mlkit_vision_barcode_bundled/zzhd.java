package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

@Deprecated
/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public final class zzhd extends AbstractList implements RandomAccess, zzew {
    /* access modifiers changed from: private */
    public final zzew zza;

    public zzhd(zzew zzew) {
        this.zza = zzew;
    }

    public final /* bridge */ /* synthetic */ Object get(int i) {
        return ((zzev) this.zza).get(i);
    }

    public final Iterator iterator() {
        return new zzhc(this);
    }

    public final ListIterator listIterator(int i) {
        return new zzhb(this, i);
    }

    public final int size() {
        return this.zza.size();
    }

    public final zzew zze() {
        return this;
    }

    public final Object zzf(int i) {
        return this.zza.zzf(i);
    }

    public final List zzh() {
        return this.zza.zzh();
    }
}

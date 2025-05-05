package com.google.android.gms.internal.pal;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzafn extends AbstractList implements RandomAccess, zzadn {
    /* access modifiers changed from: private */
    public final zzadn zza;

    public zzafn(zzadn zzadn) {
        this.zza = zzadn;
    }

    public final /* bridge */ /* synthetic */ Object get(int i) {
        return ((zzadm) this.zza).get(i);
    }

    public final Iterator iterator() {
        return new zzafm(this);
    }

    public final ListIterator listIterator(int i) {
        return new zzafl(this, i);
    }

    public final int size() {
        return this.zza.size();
    }

    public final zzadn zze() {
        return this;
    }

    public final Object zzf(int i) {
        return this.zza.zzf(i);
    }

    public final List zzh() {
        return this.zza.zzh();
    }

    public final void zzi(zzaby zzaby) {
        throw new UnsupportedOperationException();
    }
}

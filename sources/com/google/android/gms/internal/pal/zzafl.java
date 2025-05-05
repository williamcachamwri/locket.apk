package com.google.android.gms.internal.pal;

import java.util.ListIterator;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzafl implements ListIterator {
    final ListIterator zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ zzafn zzc;

    zzafl(zzafn zzafn, int i) {
        this.zzc = zzafn;
        this.zzb = i;
        this.zza = zzafn.zza.listIterator(i);
    }

    public final /* synthetic */ void add(Object obj) {
        String str = (String) obj;
        throw new UnsupportedOperationException();
    }

    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    public final boolean hasPrevious() {
        return this.zza.hasPrevious();
    }

    public final /* bridge */ /* synthetic */ Object next() {
        return (String) this.zza.next();
    }

    public final int nextIndex() {
        return this.zza.nextIndex();
    }

    public final /* bridge */ /* synthetic */ Object previous() {
        return (String) this.zza.previous();
    }

    public final int previousIndex() {
        return this.zza.previousIndex();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public final /* synthetic */ void set(Object obj) {
        String str = (String) obj;
        throw new UnsupportedOperationException();
    }
}

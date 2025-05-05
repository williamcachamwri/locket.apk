package com.google.android.recaptcha.internal;

import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzjr extends zzjm {
    final /* synthetic */ Iterable zza;
    final /* synthetic */ int zzb;

    zzjr(Iterable iterable, int i) {
        this.zza = iterable;
        this.zzb = i;
    }

    public final Iterator iterator() {
        Iterable iterable = this.zza;
        if (iterable instanceof List) {
            List list = (List) iterable;
            return list.subList(Math.min(list.size(), this.zzb), list.size()).iterator();
        }
        int i = this.zzb;
        Iterator it = iterable.iterator();
        it.getClass();
        zzjh.zzb(i >= 0, "numberToAdvance must be nonnegative");
        for (int i2 = 0; i2 < i && it.hasNext(); i2++) {
            it.next();
        }
        return new zzjq(this, it);
    }
}

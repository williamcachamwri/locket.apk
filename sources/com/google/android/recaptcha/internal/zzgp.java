package com.google.android.recaptcha.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzgp implements zzgz {
    public static final zzgp zza = new zzgp();

    private zzgp() {
    }

    private static final boolean zzb(List list) {
        Iterable<zzug> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (zzug zzR : iterable) {
            arrayList.add(Boolean.valueOf(zzR.zzR()));
        }
        if (!((List) arrayList).contains(false)) {
            return true;
        }
        return false;
    }

    public final void zza(int i, zzgf zzgf, zzug... zzugArr) throws zzcg {
        if (zzb(ArraysKt.toList((T[]) zzugArr))) {
            for (zzug zzi : zzugArr) {
                zzgf.zzc().zzb(zzi.zzi());
            }
            return;
        }
        throw new zzcg(4, 5, (Throwable) null);
    }
}

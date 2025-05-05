package com.google.android.recaptcha.internal;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzga extends zzfz {
    private final Function2 zza;
    private final String zzb;

    public zzga(Function2 function2, String str, Object obj) {
        super(obj);
        this.zza = function2;
        this.zzb = str;
    }

    public final boolean zza(Object obj, Method method, Object[] objArr) {
        Iterable iterable;
        if (!Intrinsics.areEqual((Object) method.getName(), (Object) this.zzb)) {
            return false;
        }
        zzth zzf = zztk.zzf();
        if (objArr != null) {
            Collection arrayList = new ArrayList(objArr.length);
            for (Object obj2 : objArr) {
                zzti zzf2 = zztj.zzf();
                zzf2.zzw(obj2.toString());
                arrayList.add((zztj) zzf2.zzk());
            }
            iterable = (List) arrayList;
        } else {
            iterable = CollectionsKt.emptyList();
        }
        zzf.zze(iterable);
        Function2 function2 = this.zza;
        byte[] zzd = ((zztk) zzf.zzk()).zzd();
        function2.invoke(objArr, zzkj.zzh().zzi(zzd, 0, zzd.length));
        return true;
    }
}

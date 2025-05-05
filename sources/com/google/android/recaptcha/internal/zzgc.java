package com.google.android.recaptcha.internal;

import java.lang.reflect.Method;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzgc extends zzfz {
    private final zzgb zza;
    private final String zzb;

    public zzgc(zzgb zzgb, String str, Object obj) {
        super(obj);
        this.zza = zzgb;
        this.zzb = str;
    }

    public final boolean zza(Object obj, Method method, Object[] objArr) {
        List list;
        if (!Intrinsics.areEqual((Object) method.getName(), (Object) this.zzb)) {
            return false;
        }
        zzgb zzgb = this.zza;
        if (objArr == null || (list = ArraysKt.asList((T[]) objArr)) == null) {
            list = CollectionsKt.emptyList();
        }
        zzgb.zzb(list);
        return true;
    }
}

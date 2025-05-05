package com.google.android.recaptcha.internal;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzgt extends Lambda implements Function2 {
    final /* synthetic */ zzgf zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ int zzc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzgt(zzgf zzgf, String str, int i) {
        super(2);
        this.zza = zzgf;
        this.zzb = str;
        this.zzc = i;
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        Object[] objArr = (Object[]) obj;
        this.zza.zzi().zzb(this.zzb, (String) obj2);
        int i = this.zzc;
        if (i != -1) {
            this.zza.zzc().zze(i, objArr);
        }
        return Unit.INSTANCE;
    }
}

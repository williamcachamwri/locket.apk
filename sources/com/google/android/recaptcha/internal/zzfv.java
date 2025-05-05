package com.google.android.recaptcha.internal;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzfv implements zzfq {
    private final CoroutineScope zza;
    private final zzgh zzb;
    /* access modifiers changed from: private */
    public final zzhz zzc;
    private final Map zzd;

    public zzfv(CoroutineScope coroutineScope, zzgh zzgh, zzhz zzhz, Map map) {
        this.zza = coroutineScope;
        this.zzb = zzgh;
        this.zzc = zzhz;
        this.zzd = map;
    }

    public static final /* synthetic */ void zzf(zzfv zzfv, zzuh zzuh, zzgf zzgf) {
        zzjj zzb2 = zzjj.zzb();
        int zza2 = zzgf.zza();
        zzgz zzgz = (zzgz) zzfv.zzd.get(Integer.valueOf(zzuh.zzf()));
        if (zzgz != null) {
            int zzg = zzuh.zzg();
            zzug[] zzugArr = (zzug[]) zzuh.zzj().toArray(new zzug[0]);
            zzgz.zza(zzg, zzgf, (zzug[]) Arrays.copyOf(zzugArr, zzugArr.length));
            if (zza2 == zzgf.zza()) {
                zzgf.zzg(zzgf.zza() + 1);
            }
            zzb2.zzf();
            long zza3 = zzb2.zza(TimeUnit.MICROSECONDS);
            int i = zzbm.zza;
            int zzk = zzuh.zzk();
            if (zzk != 1) {
                zzbm.zza(zzk - 2, zza3);
                return;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
        throw new zzcg(5, 2, (Throwable) null);
    }

    /* access modifiers changed from: private */
    public final Object zzg(List list, zzgf zzgf, Continuation continuation) {
        Object coroutineScope = CoroutineScopeKt.coroutineScope(new zzfs(zzgf, list, this, (Continuation) null), continuation);
        return coroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? coroutineScope : Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public final Object zzh(Exception exc, zzgf zzgf, Continuation continuation) {
        Object coroutineScope = CoroutineScopeKt.coroutineScope(new zzft(exc, zzgf, this, (Continuation) null), continuation);
        return coroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? coroutineScope : Unit.INSTANCE;
    }

    public final void zza(String str) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(this.zza, (CoroutineContext) null, (CoroutineStart) null, new zzfu(new zzgf(this.zzb), this, str, (Continuation) null), 3, (Object) null);
    }
}

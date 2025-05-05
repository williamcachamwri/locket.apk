package com.google.android.recaptcha.internal;

import java.util.concurrent.Executors;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.Job;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzbk {
    private final CoroutineScope zza = CoroutineScopeKt.MainScope();
    private final CoroutineScope zzb;
    private final CoroutineScope zzc;
    private final CoroutineScope zzd;

    public zzbk() {
        CoroutineScope CoroutineScope = CoroutineScopeKt.CoroutineScope(ExecutorsKt.from(Executors.newSingleThreadExecutor()));
        Job unused = BuildersKt__Builders_commonKt.launch$default(CoroutineScope, (CoroutineContext) null, (CoroutineStart) null, new zzbj((Continuation) null), 3, (Object) null);
        this.zzb = CoroutineScope;
        this.zzc = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
        CoroutineScope CoroutineScope2 = CoroutineScopeKt.CoroutineScope(ExecutorsKt.from(Executors.newSingleThreadExecutor()));
        Job unused2 = BuildersKt__Builders_commonKt.launch$default(CoroutineScope2, (CoroutineContext) null, (CoroutineStart) null, new zzbi((Continuation) null), 3, (Object) null);
        this.zzd = CoroutineScope2;
    }

    public final CoroutineScope zza() {
        return this.zzc;
    }

    public final CoroutineScope zzb() {
        return this.zza;
    }

    public final CoroutineScope zzc() {
        return this.zzd;
    }

    public final CoroutineScope zzd() {
        return this.zzb;
    }
}

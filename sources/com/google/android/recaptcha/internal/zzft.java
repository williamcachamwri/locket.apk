package com.google.android.recaptcha.internal;

import java.util.Arrays;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzft extends SuspendLambda implements Function2 {
    final /* synthetic */ Exception zza;
    final /* synthetic */ zzgf zzb;
    final /* synthetic */ zzfv zzc;
    private /* synthetic */ Object zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzft(Exception exc, zzgf zzgf, zzfv zzfv, Continuation continuation) {
        super(2, continuation);
        this.zza = exc;
        this.zzb = zzgf;
        this.zzc = zzfv;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        zzft zzft = new zzft(this.zza, this.zzb, this.zzc, continuation);
        zzft.zzd = obj;
        return zzft;
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzft) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        zztf zztf;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = (CoroutineScope) this.zzd;
        Exception exc = this.zza;
        if (exc instanceof zzcg) {
            zztf = ((zzcg) exc).zza();
            zztf.zze(this.zzb.zza());
        } else {
            zzgf zzgf = this.zzb;
            zztf zzf = zztg.zzf();
            zzf.zze(zzgf.zza());
            zzf.zzr(2);
            zzf.zzq(2);
            zztf = zzf;
        }
        zztg zztg = (zztg) zztf.zzk();
        zztg.zzl();
        zztg.zzk();
        Reflection.getOrCreateKotlinClass(this.zza.getClass()).getSimpleName();
        this.zza.getMessage();
        zzgf zzgf2 = this.zzb;
        zzbp zzb2 = zzgf2.zzb();
        zzbp zzbp = zzgf2.zza;
        if (zzbp == null) {
            zzbp = null;
        }
        zzrn zza2 = zzex.zza(zzb2, zzbp);
        CharSequence zzd2 = this.zzb.zzd();
        if (zzd2.length() == 0) {
            zzd2 = "recaptcha.m.Main.rge";
        }
        String str = (String) zzd2;
        if (CoroutineScopeKt.isActive(coroutineScope)) {
            zzfv zzfv = this.zzc;
            zzkj zzh = zzkj.zzh();
            byte[] zzd3 = zztg.zzd();
            String zzi = zzh.zzi(zzd3, 0, zzd3.length);
            zzkj zzh2 = zzkj.zzh();
            byte[] zzd4 = zza2.zzd();
            zzfv.zzb.zzd().zzb(str, (String[]) Arrays.copyOf(new String[]{zzi, zzh2.zzi(zzd4, 0, zzd4.length)}, 2));
        }
        return Unit.INSTANCE;
    }
}

package com.google.android.recaptcha.internal;

import android.os.Build;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzdj extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzdv zzb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdj(zzdv zzdv, Continuation continuation) {
        super(2, continuation);
        this.zzb = zzdv;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new zzdj(this.zzb, continuation);
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzdj) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.zza;
        ResultKt.throwOnFailure(obj);
        if (i == 0) {
            int zzb2 = new zzbu(GoogleApiAvailabilityLight.getInstance()).zzb(this.zzb.zzr());
            zzdv zzdv = this.zzb;
            String zzp = zzdv.zza;
            String packageName = zzdv.zzr().getPackageName();
            String zzd = this.zzb.zzb.zzd();
            zzbh zzc = this.zzb.zzt();
            int i2 = Build.VERSION.SDK_INT;
            String zza2 = zzc.zza();
            zztp zzf = zztq.zzf();
            zzf.zzt(zzp);
            zzf.zzq(packageName);
            zzf.zzu(zzb2);
            zzf.zzr("18.7.0-beta01");
            zzf.zzs(zzd);
            zzf.zzf(String.valueOf(i2));
            zzf.zze(zza2);
            zzdv zzdv2 = this.zzb;
            zzfh zzg = zzdv.zzg(zzdv2);
            String zzb3 = zzdv.zzd(zzdv2).zzb();
            this.zza = 1;
            obj = zzg.zzc(zzb3, (zztq) zzf.zzk(), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return obj;
    }
}

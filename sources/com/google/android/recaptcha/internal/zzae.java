package com.google.android.recaptcha.internal;

import com.google.android.play.core.integrity.StandardIntegrityException;
import com.google.android.play.core.integrity.model.StandardIntegrityErrorCode;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzae extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzag zzc;
    final /* synthetic */ String zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzae(zzag zzag, String str, Continuation continuation) {
        super(2, continuation);
        this.zzc = zzag;
        this.zzd = str;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new zzae(this.zzc, this.zzd, continuation);
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzae) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        zzaa zzaa;
        zzep zzep;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.zzb;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            zzep = zzab.zzb(this.zzc, this.zzd);
            zzag zzag = this.zzc;
            this.zza = zzep;
            this.zzb = 1;
            obj = zzag.zzc.zzc(zzag.zze, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i != 1) {
            zzaa = (zzaa) this.zza;
            ResultKt.throwOnFailure(obj);
            int zza2 = zzaa.zza();
            zztf zztf = (zztf) ((zztg) obj).zzr();
            zztf.zzf(zzaa.zza());
            return new zzy(zza2, (zztg) zztf.zzk());
        } else {
            zzep = (zzep) this.zza;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Exception e) {
                zzep.zzb(new zzbf(zzbd.zzb, zzbc.zzaa, e.getMessage()));
                zzaa zzaa2 = this.zzc;
                Continuation continuation = this;
                this.zza = zzaa2;
                int i2 = 2;
                this.zzb = 2;
                if (e instanceof StandardIntegrityException) {
                    int errorCode = ((StandardIntegrityException) e).getErrorCode();
                    if (errorCode == -100) {
                        i2 = 44;
                    } else if (errorCode == -12) {
                        i2 = 39;
                    } else if (errorCode == -3) {
                        i2 = 30;
                    } else if (errorCode == -2) {
                        i2 = 29;
                    } else if (errorCode != -1) {
                        switch (errorCode) {
                            case StandardIntegrityErrorCode.INTEGRITY_TOKEN_PROVIDER_INVALID:
                                i2 = 54;
                                break;
                            case StandardIntegrityErrorCode.CLIENT_TRANSIENT_ERROR:
                                i2 = 53;
                                break;
                            case -17:
                                i2 = 52;
                                break;
                            case -16:
                                i2 = 43;
                                break;
                            case -15:
                                i2 = 42;
                                break;
                            case -14:
                                i2 = 41;
                                break;
                            default:
                                switch (errorCode) {
                                    case -9:
                                        i2 = 36;
                                        break;
                                    case -8:
                                        i2 = 35;
                                        break;
                                    case -7:
                                        i2 = 34;
                                        break;
                                    case -6:
                                        i2 = 33;
                                        break;
                                    case -5:
                                        i2 = 32;
                                        break;
                                }
                        }
                    } else {
                        i2 = 28;
                    }
                } else {
                    i2 = 45;
                }
                zztf zzf = zztg.zzf();
                zzf.zzq(i2);
                zzf.zzr(15);
                obj = zzf.zzk();
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                zzaa = zzaa2;
            }
        }
        zzep.zza();
        zzth zzf2 = zztk.zzf();
        zzti zzf3 = zztj.zzf();
        zzf3.zzw((String) obj);
        zzf2.zzf((zztj) zzf3.zzk());
        return zzab.zza(this.zzc, (zztk) zzf2.zzk());
    }
}

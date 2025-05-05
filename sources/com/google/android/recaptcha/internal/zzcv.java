package com.google.android.recaptcha.internal;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzcv extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    /* synthetic */ Object zzc;
    final /* synthetic */ zzcx zzd;
    final /* synthetic */ String zze;
    final /* synthetic */ long zzf;
    final /* synthetic */ zzcp zzg;
    final /* synthetic */ zzcj zzh;
    final /* synthetic */ zzbk zzi;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcv(zzcx zzcx, String str, long j, zzcp zzcp, zzbk zzbk, zzcj zzcj, Continuation continuation) {
        super(2, continuation);
        this.zzd = zzcx;
        this.zze = str;
        this.zzf = j;
        this.zzg = zzcp;
        this.zzi = zzbk;
        this.zzh = zzcj;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        zzcv zzcv = new zzcv(this.zzd, this.zze, this.zzf, this.zzg, this.zzi, this.zzh, continuation);
        zzcv.zzc = obj;
        return zzcv;
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzcv) create((zzem) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: com.google.android.recaptcha.internal.zzem} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.zzb
            if (r1 == 0) goto L_0x0014
            java.lang.Object r0 = r6.zza
            com.google.android.recaptcha.internal.zzcp r0 = (com.google.android.recaptcha.internal.zzcp) r0
            java.lang.Object r1 = r6.zzc
            com.google.android.recaptcha.internal.zzem r1 = (com.google.android.recaptcha.internal.zzem) r1
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0052
        L_0x0014:
            kotlin.ResultKt.throwOnFailure(r7)
            java.lang.Object r7 = r6.zzc
            r1 = r7
            com.google.android.recaptcha.internal.zzem r1 = (com.google.android.recaptcha.internal.zzem) r1
            com.google.android.recaptcha.internal.zzcx r7 = r6.zzd
            java.lang.String r2 = r6.zze
            com.google.android.recaptcha.internal.zzde r7 = com.google.android.recaptcha.internal.zzcx.zza(r7, r2)
            if (r7 == 0) goto L_0x0027
            return r7
        L_0x0027:
            com.google.android.recaptcha.internal.zzcx r7 = r6.zzd
            long r2 = r6.zzf
            com.google.android.recaptcha.internal.zzcx.zzc(r7, r2)
            com.google.android.recaptcha.internal.zzcp r7 = r6.zzg
            if (r7 != 0) goto L_0x003e
            com.google.android.recaptcha.internal.zzcx r7 = r6.zzd
            java.lang.String r2 = r6.zze
            com.google.android.recaptcha.internal.zzbk r3 = r6.zzi
            com.google.android.recaptcha.internal.zzcj r4 = r6.zzh
            com.google.android.recaptcha.internal.zzcp r7 = com.google.android.recaptcha.internal.zzcx.zze(r7, r2, r3, r4, r1)
        L_0x003e:
            long r2 = r6.zzf
            r4 = r6
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r6.zzc = r1
            r6.zza = r7
            r5 = 1
            r6.zzb = r5
            java.lang.Object r2 = r7.zzb(r2, r4)
            if (r2 != r0) goto L_0x0051
            return r0
        L_0x0051:
            r0 = r7
        L_0x0052:
            java.lang.String r7 = r6.zze
            com.google.android.recaptcha.internal.zzbk r2 = r6.zzi
            com.google.android.recaptcha.internal.zzde r3 = new com.google.android.recaptcha.internal.zzde
            r3.<init>(r0, r7, r2, r1)
            com.google.android.recaptcha.internal.zzcx r7 = r6.zzd
            r7.zzc = r3
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzcv.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}

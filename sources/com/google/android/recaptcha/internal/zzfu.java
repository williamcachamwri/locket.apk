package com.google.android.recaptcha.internal;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzfu extends SuspendLambda implements Function2 {
    Object zza;
    Object zzb;
    int zzc;
    final /* synthetic */ zzgf zzd;
    final /* synthetic */ zzfv zze;
    final /* synthetic */ String zzf;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzfu(zzgf zzgf, zzfv zzfv, String str, Continuation continuation) {
        super(2, continuation);
        this.zzd = zzgf;
        this.zze = zzfv;
        this.zzf = str;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new zzfu(this.zzd, this.zze, this.zzf, continuation);
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzfu) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0086, code lost:
        if (com.google.android.recaptcha.internal.zzfv.zzd(r7.zze, r8, r7.zzd, r7) != r0) goto L_0x0089;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.zzc
            r2 = 1
            if (r1 == 0) goto L_0x001c
            if (r1 == r2) goto L_0x0010
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0089
        L_0x0010:
            java.lang.Object r1 = r7.zzb
            com.google.android.recaptcha.internal.zzjj r1 = (com.google.android.recaptcha.internal.zzjj) r1
            java.lang.Object r2 = r7.zza
            com.google.android.recaptcha.internal.zzud r2 = (com.google.android.recaptcha.internal.zzud) r2
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ Exception -> 0x0072 }
            goto L_0x0062
        L_0x001c:
            kotlin.ResultKt.throwOnFailure(r8)
            com.google.android.recaptcha.internal.zzgf r8 = r7.zzd
            com.google.android.recaptcha.internal.zzbp r1 = new com.google.android.recaptcha.internal.zzbp
            r1.<init>()
            r8.zza = r1
            java.lang.String r8 = r7.zzf     // Catch:{ Exception -> 0x0072 }
            com.google.android.recaptcha.internal.zzkj r1 = com.google.android.recaptcha.internal.zzkj.zzh()     // Catch:{ Exception -> 0x0072 }
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8     // Catch:{ Exception -> 0x0072 }
            byte[] r8 = r1.zzj(r8)     // Catch:{ Exception -> 0x0072 }
            com.google.android.recaptcha.internal.zzud r8 = com.google.android.recaptcha.internal.zzud.zzi(r8)     // Catch:{ Exception -> 0x0072 }
            r8.zzf()     // Catch:{ Exception -> 0x0072 }
            com.google.android.recaptcha.internal.zzfv r1 = r7.zze     // Catch:{ Exception -> 0x0072 }
            com.google.android.recaptcha.internal.zzhz r1 = r1.zzc     // Catch:{ Exception -> 0x0072 }
            com.google.android.recaptcha.internal.zzub r1 = r1.zza(r8)     // Catch:{ Exception -> 0x0072 }
            com.google.android.recaptcha.internal.zzjj r3 = com.google.android.recaptcha.internal.zzjj.zzb()     // Catch:{ Exception -> 0x0072 }
            com.google.android.recaptcha.internal.zzfv r4 = r7.zze     // Catch:{ Exception -> 0x0072 }
            java.util.List r1 = r1.zzi()     // Catch:{ Exception -> 0x0072 }
            com.google.android.recaptcha.internal.zzgf r5 = r7.zzd     // Catch:{ Exception -> 0x0072 }
            r6 = r7
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6     // Catch:{ Exception -> 0x0072 }
            r7.zza = r8     // Catch:{ Exception -> 0x0072 }
            r7.zzb = r3     // Catch:{ Exception -> 0x0072 }
            r7.zzc = r2     // Catch:{ Exception -> 0x0072 }
            java.lang.Object r1 = r4.zzg(r1, r5, r6)     // Catch:{ Exception -> 0x0072 }
            if (r1 == r0) goto L_0x0088
            r2 = r8
            r1 = r3
        L_0x0062:
            r1.zzf()     // Catch:{ Exception -> 0x0072 }
            java.util.concurrent.TimeUnit r8 = java.util.concurrent.TimeUnit.MICROSECONDS     // Catch:{ Exception -> 0x0072 }
            long r3 = r1.zza(r8)     // Catch:{ Exception -> 0x0072 }
            kotlin.coroutines.jvm.internal.Boxing.boxLong(r3)     // Catch:{ Exception -> 0x0072 }
            r2.zzf()     // Catch:{ Exception -> 0x0072 }
            goto L_0x0089
        L_0x0072:
            r8 = move-exception
            com.google.android.recaptcha.internal.zzfv r1 = r7.zze
            com.google.android.recaptcha.internal.zzgf r2 = r7.zzd
            r3 = r7
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r4 = 0
            r7.zza = r4
            r7.zzb = r4
            r4 = 2
            r7.zzc = r4
            java.lang.Object r8 = r1.zzh(r8, r2, r3)
            if (r8 != r0) goto L_0x0089
        L_0x0088:
            return r0
        L_0x0089:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzfu.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}

package com.google.android.recaptcha.internal;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzan extends SuspendLambda implements Function2 {
    long zza;
    boolean zzb;
    int zzc;
    final /* synthetic */ zzap zzd;
    final /* synthetic */ zzep zze;
    final /* synthetic */ Ref.ObjectRef zzf;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzan(zzap zzap, zzep zzep, Ref.ObjectRef objectRef, Continuation continuation) {
        super(2, continuation);
        this.zzd = zzap;
        this.zze = zzep;
        this.zzf = objectRef;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new zzan(this.zzd, this.zze, this.zzf, continuation);
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((zzan) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005d, code lost:
        r6 = ((com.google.android.play.core.integrity.StandardIntegrityException) r9).getErrorCode();
     */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x002a A[SYNTHETIC, Splitter:B:12:0x002a] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0090  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.zzc
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x0021
            if (r1 == r3) goto L_0x0017
            boolean r1 = r8.zzb
            long r4 = r8.zza
            kotlin.ResultKt.throwOnFailure(r9)
            r9 = r1
            r1 = r8
            goto L_0x008d
        L_0x0017:
            long r4 = r8.zza
            kotlin.ResultKt.throwOnFailure(r9)     // Catch:{ Exception -> 0x001e }
            r1 = r8
            goto L_0x0039
        L_0x001e:
            r9 = move-exception
            r1 = r8
            goto L_0x0055
        L_0x0021:
            kotlin.ResultKt.throwOnFailure(r9)
            r4 = 1000(0x3e8, double:4.94E-321)
            r1 = r8
            r9 = r3
        L_0x0028:
            if (r9 == 0) goto L_0x0091
            com.google.android.recaptcha.internal.zzap r9 = r1.zzd     // Catch:{ Exception -> 0x0052 }
            r6 = r1
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6     // Catch:{ Exception -> 0x0052 }
            r1.zza = r4     // Catch:{ Exception -> 0x0052 }
            r1.zzc = r3     // Catch:{ Exception -> 0x0052 }
            java.lang.Object r9 = r9.zzi(r6)     // Catch:{ Exception -> 0x0052 }
            if (r9 == r0) goto L_0x0054
        L_0x0039:
            com.google.android.play.core.integrity.StandardIntegrityManager$StandardIntegrityTokenProvider r9 = (com.google.android.play.core.integrity.StandardIntegrityManager.StandardIntegrityTokenProvider) r9     // Catch:{ Exception -> 0x0052 }
            com.google.android.recaptcha.internal.zzap r6 = r1.zzd     // Catch:{ Exception -> 0x0052 }
            kotlinx.coroutines.CompletableDeferred r6 = r6.zzf()     // Catch:{ Exception -> 0x0052 }
            r6.complete(r9)     // Catch:{ Exception -> 0x0052 }
            com.google.android.recaptcha.internal.zzap r9 = r1.zzd     // Catch:{ Exception -> 0x0052 }
            com.google.android.recaptcha.internal.zzaq r6 = com.google.android.recaptcha.internal.zzaq.zzc     // Catch:{ Exception -> 0x0052 }
            r9.zze = r6     // Catch:{ Exception -> 0x0052 }
            com.google.android.recaptcha.internal.zzep r9 = r1.zze     // Catch:{ Exception -> 0x0052 }
            r9.zza()     // Catch:{ Exception -> 0x0052 }
            r9 = r2
            goto L_0x0028
        L_0x0052:
            r9 = move-exception
            goto L_0x0055
        L_0x0054:
            return r0
        L_0x0055:
            kotlin.jvm.internal.Ref$ObjectRef r6 = r1.zzf
            r6.element = r9
            boolean r6 = r9 instanceof com.google.android.play.core.integrity.StandardIntegrityException
            if (r6 == 0) goto L_0x0079
            r6 = r9
            com.google.android.play.core.integrity.StandardIntegrityException r6 = (com.google.android.play.core.integrity.StandardIntegrityException) r6
            int r6 = r6.getErrorCode()
            r7 = -100
            if (r6 == r7) goto L_0x0077
            r7 = -18
            if (r6 == r7) goto L_0x0077
            r7 = -12
            if (r6 == r7) goto L_0x0077
            r7 = -8
            if (r6 == r7) goto L_0x0077
            r7 = -3
            if (r6 == r7) goto L_0x0077
            goto L_0x0079
        L_0x0077:
            r6 = r3
            goto L_0x007a
        L_0x0079:
            r6 = r2
        L_0x007a:
            if (r6 == 0) goto L_0x0090
            r9 = r1
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
            r1.zza = r4
            r1.zzb = r3
            r7 = 2
            r1.zzc = r7
            java.lang.Object r9 = kotlinx.coroutines.DelayKt.delay(r4, r9)
            if (r9 == r0) goto L_0x008f
            r9 = r6
        L_0x008d:
            long r4 = r4 + r4
            goto L_0x0028
        L_0x008f:
            return r0
        L_0x0090:
            throw r9
        L_0x0091:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzan.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}

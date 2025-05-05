package com.google.android.recaptcha.internal;

import android.content.Context;
import com.google.android.play.core.integrity.StandardIntegrityManager;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzap {
    public CompletableDeferred zza;
    /* access modifiers changed from: private */
    public final CoroutineScope zzb;
    private final zzem zzc;
    private final StandardIntegrityManager zzd;
    /* access modifiers changed from: private */
    public zzaq zze = zzaq.zza;
    private long zzf;
    private final Mutex zzg = MutexKt.Mutex$default(false, 1, (Object) null);
    private boolean zzh;

    public zzap(Context context, CoroutineScope coroutineScope, zzem zzem, StandardIntegrityManager standardIntegrityManager, long j) {
        this.zzb = coroutineScope;
        this.zzc = zzem;
        this.zzd = standardIntegrityManager;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object zzi(kotlin.coroutines.Continuation r7) throws java.lang.Exception {
        /*
            r6 = this;
            boolean r0 = r7 instanceof com.google.android.recaptcha.internal.zzai
            if (r0 == 0) goto L_0x0013
            r0 = r7
            com.google.android.recaptcha.internal.zzai r0 = (com.google.android.recaptcha.internal.zzai) r0
            int r1 = r0.zzc
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.zzc = r1
            goto L_0x0018
        L_0x0013:
            com.google.android.recaptcha.internal.zzai r0 = new com.google.android.recaptcha.internal.zzai
            r0.<init>(r6, r7)
        L_0x0018:
            java.lang.Object r7 = r0.zza
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.zzc
            r3 = 1
            if (r2 == 0) goto L_0x0031
            if (r2 != r3) goto L_0x0029
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0055
        L_0x0029:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L_0x0031:
            kotlin.ResultKt.throwOnFailure(r7)
            com.google.android.play.core.integrity.StandardIntegrityManager$PrepareIntegrityTokenRequest$Builder r7 = com.google.android.play.core.integrity.StandardIntegrityManager.PrepareIntegrityTokenRequest.builder()
            long r4 = r6.zzf
            com.google.android.play.core.integrity.StandardIntegrityManager$PrepareIntegrityTokenRequest$Builder r7 = r7.setCloudProjectNumber(r4)
            com.google.android.play.core.integrity.StandardIntegrityManager$PrepareIntegrityTokenRequest r7 = r7.build()
            com.google.android.play.core.integrity.StandardIntegrityManager r2 = r6.zzd
            com.google.android.gms.tasks.Task r7 = r2.prepareIntegrityToken(r7)
            kotlinx.coroutines.Deferred r7 = com.google.android.recaptcha.internal.zzbz.zza(r7)
            r0.zzc = r3
            java.lang.Object r7 = r7.await(r0)
            if (r7 != r1) goto L_0x0055
            return r1
        L_0x0055:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzap.zzi(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004c, code lost:
        if (r7 != r1) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x006d, code lost:
        if (r7 != r1) goto L_0x0070;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0077, code lost:
        return r1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object zzj(java.lang.String r6, kotlin.coroutines.Continuation r7) throws java.lang.Exception {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.google.android.recaptcha.internal.zzaj
            if (r0 == 0) goto L_0x0013
            r0 = r7
            com.google.android.recaptcha.internal.zzaj r0 = (com.google.android.recaptcha.internal.zzaj) r0
            int r1 = r0.zzc
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.zzc = r1
            goto L_0x0018
        L_0x0013:
            com.google.android.recaptcha.internal.zzaj r0 = new com.google.android.recaptcha.internal.zzaj
            r0.<init>(r5, r7)
        L_0x0018:
            java.lang.Object r7 = r0.zza
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.zzc
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x003d
            if (r2 == r4) goto L_0x0034
            if (r2 != r3) goto L_0x002c
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0070
        L_0x002c:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0034:
            java.lang.String r6 = r0.zzd
            r2 = r6
            java.lang.String r2 = (java.lang.String) r2
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x004e
        L_0x003d:
            kotlin.ResultKt.throwOnFailure(r7)
            kotlinx.coroutines.CompletableDeferred r7 = r5.zzf()
            r0.zzd = r6
            r0.zzc = r4
            java.lang.Object r7 = r7.await(r0)
            if (r7 == r1) goto L_0x0077
        L_0x004e:
            com.google.android.play.core.integrity.StandardIntegrityManager$StandardIntegrityTokenProvider r7 = (com.google.android.play.core.integrity.StandardIntegrityManager.StandardIntegrityTokenProvider) r7
            com.google.android.play.core.integrity.StandardIntegrityManager$StandardIntegrityTokenRequest$Builder r2 = com.google.android.play.core.integrity.StandardIntegrityManager.StandardIntegrityTokenRequest.builder()
            com.google.android.play.core.integrity.StandardIntegrityManager$StandardIntegrityTokenRequest$Builder r6 = r2.setRequestHash(r6)
            com.google.android.play.core.integrity.StandardIntegrityManager$StandardIntegrityTokenRequest r6 = r6.build()
            com.google.android.gms.tasks.Task r6 = r7.request(r6)
            kotlinx.coroutines.Deferred r6 = com.google.android.recaptcha.internal.zzbz.zza(r6)
            r7 = 0
            r0.zzd = r7
            r0.zzc = r3
            java.lang.Object r7 = r6.await(r0)
            if (r7 != r1) goto L_0x0070
            goto L_0x0077
        L_0x0070:
            com.google.android.play.core.integrity.StandardIntegrityManager$StandardIntegrityToken r7 = (com.google.android.play.core.integrity.StandardIntegrityManager.StandardIntegrityToken) r7
            java.lang.String r6 = r7.token()
            return r6
        L_0x0077:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzap.zzj(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0085 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object zzc(java.lang.String r7, kotlin.coroutines.Continuation r8) throws java.lang.Exception {
        /*
            r6 = this;
            boolean r0 = r8 instanceof com.google.android.recaptcha.internal.zzah
            if (r0 == 0) goto L_0x0013
            r0 = r8
            com.google.android.recaptcha.internal.zzah r0 = (com.google.android.recaptcha.internal.zzah) r0
            int r1 = r0.zzc
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.zzc = r1
            goto L_0x0018
        L_0x0013:
            com.google.android.recaptcha.internal.zzah r0 = new com.google.android.recaptcha.internal.zzah
            r0.<init>(r6, r8)
        L_0x0018:
            java.lang.Object r8 = r0.zza
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.zzc
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0053
            if (r2 == r5) goto L_0x0045
            if (r2 == r4) goto L_0x0037
            if (r2 != r3) goto L_0x002f
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0082
        L_0x002f:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0037:
            java.lang.String r7 = r0.zze
            r2 = r7
            java.lang.String r2 = (java.lang.String) r2
            com.google.android.recaptcha.internal.zzap r2 = r0.zzd
            r4 = r2
            com.google.android.recaptcha.internal.zzap r4 = (com.google.android.recaptcha.internal.zzap) r4
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0075
        L_0x0045:
            java.lang.String r7 = r0.zze
            r2 = r7
            java.lang.String r2 = (java.lang.String) r2
            com.google.android.recaptcha.internal.zzap r2 = r0.zzd
            r5 = r2
            com.google.android.recaptcha.internal.zzap r5 = (com.google.android.recaptcha.internal.zzap) r5
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ Exception -> 0x0068 }
            goto L_0x0064
        L_0x0053:
            kotlin.ResultKt.throwOnFailure(r8)
            r0.zzd = r6     // Catch:{ Exception -> 0x0067 }
            r0.zze = r7     // Catch:{ Exception -> 0x0067 }
            r0.zzc = r5     // Catch:{ Exception -> 0x0067 }
            java.lang.Object r8 = r6.zzj(r7, r0)     // Catch:{ Exception -> 0x0067 }
            if (r8 != r1) goto L_0x0063
            return r1
        L_0x0063:
            r2 = r6
        L_0x0064:
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ Exception -> 0x0068 }
            goto L_0x0084
        L_0x0067:
            r2 = r6
        L_0x0068:
            r0.zzd = r2
            r0.zze = r7
            r0.zzc = r4
            java.lang.Object r8 = r2.zze(r0)
            if (r8 != r1) goto L_0x0075
            return r1
        L_0x0075:
            r8 = 0
            r0.zzd = r8
            r0.zze = r8
            r0.zzc = r3
            java.lang.Object r8 = r2.zzj(r7, r0)
            if (r8 == r1) goto L_0x0085
        L_0x0082:
            java.lang.String r8 = (java.lang.String) r8
        L_0x0084:
            return r8
        L_0x0085:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzap.zzc(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Object zzd(long j, Continuation continuation) {
        this.zzf = j;
        return Unit.INSTANCE;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00b9, code lost:
        if (kotlin.Unit.INSTANCE == r1) goto L_0x00c4;
     */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x005f A[Catch:{ all -> 0x00bf }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0065 A[SYNTHETIC, Splitter:B:23:0x0065] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object zze(kotlin.coroutines.Continuation r14) {
        /*
            r13 = this;
            boolean r0 = r14 instanceof com.google.android.recaptcha.internal.zzam
            if (r0 == 0) goto L_0x0013
            r0 = r14
            com.google.android.recaptcha.internal.zzam r0 = (com.google.android.recaptcha.internal.zzam) r0
            int r1 = r0.zzd
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.zzd = r1
            goto L_0x0018
        L_0x0013:
            com.google.android.recaptcha.internal.zzam r0 = new com.google.android.recaptcha.internal.zzam
            r0.<init>(r13, r14)
        L_0x0018:
            java.lang.Object r14 = r0.zzb
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.zzd
            r3 = 2
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L_0x0043
            if (r2 == r4) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x00bc
        L_0x002e:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r0)
            throw r14
        L_0x0036:
            java.lang.Object r2 = r0.zza
            kotlinx.coroutines.sync.Mutex r2 = (kotlinx.coroutines.sync.Mutex) r2
            com.google.android.recaptcha.internal.zzap r6 = r0.zze
            r7 = r6
            com.google.android.recaptcha.internal.zzap r7 = (com.google.android.recaptcha.internal.zzap) r7
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x0055
        L_0x0043:
            kotlin.ResultKt.throwOnFailure(r14)
            kotlinx.coroutines.sync.Mutex r2 = r13.zzg
            r0.zze = r13
            r0.zza = r2
            r0.zzd = r4
            java.lang.Object r14 = r2.lock(r5, r0)
            if (r14 == r1) goto L_0x00c4
            r6 = r13
        L_0x0055:
            com.google.android.recaptcha.internal.zzaq r14 = r6.zze     // Catch:{ all -> 0x00bf }
            com.google.android.recaptcha.internal.zzaq r7 = com.google.android.recaptcha.internal.zzaq.zza     // Catch:{ all -> 0x00bf }
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r7)     // Catch:{ all -> 0x00bf }
            if (r14 != 0) goto L_0x0065
            kotlin.Unit r14 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00bf }
            r2.unlock(r5)
            return r14
        L_0x0065:
            com.google.android.recaptcha.internal.zzaq r14 = com.google.android.recaptcha.internal.zzaq.zzb     // Catch:{ all -> 0x00bf }
            r6.zze = r14     // Catch:{ all -> 0x00bf }
            kotlin.Unit r14 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00bf }
            r2.unlock(r5)
            com.google.android.recaptcha.internal.zzem r14 = r6.zzc
            java.lang.String r2 = r14.zzd()
            r14.zzc(r2)
            r14.zzb(r3)
            r2 = 38
            com.google.android.recaptcha.internal.zzep r14 = r14.zzf(r2)
            kotlinx.coroutines.CompletableDeferred r2 = kotlinx.coroutines.CompletableDeferredKt.CompletableDeferred$default(r5, r4, r5)
            r6.zza = r2
            kotlinx.coroutines.CoroutineScope r7 = r6.zzb
            r8 = 0
            r9 = 0
            com.google.android.recaptcha.internal.zzao r2 = new com.google.android.recaptcha.internal.zzao
            r2.<init>(r6, r14, r5)
            r10 = r2
            kotlin.jvm.functions.Function2 r10 = (kotlin.jvm.functions.Function2) r10
            r11 = 3
            r12 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r7, r8, r9, r10, r11, r12)
            r0.zze = r5
            r0.zza = r5
            r0.zzd = r3
            boolean r14 = r6.zzh
            if (r14 != 0) goto L_0x00b7
            java.util.Timer r7 = new java.util.Timer
            r7.<init>()
            com.google.android.recaptcha.internal.zzak r14 = new com.google.android.recaptcha.internal.zzak
            r14.<init>(r6)
            r8 = r14
            java.util.TimerTask r8 = (java.util.TimerTask) r8
            r11 = 28800000(0x1b77400, double:1.42290906E-316)
            r9 = r11
            r7.schedule(r8, r9, r11)
            r6.zzh = r4
        L_0x00b7:
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            if (r14 != r1) goto L_0x00bc
            goto L_0x00c4
        L_0x00bc:
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        L_0x00bf:
            r14 = move-exception
            r2.unlock(r5)
            throw r14
        L_0x00c4:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzap.zze(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final CompletableDeferred zzf() {
        CompletableDeferred completableDeferred = this.zza;
        if (completableDeferred != null) {
            return completableDeferred;
        }
        return null;
    }
}

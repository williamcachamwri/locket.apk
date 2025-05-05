package com.google.android.recaptcha.internal;

import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzcd {
    private Object zza;
    private final Mutex zzb = MutexKt.Mutex$default(false, 1, (Object) null);

    public zzcd(Object obj) {
        this.zza = obj;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object zza(java.lang.Object r6, kotlin.coroutines.Continuation r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.google.android.recaptcha.internal.zzca
            if (r0 == 0) goto L_0x0013
            r0 = r7
            com.google.android.recaptcha.internal.zzca r0 = (com.google.android.recaptcha.internal.zzca) r0
            int r1 = r0.zzd
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.zzd = r1
            goto L_0x0018
        L_0x0013:
            com.google.android.recaptcha.internal.zzca r0 = new com.google.android.recaptcha.internal.zzca
            r0.<init>(r5, r7)
        L_0x0018:
            java.lang.Object r7 = r0.zzb
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.zzd
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L_0x003f
            if (r2 != r3) goto L_0x0037
            java.lang.Object r6 = r0.zza
            kotlinx.coroutines.sync.Mutex r6 = (kotlinx.coroutines.sync.Mutex) r6
            com.google.android.recaptcha.internal.zzjg r1 = r0.zzf
            com.google.android.recaptcha.internal.zzcd r0 = r0.zze
            r2 = r0
            com.google.android.recaptcha.internal.zzcd r2 = (com.google.android.recaptcha.internal.zzcd) r2
            kotlin.ResultKt.throwOnFailure(r7)
            r7 = r6
            r6 = r1
            goto L_0x0056
        L_0x0037:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x003f:
            kotlin.ResultKt.throwOnFailure(r7)
            kotlinx.coroutines.sync.Mutex r7 = r5.zzb
            r0.zze = r5
            r2 = r6
            com.google.android.recaptcha.internal.zzjg r2 = (com.google.android.recaptcha.internal.zzjg) r2
            r0.zzf = r2
            r0.zza = r7
            r0.zzd = r3
            java.lang.Object r0 = r7.lock(r4, r0)
            if (r0 == r1) goto L_0x0069
            r0 = r5
        L_0x0056:
            java.lang.Object r0 = r0.zza     // Catch:{ all -> 0x0064 }
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r6)     // Catch:{ all -> 0x0064 }
            java.lang.Boolean r6 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r6)     // Catch:{ all -> 0x0064 }
            r7.unlock(r4)
            return r6
        L_0x0064:
            r6 = move-exception
            r7.unlock(r4)
            throw r6
        L_0x0069:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzcd.zza(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object zzb(java.lang.Object[] r6, kotlin.coroutines.Continuation r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.google.android.recaptcha.internal.zzcb
            if (r0 == 0) goto L_0x0013
            r0 = r7
            com.google.android.recaptcha.internal.zzcb r0 = (com.google.android.recaptcha.internal.zzcb) r0
            int r1 = r0.zzd
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.zzd = r1
            goto L_0x0018
        L_0x0013:
            com.google.android.recaptcha.internal.zzcb r0 = new com.google.android.recaptcha.internal.zzcb
            r0.<init>(r5, r7)
        L_0x0018:
            java.lang.Object r7 = r0.zzb
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.zzd
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L_0x0041
            if (r2 != r3) goto L_0x0039
            java.lang.Object r6 = r0.zza
            kotlinx.coroutines.sync.Mutex r6 = (kotlinx.coroutines.sync.Mutex) r6
            com.google.android.recaptcha.internal.zzjg[] r1 = r0.zzf
            java.lang.Object[] r1 = (java.lang.Object[]) r1
            com.google.android.recaptcha.internal.zzcd r0 = r0.zze
            r2 = r0
            com.google.android.recaptcha.internal.zzcd r2 = (com.google.android.recaptcha.internal.zzcd) r2
            kotlin.ResultKt.throwOnFailure(r7)
            r7 = r6
            r6 = r1
            goto L_0x0058
        L_0x0039:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0041:
            kotlin.ResultKt.throwOnFailure(r7)
            kotlinx.coroutines.sync.Mutex r7 = r5.zzb
            r0.zze = r5
            r2 = r6
            com.google.android.recaptcha.internal.zzjg[] r2 = (com.google.android.recaptcha.internal.zzjg[]) r2
            r0.zzf = r2
            r0.zza = r7
            r0.zzd = r3
            java.lang.Object r0 = r7.lock(r4, r0)
            if (r0 == r1) goto L_0x006b
            r0 = r5
        L_0x0058:
            java.lang.Object r0 = r0.zza     // Catch:{ all -> 0x0066 }
            boolean r6 = kotlin.collections.ArraysKt.contains((T[]) r6, r0)     // Catch:{ all -> 0x0066 }
            java.lang.Boolean r6 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r6)     // Catch:{ all -> 0x0066 }
            r7.unlock(r4)
            return r6
        L_0x0066:
            r6 = move-exception
            r7.unlock(r4)
            throw r6
        L_0x006b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzcd.zzb(java.lang.Object[], kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object zzc(java.lang.Object r6, kotlin.coroutines.Continuation r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.google.android.recaptcha.internal.zzcc
            if (r0 == 0) goto L_0x0013
            r0 = r7
            com.google.android.recaptcha.internal.zzcc r0 = (com.google.android.recaptcha.internal.zzcc) r0
            int r1 = r0.zzd
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.zzd = r1
            goto L_0x0018
        L_0x0013:
            com.google.android.recaptcha.internal.zzcc r0 = new com.google.android.recaptcha.internal.zzcc
            r0.<init>(r5, r7)
        L_0x0018:
            java.lang.Object r7 = r0.zzb
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.zzd
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L_0x003f
            if (r2 != r3) goto L_0x0037
            java.lang.Object r6 = r0.zza
            kotlinx.coroutines.sync.Mutex r6 = (kotlinx.coroutines.sync.Mutex) r6
            com.google.android.recaptcha.internal.zzjg r1 = r0.zzf
            com.google.android.recaptcha.internal.zzcd r0 = r0.zze
            r2 = r0
            com.google.android.recaptcha.internal.zzcd r2 = (com.google.android.recaptcha.internal.zzcd) r2
            kotlin.ResultKt.throwOnFailure(r7)
            r7 = r6
            r6 = r1
            goto L_0x0056
        L_0x0037:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x003f:
            kotlin.ResultKt.throwOnFailure(r7)
            kotlinx.coroutines.sync.Mutex r7 = r5.zzb
            r0.zze = r5
            r2 = r6
            com.google.android.recaptcha.internal.zzjg r2 = (com.google.android.recaptcha.internal.zzjg) r2
            r0.zzf = r2
            r0.zza = r7
            r0.zzd = r3
            java.lang.Object r0 = r7.lock(r4, r0)
            if (r0 == r1) goto L_0x0065
            r0 = r5
        L_0x0056:
            r0.zza = r6     // Catch:{ all -> 0x0060 }
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0060 }
            r7.unlock(r4)
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L_0x0060:
            r6 = move-exception
            r7.unlock(r4)
            throw r6
        L_0x0065:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzcd.zzc(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}

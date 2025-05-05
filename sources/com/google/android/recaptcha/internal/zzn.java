package com.google.android.recaptcha.internal;

import android.content.Context;
import com.google.android.gms.recaptchabase.InitRequest;
import com.google.android.gms.recaptchabase.RecaptchaBase;
import com.google.android.gms.recaptchabase.RecaptchaBaseClient;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Deferred;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzn extends zze {
    private final Context zza;
    private final zzem zzb;
    private final zzbu zzc;
    private final RecaptchaBaseClient zzd = null;
    private String zze;
    private Deferred zzf;

    public zzn(Context context, zzem zzem, zzbu zzbu, RecaptchaBaseClient recaptchaBaseClient) {
        this.zza = context;
        this.zzb = zzem;
        this.zzc = zzbu;
    }

    /* access modifiers changed from: protected */
    public final zzep zza(String str) {
        zzem zzem = this.zzb;
        zzem.zzc(str);
        return zzem.zzf(40);
    }

    /* access modifiers changed from: protected */
    public final zzep zzb() {
        zzem zzem = this.zzb;
        zzem.zzc(zzem.zzd());
        return zzem.zzf(39);
    }

    /* access modifiers changed from: protected */
    public final Object zzd(String str, Continuation continuation) {
        zzsj zzf2 = zzsk.zzf();
        zzf2.zze(str);
        return zzf2.zzk();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x009a A[Catch:{ Exception -> 0x00d0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00a3  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object zzf(java.lang.String r7, kotlin.coroutines.Continuation r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof com.google.android.recaptcha.internal.zzm
            if (r0 == 0) goto L_0x0013
            r0 = r8
            com.google.android.recaptcha.internal.zzm r0 = (com.google.android.recaptcha.internal.zzm) r0
            int r1 = r0.zzc
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.zzc = r1
            goto L_0x0018
        L_0x0013:
            com.google.android.recaptcha.internal.zzm r0 = new com.google.android.recaptcha.internal.zzm
            r0.<init>(r6, r8)
        L_0x0018:
            java.lang.Object r8 = r0.zza
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.zzc
            r3 = 2
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L_0x004d
            if (r2 == r4) goto L_0x003f
            if (r2 != r3) goto L_0x0037
            java.lang.String r7 = r0.zze
            r1 = r7
            java.lang.String r1 = (java.lang.String) r1
            com.google.android.recaptcha.internal.zzn r0 = r0.zzd
            r1 = r0
            com.google.android.recaptcha.internal.zzn r1 = (com.google.android.recaptcha.internal.zzn) r1
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ Exception -> 0x00d0 }
            goto L_0x009b
        L_0x0037:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x003f:
            java.lang.String r7 = r0.zze
            r2 = r7
            java.lang.String r2 = (java.lang.String) r2
            com.google.android.recaptcha.internal.zzn r2 = r0.zzd
            r4 = r2
            com.google.android.recaptcha.internal.zzn r4 = (com.google.android.recaptcha.internal.zzn) r4
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ Exception -> 0x00e6 }
            goto L_0x0063
        L_0x004d:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.Deferred r8 = r6.zzf     // Catch:{ Exception -> 0x00e6 }
            if (r8 != 0) goto L_0x0055
            r8 = r5
        L_0x0055:
            r0.zzd = r6     // Catch:{ Exception -> 0x00e6 }
            r0.zze = r7     // Catch:{ Exception -> 0x00e6 }
            r0.zzc = r4     // Catch:{ Exception -> 0x00e6 }
            java.lang.Object r8 = r8.await(r0)     // Catch:{ Exception -> 0x00e6 }
            if (r8 != r1) goto L_0x0062
            goto L_0x00cf
        L_0x0062:
            r2 = r6
        L_0x0063:
            com.google.android.gms.recaptchabase.InitResult r8 = (com.google.android.gms.recaptchabase.InitResult) r8     // Catch:{ Exception -> 0x00e6 }
            com.google.android.gms.recaptchabase.ExecuteRequest$Builder r8 = new com.google.android.gms.recaptchabase.ExecuteRequest$Builder
            r8.<init>()
            java.lang.String r4 = r2.zze
            if (r4 != 0) goto L_0x006f
            r4 = r5
        L_0x006f:
            com.google.android.gms.recaptchabase.ExecuteRequest$Builder r8 = r8.setNonce(r4)
            com.google.android.gms.recaptchabase.ExecuteRequest$Builder r8 = r8.setExecuteId(r7)
            com.google.android.gms.recaptchabase.ExecuteRequest r8 = r8.build()
            com.google.android.gms.recaptchabase.RecaptchaBaseClient r4 = r2.zzd
            android.content.Context r4 = r2.zza
            com.google.android.gms.recaptchabase.RecaptchaBaseClient r4 = com.google.android.gms.recaptchabase.RecaptchaBase.getClient((android.content.Context) r4)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8)     // Catch:{ Exception -> 0x00d0 }
            com.google.android.gms.tasks.Task r8 = r4.execute(r8)     // Catch:{ Exception -> 0x00d0 }
            kotlinx.coroutines.Deferred r8 = com.google.android.recaptcha.internal.zzbz.zza(r8)     // Catch:{ Exception -> 0x00d0 }
            r0.zzd = r2     // Catch:{ Exception -> 0x00d0 }
            r0.zze = r7     // Catch:{ Exception -> 0x00d0 }
            r0.zzc = r3     // Catch:{ Exception -> 0x00d0 }
            java.lang.Object r8 = r8.await(r0)     // Catch:{ Exception -> 0x00d0 }
            if (r8 == r1) goto L_0x00cf
            r0 = r2
        L_0x009b:
            com.google.android.gms.recaptchabase.ExecuteResult r8 = (com.google.android.gms.recaptchabase.ExecuteResult) r8     // Catch:{ Exception -> 0x00d0 }
            java.lang.String r8 = r8.getPayload()
            if (r8 == 0) goto L_0x00c7
            java.lang.String r0 = r0.zze
            com.google.android.recaptcha.internal.zzsj r0 = com.google.android.recaptcha.internal.zzsk.zzf()
            r0.zze(r7)
            com.google.android.recaptcha.internal.zzrz r7 = com.google.android.recaptcha.internal.zzsa.zzf()
            r7.zze(r8)
            com.google.android.recaptcha.internal.zznf r7 = r7.zzk()
            com.google.android.recaptcha.internal.zzsa r7 = (com.google.android.recaptcha.internal.zzsa) r7
            r0.zzf(r7)
            com.google.android.recaptcha.internal.zznf r7 = r0.zzk()
            com.google.android.recaptcha.internal.zzsk r7 = (com.google.android.recaptcha.internal.zzsk) r7
            java.lang.Object r7 = kotlin.Result.m2444constructorimpl(r7)
            return r7
        L_0x00c7:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "Required value was null."
            r7.<init>(r8)
            throw r7
        L_0x00cf:
            return r1
        L_0x00d0:
            kotlin.Result$Companion r7 = kotlin.Result.Companion
            com.google.android.recaptcha.internal.zzbf r7 = new com.google.android.recaptcha.internal.zzbf
            com.google.android.recaptcha.internal.zzbd r8 = com.google.android.recaptcha.internal.zzbd.zzb
            com.google.android.recaptcha.internal.zzbc r0 = com.google.android.recaptcha.internal.zzbc.zzau
            r7.<init>(r8, r0, r5)
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r7 = kotlin.ResultKt.createFailure(r7)
            java.lang.Object r7 = kotlin.Result.m2444constructorimpl(r7)
            return r7
        L_0x00e6:
            kotlin.Result$Companion r7 = kotlin.Result.Companion
            com.google.android.recaptcha.internal.zzbf r7 = new com.google.android.recaptcha.internal.zzbf
            com.google.android.recaptcha.internal.zzbd r8 = com.google.android.recaptcha.internal.zzbd.zzb
            com.google.android.recaptcha.internal.zzbc r0 = com.google.android.recaptcha.internal.zzbc.zzat
            r7.<init>(r8, r0, r5)
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r7 = kotlin.ResultKt.createFailure(r7)
            java.lang.Object r7 = kotlin.Result.m2444constructorimpl(r7)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzn.zzf(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: protected */
    public final Object zzh(zzse zzse, Continuation continuation) {
        if (!this.zzc.zza(this.zza)) {
            Result.Companion companion = Result.Companion;
            return Result.m2444constructorimpl(ResultKt.createFailure(new zzbf(zzbd.zzb, zzbc.zzar, (String) null)));
        } else if (!zzse.zzR() || zzse.zzg().zzf().zzn()) {
            Result.Companion companion2 = Result.Companion;
            return Result.m2444constructorimpl(ResultKt.createFailure(new zzbf(zzbd.zzb, zzbc.zzaD, (String) null)));
        } else {
            this.zze = zzse.zzg().zzf().zzm();
            InitRequest build = new InitRequest.Builder().build();
            RecaptchaBaseClient client = RecaptchaBase.getClient(this.zza);
            Intrinsics.checkNotNull(build);
            this.zzf = zzbz.zza(client.init(build));
            Result.Companion companion3 = Result.Companion;
            return Result.m2444constructorimpl(Unit.INSTANCE);
        }
    }
}

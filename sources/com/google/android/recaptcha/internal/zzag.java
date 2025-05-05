package com.google.android.recaptcha.internal;

import android.content.Context;
import java.nio.charset.StandardCharsets;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzag implements zzaa {
    private final zzem zza;
    private final Context zzb;
    /* access modifiers changed from: private */
    public final zzap zzc;
    private boolean zzd = true;
    /* access modifiers changed from: private */
    public String zze = "";
    private final zzbu zzf;

    public zzag(zzem zzem, Context context, CoroutineScope coroutineScope, zzap zzap, zzbu zzbu) {
        this.zza = zzem;
        this.zzb = context;
        this.zzc = zzap;
        this.zzf = zzbu;
    }

    private static final String zzi(zzlg zzlg) {
        zzkj zzg = zzkj.zzg();
        byte[] zzo = zzlg.zzo();
        byte[] zzd2 = zzkb.zza().zza(zzg.zzi(zzo, 0, zzo.length), StandardCharsets.UTF_8).zzd();
        zzlg zzl = zzlg.zzl(zzd2, 0, zzd2.length);
        zzkj zzh = zzkj.zzh();
        byte[] zzo2 = zzl.zzo();
        return zzh.zzi(zzo2, 0, zzo2.length);
    }

    public final int zza() {
        return 2;
    }

    public final zzem zzb() {
        return this.zza;
    }

    public final Object zzc(String str, Continuation continuation) {
        return CoroutineScopeKt.coroutineScope(new zzae(this, str, (Continuation) null), continuation);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x008f, code lost:
        if (r12.zze(r0) != r1) goto L_0x0092;
     */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object zzd(com.google.android.recaptcha.internal.zzsg r11, kotlin.coroutines.Continuation r12) {
        /*
            r10 = this;
            boolean r0 = r12 instanceof com.google.android.recaptcha.internal.zzaf
            if (r0 == 0) goto L_0x0013
            r0 = r12
            com.google.android.recaptcha.internal.zzaf r0 = (com.google.android.recaptcha.internal.zzaf) r0
            int r1 = r0.zzd
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.zzd = r1
            goto L_0x0018
        L_0x0013:
            com.google.android.recaptcha.internal.zzaf r0 = new com.google.android.recaptcha.internal.zzaf
            r0.<init>(r10, r12)
        L_0x0018:
            java.lang.Object r12 = r0.zzb
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.zzd
            r3 = 0
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0046
            if (r2 == r5) goto L_0x0039
            if (r2 != r4) goto L_0x0031
            java.lang.Object r11 = r0.zza
            com.google.android.recaptcha.internal.zzep r11 = (com.google.android.recaptcha.internal.zzep) r11
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x0092
        L_0x0031:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x0039:
            com.google.android.recaptcha.internal.zzep r11 = r0.zze
            r2 = r11
            com.google.android.recaptcha.internal.zzep r2 = (com.google.android.recaptcha.internal.zzep) r2
            java.lang.Object r2 = r0.zza
            com.google.android.recaptcha.internal.zzag r2 = (com.google.android.recaptcha.internal.zzag) r2
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x0083
        L_0x0046:
            kotlin.ResultKt.throwOnFailure(r12)
            r12 = r10
            com.google.android.recaptcha.internal.zzaa r12 = (com.google.android.recaptcha.internal.zzaa) r12
            com.google.android.recaptcha.internal.zzep r12 = com.google.android.recaptcha.internal.zzab.zzc(r12)
            com.google.android.recaptcha.internal.zzbu r2 = r10.zzf
            android.content.Context r6 = r10.zzb
            boolean r2 = r2.zza(r6)
            if (r2 == 0) goto L_0x0099
            long r6 = r11.zzf()
            r8 = 0
            int r2 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r2 != 0) goto L_0x0065
            goto L_0x0099
        L_0x0065:
            com.google.android.recaptcha.internal.zzlg r2 = r11.zzg()
            java.lang.String r2 = zzi(r2)
            r10.zze = r2
            com.google.android.recaptcha.internal.zzap r2 = r10.zzc
            long r6 = r11.zzf()
            r0.zza = r10
            r0.zze = r12
            r0.zzd = r5
            java.lang.Object r11 = r2.zzd(r6, r0)
            if (r11 == r1) goto L_0x0098
            r2 = r10
            r11 = r12
        L_0x0083:
            com.google.android.recaptcha.internal.zzap r12 = r2.zzc
            r0.zza = r11
            r0.zze = r3
            r0.zzd = r4
            java.lang.Object r12 = r12.zze(r0)
            if (r12 != r1) goto L_0x0092
            goto L_0x0098
        L_0x0092:
            r11.zza()
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L_0x0098:
            return r1
        L_0x0099:
            r11 = 0
            r10.zzd = r11
            com.google.android.recaptcha.internal.zzbf r11 = new com.google.android.recaptcha.internal.zzbf
            com.google.android.recaptcha.internal.zzbd r0 = com.google.android.recaptcha.internal.zzbd.zzb
            com.google.android.recaptcha.internal.zzbc r1 = com.google.android.recaptcha.internal.zzbc.zzab
            r11.<init>(r0, r1, r3)
            r12.zzb(r11)
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzag.zzd(com.google.android.recaptcha.internal.zzsg, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void zze(zzst zzst) {
        this.zze = zzi(zzst.zzf());
    }

    public final boolean zzf() {
        return this.zzd;
    }
}

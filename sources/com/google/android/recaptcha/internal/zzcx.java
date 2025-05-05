package com.google.android.recaptcha.internal;

import android.app.Application;
import android.content.Context;
import androidx.core.content.ContextCompat;
import com.google.android.gms.common.api.ApiException;
import com.google.android.recaptcha.RecaptchaException;
import java.util.List;
import java.util.UUID;
import kotlin.LazyKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.TimeoutCancellationException;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzcx {
    private final Application zza;
    private final Mutex zzb = MutexKt.Mutex$default(false, 1, (Object) null);
    /* access modifiers changed from: private */
    public zzde zzc;
    private final String zzd = UUID.randomUUID().toString();
    private final zzl zze;
    private zzbk zzf;

    public zzcx(Application application) {
        this.zza = application;
        zzbk zzbk = new zzbk();
        zzbk zzbk2 = zzbk;
        this.zzf = zzbk;
        this.zze = new zzl((List) null, 1, (DefaultConstructorMarker) null);
        int i = zzax.zza;
        Class<zzbb> cls = zzbb.class;
        Class<zzfw> cls2 = zzfw.class;
        Class<zzbg> cls3 = zzbg.class;
        Class<zzjf> cls4 = zzjf.class;
        Class<zzbt> cls5 = zzbt.class;
        Class<zzez> cls6 = zzez.class;
        Class<zzfm> cls7 = zzfm.class;
        Context context = application;
        Class<zzbh> cls8 = zzbh.class;
        Class<zzfl> cls9 = zzfl.class;
        zzbo zzbo = new zzbo(context);
        Class<zzas> cls10 = zzas.class;
        Class<zzfa> cls11 = zzfa.class;
        Class<zzfh> cls12 = zzfh.class;
        zzay[] zzayArr = {new zzay(915034659, new zzbb((List) null, 1, (DefaultConstructorMarker) null)), new zzay(915034804, new zzfw()), new zzay(915034664, new zzbg()), new zzay(915034911, new zzjf()), new zzay(915034677, new zzbt("https://www.recaptcha.net/recaptcha/api3")), new zzay(915034776, new zzez((zzfo) null, 1, (DefaultConstructorMarker) null)), new zzay(915034794, new zzfm(true)), new zzay(Application.class.getName().hashCode(), application), new zzay(915034665, new zzbh(context)), new zzay(915034793, new zzfl()), new zzay(915034645, zzbo), new zzay(915034782, new zzfc()), new zzay(915034789, new zzfh())};
        for (int i2 = 0; i2 < 13; i2++) {
            zzay zzay = zzayArr[i2];
            if (!zzax.zzc.containsKey(Integer.valueOf(zzay.zza()))) {
                zzax.zzc.put(Integer.valueOf(zzay.zza()), zzay);
            }
        }
    }

    public static final /* synthetic */ zzde zza(zzcx zzcx, String str) {
        zzde zzde = zzcx.zzc;
        if (zzde == null) {
            return null;
        }
        if (Intrinsics.areEqual((Object) zzde.zzd(), (Object) str)) {
            return zzde;
        }
        throw new zzbf(zzbd.zzd, zzbc.zzam, (String) null);
    }

    public static final /* synthetic */ void zzc(zzcx zzcx, long j) {
        if (j < 5000) {
            throw new zzbf(zzbd.zzj, zzbc.zzI, (String) null);
        } else if (ContextCompat.checkSelfPermission(zzcx.zza, "android.permission.INTERNET") != 0) {
            throw new zzbf(zzbd.zzc, zzbc.zzao, (String) null);
        }
    }

    public static final /* synthetic */ zzcp zze(zzcx zzcx, String str, zzbk zzbk, zzcj zzcj, zzem zzem) {
        zzdv zzdv = new zzdv(str, zzbk, zzem, zzcx.zze);
        if (Intrinsics.areEqual((Object) zzcj, (Object) zzcj.zza)) {
            return new zzeh(zzdv);
        }
        return new zzee(zzdv, zzbk, zzem, new zzbq());
    }

    public static /* synthetic */ Object zzf(zzcx zzcx, String str, zzcp zzcp, zzbk zzbk, Continuation continuation, int i, Object obj) throws ApiException, RecaptchaException {
        zzcx zzcx2 = zzcx;
        return zzh(zzcx2, str, 0, (zzcp) null, zzcx2.zzf, zzcj.zzb, continuation, 2, (Object) null);
    }

    public static /* synthetic */ Object zzh(zzcx zzcx, String str, long j, zzcp zzcp, zzbk zzbk, zzcj zzcj, Continuation continuation, int i, Object obj) throws TimeoutCancellationException, ApiException, RecaptchaException {
        zzbk zzbk2;
        if ((i & 8) != 0) {
            zzbk2 = zzcx.zzf;
        } else {
            zzcx zzcx2 = zzcx;
            zzbk2 = zzbk;
        }
        return zzcx.zzg(str, (i & 2) != 0 ? 10000 : j, (zzcp) null, zzbk2, (i & 16) != 0 ? zzcj.zza : zzcj, continuation);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object zzj(java.lang.String r6, int r7, kotlin.jvm.functions.Function2 r8, kotlin.coroutines.Continuation r9) {
        /*
            r5 = this;
            boolean r0 = r9 instanceof com.google.android.recaptcha.internal.zzcw
            if (r0 == 0) goto L_0x0013
            r0 = r9
            com.google.android.recaptcha.internal.zzcw r0 = (com.google.android.recaptcha.internal.zzcw) r0
            int r1 = r0.zzc
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.zzc = r1
            goto L_0x0018
        L_0x0013:
            com.google.android.recaptcha.internal.zzcw r0 = new com.google.android.recaptcha.internal.zzcw
            r0.<init>(r5, r9)
        L_0x0018:
            java.lang.Object r9 = r0.zza
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.zzc
            r3 = 1
            if (r2 == 0) goto L_0x003a
            if (r2 != r3) goto L_0x0032
            com.google.android.recaptcha.internal.zzep r6 = r0.zzd
            r7 = r6
            com.google.android.recaptcha.internal.zzep r7 = (com.google.android.recaptcha.internal.zzep) r7
            kotlin.ResultKt.throwOnFailure(r9)     // Catch:{ zzbf -> 0x0030, Exception -> 0x002e }
            goto L_0x0053
        L_0x002e:
            r7 = move-exception
            goto L_0x005c
        L_0x0030:
            r7 = move-exception
            goto L_0x0075
        L_0x0032:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x003a:
            kotlin.ResultKt.throwOnFailure(r9)
            com.google.android.recaptcha.internal.zzbk r9 = r5.zzf
            com.google.android.recaptcha.internal.zzem r6 = r5.zzk(r6, r9, r7)
            r7 = 6
            com.google.android.recaptcha.internal.zzep r7 = r6.zzf(r7)
            r0.zzd = r7     // Catch:{ zzbf -> 0x0071, Exception -> 0x0058 }
            r0.zzc = r3     // Catch:{ zzbf -> 0x0071, Exception -> 0x0058 }
            java.lang.Object r9 = r8.invoke(r6, r0)     // Catch:{ zzbf -> 0x0071, Exception -> 0x0058 }
            if (r9 == r1) goto L_0x0057
            r6 = r7
        L_0x0053:
            r6.zza()     // Catch:{ zzbf -> 0x0030, Exception -> 0x002e }
            return r9
        L_0x0057:
            return r1
        L_0x0058:
            r6 = move-exception
            r4 = r7
            r7 = r6
            r6 = r4
        L_0x005c:
            com.google.android.recaptcha.internal.zzbf r8 = new com.google.android.recaptcha.internal.zzbf
            com.google.android.recaptcha.internal.zzbd r9 = com.google.android.recaptcha.internal.zzbd.zzb
            com.google.android.recaptcha.internal.zzbc r0 = com.google.android.recaptcha.internal.zzbc.zza
            java.lang.String r7 = r7.getMessage()
            r8.<init>(r9, r0, r7)
            r6.zzb(r8)
            com.google.android.recaptcha.RecaptchaException r6 = r8.zzc()
            throw r6
        L_0x0071:
            r6 = move-exception
            r4 = r7
            r7 = r6
            r6 = r4
        L_0x0075:
            r6.zzb(r7)
            com.google.android.recaptcha.RecaptchaException r6 = r7.zzc()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzcx.zzj(java.lang.String, int, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final zzem zzk(String str, zzbk zzbk, int i) {
        String uuid = UUID.randomUUID().toString();
        int i2 = zzax.zza;
        zzeu zzeu = new zzeu(this.zza, new zzew(((zzbt) LazyKt.lazy(zzct.zza).getValue()).zzc()), zzbk.zza());
        String str2 = str;
        zzem zzem = new zzem(str2, this.zzd, uuid, i, this.zza, zzeu, (DefaultConstructorMarker) null);
        zzem.zzc(uuid);
        return zzem;
    }

    public final zzbk zzd() {
        return this.zzf;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: kotlinx.coroutines.sync.Mutex} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x009f A[Catch:{ all -> 0x00e2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00a2 A[Catch:{ all -> 0x00e2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00db  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object zzg(java.lang.String r23, long r24, com.google.android.recaptcha.internal.zzcp r26, com.google.android.recaptcha.internal.zzbk r27, com.google.android.recaptcha.internal.zzcj r28, kotlin.coroutines.Continuation r29) throws kotlinx.coroutines.TimeoutCancellationException, com.google.android.gms.common.api.ApiException, com.google.android.recaptcha.RecaptchaException {
        /*
            r22 = this;
            r1 = r22
            r0 = r29
            r11 = 0
            boolean r2 = r0 instanceof com.google.android.recaptcha.internal.zzcu
            if (r2 == 0) goto L_0x0018
            r2 = r0
            com.google.android.recaptcha.internal.zzcu r2 = (com.google.android.recaptcha.internal.zzcu) r2
            int r3 = r2.zzg
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L_0x0018
            int r3 = r3 - r4
            r2.zzg = r3
            goto L_0x001d
        L_0x0018:
            com.google.android.recaptcha.internal.zzcu r2 = new com.google.android.recaptcha.internal.zzcu
            r2.<init>(r1, r0)
        L_0x001d:
            r0 = r2
            java.lang.Object r2 = r0.zze
            java.lang.Object r12 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.zzg
            r4 = 1
            r13 = 2
            if (r3 == 0) goto L_0x006b
            if (r3 == r4) goto L_0x0043
            if (r3 != r13) goto L_0x003b
            java.lang.Object r0 = r0.zza
            r3 = r0
            kotlinx.coroutines.sync.Mutex r3 = (kotlinx.coroutines.sync.Mutex) r3
            kotlin.ResultKt.throwOnFailure(r2)     // Catch:{ all -> 0x0038 }
            goto L_0x00dc
        L_0x0038:
            r0 = move-exception
            goto L_0x00e4
        L_0x003b:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x0043:
            long r3 = r0.zzd
            java.lang.Object r5 = r0.zzc
            kotlinx.coroutines.sync.Mutex r5 = (kotlinx.coroutines.sync.Mutex) r5
            com.google.android.recaptcha.internal.zzcj r6 = r0.zzi
            r7 = r6
            com.google.android.recaptcha.internal.zzcj r7 = (com.google.android.recaptcha.internal.zzcj) r7
            com.google.android.recaptcha.internal.zzbk r7 = r0.zzj
            r8 = r7
            com.google.android.recaptcha.internal.zzbk r8 = (com.google.android.recaptcha.internal.zzbk) r8
            java.lang.Object r8 = r0.zzb
            r8 = r11
            com.google.android.recaptcha.internal.zzcp r8 = (com.google.android.recaptcha.internal.zzcp) r8
            java.lang.String r8 = r0.zzh
            r9 = r8
            java.lang.String r9 = (java.lang.String) r9
            java.lang.Object r9 = r0.zza
            com.google.android.recaptcha.internal.zzcx r9 = (com.google.android.recaptcha.internal.zzcx) r9
            kotlin.ResultKt.throwOnFailure(r2)
            r15 = r5
            r14 = r8
            r10 = r9
            r9 = r6
            r8 = r7
            r5 = r3
            goto L_0x0097
        L_0x006b:
            kotlin.ResultKt.throwOnFailure(r2)
            kotlinx.coroutines.sync.Mutex r2 = r1.zzb
            r0.zza = r1
            r3 = r23
            r0.zzh = r3
            r0.zzb = r11
            r5 = r27
            r0.zzj = r5
            r6 = r28
            r0.zzi = r6
            r0.zzc = r2
            r7 = r24
            r0.zzd = r7
            r0.zzg = r4
            java.lang.Object r4 = r2.lock(r11, r0)
            if (r4 == r12) goto L_0x00e8
            r10 = r1
            r15 = r2
            r14 = r3
            r9 = r6
            r20 = r7
            r8 = r5
            r5 = r20
        L_0x0097:
            com.google.android.recaptcha.internal.zzcj r2 = com.google.android.recaptcha.internal.zzcj.zza     // Catch:{ all -> 0x00e2 }
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r2)     // Catch:{ all -> 0x00e2 }
            if (r2 == 0) goto L_0x00a2
            r2 = 3
        L_0x00a0:
            r7 = r2
            goto L_0x00ad
        L_0x00a2:
            com.google.android.recaptcha.internal.zzcj r2 = com.google.android.recaptcha.internal.zzcj.zzb     // Catch:{ all -> 0x00e2 }
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r2)     // Catch:{ all -> 0x00e2 }
            if (r2 == 0) goto L_0x00ac
            r2 = 4
            goto L_0x00a0
        L_0x00ac:
            r7 = r13
        L_0x00ad:
            com.google.android.recaptcha.internal.zzcv r16 = new com.google.android.recaptcha.internal.zzcv     // Catch:{ all -> 0x00e2 }
            r17 = 0
            r2 = r16
            r3 = r10
            r4 = r14
            r18 = r7
            r7 = r11
            r19 = r10
            r10 = r17
            r2.<init>(r3, r4, r5, r7, r8, r9, r10)     // Catch:{ all -> 0x00e2 }
            r2 = r16
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2     // Catch:{ all -> 0x00e2 }
            r0.zza = r15     // Catch:{ all -> 0x00e2 }
            r0.zzh = r11     // Catch:{ all -> 0x00e2 }
            r0.zzb = r11     // Catch:{ all -> 0x00e2 }
            r0.zzj = r11     // Catch:{ all -> 0x00e2 }
            r0.zzi = r11     // Catch:{ all -> 0x00e2 }
            r0.zzc = r11     // Catch:{ all -> 0x00e2 }
            r0.zzg = r13     // Catch:{ all -> 0x00e2 }
            r13 = r18
            r9 = r19
            java.lang.Object r2 = r9.zzj(r14, r13, r2, r0)     // Catch:{ all -> 0x00e2 }
            if (r2 == r12) goto L_0x00e8
            r3 = r15
        L_0x00dc:
            com.google.android.recaptcha.internal.zzde r2 = (com.google.android.recaptcha.internal.zzde) r2     // Catch:{ all -> 0x0038 }
            r3.unlock(r11)
            return r2
        L_0x00e2:
            r0 = move-exception
            r3 = r15
        L_0x00e4:
            r3.unlock(r11)
            throw r0
        L_0x00e8:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzcx.zzg(java.lang.String, long, com.google.android.recaptcha.internal.zzcp, com.google.android.recaptcha.internal.zzbk, com.google.android.recaptcha.internal.zzcj, kotlin.coroutines.Continuation):java.lang.Object");
    }
}

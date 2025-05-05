package com.google.android.recaptcha.internal;

import android.app.Application;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.recaptchabase.RecaptchaBaseClient;
import com.google.android.recaptcha.RecaptchaAction;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzdv {
    /* access modifiers changed from: private */
    public final String zza;
    /* access modifiers changed from: private */
    public final zzem zzb;
    private final zzl zzc;
    private final Lazy zzd = LazyKt.lazy(zzdo.zza);
    private final Lazy zze = LazyKt.lazy(zzdp.zza);
    private final Lazy zzf = LazyKt.lazy(zzdq.zza);
    private final Lazy zzg = LazyKt.lazy(zzdr.zza);
    private final Lazy zzh = LazyKt.lazy(zzds.zza);
    private final zzbk zzi;

    public zzdv(String str, zzbk zzbk, zzem zzem, zzl zzl) {
        this.zza = str;
        this.zzi = zzbk;
        this.zzb = zzem;
        this.zzc = zzl;
        int i = zzax.zza;
    }

    public static final /* synthetic */ zzbt zzd(zzdv zzdv) {
        return (zzbt) zzdv.zze.getValue();
    }

    public static final /* synthetic */ zzfh zzg(zzdv zzdv) {
        return (zzfh) zzdv.zzd.getValue();
    }

    public static final /* synthetic */ zzfl zzh(zzdv zzdv) {
        return (zzfl) zzdv.zzg.getValue();
    }

    /* access modifiers changed from: private */
    public final Application zzr() {
        return (Application) this.zzh.getValue();
    }

    /* access modifiers changed from: private */
    public final zzbf zzs(Exception exc, zzbf zzbf) {
        return !zzx() ? new zzbf(zzbd.zzc, zzbc.zzao, exc.getMessage()) : zzbf;
    }

    /* access modifiers changed from: private */
    public final zzbh zzt() {
        return (zzbh) this.zzf.getValue();
    }

    /* access modifiers changed from: private */
    public final zzem zzu(String str) {
        zzem zza2 = this.zzb.zza();
        zza2.zzc(str);
        return zza2;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object zzv(com.google.android.recaptcha.internal.zzse r11, long r12, kotlin.coroutines.Continuation r14) {
        /*
            r10 = this;
            boolean r0 = r14 instanceof com.google.android.recaptcha.internal.zzdl
            if (r0 == 0) goto L_0x0013
            r0 = r14
            com.google.android.recaptcha.internal.zzdl r0 = (com.google.android.recaptcha.internal.zzdl) r0
            int r1 = r0.zzd
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.zzd = r1
            goto L_0x0018
        L_0x0013:
            com.google.android.recaptcha.internal.zzdl r0 = new com.google.android.recaptcha.internal.zzdl
            r0.<init>(r10, r14)
        L_0x0018:
            java.lang.Object r14 = r0.zzb
            java.lang.Object r7 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r0.zzd
            r8 = 2
            r9 = 1
            if (r1 == 0) goto L_0x0047
            if (r1 == r9) goto L_0x0039
            if (r1 == r8) goto L_0x0030
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x0030:
            java.lang.Object r11 = r0.zza
            java.lang.Throwable r11 = (java.lang.Throwable) r11
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x00be
        L_0x0039:
            java.lang.Object r11 = r0.zza
            com.google.android.recaptcha.internal.zzdv r11 = (com.google.android.recaptcha.internal.zzdv) r11
            kotlin.ResultKt.throwOnFailure(r14)
            kotlin.Result r14 = (kotlin.Result) r14
            java.lang.Object r12 = r14.m2453unboximpl()
            goto L_0x0083
        L_0x0047:
            kotlin.ResultKt.throwOnFailure(r14)
            java.lang.String r14 = r11.zzP()
            zzy(r14)
            java.util.List r14 = r10.zzw()
            java.util.Iterator r14 = r14.iterator()
        L_0x0059:
            boolean r1 = r14.hasNext()
            if (r1 == 0) goto L_0x0070
            java.lang.Object r1 = r14.next()
            com.google.android.recaptcha.internal.zze r1 = (com.google.android.recaptcha.internal.zze) r1
            com.google.android.recaptcha.internal.zzl r2 = r10.zzc
            com.google.android.recaptcha.internal.zze[] r3 = new com.google.android.recaptcha.internal.zze[r9]
            r4 = 0
            r3[r4] = r1
            r2.zzf(r3)
            goto L_0x0059
        L_0x0070:
            com.google.android.recaptcha.internal.zzl r1 = r10.zzc
            com.google.android.recaptcha.internal.zzem r5 = r10.zzb
            r0.zza = r10
            r0.zzd = r9
            r2 = r12
            r4 = r11
            r6 = r0
            java.lang.Object r12 = r1.zzc(r2, r4, r5, r6)
            if (r12 != r7) goto L_0x0082
            goto L_0x00bc
        L_0x0082:
            r11 = r10
        L_0x0083:
            java.lang.Throwable r12 = kotlin.Result.m2447exceptionOrNullimpl(r12)
            if (r12 != 0) goto L_0x008c
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L_0x008c:
            com.google.android.recaptcha.internal.zzbk r13 = r11.zzi
            kotlinx.coroutines.CoroutineScope r13 = r13.zzd()
            kotlin.coroutines.CoroutineContext r13 = r13.getCoroutineContext()
            r14 = 0
            kotlinx.coroutines.JobKt__JobKt.cancelChildren$default((kotlin.coroutines.CoroutineContext) r13, (java.util.concurrent.CancellationException) r14, (int) r9, (java.lang.Object) r14)
            com.google.android.recaptcha.internal.zzbk r11 = r11.zzi
            kotlinx.coroutines.CoroutineScope r11 = r11.zzd()
            kotlin.coroutines.CoroutineContext r11 = r11.getCoroutineContext()
            kotlinx.coroutines.Job r11 = kotlinx.coroutines.JobKt.getJob(r11)
            kotlin.sequences.Sequence r11 = r11.getChildren()
            java.util.List r11 = kotlin.sequences.SequencesKt.toList(r11)
            java.util.Collection r11 = (java.util.Collection) r11
            r0.zza = r12
            r0.zzd = r8
            java.lang.Object r11 = kotlinx.coroutines.AwaitKt.joinAll((java.util.Collection<? extends kotlinx.coroutines.Job>) r11, (kotlin.coroutines.Continuation<? super kotlin.Unit>) r0)
            if (r11 != r7) goto L_0x00bd
        L_0x00bc:
            return r7
        L_0x00bd:
            r11 = r12
        L_0x00be:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzdv.zzv(com.google.android.recaptcha.internal.zzse, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final List zzw() {
        List arrayList = new ArrayList();
        arrayList.add(new zzx(zzr(), this.zzb.zza(), this.zzi, (List) null, 8, (DefaultConstructorMarker) null));
        arrayList.add(new zzjc(this.zzb, this.zzi));
        arrayList.add(new zzn(zzr(), this.zzb, new zzbu(GoogleApiAvailabilityLight.getInstance()), (RecaptchaBaseClient) null));
        return CollectionsKt.toList(arrayList);
    }

    private final boolean zzx() {
        int i = zzax.zza;
        zzbg zzbg = (zzbg) LazyKt.lazy(zzdk.zza).getValue();
        try {
            Object systemService = zzr().getSystemService("connectivity");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
            ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
            Network activeNetwork = connectivityManager.getActiveNetwork();
            if (activeNetwork == null) {
                return false;
            }
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
            if (networkCapabilities == null) {
                return false;
            }
            return networkCapabilities.hasCapability(16);
        } catch (Exception unused) {
            return false;
        }
    }

    private static final void zzy(String str) {
        try {
            zzrx zzj = zzrx.zzj(zzbv.zza(str));
            int i = zzax.zza;
            ((zzfw) LazyKt.lazy(zzdg.zza).getValue()).zza(zzj);
        } catch (Exception e) {
            throw new zzbf(zzbd.zzl, zzbc.zzan, e.getMessage());
        }
    }

    public final zzsr zzi(RecaptchaAction recaptchaAction, zzsk zzsk, zzse zzse) {
        zzsq zzf2 = zzsr.zzf();
        zzf2.zzs(this.zza);
        zzf2.zze(recaptchaAction.getAction());
        zzf2.zzf(zzse.zzO());
        zzf2.zzq(zzse.zzN());
        zzf2.zzr(zzsk);
        return (zzsr) zzf2.zzk();
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object zzl(java.lang.String r6, long r7, kotlin.coroutines.Continuation r9) {
        /*
            r5 = this;
            boolean r0 = r9 instanceof com.google.android.recaptcha.internal.zzdf
            if (r0 == 0) goto L_0x0013
            r0 = r9
            com.google.android.recaptcha.internal.zzdf r0 = (com.google.android.recaptcha.internal.zzdf) r0
            int r1 = r0.zzc
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.zzc = r1
            goto L_0x0018
        L_0x0013:
            com.google.android.recaptcha.internal.zzdf r0 = new com.google.android.recaptcha.internal.zzdf
            r0.<init>(r5, r9)
        L_0x0018:
            java.lang.Object r9 = r0.zza
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.zzc
            r3 = 1
            if (r2 == 0) goto L_0x003c
            if (r2 != r3) goto L_0x0034
            com.google.android.recaptcha.internal.zzep r6 = r0.zzd
            r7 = r6
            com.google.android.recaptcha.internal.zzep r7 = (com.google.android.recaptcha.internal.zzep) r7
            kotlin.ResultKt.throwOnFailure(r9)     // Catch:{ zzbf -> 0x0032, TimeoutCancellationException -> 0x0030, Exception -> 0x002e }
            goto L_0x0058
        L_0x002e:
            r7 = move-exception
            goto L_0x0062
        L_0x0030:
            r7 = move-exception
            goto L_0x0076
        L_0x0032:
            r7 = move-exception
            goto L_0x008a
        L_0x0034:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x003c:
            kotlin.ResultKt.throwOnFailure(r9)
            com.google.android.recaptcha.internal.zzem r9 = r5.zzu(r6)
            r2 = 27
            com.google.android.recaptcha.internal.zzep r9 = r9.zzf(r2)
            com.google.android.recaptcha.internal.zzl r2 = r5.zzc     // Catch:{ zzbf -> 0x0087, TimeoutCancellationException -> 0x0073, Exception -> 0x005f }
            r0.zzd = r9     // Catch:{ zzbf -> 0x0087, TimeoutCancellationException -> 0x0073, Exception -> 0x005f }
            r0.zzc = r3     // Catch:{ zzbf -> 0x0087, TimeoutCancellationException -> 0x0073, Exception -> 0x005f }
            java.lang.Object r6 = r2.zzb(r6, r7, r0)     // Catch:{ zzbf -> 0x0087, TimeoutCancellationException -> 0x0073, Exception -> 0x005f }
            if (r6 == r1) goto L_0x005e
            r4 = r9
            r9 = r6
            r6 = r4
        L_0x0058:
            com.google.android.recaptcha.internal.zzsk r9 = (com.google.android.recaptcha.internal.zzsk) r9     // Catch:{ zzbf -> 0x0032, TimeoutCancellationException -> 0x0030, Exception -> 0x002e }
            r6.zza()     // Catch:{ zzbf -> 0x0032, TimeoutCancellationException -> 0x0030, Exception -> 0x002e }
            return r9
        L_0x005e:
            return r1
        L_0x005f:
            r6 = move-exception
            r7 = r6
            r6 = r9
        L_0x0062:
            com.google.android.recaptcha.internal.zzbf r8 = new com.google.android.recaptcha.internal.zzbf
            com.google.android.recaptcha.internal.zzbd r9 = com.google.android.recaptcha.internal.zzbd.zzb
            com.google.android.recaptcha.internal.zzbc r0 = com.google.android.recaptcha.internal.zzbc.zzaa
            java.lang.String r7 = r7.getMessage()
            r8.<init>(r9, r0, r7)
            r6.zzb(r8)
            throw r8
        L_0x0073:
            r6 = move-exception
            r7 = r6
            r6 = r9
        L_0x0076:
            com.google.android.recaptcha.internal.zzbf r8 = new com.google.android.recaptcha.internal.zzbf
            com.google.android.recaptcha.internal.zzbd r9 = com.google.android.recaptcha.internal.zzbd.zzb
            com.google.android.recaptcha.internal.zzbc r0 = com.google.android.recaptcha.internal.zzbc.zzb
            java.lang.String r7 = r7.getMessage()
            r8.<init>(r9, r0, r7)
            r6.zzb(r8)
            throw r8
        L_0x0087:
            r6 = move-exception
            r7 = r6
            r6 = r9
        L_0x008a:
            r6.zzb(r7)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzdv.zzl(java.lang.String, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Object zzm(zzsr zzsr, String str, long j, Continuation continuation) {
        return BuildersKt.withContext(this.zzi.zza().getCoroutineContext(), new zzdi(this, str, j, zzsr, (Continuation) null), continuation);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object zzn(com.google.android.recaptcha.internal.zzse r11, long r12, kotlin.coroutines.Continuation r14) {
        /*
            r10 = this;
            boolean r0 = r14 instanceof com.google.android.recaptcha.internal.zzdm
            if (r0 == 0) goto L_0x0013
            r0 = r14
            com.google.android.recaptcha.internal.zzdm r0 = (com.google.android.recaptcha.internal.zzdm) r0
            int r1 = r0.zzc
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.zzc = r1
            goto L_0x0018
        L_0x0013:
            com.google.android.recaptcha.internal.zzdm r0 = new com.google.android.recaptcha.internal.zzdm
            r0.<init>(r10, r14)
        L_0x0018:
            java.lang.Object r14 = r0.zza
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.zzc
            r3 = 1
            if (r2 == 0) goto L_0x0037
            if (r2 != r3) goto L_0x002f
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ zzbf -> 0x002d, TimeoutCancellationException -> 0x002b, Exception -> 0x0029 }
            goto L_0x004f
        L_0x0029:
            r11 = move-exception
            goto L_0x0052
        L_0x002b:
            r11 = move-exception
            goto L_0x0060
        L_0x002d:
            r11 = move-exception
            goto L_0x006e
        L_0x002f:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x0037:
            kotlin.ResultKt.throwOnFailure(r14)
            com.google.android.recaptcha.internal.zzdn r14 = new com.google.android.recaptcha.internal.zzdn     // Catch:{ zzbf -> 0x002d, TimeoutCancellationException -> 0x002b, Exception -> 0x0029 }
            r9 = 0
            r4 = r14
            r5 = r10
            r6 = r11
            r7 = r12
            r4.<init>(r5, r6, r7, r9)     // Catch:{ zzbf -> 0x002d, TimeoutCancellationException -> 0x002b, Exception -> 0x0029 }
            kotlin.jvm.functions.Function2 r14 = (kotlin.jvm.functions.Function2) r14     // Catch:{ zzbf -> 0x002d, TimeoutCancellationException -> 0x002b, Exception -> 0x0029 }
            r0.zzc = r3     // Catch:{ zzbf -> 0x002d, TimeoutCancellationException -> 0x002b, Exception -> 0x0029 }
            java.lang.Object r11 = kotlinx.coroutines.TimeoutKt.withTimeout(r12, r14, r0)     // Catch:{ zzbf -> 0x002d, TimeoutCancellationException -> 0x002b, Exception -> 0x0029 }
            if (r11 != r1) goto L_0x004f
            return r1
        L_0x004f:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L_0x0052:
            com.google.android.recaptcha.internal.zzbf r12 = new com.google.android.recaptcha.internal.zzbf
            com.google.android.recaptcha.internal.zzbd r13 = com.google.android.recaptcha.internal.zzbd.zzb
            com.google.android.recaptcha.internal.zzbc r14 = com.google.android.recaptcha.internal.zzbc.zzap
            java.lang.String r11 = r11.getMessage()
            r12.<init>(r13, r14, r11)
            throw r12
        L_0x0060:
            com.google.android.recaptcha.internal.zzbf r12 = new com.google.android.recaptcha.internal.zzbf
            com.google.android.recaptcha.internal.zzbd r13 = com.google.android.recaptcha.internal.zzbd.zzb
            com.google.android.recaptcha.internal.zzbc r14 = com.google.android.recaptcha.internal.zzbc.zzb
            java.lang.String r11 = r11.getMessage()
            r12.<init>(r13, r14, r11)
            throw r12
        L_0x006e:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzdv.zzn(com.google.android.recaptcha.internal.zzse, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object zzo(long r7, kotlin.coroutines.Continuation r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof com.google.android.recaptcha.internal.zzdt
            if (r0 == 0) goto L_0x0013
            r0 = r9
            com.google.android.recaptcha.internal.zzdt r0 = (com.google.android.recaptcha.internal.zzdt) r0
            int r1 = r0.zzc
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.zzc = r1
            goto L_0x0018
        L_0x0013:
            com.google.android.recaptcha.internal.zzdt r0 = new com.google.android.recaptcha.internal.zzdt
            r0.<init>(r6, r9)
        L_0x0018:
            java.lang.Object r9 = r0.zza
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.zzc
            r3 = 1
            if (r2 == 0) goto L_0x0042
            if (r2 != r3) goto L_0x003a
            com.google.android.recaptcha.internal.zzep r7 = r0.zze
            r8 = r7
            com.google.android.recaptcha.internal.zzep r8 = (com.google.android.recaptcha.internal.zzep) r8
            com.google.android.recaptcha.internal.zzdv r8 = r0.zzd
            r0 = r8
            com.google.android.recaptcha.internal.zzdv r0 = (com.google.android.recaptcha.internal.zzdv) r0
            kotlin.ResultKt.throwOnFailure(r9)     // Catch:{ zzbf -> 0x0037, TimeoutCancellationException -> 0x0035, Exception -> 0x0033 }
            goto L_0x0065
        L_0x0033:
            r9 = move-exception
            goto L_0x006e
        L_0x0035:
            r9 = move-exception
            goto L_0x0088
        L_0x0037:
            r9 = move-exception
            goto L_0x00a5
        L_0x003a:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0042:
            kotlin.ResultKt.throwOnFailure(r9)
            com.google.android.recaptcha.internal.zzem r9 = r6.zzb
            r2 = 22
            com.google.android.recaptcha.internal.zzep r9 = r9.zzf(r2)
            com.google.android.recaptcha.internal.zzdu r2 = new com.google.android.recaptcha.internal.zzdu     // Catch:{ zzbf -> 0x00a0, TimeoutCancellationException -> 0x0083, Exception -> 0x0069 }
            r4 = 0
            r2.<init>(r6, r9, r4)     // Catch:{ zzbf -> 0x00a0, TimeoutCancellationException -> 0x0083, Exception -> 0x0069 }
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2     // Catch:{ zzbf -> 0x00a0, TimeoutCancellationException -> 0x0083, Exception -> 0x0069 }
            r0.zzd = r6     // Catch:{ zzbf -> 0x00a0, TimeoutCancellationException -> 0x0083, Exception -> 0x0069 }
            r0.zze = r9     // Catch:{ zzbf -> 0x00a0, TimeoutCancellationException -> 0x0083, Exception -> 0x0069 }
            r0.zzc = r3     // Catch:{ zzbf -> 0x00a0, TimeoutCancellationException -> 0x0083, Exception -> 0x0069 }
            java.lang.Object r7 = kotlinx.coroutines.TimeoutKt.withTimeout(r7, r2, r0)     // Catch:{ zzbf -> 0x00a0, TimeoutCancellationException -> 0x0083, Exception -> 0x0069 }
            if (r7 == r1) goto L_0x0068
            r8 = r6
            r5 = r9
            r9 = r7
            r7 = r5
        L_0x0065:
            com.google.android.recaptcha.internal.zzse r9 = (com.google.android.recaptcha.internal.zzse) r9     // Catch:{ zzbf -> 0x0037, TimeoutCancellationException -> 0x0035, Exception -> 0x0033 }
            return r9
        L_0x0068:
            return r1
        L_0x0069:
            r7 = move-exception
            r8 = r6
            r5 = r9
            r9 = r7
            r7 = r5
        L_0x006e:
            com.google.android.recaptcha.internal.zzbf r0 = new com.google.android.recaptcha.internal.zzbf
            com.google.android.recaptcha.internal.zzbd r1 = com.google.android.recaptcha.internal.zzbd.zzc
            com.google.android.recaptcha.internal.zzbc r2 = com.google.android.recaptcha.internal.zzbc.zzaz
            java.lang.String r3 = r9.getMessage()
            r0.<init>(r1, r2, r3)
            com.google.android.recaptcha.internal.zzbf r8 = r8.zzs(r9, r0)
            r7.zzb(r8)
            throw r8
        L_0x0083:
            r7 = move-exception
            r8 = r6
            r5 = r9
            r9 = r7
            r7 = r5
        L_0x0088:
            r0 = r9
            java.lang.Exception r0 = (java.lang.Exception) r0
            com.google.android.recaptcha.internal.zzbf r1 = new com.google.android.recaptcha.internal.zzbf
            com.google.android.recaptcha.internal.zzbd r2 = com.google.android.recaptcha.internal.zzbd.zzc
            com.google.android.recaptcha.internal.zzbc r3 = com.google.android.recaptcha.internal.zzbc.zzb
            java.lang.String r9 = r9.getMessage()
            r1.<init>(r2, r3, r9)
            com.google.android.recaptcha.internal.zzbf r8 = r8.zzs(r0, r1)
            r7.zzb(r8)
            throw r8
        L_0x00a0:
            r7 = move-exception
            r8 = r6
            r5 = r9
            r9 = r7
            r7 = r5
        L_0x00a5:
            com.google.android.recaptcha.internal.zzbd r0 = r9.zzb()
            com.google.android.recaptcha.internal.zzbd r1 = com.google.android.recaptcha.internal.zzbd.zzc
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r1)
            if (r0 == 0) goto L_0x00b8
            r0 = r9
            java.lang.Exception r0 = (java.lang.Exception) r0
            com.google.android.recaptcha.internal.zzbf r9 = r8.zzs(r0, r9)
        L_0x00b8:
            r7.zzb(r9)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzdv.zzo(long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void zzq(String str, zzst zzst) {
        zzep zzf2 = zzu(str).zzf(29);
        try {
            Iterable<zzsv> zzk = zzst.zzk();
            Map linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(zzk, 10)), 16));
            for (zzsv zzsv : zzk) {
                Pair pair = TuplesKt.to(zzsv.zzg(), zzsv.zzi());
                linkedHashMap.put(pair.getFirst(), pair.getSecond());
            }
            zzt().zzb(linkedHashMap);
            this.zzc.zzg(zzst);
            zzf2.zza();
        } catch (zzbf e) {
            zzf2.zzb(e);
        } catch (Exception e2) {
            zzf2.zzb(new zzbf(zzbd.zzb, zzbc.zzav, e2.getMessage()));
        }
    }
}

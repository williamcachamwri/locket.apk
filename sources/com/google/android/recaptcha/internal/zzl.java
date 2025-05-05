package com.google.android.recaptcha.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzl {
    private final List zza;
    /* access modifiers changed from: private */
    public zzem zzb;

    public zzl() {
        this((List) null, 1, (DefaultConstructorMarker) null);
    }

    public /* synthetic */ zzl(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        List emptyList = CollectionsKt.emptyList();
        this.zza = new ArrayList();
        zze[] zzeArr = (zze[]) emptyList.toArray(new zze[0]);
        zzh((zze[]) Arrays.copyOf(zzeArr, zzeArr.length));
    }

    private final void zzh(zze... zzeArr) {
        CollectionsKt.addAll(this.zza, (T[]) zzeArr);
    }

    public final Object zzb(String str, long j, Continuation continuation) {
        return CoroutineScopeKt.coroutineScope(new zzh(this, str, j, (Continuation) null), continuation);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object zzc(long r13, com.google.android.recaptcha.internal.zzse r15, com.google.android.recaptcha.internal.zzem r16, kotlin.coroutines.Continuation r17) {
        /*
            r12 = this;
            r0 = r17
            boolean r1 = r0 instanceof com.google.android.recaptcha.internal.zzi
            if (r1 == 0) goto L_0x0016
            r1 = r0
            com.google.android.recaptcha.internal.zzi r1 = (com.google.android.recaptcha.internal.zzi) r1
            int r2 = r1.zzc
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L_0x0016
            int r2 = r2 - r3
            r1.zzc = r2
            r9 = r12
            goto L_0x001c
        L_0x0016:
            com.google.android.recaptcha.internal.zzi r1 = new com.google.android.recaptcha.internal.zzi
            r9 = r12
            r1.<init>(r12, r0)
        L_0x001c:
            java.lang.Object r0 = r1.zza
            java.lang.Object r10 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r1.zzc
            r11 = 1
            if (r2 == 0) goto L_0x0035
            if (r2 != r11) goto L_0x002d
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x004f
        L_0x002d:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0035:
            kotlin.ResultKt.throwOnFailure(r0)
            com.google.android.recaptcha.internal.zzk r0 = new com.google.android.recaptcha.internal.zzk
            r8 = 0
            r2 = r0
            r3 = r12
            r4 = r16
            r5 = r13
            r7 = r15
            r2.<init>(r3, r4, r5, r7, r8)
            kotlin.jvm.functions.Function2 r0 = (kotlin.jvm.functions.Function2) r0
            r1.zzc = r11
            java.lang.Object r0 = kotlinx.coroutines.CoroutineScopeKt.coroutineScope(r0, r1)
            if (r0 != r10) goto L_0x004f
            return r10
        L_0x004f:
            kotlin.Result r0 = (kotlin.Result) r0
            java.lang.Object r0 = r0.m2453unboximpl()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzl.zzc(long, com.google.android.recaptcha.internal.zzse, com.google.android.recaptcha.internal.zzem, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final List zzd() {
        return this.zza;
    }

    public final void zzf(zze... zzeArr) {
        zzh((zze[]) Arrays.copyOf(zzeArr, 1));
    }

    public final void zzg(zzst zzst) {
        for (zze zzk : this.zza) {
            zzk.zzk(zzst);
        }
    }
}

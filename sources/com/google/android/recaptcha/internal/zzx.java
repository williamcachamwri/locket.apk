package com.google.android.recaptcha.internal;

import android.content.Context;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.play.core.integrity.IntegrityManagerFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzx extends zze {
    private final zzem zza;
    /* access modifiers changed from: private */
    public final List zzb;
    /* access modifiers changed from: private */
    public zzlg zzc;
    private final Map zzd = new LinkedHashMap();

    public /* synthetic */ zzx(Context context, zzem zzem, zzbk zzbk, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        zzem zza2 = zzem.zza();
        CoroutineScope zzc2 = zzbk.zzc();
        zzap zzap = new zzap(context, zzc2, zza2, IntegrityManagerFactory.createStandard(context), 28800000);
        zzbu zzbu = new zzbu(GoogleApiAvailabilityLight.getInstance());
        zzbu zzbu2 = zzbu;
        List listOf = CollectionsKt.listOf(new zzad(zzem.zza()), new zzr(zzem.zza()), new zzo(zzem.zza(), context.getContentResolver()), new zzp(zzem.zza()), new zzag(zza2, context, zzc2, zzap, zzbu));
        this.zza = zzem;
        this.zzb = listOf;
    }

    /* access modifiers changed from: private */
    public final zzsk zzq(String str) {
        Map map;
        List list = (List) this.zzd.remove(str);
        if (list != null) {
            Iterable<zzac> iterable = list;
            map = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(iterable, 10)), 16));
            for (zzac zzac : iterable) {
                map.put(Integer.valueOf(zzac.zzb()), zzac);
            }
        } else {
            map = MapsKt.emptyMap();
        }
        zztb zzs = zzs(map, str);
        zzsj zzf = zzsk.zzf();
        zzf.zze(str);
        zzsh zzf2 = zzsi.zzf();
        byte[] zzd2 = zzs.zzd();
        zzf2.zze(zzkj.zzh().zzi(zzd2, 0, zzd2.length));
        zzf.zzq(zzf2);
        return (zzsk) zzf.zzk();
    }

    private final zzsz zzr(zzac zzac) {
        zzsx zzf = zzsz.zzf();
        zzf.zzq(3);
        zzlg zzlg = null;
        if (zzac instanceof zzz) {
            zztk zza2 = ((zzz) zzac).zza();
            zzlg zzlg2 = this.zzc;
            if (zzlg2 != null) {
                zzlg = zzlg2;
            }
            byte[] zzd2 = zza2.zzd();
            zzf.zzf(zzch.zza(zzkj.zzh().zzi(zzd2, 0, zzd2.length), zzlg));
        } else if (zzac instanceof zzy) {
            zztg zza3 = ((zzy) zzac).zza();
            zzlg zzlg3 = this.zzc;
            if (zzlg3 != null) {
                zzlg = zzlg3;
            }
            byte[] zzd3 = zza3.zzd();
            zzf.zze(zzch.zza(zzkj.zzh().zzi(zzd3, 0, zzd3.length), zzlg));
        } else {
            throw new NoWhenBranchMatchedException();
        }
        return (zzsz) zzf.zzk();
    }

    private final zztb zzs(Map map, String str) {
        zzta zzf = zztb.zzf();
        zzf.zzq(str);
        Collection arrayList = new ArrayList();
        for (Object next : this.zzb) {
            if (((zzaa) next).zzf()) {
                arrayList.add(next);
            }
        }
        for (zzaa zzaa : (List) arrayList) {
            if (!map.containsKey(Integer.valueOf(zzaa.zza()))) {
                int zza2 = zzaa.zza();
                zztf zzf2 = zztg.zzf();
                zzf2.zzf(zza2);
                zzf2.zzr(13);
                zzf2.zzq(27);
                zzf.zzf(zzr(new zzy(zza2, (zztg) zzf2.zzk())));
            }
        }
        Iterable<zzac> values = map.values();
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(values, 10));
        for (zzac zzr : values) {
            arrayList2.add(zzr(zzr));
        }
        zzf.zze((List) arrayList2);
        return (zztb) zzf.zzk();
    }

    /* access modifiers changed from: protected */
    public final zzep zza(String str) {
        zzem zzem = this.zza;
        zzem.zzc(str);
        return zzem.zzf(35);
    }

    /* access modifiers changed from: protected */
    public final zzep zzb() {
        zzem zzem = this.zza;
        zzem.zzc(zzem.zzd());
        return zzem.zzf(34);
    }

    /* access modifiers changed from: protected */
    public final Object zzd(String str, Continuation continuation) {
        return zzq(str);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object zzf(java.lang.String r5, kotlin.coroutines.Continuation r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.google.android.recaptcha.internal.zzs
            if (r0 == 0) goto L_0x0013
            r0 = r6
            com.google.android.recaptcha.internal.zzs r0 = (com.google.android.recaptcha.internal.zzs) r0
            int r1 = r0.zzc
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.zzc = r1
            goto L_0x0018
        L_0x0013:
            com.google.android.recaptcha.internal.zzs r0 = new com.google.android.recaptcha.internal.zzs
            r0.<init>(r4, r6)
        L_0x0018:
            java.lang.Object r6 = r0.zza
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.zzc
            r3 = 1
            if (r2 == 0) goto L_0x0031
            if (r2 != r3) goto L_0x0029
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0045
        L_0x0029:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0031:
            kotlin.ResultKt.throwOnFailure(r6)
            com.google.android.recaptcha.internal.zzu r6 = new com.google.android.recaptcha.internal.zzu
            r2 = 0
            r6.<init>(r4, r5, r2)
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
            r0.zzc = r3
            java.lang.Object r6 = kotlinx.coroutines.CoroutineScopeKt.coroutineScope(r6, r0)
            if (r6 != r1) goto L_0x0045
            return r1
        L_0x0045:
            kotlin.Result r6 = (kotlin.Result) r6
            java.lang.Object r5 = r6.m2453unboximpl()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzx.zzf(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object zzh(com.google.android.recaptcha.internal.zzse r5, kotlin.coroutines.Continuation r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.google.android.recaptcha.internal.zzv
            if (r0 == 0) goto L_0x0013
            r0 = r6
            com.google.android.recaptcha.internal.zzv r0 = (com.google.android.recaptcha.internal.zzv) r0
            int r1 = r0.zzc
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.zzc = r1
            goto L_0x0018
        L_0x0013:
            com.google.android.recaptcha.internal.zzv r0 = new com.google.android.recaptcha.internal.zzv
            r0.<init>(r4, r6)
        L_0x0018:
            java.lang.Object r6 = r0.zza
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.zzc
            r3 = 1
            if (r2 == 0) goto L_0x0031
            if (r2 != r3) goto L_0x0029
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0045
        L_0x0029:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0031:
            kotlin.ResultKt.throwOnFailure(r6)
            com.google.android.recaptcha.internal.zzw r6 = new com.google.android.recaptcha.internal.zzw
            r2 = 0
            r6.<init>(r5, r4, r2)
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
            r0.zzc = r3
            java.lang.Object r6 = kotlinx.coroutines.CoroutineScopeKt.coroutineScope(r6, r0)
            if (r6 != r1) goto L_0x0045
            return r1
        L_0x0045:
            kotlin.Result r6 = (kotlin.Result) r6
            java.lang.Object r5 = r6.m2453unboximpl()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzx.zzh(com.google.android.recaptcha.internal.zzse, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: protected */
    public final void zzk(zzst zzst) {
        for (zzaa zze : this.zzb) {
            zze.zze(zzst);
        }
    }

    public final Map zzo() {
        return this.zzd;
    }
}

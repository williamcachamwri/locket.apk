package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzkz implements Application.ActivityLifecycleCallbacks {
    private final /* synthetic */ zzjq zza;

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivityStopped(Activity activity) {
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x00ae A[SYNTHETIC, Splitter:B:38:0x00ae] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x010d A[Catch:{ RuntimeException -> 0x0199 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x010e A[Catch:{ RuntimeException -> 0x0199 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void zza(com.google.android.gms.measurement.internal.zzkz r17, boolean r18, android.net.Uri r19, java.lang.String r20, java.lang.String r21) {
        /*
            r1 = r17
            r0 = r20
            r2 = r21
            com.google.android.gms.measurement.internal.zzjq r3 = r1.zza
            r3.zzt()
            com.google.android.gms.measurement.internal.zzjq r3 = r1.zza     // Catch:{ RuntimeException -> 0x0199 }
            com.google.android.gms.measurement.internal.zzos r3 = r3.zzq()     // Catch:{ RuntimeException -> 0x0199 }
            boolean r4 = com.google.android.gms.internal.measurement.zzov.zza()     // Catch:{ RuntimeException -> 0x0199 }
            r5 = 0
            r6 = 1
            if (r4 == 0) goto L_0x0029
            com.google.android.gms.measurement.internal.zzjq r4 = r1.zza     // Catch:{ RuntimeException -> 0x0199 }
            com.google.android.gms.measurement.internal.zzag r4 = r4.zze()     // Catch:{ RuntimeException -> 0x0199 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r7 = com.google.android.gms.measurement.internal.zzbh.zzct     // Catch:{ RuntimeException -> 0x0199 }
            boolean r4 = r4.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r7)     // Catch:{ RuntimeException -> 0x0199 }
            if (r4 == 0) goto L_0x0029
            r4 = r6
            goto L_0x002a
        L_0x0029:
            r4 = r5
        L_0x002a:
            boolean r7 = android.text.TextUtils.isEmpty(r21)     // Catch:{ RuntimeException -> 0x0199 }
            java.lang.String r8 = "Activity created with data 'referrer' without required params"
            java.lang.String r9 = "utm_medium"
            java.lang.String r10 = "_cis"
            java.lang.String r11 = "utm_source"
            java.lang.String r12 = "utm_campaign"
            java.lang.String r14 = "gclid"
            if (r7 == 0) goto L_0x003e
        L_0x003c:
            r3 = 0
            goto L_0x00aa
        L_0x003e:
            boolean r7 = r2.contains(r14)     // Catch:{ RuntimeException -> 0x0199 }
            if (r7 != 0) goto L_0x008c
            if (r4 == 0) goto L_0x004e
            java.lang.String r7 = "gbraid"
            boolean r7 = r2.contains(r7)     // Catch:{ RuntimeException -> 0x0199 }
            if (r7 != 0) goto L_0x008c
        L_0x004e:
            boolean r7 = r2.contains(r12)     // Catch:{ RuntimeException -> 0x0199 }
            if (r7 != 0) goto L_0x008c
            boolean r7 = r2.contains(r11)     // Catch:{ RuntimeException -> 0x0199 }
            if (r7 != 0) goto L_0x008c
            boolean r7 = r2.contains(r9)     // Catch:{ RuntimeException -> 0x0199 }
            if (r7 != 0) goto L_0x008c
            java.lang.String r7 = "utm_id"
            boolean r7 = r2.contains(r7)     // Catch:{ RuntimeException -> 0x0199 }
            if (r7 != 0) goto L_0x008c
            java.lang.String r7 = "dclid"
            boolean r7 = r2.contains(r7)     // Catch:{ RuntimeException -> 0x0199 }
            if (r7 != 0) goto L_0x008c
            java.lang.String r7 = "srsltid"
            boolean r7 = r2.contains(r7)     // Catch:{ RuntimeException -> 0x0199 }
            if (r7 != 0) goto L_0x008c
            java.lang.String r7 = "sfmc_id"
            boolean r7 = r2.contains(r7)     // Catch:{ RuntimeException -> 0x0199 }
            if (r7 != 0) goto L_0x008c
            com.google.android.gms.measurement.internal.zzgo r3 = r3.zzj()     // Catch:{ RuntimeException -> 0x0199 }
            com.google.android.gms.measurement.internal.zzgq r3 = r3.zzc()     // Catch:{ RuntimeException -> 0x0199 }
            r3.zza(r8)     // Catch:{ RuntimeException -> 0x0199 }
            goto L_0x003c
        L_0x008c:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ RuntimeException -> 0x0199 }
            java.lang.String r15 = "https://google.com/search?"
            r7.<init>(r15)     // Catch:{ RuntimeException -> 0x0199 }
            java.lang.StringBuilder r7 = r7.append(r2)     // Catch:{ RuntimeException -> 0x0199 }
            java.lang.String r7 = r7.toString()     // Catch:{ RuntimeException -> 0x0199 }
            android.net.Uri r7 = android.net.Uri.parse(r7)     // Catch:{ RuntimeException -> 0x0199 }
            android.os.Bundle r3 = r3.zza((android.net.Uri) r7, (boolean) r4)     // Catch:{ RuntimeException -> 0x0199 }
            if (r3 == 0) goto L_0x00aa
            java.lang.String r4 = "referrer"
            r3.putString(r10, r4)     // Catch:{ RuntimeException -> 0x0199 }
        L_0x00aa:
            java.lang.String r4 = "_cmp"
            if (r18 == 0) goto L_0x0107
            com.google.android.gms.measurement.internal.zzjq r7 = r1.zza     // Catch:{ RuntimeException -> 0x0199 }
            com.google.android.gms.measurement.internal.zzos r7 = r7.zzq()     // Catch:{ RuntimeException -> 0x0199 }
            boolean r15 = com.google.android.gms.internal.measurement.zzov.zza()     // Catch:{ RuntimeException -> 0x0199 }
            if (r15 == 0) goto L_0x00cc
            com.google.android.gms.measurement.internal.zzjq r15 = r1.zza     // Catch:{ RuntimeException -> 0x0199 }
            com.google.android.gms.measurement.internal.zzag r15 = r15.zze()     // Catch:{ RuntimeException -> 0x0199 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r13 = com.google.android.gms.measurement.internal.zzbh.zzct     // Catch:{ RuntimeException -> 0x0199 }
            boolean r13 = r15.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r13)     // Catch:{ RuntimeException -> 0x0199 }
            if (r13 == 0) goto L_0x00cc
            r13 = r19
            r15 = r6
            goto L_0x00cf
        L_0x00cc:
            r13 = r19
            r15 = r5
        L_0x00cf:
            android.os.Bundle r7 = r7.zza((android.net.Uri) r13, (boolean) r15)     // Catch:{ RuntimeException -> 0x0199 }
            if (r7 == 0) goto L_0x0107
            java.lang.String r13 = "intent"
            r7.putString(r10, r13)     // Catch:{ RuntimeException -> 0x0199 }
            boolean r10 = r7.containsKey(r14)     // Catch:{ RuntimeException -> 0x0199 }
            if (r10 != 0) goto L_0x00fb
            if (r3 == 0) goto L_0x00fb
            boolean r10 = r3.containsKey(r14)     // Catch:{ RuntimeException -> 0x0199 }
            if (r10 == 0) goto L_0x00fb
            java.lang.String r10 = "_cer"
            java.lang.String r13 = "gclid=%s"
            java.lang.Object[] r15 = new java.lang.Object[r6]     // Catch:{ RuntimeException -> 0x0199 }
            java.lang.String r16 = r3.getString(r14)     // Catch:{ RuntimeException -> 0x0199 }
            r15[r5] = r16     // Catch:{ RuntimeException -> 0x0199 }
            java.lang.String r13 = java.lang.String.format(r13, r15)     // Catch:{ RuntimeException -> 0x0199 }
            r7.putString(r10, r13)     // Catch:{ RuntimeException -> 0x0199 }
        L_0x00fb:
            com.google.android.gms.measurement.internal.zzjq r10 = r1.zza     // Catch:{ RuntimeException -> 0x0199 }
            r10.zzc(r0, r4, r7)     // Catch:{ RuntimeException -> 0x0199 }
            com.google.android.gms.measurement.internal.zzjq r10 = r1.zza     // Catch:{ RuntimeException -> 0x0199 }
            com.google.android.gms.measurement.internal.zzu r10 = r10.zza     // Catch:{ RuntimeException -> 0x0199 }
            r10.zza(r0, r7)     // Catch:{ RuntimeException -> 0x0199 }
        L_0x0107:
            boolean r7 = android.text.TextUtils.isEmpty(r21)     // Catch:{ RuntimeException -> 0x0199 }
            if (r7 == 0) goto L_0x010e
            return
        L_0x010e:
            com.google.android.gms.measurement.internal.zzjq r7 = r1.zza     // Catch:{ RuntimeException -> 0x0199 }
            com.google.android.gms.measurement.internal.zzgo r7 = r7.zzj()     // Catch:{ RuntimeException -> 0x0199 }
            com.google.android.gms.measurement.internal.zzgq r7 = r7.zzc()     // Catch:{ RuntimeException -> 0x0199 }
            java.lang.String r10 = "Activity created with referrer"
            r7.zza(r10, r2)     // Catch:{ RuntimeException -> 0x0199 }
            com.google.android.gms.measurement.internal.zzjq r7 = r1.zza     // Catch:{ RuntimeException -> 0x0199 }
            com.google.android.gms.measurement.internal.zzag r7 = r7.zze()     // Catch:{ RuntimeException -> 0x0199 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r10 = com.google.android.gms.measurement.internal.zzbh.zzbq     // Catch:{ RuntimeException -> 0x0199 }
            boolean r7 = r7.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r10)     // Catch:{ RuntimeException -> 0x0199 }
            java.lang.String r10 = "_ldl"
            java.lang.String r13 = "auto"
            if (r7 == 0) goto L_0x0154
            if (r3 == 0) goto L_0x013e
            com.google.android.gms.measurement.internal.zzjq r2 = r1.zza     // Catch:{ RuntimeException -> 0x0199 }
            r2.zzc(r0, r4, r3)     // Catch:{ RuntimeException -> 0x0199 }
            com.google.android.gms.measurement.internal.zzjq r2 = r1.zza     // Catch:{ RuntimeException -> 0x0199 }
            com.google.android.gms.measurement.internal.zzu r2 = r2.zza     // Catch:{ RuntimeException -> 0x0199 }
            r2.zza(r0, r3)     // Catch:{ RuntimeException -> 0x0199 }
            goto L_0x014d
        L_0x013e:
            com.google.android.gms.measurement.internal.zzjq r0 = r1.zza     // Catch:{ RuntimeException -> 0x0199 }
            com.google.android.gms.measurement.internal.zzgo r0 = r0.zzj()     // Catch:{ RuntimeException -> 0x0199 }
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzc()     // Catch:{ RuntimeException -> 0x0199 }
            java.lang.String r3 = "Referrer does not contain valid parameters"
            r0.zza(r3, r2)     // Catch:{ RuntimeException -> 0x0199 }
        L_0x014d:
            com.google.android.gms.measurement.internal.zzjq r0 = r1.zza     // Catch:{ RuntimeException -> 0x0199 }
            r2 = 0
            r0.zza((java.lang.String) r13, (java.lang.String) r10, (java.lang.Object) r2, (boolean) r6)     // Catch:{ RuntimeException -> 0x0199 }
            return
        L_0x0154:
            boolean r0 = r2.contains(r14)     // Catch:{ RuntimeException -> 0x0199 }
            if (r0 == 0) goto L_0x017d
            boolean r0 = r2.contains(r12)     // Catch:{ RuntimeException -> 0x0199 }
            if (r0 != 0) goto L_0x017c
            boolean r0 = r2.contains(r11)     // Catch:{ RuntimeException -> 0x0199 }
            if (r0 != 0) goto L_0x017c
            boolean r0 = r2.contains(r9)     // Catch:{ RuntimeException -> 0x0199 }
            if (r0 != 0) goto L_0x017c
            java.lang.String r0 = "utm_term"
            boolean r0 = r2.contains(r0)     // Catch:{ RuntimeException -> 0x0199 }
            if (r0 != 0) goto L_0x017c
            java.lang.String r0 = "utm_content"
            boolean r0 = r2.contains(r0)     // Catch:{ RuntimeException -> 0x0199 }
            if (r0 == 0) goto L_0x017d
        L_0x017c:
            r5 = r6
        L_0x017d:
            if (r5 != 0) goto L_0x018d
            com.google.android.gms.measurement.internal.zzjq r0 = r1.zza     // Catch:{ RuntimeException -> 0x0199 }
            com.google.android.gms.measurement.internal.zzgo r0 = r0.zzj()     // Catch:{ RuntimeException -> 0x0199 }
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzc()     // Catch:{ RuntimeException -> 0x0199 }
            r0.zza(r8)     // Catch:{ RuntimeException -> 0x0199 }
            return
        L_0x018d:
            boolean r0 = android.text.TextUtils.isEmpty(r21)     // Catch:{ RuntimeException -> 0x0199 }
            if (r0 != 0) goto L_0x0198
            com.google.android.gms.measurement.internal.zzjq r0 = r1.zza     // Catch:{ RuntimeException -> 0x0199 }
            r0.zza((java.lang.String) r13, (java.lang.String) r10, (java.lang.Object) r2, (boolean) r6)     // Catch:{ RuntimeException -> 0x0199 }
        L_0x0198:
            return
        L_0x0199:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzjq r1 = r1.zza
            com.google.android.gms.measurement.internal.zzgo r1 = r1.zzj()
            com.google.android.gms.measurement.internal.zzgq r1 = r1.zzg()
            java.lang.String r2 = "Throwable caught in handleReferrerForOnActivityCreated"
            r1.zza(r2, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzkz.zza(com.google.android.gms.measurement.internal.zzkz, boolean, android.net.Uri, java.lang.String, java.lang.String):void");
    }

    zzkz(zzjq zzjq) {
        this.zza = zzjq;
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        try {
            this.zza.zzj().zzp().zza("onActivityCreated");
            Intent intent = activity.getIntent();
            if (intent != null) {
                Uri data = intent.getData();
                if (data == null || !data.isHierarchical()) {
                    Bundle extras = intent.getExtras();
                    if (extras != null) {
                        String string = extras.getString("com.android.vending.referral_url");
                        if (!TextUtils.isEmpty(string)) {
                            data = Uri.parse(string);
                        }
                    }
                    data = null;
                }
                Uri uri = data;
                if (uri != null) {
                    if (uri.isHierarchical()) {
                        this.zza.zzq();
                        this.zza.zzl().zzb((Runnable) new zzlc(this, bundle == null, uri, zzos.zza(intent) ? "gs" : "auto", uri.getQueryParameter("referrer")));
                        this.zza.zzn().zza(activity, bundle);
                        return;
                    }
                }
                this.zza.zzn().zza(activity, bundle);
            }
        } catch (RuntimeException e) {
            this.zza.zzj().zzg().zza("Throwable caught in onActivityCreated", e);
        } finally {
            this.zza.zzn().zza(activity, bundle);
        }
    }

    public final void onActivityDestroyed(Activity activity) {
        this.zza.zzn().zza(activity);
    }

    public final void onActivityPaused(Activity activity) {
        this.zza.zzn().zzb(activity);
        zznb zzp = this.zza.zzp();
        zzp.zzl().zzb((Runnable) new zznd(zzp, zzp.zzb().elapsedRealtime()));
    }

    public final void onActivityResumed(Activity activity) {
        zznb zzp = this.zza.zzp();
        zzp.zzl().zzb((Runnable) new zzne(zzp, zzp.zzb().elapsedRealtime()));
        this.zza.zzn().zzc(activity);
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        this.zza.zzn().zzb(activity, bundle);
    }
}

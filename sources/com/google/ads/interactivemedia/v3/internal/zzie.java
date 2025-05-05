package com.google.ads.interactivemedia.v3.internal;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzie implements Runnable, zzia {
    final CountDownLatch zza;
    private final AtomicReference zzb = new AtomicReference();
    private Context zzc;
    /* access modifiers changed from: private */
    public final zznt zzd;
    private final Executor zze;
    private final zzm zzf;
    private final boolean zzg;
    private final List zzh;

    public zzie(Context context, Executor executor, zzm zzm) {
        boolean z = true;
        this.zza = new CountDownLatch(1);
        this.zzh = new ArrayList();
        this.zzf = zzm;
        this.zzc = context;
        this.zze = executor;
        zzmj.zza(context);
        z = (!((Boolean) zzls.zzc().zza(zzmj.zzc)).booleanValue() || !zzm.zzk()) ? false : z;
        this.zzg = z;
        this.zzd = zznt.zza(context, executor, z);
        executor.execute(this);
    }

    private final void zzd() {
        if (!this.zzh.isEmpty() && this.zzb.get() != null) {
            for (Object[] objArr : this.zzh) {
                int length = objArr.length;
                if (length == 1) {
                    ((zzia) this.zzb.get()).zzk((MotionEvent) objArr[0]);
                } else if (length == 3) {
                    ((zzia) this.zzb.get()).zzl(((Integer) objArr[0]).intValue(), ((Integer) objArr[1]).intValue(), ((Integer) objArr[2]).intValue());
                }
            }
            this.zzh.clear();
        }
    }

    private final boolean zzi() {
        this.zzb.set(zzih.zzu(zzj(this.zzc), new zzif(this.zzf)));
        return true;
    }

    private static final Context zzj(Context context) {
        Context applicationContext = context.getApplicationContext();
        return applicationContext == null ? context : applicationContext;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x004c A[Catch:{ NullPointerException -> 0x0095, all -> 0x0093 }] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0062 A[Catch:{ NullPointerException -> 0x0095, all -> 0x0093 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r11 = this;
            long r0 = java.lang.System.currentTimeMillis()
            r2 = 0
            com.google.ads.interactivemedia.v3.internal.zzm r3 = r11.zzf     // Catch:{ NullPointerException -> 0x0095 }
            int r3 = r3.zzn()     // Catch:{ NullPointerException -> 0x0095 }
            int r3 = r3 + -1
            r4 = 3
            r5 = 2
            if (r3 == r5) goto L_0x0013
        L_0x0011:
            r3 = r5
            goto L_0x0048
        L_0x0013:
            android.content.Context r3 = r11.zzc     // Catch:{ NullPointerException -> 0x0095 }
            com.google.ads.interactivemedia.v3.internal.zznt r6 = r11.zzd     // Catch:{ NullPointerException -> 0x0095 }
            com.google.ads.interactivemedia.v3.internal.zzid r7 = new com.google.ads.interactivemedia.v3.internal.zzid     // Catch:{ NullPointerException -> 0x0095 }
            r7.<init>(r11)     // Catch:{ NullPointerException -> 0x0095 }
            com.google.ads.interactivemedia.v3.internal.zzpd r8 = new com.google.ads.interactivemedia.v3.internal.zzpd     // Catch:{ NullPointerException -> 0x0095 }
            android.content.Context r9 = r11.zzc     // Catch:{ NullPointerException -> 0x0095 }
            int r3 = com.google.ads.interactivemedia.v3.internal.zzol.zzb(r3, r6)     // Catch:{ NullPointerException -> 0x0095 }
            com.google.ads.interactivemedia.v3.internal.zzma r6 = com.google.ads.interactivemedia.v3.internal.zzmj.zzb     // Catch:{ NullPointerException -> 0x0095 }
            com.google.ads.interactivemedia.v3.internal.zzmh r10 = com.google.ads.interactivemedia.v3.internal.zzls.zzc()     // Catch:{ NullPointerException -> 0x0095 }
            java.lang.Object r6 = r10.zza(r6)     // Catch:{ NullPointerException -> 0x0095 }
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch:{ NullPointerException -> 0x0095 }
            boolean r6 = r6.booleanValue()     // Catch:{ NullPointerException -> 0x0095 }
            r8.<init>(r9, r3, r7, r6)     // Catch:{ NullPointerException -> 0x0095 }
            r3 = 1
            boolean r3 = r8.zzd(r3)     // Catch:{ NullPointerException -> 0x0095 }
            if (r3 != 0) goto L_0x0047
            com.google.ads.interactivemedia.v3.internal.zzm r3 = r11.zzf     // Catch:{ NullPointerException -> 0x0095 }
            boolean r3 = r3.zzl()     // Catch:{ NullPointerException -> 0x0095 }
            if (r3 == 0) goto L_0x0047
            goto L_0x0011
        L_0x0047:
            r3 = r4
        L_0x0048:
            int r3 = r3 + -1
            if (r3 == r5) goto L_0x0062
            r11.zzi()     // Catch:{ NullPointerException -> 0x0095 }
            com.google.ads.interactivemedia.v3.internal.zzm r3 = r11.zzf     // Catch:{ NullPointerException -> 0x0095 }
            int r3 = r3.zzn()     // Catch:{ NullPointerException -> 0x0095 }
            if (r3 != r4) goto L_0x00ad
            java.util.concurrent.Executor r3 = r11.zze     // Catch:{ NullPointerException -> 0x0095 }
            com.google.ads.interactivemedia.v3.internal.zzic r4 = new com.google.ads.interactivemedia.v3.internal.zzic     // Catch:{ NullPointerException -> 0x0095 }
            r4.<init>(r11)     // Catch:{ NullPointerException -> 0x0095 }
            r3.execute(r4)     // Catch:{ NullPointerException -> 0x0095 }
            goto L_0x00ad
        L_0x0062:
            com.google.ads.interactivemedia.v3.internal.zzm r3 = r11.zzf     // Catch:{ NullPointerException -> 0x0095 }
            java.lang.String r3 = r3.zze()     // Catch:{ NullPointerException -> 0x0095 }
            android.content.Context r4 = r11.zzc     // Catch:{ NullPointerException -> 0x0095 }
            android.content.Context r4 = zzj(r4)     // Catch:{ NullPointerException -> 0x0095 }
            java.util.concurrent.Executor r5 = r11.zze     // Catch:{ NullPointerException -> 0x0095 }
            com.google.ads.interactivemedia.v3.internal.zzm r6 = r11.zzf     // Catch:{ NullPointerException -> 0x0095 }
            boolean r6 = r6.zzj()     // Catch:{ NullPointerException -> 0x0095 }
            boolean r7 = r11.zzg     // Catch:{ NullPointerException -> 0x0095 }
            com.google.ads.interactivemedia.v3.internal.zzhx r3 = com.google.ads.interactivemedia.v3.internal.zzhx.zzb(r3, r4, r5, r6, r7)     // Catch:{ NullPointerException -> 0x0095 }
            java.util.concurrent.atomic.AtomicReference r4 = r11.zzb     // Catch:{ NullPointerException -> 0x0095 }
            r4.set(r3)     // Catch:{ NullPointerException -> 0x0095 }
            boolean r3 = r3.zzr()     // Catch:{ NullPointerException -> 0x0095 }
            if (r3 != 0) goto L_0x00ad
            com.google.ads.interactivemedia.v3.internal.zzm r3 = r11.zzf     // Catch:{ NullPointerException -> 0x0095 }
            boolean r3 = r3.zzl()     // Catch:{ NullPointerException -> 0x0095 }
            if (r3 == 0) goto L_0x00ad
            r11.zzi()     // Catch:{ NullPointerException -> 0x0095 }
            goto L_0x00ad
        L_0x0093:
            r0 = move-exception
            goto L_0x00b5
        L_0x0095:
            r3 = move-exception
            com.google.ads.interactivemedia.v3.internal.zzm r4 = r11.zzf     // Catch:{ all -> 0x0093 }
            boolean r4 = r4.zzl()     // Catch:{ all -> 0x0093 }
            if (r4 == 0) goto L_0x00a1
            r11.zzi()     // Catch:{ all -> 0x0093 }
        L_0x00a1:
            com.google.ads.interactivemedia.v3.internal.zznt r4 = r11.zzd     // Catch:{ all -> 0x0093 }
            long r5 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0093 }
            long r5 = r5 - r0
            r0 = 2031(0x7ef, float:2.846E-42)
            r4.zzc(r0, r5, r3)     // Catch:{ all -> 0x0093 }
        L_0x00ad:
            r11.zzc = r2
            java.util.concurrent.CountDownLatch r0 = r11.zza
            r0.countDown()
            return
        L_0x00b5:
            r11.zzc = r2
            java.util.concurrent.CountDownLatch r1 = r11.zza
            r1.countDown()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.zzie.run():void");
    }

    public final zzia zza() {
        return (zzia) this.zzb.get();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzc() {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            zzhx.zza(this.zzf.zze(), zzj(this.zzc), this.zzf.zzj(), this.zzg).zzo();
        } catch (NullPointerException e) {
            this.zzd.zzc(2027, System.currentTimeMillis() - currentTimeMillis, e);
        }
    }

    @Deprecated
    public final String zze(Context context, String str, View view, Activity activity) {
        if (!zzs()) {
            return "";
        }
        zzd();
        return ((zzia) this.zzb.get()).zze(zzj(context), str, view, activity);
    }

    public final String zzf(Context context) {
        return zzg(context, (byte[]) null);
    }

    public final String zzg(Context context, byte[] bArr) {
        if (!zzs()) {
            return "";
        }
        zzd();
        return ((zzia) this.zzb.get()).zzf(zzj(context));
    }

    public final String zzh(Context context, View view, Activity activity) {
        return zzs() ? ((zzia) this.zzb.get()).zzh(context, view, activity) : "";
    }

    public final void zzk(MotionEvent motionEvent) {
        if (this.zzb.get() != null) {
            zzd();
            ((zzia) this.zzb.get()).zzk(motionEvent);
            return;
        }
        this.zzh.add(new Object[]{motionEvent});
    }

    public final void zzl(int i, int i2, int i3) {
        if (this.zzb.get() != null) {
            zzd();
            ((zzia) this.zzb.get()).zzl(i, i2, i3);
            return;
        }
        this.zzh.add(new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)});
    }

    public final void zzn(View view) {
        if (this.zzb.get() != null) {
            ((zzia) this.zzb.get()).zzn(view);
        }
    }

    public final boolean zzq() {
        return this.zza.getCount() == 0 && this.zzb.get() != null && ((zzia) this.zzb.get()).zzq();
    }

    public final boolean zzs() {
        try {
            this.zza.await();
            if (this.zzb.get() == null || !((zzia) this.zzb.get()).zzs()) {
                return false;
            }
            return true;
        } catch (InterruptedException unused) {
            return false;
        }
    }
}

package com.google.ads.interactivemedia.v3.internal;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import androidx.media3.common.PlaybackException;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzhx implements zzia {
    private static zzhx zzb;
    volatile long zza = 0;
    private final Context zzc;
    private final zzow zzd;
    private final zzpd zze;
    private final zzpf zzf;
    private final zzjd zzg;
    /* access modifiers changed from: private */
    public final zznt zzh;
    private final Executor zzi;
    private final zzpc zzj;
    private final CountDownLatch zzk;
    private final zzjs zzl;
    private final zzjk zzm;
    /* access modifiers changed from: private */
    public final Object zzn = new Object();
    /* access modifiers changed from: private */
    public volatile boolean zzo;
    private volatile boolean zzp = false;
    private final int zzq;

    zzhx(Context context, zznt zznt, zzow zzow, zzpd zzpd, zzpf zzpf, zzjd zzjd, Executor executor, zzno zzno, int i, zzjs zzjs, zzjk zzjk, zzjb zzjb) {
        this.zzc = context;
        this.zzh = zznt;
        this.zzd = zzow;
        this.zze = zzpd;
        this.zzf = zzpf;
        this.zzg = zzjd;
        this.zzi = executor;
        this.zzq = i;
        this.zzl = zzjs;
        this.zzm = zzjk;
        this.zzp = false;
        this.zzk = new CountDownLatch(1);
        this.zzj = new zzhv(this, zzno);
    }

    public static synchronized zzhx zza(String str, Context context, boolean z, boolean z2) {
        zzhx zzb2;
        synchronized (zzhx.class) {
            zzb2 = zzb(str, context, Executors.newCachedThreadPool(), z, z2);
        }
        return zzb2;
    }

    @Deprecated
    public static synchronized zzhx zzb(String str, Context context, Executor executor, boolean z, boolean z2) {
        zzhx zzhx;
        Context context2 = context;
        Executor executor2 = executor;
        synchronized (zzhx.class) {
            if (zzb == null) {
                zznu zzc2 = zznv.zzc();
                zzc2.zza(str);
                zzc2.zzg(z);
                zznv zzh2 = zzc2.zzh();
                zznt zza2 = zznt.zza(context2, executor2, z2);
                zzim zzc3 = ((Boolean) zzls.zzc().zza(zzmj.zzx)).booleanValue() ? zzim.zzc(context) : null;
                zzjs zzd2 = ((Boolean) zzls.zzc().zza(zzmj.zzy)).booleanValue() ? zzjs.zzd(context, executor) : null;
                zzjk zzjk = ((Boolean) zzls.zzc().zza(zzmj.zzp)).booleanValue() ? new zzjk() : null;
                zzjb zzjb = ((Boolean) zzls.zzc().zza(zzmj.zzs)).booleanValue() ? new zzjb() : null;
                zzok zze2 = zzok.zze(context2, executor2, zza2, zzh2);
                zzjc zzjc = new zzjc(context2);
                zzjd zzjd = new zzjd(zzh2, zze2, new zzjq(context2, zzjc), zzjc, zzc3, zzd2, zzjk, zzjb);
                int zzb2 = zzol.zzb(context2, zza2);
                zzno zzno = new zzno();
                zzhx zzhx2 = new zzhx(context, zza2, new zzow(context2, zzb2), new zzpd(context2, zzb2, new zzhu(zza2), ((Boolean) zzls.zzc().zza(zzmj.zzb)).booleanValue()), new zzpf(context2, zzjd, zza2, zzno), zzjd, executor, zzno, zzb2, zzd2, zzjk, zzjb);
                zzb = zzhx2;
                zzhx2.zzm();
                zzb.zzo();
            }
            zzhx = zzb;
        }
        return zzhx;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x009a, code lost:
        if (r4.zzc().zzi().equals(r5.zzi()) != false) goto L_0x00f5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* bridge */ /* synthetic */ void zzj(com.google.ads.interactivemedia.v3.internal.zzhx r12) {
        /*
            long r0 = java.lang.System.currentTimeMillis()
            r2 = 1
            com.google.ads.interactivemedia.v3.internal.zzov r3 = r12.zzu(r2)
            if (r3 == 0) goto L_0x001e
            com.google.ads.interactivemedia.v3.internal.zzlf r4 = r3.zza()
            java.lang.String r4 = r4.zzj()
            com.google.ads.interactivemedia.v3.internal.zzlf r3 = r3.zza()
            java.lang.String r3 = r3.zzi()
            r9 = r3
            r8 = r4
            goto L_0x0021
        L_0x001e:
            r4 = 0
            r8 = r4
            r9 = r8
        L_0x0021:
            android.content.Context r5 = r12.zzc     // Catch:{ zzaeg -> 0x011e }
            r6 = 1
            int r7 = r12.zzq     // Catch:{ zzaeg -> 0x011e }
            java.lang.String r10 = "1"
            com.google.ads.interactivemedia.v3.internal.zznt r11 = r12.zzh     // Catch:{ zzaeg -> 0x011e }
            com.google.ads.interactivemedia.v3.internal.zzpa r3 = com.google.ads.interactivemedia.v3.internal.zzod.zza(r5, r6, r7, r8, r9, r10, r11)     // Catch:{ zzaeg -> 0x011e }
            byte[] r4 = r3.zzb     // Catch:{ zzaeg -> 0x011e }
            if (r4 == 0) goto L_0x010f
            int r5 = r4.length     // Catch:{ zzaeg -> 0x011e }
            if (r5 != 0) goto L_0x0037
            goto L_0x010f
        L_0x0037:
            r6 = 0
            com.google.ads.interactivemedia.v3.internal.zzacw r4 = com.google.ads.interactivemedia.v3.internal.zzacw.zzp(r4, r6, r5)     // Catch:{ NullPointerException -> 0x0102 }
            com.google.ads.interactivemedia.v3.internal.zzadk r5 = com.google.ads.interactivemedia.v3.internal.zzadk.zza()     // Catch:{ NullPointerException -> 0x0102 }
            com.google.ads.interactivemedia.v3.internal.zzlc r4 = com.google.ads.interactivemedia.v3.internal.zzlc.zzb(r4, r5)     // Catch:{ NullPointerException -> 0x0102 }
            com.google.ads.interactivemedia.v3.internal.zzlf r5 = r4.zzc()     // Catch:{ zzaeg -> 0x011e }
            java.lang.String r5 = r5.zzj()     // Catch:{ zzaeg -> 0x011e }
            boolean r5 = r5.isEmpty()     // Catch:{ zzaeg -> 0x011e }
            if (r5 != 0) goto L_0x00f5
            com.google.ads.interactivemedia.v3.internal.zzlf r5 = r4.zzc()     // Catch:{ zzaeg -> 0x011e }
            java.lang.String r5 = r5.zzi()     // Catch:{ zzaeg -> 0x011e }
            boolean r5 = r5.isEmpty()     // Catch:{ zzaeg -> 0x011e }
            if (r5 != 0) goto L_0x00f5
            com.google.ads.interactivemedia.v3.internal.zzacw r5 = r4.zzd()     // Catch:{ zzaeg -> 0x011e }
            byte[] r5 = r5.zzt()     // Catch:{ zzaeg -> 0x011e }
            int r5 = r5.length     // Catch:{ zzaeg -> 0x011e }
            if (r5 != 0) goto L_0x006d
            goto L_0x00f5
        L_0x006d:
            com.google.ads.interactivemedia.v3.internal.zzov r5 = r12.zzu(r2)     // Catch:{ zzaeg -> 0x011e }
            if (r5 != 0) goto L_0x0074
            goto L_0x009c
        L_0x0074:
            com.google.ads.interactivemedia.v3.internal.zzlf r5 = r5.zza()     // Catch:{ zzaeg -> 0x011e }
            com.google.ads.interactivemedia.v3.internal.zzlf r6 = r4.zzc()     // Catch:{ zzaeg -> 0x011e }
            java.lang.String r6 = r6.zzj()     // Catch:{ zzaeg -> 0x011e }
            java.lang.String r7 = r5.zzj()     // Catch:{ zzaeg -> 0x011e }
            boolean r6 = r6.equals(r7)     // Catch:{ zzaeg -> 0x011e }
            if (r6 == 0) goto L_0x009c
            com.google.ads.interactivemedia.v3.internal.zzlf r6 = r4.zzc()     // Catch:{ zzaeg -> 0x011e }
            java.lang.String r6 = r6.zzi()     // Catch:{ zzaeg -> 0x011e }
            java.lang.String r5 = r5.zzi()     // Catch:{ zzaeg -> 0x011e }
            boolean r5 = r6.equals(r5)     // Catch:{ zzaeg -> 0x011e }
            if (r5 != 0) goto L_0x00f5
        L_0x009c:
            com.google.ads.interactivemedia.v3.internal.zzpc r5 = r12.zzj     // Catch:{ zzaeg -> 0x011e }
            int r3 = r3.zzc     // Catch:{ zzaeg -> 0x011e }
            com.google.ads.interactivemedia.v3.internal.zzma r6 = com.google.ads.interactivemedia.v3.internal.zzmj.zza     // Catch:{ zzaeg -> 0x011e }
            com.google.ads.interactivemedia.v3.internal.zzmh r7 = com.google.ads.interactivemedia.v3.internal.zzls.zzc()     // Catch:{ zzaeg -> 0x011e }
            java.lang.Object r6 = r7.zza(r6)     // Catch:{ zzaeg -> 0x011e }
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch:{ zzaeg -> 0x011e }
            boolean r6 = r6.booleanValue()     // Catch:{ zzaeg -> 0x011e }
            if (r6 == 0) goto L_0x00c6
            r6 = 3
            if (r3 != r6) goto L_0x00bc
            com.google.ads.interactivemedia.v3.internal.zzpd r3 = r12.zze     // Catch:{ zzaeg -> 0x011e }
            boolean r3 = r3.zza(r4)     // Catch:{ zzaeg -> 0x011e }
            goto L_0x00cc
        L_0x00bc:
            r6 = 4
            if (r3 != r6) goto L_0x00ce
            com.google.ads.interactivemedia.v3.internal.zzpd r3 = r12.zze     // Catch:{ zzaeg -> 0x011e }
            boolean r3 = r3.zzb(r4, r5)     // Catch:{ zzaeg -> 0x011e }
            goto L_0x00cc
        L_0x00c6:
            com.google.ads.interactivemedia.v3.internal.zzow r3 = r12.zzd     // Catch:{ zzaeg -> 0x011e }
            boolean r3 = r3.zza(r4, r5)     // Catch:{ zzaeg -> 0x011e }
        L_0x00cc:
            if (r3 != 0) goto L_0x00db
        L_0x00ce:
            com.google.ads.interactivemedia.v3.internal.zznt r2 = r12.zzh     // Catch:{ zzaeg -> 0x011e }
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ zzaeg -> 0x011e }
            long r3 = r3 - r0
            r5 = 4009(0xfa9, float:5.618E-42)
            r2.zzd(r5, r3)     // Catch:{ zzaeg -> 0x011e }
            goto L_0x012b
        L_0x00db:
            com.google.ads.interactivemedia.v3.internal.zzov r3 = r12.zzu(r2)     // Catch:{ zzaeg -> 0x011e }
            if (r3 == 0) goto L_0x012b
            com.google.ads.interactivemedia.v3.internal.zzpf r4 = r12.zzf     // Catch:{ zzaeg -> 0x011e }
            boolean r3 = r4.zzc(r3)     // Catch:{ zzaeg -> 0x011e }
            if (r3 == 0) goto L_0x00eb
            r12.zzp = r2     // Catch:{ zzaeg -> 0x011e }
        L_0x00eb:
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ zzaeg -> 0x011e }
            r4 = 1000(0x3e8, double:4.94E-321)
            long r2 = r2 / r4
            r12.zza = r2     // Catch:{ zzaeg -> 0x011e }
            goto L_0x012b
        L_0x00f5:
            com.google.ads.interactivemedia.v3.internal.zznt r2 = r12.zzh     // Catch:{ zzaeg -> 0x011e }
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ zzaeg -> 0x011e }
            long r3 = r3 - r0
            r5 = 5010(0x1392, float:7.02E-42)
            r2.zzd(r5, r3)     // Catch:{ zzaeg -> 0x011e }
            goto L_0x012b
        L_0x0102:
            com.google.ads.interactivemedia.v3.internal.zznt r2 = r12.zzh     // Catch:{ zzaeg -> 0x011e }
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ zzaeg -> 0x011e }
            long r3 = r3 - r0
            r5 = 2030(0x7ee, float:2.845E-42)
            r2.zzd(r5, r3)     // Catch:{ zzaeg -> 0x011e }
            goto L_0x012b
        L_0x010f:
            com.google.ads.interactivemedia.v3.internal.zznt r2 = r12.zzh     // Catch:{ zzaeg -> 0x011e }
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ zzaeg -> 0x011e }
            long r3 = r3 - r0
            r5 = 5009(0x1391, float:7.019E-42)
            r2.zzd(r5, r3)     // Catch:{ zzaeg -> 0x011e }
            goto L_0x012b
        L_0x011c:
            r0 = move-exception
            goto L_0x0131
        L_0x011e:
            r2 = move-exception
            com.google.ads.interactivemedia.v3.internal.zznt r3 = r12.zzh     // Catch:{ all -> 0x011c }
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x011c }
            long r4 = r4 - r0
            r0 = 4002(0xfa2, float:5.608E-42)
            r3.zzc(r0, r4, r2)     // Catch:{ all -> 0x011c }
        L_0x012b:
            java.util.concurrent.CountDownLatch r12 = r12.zzk
            r12.countDown()
            return
        L_0x0131:
            java.util.concurrent.CountDownLatch r12 = r12.zzk
            r12.countDown()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.zzhx.zzj(com.google.ads.interactivemedia.v3.internal.zzhx):void");
    }

    private final void zzt() {
        zzjs zzjs = this.zzl;
        if (zzjs != null) {
            zzjs.zzh();
        }
    }

    private final zzov zzu(int i) {
        if (!zzol.zza(this.zzq)) {
            return null;
        }
        if (((Boolean) zzls.zzc().zza(zzmj.zza)).booleanValue()) {
            return this.zze.zzc(1);
        }
        return this.zzd.zzc(1);
    }

    public final String zze(Context context, String str, View view, Activity activity) {
        zzt();
        if (((Boolean) zzls.zzc().zza(zzmj.zzp)).booleanValue()) {
            this.zzm.zzi();
        }
        zzo();
        zznw zza2 = this.zzf.zza();
        if (zza2 == null) {
            return "";
        }
        long currentTimeMillis = System.currentTimeMillis();
        String zza3 = zza2.zza(context, (String) null, str, view, activity);
        this.zzh.zzf(5000, System.currentTimeMillis() - currentTimeMillis, zza3, (Map) null);
        return zza3;
    }

    public final String zzf(Context context) {
        zzt();
        if (((Boolean) zzls.zzc().zza(zzmj.zzp)).booleanValue()) {
            this.zzm.zzj();
        }
        zzo();
        zznw zza2 = this.zzf.zza();
        if (zza2 == null) {
            return "";
        }
        long currentTimeMillis = System.currentTimeMillis();
        String zzc2 = zza2.zzc(context, (String) null);
        this.zzh.zzf(5001, System.currentTimeMillis() - currentTimeMillis, zzc2, (Map) null);
        return zzc2;
    }

    public final String zzg(Context context, byte[] bArr) {
        throw null;
    }

    public final String zzh(Context context, View view, Activity activity) {
        zzt();
        if (((Boolean) zzls.zzc().zza(zzmj.zzp)).booleanValue()) {
            this.zzm.zzk(context, view);
        }
        zzo();
        zznw zza2 = this.zzf.zza();
        if (zza2 == null) {
            return "";
        }
        long currentTimeMillis = System.currentTimeMillis();
        String zzb2 = zza2.zzb(context, (String) null, view, activity);
        this.zzh.zzf(PlaybackException.ERROR_CODE_AUDIO_TRACK_WRITE_FAILED, System.currentTimeMillis() - currentTimeMillis, zzb2, (Map) null);
        return zzb2;
    }

    public final void zzk(MotionEvent motionEvent) {
        zznw zza2 = this.zzf.zza();
        if (zza2 != null) {
            try {
                zza2.zzd((String) null, motionEvent);
            } catch (zzpe e) {
                this.zzh.zzc(e.zza(), -1, e);
            }
        }
    }

    public final void zzl(int i, int i2, int i3) {
        DisplayMetrics displayMetrics;
        if (((Boolean) zzls.zzc().zza(zzmj.zzC)).booleanValue() && (displayMetrics = this.zzc.getResources().getDisplayMetrics()) != null) {
            float f = (float) i;
            float f2 = (float) i2;
            float f3 = f2;
            MotionEvent obtain = MotionEvent.obtain(0, 0, 0, displayMetrics.density * f, displayMetrics.density * f2, 0.0f, 0.0f, 0, 0.0f, 0.0f, 0, 0);
            zzk(obtain);
            obtain.recycle();
            MotionEvent obtain2 = MotionEvent.obtain(0, 0, 2, f * displayMetrics.density, f3 * displayMetrics.density, 0.0f, 0.0f, 0, 0.0f, 0.0f, 0, 0);
            zzk(obtain2);
            obtain2.recycle();
            MotionEvent obtain3 = MotionEvent.obtain(0, (long) i3, 1, f * displayMetrics.density, f3 * displayMetrics.density, 0.0f, 0.0f, 0, 0.0f, 0.0f, 0, 0);
            zzk(obtain3);
            obtain3.recycle();
        }
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zzm() {
        long currentTimeMillis = System.currentTimeMillis();
        zzov zzu = zzu(1);
        if (zzu == null) {
            this.zzh.zzd(4013, System.currentTimeMillis() - currentTimeMillis);
        } else if (this.zzf.zzc(zzu)) {
            this.zzp = true;
            this.zzk.countDown();
        }
    }

    public final void zzn(View view) {
        this.zzg.zzd(view);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzo() {
        /*
            r5 = this;
            boolean r0 = r5.zzo
            if (r0 != 0) goto L_0x0042
            java.lang.Object r0 = r5.zzn
            monitor-enter(r0)
            boolean r1 = r5.zzo     // Catch:{ all -> 0x003f }
            if (r1 != 0) goto L_0x003d
            long r1 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x003f }
            r3 = 1000(0x3e8, double:4.94E-321)
            long r1 = r1 / r3
            long r3 = r5.zza     // Catch:{ all -> 0x003f }
            long r1 = r1 - r3
            r3 = 3600(0xe10, double:1.7786E-320)
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 >= 0) goto L_0x001d
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            return
        L_0x001d:
            com.google.ads.interactivemedia.v3.internal.zzpf r1 = r5.zzf     // Catch:{ all -> 0x003f }
            com.google.ads.interactivemedia.v3.internal.zzov r1 = r1.zzb()     // Catch:{ all -> 0x003f }
            if (r1 == 0) goto L_0x002b
            boolean r1 = r1.zzd(r3)     // Catch:{ all -> 0x003f }
            if (r1 == 0) goto L_0x003d
        L_0x002b:
            int r1 = r5.zzq     // Catch:{ all -> 0x003f }
            boolean r1 = com.google.ads.interactivemedia.v3.internal.zzol.zza(r1)     // Catch:{ all -> 0x003f }
            if (r1 == 0) goto L_0x003d
            java.util.concurrent.Executor r1 = r5.zzi     // Catch:{ all -> 0x003f }
            com.google.ads.interactivemedia.v3.internal.zzhw r2 = new com.google.ads.interactivemedia.v3.internal.zzhw     // Catch:{ all -> 0x003f }
            r2.<init>(r5)     // Catch:{ all -> 0x003f }
            r1.execute(r2)     // Catch:{ all -> 0x003f }
        L_0x003d:
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            return
        L_0x003f:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            throw r1
        L_0x0042:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.zzhx.zzo():void");
    }

    public final boolean zzq() {
        return zzr();
    }

    public final synchronized boolean zzr() {
        return this.zzp;
    }

    public final boolean zzs() {
        try {
            this.zzk.await();
        } catch (InterruptedException unused) {
        }
        return zzr();
    }
}

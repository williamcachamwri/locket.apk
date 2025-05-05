package com.google.ads.interactivemedia.v3.internal;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import java.util.Iterator;
import java.util.LinkedList;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzib implements zzia {
    protected static volatile zzjj zza;
    protected MotionEvent zzb;
    protected final LinkedList zzc = new LinkedList();
    protected long zzd = 0;
    protected long zze = 0;
    protected long zzf = 0;
    protected long zzg = 0;
    protected long zzh = 0;
    protected long zzi = 0;
    protected long zzj = 0;
    protected double zzk;
    protected float zzl;
    protected float zzm;
    protected float zzn;
    protected float zzo;
    protected boolean zzp = false;
    protected DisplayMetrics zzq;
    protected zzjb zzr;
    private double zzs;
    private double zzt;
    private boolean zzu = false;

    protected zzib(Context context) {
        try {
            zzgt.zzd();
            this.zzq = context.getResources().getDisplayMetrics();
            if (((Boolean) zzls.zzc().zza(zzmj.zzs)).booleanValue()) {
                this.zzr = new zzjb();
            }
        } catch (Throwable unused) {
        }
    }

    private final void zzj() {
        this.zzh = 0;
        this.zzd = 0;
        this.zze = 0;
        this.zzf = 0;
        this.zzg = 0;
        this.zzi = 0;
        this.zzj = 0;
        if (!this.zzc.isEmpty()) {
            Iterator it = this.zzc.iterator();
            while (it.hasNext()) {
                ((MotionEvent) it.next()).recycle();
            }
            this.zzc.clear();
        } else {
            MotionEvent motionEvent = this.zzb;
            if (motionEvent != null) {
                motionEvent.recycle();
            }
        }
        this.zzb = null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00a6 A[SYNTHETIC, Splitter:B:45:0x00a6] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.String zzm(android.content.Context r20, java.lang.String r21, int r22, android.view.View r23, android.app.Activity r24, byte[] r25) {
        /*
            r19 = this;
            r1 = r19
            r0 = r20
            r2 = r22
            r3 = r23
            r4 = r24
            long r5 = java.lang.System.currentTimeMillis()
            com.google.ads.interactivemedia.v3.internal.zzma r7 = com.google.ads.interactivemedia.v3.internal.zzmj.zzj
            com.google.ads.interactivemedia.v3.internal.zzmh r8 = com.google.ads.interactivemedia.v3.internal.zzls.zzc()
            java.lang.Object r7 = r8.zza(r7)
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            r8 = 0
            if (r7 == 0) goto L_0x0030
            com.google.ads.interactivemedia.v3.internal.zzjj r9 = zza
            if (r9 == 0) goto L_0x002c
            com.google.ads.interactivemedia.v3.internal.zzjj r9 = zza
            com.google.ads.interactivemedia.v3.internal.zzhz r9 = r9.zzd()
            goto L_0x002d
        L_0x002c:
            r9 = r8
        L_0x002d:
            java.lang.String r10 = "be"
            goto L_0x0032
        L_0x0030:
            r9 = r8
            r10 = r9
        L_0x0032:
            r14 = 1
            r15 = 2
            r13 = 3
            if (r2 != r13) goto L_0x004d
            com.google.ads.interactivemedia.v3.internal.zzan r8 = r1.zzb(r0, r3, r4)     // Catch:{ Exception -> 0x0048 }
            r1.zzu = r14     // Catch:{ Exception -> 0x0041 }
            r0 = 1002(0x3ea, float:1.404E-42)
            r12 = r0
            goto L_0x005e
        L_0x0041:
            r0 = move-exception
            r17 = r0
            r1 = r13
            r18 = r14
            goto L_0x007d
        L_0x0048:
            r0 = move-exception
            r1 = r13
            r18 = r14
            goto L_0x007b
        L_0x004d:
            if (r2 != r15) goto L_0x0056
            com.google.ads.interactivemedia.v3.internal.zzan r0 = r1.zzd(r0, r3, r4)     // Catch:{ Exception -> 0x0048 }
            r3 = 1008(0x3f0, float:1.413E-42)
            goto L_0x005c
        L_0x0056:
            com.google.ads.interactivemedia.v3.internal.zzan r0 = r1.zzc(r0, r8)     // Catch:{ Exception -> 0x0048 }
            r3 = 1000(0x3e8, float:1.401E-42)
        L_0x005c:
            r8 = r0
            r12 = r3
        L_0x005e:
            if (r7 == 0) goto L_0x0078
            if (r9 == 0) goto L_0x0078
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0048 }
            long r3 = r3 - r5
            r0 = -1
            r17 = 0
            r11 = r9
            r1 = r13
            r13 = r0
            r18 = r14
            r14 = r3
            r16 = r10
            r11.zzc(r12, r13, r14, r16, r17)     // Catch:{ Exception -> 0x0076 }
            goto L_0x0079
        L_0x0076:
            r0 = move-exception
            goto L_0x007b
        L_0x0078:
            r1 = r13
        L_0x0079:
            r3 = 2
            goto L_0x00a0
        L_0x007b:
            r17 = r0
        L_0x007d:
            if (r7 == 0) goto L_0x0079
            if (r9 == 0) goto L_0x0079
            if (r2 != r1) goto L_0x0088
            r0 = 1003(0x3eb, float:1.406E-42)
            r12 = r0
            r3 = 2
            goto L_0x0094
        L_0x0088:
            r3 = 2
            if (r2 != r3) goto L_0x008f
            r0 = 1009(0x3f1, float:1.414E-42)
            r12 = r0
            goto L_0x0094
        L_0x008f:
            r0 = 1001(0x3e9, float:1.403E-42)
            r12 = r0
            r2 = r18
        L_0x0094:
            r13 = -1
            long r14 = java.lang.System.currentTimeMillis()
            long r14 = r14 - r5
            r11 = r9
            r16 = r10
            r11.zzc(r12, r13, r14, r16, r17)
        L_0x00a0:
            long r4 = java.lang.System.currentTimeMillis()
            if (r8 == 0) goto L_0x00e7
            com.google.ads.interactivemedia.v3.internal.zzady r0 = r8.zzal()     // Catch:{ Exception -> 0x00ed }
            com.google.ads.interactivemedia.v3.internal.zzbp r0 = (com.google.ads.interactivemedia.v3.internal.zzbp) r0     // Catch:{ Exception -> 0x00ed }
            int r0 = r0.zzax()     // Catch:{ Exception -> 0x00ed }
            if (r0 != 0) goto L_0x00b3
            goto L_0x00e7
        L_0x00b3:
            com.google.ads.interactivemedia.v3.internal.zzady r0 = r8.zzal()     // Catch:{ Exception -> 0x00ed }
            com.google.ads.interactivemedia.v3.internal.zzbp r0 = (com.google.ads.interactivemedia.v3.internal.zzbp) r0     // Catch:{ Exception -> 0x00ed }
            int r6 = com.google.ads.interactivemedia.v3.internal.zzgt.zzc     // Catch:{ Exception -> 0x00ed }
            byte[] r0 = r0.zzav()     // Catch:{ Exception -> 0x00ed }
            r6 = r21
            java.lang.String r0 = com.google.ads.interactivemedia.v3.internal.zzgt.zza(r0, r6)     // Catch:{ Exception -> 0x00ed }
            if (r7 == 0) goto L_0x0114
            if (r9 == 0) goto L_0x0114
            if (r2 != r1) goto L_0x00cf
            r6 = 1006(0x3ee, float:1.41E-42)
        L_0x00cd:
            r12 = r6
            goto L_0x00d7
        L_0x00cf:
            if (r2 != r3) goto L_0x00d4
            r6 = 1010(0x3f2, float:1.415E-42)
            goto L_0x00cd
        L_0x00d4:
            r6 = 1004(0x3ec, float:1.407E-42)
            goto L_0x00cd
        L_0x00d7:
            long r13 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x00ed }
            long r14 = r13 - r4
            r13 = -1
            r17 = 0
            r11 = r9
            r16 = r10
            r11.zzc(r12, r13, r14, r16, r17)     // Catch:{ Exception -> 0x00ed }
            goto L_0x0114
        L_0x00e7:
            r0 = 5
            java.lang.String r0 = java.lang.Integer.toString(r0)     // Catch:{ Exception -> 0x00ed }
            goto L_0x0114
        L_0x00ed:
            r0 = move-exception
            r17 = r0
            r0 = 7
            java.lang.String r0 = java.lang.Integer.toString(r0)
            if (r7 == 0) goto L_0x0114
            if (r9 == 0) goto L_0x0114
            if (r2 != r1) goto L_0x00ff
            r1 = 1007(0x3ef, float:1.411E-42)
        L_0x00fd:
            r12 = r1
            goto L_0x0107
        L_0x00ff:
            if (r2 != r3) goto L_0x0104
            r1 = 1011(0x3f3, float:1.417E-42)
            goto L_0x00fd
        L_0x0104:
            r1 = 1005(0x3ed, float:1.408E-42)
            goto L_0x00fd
        L_0x0107:
            r13 = -1
            long r1 = java.lang.System.currentTimeMillis()
            long r14 = r1 - r4
            r11 = r9
            r16 = r10
            r11.zzc(r12, r13, r14, r16, r17)
        L_0x0114:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.zzib.zzm(android.content.Context, java.lang.String, int, android.view.View, android.app.Activity, byte[]):java.lang.String");
    }

    /* access modifiers changed from: protected */
    public abstract long zza(StackTraceElement[] stackTraceElementArr) throws zziz;

    /* access modifiers changed from: protected */
    public abstract zzan zzb(Context context, View view, Activity activity);

    /* access modifiers changed from: protected */
    public abstract zzan zzc(Context context, zzy zzy);

    /* access modifiers changed from: protected */
    public abstract zzan zzd(Context context, View view, Activity activity);

    public final String zze(Context context, String str, View view, Activity activity) {
        return zzm(context, str, 3, view, activity, (byte[]) null);
    }

    public final String zzf(Context context) {
        if (!zzjm.zzc()) {
            return zzm(context, (String) null, 1, (View) null, (Activity) null, (byte[]) null);
        }
        throw new IllegalStateException("The caller must not be called from the UI thread.");
    }

    public final String zzg(Context context, byte[] bArr) {
        throw null;
    }

    public final String zzh(Context context, View view, Activity activity) {
        return zzm(context, (String) null, 2, view, activity, (byte[]) null);
    }

    /* access modifiers changed from: protected */
    public abstract zzjl zzi(MotionEvent motionEvent) throws zziz;

    public final synchronized void zzk(MotionEvent motionEvent) {
        Long l;
        if (this.zzu) {
            zzj();
            this.zzu = false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.zzk = 0.0d;
            this.zzs = (double) motionEvent.getRawX();
            this.zzt = (double) motionEvent.getRawY();
        } else if (action == 1 || action == 2) {
            double rawX = (double) motionEvent.getRawX();
            double rawY = (double) motionEvent.getRawY();
            double d = rawX - this.zzs;
            double d2 = rawY - this.zzt;
            this.zzk += Math.sqrt((d * d) + (d2 * d2));
            this.zzs = rawX;
            this.zzt = rawY;
        }
        int action2 = motionEvent.getAction();
        if (action2 == 0) {
            this.zzl = motionEvent.getX();
            this.zzm = motionEvent.getY();
            this.zzn = motionEvent.getRawX();
            this.zzo = motionEvent.getRawY();
            this.zzd++;
        } else if (action2 == 1) {
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            this.zzb = obtain;
            this.zzc.add(obtain);
            if (this.zzc.size() > 6) {
                ((MotionEvent) this.zzc.remove()).recycle();
            }
            this.zzf++;
            this.zzh = zza(new Throwable().getStackTrace());
        } else if (action2 == 2) {
            this.zze += (long) (motionEvent.getHistorySize() + 1);
            try {
                zzjl zzi2 = zzi(motionEvent);
                Long l2 = zzi2.zzd;
                if (!(l2 == null || zzi2.zzg == null)) {
                    this.zzi += l2.longValue() + zzi2.zzg.longValue();
                }
                if (!(this.zzq == null || (l = zzi2.zze) == null || zzi2.zzh == null)) {
                    this.zzj += l.longValue() + zzi2.zzh.longValue();
                }
            } catch (zziz unused) {
            }
        } else if (action2 == 3) {
            this.zzg++;
        }
        this.zzp = true;
    }

    public final synchronized void zzl(int i, int i2, int i3) {
        synchronized (this) {
            if (this.zzb != null) {
                if (((Boolean) zzls.zzc().zza(zzmj.zzh)).booleanValue()) {
                    zzj();
                } else {
                    this.zzb.recycle();
                }
            }
            DisplayMetrics displayMetrics = this.zzq;
            if (displayMetrics != null) {
                this.zzb = MotionEvent.obtain(0, (long) i3, 1, ((float) i) * displayMetrics.density, this.zzq.density * ((float) i2), 0.0f, 0.0f, 0, 0.0f, 0.0f, 0, 0);
            } else {
                this.zzb = null;
            }
            this.zzp = false;
        }
    }

    public void zzn(View view) {
    }

    public final boolean zzq() {
        return true;
    }

    public final boolean zzs() {
        return true;
    }
}

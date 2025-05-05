package com.google.android.gms.internal.mlkit_vision_barcode;

import android.content.Context;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import com.google.mlkit.vision.barcode.ZoomSuggestionOptions;
import com.google.mlkit.vision.barcode.internal.zze;
import com.google.mlkit.vision.barcode.internal.zzh;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
public final class zzxk {
    /* access modifiers changed from: private */
    public static final GmsLogger zzf = new GmsLogger("AutoZoom");
    final zzxm zza;
    final zzbw zzb;
    @Nullable
    ScheduledFuture zzc;
    @Nullable
    String zzd;
    int zze;
    /* access modifiers changed from: private */
    public final AtomicBoolean zzg;
    private final Object zzh = new Object();
    private final ScheduledExecutorService zzi;
    private final zzbb zzj;
    private final zzwp zzk;
    private final String zzl;
    private Executor zzm;
    private float zzn;
    private float zzo;
    private long zzp;
    private long zzq;
    private boolean zzr;
    private zze zzs;

    private zzxk(Context context, zzxm zzxm, String str) {
        zzg.zza();
        ScheduledExecutorService unconfigurableScheduledExecutorService = Executors.unconfigurableScheduledExecutorService(Executors.newScheduledThreadPool(2));
        zzbb zza2 = zzar.zza();
        zzwp zzwp = new zzwp(context, new SharedPrefManager(context), new zzwi(context, zzwh.zzd("scanner-auto-zoom").zzd()), "scanner-auto-zoom");
        this.zza = zzxm;
        this.zzg = new AtomicBoolean(false);
        this.zzb = zzbw.zzz();
        this.zzi = unconfigurableScheduledExecutorService;
        this.zzj = zza2;
        this.zzk = zzwp;
        this.zzl = str;
        this.zze = 1;
        this.zzn = 1.0f;
        this.zzo = -1.0f;
        this.zzp = zza2.zza();
    }

    public static zzxk zzd(Context context, String str) {
        return new zzxk(context, zzxm.zza, str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0041, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0043, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ void zzf(com.google.android.gms.internal.mlkit_vision_barcode.zzxk r7) {
        /*
            java.lang.Object r0 = r7.zzh
            monitor-enter(r0)
            int r1 = r7.zze     // Catch:{ all -> 0x0044 }
            r2 = 2
            if (r1 != r2) goto L_0x0042
            java.util.concurrent.atomic.AtomicBoolean r1 = r7.zzg     // Catch:{ all -> 0x0044 }
            boolean r1 = r1.get()     // Catch:{ all -> 0x0044 }
            if (r1 != 0) goto L_0x0042
            java.util.concurrent.ScheduledFuture r1 = r7.zzc     // Catch:{ all -> 0x0044 }
            if (r1 == 0) goto L_0x0042
            boolean r1 = r1.isCancelled()     // Catch:{ all -> 0x0044 }
            if (r1 == 0) goto L_0x001b
            goto L_0x0042
        L_0x001b:
            float r1 = r7.zzn     // Catch:{ all -> 0x0044 }
            r2 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r1 <= 0) goto L_0x0040
            long r3 = r7.zza()     // Catch:{ all -> 0x0044 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzxm r1 = r7.zza     // Catch:{ all -> 0x0044 }
            long r5 = r1.zzi()     // Catch:{ all -> 0x0044 }
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 < 0) goto L_0x0040
            com.google.android.gms.common.internal.GmsLogger r1 = zzf     // Catch:{ all -> 0x0044 }
            java.lang.String r3 = "AutoZoom"
            java.lang.String r4 = "Reset zoom = 1"
            r1.i(r3, r4)     // Catch:{ all -> 0x0044 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzrc r1 = com.google.android.gms.internal.mlkit_vision_barcode.zzrc.SCANNER_AUTO_ZOOM_AUTO_RESET     // Catch:{ all -> 0x0044 }
            r3 = 0
            r7.zzl(r2, r1, r3)     // Catch:{ all -> 0x0044 }
        L_0x0040:
            monitor-exit(r0)     // Catch:{ all -> 0x0044 }
            return
        L_0x0042:
            monitor-exit(r0)     // Catch:{ all -> 0x0044 }
            return
        L_0x0044:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0044 }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode.zzxk.zzf(com.google.android.gms.internal.mlkit_vision_barcode.zzxk):void");
    }

    static /* bridge */ /* synthetic */ void zzg(zzxk zzxk, float f) {
        synchronized (zzxk.zzh) {
            zzxk.zzn = f;
            zzxk.zzr(false);
        }
    }

    private final float zzp(float f) {
        int i = (f > 1.0f ? 1 : (f == 1.0f ? 0 : -1));
        float f2 = this.zzo;
        if (i < 0) {
            f = 1.0f;
        }
        return (f2 <= 0.0f || f <= f2) ? f : f2;
    }

    /* access modifiers changed from: private */
    public final void zzq(zzrc zzrc, float f, float f2, @Nullable zzxn zzxn) {
        long convert;
        if (this.zzd != null) {
            zzuo zzuo = new zzuo();
            zzuo.zza(this.zzl);
            String str = this.zzd;
            str.getClass();
            String str2 = str;
            zzuo.zze(str);
            zzuo.zzf(Float.valueOf(f));
            zzuo.zzc(Float.valueOf(f2));
            synchronized (this.zzh) {
                convert = TimeUnit.MILLISECONDS.convert(this.zzj.zza() - this.zzq, TimeUnit.NANOSECONDS);
            }
            zzuo.zzb(Long.valueOf(convert));
            if (zzxn != null) {
                zzup zzup = new zzup();
                zzup.zzc(Float.valueOf(zzxn.zzc()));
                zzup.zze(Float.valueOf(zzxn.zze()));
                zzup.zzb(Float.valueOf(zzxn.zzb()));
                zzup.zzd(Float.valueOf(zzxn.zzd()));
                zzup.zza(Float.valueOf(0.0f));
                zzuo.zzd(zzup.zzf());
            }
            zzwp zzwp = this.zzk;
            zzrd zzrd = new zzrd();
            zzrd.zzi(zzuo.zzh());
            zzwp.zzd(zzws.zzf(zzrd), zzrc);
        }
    }

    private final void zzr(boolean z) {
        ScheduledFuture scheduledFuture;
        synchronized (this.zzh) {
            this.zzb.zzs();
            this.zzp = this.zzj.zza();
            if (z && (scheduledFuture = this.zzc) != null) {
                scheduledFuture.cancel(false);
                this.zzc = null;
            }
        }
    }

    public final long zza() {
        long convert;
        synchronized (this.zzh) {
            convert = TimeUnit.MILLISECONDS.convert(this.zzj.zza() - this.zzp, TimeUnit.NANOSECONDS);
        }
        return convert;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzet zzc(float f) throws Exception {
        zze zze2 = this.zzs;
        float zzp2 = zzp(f);
        ZoomSuggestionOptions zoomSuggestionOptions = zze2.zza;
        int i = zzh.zzc;
        if (true != zoomSuggestionOptions.zzb().setZoom(zzp2)) {
            zzp2 = 0.0f;
        }
        return zzej.zza(Float.valueOf(zzp2));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0275, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x016a  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x00e6 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzi(int r20, com.google.android.gms.internal.mlkit_vision_barcode.zzxn r21) {
        /*
            r19 = this;
            r1 = r19
            r0 = r21
            java.lang.Object r2 = r1.zzh
            monitor-enter(r2)
            int r3 = r1.zze     // Catch:{ all -> 0x0276 }
            r4 = 2
            if (r3 == r4) goto L_0x000e
            monitor-exit(r2)     // Catch:{ all -> 0x0276 }
            return
        L_0x000e:
            boolean r3 = r21.zzh()     // Catch:{ all -> 0x0276 }
            if (r3 == 0) goto L_0x0274
            com.google.android.gms.internal.mlkit_vision_barcode.zzxm r3 = r1.zza     // Catch:{ all -> 0x0276 }
            boolean r3 = r3.zzl()     // Catch:{ all -> 0x0276 }
            r5 = 0
            if (r3 == 0) goto L_0x0029
            com.google.android.gms.internal.mlkit_vision_barcode.zzxm r3 = r1.zza     // Catch:{ all -> 0x0276 }
            float r3 = r3.zzb()     // Catch:{ all -> 0x0276 }
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 <= 0) goto L_0x0029
            goto L_0x0274
        L_0x0029:
            boolean r3 = r1.zzr     // Catch:{ all -> 0x0276 }
            r6 = 1
            if (r3 != 0) goto L_0x0037
            com.google.android.gms.internal.mlkit_vision_barcode.zzrc r3 = com.google.android.gms.internal.mlkit_vision_barcode.zzrc.SCANNER_AUTO_ZOOM_FIRST_ATTEMPT     // Catch:{ all -> 0x0276 }
            float r7 = r1.zzn     // Catch:{ all -> 0x0276 }
            r1.zzq(r3, r7, r7, r0)     // Catch:{ all -> 0x0276 }
            r1.zzr = r6     // Catch:{ all -> 0x0276 }
        L_0x0037:
            com.google.android.gms.common.internal.GmsLogger r3 = zzf     // Catch:{ all -> 0x0276 }
            java.lang.String r7 = "AutoZoom"
            java.util.Locale r8 = java.util.Locale.getDefault()     // Catch:{ all -> 0x0276 }
            java.lang.String r9 = "Process PredictedArea: [%.2f, %.2f, %.2f, %.2f, %.2f], frameIndex = %d"
            r10 = 6
            java.lang.Object[] r10 = new java.lang.Object[r10]     // Catch:{ all -> 0x0276 }
            float r11 = r21.zzc()     // Catch:{ all -> 0x0276 }
            java.lang.Float r11 = java.lang.Float.valueOf(r11)     // Catch:{ all -> 0x0276 }
            r12 = 0
            r10[r12] = r11     // Catch:{ all -> 0x0276 }
            float r11 = r21.zze()     // Catch:{ all -> 0x0276 }
            java.lang.Float r11 = java.lang.Float.valueOf(r11)     // Catch:{ all -> 0x0276 }
            r10[r6] = r11     // Catch:{ all -> 0x0276 }
            float r6 = r21.zzb()     // Catch:{ all -> 0x0276 }
            java.lang.Float r6 = java.lang.Float.valueOf(r6)     // Catch:{ all -> 0x0276 }
            r10[r4] = r6     // Catch:{ all -> 0x0276 }
            float r4 = r21.zzd()     // Catch:{ all -> 0x0276 }
            java.lang.Float r4 = java.lang.Float.valueOf(r4)     // Catch:{ all -> 0x0276 }
            r6 = 3
            r10[r6] = r4     // Catch:{ all -> 0x0276 }
            java.lang.Float r4 = java.lang.Float.valueOf(r5)     // Catch:{ all -> 0x0276 }
            r6 = 4
            r10[r6] = r4     // Catch:{ all -> 0x0276 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r20)     // Catch:{ all -> 0x0276 }
            r6 = 5
            r10[r6] = r4     // Catch:{ all -> 0x0276 }
            java.lang.String r6 = java.lang.String.format(r8, r9, r10)     // Catch:{ all -> 0x0276 }
            r3.i(r7, r6)     // Catch:{ all -> 0x0276 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzbw r3 = r1.zzb     // Catch:{ all -> 0x0276 }
            r3.zzt(r4, r0)     // Catch:{ all -> 0x0276 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzbw r3 = r1.zzb     // Catch:{ all -> 0x0276 }
            java.util.Set r3 = r3.zzw()     // Catch:{ all -> 0x0276 }
            int r4 = r3.size()     // Catch:{ all -> 0x0276 }
            int r4 = r4 + -1
            com.google.android.gms.internal.mlkit_vision_barcode.zzxm r6 = r1.zza     // Catch:{ all -> 0x0276 }
            int r6 = r6.zzh()     // Catch:{ all -> 0x0276 }
            if (r4 <= r6) goto L_0x00d7
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x0276 }
            r4 = r20
        L_0x00a2:
            boolean r6 = r3.hasNext()     // Catch:{ all -> 0x0276 }
            if (r6 == 0) goto L_0x00b6
            java.lang.Object r6 = r3.next()     // Catch:{ all -> 0x0276 }
            java.lang.Integer r6 = (java.lang.Integer) r6     // Catch:{ all -> 0x0276 }
            int r6 = r6.intValue()     // Catch:{ all -> 0x0276 }
            if (r4 <= r6) goto L_0x00a2
            r4 = r6
            goto L_0x00a2
        L_0x00b6:
            com.google.android.gms.common.internal.GmsLogger r3 = zzf     // Catch:{ all -> 0x0276 }
            java.lang.String r6 = "AutoZoom"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x0276 }
            r7.<init>()     // Catch:{ all -> 0x0276 }
            java.lang.String r8 = "Removing recent frameIndex = "
            r7.append(r8)     // Catch:{ all -> 0x0276 }
            r7.append(r4)     // Catch:{ all -> 0x0276 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x0276 }
            r3.i(r6, r7)     // Catch:{ all -> 0x0276 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzbw r3 = r1.zzb     // Catch:{ all -> 0x0276 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x0276 }
            r3.zzf(r4)     // Catch:{ all -> 0x0276 }
        L_0x00d7:
            java.util.HashSet r3 = new java.util.HashSet     // Catch:{ all -> 0x0276 }
            r3.<init>()     // Catch:{ all -> 0x0276 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzbw r4 = r1.zzb     // Catch:{ all -> 0x0276 }
            java.util.Collection r4 = r4.zzu()     // Catch:{ all -> 0x0276 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x0276 }
        L_0x00e6:
            boolean r6 = r4.hasNext()     // Catch:{ all -> 0x0276 }
            if (r6 == 0) goto L_0x0175
            java.lang.Object r6 = r4.next()     // Catch:{ all -> 0x0276 }
            java.util.Map$Entry r6 = (java.util.Map.Entry) r6     // Catch:{ all -> 0x0276 }
            java.lang.Object r7 = r6.getKey()     // Catch:{ all -> 0x0276 }
            java.lang.Integer r7 = (java.lang.Integer) r7     // Catch:{ all -> 0x0276 }
            int r7 = r7.intValue()     // Catch:{ all -> 0x0276 }
            r8 = r20
            if (r7 == r8) goto L_0x00e6
            java.lang.Object r7 = r6.getValue()     // Catch:{ all -> 0x0276 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzxn r7 = (com.google.android.gms.internal.mlkit_vision_barcode.zzxn) r7     // Catch:{ all -> 0x0276 }
            boolean r9 = r7.zzh()     // Catch:{ all -> 0x0276 }
            if (r9 == 0) goto L_0x015f
            boolean r9 = r21.zzh()     // Catch:{ all -> 0x0276 }
            if (r9 != 0) goto L_0x0113
            goto L_0x015f
        L_0x0113:
            float r9 = r7.zzc()     // Catch:{ all -> 0x0276 }
            float r10 = r21.zzc()     // Catch:{ all -> 0x0276 }
            float r14 = java.lang.Math.max(r9, r10)     // Catch:{ all -> 0x0276 }
            float r9 = r7.zze()     // Catch:{ all -> 0x0276 }
            float r10 = r21.zze()     // Catch:{ all -> 0x0276 }
            float r15 = java.lang.Math.max(r9, r10)     // Catch:{ all -> 0x0276 }
            float r9 = r7.zzb()     // Catch:{ all -> 0x0276 }
            float r10 = r21.zzb()     // Catch:{ all -> 0x0276 }
            float r16 = java.lang.Math.min(r9, r10)     // Catch:{ all -> 0x0276 }
            float r9 = r7.zzd()     // Catch:{ all -> 0x0276 }
            float r10 = r21.zzd()     // Catch:{ all -> 0x0276 }
            float r17 = java.lang.Math.min(r9, r10)     // Catch:{ all -> 0x0276 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzxg r9 = new com.google.android.gms.internal.mlkit_vision_barcode.zzxg     // Catch:{ all -> 0x0276 }
            r18 = 0
            r13 = r9
            r13.<init>(r14, r15, r16, r17, r18)     // Catch:{ all -> 0x0276 }
            float r10 = r9.zzf()     // Catch:{ all -> 0x0276 }
            float r7 = r7.zzf()     // Catch:{ all -> 0x0276 }
            float r11 = r21.zzf()     // Catch:{ all -> 0x0276 }
            float r7 = r7 + r11
            float r9 = r9.zzf()     // Catch:{ all -> 0x0276 }
            float r7 = r7 - r9
            float r10 = r10 / r7
            goto L_0x0160
        L_0x015f:
            r10 = r5
        L_0x0160:
            com.google.android.gms.internal.mlkit_vision_barcode.zzxm r7 = r1.zza     // Catch:{ all -> 0x0276 }
            float r7 = r7.zzd()     // Catch:{ all -> 0x0276 }
            int r7 = (r10 > r7 ? 1 : (r10 == r7 ? 0 : -1))
            if (r7 < 0) goto L_0x00e6
            java.lang.Object r6 = r6.getKey()     // Catch:{ all -> 0x0276 }
            java.lang.Integer r6 = (java.lang.Integer) r6     // Catch:{ all -> 0x0276 }
            r3.add(r6)     // Catch:{ all -> 0x0276 }
            goto L_0x00e6
        L_0x0175:
            int r3 = r3.size()     // Catch:{ all -> 0x0276 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzxm r4 = r1.zza     // Catch:{ all -> 0x0276 }
            int r4 = r4.zzg()     // Catch:{ all -> 0x0276 }
            if (r3 >= r4) goto L_0x0193
            com.google.android.gms.internal.mlkit_vision_barcode.zzxm r3 = r1.zza     // Catch:{ all -> 0x0276 }
            boolean r3 = r3.zzl()     // Catch:{ all -> 0x0276 }
            if (r3 == 0) goto L_0x026f
            com.google.android.gms.internal.mlkit_vision_barcode.zzxm r3 = r1.zza     // Catch:{ all -> 0x0276 }
            float r3 = r3.zza()     // Catch:{ all -> 0x0276 }
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 > 0) goto L_0x026f
        L_0x0193:
            java.lang.Object r3 = r1.zzh     // Catch:{ all -> 0x0276 }
            monitor-enter(r3)     // Catch:{ all -> 0x0276 }
            long r4 = r19.zza()     // Catch:{ all -> 0x0271 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzxm r6 = r1.zza     // Catch:{ all -> 0x0271 }
            long r6 = r6.zzj()     // Catch:{ all -> 0x0271 }
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 >= 0) goto L_0x01a7
            monitor-exit(r3)     // Catch:{ all -> 0x0271 }
            goto L_0x026f
        L_0x01a7:
            float r4 = r21.zzc()     // Catch:{ all -> 0x0271 }
            java.lang.Float r4 = java.lang.Float.valueOf(r4)     // Catch:{ all -> 0x0271 }
            float r5 = r21.zze()     // Catch:{ all -> 0x0271 }
            java.lang.Float r5 = java.lang.Float.valueOf(r5)     // Catch:{ all -> 0x0271 }
            float r6 = r21.zzb()     // Catch:{ all -> 0x0271 }
            java.lang.Float r6 = java.lang.Float.valueOf(r6)     // Catch:{ all -> 0x0271 }
            float r7 = r21.zzd()     // Catch:{ all -> 0x0271 }
            java.lang.Float r7 = java.lang.Float.valueOf(r7)     // Catch:{ all -> 0x0271 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzcs r4 = com.google.android.gms.internal.mlkit_vision_barcode.zzcs.zzi(r4, r5, r6, r7)     // Catch:{ all -> 0x0271 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzdv r4 = r4.listIterator(r12)     // Catch:{ all -> 0x0271 }
            r5 = 1315859240(0x4e6e6b28, float:1.0E9)
        L_0x01d2:
            boolean r6 = r4.hasNext()     // Catch:{ all -> 0x0271 }
            if (r6 == 0) goto L_0x0201
            java.lang.Object r6 = r4.next()     // Catch:{ all -> 0x0271 }
            java.lang.Float r6 = (java.lang.Float) r6     // Catch:{ all -> 0x0271 }
            float r6 = r6.floatValue()     // Catch:{ all -> 0x0271 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzxm r7 = r1.zza     // Catch:{ all -> 0x0271 }
            float r7 = r7.zzc()     // Catch:{ all -> 0x0271 }
            r8 = 1073741824(0x40000000, float:2.0)
            float r7 = r7 / r8
            r8 = -1090519040(0xffffffffbf000000, float:-0.5)
            float r6 = r6 + r8
            float r6 = java.lang.Math.abs(r6)     // Catch:{ all -> 0x0271 }
            r8 = 981668463(0x3a83126f, float:0.001)
            float r6 = java.lang.Math.max(r6, r8)     // Catch:{ all -> 0x0271 }
            float r6 = r7 / r6
            int r7 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r7 <= 0) goto L_0x01d2
            r5 = r6
            goto L_0x01d2
        L_0x0201:
            float r4 = r1.zzn     // Catch:{ all -> 0x0271 }
            float r4 = r4 * r5
            float r4 = r1.zzp(r4)     // Catch:{ all -> 0x0271 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzxm r5 = r1.zza     // Catch:{ all -> 0x0271 }
            boolean r5 = r5.zzk()     // Catch:{ all -> 0x0271 }
            if (r5 == 0) goto L_0x0251
            float r5 = r1.zzn     // Catch:{ all -> 0x0271 }
            float r6 = r4 - r5
            float r6 = r6 / r5
            com.google.android.gms.internal.mlkit_vision_barcode.zzxm r5 = r1.zza     // Catch:{ all -> 0x0271 }
            float r5 = r5.zze()     // Catch:{ all -> 0x0271 }
            int r5 = (r6 > r5 ? 1 : (r6 == r5 ? 0 : -1))
            if (r5 > 0) goto L_0x0251
            com.google.android.gms.internal.mlkit_vision_barcode.zzxm r5 = r1.zza     // Catch:{ all -> 0x0271 }
            float r5 = r5.zzf()     // Catch:{ all -> 0x0271 }
            float r5 = -r5
            int r5 = (r6 > r5 ? 1 : (r6 == r5 ? 0 : -1))
            if (r5 < 0) goto L_0x0251
            com.google.android.gms.common.internal.GmsLogger r0 = zzf     // Catch:{ all -> 0x0271 }
            java.lang.String r5 = "AutoZoom"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0271 }
            r6.<init>()     // Catch:{ all -> 0x0271 }
            java.lang.String r7 = "Auto zoom to "
            r6.append(r7)     // Catch:{ all -> 0x0271 }
            r6.append(r4)     // Catch:{ all -> 0x0271 }
            java.lang.String r4 = " is filtered by threshold"
            r6.append(r4)     // Catch:{ all -> 0x0271 }
            java.lang.String r4 = r6.toString()     // Catch:{ all -> 0x0271 }
            r0.i(r5, r4)     // Catch:{ all -> 0x0271 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzbb r0 = r1.zzj     // Catch:{ all -> 0x0271 }
            long r4 = r0.zza()     // Catch:{ all -> 0x0271 }
            r1.zzp = r4     // Catch:{ all -> 0x0271 }
            monitor-exit(r3)     // Catch:{ all -> 0x0271 }
            goto L_0x026f
        L_0x0251:
            com.google.android.gms.common.internal.GmsLogger r5 = zzf     // Catch:{ all -> 0x0271 }
            java.lang.String r6 = "AutoZoom"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x0271 }
            r7.<init>()     // Catch:{ all -> 0x0271 }
            java.lang.String r8 = "Going to set zoom = "
            r7.append(r8)     // Catch:{ all -> 0x0271 }
            r7.append(r4)     // Catch:{ all -> 0x0271 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x0271 }
            r5.i(r6, r7)     // Catch:{ all -> 0x0271 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzrc r5 = com.google.android.gms.internal.mlkit_vision_barcode.zzrc.SCANNER_AUTO_ZOOM_AUTO_ZOOM     // Catch:{ all -> 0x0271 }
            r1.zzl(r4, r5, r0)     // Catch:{ all -> 0x0271 }
            monitor-exit(r3)     // Catch:{ all -> 0x0271 }
        L_0x026f:
            monitor-exit(r2)     // Catch:{ all -> 0x0276 }
            return
        L_0x0271:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0271 }
            throw r0     // Catch:{ all -> 0x0276 }
        L_0x0274:
            monitor-exit(r2)     // Catch:{ all -> 0x0276 }
            return
        L_0x0276:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0276 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode.zzxk.zzi(int, com.google.android.gms.internal.mlkit_vision_barcode.zzxn):void");
    }

    public final void zzj() {
        synchronized (this.zzh) {
            if (this.zze != 4) {
                zzn(false);
                this.zzi.shutdown();
                this.zze = 4;
            }
        }
    }

    public final void zzk(float f) {
        synchronized (this.zzh) {
            zzaz.zzd(f >= 1.0f);
            this.zzo = f;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzl(float r10, com.google.android.gms.internal.mlkit_vision_barcode.zzrc r11, @javax.annotation.Nullable com.google.android.gms.internal.mlkit_vision_barcode.zzxn r12) {
        /*
            r9 = this;
            java.lang.Object r0 = r9.zzh
            monitor-enter(r0)
            java.util.concurrent.Executor r1 = r9.zzm     // Catch:{ all -> 0x003f }
            if (r1 == 0) goto L_0x003d
            com.google.mlkit.vision.barcode.internal.zze r1 = r9.zzs     // Catch:{ all -> 0x003f }
            if (r1 == 0) goto L_0x003d
            int r1 = r9.zze     // Catch:{ all -> 0x003f }
            r2 = 2
            if (r1 == r2) goto L_0x0011
            goto L_0x003d
        L_0x0011:
            java.util.concurrent.atomic.AtomicBoolean r1 = r9.zzg     // Catch:{ all -> 0x003f }
            r2 = 0
            r3 = 1
            boolean r1 = r1.compareAndSet(r2, r3)     // Catch:{ all -> 0x003f }
            if (r1 != 0) goto L_0x001d
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            return
        L_0x001d:
            float r4 = r9.zzn     // Catch:{ all -> 0x003f }
            com.google.android.gms.internal.mlkit_vision_barcode.zzxh r1 = new com.google.android.gms.internal.mlkit_vision_barcode.zzxh     // Catch:{ all -> 0x003f }
            r1.<init>(r9, r10)     // Catch:{ all -> 0x003f }
            java.util.concurrent.Executor r2 = r9.zzm     // Catch:{ all -> 0x003f }
            com.google.android.gms.internal.mlkit_vision_barcode.zzet r7 = com.google.android.gms.internal.mlkit_vision_barcode.zzej.zzc(r1, r2)     // Catch:{ all -> 0x003f }
            com.google.android.gms.internal.mlkit_vision_barcode.zzxj r8 = new com.google.android.gms.internal.mlkit_vision_barcode.zzxj     // Catch:{ all -> 0x003f }
            r1 = r8
            r2 = r9
            r3 = r11
            r5 = r12
            r6 = r10
            r1.<init>(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x003f }
            java.util.concurrent.Executor r10 = com.google.android.gms.internal.mlkit_vision_barcode.zzeu.zza()     // Catch:{ all -> 0x003f }
            com.google.android.gms.internal.mlkit_vision_barcode.zzej.zzb(r7, r8, r10)     // Catch:{ all -> 0x003f }
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            return
        L_0x003d:
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            return
        L_0x003f:
            r10 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode.zzxk.zzl(float, com.google.android.gms.internal.mlkit_vision_barcode.zzrc, com.google.android.gms.internal.mlkit_vision_barcode.zzxn):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0050, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzm() {
        /*
            r10 = this;
            java.lang.Object r0 = r10.zzh
            monitor-enter(r0)
            int r1 = r10.zze     // Catch:{ all -> 0x0051 }
            r2 = 2
            if (r1 == r2) goto L_0x004f
            r3 = 4
            if (r1 != r3) goto L_0x000c
            goto L_0x004f
        L_0x000c:
            r1 = 1
            r10.zzr(r1)     // Catch:{ all -> 0x0051 }
            java.util.concurrent.ScheduledExecutorService r3 = r10.zzi     // Catch:{ all -> 0x0051 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzxi r4 = new com.google.android.gms.internal.mlkit_vision_barcode.zzxi     // Catch:{ all -> 0x0051 }
            r4.<init>(r10)     // Catch:{ all -> 0x0051 }
            r7 = 500(0x1f4, double:2.47E-321)
            java.util.concurrent.TimeUnit r9 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ all -> 0x0051 }
            r5 = r7
            java.util.concurrent.ScheduledFuture r3 = r3.scheduleWithFixedDelay(r4, r5, r7, r9)     // Catch:{ all -> 0x0051 }
            r10.zzc = r3     // Catch:{ all -> 0x0051 }
            int r3 = r10.zze     // Catch:{ all -> 0x0051 }
            r4 = 0
            if (r3 != r1) goto L_0x0044
            java.util.UUID r1 = java.util.UUID.randomUUID()     // Catch:{ all -> 0x0051 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0051 }
            r10.zzd = r1     // Catch:{ all -> 0x0051 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzbb r1 = r10.zzj     // Catch:{ all -> 0x0051 }
            long r5 = r1.zza()     // Catch:{ all -> 0x0051 }
            r10.zzq = r5     // Catch:{ all -> 0x0051 }
            r1 = 0
            r10.zzr = r1     // Catch:{ all -> 0x0051 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzrc r1 = com.google.android.gms.internal.mlkit_vision_barcode.zzrc.SCANNER_AUTO_ZOOM_START     // Catch:{ all -> 0x0051 }
            float r3 = r10.zzn     // Catch:{ all -> 0x0051 }
            r10.zzq(r1, r3, r3, r4)     // Catch:{ all -> 0x0051 }
            goto L_0x004b
        L_0x0044:
            com.google.android.gms.internal.mlkit_vision_barcode.zzrc r1 = com.google.android.gms.internal.mlkit_vision_barcode.zzrc.SCANNER_AUTO_ZOOM_RESUME     // Catch:{ all -> 0x0051 }
            float r3 = r10.zzn     // Catch:{ all -> 0x0051 }
            r10.zzq(r1, r3, r3, r4)     // Catch:{ all -> 0x0051 }
        L_0x004b:
            r10.zze = r2     // Catch:{ all -> 0x0051 }
            monitor-exit(r0)     // Catch:{ all -> 0x0051 }
            return
        L_0x004f:
            monitor-exit(r0)     // Catch:{ all -> 0x0051 }
            return
        L_0x0051:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0051 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode.zzxk.zzm():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0036, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzn(boolean r5) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.zzh
            monitor-enter(r0)
            int r1 = r4.zze     // Catch:{ all -> 0x0037 }
            r2 = 1
            if (r1 == r2) goto L_0x0035
            r3 = 4
            if (r1 != r3) goto L_0x000c
            goto L_0x0035
        L_0x000c:
            r4.zzr(r2)     // Catch:{ all -> 0x0037 }
            r1 = 0
            if (r5 == 0) goto L_0x0025
            boolean r5 = r4.zzr     // Catch:{ all -> 0x0037 }
            if (r5 != 0) goto L_0x001d
            com.google.android.gms.internal.mlkit_vision_barcode.zzrc r5 = com.google.android.gms.internal.mlkit_vision_barcode.zzrc.SCANNER_AUTO_ZOOM_FIRST_ATTEMPT     // Catch:{ all -> 0x0037 }
            float r3 = r4.zzn     // Catch:{ all -> 0x0037 }
            r4.zzq(r5, r3, r3, r1)     // Catch:{ all -> 0x0037 }
        L_0x001d:
            com.google.android.gms.internal.mlkit_vision_barcode.zzrc r5 = com.google.android.gms.internal.mlkit_vision_barcode.zzrc.SCANNER_AUTO_ZOOM_SCAN_SUCCESS     // Catch:{ all -> 0x0037 }
            float r3 = r4.zzn     // Catch:{ all -> 0x0037 }
            r4.zzq(r5, r3, r3, r1)     // Catch:{ all -> 0x0037 }
            goto L_0x002c
        L_0x0025:
            com.google.android.gms.internal.mlkit_vision_barcode.zzrc r5 = com.google.android.gms.internal.mlkit_vision_barcode.zzrc.SCANNER_AUTO_ZOOM_SCAN_FAILED     // Catch:{ all -> 0x0037 }
            float r3 = r4.zzn     // Catch:{ all -> 0x0037 }
            r4.zzq(r5, r3, r3, r1)     // Catch:{ all -> 0x0037 }
        L_0x002c:
            r5 = 0
            r4.zzr = r5     // Catch:{ all -> 0x0037 }
            r4.zze = r2     // Catch:{ all -> 0x0037 }
            r4.zzd = r1     // Catch:{ all -> 0x0037 }
            monitor-exit(r0)     // Catch:{ all -> 0x0037 }
            return
        L_0x0035:
            monitor-exit(r0)     // Catch:{ all -> 0x0037 }
            return
        L_0x0037:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0037 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode.zzxk.zzn(boolean):void");
    }

    public final void zzo(zze zze2, Executor executor) {
        this.zzs = zze2;
        this.zzm = executor;
    }
}

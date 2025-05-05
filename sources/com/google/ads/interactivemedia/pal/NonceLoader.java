package com.google.ads.interactivemedia.pal;

import android.content.Context;
import androidx.core.location.LocationRequestCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.exoplayer.upstream.CmcdData;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.internal.pal.zzagb;
import com.google.android.gms.internal.pal.zzagc;
import com.google.android.gms.internal.pal.zzav;
import com.google.android.gms.internal.pal.zzba;
import com.google.android.gms.internal.pal.zzbc;
import com.google.android.gms.internal.pal.zzbg;
import com.google.android.gms.internal.pal.zzij;
import com.google.android.gms.internal.pal.zzil;
import com.google.android.gms.internal.pal.zzjb;
import com.google.android.gms.internal.pal.zzjc;
import com.google.android.gms.internal.pal.zzjl;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import io.sentry.android.core.SentryLogcatAdapter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.TreeSet;
import java.util.concurrent.Executors;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class NonceLoader {
    public static final /* synthetic */ int zza = 0;
    private static final Random zzb = new Random();
    private final Context zzc;
    private final zzagb zzd;
    private final zzagb zze;
    private final Task zzf;
    private final zzav zzg;
    private final zzbg zzh;
    private final zzbg zzi;
    private final zzbg zzj;
    private final zzbc zzk;
    private final zzx zzl;
    private final long zzm;
    private long zzn;
    private final String zzo;

    static /* synthetic */ Map zzb(zzjb zzjb, Task task, Task task2, Task task3, Task task4, Task task5) throws Exception {
        zzjc zzjc;
        zzjb.zzb((Map) zze(task).zza(zzaf.zza).zzc(zzjc.zzc()));
        zzil zze2 = zze(task);
        zzil zze3 = zze(task2);
        if (((Boolean) zze2.zza(zzab.zza).zzc(false)).booleanValue()) {
            zzjc = zzjc.zzc();
        } else {
            zzjc = (zzjc) zze3.zza(zzac.zza).zzc(zzjc.zzc());
        }
        zzjb.zzb(zzjc);
        zzjb.zzb((Map) zze(task3).zza(zzag.zza).zzc(zzjc.zzc()));
        zzjb.zzb((Map) zze(task4).zza(zzah.zza).zzc(zzjc.zzc()));
        return zzjb.zzc();
    }

    private static zzil zze(Task task) {
        if (!task.isSuccessful()) {
            return zzil.zze();
        }
        return (zzil) task.getResult();
    }

    private static String zzf() {
        return Integer.toString(zzb.nextInt(Integer.MAX_VALUE));
    }

    private static String zzg(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            SentryLogcatAdapter.e("NonceGenerator", "Failed to encode the input string.");
            return "";
        }
    }

    private static String zzh(Context context) {
        return "h.3.2.2/n.android.3.2.2/".concat(String.valueOf(context.getApplicationContext().getPackageName()));
    }

    public Task<NonceManager> loadNonceManager(NonceRequest nonceRequest) {
        String str;
        Task<Map<String, String>> task;
        if (nonceRequest == null) {
            this.zzl.zza(103);
            return Tasks.forException(NonceLoaderException.zzb(103));
        }
        String zzf2 = zzf();
        zzjb zzjb = new zzjb();
        if (nonceRequest.zzi().length() <= 500) {
            zzjb.zza(zzak.DESCRIPTION_URL.zza(), zzg(nonceRequest.zzi()));
        }
        if (nonceRequest.zzo().length() <= 200) {
            zzjb.zza(zzak.PPID.zza(), zzg(nonceRequest.zzo()));
        }
        if (nonceRequest.zzl().length() > 0 && nonceRequest.zzl().length() <= 200) {
            zzjb.zza(zzak.OMID_VERSION.zza(), zzg(nonceRequest.zzl()));
        }
        if (nonceRequest.zzm().length() <= 200) {
            zzjb.zza(zzak.PLAYER_TYPE.zza(), zzg(nonceRequest.zzm()));
        }
        if (nonceRequest.zzn().length() <= 200) {
            zzjb.zza(zzak.PLAYER_VERSION.zza(), zzg(nonceRequest.zzn()));
        }
        if (nonceRequest.zzj().length() == 0 || nonceRequest.zzj().length() > 200 || nonceRequest.zzk().length() == 0 || nonceRequest.zzk().length() > 200) {
            str = "";
        } else {
            str = nonceRequest.zzj() + "/" + nonceRequest.zzk();
        }
        zzjb.zza(zzak.OMID_PARTNER.zza(), zzg(str));
        TreeSet treeSet = new TreeSet(nonceRequest.zzq());
        if (!str.isEmpty()) {
            treeSet.add(7);
        }
        String zza2 = zzak.API_FRAMEWORKS.zza();
        Iterator it = treeSet.iterator();
        StringBuilder sb = new StringBuilder();
        try {
            zzij.zzb(sb, it, ",");
            zzjb.zza(zza2, sb.toString());
            Integer zzg2 = nonceRequest.zzg();
            if (zzg2 != null) {
                String zza3 = zzak.PLAYER_HEIGHT.zza();
                StringBuilder sb2 = new StringBuilder();
                sb2.append(zzg2);
                zzjb.zza(zza3, sb2.toString());
            }
            Integer zzh2 = nonceRequest.zzh();
            if (zzh2 != null) {
                String zza4 = zzak.PLAYER_WIDTH.zza();
                StringBuilder sb3 = new StringBuilder();
                sb3.append(zzh2);
                zzjb.zza(zza4, sb3.toString());
            }
            if (!(zzg2 == null || zzh2 == null)) {
                zzjb.zza(zzak.ORIENTATION.zza(), zzg2.intValue() <= zzh2.intValue() ? CmcdData.Factory.STREAM_TYPE_LIVE : "p");
            }
            Boolean zzd2 = nonceRequest.zzd();
            if (zzd2 != null) {
                zzjb.zza(zzak.PLAY_ACTIVATION.zza(), true != zzd2.booleanValue() ? "click" : "auto");
            }
            String str2 = "0";
            String str3 = "1";
            zzjb.zza(zzak.WTA_SUPPORTED.zza(), true != nonceRequest.zzc().booleanValue() ? str2 : str3);
            Boolean zze2 = nonceRequest.zze();
            if (zze2 != null) {
                String zza5 = zzak.PLAY_MUTED.zza();
                if (true == zze2.booleanValue()) {
                    str2 = str3;
                }
                zzjb.zza(zza5, str2);
            }
            Boolean zzb2 = nonceRequest.zzb();
            if (zzb2 != null) {
                String zza6 = zzak.CONTINUOUS_PLAYBACK.zza();
                if (true == zzb2.booleanValue()) {
                    str3 = ExifInterface.GPS_MEASUREMENT_2D;
                }
                zzjb.zza(zza6, str3);
            }
            zzjb.zza(zzak.SESSION_ID.zza(), nonceRequest.zzp());
            zzjb zzjb2 = new zzjb();
            zzjb2.zza(zzak.PAL_VERSION.zza(), zzat.zza);
            zzjb2.zza(zzak.SDK_VERSION.zza(), zzh(this.zzc));
            zzjb2.zza(zzak.APP_NAME.zza(), this.zzc.getApplicationContext().getPackageName());
            zzjb2.zza(zzak.PAGE_CORRELATOR.zza(), this.zzo);
            zzjb2.zza(zzak.AD_SPAM_CAPABILITIES.zza(), ExifInterface.GPS_MEASUREMENT_3D);
            zzjb2.zza(zzak.SPAM_CORRELATOR.zza(), zzf2);
            Task zzb3 = this.zzi.zzb();
            Task zzb4 = this.zzj.zzb();
            Task zzb5 = this.zzg.zzb();
            Task zzb6 = this.zzh.zzb();
            Task<TContinuationResult> continueWith = Tasks.whenAllComplete((Task<?>[]) new Task[]{zzb3, zzb4, zzb5, zzb6}).continueWith(new zzae(zzjb2, zzb3, zzb4, zzb5, zzb6));
            PlatformSignalCollector zza7 = nonceRequest.zza();
            if (zza7 == null) {
                task = Tasks.forResult(zzjc.zzc());
            } else {
                task = zza7.collectSignals(this.zzc, Executors.newSingleThreadExecutor());
            }
            Task zzb7 = this.zzk.zzb();
            return Tasks.whenAllComplete((Task<?>[]) new Task[]{continueWith, zzb7, task}).continueWith(Executors.newSingleThreadExecutor(), new zzz(this, zzjb, continueWith, task, zzb7, nonceRequest, zzf2, DefaultClock.getInstance().currentTimeMillis())).addOnFailureListener(new zzaa(this));
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public void release() {
        this.zzg.zze();
        this.zzh.zze();
        this.zzi.zze();
        this.zzj.zze();
        this.zzk.zze();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ NonceManager zza(zzjb zzjb, Task task, Task task2, Task task3, NonceRequest nonceRequest, String str, long j, Task task4) throws Exception {
        zzjb.zzb((Map) task.getResult());
        if (task2.isSuccessful()) {
            zzjb.zzb((Map) task2.getResult());
        }
        zzba zzba = (zzba) ((zzil) task3.getResult()).zzb();
        zzjc zzc2 = zzjb.zzc();
        StringBuilder sb = new StringBuilder();
        zzjl zzd2 = zzc2.entrySet().iterator();
        while (zzd2.hasNext()) {
            Map.Entry entry = (Map.Entry) zzd2.next();
            if (!(entry.getValue() == null || ((String) entry.getValue()).length() == 0)) {
                if (sb.length() > 0) {
                    sb.append("&");
                }
                sb.append((String) entry.getKey());
                sb.append("=");
                sb.append((String) entry.getValue());
            }
        }
        String zza2 = zzba.zza(sb.toString());
        Integer zzf2 = nonceRequest.zzf();
        if (zzf2 == null || zza2.length() <= zzf2.intValue()) {
            String zzh2 = zzh(this.zzc);
            String str2 = this.zzo;
            zze zze2 = new zze();
            zze2.zzb(zzat.zza);
            zze2.zzc(zzh2);
            zze2.zza(str2);
            zzax zzax = new zzax(new zzs(zze2.zzd()), str);
            int length = zza2.length();
            zzh zzh3 = new zzh();
            zzh3.zzc(zzagc.zza);
            zzh3.zzd(zzagc.zza(j - this.zzm));
            zzh3.zzb(zzagc.zza(DefaultClock.getInstance().currentTimeMillis() - this.zzm));
            zzh3.zzf(zzagc.zza);
            zzh3.zze(zzagc.zza(this.zzn - this.zzm));
            zzh3.zza(length);
            this.zzl.zzb(zzh3.zzg());
            return new NonceManager(this.zzc, zzaj.zza(), Executors.newSingleThreadExecutor(), this.zzf, zzax, zza2);
        }
        SentryLogcatAdapter.e("NonceGenerator", "Nonce length limit crossed.");
        throw NonceLoaderException.zzb(LocationRequestCompat.QUALITY_LOW_POWER);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(Exception exc) {
        if (exc instanceof NonceLoaderException) {
            this.zzl.zza(((NonceLoaderException) exc).zza());
        } else {
            this.zzl.zza(100);
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(Task task) {
        this.zzn = DefaultClock.getInstance().currentTimeMillis();
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x00be  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x00cc  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00e3  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00f1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public NonceLoader(android.content.Context r17, com.google.ads.interactivemedia.pal.ConsentSettings r18) {
        /*
            r16 = this;
            r0 = r16
            r7 = r17
            r17.getClass()
            r18.getClass()
            com.google.ads.interactivemedia.pal.zzaj r8 = new com.google.ads.interactivemedia.pal.zzaj
            r8.<init>()
            com.google.ads.interactivemedia.pal.zzai r9 = new com.google.ads.interactivemedia.pal.zzai
            r9.<init>()
            java.lang.String r10 = zzf()
            java.util.concurrent.ExecutorService r1 = java.util.concurrent.Executors.newSingleThreadExecutor()
            com.google.android.gms.tasks.TaskCompletionSource r2 = new com.google.android.gms.tasks.TaskCompletionSource
            r2.<init>()
            com.google.ads.interactivemedia.pal.zzy r3 = new com.google.ads.interactivemedia.pal.zzy
            r3.<init>(r7, r2)
            r1.execute(r3)
            com.google.android.gms.tasks.Task r11 = r2.getTask()
            java.lang.String r1 = zzh(r17)
            com.google.ads.interactivemedia.pal.zze r2 = new com.google.ads.interactivemedia.pal.zze
            r2.<init>()
            java.lang.String r3 = com.google.ads.interactivemedia.pal.zzat.zza
            r2.zzb(r3)
            r2.zzc(r1)
            r2.zza(r10)
            com.google.ads.interactivemedia.pal.zzq r1 = r2.zzd()
            com.google.ads.interactivemedia.pal.zzx r12 = new com.google.ads.interactivemedia.pal.zzx
            com.google.ads.interactivemedia.pal.zzs r2 = new com.google.ads.interactivemedia.pal.zzs
            r2.<init>(r1)
            boolean r1 = com.google.ads.interactivemedia.pal.zzx.zza
            r12.<init>(r2, r1)
            com.google.android.gms.internal.pal.zzav r13 = new com.google.android.gms.internal.pal.zzav
            android.os.Handler r2 = com.google.ads.interactivemedia.pal.zzaj.zza()
            java.util.concurrent.ExecutorService r3 = java.util.concurrent.Executors.newSingleThreadExecutor()
            r1 = r13
            r4 = r17
            r5 = r11
            r6 = r12
            r1.<init>(r2, r3, r4, r5, r6)
            java.lang.String r1 = "uimode"
            java.lang.Object r1 = r7.getSystemService(r1)
            android.app.UiModeManager r1 = (android.app.UiModeManager) r1
            r3 = 4
            r4 = 0
            if (r1 == 0) goto L_0x0077
            int r1 = r1.getCurrentModeType()
            if (r1 != r3) goto L_0x0077
            r1 = 1
            goto L_0x0078
        L_0x0077:
            r1 = r4
        L_0x0078:
            java.lang.Boolean r5 = r18.zza()
            boolean r5 = r5.booleanValue()
            if (r5 != 0) goto L_0x0083
            goto L_0x009d
        L_0x0083:
            java.lang.Boolean r5 = r18.zzc()
            if (r5 == 0) goto L_0x008d
            boolean r1 = r5.booleanValue()
        L_0x008d:
            if (r1 == 0) goto L_0x009d
            com.google.android.gms.internal.pal.zzbh r1 = new com.google.android.gms.internal.pal.zzbh
            android.os.Handler r5 = com.google.ads.interactivemedia.pal.zzaj.zza()
            java.util.concurrent.ExecutorService r6 = java.util.concurrent.Executors.newSingleThreadExecutor()
            r1.<init>(r5, r6, r7, r12)
            goto L_0x00aa
        L_0x009d:
            com.google.android.gms.internal.pal.zzbd r1 = new com.google.android.gms.internal.pal.zzbd
            android.os.Handler r5 = com.google.ads.interactivemedia.pal.zzaj.zza()
            java.util.concurrent.ExecutorService r6 = java.util.concurrent.Executors.newSingleThreadExecutor()
            r1.<init>(r5, r6)
        L_0x00aa:
            java.lang.Boolean r5 = r18.zza()
            boolean r5 = r5.booleanValue()
            if (r5 == 0) goto L_0x00cc
            java.lang.Boolean r5 = r18.zzb()
            boolean r5 = r5.booleanValue()
            if (r5 != 0) goto L_0x00cc
            com.google.android.gms.internal.pal.zzax r5 = new com.google.android.gms.internal.pal.zzax
            android.os.Handler r6 = com.google.ads.interactivemedia.pal.zzaj.zza()
            java.util.concurrent.ExecutorService r14 = java.util.concurrent.Executors.newSingleThreadExecutor()
            r5.<init>(r6, r14, r7)
            goto L_0x00d9
        L_0x00cc:
            com.google.android.gms.internal.pal.zzbd r5 = new com.google.android.gms.internal.pal.zzbd
            android.os.Handler r6 = com.google.ads.interactivemedia.pal.zzaj.zza()
            java.util.concurrent.ExecutorService r14 = java.util.concurrent.Executors.newSingleThreadExecutor()
            r5.<init>(r6, r14)
        L_0x00d9:
            java.lang.Boolean r6 = r18.zza()
            boolean r6 = r6.booleanValue()
            if (r6 == 0) goto L_0x00f1
            com.google.android.gms.internal.pal.zzay r6 = new com.google.android.gms.internal.pal.zzay
            android.os.Handler r14 = com.google.ads.interactivemedia.pal.zzaj.zza()
            java.util.concurrent.ExecutorService r15 = java.util.concurrent.Executors.newSingleThreadExecutor()
            r6.<init>(r14, r15, r7)
            goto L_0x00fe
        L_0x00f1:
            com.google.android.gms.internal.pal.zzbd r6 = new com.google.android.gms.internal.pal.zzbd
            android.os.Handler r14 = com.google.ads.interactivemedia.pal.zzaj.zza()
            java.util.concurrent.ExecutorService r15 = java.util.concurrent.Executors.newSingleThreadExecutor()
            r6.<init>(r14, r15)
        L_0x00fe:
            com.google.android.gms.internal.pal.zzbc r14 = new com.google.android.gms.internal.pal.zzbc
            android.os.Handler r15 = com.google.ads.interactivemedia.pal.zzaj.zza()
            java.util.concurrent.ExecutorService r3 = java.util.concurrent.Executors.newSingleThreadExecutor()
            r14.<init>(r15, r3)
            r16.<init>()
            r2 = -1
            r0.zzn = r2
            r0.zzc = r7
            r0.zzd = r8
            r0.zze = r9
            r0.zzf = r11
            r0.zzg = r13
            r0.zzh = r1
            r0.zzi = r5
            r0.zzj = r6
            r0.zzk = r14
            r0.zzl = r12
            r0.zzo = r10
            com.google.android.gms.common.util.Clock r2 = com.google.android.gms.common.util.DefaultClock.getInstance()
            long r2 = r2.currentTimeMillis()
            r0.zzm = r2
            r14.zzd()
            r13.zzd()
            r5.zzd()
            r6.zzd()
            r1.zzd()
            com.google.android.gms.tasks.Task r2 = r5.zzb()
            com.google.android.gms.tasks.Task r3 = r6.zzb()
            com.google.android.gms.tasks.Task r5 = r13.zzb()
            com.google.android.gms.tasks.Task r1 = r1.zzb()
            com.google.android.gms.tasks.Task r6 = r14.zzb()
            r7 = 5
            com.google.android.gms.tasks.Task[] r7 = new com.google.android.gms.tasks.Task[r7]
            r7[r4] = r2
            r2 = 1
            r7[r2] = r3
            r2 = 2
            r7[r2] = r5
            r2 = 3
            r7[r2] = r1
            r1 = 4
            r7[r1] = r6
            com.google.android.gms.tasks.Task r1 = com.google.android.gms.tasks.Tasks.whenAllComplete((com.google.android.gms.tasks.Task<?>[]) r7)
            com.google.ads.interactivemedia.pal.zzad r2 = new com.google.ads.interactivemedia.pal.zzad
            r2.<init>(r0)
            r1.addOnCompleteListener(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.pal.NonceLoader.<init>(android.content.Context, com.google.ads.interactivemedia.pal.ConsentSettings):void");
    }
}

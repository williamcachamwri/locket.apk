package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzdw;
import com.google.android.gms.internal.measurement.zzhj;
import com.google.android.gms.internal.measurement.zzov;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public class zzhy implements zzjc {
    private static volatile zzhy zzb;
    final long zza;
    private Boolean zzaa;
    private long zzab;
    private volatile Boolean zzac;
    private Boolean zzad;
    private Boolean zzae;
    private volatile boolean zzaf;
    private int zzag;
    private int zzah;
    private AtomicInteger zzai = new AtomicInteger(0);
    private final Context zzc;
    private final String zzd;
    private final String zze;
    private final String zzf;
    private final boolean zzg;
    private final zzab zzh;
    private final zzag zzi;
    private final zzha zzj;
    private final zzgo zzk;
    private final zzhv zzl;
    private final zznb zzm;
    private final zzos zzn;
    private final zzgh zzo;
    private final Clock zzp;
    private final zzlj zzq;
    private final zzjq zzr;
    private final zzb zzs;
    private final zzle zzt;
    private final String zzu;
    private zzgf zzv;
    private zzls zzw;
    private zzaz zzx;
    private zzgg zzy;
    private boolean zzz = false;

    public final int zzc() {
        zzl().zzt();
        if (this.zzi.zzx()) {
            return 1;
        }
        Boolean bool = this.zzae;
        if (bool != null && bool.booleanValue()) {
            return 2;
        }
        if (!zzad()) {
            return 8;
        }
        Boolean zzv2 = zzn().zzv();
        if (zzv2 == null) {
            Boolean zze2 = this.zzi.zze("firebase_analytics_collection_enabled");
            if (zze2 == null) {
                Boolean bool2 = this.zzad;
                if (bool2 != null) {
                    if (bool2.booleanValue()) {
                        return 0;
                    }
                    return 5;
                } else if (this.zzac == null || this.zzac.booleanValue()) {
                    return 0;
                } else {
                    return 7;
                }
            } else if (zze2.booleanValue()) {
                return 0;
            } else {
                return 4;
            }
        } else if (zzv2.booleanValue()) {
            return 0;
        } else {
            return 3;
        }
    }

    @Pure
    public final Context zza() {
        return this.zzc;
    }

    @Pure
    public final Clock zzb() {
        return this.zzp;
    }

    @Pure
    public final zzb zze() {
        zzb zzb2 = this.zzs;
        if (zzb2 != null) {
            return zzb2;
        }
        throw new IllegalStateException("Component not created");
    }

    @Pure
    public final zzab zzd() {
        return this.zzh;
    }

    @Pure
    public final zzag zzf() {
        return this.zzi;
    }

    @Pure
    public final zzaz zzg() {
        zza((zzjd) this.zzx);
        return this.zzx;
    }

    @Pure
    public final zzgg zzh() {
        zza((zzh) this.zzy);
        return this.zzy;
    }

    @Pure
    public final zzgf zzi() {
        zza((zzh) this.zzv);
        return this.zzv;
    }

    @Pure
    public final zzgh zzk() {
        return this.zzo;
    }

    @Pure
    public final zzgo zzj() {
        zza((zzjd) this.zzk);
        return this.zzk;
    }

    public final zzgo zzm() {
        zzgo zzgo = this.zzk;
        if (zzgo == null || !zzgo.zzaf()) {
            return null;
        }
        return this.zzk;
    }

    @Pure
    public final zzha zzn() {
        zza((zzja) this.zzj);
        return this.zzj;
    }

    @Pure
    public final zzhv zzl() {
        zza((zzjd) this.zzl);
        return this.zzl;
    }

    /* access modifiers changed from: package-private */
    @SideEffectFree
    public final zzhv zzo() {
        return this.zzl;
    }

    public static zzhy zza(Context context, zzdw zzdw, Long l) {
        if (zzdw != null && (zzdw.zze == null || zzdw.zzf == null)) {
            zzdw = new zzdw(zzdw.zza, zzdw.zzb, zzdw.zzc, zzdw.zzd, (String) null, (String) null, zzdw.zzg, (String) null);
        }
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzb == null) {
            synchronized (zzhy.class) {
                if (zzb == null) {
                    zzb = new zzhy(new zzjo(context, zzdw, l));
                }
            }
        } else if (!(zzdw == null || zzdw.zzg == null || !zzdw.zzg.containsKey("dataCollectionDefaultEnabled"))) {
            Preconditions.checkNotNull(zzb);
            zzb.zza(zzdw.zzg.getBoolean("dataCollectionDefaultEnabled"));
        }
        Preconditions.checkNotNull(zzb);
        return zzb;
    }

    @Pure
    public final zzjq zzp() {
        zza((zzh) this.zzr);
        return this.zzr;
    }

    @Pure
    private final zzle zzai() {
        zza((zzjd) this.zzt);
        return this.zzt;
    }

    @Pure
    public final zzlj zzq() {
        zza((zzh) this.zzq);
        return this.zzq;
    }

    @Pure
    public final zzls zzr() {
        zza((zzh) this.zzw);
        return this.zzw;
    }

    @Pure
    public final zznb zzs() {
        zza((zzh) this.zzm);
        return this.zzm;
    }

    @Pure
    public final zzos zzt() {
        zza((zzja) this.zzn);
        return this.zzn;
    }

    @Pure
    public final String zzu() {
        return this.zzd;
    }

    @Pure
    public final String zzv() {
        return this.zze;
    }

    @Pure
    public final String zzw() {
        return this.zzf;
    }

    @Pure
    public final String zzx() {
        return this.zzu;
    }

    static /* synthetic */ void zza(zzhy zzhy, zzjo zzjo) {
        zzhy.zzl().zzt();
        zzaz zzaz = new zzaz(zzhy);
        zzaz.zzad();
        zzhy.zzx = zzaz;
        zzgg zzgg = new zzgg(zzhy, zzjo.zzf);
        zzgg.zzv();
        zzhy.zzy = zzgg;
        zzgf zzgf = new zzgf(zzhy);
        zzgf.zzv();
        zzhy.zzv = zzgf;
        zzls zzls = new zzls(zzhy);
        zzls.zzv();
        zzhy.zzw = zzls;
        zzhy.zzn.zzae();
        zzhy.zzj.zzae();
        zzhy.zzy.zzw();
        zzhy.zzj().zzo().zza("App measurement initialized, version", 106000L);
        zzhy.zzj().zzo().zza("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
        String zzad2 = zzgg.zzad();
        if (TextUtils.isEmpty(zzhy.zzd)) {
            if (zzhy.zzt().zzd(zzad2, zzhy.zzi.zzu())) {
                zzhy.zzj().zzo().zza("Faster debug mode event logging enabled. To disable, run:\n  adb shell setprop debug.firebase.analytics.app .none.");
            } else {
                zzhy.zzj().zzo().zza("To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app " + zzad2);
            }
        }
        zzhy.zzj().zzc().zza("Debug-level message logging enabled");
        if (zzhy.zzag != zzhy.zzai.get()) {
            zzhy.zzj().zzg().zza("Not all components initialized", Integer.valueOf(zzhy.zzag), Integer.valueOf(zzhy.zzai.get()));
        }
        zzhy.zzz = true;
    }

    private zzhy(zzjo zzjo) {
        long j;
        boolean z = false;
        Preconditions.checkNotNull(zzjo);
        zzab zzab2 = new zzab(zzjo.zza);
        this.zzh = zzab2;
        zzga.zza = zzab2;
        Context context = zzjo.zza;
        this.zzc = context;
        this.zzd = zzjo.zzb;
        this.zze = zzjo.zzc;
        this.zzf = zzjo.zzd;
        this.zzg = zzjo.zzh;
        this.zzac = zzjo.zze;
        this.zzu = zzjo.zzj;
        this.zzaf = true;
        zzdw zzdw = zzjo.zzg;
        if (!(zzdw == null || zzdw.zzg == null)) {
            Object obj = zzdw.zzg.get("measurementEnabled");
            if (obj instanceof Boolean) {
                this.zzad = (Boolean) obj;
            }
            Object obj2 = zzdw.zzg.get("measurementDeactivated");
            if (obj2 instanceof Boolean) {
                this.zzae = (Boolean) obj2;
            }
        }
        zzhj.zzb(context);
        Clock instance = DefaultClock.getInstance();
        this.zzp = instance;
        if (zzjo.zzi != null) {
            j = zzjo.zzi.longValue();
        } else {
            j = instance.currentTimeMillis();
        }
        this.zza = j;
        this.zzi = new zzag(this);
        zzha zzha = new zzha(this);
        zzha.zzad();
        this.zzj = zzha;
        zzgo zzgo = new zzgo(this);
        zzgo.zzad();
        this.zzk = zzgo;
        zzos zzos = new zzos(this);
        zzos.zzad();
        this.zzn = zzos;
        this.zzo = new zzgh(new zzjn(zzjo, this));
        this.zzs = new zzb(this);
        zzlj zzlj = new zzlj(this);
        zzlj.zzv();
        this.zzq = zzlj;
        zzjq zzjq = new zzjq(this);
        zzjq.zzv();
        this.zzr = zzjq;
        zznb zznb = new zznb(this);
        zznb.zzv();
        this.zzm = zznb;
        zzle zzle = new zzle(this);
        zzle.zzad();
        this.zzt = zzle;
        zzhv zzhv = new zzhv(this);
        zzhv.zzad();
        this.zzl = zzhv;
        if (!(zzjo.zzg == null || zzjo.zzg.zzb == 0)) {
            z = true;
        }
        boolean z2 = !z;
        if (context.getApplicationContext() instanceof Application) {
            zzp().zzb(z2);
        } else {
            zzj().zzu().zza("Application context is not an Application");
        }
        zzhv.zzb((Runnable) new zzid(this, zzjo));
    }

    private static void zza(zzja zzja) {
        if (zzja == null) {
            throw new IllegalStateException("Component not created");
        }
    }

    private static void zza(zzh zzh2) {
        if (zzh2 == null) {
            throw new IllegalStateException("Component not created");
        } else if (!zzh2.zzy()) {
            throw new IllegalStateException("Component not initialized: " + String.valueOf(zzh2.getClass()));
        }
    }

    private static void zza(zzjd zzjd) {
        if (zzjd == null) {
            throw new IllegalStateException("Component not created");
        } else if (!zzjd.zzaf()) {
            throw new IllegalStateException("Component not initialized: " + String.valueOf(zzjd.getClass()));
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzy() {
        throw new IllegalStateException("Unexpected call on client side");
    }

    /* access modifiers changed from: package-private */
    public final void zzz() {
        this.zzai.incrementAndGet();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(String str, int i, Throwable th, byte[] bArr, Map map) {
        if (!((i == 200 || i == 204 || i == 304) && th == null)) {
            zzj().zzu().zza("Network Request for Deferred Deep Link failed. response, exception", Integer.valueOf(i), th);
            return;
        }
        zzn().zzo.zza(true);
        if (bArr == null || bArr.length == 0) {
            zzj().zzc().zza("Deferred Deep Link response empty.");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(new String(bArr));
            String optString = jSONObject.optString("deeplink", "");
            if (TextUtils.isEmpty(optString)) {
                zzj().zzc().zza("Deferred Deep Link is empty.");
                return;
            }
            String optString2 = jSONObject.optString("gclid", "");
            String optString3 = jSONObject.optString("gbraid", "");
            String optString4 = jSONObject.optString("gad_source", "");
            double optDouble = jSONObject.optDouble("timestamp", 0.0d);
            Bundle bundle = new Bundle();
            if (!zzov.zza() || !this.zzi.zza(zzbh.zzct)) {
                if (!zzt().zzi(optString)) {
                    zzj().zzu().zza("Deferred Deep Link validation failed. gclid, deep link", optString2, optString);
                    return;
                }
            } else if (!zzt().zzi(optString)) {
                zzj().zzu().zza("Deferred Deep Link validation failed. gclid, gbraid, deep link", optString2, optString3, optString);
                return;
            } else {
                if (!TextUtils.isEmpty(optString3)) {
                    bundle.putString("gbraid", optString3);
                }
                if (!TextUtils.isEmpty(optString4)) {
                    bundle.putString("gad_source", optString4);
                }
            }
            if (zzov.zza()) {
                this.zzi.zza(zzbh.zzct);
            }
            bundle.putString("gclid", optString2);
            bundle.putString("_cis", "ddp");
            this.zzr.zzc("auto", "_cmp", bundle);
            zzos zzt2 = zzt();
            if (!TextUtils.isEmpty(optString) && zzt2.zza(optString, optDouble)) {
                zzt2.zza().sendBroadcast(new Intent("android.google.analytics.action.DEEPLINK_ACTION"));
            }
        } catch (JSONException e) {
            zzj().zzg().zza("Failed to parse the Deferred Deep Link response. exception", e);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzaa() {
        this.zzag++;
    }

    /* access modifiers changed from: package-private */
    public final void zza(boolean z) {
        this.zzac = Boolean.valueOf(z);
    }

    public final void zzb(boolean z) {
        zzl().zzt();
        this.zzaf = z;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00dc, code lost:
        if (r1.zzi() != false) goto L_0x00e0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.measurement.zzdw r12) {
        /*
            r11 = this;
            com.google.android.gms.measurement.internal.zzhv r0 = r11.zzl()
            r0.zzt()
            boolean r0 = com.google.android.gms.internal.measurement.zzpn.zza()
            if (r0 == 0) goto L_0x004e
            com.google.android.gms.measurement.internal.zzag r0 = r11.zzi
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r1 = com.google.android.gms.measurement.internal.zzbh.zzci
            boolean r0 = r0.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r1)
            if (r0 == 0) goto L_0x004e
            com.google.android.gms.measurement.internal.zzos r0 = r11.zzt()
            boolean r0 = r0.zzw()
            if (r0 == 0) goto L_0x004e
            com.google.android.gms.measurement.internal.zzos r0 = r11.zzt()
            r0.zzt()
            android.content.IntentFilter r1 = new android.content.IntentFilter
            r1.<init>()
            java.lang.String r2 = "com.google.android.gms.measurement.TRIGGERS_AVAILABLE"
            r1.addAction(r2)
            com.google.android.gms.measurement.internal.zzp r2 = new com.google.android.gms.measurement.internal.zzp
            com.google.android.gms.measurement.internal.zzhy r3 = r0.zzu
            r2.<init>(r3)
            android.content.Context r3 = r0.zza()
            r4 = 2
            androidx.core.content.ContextCompat.registerReceiver(r3, r2, r1, r4)
            com.google.android.gms.measurement.internal.zzgo r0 = r0.zzj()
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzc()
            java.lang.String r1 = "Registered app receiver"
            r0.zza(r1)
        L_0x004e:
            com.google.android.gms.measurement.internal.zzha r0 = r11.zzn()
            com.google.android.gms.measurement.internal.zzje r0 = r0.zzo()
            int r1 = r0.zza()
            com.google.android.gms.measurement.internal.zzag r2 = r11.zzi
            java.lang.String r3 = "google_analytics_default_allow_ad_storage"
            r4 = 0
            com.google.android.gms.measurement.internal.zzjh r2 = r2.zzc((java.lang.String) r3, (boolean) r4)
            com.google.android.gms.measurement.internal.zzag r3 = r11.zzi
            java.lang.String r5 = "google_analytics_default_allow_analytics_storage"
            com.google.android.gms.measurement.internal.zzjh r3 = r3.zzc((java.lang.String) r5, (boolean) r4)
            com.google.android.gms.measurement.internal.zzjh r5 = com.google.android.gms.measurement.internal.zzjh.UNINITIALIZED
            r6 = -10
            r7 = 0
            r8 = 30
            if (r2 != r5) goto L_0x0078
            com.google.android.gms.measurement.internal.zzjh r5 = com.google.android.gms.measurement.internal.zzjh.UNINITIALIZED
            if (r3 == r5) goto L_0x0087
        L_0x0078:
            com.google.android.gms.measurement.internal.zzha r5 = r11.zzn()
            boolean r5 = r5.zza((int) r6)
            if (r5 == 0) goto L_0x0087
            com.google.android.gms.measurement.internal.zzje r1 = com.google.android.gms.measurement.internal.zzje.zza(r2, r3, r6)
            goto L_0x00e0
        L_0x0087:
            com.google.android.gms.measurement.internal.zzgg r2 = r11.zzh()
            java.lang.String r2 = r2.zzae()
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto L_0x00b4
            if (r1 == 0) goto L_0x00a5
            if (r1 == r8) goto L_0x00a5
            r2 = 10
            if (r1 == r2) goto L_0x00a5
            if (r1 == r8) goto L_0x00a5
            if (r1 == r8) goto L_0x00a5
            r2 = 40
            if (r1 != r2) goto L_0x00b4
        L_0x00a5:
            com.google.android.gms.measurement.internal.zzjq r1 = r11.zzp()
            com.google.android.gms.measurement.internal.zzje r2 = new com.google.android.gms.measurement.internal.zzje
            r2.<init>(r7, r7, r6)
            long r9 = r11.zza
            r1.zza((com.google.android.gms.measurement.internal.zzje) r2, (long) r9, (boolean) r4)
            goto L_0x00df
        L_0x00b4:
            com.google.android.gms.measurement.internal.zzgg r1 = r11.zzh()
            java.lang.String r1 = r1.zzae()
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 == 0) goto L_0x00df
            if (r12 == 0) goto L_0x00df
            android.os.Bundle r1 = r12.zzg
            if (r1 == 0) goto L_0x00df
            com.google.android.gms.measurement.internal.zzha r1 = r11.zzn()
            boolean r1 = r1.zza((int) r8)
            if (r1 == 0) goto L_0x00df
            android.os.Bundle r1 = r12.zzg
            com.google.android.gms.measurement.internal.zzje r1 = com.google.android.gms.measurement.internal.zzje.zza((android.os.Bundle) r1, (int) r8)
            boolean r2 = r1.zzi()
            if (r2 == 0) goto L_0x00df
            goto L_0x00e0
        L_0x00df:
            r1 = r7
        L_0x00e0:
            r2 = 1
            if (r1 == 0) goto L_0x00ed
            com.google.android.gms.measurement.internal.zzjq r0 = r11.zzp()
            long r9 = r11.zza
            r0.zza((com.google.android.gms.measurement.internal.zzje) r1, (long) r9, (boolean) r2)
            r0 = r1
        L_0x00ed:
            com.google.android.gms.measurement.internal.zzjq r1 = r11.zzp()
            r1.zza((com.google.android.gms.measurement.internal.zzje) r0)
            com.google.android.gms.measurement.internal.zzha r0 = r11.zzn()
            com.google.android.gms.measurement.internal.zzax r0 = r0.zzn()
            int r0 = r0.zza()
            com.google.android.gms.measurement.internal.zzag r1 = r11.zzi
            java.lang.String r3 = "google_analytics_default_allow_ad_personalization_signals"
            com.google.android.gms.measurement.internal.zzjh r1 = r1.zzc((java.lang.String) r3, (boolean) r2)
            com.google.android.gms.measurement.internal.zzjh r3 = com.google.android.gms.measurement.internal.zzjh.UNINITIALIZED
            if (r1 == r3) goto L_0x0119
            com.google.android.gms.measurement.internal.zzgo r3 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgq r3 = r3.zzp()
            java.lang.String r5 = "Default ad personalization consent from Manifest"
            r3.zza(r5, r1)
        L_0x0119:
            com.google.android.gms.measurement.internal.zzag r1 = r11.zzi
            java.lang.String r3 = "google_analytics_default_allow_ad_user_data"
            com.google.android.gms.measurement.internal.zzjh r1 = r1.zzc((java.lang.String) r3, (boolean) r2)
            com.google.android.gms.measurement.internal.zzjh r3 = com.google.android.gms.measurement.internal.zzjh.UNINITIALIZED
            if (r1 == r3) goto L_0x0138
            boolean r3 = com.google.android.gms.measurement.internal.zzje.zza((int) r6, (int) r0)
            if (r3 == 0) goto L_0x0138
            com.google.android.gms.measurement.internal.zzjq r12 = r11.zzp()
            com.google.android.gms.measurement.internal.zzax r0 = com.google.android.gms.measurement.internal.zzax.zza((com.google.android.gms.measurement.internal.zzjh) r1, (int) r6)
            r12.zza((com.google.android.gms.measurement.internal.zzax) r0, (boolean) r2)
            goto L_0x01bb
        L_0x0138:
            com.google.android.gms.measurement.internal.zzgg r1 = r11.zzh()
            java.lang.String r1 = r1.zzae()
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x0157
            if (r0 == 0) goto L_0x014a
            if (r0 != r8) goto L_0x0157
        L_0x014a:
            com.google.android.gms.measurement.internal.zzjq r12 = r11.zzp()
            com.google.android.gms.measurement.internal.zzax r0 = new com.google.android.gms.measurement.internal.zzax
            r0.<init>(r7, r6)
            r12.zza((com.google.android.gms.measurement.internal.zzax) r0, (boolean) r2)
            goto L_0x01bb
        L_0x0157:
            com.google.android.gms.measurement.internal.zzgg r1 = r11.zzh()
            java.lang.String r1 = r1.zzae()
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 == 0) goto L_0x0184
            if (r12 == 0) goto L_0x0184
            android.os.Bundle r1 = r12.zzg
            if (r1 == 0) goto L_0x0184
            boolean r0 = com.google.android.gms.measurement.internal.zzje.zza((int) r8, (int) r0)
            if (r0 == 0) goto L_0x0184
            android.os.Bundle r0 = r12.zzg
            com.google.android.gms.measurement.internal.zzax r0 = com.google.android.gms.measurement.internal.zzax.zza((android.os.Bundle) r0, (int) r8)
            boolean r1 = r0.zzg()
            if (r1 == 0) goto L_0x0184
            com.google.android.gms.measurement.internal.zzjq r1 = r11.zzp()
            r1.zza((com.google.android.gms.measurement.internal.zzax) r0, (boolean) r2)
        L_0x0184:
            com.google.android.gms.measurement.internal.zzgg r0 = r11.zzh()
            java.lang.String r0 = r0.zzae()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x01bb
            if (r12 == 0) goto L_0x01bb
            android.os.Bundle r0 = r12.zzg
            if (r0 == 0) goto L_0x01bb
            com.google.android.gms.measurement.internal.zzha r0 = r11.zzn()
            com.google.android.gms.measurement.internal.zzhd r0 = r0.zzh
            java.lang.String r0 = r0.zza()
            if (r0 != 0) goto L_0x01bb
            android.os.Bundle r0 = r12.zzg
            java.lang.Boolean r0 = com.google.android.gms.measurement.internal.zzax.zza((android.os.Bundle) r0)
            if (r0 == 0) goto L_0x01bb
            com.google.android.gms.measurement.internal.zzjq r1 = r11.zzp()
            java.lang.String r12 = r12.zze
            java.lang.String r3 = "allow_personalized_ads"
            java.lang.String r0 = r0.toString()
            r1.zza((java.lang.String) r12, (java.lang.String) r3, (java.lang.Object) r0, (boolean) r4)
        L_0x01bb:
            com.google.android.gms.measurement.internal.zzag r12 = r11.zzi
            java.lang.String r0 = "google_analytics_tcf_data_enabled"
            java.lang.Boolean r12 = r12.zze(r0)
            if (r12 != 0) goto L_0x01c7
            r12 = r2
            goto L_0x01cb
        L_0x01c7:
            boolean r12 = r12.booleanValue()
        L_0x01cb:
            if (r12 == 0) goto L_0x01e8
            com.google.android.gms.measurement.internal.zzgo r12 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgq r12 = r12.zzc()
            java.lang.String r0 = "TCF client enabled."
            r12.zza(r0)
            com.google.android.gms.measurement.internal.zzjq r12 = r11.zzp()
            r12.zzat()
            com.google.android.gms.measurement.internal.zzjq r12 = r11.zzp()
            r12.zzar()
        L_0x01e8:
            com.google.android.gms.measurement.internal.zzha r12 = r11.zzn()
            com.google.android.gms.measurement.internal.zzhb r12 = r12.zzc
            long r0 = r12.zza()
            r5 = 0
            int r12 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r12 != 0) goto L_0x0216
            com.google.android.gms.measurement.internal.zzgo r12 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgq r12 = r12.zzp()
            long r0 = r11.zza
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            java.lang.String r1 = "Persisting first open"
            r12.zza(r1, r0)
            com.google.android.gms.measurement.internal.zzha r12 = r11.zzn()
            com.google.android.gms.measurement.internal.zzhb r12 = r12.zzc
            long r0 = r11.zza
            r12.zza(r0)
        L_0x0216:
            com.google.android.gms.measurement.internal.zzjq r12 = r11.zzp()
            com.google.android.gms.measurement.internal.zzu r12 = r12.zza
            r12.zzb()
            boolean r12 = r11.zzaf()
            if (r12 != 0) goto L_0x02aa
            boolean r12 = r11.zzac()
            if (r12 == 0) goto L_0x040a
            com.google.android.gms.measurement.internal.zzos r12 = r11.zzt()
            java.lang.String r0 = "android.permission.INTERNET"
            boolean r12 = r12.zze(r0)
            if (r12 != 0) goto L_0x0244
            com.google.android.gms.measurement.internal.zzgo r12 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgq r12 = r12.zzg()
            java.lang.String r0 = "App is missing INTERNET permission"
            r12.zza(r0)
        L_0x0244:
            com.google.android.gms.measurement.internal.zzos r12 = r11.zzt()
            java.lang.String r0 = "android.permission.ACCESS_NETWORK_STATE"
            boolean r12 = r12.zze(r0)
            if (r12 != 0) goto L_0x025d
            com.google.android.gms.measurement.internal.zzgo r12 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgq r12 = r12.zzg()
            java.lang.String r0 = "App is missing ACCESS_NETWORK_STATE permission"
            r12.zza(r0)
        L_0x025d:
            android.content.Context r12 = r11.zzc
            com.google.android.gms.common.wrappers.PackageManagerWrapper r12 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r12)
            boolean r12 = r12.isCallerInstantApp()
            if (r12 != 0) goto L_0x029b
            com.google.android.gms.measurement.internal.zzag r12 = r11.zzi
            boolean r12 = r12.zzy()
            if (r12 != 0) goto L_0x029b
            android.content.Context r12 = r11.zzc
            boolean r12 = com.google.android.gms.measurement.internal.zzos.zza((android.content.Context) r12)
            if (r12 != 0) goto L_0x0286
            com.google.android.gms.measurement.internal.zzgo r12 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgq r12 = r12.zzg()
            java.lang.String r0 = "AppMeasurementReceiver not registered/enabled"
            r12.zza(r0)
        L_0x0286:
            android.content.Context r12 = r11.zzc
            boolean r12 = com.google.android.gms.measurement.internal.zzos.zza((android.content.Context) r12, (boolean) r4)
            if (r12 != 0) goto L_0x029b
            com.google.android.gms.measurement.internal.zzgo r12 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgq r12 = r12.zzg()
            java.lang.String r0 = "AppMeasurementService not registered/enabled"
            r12.zza(r0)
        L_0x029b:
            com.google.android.gms.measurement.internal.zzgo r12 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgq r12 = r12.zzg()
            java.lang.String r0 = "Uploading is not possible. App measurement disabled"
            r12.zza(r0)
            goto L_0x040a
        L_0x02aa:
            com.google.android.gms.measurement.internal.zzgg r12 = r11.zzh()
            java.lang.String r12 = r12.zzae()
            boolean r12 = android.text.TextUtils.isEmpty(r12)
            if (r12 == 0) goto L_0x02c6
            com.google.android.gms.measurement.internal.zzgg r12 = r11.zzh()
            java.lang.String r12 = r12.zzac()
            boolean r12 = android.text.TextUtils.isEmpty(r12)
            if (r12 != 0) goto L_0x0346
        L_0x02c6:
            r11.zzt()
            com.google.android.gms.measurement.internal.zzgg r12 = r11.zzh()
            java.lang.String r12 = r12.zzae()
            com.google.android.gms.measurement.internal.zzha r0 = r11.zzn()
            java.lang.String r0 = r0.zzy()
            com.google.android.gms.measurement.internal.zzgg r1 = r11.zzh()
            java.lang.String r1 = r1.zzac()
            com.google.android.gms.measurement.internal.zzha r3 = r11.zzn()
            java.lang.String r3 = r3.zzx()
            boolean r12 = com.google.android.gms.measurement.internal.zzos.zza((java.lang.String) r12, (java.lang.String) r0, (java.lang.String) r1, (java.lang.String) r3)
            if (r12 == 0) goto L_0x0328
            com.google.android.gms.measurement.internal.zzgo r12 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgq r12 = r12.zzo()
            java.lang.String r0 = "Rechecking which service to use due to a GMP App Id change"
            r12.zza(r0)
            com.google.android.gms.measurement.internal.zzha r12 = r11.zzn()
            r12.zzz()
            com.google.android.gms.measurement.internal.zzgf r12 = r11.zzi()
            r12.zzaa()
            com.google.android.gms.measurement.internal.zzls r12 = r11.zzw
            r12.zzaf()
            com.google.android.gms.measurement.internal.zzls r12 = r11.zzw
            r12.zzae()
            com.google.android.gms.measurement.internal.zzha r12 = r11.zzn()
            com.google.android.gms.measurement.internal.zzhb r12 = r12.zzc
            long r0 = r11.zza
            r12.zza(r0)
            com.google.android.gms.measurement.internal.zzha r12 = r11.zzn()
            com.google.android.gms.measurement.internal.zzhd r12 = r12.zze
            r12.zza(r7)
        L_0x0328:
            com.google.android.gms.measurement.internal.zzha r12 = r11.zzn()
            com.google.android.gms.measurement.internal.zzgg r0 = r11.zzh()
            java.lang.String r0 = r0.zzae()
            r12.zzc(r0)
            com.google.android.gms.measurement.internal.zzha r12 = r11.zzn()
            com.google.android.gms.measurement.internal.zzgg r0 = r11.zzh()
            java.lang.String r0 = r0.zzac()
            r12.zzb((java.lang.String) r0)
        L_0x0346:
            com.google.android.gms.measurement.internal.zzha r12 = r11.zzn()
            com.google.android.gms.measurement.internal.zzje r12 = r12.zzo()
            com.google.android.gms.measurement.internal.zzje$zza r0 = com.google.android.gms.measurement.internal.zzje.zza.ANALYTICS_STORAGE
            boolean r12 = r12.zza((com.google.android.gms.measurement.internal.zzje.zza) r0)
            if (r12 != 0) goto L_0x035f
            com.google.android.gms.measurement.internal.zzha r12 = r11.zzn()
            com.google.android.gms.measurement.internal.zzhd r12 = r12.zze
            r12.zza(r7)
        L_0x035f:
            com.google.android.gms.measurement.internal.zzjq r12 = r11.zzp()
            com.google.android.gms.measurement.internal.zzha r0 = r11.zzn()
            com.google.android.gms.measurement.internal.zzhd r0 = r0.zze
            java.lang.String r0 = r0.zza()
            r12.zzc((java.lang.String) r0)
            com.google.android.gms.measurement.internal.zzos r12 = r11.zzt()
            boolean r12 = r12.zzx()
            if (r12 != 0) goto L_0x03a0
            com.google.android.gms.measurement.internal.zzha r12 = r11.zzn()
            com.google.android.gms.measurement.internal.zzhd r12 = r12.zzq
            java.lang.String r12 = r12.zza()
            boolean r12 = android.text.TextUtils.isEmpty(r12)
            if (r12 != 0) goto L_0x03a0
            com.google.android.gms.measurement.internal.zzgo r12 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgq r12 = r12.zzu()
            java.lang.String r0 = "Remote config removed with active feature rollouts"
            r12.zza(r0)
            com.google.android.gms.measurement.internal.zzha r12 = r11.zzn()
            com.google.android.gms.measurement.internal.zzhd r12 = r12.zzq
            r12.zza(r7)
        L_0x03a0:
            com.google.android.gms.measurement.internal.zzgg r12 = r11.zzh()
            java.lang.String r12 = r12.zzae()
            boolean r12 = android.text.TextUtils.isEmpty(r12)
            if (r12 == 0) goto L_0x03bc
            com.google.android.gms.measurement.internal.zzgg r12 = r11.zzh()
            java.lang.String r12 = r12.zzac()
            boolean r12 = android.text.TextUtils.isEmpty(r12)
            if (r12 != 0) goto L_0x040a
        L_0x03bc:
            boolean r12 = r11.zzac()
            com.google.android.gms.measurement.internal.zzha r0 = r11.zzn()
            boolean r0 = r0.zzab()
            if (r0 != 0) goto L_0x03db
            com.google.android.gms.measurement.internal.zzag r0 = r11.zzi
            boolean r0 = r0.zzx()
            if (r0 != 0) goto L_0x03db
            com.google.android.gms.measurement.internal.zzha r0 = r11.zzn()
            r1 = r12 ^ 1
            r0.zzb((boolean) r1)
        L_0x03db:
            if (r12 == 0) goto L_0x03e4
            com.google.android.gms.measurement.internal.zzjq r12 = r11.zzp()
            r12.zzan()
        L_0x03e4:
            com.google.android.gms.measurement.internal.zznb r12 = r11.zzs()
            com.google.android.gms.measurement.internal.zznj r12 = r12.zza
            r12.zza()
            com.google.android.gms.measurement.internal.zzls r12 = r11.zzr()
            java.util.concurrent.atomic.AtomicReference r0 = new java.util.concurrent.atomic.AtomicReference
            r0.<init>()
            r12.zza((java.util.concurrent.atomic.AtomicReference<java.lang.String>) r0)
            com.google.android.gms.measurement.internal.zzls r12 = r11.zzr()
            com.google.android.gms.measurement.internal.zzha r0 = r11.zzn()
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzt
            android.os.Bundle r0 = r0.zza()
            r12.zza((android.os.Bundle) r0)
        L_0x040a:
            boolean r12 = com.google.android.gms.internal.measurement.zzpn.zza()
            if (r12 == 0) goto L_0x0438
            com.google.android.gms.measurement.internal.zzag r12 = r11.zzi
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r0 = com.google.android.gms.measurement.internal.zzbh.zzci
            boolean r12 = r12.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r0)
            if (r12 == 0) goto L_0x0438
            com.google.android.gms.measurement.internal.zzos r12 = r11.zzt()
            boolean r12 = r12.zzw()
            if (r12 == 0) goto L_0x0438
            java.lang.Thread r12 = new java.lang.Thread
            com.google.android.gms.measurement.internal.zzjq r0 = r11.zzp()
            java.util.Objects.requireNonNull(r0)
            com.google.android.gms.measurement.internal.zzib r1 = new com.google.android.gms.measurement.internal.zzib
            r1.<init>(r0)
            r12.<init>(r1)
            r12.start()
        L_0x0438:
            com.google.android.gms.measurement.internal.zzha r12 = r11.zzn()
            com.google.android.gms.measurement.internal.zzgz r12 = r12.zzj
            r12.zza(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzhy.zza(com.google.android.gms.internal.measurement.zzdw):void");
    }

    public final boolean zzab() {
        return this.zzac != null && this.zzac.booleanValue();
    }

    public final boolean zzac() {
        return zzc() == 0;
    }

    public final boolean zzad() {
        zzl().zzt();
        return this.zzaf;
    }

    @Pure
    public final boolean zzae() {
        return TextUtils.isEmpty(this.zzd);
    }

    /* access modifiers changed from: protected */
    public final boolean zzaf() {
        if (this.zzz) {
            zzl().zzt();
            Boolean bool = this.zzaa;
            if (bool == null || this.zzab == 0 || (bool != null && !bool.booleanValue() && Math.abs(this.zzp.elapsedRealtime() - this.zzab) > 1000)) {
                this.zzab = this.zzp.elapsedRealtime();
                boolean z = true;
                Boolean valueOf = Boolean.valueOf(zzt().zze("android.permission.INTERNET") && zzt().zze("android.permission.ACCESS_NETWORK_STATE") && (Wrappers.packageManager(this.zzc).isCallerInstantApp() || this.zzi.zzy() || (zzos.zza(this.zzc) && zzos.zza(this.zzc, false))));
                this.zzaa = valueOf;
                if (valueOf.booleanValue()) {
                    if (!zzt().zza(zzh().zzae(), zzh().zzac()) && TextUtils.isEmpty(zzh().zzac())) {
                        z = false;
                    }
                    this.zzaa = Boolean.valueOf(z);
                }
            }
            return this.zzaa.booleanValue();
        }
        throw new IllegalStateException("AppMeasurement is not initialized");
    }

    @Pure
    public final boolean zzag() {
        return this.zzg;
    }

    public final boolean zzah() {
        boolean z;
        zzl().zzt();
        zza((zzjd) zzai());
        String zzad2 = zzh().zzad();
        boolean z2 = false;
        if (!this.zzi.zzv()) {
            zzj().zzp().zza("ADID collection is disabled from Manifest. Skipping");
            return false;
        }
        Pair<String, Boolean> zza2 = zzn().zza(zzad2);
        if (((Boolean) zza2.second).booleanValue() || TextUtils.isEmpty((CharSequence) zza2.first)) {
            zzj().zzp().zza("ADID unavailable to retrieve Deferred Deep Link. Skipping");
            return false;
        } else if (!zzai().zzc()) {
            zzj().zzu().zza("Network is not available for Deferred Deep Link request. Skipping");
            return false;
        } else {
            StringBuilder sb = new StringBuilder();
            zzls zzr2 = zzr();
            zzr2.zzt();
            zzr2.zzu();
            int i = 1;
            if (zzr2.zzap() && zzr2.zzq().zzg() < 234200) {
                z = false;
            } else {
                z = true;
            }
            if (z) {
                zzaj zzab2 = zzp().zzab();
                Bundle bundle = zzab2 != null ? zzab2.zza : null;
                if (bundle == null) {
                    int i2 = this.zzah;
                    this.zzah = i2 + 1;
                    if (i2 < 10) {
                        z2 = true;
                    }
                    zzj().zzc().zza("Failed to retrieve DMA consent from the service, " + (z2 ? "Retrying." : "Skipping.") + " retryCount", Integer.valueOf(this.zzah));
                    return z2;
                }
                sb.append("&gcs=").append(zzje.zza(bundle, 100).zze());
                zzax zza3 = zzax.zza(bundle, 100);
                sb.append("&dma=").append(zza3.zzd() == Boolean.FALSE ? 0 : 1);
                if (!TextUtils.isEmpty(zza3.zze())) {
                    sb.append("&dma_cps=").append(zza3.zze());
                }
                if (zzax.zza(bundle) == Boolean.TRUE) {
                    i = 0;
                }
                sb.append("&npa=").append(i);
                zzj().zzp().zza("Consent query parameters to Bow", sb);
            }
            zzos zzt2 = zzt();
            zzh();
            URL zza4 = zzt2.zza(106000, zzad2, (String) zza2.first, zzn().zzp.zza() - 1, sb.toString());
            if (zza4 != null) {
                zzle zzai2 = zzai();
                zzia zzia = new zzia(this);
                zzai2.zzt();
                zzai2.zzac();
                Preconditions.checkNotNull(zza4);
                Preconditions.checkNotNull(zzia);
                zzai2.zzl().zza((Runnable) new zzlg(zzai2, zzad2, zza4, (byte[]) null, (Map<String, String>) null, zzia));
            }
            return false;
        }
    }
}

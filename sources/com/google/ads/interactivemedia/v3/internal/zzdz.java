package com.google.ads.interactivemedia.v3.internal;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.google.ads.interactivemedia.omid.library.adsession.zze;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzdz implements zzda {
    private static final zzdz zza = new zzdz();
    private static final Handler zzb = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public static Handler zzc = null;
    /* access modifiers changed from: private */
    public static final Runnable zzd = new zzdv();
    /* access modifiers changed from: private */
    public static final Runnable zze = new zzdw();
    private final List zzf = new ArrayList();
    private int zzg;
    private boolean zzh = false;
    private final List zzi = new ArrayList();
    private final zzdc zzj = new zzdc();
    private final zzds zzk = new zzds();
    /* access modifiers changed from: private */
    public final zzdt zzl = new zzdt(new zzec());
    private long zzm;

    zzdz() {
    }

    public static zzdz zzd() {
        return zza;
    }

    static /* bridge */ /* synthetic */ void zzg(zzdz zzdz) {
        zzdz.zzg = 0;
        zzdz.zzi.clear();
        zzdz.zzh = false;
        for (zze zze2 : zzcr.zza().zzb()) {
        }
        zzdz.zzm = System.nanoTime();
        zzdz.zzk.zzi();
        long nanoTime = System.nanoTime();
        zzdb zza2 = zzdz.zzj.zza();
        if (zzdz.zzk.zze().size() > 0) {
            Iterator it = zzdz.zzk.zze().iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                JSONObject zza3 = zza2.zza((View) null);
                View zza4 = zzdz.zzk.zza(str);
                zzdb zzb2 = zzdz.zzj.zzb();
                String zzc2 = zzdz.zzk.zzc(str);
                if (zzc2 != null) {
                    JSONObject zza5 = zzb2.zza(zza4);
                    zzdl.zzb(zza5, str);
                    try {
                        zza5.put("notVisibleReason", zzc2);
                    } catch (JSONException e) {
                        zzdm.zza("Error with setting not visible reason", e);
                    }
                    zzdl.zzc(zza3, zza5);
                }
                zzdl.zzf(zza3);
                HashSet hashSet = new HashSet();
                hashSet.add(str);
                zzdz.zzl.zzc(zza3, hashSet, nanoTime);
            }
        }
        if (zzdz.zzk.zzf().size() > 0) {
            JSONObject zza6 = zza2.zza((View) null);
            zzdz.zzk((View) null, zza2, zza6, 1, false);
            zzdl.zzf(zza6);
            zzdz.zzl.zzd(zza6, zzdz.zzk.zzf(), nanoTime);
            boolean z = zzdz.zzh;
        } else {
            zzdz.zzl.zzb();
        }
        zzdz.zzk.zzg();
        long nanoTime2 = System.nanoTime() - zzdz.zzm;
        if (zzdz.zzf.size() > 0) {
            for (zzdy zzdy : zzdz.zzf) {
                int i = zzdz.zzg;
                TimeUnit.NANOSECONDS.toMillis(nanoTime2);
                zzdy.zzb();
                if (zzdy instanceof zzdx) {
                    int i2 = zzdz.zzg;
                    ((zzdx) zzdy).zza();
                }
            }
        }
    }

    private final void zzk(View view, zzdb zzdb, JSONObject jSONObject, int i, boolean z) {
        boolean z2 = true;
        if (i != 1) {
            z2 = false;
        }
        zzdb.zzb(view, jSONObject, this, z2, z);
    }

    private static final void zzl() {
        Handler handler = zzc;
        if (handler != null) {
            handler.removeCallbacks(zze);
            zzc = null;
        }
    }

    public final void zza(View view, zzdb zzdb, JSONObject jSONObject, boolean z) {
        int zzk2;
        boolean z2;
        if (zzdq.zza(view) == null && (zzk2 = this.zzk.zzk(view)) != 3) {
            JSONObject zza2 = zzdb.zza(view);
            zzdl.zzc(jSONObject, zza2);
            String zzd2 = this.zzk.zzd(view);
            if (zzd2 != null) {
                zzdl.zzb(zza2, zzd2);
                try {
                    zza2.put("hasWindowFocus", Boolean.valueOf(this.zzk.zzj(view)));
                } catch (JSONException e) {
                    zzdm.zza("Error with setting has window focus", e);
                }
                this.zzk.zzh();
            } else {
                zzdr zzb2 = this.zzk.zzb(view);
                if (zzb2 != null) {
                    zzcu zza3 = zzb2.zza();
                    JSONArray jSONArray = new JSONArray();
                    ArrayList zzb3 = zzb2.zzb();
                    int size = zzb3.size();
                    for (int i = 0; i < size; i++) {
                        jSONArray.put((String) zzb3.get(i));
                    }
                    try {
                        zza2.put("isFriendlyObstructionFor", jSONArray);
                        zza2.put("friendlyObstructionClass", zza3.zzd());
                        zza2.put("friendlyObstructionPurpose", zza3.zza());
                        zza2.put("friendlyObstructionReason", zza3.zzc());
                    } catch (JSONException e2) {
                        zzdm.zza("Error with setting friendly obstruction", e2);
                    }
                    z2 = true;
                } else {
                    z2 = false;
                }
                zzk(view, zzdb, zza2, zzk2, z || z2);
            }
            this.zzg++;
        }
    }

    public final void zzh() {
        zzl();
    }

    public final void zzi() {
        if (zzc == null) {
            Handler handler = new Handler(Looper.getMainLooper());
            zzc = handler;
            handler.post(zzd);
            zzc.postDelayed(zze, 200);
        }
    }

    public final void zzj() {
        zzl();
        this.zzf.clear();
        zzb.post(new zzdu(this));
    }
}

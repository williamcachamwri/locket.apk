package com.google.ads.interactivemedia.v3.internal;

import android.view.View;
import android.view.ViewParent;
import com.google.ads.interactivemedia.omid.library.adsession.zze;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.WeakHashMap;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzds {
    private final HashMap zza = new HashMap();
    private final HashMap zzb = new HashMap();
    private final HashMap zzc = new HashMap();
    private final HashSet zzd = new HashSet();
    private final HashSet zze = new HashSet();
    private final HashSet zzf = new HashSet();
    private final HashMap zzg = new HashMap();
    private final Map zzh = new WeakHashMap();
    private boolean zzi;

    public final View zza(String str) {
        return (View) this.zzc.get(str);
    }

    public final zzdr zzb(View view) {
        zzdr zzdr = (zzdr) this.zzb.get(view);
        if (zzdr != null) {
            this.zzb.remove(view);
        }
        return zzdr;
    }

    public final String zzc(String str) {
        return (String) this.zzg.get(str);
    }

    public final String zzd(View view) {
        if (this.zza.size() == 0) {
            return null;
        }
        String str = (String) this.zza.get(view);
        if (str != null) {
            this.zza.remove(view);
        }
        return str;
    }

    public final HashSet zze() {
        return this.zzf;
    }

    public final HashSet zzf() {
        return this.zze;
    }

    public final void zzg() {
        this.zza.clear();
        this.zzb.clear();
        this.zzc.clear();
        this.zzd.clear();
        this.zze.clear();
        this.zzf.clear();
        this.zzg.clear();
        this.zzi = false;
    }

    public final void zzh() {
        this.zzi = true;
    }

    public final void zzi() {
        String str;
        Boolean bool;
        zzcr zza2 = zzcr.zza();
        if (zza2 != null) {
            for (zze zze2 : zza2.zzb()) {
                View zzg2 = zze2.zzg();
                if (zze2.zzk()) {
                    String zzi2 = zze2.zzi();
                    if (zzg2 != null) {
                        if (zzg2.isAttachedToWindow()) {
                            if (zzg2.hasWindowFocus()) {
                                this.zzh.remove(zzg2);
                                bool = false;
                            } else if (this.zzh.containsKey(zzg2)) {
                                bool = (Boolean) this.zzh.get(zzg2);
                            } else {
                                this.zzh.put(zzg2, false);
                                bool = false;
                            }
                            if (!bool.booleanValue()) {
                                HashSet hashSet = new HashSet();
                                View view = zzg2;
                                while (true) {
                                    if (view == null) {
                                        this.zzd.addAll(hashSet);
                                        str = null;
                                        break;
                                    }
                                    String zza3 = zzdq.zza(view);
                                    if (zza3 != null) {
                                        str = zza3;
                                        break;
                                    }
                                    hashSet.add(view);
                                    ViewParent parent = view.getParent();
                                    view = parent instanceof View ? (View) parent : null;
                                }
                            } else {
                                str = "noWindowFocus";
                            }
                        } else {
                            str = "notAttached";
                        }
                        if (str == null) {
                            this.zze.add(zzi2);
                            this.zza.put(zzg2, zzi2);
                            for (zzcu zzcu : zze2.zzj()) {
                                View view2 = (View) zzcu.zzb().get();
                                if (view2 != null) {
                                    zzdr zzdr = (zzdr) this.zzb.get(view2);
                                    if (zzdr != null) {
                                        zzdr.zzc(zze2.zzi());
                                    } else {
                                        this.zzb.put(view2, new zzdr(zzcu, zze2.zzi()));
                                    }
                                }
                            }
                        } else if (str != "noWindowFocus") {
                            this.zzf.add(zzi2);
                            this.zzc.put(zzi2, zzg2);
                            this.zzg.put(zzi2, str);
                        }
                    } else {
                        this.zzf.add(zzi2);
                        this.zzg.put(zzi2, "noAdView");
                    }
                }
            }
        }
    }

    public final boolean zzj(View view) {
        if (!this.zzh.containsKey(view)) {
            return true;
        }
        this.zzh.put(view, true);
        return false;
    }

    public final int zzk(View view) {
        if (this.zzd.contains(view)) {
            return 1;
        }
        return this.zzi ? 2 : 3;
    }
}

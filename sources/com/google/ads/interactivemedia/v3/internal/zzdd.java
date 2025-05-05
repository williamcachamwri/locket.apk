package com.google.ads.interactivemedia.v3.internal;

import android.view.View;
import android.view.ViewParent;
import com.google.ads.interactivemedia.omid.library.adsession.zze;
import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzdd implements zzdb {
    private final zzdb zza;

    public zzdd(zzdb zzdb) {
        this.zza = zzdb;
    }

    public final JSONObject zza(View view) {
        boolean z = false;
        JSONObject zza2 = zzdl.zza(0, 0, 0, 0);
        int zzb = zzdo.zzb();
        int i = zzb - 1;
        if (zzb != 0) {
            if (i == 0) {
                z = true;
            }
            try {
                zza2.put("noOutputDevice", z);
            } catch (JSONException e) {
                zzdm.zza("Error with setting output device status", e);
            }
            return zza2;
        }
        throw null;
    }

    public final void zzb(View view, JSONObject jSONObject, zzda zzda, boolean z, boolean z2) {
        ArrayList arrayList = new ArrayList();
        zzcr zza2 = zzcr.zza();
        if (zza2 != null) {
            Collection<zze> zzb = zza2.zzb();
            int size = zzb.size();
            IdentityHashMap identityHashMap = new IdentityHashMap(size + size + 3);
            for (zze zzg : zzb) {
                View zzg2 = zzg.zzg();
                if (zzg2 != null && zzg2.isAttachedToWindow() && zzg2.isShown()) {
                    View view2 = zzg2;
                    while (true) {
                        if (view2 != null) {
                            if (view2.getAlpha() == 0.0f) {
                                break;
                            }
                            ViewParent parent = view2.getParent();
                            view2 = parent instanceof View ? (View) parent : null;
                        } else {
                            View rootView = zzg2.getRootView();
                            if (rootView != null && !identityHashMap.containsKey(rootView)) {
                                identityHashMap.put(rootView, rootView);
                                float z3 = rootView.getZ();
                                int size2 = arrayList.size();
                                while (size2 > 0) {
                                    int i = size2 - 1;
                                    if (((View) arrayList.get(i)).getZ() <= z3) {
                                        break;
                                    }
                                    size2 = i;
                                }
                                arrayList.add(size2, rootView);
                            }
                        }
                    }
                }
            }
        }
        int size3 = arrayList.size();
        for (int i2 = 0; i2 < size3; i2++) {
            zzda.zza((View) arrayList.get(i2), this.zza, jSONObject, z2);
        }
    }
}

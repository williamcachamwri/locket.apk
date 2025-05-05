package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.omid.library.adsession.zze;
import java.util.HashSet;
import org.json.JSONObject;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzee extends zzea {
    public zzee(zzdt zzdt, HashSet hashSet, JSONObject jSONObject, long j) {
        super(zzdt, hashSet, jSONObject, j);
    }

    private final void zzc(String str) {
        zzcr zza = zzcr.zza();
        if (zza != null) {
            for (zze zze : zza.zzc()) {
                if (this.zza.contains(zze.zzi())) {
                    zze.zzh().zzd(str, this.zzc);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object doInBackground(Object[] objArr) {
        return this.zzb.toString();
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void onPostExecute(Object obj) {
        String str = (String) obj;
        zzc(str);
        super.onPostExecute(str);
    }

    /* access modifiers changed from: protected */
    public final void zza(String str) {
        zzc(str);
        super.onPostExecute(str);
    }
}

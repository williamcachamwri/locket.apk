package com.google.ads.interactivemedia.v3.internal;

import android.text.TextUtils;
import com.google.ads.interactivemedia.omid.library.adsession.zze;
import java.util.HashSet;
import org.json.JSONObject;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzef extends zzea {
    public zzef(zzdt zzdt, HashSet hashSet, JSONObject jSONObject, long j) {
        super(zzdt, hashSet, jSONObject, j);
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ Object doInBackground(Object[] objArr) {
        if (zzdl.zzg(this.zzb, this.zzd.zza())) {
            return null;
        }
        this.zzd.zze(this.zzb);
        return this.zzb.toString();
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ void onPostExecute(Object obj) {
        onPostExecute((String) obj);
    }

    /* access modifiers changed from: protected */
    public final void zza(String str) {
        zzcr zza;
        if (!TextUtils.isEmpty(str) && (zza = zzcr.zza()) != null) {
            for (zze zze : zza.zzc()) {
                if (this.zza.contains(zze.zzi())) {
                    zze.zzh().zzh(str, this.zzc);
                }
            }
        }
        super.onPostExecute(str);
    }
}

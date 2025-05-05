package com.google.ads.interactivemedia.v3.internal;

import android.content.pm.ApkChecksum;
import android.content.pm.PackageManager;
import java.util.List;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final /* synthetic */ class zzkz implements PackageManager.OnChecksumsReadyListener {
    public final /* synthetic */ zzvd zza;

    public /* synthetic */ zzkz(zzvd zzvd) {
        this.zza = zzvd;
    }

    public final void onChecksumsReady(List list) {
        zzvd zzvd = this.zza;
        if (list == null) {
            zzvd.zzc((Object) null);
            return;
        }
        try {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                ApkChecksum apkChecksum = (ApkChecksum) list.get(i);
                if (apkChecksum.getType() == 8) {
                    zzvd.zzc(zzjm.zzb(apkChecksum.getValue()));
                    return;
                }
            }
            zzvd.zzc((Object) null);
        } catch (Throwable unused) {
            zzvd.zzc((Object) null);
        }
    }
}

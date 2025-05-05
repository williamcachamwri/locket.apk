package com.google.android.gms.internal.pal;

import android.content.pm.ApkChecksum;
import android.content.pm.PackageManager;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final /* synthetic */ class zzfi implements PackageManager.OnChecksumsReadyListener {
    public final /* synthetic */ zzjr zza;

    public /* synthetic */ zzfi(zzjr zzjr) {
        this.zza = zzjr;
    }

    public final void onChecksumsReady(List list) {
        zzjr zzjr = this.zza;
        if (list == null) {
            zzjr.zzi((Object) null);
            return;
        }
        try {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                ApkChecksum apkChecksum = (ApkChecksum) list.get(i);
                if (apkChecksum.getType() == 8) {
                    zzjr.zzi(zzdx.zzc(apkChecksum.getValue()));
                    return;
                }
            }
            zzjr.zzi((Object) null);
        } catch (Throwable unused) {
            zzjr.zzi((Object) null);
        }
    }
}

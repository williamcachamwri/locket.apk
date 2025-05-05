package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzih extends zzig {
    private zzih(Context context, zzif zzif) {
        super(context, zzif);
    }

    public static zzih zzu(Context context, zzif zzif) {
        zzr(context, zzif);
        return new zzih(context, zzif);
    }

    /* access modifiers changed from: protected */
    public final List zzo(zzjj zzjj, Context context, zzan zzan, zzy zzy) {
        if (zzjj.zzk() == null || !this.zzu.zza) {
            return super.zzo(zzjj, context, zzan, (zzy) null);
        }
        int zza = zzjj.zza();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(super.zzo(zzjj, context, zzan, (zzy) null));
        arrayList.add(new zzkb(zzjj, "CgPRYuzQrSKB4HHU/qweoT6whjRKh5s88SYFeVTlix/HzZdKOZnoIu1auPhHwMiw", "UcPRGM0BZSE4Gd9/Us196LnIBiXWDE9D3TOlCfboVSQ=", zzan, zza, 24));
        return arrayList;
    }
}

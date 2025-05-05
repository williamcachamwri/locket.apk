package com.google.ads.interactivemedia.v3.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final /* synthetic */ class zzfx implements Continuation {
    public final /* synthetic */ zzgd zza;

    public /* synthetic */ zzfx(zzgd zzgd) {
        this.zza = zzgd;
    }

    public final Object then(Task task) {
        List<zzft> list = (List) task.getResult();
        ArrayList arrayList = new ArrayList(list.size());
        for (zzft zzft : list) {
            zzgd zzgd = this.zza;
            arrayList.add(zzft.zzb().addOnFailureListener(new zzfu(zzgd, zzft)));
        }
        return arrayList;
    }
}

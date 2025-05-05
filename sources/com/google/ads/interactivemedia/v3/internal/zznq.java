package com.google.ads.interactivemedia.v3.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final /* synthetic */ class zznq implements Continuation {
    public final /* synthetic */ zzq zza;
    public final /* synthetic */ int zzb;

    public /* synthetic */ zznq(zzq zzq, int i) {
        this.zza = zzq;
        this.zzb = i;
    }

    public final Object then(Task task) {
        int i = zznt.zza;
        if (!task.isSuccessful()) {
            return false;
        }
        int i2 = this.zzb;
        zzpi zza2 = ((zzpj) task.getResult()).zza(((zzv) this.zza.zzal()).zzav());
        zza2.zza(i2);
        zza2.zzc();
        return true;
    }
}

package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.util.Base64;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.nio.ByteBuffer;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzok {
    private final Context zza;
    private final Executor zzb;
    private final zznt zzc;
    private final zznv zzd;
    private final zzoj zze;
    private final zzoj zzf;
    private Task zzg;
    private Task zzh;

    zzok(Context context, Executor executor, zznt zznt, zznv zznv, zzoh zzoh, zzoi zzoi) {
        this.zza = context;
        this.zzb = executor;
        this.zzc = zznt;
        this.zzd = zznv;
        this.zze = zzoh;
        this.zzf = zzoi;
    }

    public static zzok zze(Context context, Executor executor, zznt zznt, zznv zznv) {
        zzok zzok = new zzok(context, executor, zznt, zznv, new zzoh(), new zzoi());
        if (zzok.zzd.zzh()) {
            zzok.zzg = zzok.zzh(new zzoe(zzok));
        } else {
            zzok.zzg = Tasks.forResult(zzok.zze.zza());
        }
        zzok.zzh = zzok.zzh(new zzof(zzok));
        return zzok;
    }

    private static zzbp zzg(Task task, zzbp zzbp) {
        if (!task.isSuccessful()) {
            return zzbp;
        }
        return (zzbp) task.getResult();
    }

    private final Task zzh(Callable callable) {
        return Tasks.call(this.zzb, callable).addOnFailureListener(this.zzb, (OnFailureListener) new zzog(this));
    }

    public final zzbp zza() {
        return zzg(this.zzg, this.zze.zza());
    }

    public final zzbp zzb() {
        return zzg(this.zzh, this.zzf.zza());
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzbp zzc() throws Exception {
        zzan zza2 = zzbp.zza();
        AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.zza);
        String id = advertisingIdInfo.getId();
        if (id != null && id.matches("^[a-fA-F0-9]{8}-([a-fA-F0-9]{4}-){3}[a-fA-F0-9]{12}$")) {
            UUID fromString = UUID.fromString(id);
            byte[] bArr = new byte[16];
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            wrap.putLong(fromString.getMostSignificantBits());
            wrap.putLong(fromString.getLeastSignificantBits());
            id = Base64.encodeToString(bArr, 11);
        }
        if (id != null) {
            zza2.zzs(id);
            zza2.zzr(advertisingIdInfo.isLimitAdTrackingEnabled());
            zza2.zzab(6);
        }
        return (zzbp) zza2.zzal();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzbp zzd() throws Exception {
        Context context = this.zza;
        return zzob.zza(context, context.getPackageName(), Integer.toString(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzf(Exception exc) {
        if (exc instanceof InterruptedException) {
            Thread.currentThread().interrupt();
        }
        this.zzc.zzc(2025, -1, exc);
    }
}

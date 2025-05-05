package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.measurement.zzdy;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@22.1.2 */
final class zzfg extends zzdy.zza {
    private final /* synthetic */ Bundle zzc;
    private final /* synthetic */ Activity zzd;
    private final /* synthetic */ zzdy.zzd zze;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzfg(zzdy.zzd zzd2, Bundle bundle, Activity activity) {
        super(zzdy.this);
        this.zzc = bundle;
        this.zzd = activity;
        this.zze = zzd2;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        Bundle bundle;
        if (this.zzc != null) {
            bundle = new Bundle();
            if (this.zzc.containsKey("com.google.app_measurement.screen_service")) {
                Object obj = this.zzc.get("com.google.app_measurement.screen_service");
                if (obj instanceof Bundle) {
                    bundle.putBundle("com.google.app_measurement.screen_service", (Bundle) obj);
                }
            }
        } else {
            bundle = null;
        }
        ((zzdj) Preconditions.checkNotNull(zzdy.this.zzj)).onActivityCreated(ObjectWrapper.wrap(this.zzd), bundle, this.zzb);
    }
}

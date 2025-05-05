package com.google.android.recaptcha.internal;

import android.content.Context;
import com.google.android.gms.common.GoogleApiAvailabilityLight;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzbu {
    private final GoogleApiAvailabilityLight zza;

    public zzbu() {
        this.zza = GoogleApiAvailabilityLight.getInstance();
    }

    public zzbu(GoogleApiAvailabilityLight googleApiAvailabilityLight) {
        this.zza = googleApiAvailabilityLight;
    }

    public final boolean zza(Context context) {
        return zzb(context) == 3;
    }

    public final int zzb(Context context) {
        int isGooglePlayServicesAvailable = this.zza.isGooglePlayServicesAvailable(context);
        return (isGooglePlayServicesAvailable == 1 || isGooglePlayServicesAvailable == 3 || isGooglePlayServicesAvailable == 9) ? 4 : 3;
    }
}

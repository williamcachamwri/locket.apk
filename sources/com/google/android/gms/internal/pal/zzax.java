package com.google.android.gms.internal.pal;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Handler;
import android.provider.Settings;
import com.amplitude.api.Constants;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import io.sentry.android.core.SentryLogcatAdapter;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzax extends zzbg {
    private final Context zza;

    public zzax(Handler handler, ExecutorService executorService, Context context) {
        super(handler, executorService, zzagc.zzb(2));
        this.zza = context;
    }

    private final zzil zzf() {
        if (this.zza.getPackageManager().hasSystemFeature("amazon.hardware.fire_tv")) {
            try {
                ContentResolver contentResolver = this.zza.getContentResolver();
                String string = Settings.Secure.getString(contentResolver, "advertising_id");
                boolean z = true;
                if (Settings.Secure.getInt(contentResolver, "limit_ad_tracking") != 1) {
                    z = false;
                }
                return zzil.zzf(new zzaz(string, "afai", z));
            } catch (Settings.SettingNotFoundException e) {
                SentryLogcatAdapter.e("NonceGenerator", "Failed to retrieve advertising info from amazon fire tv.", e);
            }
        }
        return zzil.zze();
    }

    private final zzil zzg() {
        try {
            AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.zza);
            String id = advertisingIdInfo.getId();
            if (id == null) {
                id = "";
            }
            return zzil.zzf(new zzaz(id, Constants.AMP_TRACKING_OPTION_ADID, advertisingIdInfo.isLimitAdTrackingEnabled()));
        } catch (IOException e) {
            SentryLogcatAdapter.e("NonceGenerator", "Unrecoverable error connecting to Google Play services.", e);
            return zzil.zze();
        } catch (GooglePlayServicesRepairableException e2) {
            SentryLogcatAdapter.e("NonceGenerator", "Obsolete or disabled version of Google Play Services", e2);
            return zzil.zze();
        } catch (GooglePlayServicesNotAvailableException e3) {
            SentryLogcatAdapter.e("NonceGenerator", "Google Play services is not available entirely.", e3);
            return zzil.zze();
        } catch (IllegalStateException e4) {
            SentryLogcatAdapter.e("NonceGenerator", "IllegalStateException, can't access android advertising info.", e4);
            return zzil.zze();
        }
    }

    /* access modifiers changed from: package-private */
    public final zzil zza() {
        zzil zzf = zzf();
        return !zzf.zzd() ? zzg() : zzf;
    }
}

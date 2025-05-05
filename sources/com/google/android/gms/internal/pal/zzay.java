package com.google.android.gms.internal.pal;

import android.content.Context;
import android.os.Handler;
import com.google.ads.interactivemedia.pal.zzat;
import com.google.android.gms.appset.AppSet;
import com.google.android.gms.appset.AppSetIdClient;
import com.google.android.gms.appset.AppSetIdInfo;
import com.google.android.gms.tasks.Tasks;
import io.sentry.android.core.SentryLogcatAdapter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzay extends zzbg {
    private final AppSetIdClient zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzay(Handler handler, ExecutorService executorService, Context context) {
        super(handler, executorService, zzagc.zzb(2));
        AppSetIdClient zzf = zzf(context);
        this.zza = zzf;
    }

    private static AppSetIdClient zzf(Context context) {
        try {
            return AppSet.getClient(context);
        } catch (NoClassDefFoundError | NoSuchMethodError e) {
            SentryLogcatAdapter.e("NonceGenerator", "Failed to contact the App Set SDK.", e);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public final zzil zza() {
        AppSetIdClient appSetIdClient = this.zza;
        if (appSetIdClient == null) {
            return zzil.zze();
        }
        try {
            return zzil.zzf((AppSetIdInfo) Tasks.await(appSetIdClient.getAppSetIdInfo(), zzat.zzd.zzd(), TimeUnit.MILLISECONDS));
        } catch (InterruptedException | NoClassDefFoundError | NoSuchMethodError | ExecutionException | TimeoutException e) {
            SentryLogcatAdapter.e("NonceGenerator", "Failed to get the App Set ID.", e);
            return zzil.zze();
        }
    }
}

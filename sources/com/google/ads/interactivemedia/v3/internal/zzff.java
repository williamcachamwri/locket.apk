package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.net.Uri;
import androidx.webkit.ProxyConfig;
import com.google.ads.interactivemedia.v3.impl.JavaScriptMessage;
import com.google.ads.interactivemedia.v3.impl.data.TestingConfiguration;
import com.google.ads.interactivemedia.v3.impl.zzba;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzff {
    protected ArrayList zza;

    public zzff() {
        this.zza = new ArrayList();
        this.zza = new ArrayList();
    }

    private static final zzfe zzc(Context context, Uri uri, TestingConfiguration testingConfiguration, ExecutorService executorService) {
        zzahj zzd = zzahk.zzd();
        zzfl zzb = zzfl.zzb(context);
        zzba zzc = zzba.zzc(context.getApplicationContext(), testingConfiguration, uri, zzd, executorService);
        zzc.zzi(ProxyConfig.MATCH_ALL_SCHEMES, JavaScriptMessage.MsgChannel.omid, zzb);
        return new zzeo(uri, testingConfiguration, zzc, zzd, executorService, zzb);
    }

    private static final boolean zzd(Uri uri, TestingConfiguration testingConfiguration, zzfe zzfe) {
        return Objects.equals(zzfe.zza(), uri) && Objects.equals(zzfe.zzc(), testingConfiguration);
    }

    public final zzfe zza(Context context, Uri uri, TestingConfiguration testingConfiguration, ExecutorService executorService) {
        if (this.zza.isEmpty()) {
            return zzc(context, uri, testingConfiguration, executorService);
        }
        zzfe zzfe = (zzfe) this.zza.remove(0);
        return !zzd(uri, testingConfiguration, zzfe) ? zzc(context, uri, testingConfiguration, executorService) : zzfe;
    }

    public final void zzb(Context context, Uri uri, TestingConfiguration testingConfiguration, ExecutorService executorService) {
        if (!this.zza.isEmpty()) {
            if (!zzd(uri, testingConfiguration, (zzfe) this.zza.get(0))) {
                this.zza.remove(0);
            } else {
                return;
            }
        }
        zzahj zzd = zzahk.zzd();
        zzfl zzb = zzfl.zzb(context);
        zzba zzc = zzba.zzc(context.getApplicationContext(), testingConfiguration, uri, zzd, executorService);
        zzc.zzi(ProxyConfig.MATCH_ALL_SCHEMES, JavaScriptMessage.MsgChannel.omid, zzb);
        this.zza.add(new zzeo(uri, testingConfiguration, zzc, zzd, executorService, zzb));
    }
}

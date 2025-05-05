package com.google.android.gms.internal.pal;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzic extends zzhw {
    private zzis zza;
    private final zzis zzb;
    private HttpURLConnection zzc;
    private zzhz zzd = null;

    zzic() {
        zzia zzia = zzia.zza;
        zzib zzib = zzib.zza;
        this.zza = zzia;
        this.zzb = zzib;
    }

    public final void close() {
        HttpURLConnection httpURLConnection = this.zzc;
        zzhx.zza();
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    public final URLConnection zzb(URL url, int i) throws IOException {
        this.zza = new zzhy(26624);
        this.zzd = new zzhz(url);
        Integer num = -1;
        zzhx.zzb(((Integer) this.zza.zza()).intValue(), num.intValue());
        zzhz zzhz = this.zzd;
        zzhz.getClass();
        HttpURLConnection httpURLConnection = (HttpURLConnection) zzhz.zza.openConnection();
        this.zzc = httpURLConnection;
        return httpURLConnection;
    }
}

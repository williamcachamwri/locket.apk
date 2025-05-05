package com.google.android.recaptcha.internal;

import com.google.common.net.HttpHeaders;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzfc implements zzfa {
    private final Lazy zza = LazyKt.lazy(zzfb.zza);

    public zzfc() {
        int i = zzax.zza;
    }

    public final zzse zza(String str, zztq zztq) throws zzbf {
        Throwable th;
        zzbf zzbf;
        zzey zzey = null;
        try {
            zzey = ((zzez) this.zza.getValue()).zza(str);
            zzey.zzc();
            zzey.zze(zztq.zzd());
            zzse zzse = (zzse) zzey.zza(zzse.zzj());
            zzey.zzd();
            return zzse;
        } catch (zzbf e) {
            if (zzey != null) {
                if (Intrinsics.areEqual((Object) e.zza(), (Object) zzbc.zzax)) {
                    try {
                        zzbf = zzbe.zza(zztw.zzg(zzey.zzb().getErrorStream()).zzi());
                    } catch (Exception e2) {
                        zzbf = new zzbf(zzbd.zzc, zzbc.zzG, e2.getMessage());
                    }
                    th = zzbf;
                } else {
                    th = e;
                }
                throw th;
            }
            throw e;
        } catch (Exception e3) {
            throw new zzbf(zzbd.zzc, zzbc.zzF, e3.getMessage());
        } catch (Throwable th2) {
            if (zzey != null) {
                zzey.zzd();
            }
            throw th2;
        }
    }

    public final String zzb(String str) throws zzbf {
        InputStreamReader inputStreamReader;
        try {
            try {
                URLConnection uRLConnection = (URLConnection) FirebasePerfUrlConnection.instrument(new URL(str).openConnection());
                Intrinsics.checkNotNull(uRLConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
                HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnection;
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setRequestProperty(HttpHeaders.ACCEPT, "application/x-protobuffer");
                httpURLConnection.setRequestProperty(HttpHeaders.ACCEPT_ENCODING, "gzip");
                httpURLConnection.connect();
                if (httpURLConnection.getResponseCode() == 200) {
                    try {
                        if (Intrinsics.areEqual((Object) "gzip", (Object) httpURLConnection.getContentEncoding())) {
                            inputStreamReader = new InputStreamReader(new GZIPInputStream(httpURLConnection.getInputStream()));
                        } else {
                            inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
                        }
                        return TextStreamsKt.readText(inputStreamReader);
                    } catch (Exception unused) {
                        throw new zzbf(zzbd.zzc, zzbc.zzP, (String) null);
                    }
                } else {
                    throw new zzbf(zzbd.zzc, new zzbc(httpURLConnection.getResponseCode()), (String) null);
                }
            } catch (Exception unused2) {
                throw new zzbf(zzbd.zzc, zzbc.zzO, (String) null);
            }
        } catch (Exception unused3) {
            throw new zzbf(zzbd.zzb, zzbc.zzN, (String) null);
        }
    }
}

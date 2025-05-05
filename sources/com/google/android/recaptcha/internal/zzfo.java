package com.google.android.recaptcha.internal;

import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzfo {
    private final Lazy zza = LazyKt.lazy(zzfn.zza);

    public zzfo() {
        int i = zzax.zza;
    }

    public final HttpURLConnection zza(String str) throws zzbf, MalformedURLException, IOException {
        if (((zzfm) this.zza.getValue()).zzb(str)) {
            URLConnection uRLConnection = (URLConnection) FirebasePerfUrlConnection.instrument(new URL(str).openConnection());
            Intrinsics.checkNotNull(uRLConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
            return (HttpURLConnection) uRLConnection;
        }
        throw new zzbf(zzbd.zzc, zzbc.zzQ, (String) null);
    }
}

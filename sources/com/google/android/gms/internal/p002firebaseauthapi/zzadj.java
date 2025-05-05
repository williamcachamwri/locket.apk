package com.google.android.gms.internal.p002firebaseauthapi;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.common.net.HttpHeaders;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.internal.zzaq;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzadj  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzadj extends AsyncTask<Void, Void, zzadi> {
    private static final Logger zza = new Logger("FirebaseAuth", "GetAuthDomainTask");
    private final String zzb;
    private final String zzc;
    private final WeakReference<zzadl> zzd;
    private final Uri.Builder zze;
    private final String zzf;
    private final FirebaseApp zzg;

    /* access modifiers changed from: private */
    /* renamed from: zza */
    public final zzadi doInBackground(Void... voidArr) {
        try {
            URL url = new URL(this.zzc);
            zzadl zzadl = (zzadl) this.zzd.get();
            HttpURLConnection zza2 = zzadl.zza(url);
            zza2.addRequestProperty(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
            zza2.setConnectTimeout(60000);
            new zzadu(zzadl.zza(), this.zzg, zzads.zza().zzb()).zza((URLConnection) zza2);
            int responseCode = zza2.getResponseCode();
            if (responseCode != 200) {
                String zza3 = zza(zza2);
                zza.e(String.format("Error getting project config. Failed with %s %s", new Object[]{zza3, Integer.valueOf(responseCode)}), new Object[0]);
                return zzadi.zzb(zza3);
            }
            zzagk zzagk = new zzagk();
            zzagk.zza(new String(zza(zza2.getInputStream(), 128)));
            if (TextUtils.isEmpty(this.zzf)) {
                for (String next : zzagk.zza()) {
                    if (zza(next)) {
                        return zzadi.zza(next);
                    }
                }
                return null;
            } else if (!zzagk.zza().contains(this.zzf)) {
                return zzadi.zzb("UNAUTHORIZED_DOMAIN");
            } else {
                return zzadi.zza(this.zzf);
            }
        } catch (IOException e) {
            zza.e("IOException occurred: " + e.getMessage(), new Object[0]);
            return null;
        } catch (NullPointerException e2) {
            zza.e("Null pointer encountered: " + e2.getMessage(), new Object[0]);
            return null;
        } catch (zzabg e3) {
            zza.e("ConversionException encountered: " + e3.getMessage(), new Object[0]);
            return null;
        }
    }

    private static String zza(HttpURLConnection httpURLConnection) throws zzabg {
        try {
            if (httpURLConnection.getResponseCode() < 400) {
                return null;
            }
            InputStream errorStream = httpURLConnection.getErrorStream();
            if (errorStream == null) {
                return "WEB_INTERNAL_ERROR:Could not retrieve the authDomain for this project but did not receive an error response from the network request. Please try again.";
            }
            return (String) zzadr.zza(new String(zza(errorStream, 128)), String.class);
        } catch (IOException e) {
            zza.w("Error parsing error message from response body in getErrorMessageFromBody. " + String.valueOf(e), new Object[0]);
            return null;
        }
    }

    public zzadj(String str, String str2, Intent intent, FirebaseApp firebaseApp, zzadl zzadl) {
        this.zzb = Preconditions.checkNotEmpty(str);
        this.zzg = (FirebaseApp) Preconditions.checkNotNull(firebaseApp);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(intent);
        String checkNotEmpty = Preconditions.checkNotEmpty(intent.getStringExtra("com.google.firebase.auth.KEY_API_KEY"));
        Uri.Builder buildUpon = Uri.parse(zzadl.zza(checkNotEmpty)).buildUpon();
        buildUpon.appendPath("getProjectConfig").appendQueryParameter("key", checkNotEmpty).appendQueryParameter("androidPackageName", str).appendQueryParameter("sha1Cert", (String) Preconditions.checkNotNull(str2));
        this.zzc = buildUpon.build().toString();
        this.zzd = new WeakReference<>(zzadl);
        this.zze = zzadl.zza(intent, str, str2);
        this.zzf = intent.getStringExtra("com.google.firebase.auth.KEY_CUSTOM_AUTH_DOMAIN");
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void onCancelled(Object obj) {
        zzadi zzadi = (zzadi) obj;
        onPostExecute((zzadi) null);
    }

    /* access modifiers changed from: private */
    /* renamed from: zza */
    public final void onPostExecute(zzadi zzadi) {
        String str;
        String str2;
        Uri.Builder builder;
        zzadl zzadl = (zzadl) this.zzd.get();
        if (zzadi != null) {
            str2 = zzadi.zza();
            str = zzadi.zzb();
        } else {
            str2 = null;
            str = null;
        }
        if (zzadl == null) {
            zza.e("An error has occurred: the handler reference has returned null.", new Object[0]);
        } else if (TextUtils.isEmpty(str2) || (builder = this.zze) == null) {
            zzadl.zza(this.zzb, zzaq.zza(str));
        } else {
            builder.authority(str2);
            zzadl.zza(this.zze.build(), this.zzb, FirebaseAuth.getInstance(this.zzg).zzc());
        }
    }

    private static boolean zza(String str) {
        try {
            String host = new URI("https://" + str).getHost();
            if (host == null || (!host.endsWith("firebaseapp.com") && !host.endsWith("web.app"))) {
                return false;
            }
            return true;
        } catch (URISyntaxException e) {
            zza.e("Error parsing URL for auth domain check: " + str + ". " + e.getMessage(), new Object[0]);
        }
    }

    private static byte[] zza(InputStream inputStream, int i) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] bArr = new byte[128];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    return byteArrayOutputStream.toByteArray();
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
        } finally {
            byteArrayOutputStream.close();
        }
    }
}

package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.iid.InstanceID;
import com.google.common.net.HttpHeaders;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import org.json.JSONException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaeo  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzaeo {
    private static void zza(HttpURLConnection httpURLConnection, zzael<?> zzael, Type type) {
        InputStream inputStream;
        BufferedReader bufferedReader;
        try {
            int responseCode = httpURLConnection.getResponseCode();
            if (zza(responseCode)) {
                inputStream = httpURLConnection.getInputStream();
            } else {
                inputStream = httpURLConnection.getErrorStream();
            }
            StringBuilder sb = new StringBuilder();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            }
            bufferedReader.close();
            String sb2 = sb.toString();
            if (!zza(responseCode)) {
                zzael.zza((String) zzadr.zza(sb2, String.class));
            } else {
                zzael.zza((zzadt) zzadr.zza(sb2, type));
            }
            httpURLConnection.disconnect();
            return;
        } catch (SocketTimeoutException unused) {
            zzael.zza(InstanceID.ERROR_TIMEOUT);
            httpURLConnection.disconnect();
            return;
        } catch (zzabg | IOException e) {
            try {
                zzael.zza(e.getMessage());
                return;
            } finally {
                httpURLConnection.disconnect();
            }
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private static final boolean zza(int i) {
        return i >= 200 && i < 300;
    }

    public static void zza(String str, zzael<?> zzael, Type type, zzadu zzadu) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setConnectTimeout(60000);
            zzadu.zza((URLConnection) httpURLConnection);
            zza(httpURLConnection, zzael, type);
        } catch (SocketTimeoutException unused) {
            zzael.zza(InstanceID.ERROR_TIMEOUT);
        } catch (UnknownHostException unused2) {
            zzael.zza("<<Network Error>>");
        } catch (IOException e) {
            zzael.zza(e.getMessage());
        }
    }

    public static void zza(String str, zzadq zzadq, zzael<?> zzael, Type type, zzadu zzadu) {
        BufferedOutputStream bufferedOutputStream;
        try {
            Preconditions.checkNotNull(zzadq);
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setDoOutput(true);
            byte[] bytes = zzadq.zza().getBytes(Charset.defaultCharset());
            httpURLConnection.setFixedLengthStreamingMode(bytes.length);
            httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/json");
            httpURLConnection.setConnectTimeout(60000);
            zzadu.zza((URLConnection) httpURLConnection);
            bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream(), bytes.length);
            bufferedOutputStream.write(bytes, 0, bytes.length);
            bufferedOutputStream.close();
            zza(httpURLConnection, zzael, type);
            return;
        } catch (SocketTimeoutException unused) {
            zzael.zza(InstanceID.ERROR_TIMEOUT);
            return;
        } catch (UnknownHostException unused2) {
            zzael.zza("<<Network Error>>");
            return;
        } catch (IOException | NullPointerException | JSONException e) {
            zzael.zza(e.getMessage());
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }
}

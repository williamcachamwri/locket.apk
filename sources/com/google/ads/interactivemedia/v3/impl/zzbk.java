package com.google.ads.interactivemedia.v3.impl;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import androidx.webkit.WebViewCompat;
import androidx.webkit.WebViewFeature;
import com.google.ads.interactivemedia.v3.internal.zzahj;
import com.google.ads.interactivemedia.v3.internal.zzek;
import com.google.ads.interactivemedia.v3.internal.zzfh;
import com.google.ads.interactivemedia.v3.internal.zzfk;
import com.google.ads.interactivemedia.v3.internal.zzrr;
import com.google.ads.interactivemedia.v3.internal.zzuu;
import com.google.ads.interactivemedia.v3.internal.zzvd;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzbk {
    private WebView zza;
    private final Handler zzb = new Handler(Looper.getMainLooper());
    private zzbh zzc;
    private zzfh zzd;
    private final zzahj zze;
    private final zzek zzf = new zzek();

    private zzbk(WebView webView, zzahj zzahj) {
        this.zza = webView;
        this.zze = zzahj;
        this.zzd = new zzfh();
    }

    public static zzbk zzb(WebView webView, Uri uri, zzahj zzahj) {
        zzbk zzbk = new zzbk(webView, zzahj);
        webView.setBackgroundColor(0);
        webView.getSettings().setMixedContentMode(0);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        if (WebViewFeature.isFeatureSupported("WEB_MESSAGE_LISTENER")) {
            String format = String.format("%s://%s", new Object[]{uri.getScheme(), uri.getHost()});
            if (uri.getPort() != -1) {
                format = String.format("%s:%s", new Object[]{format, Integer.valueOf(uri.getPort())});
            }
            WebViewCompat.addWebMessageListener(zzbk.zza, "androidWebViewCompatSender", zzrr.zzm(format), new zzbg(zzbk));
        }
        webView.setWebViewClient(new zzbj(zzbk, zzbk.zze));
        webView.setWebChromeClient(new WebChromeClient());
        webView.setOnTouchListener(zzbk.zzf);
        webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        CookieManager instance = CookieManager.getInstance();
        instance.setAcceptCookie(true);
        instance.setAcceptThirdPartyCookies(webView, true);
        webView.loadUrl(uri.toString());
        return zzbk;
    }

    private final void zzk(String str, ValueCallback valueCallback, ValueCallback valueCallback2) {
        WebView webView = this.zza;
        if (webView != null) {
            try {
                webView.evaluateJavascript(str, valueCallback);
            } catch (IllegalStateException unused) {
                this.zza.loadUrl(str);
                if (valueCallback2 != null) {
                    valueCallback2.onReceiveValue((Object) null);
                }
            }
        } else {
            zzfk.zzd("WebView not available at evaluateJavascript");
        }
    }

    public final WebView zza() {
        return this.zza;
    }

    public final zzuu zzc(String str) {
        zzvd zzs = zzvd.zzs();
        this.zzb.post(new zzbf(this, str, zzs));
        return zzs;
    }

    public final void zzd() {
        this.zzb.post(new zzbd(this));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zze() {
        WebView webView = this.zza;
        if (webView != null) {
            webView.destroy();
            this.zza = null;
        }
        this.zzc = null;
        this.zzd = null;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzf(String str, zzvd zzvd) {
        zzk(str, new zzbb(zzvd), new zzbc(zzvd));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzg(JavaScriptMessage javaScriptMessage) {
        zzfh zzfh;
        if (this.zza == null || (zzfh = this.zzd) == null) {
            zzfk.zzd("Attempted to send bridge message after cleanup: ".concat(javaScriptMessage.toString()));
            return;
        }
        String zzc2 = zzfh.zzc(javaScriptMessage);
        String obj = javaScriptMessage.toString();
        zzfk.zzc("Sending Javascript msg: " + obj + "; URL: " + zzc2);
        zzk(zzc2, (ValueCallback) null, (ValueCallback) null);
    }

    /* access modifiers changed from: protected */
    public final void zzi(zzbh zzbh) {
        this.zzc = zzbh;
    }

    public final void zzj(JavaScriptMessage javaScriptMessage) {
        this.zzb.post(new zzbe(this, javaScriptMessage));
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x002d  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0036 A[Catch:{ IllegalArgumentException -> 0x0074, Exception -> 0x005b }] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0057  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzh(java.lang.String r7, java.lang.String r8) {
        /*
            r6 = this;
            java.lang.String r0 = ", Message Type: "
            java.lang.String r1 = "Received Javascript msg: "
            com.google.ads.interactivemedia.v3.internal.zzfh r2 = r6.zzd
            if (r2 == 0) goto L_0x008c
            int r3 = r8.hashCode()     // Catch:{ IllegalArgumentException -> 0x0074, Exception -> 0x005b }
            r4 = 48
            r5 = 1
            if (r3 == r4) goto L_0x0020
            r4 = 52
            if (r3 == r4) goto L_0x0016
            goto L_0x002a
        L_0x0016:
            java.lang.String r3 = "4"
            boolean r3 = r8.equals(r3)
            if (r3 == 0) goto L_0x002a
            r3 = r5
            goto L_0x002b
        L_0x0020:
            java.lang.String r3 = "0"
            boolean r3 = r8.equals(r3)
            if (r3 == 0) goto L_0x002a
            r3 = 0
            goto L_0x002b
        L_0x002a:
            r3 = -1
        L_0x002b:
            if (r3 == 0) goto L_0x0036
            if (r3 == r5) goto L_0x0031
            r2 = 0
            goto L_0x003a
        L_0x0031:
            com.google.ads.interactivemedia.v3.impl.JavaScriptMessage r2 = r2.zzb(r7)     // Catch:{ IllegalArgumentException -> 0x0074, Exception -> 0x005b }
            goto L_0x003a
        L_0x0036:
            com.google.ads.interactivemedia.v3.impl.JavaScriptMessage r2 = r2.zza(r7)     // Catch:{ IllegalArgumentException -> 0x0074, Exception -> 0x005b }
        L_0x003a:
            java.lang.String r3 = java.lang.String.valueOf(r2)     // Catch:{ IllegalArgumentException -> 0x0074, Exception -> 0x005b }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x0074, Exception -> 0x005b }
            r4.<init>(r1)     // Catch:{ IllegalArgumentException -> 0x0074, Exception -> 0x005b }
            r4.append(r3)     // Catch:{ IllegalArgumentException -> 0x0074, Exception -> 0x005b }
            java.lang.String r1 = r4.toString()     // Catch:{ IllegalArgumentException -> 0x0074, Exception -> 0x005b }
            com.google.ads.interactivemedia.v3.internal.zzfk.zzc(r1)     // Catch:{ IllegalArgumentException -> 0x0074, Exception -> 0x005b }
            com.google.ads.interactivemedia.v3.impl.zzbh r7 = r6.zzc
            if (r7 != 0) goto L_0x0057
            java.lang.String r7 = "Received JS Message without a listener."
            com.google.ads.interactivemedia.v3.internal.zzfk.zzd(r7)
            return
        L_0x0057:
            r7.zza(r2)
            return
        L_0x005b:
            r1 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Invalid internal message. Message could not be be parsed: "
            r2.<init>(r3)
            r2.append(r7)
            r2.append(r0)
            r2.append(r8)
            java.lang.String r7 = r2.toString()
            com.google.ads.interactivemedia.v3.internal.zzfk.zzb(r7, r1)
            return
        L_0x0074:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Invalid internal message. Make sure the Google IMA SDK library is up to date. Message: "
            r1.<init>(r2)
            r1.append(r7)
            r1.append(r0)
            r1.append(r8)
            java.lang.String r7 = r1.toString()
            com.google.ads.interactivemedia.v3.internal.zzfk.zzd(r7)
            return
        L_0x008c:
            java.lang.String r7 = "Received JS Message after JavaScriptWebView destroyed"
            com.google.ads.interactivemedia.v3.internal.zzfk.zzd(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.impl.zzbk.zzh(java.lang.String, java.lang.String):void");
    }
}

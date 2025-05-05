package com.google.android.recaptcha.internal;

import android.net.Uri;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.webkit.internal.AssetHelper;
import java.io.ByteArrayInputStream;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzim extends WebViewClient {
    final /* synthetic */ zzjc zza;

    zzim(zzjc zzjc) {
        this.zza = zzjc;
    }

    public final void onLoadResource(WebView webView, String str) {
        System.currentTimeMillis();
    }

    public final void onPageFinished(WebView webView, String str) {
        long zza2 = this.zza.zzh.zza(TimeUnit.MICROSECONDS);
        int i = zzbm.zza;
        zzbm.zza(zzbn.zzg.zza(), zza2);
    }

    @Deprecated(message = "Use onReceivedError(WebView,request,error) instead")
    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
        zzbd zzbd = zzbd.zzc;
        zzbc zzbc = (zzbc) this.zza.zzd.get(Integer.valueOf(i));
        if (zzbc == null) {
            zzbc = zzbc.zzM;
        }
        zzbf zzbf = new zzbf(zzbd, zzbc, (String) null);
        this.zza.zzA().hashCode();
        zzbf.getMessage();
        this.zza.zzA().completeExceptionally(zzbf);
    }

    @Deprecated(message = "Use shouldInterceptRequest(WebView,WebResourceRequest) instead")
    public final WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        zzjc zzjc = this.zza;
        Uri parse = Uri.parse(str);
        zzjc.zzp(zzjc);
        Intrinsics.checkNotNull(parse);
        if (!zzfm.zzc(parse) || zzjc.zzp(this.zza).zza(parse)) {
            return super.shouldInterceptRequest(webView, str);
        }
        zzbf zzbf = new zzbf(zzbd.zzb, zzbc.zzQ, (String) null);
        this.zza.zzA().hashCode();
        parse.toString();
        this.zza.zzA().completeExceptionally(zzbf);
        return new WebResourceResponse(AssetHelper.DEFAULT_MIME_TYPE, "UTF-8", new ByteArrayInputStream(new byte[0]));
    }
}

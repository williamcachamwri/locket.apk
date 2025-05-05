package com.google.ads.interactivemedia.v3.impl;

import android.content.Context;
import android.util.Base64;
import android.webkit.WebView;
import com.google.ads.interactivemedia.v3.impl.data.zzbd;
import com.google.ads.interactivemedia.v3.impl.data.zzbe;
import com.google.ads.interactivemedia.v3.internal.zzgi;
import com.jimmydaddy.imagemarker.base.Constants;
import java.util.List;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzap extends WebView {
    private zzap(Context context) {
        super(context);
    }

    public static zzap zza(Context context, zzbe zzbe, List list, zzgi zzgi) {
        zzap zzap = new zzap(context);
        zzap.getSettings().setJavaScriptEnabled(true);
        zzap.getSettings().setSupportMultipleWindows(true);
        zzap.setBackgroundColor(0);
        zzap.setWebChromeClient(new zzao(context, zzgi, list));
        if (zzbe.type() == zzbd.Html) {
            zzap.loadData(Base64.encodeToString(zzbe.src().getBytes(), 1), "text/html", Constants.BASE64);
        } else if (zzbe.type() == zzbd.IFrame) {
            zzap.loadUrl(zzbe.src());
        } else {
            String valueOf = String.valueOf(zzbe.type());
            throw new IllegalArgumentException("Companion type " + valueOf + " is not valid for a CompanionWebView");
        }
        return zzap;
    }
}

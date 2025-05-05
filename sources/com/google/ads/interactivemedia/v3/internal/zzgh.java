package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;
import androidx.webkit.WebViewFeature;
import com.google.ads.interactivemedia.v3.api.ImaSdkSettings;
import com.google.ads.interactivemedia.v3.impl.data.TestingConfiguration;
import com.google.ads.interactivemedia.v3.impl.zzav;
import io.sentry.protocol.App;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzgh {
    public static Uri zza(ImaSdkSettings imaSdkSettings, String str) {
        Uri uri;
        if (imaSdkSettings == null || !imaSdkSettings.isDebugMode()) {
            uri = zzav.zza;
        } else {
            uri = zzav.zzb;
        }
        return zzb(uri, imaSdkSettings, str);
    }

    public static Uri zzb(Uri uri, ImaSdkSettings imaSdkSettings, String str) {
        Uri.Builder appendQueryParameter = uri.buildUpon().appendQueryParameter("sdk_version", "a.3.35.1").appendQueryParameter("hl", imaSdkSettings.getLanguage()).appendQueryParameter("omv", "1.4.10-google_20240110").appendQueryParameter(App.TYPE, str);
        appendQueryParameter.appendQueryParameter("mt", true != WebViewFeature.isFeatureSupported("WEB_MESSAGE_LISTENER") ? "0" : "4");
        if (imaSdkSettings.getTestingConfig() != null) {
            zzvs zzvs = new zzvs();
            zzvs.zzc(new zzpu());
            zzvs.zze(new zzpt());
            appendQueryParameter.appendQueryParameter(TestingConfiguration.PARAMETER_KEY, zzvs.zza().zzf(imaSdkSettings.getTestingConfig()));
        }
        return appendQueryParameter.build();
    }

    public static Map zzc(Uri uri) {
        if (uri == null || uri.isOpaque()) {
            throw new UnsupportedOperationException("This isn't a hierarchical URI.");
        }
        String encodedQuery = uri.getEncodedQuery();
        if (encodedQuery == null || encodedQuery.length() == 0) {
            return Collections.emptyMap();
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int indexOf = encodedQuery.indexOf(35);
        int i = 0;
        if (indexOf == -1) {
            indexOf = encodedQuery.length();
        }
        do {
            int indexOf2 = encodedQuery.indexOf(38, i);
            if (indexOf2 == -1) {
                indexOf2 = indexOf;
            }
            int indexOf3 = encodedQuery.indexOf(61, i);
            if (indexOf3 > indexOf2 || indexOf3 == -1) {
                indexOf3 = indexOf2;
            }
            linkedHashMap.put(encodedQuery.substring(i, indexOf3), indexOf3 < indexOf2 ? encodedQuery.substring(indexOf3 + 1, indexOf2) : "");
            i = indexOf2 + 1;
        } while (i < indexOf);
        return Collections.unmodifiableMap(linkedHashMap);
    }
}

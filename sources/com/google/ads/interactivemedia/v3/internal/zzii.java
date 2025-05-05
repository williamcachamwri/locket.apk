package com.google.ads.interactivemedia.v3.internal;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzii {
    private String zza = "googleads.g.doubleclick.net";
    private String zzb = "/pagead/ads";
    private final String zzc = "ad.doubleclick.net";
    private String[] zzd = {".doubleclick.net", ".googleadservices.com", ".googlesyndication.com"};
    private final zzia zze;

    @Deprecated
    public zzii(zzia zzia) {
        this.zze = zzia;
    }

    private final Uri zzh(Uri uri, String str) throws zzij {
        uri.getClass();
        try {
            if (uri.getHost().equals(this.zzc) && uri.getPath().contains(";")) {
                try {
                    if (!uri.toString().contains("dc_ms=")) {
                        String uri2 = uri.toString();
                        int indexOf = uri2.indexOf(";adurl");
                        if (indexOf != -1) {
                            int i = indexOf + 1;
                            return Uri.parse(uri2.substring(0, i) + "dc_ms=" + str + ";" + uri2.substring(i));
                        }
                        String encodedPath = uri.getEncodedPath();
                        int indexOf2 = uri2.indexOf(encodedPath);
                        return Uri.parse(uri2.substring(0, encodedPath.length() + indexOf2) + ";dc_ms=" + str + ";" + uri2.substring(indexOf2 + encodedPath.length()));
                    }
                    throw new zzij("Parameter already exists: dc_ms");
                } catch (UnsupportedOperationException unused) {
                    throw new zzij("Provided Uri is not in a valid state");
                }
            }
        } catch (NullPointerException unused2) {
        }
        if (uri.getQueryParameter("ms") == null) {
            String uri3 = uri.toString();
            int indexOf3 = uri3.indexOf("&adurl");
            if (indexOf3 == -1) {
                indexOf3 = uri3.indexOf("?adurl");
            }
            if (indexOf3 == -1) {
                return uri.buildUpon().appendQueryParameter("ms", str).build();
            }
            int i2 = indexOf3 + 1;
            return Uri.parse(uri3.substring(0, i2) + "ms=" + str + "&" + uri3.substring(i2));
        }
        throw new zzij("Query parameter already exists: ms");
    }

    @Deprecated
    public final Uri zza(Uri uri, Context context, View view, Activity activity) throws zzij {
        try {
            return zzh(uri, this.zze.zze(context, uri.getQueryParameter("ai"), (View) null, (Activity) null));
        } catch (UnsupportedOperationException unused) {
            throw new zzij("Provided Uri is not in a valid state");
        }
    }

    @Deprecated
    public final Uri zzb(Uri uri, Context context) throws zzij {
        return zzh(uri, ((zzie) this.zze).zzg(context, (byte[]) null));
    }

    @Deprecated
    public final void zzc(MotionEvent motionEvent) {
        this.zze.zzk(motionEvent);
    }

    public final void zzd(String str, String str2) {
        this.zza = str;
        this.zzb = str2;
    }

    public final void zze(String str) {
        this.zzd = str.split(",");
    }

    public final boolean zzf(Uri uri) {
        uri.getClass();
        try {
            String host = uri.getHost();
            for (String endsWith : this.zzd) {
                if (host.endsWith(endsWith)) {
                    return true;
                }
            }
        } catch (NullPointerException unused) {
        }
        return false;
    }

    public final boolean zzg(Uri uri) {
        uri.getClass();
        try {
            if (!uri.getHost().equals(this.zza) || !uri.getPath().equals(this.zzb)) {
                return false;
            }
            return true;
        } catch (NullPointerException unused) {
        }
    }
}

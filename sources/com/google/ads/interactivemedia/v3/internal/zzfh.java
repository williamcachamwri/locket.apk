package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot;
import com.google.ads.interactivemedia.v3.api.UiElement;
import com.google.ads.interactivemedia.v3.impl.JavaScriptMessage;
import com.google.ads.interactivemedia.v3.impl.data.zzbu;
import com.google.ads.interactivemedia.v3.impl.data.zzbv;
import com.google.ads.interactivemedia.v3.impl.data.zzcl;
import java.net.MalformedURLException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzfh {
    private final zzvr zza;

    public zzfh() {
        zzvs zzvs = new zzvs();
        zzvs.zzb(UiElement.class, zzcl.GSON_TYPE_ADAPTER);
        zzvs.zzd();
        zzvs.zzb(CompanionAdSlot.class, new zzfg(this));
        zzvs.zzc(new zzpu());
        this.zza = zzvs.zza();
    }

    public final JavaScriptMessage zza(String str) throws MalformedURLException {
        Uri parse = Uri.parse(str);
        String path = parse.getPath();
        if (path != null) {
            String substring = path.substring(1);
            if (parse.getQueryParameter("sid") != null) {
                return new JavaScriptMessage(JavaScriptMessage.MsgChannel.zza(substring), JavaScriptMessage.MsgType.zza(parse.getQueryParameter("type")), parse.getQueryParameter("sid"), this.zza.zze(parse.getQueryParameter("data"), zzbu.class));
            }
            throw new MalformedURLException("Session id must be provided in message.");
        }
        throw new MalformedURLException("URL must have message.");
    }

    public final JavaScriptMessage zzb(String str) {
        zzbv zzbv = (zzbv) this.zza.zze(str, zzbv.class);
        if (zzbv.sid != null) {
            return new JavaScriptMessage(JavaScriptMessage.MsgChannel.zza(zzbv.name), JavaScriptMessage.MsgType.zza(zzbv.type), zzbv.sid, this.zza.zze(zzbv.data, zzbu.class));
        }
        throw new NullPointerException("Session id must be provided in message.");
    }

    public final String zzc(JavaScriptMessage javaScriptMessage) {
        zzro zzro = new zzro();
        zzro.zza("type", javaScriptMessage.zzb());
        zzro.zza("sid", javaScriptMessage.zzd());
        if (javaScriptMessage.zzc() != null) {
            zzro.zza("data", javaScriptMessage.zzc());
        }
        return String.format("%s('%s', %s);", new Object[]{"javascript:adsense.mobileads.afmanotify.receiveMessage", javaScriptMessage.zza(), this.zza.zzf(zzro.zzc())});
    }
}

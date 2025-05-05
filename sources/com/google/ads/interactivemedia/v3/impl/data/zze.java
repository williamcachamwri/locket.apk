package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.api.zza;
import com.google.ads.interactivemedia.v3.api.zzb;
import com.google.ads.interactivemedia.v3.impl.JavaScriptMessage;
import com.google.ads.interactivemedia.v3.impl.zzba;
import com.google.ads.interactivemedia.v3.internal.zzrz;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zze implements zza {
    private final List<zzb> icons;
    private final zzba router;
    private final String sessionId;

    public zze(zzba zzba, List<zzb> list, String str) {
        this.router = zzba;
        this.icons = list;
        this.sessionId = str;
    }

    private Map<String, Object> createIconData(zzb zzb) {
        HashMap zzb2 = zzrz.zzb(1);
        zzb2.put("id", Integer.valueOf(zzb.getId()));
        return zzb2;
    }

    public List<zzb> getIcons() {
        return this.icons;
    }

    public void iconClicked(zzb zzb) {
        this.router.zzn(new JavaScriptMessage(JavaScriptMessage.MsgChannel.nativeUi, JavaScriptMessage.MsgType.iconClicked, this.sessionId, createIconData(zzb)));
    }

    public void iconShown(zzb zzb) {
        this.router.zzn(new JavaScriptMessage(JavaScriptMessage.MsgChannel.nativeUi, JavaScriptMessage.MsgType.iconRendered, this.sessionId, createIconData(zzb)));
    }
}

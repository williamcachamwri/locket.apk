package com.google.ads.interactivemedia.v3.impl;

import com.google.ads.interactivemedia.v3.impl.JavaScriptMessage;
import com.google.ads.interactivemedia.v3.impl.data.zzbz;
import com.google.ads.interactivemedia.v3.internal.zzfk;
import com.google.ads.interactivemedia.v3.internal.zzug;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzbn implements zzug {
    final /* synthetic */ String zza;
    final /* synthetic */ zzbs zzb;

    zzbn(zzbs zzbs, String str) {
        this.zza = str;
        this.zzb = zzbs;
    }

    public final void zza(Throwable th) {
        zzfk.zzb("Failure to make Native-layer network request", th);
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        this.zzb.zzb.zzn(new JavaScriptMessage(JavaScriptMessage.MsgChannel.nativeXhr, JavaScriptMessage.MsgType.nativeResponse, this.zza, (zzbz) obj));
    }
}

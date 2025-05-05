package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import com.google.ads.interactivemedia.v3.impl.JavaScriptMessage;
import com.google.ads.interactivemedia.v3.impl.zzbh;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzfl implements zzbh {
    private final Context zza;
    private boolean zzb = false;

    private zzfl(Context context, zzfm zzfm) {
        this.zza = context;
    }

    public static zzfl zzb(Context context) {
        return new zzfl(context, new zzfm());
    }

    public final void zza(JavaScriptMessage javaScriptMessage) {
        boolean z;
        JavaScriptMessage.MsgType msgType = JavaScriptMessage.MsgType.activate;
        int ordinal = javaScriptMessage.zzb().ordinal();
        if (ordinal == 53) {
            zzcj.zza(this.zza);
            z = true;
        } else if (ordinal == 54) {
            z = false;
        } else {
            return;
        }
        this.zzb = z;
    }

    public final boolean zzc() {
        return this.zzb;
    }
}

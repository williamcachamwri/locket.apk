package com.google.ads.interactivemedia.v3.impl;

import android.content.Context;
import com.google.ads.interactivemedia.v3.impl.JavaScriptMessage;
import com.google.ads.interactivemedia.v3.impl.data.zzby;
import com.google.ads.interactivemedia.v3.impl.data.zzbz;
import com.google.ads.interactivemedia.v3.internal.zzfk;
import com.google.ads.interactivemedia.v3.internal.zzuk;
import com.google.ads.interactivemedia.v3.internal.zzuv;
import com.google.ads.interactivemedia.v3.internal.zzvb;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzbs {
    private final zzbq zza;
    /* access modifiers changed from: private */
    public final zzbi zzb;
    private final zzuv zzc;

    zzbs(Context context, zzbi zzbi, ExecutorService executorService, zzbq zzbq) {
        this.zzc = zzvb.zza(executorService);
        this.zza = zzbq;
        this.zzb = zzbi;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzbz zzb(zzby zzby) throws Exception {
        return this.zza.zza(zzby);
    }

    /* access modifiers changed from: package-private */
    public final void zzc(JavaScriptMessage.MsgType msgType, String str, zzby zzby) {
        JavaScriptMessage.MsgType msgType2 = JavaScriptMessage.MsgType.activate;
        if (msgType.ordinal() != 35) {
            zzfk.zzc("Unexpected network request of type".concat(String.valueOf(String.valueOf(msgType))));
        } else {
            zzuk.zze(this.zzc.zza(new zzbm(this, zzby)), new zzbn(this, str), this.zzc);
        }
    }
}

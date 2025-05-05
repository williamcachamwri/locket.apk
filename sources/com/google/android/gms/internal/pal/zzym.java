package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;
import javax.crypto.Mac;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzym extends ThreadLocal {
    final /* synthetic */ zzyn zza;

    zzym(zzyn zzyn) {
        this.zza = zzyn;
    }

    /* access modifiers changed from: protected */
    /* renamed from: zza */
    public final Mac initialValue() {
        try {
            Mac mac = (Mac) zzxz.zzb.zza(this.zza.zzb);
            mac.init(this.zza.zzc);
            return mac;
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException(e);
        }
    }
}

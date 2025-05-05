package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
public final class zzej extends zzel {
    public static zzet zza(Object obj) {
        return new zzem(obj);
    }

    public static void zzb(zzet zzet, zzeh zzeh, Executor executor) {
        zzet.zzl(new zzei(zzet, zzeh), executor);
    }

    public static zzet zzc(zzxh zzxh, Executor executor) {
        zzew zzew = new zzew(zzxh);
        zzew.run();
        return zzew;
    }
}

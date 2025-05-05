package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
final class zzwz extends LazyInstanceMap {
    private zzwz() {
        throw null;
    }

    /* synthetic */ zzwz(zzwy zzwy) {
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ Object create(Object obj) {
        zzwh zzwh = (zzwh) obj;
        MlKitContext instance = MlKitContext.getInstance();
        return new zzwp(instance.getApplicationContext(), (SharedPrefManager) instance.get(SharedPrefManager.class), new zzwi(MlKitContext.getInstance().getApplicationContext(), zzwh), zzwh.zzb());
    }
}

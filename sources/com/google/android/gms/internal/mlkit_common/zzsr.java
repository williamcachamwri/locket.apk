package com.google.android.gms.internal.mlkit_common;

import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;

/* compiled from: com.google.mlkit:common@@18.11.0 */
final class zzsr extends LazyInstanceMap {
    private zzsr() {
        throw null;
    }

    /* synthetic */ zzsr(zzsq zzsq) {
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ Object create(Object obj) {
        zzsb zzsb = (zzsb) obj;
        MlKitContext instance = MlKitContext.getInstance();
        return new zzsh(instance.getApplicationContext(), (SharedPrefManager) instance.get(SharedPrefManager.class), new zzsc(MlKitContext.getInstance().getApplicationContext(), zzsb), zzsb.zzb());
    }
}

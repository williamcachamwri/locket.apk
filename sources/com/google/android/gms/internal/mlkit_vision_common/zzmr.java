package com.google.android.gms.internal.mlkit_vision_common;

import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;

/* compiled from: com.google.mlkit:vision-common@@17.3.0 */
final class zzmr extends LazyInstanceMap {
    private zzmr() {
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ Object create(Object obj) {
        zzme zzme = (zzme) obj;
        MlKitContext instance = MlKitContext.getInstance();
        return new zzmj(instance.getApplicationContext(), (SharedPrefManager) instance.get(SharedPrefManager.class), new zzmf(MlKitContext.getInstance().getApplicationContext(), zzme), zzme.zzb());
    }

    /* synthetic */ zzmr(zzmq zzmq) {
    }
}

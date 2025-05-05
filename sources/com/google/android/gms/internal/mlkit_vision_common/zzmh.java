package com.google.android.gms.internal.mlkit_vision_common;

import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.util.concurrent.Callable;

/* compiled from: com.google.mlkit:vision-common@@17.3.0 */
public final /* synthetic */ class zzmh implements Callable {
    public final /* synthetic */ SharedPrefManager zza;

    public /* synthetic */ zzmh(SharedPrefManager sharedPrefManager) {
        this.zza = sharedPrefManager;
    }

    public final Object call() {
        return this.zza.getMlSdkInstanceId();
    }
}

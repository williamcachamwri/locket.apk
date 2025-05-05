package com.google.mlkit.vision.barcode.internal;

import com.google.android.gms.internal.mlkit_vision_barcode.zzcs;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
public class BarcodeRegistrar implements ComponentRegistrar {
    public static final /* synthetic */ int zza = 0;

    public final List getComponents() {
        return zzcs.zzh(Component.builder(zzi.class).add(Dependency.required((Class<?>) MlKitContext.class)).factory(new zzc()).build(), Component.builder(zzg.class).add(Dependency.required((Class<?>) zzi.class)).add(Dependency.required((Class<?>) ExecutorSelector.class)).add(Dependency.required((Class<?>) MlKitContext.class)).factory(new zzd()).build());
    }
}

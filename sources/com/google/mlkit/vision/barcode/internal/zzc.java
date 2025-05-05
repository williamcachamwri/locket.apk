package com.google.mlkit.vision.barcode.internal;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.mlkit.common.sdkinternal.MlKitContext;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
public final /* synthetic */ class zzc implements ComponentFactory {
    public final Object create(ComponentContainer componentContainer) {
        int i = BarcodeRegistrar.zza;
        return new zzi((MlKitContext) componentContainer.get(MlKitContext.class));
    }
}

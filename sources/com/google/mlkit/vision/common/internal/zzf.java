package com.google.mlkit.vision.common.internal;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.mlkit.vision.common.internal.MultiFlavorDetectorCreator;

/* compiled from: com.google.mlkit:vision-common@@17.3.0 */
public final /* synthetic */ class zzf implements ComponentFactory {
    public static final /* synthetic */ zzf zza = new zzf();

    private /* synthetic */ zzf() {
    }

    public final Object create(ComponentContainer componentContainer) {
        int i = VisionCommonRegistrar.zza;
        return new MultiFlavorDetectorCreator(componentContainer.setOf(MultiFlavorDetectorCreator.Registration.class));
    }
}

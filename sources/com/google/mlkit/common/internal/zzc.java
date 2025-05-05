package com.google.mlkit.common.internal;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.mlkit.common.model.RemoteModelManager;

/* compiled from: com.google.mlkit:common@@18.11.0 */
public final /* synthetic */ class zzc implements ComponentFactory {
    public final Object create(ComponentContainer componentContainer) {
        int i = CommonComponentRegistrar.zza;
        return new RemoteModelManager(componentContainer.setOf(RemoteModelManager.RemoteModelManagerRegistration.class));
    }
}

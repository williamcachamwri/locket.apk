package com.google.mlkit.common.internal;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.mlkit.common.sdkinternal.MlKitContext;

/* compiled from: com.google.mlkit:common@@18.11.0 */
public final /* synthetic */ class zzg implements ComponentFactory {
    public final Object create(ComponentContainer componentContainer) {
        return new com.google.mlkit.common.internal.model.zzg((MlKitContext) componentContainer.get(MlKitContext.class));
    }
}

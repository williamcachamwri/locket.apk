package com.google.mlkit.common.internal;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.model.ModelFileHelper;

/* compiled from: com.google.mlkit:common@@18.11.0 */
public final /* synthetic */ class zza implements ComponentFactory {
    public final Object create(ComponentContainer componentContainer) {
        int i = CommonComponentRegistrar.zza;
        return new ModelFileHelper((MlKitContext) componentContainer.get(MlKitContext.class));
    }
}

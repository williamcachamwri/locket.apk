package com.google.firebase.functions;

import android.content.Context;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.appcheck.interop.InteropAppCheckTokenProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.Qualified;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import java.util.concurrent.Executor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FunctionsRegistrar$$ExternalSyntheticLambda0 implements ComponentFactory {
    public final /* synthetic */ Qualified f$0;
    public final /* synthetic */ Qualified f$1;

    public /* synthetic */ FunctionsRegistrar$$ExternalSyntheticLambda0(Qualified qualified, Qualified qualified2) {
        this.f$0 = qualified;
        this.f$1 = qualified2;
    }

    public final Object create(ComponentContainer componentContainer) {
        return DaggerFunctionsComponent.builder().setApplicationContext((Context) componentContainer.get(Context.class)).setFirebaseOptions((FirebaseOptions) componentContainer.get(FirebaseOptions.class)).setLiteExecutor((Executor) componentContainer.get(this.f$0)).setUiExecutor((Executor) componentContainer.get(this.f$1)).setAuth(componentContainer.getProvider(InternalAuthProvider.class)).setIid(componentContainer.getProvider(FirebaseInstanceIdInternal.class)).setAppCheck(componentContainer.getDeferred(InteropAppCheckTokenProvider.class)).build().getMultiResourceComponent();
    }
}

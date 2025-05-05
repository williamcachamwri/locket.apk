package com.google.firebase.functions;

import android.content.Context;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.annotations.concurrent.Lightweight;
import com.google.firebase.annotations.concurrent.UiThread;
import com.google.firebase.appcheck.interop.InteropAppCheckTokenProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.components.Qualified;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;

public class FunctionsRegistrar implements ComponentRegistrar {
    private static final String LIBRARY_NAME = "fire-fn";

    public List<Component<?>> getComponents() {
        Qualified<Executor> qualified = Qualified.qualified(Lightweight.class, Executor.class);
        Qualified<Executor> qualified2 = Qualified.qualified(UiThread.class, Executor.class);
        return Arrays.asList(new Component[]{Component.builder(FunctionsMultiResourceComponent.class).name(LIBRARY_NAME).add(Dependency.required((Class<?>) Context.class)).add(Dependency.required((Class<?>) FirebaseOptions.class)).add(Dependency.optionalProvider((Class<?>) InternalAuthProvider.class)).add(Dependency.requiredProvider((Class<?>) FirebaseInstanceIdInternal.class)).add(Dependency.deferred((Class<?>) InteropAppCheckTokenProvider.class)).add(Dependency.required((Qualified<?>) qualified)).add(Dependency.required((Qualified<?>) qualified2)).factory(new FunctionsRegistrar$$ExternalSyntheticLambda0(qualified, qualified2)).build(), LibraryVersionComponent.create(LIBRARY_NAME, "21.0.0")});
    }
}

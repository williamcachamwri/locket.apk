package com.google.firebase.appcheck.debug;

import com.google.firebase.FirebaseApp;
import com.google.firebase.annotations.concurrent.Background;
import com.google.firebase.annotations.concurrent.Blocking;
import com.google.firebase.annotations.concurrent.Lightweight;
import com.google.firebase.appcheck.debug.internal.DebugAppCheckProvider;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.components.Qualified;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;

public class FirebaseAppCheckDebugRegistrar implements ComponentRegistrar {
    private static final String LIBRARY_NAME = "fire-app-check-debug";

    public List<Component<?>> getComponents() {
        Qualified<Executor> qualified = Qualified.qualified(Lightweight.class, Executor.class);
        Qualified<Executor> qualified2 = Qualified.qualified(Background.class, Executor.class);
        Qualified<Executor> qualified3 = Qualified.qualified(Blocking.class, Executor.class);
        return Arrays.asList(new Component[]{Component.builder(DebugAppCheckProvider.class).name(LIBRARY_NAME).add(Dependency.required((Class<?>) FirebaseApp.class)).add(Dependency.optionalProvider((Class<?>) InternalDebugSecretProvider.class)).add(Dependency.required((Qualified<?>) qualified)).add(Dependency.required((Qualified<?>) qualified2)).add(Dependency.required((Qualified<?>) qualified3)).factory(new FirebaseAppCheckDebugRegistrar$$ExternalSyntheticLambda0(qualified, qualified2, qualified3)).build(), LibraryVersionComponent.create(LIBRARY_NAME, "18.0.0")});
    }

    static /* synthetic */ DebugAppCheckProvider lambda$getComponents$0(Qualified qualified, Qualified qualified2, Qualified qualified3, ComponentContainer componentContainer) {
        return new DebugAppCheckProvider((FirebaseApp) componentContainer.get(FirebaseApp.class), componentContainer.getProvider(InternalDebugSecretProvider.class), (Executor) componentContainer.get(qualified), (Executor) componentContainer.get(qualified2), (Executor) componentContainer.get(qualified3));
    }
}

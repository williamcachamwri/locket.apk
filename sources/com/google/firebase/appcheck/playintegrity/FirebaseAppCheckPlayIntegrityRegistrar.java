package com.google.firebase.appcheck.playintegrity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.annotations.concurrent.Blocking;
import com.google.firebase.annotations.concurrent.Lightweight;
import com.google.firebase.appcheck.playintegrity.internal.PlayIntegrityAppCheckProvider;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.components.Qualified;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;

public class FirebaseAppCheckPlayIntegrityRegistrar implements ComponentRegistrar {
    private static final String LIBRARY_NAME = "fire-app-check-play-integrity";

    public List<Component<?>> getComponents() {
        Qualified<Executor> qualified = Qualified.qualified(Lightweight.class, Executor.class);
        Qualified<Executor> qualified2 = Qualified.qualified(Blocking.class, Executor.class);
        return Arrays.asList(new Component[]{Component.builder(PlayIntegrityAppCheckProvider.class).name(LIBRARY_NAME).add(Dependency.required((Class<?>) FirebaseApp.class)).add(Dependency.required((Qualified<?>) qualified)).add(Dependency.required((Qualified<?>) qualified2)).factory(new FirebaseAppCheckPlayIntegrityRegistrar$$ExternalSyntheticLambda0(qualified, qualified2)).build(), LibraryVersionComponent.create(LIBRARY_NAME, "18.0.0")});
    }

    static /* synthetic */ PlayIntegrityAppCheckProvider lambda$getComponents$0(Qualified qualified, Qualified qualified2, ComponentContainer componentContainer) {
        return new PlayIntegrityAppCheckProvider((FirebaseApp) componentContainer.get(FirebaseApp.class), (Executor) componentContainer.get(qualified), (Executor) componentContainer.get(qualified2));
    }
}

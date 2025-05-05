package com.google.firebase.appcheck;

import com.google.firebase.FirebaseApp;
import com.google.firebase.annotations.concurrent.Background;
import com.google.firebase.annotations.concurrent.Blocking;
import com.google.firebase.annotations.concurrent.Lightweight;
import com.google.firebase.annotations.concurrent.UiThread;
import com.google.firebase.appcheck.internal.DefaultFirebaseAppCheck;
import com.google.firebase.appcheck.interop.InteropAppCheckTokenProvider;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.components.Qualified;
import com.google.firebase.heartbeatinfo.HeartBeatConsumerComponent;
import com.google.firebase.heartbeatinfo.HeartBeatController;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

public class FirebaseAppCheckRegistrar implements ComponentRegistrar {
    private static final String LIBRARY_NAME = "fire-app-check";

    public List<Component<?>> getComponents() {
        Qualified<Executor> qualified = Qualified.qualified(UiThread.class, Executor.class);
        Qualified<Executor> qualified2 = Qualified.qualified(Lightweight.class, Executor.class);
        Qualified<Executor> qualified3 = Qualified.qualified(Background.class, Executor.class);
        Qualified<ScheduledExecutorService> qualified4 = Qualified.qualified(Blocking.class, ScheduledExecutorService.class);
        return Arrays.asList(new Component[]{Component.builder(FirebaseAppCheck.class, (Class<? super T>[]) new Class[]{InteropAppCheckTokenProvider.class}).name(LIBRARY_NAME).add(Dependency.required((Class<?>) FirebaseApp.class)).add(Dependency.required((Qualified<?>) qualified)).add(Dependency.required((Qualified<?>) qualified2)).add(Dependency.required((Qualified<?>) qualified3)).add(Dependency.required((Qualified<?>) qualified4)).add(Dependency.optionalProvider((Class<?>) HeartBeatController.class)).factory(new FirebaseAppCheckRegistrar$$ExternalSyntheticLambda0(qualified, qualified2, qualified3, qualified4)).alwaysEager().build(), HeartBeatConsumerComponent.create(), LibraryVersionComponent.create(LIBRARY_NAME, "18.0.0")});
    }

    static /* synthetic */ FirebaseAppCheck lambda$getComponents$0(Qualified qualified, Qualified qualified2, Qualified qualified3, Qualified qualified4, ComponentContainer componentContainer) {
        return new DefaultFirebaseAppCheck((FirebaseApp) componentContainer.get(FirebaseApp.class), componentContainer.getProvider(HeartBeatController.class), (Executor) componentContainer.get(qualified), (Executor) componentContainer.get(qualified2), (Executor) componentContainer.get(qualified3), (ScheduledExecutorService) componentContainer.get(qualified4));
    }
}

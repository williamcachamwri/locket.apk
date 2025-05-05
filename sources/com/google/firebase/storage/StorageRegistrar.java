package com.google.firebase.storage;

import com.google.firebase.FirebaseApp;
import com.google.firebase.annotations.concurrent.Blocking;
import com.google.firebase.annotations.concurrent.UiThread;
import com.google.firebase.appcheck.interop.InteropAppCheckTokenProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.components.Qualified;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;

public class StorageRegistrar implements ComponentRegistrar {
    private static final String LIBRARY_NAME = "fire-gcs";
    Qualified<Executor> blockingExecutor = Qualified.qualified(Blocking.class, Executor.class);
    Qualified<Executor> uiExecutor = Qualified.qualified(UiThread.class, Executor.class);

    public List<Component<?>> getComponents() {
        return Arrays.asList(new Component[]{Component.builder(FirebaseStorageComponent.class).name(LIBRARY_NAME).add(Dependency.required((Class<?>) FirebaseApp.class)).add(Dependency.required((Qualified<?>) this.blockingExecutor)).add(Dependency.required((Qualified<?>) this.uiExecutor)).add(Dependency.optionalProvider((Class<?>) InternalAuthProvider.class)).add(Dependency.optionalProvider((Class<?>) InteropAppCheckTokenProvider.class)).factory(new StorageRegistrar$$ExternalSyntheticLambda0(this)).build(), LibraryVersionComponent.create(LIBRARY_NAME, BuildConfig.VERSION_NAME)});
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getComponents$0$com-google-firebase-storage-StorageRegistrar  reason: not valid java name */
    public /* synthetic */ FirebaseStorageComponent m843lambda$getComponents$0$comgooglefirebasestorageStorageRegistrar(ComponentContainer componentContainer) {
        return new FirebaseStorageComponent((FirebaseApp) componentContainer.get(FirebaseApp.class), componentContainer.getProvider(InternalAuthProvider.class), componentContainer.getProvider(InteropAppCheckTokenProvider.class), (Executor) componentContainer.get(this.blockingExecutor), (Executor) componentContainer.get(this.uiExecutor));
    }
}

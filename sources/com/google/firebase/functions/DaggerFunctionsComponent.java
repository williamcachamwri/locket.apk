package com.google.firebase.functions;

import android.content.Context;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.appcheck.interop.InteropAppCheckTokenProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.functions.FunctionsComponent;
import com.google.firebase.functions.FunctionsMultiResourceComponent;
import com.google.firebase.functions.dagger.internal.DoubleCheck;
import com.google.firebase.functions.dagger.internal.Factory;
import com.google.firebase.functions.dagger.internal.InstanceFactory;
import com.google.firebase.functions.dagger.internal.Preconditions;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import java.util.concurrent.Executor;

final class DaggerFunctionsComponent {
    private DaggerFunctionsComponent() {
    }

    public static FunctionsComponent.Builder builder() {
        return new Builder();
    }

    private static final class Builder implements FunctionsComponent.Builder {
        private Deferred<InteropAppCheckTokenProvider> setAppCheck;
        private Context setApplicationContext;
        private Provider<InternalAuthProvider> setAuth;
        private FirebaseOptions setFirebaseOptions;
        private Provider<FirebaseInstanceIdInternal> setIid;
        private Executor setLiteExecutor;
        private Executor setUiExecutor;

        private Builder() {
        }

        public Builder setApplicationContext(Context context) {
            this.setApplicationContext = (Context) Preconditions.checkNotNull(context);
            return this;
        }

        public Builder setFirebaseOptions(FirebaseOptions firebaseOptions) {
            this.setFirebaseOptions = (FirebaseOptions) Preconditions.checkNotNull(firebaseOptions);
            return this;
        }

        public Builder setLiteExecutor(Executor executor) {
            this.setLiteExecutor = (Executor) Preconditions.checkNotNull(executor);
            return this;
        }

        public Builder setUiExecutor(Executor executor) {
            this.setUiExecutor = (Executor) Preconditions.checkNotNull(executor);
            return this;
        }

        /* JADX WARNING: type inference failed for: r1v0, types: [com.google.firebase.inject.Provider<com.google.firebase.auth.internal.InternalAuthProvider>, java.lang.Object] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.firebase.functions.DaggerFunctionsComponent.Builder setAuth(com.google.firebase.inject.Provider<com.google.firebase.auth.internal.InternalAuthProvider> r1) {
            /*
                r0 = this;
                java.lang.Object r1 = com.google.firebase.functions.dagger.internal.Preconditions.checkNotNull(r1)
                com.google.firebase.inject.Provider r1 = (com.google.firebase.inject.Provider) r1
                r0.setAuth = r1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.functions.DaggerFunctionsComponent.Builder.setAuth(com.google.firebase.inject.Provider):com.google.firebase.functions.DaggerFunctionsComponent$Builder");
        }

        /* JADX WARNING: type inference failed for: r1v0, types: [com.google.firebase.inject.Provider<com.google.firebase.iid.internal.FirebaseInstanceIdInternal>, java.lang.Object] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.firebase.functions.DaggerFunctionsComponent.Builder setIid(com.google.firebase.inject.Provider<com.google.firebase.iid.internal.FirebaseInstanceIdInternal> r1) {
            /*
                r0 = this;
                java.lang.Object r1 = com.google.firebase.functions.dagger.internal.Preconditions.checkNotNull(r1)
                com.google.firebase.inject.Provider r1 = (com.google.firebase.inject.Provider) r1
                r0.setIid = r1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.functions.DaggerFunctionsComponent.Builder.setIid(com.google.firebase.inject.Provider):com.google.firebase.functions.DaggerFunctionsComponent$Builder");
        }

        /* JADX WARNING: type inference failed for: r1v0, types: [com.google.firebase.inject.Deferred<com.google.firebase.appcheck.interop.InteropAppCheckTokenProvider>, java.lang.Object] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.firebase.functions.DaggerFunctionsComponent.Builder setAppCheck(com.google.firebase.inject.Deferred<com.google.firebase.appcheck.interop.InteropAppCheckTokenProvider> r1) {
            /*
                r0 = this;
                java.lang.Object r1 = com.google.firebase.functions.dagger.internal.Preconditions.checkNotNull(r1)
                com.google.firebase.inject.Deferred r1 = (com.google.firebase.inject.Deferred) r1
                r0.setAppCheck = r1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.functions.DaggerFunctionsComponent.Builder.setAppCheck(com.google.firebase.inject.Deferred):com.google.firebase.functions.DaggerFunctionsComponent$Builder");
        }

        public FunctionsComponent build() {
            Preconditions.checkBuilderRequirement(this.setApplicationContext, Context.class);
            Preconditions.checkBuilderRequirement(this.setFirebaseOptions, FirebaseOptions.class);
            Preconditions.checkBuilderRequirement(this.setLiteExecutor, Executor.class);
            Preconditions.checkBuilderRequirement(this.setUiExecutor, Executor.class);
            Preconditions.checkBuilderRequirement(this.setAuth, Provider.class);
            Preconditions.checkBuilderRequirement(this.setIid, Provider.class);
            Preconditions.checkBuilderRequirement(this.setAppCheck, Deferred.class);
            return new FunctionsComponentImpl(this.setApplicationContext, this.setFirebaseOptions, this.setLiteExecutor, this.setUiExecutor, this.setAuth, this.setIid, this.setAppCheck);
        }
    }

    private static final class FunctionsComponentImpl implements FunctionsComponent {
        private javax.inject.Provider<String> bindProjectIdProvider;
        private javax.inject.Provider<FirebaseContextProvider> firebaseContextProvider;
        private javax.inject.Provider<FunctionsMultiResourceComponent.FirebaseFunctionsFactory> firebaseFunctionsFactoryProvider;
        private FirebaseFunctions_Factory firebaseFunctionsProvider;
        private final FunctionsComponentImpl functionsComponentImpl;
        private javax.inject.Provider<FunctionsMultiResourceComponent> functionsMultiResourceComponentProvider;
        private javax.inject.Provider<Deferred<InteropAppCheckTokenProvider>> setAppCheckProvider;
        private javax.inject.Provider<Context> setApplicationContextProvider;
        private javax.inject.Provider<Provider<InternalAuthProvider>> setAuthProvider;
        private javax.inject.Provider<FirebaseOptions> setFirebaseOptionsProvider;
        private javax.inject.Provider<Provider<FirebaseInstanceIdInternal>> setIidProvider;
        private javax.inject.Provider<Executor> setLiteExecutorProvider;
        private javax.inject.Provider<Executor> setUiExecutorProvider;

        private FunctionsComponentImpl(Context context, FirebaseOptions firebaseOptions, Executor executor, Executor executor2, Provider<InternalAuthProvider> provider, Provider<FirebaseInstanceIdInternal> provider2, Deferred<InteropAppCheckTokenProvider> deferred) {
            this.functionsComponentImpl = this;
            initialize(context, firebaseOptions, executor, executor2, provider, provider2, deferred);
        }

        private void initialize(Context context, FirebaseOptions firebaseOptions, Executor executor, Executor executor2, Provider<InternalAuthProvider> provider, Provider<FirebaseInstanceIdInternal> provider2, Deferred<InteropAppCheckTokenProvider> deferred) {
            this.setApplicationContextProvider = InstanceFactory.create(context);
            Factory create = InstanceFactory.create(firebaseOptions);
            this.setFirebaseOptionsProvider = create;
            this.bindProjectIdProvider = FunctionsComponent_MainModule_BindProjectIdFactory.create(create);
            this.setAuthProvider = InstanceFactory.create(provider);
            this.setIidProvider = InstanceFactory.create(provider2);
            this.setAppCheckProvider = InstanceFactory.create(deferred);
            Factory create2 = InstanceFactory.create(executor);
            this.setLiteExecutorProvider = create2;
            this.firebaseContextProvider = DoubleCheck.provider(FirebaseContextProvider_Factory.create(this.setAuthProvider, this.setIidProvider, this.setAppCheckProvider, create2));
            Factory create3 = InstanceFactory.create(executor2);
            this.setUiExecutorProvider = create3;
            FirebaseFunctions_Factory create4 = FirebaseFunctions_Factory.create(this.setApplicationContextProvider, this.bindProjectIdProvider, this.firebaseContextProvider, this.setLiteExecutorProvider, create3);
            this.firebaseFunctionsProvider = create4;
            javax.inject.Provider<FunctionsMultiResourceComponent.FirebaseFunctionsFactory> create5 = FunctionsMultiResourceComponent_FirebaseFunctionsFactory_Impl.create(create4);
            this.firebaseFunctionsFactoryProvider = create5;
            this.functionsMultiResourceComponentProvider = DoubleCheck.provider(FunctionsMultiResourceComponent_Factory.create(create5));
        }

        public FunctionsMultiResourceComponent getMultiResourceComponent() {
            return this.functionsMultiResourceComponentProvider.get();
        }
    }
}

package com.google.firebase.functions;

import com.google.firebase.functions.dagger.assisted.Assisted;
import com.google.firebase.functions.dagger.assisted.AssistedFactory;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class FunctionsMultiResourceComponent {
    private final FirebaseFunctionsFactory functionsFactory;
    private final Map<String, FirebaseFunctions> instances = new HashMap();

    @AssistedFactory
    interface FirebaseFunctionsFactory {
        FirebaseFunctions create(@Assisted String str);
    }

    @Inject
    FunctionsMultiResourceComponent(FirebaseFunctionsFactory firebaseFunctionsFactory) {
        this.functionsFactory = firebaseFunctionsFactory;
    }

    /* access modifiers changed from: package-private */
    public synchronized FirebaseFunctions get(String str) {
        FirebaseFunctions firebaseFunctions;
        firebaseFunctions = this.instances.get(str);
        if (firebaseFunctions == null) {
            firebaseFunctions = this.functionsFactory.create(str);
            this.instances.put(str, firebaseFunctions);
        }
        return firebaseFunctions;
    }
}

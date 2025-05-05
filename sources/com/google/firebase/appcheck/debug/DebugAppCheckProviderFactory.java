package com.google.firebase.appcheck.debug;

import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.AppCheckProvider;
import com.google.firebase.appcheck.AppCheckProviderFactory;
import com.google.firebase.appcheck.debug.internal.DebugAppCheckProvider;

public class DebugAppCheckProviderFactory implements AppCheckProviderFactory {
    private static final DebugAppCheckProviderFactory instance = new DebugAppCheckProviderFactory();

    private DebugAppCheckProviderFactory() {
    }

    public static DebugAppCheckProviderFactory getInstance() {
        return instance;
    }

    public AppCheckProvider create(FirebaseApp firebaseApp) {
        return (AppCheckProvider) firebaseApp.get(DebugAppCheckProvider.class);
    }
}

package com.google.firebase.appcheck.playintegrity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.AppCheckProvider;
import com.google.firebase.appcheck.AppCheckProviderFactory;
import com.google.firebase.appcheck.playintegrity.internal.PlayIntegrityAppCheckProvider;

public class PlayIntegrityAppCheckProviderFactory implements AppCheckProviderFactory {
    private static final PlayIntegrityAppCheckProviderFactory instance = new PlayIntegrityAppCheckProviderFactory();

    public static PlayIntegrityAppCheckProviderFactory getInstance() {
        return instance;
    }

    public AppCheckProvider create(FirebaseApp firebaseApp) {
        return (AppCheckProvider) firebaseApp.get(PlayIntegrityAppCheckProvider.class);
    }
}

package com.google.firebase.appcheck;

import com.google.firebase.FirebaseApp;

public interface AppCheckProviderFactory {
    AppCheckProvider create(FirebaseApp firebaseApp);
}

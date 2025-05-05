package com.google.firebase.storage.network;

import com.google.firebase.FirebaseApp;
import com.google.firebase.perf.FirebasePerformance;
import com.google.firebase.storage.internal.StorageReferenceUri;

public class DeleteNetworkRequest extends NetworkRequest {
    /* access modifiers changed from: protected */
    public String getAction() {
        return FirebasePerformance.HttpMethod.DELETE;
    }

    public DeleteNetworkRequest(StorageReferenceUri storageReferenceUri, FirebaseApp firebaseApp) {
        super(storageReferenceUri, firebaseApp);
    }
}

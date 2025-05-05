package com.google.firebase.storage.network;

import com.google.firebase.FirebaseApp;
import com.google.firebase.perf.FirebasePerformance;
import com.google.firebase.storage.internal.StorageReferenceUri;
import org.json.JSONObject;

public class UpdateMetadataNetworkRequest extends NetworkRequest {
    private final JSONObject metadata;

    /* access modifiers changed from: protected */
    public String getAction() {
        return FirebasePerformance.HttpMethod.PUT;
    }

    public UpdateMetadataNetworkRequest(StorageReferenceUri storageReferenceUri, FirebaseApp firebaseApp, JSONObject jSONObject) {
        super(storageReferenceUri, firebaseApp);
        this.metadata = jSONObject;
        setCustomHeader("X-HTTP-Method-Override", FirebasePerformance.HttpMethod.PATCH);
    }

    /* access modifiers changed from: protected */
    public JSONObject getOutputJSON() {
        return this.metadata;
    }
}

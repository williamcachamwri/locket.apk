package com.google.firebase.storage.network;

import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.internal.StorageReferenceUri;

abstract class ResumableNetworkRequest extends NetworkRequest {
    static final String COMMAND = "X-Goog-Upload-Command";
    static final String CONTENT_TYPE = "X-Goog-Upload-Header-Content-Type";
    static final String OFFSET = "X-Goog-Upload-Offset";
    static final String PROTOCOL = "X-Goog-Upload-Protocol";

    ResumableNetworkRequest(StorageReferenceUri storageReferenceUri, FirebaseApp firebaseApp) {
        super(storageReferenceUri, firebaseApp);
    }
}

package com.google.firebase.storage.network;

import com.google.common.net.HttpHeaders;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.internal.StorageReferenceUri;
import java.util.Collections;
import java.util.Map;

public class GetNetworkRequest extends NetworkRequest {
    private static final String TAG = "GetNetworkRequest";

    /* access modifiers changed from: protected */
    public String getAction() {
        return "GET";
    }

    public GetNetworkRequest(StorageReferenceUri storageReferenceUri, FirebaseApp firebaseApp, long j) {
        super(storageReferenceUri, firebaseApp);
        if (j != 0) {
            super.setCustomHeader(HttpHeaders.RANGE, "bytes=" + j + "-");
        }
    }

    /* access modifiers changed from: protected */
    public Map<String, String> getQueryParameters() {
        return Collections.singletonMap("alt", SVGParser.XML_STYLESHEET_ATTR_MEDIA);
    }
}

package com.google.firebase.storage.internal;

import android.net.Uri;
import com.google.firebase.emulators.EmulatedServiceSettings;
import com.google.firebase.storage.network.NetworkRequest;

public class StorageReferenceUri {
    private final Uri gsUri;
    private final Uri httpBaseUri;
    private final Uri httpUri;

    public StorageReferenceUri(Uri uri) {
        this(uri, (EmulatedServiceSettings) null);
    }

    public StorageReferenceUri(Uri uri, EmulatedServiceSettings emulatedServiceSettings) {
        Uri uri2;
        this.gsUri = uri;
        if (emulatedServiceSettings == null) {
            uri2 = NetworkRequest.PROD_BASE_URL;
        } else {
            uri2 = Uri.parse("http://" + emulatedServiceSettings.getHost() + ":" + emulatedServiceSettings.getPort() + "/v0");
        }
        this.httpBaseUri = uri2;
        Uri.Builder appendEncodedPath = uri2.buildUpon().appendPath("b").appendEncodedPath(uri.getAuthority());
        String normalizeSlashes = Slashes.normalizeSlashes(uri.getPath());
        if (normalizeSlashes.length() > 0 && !"/".equals(normalizeSlashes)) {
            appendEncodedPath = appendEncodedPath.appendPath("o").appendPath(normalizeSlashes);
        }
        this.httpUri = appendEncodedPath.build();
    }

    public Uri getHttpBaseUri() {
        return this.httpBaseUri;
    }

    public Uri getHttpUri() {
        return this.httpUri;
    }

    public Uri getGsUri() {
        return this.gsUri;
    }
}

package com.google.firebase.dynamiclinks;

import android.content.Intent;
import android.net.Uri;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.dynamiclinks.DynamicLink;

@Deprecated
public abstract class FirebaseDynamicLinks {
    @Deprecated
    public abstract DynamicLink.Builder createDynamicLink();

    @Deprecated
    public abstract Task<PendingDynamicLinkData> getDynamicLink(Intent intent);

    @Deprecated
    public abstract Task<PendingDynamicLinkData> getDynamicLink(Uri uri);

    @Deprecated
    public static synchronized FirebaseDynamicLinks getInstance() {
        FirebaseDynamicLinks instance;
        synchronized (FirebaseDynamicLinks.class) {
            instance = getInstance(FirebaseApp.getInstance());
        }
        return instance;
    }

    @Deprecated
    public static synchronized FirebaseDynamicLinks getInstance(FirebaseApp firebaseApp) {
        FirebaseDynamicLinks firebaseDynamicLinks;
        synchronized (FirebaseDynamicLinks.class) {
            firebaseDynamicLinks = (FirebaseDynamicLinks) firebaseApp.get(FirebaseDynamicLinks.class);
        }
        return firebaseDynamicLinks;
    }
}

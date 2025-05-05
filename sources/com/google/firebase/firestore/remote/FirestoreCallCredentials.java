package com.google.firebase.firestore.remote;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.common.net.HttpHeaders;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.firestore.auth.CredentialsProvider;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.internal.api.FirebaseNoSignedInUserException;
import io.grpc.CallCredentials;
import io.grpc.Metadata;
import io.grpc.Status;
import java.util.concurrent.Executor;

final class FirestoreCallCredentials extends CallCredentials {
    private static final Metadata.Key<String> AUTHORIZATION_HEADER = Metadata.Key.of(HttpHeaders.AUTHORIZATION, Metadata.ASCII_STRING_MARSHALLER);
    private static final String LOG_TAG = "FirestoreCallCredentials";
    private static final Metadata.Key<String> X_FIREBASE_APPCHECK = Metadata.Key.of("x-firebase-appcheck", Metadata.ASCII_STRING_MARSHALLER);
    private final CredentialsProvider<String> appCheckProvider;
    private final CredentialsProvider<User> authProvider;

    public void thisUsesUnstableApi() {
    }

    FirestoreCallCredentials(CredentialsProvider<User> credentialsProvider, CredentialsProvider<String> credentialsProvider2) {
        this.authProvider = credentialsProvider;
        this.appCheckProvider = credentialsProvider2;
    }

    public void applyRequestMetadata(CallCredentials.RequestInfo requestInfo, Executor executor, CallCredentials.MetadataApplier metadataApplier) {
        Task<String> token = this.authProvider.getToken();
        Task<String> token2 = this.appCheckProvider.getToken();
        Tasks.whenAll((Task<?>[]) new Task[]{token, token2}).addOnCompleteListener(Executors.DIRECT_EXECUTOR, new FirestoreCallCredentials$$ExternalSyntheticLambda0(token, metadataApplier, token2));
    }

    static /* synthetic */ void lambda$applyRequestMetadata$0(Task task, CallCredentials.MetadataApplier metadataApplier, Task task2, Task task3) {
        Metadata metadata = new Metadata();
        if (task.isSuccessful()) {
            String str = (String) task.getResult();
            Logger.debug(LOG_TAG, "Successfully fetched auth token.", new Object[0]);
            if (str != null) {
                metadata.put(AUTHORIZATION_HEADER, "Bearer " + str);
            }
        } else {
            Exception exception = task.getException();
            if (exception instanceof FirebaseApiNotAvailableException) {
                Logger.debug(LOG_TAG, "Firebase Auth API not available, not using authentication.", new Object[0]);
            } else if (exception instanceof FirebaseNoSignedInUserException) {
                Logger.debug(LOG_TAG, "No user signed in, not using authentication.", new Object[0]);
            } else {
                Logger.warn(LOG_TAG, "Failed to get auth token: %s.", exception);
                metadataApplier.fail(Status.UNAUTHENTICATED.withCause(exception));
                return;
            }
        }
        if (task2.isSuccessful()) {
            String str2 = (String) task2.getResult();
            if (str2 != null && !str2.isEmpty()) {
                Logger.debug(LOG_TAG, "Successfully fetched AppCheck token.", new Object[0]);
                metadata.put(X_FIREBASE_APPCHECK, str2);
            }
        } else {
            Exception exception2 = task2.getException();
            if (exception2 instanceof FirebaseApiNotAvailableException) {
                Logger.debug(LOG_TAG, "Firebase AppCheck API not available.", new Object[0]);
            } else {
                Logger.warn(LOG_TAG, "Failed to get AppCheck token: %s.", exception2);
                metadataApplier.fail(Status.UNAUTHENTICATED.withCause(exception2));
                return;
            }
        }
        metadataApplier.apply(metadata);
    }
}

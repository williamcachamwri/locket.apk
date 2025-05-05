package com.google.firebase.firestore.remote;

import android.content.Context;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.firestore.BuildConfig;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.auth.CredentialsProvider;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.core.DatabaseInfo;
import com.google.firebase.firestore.model.DatabaseId;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Util;
import io.grpc.ClientCall;
import io.grpc.ForwardingClientCall;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;

public class FirestoreChannel {
    private static final Metadata.Key<String> RESOURCE_PREFIX_HEADER = Metadata.Key.of("google-cloud-resource-prefix", Metadata.ASCII_STRING_MARSHALLER);
    private static final Metadata.Key<String> X_GOOG_API_CLIENT_HEADER = Metadata.Key.of("x-goog-api-client", Metadata.ASCII_STRING_MARSHALLER);
    private static final Metadata.Key<String> X_GOOG_REQUEST_PARAMS_HEADER = Metadata.Key.of("x-goog-request-params", Metadata.ASCII_STRING_MARSHALLER);
    private static volatile String clientLanguage = "gl-java/";
    private final CredentialsProvider<String> appCheckProvider;
    /* access modifiers changed from: private */
    public final AsyncQueue asyncQueue;
    private final CredentialsProvider<User> authProvider;
    private final GrpcCallProvider callProvider;
    private final GrpcMetadataProvider metadataProvider;
    private final String resourcePrefixValue;

    public static abstract class StreamingListener<T> {
        public void onClose(Status status) {
        }

        public void onMessage(T t) {
        }
    }

    FirestoreChannel(AsyncQueue asyncQueue2, Context context, CredentialsProvider<User> credentialsProvider, CredentialsProvider<String> credentialsProvider2, DatabaseInfo databaseInfo, GrpcMetadataProvider grpcMetadataProvider) {
        this(asyncQueue2, credentialsProvider, credentialsProvider2, databaseInfo.getDatabaseId(), grpcMetadataProvider, getGrpcCallProvider(asyncQueue2, context, credentialsProvider, credentialsProvider2, databaseInfo));
    }

    FirestoreChannel(AsyncQueue asyncQueue2, CredentialsProvider<User> credentialsProvider, CredentialsProvider<String> credentialsProvider2, DatabaseId databaseId, GrpcMetadataProvider grpcMetadataProvider, GrpcCallProvider grpcCallProvider) {
        this.asyncQueue = asyncQueue2;
        this.metadataProvider = grpcMetadataProvider;
        this.authProvider = credentialsProvider;
        this.appCheckProvider = credentialsProvider2;
        this.callProvider = grpcCallProvider;
        this.resourcePrefixValue = String.format("projects/%s/databases/%s", new Object[]{databaseId.getProjectId(), databaseId.getDatabaseId()});
    }

    private static GrpcCallProvider getGrpcCallProvider(AsyncQueue asyncQueue2, Context context, CredentialsProvider<User> credentialsProvider, CredentialsProvider<String> credentialsProvider2, DatabaseInfo databaseInfo) {
        return new GrpcCallProvider(asyncQueue2, context, databaseInfo, new FirestoreCallCredentials(credentialsProvider, credentialsProvider2));
    }

    public void shutdown() {
        this.callProvider.shutdown();
    }

    /* access modifiers changed from: package-private */
    public <ReqT, RespT> ClientCall<ReqT, RespT> runBidiStreamingRpc(MethodDescriptor<ReqT, RespT> methodDescriptor, IncomingStreamObserver<RespT> incomingStreamObserver) {
        final ClientCall[] clientCallArr = {null};
        final Task<ClientCall<ReqT, RespT>> createClientCall = this.callProvider.createClientCall(methodDescriptor);
        createClientCall.addOnCompleteListener(this.asyncQueue.getExecutor(), (OnCompleteListener<ClientCall<ReqT, RespT>>) new FirestoreChannel$$ExternalSyntheticLambda2(this, clientCallArr, incomingStreamObserver));
        return new ForwardingClientCall<ReqT, RespT>() {
            /* access modifiers changed from: protected */
            public ClientCall<ReqT, RespT> delegate() {
                Assert.hardAssert(clientCallArr[0] != null, "ClientCall used before onOpen() callback", new Object[0]);
                return clientCallArr[0];
            }

            public void halfClose() {
                if (clientCallArr[0] == null) {
                    createClientCall.addOnSuccessListener(FirestoreChannel.this.asyncQueue.getExecutor(), new FirestoreChannel$2$$ExternalSyntheticLambda0());
                } else {
                    super.halfClose();
                }
            }
        };
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$runBidiStreamingRpc$0$com-google-firebase-firestore-remote-FirestoreChannel  reason: not valid java name */
    public /* synthetic */ void m749lambda$runBidiStreamingRpc$0$comgooglefirebasefirestoreremoteFirestoreChannel(final ClientCall[] clientCallArr, final IncomingStreamObserver incomingStreamObserver, Task task) {
        ClientCall clientCall = (ClientCall) task.getResult();
        clientCallArr[0] = clientCall;
        clientCall.start(new ClientCall.Listener<RespT>() {
            public void onReady() {
            }

            public void onHeaders(Metadata metadata) {
                try {
                    incomingStreamObserver.onHeaders(metadata);
                } catch (Throwable th) {
                    FirestoreChannel.this.asyncQueue.panic(th);
                }
            }

            public void onMessage(RespT respt) {
                try {
                    incomingStreamObserver.onNext(respt);
                    clientCallArr[0].request(1);
                } catch (Throwable th) {
                    FirestoreChannel.this.asyncQueue.panic(th);
                }
            }

            public void onClose(Status status, Metadata metadata) {
                try {
                    incomingStreamObserver.onClose(status);
                } catch (Throwable th) {
                    FirestoreChannel.this.asyncQueue.panic(th);
                }
            }
        }, requestHeaders());
        incomingStreamObserver.onOpen();
        clientCallArr[0].request(1);
    }

    /* access modifiers changed from: package-private */
    public <ReqT, RespT> void runStreamingResponseRpc(MethodDescriptor<ReqT, RespT> methodDescriptor, ReqT reqt, StreamingListener<RespT> streamingListener) {
        this.callProvider.createClientCall(methodDescriptor).addOnCompleteListener(this.asyncQueue.getExecutor(), new FirestoreChannel$$ExternalSyntheticLambda0(this, streamingListener, reqt));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$runStreamingResponseRpc$1$com-google-firebase-firestore-remote-FirestoreChannel  reason: not valid java name */
    public /* synthetic */ void m751lambda$runStreamingResponseRpc$1$comgooglefirebasefirestoreremoteFirestoreChannel(final StreamingListener streamingListener, Object obj, Task task) {
        final ClientCall clientCall = (ClientCall) task.getResult();
        clientCall.start(new ClientCall.Listener<RespT>() {
            public void onMessage(RespT respt) {
                streamingListener.onMessage(respt);
                clientCall.request(1);
            }

            public void onClose(Status status, Metadata metadata) {
                streamingListener.onClose(status);
            }
        }, requestHeaders());
        clientCall.request(1);
        clientCall.sendMessage(obj);
        clientCall.halfClose();
    }

    /* access modifiers changed from: package-private */
    public <ReqT, RespT> Task<RespT> runRpc(MethodDescriptor<ReqT, RespT> methodDescriptor, ReqT reqt) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.callProvider.createClientCall(methodDescriptor).addOnCompleteListener(this.asyncQueue.getExecutor(), new FirestoreChannel$$ExternalSyntheticLambda1(this, taskCompletionSource, reqt));
        return taskCompletionSource.getTask();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$runRpc$2$com-google-firebase-firestore-remote-FirestoreChannel  reason: not valid java name */
    public /* synthetic */ void m750lambda$runRpc$2$comgooglefirebasefirestoreremoteFirestoreChannel(final TaskCompletionSource taskCompletionSource, Object obj, Task task) {
        ClientCall clientCall = (ClientCall) task.getResult();
        clientCall.start(new ClientCall.Listener<RespT>() {
            public void onMessage(RespT respt) {
                taskCompletionSource.setResult(respt);
            }

            public void onClose(Status status, Metadata metadata) {
                if (!status.isOk()) {
                    taskCompletionSource.setException(FirestoreChannel.this.exceptionFromStatus(status));
                } else if (!taskCompletionSource.getTask().isComplete()) {
                    taskCompletionSource.setException(new FirebaseFirestoreException("Received onClose with status OK, but no message.", FirebaseFirestoreException.Code.INTERNAL));
                }
            }
        }, requestHeaders());
        clientCall.request(2);
        clientCall.sendMessage(obj);
        clientCall.halfClose();
    }

    /* access modifiers changed from: private */
    public FirebaseFirestoreException exceptionFromStatus(Status status) {
        if (Datastore.isMissingSslCiphers(status)) {
            return new FirebaseFirestoreException("The Cloud Firestore client failed to establish a secure connection. This is likely a problem with your app, rather than with Cloud Firestore itself. See https://bit.ly/2XFpdma for instructions on how to enable TLS on Android 4.x devices.", FirebaseFirestoreException.Code.fromValue(status.getCode().value()), status.getCause());
        }
        return Util.exceptionFromStatus(status);
    }

    public void invalidateToken() {
        this.authProvider.invalidateToken();
        this.appCheckProvider.invalidateToken();
    }

    public static void setClientLanguage(String str) {
        clientLanguage = str;
    }

    private String getGoogApiClientValue() {
        return String.format("%s fire/%s grpc/", new Object[]{clientLanguage, BuildConfig.VERSION_NAME});
    }

    private Metadata requestHeaders() {
        Metadata metadata = new Metadata();
        metadata.put(X_GOOG_API_CLIENT_HEADER, getGoogApiClientValue());
        metadata.put(RESOURCE_PREFIX_HEADER, this.resourcePrefixValue);
        metadata.put(X_GOOG_REQUEST_PARAMS_HEADER, this.resourcePrefixValue);
        GrpcMetadataProvider grpcMetadataProvider = this.metadataProvider;
        if (grpcMetadataProvider != null) {
            grpcMetadataProvider.updateMetadata(metadata);
        }
        return metadata;
    }
}

package com.google.firebase.firestore.remote;

import android.content.Context;
import androidx.media3.common.C;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.core.DatabaseInfo;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.firestore.util.Supplier;
import com.google.firestore.v1.FirestoreGrpc;
import io.grpc.CallCredentials;
import io.grpc.CallOptions;
import io.grpc.ClientCall;
import io.grpc.ConnectivityState;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.MethodDescriptor;
import io.grpc.android.AndroidChannelBuilder;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class GrpcCallProvider {
    private static final int CONNECTIVITY_ATTEMPT_TIMEOUT_MS = 15000;
    private static final String LOG_TAG = "GrpcCallProvider";
    private static Supplier<ManagedChannelBuilder<?>> overrideChannelBuilderSupplier;
    private final AsyncQueue asyncQueue;
    private CallOptions callOptions;
    private Task<ManagedChannel> channelTask;
    private AsyncQueue.DelayedTask connectivityAttemptTimer;
    private final Context context;
    private final DatabaseInfo databaseInfo;
    private final CallCredentials firestoreHeaders;

    GrpcCallProvider(AsyncQueue asyncQueue2, Context context2, DatabaseInfo databaseInfo2, CallCredentials callCredentials) {
        this.asyncQueue = asyncQueue2;
        this.context = context2;
        this.databaseInfo = databaseInfo2;
        this.firestoreHeaders = callCredentials;
        initChannelTask();
    }

    private ManagedChannel initChannel(Context context2, DatabaseInfo databaseInfo2) {
        ManagedChannelBuilder<?> managedChannelBuilder;
        try {
            ProviderInstaller.installIfNeeded(context2);
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IllegalStateException e) {
            Logger.warn(LOG_TAG, "Failed to update ssl context: %s", e);
        }
        Supplier<ManagedChannelBuilder<?>> supplier = overrideChannelBuilderSupplier;
        if (supplier != null) {
            managedChannelBuilder = supplier.get();
        } else {
            ManagedChannelBuilder<?> forTarget = ManagedChannelBuilder.forTarget(databaseInfo2.getHost());
            if (!databaseInfo2.isSslEnabled()) {
                forTarget.usePlaintext();
            }
            managedChannelBuilder = forTarget;
        }
        managedChannelBuilder.keepAliveTime(30, TimeUnit.SECONDS);
        return AndroidChannelBuilder.usingBuilder(managedChannelBuilder).context(context2).build();
    }

    public <ReqT, RespT> Task<ClientCall<ReqT, RespT>> createClientCall(MethodDescriptor<ReqT, RespT> methodDescriptor) {
        return this.channelTask.continueWithTask(this.asyncQueue.getExecutor(), new GrpcCallProvider$$ExternalSyntheticLambda6(this, methodDescriptor));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$createClientCall$0$com-google-firebase-firestore-remote-GrpcCallProvider  reason: not valid java name */
    public /* synthetic */ Task m753lambda$createClientCall$0$comgooglefirebasefirestoreremoteGrpcCallProvider(MethodDescriptor methodDescriptor, Task task) throws Exception {
        return Tasks.forResult(((ManagedChannel) task.getResult()).newCall(methodDescriptor, this.callOptions));
    }

    public void shutdown() {
        try {
            ManagedChannel managedChannel = (ManagedChannel) Tasks.await(this.channelTask);
            managedChannel.shutdown();
            try {
                if (!managedChannel.awaitTermination(1, TimeUnit.SECONDS)) {
                    Class<FirestoreChannel> cls = FirestoreChannel.class;
                    Logger.debug("FirestoreChannel", "Unable to gracefully shutdown the gRPC ManagedChannel. Will attempt an immediate shutdown.", new Object[0]);
                    managedChannel.shutdownNow();
                    if (!managedChannel.awaitTermination(60, TimeUnit.SECONDS)) {
                        Class<FirestoreChannel> cls2 = FirestoreChannel.class;
                        Logger.warn("FirestoreChannel", "Unable to forcefully shutdown the gRPC ManagedChannel.", new Object[0]);
                    }
                }
            } catch (InterruptedException unused) {
                managedChannel.shutdownNow();
                Class<FirestoreChannel> cls3 = FirestoreChannel.class;
                Logger.warn("FirestoreChannel", "Interrupted while shutting down the gRPC Managed Channel", new Object[0]);
                Thread.currentThread().interrupt();
            }
        } catch (ExecutionException e) {
            Class<FirestoreChannel> cls4 = FirestoreChannel.class;
            Logger.warn("FirestoreChannel", "Channel is not initialized, shutdown will just do nothing. Channel initializing run into exception: %s", e);
        } catch (InterruptedException unused2) {
            Class<FirestoreChannel> cls5 = FirestoreChannel.class;
            Logger.warn("FirestoreChannel", "Interrupted while retrieving the gRPC Managed Channel", new Object[0]);
            Thread.currentThread().interrupt();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: onConnectivityStateChange */
    public void m757lambda$onConnectivityStateChange$2$comgooglefirebasefirestoreremoteGrpcCallProvider(ManagedChannel managedChannel) {
        ConnectivityState state = managedChannel.getState(true);
        Logger.debug(LOG_TAG, "Current gRPC connectivity state: " + state, new Object[0]);
        clearConnectivityAttemptTimer();
        if (state == ConnectivityState.CONNECTING) {
            Logger.debug(LOG_TAG, "Setting the connectivityAttemptTimer", new Object[0]);
            this.connectivityAttemptTimer = this.asyncQueue.enqueueAfterDelay(AsyncQueue.TimerId.CONNECTIVITY_ATTEMPT_TIMER, C.DEFAULT_SEEK_FORWARD_INCREMENT_MS, new GrpcCallProvider$$ExternalSyntheticLambda3(this, managedChannel));
        }
        managedChannel.notifyWhenStateChanged(state, new GrpcCallProvider$$ExternalSyntheticLambda4(this, managedChannel));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onConnectivityStateChange$1$com-google-firebase-firestore-remote-GrpcCallProvider  reason: not valid java name */
    public /* synthetic */ void m756lambda$onConnectivityStateChange$1$comgooglefirebasefirestoreremoteGrpcCallProvider(ManagedChannel managedChannel) {
        Logger.debug(LOG_TAG, "connectivityAttemptTimer elapsed. Resetting the channel.", new Object[0]);
        clearConnectivityAttemptTimer();
        resetChannel(managedChannel);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onConnectivityStateChange$3$com-google-firebase-firestore-remote-GrpcCallProvider  reason: not valid java name */
    public /* synthetic */ void m758lambda$onConnectivityStateChange$3$comgooglefirebasefirestoreremoteGrpcCallProvider(ManagedChannel managedChannel) {
        this.asyncQueue.enqueueAndForget(new GrpcCallProvider$$ExternalSyntheticLambda5(this, managedChannel));
    }

    private void resetChannel(ManagedChannel managedChannel) {
        this.asyncQueue.enqueueAndForget(new GrpcCallProvider$$ExternalSyntheticLambda0(this, managedChannel));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$resetChannel$4$com-google-firebase-firestore-remote-GrpcCallProvider  reason: not valid java name */
    public /* synthetic */ void m759lambda$resetChannel$4$comgooglefirebasefirestoreremoteGrpcCallProvider(ManagedChannel managedChannel) {
        managedChannel.shutdownNow();
        initChannelTask();
    }

    private void initChannelTask() {
        this.channelTask = Tasks.call(Executors.BACKGROUND_EXECUTOR, new GrpcCallProvider$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initChannelTask$6$com-google-firebase-firestore-remote-GrpcCallProvider  reason: not valid java name */
    public /* synthetic */ ManagedChannel m755lambda$initChannelTask$6$comgooglefirebasefirestoreremoteGrpcCallProvider() throws Exception {
        ManagedChannel initChannel = initChannel(this.context, this.databaseInfo);
        this.asyncQueue.enqueueAndForget(new GrpcCallProvider$$ExternalSyntheticLambda2(this, initChannel));
        this.callOptions = ((FirestoreGrpc.FirestoreStub) ((FirestoreGrpc.FirestoreStub) FirestoreGrpc.newStub(initChannel).withCallCredentials(this.firestoreHeaders)).withExecutor(this.asyncQueue.getExecutor())).getCallOptions();
        Logger.debug(LOG_TAG, "Channel successfully reset.", new Object[0]);
        return initChannel;
    }

    private void clearConnectivityAttemptTimer() {
        if (this.connectivityAttemptTimer != null) {
            Logger.debug(LOG_TAG, "Clearing the connectivityAttemptTimer", new Object[0]);
            this.connectivityAttemptTimer.cancel();
            this.connectivityAttemptTimer = null;
        }
    }
}

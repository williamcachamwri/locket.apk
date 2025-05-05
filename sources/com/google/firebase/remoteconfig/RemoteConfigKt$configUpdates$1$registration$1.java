package com.google.firebase.remoteconfig;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.channels.ChannelsKt;
import kotlinx.coroutines.channels.ProducerScope;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"com/google/firebase/remoteconfig/RemoteConfigKt$configUpdates$1$registration$1", "Lcom/google/firebase/remoteconfig/ConfigUpdateListener;", "onError", "", "error", "Lcom/google/firebase/remoteconfig/FirebaseRemoteConfigException;", "onUpdate", "configUpdate", "Lcom/google/firebase/remoteconfig/ConfigUpdate;", "com.google.firebase-firebase-config"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RemoteConfig.kt */
public final class RemoteConfigKt$configUpdates$1$registration$1 implements ConfigUpdateListener {
    final /* synthetic */ ProducerScope<ConfigUpdate> $$this$callbackFlow;
    final /* synthetic */ FirebaseRemoteConfig $this_configUpdates;

    RemoteConfigKt$configUpdates$1$registration$1(FirebaseRemoteConfig firebaseRemoteConfig, ProducerScope<? super ConfigUpdate> producerScope) {
        this.$this_configUpdates = firebaseRemoteConfig;
        this.$$this$callbackFlow = producerScope;
    }

    /* access modifiers changed from: private */
    public static final void onUpdate$lambda$0(ProducerScope producerScope, ConfigUpdate configUpdate) {
        Intrinsics.checkNotNullParameter(producerScope, "$$this$callbackFlow");
        Intrinsics.checkNotNullParameter(configUpdate, "$configUpdate");
        ChannelsKt.trySendBlocking(producerScope, configUpdate);
    }

    public void onUpdate(ConfigUpdate configUpdate) {
        Intrinsics.checkNotNullParameter(configUpdate, "configUpdate");
        this.$this_configUpdates.schedule(new RemoteConfigKt$configUpdates$1$registration$1$$ExternalSyntheticLambda0(this.$$this$callbackFlow, configUpdate));
    }

    public void onError(FirebaseRemoteConfigException firebaseRemoteConfigException) {
        Intrinsics.checkNotNullParameter(firebaseRemoteConfigException, "error");
        CoroutineScopeKt.cancel(this.$$this$callbackFlow, "Error listening for config updates.", firebaseRemoteConfigException);
    }
}

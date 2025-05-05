package com.google.mlkit.common.sdkinternal;

import com.google.firebase.inject.Provider;
import java.util.concurrent.Executor;

/* compiled from: com.google.mlkit:common@@18.11.0 */
public class ExecutorSelector {
    private final Provider zza;

    public ExecutorSelector(Provider provider) {
        this.zza = provider;
    }

    public Executor getExecutorToUse(Executor executor) {
        return executor != null ? executor : (Executor) this.zza.get();
    }
}

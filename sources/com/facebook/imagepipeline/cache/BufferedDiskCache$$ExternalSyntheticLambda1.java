package com.facebook.imagepipeline.cache;

import java.util.concurrent.Callable;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BufferedDiskCache$$ExternalSyntheticLambda1 implements Callable {
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ BufferedDiskCache f$1;

    public /* synthetic */ BufferedDiskCache$$ExternalSyntheticLambda1(Object obj, BufferedDiskCache bufferedDiskCache) {
        this.f$0 = obj;
        this.f$1 = bufferedDiskCache;
    }

    public final Object call() {
        return BufferedDiskCache.clearAll$lambda$6(this.f$0, this.f$1);
    }
}

package com.facebook.imagepipeline.cache;

import com.facebook.common.internal.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AnimatedCache$$ExternalSyntheticLambda3 implements Supplier {
    public final /* synthetic */ AnimatedCache f$0;

    public /* synthetic */ AnimatedCache$$ExternalSyntheticLambda3(AnimatedCache animatedCache) {
        this.f$0 = animatedCache;
    }

    public final Object get() {
        return AnimatedCache.lruCache$lambda$2(this.f$0);
    }
}

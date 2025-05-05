package com.facebook.imagepipeline.cache;

import com.facebook.common.memory.MemoryTrimType;
import com.facebook.imagepipeline.cache.MemoryCache;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AnimatedCache$$ExternalSyntheticLambda2 implements MemoryCache.CacheTrimStrategy {
    public final double getTrimRatio(MemoryTrimType memoryTrimType) {
        return AnimatedCache.lruCache$lambda$1(memoryTrimType);
    }
}

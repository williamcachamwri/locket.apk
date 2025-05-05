package com.facebook.imagepipeline.cache;

import com.facebook.common.memory.MemoryTrimType;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\r\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\tJ\u000e\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\tJ\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\tJ\u001e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\nR\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/facebook/imagepipeline/cache/AnimatedCache;", "", "memoryMB", "", "(I)V", "evictionRatio", "", "lruCache", "Lcom/facebook/imagepipeline/cache/LruCountingMemoryCache;", "", "Lcom/facebook/imagepipeline/cache/AnimationFrames;", "maxCacheEntrySize", "sizeBytes", "findAnimation", "Lcom/facebook/common/references/CloseableReference;", "key", "getSize", "removeAnimation", "", "saveAnimation", "animationFrames", "Companion", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: AnimatedCache.kt */
public final class AnimatedCache {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int EVICTION_QUEUE = 50;
    /* access modifiers changed from: private */
    public static AnimatedCache instance;
    private final float evictionRatio;
    private final LruCountingMemoryCache<String, AnimationFrames> lruCache;
    private final int maxCacheEntrySize;
    private final int sizeBytes;

    public /* synthetic */ AnimatedCache(int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(i);
    }

    @JvmStatic
    public static final AnimatedCache getInstance(int i) {
        return Companion.getInstance(i);
    }

    private AnimatedCache(int i) {
        int i2 = 1048576 * i;
        this.sizeBytes = i2;
        this.evictionRatio = i < 90 ? 0.0f : 0.3f;
        this.maxCacheEntrySize = (int) (((double) i2) * 0.1d);
        this.lruCache = new LruCountingMemoryCache(new AnimatedCache$$ExternalSyntheticLambda1(), new AnimatedCache$$ExternalSyntheticLambda2(), new AnimatedCache$$ExternalSyntheticLambda3(this), (CountingMemoryCache.EntryStateObserver) null, false, false);
    }

    /* access modifiers changed from: private */
    public static final int lruCache$lambda$0(AnimationFrames animationFrames) {
        return animationFrames.getSizeBytes();
    }

    /* access modifiers changed from: private */
    public static final double lruCache$lambda$1(MemoryTrimType memoryTrimType) {
        Intrinsics.checkNotNullParameter(memoryTrimType, "it");
        return memoryTrimType.getSuggestedTrimRatio();
    }

    /* access modifiers changed from: private */
    public static final MemoryCacheParams lruCache$lambda$2(AnimatedCache animatedCache) {
        Intrinsics.checkNotNullParameter(animatedCache, "this$0");
        int i = animatedCache.sizeBytes;
        return new MemoryCacheParams(i, Integer.MAX_VALUE, (int) (((float) i) * animatedCache.evictionRatio), 50, animatedCache.maxCacheEntrySize, TimeUnit.SECONDS.toMillis(5));
    }

    public final int getSize(String str) {
        Intrinsics.checkNotNullParameter(str, "key");
        return this.lruCache.getSizeInBytes();
    }

    public final CloseableReference<AnimationFrames> findAnimation(String str) {
        Intrinsics.checkNotNullParameter(str, "key");
        return this.lruCache.get(str);
    }

    public final CloseableReference<AnimationFrames> saveAnimation(String str, AnimationFrames animationFrames) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(animationFrames, "animationFrames");
        return this.lruCache.cache(str, CloseableReference.of(animationFrames));
    }

    /* access modifiers changed from: private */
    public static final boolean removeAnimation$lambda$3(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "$key");
        Intrinsics.checkNotNullParameter(str2, "cacheKey");
        return Intrinsics.areEqual((Object) str, (Object) str2);
    }

    public final void removeAnimation(String str) {
        Intrinsics.checkNotNullParameter(str, "key");
        this.lruCache.removeAll(new AnimatedCache$$ExternalSyntheticLambda0(str));
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/facebook/imagepipeline/cache/AnimatedCache$Companion;", "", "()V", "EVICTION_QUEUE", "", "instance", "Lcom/facebook/imagepipeline/cache/AnimatedCache;", "getInstance", "memoryMB", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: AnimatedCache.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final AnimatedCache getInstance(int i) {
            AnimatedCache access$getInstance$cp = AnimatedCache.instance;
            if (access$getInstance$cp != null) {
                return access$getInstance$cp;
            }
            AnimatedCache animatedCache = new AnimatedCache(i, (DefaultConstructorMarker) null);
            Companion companion = AnimatedCache.Companion;
            AnimatedCache.instance = animatedCache;
            return animatedCache;
        }
    }
}

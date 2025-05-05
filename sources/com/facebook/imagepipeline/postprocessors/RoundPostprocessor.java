package com.facebook.imagepipeline.postprocessors;

import android.graphics.Bitmap;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.imagepipeline.filter.XferRoundFilter;
import com.facebook.imagepipeline.request.BasePostprocessor;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\u0006H\u0016J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/facebook/imagepipeline/postprocessors/RoundPostprocessor;", "Lcom/facebook/imagepipeline/request/BasePostprocessor;", "enableAntiAliasing", "", "(Z)V", "cacheKey", "Lcom/facebook/cache/common/CacheKey;", "getPostprocessorCacheKey", "process", "", "destBitmap", "Landroid/graphics/Bitmap;", "sourceBitmap", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RoundPostprocessor.kt */
public final class RoundPostprocessor extends BasePostprocessor {
    private final CacheKey cacheKey;
    private final boolean enableAntiAliasing;

    public RoundPostprocessor() {
        this(false, 1, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ RoundPostprocessor(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z);
    }

    public RoundPostprocessor(boolean z) {
        this.enableAntiAliasing = z;
        this.cacheKey = new SimpleCacheKey("XferRoundFilter");
    }

    public CacheKey getPostprocessorCacheKey() {
        return this.cacheKey;
    }

    public void process(Bitmap bitmap, Bitmap bitmap2) {
        Intrinsics.checkNotNullParameter(bitmap, "destBitmap");
        Intrinsics.checkNotNullParameter(bitmap2, "sourceBitmap");
        XferRoundFilter.xferRoundBitmap(bitmap, bitmap2, this.enableAntiAliasing);
    }
}

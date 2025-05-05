package com.facebook.imagepipeline.cache;

import android.net.Uri;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.time.RealtimeSinceBootClock;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0001\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\fJ\t\u0010$\u001a\u00020\u0003HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010&\u001a\u00020\u0007HÆ\u0003J\t\u0010'\u001a\u00020\tHÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0016JK\u0010.\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010/\u001a\u00020+2\b\u00100\u001a\u0004\u0018\u00010\u000eH\u0002J\b\u00101\u001a\u00020\u0003H\u0016J\b\u00102\u001a\u00020\u0014H\u0016J\b\u00103\u001a\u00020+H\u0016J\t\u00104\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\n\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001e¨\u00065"}, d2 = {"Lcom/facebook/imagepipeline/cache/BitmapMemoryCacheKey;", "Lcom/facebook/cache/common/CacheKey;", "sourceString", "", "resizeOptions", "Lcom/facebook/imagepipeline/common/ResizeOptions;", "rotationOptions", "Lcom/facebook/imagepipeline/common/RotationOptions;", "imageDecodeOptions", "Lcom/facebook/imagepipeline/common/ImageDecodeOptions;", "postprocessorCacheKey", "postprocessorName", "(Ljava/lang/String;Lcom/facebook/imagepipeline/common/ResizeOptions;Lcom/facebook/imagepipeline/common/RotationOptions;Lcom/facebook/imagepipeline/common/ImageDecodeOptions;Lcom/facebook/cache/common/CacheKey;Ljava/lang/String;)V", "callerContext", "", "getCallerContext", "()Ljava/lang/Object;", "setCallerContext", "(Ljava/lang/Object;)V", "hash", "", "getImageDecodeOptions", "()Lcom/facebook/imagepipeline/common/ImageDecodeOptions;", "inBitmapCacheSince", "", "getInBitmapCacheSince", "()J", "getPostprocessorCacheKey", "()Lcom/facebook/cache/common/CacheKey;", "getPostprocessorName", "()Ljava/lang/String;", "getResizeOptions", "()Lcom/facebook/imagepipeline/common/ResizeOptions;", "getRotationOptions", "()Lcom/facebook/imagepipeline/common/RotationOptions;", "getSourceString", "component1", "component2", "component3", "component4", "component5", "component6", "containsUri", "", "uri", "Landroid/net/Uri;", "copy", "equals", "other", "getUriString", "hashCode", "isResourceIdForDebugging", "toString", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: BitmapMemoryCacheKey.kt */
public final class BitmapMemoryCacheKey implements CacheKey {
    private Object callerContext;
    private final int hash;
    private final ImageDecodeOptions imageDecodeOptions;
    private final long inBitmapCacheSince;
    private final CacheKey postprocessorCacheKey;
    private final String postprocessorName;
    private final ResizeOptions resizeOptions;
    private final RotationOptions rotationOptions;
    private final String sourceString;

    public static /* synthetic */ BitmapMemoryCacheKey copy$default(BitmapMemoryCacheKey bitmapMemoryCacheKey, String str, ResizeOptions resizeOptions2, RotationOptions rotationOptions2, ImageDecodeOptions imageDecodeOptions2, CacheKey cacheKey, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = bitmapMemoryCacheKey.sourceString;
        }
        if ((i & 2) != 0) {
            resizeOptions2 = bitmapMemoryCacheKey.resizeOptions;
        }
        ResizeOptions resizeOptions3 = resizeOptions2;
        if ((i & 4) != 0) {
            rotationOptions2 = bitmapMemoryCacheKey.rotationOptions;
        }
        RotationOptions rotationOptions3 = rotationOptions2;
        if ((i & 8) != 0) {
            imageDecodeOptions2 = bitmapMemoryCacheKey.imageDecodeOptions;
        }
        ImageDecodeOptions imageDecodeOptions3 = imageDecodeOptions2;
        if ((i & 16) != 0) {
            cacheKey = bitmapMemoryCacheKey.postprocessorCacheKey;
        }
        CacheKey cacheKey2 = cacheKey;
        if ((i & 32) != 0) {
            str2 = bitmapMemoryCacheKey.postprocessorName;
        }
        return bitmapMemoryCacheKey.copy(str, resizeOptions3, rotationOptions3, imageDecodeOptions3, cacheKey2, str2);
    }

    public final String component1() {
        return this.sourceString;
    }

    public final ResizeOptions component2() {
        return this.resizeOptions;
    }

    public final RotationOptions component3() {
        return this.rotationOptions;
    }

    public final ImageDecodeOptions component4() {
        return this.imageDecodeOptions;
    }

    public final CacheKey component5() {
        return this.postprocessorCacheKey;
    }

    public final String component6() {
        return this.postprocessorName;
    }

    public final BitmapMemoryCacheKey copy(String str, ResizeOptions resizeOptions2, RotationOptions rotationOptions2, ImageDecodeOptions imageDecodeOptions2, CacheKey cacheKey, String str2) {
        Intrinsics.checkNotNullParameter(str, "sourceString");
        Intrinsics.checkNotNullParameter(rotationOptions2, "rotationOptions");
        Intrinsics.checkNotNullParameter(imageDecodeOptions2, "imageDecodeOptions");
        return new BitmapMemoryCacheKey(str, resizeOptions2, rotationOptions2, imageDecodeOptions2, cacheKey, str2);
    }

    public boolean isResourceIdForDebugging() {
        return false;
    }

    public String toString() {
        return "BitmapMemoryCacheKey(sourceString=" + this.sourceString + ", resizeOptions=" + this.resizeOptions + ", rotationOptions=" + this.rotationOptions + ", imageDecodeOptions=" + this.imageDecodeOptions + ", postprocessorCacheKey=" + this.postprocessorCacheKey + ", postprocessorName=" + this.postprocessorName + ')';
    }

    public BitmapMemoryCacheKey(String str, ResizeOptions resizeOptions2, RotationOptions rotationOptions2, ImageDecodeOptions imageDecodeOptions2, CacheKey cacheKey, String str2) {
        Intrinsics.checkNotNullParameter(str, "sourceString");
        Intrinsics.checkNotNullParameter(rotationOptions2, "rotationOptions");
        Intrinsics.checkNotNullParameter(imageDecodeOptions2, "imageDecodeOptions");
        this.sourceString = str;
        this.resizeOptions = resizeOptions2;
        this.rotationOptions = rotationOptions2;
        this.imageDecodeOptions = imageDecodeOptions2;
        this.postprocessorCacheKey = cacheKey;
        this.postprocessorName = str2;
        BitmapMemoryCacheKey bitmapMemoryCacheKey = this;
        int hashCode = this.sourceString.hashCode() * 31;
        ResizeOptions resizeOptions3 = this.resizeOptions;
        int i = 0;
        int hashCode2 = (((((hashCode + (resizeOptions3 != null ? resizeOptions3.hashCode() : 0)) * 31) + this.rotationOptions.hashCode()) * 31) + this.imageDecodeOptions.hashCode()) * 31;
        CacheKey cacheKey2 = this.postprocessorCacheKey;
        int hashCode3 = (hashCode2 + (cacheKey2 != null ? cacheKey2.hashCode() : 0)) * 31;
        String str3 = this.postprocessorName;
        this.hash = hashCode3 + (str3 != null ? str3.hashCode() : i);
        this.inBitmapCacheSince = RealtimeSinceBootClock.get().now();
    }

    public final String getSourceString() {
        return this.sourceString;
    }

    public final ResizeOptions getResizeOptions() {
        return this.resizeOptions;
    }

    public final RotationOptions getRotationOptions() {
        return this.rotationOptions;
    }

    public final ImageDecodeOptions getImageDecodeOptions() {
        return this.imageDecodeOptions;
    }

    public final CacheKey getPostprocessorCacheKey() {
        return this.postprocessorCacheKey;
    }

    public final String getPostprocessorName() {
        return this.postprocessorName;
    }

    public final Object getCallerContext() {
        return this.callerContext;
    }

    public final void setCallerContext(Object obj) {
        this.callerContext = obj;
    }

    public final long getInBitmapCacheSince() {
        return this.inBitmapCacheSince;
    }

    public int hashCode() {
        return this.hash;
    }

    public boolean containsUri(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        String uri2 = uri.toString();
        Intrinsics.checkNotNullExpressionValue(uri2, "uri.toString()");
        return StringsKt.contains$default((CharSequence) getUriString(), (CharSequence) uri2, false, 2, (Object) null);
    }

    public String getUriString() {
        return this.sourceString;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual((Object) getClass(), (Object) obj != null ? obj.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.facebook.imagepipeline.cache.BitmapMemoryCacheKey");
        BitmapMemoryCacheKey bitmapMemoryCacheKey = (BitmapMemoryCacheKey) obj;
        if (!Intrinsics.areEqual((Object) this.sourceString, (Object) bitmapMemoryCacheKey.sourceString) || !Intrinsics.areEqual((Object) this.resizeOptions, (Object) bitmapMemoryCacheKey.resizeOptions) || !Intrinsics.areEqual((Object) this.rotationOptions, (Object) bitmapMemoryCacheKey.rotationOptions) || !Intrinsics.areEqual((Object) this.imageDecodeOptions, (Object) bitmapMemoryCacheKey.imageDecodeOptions) || !Intrinsics.areEqual((Object) this.postprocessorCacheKey, (Object) bitmapMemoryCacheKey.postprocessorCacheKey) || !Intrinsics.areEqual((Object) this.postprocessorName, (Object) bitmapMemoryCacheKey.postprocessorName)) {
            return false;
        }
        return true;
    }
}

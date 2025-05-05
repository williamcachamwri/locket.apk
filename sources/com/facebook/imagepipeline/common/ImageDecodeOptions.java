package com.facebook.imagepipeline.common;

import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import com.facebook.common.internal.Objects;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.transformation.BitmapTransformation;
import javax.annotation.Nullable;

public class ImageDecodeOptions {
    private static final ImageDecodeOptions DEFAULTS = newBuilder().build();
    public final Bitmap.Config animatedBitmapConfig;
    public final Bitmap.Config bitmapConfig;
    @Nullable
    public final BitmapTransformation bitmapTransformation;
    @Nullable
    public final ColorSpace colorSpace;
    @Nullable
    public final ImageDecoder customImageDecoder;
    public final boolean decodeAllFrames;
    public final boolean decodePreviewFrame;
    private final boolean excludeBitmapConfigFromComparison;
    public final boolean forceStaticImage;
    public final int maxDimensionPx;
    public final int minDecodeIntervalMs;
    public final boolean useLastFrameForPreview;

    public ImageDecodeOptions(ImageDecodeOptionsBuilder imageDecodeOptionsBuilder) {
        this.minDecodeIntervalMs = imageDecodeOptionsBuilder.getMinDecodeIntervalMs();
        this.maxDimensionPx = imageDecodeOptionsBuilder.getMaxDimensionPx();
        this.decodePreviewFrame = imageDecodeOptionsBuilder.getDecodePreviewFrame();
        this.useLastFrameForPreview = imageDecodeOptionsBuilder.getUseLastFrameForPreview();
        this.decodeAllFrames = imageDecodeOptionsBuilder.getDecodeAllFrames();
        this.forceStaticImage = imageDecodeOptionsBuilder.getForceStaticImage();
        this.bitmapConfig = imageDecodeOptionsBuilder.getBitmapConfig();
        this.animatedBitmapConfig = imageDecodeOptionsBuilder.getAnimatedBitmapConfig();
        this.customImageDecoder = imageDecodeOptionsBuilder.getCustomImageDecoder();
        this.bitmapTransformation = imageDecodeOptionsBuilder.getBitmapTransformation();
        this.colorSpace = imageDecodeOptionsBuilder.getColorSpace();
        this.excludeBitmapConfigFromComparison = imageDecodeOptionsBuilder.getExcludeBitmapConfigFromComparison();
    }

    public static ImageDecodeOptions defaults() {
        return DEFAULTS;
    }

    public static ImageDecodeOptionsBuilder newBuilder() {
        return new ImageDecodeOptionsBuilder();
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ImageDecodeOptions imageDecodeOptions = (ImageDecodeOptions) obj;
        if (this.minDecodeIntervalMs != imageDecodeOptions.minDecodeIntervalMs || this.maxDimensionPx != imageDecodeOptions.maxDimensionPx || this.decodePreviewFrame != imageDecodeOptions.decodePreviewFrame || this.useLastFrameForPreview != imageDecodeOptions.useLastFrameForPreview || this.decodeAllFrames != imageDecodeOptions.decodeAllFrames || this.forceStaticImage != imageDecodeOptions.forceStaticImage) {
            return false;
        }
        boolean z = this.excludeBitmapConfigFromComparison;
        if (z || this.bitmapConfig == imageDecodeOptions.bitmapConfig) {
            return (z || this.animatedBitmapConfig == imageDecodeOptions.animatedBitmapConfig) && this.customImageDecoder == imageDecodeOptions.customImageDecoder && this.bitmapTransformation == imageDecodeOptions.bitmapTransformation && this.colorSpace == imageDecodeOptions.colorSpace;
        }
        return false;
    }

    public int hashCode() {
        int i = (((((((((this.minDecodeIntervalMs * 31) + this.maxDimensionPx) * 31) + (this.decodePreviewFrame ? 1 : 0)) * 31) + (this.useLastFrameForPreview ? 1 : 0)) * 31) + (this.decodeAllFrames ? 1 : 0)) * 31) + (this.forceStaticImage ? 1 : 0);
        if (!this.excludeBitmapConfigFromComparison) {
            i = (i * 31) + this.bitmapConfig.ordinal();
        }
        int i2 = 0;
        if (!this.excludeBitmapConfigFromComparison) {
            int i3 = i * 31;
            Bitmap.Config config = this.animatedBitmapConfig;
            i = i3 + (config != null ? config.ordinal() : 0);
        }
        int i4 = i * 31;
        ImageDecoder imageDecoder = this.customImageDecoder;
        int hashCode = (i4 + (imageDecoder != null ? imageDecoder.hashCode() : 0)) * 31;
        BitmapTransformation bitmapTransformation2 = this.bitmapTransformation;
        int hashCode2 = (hashCode + (bitmapTransformation2 != null ? bitmapTransformation2.hashCode() : 0)) * 31;
        ColorSpace colorSpace2 = this.colorSpace;
        if (colorSpace2 != null) {
            i2 = colorSpace2.hashCode();
        }
        return hashCode2 + i2;
    }

    public String toString() {
        return "ImageDecodeOptions{" + toStringHelper().toString() + "}";
    }

    /* access modifiers changed from: protected */
    public Objects.ToStringHelper toStringHelper() {
        return Objects.toStringHelper((Object) this).add("minDecodeIntervalMs", this.minDecodeIntervalMs).add("maxDimensionPx", this.maxDimensionPx).add("decodePreviewFrame", this.decodePreviewFrame).add("useLastFrameForPreview", this.useLastFrameForPreview).add("decodeAllFrames", this.decodeAllFrames).add("forceStaticImage", this.forceStaticImage).add("bitmapConfigName", (Object) this.bitmapConfig.name()).add("animatedBitmapConfigName", (Object) this.animatedBitmapConfig.name()).add("customImageDecoder", (Object) this.customImageDecoder).add("bitmapTransformation", (Object) this.bitmapTransformation).add("colorSpace", (Object) this.colorSpace);
    }
}

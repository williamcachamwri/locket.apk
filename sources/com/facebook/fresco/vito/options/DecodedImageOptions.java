package com.facebook.fresco.vito.options;

import android.graphics.Bitmap;
import android.graphics.PointF;
import com.facebook.common.internal.Objects;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.fresco.vito.options.EncodedImageOptions;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.request.Postprocessor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001:\u0001=B\u0011\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004J\u0006\u00102\u001a\u00020\u001aJ\u0010\u00103\u001a\u00020\u001a2\u0006\u00104\u001a\u00020\u0000H\u0004J\u0013\u00105\u001a\u00020\u001a2\b\u00104\u001a\u0004\u0018\u000106H\u0002J\b\u00107\u001a\u000208H\u0016J\b\u00109\u001a\u00020:H\u0016J\b\u0010;\u001a\u00020<H\u0014R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0015\u0010\u0019\u001a\u0004\u0018\u00010\u001a¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u0019\u0010\u001bR\u0011\u0010\u001d\u001a\u00020\u001a¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010 \u001a\u00020\u001a¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001fR\u0013\u0010\"\u001a\u0004\u0018\u00010#¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0013\u0010&\u001a\u0004\u0018\u00010'¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0013\u0010*\u001a\u0004\u0018\u00010+¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0013\u0010.\u001a\u0004\u0018\u00010/¢\u0006\b\n\u0000\u001a\u0004\b0\u00101¨\u0006>"}, d2 = {"Lcom/facebook/fresco/vito/options/DecodedImageOptions;", "Lcom/facebook/fresco/vito/options/EncodedImageOptions;", "builder", "Lcom/facebook/fresco/vito/options/DecodedImageOptions$Builder;", "(Lcom/facebook/fresco/vito/options/DecodedImageOptions$Builder;)V", "actualImageFocusPoint", "Landroid/graphics/PointF;", "getActualImageFocusPoint", "()Landroid/graphics/PointF;", "actualImageScaleType", "Lcom/facebook/drawee/drawable/ScalingUtils$ScaleType;", "getActualImageScaleType", "()Lcom/facebook/drawee/drawable/ScalingUtils$ScaleType;", "bitmapConfig", "Landroid/graphics/Bitmap$Config;", "getBitmapConfig", "()Landroid/graphics/Bitmap$Config;", "borderOptions", "Lcom/facebook/fresco/vito/options/BorderOptions;", "getBorderOptions", "()Lcom/facebook/fresco/vito/options/BorderOptions;", "imageDecodeOptions", "Lcom/facebook/imagepipeline/common/ImageDecodeOptions;", "getImageDecodeOptions", "()Lcom/facebook/imagepipeline/common/ImageDecodeOptions;", "isProgressiveDecodingEnabled", "", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "loadThumbnailOnly", "getLoadThumbnailOnly", "()Z", "mLocalThumbnailPreviewsEnabled", "getMLocalThumbnailPreviewsEnabled", "postprocessor", "Lcom/facebook/imagepipeline/request/Postprocessor;", "getPostprocessor", "()Lcom/facebook/imagepipeline/request/Postprocessor;", "resizeOptions", "Lcom/facebook/imagepipeline/common/ResizeOptions;", "getResizeOptions", "()Lcom/facebook/imagepipeline/common/ResizeOptions;", "rotationOptions", "Lcom/facebook/imagepipeline/common/RotationOptions;", "getRotationOptions", "()Lcom/facebook/imagepipeline/common/RotationOptions;", "roundingOptions", "Lcom/facebook/fresco/vito/options/RoundingOptions;", "getRoundingOptions", "()Lcom/facebook/fresco/vito/options/RoundingOptions;", "areLocalThumbnailPreviewsEnabled", "equalDecodedOptions", "other", "equals", "", "hashCode", "", "toString", "", "toStringHelper", "Lcom/facebook/common/internal/Objects$ToStringHelper;", "Builder", "options_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: DecodedImageOptions.kt */
public class DecodedImageOptions extends EncodedImageOptions {
    private final PointF actualImageFocusPoint;
    private final ScalingUtils.ScaleType actualImageScaleType;
    private final Bitmap.Config bitmapConfig;
    private final BorderOptions borderOptions;
    private final ImageDecodeOptions imageDecodeOptions;
    private final Boolean isProgressiveDecodingEnabled;
    private final boolean loadThumbnailOnly;
    private final boolean mLocalThumbnailPreviewsEnabled;
    private final Postprocessor postprocessor;
    private final ResizeOptions resizeOptions;
    private final RotationOptions rotationOptions;
    private final RoundingOptions roundingOptions;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DecodedImageOptions(Builder<?> builder) {
        super(builder);
        Intrinsics.checkNotNullParameter(builder, "builder");
        this.resizeOptions = builder.getResizeOptions$options_release();
        this.rotationOptions = builder.getRotationOptions$options_release();
        this.postprocessor = builder.getPostprocessor$options_release();
        this.imageDecodeOptions = builder.getImageDecodeOptions$options_release();
        this.roundingOptions = builder.getRoundingOptions$options_release();
        this.borderOptions = builder.getBorderOptions$options_release();
        this.actualImageScaleType = builder.getActualImageScaleType$options_release();
        this.actualImageFocusPoint = builder.getActualFocusPoint$options_release();
        this.mLocalThumbnailPreviewsEnabled = builder.getLocalThumbnailPreviewsEnabled$options_release();
        this.loadThumbnailOnly = builder.getLoadThumbnailOnly$options_release();
        this.bitmapConfig = builder.getBitmapConfig$options_release();
        this.isProgressiveDecodingEnabled = builder.getProgressiveDecodingEnabled$options_release();
    }

    public final ResizeOptions getResizeOptions() {
        return this.resizeOptions;
    }

    public final RotationOptions getRotationOptions() {
        return this.rotationOptions;
    }

    public final Postprocessor getPostprocessor() {
        return this.postprocessor;
    }

    public final ImageDecodeOptions getImageDecodeOptions() {
        return this.imageDecodeOptions;
    }

    public final RoundingOptions getRoundingOptions() {
        return this.roundingOptions;
    }

    public final BorderOptions getBorderOptions() {
        return this.borderOptions;
    }

    public final ScalingUtils.ScaleType getActualImageScaleType() {
        return this.actualImageScaleType;
    }

    public final PointF getActualImageFocusPoint() {
        return this.actualImageFocusPoint;
    }

    public final boolean getMLocalThumbnailPreviewsEnabled() {
        return this.mLocalThumbnailPreviewsEnabled;
    }

    public final boolean getLoadThumbnailOnly() {
        return this.loadThumbnailOnly;
    }

    public final Bitmap.Config getBitmapConfig() {
        return this.bitmapConfig;
    }

    public final Boolean isProgressiveDecodingEnabled() {
        return this.isProgressiveDecodingEnabled;
    }

    public final boolean areLocalThumbnailPreviewsEnabled() {
        return this.mLocalThumbnailPreviewsEnabled;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !Intrinsics.areEqual((Object) getClass(), (Object) obj.getClass())) {
            return false;
        }
        return equalDecodedOptions((DecodedImageOptions) obj);
    }

    /* access modifiers changed from: protected */
    public final boolean equalDecodedOptions(DecodedImageOptions decodedImageOptions) {
        Intrinsics.checkNotNullParameter(decodedImageOptions, "other");
        if (!Objects.equal(this.resizeOptions, decodedImageOptions.resizeOptions) || !Objects.equal(this.rotationOptions, decodedImageOptions.rotationOptions) || !Objects.equal(this.postprocessor, decodedImageOptions.postprocessor) || !Objects.equal(this.imageDecodeOptions, decodedImageOptions.imageDecodeOptions) || !Objects.equal(this.roundingOptions, decodedImageOptions.roundingOptions) || !Objects.equal(this.borderOptions, decodedImageOptions.borderOptions) || !Objects.equal(this.actualImageScaleType, decodedImageOptions.actualImageScaleType) || !Objects.equal(this.actualImageFocusPoint, decodedImageOptions.actualImageFocusPoint) || this.mLocalThumbnailPreviewsEnabled != decodedImageOptions.mLocalThumbnailPreviewsEnabled || this.loadThumbnailOnly != decodedImageOptions.loadThumbnailOnly || this.isProgressiveDecodingEnabled != decodedImageOptions.isProgressiveDecodingEnabled || !Objects.equal(this.bitmapConfig, decodedImageOptions.bitmapConfig)) {
            return false;
        }
        return equalEncodedOptions(decodedImageOptions);
    }

    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        ResizeOptions resizeOptions2 = this.resizeOptions;
        int i = 0;
        int hashCode2 = (hashCode + (resizeOptions2 != null ? resizeOptions2.hashCode() : 0)) * 31;
        RotationOptions rotationOptions2 = this.rotationOptions;
        int hashCode3 = (hashCode2 + (rotationOptions2 != null ? rotationOptions2.hashCode() : 0)) * 31;
        Postprocessor postprocessor2 = this.postprocessor;
        int hashCode4 = (hashCode3 + (postprocessor2 != null ? postprocessor2.hashCode() : 0)) * 31;
        ImageDecodeOptions imageDecodeOptions2 = this.imageDecodeOptions;
        int hashCode5 = (hashCode4 + (imageDecodeOptions2 != null ? imageDecodeOptions2.hashCode() : 0)) * 31;
        RoundingOptions roundingOptions2 = this.roundingOptions;
        int hashCode6 = (hashCode5 + (roundingOptions2 != null ? roundingOptions2.hashCode() : 0)) * 31;
        BorderOptions borderOptions2 = this.borderOptions;
        int hashCode7 = (((hashCode6 + (borderOptions2 != null ? borderOptions2.hashCode() : 0)) * 31) + this.actualImageScaleType.hashCode()) * 31;
        PointF pointF = this.actualImageFocusPoint;
        int hashCode8 = (((((hashCode7 + (pointF != null ? pointF.hashCode() : 0)) * 31) + (this.mLocalThumbnailPreviewsEnabled ? 1 : 0)) * 31) + (this.loadThumbnailOnly ? 1 : 0)) * 31;
        Bitmap.Config config = this.bitmapConfig;
        int hashCode9 = (hashCode8 + (config != null ? config.hashCode() : 0)) * 31;
        Boolean bool = this.isProgressiveDecodingEnabled;
        if (bool != null) {
            i = bool.hashCode();
        }
        return hashCode9 + i;
    }

    public String toString() {
        return "DecodedImageOptions{" + toStringHelper() + AbstractJsonLexerKt.END_OBJ;
    }

    /* access modifiers changed from: protected */
    public Objects.ToStringHelper toStringHelper() {
        Objects.ToStringHelper add = super.toStringHelper().add("resizeOptions", (Object) this.resizeOptions).add("rotationOptions", (Object) this.resizeOptions).add("postprocessor", (Object) this.postprocessor).add("imageDecodeOptions", (Object) this.imageDecodeOptions).add("roundingOptions", (Object) this.roundingOptions).add("borderOptions", (Object) this.borderOptions).add("actualImageScaleType", (Object) this.actualImageScaleType).add("actualImageFocusPoint", (Object) this.actualImageFocusPoint).add("localThumbnailPreviewsEnabled", this.mLocalThumbnailPreviewsEnabled).add("loadThumbnailOnly", this.loadThumbnailOnly).add("bitmapConfig", (Object) this.bitmapConfig).add("progressiveRenderingEnabled", (Object) this.isProgressiveDecodingEnabled);
        Intrinsics.checkNotNullExpressionValue(add, "super.toStringHelper()\n …ogressiveDecodingEnabled)");
        return add;
    }

    @Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0016\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0007\b\u0016¢\u0006\u0002\u0010\u0003B\u000f\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0015\u0010\u0013\u001a\u00028\u00002\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014¢\u0006\u0002\u0010LJ\u0015\u0010M\u001a\u00028\u00002\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a¢\u0006\u0002\u0010NJ\b\u0010O\u001a\u00020PH\u0016J\u0015\u0010Q\u001a\u00028\u00002\b\u0010Q\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010RJ\u0015\u0010\u001f\u001a\u00028\u00002\b\u0010\u001f\u001a\u0004\u0018\u00010 ¢\u0006\u0002\u0010SJ\u0013\u0010%\u001a\u00028\u00002\u0006\u0010%\u001a\u00020&¢\u0006\u0002\u0010TJ\u0013\u0010+\u001a\u00028\u00002\u0006\u0010+\u001a\u00020&¢\u0006\u0002\u0010TJ-\u0010U\u001a\u00028\u00002\u001d\u0010V\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0000\u0012\u0004\u0012\u00020X0W¢\u0006\u0002\bYH\b¢\u0006\u0002\u0010ZJ\u0015\u0010[\u001a\u00028\u00002\b\u0010.\u001a\u0004\u0018\u00010/¢\u0006\u0002\u0010\\J\u0015\u0010]\u001a\u00028\u00002\b\u00104\u001a\u0004\u0018\u00010&¢\u0006\u0002\u0010^J\u0015\u0010_\u001a\u00028\u00002\b\u0010:\u001a\u0004\u0018\u00010;¢\u0006\u0002\u0010`J\u0015\u0010a\u001a\u00028\u00002\b\u0010@\u001a\u0004\u0018\u00010A¢\u0006\u0002\u0010bJ\u0015\u0010c\u001a\u00028\u00002\b\u0010F\u001a\u0004\u0018\u00010G¢\u0006\u0002\u0010dJ\u0015\u0010e\u001a\u00028\u00002\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0002\u0010fR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001c\u0010\u001f\u001a\u0004\u0018\u00010 X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001a\u0010%\u001a\u00020&X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001a\u0010+\u001a\u00020&X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010(\"\u0004\b-\u0010*R\u001c\u0010.\u001a\u0004\u0018\u00010/X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u001e\u00104\u001a\u0004\u0018\u00010&X\u000e¢\u0006\u0010\n\u0002\u00109\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u001c\u0010:\u001a\u0004\u0018\u00010;X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\u001c\u0010@\u001a\u0004\u0018\u00010AX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ER\u001c\u0010F\u001a\u0004\u0018\u00010GX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010K¨\u0006g"}, d2 = {"Lcom/facebook/fresco/vito/options/DecodedImageOptions$Builder;", "T", "Lcom/facebook/fresco/vito/options/EncodedImageOptions$Builder;", "()V", "defaultOptions", "Lcom/facebook/fresco/vito/options/ImageOptions;", "(Lcom/facebook/fresco/vito/options/ImageOptions;)V", "actualFocusPoint", "Landroid/graphics/PointF;", "getActualFocusPoint$options_release", "()Landroid/graphics/PointF;", "setActualFocusPoint$options_release", "(Landroid/graphics/PointF;)V", "actualImageScaleType", "Lcom/facebook/drawee/drawable/ScalingUtils$ScaleType;", "getActualImageScaleType$options_release", "()Lcom/facebook/drawee/drawable/ScalingUtils$ScaleType;", "setActualImageScaleType$options_release", "(Lcom/facebook/drawee/drawable/ScalingUtils$ScaleType;)V", "bitmapConfig", "Landroid/graphics/Bitmap$Config;", "getBitmapConfig$options_release", "()Landroid/graphics/Bitmap$Config;", "setBitmapConfig$options_release", "(Landroid/graphics/Bitmap$Config;)V", "borderOptions", "Lcom/facebook/fresco/vito/options/BorderOptions;", "getBorderOptions$options_release", "()Lcom/facebook/fresco/vito/options/BorderOptions;", "setBorderOptions$options_release", "(Lcom/facebook/fresco/vito/options/BorderOptions;)V", "imageDecodeOptions", "Lcom/facebook/imagepipeline/common/ImageDecodeOptions;", "getImageDecodeOptions$options_release", "()Lcom/facebook/imagepipeline/common/ImageDecodeOptions;", "setImageDecodeOptions$options_release", "(Lcom/facebook/imagepipeline/common/ImageDecodeOptions;)V", "loadThumbnailOnly", "", "getLoadThumbnailOnly$options_release", "()Z", "setLoadThumbnailOnly$options_release", "(Z)V", "localThumbnailPreviewsEnabled", "getLocalThumbnailPreviewsEnabled$options_release", "setLocalThumbnailPreviewsEnabled$options_release", "postprocessor", "Lcom/facebook/imagepipeline/request/Postprocessor;", "getPostprocessor$options_release", "()Lcom/facebook/imagepipeline/request/Postprocessor;", "setPostprocessor$options_release", "(Lcom/facebook/imagepipeline/request/Postprocessor;)V", "progressiveDecodingEnabled", "getProgressiveDecodingEnabled$options_release", "()Ljava/lang/Boolean;", "setProgressiveDecodingEnabled$options_release", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "resizeOptions", "Lcom/facebook/imagepipeline/common/ResizeOptions;", "getResizeOptions$options_release", "()Lcom/facebook/imagepipeline/common/ResizeOptions;", "setResizeOptions$options_release", "(Lcom/facebook/imagepipeline/common/ResizeOptions;)V", "rotationOptions", "Lcom/facebook/imagepipeline/common/RotationOptions;", "getRotationOptions$options_release", "()Lcom/facebook/imagepipeline/common/RotationOptions;", "setRotationOptions$options_release", "(Lcom/facebook/imagepipeline/common/RotationOptions;)V", "roundingOptions", "Lcom/facebook/fresco/vito/options/RoundingOptions;", "getRoundingOptions$options_release", "()Lcom/facebook/fresco/vito/options/RoundingOptions;", "setRoundingOptions$options_release", "(Lcom/facebook/fresco/vito/options/RoundingOptions;)V", "(Landroid/graphics/Bitmap$Config;)Lcom/facebook/fresco/vito/options/DecodedImageOptions$Builder;", "borders", "(Lcom/facebook/fresco/vito/options/BorderOptions;)Lcom/facebook/fresco/vito/options/DecodedImageOptions$Builder;", "build", "Lcom/facebook/fresco/vito/options/DecodedImageOptions;", "focusPoint", "(Landroid/graphics/PointF;)Lcom/facebook/fresco/vito/options/DecodedImageOptions$Builder;", "(Lcom/facebook/imagepipeline/common/ImageDecodeOptions;)Lcom/facebook/fresco/vito/options/DecodedImageOptions$Builder;", "(Z)Lcom/facebook/fresco/vito/options/DecodedImageOptions$Builder;", "modify", "block", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function1;)Lcom/facebook/fresco/vito/options/DecodedImageOptions$Builder;", "postprocess", "(Lcom/facebook/imagepipeline/request/Postprocessor;)Lcom/facebook/fresco/vito/options/DecodedImageOptions$Builder;", "progressiveRendering", "(Ljava/lang/Boolean;)Lcom/facebook/fresco/vito/options/DecodedImageOptions$Builder;", "resize", "(Lcom/facebook/imagepipeline/common/ResizeOptions;)Lcom/facebook/fresco/vito/options/DecodedImageOptions$Builder;", "rotate", "(Lcom/facebook/imagepipeline/common/RotationOptions;)Lcom/facebook/fresco/vito/options/DecodedImageOptions$Builder;", "round", "(Lcom/facebook/fresco/vito/options/RoundingOptions;)Lcom/facebook/fresco/vito/options/DecodedImageOptions$Builder;", "scale", "(Lcom/facebook/drawee/drawable/ScalingUtils$ScaleType;)Lcom/facebook/fresco/vito/options/DecodedImageOptions$Builder;", "options_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: DecodedImageOptions.kt */
    public static class Builder<T extends Builder<T>> extends EncodedImageOptions.Builder<T> {
        private PointF actualFocusPoint;
        private ScalingUtils.ScaleType actualImageScaleType;
        private Bitmap.Config bitmapConfig;
        private BorderOptions borderOptions;
        private ImageDecodeOptions imageDecodeOptions;
        private boolean loadThumbnailOnly;
        private boolean localThumbnailPreviewsEnabled;
        private Postprocessor postprocessor;
        private Boolean progressiveDecodingEnabled;
        private ResizeOptions resizeOptions;
        private RotationOptions rotationOptions;
        private RoundingOptions roundingOptions;

        public final ResizeOptions getResizeOptions$options_release() {
            return this.resizeOptions;
        }

        public final void setResizeOptions$options_release(ResizeOptions resizeOptions2) {
            this.resizeOptions = resizeOptions2;
        }

        public final RotationOptions getRotationOptions$options_release() {
            return this.rotationOptions;
        }

        public final void setRotationOptions$options_release(RotationOptions rotationOptions2) {
            this.rotationOptions = rotationOptions2;
        }

        public final Postprocessor getPostprocessor$options_release() {
            return this.postprocessor;
        }

        public final void setPostprocessor$options_release(Postprocessor postprocessor2) {
            this.postprocessor = postprocessor2;
        }

        public final ImageDecodeOptions getImageDecodeOptions$options_release() {
            return this.imageDecodeOptions;
        }

        public final void setImageDecodeOptions$options_release(ImageDecodeOptions imageDecodeOptions2) {
            this.imageDecodeOptions = imageDecodeOptions2;
        }

        public final RoundingOptions getRoundingOptions$options_release() {
            return this.roundingOptions;
        }

        public final void setRoundingOptions$options_release(RoundingOptions roundingOptions2) {
            this.roundingOptions = roundingOptions2;
        }

        public final BorderOptions getBorderOptions$options_release() {
            return this.borderOptions;
        }

        public final void setBorderOptions$options_release(BorderOptions borderOptions2) {
            this.borderOptions = borderOptions2;
        }

        public final ScalingUtils.ScaleType getActualImageScaleType$options_release() {
            return this.actualImageScaleType;
        }

        public final void setActualImageScaleType$options_release(ScalingUtils.ScaleType scaleType) {
            Intrinsics.checkNotNullParameter(scaleType, "<set-?>");
            this.actualImageScaleType = scaleType;
        }

        public final PointF getActualFocusPoint$options_release() {
            return this.actualFocusPoint;
        }

        public final void setActualFocusPoint$options_release(PointF pointF) {
            this.actualFocusPoint = pointF;
        }

        public final boolean getLocalThumbnailPreviewsEnabled$options_release() {
            return this.localThumbnailPreviewsEnabled;
        }

        public final void setLocalThumbnailPreviewsEnabled$options_release(boolean z) {
            this.localThumbnailPreviewsEnabled = z;
        }

        public final boolean getLoadThumbnailOnly$options_release() {
            return this.loadThumbnailOnly;
        }

        public final void setLoadThumbnailOnly$options_release(boolean z) {
            this.loadThumbnailOnly = z;
        }

        public final Bitmap.Config getBitmapConfig$options_release() {
            return this.bitmapConfig;
        }

        public final void setBitmapConfig$options_release(Bitmap.Config config) {
            this.bitmapConfig = config;
        }

        public final Boolean getProgressiveDecodingEnabled$options_release() {
            return this.progressiveDecodingEnabled;
        }

        public final void setProgressiveDecodingEnabled$options_release(Boolean bool) {
            this.progressiveDecodingEnabled = bool;
        }

        public Builder() {
            ScalingUtils.ScaleType scaleType = ScalingUtils.ScaleType.CENTER_CROP;
            Intrinsics.checkNotNullExpressionValue(scaleType, "CENTER_CROP");
            this.actualImageScaleType = scaleType;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Builder(ImageOptions imageOptions) {
            super(imageOptions);
            Intrinsics.checkNotNullParameter(imageOptions, "defaultOptions");
            ScalingUtils.ScaleType scaleType = ScalingUtils.ScaleType.CENTER_CROP;
            Intrinsics.checkNotNullExpressionValue(scaleType, "CENTER_CROP");
            this.actualImageScaleType = scaleType;
            this.resizeOptions = imageOptions.getResizeOptions();
            this.rotationOptions = imageOptions.getRotationOptions();
            this.postprocessor = imageOptions.getPostprocessor();
            this.imageDecodeOptions = imageOptions.getImageDecodeOptions();
            this.roundingOptions = imageOptions.getRoundingOptions();
            this.borderOptions = imageOptions.getBorderOptions();
            this.actualImageScaleType = imageOptions.getActualImageScaleType();
            this.actualFocusPoint = imageOptions.getActualImageFocusPoint();
            this.localThumbnailPreviewsEnabled = imageOptions.areLocalThumbnailPreviewsEnabled();
            this.loadThumbnailOnly = imageOptions.getLoadThumbnailOnly();
            this.bitmapConfig = imageOptions.getBitmapConfig();
            this.progressiveDecodingEnabled = imageOptions.isProgressiveDecodingEnabled();
        }

        public DecodedImageOptions build() {
            return new DecodedImageOptions(this);
        }

        private final T modify(Function1<? super Builder<T>, Unit> function1) {
            function1.invoke(this);
            return (Builder) getThis();
        }

        public final T resize(ResizeOptions resizeOptions2) {
            Builder builder = this;
            this.resizeOptions = resizeOptions2;
            return (Builder) getThis();
        }

        public final T rotate(RotationOptions rotationOptions2) {
            Builder builder = this;
            this.rotationOptions = rotationOptions2;
            return (Builder) getThis();
        }

        public final T postprocess(Postprocessor postprocessor2) {
            Builder builder = this;
            this.postprocessor = postprocessor2;
            return (Builder) getThis();
        }

        public final T imageDecodeOptions(ImageDecodeOptions imageDecodeOptions2) {
            Builder builder = this;
            this.imageDecodeOptions = imageDecodeOptions2;
            return (Builder) getThis();
        }

        public final T round(RoundingOptions roundingOptions2) {
            Builder builder = this;
            this.roundingOptions = roundingOptions2;
            return (Builder) getThis();
        }

        public final T borders(BorderOptions borderOptions2) {
            Builder builder = this;
            this.borderOptions = borderOptions2;
            return (Builder) getThis();
        }

        public final T scale(ScalingUtils.ScaleType scaleType) {
            Builder builder = this;
            if (scaleType == null) {
                scaleType = ImageOptions.Companion.defaults().getActualImageScaleType();
            }
            this.actualImageScaleType = scaleType;
            return (Builder) getThis();
        }

        public final T focusPoint(PointF pointF) {
            Builder builder = this;
            this.actualFocusPoint = pointF;
            return (Builder) getThis();
        }

        public final T localThumbnailPreviewsEnabled(boolean z) {
            Builder builder = this;
            this.localThumbnailPreviewsEnabled = z;
            return (Builder) getThis();
        }

        public final T loadThumbnailOnly(boolean z) {
            Builder builder = this;
            this.loadThumbnailOnly = z;
            return (Builder) getThis();
        }

        public final T bitmapConfig(Bitmap.Config config) {
            Builder builder = this;
            this.bitmapConfig = config;
            return (Builder) getThis();
        }

        public final T progressiveRendering(Boolean bool) {
            Builder builder = this;
            this.progressiveDecodingEnabled = bool;
            return (Builder) getThis();
        }
    }
}

package com.facebook.fresco.vito.options;

import android.graphics.ColorFilter;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import com.facebook.common.internal.Objects;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.fresco.vito.options.DecodedImageOptions;
import com.facebook.imagepipeline.common.Priority;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 R2\u00020\u0001:\u0002QRB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010C\u001a\u00020\u00062\b\u0010D\u001a\u0004\u0018\u00010EH\u0002J\u000e\u0010F\u001a\u00020\u00062\u0006\u0010G\u001a\u00020\u0000J\u0006\u0010H\u001a\u00020\u0003J\b\u0010I\u001a\u00020\u0015H\u0016J\u0006\u0010J\u001a\u00020\u0006J\u0006\u0010K\u001a\u00020\u0006J\u0006\u0010L\u001a\u00020\u0006J\b\u0010M\u001a\u00020NH\u0016J\b\u0010O\u001a\u00020PH\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u0004\u0018\u00010\u00158GX\u0004¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u001a¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u001e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0016\u0010!\u001a\u00020\u00158GX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0013\u0010$\u001a\u0004\u0018\u00010%¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0011\u0010(\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0013R\u0011\u0010*\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b+\u0010#R\u0011\u0010,\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u0013R\u0013\u0010-\u001a\u0004\u0018\u00010\u001a¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001cR\u0016\u0010/\u001a\u00020\u00158GX\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u0010#R\u0011\u00101\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u0013R\u001a\u00103\u001a\u0004\u0018\u00010\u00158GX\u0004¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b4\u0010\u0017R\u0013\u00105\u001a\u0004\u0018\u00010\u001a¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\u001cR\u0013\u00107\u001a\u0004\u0018\u00010\u001e¢\u0006\b\n\u0000\u001a\u0004\b8\u0010 R\u0016\u00109\u001a\u00020\u00158GX\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u0010#R\u0013\u0010;\u001a\u0004\u0018\u00010%¢\u0006\b\n\u0000\u001a\u0004\b<\u0010'R\u0013\u0010=\u001a\u0004\u0018\u00010\u001a¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\u001cR\u0016\u0010?\u001a\u00020\u00158GX\u0004¢\u0006\b\n\u0000\u001a\u0004\b@\u0010#R\u0013\u0010A\u001a\u0004\u0018\u00010%¢\u0006\b\n\u0000\u001a\u0004\bB\u0010'¨\u0006S"}, d2 = {"Lcom/facebook/fresco/vito/options/ImageOptions;", "Lcom/facebook/fresco/vito/options/DecodedImageOptions;", "builder", "Lcom/facebook/fresco/vito/options/ImageOptions$Builder;", "(Lcom/facebook/fresco/vito/options/ImageOptions$Builder;)V", "_autoPlay", "", "_autoStop", "_resizeToViewport", "actualImageColorFilter", "Landroid/graphics/ColorFilter;", "getActualImageColorFilter", "()Landroid/graphics/ColorFilter;", "customDrawableFactory", "Lcom/facebook/fresco/vito/options/ImageOptionsDrawableFactory;", "getCustomDrawableFactory", "()Lcom/facebook/fresco/vito/options/ImageOptionsDrawableFactory;", "errorApplyRoundingOptions", "getErrorApplyRoundingOptions", "()Z", "errorColor", "", "getErrorColor", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "errorDrawable", "Landroid/graphics/drawable/Drawable;", "getErrorDrawable", "()Landroid/graphics/drawable/Drawable;", "errorFocusPoint", "Landroid/graphics/PointF;", "getErrorFocusPoint", "()Landroid/graphics/PointF;", "errorRes", "getErrorRes", "()I", "errorScaleType", "Lcom/facebook/drawee/drawable/ScalingUtils$ScaleType;", "getErrorScaleType", "()Lcom/facebook/drawee/drawable/ScalingUtils$ScaleType;", "experimentalDynamicSize", "getExperimentalDynamicSize", "fadeDurationMs", "getFadeDurationMs", "isPerfMediaRemountInstrumentationFix", "overlayDrawable", "getOverlayDrawable", "overlayRes", "getOverlayRes", "placeholderApplyRoundingOptions", "getPlaceholderApplyRoundingOptions", "placeholderColor", "getPlaceholderColor", "placeholderDrawable", "getPlaceholderDrawable", "placeholderFocusPoint", "getPlaceholderFocusPoint", "placeholderRes", "getPlaceholderRes", "placeholderScaleType", "getPlaceholderScaleType", "progressDrawable", "getProgressDrawable", "progressRes", "getProgressRes", "progressScaleType", "getProgressScaleType", "equals", "otherObject", "", "equalsForActualImage", "other", "extend", "hashCode", "shouldAutoPlay", "shouldAutoStop", "shouldResizeToViewport", "toString", "", "toStringHelper", "Lcom/facebook/common/internal/Objects$ToStringHelper;", "Builder", "Companion", "options_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ImageOptions.kt */
public final class ImageOptions extends DecodedImageOptions {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static ImageOptions defaultImageOptions = ((Builder) new Builder().placeholderScaleType(ScalingUtils.ScaleType.CENTER_INSIDE).progressScaleType(ScalingUtils.ScaleType.CENTER_INSIDE).errorScaleType(ScalingUtils.ScaleType.CENTER_INSIDE).priority(Priority.HIGH)).build();
    private final boolean _autoPlay;
    private final boolean _autoStop;
    private final boolean _resizeToViewport;
    private final ColorFilter actualImageColorFilter;
    private final ImageOptionsDrawableFactory customDrawableFactory;
    private final boolean errorApplyRoundingOptions;
    private final Integer errorColor;
    private final Drawable errorDrawable;
    private final PointF errorFocusPoint;
    private final int errorRes;
    private final ScalingUtils.ScaleType errorScaleType;
    private final boolean experimentalDynamicSize;
    private final int fadeDurationMs;
    private final boolean isPerfMediaRemountInstrumentationFix;
    private final Drawable overlayDrawable;
    private final int overlayRes;
    private final boolean placeholderApplyRoundingOptions;
    private final Integer placeholderColor;
    private final Drawable placeholderDrawable;
    private final PointF placeholderFocusPoint;
    private final int placeholderRes;
    private final ScalingUtils.ScaleType placeholderScaleType;
    private final Drawable progressDrawable;
    private final int progressRes;
    private final ScalingUtils.ScaleType progressScaleType;

    @JvmStatic
    public static final Builder create() {
        return Companion.create();
    }

    @JvmStatic
    public static final ImageOptions defaults() {
        return Companion.defaults();
    }

    @JvmStatic
    public static final Builder extend(ImageOptions imageOptions) {
        return Companion.extend(imageOptions);
    }

    @JvmStatic
    public static final void setDefaults(ImageOptions imageOptions) {
        Companion.setDefaults(imageOptions);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ImageOptions(Builder builder) {
        super(builder);
        Intrinsics.checkNotNullParameter(builder, "builder");
        this.placeholderColor = builder.get_placeholderColor$options_release();
        this.placeholderRes = builder.get_placeholderRes$options_release();
        this.placeholderDrawable = builder.get_placeholderDrawable$options_release();
        this.placeholderScaleType = builder.get_placeholderScaleType$options_release();
        this.placeholderFocusPoint = builder.get_placeholderFocusPoint$options_release();
        this.placeholderApplyRoundingOptions = builder.get_placeholderApplyRoundingOptions$options_release();
        this.progressRes = builder.get_progressRes$options_release();
        this.progressDrawable = builder.get_progressDrawable$options_release();
        this.progressScaleType = builder.get_progressScaleType$options_release();
        this.errorColor = builder.get_errorColor$options_release();
        this.errorRes = builder.get_errorRes$options_release();
        this.errorScaleType = builder.get_errorScaleType$options_release();
        this.errorFocusPoint = builder.get_errorFocusPoint$options_release();
        this.errorDrawable = builder.get_errorDrawable$options_release();
        this.errorApplyRoundingOptions = builder.get_errorApplyRoundingOptions$options_release();
        this.actualImageColorFilter = builder.get_actualImageColorFilter$options_release();
        this.overlayRes = builder.get_overlayRes$options_release();
        this.overlayDrawable = builder.get_overlayDrawable$options_release();
        this._resizeToViewport = builder.get_resizeToViewport$options_release();
        this.fadeDurationMs = builder.get_fadeDurationMs$options_release();
        this._autoPlay = builder.get_autoPlay$options_release();
        this._autoStop = builder.get_autoStop$options_release();
        this.isPerfMediaRemountInstrumentationFix = builder.get_perfMediaRemountInstrumentationFix$options_release();
        this.customDrawableFactory = builder.get_customDrawableFactory$options_release();
        this.experimentalDynamicSize = builder.get_experimentalDynamicSize$options_release();
    }

    public final Integer getPlaceholderColor() {
        return this.placeholderColor;
    }

    public final int getPlaceholderRes() {
        return this.placeholderRes;
    }

    public final Drawable getPlaceholderDrawable() {
        return this.placeholderDrawable;
    }

    public final ScalingUtils.ScaleType getPlaceholderScaleType() {
        return this.placeholderScaleType;
    }

    public final PointF getPlaceholderFocusPoint() {
        return this.placeholderFocusPoint;
    }

    public final boolean getPlaceholderApplyRoundingOptions() {
        return this.placeholderApplyRoundingOptions;
    }

    public final int getProgressRes() {
        return this.progressRes;
    }

    public final Drawable getProgressDrawable() {
        return this.progressDrawable;
    }

    public final ScalingUtils.ScaleType getProgressScaleType() {
        return this.progressScaleType;
    }

    public final Integer getErrorColor() {
        return this.errorColor;
    }

    public final int getErrorRes() {
        return this.errorRes;
    }

    public final ScalingUtils.ScaleType getErrorScaleType() {
        return this.errorScaleType;
    }

    public final PointF getErrorFocusPoint() {
        return this.errorFocusPoint;
    }

    public final Drawable getErrorDrawable() {
        return this.errorDrawable;
    }

    public final boolean getErrorApplyRoundingOptions() {
        return this.errorApplyRoundingOptions;
    }

    public final ColorFilter getActualImageColorFilter() {
        return this.actualImageColorFilter;
    }

    public final int getOverlayRes() {
        return this.overlayRes;
    }

    public final Drawable getOverlayDrawable() {
        return this.overlayDrawable;
    }

    public final int getFadeDurationMs() {
        return this.fadeDurationMs;
    }

    public final boolean isPerfMediaRemountInstrumentationFix() {
        return this.isPerfMediaRemountInstrumentationFix;
    }

    public final ImageOptionsDrawableFactory getCustomDrawableFactory() {
        return this.customDrawableFactory;
    }

    public final boolean getExperimentalDynamicSize() {
        return this.experimentalDynamicSize;
    }

    public final Builder extend() {
        return Companion.extend(this);
    }

    public final boolean shouldAutoPlay() {
        return this._autoPlay;
    }

    public final boolean shouldAutoStop() {
        return this._autoStop;
    }

    public final boolean shouldResizeToViewport() {
        return this._resizeToViewport;
    }

    public final boolean equalsForActualImage(ImageOptions imageOptions) {
        Intrinsics.checkNotNullParameter(imageOptions, "other");
        if (this == imageOptions) {
            return true;
        }
        if (this.isPerfMediaRemountInstrumentationFix) {
            if (!(this.overlayRes == imageOptions.overlayRes && Objects.equal(this.overlayDrawable, imageOptions.overlayDrawable) && Objects.equal(this.actualImageColorFilter, imageOptions.actualImageColorFilter) && this._resizeToViewport == imageOptions._resizeToViewport && this._autoPlay == imageOptions._autoPlay && this._autoStop == imageOptions._autoStop && Objects.equal(this.customDrawableFactory, imageOptions.customDrawableFactory) && this.isPerfMediaRemountInstrumentationFix == imageOptions.isPerfMediaRemountInstrumentationFix)) {
                return false;
            }
        } else if (this.overlayRes != imageOptions.overlayRes || !Objects.equal(this.overlayDrawable, imageOptions.overlayDrawable) || !Objects.equal(this.actualImageColorFilter, imageOptions.actualImageColorFilter) || this._resizeToViewport != imageOptions._resizeToViewport || !Objects.equal(this.customDrawableFactory, imageOptions.customDrawableFactory)) {
            return false;
        }
        return equalDecodedOptions(imageOptions);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0192, code lost:
        if (r3.errorDrawable == r4.errorDrawable) goto L_0x0195;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r4) {
        /*
            r3 = this;
            if (r3 != r4) goto L_0x0004
            r4 = 1
            return r4
        L_0x0004:
            r0 = 0
            if (r4 == 0) goto L_0x019c
            java.lang.Class r1 = r3.getClass()
            java.lang.Class r2 = r4.getClass()
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r2)
            if (r1 != 0) goto L_0x0017
            goto L_0x019c
        L_0x0017:
            com.facebook.fresco.vito.options.ImageOptions r4 = (com.facebook.fresco.vito.options.ImageOptions) r4
            boolean r1 = r3.isPerfMediaRemountInstrumentationFix
            if (r1 == 0) goto L_0x00e2
            java.lang.Integer r1 = r3.placeholderColor
            java.lang.Integer r2 = r4.placeholderColor
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r2)
            if (r1 == 0) goto L_0x00e1
            int r1 = r3.placeholderRes
            int r2 = r4.placeholderRes
            if (r1 != r2) goto L_0x00e1
            android.graphics.drawable.Drawable r1 = r3.placeholderDrawable
            android.graphics.drawable.Drawable r2 = r4.placeholderDrawable
            boolean r1 = com.facebook.common.internal.Objects.equal(r1, r2)
            if (r1 == 0) goto L_0x00e1
            com.facebook.drawee.drawable.ScalingUtils$ScaleType r1 = r3.placeholderScaleType
            com.facebook.drawee.drawable.ScalingUtils$ScaleType r2 = r4.placeholderScaleType
            boolean r1 = com.facebook.common.internal.Objects.equal(r1, r2)
            if (r1 == 0) goto L_0x00e1
            android.graphics.PointF r1 = r3.placeholderFocusPoint
            android.graphics.PointF r2 = r4.placeholderFocusPoint
            boolean r1 = com.facebook.common.internal.Objects.equal(r1, r2)
            if (r1 == 0) goto L_0x00e1
            boolean r1 = r3.placeholderApplyRoundingOptions
            boolean r2 = r4.placeholderApplyRoundingOptions
            if (r1 != r2) goto L_0x00e1
            java.lang.Integer r1 = r3.errorColor
            java.lang.Integer r2 = r4.errorColor
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r2)
            if (r1 == 0) goto L_0x00e1
            int r1 = r3.errorRes
            int r2 = r4.errorRes
            if (r1 != r2) goto L_0x00e1
            com.facebook.drawee.drawable.ScalingUtils$ScaleType r1 = r3.errorScaleType
            com.facebook.drawee.drawable.ScalingUtils$ScaleType r2 = r4.errorScaleType
            boolean r1 = com.facebook.common.internal.Objects.equal(r1, r2)
            if (r1 == 0) goto L_0x00e1
            android.graphics.PointF r1 = r3.errorFocusPoint
            android.graphics.PointF r2 = r4.errorFocusPoint
            boolean r1 = com.facebook.common.internal.Objects.equal(r1, r2)
            if (r1 == 0) goto L_0x00e1
            boolean r1 = r3.errorApplyRoundingOptions
            boolean r2 = r4.errorApplyRoundingOptions
            if (r1 != r2) goto L_0x00e1
            int r1 = r3.overlayRes
            int r2 = r4.overlayRes
            if (r1 != r2) goto L_0x00e1
            android.graphics.drawable.Drawable r1 = r3.overlayDrawable
            android.graphics.drawable.Drawable r2 = r4.overlayDrawable
            boolean r1 = com.facebook.common.internal.Objects.equal(r1, r2)
            if (r1 == 0) goto L_0x00e1
            int r1 = r3.progressRes
            int r2 = r4.progressRes
            if (r1 != r2) goto L_0x00e1
            android.graphics.drawable.Drawable r1 = r3.progressDrawable
            android.graphics.drawable.Drawable r2 = r4.progressDrawable
            boolean r1 = com.facebook.common.internal.Objects.equal(r1, r2)
            if (r1 == 0) goto L_0x00e1
            com.facebook.drawee.drawable.ScalingUtils$ScaleType r1 = r3.progressScaleType
            com.facebook.drawee.drawable.ScalingUtils$ScaleType r2 = r4.progressScaleType
            boolean r1 = com.facebook.common.internal.Objects.equal(r1, r2)
            if (r1 == 0) goto L_0x00e1
            android.graphics.ColorFilter r1 = r3.actualImageColorFilter
            android.graphics.ColorFilter r2 = r4.actualImageColorFilter
            boolean r1 = com.facebook.common.internal.Objects.equal(r1, r2)
            if (r1 == 0) goto L_0x00e1
            boolean r1 = r3._resizeToViewport
            boolean r2 = r4._resizeToViewport
            if (r1 != r2) goto L_0x00e1
            int r1 = r3.fadeDurationMs
            int r2 = r4.fadeDurationMs
            if (r1 != r2) goto L_0x00e1
            boolean r1 = r3._autoPlay
            boolean r2 = r4._autoPlay
            if (r1 != r2) goto L_0x00e1
            boolean r1 = r3._autoStop
            boolean r2 = r4._autoStop
            if (r1 != r2) goto L_0x00e1
            com.facebook.fresco.vito.options.ImageOptionsDrawableFactory r1 = r3.customDrawableFactory
            com.facebook.fresco.vito.options.ImageOptionsDrawableFactory r2 = r4.customDrawableFactory
            boolean r1 = com.facebook.common.internal.Objects.equal(r1, r2)
            if (r1 == 0) goto L_0x00e1
            android.graphics.drawable.Drawable r1 = r3.errorDrawable
            android.graphics.drawable.Drawable r2 = r4.errorDrawable
            boolean r1 = com.facebook.common.internal.Objects.equal(r1, r2)
            if (r1 == 0) goto L_0x00e1
            boolean r1 = r3.isPerfMediaRemountInstrumentationFix
            boolean r2 = r4.isPerfMediaRemountInstrumentationFix
            if (r1 == r2) goto L_0x0195
        L_0x00e1:
            return r0
        L_0x00e2:
            java.lang.Integer r1 = r3.placeholderColor
            java.lang.Integer r2 = r4.placeholderColor
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r2)
            if (r1 == 0) goto L_0x019c
            int r1 = r3.placeholderRes
            int r2 = r4.placeholderRes
            if (r1 != r2) goto L_0x019c
            android.graphics.drawable.Drawable r1 = r3.placeholderDrawable
            android.graphics.drawable.Drawable r2 = r4.placeholderDrawable
            boolean r1 = com.facebook.common.internal.Objects.equal(r1, r2)
            if (r1 == 0) goto L_0x019c
            com.facebook.drawee.drawable.ScalingUtils$ScaleType r1 = r3.placeholderScaleType
            com.facebook.drawee.drawable.ScalingUtils$ScaleType r2 = r4.placeholderScaleType
            boolean r1 = com.facebook.common.internal.Objects.equal(r1, r2)
            if (r1 == 0) goto L_0x019c
            android.graphics.PointF r1 = r3.placeholderFocusPoint
            android.graphics.PointF r2 = r4.placeholderFocusPoint
            boolean r1 = com.facebook.common.internal.Objects.equal(r1, r2)
            if (r1 == 0) goto L_0x019c
            boolean r1 = r3.placeholderApplyRoundingOptions
            boolean r2 = r4.placeholderApplyRoundingOptions
            if (r1 != r2) goto L_0x019c
            java.lang.Integer r1 = r3.errorColor
            java.lang.Integer r2 = r4.errorColor
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r2)
            if (r1 == 0) goto L_0x019c
            int r1 = r3.errorRes
            int r2 = r4.errorRes
            if (r1 != r2) goto L_0x019c
            com.facebook.drawee.drawable.ScalingUtils$ScaleType r1 = r3.errorScaleType
            com.facebook.drawee.drawable.ScalingUtils$ScaleType r2 = r4.errorScaleType
            boolean r1 = com.facebook.common.internal.Objects.equal(r1, r2)
            if (r1 == 0) goto L_0x019c
            android.graphics.PointF r1 = r3.errorFocusPoint
            android.graphics.PointF r2 = r4.errorFocusPoint
            boolean r1 = com.facebook.common.internal.Objects.equal(r1, r2)
            if (r1 == 0) goto L_0x019c
            boolean r1 = r3.errorApplyRoundingOptions
            boolean r2 = r4.errorApplyRoundingOptions
            if (r1 != r2) goto L_0x019c
            int r1 = r3.overlayRes
            int r2 = r4.overlayRes
            if (r1 != r2) goto L_0x019c
            android.graphics.drawable.Drawable r1 = r3.overlayDrawable
            android.graphics.drawable.Drawable r2 = r4.overlayDrawable
            boolean r1 = com.facebook.common.internal.Objects.equal(r1, r2)
            if (r1 == 0) goto L_0x019c
            int r1 = r3.progressRes
            int r2 = r4.progressRes
            if (r1 != r2) goto L_0x019c
            android.graphics.drawable.Drawable r1 = r3.progressDrawable
            android.graphics.drawable.Drawable r2 = r4.progressDrawable
            if (r1 != r2) goto L_0x019c
            com.facebook.drawee.drawable.ScalingUtils$ScaleType r1 = r3.progressScaleType
            com.facebook.drawee.drawable.ScalingUtils$ScaleType r2 = r4.progressScaleType
            if (r1 != r2) goto L_0x019c
            android.graphics.ColorFilter r1 = r3.actualImageColorFilter
            android.graphics.ColorFilter r2 = r4.actualImageColorFilter
            boolean r1 = com.facebook.common.internal.Objects.equal(r1, r2)
            if (r1 == 0) goto L_0x019c
            boolean r1 = r3._resizeToViewport
            boolean r2 = r4._resizeToViewport
            if (r1 != r2) goto L_0x019c
            int r1 = r3.fadeDurationMs
            int r2 = r4.fadeDurationMs
            if (r1 != r2) goto L_0x019c
            boolean r1 = r3._autoPlay
            boolean r2 = r4._autoPlay
            if (r1 != r2) goto L_0x019c
            boolean r1 = r3._autoStop
            boolean r2 = r4._autoStop
            if (r1 != r2) goto L_0x019c
            com.facebook.fresco.vito.options.ImageOptionsDrawableFactory r1 = r3.customDrawableFactory
            com.facebook.fresco.vito.options.ImageOptionsDrawableFactory r2 = r4.customDrawableFactory
            boolean r1 = com.facebook.common.internal.Objects.equal(r1, r2)
            if (r1 == 0) goto L_0x019c
            android.graphics.drawable.Drawable r1 = r3.errorDrawable
            android.graphics.drawable.Drawable r2 = r4.errorDrawable
            if (r1 == r2) goto L_0x0195
            goto L_0x019c
        L_0x0195:
            com.facebook.fresco.vito.options.DecodedImageOptions r4 = (com.facebook.fresco.vito.options.DecodedImageOptions) r4
            boolean r4 = r3.equalDecodedOptions(r4)
            return r4
        L_0x019c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.fresco.vito.options.ImageOptions.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        Integer num = this.placeholderColor;
        int i = 0;
        int intValue = (((hashCode + (num != null ? num.intValue() : 0)) * 31) + this.placeholderRes) * 31;
        Drawable drawable = this.placeholderDrawable;
        int hashCode2 = (intValue + (drawable != null ? drawable.hashCode() : 0)) * 31;
        ScalingUtils.ScaleType scaleType = this.placeholderScaleType;
        int hashCode3 = (hashCode2 + (scaleType != null ? scaleType.hashCode() : 0)) * 31;
        PointF pointF = this.placeholderFocusPoint;
        int hashCode4 = (((hashCode3 + (pointF != null ? pointF.hashCode() : 0)) * 31) + (this.placeholderApplyRoundingOptions ? 1 : 0)) * 31;
        Integer num2 = this.errorColor;
        int intValue2 = (((hashCode4 + (num2 != null ? num2.intValue() : 0)) * 31) + this.errorRes) * 31;
        ScalingUtils.ScaleType scaleType2 = this.errorScaleType;
        int hashCode5 = (intValue2 + (scaleType2 != null ? scaleType2.hashCode() : 0)) * 31;
        PointF pointF2 = this.errorFocusPoint;
        int hashCode6 = (hashCode5 + (pointF2 != null ? pointF2.hashCode() : 0)) * 31;
        Drawable drawable2 = this.errorDrawable;
        int hashCode7 = (((((hashCode6 + (drawable2 != null ? drawable2.hashCode() : 0)) * 31) + (this.errorApplyRoundingOptions ? 1 : 0)) * 31) + this.overlayRes) * 31;
        Drawable drawable3 = this.overlayDrawable;
        int hashCode8 = (hashCode7 + (drawable3 != null ? drawable3.hashCode() : 0)) * 31;
        Drawable drawable4 = this.progressDrawable;
        int hashCode9 = (hashCode8 + (drawable4 != null ? drawable4.hashCode() : 0)) * 31;
        ScalingUtils.ScaleType scaleType3 = this.progressScaleType;
        int hashCode10 = (hashCode9 + (scaleType3 != null ? scaleType3.hashCode() : 0)) * 31;
        ColorFilter colorFilter = this.actualImageColorFilter;
        int hashCode11 = (((((((((((((hashCode10 + (colorFilter != null ? colorFilter.hashCode() : 0)) * 31) + (this._resizeToViewport ? 1 : 0)) * 31) + this.fadeDurationMs) * 31) + (this._autoPlay ? 1 : 0)) * 31) + (this._autoStop ? 1 : 0)) * 31) + (this.isPerfMediaRemountInstrumentationFix ? 1 : 0)) * 31) + this.progressRes) * 31;
        ImageOptionsDrawableFactory imageOptionsDrawableFactory = this.customDrawableFactory;
        if (imageOptionsDrawableFactory != null) {
            i = imageOptionsDrawableFactory.hashCode();
        }
        return hashCode11 + i;
    }

    public String toString() {
        return "ImageOptions{" + toStringHelper() + AbstractJsonLexerKt.END_OBJ;
    }

    /* access modifiers changed from: protected */
    public Objects.ToStringHelper toStringHelper() {
        Objects.ToStringHelper add = super.toStringHelper().add("placeholderColor", (Object) this.placeholderColor).add("placeholderRes", this.placeholderRes).add("placeholderDrawable", (Object) this.placeholderDrawable).add("placeholderScaleType", (Object) this.placeholderScaleType).add("placeholderFocusPoint", (Object) this.placeholderFocusPoint).add("placeholderApplyRoundingOptions", this.placeholderApplyRoundingOptions).add("progressRes", this.progressRes).add("progressDrawable", (Object) this.progressDrawable).add("progressScaleType", (Object) this.progressScaleType).add("errorColor", (Object) this.errorColor).add("errorRes", this.errorRes).add("errorScaleType", (Object) this.errorScaleType).add("errorFocusPoint", (Object) this.errorFocusPoint).add("errorDrawable", (Object) this.errorDrawable).add("errorApplyRoundingOptions", this.errorApplyRoundingOptions).add("actualImageColorFilter", (Object) this.actualImageColorFilter).add("overlayRes", this.overlayRes).add("overlayDrawable", (Object) this.overlayDrawable).add("resizeToViewport", this._resizeToViewport).add("autoPlay", this._autoPlay).add("autoStop", this._autoStop).add("mPerfMediaRemountInstrumentationFix", this.isPerfMediaRemountInstrumentationFix).add("fadeDurationMs", this.fadeDurationMs).add("customDrawableFactory", (Object) this.customDrawableFactory);
        Intrinsics.checkNotNullExpressionValue(add, "super.toStringHelper()\n …\", customDrawableFactory)");
        return add;
    }

    @Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\bC\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0010¢\u0006\u0002\u0010\u0002B\u000f\b\u0010\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000e\u0010i\u001a\u00020\u00002\u0006\u0010i\u001a\u00020\rJ\u000e\u0010j\u001a\u00020\u00002\u0006\u0010j\u001a\u00020\rJ\b\u0010k\u001a\u00020\u0004H\u0016J\u0010\u0010l\u001a\u00020\u00002\b\u0010l\u001a\u0004\u0018\u00010\u0007J\u0010\u0010m\u001a\u00020\u00002\b\u0010n\u001a\u0004\u0018\u00010\u0016J\u000e\u0010o\u001a\u00020\u00002\u0006\u0010o\u001a\u00020\rJ\u0010\u0010p\u001a\u00020\u00002\b\b\u0001\u0010p\u001a\u00020\u001fJ\u0010\u0010q\u001a\u00020\u00002\b\u0010q\u001a\u0004\u0018\u00010&J\u0010\u0010r\u001a\u00020\u00002\b\u0010r\u001a\u0004\u0018\u00010,J\u0010\u0010s\u001a\u00020\u00002\b\b\u0001\u0010s\u001a\u00020\u001fJ\u0010\u0010t\u001a\u00020\u00002\b\u0010t\u001a\u0004\u0018\u000107J\u000e\u0010u\u001a\u00020\u00002\u0006\u0010v\u001a\u00020\rJ\u000e\u0010w\u001a\u00020\u00002\u0006\u0010x\u001a\u00020\u001fJ\"\u0010y\u001a\u00020\u00002\u0017\u0010z\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020|0{¢\u0006\u0002\b}H\bJ\u0010\u0010~\u001a\u00020\u00002\b\u0010\u001a\u0004\u0018\u00010&J\u0012\u0010\u0001\u001a\u00020\u00002\t\b\u0001\u0010\u0001\u001a\u00020\u001fJ\u0010\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020\rJ\u0012\u0010\u0001\u001a\u00020\u00002\t\u0010\u0001\u001a\u0004\u0018\u00010&J\u001d\u0010\u0001\u001a\u00020\u00002\t\u0010\u0001\u001a\u0004\u0018\u00010&2\t\u0010\u0001\u001a\u0004\u0018\u000107J\u0010\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020\rJ\u0012\u0010\u0001\u001a\u00020\u00002\t\b\u0001\u0010\u0001\u001a\u00020\u001fJ\u0012\u0010\u0001\u001a\u00020\u00002\t\u0010\u0001\u001a\u0004\u0018\u00010,J\u0012\u0010\u0001\u001a\u00020\u00002\t\b\u0001\u0010\u0001\u001a\u00020\u001fJ\u001d\u0010\u0001\u001a\u00020\u00002\t\b\u0001\u0010\u0001\u001a\u00020\u001f2\t\u0010\u0001\u001a\u0004\u0018\u000107J\u0012\u0010\u0001\u001a\u00020\u00002\t\u0010\u0001\u001a\u0004\u0018\u000107J\u0012\u0010\u0001\u001a\u00020\u00002\t\u0010\u0001\u001a\u0004\u0018\u00010&J\u001d\u0010\u0001\u001a\u00020\u00002\t\u0010\u0001\u001a\u0004\u0018\u00010&2\t\u0010\u0001\u001a\u0004\u0018\u000107J\u0012\u0010\u0001\u001a\u00020\u00002\t\b\u0001\u0010\u0001\u001a\u00020\u001fJ\u001d\u0010\u0001\u001a\u00020\u00002\t\b\u0001\u0010\u0001\u001a\u00020\u001f2\t\u0010\u0001\u001a\u0004\u0018\u000107J\u0012\u0010\u0001\u001a\u00020\u00002\t\u0010\u0001\u001a\u0004\u0018\u000107J\u0010\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020\rR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u000f\"\u0004\b\u001d\u0010\u0011R\"\u0010\u001e\u001a\u0004\u0018\u00010\u001f8\u0000@\u0000X\u000e¢\u0006\u0010\n\u0002\u0010$\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001c\u0010%\u001a\u0004\u0018\u00010&X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001c\u0010+\u001a\u0004\u0018\u00010,X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001e\u00101\u001a\u00020\u001f8\u0000@\u0000X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u001c\u00106\u001a\u0004\u0018\u000107X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\u001a\u0010<\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u000f\"\u0004\b>\u0010\u0011R\u001a\u0010?\u001a\u00020\u001fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u00103\"\u0004\bA\u00105R\u001c\u0010B\u001a\u0004\u0018\u00010&X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010(\"\u0004\bD\u0010*R\u001e\u0010E\u001a\u00020\u001f8\u0000@\u0000X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u00103\"\u0004\bG\u00105R\u001a\u0010H\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010\u000f\"\u0004\bJ\u0010\u0011R\u001a\u0010K\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010\u000f\"\u0004\bM\u0010\u0011R\"\u0010N\u001a\u0004\u0018\u00010\u001f8\u0000@\u0000X\u000e¢\u0006\u0010\n\u0002\u0010$\u001a\u0004\bO\u0010!\"\u0004\bP\u0010#R\u001c\u0010Q\u001a\u0004\u0018\u00010&X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010(\"\u0004\bS\u0010*R\u001c\u0010T\u001a\u0004\u0018\u00010,X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bU\u0010.\"\u0004\bV\u00100R\u001e\u0010W\u001a\u00020\u001f8\u0000@\u0000X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u00103\"\u0004\bY\u00105R\u001c\u0010Z\u001a\u0004\u0018\u000107X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b[\u00109\"\u0004\b\\\u0010;R\u001c\u0010]\u001a\u0004\u0018\u00010&X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b^\u0010(\"\u0004\b_\u0010*R\u001e\u0010`\u001a\u00020\u001f8\u0000@\u0000X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\ba\u00103\"\u0004\bb\u00105R\u001c\u0010c\u001a\u0004\u0018\u000107X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bd\u00109\"\u0004\be\u0010;R\u001a\u0010f\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bg\u0010\u000f\"\u0004\bh\u0010\u0011¨\u0006\u0001"}, d2 = {"Lcom/facebook/fresco/vito/options/ImageOptions$Builder;", "Lcom/facebook/fresco/vito/options/DecodedImageOptions$Builder;", "()V", "defaultOptions", "Lcom/facebook/fresco/vito/options/ImageOptions;", "(Lcom/facebook/fresco/vito/options/ImageOptions;)V", "_actualImageColorFilter", "Landroid/graphics/ColorFilter;", "get_actualImageColorFilter$options_release", "()Landroid/graphics/ColorFilter;", "set_actualImageColorFilter$options_release", "(Landroid/graphics/ColorFilter;)V", "_autoPlay", "", "get_autoPlay$options_release", "()Z", "set_autoPlay$options_release", "(Z)V", "_autoStop", "get_autoStop$options_release", "set_autoStop$options_release", "_customDrawableFactory", "Lcom/facebook/fresco/vito/options/ImageOptionsDrawableFactory;", "get_customDrawableFactory$options_release", "()Lcom/facebook/fresco/vito/options/ImageOptionsDrawableFactory;", "set_customDrawableFactory$options_release", "(Lcom/facebook/fresco/vito/options/ImageOptionsDrawableFactory;)V", "_errorApplyRoundingOptions", "get_errorApplyRoundingOptions$options_release", "set_errorApplyRoundingOptions$options_release", "_errorColor", "", "get_errorColor$options_release", "()Ljava/lang/Integer;", "set_errorColor$options_release", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "_errorDrawable", "Landroid/graphics/drawable/Drawable;", "get_errorDrawable$options_release", "()Landroid/graphics/drawable/Drawable;", "set_errorDrawable$options_release", "(Landroid/graphics/drawable/Drawable;)V", "_errorFocusPoint", "Landroid/graphics/PointF;", "get_errorFocusPoint$options_release", "()Landroid/graphics/PointF;", "set_errorFocusPoint$options_release", "(Landroid/graphics/PointF;)V", "_errorRes", "get_errorRes$options_release", "()I", "set_errorRes$options_release", "(I)V", "_errorScaleType", "Lcom/facebook/drawee/drawable/ScalingUtils$ScaleType;", "get_errorScaleType$options_release", "()Lcom/facebook/drawee/drawable/ScalingUtils$ScaleType;", "set_errorScaleType$options_release", "(Lcom/facebook/drawee/drawable/ScalingUtils$ScaleType;)V", "_experimentalDynamicSize", "get_experimentalDynamicSize$options_release", "set_experimentalDynamicSize$options_release", "_fadeDurationMs", "get_fadeDurationMs$options_release", "set_fadeDurationMs$options_release", "_overlayDrawable", "get_overlayDrawable$options_release", "set_overlayDrawable$options_release", "_overlayRes", "get_overlayRes$options_release", "set_overlayRes$options_release", "_perfMediaRemountInstrumentationFix", "get_perfMediaRemountInstrumentationFix$options_release", "set_perfMediaRemountInstrumentationFix$options_release", "_placeholderApplyRoundingOptions", "get_placeholderApplyRoundingOptions$options_release", "set_placeholderApplyRoundingOptions$options_release", "_placeholderColor", "get_placeholderColor$options_release", "set_placeholderColor$options_release", "_placeholderDrawable", "get_placeholderDrawable$options_release", "set_placeholderDrawable$options_release", "_placeholderFocusPoint", "get_placeholderFocusPoint$options_release", "set_placeholderFocusPoint$options_release", "_placeholderRes", "get_placeholderRes$options_release", "set_placeholderRes$options_release", "_placeholderScaleType", "get_placeholderScaleType$options_release", "set_placeholderScaleType$options_release", "_progressDrawable", "get_progressDrawable$options_release", "set_progressDrawable$options_release", "_progressRes", "get_progressRes$options_release", "set_progressRes$options_release", "_progressScaleType", "get_progressScaleType$options_release", "set_progressScaleType$options_release", "_resizeToViewport", "get_resizeToViewport$options_release", "set_resizeToViewport$options_release", "autoPlay", "autoStop", "build", "colorFilter", "customDrawableFactory", "drawableFactory", "errorApplyRoundingOptions", "errorColor", "errorDrawable", "errorFocusPoint", "errorRes", "errorScaleType", "experimentalDynamicSize", "dynamicSize", "fadeDurationMs", "fadeInDurationMs", "modify", "block", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "overlay", "overlayDrawable", "overlayRes", "perfMediaRemountInstrumentationFix", "fix", "placeholder", "placeholderScaleType", "placeholderApplyRoundingOptions", "placeholderColor", "placeholderFocusPoint", "placeholderRes", "progress", "progressScaleType", "progressRes", "resizeToViewport", "options_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ImageOptions.kt */
    public static final class Builder extends DecodedImageOptions.Builder<Builder> {
        private ColorFilter _actualImageColorFilter;
        private boolean _autoPlay;
        private boolean _autoStop = true;
        private ImageOptionsDrawableFactory _customDrawableFactory;
        private boolean _errorApplyRoundingOptions;
        private Integer _errorColor;
        private Drawable _errorDrawable;
        private PointF _errorFocusPoint;
        private int _errorRes;
        private ScalingUtils.ScaleType _errorScaleType;
        private boolean _experimentalDynamicSize;
        private int _fadeDurationMs;
        private Drawable _overlayDrawable;
        private int _overlayRes;
        private boolean _perfMediaRemountInstrumentationFix;
        private boolean _placeholderApplyRoundingOptions;
        private Integer _placeholderColor;
        private Drawable _placeholderDrawable;
        private PointF _placeholderFocusPoint;
        private int _placeholderRes;
        private ScalingUtils.ScaleType _placeholderScaleType;
        private Drawable _progressDrawable;
        private int _progressRes;
        private ScalingUtils.ScaleType _progressScaleType;
        private boolean _resizeToViewport;

        public final Integer get_placeholderColor$options_release() {
            return this._placeholderColor;
        }

        public final void set_placeholderColor$options_release(Integer num) {
            this._placeholderColor = num;
        }

        public final int get_placeholderRes$options_release() {
            return this._placeholderRes;
        }

        public final void set_placeholderRes$options_release(int i) {
            this._placeholderRes = i;
        }

        public final Drawable get_placeholderDrawable$options_release() {
            return this._placeholderDrawable;
        }

        public final void set_placeholderDrawable$options_release(Drawable drawable) {
            this._placeholderDrawable = drawable;
        }

        public final ScalingUtils.ScaleType get_placeholderScaleType$options_release() {
            return this._placeholderScaleType;
        }

        public final void set_placeholderScaleType$options_release(ScalingUtils.ScaleType scaleType) {
            this._placeholderScaleType = scaleType;
        }

        public final PointF get_placeholderFocusPoint$options_release() {
            return this._placeholderFocusPoint;
        }

        public final void set_placeholderFocusPoint$options_release(PointF pointF) {
            this._placeholderFocusPoint = pointF;
        }

        public final boolean get_placeholderApplyRoundingOptions$options_release() {
            return this._placeholderApplyRoundingOptions;
        }

        public final void set_placeholderApplyRoundingOptions$options_release(boolean z) {
            this._placeholderApplyRoundingOptions = z;
        }

        public final int get_progressRes$options_release() {
            return this._progressRes;
        }

        public final void set_progressRes$options_release(int i) {
            this._progressRes = i;
        }

        public final Drawable get_progressDrawable$options_release() {
            return this._progressDrawable;
        }

        public final void set_progressDrawable$options_release(Drawable drawable) {
            this._progressDrawable = drawable;
        }

        public final ScalingUtils.ScaleType get_progressScaleType$options_release() {
            return this._progressScaleType;
        }

        public final void set_progressScaleType$options_release(ScalingUtils.ScaleType scaleType) {
            this._progressScaleType = scaleType;
        }

        public final Integer get_errorColor$options_release() {
            return this._errorColor;
        }

        public final void set_errorColor$options_release(Integer num) {
            this._errorColor = num;
        }

        public final int get_errorRes$options_release() {
            return this._errorRes;
        }

        public final void set_errorRes$options_release(int i) {
            this._errorRes = i;
        }

        public final ScalingUtils.ScaleType get_errorScaleType$options_release() {
            return this._errorScaleType;
        }

        public final void set_errorScaleType$options_release(ScalingUtils.ScaleType scaleType) {
            this._errorScaleType = scaleType;
        }

        public final PointF get_errorFocusPoint$options_release() {
            return this._errorFocusPoint;
        }

        public final void set_errorFocusPoint$options_release(PointF pointF) {
            this._errorFocusPoint = pointF;
        }

        public final Drawable get_errorDrawable$options_release() {
            return this._errorDrawable;
        }

        public final void set_errorDrawable$options_release(Drawable drawable) {
            this._errorDrawable = drawable;
        }

        public final boolean get_errorApplyRoundingOptions$options_release() {
            return this._errorApplyRoundingOptions;
        }

        public final void set_errorApplyRoundingOptions$options_release(boolean z) {
            this._errorApplyRoundingOptions = z;
        }

        public final ColorFilter get_actualImageColorFilter$options_release() {
            return this._actualImageColorFilter;
        }

        public final void set_actualImageColorFilter$options_release(ColorFilter colorFilter) {
            this._actualImageColorFilter = colorFilter;
        }

        public final int get_overlayRes$options_release() {
            return this._overlayRes;
        }

        public final void set_overlayRes$options_release(int i) {
            this._overlayRes = i;
        }

        public final Drawable get_overlayDrawable$options_release() {
            return this._overlayDrawable;
        }

        public final void set_overlayDrawable$options_release(Drawable drawable) {
            this._overlayDrawable = drawable;
        }

        public final boolean get_resizeToViewport$options_release() {
            return this._resizeToViewport;
        }

        public final void set_resizeToViewport$options_release(boolean z) {
            this._resizeToViewport = z;
        }

        public final boolean get_autoPlay$options_release() {
            return this._autoPlay;
        }

        public final void set_autoPlay$options_release(boolean z) {
            this._autoPlay = z;
        }

        public final boolean get_autoStop$options_release() {
            return this._autoStop;
        }

        public final void set_autoStop$options_release(boolean z) {
            this._autoStop = z;
        }

        public final boolean get_perfMediaRemountInstrumentationFix$options_release() {
            return this._perfMediaRemountInstrumentationFix;
        }

        public final void set_perfMediaRemountInstrumentationFix$options_release(boolean z) {
            this._perfMediaRemountInstrumentationFix = z;
        }

        public final int get_fadeDurationMs$options_release() {
            return this._fadeDurationMs;
        }

        public final void set_fadeDurationMs$options_release(int i) {
            this._fadeDurationMs = i;
        }

        public final ImageOptionsDrawableFactory get_customDrawableFactory$options_release() {
            return this._customDrawableFactory;
        }

        public final void set_customDrawableFactory$options_release(ImageOptionsDrawableFactory imageOptionsDrawableFactory) {
            this._customDrawableFactory = imageOptionsDrawableFactory;
        }

        public final boolean get_experimentalDynamicSize$options_release() {
            return this._experimentalDynamicSize;
        }

        public final void set_experimentalDynamicSize$options_release(boolean z) {
            this._experimentalDynamicSize = z;
        }

        public Builder() {
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Builder(ImageOptions imageOptions) {
            super(imageOptions);
            Intrinsics.checkNotNullParameter(imageOptions, "defaultOptions");
            this._placeholderColor = imageOptions.getPlaceholderColor();
            this._placeholderRes = imageOptions.getPlaceholderRes();
            this._placeholderDrawable = imageOptions.getPlaceholderDrawable();
            this._placeholderScaleType = imageOptions.getPlaceholderScaleType();
            this._placeholderFocusPoint = imageOptions.getPlaceholderFocusPoint();
            this._placeholderApplyRoundingOptions = imageOptions.getPlaceholderApplyRoundingOptions();
            this._progressRes = imageOptions.getProgressRes();
            this._progressDrawable = imageOptions.getProgressDrawable();
            this._progressScaleType = imageOptions.getProgressScaleType();
            this._errorColor = imageOptions.getErrorColor();
            this._errorRes = imageOptions.getErrorRes();
            this._errorScaleType = imageOptions.getErrorScaleType();
            this._errorFocusPoint = imageOptions.getErrorFocusPoint();
            this._errorDrawable = imageOptions.getErrorDrawable();
            this._errorApplyRoundingOptions = imageOptions.getErrorApplyRoundingOptions();
            this._actualImageColorFilter = imageOptions.getActualImageColorFilter();
            this._overlayRes = imageOptions.getOverlayRes();
            this._overlayDrawable = imageOptions.getOverlayDrawable();
            this._resizeToViewport = imageOptions.shouldResizeToViewport();
            this._autoPlay = imageOptions.shouldAutoPlay();
            this._autoStop = imageOptions.shouldAutoStop();
            this._fadeDurationMs = imageOptions.getFadeDurationMs();
            this._customDrawableFactory = imageOptions.getCustomDrawableFactory();
            this._experimentalDynamicSize = imageOptions.getExperimentalDynamicSize();
        }

        public ImageOptions build() {
            return new ImageOptions(this);
        }

        private final Builder modify(Function1<? super Builder, Unit> function1) {
            function1.invoke(this);
            return this;
        }

        public final Builder placeholder(Drawable drawable) {
            Builder builder = this;
            this._placeholderDrawable = drawable;
            this._placeholderColor = null;
            this._placeholderRes = 0;
            return this;
        }

        public final Builder placeholder(Drawable drawable, ScalingUtils.ScaleType scaleType) {
            Builder builder = this;
            this._placeholderDrawable = drawable;
            this._placeholderScaleType = scaleType;
            this._placeholderColor = null;
            this._placeholderRes = 0;
            return this;
        }

        public final Builder placeholderColor(int i) {
            Builder builder = this;
            this._placeholderColor = Integer.valueOf(i);
            this._placeholderRes = 0;
            this._placeholderDrawable = null;
            return this;
        }

        public final Builder placeholderRes(int i) {
            Builder builder = this;
            this._placeholderRes = i;
            this._placeholderColor = null;
            this._placeholderDrawable = null;
            return this;
        }

        public final Builder placeholderRes(int i, ScalingUtils.ScaleType scaleType) {
            Builder builder = this;
            this._placeholderRes = i;
            this._placeholderScaleType = scaleType;
            this._placeholderColor = null;
            this._placeholderDrawable = null;
            return this;
        }

        public final Builder placeholderScaleType(ScalingUtils.ScaleType scaleType) {
            Builder builder = this;
            this._placeholderScaleType = scaleType;
            return this;
        }

        public final Builder placeholderFocusPoint(PointF pointF) {
            Builder builder = this;
            this._placeholderFocusPoint = pointF;
            return this;
        }

        public final Builder placeholderApplyRoundingOptions(boolean z) {
            Builder builder = this;
            this._placeholderApplyRoundingOptions = z;
            return this;
        }

        public final Builder errorColor(int i) {
            Builder builder = this;
            this._errorColor = Integer.valueOf(i);
            this._errorRes = 0;
            this._errorDrawable = null;
            return this;
        }

        public final Builder errorRes(int i) {
            Builder builder = this;
            this._errorColor = null;
            this._errorRes = i;
            this._errorDrawable = null;
            return this;
        }

        public final Builder errorScaleType(ScalingUtils.ScaleType scaleType) {
            Builder builder = this;
            this._errorScaleType = scaleType;
            return this;
        }

        public final Builder errorFocusPoint(PointF pointF) {
            Builder builder = this;
            this._errorFocusPoint = pointF;
            return this;
        }

        public final Builder errorDrawable(Drawable drawable) {
            Builder builder = this;
            this._errorColor = null;
            this._errorRes = 0;
            this._errorDrawable = drawable;
            return this;
        }

        public final Builder errorApplyRoundingOptions(boolean z) {
            Builder builder = this;
            this._errorApplyRoundingOptions = z;
            return this;
        }

        public final Builder progress(Drawable drawable) {
            Builder builder = this;
            this._progressDrawable = drawable;
            return this;
        }

        public final Builder progress(Drawable drawable, ScalingUtils.ScaleType scaleType) {
            Builder builder = this;
            this._progressDrawable = drawable;
            this._progressScaleType = scaleType;
            return this;
        }

        public final Builder progressRes(int i) {
            Builder builder = this;
            this._progressRes = i;
            return this;
        }

        public final Builder progressRes(int i, ScalingUtils.ScaleType scaleType) {
            Builder builder = this;
            this._progressRes = i;
            this._progressScaleType = scaleType;
            return this;
        }

        public final Builder progressScaleType(ScalingUtils.ScaleType scaleType) {
            Builder builder = this;
            this._progressScaleType = scaleType;
            return this;
        }

        public final Builder overlayRes(int i) {
            Builder builder = this;
            this._overlayRes = i;
            this._overlayDrawable = null;
            return this;
        }

        public final Builder overlay(Drawable drawable) {
            Builder builder = this;
            this._overlayDrawable = drawable;
            this._overlayRes = 0;
            return this;
        }

        public final Builder colorFilter(ColorFilter colorFilter) {
            Builder builder = this;
            this._actualImageColorFilter = colorFilter;
            return this;
        }

        public final Builder autoPlay(boolean z) {
            Builder builder = this;
            this._autoPlay = z;
            return this;
        }

        public final Builder autoStop(boolean z) {
            Builder builder = this;
            this._autoStop = z;
            return this;
        }

        public final Builder perfMediaRemountInstrumentationFix(boolean z) {
            Builder builder = this;
            this._perfMediaRemountInstrumentationFix = z;
            return this;
        }

        public final Builder resizeToViewport(boolean z) {
            Builder builder = this;
            this._resizeToViewport = z;
            return this;
        }

        public final Builder fadeDurationMs(int i) {
            Builder builder = this;
            this._fadeDurationMs = i;
            return this;
        }

        public final Builder customDrawableFactory(ImageOptionsDrawableFactory imageOptionsDrawableFactory) {
            Builder builder = this;
            this._customDrawableFactory = imageOptionsDrawableFactory;
            return this;
        }

        public final Builder experimentalDynamicSize(boolean z) {
            Builder builder = this;
            this._experimentalDynamicSize = z;
            return this;
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0007J\b\u0010\u0007\u001a\u00020\u0004H\u0007J\u0010\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0004H\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/facebook/fresco/vito/options/ImageOptions$Companion;", "", "()V", "defaultImageOptions", "Lcom/facebook/fresco/vito/options/ImageOptions;", "create", "Lcom/facebook/fresco/vito/options/ImageOptions$Builder;", "defaults", "extend", "imageOptions", "setDefaults", "", "options_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ImageOptions.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final ImageOptions defaults() {
            return ImageOptions.defaultImageOptions;
        }

        @JvmStatic
        public final void setDefaults(ImageOptions imageOptions) {
            Intrinsics.checkNotNullParameter(imageOptions, "imageOptions");
            ImageOptions.defaultImageOptions = imageOptions;
        }

        @JvmStatic
        public final Builder extend(ImageOptions imageOptions) {
            Intrinsics.checkNotNullParameter(imageOptions, "imageOptions");
            return new Builder(imageOptions);
        }

        @JvmStatic
        public final Builder create() {
            return extend(defaults());
        }
    }
}

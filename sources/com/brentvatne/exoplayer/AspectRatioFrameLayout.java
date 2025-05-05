package com.brentvatne.exoplayer;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import androidx.media3.common.Format;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0012\u001a\u00020\u0013J\u0018\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0006H\u0014J\u000e\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0019R$\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR$\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u001b"}, d2 = {"Lcom/brentvatne/exoplayer/AspectRatioFrameLayout;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "value", "", "resizeMode", "getResizeMode", "()I", "setResizeMode", "(I)V", "", "videoAspectRatio", "getVideoAspectRatio", "()F", "setVideoAspectRatio", "(F)V", "invalidateAspectRatio", "", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "updateAspectRatio", "format", "Landroidx/media3/common/Format;", "Companion", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AspectRatioFrameLayout.kt */
public final class AspectRatioFrameLayout extends FrameLayout {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final float MAX_ASPECT_RATIO_DEFORMATION_FRACTION = 0.01f;
    private int resizeMode;
    private float videoAspectRatio;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AspectRatioFrameLayout(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/brentvatne/exoplayer/AspectRatioFrameLayout$Companion;", "", "()V", "MAX_ASPECT_RATIO_DEFORMATION_FRACTION", "", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: AspectRatioFrameLayout.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final float getVideoAspectRatio() {
        return this.videoAspectRatio;
    }

    public final void setVideoAspectRatio(float f) {
        if (!(f == this.videoAspectRatio)) {
            this.videoAspectRatio = f;
            requestLayout();
        }
    }

    public final int getResizeMode() {
        return this.resizeMode;
    }

    public final void setResizeMode(int i) {
        if (i != this.resizeMode) {
            this.resizeMode = i;
            requestLayout();
        }
    }

    public final void invalidateAspectRatio() {
        setVideoAspectRatio(0.0f);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        float f;
        float f2;
        float f3;
        super.onMeasure(i, i2);
        if (!(this.videoAspectRatio == 0.0f)) {
            int measuredWidth = getMeasuredWidth();
            int measuredHeight = getMeasuredHeight();
            float f4 = (float) measuredWidth;
            float f5 = (float) measuredHeight;
            float f6 = (this.videoAspectRatio / (f4 / f5)) - ((float) 1);
            if (Math.abs(f6) > MAX_ASPECT_RATIO_DEFORMATION_FRACTION) {
                int i3 = this.resizeMode;
                if (i3 != 1) {
                    if (i3 != 2) {
                        if (i3 != 3) {
                            if (i3 == 4) {
                                int i4 = (int) (this.videoAspectRatio * f5);
                                if (i4 < measuredWidth) {
                                    float f7 = (float) i4;
                                    float f8 = f4 / f7;
                                    measuredWidth = (int) (f7 * f8);
                                    f = f8 * f5;
                                    measuredHeight = (int) f;
                                } else {
                                    measuredWidth = i4;
                                }
                            } else if (f6 > 0.0f) {
                                f2 = this.videoAspectRatio;
                            } else {
                                f3 = this.videoAspectRatio;
                            }
                        }
                        super.onMeasure(View.MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824));
                    }
                    f3 = this.videoAspectRatio;
                    measuredWidth = (int) (f5 * f3);
                    super.onMeasure(View.MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824));
                }
                f2 = this.videoAspectRatio;
                f = f4 / f2;
                measuredHeight = (int) f;
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824));
            }
        }
    }

    public final void updateAspectRatio(Format format) {
        Intrinsics.checkNotNullParameter(format, "format");
        int i = format.rotationDegrees;
        float f = 1.0f;
        if (i == 90 || i == 270) {
            if (format.width != 0) {
                f = (((float) format.height) * format.pixelWidthHeightRatio) / ((float) format.width);
            }
            setVideoAspectRatio(f);
            return;
        }
        if (format.height != 0) {
            f = (((float) format.width) * format.pixelWidthHeightRatio) / ((float) format.height);
        }
        setVideoAspectRatio(f);
    }
}

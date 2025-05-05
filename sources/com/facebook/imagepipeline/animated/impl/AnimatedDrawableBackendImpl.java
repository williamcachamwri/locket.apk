package com.facebook.imagepipeline.animated.impl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableBackend;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableFrameInfo;
import com.facebook.imagepipeline.animated.base.AnimatedImage;
import com.facebook.imagepipeline.animated.base.AnimatedImageFrame;
import com.facebook.imagepipeline.animated.base.AnimatedImageResult;
import com.facebook.imagepipeline.animated.util.AnimatedDrawableUtil;
import javax.annotation.Nullable;

public class AnimatedDrawableBackendImpl implements AnimatedDrawableBackend {
    private final AnimatedDrawableUtil mAnimatedDrawableUtil;
    private final AnimatedImage mAnimatedImage;
    private final AnimatedImageResult mAnimatedImageResult;
    private final boolean mDownscaleFrameToDrawableDimensions;
    private final int mDurationMs;
    private final int[] mFrameDurationsMs;
    private final AnimatedDrawableFrameInfo[] mFrameInfos;
    private final int[] mFrameTimestampsMs;
    private final Rect mRenderDstRect = new Rect();
    private final Rect mRenderSrcRect = new Rect();
    private final Rect mRenderedBounds;
    @Nullable
    private Bitmap mTempBitmap;
    private final Paint mTransparentPaint;

    public AnimatedDrawableBackendImpl(AnimatedDrawableUtil animatedDrawableUtil, AnimatedImageResult animatedImageResult, @Nullable Rect rect, boolean z) {
        this.mAnimatedDrawableUtil = animatedDrawableUtil;
        this.mAnimatedImageResult = animatedImageResult;
        AnimatedImage image = animatedImageResult.getImage();
        this.mAnimatedImage = image;
        int[] frameDurations = image.getFrameDurations();
        this.mFrameDurationsMs = frameDurations;
        animatedDrawableUtil.fixFrameDurations(frameDurations);
        this.mDurationMs = animatedDrawableUtil.getTotalDurationFromFrameDurations(frameDurations);
        this.mFrameTimestampsMs = animatedDrawableUtil.getFrameTimeStampsFromDurations(frameDurations);
        this.mRenderedBounds = getBoundsToUse(image, rect);
        this.mDownscaleFrameToDrawableDimensions = z;
        this.mFrameInfos = new AnimatedDrawableFrameInfo[image.getFrameCount()];
        for (int i = 0; i < this.mAnimatedImage.getFrameCount(); i++) {
            this.mFrameInfos[i] = this.mAnimatedImage.getFrameInfo(i);
        }
        Paint paint = new Paint();
        this.mTransparentPaint = paint;
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    private static Rect getBoundsToUse(AnimatedImage animatedImage, @Nullable Rect rect) {
        if (rect == null) {
            return new Rect(0, 0, animatedImage.getWidth(), animatedImage.getHeight());
        }
        return new Rect(0, 0, Math.min(rect.width(), animatedImage.getWidth()), Math.min(rect.height(), animatedImage.getHeight()));
    }

    public AnimatedImageResult getAnimatedImageResult() {
        return this.mAnimatedImageResult;
    }

    public int getDurationMs() {
        return this.mDurationMs;
    }

    public int getFrameCount() {
        return this.mAnimatedImage.getFrameCount();
    }

    public int getLoopCount() {
        return this.mAnimatedImage.getLoopCount();
    }

    public int getWidth() {
        return this.mAnimatedImage.getWidth();
    }

    public int getHeight() {
        return this.mAnimatedImage.getHeight();
    }

    public int getRenderedWidth() {
        return this.mRenderedBounds.width();
    }

    public int getRenderedHeight() {
        return this.mRenderedBounds.height();
    }

    public AnimatedDrawableFrameInfo getFrameInfo(int i) {
        return this.mFrameInfos[i];
    }

    public int getFrameForTimestampMs(int i) {
        return this.mAnimatedDrawableUtil.getFrameForTimestampMs(this.mFrameTimestampsMs, i);
    }

    public int getTimestampMsForFrame(int i) {
        Preconditions.checkElementIndex(i, this.mFrameTimestampsMs.length);
        return this.mFrameTimestampsMs[i];
    }

    public int getDurationMsForFrame(int i) {
        return this.mFrameDurationsMs[i];
    }

    public int getFrameForPreview() {
        return this.mAnimatedImageResult.getFrameForPreview();
    }

    public AnimatedDrawableBackend forNewBounds(@Nullable Rect rect) {
        if (getBoundsToUse(this.mAnimatedImage, rect).equals(this.mRenderedBounds)) {
            return this;
        }
        return new AnimatedDrawableBackendImpl(this.mAnimatedDrawableUtil, this.mAnimatedImageResult, rect, this.mDownscaleFrameToDrawableDimensions);
    }

    public synchronized int getMemoryUsage() {
        int i;
        Bitmap bitmap = this.mTempBitmap;
        i = 0;
        if (bitmap != null) {
            i = 0 + this.mAnimatedDrawableUtil.getSizeOfBitmap(bitmap);
        }
        return i + this.mAnimatedImage.getSizeInBytes();
    }

    @Nullable
    public CloseableReference<Bitmap> getPreDecodedFrame(int i) {
        return this.mAnimatedImageResult.getDecodedFrame(i);
    }

    public boolean hasPreDecodedFrame(int i) {
        return this.mAnimatedImageResult.hasDecodedFrame(i);
    }

    public void renderFrame(int i, Canvas canvas) {
        AnimatedImageFrame frame = this.mAnimatedImage.getFrame(i);
        try {
            if (frame.getWidth() > 0) {
                if (frame.getHeight() > 0) {
                    if (this.mAnimatedImage.doesRenderSupportScaling()) {
                        renderImageSupportsScaling(canvas, frame);
                    } else {
                        renderImageDoesNotSupportScaling(canvas, frame);
                    }
                    frame.dispose();
                }
            }
        } finally {
            frame.dispose();
        }
    }

    public void renderDeltas(int i, Canvas canvas) {
        AnimatedDrawableFrameInfo animatedDrawableFrameInfo;
        AnimatedImageFrame frame = this.mAnimatedImage.getFrame(i);
        AnimatedDrawableFrameInfo frameInfo = this.mAnimatedImage.getFrameInfo(i);
        if (i == 0) {
            animatedDrawableFrameInfo = null;
        } else {
            animatedDrawableFrameInfo = this.mAnimatedImage.getFrameInfo(i - 1);
        }
        try {
            if (frame.getWidth() > 0) {
                if (frame.getHeight() > 0) {
                    if (this.mAnimatedImage.doesRenderSupportScaling()) {
                        renderScalingFrames(canvas, frame, frameInfo, animatedDrawableFrameInfo);
                    } else {
                        renderNonScalingFrames(canvas, frame, frameInfo, animatedDrawableFrameInfo);
                    }
                    frame.dispose();
                }
            }
        } finally {
            frame.dispose();
        }
    }

    private synchronized Bitmap prepareTempBitmapForThisSize(int i, int i2) {
        Bitmap bitmap = this.mTempBitmap;
        if (bitmap != null && (bitmap.getWidth() < i || this.mTempBitmap.getHeight() < i2)) {
            clearTempBitmap();
        }
        if (this.mTempBitmap == null) {
            this.mTempBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        }
        this.mTempBitmap.eraseColor(0);
        return this.mTempBitmap;
    }

    private void renderImageSupportsScaling(Canvas canvas, AnimatedImageFrame animatedImageFrame) {
        double width = ((double) this.mRenderedBounds.width()) / ((double) this.mAnimatedImage.getWidth());
        double height = ((double) this.mRenderedBounds.height()) / ((double) this.mAnimatedImage.getHeight());
        int round = (int) Math.round(((double) animatedImageFrame.getWidth()) * width);
        int round2 = (int) Math.round(((double) animatedImageFrame.getHeight()) * height);
        int xOffset = (int) (((double) animatedImageFrame.getXOffset()) * width);
        int yOffset = (int) (((double) animatedImageFrame.getYOffset()) * height);
        synchronized (this) {
            int width2 = this.mRenderedBounds.width();
            int height2 = this.mRenderedBounds.height();
            prepareTempBitmapForThisSize(width2, height2);
            Bitmap bitmap = this.mTempBitmap;
            if (bitmap != null) {
                animatedImageFrame.renderFrame(round, round2, bitmap);
            }
            this.mRenderSrcRect.set(0, 0, width2, height2);
            this.mRenderDstRect.set(xOffset, yOffset, width2 + xOffset, height2 + yOffset);
            Bitmap bitmap2 = this.mTempBitmap;
            if (bitmap2 != null) {
                canvas.drawBitmap(bitmap2, this.mRenderSrcRect, this.mRenderDstRect, (Paint) null);
            }
        }
    }

    private void renderScalingFrames(Canvas canvas, AnimatedImageFrame animatedImageFrame, AnimatedDrawableFrameInfo animatedDrawableFrameInfo, @Nullable AnimatedDrawableFrameInfo animatedDrawableFrameInfo2) {
        float f;
        float f2;
        float f3;
        float f4;
        int width = this.mAnimatedImage.getWidth();
        int height = this.mAnimatedImage.getHeight();
        float f5 = (float) width;
        float f6 = (float) height;
        int width2 = animatedImageFrame.getWidth();
        int height2 = animatedImageFrame.getHeight();
        int xOffset = animatedImageFrame.getXOffset();
        int yOffset = animatedImageFrame.getYOffset();
        if (f5 > ((float) canvas.getWidth()) || f6 > ((float) canvas.getHeight())) {
            int min = Math.min(canvas.getWidth(), width);
            int min2 = Math.min(canvas.getHeight(), height);
            float f7 = f5 / f6;
            if (min > min2) {
                f4 = (float) min;
                f3 = f4 / f7;
            } else {
                f3 = (float) min2;
                f4 = f3 * f7;
            }
            f2 = f4 / f5;
            f = f3 / f6;
            width2 = (int) Math.ceil((double) (((float) animatedImageFrame.getWidth()) * f2));
            height2 = (int) Math.ceil((double) (((float) animatedImageFrame.getHeight()) * f));
            xOffset = (int) Math.ceil((double) (((float) animatedImageFrame.getXOffset()) * f2));
            yOffset = (int) Math.ceil((double) (((float) animatedImageFrame.getYOffset()) * f));
        } else {
            f2 = 1.0f;
            f = 1.0f;
        }
        Rect rect = new Rect(0, 0, width2, height2);
        Rect rect2 = new Rect(xOffset, yOffset, xOffset + width2, yOffset + height2);
        if (animatedDrawableFrameInfo2 != null) {
            maybeDisposeBackground(canvas, f2, f, animatedDrawableFrameInfo2);
        }
        if (animatedDrawableFrameInfo.blendOperation == AnimatedDrawableFrameInfo.BlendOperation.NO_BLEND) {
            canvas.drawRect(rect2, this.mTransparentPaint);
        }
        synchronized (this) {
            Bitmap prepareTempBitmapForThisSize = prepareTempBitmapForThisSize(width2, height2);
            animatedImageFrame.renderFrame(width2, height2, prepareTempBitmapForThisSize);
            canvas.drawBitmap(prepareTempBitmapForThisSize, rect, rect2, (Paint) null);
        }
    }

    private void maybeDisposeBackground(Canvas canvas, float f, float f2, AnimatedDrawableFrameInfo animatedDrawableFrameInfo) {
        if (animatedDrawableFrameInfo.disposalMethod == AnimatedDrawableFrameInfo.DisposalMethod.DISPOSE_TO_BACKGROUND) {
            int ceil = (int) Math.ceil((double) (((float) animatedDrawableFrameInfo.xOffset) * f));
            int ceil2 = (int) Math.ceil((double) (((float) animatedDrawableFrameInfo.yOffset) * f2));
            canvas.drawRect(new Rect(ceil, ceil2, ((int) Math.ceil((double) (((float) animatedDrawableFrameInfo.width) * f))) + ceil, ((int) Math.ceil((double) (((float) animatedDrawableFrameInfo.height) * f2))) + ceil2), this.mTransparentPaint);
        }
    }

    private void renderImageDoesNotSupportScaling(Canvas canvas, AnimatedImageFrame animatedImageFrame) {
        int i;
        int i2;
        int i3;
        int i4;
        if (this.mDownscaleFrameToDrawableDimensions) {
            float max = Math.max(((float) animatedImageFrame.getWidth()) / ((float) Math.min(animatedImageFrame.getWidth(), canvas.getWidth())), ((float) animatedImageFrame.getHeight()) / ((float) Math.min(animatedImageFrame.getHeight(), canvas.getHeight())));
            i3 = (int) (((float) animatedImageFrame.getWidth()) / max);
            i2 = (int) (((float) animatedImageFrame.getHeight()) / max);
            i = (int) (((float) animatedImageFrame.getXOffset()) / max);
            i4 = (int) (((float) animatedImageFrame.getYOffset()) / max);
        } else {
            i3 = animatedImageFrame.getWidth();
            i2 = animatedImageFrame.getHeight();
            i = animatedImageFrame.getXOffset();
            i4 = animatedImageFrame.getYOffset();
        }
        synchronized (this) {
            Bitmap prepareTempBitmapForThisSize = prepareTempBitmapForThisSize(i3, i2);
            this.mTempBitmap = prepareTempBitmapForThisSize;
            animatedImageFrame.renderFrame(i3, i2, prepareTempBitmapForThisSize);
            canvas.save();
            canvas.translate((float) i, (float) i4);
            canvas.drawBitmap(this.mTempBitmap, 0.0f, 0.0f, (Paint) null);
            canvas.restore();
        }
    }

    private void renderNonScalingFrames(Canvas canvas, AnimatedImageFrame animatedImageFrame, AnimatedDrawableFrameInfo animatedDrawableFrameInfo, @Nullable AnimatedDrawableFrameInfo animatedDrawableFrameInfo2) {
        Rect rect = this.mRenderedBounds;
        if (rect != null && rect.width() > 0 && this.mRenderedBounds.height() > 0) {
            float width = ((float) canvas.getWidth()) / ((float) this.mRenderedBounds.width());
            if (animatedDrawableFrameInfo2 != null) {
                maybeDisposeBackground(canvas, width, width, animatedDrawableFrameInfo2);
            }
            int width2 = animatedImageFrame.getWidth();
            int height = animatedImageFrame.getHeight();
            Rect rect2 = new Rect(0, 0, width2, height);
            int xOffset = (int) (((float) animatedImageFrame.getXOffset()) * width);
            int yOffset = (int) (((float) animatedImageFrame.getYOffset()) * width);
            Rect rect3 = new Rect(xOffset, yOffset, ((int) (((float) width2) * width)) + xOffset, ((int) (((float) height) * width)) + yOffset);
            if (animatedDrawableFrameInfo.blendOperation == AnimatedDrawableFrameInfo.BlendOperation.NO_BLEND) {
                canvas.drawRect(rect3, this.mTransparentPaint);
            }
            synchronized (this) {
                Bitmap prepareTempBitmapForThisSize = prepareTempBitmapForThisSize(width2, height);
                animatedImageFrame.renderFrame(width2, height, prepareTempBitmapForThisSize);
                canvas.drawBitmap(prepareTempBitmapForThisSize, rect2, rect3, (Paint) null);
            }
        }
    }

    public synchronized void dropCaches() {
        clearTempBitmap();
    }

    private synchronized void clearTempBitmap() {
        Bitmap bitmap = this.mTempBitmap;
        if (bitmap != null) {
            bitmap.recycle();
            this.mTempBitmap = null;
        }
    }
}

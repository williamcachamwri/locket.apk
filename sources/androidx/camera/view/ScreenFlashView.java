package androidx.camera.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.view.internal.ScreenFlashUiInfo;
import java.util.Objects;

public final class ScreenFlashView extends View {
    private static final long ANIMATION_DURATION_MILLIS = 1000;
    private static final String TAG = "ScreenFlashView";
    private CameraController mCameraController;
    private ImageCapture.ScreenFlash mScreenFlash;
    private Window mScreenFlashWindow;

    public long getVisibilityRampUpAnimationDurationMillis() {
        return 1000;
    }

    public ScreenFlashView(Context context) {
        this(context, (AttributeSet) null);
    }

    public ScreenFlashView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ScreenFlashView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public ScreenFlashView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        setBackgroundColor(-1);
        setAlpha(0.0f);
        setElevation(Float.MAX_VALUE);
    }

    public void setController(CameraController cameraController) {
        Threads.checkMainThread();
        CameraController cameraController2 = this.mCameraController;
        if (!(cameraController2 == null || cameraController2 == cameraController)) {
            setScreenFlashUiInfo((ImageCapture.ScreenFlash) null);
        }
        this.mCameraController = cameraController;
        if (cameraController != null) {
            if (cameraController.getImageCaptureFlashMode() == 3 && this.mScreenFlashWindow == null) {
                throw new IllegalStateException("No window set despite setting FLASH_MODE_SCREEN in CameraController");
            }
            setScreenFlashUiInfo(getScreenFlash());
        }
    }

    private void setScreenFlashUiInfo(ImageCapture.ScreenFlash screenFlash) {
        CameraController cameraController = this.mCameraController;
        if (cameraController == null) {
            Logger.d(TAG, "setScreenFlashUiInfo: mCameraController is null!");
        } else {
            cameraController.setScreenFlashUiInfo(new ScreenFlashUiInfo(ScreenFlashUiInfo.ProviderType.SCREEN_FLASH_VIEW, screenFlash));
        }
    }

    public void setScreenFlashWindow(Window window) {
        Threads.checkMainThread();
        updateScreenFlash(window);
        this.mScreenFlashWindow = window;
        setScreenFlashUiInfo(getScreenFlash());
    }

    private void updateScreenFlash(Window window) {
        AnonymousClass1 r2;
        if (this.mScreenFlashWindow != window) {
            if (window == null) {
                r2 = null;
            } else {
                r2 = new ImageCapture.ScreenFlash() {
                    private ValueAnimator mAnimator;
                    private float mPreviousBrightness;

                    public void apply(long j, ImageCapture.ScreenFlashListener screenFlashListener) {
                        Logger.d(ScreenFlashView.TAG, "ScreenFlash#apply");
                        this.mPreviousBrightness = ScreenFlashView.this.getBrightness();
                        ScreenFlashView.this.setBrightness(1.0f);
                        ValueAnimator valueAnimator = this.mAnimator;
                        if (valueAnimator != null) {
                            valueAnimator.cancel();
                        }
                        ScreenFlashView screenFlashView = ScreenFlashView.this;
                        Objects.requireNonNull(screenFlashListener);
                        this.mAnimator = screenFlashView.animateToFullOpacity(new ScreenFlashView$1$$ExternalSyntheticLambda0(screenFlashListener));
                    }

                    public void clear() {
                        Logger.d(ScreenFlashView.TAG, "ScreenFlash#clearScreenFlashUi");
                        ValueAnimator valueAnimator = this.mAnimator;
                        if (valueAnimator != null) {
                            valueAnimator.cancel();
                            this.mAnimator = null;
                        }
                        ScreenFlashView.this.setAlpha(0.0f);
                        ScreenFlashView.this.setBrightness(this.mPreviousBrightness);
                    }
                };
            }
            this.mScreenFlash = r2;
        }
    }

    /* access modifiers changed from: private */
    public ValueAnimator animateToFullOpacity(final Runnable runnable) {
        Logger.d(TAG, "animateToFullOpacity");
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ofFloat.setDuration(getVisibilityRampUpAnimationDurationMillis());
        ofFloat.addUpdateListener(new ScreenFlashView$$ExternalSyntheticLambda0(this));
        ofFloat.addListener(new Animator.AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                Logger.d(ScreenFlashView.TAG, "ScreenFlash#apply: onAnimationEnd");
                Runnable runnable = runnable;
                if (runnable != null) {
                    runnable.run();
                }
            }
        });
        ofFloat.start();
        return ofFloat;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$animateToFullOpacity$0$androidx-camera-view-ScreenFlashView  reason: not valid java name */
    public /* synthetic */ void m321lambda$animateToFullOpacity$0$androidxcameraviewScreenFlashView(ValueAnimator valueAnimator) {
        Logger.d(TAG, "animateToFullOpacity: value = " + ((Float) valueAnimator.getAnimatedValue()).floatValue());
        setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }

    /* access modifiers changed from: private */
    public float getBrightness() {
        Window window = this.mScreenFlashWindow;
        if (window != null) {
            return window.getAttributes().screenBrightness;
        }
        Logger.e(TAG, "setBrightness: mScreenFlashWindow is null!");
        return Float.NaN;
    }

    /* access modifiers changed from: private */
    public void setBrightness(float f) {
        if (this.mScreenFlashWindow == null) {
            Logger.e(TAG, "setBrightness: mScreenFlashWindow is null!");
        } else if (Float.isNaN(f)) {
            Logger.e(TAG, "setBrightness: value is NaN!");
        } else {
            WindowManager.LayoutParams attributes = this.mScreenFlashWindow.getAttributes();
            attributes.screenBrightness = f;
            this.mScreenFlashWindow.setAttributes(attributes);
            Logger.d(TAG, "Brightness set to " + attributes.screenBrightness);
        }
    }

    public ImageCapture.ScreenFlash getScreenFlash() {
        return this.mScreenFlash;
    }
}

package androidx.camera.core.internal;

import androidx.camera.core.ImageCapture;
import androidx.camera.core.Logger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\b\b\u0000\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0011\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u0003J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tH\u0016J\b\u0010\u000f\u001a\u00020\u000bH\u0016J\b\u0010\u0010\u001a\u00020\u000bH\u0002J\b\u0010\u0011\u001a\u00020\u000bH\u0002J\u0006\u0010\u0012\u001a\u00020\u000bJ\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001R\u0012\u0010\u0004\u001a\u00020\u00058\u0002@\u0002X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Landroidx/camera/core/internal/ScreenFlashWrapper;", "Landroidx/camera/core/ImageCapture$ScreenFlash;", "screenFlash", "(Landroidx/camera/core/ImageCapture$ScreenFlash;)V", "isClearScreenFlashPending", "", "lock", "Ljava/lang/Object;", "pendingListener", "Landroidx/camera/core/ImageCapture$ScreenFlashListener;", "apply", "", "expirationTimeMillis", "", "screenFlashListener", "clear", "completePendingScreenFlashClear", "completePendingScreenFlashListener", "completePendingTasks", "getBaseScreenFlash", "Companion", "camera-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ScreenFlashWrapper.kt */
public final class ScreenFlashWrapper implements ImageCapture.ScreenFlash {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "ScreenFlashWrapper";
    private boolean isClearScreenFlashPending;
    private final Object lock;
    private ImageCapture.ScreenFlashListener pendingListener;
    private final ImageCapture.ScreenFlash screenFlash;

    public /* synthetic */ ScreenFlashWrapper(ImageCapture.ScreenFlash screenFlash2, DefaultConstructorMarker defaultConstructorMarker) {
        this(screenFlash2);
    }

    @JvmStatic
    public static final ScreenFlashWrapper from(ImageCapture.ScreenFlash screenFlash2) {
        return Companion.from(screenFlash2);
    }

    private ScreenFlashWrapper(ImageCapture.ScreenFlash screenFlash2) {
        this.screenFlash = screenFlash2;
        this.lock = new Object();
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Landroidx/camera/core/internal/ScreenFlashWrapper$Companion;", "", "()V", "TAG", "", "from", "Landroidx/camera/core/internal/ScreenFlashWrapper;", "screenFlash", "Landroidx/camera/core/ImageCapture$ScreenFlash;", "camera-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ScreenFlashWrapper.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final ScreenFlashWrapper from(ImageCapture.ScreenFlash screenFlash) {
            return new ScreenFlashWrapper(screenFlash, (DefaultConstructorMarker) null);
        }
    }

    public void apply(long j, ImageCapture.ScreenFlashListener screenFlashListener) {
        Unit unit;
        Intrinsics.checkNotNullParameter(screenFlashListener, "screenFlashListener");
        synchronized (this.lock) {
            this.isClearScreenFlashPending = true;
            this.pendingListener = screenFlashListener;
            Unit unit2 = Unit.INSTANCE;
        }
        ImageCapture.ScreenFlash screenFlash2 = this.screenFlash;
        if (screenFlash2 != null) {
            screenFlash2.apply(j, new ScreenFlashWrapper$$ExternalSyntheticLambda0(this));
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            ScreenFlashWrapper screenFlashWrapper = this;
            Logger.e(TAG, "apply: screenFlash is null!");
            completePendingScreenFlashListener();
        }
    }

    /* access modifiers changed from: private */
    public static final void apply$lambda$2(ScreenFlashWrapper screenFlashWrapper) {
        Intrinsics.checkNotNullParameter(screenFlashWrapper, "this$0");
        synchronized (screenFlashWrapper.lock) {
            if (screenFlashWrapper.pendingListener == null) {
                Logger.w(TAG, "apply: pendingListener is null!");
            }
            screenFlashWrapper.completePendingScreenFlashListener();
            Unit unit = Unit.INSTANCE;
        }
    }

    public void clear() {
        completePendingScreenFlashClear();
    }

    public final ImageCapture.ScreenFlash getBaseScreenFlash() {
        return this.screenFlash;
    }

    private final void completePendingScreenFlashListener() {
        synchronized (this.lock) {
            ImageCapture.ScreenFlashListener screenFlashListener = this.pendingListener;
            if (screenFlashListener != null) {
                screenFlashListener.onCompleted();
            }
            this.pendingListener = null;
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void completePendingScreenFlashClear() {
        Unit unit;
        synchronized (this.lock) {
            if (this.isClearScreenFlashPending) {
                ImageCapture.ScreenFlash screenFlash2 = this.screenFlash;
                if (screenFlash2 != null) {
                    screenFlash2.clear();
                    unit = Unit.INSTANCE;
                } else {
                    unit = null;
                }
                if (unit == null) {
                    ScreenFlashWrapper screenFlashWrapper = this;
                    Logger.e(TAG, "completePendingScreenFlashClear: screenFlash is null!");
                }
            } else {
                Logger.w(TAG, "completePendingScreenFlashClear: none pending!");
            }
            this.isClearScreenFlashPending = false;
            Unit unit2 = Unit.INSTANCE;
        }
    }

    public final void completePendingTasks() {
        completePendingScreenFlashListener();
        completePendingScreenFlashClear();
    }
}

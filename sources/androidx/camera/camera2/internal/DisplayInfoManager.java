package androidx.camera.camera2.internal;

import android.content.Context;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.util.Size;
import android.view.Display;
import androidx.camera.camera2.internal.compat.workaround.DisplaySizeCorrector;
import androidx.camera.camera2.internal.compat.workaround.MaxPreviewSize;
import androidx.camera.core.internal.utils.SizeUtil;
import androidx.media3.extractor.ts.PsExtractor;

public class DisplayInfoManager {
    private static final Size ABNORMAL_DISPLAY_SIZE_THRESHOLD = new Size(320, PsExtractor.VIDEO_STREAM_MASK);
    private static final Size FALLBACK_DISPLAY_SIZE = new Size(640, 480);
    private static final Object INSTANCE_LOCK = new Object();
    private static final Size MAX_PREVIEW_SIZE = new Size(1920, 1080);
    private static volatile DisplayInfoManager sInstance;
    private final DisplayManager mDisplayManager;
    private final DisplaySizeCorrector mDisplaySizeCorrector = new DisplaySizeCorrector();
    private final MaxPreviewSize mMaxPreviewSize = new MaxPreviewSize();
    private volatile Size mPreviewSize = null;

    private DisplayInfoManager(Context context) {
        this.mDisplayManager = (DisplayManager) context.getSystemService("display");
    }

    public static DisplayInfoManager getInstance(Context context) {
        if (sInstance == null) {
            synchronized (INSTANCE_LOCK) {
                if (sInstance == null) {
                    sInstance = new DisplayInfoManager(context);
                }
            }
        }
        return sInstance;
    }

    static void releaseInstance() {
        sInstance = null;
    }

    /* access modifiers changed from: package-private */
    public void refresh() {
        this.mPreviewSize = calculatePreviewSize();
    }

    public Display getMaxSizeDisplay(boolean z) {
        Display[] displays = this.mDisplayManager.getDisplays();
        if (displays.length == 1) {
            return displays[0];
        }
        Display maxSizeDisplayInternal = getMaxSizeDisplayInternal(displays, z);
        if (maxSizeDisplayInternal == null && z) {
            maxSizeDisplayInternal = getMaxSizeDisplayInternal(displays, false);
        }
        if (maxSizeDisplayInternal != null) {
            return maxSizeDisplayInternal;
        }
        throw new IllegalArgumentException("No display can be found from the input display manager!");
    }

    private Display getMaxSizeDisplayInternal(Display[] displayArr, boolean z) {
        Display display = null;
        int i = -1;
        for (Display display2 : displayArr) {
            if (!z || display2.getState() != 1) {
                Point point = new Point();
                display2.getRealSize(point);
                if (point.x * point.y > i) {
                    i = point.x * point.y;
                    display = display2;
                }
            }
        }
        return display;
    }

    /* access modifiers changed from: package-private */
    public Size getPreviewSize() {
        if (this.mPreviewSize != null) {
            return this.mPreviewSize;
        }
        this.mPreviewSize = calculatePreviewSize();
        return this.mPreviewSize;
    }

    private Size calculatePreviewSize() {
        Size correctedDisplaySize = getCorrectedDisplaySize();
        int width = correctedDisplaySize.getWidth() * correctedDisplaySize.getHeight();
        Size size = MAX_PREVIEW_SIZE;
        if (width > size.getWidth() * size.getHeight()) {
            correctedDisplaySize = size;
        }
        return this.mMaxPreviewSize.getMaxPreviewResolution(correctedDisplaySize);
    }

    private Size getCorrectedDisplaySize() {
        Point point = new Point();
        getMaxSizeDisplay(false).getRealSize(point);
        Size size = new Size(point.x, point.y);
        if (SizeUtil.isSmallerByArea(size, ABNORMAL_DISPLAY_SIZE_THRESHOLD) && (size = this.mDisplaySizeCorrector.getDisplaySize()) == null) {
            size = FALLBACK_DISPLAY_SIZE;
        }
        return size.getHeight() > size.getWidth() ? new Size(size.getHeight(), size.getWidth()) : size;
    }
}

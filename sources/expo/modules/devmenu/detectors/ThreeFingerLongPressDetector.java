package expo.modules.devmenu.detectors;

import android.os.SystemClock;
import android.view.MotionEvent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0010\u001a\u00020\u00042\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lexpo/modules/devmenu/detectors/ThreeFingerLongPressDetector;", "", "longPressListener", "Lkotlin/Function0;", "", "(Lkotlin/jvm/functions/Function0;)V", "getLongPressListener", "()Lkotlin/jvm/functions/Function0;", "startPosition", "", "Landroid/view/MotionEvent$PointerCoords;", "[Landroid/view/MotionEvent$PointerCoords;", "startTime", "", "startedDetecting", "", "onTouchEvent", "event", "Landroid/view/MotionEvent;", "expo-dev-menu_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ThreeFingerLongPressDetector.kt */
public final class ThreeFingerLongPressDetector {
    private final Function0<Unit> longPressListener;
    private MotionEvent.PointerCoords[] startPosition;
    private long startTime = Long.MAX_VALUE;
    private boolean startedDetecting;

    public ThreeFingerLongPressDetector(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "longPressListener");
        this.longPressListener = function0;
        MotionEvent.PointerCoords[] pointerCoordsArr = new MotionEvent.PointerCoords[3];
        for (int i = 0; i < 3; i++) {
            pointerCoordsArr[i] = new MotionEvent.PointerCoords();
        }
        this.startPosition = pointerCoordsArr;
    }

    public final Function0<Unit> getLongPressListener() {
        return this.longPressListener;
    }

    public final void onTouchEvent(MotionEvent motionEvent) {
        boolean z = true;
        if (!this.startedDetecting) {
            if ((motionEvent != null && motionEvent.getAction() == 2) && motionEvent.getPointerCount() == 3) {
                this.startedDetecting = true;
                this.startTime = SystemClock.uptimeMillis();
                for (int i = 0; i < 3; i++) {
                    motionEvent.getPointerCoords(i, this.startPosition[i]);
                }
                return;
            }
        }
        if (motionEvent == null || motionEvent.getAction() != 2) {
            z = false;
        }
        if (!z || motionEvent.getPointerCount() != 3) {
            this.startedDetecting = false;
            return;
        }
        for (int i2 = 0; i2 < 3; i2++) {
            MotionEvent.PointerCoords pointerCoords = new MotionEvent.PointerCoords();
            motionEvent.getPointerCoords(i2, pointerCoords);
            if (((double) Math.abs(pointerCoords.x - this.startPosition[i2].x)) > 20.0d || ((double) Math.abs(pointerCoords.y - this.startPosition[i2].y)) > 20.0d) {
                this.startedDetecting = false;
                return;
            }
        }
        if (SystemClock.uptimeMillis() - this.startTime >= 800) {
            this.longPressListener.invoke();
            this.startedDetecting = false;
        }
    }
}

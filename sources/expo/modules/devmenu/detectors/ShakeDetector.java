package expo.modules.devmenu.detectors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0007H\u0002J\u0018\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\rH\u0016J\u0010\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u0004H\u0002J\u000e\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u0014J\u0006\u0010\"\u001a\u00020\u0004R\u000e\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lexpo/modules/devmenu/detectors/ShakeDetector;", "Landroid/hardware/SensorEventListener;", "shakeListener", "Lkotlin/Function0;", "", "(Lkotlin/jvm/functions/Function0;)V", "accelerationX", "", "accelerationY", "accelerationZ", "lastDispatchedShakeTimestamp", "", "minRecordedShakes", "", "getMinRecordedShakes", "()I", "setMinRecordedShakes", "(I)V", "numShakes", "sensorManager", "Landroid/hardware/SensorManager;", "atLeastRequiredForce", "", "a", "onAccuracyChanged", "sensor", "Landroid/hardware/Sensor;", "i", "onSensorChanged", "sensorEvent", "Landroid/hardware/SensorEvent;", "reset", "start", "manager", "stop", "expo-dev-menu_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ShakeDetector.kt */
public final class ShakeDetector implements SensorEventListener {
    private float accelerationX;
    private float accelerationY;
    private float accelerationZ;
    private long lastDispatchedShakeTimestamp;
    private int minRecordedShakes = 3;
    private int numShakes;
    private SensorManager sensorManager;
    private final Function0<Unit> shakeListener;

    public void onAccuracyChanged(Sensor sensor, int i) {
        Intrinsics.checkNotNullParameter(sensor, "sensor");
    }

    public ShakeDetector(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "shakeListener");
        this.shakeListener = function0;
    }

    public final int getMinRecordedShakes() {
        return this.minRecordedShakes;
    }

    public final void setMinRecordedShakes(int i) {
        this.minRecordedShakes = i;
    }

    public final void start(SensorManager sensorManager2) {
        Intrinsics.checkNotNullParameter(sensorManager2, "manager");
        Sensor defaultSensor = sensorManager2.getDefaultSensor(1);
        if (defaultSensor != null) {
            this.sensorManager = sensorManager2;
            sensorManager2.registerListener(this, defaultSensor, 2);
            this.lastDispatchedShakeTimestamp = 0;
            reset();
        }
    }

    public final void stop() {
        SensorManager sensorManager2 = this.sensorManager;
        if (sensorManager2 != null) {
            sensorManager2.unregisterListener(this);
        }
        this.sensorManager = null;
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        Intrinsics.checkNotNullParameter(sensorEvent, "sensorEvent");
        if (sensorEvent.timestamp - this.lastDispatchedShakeTimestamp >= ShakeDetectorKt.MIN_TIME_AFTER_SHAKE_NS) {
            float f = sensorEvent.values[0];
            float f2 = sensorEvent.values[1];
            float f3 = sensorEvent.values[2] - 9.80665f;
            if (atLeastRequiredForce(f) && this.accelerationX * f <= 0.0f) {
                this.numShakes++;
                this.accelerationX = f;
            } else if (atLeastRequiredForce(f2) && this.accelerationY * f2 <= 0.0f) {
                this.numShakes++;
                this.accelerationY = f2;
            } else if (atLeastRequiredForce(f3) && this.accelerationZ * f3 <= 0.0f) {
                this.numShakes++;
                this.accelerationZ = f3;
            }
            if (this.numShakes >= this.minRecordedShakes) {
                reset();
                this.shakeListener.invoke();
                this.lastDispatchedShakeTimestamp = sensorEvent.timestamp;
            }
        }
    }

    private final void reset() {
        this.numShakes = 0;
        this.accelerationX = 0.0f;
        this.accelerationY = 0.0f;
        this.accelerationZ = 0.0f;
    }

    private final boolean atLeastRequiredForce(float f) {
        return Math.abs(f) > 13.042845f;
    }
}

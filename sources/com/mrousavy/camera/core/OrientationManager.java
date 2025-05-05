package com.mrousavy.camera.core;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.util.Log;
import com.mrousavy.camera.core.types.Orientation;
import com.mrousavy.camera.core.types.OutputOrientation;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006*\u0002\n\u0012\u0018\u0000 $2\u00020\u0001:\u0002#$B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\bH\u0002J\b\u0010\u001e\u001a\u00020\u001fH\u0002J\u000e\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u001bJ\u0006\u0010\"\u001a\u00020\u001fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u0004\n\u0002\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0016R\u000e\u0010\u0019\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/mrousavy/camera/core/OrientationManager;", "", "context", "Landroid/content/Context;", "callback", "Lcom/mrousavy/camera/core/OrientationManager$Callback;", "(Landroid/content/Context;Lcom/mrousavy/camera/core/OrientationManager$Callback;)V", "deviceRotation", "", "displayListener", "com/mrousavy/camera/core/OrientationManager$displayListener$1", "Lcom/mrousavy/camera/core/OrientationManager$displayListener$1;", "displayManager", "Landroid/hardware/display/DisplayManager;", "lastOutputOrientation", "Lcom/mrousavy/camera/core/types/Orientation;", "lastPreviewOrientation", "orientationListener", "com/mrousavy/camera/core/OrientationManager$orientationListener$1", "Lcom/mrousavy/camera/core/OrientationManager$orientationListener$1;", "outputOrientation", "getOutputOrientation", "()Lcom/mrousavy/camera/core/types/Orientation;", "previewOrientation", "getPreviewOrientation", "screenRotation", "targetOutputOrientation", "Lcom/mrousavy/camera/core/types/OutputOrientation;", "degreesToSurfaceRotation", "degrees", "maybeNotifyOrientationChanged", "", "setTargetOutputOrientation", "targetOrientation", "stopOrientationUpdates", "Callback", "Companion", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: OrientationManager.kt */
public final class OrientationManager {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "OrientationManager";
    private final Callback callback;
    private final Context context;
    /* access modifiers changed from: private */
    public int deviceRotation;
    private final OrientationManager$displayListener$1 displayListener;
    /* access modifiers changed from: private */
    public final DisplayManager displayManager;
    private Orientation lastOutputOrientation;
    private Orientation lastPreviewOrientation;
    private final OrientationManager$orientationListener$1 orientationListener;
    /* access modifiers changed from: private */
    public int screenRotation;
    private OutputOrientation targetOutputOrientation = OutputOrientation.DEVICE;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005H&¨\u0006\b"}, d2 = {"Lcom/mrousavy/camera/core/OrientationManager$Callback;", "", "onOutputOrientationChanged", "", "outputOrientation", "Lcom/mrousavy/camera/core/types/Orientation;", "onPreviewOrientationChanged", "previewOrientation", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: OrientationManager.kt */
    public interface Callback {
        void onOutputOrientationChanged(Orientation orientation);

        void onPreviewOrientationChanged(Orientation orientation);
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: OrientationManager.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                com.mrousavy.camera.core.types.OutputOrientation[] r0 = com.mrousavy.camera.core.types.OutputOrientation.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.mrousavy.camera.core.types.OutputOrientation r1 = com.mrousavy.camera.core.types.OutputOrientation.DEVICE     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.mrousavy.camera.core.types.OutputOrientation r1 = com.mrousavy.camera.core.types.OutputOrientation.PREVIEW     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mrousavy.camera.core.OrientationManager.WhenMappings.<clinit>():void");
        }
    }

    /* access modifiers changed from: private */
    public final int degreesToSurfaceRotation(int i) {
        if (45 <= i && i < 136) {
            return 3;
        }
        if (135 <= i && i < 226) {
            return 2;
        }
        return 225 <= i && i < 316 ? 1 : 0;
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/mrousavy/camera/core/OrientationManager$Companion;", "", "()V", "TAG", "", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: OrientationManager.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public OrientationManager(Context context2, Callback callback2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(callback2, "callback");
        this.context = context2;
        this.callback = callback2;
        Object systemService = context2.getSystemService("display");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.hardware.display.DisplayManager");
        this.displayManager = (DisplayManager) systemService;
        this.displayListener = new OrientationManager$displayListener$1(this);
        this.orientationListener = new OrientationManager$orientationListener$1(this, context2);
    }

    public final Orientation getPreviewOrientation() {
        return Orientation.Companion.fromSurfaceRotation(this.screenRotation);
    }

    public final Orientation getOutputOrientation() {
        int i = WhenMappings.$EnumSwitchMapping$0[this.targetOutputOrientation.ordinal()];
        if (i == 1) {
            return Orientation.Companion.fromSurfaceRotation(this.deviceRotation);
        }
        if (i == 2) {
            return getPreviewOrientation();
        }
        throw new NoWhenBranchMatchedException();
    }

    /* access modifiers changed from: private */
    public final void maybeNotifyOrientationChanged() {
        Orientation previewOrientation = getPreviewOrientation();
        if (this.lastPreviewOrientation != previewOrientation) {
            this.callback.onPreviewOrientationChanged(previewOrientation);
            this.lastPreviewOrientation = previewOrientation;
        }
        Orientation outputOrientation = getOutputOrientation();
        if (this.lastOutputOrientation != outputOrientation) {
            this.callback.onOutputOrientationChanged(outputOrientation);
            this.lastOutputOrientation = outputOrientation;
        }
    }

    public final void stopOrientationUpdates() {
        this.displayManager.unregisterDisplayListener(this.displayListener);
        this.orientationListener.disable();
    }

    public final void setTargetOutputOrientation(OutputOrientation outputOrientation) {
        Intrinsics.checkNotNullParameter(outputOrientation, "targetOrientation");
        Log.i(TAG, "Target Orientation changed " + this.targetOutputOrientation + " -> " + outputOrientation + "!");
        this.targetOutputOrientation = outputOrientation;
        stopOrientationUpdates();
        int i = WhenMappings.$EnumSwitchMapping$0[outputOrientation.ordinal()];
        if (i == 1) {
            Log.i(TAG, "Starting streaming device and screen orientation updates...");
            this.orientationListener.enable();
            this.displayManager.registerDisplayListener(this.displayListener, (Handler) null);
        } else if (i == 2) {
            Log.i(TAG, "Starting streaming device and screen orientation updates...");
            this.displayManager.registerDisplayListener(this.displayListener, (Handler) null);
        }
    }
}

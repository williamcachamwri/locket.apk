package androidx.camera.core;

import android.view.Display;
import com.canhub.cropper.CropImageOptions;

public final class DisplayOrientedMeteringPointFactory extends MeteringPointFactory {
    private final CameraInfo mCameraInfo;
    private final Display mDisplay;
    private final float mHeight;
    private final float mWidth;

    public DisplayOrientedMeteringPointFactory(Display display, CameraInfo cameraInfo, float f, float f2) {
        this.mWidth = f;
        this.mHeight = f2;
        this.mDisplay = display;
        this.mCameraInfo = cameraInfo;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0034  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.PointF convertPoint(float r9, float r10) {
        /*
            r8 = this;
            float r0 = r8.mWidth
            float r1 = r8.mHeight
            androidx.camera.core.CameraInfo r2 = r8.mCameraInfo
            int r2 = r2.getLensFacing()
            if (r2 != 0) goto L_0x000e
            r2 = 1
            goto L_0x000f
        L_0x000e:
            r2 = 0
        L_0x000f:
            int r3 = r8.getRelativeCameraOrientation(r2)
            r4 = 270(0x10e, float:3.78E-43)
            r5 = 90
            if (r3 == r5) goto L_0x0022
            if (r3 != r4) goto L_0x001c
            goto L_0x0022
        L_0x001c:
            r6 = r10
            r10 = r9
            r9 = r6
            r7 = r1
            r1 = r0
            r0 = r7
        L_0x0022:
            if (r3 == r5) goto L_0x0030
            r5 = 180(0xb4, float:2.52E-43)
            if (r3 == r5) goto L_0x002e
            if (r3 == r4) goto L_0x002b
            goto L_0x0032
        L_0x002b:
            float r10 = r1 - r10
            goto L_0x0032
        L_0x002e:
            float r10 = r1 - r10
        L_0x0030:
            float r9 = r0 - r9
        L_0x0032:
            if (r2 == 0) goto L_0x0036
            float r10 = r1 - r10
        L_0x0036:
            float r10 = r10 / r1
            float r9 = r9 / r0
            android.graphics.PointF r0 = new android.graphics.PointF
            r0.<init>(r10, r9)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.DisplayOrientedMeteringPointFactory.convertPoint(float, float):android.graphics.PointF");
    }

    private int getRelativeCameraOrientation(boolean z) {
        try {
            int sensorRotationDegrees = this.mCameraInfo.getSensorRotationDegrees(this.mDisplay.getRotation());
            return z ? (360 - sensorRotationDegrees) % CropImageOptions.DEGREES_360 : sensorRotationDegrees;
        } catch (Exception unused) {
            return 0;
        }
    }
}

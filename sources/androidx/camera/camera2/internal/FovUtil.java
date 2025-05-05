package androidx.camera.camera2.internal;

import android.graphics.Rect;
import android.hardware.camera2.CameraCharacteristics;
import android.util.Size;
import android.util.SizeF;
import androidx.camera.camera2.internal.compat.CameraAccessExceptionCompat;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.camera2.internal.compat.CameraManagerCompat;
import androidx.camera.core.impl.utils.TransformUtils;
import androidx.core.util.Preconditions;
import com.canhub.cropper.CropImageOptions;

public class FovUtil {
    private static final String TAG = "FovUtil";

    private FovUtil() {
    }

    public static int focalLengthToViewAngleDegrees(float f, float f2) {
        boolean z = true;
        Preconditions.checkArgument(f > 0.0f, "Focal length should be positive.");
        if (f2 <= 0.0f) {
            z = false;
        }
        Preconditions.checkArgument(z, "Sensor length should be positive.");
        int degrees = (int) Math.toDegrees(Math.atan((double) (f2 / (f * 2.0f))) * 2.0d);
        Preconditions.checkArgumentInRange(degrees, 0, (int) CropImageOptions.DEGREES_360, "The provided focal length and sensor length result in an invalid view angle degrees.");
        return degrees;
    }

    public static int getDeviceDefaultViewAngleDegrees(CameraManagerCompat cameraManagerCompat, int i) {
        try {
            for (String cameraCharacteristicsCompat : cameraManagerCompat.getCameraIdList()) {
                CameraCharacteristicsCompat cameraCharacteristicsCompat2 = cameraManagerCompat.getCameraCharacteristicsCompat(cameraCharacteristicsCompat);
                Integer num = (Integer) cameraCharacteristicsCompat2.get(CameraCharacteristics.LENS_FACING);
                Preconditions.checkNotNull(num, "Lens facing can not be null");
                if (num.intValue() == LensFacingUtil.getLensFacingInt(i)) {
                    return focalLengthToViewAngleDegrees(getDefaultFocalLength(cameraCharacteristicsCompat2), getSensorHorizontalLength(cameraCharacteristicsCompat2));
                }
            }
            throw new IllegalArgumentException("Unable to get the default focal length with the specified lens facing.");
        } catch (CameraAccessExceptionCompat unused) {
            throw new IllegalArgumentException("Unable to get the default focal length.");
        }
    }

    public static float getSensorHorizontalLength(CameraCharacteristicsCompat cameraCharacteristicsCompat) {
        SizeF sizeF = (SizeF) cameraCharacteristicsCompat.get(CameraCharacteristics.SENSOR_INFO_PHYSICAL_SIZE);
        Rect rect = (Rect) cameraCharacteristicsCompat.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
        Size size = (Size) cameraCharacteristicsCompat.get(CameraCharacteristics.SENSOR_INFO_PIXEL_ARRAY_SIZE);
        Integer num = (Integer) cameraCharacteristicsCompat.get(CameraCharacteristics.SENSOR_ORIENTATION);
        Preconditions.checkNotNull(sizeF, "The sensor size can't be null.");
        Preconditions.checkNotNull(num, "The sensor orientation can't be null.");
        Preconditions.checkNotNull(rect, "The active array size can't be null.");
        Preconditions.checkNotNull(size, "The pixel array size can't be null.");
        Size rectToSize = TransformUtils.rectToSize(rect);
        if (TransformUtils.is90or270(num.intValue())) {
            sizeF = TransformUtils.reverseSizeF(sizeF);
            rectToSize = TransformUtils.reverseSize(rectToSize);
            size = TransformUtils.reverseSize(size);
        }
        return (sizeF.getWidth() * ((float) rectToSize.getWidth())) / ((float) size.getWidth());
    }

    public static float getDefaultFocalLength(CameraCharacteristicsCompat cameraCharacteristicsCompat) {
        float[] fArr = (float[]) cameraCharacteristicsCompat.get(CameraCharacteristics.LENS_INFO_AVAILABLE_FOCAL_LENGTHS);
        Preconditions.checkNotNull(fArr, "The focal lengths can not be empty.");
        return fArr[0];
    }
}

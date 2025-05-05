package androidx.camera.mlkit.vision;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.media.Image;
import android.util.Size;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.utils.TransformUtils;
import androidx.camera.view.transform.ImageProxyTransformFactory;
import androidx.core.util.Consumer;
import com.canhub.cropper.CropImageOptions;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.interfaces.Detector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;

public class MlKitAnalyzer implements ImageAnalysis.Analyzer {
    private static final Size DEFAULT_SIZE = new Size(480, CropImageOptions.DEGREES_360);
    private static final String TAG = "MlKitAnalyzer";
    final Consumer<Result> mConsumer;
    private final List<Detector<?>> mDetectors;
    private final Executor mExecutor;
    final ImageProxyTransformFactory mImageAnalysisTransformFactory;
    private Matrix mSensorToTarget;
    private final int mTargetCoordinateSystem;

    public MlKitAnalyzer(List<Detector<?>> list, int i, Executor executor, Consumer<Result> consumer) {
        if (i != 0) {
            for (Detector<?> detectorType : list) {
                Preconditions.checkArgument(detectorType.getDetectorType() != 7, "Segmentation only works with COORDINATE_SYSTEM_ORIGINAL");
            }
        }
        this.mDetectors = new ArrayList(list);
        this.mTargetCoordinateSystem = i;
        this.mConsumer = consumer;
        this.mExecutor = executor;
        ImageProxyTransformFactory imageProxyTransformFactory = new ImageProxyTransformFactory();
        this.mImageAnalysisTransformFactory = imageProxyTransformFactory;
        imageProxyTransformFactory.setUsingRotationDegrees(true);
    }

    public final void analyze(ImageProxy imageProxy) {
        Matrix matrix = new Matrix();
        int i = this.mTargetCoordinateSystem;
        if (i != 0) {
            Matrix matrix2 = this.mSensorToTarget;
            if (i == 2 || matrix2 != null) {
                Matrix matrix3 = new Matrix(imageProxy.getImageInfo().getSensorToBufferTransformMatrix());
                RectF rectF = new RectF(0.0f, 0.0f, (float) imageProxy.getWidth(), (float) imageProxy.getHeight());
                matrix3.postConcat(TransformUtils.getRectToRect(rectF, TransformUtils.rotateRect(rectF, imageProxy.getImageInfo().getRotationDegrees()), imageProxy.getImageInfo().getRotationDegrees()));
                matrix3.invert(matrix);
                if (this.mTargetCoordinateSystem != 2) {
                    matrix.postConcat(matrix2);
                }
            } else {
                Logger.d(TAG, "Sensor-to-target transformation is null.");
                imageProxy.close();
                return;
            }
        }
        detectRecursively(imageProxy, 0, matrix, new HashMap(), new HashMap());
    }

    private void detectRecursively(ImageProxy imageProxy, int i, Matrix matrix, Map<Detector<?>, Object> map, Map<Detector<?>, Throwable> map2) {
        int i2 = i;
        Map<Detector<?>, Throwable> map3 = map2;
        Image image = imageProxy.getImage();
        if (image == null) {
            Logger.e(TAG, "Image is null.");
            imageProxy.close();
        } else if (i2 > this.mDetectors.size() - 1) {
            imageProxy.close();
            ImageProxy imageProxy2 = imageProxy;
            this.mExecutor.execute(new MlKitAnalyzer$$ExternalSyntheticLambda0(this, map, imageProxy, map3));
        } else {
            ImageProxy imageProxy3 = imageProxy;
            Map<Detector<?>, Object> map4 = map;
            Detector detector = this.mDetectors.get(i);
            try {
                detector.process(image, imageProxy.getImageInfo().getRotationDegrees(), matrix).addOnCompleteListener(this.mExecutor, new MlKitAnalyzer$$ExternalSyntheticLambda1(this, map2, detector, map, imageProxy, i, matrix));
            } catch (Exception e) {
                map3.put(detector, new RuntimeException("Failed to process the image.", e));
                detectRecursively(imageProxy, i2 + 1, matrix, map, map2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$detectRecursively$0$androidx-camera-mlkit-vision-MlKitAnalyzer  reason: not valid java name */
    public /* synthetic */ void m238lambda$detectRecursively$0$androidxcameramlkitvisionMlKitAnalyzer(Map map, ImageProxy imageProxy, Map map2) {
        this.mConsumer.accept(new Result(map, imageProxy.getImageInfo().getTimestamp(), map2));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$detectRecursively$1$androidx-camera-mlkit-vision-MlKitAnalyzer  reason: not valid java name */
    public /* synthetic */ void m239lambda$detectRecursively$1$androidxcameramlkitvisionMlKitAnalyzer(Map map, Detector detector, Map map2, ImageProxy imageProxy, int i, Matrix matrix, Task task) {
        if (task.isCanceled()) {
            map.put(detector, new CancellationException("The task is canceled."));
        } else if (task.isSuccessful()) {
            map2.put(detector, task.getResult());
        } else {
            map.put(detector, task.getException());
        }
        detectRecursively(imageProxy, i + 1, matrix, map2, map);
    }

    public final Size getDefaultTargetResolution() {
        Size size = DEFAULT_SIZE;
        for (Detector<?> detectorType : this.mDetectors) {
            Size targetResolution = getTargetResolution(detectorType.getDetectorType());
            if (targetResolution.getHeight() * targetResolution.getWidth() > size.getWidth() * size.getHeight()) {
                size = targetResolution;
            }
        }
        return size;
    }

    private Size getTargetResolution(int i) {
        if (i == 1 || i == 4) {
            return new Size(1280, 720);
        }
        return DEFAULT_SIZE;
    }

    public final int getTargetCoordinateSystem() {
        return this.mTargetCoordinateSystem;
    }

    public final void updateTransform(Matrix matrix) {
        if (matrix == null) {
            this.mSensorToTarget = null;
        } else {
            this.mSensorToTarget = new Matrix(matrix);
        }
    }

    public static final class Result {
        private final Map<Detector<?>, Throwable> mThrowables;
        private final long mTimestamp;
        private final Map<Detector<?>, Object> mValues;

        public Result(Map<Detector<?>, Object> map, long j, Map<Detector<?>, Throwable> map2) {
            this.mValues = map;
            this.mThrowables = map2;
            this.mTimestamp = j;
        }

        public <T> T getValue(Detector<T> detector) {
            checkDetectorExists(detector);
            return this.mValues.get(detector);
        }

        public Throwable getThrowable(Detector<?> detector) {
            checkDetectorExists(detector);
            return this.mThrowables.get(detector);
        }

        public long getTimestamp() {
            return this.mTimestamp;
        }

        private void checkDetectorExists(Detector<?> detector) {
            Preconditions.checkArgument(this.mValues.containsKey(detector) || this.mThrowables.containsKey(detector), "The detector does not exist");
        }
    }
}

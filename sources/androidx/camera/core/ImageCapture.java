package androidx.camera.core;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.location.Location;
import android.net.Uri;
import android.os.Looper;
import android.util.Log;
import android.util.Pair;
import android.util.Rational;
import android.util.Size;
import androidx.camera.core.imagecapture.ImageCaptureControl;
import androidx.camera.core.imagecapture.ImagePipeline;
import androidx.camera.core.imagecapture.TakePictureManager;
import androidx.camera.core.imagecapture.TakePictureRequest;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.ConfigProvider;
import androidx.camera.core.impl.ImageCaptureConfig;
import androidx.camera.core.impl.ImageInputConfig;
import androidx.camera.core.impl.ImageOutputConfig;
import androidx.camera.core.impl.ImageReaderProxy;
import androidx.camera.core.impl.MutableConfig;
import androidx.camera.core.impl.MutableOptionsBundle;
import androidx.camera.core.impl.OptionsBundle;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.SessionProcessor;
import androidx.camera.core.impl.StreamSpec;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.impl.utils.CameraOrientationUtil;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.TransformUtils;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.core.internal.IoConfig;
import androidx.camera.core.internal.ScreenFlashWrapper;
import androidx.camera.core.internal.TargetConfig;
import androidx.camera.core.internal.compat.workaround.ExifRotationAvailability;
import androidx.camera.core.internal.utils.ImageUtil;
import androidx.camera.core.resolutionselector.AspectRatioStrategy;
import androidx.camera.core.resolutionselector.ResolutionSelector;
import androidx.camera.core.resolutionselector.ResolutionStrategy;
import androidx.core.util.Preconditions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.google.common.util.concurrent.ListenableFuture;
import io.sentry.android.core.SentryLogcatAdapter;
import java.io.File;
import java.io.OutputStream;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

public final class ImageCapture extends UseCase {
    public static final int CAPTURE_MODE_MAXIMIZE_QUALITY = 0;
    public static final int CAPTURE_MODE_MINIMIZE_LATENCY = 1;
    public static final int CAPTURE_MODE_ZERO_SHUTTER_LAG = 2;
    private static final int DEFAULT_CAPTURE_MODE = 1;
    public static final Defaults DEFAULT_CONFIG = new Defaults();
    private static final int DEFAULT_FLASH_MODE = 2;
    public static final int ERROR_CAMERA_CLOSED = 3;
    public static final int ERROR_CAPTURE_FAILED = 2;
    public static final int ERROR_FILE_IO = 1;
    public static final int ERROR_INVALID_CAMERA = 4;
    public static final int ERROR_UNKNOWN = 0;
    static final ExifRotationAvailability EXIF_ROTATION_AVAILABILITY = new ExifRotationAvailability();
    public static final int FLASH_MODE_AUTO = 0;
    public static final int FLASH_MODE_OFF = 2;
    public static final int FLASH_MODE_ON = 1;
    public static final int FLASH_MODE_SCREEN = 3;
    private static final int FLASH_MODE_UNKNOWN = -1;
    public static final int FLASH_TYPE_ONE_SHOT_FLASH = 0;
    public static final int FLASH_TYPE_USE_TORCH_AS_FLASH = 1;
    private static final byte JPEG_QUALITY_MAXIMIZE_QUALITY_MODE = 100;
    private static final byte JPEG_QUALITY_MINIMIZE_LATENCY_MODE = 95;
    private static final int MAX_IMAGES = 2;
    public static final int OUTPUT_FORMAT_JPEG = 0;
    public static final int OUTPUT_FORMAT_JPEG_ULTRA_HDR = 1;
    public static final int OUTPUT_FORMAT_RAW = 2;
    public static final int OUTPUT_FORMAT_RAW_JPEG = 3;
    public static final long SCREEN_FLASH_UI_APPLY_TIMEOUT_SECONDS = 3;
    private static final String TAG = "ImageCapture";
    private final int mCaptureMode;
    private SessionConfig.CloseableErrorListener mCloseableErrorListener;
    private final ImageReaderProxy.OnImageAvailableListener mClosingListener = new ImageCapture$$ExternalSyntheticLambda2();
    private Rational mCropAspectRatio = null;
    private int mFlashMode = -1;
    private final int mFlashType;
    private final ImageCaptureControl mImageCaptureControl = new ImageCaptureControl() {
        public void lockFlashMode() {
            ImageCapture.this.lockFlashMode();
        }

        public void unlockFlashMode() {
            ImageCapture.this.unlockFlashMode();
        }

        public ListenableFuture<Void> submitStillCaptureRequests(List<CaptureConfig> list) {
            return ImageCapture.this.submitStillCaptureRequest(list);
        }
    };
    private ImagePipeline mImagePipeline;
    private final AtomicReference<Integer> mLockedFlashMode = new AtomicReference<>((Object) null);
    private ScreenFlashWrapper mScreenFlashWrapper;
    SessionConfig.Builder mSessionConfigBuilder;
    private TakePictureManager mTakePictureManager;

    @Retention(RetentionPolicy.SOURCE)
    public @interface CaptureMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface FlashMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface FlashType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ImageCaptureError {
    }

    public static abstract class OnImageCapturedCallback {
        public void onCaptureProcessProgressed(int i) {
        }

        public void onCaptureStarted() {
        }

        public void onCaptureSuccess(ImageProxy imageProxy) {
        }

        public void onError(ImageCaptureException imageCaptureException) {
        }

        public void onPostviewBitmapAvailable(Bitmap bitmap) {
        }
    }

    public interface OnImageSavedCallback {
        void onCaptureProcessProgressed(int i) {
        }

        void onCaptureStarted() {
        }

        void onError(ImageCaptureException imageCaptureException);

        void onImageSaved(OutputFileResults outputFileResults);

        void onPostviewBitmapAvailable(Bitmap bitmap) {
        }
    }

    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface OutputFormat {
    }

    public interface ScreenFlash {
        void apply(long j, ScreenFlashListener screenFlashListener);

        void clear();
    }

    public interface ScreenFlashListener {
        void onCompleted();
    }

    static /* synthetic */ Void lambda$submitStillCaptureRequest$5(List list) {
        return null;
    }

    static /* synthetic */ void lambda$new$0(ImageReaderProxy imageReaderProxy) {
        ImageProxy acquireLatestImage;
        try {
            acquireLatestImage = imageReaderProxy.acquireLatestImage();
            Log.d(TAG, "Discarding ImageProxy which was inadvertently acquired: " + acquireLatestImage);
            if (acquireLatestImage != null) {
                acquireLatestImage.close();
                return;
            }
            return;
        } catch (IllegalStateException e) {
            SentryLogcatAdapter.e(TAG, "Failed to acquire latest image.", e);
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    ImageCapture(ImageCaptureConfig imageCaptureConfig) {
        super(imageCaptureConfig);
        ImageCaptureConfig imageCaptureConfig2 = (ImageCaptureConfig) getCurrentConfig();
        if (imageCaptureConfig2.containsOption(ImageCaptureConfig.OPTION_IMAGE_CAPTURE_MODE)) {
            this.mCaptureMode = imageCaptureConfig2.getCaptureMode();
        } else {
            this.mCaptureMode = 1;
        }
        this.mFlashType = imageCaptureConfig2.getFlashType(0);
        this.mScreenFlashWrapper = ScreenFlashWrapper.from(imageCaptureConfig2.getScreenFlash());
    }

    private boolean isSessionProcessorEnabledInCurrentCamera() {
        if (getCamera() == null || getCamera().getExtendedConfig().getSessionProcessor((SessionProcessor) null) == null) {
            return false;
        }
        return true;
    }

    public UseCaseConfig<?> getDefaultConfig(boolean z, UseCaseConfigFactory useCaseConfigFactory) {
        Defaults defaults = DEFAULT_CONFIG;
        Config config = useCaseConfigFactory.getConfig(defaults.getConfig().getCaptureType(), getCaptureMode());
        if (z) {
            config = Config.mergeConfigs(config, defaults.getConfig());
        }
        if (config == null) {
            return null;
        }
        return getUseCaseConfigBuilder(config).getUseCaseConfig();
    }

    public UseCaseConfig.Builder<?, ?, ?> getUseCaseConfigBuilder(Config config) {
        return Builder.fromConfig(config);
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [androidx.camera.core.impl.UseCaseConfig$Builder, androidx.camera.core.impl.UseCaseConfig$Builder<?, ?, ?>] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.camera.core.impl.UseCaseConfig<?> onMergeConfig(androidx.camera.core.impl.CameraInfoInternal r6, androidx.camera.core.impl.UseCaseConfig.Builder<?, ?, ?> r7) {
        /*
            r5 = this;
            androidx.camera.core.impl.Quirks r6 = r6.getCameraQuirks()
            java.lang.Class<androidx.camera.core.internal.compat.quirk.SoftwareJpegEncodingPreferredQuirk> r0 = androidx.camera.core.internal.compat.quirk.SoftwareJpegEncodingPreferredQuirk.class
            boolean r6 = r6.contains(r0)
            r0 = 1
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r0)
            if (r6 == 0) goto L_0x0039
            java.lang.Boolean r6 = java.lang.Boolean.FALSE
            androidx.camera.core.impl.MutableConfig r2 = r7.getMutableConfig()
            androidx.camera.core.impl.Config$Option<java.lang.Boolean> r3 = androidx.camera.core.impl.ImageCaptureConfig.OPTION_USE_SOFTWARE_JPEG_ENCODER
            java.lang.Object r2 = r2.retrieveOption(r3, r1)
            boolean r6 = r6.equals(r2)
            java.lang.String r2 = "ImageCapture"
            if (r6 == 0) goto L_0x002b
            java.lang.String r6 = "Device quirk suggests software JPEG encoder, but it has been explicitly disabled."
            androidx.camera.core.Logger.w(r2, r6)
            goto L_0x0039
        L_0x002b:
            java.lang.String r6 = "Requesting software JPEG due to device quirk."
            androidx.camera.core.Logger.i(r2, r6)
            androidx.camera.core.impl.MutableConfig r6 = r7.getMutableConfig()
            androidx.camera.core.impl.Config$Option<java.lang.Boolean> r2 = androidx.camera.core.impl.ImageCaptureConfig.OPTION_USE_SOFTWARE_JPEG_ENCODER
            r6.insertOption(r2, r1)
        L_0x0039:
            androidx.camera.core.impl.MutableConfig r6 = r7.getMutableConfig()
            boolean r6 = r5.enforceSoftwareJpegConstraints(r6)
            androidx.camera.core.impl.MutableConfig r1 = r7.getMutableConfig()
            androidx.camera.core.impl.Config$Option<java.lang.Integer> r2 = androidx.camera.core.impl.ImageCaptureConfig.OPTION_BUFFER_FORMAT
            r3 = 0
            java.lang.Object r1 = r1.retrieveOption(r2, r3)
            java.lang.Integer r1 = (java.lang.Integer) r1
            r2 = 35
            r4 = 256(0x100, float:3.59E-43)
            if (r1 == 0) goto L_0x007d
            boolean r3 = r5.isSessionProcessorEnabledInCurrentCamera()
            if (r3 == 0) goto L_0x0062
            int r3 = r1.intValue()
            if (r3 != r4) goto L_0x0061
            goto L_0x0062
        L_0x0061:
            r0 = 0
        L_0x0062:
            java.lang.String r3 = "Cannot set non-JPEG buffer format with Extensions enabled."
            androidx.core.util.Preconditions.checkArgument(r0, r3)
            androidx.camera.core.impl.MutableConfig r0 = r7.getMutableConfig()
            androidx.camera.core.impl.Config$Option<java.lang.Integer> r3 = androidx.camera.core.impl.ImageInputConfig.OPTION_INPUT_FORMAT
            if (r6 == 0) goto L_0x0070
            goto L_0x0074
        L_0x0070:
            int r2 = r1.intValue()
        L_0x0074:
            java.lang.Integer r6 = java.lang.Integer.valueOf(r2)
            r0.insertOption(r3, r6)
            goto L_0x0136
        L_0x007d:
            androidx.camera.core.impl.MutableConfig r0 = r7.getMutableConfig()
            boolean r0 = isOutputFormatRaw(r0)
            r1 = 32
            if (r0 == 0) goto L_0x0098
            androidx.camera.core.impl.MutableConfig r6 = r7.getMutableConfig()
            androidx.camera.core.impl.Config$Option<java.lang.Integer> r0 = androidx.camera.core.impl.ImageInputConfig.OPTION_INPUT_FORMAT
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r6.insertOption(r0, r1)
            goto L_0x0136
        L_0x0098:
            androidx.camera.core.impl.MutableConfig r0 = r7.getMutableConfig()
            boolean r0 = isOutputFormatRawJpeg(r0)
            if (r0 == 0) goto L_0x00be
            androidx.camera.core.impl.MutableConfig r6 = r7.getMutableConfig()
            androidx.camera.core.impl.Config$Option<java.lang.Integer> r0 = androidx.camera.core.impl.ImageInputConfig.OPTION_INPUT_FORMAT
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r6.insertOption(r0, r1)
            androidx.camera.core.impl.MutableConfig r6 = r7.getMutableConfig()
            androidx.camera.core.impl.Config$Option<java.lang.Integer> r0 = androidx.camera.core.impl.ImageInputConfig.OPTION_SECONDARY_INPUT_FORMAT
            java.lang.Integer r1 = java.lang.Integer.valueOf(r4)
            r6.insertOption(r0, r1)
            goto L_0x0136
        L_0x00be:
            androidx.camera.core.impl.MutableConfig r0 = r7.getMutableConfig()
            boolean r0 = isOutputFormatUltraHdr(r0)
            if (r0 == 0) goto L_0x00e3
            androidx.camera.core.impl.MutableConfig r6 = r7.getMutableConfig()
            androidx.camera.core.impl.Config$Option<java.lang.Integer> r0 = androidx.camera.core.impl.ImageInputConfig.OPTION_INPUT_FORMAT
            r1 = 4101(0x1005, float:5.747E-42)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r6.insertOption(r0, r1)
            androidx.camera.core.impl.MutableConfig r6 = r7.getMutableConfig()
            androidx.camera.core.impl.Config$Option<androidx.camera.core.DynamicRange> r0 = androidx.camera.core.impl.ImageInputConfig.OPTION_INPUT_DYNAMIC_RANGE
            androidx.camera.core.DynamicRange r1 = androidx.camera.core.DynamicRange.UNSPECIFIED
            r6.insertOption(r0, r1)
            goto L_0x0136
        L_0x00e3:
            if (r6 == 0) goto L_0x00f3
            androidx.camera.core.impl.MutableConfig r6 = r7.getMutableConfig()
            androidx.camera.core.impl.Config$Option<java.lang.Integer> r0 = androidx.camera.core.impl.ImageInputConfig.OPTION_INPUT_FORMAT
            java.lang.Integer r1 = java.lang.Integer.valueOf(r2)
            r6.insertOption(r0, r1)
            goto L_0x0136
        L_0x00f3:
            androidx.camera.core.impl.MutableConfig r6 = r7.getMutableConfig()
            androidx.camera.core.impl.Config$Option r0 = androidx.camera.core.impl.ImageCaptureConfig.OPTION_SUPPORTED_RESOLUTIONS
            java.lang.Object r6 = r6.retrieveOption(r0, r3)
            java.util.List r6 = (java.util.List) r6
            if (r6 != 0) goto L_0x010f
            androidx.camera.core.impl.MutableConfig r6 = r7.getMutableConfig()
            androidx.camera.core.impl.Config$Option<java.lang.Integer> r0 = androidx.camera.core.impl.ImageInputConfig.OPTION_INPUT_FORMAT
            java.lang.Integer r1 = java.lang.Integer.valueOf(r4)
            r6.insertOption(r0, r1)
            goto L_0x0136
        L_0x010f:
            boolean r0 = isImageFormatSupported(r6, r4)
            if (r0 == 0) goto L_0x0123
            androidx.camera.core.impl.MutableConfig r6 = r7.getMutableConfig()
            androidx.camera.core.impl.Config$Option<java.lang.Integer> r0 = androidx.camera.core.impl.ImageInputConfig.OPTION_INPUT_FORMAT
            java.lang.Integer r1 = java.lang.Integer.valueOf(r4)
            r6.insertOption(r0, r1)
            goto L_0x0136
        L_0x0123:
            boolean r6 = isImageFormatSupported(r6, r2)
            if (r6 == 0) goto L_0x0136
            androidx.camera.core.impl.MutableConfig r6 = r7.getMutableConfig()
            androidx.camera.core.impl.Config$Option<java.lang.Integer> r0 = androidx.camera.core.impl.ImageInputConfig.OPTION_INPUT_FORMAT
            java.lang.Integer r1 = java.lang.Integer.valueOf(r2)
            r6.insertOption(r0, r1)
        L_0x0136:
            androidx.camera.core.impl.UseCaseConfig r6 = r7.getUseCaseConfig()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.ImageCapture.onMergeConfig(androidx.camera.core.impl.CameraInfoInternal, androidx.camera.core.impl.UseCaseConfig$Builder):androidx.camera.core.impl.UseCaseConfig");
    }

    private static boolean isImageFormatSupported(List<Pair<Integer, Size[]>> list, int i) {
        if (list == null) {
            return false;
        }
        for (Pair<Integer, Size[]> pair : list) {
            if (((Integer) pair.first).equals(Integer.valueOf(i))) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static boolean isOutputFormatUltraHdr(MutableConfig mutableConfig) {
        return Objects.equals(mutableConfig.retrieveOption(ImageCaptureConfig.OPTION_OUTPUT_FORMAT, null), 1);
    }

    /* access modifiers changed from: private */
    public static boolean isOutputFormatRaw(MutableConfig mutableConfig) {
        return Objects.equals(mutableConfig.retrieveOption(ImageCaptureConfig.OPTION_OUTPUT_FORMAT, null), 2);
    }

    /* access modifiers changed from: private */
    public static boolean isOutputFormatRawJpeg(MutableConfig mutableConfig) {
        return Objects.equals(mutableConfig.retrieveOption(ImageCaptureConfig.OPTION_OUTPUT_FORMAT, null), 3);
    }

    public void onCameraControlReady() {
        Logger.d(TAG, "onCameraControlReady");
        trySetFlashModeToCameraControl();
        setScreenFlashToCameraControl();
    }

    private int getCameraLens() {
        CameraInternal camera = getCamera();
        if (camera != null) {
            return camera.getCameraInfo().getLensFacing();
        }
        return -1;
    }

    public int getFlashMode() {
        int i;
        synchronized (this.mLockedFlashMode) {
            i = this.mFlashMode;
            if (i == -1) {
                i = ((ImageCaptureConfig) getCurrentConfig()).getFlashMode(2);
            }
        }
        return i;
    }

    public void setFlashMode(int i) {
        Logger.d(TAG, "setFlashMode: flashMode = " + i);
        if (!(i == 0 || i == 1 || i == 2)) {
            if (i != 3) {
                throw new IllegalArgumentException("Invalid flash mode: " + i);
            } else if (this.mScreenFlashWrapper.getBaseScreenFlash() == null) {
                throw new IllegalArgumentException("ScreenFlash not set for FLASH_MODE_SCREEN");
            } else if (!(getCamera() == null || getCameraLens() == 0)) {
                throw new IllegalArgumentException("Not a front camera despite setting FLASH_MODE_SCREEN");
            }
        }
        synchronized (this.mLockedFlashMode) {
            this.mFlashMode = i;
            trySetFlashModeToCameraControl();
        }
    }

    public void setScreenFlash(ScreenFlash screenFlash) {
        this.mScreenFlashWrapper = ScreenFlashWrapper.from(screenFlash);
        setScreenFlashToCameraControl();
    }

    public ScreenFlash getScreenFlash() {
        return this.mScreenFlashWrapper.getBaseScreenFlash();
    }

    private void setScreenFlashToCameraControl() {
        setScreenFlashToCameraControl(this.mScreenFlashWrapper);
    }

    private void setScreenFlashToCameraControl(ScreenFlash screenFlash) {
        getCameraControl().setScreenFlash(screenFlash);
    }

    public void setCropAspectRatio(Rational rational) {
        this.mCropAspectRatio = rational;
    }

    public int getTargetRotation() {
        return getTargetRotationInternal();
    }

    public void setTargetRotation(int i) {
        int targetRotation = getTargetRotation();
        if (setTargetRotationInternal(i) && this.mCropAspectRatio != null) {
            this.mCropAspectRatio = ImageUtil.getRotatedAspectRatio(Math.abs(CameraOrientationUtil.surfaceRotationToDegrees(i) - CameraOrientationUtil.surfaceRotationToDegrees(targetRotation)), this.mCropAspectRatio);
        }
    }

    public int getCaptureMode() {
        return this.mCaptureMode;
    }

    public int getJpegQuality() {
        return getJpegQualityInternal();
    }

    public ResolutionInfo getResolutionInfo() {
        return getResolutionInfoInternal();
    }

    /* access modifiers changed from: protected */
    public ResolutionInfo getResolutionInfoInternal() {
        CameraInternal camera = getCamera();
        Size attachedSurfaceResolution = getAttachedSurfaceResolution();
        if (camera == null || attachedSurfaceResolution == null) {
            return null;
        }
        Rect viewPortCropRect = getViewPortCropRect();
        Rational rational = this.mCropAspectRatio;
        if (viewPortCropRect == null) {
            if (rational != null) {
                viewPortCropRect = ImageUtil.computeCropRectFromAspectRatio(attachedSurfaceResolution, rational);
            } else {
                viewPortCropRect = new Rect(0, 0, attachedSurfaceResolution.getWidth(), attachedSurfaceResolution.getHeight());
            }
        }
        return new ResolutionInfo(attachedSurfaceResolution, (Rect) Objects.requireNonNull(viewPortCropRect), getRelativeRotation(camera));
    }

    public ResolutionSelector getResolutionSelector() {
        return ((ImageOutputConfig) getCurrentConfig()).getResolutionSelector((ResolutionSelector) null);
    }

    public int getOutputFormat() {
        return ((Integer) Preconditions.checkNotNull((Integer) getCurrentConfig().retrieveOption(ImageCaptureConfig.OPTION_OUTPUT_FORMAT, 0))).intValue();
    }

    /* renamed from: takePicture */
    public void m145lambda$takePicture$1$androidxcameracoreImageCapture(Executor executor, OnImageCapturedCallback onImageCapturedCallback) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            CameraXExecutors.mainThreadExecutor().execute(new ImageCapture$$ExternalSyntheticLambda6(this, executor, onImageCapturedCallback));
        } else {
            takePictureInternal(executor, onImageCapturedCallback, (OnImageSavedCallback) null, (OutputFileOptions) null, (OutputFileOptions) null);
        }
    }

    /* renamed from: takePicture */
    public void m146lambda$takePicture$2$androidxcameracoreImageCapture(OutputFileOptions outputFileOptions, Executor executor, OnImageSavedCallback onImageSavedCallback) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            CameraXExecutors.mainThreadExecutor().execute(new ImageCapture$$ExternalSyntheticLambda3(this, outputFileOptions, executor, onImageSavedCallback));
        } else {
            takePictureInternal(executor, (OnImageCapturedCallback) null, onImageSavedCallback, outputFileOptions, (OutputFileOptions) null);
        }
    }

    /* renamed from: takePicture */
    public void m147lambda$takePicture$3$androidxcameracoreImageCapture(OutputFileOptions outputFileOptions, OutputFileOptions outputFileOptions2, Executor executor, OnImageSavedCallback onImageSavedCallback) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            CameraXExecutors.mainThreadExecutor().execute(new ImageCapture$$ExternalSyntheticLambda1(this, outputFileOptions, outputFileOptions2, executor, onImageSavedCallback));
        } else {
            takePictureInternal(executor, (OnImageCapturedCallback) null, onImageSavedCallback, outputFileOptions, outputFileOptions2);
        }
    }

    public static ImageCaptureCapabilities getImageCaptureCapabilities(CameraInfo cameraInfo) {
        return new ImageCaptureCapabilitiesImpl(cameraInfo);
    }

    private static class ImageCaptureCapabilitiesImpl implements ImageCaptureCapabilities {
        private final CameraInfo mCameraInfo;

        ImageCaptureCapabilitiesImpl(CameraInfo cameraInfo) {
            this.mCameraInfo = cameraInfo;
        }

        public boolean isPostviewSupported() {
            CameraInfo cameraInfo = this.mCameraInfo;
            if (cameraInfo instanceof CameraInfoInternal) {
                return ((CameraInfoInternal) cameraInfo).isPostviewSupported();
            }
            return false;
        }

        public boolean isCaptureProcessProgressSupported() {
            CameraInfo cameraInfo = this.mCameraInfo;
            if (cameraInfo instanceof CameraInfoInternal) {
                return ((CameraInfoInternal) cameraInfo).isCaptureProcessProgressSupported();
            }
            return false;
        }

        public Set<Integer> getSupportedOutputFormats() {
            HashSet hashSet = new HashSet();
            hashSet.add(0);
            if (isUltraHdrSupported()) {
                hashSet.add(1);
            }
            if (isRawSupported()) {
                hashSet.add(2);
                hashSet.add(3);
            }
            return hashSet;
        }

        private boolean isUltraHdrSupported() {
            CameraInfo cameraInfo = this.mCameraInfo;
            if (cameraInfo instanceof CameraInfoInternal) {
                return ((CameraInfoInternal) cameraInfo).getSupportedOutputFormats().contains(4101);
            }
            return false;
        }

        private boolean isRawSupported() {
            CameraInfo cameraInfo = this.mCameraInfo;
            if (cameraInfo instanceof CameraInfoInternal) {
                return ((CameraInfoInternal) cameraInfo).getSupportedOutputFormats().contains(32);
            }
            return false;
        }
    }

    static Rect computeDispatchCropRect(Rect rect, Rational rational, int i, Size size, int i2) {
        if (rect != null) {
            return ImageUtil.computeCropRectFromDispatchInfo(rect, i, size, i2);
        }
        if (rational != null) {
            if (i2 % RotationOptions.ROTATE_180 != 0) {
                rational = new Rational(rational.getDenominator(), rational.getNumerator());
            }
            if (ImageUtil.isAspectRatioValid(size, rational)) {
                return (Rect) Objects.requireNonNull(ImageUtil.computeCropRectFromAspectRatio(size, rational));
            }
        }
        return new Rect(0, 0, size.getWidth(), size.getHeight());
    }

    public void onStateDetached() {
        abortImageCaptureRequests();
    }

    private void abortImageCaptureRequests() {
        this.mScreenFlashWrapper.completePendingTasks();
        TakePictureManager takePictureManager = this.mTakePictureManager;
        if (takePictureManager != null) {
            takePictureManager.abortRequests();
        }
    }

    /* access modifiers changed from: package-private */
    public void lockFlashMode() {
        synchronized (this.mLockedFlashMode) {
            if (this.mLockedFlashMode.get() == null) {
                this.mLockedFlashMode.set(Integer.valueOf(getFlashMode()));
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void unlockFlashMode() {
        /*
            r3 = this;
            java.util.concurrent.atomic.AtomicReference<java.lang.Integer> r0 = r3.mLockedFlashMode
            monitor-enter(r0)
            java.util.concurrent.atomic.AtomicReference<java.lang.Integer> r1 = r3.mLockedFlashMode     // Catch:{ all -> 0x001f }
            r2 = 0
            java.lang.Object r1 = r1.getAndSet(r2)     // Catch:{ all -> 0x001f }
            java.lang.Integer r1 = (java.lang.Integer) r1     // Catch:{ all -> 0x001f }
            if (r1 != 0) goto L_0x0010
            monitor-exit(r0)     // Catch:{ all -> 0x001f }
            return
        L_0x0010:
            int r1 = r1.intValue()     // Catch:{ all -> 0x001f }
            int r2 = r3.getFlashMode()     // Catch:{ all -> 0x001f }
            if (r1 == r2) goto L_0x001d
            r3.trySetFlashModeToCameraControl()     // Catch:{ all -> 0x001f }
        L_0x001d:
            monitor-exit(r0)     // Catch:{ all -> 0x001f }
            return
        L_0x001f:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x001f }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.ImageCapture.unlockFlashMode():void");
    }

    private void trySetFlashModeToCameraControl() {
        synchronized (this.mLockedFlashMode) {
            if (this.mLockedFlashMode.get() == null) {
                getCameraControl().setFlashMode(getFlashMode());
            }
        }
    }

    private int getJpegQualityInternal() {
        ImageCaptureConfig imageCaptureConfig = (ImageCaptureConfig) getCurrentConfig();
        if (imageCaptureConfig.containsOption(ImageCaptureConfig.OPTION_JPEG_COMPRESSION_QUALITY)) {
            return imageCaptureConfig.getJpegQuality();
        }
        int i = this.mCaptureMode;
        if (i == 0) {
            return 100;
        }
        if (i == 1 || i == 2) {
            return 95;
        }
        throw new IllegalStateException("CaptureMode " + this.mCaptureMode + " is invalid");
    }

    public String toString() {
        return "ImageCapture:" + getName();
    }

    static int getError(Throwable th) {
        if (th instanceof CameraClosedException) {
            return 3;
        }
        if (th instanceof ImageCaptureException) {
            return ((ImageCaptureException) th).getImageCaptureError();
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public boolean enforceSoftwareJpegConstraints(MutableConfig mutableConfig) {
        boolean z;
        boolean z2 = false;
        if (Boolean.TRUE.equals(mutableConfig.retrieveOption(ImageCaptureConfig.OPTION_USE_SOFTWARE_JPEG_ENCODER, false))) {
            if (isSessionProcessorEnabledInCurrentCamera()) {
                Logger.w(TAG, "Software JPEG cannot be used with Extensions.");
                z = false;
            } else {
                z = true;
            }
            Integer num = (Integer) mutableConfig.retrieveOption(ImageCaptureConfig.OPTION_BUFFER_FORMAT, null);
            if (num == null || num.intValue() == 256) {
                z2 = z;
            } else {
                Logger.w(TAG, "Software JPEG cannot be used with non-JPEG output buffer format.");
            }
            if (!z2) {
                Logger.w(TAG, "Unable to support software JPEG. Disabling.");
                mutableConfig.insertOption(ImageCaptureConfig.OPTION_USE_SOFTWARE_JPEG_ENCODER, false);
            }
        }
        return z2;
    }

    public void onUnbind() {
        abortImageCaptureRequests();
        clearPipeline();
        setScreenFlashToCameraControl((ScreenFlash) null);
    }

    public void onBind() {
        Preconditions.checkNotNull(getCamera(), "Attached camera cannot be null");
        if (getFlashMode() == 3 && getCameraLens() != 0) {
            throw new IllegalArgumentException("Not a front camera despite setting FLASH_MODE_SCREEN in ImageCapture");
        }
    }

    private SessionProcessor getSessionProcessor() {
        return getCamera().getExtendedConfig().getSessionProcessor((SessionProcessor) null);
    }

    /* access modifiers changed from: protected */
    public StreamSpec onSuggestedStreamSpecUpdated(StreamSpec streamSpec, StreamSpec streamSpec2) {
        SessionConfig.Builder createPipeline = createPipeline(getCameraId(), (ImageCaptureConfig) getCurrentConfig(), streamSpec);
        this.mSessionConfigBuilder = createPipeline;
        updateSessionConfig(Preview$$ExternalSyntheticBackport0.m(new Object[]{createPipeline.build()}));
        notifyActive();
        return streamSpec;
    }

    /* access modifiers changed from: protected */
    public StreamSpec onSuggestedStreamSpecImplementationOptionsUpdated(Config config) {
        this.mSessionConfigBuilder.addImplementationOptions(config);
        updateSessionConfig(Preview$$ExternalSyntheticBackport0.m(new Object[]{this.mSessionConfigBuilder.build()}));
        return getAttachedStreamSpec().toBuilder().setImplementationOptions(config).build();
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x00f7 A[SYNTHETIC, Splitter:B:27:0x00f7] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0124  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x015f  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x016a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private androidx.camera.core.impl.SessionConfig.Builder createPipeline(java.lang.String r19, androidx.camera.core.impl.ImageCaptureConfig r20, androidx.camera.core.impl.StreamSpec r21) {
        /*
            r18 = this;
            r1 = r18
            androidx.camera.core.impl.utils.Threads.checkMainThread()
            java.lang.String r0 = "createPipeline(cameraId: %s, streamSpec: %s)"
            r2 = r19
            r3 = r21
            java.lang.Object[] r2 = new java.lang.Object[]{r2, r3}
            java.lang.String r0 = java.lang.String.format(r0, r2)
            java.lang.String r2 = "ImageCapture"
            android.util.Log.d(r2, r0)
            android.util.Size r6 = r21.getResolution()
            androidx.camera.core.impl.CameraInternal r0 = r18.getCamera()
            java.lang.Object r0 = java.util.Objects.requireNonNull(r0)
            androidx.camera.core.impl.CameraInternal r0 = (androidx.camera.core.impl.CameraInternal) r0
            boolean r0 = r0.getHasTransform()
            r4 = 1
            r9 = r0 ^ 1
            androidx.camera.core.imagecapture.ImagePipeline r0 = r1.mImagePipeline
            if (r0 == 0) goto L_0x0039
            androidx.core.util.Preconditions.checkState(r9)
            androidx.camera.core.imagecapture.ImagePipeline r0 = r1.mImagePipeline
            r0.close()
        L_0x0039:
            androidx.camera.core.impl.UseCaseConfig r0 = r18.getCurrentConfig()
            androidx.camera.core.impl.Config$Option<java.lang.Boolean> r5 = androidx.camera.core.impl.ImageCaptureConfig.OPTION_POSTVIEW_ENABLED
            r7 = 0
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r7)
            java.lang.Object r0 = r0.retrieveOption(r5, r8)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            r5 = 35
            r8 = 0
            if (r0 == 0) goto L_0x00ef
            androidx.camera.core.impl.SessionProcessor r0 = r18.getSessionProcessor()
            if (r0 == 0) goto L_0x00ef
            androidx.camera.core.impl.UseCaseConfig r10 = r18.getCurrentConfig()
            androidx.camera.core.impl.Config$Option<androidx.camera.core.resolutionselector.ResolutionSelector> r11 = androidx.camera.core.impl.ImageCaptureConfig.OPTION_POSTVIEW_RESOLUTION_SELECTOR
            java.lang.Object r10 = r10.retrieveOption(r11, r8)
            r11 = r10
            androidx.camera.core.resolutionselector.ResolutionSelector r11 = (androidx.camera.core.resolutionselector.ResolutionSelector) r11
            java.util.Map r0 = r0.getSupportedPostviewSize(r6)
            java.lang.Integer r10 = java.lang.Integer.valueOf(r5)
            java.lang.Object r10 = r0.get(r10)
            java.util.List r10 = (java.util.List) r10
            if (r10 == 0) goto L_0x007f
            boolean r12 = r10.isEmpty()
            if (r12 == 0) goto L_0x007d
            goto L_0x007f
        L_0x007d:
            r12 = r10
            goto L_0x008c
        L_0x007f:
            r5 = 256(0x100, float:3.59E-43)
            java.lang.Integer r10 = java.lang.Integer.valueOf(r5)
            java.lang.Object r0 = r0.get(r10)
            java.util.List r0 = (java.util.List) r0
            r12 = r0
        L_0x008c:
            if (r12 == 0) goto L_0x00ef
            boolean r0 = r12.isEmpty()
            if (r0 != 0) goto L_0x00ef
            if (r11 == 0) goto L_0x00e1
            androidx.camera.core.impl.utils.CompareSizesByArea r0 = new androidx.camera.core.impl.utils.CompareSizesByArea
            r0.<init>(r4)
            java.util.Collections.sort(r12, r0)
            androidx.camera.core.impl.CameraInternal r0 = r18.getCamera()
            androidx.camera.core.impl.CameraControlInternal r4 = r0.getCameraControlInternal()
            android.graphics.Rect r4 = r4.getSensorRect()
            androidx.camera.core.impl.CameraInfoInternal r0 = r0.getCameraInfoInternal()
            android.util.Rational r15 = new android.util.Rational
            int r10 = r4.width()
            int r4 = r4.height()
            r15.<init>(r10, r4)
            r13 = 0
            int r14 = r18.getTargetRotation()
            int r16 = r0.getSensorRotationDegrees()
            int r17 = r0.getLensFacing()
            java.util.List r0 = androidx.camera.core.internal.SupportedOutputSizesSorter.sortSupportedOutputSizesByResolutionSelector(r11, r12, r13, r14, r15, r16, r17)
            boolean r4 = r0.isEmpty()
            if (r4 != 0) goto L_0x00d9
            java.lang.Object r0 = r0.get(r7)
            android.util.Size r0 = (android.util.Size) r0
            goto L_0x00ec
        L_0x00d9:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r2 = "The postview ResolutionSelector cannot select a valid size for the postview."
            r0.<init>(r2)
            throw r0
        L_0x00e1:
            androidx.camera.core.impl.utils.CompareSizesByArea r0 = new androidx.camera.core.impl.utils.CompareSizesByArea
            r0.<init>()
            java.lang.Object r0 = java.util.Collections.max(r12, r0)
            android.util.Size r0 = (android.util.Size) r0
        L_0x00ec:
            r10 = r0
            r11 = r5
            goto L_0x00f1
        L_0x00ef:
            r11 = r5
            r10 = r8
        L_0x00f1:
            androidx.camera.core.impl.CameraInternal r0 = r18.getCamera()
            if (r0 == 0) goto L_0x0111
            androidx.camera.core.impl.CameraInternal r0 = r18.getCamera()     // Catch:{ Exception -> 0x010b }
            androidx.camera.core.impl.CameraInfoInternal r0 = r0.getCameraInfoInternal()     // Catch:{ Exception -> 0x010b }
            java.lang.Object r0 = r0.getCameraCharacteristics()     // Catch:{ Exception -> 0x010b }
            boolean r4 = r0 instanceof android.hardware.camera2.CameraCharacteristics     // Catch:{ Exception -> 0x010b }
            if (r4 == 0) goto L_0x0111
            android.hardware.camera2.CameraCharacteristics r0 = (android.hardware.camera2.CameraCharacteristics) r0     // Catch:{ Exception -> 0x010b }
            r8 = r0
            goto L_0x0111
        L_0x010b:
            r0 = move-exception
            java.lang.String r4 = "getCameraCharacteristics failed"
            io.sentry.android.core.SentryLogcatAdapter.e(r2, r4, r0)
        L_0x0111:
            r7 = r8
            androidx.camera.core.imagecapture.ImagePipeline r0 = new androidx.camera.core.imagecapture.ImagePipeline
            androidx.camera.core.CameraEffect r8 = r18.getEffect()
            r4 = r0
            r5 = r20
            r4.<init>(r5, r6, r7, r8, r9, r10, r11)
            r1.mImagePipeline = r0
            androidx.camera.core.imagecapture.TakePictureManager r0 = r1.mTakePictureManager
            if (r0 != 0) goto L_0x0134
            androidx.camera.core.impl.UseCaseConfig r0 = r18.getCurrentConfig()
            androidx.camera.core.imagecapture.TakePictureManager$Provider r0 = r0.getTakePictureManagerProvider()
            androidx.camera.core.imagecapture.ImageCaptureControl r2 = r1.mImageCaptureControl
            androidx.camera.core.imagecapture.TakePictureManager r0 = r0.newInstance(r2)
            r1.mTakePictureManager = r0
        L_0x0134:
            androidx.camera.core.imagecapture.TakePictureManager r0 = r1.mTakePictureManager
            androidx.camera.core.imagecapture.ImagePipeline r2 = r1.mImagePipeline
            r0.setImagePipeline(r2)
            androidx.camera.core.imagecapture.ImagePipeline r0 = r1.mImagePipeline
            android.util.Size r2 = r21.getResolution()
            androidx.camera.core.impl.SessionConfig$Builder r0 = r0.createSessionConfigBuilder(r2)
            int r2 = r18.getCaptureMode()
            r4 = 2
            if (r2 != r4) goto L_0x0159
            boolean r2 = r21.getZslDisabled()
            if (r2 != 0) goto L_0x0159
            androidx.camera.core.impl.CameraControlInternal r2 = r18.getCameraControl()
            r2.addZslConfig(r0)
        L_0x0159:
            androidx.camera.core.impl.Config r2 = r21.getImplementationOptions()
            if (r2 == 0) goto L_0x0166
            androidx.camera.core.impl.Config r2 = r21.getImplementationOptions()
            r0.addImplementationOptions(r2)
        L_0x0166:
            androidx.camera.core.impl.SessionConfig$CloseableErrorListener r2 = r1.mCloseableErrorListener
            if (r2 == 0) goto L_0x016d
            r2.close()
        L_0x016d:
            androidx.camera.core.impl.SessionConfig$CloseableErrorListener r2 = new androidx.camera.core.impl.SessionConfig$CloseableErrorListener
            androidx.camera.core.ImageCapture$$ExternalSyntheticLambda5 r3 = new androidx.camera.core.ImageCapture$$ExternalSyntheticLambda5
            r3.<init>(r1)
            r2.<init>(r3)
            r1.mCloseableErrorListener = r2
            r0.setErrorListener(r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.ImageCapture.createPipeline(java.lang.String, androidx.camera.core.impl.ImageCaptureConfig, androidx.camera.core.impl.StreamSpec):androidx.camera.core.impl.SessionConfig$Builder");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$createPipeline$4$androidx-camera-core-ImageCapture  reason: not valid java name */
    public /* synthetic */ void m144lambda$createPipeline$4$androidxcameracoreImageCapture(SessionConfig sessionConfig, SessionConfig.SessionError sessionError) {
        if (getCamera() != null) {
            this.mTakePictureManager.pause();
            clearPipeline(true);
            SessionConfig.Builder createPipeline = createPipeline(getCameraId(), (ImageCaptureConfig) getCurrentConfig(), (StreamSpec) Preconditions.checkNotNull(getAttachedStreamSpec()));
            this.mSessionConfigBuilder = createPipeline;
            updateSessionConfig(Preview$$ExternalSyntheticBackport0.m(new Object[]{createPipeline.build()}));
            notifyReset();
            this.mTakePictureManager.resume();
        }
    }

    private void takePictureInternal(Executor executor, OnImageCapturedCallback onImageCapturedCallback, OnImageSavedCallback onImageSavedCallback, OutputFileOptions outputFileOptions, OutputFileOptions outputFileOptions2) {
        Threads.checkMainThread();
        if (getFlashMode() == 3 && this.mScreenFlashWrapper.getBaseScreenFlash() == null) {
            throw new IllegalArgumentException("ScreenFlash not set for FLASH_MODE_SCREEN");
        }
        Log.d(TAG, "takePictureInternal");
        CameraInternal camera = getCamera();
        if (camera == null) {
            sendInvalidCameraError(executor, onImageCapturedCallback, onImageSavedCallback);
            return;
        }
        boolean z = getCurrentConfig().getSecondaryInputFormat() != 0;
        if (z && outputFileOptions2 == null) {
            throw new IllegalArgumentException("Simultaneous capture RAW and JPEG needs two output file options");
        } else if (z || outputFileOptions2 == null) {
            ((TakePictureManager) Objects.requireNonNull(this.mTakePictureManager)).offerRequest(TakePictureRequest.of(executor, onImageCapturedCallback, onImageSavedCallback, outputFileOptions, outputFileOptions2, getTakePictureCropRect(), getSensorToBufferTransformMatrix(), getRelativeRotation(camera), getJpegQualityInternal(), getCaptureMode(), z, this.mSessionConfigBuilder.getSingleCameraCaptureCallbacks()));
        } else {
            throw new IllegalArgumentException("Non simultaneous capture cannot have two output file options");
        }
    }

    private void sendInvalidCameraError(Executor executor, OnImageCapturedCallback onImageCapturedCallback, OnImageSavedCallback onImageSavedCallback) {
        ImageCaptureException imageCaptureException = new ImageCaptureException(4, "Not bound to a valid Camera [" + this + "]", (Throwable) null);
        if (onImageCapturedCallback != null) {
            onImageCapturedCallback.onError(imageCaptureException);
        } else if (onImageSavedCallback != null) {
            onImageSavedCallback.onError(imageCaptureException);
        } else {
            throw new IllegalArgumentException("Must have either in-memory or on-disk callback.");
        }
    }

    private Rect getTakePictureCropRect() {
        Rect viewPortCropRect = getViewPortCropRect();
        Size size = (Size) Objects.requireNonNull(getAttachedSurfaceResolution());
        if (viewPortCropRect != null) {
            return viewPortCropRect;
        }
        if (!ImageUtil.isAspectRatioValid(this.mCropAspectRatio)) {
            return new Rect(0, 0, size.getWidth(), size.getHeight());
        }
        int relativeRotation = getRelativeRotation((CameraInternal) Objects.requireNonNull(getCamera()));
        Rational rational = new Rational(this.mCropAspectRatio.getDenominator(), this.mCropAspectRatio.getNumerator());
        if (!TransformUtils.is90or270(relativeRotation)) {
            rational = this.mCropAspectRatio;
        }
        return (Rect) Objects.requireNonNull(ImageUtil.computeCropRectFromAspectRatio(size, rational));
    }

    private void clearPipeline() {
        clearPipeline(false);
    }

    private void clearPipeline(boolean z) {
        TakePictureManager takePictureManager;
        Log.d(TAG, "clearPipeline");
        Threads.checkMainThread();
        SessionConfig.CloseableErrorListener closeableErrorListener = this.mCloseableErrorListener;
        if (closeableErrorListener != null) {
            closeableErrorListener.close();
            this.mCloseableErrorListener = null;
        }
        ImagePipeline imagePipeline = this.mImagePipeline;
        if (imagePipeline != null) {
            imagePipeline.close();
            this.mImagePipeline = null;
        }
        if (!z && (takePictureManager = this.mTakePictureManager) != null) {
            takePictureManager.abortRequests();
            this.mTakePictureManager = null;
        }
    }

    /* access modifiers changed from: package-private */
    public ListenableFuture<Void> submitStillCaptureRequest(List<CaptureConfig> list) {
        Threads.checkMainThread();
        return Futures.transform(getCameraControl().submitStillCaptureRequests(list, this.mCaptureMode, this.mFlashType), new ImageCapture$$ExternalSyntheticLambda4(), CameraXExecutors.directExecutor());
    }

    /* access modifiers changed from: package-private */
    public boolean isProcessingPipelineEnabled() {
        return (this.mImagePipeline == null || this.mTakePictureManager == null) ? false : true;
    }

    /* access modifiers changed from: package-private */
    public ImagePipeline getImagePipeline() {
        return this.mImagePipeline;
    }

    /* access modifiers changed from: package-private */
    public TakePictureManager getTakePictureManager() {
        return (TakePictureManager) Objects.requireNonNull(this.mTakePictureManager);
    }

    public Set<Integer> getSupportedEffectTargets() {
        HashSet hashSet = new HashSet();
        hashSet.add(4);
        return hashSet;
    }

    public ImageCaptureLatencyEstimate getRealtimeCaptureLatencyEstimate() {
        CameraInternal camera = getCamera();
        if (camera == null) {
            return ImageCaptureLatencyEstimate.UNDEFINED_IMAGE_CAPTURE_LATENCY;
        }
        Pair<Long, Long> realtimeCaptureLatency = camera.getExtendedConfig().getSessionProcessor().getRealtimeCaptureLatency();
        if (realtimeCaptureLatency == null) {
            return ImageCaptureLatencyEstimate.UNDEFINED_IMAGE_CAPTURE_LATENCY;
        }
        return new ImageCaptureLatencyEstimate(((Long) realtimeCaptureLatency.first).longValue(), ((Long) realtimeCaptureLatency.second).longValue());
    }

    public boolean isPostviewEnabled() {
        return ((Boolean) getCurrentConfig().retrieveOption(ImageCaptureConfig.OPTION_POSTVIEW_ENABLED, false)).booleanValue();
    }

    public ResolutionSelector getPostviewResolutionSelector() {
        return (ResolutionSelector) getCurrentConfig().retrieveOption(ImageCaptureConfig.OPTION_POSTVIEW_RESOLUTION_SELECTOR, null);
    }

    public static final class Defaults implements ConfigProvider<ImageCaptureConfig> {
        private static final int DEFAULT_ASPECT_RATIO = 0;
        private static final ImageCaptureConfig DEFAULT_CONFIG;
        private static final DynamicRange DEFAULT_DYNAMIC_RANGE;
        private static final int DEFAULT_OUTPUT_FORMAT = 0;
        private static final ResolutionSelector DEFAULT_RESOLUTION_SELECTOR;
        private static final int DEFAULT_SURFACE_OCCUPANCY_PRIORITY = 4;

        static {
            ResolutionSelector build = new ResolutionSelector.Builder().setAspectRatioStrategy(AspectRatioStrategy.RATIO_4_3_FALLBACK_AUTO_STRATEGY).setResolutionStrategy(ResolutionStrategy.HIGHEST_AVAILABLE_STRATEGY).build();
            DEFAULT_RESOLUTION_SELECTOR = build;
            DynamicRange dynamicRange = DynamicRange.SDR;
            DEFAULT_DYNAMIC_RANGE = dynamicRange;
            DEFAULT_CONFIG = new Builder().setSurfaceOccupancyPriority(4).setTargetAspectRatio(0).setResolutionSelector(build).setOutputFormat(0).setDynamicRange(dynamicRange).getUseCaseConfig();
        }

        public ImageCaptureConfig getConfig() {
            return DEFAULT_CONFIG;
        }
    }

    public static final class OutputFileOptions {
        private final ContentResolver mContentResolver;
        private final ContentValues mContentValues;
        private final File mFile;
        private final Metadata mMetadata;
        private final OutputStream mOutputStream;
        private final Uri mSaveCollection;

        OutputFileOptions(File file, ContentResolver contentResolver, Uri uri, ContentValues contentValues, OutputStream outputStream, Metadata metadata) {
            this.mFile = file;
            this.mContentResolver = contentResolver;
            this.mSaveCollection = uri;
            this.mContentValues = contentValues;
            this.mOutputStream = outputStream;
            this.mMetadata = metadata == null ? new Metadata() : metadata;
        }

        public File getFile() {
            return this.mFile;
        }

        public ContentResolver getContentResolver() {
            return this.mContentResolver;
        }

        public Uri getSaveCollection() {
            return this.mSaveCollection;
        }

        public ContentValues getContentValues() {
            return this.mContentValues;
        }

        public OutputStream getOutputStream() {
            return this.mOutputStream;
        }

        public Metadata getMetadata() {
            return this.mMetadata;
        }

        public String toString() {
            return "OutputFileOptions{mFile=" + this.mFile + ", mContentResolver=" + this.mContentResolver + ", mSaveCollection=" + this.mSaveCollection + ", mContentValues=" + this.mContentValues + ", mOutputStream=" + this.mOutputStream + ", mMetadata=" + this.mMetadata + "}";
        }

        public static final class Builder {
            private ContentResolver mContentResolver;
            private ContentValues mContentValues;
            private File mFile;
            private Metadata mMetadata;
            private OutputStream mOutputStream;
            private Uri mSaveCollection;

            public Builder(File file) {
                this.mFile = file;
            }

            public Builder(ContentResolver contentResolver, Uri uri, ContentValues contentValues) {
                this.mContentResolver = contentResolver;
                this.mSaveCollection = uri;
                this.mContentValues = contentValues;
            }

            public Builder(OutputStream outputStream) {
                this.mOutputStream = outputStream;
            }

            public Builder setMetadata(Metadata metadata) {
                this.mMetadata = metadata;
                return this;
            }

            public OutputFileOptions build() {
                return new OutputFileOptions(this.mFile, this.mContentResolver, this.mSaveCollection, this.mContentValues, this.mOutputStream, this.mMetadata);
            }
        }
    }

    public static class OutputFileResults {
        private final int mImageFormat;
        private final Uri mSavedUri;

        public OutputFileResults(Uri uri) {
            this(uri, 256);
        }

        public OutputFileResults(Uri uri, int i) {
            this.mSavedUri = uri;
            this.mImageFormat = i;
        }

        public Uri getSavedUri() {
            return this.mSavedUri;
        }

        public int getImageFormat() {
            return this.mImageFormat;
        }
    }

    public static final class Metadata {
        private boolean mIsReversedHorizontal;
        private boolean mIsReversedHorizontalSet = false;
        private boolean mIsReversedVertical;
        private Location mLocation;

        public boolean isReversedHorizontal() {
            return this.mIsReversedHorizontal;
        }

        public boolean isReversedHorizontalSet() {
            return this.mIsReversedHorizontalSet;
        }

        public void setReversedHorizontal(boolean z) {
            this.mIsReversedHorizontal = z;
            this.mIsReversedHorizontalSet = true;
        }

        public boolean isReversedVertical() {
            return this.mIsReversedVertical;
        }

        public void setReversedVertical(boolean z) {
            this.mIsReversedVertical = z;
        }

        public Location getLocation() {
            return this.mLocation;
        }

        public void setLocation(Location location) {
            this.mLocation = location;
        }

        public String toString() {
            return "Metadata{mIsReversedHorizontal=" + this.mIsReversedHorizontal + ", mIsReversedVertical=" + this.mIsReversedVertical + ", mLocation=" + this.mLocation + "}";
        }
    }

    public static final class Builder implements UseCaseConfig.Builder<ImageCapture, ImageCaptureConfig, Builder>, ImageOutputConfig.Builder<Builder>, IoConfig.Builder<Builder>, ImageInputConfig.Builder<Builder> {
        private final MutableOptionsBundle mMutableConfig;

        public Builder() {
            this(MutableOptionsBundle.create());
        }

        private Builder(MutableOptionsBundle mutableOptionsBundle) {
            this.mMutableConfig = mutableOptionsBundle;
            Class cls = (Class) mutableOptionsBundle.retrieveOption(TargetConfig.OPTION_TARGET_CLASS, null);
            if (cls == null || cls.equals(ImageCapture.class)) {
                setCaptureType(UseCaseConfigFactory.CaptureType.IMAGE_CAPTURE);
                setTargetClass((Class<ImageCapture>) ImageCapture.class);
                return;
            }
            throw new IllegalArgumentException("Invalid target class configuration for " + this + ": " + cls);
        }

        public static Builder fromConfig(Config config) {
            return new Builder(MutableOptionsBundle.from(config));
        }

        static Builder fromConfig(ImageCaptureConfig imageCaptureConfig) {
            return new Builder(MutableOptionsBundle.from(imageCaptureConfig));
        }

        public MutableConfig getMutableConfig() {
            return this.mMutableConfig;
        }

        public ImageCaptureConfig getUseCaseConfig() {
            return new ImageCaptureConfig(OptionsBundle.from(this.mMutableConfig));
        }

        public ImageCapture build() {
            Integer num = (Integer) getMutableConfig().retrieveOption(ImageCaptureConfig.OPTION_BUFFER_FORMAT, null);
            if (num != null) {
                getMutableConfig().insertOption(ImageInputConfig.OPTION_INPUT_FORMAT, num);
            } else if (ImageCapture.isOutputFormatRaw(getMutableConfig())) {
                getMutableConfig().insertOption(ImageInputConfig.OPTION_INPUT_FORMAT, 32);
            } else if (ImageCapture.isOutputFormatRawJpeg(getMutableConfig())) {
                getMutableConfig().insertOption(ImageInputConfig.OPTION_INPUT_FORMAT, 32);
                getMutableConfig().insertOption(ImageInputConfig.OPTION_SECONDARY_INPUT_FORMAT, 256);
            } else if (ImageCapture.isOutputFormatUltraHdr(getMutableConfig())) {
                getMutableConfig().insertOption(ImageInputConfig.OPTION_INPUT_FORMAT, 4101);
                getMutableConfig().insertOption(ImageInputConfig.OPTION_INPUT_DYNAMIC_RANGE, DynamicRange.UNSPECIFIED);
            } else {
                getMutableConfig().insertOption(ImageInputConfig.OPTION_INPUT_FORMAT, 256);
            }
            ImageCaptureConfig useCaseConfig = getUseCaseConfig();
            ImageOutputConfig.validateConfig(useCaseConfig);
            ImageCapture imageCapture = new ImageCapture(useCaseConfig);
            Size size = (Size) getMutableConfig().retrieveOption(ImageCaptureConfig.OPTION_TARGET_RESOLUTION, null);
            if (size != null) {
                imageCapture.setCropAspectRatio(new Rational(size.getWidth(), size.getHeight()));
            }
            Preconditions.checkNotNull((Executor) getMutableConfig().retrieveOption(ImageCaptureConfig.OPTION_IO_EXECUTOR, CameraXExecutors.ioExecutor()), "The IO executor can't be null");
            if (getMutableConfig().containsOption(ImageCaptureConfig.OPTION_FLASH_MODE)) {
                Integer num2 = (Integer) getMutableConfig().retrieveOption(ImageCaptureConfig.OPTION_FLASH_MODE);
                if (num2 == null || !(num2.intValue() == 0 || num2.intValue() == 1 || num2.intValue() == 3 || num2.intValue() == 2)) {
                    throw new IllegalArgumentException("The flash mode is not allowed to set: " + num2);
                } else if (num2.intValue() == 3 && getMutableConfig().retrieveOption(ImageCaptureConfig.OPTION_SCREEN_FLASH, null) == null) {
                    throw new IllegalArgumentException("The flash mode is not allowed to set to FLASH_MODE_SCREEN without setting ScreenFlash");
                }
            }
            return imageCapture;
        }

        public Builder setCaptureMode(int i) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_IMAGE_CAPTURE_MODE, Integer.valueOf(i));
            return this;
        }

        public Builder setFlashMode(int i) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_FLASH_MODE, Integer.valueOf(i));
            return this;
        }

        public Builder setScreenFlash(ScreenFlash screenFlash) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_SCREEN_FLASH, screenFlash);
            return this;
        }

        public Builder setBufferFormat(int i) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_BUFFER_FORMAT, Integer.valueOf(i));
            return this;
        }

        public Builder setSupportedResolutions(List<Pair<Integer, Size[]>> list) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_SUPPORTED_RESOLUTIONS, list);
            return this;
        }

        public Builder setCustomOrderedResolutions(List<Size> list) {
            getMutableConfig().insertOption(ImageOutputConfig.OPTION_CUSTOM_ORDERED_RESOLUTIONS, list);
            return this;
        }

        public Builder setTargetClass(Class<ImageCapture> cls) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_TARGET_CLASS, cls);
            if (getMutableConfig().retrieveOption(ImageCaptureConfig.OPTION_TARGET_NAME, null) == null) {
                setTargetName(cls.getCanonicalName() + "-" + UUID.randomUUID());
            }
            return this;
        }

        public Builder setTargetName(String str) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_TARGET_NAME, str);
            return this;
        }

        @Deprecated
        public Builder setTargetAspectRatio(int i) {
            if (i == -1) {
                i = 0;
            }
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_TARGET_ASPECT_RATIO, Integer.valueOf(i));
            return this;
        }

        public Builder setTargetRotation(int i) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_TARGET_ROTATION, Integer.valueOf(i));
            return this;
        }

        public Builder setMirrorMode(int i) {
            throw new UnsupportedOperationException("setMirrorMode is not supported.");
        }

        @Deprecated
        public Builder setTargetResolution(Size size) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_TARGET_RESOLUTION, size);
            return this;
        }

        public Builder setDefaultResolution(Size size) {
            getMutableConfig().insertOption(ImageOutputConfig.OPTION_DEFAULT_RESOLUTION, size);
            return this;
        }

        public Builder setMaxResolution(Size size) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_MAX_RESOLUTION, size);
            return this;
        }

        public Builder setResolutionSelector(ResolutionSelector resolutionSelector) {
            getMutableConfig().insertOption(ImageOutputConfig.OPTION_RESOLUTION_SELECTOR, resolutionSelector);
            return this;
        }

        public Builder setPostviewEnabled(boolean z) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_POSTVIEW_ENABLED, Boolean.valueOf(z));
            return this;
        }

        public Builder setPostviewResolutionSelector(ResolutionSelector resolutionSelector) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_POSTVIEW_RESOLUTION_SELECTOR, resolutionSelector);
            return this;
        }

        public Builder setImageReaderProxyProvider(ImageReaderProxyProvider imageReaderProxyProvider) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_IMAGE_READER_PROXY_PROVIDER, imageReaderProxyProvider);
            return this;
        }

        public Builder setSoftwareJpegEncoderRequested(boolean z) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_USE_SOFTWARE_JPEG_ENCODER, Boolean.valueOf(z));
            return this;
        }

        public Builder setFlashType(int i) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_FLASH_TYPE, Integer.valueOf(i));
            return this;
        }

        public Builder setJpegQuality(int i) {
            Preconditions.checkArgumentInRange(i, 1, 100, "jpegQuality");
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_JPEG_COMPRESSION_QUALITY, Integer.valueOf(i));
            return this;
        }

        public Builder setIoExecutor(Executor executor) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_IO_EXECUTOR, executor);
            return this;
        }

        public Builder setDefaultSessionConfig(SessionConfig sessionConfig) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_DEFAULT_SESSION_CONFIG, sessionConfig);
            return this;
        }

        public Builder setOutputFormat(int i) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_OUTPUT_FORMAT, Integer.valueOf(i));
            return this;
        }

        public Builder setDefaultCaptureConfig(CaptureConfig captureConfig) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_DEFAULT_CAPTURE_CONFIG, captureConfig);
            return this;
        }

        public Builder setSessionOptionUnpacker(SessionConfig.OptionUnpacker optionUnpacker) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_SESSION_CONFIG_UNPACKER, optionUnpacker);
            return this;
        }

        public Builder setCaptureOptionUnpacker(CaptureConfig.OptionUnpacker optionUnpacker) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_CAPTURE_CONFIG_UNPACKER, optionUnpacker);
            return this;
        }

        public Builder setSurfaceOccupancyPriority(int i) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_SURFACE_OCCUPANCY_PRIORITY, Integer.valueOf(i));
            return this;
        }

        public Builder setZslDisabled(boolean z) {
            getMutableConfig().insertOption(UseCaseConfig.OPTION_ZSL_DISABLED, Boolean.valueOf(z));
            return this;
        }

        public Builder setHighResolutionDisabled(boolean z) {
            getMutableConfig().insertOption(UseCaseConfig.OPTION_HIGH_RESOLUTION_DISABLED, Boolean.valueOf(z));
            return this;
        }

        public Builder setCaptureType(UseCaseConfigFactory.CaptureType captureType) {
            getMutableConfig().insertOption(UseCaseConfig.OPTION_CAPTURE_TYPE, captureType);
            return this;
        }

        public Builder setDynamicRange(DynamicRange dynamicRange) {
            getMutableConfig().insertOption(ImageInputConfig.OPTION_INPUT_DYNAMIC_RANGE, dynamicRange);
            return this;
        }
    }
}

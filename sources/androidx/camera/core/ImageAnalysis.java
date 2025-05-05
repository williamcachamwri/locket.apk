package androidx.camera.core;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Pair;
import android.util.Size;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.ConfigProvider;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.ImageAnalysisConfig;
import androidx.camera.core.impl.ImageInputConfig;
import androidx.camera.core.impl.ImageOutputConfig;
import androidx.camera.core.impl.ImmediateSurface;
import androidx.camera.core.impl.MutableConfig;
import androidx.camera.core.impl.MutableOptionsBundle;
import androidx.camera.core.impl.OptionsBundle;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.StreamSpec;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.internal.TargetConfig;
import androidx.camera.core.internal.ThreadConfig;
import androidx.camera.core.internal.utils.SizeUtil;
import androidx.camera.core.resolutionselector.AspectRatioStrategy;
import androidx.camera.core.resolutionselector.ResolutionSelector;
import androidx.camera.core.resolutionselector.ResolutionStrategy;
import androidx.core.util.Preconditions;
import com.facebook.imagepipeline.common.RotationOptions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.Executor;

public final class ImageAnalysis extends UseCase {
    public static final int COORDINATE_SYSTEM_ORIGINAL = 0;
    public static final int COORDINATE_SYSTEM_SENSOR = 2;
    public static final int COORDINATE_SYSTEM_VIEW_REFERENCED = 1;
    private static final int DEFAULT_BACKPRESSURE_STRATEGY = 0;
    public static final Defaults DEFAULT_CONFIG = new Defaults();
    private static final int DEFAULT_IMAGE_QUEUE_DEPTH = 6;
    private static final Boolean DEFAULT_ONE_PIXEL_SHIFT_ENABLED = null;
    private static final int DEFAULT_OUTPUT_IMAGE_FORMAT = 1;
    private static final boolean DEFAULT_OUTPUT_IMAGE_ROTATION_ENABLED = false;
    private static final int NON_BLOCKING_IMAGE_DEPTH = 4;
    public static final int OUTPUT_IMAGE_FORMAT_RGBA_8888 = 2;
    public static final int OUTPUT_IMAGE_FORMAT_YUV_420_888 = 1;
    public static final int STRATEGY_BLOCK_PRODUCER = 1;
    public static final int STRATEGY_KEEP_ONLY_LATEST = 0;
    private static final String TAG = "ImageAnalysis";
    private final Object mAnalysisLock = new Object();
    private SessionConfig.CloseableErrorListener mCloseableErrorListener;
    private DeferrableSurface mDeferrableSurface;
    final ImageAnalysisAbstractAnalyzer mImageAnalysisAbstractAnalyzer;
    SessionConfig.Builder mSessionConfigBuilder;
    private Analyzer mSubscribedAnalyzer;

    public interface Analyzer {
        void analyze(ImageProxy imageProxy);

        Size getDefaultTargetResolution() {
            return null;
        }

        int getTargetCoordinateSystem() {
            return 0;
        }

        void updateTransform(Matrix matrix) {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface BackpressureStrategy {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface OutputImageFormat {
    }

    ImageAnalysis(ImageAnalysisConfig imageAnalysisConfig) {
        super(imageAnalysisConfig);
        if (((ImageAnalysisConfig) getCurrentConfig()).getBackpressureStrategy(0) == 1) {
            this.mImageAnalysisAbstractAnalyzer = new ImageAnalysisBlockingAnalyzer();
        } else {
            this.mImageAnalysisAbstractAnalyzer = new ImageAnalysisNonBlockingAnalyzer(imageAnalysisConfig.getBackgroundExecutor(CameraXExecutors.highPriorityExecutor()));
        }
        this.mImageAnalysisAbstractAnalyzer.setOutputImageFormat(getOutputImageFormat());
        this.mImageAnalysisAbstractAnalyzer.setOutputImageRotationEnabled(isOutputImageRotationEnabled());
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [androidx.camera.core.impl.UseCaseConfig$Builder, androidx.camera.core.impl.UseCaseConfig$Builder<?, ?, ?>] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.camera.core.impl.UseCaseConfig<?> onMergeConfig(androidx.camera.core.impl.CameraInfoInternal r6, androidx.camera.core.impl.UseCaseConfig.Builder<?, ?, ?> r7) {
        /*
            r5 = this;
            java.lang.Boolean r0 = r5.getOnePixelShiftEnabled()
            androidx.camera.core.impl.Quirks r1 = r6.getCameraQuirks()
            java.lang.Class<androidx.camera.core.internal.compat.quirk.OnePixelShiftQuirk> r2 = androidx.camera.core.internal.compat.quirk.OnePixelShiftQuirk.class
            boolean r1 = r1.contains(r2)
            androidx.camera.core.ImageAnalysisAbstractAnalyzer r2 = r5.mImageAnalysisAbstractAnalyzer
            if (r0 != 0) goto L_0x0013
            goto L_0x0017
        L_0x0013:
            boolean r1 = r0.booleanValue()
        L_0x0017:
            r2.setOnePixelShiftEnabled(r1)
            java.lang.Object r0 = r5.mAnalysisLock
            monitor-enter(r0)
            androidx.camera.core.ImageAnalysis$Analyzer r1 = r5.mSubscribedAnalyzer     // Catch:{ all -> 0x00c3 }
            r2 = 0
            if (r1 == 0) goto L_0x0027
            android.util.Size r1 = r1.getDefaultTargetResolution()     // Catch:{ all -> 0x00c3 }
            goto L_0x0028
        L_0x0027:
            r1 = r2
        L_0x0028:
            monitor-exit(r0)     // Catch:{ all -> 0x00c3 }
            if (r1 != 0) goto L_0x0030
            androidx.camera.core.impl.UseCaseConfig r6 = r7.getUseCaseConfig()
            return r6
        L_0x0030:
            androidx.camera.core.impl.MutableConfig r0 = r7.getMutableConfig()
            androidx.camera.core.impl.Config$Option<java.lang.Integer> r3 = androidx.camera.core.impl.ImageOutputConfig.OPTION_TARGET_ROTATION
            r4 = 0
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            java.lang.Object r0 = r0.retrieveOption(r3, r4)
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            int r6 = r6.getSensorRotationDegrees(r0)
            int r6 = r6 % 180
            r0 = 90
            if (r6 != r0) goto L_0x005d
            android.util.Size r6 = new android.util.Size
            int r0 = r1.getHeight()
            int r1 = r1.getWidth()
            r6.<init>(r0, r1)
            r1 = r6
        L_0x005d:
            androidx.camera.core.impl.UseCaseConfig r6 = r7.getUseCaseConfig()
            androidx.camera.core.impl.Config$Option<android.util.Size> r0 = androidx.camera.core.impl.ImageOutputConfig.OPTION_TARGET_RESOLUTION
            boolean r6 = r6.containsOption(r0)
            if (r6 != 0) goto L_0x0072
            androidx.camera.core.impl.MutableConfig r6 = r7.getMutableConfig()
            androidx.camera.core.impl.Config$Option<android.util.Size> r0 = androidx.camera.core.impl.ImageOutputConfig.OPTION_TARGET_RESOLUTION
            r6.insertOption(r0, r1)
        L_0x0072:
            androidx.camera.core.impl.UseCaseConfig r6 = r7.getUseCaseConfig()
            androidx.camera.core.impl.Config$Option<androidx.camera.core.resolutionselector.ResolutionSelector> r0 = androidx.camera.core.impl.ImageOutputConfig.OPTION_RESOLUTION_SELECTOR
            boolean r6 = r6.containsOption(r0)
            if (r6 == 0) goto L_0x00be
            androidx.camera.core.impl.UseCaseConfig r6 = r5.getAppConfig()
            androidx.camera.core.impl.Config$Option<androidx.camera.core.resolutionselector.ResolutionSelector> r0 = androidx.camera.core.impl.ImageOutputConfig.OPTION_RESOLUTION_SELECTOR
            java.lang.Object r6 = r6.retrieveOption(r0, r2)
            androidx.camera.core.resolutionselector.ResolutionSelector r6 = (androidx.camera.core.resolutionselector.ResolutionSelector) r6
            if (r6 != 0) goto L_0x0092
            androidx.camera.core.resolutionselector.ResolutionSelector$Builder r0 = new androidx.camera.core.resolutionselector.ResolutionSelector$Builder
            r0.<init>()
            goto L_0x0096
        L_0x0092:
            androidx.camera.core.resolutionselector.ResolutionSelector$Builder r0 = androidx.camera.core.resolutionselector.ResolutionSelector.Builder.fromResolutionSelector(r6)
        L_0x0096:
            if (r6 == 0) goto L_0x009e
            androidx.camera.core.resolutionselector.ResolutionStrategy r2 = r6.getResolutionStrategy()
            if (r2 != 0) goto L_0x00a7
        L_0x009e:
            androidx.camera.core.resolutionselector.ResolutionStrategy r2 = new androidx.camera.core.resolutionselector.ResolutionStrategy
            r3 = 1
            r2.<init>(r1, r3)
            r0.setResolutionStrategy(r2)
        L_0x00a7:
            if (r6 != 0) goto L_0x00b1
            androidx.camera.core.ImageAnalysis$$ExternalSyntheticLambda4 r6 = new androidx.camera.core.ImageAnalysis$$ExternalSyntheticLambda4
            r6.<init>(r1)
            r0.setResolutionFilter(r6)
        L_0x00b1:
            androidx.camera.core.impl.MutableConfig r6 = r7.getMutableConfig()
            androidx.camera.core.impl.Config$Option<androidx.camera.core.resolutionselector.ResolutionSelector> r1 = androidx.camera.core.impl.ImageOutputConfig.OPTION_RESOLUTION_SELECTOR
            androidx.camera.core.resolutionselector.ResolutionSelector r0 = r0.build()
            r6.insertOption(r1, r0)
        L_0x00be:
            androidx.camera.core.impl.UseCaseConfig r6 = r7.getUseCaseConfig()
            return r6
        L_0x00c3:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00c3 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.ImageAnalysis.onMergeConfig(androidx.camera.core.impl.CameraInfoInternal, androidx.camera.core.impl.UseCaseConfig$Builder):androidx.camera.core.impl.UseCaseConfig");
    }

    static /* synthetic */ List lambda$onMergeConfig$0(Size size, List list, int i) {
        ArrayList arrayList = new ArrayList(list);
        if (arrayList.contains(size)) {
            arrayList.remove(size);
            arrayList.add(0, size);
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public SessionConfig.Builder createPipeline(String str, ImageAnalysisConfig imageAnalysisConfig, StreamSpec streamSpec) {
        SafeCloseImageReaderProxy safeCloseImageReaderProxy;
        SafeCloseImageReaderProxy safeCloseImageReaderProxy2;
        Threads.checkMainThread();
        Size resolution = streamSpec.getResolution();
        Executor executor = (Executor) Preconditions.checkNotNull(imageAnalysisConfig.getBackgroundExecutor(CameraXExecutors.highPriorityExecutor()));
        boolean z = true;
        int imageQueueDepth = getBackpressureStrategy() == 1 ? getImageQueueDepth() : 4;
        if (imageAnalysisConfig.getImageReaderProxyProvider() != null) {
            safeCloseImageReaderProxy = new SafeCloseImageReaderProxy(imageAnalysisConfig.getImageReaderProxyProvider().newInstance(resolution.getWidth(), resolution.getHeight(), getImageFormat(), imageQueueDepth, 0));
        } else {
            safeCloseImageReaderProxy = new SafeCloseImageReaderProxy(ImageReaderProxys.createIsolatedReader(resolution.getWidth(), resolution.getHeight(), getImageFormat(), imageQueueDepth));
        }
        boolean isFlipWH = getCamera() != null ? isFlipWH(getCamera()) : false;
        int height = isFlipWH ? resolution.getHeight() : resolution.getWidth();
        int width = isFlipWH ? resolution.getWidth() : resolution.getHeight();
        int i = getOutputImageFormat() == 2 ? 1 : 35;
        boolean z2 = getImageFormat() == 35 && getOutputImageFormat() == 2;
        if (getImageFormat() != 35 || ((getCamera() == null || getRelativeRotation(getCamera()) == 0) && !Boolean.TRUE.equals(getOnePixelShiftEnabled()))) {
            z = false;
        }
        if (z2 || z) {
            safeCloseImageReaderProxy2 = new SafeCloseImageReaderProxy(ImageReaderProxys.createIsolatedReader(height, width, i, safeCloseImageReaderProxy.getMaxImages()));
        } else {
            safeCloseImageReaderProxy2 = null;
        }
        if (safeCloseImageReaderProxy2 != null) {
            this.mImageAnalysisAbstractAnalyzer.setProcessedImageReaderProxy(safeCloseImageReaderProxy2);
        }
        tryUpdateRelativeRotation();
        safeCloseImageReaderProxy.setOnImageAvailableListener(this.mImageAnalysisAbstractAnalyzer, executor);
        SessionConfig.Builder createFrom = SessionConfig.Builder.createFrom(imageAnalysisConfig, streamSpec.getResolution());
        if (streamSpec.getImplementationOptions() != null) {
            createFrom.addImplementationOptions(streamSpec.getImplementationOptions());
        }
        DeferrableSurface deferrableSurface = this.mDeferrableSurface;
        if (deferrableSurface != null) {
            deferrableSurface.close();
        }
        ImmediateSurface immediateSurface = new ImmediateSurface(safeCloseImageReaderProxy.getSurface(), resolution, getImageFormat());
        this.mDeferrableSurface = immediateSurface;
        immediateSurface.getTerminationFuture().addListener(new ImageAnalysis$$ExternalSyntheticLambda2(safeCloseImageReaderProxy, safeCloseImageReaderProxy2), CameraXExecutors.mainThreadExecutor());
        applyExpectedFrameRateRange(createFrom, streamSpec);
        createFrom.addSurface(this.mDeferrableSurface, streamSpec.getDynamicRange(), (String) null, -1);
        SessionConfig.CloseableErrorListener closeableErrorListener = this.mCloseableErrorListener;
        if (closeableErrorListener != null) {
            closeableErrorListener.close();
        }
        SessionConfig.CloseableErrorListener closeableErrorListener2 = new SessionConfig.CloseableErrorListener(new ImageAnalysis$$ExternalSyntheticLambda3(this));
        this.mCloseableErrorListener = closeableErrorListener2;
        createFrom.setErrorListener(closeableErrorListener2);
        return createFrom;
    }

    static /* synthetic */ void lambda$createPipeline$1(SafeCloseImageReaderProxy safeCloseImageReaderProxy, SafeCloseImageReaderProxy safeCloseImageReaderProxy2) {
        safeCloseImageReaderProxy.safeClose();
        if (safeCloseImageReaderProxy2 != null) {
            safeCloseImageReaderProxy2.safeClose();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$createPipeline$2$androidx-camera-core-ImageAnalysis  reason: not valid java name */
    public /* synthetic */ void m140lambda$createPipeline$2$androidxcameracoreImageAnalysis(SessionConfig sessionConfig, SessionConfig.SessionError sessionError) {
        if (getCamera() != null) {
            clearPipeline();
            this.mImageAnalysisAbstractAnalyzer.clearCache();
            SessionConfig.Builder createPipeline = createPipeline(getCameraId(), (ImageAnalysisConfig) getCurrentConfig(), (StreamSpec) Preconditions.checkNotNull(getAttachedStreamSpec()));
            this.mSessionConfigBuilder = createPipeline;
            updateSessionConfig(Preview$$ExternalSyntheticBackport0.m(new Object[]{createPipeline.build()}));
            notifyReset();
        }
    }

    /* access modifiers changed from: package-private */
    public void clearPipeline() {
        Threads.checkMainThread();
        SessionConfig.CloseableErrorListener closeableErrorListener = this.mCloseableErrorListener;
        if (closeableErrorListener != null) {
            closeableErrorListener.close();
            this.mCloseableErrorListener = null;
        }
        DeferrableSurface deferrableSurface = this.mDeferrableSurface;
        if (deferrableSurface != null) {
            deferrableSurface.close();
            this.mDeferrableSurface = null;
        }
    }

    public void clearAnalyzer() {
        synchronized (this.mAnalysisLock) {
            this.mImageAnalysisAbstractAnalyzer.setAnalyzer((Executor) null, (Analyzer) null);
            if (this.mSubscribedAnalyzer != null) {
                notifyInactive();
            }
            this.mSubscribedAnalyzer = null;
        }
    }

    public int getTargetRotation() {
        return getTargetRotationInternal();
    }

    public void setTargetRotation(int i) {
        if (setTargetRotationInternal(i)) {
            tryUpdateRelativeRotation();
        }
    }

    public void setAnalyzer(Executor executor, Analyzer analyzer) {
        synchronized (this.mAnalysisLock) {
            this.mImageAnalysisAbstractAnalyzer.setAnalyzer(executor, new ImageAnalysis$$ExternalSyntheticLambda5(analyzer));
            if (this.mSubscribedAnalyzer == null) {
                notifyActive();
            }
            this.mSubscribedAnalyzer = analyzer;
        }
    }

    public void setViewPortCropRect(Rect rect) {
        super.setViewPortCropRect(rect);
        this.mImageAnalysisAbstractAnalyzer.setViewPortCropRect(rect);
    }

    public void setSensorToBufferTransformMatrix(Matrix matrix) {
        super.setSensorToBufferTransformMatrix(matrix);
        this.mImageAnalysisAbstractAnalyzer.setSensorToBufferTransformMatrix(matrix);
    }

    private boolean isFlipWH(CameraInternal cameraInternal) {
        if (!isOutputImageRotationEnabled() || getRelativeRotation(cameraInternal) % RotationOptions.ROTATE_180 == 0) {
            return false;
        }
        return true;
    }

    public int getBackpressureStrategy() {
        return ((ImageAnalysisConfig) getCurrentConfig()).getBackpressureStrategy(0);
    }

    public Executor getBackgroundExecutor() {
        return ((ImageAnalysisConfig) getCurrentConfig()).getBackgroundExecutor((Executor) null);
    }

    public int getImageQueueDepth() {
        return ((ImageAnalysisConfig) getCurrentConfig()).getImageQueueDepth(6);
    }

    public int getOutputImageFormat() {
        return ((ImageAnalysisConfig) getCurrentConfig()).getOutputImageFormat(1);
    }

    public boolean isOutputImageRotationEnabled() {
        return ((ImageAnalysisConfig) getCurrentConfig()).isOutputImageRotationEnabled(false).booleanValue();
    }

    public Boolean getOnePixelShiftEnabled() {
        return ((ImageAnalysisConfig) getCurrentConfig()).getOnePixelShiftEnabled(DEFAULT_ONE_PIXEL_SHIFT_ENABLED);
    }

    public ResolutionInfo getResolutionInfo() {
        return getResolutionInfoInternal();
    }

    public ResolutionSelector getResolutionSelector() {
        return ((ImageOutputConfig) getCurrentConfig()).getResolutionSelector((ResolutionSelector) null);
    }

    public String toString() {
        return "ImageAnalysis:" + getName();
    }

    public void onUnbind() {
        clearPipeline();
        this.mImageAnalysisAbstractAnalyzer.detach();
    }

    public UseCaseConfig<?> getDefaultConfig(boolean z, UseCaseConfigFactory useCaseConfigFactory) {
        Defaults defaults = DEFAULT_CONFIG;
        Config config = useCaseConfigFactory.getConfig(defaults.getConfig().getCaptureType(), 1);
        if (z) {
            config = Config.mergeConfigs(config, defaults.getConfig());
        }
        if (config == null) {
            return null;
        }
        return getUseCaseConfigBuilder(config).getUseCaseConfig();
    }

    public void onBind() {
        this.mImageAnalysisAbstractAnalyzer.attach();
    }

    public UseCaseConfig.Builder<?, ?, ?> getUseCaseConfigBuilder(Config config) {
        return Builder.fromConfig(config);
    }

    /* access modifiers changed from: protected */
    public StreamSpec onSuggestedStreamSpecUpdated(StreamSpec streamSpec, StreamSpec streamSpec2) {
        SessionConfig.Builder createPipeline = createPipeline(getCameraId(), (ImageAnalysisConfig) getCurrentConfig(), streamSpec);
        this.mSessionConfigBuilder = createPipeline;
        updateSessionConfig(Preview$$ExternalSyntheticBackport0.m(new Object[]{createPipeline.build()}));
        return streamSpec;
    }

    /* access modifiers changed from: protected */
    public StreamSpec onSuggestedStreamSpecImplementationOptionsUpdated(Config config) {
        this.mSessionConfigBuilder.addImplementationOptions(config);
        updateSessionConfig(Preview$$ExternalSyntheticBackport0.m(new Object[]{this.mSessionConfigBuilder.build()}));
        return getAttachedStreamSpec().toBuilder().setImplementationOptions(config).build();
    }

    private void tryUpdateRelativeRotation() {
        CameraInternal camera = getCamera();
        if (camera != null) {
            this.mImageAnalysisAbstractAnalyzer.setRelativeRotation(getRelativeRotation(camera));
        }
    }

    public static final class Defaults implements ConfigProvider<ImageAnalysisConfig> {
        private static final int DEFAULT_ASPECT_RATIO = 0;
        private static final ImageAnalysisConfig DEFAULT_CONFIG;
        private static final DynamicRange DEFAULT_DYNAMIC_RANGE;
        private static final ResolutionSelector DEFAULT_RESOLUTION_SELECTOR;
        private static final int DEFAULT_SURFACE_OCCUPANCY_PRIORITY = 1;
        private static final Size DEFAULT_TARGET_RESOLUTION;

        static {
            Size size = new Size(640, 480);
            DEFAULT_TARGET_RESOLUTION = size;
            DynamicRange dynamicRange = DynamicRange.SDR;
            DEFAULT_DYNAMIC_RANGE = dynamicRange;
            ResolutionSelector build = new ResolutionSelector.Builder().setAspectRatioStrategy(AspectRatioStrategy.RATIO_4_3_FALLBACK_AUTO_STRATEGY).setResolutionStrategy(new ResolutionStrategy(SizeUtil.RESOLUTION_VGA, 1)).build();
            DEFAULT_RESOLUTION_SELECTOR = build;
            DEFAULT_CONFIG = new Builder().setDefaultResolution(size).setSurfaceOccupancyPriority(1).setTargetAspectRatio(0).setResolutionSelector(build).setDynamicRange(dynamicRange).getUseCaseConfig();
        }

        public ImageAnalysisConfig getConfig() {
            return DEFAULT_CONFIG;
        }
    }

    public static final class Builder implements ImageOutputConfig.Builder<Builder>, ThreadConfig.Builder<Builder>, UseCaseConfig.Builder<ImageAnalysis, ImageAnalysisConfig, Builder>, ImageInputConfig.Builder<Builder> {
        private final MutableOptionsBundle mMutableConfig;

        public Builder() {
            this(MutableOptionsBundle.create());
        }

        private Builder(MutableOptionsBundle mutableOptionsBundle) {
            this.mMutableConfig = mutableOptionsBundle;
            Class cls = (Class) mutableOptionsBundle.retrieveOption(TargetConfig.OPTION_TARGET_CLASS, null);
            if (cls == null || cls.equals(ImageAnalysis.class)) {
                setCaptureType(UseCaseConfigFactory.CaptureType.IMAGE_ANALYSIS);
                setTargetClass((Class<ImageAnalysis>) ImageAnalysis.class);
                return;
            }
            throw new IllegalArgumentException("Invalid target class configuration for " + this + ": " + cls);
        }

        static Builder fromConfig(Config config) {
            return new Builder(MutableOptionsBundle.from(config));
        }

        public static Builder fromConfig(ImageAnalysisConfig imageAnalysisConfig) {
            return new Builder(MutableOptionsBundle.from(imageAnalysisConfig));
        }

        public Builder setBackpressureStrategy(int i) {
            getMutableConfig().insertOption(ImageAnalysisConfig.OPTION_BACKPRESSURE_STRATEGY, Integer.valueOf(i));
            return this;
        }

        public Builder setImageQueueDepth(int i) {
            getMutableConfig().insertOption(ImageAnalysisConfig.OPTION_IMAGE_QUEUE_DEPTH, Integer.valueOf(i));
            return this;
        }

        public Builder setOutputImageFormat(int i) {
            getMutableConfig().insertOption(ImageAnalysisConfig.OPTION_OUTPUT_IMAGE_FORMAT, Integer.valueOf(i));
            return this;
        }

        public Builder setOutputImageRotationEnabled(boolean z) {
            getMutableConfig().insertOption(ImageAnalysisConfig.OPTION_OUTPUT_IMAGE_ROTATION_ENABLED, Boolean.valueOf(z));
            return this;
        }

        public Builder setOnePixelShiftEnabled(boolean z) {
            getMutableConfig().insertOption(ImageAnalysisConfig.OPTION_ONE_PIXEL_SHIFT_ENABLED, Boolean.valueOf(z));
            return this;
        }

        public MutableConfig getMutableConfig() {
            return this.mMutableConfig;
        }

        public ImageAnalysisConfig getUseCaseConfig() {
            return new ImageAnalysisConfig(OptionsBundle.from(this.mMutableConfig));
        }

        public ImageAnalysis build() {
            ImageAnalysisConfig useCaseConfig = getUseCaseConfig();
            ImageOutputConfig.validateConfig(useCaseConfig);
            return new ImageAnalysis(useCaseConfig);
        }

        public Builder setTargetClass(Class<ImageAnalysis> cls) {
            getMutableConfig().insertOption(UseCaseConfig.OPTION_TARGET_CLASS, cls);
            if (getMutableConfig().retrieveOption(UseCaseConfig.OPTION_TARGET_NAME, null) == null) {
                setTargetName(cls.getCanonicalName() + "-" + UUID.randomUUID());
            }
            return this;
        }

        public Builder setTargetName(String str) {
            getMutableConfig().insertOption(UseCaseConfig.OPTION_TARGET_NAME, str);
            return this;
        }

        @Deprecated
        public Builder setTargetAspectRatio(int i) {
            if (i == -1) {
                i = 0;
            }
            getMutableConfig().insertOption(ImageOutputConfig.OPTION_TARGET_ASPECT_RATIO, Integer.valueOf(i));
            return this;
        }

        public Builder setTargetRotation(int i) {
            getMutableConfig().insertOption(ImageOutputConfig.OPTION_TARGET_ROTATION, Integer.valueOf(i));
            return this;
        }

        public Builder setMirrorMode(int i) {
            throw new UnsupportedOperationException("setMirrorMode is not supported.");
        }

        @Deprecated
        public Builder setTargetResolution(Size size) {
            getMutableConfig().insertOption(ImageOutputConfig.OPTION_TARGET_RESOLUTION, size);
            return this;
        }

        public Builder setDefaultResolution(Size size) {
            getMutableConfig().insertOption(ImageOutputConfig.OPTION_DEFAULT_RESOLUTION, size);
            return this;
        }

        public Builder setMaxResolution(Size size) {
            getMutableConfig().insertOption(ImageOutputConfig.OPTION_MAX_RESOLUTION, size);
            return this;
        }

        public Builder setSupportedResolutions(List<Pair<Integer, Size[]>> list) {
            getMutableConfig().insertOption(ImageOutputConfig.OPTION_SUPPORTED_RESOLUTIONS, list);
            return this;
        }

        public Builder setCustomOrderedResolutions(List<Size> list) {
            getMutableConfig().insertOption(ImageOutputConfig.OPTION_CUSTOM_ORDERED_RESOLUTIONS, list);
            return this;
        }

        public Builder setResolutionSelector(ResolutionSelector resolutionSelector) {
            getMutableConfig().insertOption(ImageOutputConfig.OPTION_RESOLUTION_SELECTOR, resolutionSelector);
            return this;
        }

        public Builder setBackgroundExecutor(Executor executor) {
            getMutableConfig().insertOption(ThreadConfig.OPTION_BACKGROUND_EXECUTOR, executor);
            return this;
        }

        public Builder setDefaultSessionConfig(SessionConfig sessionConfig) {
            getMutableConfig().insertOption(UseCaseConfig.OPTION_DEFAULT_SESSION_CONFIG, sessionConfig);
            return this;
        }

        public Builder setDefaultCaptureConfig(CaptureConfig captureConfig) {
            getMutableConfig().insertOption(UseCaseConfig.OPTION_DEFAULT_CAPTURE_CONFIG, captureConfig);
            return this;
        }

        public Builder setSessionOptionUnpacker(SessionConfig.OptionUnpacker optionUnpacker) {
            getMutableConfig().insertOption(UseCaseConfig.OPTION_SESSION_CONFIG_UNPACKER, optionUnpacker);
            return this;
        }

        public Builder setCaptureOptionUnpacker(CaptureConfig.OptionUnpacker optionUnpacker) {
            getMutableConfig().insertOption(UseCaseConfig.OPTION_CAPTURE_CONFIG_UNPACKER, optionUnpacker);
            return this;
        }

        public Builder setSurfaceOccupancyPriority(int i) {
            getMutableConfig().insertOption(UseCaseConfig.OPTION_SURFACE_OCCUPANCY_PRIORITY, Integer.valueOf(i));
            return this;
        }

        public Builder setImageReaderProxyProvider(ImageReaderProxyProvider imageReaderProxyProvider) {
            getMutableConfig().insertOption(ImageAnalysisConfig.OPTION_IMAGE_READER_PROXY_PROVIDER, imageReaderProxyProvider);
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
            if (Objects.equals(DynamicRange.SDR, dynamicRange)) {
                getMutableConfig().insertOption(ImageInputConfig.OPTION_INPUT_DYNAMIC_RANGE, dynamicRange);
                return this;
            }
            throw new UnsupportedOperationException("ImageAnalysis currently only supports SDR");
        }
    }
}

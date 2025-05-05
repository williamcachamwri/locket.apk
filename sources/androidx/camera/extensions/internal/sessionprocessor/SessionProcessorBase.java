package androidx.camera.extensions.internal.sessionprocessor;

import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.OutputSurfaceConfiguration;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.SessionProcessor;
import androidx.camera.core.impl.SessionProcessorSurface;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.extensions.CameraExtensionsControl;
import androidx.camera.extensions.CameraExtensionsInfo;
import androidx.camera.extensions.internal.ExtensionsUtils;
import androidx.camera.extensions.internal.RequestOptionConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

abstract class SessionProcessorBase implements SessionProcessor, CameraExtensionsInfo, CameraExtensionsControl {
    protected static final int EXTENSION_STRENGTH_UNKNOWN = -1;
    private static final String TAG = "SessionProcessorBase";
    private String mCameraId;
    protected int mExtensionStrength = -1;
    private HandlerThread mImageReaderHandlerThread;
    private final Map<Integer, ImageReader> mImageReaderMap = new HashMap();
    protected final Object mLock = new Object();
    private final Map<Integer, Camera2OutputConfig> mOutputConfigMap = new HashMap();
    private final Set<Integer> mSupportedCameraOperations;
    private final List<DeferrableSurface> mSurfacesList = new ArrayList();

    /* access modifiers changed from: protected */
    public abstract void deInitSessionInternal();

    /* access modifiers changed from: protected */
    public abstract Camera2SessionConfig initSessionInternal(String str, Map<String, CameraCharacteristics> map, OutputSurfaceConfiguration outputSurfaceConfiguration);

    SessionProcessorBase(List<CaptureRequest.Key> list) {
        this.mSupportedCameraOperations = getSupportedCameraOperations(list);
    }

    private Set<Integer> getSupportedCameraOperations(List<CaptureRequest.Key> list) {
        HashSet hashSet = new HashSet();
        if (Build.VERSION.SDK_INT >= 30) {
            if (list.contains(CaptureRequest.CONTROL_ZOOM_RATIO) || list.contains(CaptureRequest.SCALER_CROP_REGION)) {
                hashSet.add(0);
            }
        } else if (list.contains(CaptureRequest.SCALER_CROP_REGION)) {
            hashSet.add(0);
        }
        if (list.containsAll(Arrays.asList(new CaptureRequest.Key[]{CaptureRequest.CONTROL_AF_TRIGGER, CaptureRequest.CONTROL_AF_MODE}))) {
            hashSet.add(1);
        }
        if (list.contains(CaptureRequest.CONTROL_AF_REGIONS)) {
            hashSet.add(2);
        }
        if (list.contains(CaptureRequest.CONTROL_AE_REGIONS)) {
            hashSet.add(3);
        }
        if (list.contains(CaptureRequest.CONTROL_AWB_REGIONS)) {
            hashSet.add(4);
        }
        if (list.containsAll(Arrays.asList(new CaptureRequest.Key[]{CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER}))) {
            hashSet.add(5);
        }
        if (list.containsAll(Arrays.asList(new CaptureRequest.Key[]{CaptureRequest.CONTROL_AE_MODE, CaptureRequest.FLASH_MODE}))) {
            hashSet.add(6);
        }
        if (list.contains(CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION)) {
            hashSet.add(7);
        }
        if (Build.VERSION.SDK_INT >= 34 && list.contains(CaptureRequest.EXTENSION_STRENGTH)) {
            hashSet.add(8);
        }
        return hashSet;
    }

    private static SessionProcessorSurface createOutputConfigSurface(Camera2OutputConfig camera2OutputConfig, Map<Integer, ImageReader> map) {
        if (camera2OutputConfig instanceof SurfaceOutputConfig) {
            return new SessionProcessorSurface(((SurfaceOutputConfig) camera2OutputConfig).getSurface(), camera2OutputConfig.getId());
        }
        if (camera2OutputConfig instanceof ImageReaderOutputConfig) {
            ImageReaderOutputConfig imageReaderOutputConfig = (ImageReaderOutputConfig) camera2OutputConfig;
            ImageReader newInstance = ImageReader.newInstance(imageReaderOutputConfig.getSize().getWidth(), imageReaderOutputConfig.getSize().getHeight(), imageReaderOutputConfig.getImageFormat(), imageReaderOutputConfig.getMaxImages());
            map.put(Integer.valueOf(camera2OutputConfig.getId()), newInstance);
            SessionProcessorSurface sessionProcessorSurface = new SessionProcessorSurface(newInstance.getSurface(), camera2OutputConfig.getId());
            sessionProcessorSurface.getTerminationFuture().addListener(new SessionProcessorBase$$ExternalSyntheticLambda0(newInstance), CameraXExecutors.directExecutor());
            return sessionProcessorSurface;
        } else if (camera2OutputConfig instanceof MultiResolutionImageReaderOutputConfig) {
            throw new UnsupportedOperationException("MultiResolutionImageReader not supported yet");
        } else {
            throw new UnsupportedOperationException("Unsupported Camera2OutputConfig:" + camera2OutputConfig);
        }
    }

    public final SessionConfig initSession(CameraInfo cameraInfo, OutputSurfaceConfiguration outputSurfaceConfiguration) {
        CameraInfoInternal cameraInfoInternal = (CameraInfoInternal) cameraInfo;
        Camera2SessionConfig initSessionInternal = initSessionInternal(cameraInfoInternal.getCameraId(), ExtensionsUtils.getCameraCharacteristicsMap(cameraInfoInternal), outputSurfaceConfiguration);
        SessionConfig.Builder builder = new SessionConfig.Builder();
        synchronized (this.mLock) {
            for (Camera2OutputConfig next : initSessionInternal.getOutputConfigs()) {
                SessionProcessorSurface createOutputConfigSurface = createOutputConfigSurface(next, this.mImageReaderMap);
                this.mSurfacesList.add(createOutputConfigSurface);
                this.mOutputConfigMap.put(Integer.valueOf(next.getId()), next);
                SessionConfig.OutputConfig.Builder surfaceGroupId = SessionConfig.OutputConfig.builder(createOutputConfigSurface).setPhysicalCameraId(next.getPhysicalCameraId()).setSurfaceGroupId(next.getSurfaceGroupId());
                List<Camera2OutputConfig> surfaceSharingOutputConfigs = next.getSurfaceSharingOutputConfigs();
                if (surfaceSharingOutputConfigs != null && !surfaceSharingOutputConfigs.isEmpty()) {
                    ArrayList arrayList = new ArrayList();
                    for (Camera2OutputConfig next2 : surfaceSharingOutputConfigs) {
                        this.mOutputConfigMap.put(Integer.valueOf(next2.getId()), next2);
                        arrayList.add(createOutputConfigSurface(next2, this.mImageReaderMap));
                    }
                    surfaceGroupId.setSharedSurfaces(arrayList);
                }
                builder.addOutputConfig(surfaceGroupId.build());
            }
        }
        RequestOptionConfig.Builder builder2 = new RequestOptionConfig.Builder();
        for (CaptureRequest.Key next3 : initSessionInternal.getSessionParameters().keySet()) {
            builder2.setCaptureRequestOption(next3, initSessionInternal.getSessionParameters().get(next3));
        }
        builder.setImplementationOptions(builder2.build());
        builder.setTemplateType(initSessionInternal.getSessionTemplateId());
        builder.setSessionType(initSessionInternal.getSessionType());
        HandlerThread handlerThread = new HandlerThread("CameraX-extensions_image_reader");
        this.mImageReaderHandlerThread = handlerThread;
        handlerThread.start();
        this.mCameraId = cameraInfoInternal.getCameraId();
        Logger.d(TAG, "initSession: cameraId=" + this.mCameraId);
        return builder.build();
    }

    public Set<Integer> getSupportedCameraOperations() {
        return this.mSupportedCameraOperations;
    }

    /* access modifiers changed from: protected */
    public void setImageProcessor(int i, ImageProcessor imageProcessor) {
        ImageReader imageReader;
        String str;
        synchronized (this.mLock) {
            imageReader = this.mImageReaderMap.get(Integer.valueOf(i));
            Camera2OutputConfig camera2OutputConfig = this.mOutputConfigMap.get(Integer.valueOf(i));
            if (camera2OutputConfig == null) {
                str = null;
            } else {
                str = camera2OutputConfig.getPhysicalCameraId();
            }
        }
        if (imageReader != null) {
            imageReader.setOnImageAvailableListener(new SessionProcessorBase$$ExternalSyntheticLambda1(imageProcessor, i, str), new Handler(this.mImageReaderHandlerThread.getLooper()));
        }
    }

    static /* synthetic */ void lambda$setImageProcessor$1(ImageProcessor imageProcessor, int i, String str, ImageReader imageReader) {
        try {
            Image acquireNextImage = imageReader.acquireNextImage();
            ImageProcessor imageProcessor2 = imageProcessor;
            int i2 = i;
            imageProcessor2.onNextImageAvailable(i2, acquireNextImage.getTimestamp(), new ImageRefHolder(acquireNextImage), str);
        } catch (IllegalStateException e) {
            Logger.e(TAG, "Failed to acquire next image.", e);
        }
    }

    public final void deInitSession() {
        Logger.e(TAG, "deInitSession: cameraId=" + this.mCameraId);
        deInitSessionInternal();
        synchronized (this.mLock) {
            for (DeferrableSurface close : this.mSurfacesList) {
                close.close();
            }
            this.mSurfacesList.clear();
            this.mImageReaderMap.clear();
            this.mOutputConfigMap.clear();
            this.mExtensionStrength = -1;
        }
        HandlerThread handlerThread = this.mImageReaderHandlerThread;
        if (handlerThread != null) {
            handlerThread.quitSafely();
            this.mImageReaderHandlerThread = null;
        }
    }

    private static class ImageRefHolder implements ImageReference {
        private final Image mImage;
        private final Object mImageLock = new Object();
        private int mRefCount = 1;

        ImageRefHolder(Image image) {
            this.mImage = image;
        }

        public boolean increment() {
            synchronized (this.mImageLock) {
                int i = this.mRefCount;
                if (i <= 0) {
                    return false;
                }
                this.mRefCount = i + 1;
                return true;
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0016, code lost:
            return true;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean decrement() {
            /*
                r3 = this;
                java.lang.Object r0 = r3.mImageLock
                monitor-enter(r0)
                int r1 = r3.mRefCount     // Catch:{ all -> 0x0017 }
                if (r1 > 0) goto L_0x000a
                monitor-exit(r0)     // Catch:{ all -> 0x0017 }
                r0 = 0
                return r0
            L_0x000a:
                r2 = 1
                int r1 = r1 - r2
                r3.mRefCount = r1     // Catch:{ all -> 0x0017 }
                if (r1 > 0) goto L_0x0015
                android.media.Image r1 = r3.mImage     // Catch:{ all -> 0x0017 }
                r1.close()     // Catch:{ all -> 0x0017 }
            L_0x0015:
                monitor-exit(r0)     // Catch:{ all -> 0x0017 }
                return r2
            L_0x0017:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0017 }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.camera.extensions.internal.sessionprocessor.SessionProcessorBase.ImageRefHolder.decrement():boolean");
        }

        public Image get() {
            return this.mImage;
        }
    }
}

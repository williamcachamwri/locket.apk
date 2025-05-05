package androidx.camera.extensions.internal;

import android.content.Context;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Build;
import android.util.Pair;
import android.util.Range;
import android.util.Size;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.SessionProcessor;
import androidx.camera.extensions.impl.AutoImageCaptureExtenderImpl;
import androidx.camera.extensions.impl.AutoPreviewExtenderImpl;
import androidx.camera.extensions.impl.BeautyImageCaptureExtenderImpl;
import androidx.camera.extensions.impl.BeautyPreviewExtenderImpl;
import androidx.camera.extensions.impl.BokehImageCaptureExtenderImpl;
import androidx.camera.extensions.impl.BokehPreviewExtenderImpl;
import androidx.camera.extensions.impl.HdrImageCaptureExtenderImpl;
import androidx.camera.extensions.impl.HdrPreviewExtenderImpl;
import androidx.camera.extensions.impl.ImageCaptureExtenderImpl;
import androidx.camera.extensions.impl.NightImageCaptureExtenderImpl;
import androidx.camera.extensions.impl.NightPreviewExtenderImpl;
import androidx.camera.extensions.impl.PreviewExtenderImpl;
import androidx.camera.extensions.internal.compat.workaround.AvailableKeysRetriever;
import androidx.camera.extensions.internal.compat.workaround.ExtensionDisabledValidator;
import androidx.camera.extensions.internal.sessionprocessor.BasicExtenderSessionProcessor;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicVendorExtender implements VendorExtender {
    private static final String TAG = "BasicVendorExtender";
    static final List<CaptureRequest.Key> sBaseSupportedKeys;
    private AvailableKeysRetriever mAvailableKeysRetriever = new AvailableKeysRetriever();
    private CameraCharacteristics mCameraCharacteristics;
    private String mCameraId;
    private CameraInfoInternal mCameraInfo;
    private final ExtensionDisabledValidator mExtensionDisabledValidator = new ExtensionDisabledValidator();
    private ImageCaptureExtenderImpl mImageCaptureExtenderImpl = null;
    private PreviewExtenderImpl mPreviewExtenderImpl = null;

    public boolean isExtensionStrengthAvailable() {
        return false;
    }

    static {
        ArrayList arrayList = new ArrayList(Arrays.asList(new CaptureRequest.Key[]{CaptureRequest.SCALER_CROP_REGION, CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_TRIGGER, CaptureRequest.CONTROL_AF_REGIONS, CaptureRequest.CONTROL_AE_REGIONS, CaptureRequest.CONTROL_AWB_REGIONS, CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, CaptureRequest.FLASH_MODE, CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION}));
        sBaseSupportedKeys = arrayList;
        if (Build.VERSION.SDK_INT >= 30) {
            arrayList.add(CaptureRequest.CONTROL_ZOOM_RATIO);
        }
    }

    public BasicVendorExtender(int i) {
        if (i == 1) {
            this.mPreviewExtenderImpl = new BokehPreviewExtenderImpl();
            this.mImageCaptureExtenderImpl = new BokehImageCaptureExtenderImpl();
        } else if (i == 2) {
            this.mPreviewExtenderImpl = new HdrPreviewExtenderImpl();
            this.mImageCaptureExtenderImpl = new HdrImageCaptureExtenderImpl();
        } else if (i == 3) {
            this.mPreviewExtenderImpl = new NightPreviewExtenderImpl();
            this.mImageCaptureExtenderImpl = new NightImageCaptureExtenderImpl();
        } else if (i == 4) {
            this.mPreviewExtenderImpl = new BeautyPreviewExtenderImpl();
            this.mImageCaptureExtenderImpl = new BeautyImageCaptureExtenderImpl();
        } else if (i == 5) {
            try {
                this.mPreviewExtenderImpl = new AutoPreviewExtenderImpl();
                this.mImageCaptureExtenderImpl = new AutoImageCaptureExtenderImpl();
            } catch (NoClassDefFoundError unused) {
                Logger.e(TAG, "OEM implementation for extension mode " + i + "does not exist!");
            }
        } else {
            throw new IllegalArgumentException("Should not activate ExtensionMode.NONE");
        }
    }

    public BasicVendorExtender(ImageCaptureExtenderImpl imageCaptureExtenderImpl, PreviewExtenderImpl previewExtenderImpl) {
        this.mPreviewExtenderImpl = previewExtenderImpl;
        this.mImageCaptureExtenderImpl = imageCaptureExtenderImpl;
    }

    public boolean isExtensionAvailable(String str, Map<String, CameraCharacteristics> map) {
        if (this.mExtensionDisabledValidator.shouldDisableExtension(str) || this.mPreviewExtenderImpl == null || this.mImageCaptureExtenderImpl == null) {
            return false;
        }
        CameraCharacteristics cameraCharacteristics = map.get(str);
        if (!this.mPreviewExtenderImpl.isExtensionAvailable(str, cameraCharacteristics) || !this.mImageCaptureExtenderImpl.isExtensionAvailable(str, cameraCharacteristics)) {
            return false;
        }
        return true;
    }

    public void init(CameraInfo cameraInfo) {
        CameraInfoInternal cameraInfoInternal = (CameraInfoInternal) cameraInfo;
        this.mCameraInfo = cameraInfoInternal;
        if (this.mPreviewExtenderImpl != null && this.mImageCaptureExtenderImpl != null) {
            this.mCameraId = cameraInfoInternal.getCameraId();
            CameraCharacteristics cameraCharacteristics = (CameraCharacteristics) this.mCameraInfo.getCameraCharacteristics();
            this.mCameraCharacteristics = cameraCharacteristics;
            this.mPreviewExtenderImpl.init(this.mCameraId, cameraCharacteristics);
            this.mImageCaptureExtenderImpl.init(this.mCameraId, this.mCameraCharacteristics);
            Logger.d(TAG, "PreviewExtender processorType= " + this.mPreviewExtenderImpl.getProcessorType());
            Logger.d(TAG, "ImageCaptureExtender processor= " + this.mImageCaptureExtenderImpl.getCaptureProcessor());
        }
    }

    public Range<Long> getEstimatedCaptureLatencyRange(Size size) {
        Preconditions.checkNotNull(this.mCameraInfo, "VendorExtender#init() must be called first");
        if (this.mImageCaptureExtenderImpl == null || ExtensionVersion.getRuntimeVersion().compareTo(Version.VERSION_1_2) < 0) {
            return null;
        }
        try {
            return this.mImageCaptureExtenderImpl.getEstimatedCaptureLatencyRange(size);
        } catch (Throwable unused) {
            return null;
        }
    }

    private Size[] getOutputSizes(int i) {
        return ((StreamConfigurationMap) this.mCameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)).getOutputSizes(i);
    }

    private int getCaptureInputImageFormat() {
        ImageCaptureExtenderImpl imageCaptureExtenderImpl = this.mImageCaptureExtenderImpl;
        return (imageCaptureExtenderImpl == null || imageCaptureExtenderImpl.getCaptureProcessor() == null) ? 256 : 35;
    }

    private int getPreviewInputImageFormat() {
        PreviewExtenderImpl previewExtenderImpl = this.mPreviewExtenderImpl;
        return (previewExtenderImpl == null || previewExtenderImpl.getProcessorType() != PreviewExtenderImpl.ProcessorType.PROCESSOR_TYPE_IMAGE_PROCESSOR) ? 34 : 35;
    }

    public List<Pair<Integer, Size[]>> getSupportedPreviewOutputResolutions() {
        Preconditions.checkNotNull(this.mCameraInfo, "VendorExtender#init() must be called first");
        if (this.mPreviewExtenderImpl != null && ExtensionVersion.getRuntimeVersion().compareTo(Version.VERSION_1_1) >= 0) {
            try {
                List supportedResolutions = this.mPreviewExtenderImpl.getSupportedResolutions();
                if (supportedResolutions != null) {
                    return getSupportedResolutionsOfFormat(supportedResolutions, 34, 35);
                }
            } catch (NoSuchMethodError unused) {
            }
        }
        return Arrays.asList(new Pair[]{new Pair(34, getOutputSizes(getPreviewInputImageFormat()))});
    }

    public List<Pair<Integer, Size[]>> getSupportedCaptureOutputResolutions() {
        Preconditions.checkNotNull(this.mCameraInfo, "VendorExtender#init() must be called first");
        if (this.mImageCaptureExtenderImpl != null && ExtensionVersion.getRuntimeVersion().compareTo(Version.VERSION_1_1) >= 0) {
            try {
                List<Pair<Integer, Size[]>> supportedResolutions = this.mImageCaptureExtenderImpl.getSupportedResolutions();
                if (supportedResolutions != null) {
                    if (this.mImageCaptureExtenderImpl.getCaptureProcessor() != null) {
                        return getSupportedResolutionsOfFormat(supportedResolutions, 35, 256);
                    }
                    return supportedResolutions;
                }
            } catch (NoSuchMethodError unused) {
            }
        }
        int captureInputImageFormat = getCaptureInputImageFormat();
        return Arrays.asList(new Pair[]{new Pair(Integer.valueOf(captureInputImageFormat), getOutputSizes(captureInputImageFormat))});
    }

    private List<Pair<Integer, Size[]>> getSupportedResolutionsOfFormat(List<Pair<Integer, Size[]>> list, int i, int i2) {
        ArrayList arrayList = new ArrayList();
        for (Pair next : list) {
            if (((Integer) next.first).intValue() == i) {
                arrayList.add(new Pair(Integer.valueOf(i), (Size[]) next.second));
                return arrayList;
            }
        }
        for (Pair next2 : list) {
            if (((Integer) next2.first).intValue() == i2) {
                arrayList.add(new Pair(Integer.valueOf(i), (Size[]) next2.second));
            }
        }
        if (!arrayList.isEmpty()) {
            return arrayList;
        }
        throw new IllegalArgumentException("Supported resolution should contain " + i + " format.");
    }

    public Size[] getSupportedYuvAnalysisResolutions() {
        Preconditions.checkNotNull(this.mCameraInfo, "VendorExtender#init() must be called first");
        return new Size[0];
    }

    private List<CaptureRequest.Key> getSupportedParameterKeys(Context context) {
        if (!ExtensionVersion.isMinimumCompatibleVersion(Version.VERSION_1_3)) {
            return Collections.unmodifiableList(sBaseSupportedKeys);
        }
        try {
            List<CaptureRequest.Key> availableCaptureRequestKeys = this.mAvailableKeysRetriever.getAvailableCaptureRequestKeys(this.mImageCaptureExtenderImpl, this.mCameraId, this.mCameraCharacteristics, context);
            if (availableCaptureRequestKeys != null) {
                return Collections.unmodifiableList(availableCaptureRequestKeys);
            }
        } catch (Exception e) {
            Logger.e(TAG, "ImageCaptureExtenderImpl.getAvailableCaptureRequestKeys throws exceptions", e);
        }
        return Collections.emptyList();
    }

    public List<CaptureResult.Key> getSupportedCaptureResultKeys() {
        if (ExtensionVersion.isMinimumCompatibleVersion(Version.VERSION_1_3)) {
            try {
                List availableCaptureResultKeys = this.mImageCaptureExtenderImpl.getAvailableCaptureResultKeys();
                if (availableCaptureResultKeys != null) {
                    return Collections.unmodifiableList(availableCaptureResultKeys);
                }
            } catch (Exception e) {
                Logger.e(TAG, "ImageCaptureExtenderImpl.getAvailableCaptureResultKeys throws exceptions", e);
            }
        }
        return Collections.emptyList();
    }

    public Map<Integer, List<Size>> getSupportedPostviewResolutions(Size size) {
        if (!ClientVersion.isMinimumCompatibleVersion(Version.VERSION_1_4) || !ExtensionVersion.isMinimumCompatibleVersion(Version.VERSION_1_4)) {
            return Collections.emptyMap();
        }
        List<Pair> supportedPostviewResolutions = this.mImageCaptureExtenderImpl.getSupportedPostviewResolutions(size);
        HashMap hashMap = new HashMap();
        for (Pair pair : supportedPostviewResolutions) {
            hashMap.put(Integer.valueOf(((Integer) pair.first).intValue()), Arrays.asList((Size[]) pair.second));
        }
        return Collections.unmodifiableMap(hashMap);
    }

    public boolean isPostviewAvailable() {
        if (!ClientVersion.isMinimumCompatibleVersion(Version.VERSION_1_4) || !ExtensionVersion.isMinimumCompatibleVersion(Version.VERSION_1_4)) {
            return false;
        }
        return this.mImageCaptureExtenderImpl.isPostviewAvailable();
    }

    public boolean isCaptureProcessProgressAvailable() {
        if (!ClientVersion.isMinimumCompatibleVersion(Version.VERSION_1_4) || !ExtensionVersion.isMinimumCompatibleVersion(Version.VERSION_1_4)) {
            return false;
        }
        return this.mImageCaptureExtenderImpl.isCaptureProcessProgressAvailable();
    }

    public SessionProcessor createSessionProcessor(Context context) {
        Preconditions.checkNotNull(this.mCameraInfo, "VendorExtender#init() must be called first");
        return new BasicExtenderSessionProcessor(this.mPreviewExtenderImpl, this.mImageCaptureExtenderImpl, getSupportedParameterKeys(context), this, context);
    }
}

package androidx.camera.extensions.internal;

import android.content.Context;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.os.Build;
import android.util.Pair;
import android.util.Range;
import android.util.Size;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.SessionProcessor;
import androidx.camera.extensions.impl.advanced.AdvancedExtenderImpl;
import androidx.camera.extensions.impl.advanced.AutoAdvancedExtenderImpl;
import androidx.camera.extensions.impl.advanced.BeautyAdvancedExtenderImpl;
import androidx.camera.extensions.impl.advanced.BokehAdvancedExtenderImpl;
import androidx.camera.extensions.impl.advanced.HdrAdvancedExtenderImpl;
import androidx.camera.extensions.impl.advanced.NightAdvancedExtenderImpl;
import androidx.camera.extensions.internal.compat.workaround.ExtensionDisabledValidator;
import androidx.camera.extensions.internal.sessionprocessor.AdvancedSessionProcessor;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AdvancedVendorExtender implements VendorExtender {
    private static final String TAG = "AdvancedVendorExtender";
    private final AdvancedExtenderImpl mAdvancedExtenderImpl;
    private String mCameraId;
    private final ExtensionDisabledValidator mExtensionDisabledValidator = new ExtensionDisabledValidator();
    private final int mMode;

    public AdvancedVendorExtender(int i) {
        if (i == 1) {
            this.mAdvancedExtenderImpl = new BokehAdvancedExtenderImpl();
        } else if (i == 2) {
            this.mAdvancedExtenderImpl = new HdrAdvancedExtenderImpl();
        } else if (i == 3) {
            this.mAdvancedExtenderImpl = new NightAdvancedExtenderImpl();
        } else if (i == 4) {
            this.mAdvancedExtenderImpl = new BeautyAdvancedExtenderImpl();
        } else if (i == 5) {
            try {
                this.mAdvancedExtenderImpl = new AutoAdvancedExtenderImpl();
            } catch (NoClassDefFoundError unused) {
                throw new IllegalArgumentException("AdvancedExtenderImpl does not exist");
            }
        } else {
            throw new IllegalArgumentException("Should not active ExtensionMode.NONE");
        }
        this.mMode = i;
    }

    AdvancedVendorExtender(AdvancedExtenderImpl advancedExtenderImpl) {
        this.mAdvancedExtenderImpl = advancedExtenderImpl;
        this.mMode = 0;
    }

    public void init(CameraInfo cameraInfo) {
        CameraInfoInternal cameraInfoInternal = (CameraInfoInternal) cameraInfo;
        this.mCameraId = cameraInfoInternal.getCameraId();
        this.mAdvancedExtenderImpl.init(this.mCameraId, ExtensionsUtils.getCameraCharacteristicsMap(cameraInfoInternal));
    }

    public boolean isExtensionAvailable(String str, Map<String, CameraCharacteristics> map) {
        if (this.mExtensionDisabledValidator.shouldDisableExtension(str)) {
            return false;
        }
        return this.mAdvancedExtenderImpl.isExtensionAvailable(str, map);
    }

    public Range<Long> getEstimatedCaptureLatencyRange(Size size) {
        Preconditions.checkNotNull(this.mCameraId, "VendorExtender#init() must be called first");
        try {
            return this.mAdvancedExtenderImpl.getEstimatedCaptureLatencyRange(this.mCameraId, size, 256);
        } catch (Throwable unused) {
            return null;
        }
    }

    public List<Pair<Integer, Size[]>> getSupportedPreviewOutputResolutions() {
        Preconditions.checkNotNull(this.mCameraId, "VendorExtender#init() must be called first");
        return convertResolutionMapToList(this.mAdvancedExtenderImpl.getSupportedPreviewOutputResolutions(this.mCameraId));
    }

    public List<Pair<Integer, Size[]>> getSupportedCaptureOutputResolutions() {
        Preconditions.checkNotNull(this.mCameraId, "VendorExtender#init() must be called first");
        return convertResolutionMapToList(this.mAdvancedExtenderImpl.getSupportedCaptureOutputResolutions(this.mCameraId));
    }

    private List<Pair<Integer, Size[]>> convertResolutionMapToList(Map<Integer, List<Size>> map) {
        ArrayList arrayList = new ArrayList();
        for (Integer next : map.keySet()) {
            arrayList.add(new Pair(next, (Size[]) map.get(next).toArray(new Size[0])));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public Size[] getSupportedYuvAnalysisResolutions() {
        Preconditions.checkNotNull(this.mCameraId, "VendorExtender#init() must be called first");
        return new Size[0];
    }

    private List<CaptureRequest.Key> getSupportedParameterKeys() {
        List<CaptureRequest.Key> emptyList = Collections.emptyList();
        if (ExtensionVersion.getRuntimeVersion().compareTo(Version.VERSION_1_3) < 0) {
            return emptyList;
        }
        try {
            return Collections.unmodifiableList(this.mAdvancedExtenderImpl.getAvailableCaptureRequestKeys());
        } catch (Exception e) {
            Logger.e(TAG, "AdvancedExtenderImpl.getAvailableCaptureRequestKeys throws exceptions", e);
            return emptyList;
        }
    }

    public List<CaptureResult.Key> getSupportedCaptureResultKeys() {
        List<CaptureResult.Key> emptyList = Collections.emptyList();
        if (ExtensionVersion.getRuntimeVersion().compareTo(Version.VERSION_1_3) < 0) {
            return emptyList;
        }
        try {
            return Collections.unmodifiableList(this.mAdvancedExtenderImpl.getAvailableCaptureResultKeys());
        } catch (Exception e) {
            Logger.e(TAG, "AdvancedExtenderImpl.getAvailableCaptureResultKeys throws exceptions", e);
            return emptyList;
        }
    }

    public Map<Integer, List<Size>> getSupportedPostviewResolutions(Size size) {
        if (!ClientVersion.isMinimumCompatibleVersion(Version.VERSION_1_4) || !ExtensionVersion.isMinimumCompatibleVersion(Version.VERSION_1_4)) {
            return Collections.emptyMap();
        }
        return Collections.unmodifiableMap(this.mAdvancedExtenderImpl.getSupportedPostviewResolutions(size));
    }

    public boolean isPostviewAvailable() {
        if (!ClientVersion.isMinimumCompatibleVersion(Version.VERSION_1_4) || !ExtensionVersion.isMinimumCompatibleVersion(Version.VERSION_1_4)) {
            return false;
        }
        return this.mAdvancedExtenderImpl.isPostviewAvailable();
    }

    public boolean isCaptureProcessProgressAvailable() {
        if (!ClientVersion.isMinimumCompatibleVersion(Version.VERSION_1_4) || !ExtensionVersion.isMinimumCompatibleVersion(Version.VERSION_1_4)) {
            return false;
        }
        return this.mAdvancedExtenderImpl.isCaptureProcessProgressAvailable();
    }

    public boolean isExtensionStrengthAvailable() {
        if (!ClientVersion.isMinimumCompatibleVersion(Version.VERSION_1_4) || !ExtensionVersion.isMinimumCompatibleVersion(Version.VERSION_1_4) || Build.VERSION.SDK_INT < 34) {
            return false;
        }
        return getSupportedParameterKeys().contains(CaptureRequest.EXTENSION_STRENGTH);
    }

    public boolean isCurrentExtensionModeAvailable() {
        if (!ClientVersion.isMinimumCompatibleVersion(Version.VERSION_1_4) || !ExtensionVersion.isMinimumCompatibleVersion(Version.VERSION_1_4) || Build.VERSION.SDK_INT < 34) {
            return false;
        }
        return getSupportedCaptureResultKeys().contains(CaptureResult.EXTENSION_CURRENT_TYPE);
    }

    public SessionProcessor createSessionProcessor(Context context) {
        Preconditions.checkNotNull(this.mCameraId, "VendorExtender#init() must be called first");
        return new AdvancedSessionProcessor(this.mAdvancedExtenderImpl.createSessionProcessor(), getSupportedParameterKeys(), this, context, this.mMode);
    }
}

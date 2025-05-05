package androidx.camera.extensions;

import android.content.Context;
import android.util.Range;
import android.util.Size;
import androidx.camera.core.CameraFilter;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.CameraProvider;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.impl.CameraConfig;
import androidx.camera.core.impl.CameraConfigProvider;
import androidx.camera.core.impl.ExtendedCameraConfigProviderStore;
import androidx.camera.core.impl.Identifier;
import androidx.camera.core.impl.SessionProcessor;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.extensions.ExtensionsConfig;
import androidx.camera.extensions.internal.AdvancedVendorExtender;
import androidx.camera.extensions.internal.BasicVendorExtender;
import androidx.camera.extensions.internal.ClientVersion;
import androidx.camera.extensions.internal.ExtensionVersion;
import androidx.camera.extensions.internal.ExtensionsUseCaseConfigFactory;
import androidx.camera.extensions.internal.VendorExtender;
import androidx.camera.extensions.internal.Version;
import java.util.Iterator;
import java.util.List;

final class ExtensionsInfo {
    private static final String EXTENDED_CAMERA_CONFIG_PROVIDER_ID_PREFIX = ":camera:camera-extensions-";
    private final CameraProvider mCameraProvider;
    private VendorExtenderFactory mVendorExtenderFactory = new ExtensionsInfo$$ExternalSyntheticLambda0();

    ExtensionsInfo(CameraProvider cameraProvider) {
        this.mCameraProvider = cameraProvider;
    }

    /* access modifiers changed from: package-private */
    public CameraSelector getExtensionCameraSelectorAndInjectCameraConfig(CameraSelector cameraSelector, int i) {
        if (isExtensionAvailable(cameraSelector, i)) {
            Iterator it = cameraSelector.getCameraFilterSet().iterator();
            while (it.hasNext()) {
                if (((CameraFilter) it.next()) instanceof ExtensionCameraFilter) {
                    throw new IllegalArgumentException("An extension is already applied to the base CameraSelector.");
                }
            }
            injectExtensionCameraConfig(i);
            CameraSelector.Builder fromSelector = CameraSelector.Builder.fromSelector(cameraSelector);
            fromSelector.addCameraFilter(getFilter(i));
            return fromSelector.build();
        }
        throw new IllegalArgumentException("No camera can be found to support the specified extensions mode! isExtensionAvailable should be checked first before calling getExtensionEnabledCameraSelector.");
    }

    /* access modifiers changed from: package-private */
    public boolean isExtensionAvailable(CameraSelector cameraSelector, int i) {
        CameraSelector.Builder fromSelector = CameraSelector.Builder.fromSelector(cameraSelector);
        fromSelector.addCameraFilter(getFilter(i));
        return !fromSelector.build().filter(this.mCameraProvider.getAvailableCameraInfos()).isEmpty();
    }

    /* access modifiers changed from: package-private */
    public Range<Long> getEstimatedCaptureLatencyRange(CameraSelector cameraSelector, int i, Size size) {
        List<CameraInfo> filter = CameraSelector.Builder.fromSelector(cameraSelector).addCameraFilter(getFilter(i)).build().filter(this.mCameraProvider.getAvailableCameraInfos());
        if (filter.isEmpty()) {
            return null;
        }
        CameraInfo cameraInfo = filter.get(0);
        if (ExtensionVersion.getRuntimeVersion().compareTo(Version.VERSION_1_2) < 0) {
            return null;
        }
        try {
            VendorExtender createVendorExtender = this.mVendorExtenderFactory.createVendorExtender(i);
            createVendorExtender.init(cameraInfo);
            return createVendorExtender.getEstimatedCaptureLatencyRange(size);
        } catch (NoSuchMethodError unused) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isImageAnalysisSupported(CameraSelector cameraSelector, int i) {
        List<CameraInfo> filter = CameraSelector.Builder.fromSelector(cameraSelector).addCameraFilter(getFilter(i)).build().filter(this.mCameraProvider.getAvailableCameraInfos());
        if (filter.isEmpty()) {
            return false;
        }
        VendorExtender createVendorExtender = this.mVendorExtenderFactory.createVendorExtender(i);
        createVendorExtender.init(filter.get(0));
        Size[] supportedYuvAnalysisResolutions = createVendorExtender.getSupportedYuvAnalysisResolutions();
        if (supportedYuvAnalysisResolutions == null || supportedYuvAnalysisResolutions.length <= 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void setVendorExtenderFactory(VendorExtenderFactory vendorExtenderFactory) {
        this.mVendorExtenderFactory = vendorExtenderFactory;
    }

    private CameraFilter getFilter(int i) {
        return new ExtensionCameraFilter(getExtendedCameraConfigProviderId(i), this.mVendorExtenderFactory.createVendorExtender(i));
    }

    private void injectExtensionCameraConfig(int i) {
        Identifier create = Identifier.create(getExtendedCameraConfigProviderId(i));
        if (ExtendedCameraConfigProviderStore.getConfigProvider(create) == CameraConfigProvider.EMPTY) {
            ExtendedCameraConfigProviderStore.addConfig(create, new ExtensionsInfo$$ExternalSyntheticLambda1(this, i, create));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$injectExtensionCameraConfig$1$androidx-camera-extensions-ExtensionsInfo  reason: not valid java name */
    public /* synthetic */ CameraConfig m227lambda$injectExtensionCameraConfig$1$androidxcameraextensionsExtensionsInfo(int i, Identifier identifier, CameraInfo cameraInfo, Context context) {
        VendorExtender createVendorExtender = this.mVendorExtenderFactory.createVendorExtender(i);
        createVendorExtender.init(cameraInfo);
        ExtensionsConfig.Builder useCaseCombinationRequiredRule = new ExtensionsConfig.Builder().setExtensionMode(i).setUseCaseConfigFactory((UseCaseConfigFactory) new ExtensionsUseCaseConfigFactory(createVendorExtender)).setCompatibilityId(identifier).setZslDisabled(true).setPostviewSupported(createVendorExtender.isPostviewAvailable()).setCaptureProcessProgressSupported(createVendorExtender.isCaptureProcessProgressAvailable()).setUseCaseCombinationRequiredRule(1);
        SessionProcessor createSessionProcessor = createVendorExtender.createSessionProcessor(context);
        if (createSessionProcessor != null) {
            useCaseCombinationRequiredRule.setSessionProcessor(createSessionProcessor);
        }
        return useCaseCombinationRequiredRule.build();
    }

    /* access modifiers changed from: package-private */
    public static VendorExtender getVendorExtender(int i) {
        if (isAdvancedExtenderSupported()) {
            return new AdvancedVendorExtender(i);
        }
        return new BasicVendorExtender(i);
    }

    private static boolean isAdvancedExtenderSupported() {
        if (ClientVersion.isMaximumCompatibleVersion(Version.VERSION_1_1) || ExtensionVersion.isMaximumCompatibleVersion(Version.VERSION_1_1)) {
            return false;
        }
        return ExtensionVersion.isAdvancedExtenderSupported();
    }

    private static String getExtendedCameraConfigProviderId(int i) {
        if (i == 0) {
            return ":camera:camera-extensions-EXTENSION_MODE_NONE";
        }
        if (i == 1) {
            return ":camera:camera-extensions-EXTENSION_MODE_BOKEH";
        }
        if (i == 2) {
            return ":camera:camera-extensions-EXTENSION_MODE_HDR";
        }
        if (i == 3) {
            return ":camera:camera-extensions-EXTENSION_MODE_NIGHT";
        }
        if (i == 4) {
            return ":camera:camera-extensions-EXTENSION_MODE_FACE_RETOUCH";
        }
        if (i == 5) {
            return ":camera:camera-extensions-EXTENSION_MODE_AUTO";
        }
        throw new IllegalArgumentException("Invalid extension mode!");
    }
}

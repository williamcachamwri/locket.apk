package androidx.camera.extensions.internal;

import androidx.camera.core.ImageCapture;
import androidx.camera.core.impl.ConfigProvider;
import androidx.camera.core.impl.ImageCaptureConfig;

public class ImageCaptureConfigProvider implements ConfigProvider<ImageCaptureConfig> {
    private final VendorExtender mVendorExtender;

    public ImageCaptureConfigProvider(VendorExtender vendorExtender) {
        this.mVendorExtender = vendorExtender;
    }

    public ImageCaptureConfig getConfig() {
        ImageCapture.Builder builder = new ImageCapture.Builder();
        updateBuilderConfig(builder, this.mVendorExtender);
        return builder.getUseCaseConfig();
    }

    /* access modifiers changed from: package-private */
    public void updateBuilderConfig(ImageCapture.Builder builder, VendorExtender vendorExtender) {
        builder.setSupportedResolutions(vendorExtender.getSupportedCaptureOutputResolutions());
        builder.setHighResolutionDisabled(true);
    }
}

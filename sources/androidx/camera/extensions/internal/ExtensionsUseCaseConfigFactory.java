package androidx.camera.extensions.internal;

import android.util.Pair;
import android.util.Size;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.ImageAnalysisConfig;
import androidx.camera.core.impl.MutableOptionsBundle;
import androidx.camera.core.impl.OptionsBundle;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import java.util.List;

public final class ExtensionsUseCaseConfigFactory implements UseCaseConfigFactory {
    private final ImageAnalysisConfigProvider mImageAnalysisConfigProvider;
    private final ImageCaptureConfigProvider mImageCaptureConfigProvider;
    private final PreviewConfigProvider mPreviewConfigProvider;

    public ExtensionsUseCaseConfigFactory(VendorExtender vendorExtender) {
        this.mImageCaptureConfigProvider = new ImageCaptureConfigProvider(vendorExtender);
        this.mPreviewConfigProvider = new PreviewConfigProvider(vendorExtender);
        this.mImageAnalysisConfigProvider = new ImageAnalysisConfigProvider(vendorExtender);
    }

    private boolean isImageAnalysisSupported(List<Pair<Integer, Size[]>> list) {
        if (list == null) {
            return false;
        }
        for (Pair next : list) {
            int intValue = ((Integer) next.first).intValue();
            Size[] sizeArr = (Size[]) next.second;
            if (intValue == 35 && sizeArr != null && sizeArr.length > 0) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: androidx.camera.extensions.internal.ExtensionsUseCaseConfigFactory$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$camera$core$impl$UseCaseConfigFactory$CaptureType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                androidx.camera.core.impl.UseCaseConfigFactory$CaptureType[] r0 = androidx.camera.core.impl.UseCaseConfigFactory.CaptureType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$androidx$camera$core$impl$UseCaseConfigFactory$CaptureType = r0
                androidx.camera.core.impl.UseCaseConfigFactory$CaptureType r1 = androidx.camera.core.impl.UseCaseConfigFactory.CaptureType.IMAGE_CAPTURE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$androidx$camera$core$impl$UseCaseConfigFactory$CaptureType     // Catch:{ NoSuchFieldError -> 0x001d }
                androidx.camera.core.impl.UseCaseConfigFactory$CaptureType r1 = androidx.camera.core.impl.UseCaseConfigFactory.CaptureType.PREVIEW     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$androidx$camera$core$impl$UseCaseConfigFactory$CaptureType     // Catch:{ NoSuchFieldError -> 0x0028 }
                androidx.camera.core.impl.UseCaseConfigFactory$CaptureType r1 = androidx.camera.core.impl.UseCaseConfigFactory.CaptureType.IMAGE_ANALYSIS     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$androidx$camera$core$impl$UseCaseConfigFactory$CaptureType     // Catch:{ NoSuchFieldError -> 0x0033 }
                androidx.camera.core.impl.UseCaseConfigFactory$CaptureType r1 = androidx.camera.core.impl.UseCaseConfigFactory.CaptureType.VIDEO_CAPTURE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.camera.extensions.internal.ExtensionsUseCaseConfigFactory.AnonymousClass1.<clinit>():void");
        }
    }

    public Config getConfig(UseCaseConfigFactory.CaptureType captureType, int i) {
        MutableOptionsBundle mutableOptionsBundle;
        int i2 = AnonymousClass1.$SwitchMap$androidx$camera$core$impl$UseCaseConfigFactory$CaptureType[captureType.ordinal()];
        if (i2 == 1) {
            mutableOptionsBundle = MutableOptionsBundle.from(this.mImageCaptureConfigProvider.getConfig());
        } else if (i2 == 2) {
            mutableOptionsBundle = MutableOptionsBundle.from(this.mPreviewConfigProvider.getConfig());
        } else if (i2 == 3) {
            ImageAnalysisConfig config = this.mImageAnalysisConfigProvider.getConfig();
            if (isImageAnalysisSupported(config.getSupportedResolutions((List<Pair<Integer, Size[]>>) null))) {
                mutableOptionsBundle = MutableOptionsBundle.from(config);
            } else {
                throw new IllegalArgumentException("ImageAnalysis is not supported when Extension is enabled on this device. Check ExtensionsManager.isImageAnalysisSupported before binding the ImageAnalysis use case.");
            }
        } else if (i2 != 4) {
            return null;
        } else {
            throw new IllegalArgumentException("Should not go here. VideoCapture is supported by recording the preview stream when Extension is enabled.");
        }
        mutableOptionsBundle.insertOption(UseCaseConfig.OPTION_ZSL_DISABLED, true);
        return OptionsBundle.from(mutableOptionsBundle);
    }
}

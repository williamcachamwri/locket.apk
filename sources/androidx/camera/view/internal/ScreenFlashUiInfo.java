package androidx.camera.view.internal;

import androidx.camera.core.ImageCapture;
import java.util.Objects;

public class ScreenFlashUiInfo {
    private final ProviderType mProviderType;
    private final ImageCapture.ScreenFlash mScreenFlash;

    public enum ProviderType {
        PREVIEW_VIEW,
        SCREEN_FLASH_VIEW
    }

    public ScreenFlashUiInfo(ProviderType providerType, ImageCapture.ScreenFlash screenFlash) {
        this.mProviderType = providerType;
        this.mScreenFlash = screenFlash;
    }

    public ProviderType getProviderType() {
        return this.mProviderType;
    }

    public ImageCapture.ScreenFlash getScreenFlash() {
        return this.mScreenFlash;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ScreenFlashUiInfo)) {
            return false;
        }
        ScreenFlashUiInfo screenFlashUiInfo = (ScreenFlashUiInfo) obj;
        if (this.mProviderType != screenFlashUiInfo.mProviderType || !Objects.equals(this.mScreenFlash, screenFlashUiInfo.mScreenFlash)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mProviderType, this.mScreenFlash});
    }
}

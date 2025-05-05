package androidx.camera.core;

import java.util.Set;

public interface ImageCaptureCapabilities {
    Set<Integer> getSupportedOutputFormats();

    boolean isCaptureProcessProgressSupported();

    boolean isPostviewSupported();
}

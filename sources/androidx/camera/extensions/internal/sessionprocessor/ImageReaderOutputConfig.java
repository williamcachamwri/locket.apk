package androidx.camera.extensions.internal.sessionprocessor;

import android.util.Size;
import java.util.Collections;
import java.util.List;

public abstract class ImageReaderOutputConfig implements Camera2OutputConfig {
    /* access modifiers changed from: package-private */
    public abstract int getImageFormat();

    /* access modifiers changed from: package-private */
    public abstract int getMaxImages();

    /* access modifiers changed from: package-private */
    public abstract Size getSize();

    static ImageReaderOutputConfig create(int i, int i2, String str, List<Camera2OutputConfig> list, Size size, int i3, int i4) {
        return new AutoValue_ImageReaderOutputConfig(i, i2, str, list, size, i3, i4);
    }

    static ImageReaderOutputConfig create(int i, Size size, int i2, int i3) {
        return new AutoValue_ImageReaderOutputConfig(i, -1, (String) null, Collections.emptyList(), size, i2, i3);
    }
}

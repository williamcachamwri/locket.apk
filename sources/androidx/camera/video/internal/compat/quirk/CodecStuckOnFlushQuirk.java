package androidx.camera.video.internal.compat.quirk;

import android.os.Build;
import androidx.camera.core.impl.Quirk;
import androidx.media3.common.MimeTypes;

public class CodecStuckOnFlushQuirk implements Quirk {
    static boolean load() {
        return isNokia1();
    }

    private static boolean isNokia1() {
        return "Nokia".equalsIgnoreCase(Build.BRAND) && "Nokia 1".equalsIgnoreCase(Build.MODEL);
    }

    public boolean isProblematicMimeType(String str) {
        return MimeTypes.VIDEO_MP4V.equals(str);
    }
}

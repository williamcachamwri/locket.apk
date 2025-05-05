package androidx.camera.core.internal.compat.quirk;

import android.util.Range;
import androidx.camera.core.impl.Quirk;
import androidx.camera.core.impl.StreamSpec;

public interface AeFpsRangeQuirk extends Quirk {
    Range<Integer> getTargetAeFpsRange() {
        return StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED;
    }
}

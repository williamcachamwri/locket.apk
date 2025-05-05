package androidx.media3.common.util;

import androidx.media3.common.C;

public interface TimestampIterator {
    TimestampIterator copyOf();

    long getLastTimestampUs() {
        return C.TIME_UNSET;
    }

    boolean hasNext();

    long next();
}

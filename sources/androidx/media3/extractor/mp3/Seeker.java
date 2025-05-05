package androidx.media3.extractor.mp3;

import androidx.media3.common.C;
import androidx.media3.extractor.SeekMap;

interface Seeker extends SeekMap {
    int getAverageBitrate();

    long getDataEndPosition();

    long getTimeUs(long j);

    public static class UnseekableSeeker extends SeekMap.Unseekable implements Seeker {
        public int getAverageBitrate() {
            return C.RATE_UNSET_INT;
        }

        public long getDataEndPosition() {
            return -1;
        }

        public long getTimeUs(long j) {
            return 0;
        }

        public UnseekableSeeker() {
            super(C.TIME_UNSET);
        }
    }
}

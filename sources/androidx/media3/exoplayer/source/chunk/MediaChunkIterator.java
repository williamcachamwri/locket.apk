package androidx.media3.exoplayer.source.chunk;

import androidx.media3.datasource.DataSpec;
import java.util.NoSuchElementException;

public interface MediaChunkIterator {
    public static final MediaChunkIterator EMPTY = new MediaChunkIterator() {
        public boolean isEnded() {
            return true;
        }

        public boolean next() {
            return false;
        }

        public void reset() {
        }

        public DataSpec getDataSpec() {
            throw new NoSuchElementException();
        }

        public long getChunkStartTimeUs() {
            throw new NoSuchElementException();
        }

        public long getChunkEndTimeUs() {
            throw new NoSuchElementException();
        }
    };

    long getChunkEndTimeUs();

    long getChunkStartTimeUs();

    DataSpec getDataSpec();

    boolean isEnded();

    boolean next();

    void reset();
}

package androidx.media3.exoplayer.upstream;

import java.io.IOException;

public interface LoaderErrorThrower {

    public static final class Placeholder implements LoaderErrorThrower {
        public void maybeThrowError() {
        }

        public void maybeThrowError(int i) {
        }
    }

    void maybeThrowError() throws IOException;

    void maybeThrowError(int i) throws IOException;
}

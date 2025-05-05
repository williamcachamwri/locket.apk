package androidx.media3.extractor.mp4;

import androidx.media3.extractor.SniffFailure;

public final class NoDeclaredBrandSniffFailure implements SniffFailure {
    public static final NoDeclaredBrandSniffFailure INSTANCE = new NoDeclaredBrandSniffFailure();

    private NoDeclaredBrandSniffFailure() {
    }
}

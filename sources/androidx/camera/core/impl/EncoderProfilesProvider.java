package androidx.camera.core.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public interface EncoderProfilesProvider {
    public static final EncoderProfilesProvider EMPTY = new EncoderProfilesProvider() {
        public EncoderProfilesProxy getAll(int i) {
            return null;
        }

        public boolean hasProfile(int i) {
            return false;
        }
    };
    public static final List<Integer> QUALITY_HIGH_TO_LOW = Collections.unmodifiableList(Arrays.asList(new Integer[]{13, 10, 8, 11, 6, 5, 4, 9, 3, 7, 2}));

    EncoderProfilesProxy getAll(int i);

    boolean hasProfile(int i);
}

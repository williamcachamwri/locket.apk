package com.facebook.soloader;

import android.os.StrictMode;
import java.io.File;

public class NoopSoSource extends SoSource {
    public String getName() {
        return "NoopSoSource";
    }

    public int loadLibrary(String str, int i, StrictMode.ThreadPolicy threadPolicy) {
        return 1;
    }

    public File unpackLibrary(String str) {
        throw new UnsupportedOperationException("unpacking not supported in test mode");
    }
}

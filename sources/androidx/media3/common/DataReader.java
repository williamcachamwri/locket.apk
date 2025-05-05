package androidx.media3.common;

import java.io.IOException;

public interface DataReader {
    int read(byte[] bArr, int i, int i2) throws IOException;
}

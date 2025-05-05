package kotlinx.serialization.json.internal;

import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH\u0016J\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lkotlinx/serialization/json/internal/JavaStreamSerialReader;", "Lkotlinx/serialization/json/internal/SerialReader;", "stream", "Ljava/io/InputStream;", "(Ljava/io/InputStream;)V", "reader", "Lkotlinx/serialization/json/internal/CharsetReader;", "read", "", "buffer", "", "bufferOffset", "count", "release", "", "kotlinx-serialization-json"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: JvmJsonStreams.kt */
public final class JavaStreamSerialReader implements SerialReader {
    private final CharsetReader reader;

    public JavaStreamSerialReader(InputStream inputStream) {
        Intrinsics.checkNotNullParameter(inputStream, "stream");
        this.reader = new CharsetReader(inputStream, Charsets.UTF_8);
    }

    public int read(char[] cArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(cArr, "buffer");
        return this.reader.read(cArr, i, i2);
    }

    public final void release() {
        this.reader.release();
    }
}

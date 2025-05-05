package androidx.media3.exoplayer.source;

import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.FormatHolder;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public interface SampleStream {
    public static final int FLAG_OMIT_SAMPLE_DATA = 4;
    public static final int FLAG_PEEK = 1;
    public static final int FLAG_REQUIRE_FORMAT = 2;

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ReadDataResult {
    }

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ReadFlags {
    }

    boolean isReady();

    void maybeThrowError() throws IOException;

    int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i);

    int skipData(long j);
}

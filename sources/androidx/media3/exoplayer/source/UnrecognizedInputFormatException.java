package androidx.media3.exoplayer.source;

import android.net.Uri;
import androidx.media3.common.ParserException;
import androidx.media3.extractor.SniffFailure;
import com.google.common.collect.ImmutableList;
import java.util.List;

public class UnrecognizedInputFormatException extends ParserException {
    public final ImmutableList<SniffFailure> sniffFailures;
    public final Uri uri;

    @Deprecated
    public UnrecognizedInputFormatException(String str, Uri uri2) {
        this(str, uri2, ImmutableList.of());
    }

    public UnrecognizedInputFormatException(String str, Uri uri2, List<? extends SniffFailure> list) {
        super(str, (Throwable) null, false, 1);
        this.uri = uri2;
        this.sniffFailures = ImmutableList.copyOf(list);
    }
}

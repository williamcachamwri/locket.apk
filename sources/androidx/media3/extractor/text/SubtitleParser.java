package androidx.media3.extractor.text;

import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.util.Consumer;
import com.google.common.collect.ImmutableList;
import java.util.Objects;

public interface SubtitleParser {

    public interface Factory {
        public static final Factory UNSUPPORTED = new Factory() {
            public int getCueReplacementBehavior(Format format) {
                return 1;
            }

            public boolean supportsFormat(Format format) {
                return false;
            }

            public SubtitleParser create(Format format) {
                throw new IllegalStateException("This SubtitleParser.Factory doesn't support any formats.");
            }
        };

        SubtitleParser create(Format format);

        int getCueReplacementBehavior(Format format);

        boolean supportsFormat(Format format);
    }

    int getCueReplacementBehavior();

    void parse(byte[] bArr, int i, int i2, OutputOptions outputOptions, Consumer<CuesWithTiming> consumer);

    void reset() {
    }

    public static class OutputOptions {
        /* access modifiers changed from: private */
        public static final OutputOptions ALL = new OutputOptions(C.TIME_UNSET, false);
        public final boolean outputAllCues;
        public final long startTimeUs;

        private OutputOptions(long j, boolean z) {
            this.startTimeUs = j;
            this.outputAllCues = z;
        }

        public static OutputOptions allCues() {
            return ALL;
        }

        public static OutputOptions onlyCuesAfter(long j) {
            return new OutputOptions(j, false);
        }

        public static OutputOptions cuesAfterThenRemainingCuesBefore(long j) {
            return new OutputOptions(j, true);
        }
    }

    void parse(byte[] bArr, OutputOptions outputOptions, Consumer<CuesWithTiming> consumer) {
        parse(bArr, 0, bArr.length, outputOptions, consumer);
    }

    Subtitle parseToLegacySubtitle(byte[] bArr, int i, int i2) {
        ImmutableList.Builder builder = ImmutableList.builder();
        OutputOptions access$000 = OutputOptions.ALL;
        Objects.requireNonNull(builder);
        parse(bArr, i, i2, access$000, new SubtitleParser$$ExternalSyntheticLambda0(builder));
        return new CuesWithTimingSubtitle(builder.build());
    }
}

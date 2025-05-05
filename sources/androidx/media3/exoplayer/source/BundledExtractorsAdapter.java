package androidx.media3.exoplayer.source;

import androidx.media3.common.util.Assertions;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.mp3.Mp3Extractor;
import java.io.IOException;

public final class BundledExtractorsAdapter implements ProgressiveMediaExtractor {
    private Extractor extractor;
    private ExtractorInput extractorInput;
    private final ExtractorsFactory extractorsFactory;

    public BundledExtractorsAdapter(ExtractorsFactory extractorsFactory2) {
        this.extractorsFactory = extractorsFactory2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0057, code lost:
        if (r0.getPosition() != r11) goto L_0x007c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0079, code lost:
        if (r0.getPosition() != r11) goto L_0x007c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x007c, code lost:
        r2 = false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void init(androidx.media3.common.DataReader r8, android.net.Uri r9, java.util.Map<java.lang.String, java.util.List<java.lang.String>> r10, long r11, long r13, androidx.media3.extractor.ExtractorOutput r15) throws java.io.IOException {
        /*
            r7 = this;
            androidx.media3.extractor.DefaultExtractorInput r6 = new androidx.media3.extractor.DefaultExtractorInput
            r0 = r6
            r1 = r8
            r2 = r11
            r4 = r13
            r0.<init>(r1, r2, r4)
            r7.extractorInput = r6
            androidx.media3.extractor.Extractor r8 = r7.extractor
            if (r8 == 0) goto L_0x0010
            return
        L_0x0010:
            androidx.media3.extractor.ExtractorsFactory r8 = r7.extractorsFactory
            androidx.media3.extractor.Extractor[] r8 = r8.createExtractors(r9, r10)
            int r10 = r8.length
            com.google.common.collect.ImmutableList$Builder r10 = com.google.common.collect.ImmutableList.builderWithExpectedSize(r10)
            int r13 = r8.length
            r14 = 0
            r0 = 1
            if (r13 != r0) goto L_0x0026
            r8 = r8[r14]
            r7.extractor = r8
            goto L_0x008c
        L_0x0026:
            int r13 = r8.length
            r1 = r14
        L_0x0028:
            if (r1 >= r13) goto L_0x0088
            r2 = r8[r1]
            boolean r3 = r2.sniff(r6)     // Catch:{ EOFException -> 0x006f, all -> 0x005a }
            if (r3 == 0) goto L_0x0046
            r7.extractor = r2     // Catch:{ EOFException -> 0x006f, all -> 0x005a }
            if (r2 != 0) goto L_0x003e
            long r1 = r6.getPosition()
            int r11 = (r1 > r11 ? 1 : (r1 == r11 ? 0 : -1))
            if (r11 != 0) goto L_0x003f
        L_0x003e:
            r14 = r0
        L_0x003f:
            androidx.media3.common.util.Assertions.checkState(r14)
            r6.resetPeekPosition()
            goto L_0x0088
        L_0x0046:
            java.util.List r2 = r2.getSniffFailureDetails()     // Catch:{ EOFException -> 0x006f, all -> 0x005a }
            r10.addAll((java.lang.Iterable) r2)     // Catch:{ EOFException -> 0x006f, all -> 0x005a }
            androidx.media3.extractor.Extractor r2 = r7.extractor
            if (r2 != 0) goto L_0x007e
            long r2 = r6.getPosition()
            int r2 = (r2 > r11 ? 1 : (r2 == r11 ? 0 : -1))
            if (r2 != 0) goto L_0x007c
            goto L_0x007e
        L_0x005a:
            r8 = move-exception
            androidx.media3.extractor.Extractor r9 = r7.extractor
            if (r9 != 0) goto L_0x0067
            long r9 = r6.getPosition()
            int r9 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r9 != 0) goto L_0x0068
        L_0x0067:
            r14 = r0
        L_0x0068:
            androidx.media3.common.util.Assertions.checkState(r14)
            r6.resetPeekPosition()
            throw r8
        L_0x006f:
            androidx.media3.extractor.Extractor r2 = r7.extractor
            if (r2 != 0) goto L_0x007e
            long r2 = r6.getPosition()
            int r2 = (r2 > r11 ? 1 : (r2 == r11 ? 0 : -1))
            if (r2 != 0) goto L_0x007c
            goto L_0x007e
        L_0x007c:
            r2 = r14
            goto L_0x007f
        L_0x007e:
            r2 = r0
        L_0x007f:
            androidx.media3.common.util.Assertions.checkState(r2)
            r6.resetPeekPosition()
            int r1 = r1 + 1
            goto L_0x0028
        L_0x0088:
            androidx.media3.extractor.Extractor r11 = r7.extractor
            if (r11 == 0) goto L_0x0092
        L_0x008c:
            androidx.media3.extractor.Extractor r8 = r7.extractor
            r8.init(r15)
            return
        L_0x0092:
            androidx.media3.exoplayer.source.UnrecognizedInputFormatException r11 = new androidx.media3.exoplayer.source.UnrecognizedInputFormatException
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            java.lang.String r13 = "None of the available extractors ("
            r12.<init>(r13)
            java.lang.String r13 = ", "
            com.google.common.base.Joiner r13 = com.google.common.base.Joiner.on((java.lang.String) r13)
            com.google.common.collect.ImmutableList r8 = com.google.common.collect.ImmutableList.copyOf((E[]) r8)
            androidx.media3.exoplayer.source.BundledExtractorsAdapter$$ExternalSyntheticLambda0 r14 = new androidx.media3.exoplayer.source.BundledExtractorsAdapter$$ExternalSyntheticLambda0
            r14.<init>()
            java.util.List r8 = com.google.common.collect.Lists.transform(r8, r14)
            java.lang.String r8 = r13.join((java.lang.Iterable<? extends java.lang.Object>) r8)
            java.lang.StringBuilder r8 = r12.append(r8)
            java.lang.String r12 = ") could read the stream."
            java.lang.StringBuilder r8 = r8.append(r12)
            java.lang.String r8 = r8.toString()
            java.lang.Object r9 = androidx.media3.common.util.Assertions.checkNotNull(r9)
            android.net.Uri r9 = (android.net.Uri) r9
            com.google.common.collect.ImmutableList r10 = r10.build()
            r11.<init>(r8, r9, r10)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.source.BundledExtractorsAdapter.init(androidx.media3.common.DataReader, android.net.Uri, java.util.Map, long, long, androidx.media3.extractor.ExtractorOutput):void");
    }

    public void release() {
        Extractor extractor2 = this.extractor;
        if (extractor2 != null) {
            extractor2.release();
            this.extractor = null;
        }
        this.extractorInput = null;
    }

    public void disableSeekingOnMp3Streams() {
        Extractor extractor2 = this.extractor;
        if (extractor2 != null) {
            Extractor underlyingImplementation = extractor2.getUnderlyingImplementation();
            if (underlyingImplementation instanceof Mp3Extractor) {
                ((Mp3Extractor) underlyingImplementation).disableSeeking();
            }
        }
    }

    public long getCurrentInputPosition() {
        ExtractorInput extractorInput2 = this.extractorInput;
        if (extractorInput2 != null) {
            return extractorInput2.getPosition();
        }
        return -1;
    }

    public void seek(long j, long j2) {
        ((Extractor) Assertions.checkNotNull(this.extractor)).seek(j, j2);
    }

    public int read(PositionHolder positionHolder) throws IOException {
        return ((Extractor) Assertions.checkNotNull(this.extractor)).read((ExtractorInput) Assertions.checkNotNull(this.extractorInput), positionHolder);
    }
}

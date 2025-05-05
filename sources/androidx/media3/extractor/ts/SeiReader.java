package androidx.media3.extractor.ts;

import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.container.ReorderingSeiMessageQueue;
import androidx.media3.extractor.CeaUtil;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.ts.TsPayloadReader;
import java.util.List;

public final class SeiReader {
    private final List<Format> closedCaptionFormats;
    private final TrackOutput[] outputs;
    private final ReorderingSeiMessageQueue reorderingSeiMessageQueue = new ReorderingSeiMessageQueue(new SeiReader$$ExternalSyntheticLambda0(this));

    public SeiReader(List<Format> list) {
        this.closedCaptionFormats = list;
        this.outputs = new TrackOutput[list.size()];
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$androidx-media3-extractor-ts-SeiReader  reason: not valid java name */
    public /* synthetic */ void m910lambda$new$0$androidxmedia3extractortsSeiReader(long j, ParsableByteArray parsableByteArray) {
        CeaUtil.consume(j, parsableByteArray, this.outputs);
    }

    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        for (int i = 0; i < this.outputs.length; i++) {
            trackIdGenerator.generateNewId();
            TrackOutput track = extractorOutput.track(trackIdGenerator.getTrackId(), 3);
            Format format = this.closedCaptionFormats.get(i);
            String str = format.sampleMimeType;
            Assertions.checkArgument(MimeTypes.APPLICATION_CEA608.equals(str) || MimeTypes.APPLICATION_CEA708.equals(str), "Invalid closed caption MIME type provided: " + str);
            track.format(new Format.Builder().setId(format.id != null ? format.id : trackIdGenerator.getFormatId()).setSampleMimeType(str).setSelectionFlags(format.selectionFlags).setLanguage(format.language).setAccessibilityChannel(format.accessibilityChannel).setInitializationData(format.initializationData).build());
            this.outputs[i] = track;
        }
    }

    public void setReorderingQueueSize(int i) {
        this.reorderingSeiMessageQueue.setMaxSize(i);
    }

    public void consume(long j, ParsableByteArray parsableByteArray) {
        this.reorderingSeiMessageQueue.add(j, parsableByteArray);
    }

    public void flush() {
        this.reorderingSeiMessageQueue.flush();
    }
}

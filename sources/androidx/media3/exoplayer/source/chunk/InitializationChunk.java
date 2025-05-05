package androidx.media3.exoplayer.source.chunk;

import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSourceUtil;
import androidx.media3.datasource.DataSpec;
import androidx.media3.exoplayer.source.chunk.ChunkExtractor;
import androidx.media3.extractor.ChunkIndex;
import androidx.media3.extractor.DefaultExtractorInput;
import java.io.IOException;

public final class InitializationChunk extends Chunk {
    private final ChunkExtractor chunkExtractor;
    private ChunkIndex chunkIndex;
    private volatile boolean loadCanceled;
    private long nextLoadPosition;
    private ChunkExtractor.TrackOutputProvider trackOutputProvider;

    public InitializationChunk(DataSource dataSource, DataSpec dataSpec, Format format, int i, Object obj, ChunkExtractor chunkExtractor2) {
        super(dataSource, dataSpec, 2, format, i, obj, C.TIME_UNSET, C.TIME_UNSET);
        this.chunkExtractor = chunkExtractor2;
    }

    public void init(ChunkExtractor.TrackOutputProvider trackOutputProvider2) {
        this.trackOutputProvider = trackOutputProvider2;
    }

    public void cancelLoad() {
        this.loadCanceled = true;
    }

    public void load() throws IOException {
        DefaultExtractorInput defaultExtractorInput;
        if (this.nextLoadPosition == 0) {
            this.chunkExtractor.init(this.trackOutputProvider, C.TIME_UNSET, C.TIME_UNSET);
        }
        try {
            DataSpec subrange = this.dataSpec.subrange(this.nextLoadPosition);
            defaultExtractorInput = new DefaultExtractorInput(this.dataSource, subrange.position, this.dataSource.open(subrange));
            do {
                if (this.loadCanceled || !this.chunkExtractor.read(defaultExtractorInput)) {
                    break;
                }
                break;
                break;
            } while (!this.chunkExtractor.read(defaultExtractorInput));
            break;
            this.nextLoadPosition = defaultExtractorInput.getPosition() - this.dataSpec.position;
            this.chunkIndex = this.chunkExtractor.getChunkIndex();
            DataSourceUtil.closeQuietly(this.dataSource);
        } catch (Throwable th) {
            DataSourceUtil.closeQuietly(this.dataSource);
            throw th;
        }
    }

    public ChunkIndex getChunkIndex() {
        return this.chunkIndex;
    }
}

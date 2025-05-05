package androidx.media3.extractor.metadata;

import androidx.media3.common.Metadata;

public interface MetadataDecoder {
    Metadata decode(MetadataInputBuffer metadataInputBuffer);
}

package androidx.media3.exoplayer.metadata;

import androidx.media3.common.Metadata;

public interface MetadataOutput {
    void onMetadata(Metadata metadata);
}

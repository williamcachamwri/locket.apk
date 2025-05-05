package androidx.media3.exoplayer.offline;

import java.io.IOException;

public interface DownloadIndex {
    Download getDownload(String str) throws IOException;

    DownloadCursor getDownloads(int... iArr) throws IOException;
}

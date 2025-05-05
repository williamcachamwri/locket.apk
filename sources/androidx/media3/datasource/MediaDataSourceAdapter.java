package androidx.media3.datasource;

import android.media.MediaDataSource;
import android.net.Uri;
import java.io.IOException;

public class MediaDataSourceAdapter extends BaseDataSource {
    private long bytesRemaining;
    private final MediaDataSource mediaDataSource;
    private boolean opened;
    private long position;
    private Uri uri;

    public MediaDataSourceAdapter(MediaDataSource mediaDataSource2, boolean z) {
        super(z);
        this.mediaDataSource = mediaDataSource2;
    }

    public long open(DataSpec dataSpec) throws IOException {
        this.uri = dataSpec.uri;
        this.position = dataSpec.position;
        transferInitializing(dataSpec);
        if (this.mediaDataSource.getSize() == -1 || this.position <= this.mediaDataSource.getSize()) {
            if (this.mediaDataSource.getSize() == -1) {
                this.bytesRemaining = -1;
            } else {
                this.bytesRemaining = this.mediaDataSource.getSize() - this.position;
            }
            if (dataSpec.length != -1) {
                long j = this.bytesRemaining;
                this.bytesRemaining = j == -1 ? dataSpec.length : Math.min(j, dataSpec.length);
            }
            this.opened = true;
            transferStarted(dataSpec);
            return dataSpec.length != -1 ? dataSpec.length : this.bytesRemaining;
        }
        throw new DataSourceException(2008);
    }

    public int read(byte[] bArr, int i, int i2) throws DataSourceException {
        if (i2 == 0) {
            return 0;
        }
        long j = this.bytesRemaining;
        if (j == 0) {
            return -1;
        }
        if (j != -1) {
            i2 = (int) Math.min(j, (long) i2);
        }
        try {
            int readAt = this.mediaDataSource.readAt(this.position, bArr, i, i2);
            if (readAt == -1) {
                return -1;
            }
            long j2 = (long) readAt;
            this.position += j2;
            long j3 = this.bytesRemaining;
            if (j3 != -1) {
                this.bytesRemaining = j3 - j2;
            }
            bytesTransferred(readAt);
            return readAt;
        } catch (IOException e) {
            throw new DataSourceException((Throwable) e, 2000);
        }
    }

    public Uri getUri() {
        return this.uri;
    }

    public void close() throws IOException {
        this.uri = null;
        if (this.opened) {
            this.opened = false;
            transferEnded();
        }
    }
}

package androidx.media3.datasource;

import android.net.Uri;
import androidx.media3.common.util.Assertions;
import java.io.IOException;

public final class ByteArrayDataSource extends BaseDataSource {
    private int bytesRemaining;
    private byte[] data;
    private boolean opened;
    private int readPosition;
    private Uri uri;
    private final UriResolver uriResolver;

    public interface UriResolver {
        byte[] resolve(Uri uri) throws IOException;
    }

    static /* synthetic */ byte[] lambda$new$0(byte[] bArr, Uri uri2) throws IOException {
        return bArr;
    }

    public ByteArrayDataSource(byte[] bArr) {
        this((UriResolver) new ByteArrayDataSource$$ExternalSyntheticLambda0(bArr));
        Assertions.checkArgument(bArr.length > 0);
    }

    public ByteArrayDataSource(UriResolver uriResolver2) {
        super(false);
        this.uriResolver = (UriResolver) Assertions.checkNotNull(uriResolver2);
    }

    public long open(DataSpec dataSpec) throws IOException {
        transferInitializing(dataSpec);
        Uri uri2 = dataSpec.uri;
        this.uri = uri2;
        this.data = this.uriResolver.resolve(uri2);
        if (dataSpec.position <= ((long) this.data.length)) {
            this.readPosition = (int) dataSpec.position;
            this.bytesRemaining = this.data.length - ((int) dataSpec.position);
            if (dataSpec.length != -1) {
                this.bytesRemaining = (int) Math.min((long) this.bytesRemaining, dataSpec.length);
            }
            this.opened = true;
            transferStarted(dataSpec);
            return dataSpec.length != -1 ? dataSpec.length : (long) this.bytesRemaining;
        }
        throw new DataSourceException(2008);
    }

    public int read(byte[] bArr, int i, int i2) {
        if (i2 == 0) {
            return 0;
        }
        int i3 = this.bytesRemaining;
        if (i3 == 0) {
            return -1;
        }
        int min = Math.min(i2, i3);
        System.arraycopy(Assertions.checkStateNotNull(this.data), this.readPosition, bArr, i, min);
        this.readPosition += min;
        this.bytesRemaining -= min;
        bytesTransferred(min);
        return min;
    }

    public Uri getUri() {
        return this.uri;
    }

    public void close() {
        if (this.opened) {
            this.opened = false;
            transferEnded();
        }
        this.uri = null;
        this.data = null;
    }
}

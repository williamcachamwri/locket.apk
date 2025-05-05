package androidx.media3.datasource;

import android.net.Uri;
import androidx.media3.datasource.DataSource;
import java.io.IOException;

public final class PlaceholderDataSource implements DataSource {
    public static final DataSource.Factory FACTORY = new PlaceholderDataSource$$ExternalSyntheticLambda0();
    public static final PlaceholderDataSource INSTANCE = new PlaceholderDataSource();

    /* renamed from: $r8$lambda$vtmV5Njo8kR2KAgmyl6w_Qrh-mI  reason: not valid java name */
    public static /* synthetic */ PlaceholderDataSource m406$r8$lambda$vtmV5Njo8kR2KAgmyl6w_QrhmI() {
        return new PlaceholderDataSource();
    }

    public void addTransferListener(TransferListener transferListener) {
    }

    public void close() {
    }

    public Uri getUri() {
        return null;
    }

    private PlaceholderDataSource() {
    }

    public long open(DataSpec dataSpec) throws IOException {
        throw new IOException("PlaceholderDataSource cannot be opened");
    }

    public int read(byte[] bArr, int i, int i2) {
        throw new UnsupportedOperationException();
    }
}

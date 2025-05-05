package androidx.media3.datasource;

import android.net.Uri;
import androidx.media3.common.DataReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public interface DataSource extends DataReader {

    public interface Factory {
        DataSource createDataSource();
    }

    void addTransferListener(TransferListener transferListener);

    void close() throws IOException;

    Uri getUri();

    long open(DataSpec dataSpec) throws IOException;

    Map<String, List<String>> getResponseHeaders() {
        return Collections.emptyMap();
    }
}

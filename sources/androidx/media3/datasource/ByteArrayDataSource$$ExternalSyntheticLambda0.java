package androidx.media3.datasource;

import android.net.Uri;
import androidx.media3.datasource.ByteArrayDataSource;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ByteArrayDataSource$$ExternalSyntheticLambda0 implements ByteArrayDataSource.UriResolver {
    public final /* synthetic */ byte[] f$0;

    public /* synthetic */ ByteArrayDataSource$$ExternalSyntheticLambda0(byte[] bArr) {
        this.f$0 = bArr;
    }

    public final byte[] resolve(Uri uri) {
        return ByteArrayDataSource.lambda$new$0(this.f$0, uri);
    }
}

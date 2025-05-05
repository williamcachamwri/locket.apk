package androidx.media3.datasource;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BitmapLoader;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DefaultDataSource;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import java.io.IOException;

public final class DataSourceBitmapLoader implements BitmapLoader {
    public static final Supplier<ListeningExecutorService> DEFAULT_EXECUTOR_SERVICE = Suppliers.memoize(new DataSourceBitmapLoader$$ExternalSyntheticLambda1());
    private final DataSource.Factory dataSourceFactory;
    private final ListeningExecutorService listeningExecutorService;
    private final int maximumOutputDimension;
    private final BitmapFactory.Options options;

    public DataSourceBitmapLoader(Context context) {
        this((ListeningExecutorService) Assertions.checkStateNotNull(DEFAULT_EXECUTOR_SERVICE.get()), new DefaultDataSource.Factory(context));
    }

    public DataSourceBitmapLoader(ListeningExecutorService listeningExecutorService2, DataSource.Factory factory) {
        this(listeningExecutorService2, factory, (BitmapFactory.Options) null);
    }

    public DataSourceBitmapLoader(ListeningExecutorService listeningExecutorService2, DataSource.Factory factory, BitmapFactory.Options options2) {
        this(listeningExecutorService2, factory, options2, -1);
    }

    public DataSourceBitmapLoader(ListeningExecutorService listeningExecutorService2, DataSource.Factory factory, BitmapFactory.Options options2, int i) {
        this.listeningExecutorService = listeningExecutorService2;
        this.dataSourceFactory = factory;
        this.options = options2;
        this.maximumOutputDimension = i;
    }

    public boolean supportsMimeType(String str) {
        return Util.isBitmapFactorySupportedMimeType(str);
    }

    public ListenableFuture<Bitmap> decodeBitmap(byte[] bArr) {
        return this.listeningExecutorService.submit(new DataSourceBitmapLoader$$ExternalSyntheticLambda0(this, bArr));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$decodeBitmap$1$androidx-media3-datasource-DataSourceBitmapLoader  reason: not valid java name */
    public /* synthetic */ Bitmap m404lambda$decodeBitmap$1$androidxmedia3datasourceDataSourceBitmapLoader(byte[] bArr) throws Exception {
        return BitmapUtil.decode(bArr, bArr.length, this.options, this.maximumOutputDimension);
    }

    public ListenableFuture<Bitmap> loadBitmap(Uri uri) {
        return this.listeningExecutorService.submit(new DataSourceBitmapLoader$$ExternalSyntheticLambda2(this, uri));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$loadBitmap$2$androidx-media3-datasource-DataSourceBitmapLoader  reason: not valid java name */
    public /* synthetic */ Bitmap m405lambda$loadBitmap$2$androidxmedia3datasourceDataSourceBitmapLoader(Uri uri) throws Exception {
        return load(this.dataSourceFactory.createDataSource(), uri, this.options, this.maximumOutputDimension);
    }

    private static Bitmap load(DataSource dataSource, Uri uri, BitmapFactory.Options options2, int i) throws IOException {
        try {
            dataSource.open(new DataSpec(uri));
            byte[] readToEnd = DataSourceUtil.readToEnd(dataSource);
            return BitmapUtil.decode(readToEnd, readToEnd.length, options2, i);
        } finally {
            dataSource.close();
        }
    }
}

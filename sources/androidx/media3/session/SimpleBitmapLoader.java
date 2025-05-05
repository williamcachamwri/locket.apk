package androidx.media3.session;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BitmapLoader;
import androidx.media3.common.util.Util;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.io.ByteStreams;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;

@Deprecated
public final class SimpleBitmapLoader implements BitmapLoader {
    private static final Supplier<ListeningExecutorService> DEFAULT_EXECUTOR_SERVICE = Suppliers.memoize(new SimpleBitmapLoader$$ExternalSyntheticLambda2());
    private static final String FILE_URI_EXCEPTION_MESSAGE = "Could not read image from file";
    private final ListeningExecutorService executorService;

    public SimpleBitmapLoader() {
        this((ExecutorService) Assertions.checkStateNotNull(DEFAULT_EXECUTOR_SERVICE.get()));
    }

    public SimpleBitmapLoader(ExecutorService executorService2) {
        this.executorService = MoreExecutors.listeningDecorator(executorService2);
    }

    public boolean supportsMimeType(String str) {
        return Util.isBitmapFactorySupportedMimeType(str);
    }

    public ListenableFuture<Bitmap> decodeBitmap(byte[] bArr) {
        return this.executorService.submit(new SimpleBitmapLoader$$ExternalSyntheticLambda1(bArr));
    }

    public ListenableFuture<Bitmap> loadBitmap(Uri uri) {
        return this.executorService.submit(new SimpleBitmapLoader$$ExternalSyntheticLambda0(uri));
    }

    /* access modifiers changed from: private */
    public static Bitmap decode(byte[] bArr) {
        boolean z = false;
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        if (decodeByteArray != null) {
            z = true;
        }
        Assertions.checkArgument(z, "Could not decode image data");
        return decodeByteArray;
    }

    /* access modifiers changed from: private */
    public static Bitmap load(Uri uri) throws IOException {
        if ("file".equals(uri.getScheme())) {
            String path = uri.getPath();
            if (path != null) {
                Bitmap decodeFile = BitmapFactory.decodeFile(path);
                if (decodeFile != null) {
                    return decodeFile;
                }
                throw new IllegalArgumentException(FILE_URI_EXCEPTION_MESSAGE);
            }
            throw new IllegalArgumentException(FILE_URI_EXCEPTION_MESSAGE);
        }
        URLConnection uRLConnection = (URLConnection) FirebasePerfUrlConnection.instrument(new URL(uri.toString()).openConnection());
        if (uRLConnection instanceof HttpURLConnection) {
            HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnection;
            httpURLConnection.connect();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                InputStream inputStream = httpURLConnection.getInputStream();
                try {
                    Bitmap decode = decode(ByteStreams.toByteArray(inputStream));
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    return decode;
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            } else {
                throw new IOException("Invalid response status code: " + responseCode);
            }
        } else {
            throw new UnsupportedOperationException("Unsupported scheme: " + uri.getScheme());
        }
        throw th;
    }
}

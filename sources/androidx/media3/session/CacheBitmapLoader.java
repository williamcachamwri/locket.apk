package androidx.media3.session;

import android.graphics.Bitmap;
import android.net.Uri;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BitmapLoader;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Arrays;

public final class CacheBitmapLoader implements BitmapLoader {
    private final BitmapLoader bitmapLoader;
    private BitmapLoadRequest lastBitmapLoadRequest;

    public CacheBitmapLoader(BitmapLoader bitmapLoader2) {
        this.bitmapLoader = bitmapLoader2;
    }

    public boolean supportsMimeType(String str) {
        return this.bitmapLoader.supportsMimeType(str);
    }

    public ListenableFuture<Bitmap> decodeBitmap(byte[] bArr) {
        BitmapLoadRequest bitmapLoadRequest = this.lastBitmapLoadRequest;
        if (bitmapLoadRequest != null && bitmapLoadRequest.matches(bArr)) {
            return this.lastBitmapLoadRequest.getFuture();
        }
        ListenableFuture<Bitmap> decodeBitmap = this.bitmapLoader.decodeBitmap(bArr);
        this.lastBitmapLoadRequest = new BitmapLoadRequest(bArr, decodeBitmap);
        return decodeBitmap;
    }

    public ListenableFuture<Bitmap> loadBitmap(Uri uri) {
        BitmapLoadRequest bitmapLoadRequest = this.lastBitmapLoadRequest;
        if (bitmapLoadRequest != null && bitmapLoadRequest.matches(uri)) {
            return this.lastBitmapLoadRequest.getFuture();
        }
        ListenableFuture<Bitmap> loadBitmap = this.bitmapLoader.loadBitmap(uri);
        this.lastBitmapLoadRequest = new BitmapLoadRequest(uri, loadBitmap);
        return loadBitmap;
    }

    private static class BitmapLoadRequest {
        private final byte[] data;
        private final ListenableFuture<Bitmap> future;
        private final Uri uri;

        public BitmapLoadRequest(byte[] bArr, ListenableFuture<Bitmap> listenableFuture) {
            this.data = bArr;
            this.uri = null;
            this.future = listenableFuture;
        }

        public BitmapLoadRequest(Uri uri2, ListenableFuture<Bitmap> listenableFuture) {
            this.data = null;
            this.uri = uri2;
            this.future = listenableFuture;
        }

        public boolean matches(byte[] bArr) {
            byte[] bArr2 = this.data;
            return bArr2 != null && Arrays.equals(bArr2, bArr);
        }

        public boolean matches(Uri uri2) {
            Uri uri3 = this.uri;
            return uri3 != null && uri3.equals(uri2);
        }

        public ListenableFuture<Bitmap> getFuture() {
            return (ListenableFuture) Assertions.checkStateNotNull(this.future);
        }
    }
}

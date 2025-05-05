package androidx.media3.common.util;

import android.graphics.Bitmap;
import android.net.Uri;
import androidx.media3.common.MediaMetadata;
import com.google.common.util.concurrent.ListenableFuture;

public interface BitmapLoader {
    ListenableFuture<Bitmap> decodeBitmap(byte[] bArr);

    ListenableFuture<Bitmap> loadBitmap(Uri uri);

    boolean supportsMimeType(String str);

    ListenableFuture<Bitmap> loadBitmapFromMetadata(MediaMetadata mediaMetadata) {
        if (mediaMetadata.artworkData != null) {
            return decodeBitmap(mediaMetadata.artworkData);
        }
        if (mediaMetadata.artworkUri != null) {
            return loadBitmap(mediaMetadata.artworkUri);
        }
        return null;
    }
}

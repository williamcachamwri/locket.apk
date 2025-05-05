package androidx.media3.transformer;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import android.os.Looper;
import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BitmapLoader;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSourceBitmapLoader;
import androidx.media3.datasource.DefaultDataSource;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.transformer.AssetLoader;
import androidx.media3.transformer.Codec;
import androidx.media3.transformer.DefaultDecoderFactory;
import androidx.media3.transformer.ExoPlayerAssetLoader;
import androidx.media3.transformer.ImageAssetLoader;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.Executors;

public final class DefaultAssetLoaderFactory implements AssetLoader.Factory {
    private static final int MAXIMUM_BITMAP_OUTPUT_DIMENSION = 4096;
    private static final String TAG = "DefaultAssetLoaderFact";
    private final BitmapLoader bitmapLoader;
    private final Clock clock;
    private final Context context;
    private final Codec.DecoderFactory decoderFactory;
    private AssetLoader.Factory exoPlayerAssetLoaderFactory;
    private AssetLoader.Factory imageAssetLoaderFactory;
    private final MediaSource.Factory mediaSourceFactory;

    public DefaultAssetLoaderFactory(Context context2, Codec.DecoderFactory decoderFactory2, Clock clock2) {
        this.context = context2.getApplicationContext();
        this.decoderFactory = decoderFactory2;
        this.clock = clock2;
        BitmapFactory.Options options = null;
        this.mediaSourceFactory = null;
        if (Util.SDK_INT >= 26) {
            options = new BitmapFactory.Options();
            options.inPreferredColorSpace = ColorSpace.get(ColorSpace.Named.SRGB);
        }
        this.bitmapLoader = new DataSourceBitmapLoader(MoreExecutors.listeningDecorator(Executors.newSingleThreadExecutor()), new DefaultDataSource.Factory(context2), options, 4096);
    }

    public DefaultAssetLoaderFactory(Context context2, BitmapLoader bitmapLoader2) {
        this.context = context2.getApplicationContext();
        this.bitmapLoader = bitmapLoader2;
        this.decoderFactory = new DefaultDecoderFactory.Builder(context2).build();
        this.clock = Clock.DEFAULT;
        this.mediaSourceFactory = null;
    }

    public DefaultAssetLoaderFactory(Context context2, Codec.DecoderFactory decoderFactory2, Clock clock2, MediaSource.Factory factory, BitmapLoader bitmapLoader2) {
        this.context = context2.getApplicationContext();
        this.decoderFactory = decoderFactory2;
        this.clock = clock2;
        this.mediaSourceFactory = factory;
        this.bitmapLoader = bitmapLoader2;
    }

    public AssetLoader createAssetLoader(EditedMediaItem editedMediaItem, Looper looper, AssetLoader.Listener listener, AssetLoader.CompositionSettings compositionSettings) {
        ExoPlayerAssetLoader.Factory factory;
        MediaItem mediaItem = editedMediaItem.mediaItem;
        boolean isImage = TransformerUtil.isImage(this.context, mediaItem);
        boolean z = isImage && editedMediaItem.durationUs == C.TIME_UNSET;
        if (!isImage || z) {
            if (this.exoPlayerAssetLoaderFactory == null) {
                if (this.mediaSourceFactory != null) {
                    factory = new ExoPlayerAssetLoader.Factory(this.context, this.decoderFactory, this.clock, this.mediaSourceFactory);
                } else {
                    factory = new ExoPlayerAssetLoader.Factory(this.context, this.decoderFactory, this.clock);
                }
                this.exoPlayerAssetLoaderFactory = factory;
            }
            return this.exoPlayerAssetLoaderFactory.createAssetLoader(editedMediaItem, looper, listener, compositionSettings);
        }
        if (((MediaItem.LocalConfiguration) Assertions.checkNotNull(mediaItem.localConfiguration)).imageDurationMs == C.TIME_UNSET) {
            Log.w(TAG, "The imageDurationMs field must be set on image MediaItems.");
        }
        if (this.imageAssetLoaderFactory == null) {
            this.imageAssetLoaderFactory = new ImageAssetLoader.Factory(this.context, this.bitmapLoader);
        }
        return this.imageAssetLoaderFactory.createAssetLoader(editedMediaItem, looper, listener, compositionSettings);
    }
}

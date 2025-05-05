package androidx.media3.exoplayer.mediacodec;

import android.content.Context;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.mediacodec.AsynchronousMediaCodecAdapter;
import androidx.media3.exoplayer.mediacodec.MediaCodecAdapter;
import androidx.media3.exoplayer.mediacodec.SynchronousMediaCodecAdapter;
import java.io.IOException;

public final class DefaultMediaCodecAdapterFactory implements MediaCodecAdapter.Factory {
    private static final int MODE_DEFAULT = 0;
    private static final int MODE_DISABLED = 2;
    private static final int MODE_ENABLED = 1;
    private static final String TAG = "DMCodecAdapterFactory";
    private boolean asyncCryptoFlagEnabled;
    private int asynchronousMode;
    private final Context context;

    @Deprecated
    public DefaultMediaCodecAdapterFactory() {
        this.asynchronousMode = 0;
        this.asyncCryptoFlagEnabled = false;
        this.context = null;
    }

    public DefaultMediaCodecAdapterFactory(Context context2) {
        this.context = context2;
        this.asynchronousMode = 0;
        this.asyncCryptoFlagEnabled = false;
    }

    public DefaultMediaCodecAdapterFactory forceEnableAsynchronous() {
        this.asynchronousMode = 1;
        return this;
    }

    public DefaultMediaCodecAdapterFactory forceDisableAsynchronous() {
        this.asynchronousMode = 2;
        return this;
    }

    public DefaultMediaCodecAdapterFactory experimentalSetAsyncCryptoFlagEnabled(boolean z) {
        this.asyncCryptoFlagEnabled = z;
        return this;
    }

    public MediaCodecAdapter createAdapter(MediaCodecAdapter.Configuration configuration) throws IOException {
        int i;
        if (Util.SDK_INT < 23 || ((i = this.asynchronousMode) != 1 && (i != 0 || !shouldUseAsynchronousAdapterInDefaultMode()))) {
            return new SynchronousMediaCodecAdapter.Factory().createAdapter(configuration);
        }
        int trackType = MimeTypes.getTrackType(configuration.format.sampleMimeType);
        Log.i(TAG, "Creating an asynchronous MediaCodec adapter for track type " + Util.getTrackTypeString(trackType));
        AsynchronousMediaCodecAdapter.Factory factory = new AsynchronousMediaCodecAdapter.Factory(trackType);
        factory.experimentalSetAsyncCryptoFlagEnabled(this.asyncCryptoFlagEnabled);
        return factory.createAdapter(configuration);
    }

    private boolean shouldUseAsynchronousAdapterInDefaultMode() {
        if (Util.SDK_INT >= 31) {
            return true;
        }
        if (this.context == null || Util.SDK_INT < 28 || !this.context.getPackageManager().hasSystemFeature("com.amazon.hardware.tv_screen")) {
            return false;
        }
        return true;
    }
}

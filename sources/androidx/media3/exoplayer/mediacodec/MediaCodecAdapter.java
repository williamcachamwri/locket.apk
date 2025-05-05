package androidx.media3.exoplayer.mediacodec;

import android.content.Context;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.Surface;
import androidx.media3.common.Format;
import androidx.media3.decoder.CryptoInfo;
import java.io.IOException;
import java.nio.ByteBuffer;

public interface MediaCodecAdapter {

    public interface OnBufferAvailableListener {
        void onInputBufferAvailable() {
        }

        void onOutputBufferAvailable() {
        }
    }

    public interface OnFrameRenderedListener {
        void onFrameRendered(MediaCodecAdapter mediaCodecAdapter, long j, long j2);
    }

    int dequeueInputBufferIndex();

    int dequeueOutputBufferIndex(MediaCodec.BufferInfo bufferInfo);

    void detachOutputSurface();

    void flush();

    ByteBuffer getInputBuffer(int i);

    PersistableBundle getMetrics();

    ByteBuffer getOutputBuffer(int i);

    MediaFormat getOutputFormat();

    boolean needsReconfiguration();

    void queueInputBuffer(int i, int i2, int i3, long j, int i4);

    void queueSecureInputBuffer(int i, int i2, CryptoInfo cryptoInfo, long j, int i3);

    boolean registerOnBufferAvailableListener(OnBufferAvailableListener onBufferAvailableListener) {
        return false;
    }

    void release();

    void releaseOutputBuffer(int i, long j);

    void releaseOutputBuffer(int i, boolean z);

    void setOnFrameRenderedListener(OnFrameRenderedListener onFrameRenderedListener, Handler handler);

    void setOutputSurface(Surface surface);

    void setParameters(Bundle bundle);

    void setVideoScalingMode(int i);

    public static final class Configuration {
        public final MediaCodecInfo codecInfo;
        public final MediaCrypto crypto;
        public final Format format;
        public final LoudnessCodecController loudnessCodecController;
        public final MediaFormat mediaFormat;
        public final Surface surface;

        public static Configuration createForAudioDecoding(MediaCodecInfo mediaCodecInfo, MediaFormat mediaFormat2, Format format2, MediaCrypto mediaCrypto, LoudnessCodecController loudnessCodecController2) {
            return new Configuration(mediaCodecInfo, mediaFormat2, format2, (Surface) null, mediaCrypto, loudnessCodecController2);
        }

        public static Configuration createForVideoDecoding(MediaCodecInfo mediaCodecInfo, MediaFormat mediaFormat2, Format format2, Surface surface2, MediaCrypto mediaCrypto) {
            return new Configuration(mediaCodecInfo, mediaFormat2, format2, surface2, mediaCrypto, (LoudnessCodecController) null);
        }

        private Configuration(MediaCodecInfo mediaCodecInfo, MediaFormat mediaFormat2, Format format2, Surface surface2, MediaCrypto mediaCrypto, LoudnessCodecController loudnessCodecController2) {
            this.codecInfo = mediaCodecInfo;
            this.mediaFormat = mediaFormat2;
            this.format = format2;
            this.surface = surface2;
            this.crypto = mediaCrypto;
            this.loudnessCodecController = loudnessCodecController2;
        }
    }

    public interface Factory {
        @Deprecated
        public static final Factory DEFAULT = new DefaultMediaCodecAdapterFactory();

        MediaCodecAdapter createAdapter(Configuration configuration) throws IOException;

        static Factory getDefault(Context context) {
            return new DefaultMediaCodecAdapterFactory(context);
        }
    }
}

package androidx.media3.exoplayer.video.spherical;

import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.BaseRenderer;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.audio.SilenceSkippingAudioProcessor;
import java.nio.ByteBuffer;

public final class CameraMotionRenderer extends BaseRenderer {
    private static final int SAMPLE_WINDOW_DURATION_US = 100000;
    private static final String TAG = "CameraMotionRenderer";
    private final DecoderInputBuffer buffer = new DecoderInputBuffer(1);
    private long lastTimestampUs;
    private CameraMotionListener listener;
    private final ParsableByteArray scratch = new ParsableByteArray();

    public String getName() {
        return TAG;
    }

    public boolean isReady() {
        return true;
    }

    public CameraMotionRenderer() {
        super(6);
    }

    public int supportsFormat(Format format) {
        if (MimeTypes.APPLICATION_CAMERA_MOTION.equals(format.sampleMimeType)) {
            return RendererCapabilities.create(4);
        }
        return RendererCapabilities.create(0);
    }

    public void handleMessage(int i, Object obj) throws ExoPlaybackException {
        if (i == 8) {
            this.listener = (CameraMotionListener) obj;
        } else {
            super.handleMessage(i, obj);
        }
    }

    /* access modifiers changed from: protected */
    public void onPositionReset(long j, boolean z) {
        this.lastTimestampUs = Long.MIN_VALUE;
        resetListener();
    }

    /* access modifiers changed from: protected */
    public void onDisabled() {
        resetListener();
    }

    public void render(long j, long j2) {
        while (!hasReadStreamToEnd() && this.lastTimestampUs < SilenceSkippingAudioProcessor.DEFAULT_MINIMUM_SILENCE_DURATION_US + j) {
            this.buffer.clear();
            boolean z = false;
            if (readSource(getFormatHolder(), this.buffer, 0) == -4 && !this.buffer.isEndOfStream()) {
                long j3 = this.buffer.timeUs;
                this.lastTimestampUs = j3;
                if (j3 < getLastResetPositionUs()) {
                    z = true;
                }
                if (this.listener != null && !z) {
                    this.buffer.flip();
                    float[] parseMetadata = parseMetadata((ByteBuffer) Util.castNonNull(this.buffer.data));
                    if (parseMetadata != null) {
                        ((CameraMotionListener) Util.castNonNull(this.listener)).onCameraMotion(this.lastTimestampUs - getStreamOffsetUs(), parseMetadata);
                    }
                }
            } else {
                return;
            }
        }
    }

    public boolean isEnded() {
        return hasReadStreamToEnd();
    }

    private float[] parseMetadata(ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() != 16) {
            return null;
        }
        this.scratch.reset(byteBuffer.array(), byteBuffer.limit());
        this.scratch.setPosition(byteBuffer.arrayOffset() + 4);
        float[] fArr = new float[3];
        for (int i = 0; i < 3; i++) {
            fArr[i] = Float.intBitsToFloat(this.scratch.readLittleEndianInt());
        }
        return fArr;
    }

    private void resetListener() {
        CameraMotionListener cameraMotionListener = this.listener;
        if (cameraMotionListener != null) {
            cameraMotionListener.onCameraMotionReset();
        }
    }
}

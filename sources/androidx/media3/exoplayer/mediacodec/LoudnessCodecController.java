package androidx.media3.exoplayer.mediacodec;

import android.media.LoudnessCodecController;
import android.media.MediaCodec;
import android.os.Bundle;
import androidx.media3.common.util.Assertions;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.HashSet;
import java.util.Iterator;

public final class LoudnessCodecController {
    private android.media.LoudnessCodecController loudnessCodecController;
    private final HashSet<MediaCodec> mediaCodecs;
    /* access modifiers changed from: private */
    public final LoudnessParameterUpdateListener updateListener;

    public interface LoudnessParameterUpdateListener {
        public static final LoudnessParameterUpdateListener DEFAULT = new LoudnessCodecController$LoudnessParameterUpdateListener$$ExternalSyntheticLambda0();

        static /* synthetic */ Bundle lambda$static$0(Bundle bundle) {
            return bundle;
        }

        Bundle onLoudnessParameterUpdate(Bundle bundle);
    }

    public LoudnessCodecController() {
        this(LoudnessParameterUpdateListener.DEFAULT);
    }

    public LoudnessCodecController(LoudnessParameterUpdateListener loudnessParameterUpdateListener) {
        this.mediaCodecs = new HashSet<>();
        this.updateListener = loudnessParameterUpdateListener;
    }

    public void setAudioSessionId(int i) {
        android.media.LoudnessCodecController loudnessCodecController2 = this.loudnessCodecController;
        if (loudnessCodecController2 != null) {
            loudnessCodecController2.close();
            this.loudnessCodecController = null;
        }
        android.media.LoudnessCodecController create = android.media.LoudnessCodecController.create(i, MoreExecutors.directExecutor(), new LoudnessCodecController.OnLoudnessCodecUpdateListener() {
            public Bundle onLoudnessCodecUpdate(MediaCodec mediaCodec, Bundle bundle) {
                return LoudnessCodecController.this.updateListener.onLoudnessParameterUpdate(bundle);
            }
        });
        this.loudnessCodecController = create;
        Iterator<MediaCodec> it = this.mediaCodecs.iterator();
        while (it.hasNext()) {
            if (!create.addMediaCodec(it.next())) {
                it.remove();
            }
        }
    }

    public void addMediaCodec(MediaCodec mediaCodec) {
        android.media.LoudnessCodecController loudnessCodecController2 = this.loudnessCodecController;
        if (loudnessCodecController2 == null || loudnessCodecController2.addMediaCodec(mediaCodec)) {
            Assertions.checkState(this.mediaCodecs.add(mediaCodec));
        }
    }

    public void removeMediaCodec(MediaCodec mediaCodec) {
        android.media.LoudnessCodecController loudnessCodecController2;
        if (this.mediaCodecs.remove(mediaCodec) && (loudnessCodecController2 = this.loudnessCodecController) != null) {
            loudnessCodecController2.removeMediaCodec(mediaCodec);
        }
    }

    public void release() {
        this.mediaCodecs.clear();
        android.media.LoudnessCodecController loudnessCodecController2 = this.loudnessCodecController;
        if (loudnessCodecController2 != null) {
            loudnessCodecController2.close();
        }
    }
}

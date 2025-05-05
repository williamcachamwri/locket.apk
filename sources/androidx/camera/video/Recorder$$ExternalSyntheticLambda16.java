package androidx.camera.video;

import androidx.camera.video.internal.encoder.Encoder;
import androidx.camera.video.internal.encoder.EncoderConfig;
import androidx.camera.video.internal.encoder.EncoderFactory;
import androidx.camera.video.internal.encoder.EncoderImpl;
import java.util.concurrent.Executor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Recorder$$ExternalSyntheticLambda16 implements EncoderFactory {
    public final Encoder createEncoder(Executor executor, EncoderConfig encoderConfig) {
        return new EncoderImpl(executor, encoderConfig);
    }
}

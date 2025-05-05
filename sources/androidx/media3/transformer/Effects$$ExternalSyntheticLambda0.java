package androidx.media3.transformer;

import androidx.media3.common.audio.SpeedChangingAudioProcessor;
import androidx.media3.common.util.TimestampConsumer;
import androidx.media3.effect.TimestampAdjustment;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Effects$$ExternalSyntheticLambda0 implements TimestampAdjustment.TimestampMap {
    public final /* synthetic */ SpeedChangingAudioProcessor f$0;

    public /* synthetic */ Effects$$ExternalSyntheticLambda0(SpeedChangingAudioProcessor speedChangingAudioProcessor) {
        this.f$0 = speedChangingAudioProcessor;
    }

    public final void calculateOutputTimeUs(long j, TimestampConsumer timestampConsumer) {
        this.f$0.getSpeedAdjustedTimeAsync(j, timestampConsumer);
    }
}

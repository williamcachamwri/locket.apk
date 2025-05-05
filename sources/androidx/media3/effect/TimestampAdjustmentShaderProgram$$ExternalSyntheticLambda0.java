package androidx.media3.effect;

import androidx.media3.common.util.TimestampConsumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TimestampAdjustmentShaderProgram$$ExternalSyntheticLambda0 implements TimestampConsumer {
    public final /* synthetic */ TimestampAdjustmentShaderProgram f$0;

    public /* synthetic */ TimestampAdjustmentShaderProgram$$ExternalSyntheticLambda0(TimestampAdjustmentShaderProgram timestampAdjustmentShaderProgram) {
        this.f$0 = timestampAdjustmentShaderProgram;
    }

    public final void onTimestamp(long j) {
        this.f$0.onOutputTimeAvailable(j);
    }
}

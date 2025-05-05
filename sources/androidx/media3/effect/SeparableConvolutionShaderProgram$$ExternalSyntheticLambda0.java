package androidx.media3.effect;

import androidx.media3.common.util.GlUtil;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SeparableConvolutionShaderProgram$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ SeparableConvolutionShaderProgram f$0;
    public final /* synthetic */ GlUtil.GlException f$1;
    public final /* synthetic */ long f$2;

    public /* synthetic */ SeparableConvolutionShaderProgram$$ExternalSyntheticLambda0(SeparableConvolutionShaderProgram separableConvolutionShaderProgram, GlUtil.GlException glException, long j) {
        this.f$0 = separableConvolutionShaderProgram;
        this.f$1 = glException;
        this.f$2 = j;
    }

    public final void run() {
        this.f$0.m452lambda$queueInputFrame$1$androidxmedia3effectSeparableConvolutionShaderProgram(this.f$1, this.f$2);
    }
}

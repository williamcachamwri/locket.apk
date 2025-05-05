package androidx.media3.effect;

import android.content.Context;
import androidx.media3.common.GlObjectsProvider;
import androidx.media3.common.GlTextureInfo;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.Assertions;
import androidx.media3.effect.GlShaderProgram;
import java.util.concurrent.Executor;

final class TimestampWrapperShaderProgram implements GlShaderProgram, GlShaderProgram.InputListener {
    private final GlShaderProgram copyShaderProgram;
    private final long endTimeUs;
    private int pendingCopyGlShaderProgramFrames;
    private int pendingWrappedGlShaderProgramFrames;
    private final long startTimeUs;
    private final GlShaderProgram wrappedShaderProgram;
    private final WrappedShaderProgramInputListener wrappedShaderProgramInputListener;

    public TimestampWrapperShaderProgram(Context context, boolean z, TimestampWrapper timestampWrapper) throws VideoFrameProcessingException {
        this.startTimeUs = timestampWrapper.startTimeUs;
        this.endTimeUs = timestampWrapper.endTimeUs;
        GlShaderProgram glShaderProgram = timestampWrapper.glEffect.toGlShaderProgram(context, z);
        this.wrappedShaderProgram = glShaderProgram;
        WrappedShaderProgramInputListener wrappedShaderProgramInputListener2 = new WrappedShaderProgramInputListener();
        this.wrappedShaderProgramInputListener = wrappedShaderProgramInputListener2;
        glShaderProgram.setInputListener(wrappedShaderProgramInputListener2);
        this.copyShaderProgram = new FrameCache(wrappedShaderProgramInputListener2.readyFrameCount).toGlShaderProgram(context, z);
    }

    public void setInputListener(GlShaderProgram.InputListener inputListener) {
        this.wrappedShaderProgramInputListener.setListener(inputListener);
        this.wrappedShaderProgramInputListener.setToForwardingMode(true);
        this.copyShaderProgram.setInputListener(inputListener);
    }

    public void setOutputListener(GlShaderProgram.OutputListener outputListener) {
        this.wrappedShaderProgram.setOutputListener(outputListener);
        this.copyShaderProgram.setOutputListener(outputListener);
    }

    public void setErrorListener(Executor executor, GlShaderProgram.ErrorListener errorListener) {
        this.wrappedShaderProgram.setErrorListener(executor, errorListener);
        this.copyShaderProgram.setErrorListener(executor, errorListener);
    }

    public void queueInputFrame(GlObjectsProvider glObjectsProvider, GlTextureInfo glTextureInfo, long j) {
        if (this.startTimeUs > j || j > this.endTimeUs) {
            this.pendingCopyGlShaderProgramFrames++;
            this.copyShaderProgram.queueInputFrame(glObjectsProvider, glTextureInfo, j);
            return;
        }
        this.pendingWrappedGlShaderProgramFrames++;
        this.wrappedShaderProgram.queueInputFrame(glObjectsProvider, glTextureInfo, j);
    }

    public void releaseOutputFrame(GlTextureInfo glTextureInfo) {
        if (this.pendingCopyGlShaderProgramFrames > 0) {
            this.copyShaderProgram.releaseOutputFrame(glTextureInfo);
            this.pendingCopyGlShaderProgramFrames--;
        } else if (this.pendingWrappedGlShaderProgramFrames > 0) {
            this.wrappedShaderProgram.releaseOutputFrame(glTextureInfo);
            this.pendingWrappedGlShaderProgramFrames--;
        } else {
            throw new IllegalArgumentException("Output texture not contained in either shader.");
        }
    }

    public void signalEndOfCurrentInputStream() {
        this.wrappedShaderProgram.signalEndOfCurrentInputStream();
    }

    public void flush() {
        this.wrappedShaderProgramInputListener.setToForwardingMode(false);
        this.wrappedShaderProgram.flush();
        this.wrappedShaderProgramInputListener.setToForwardingMode(true);
        this.copyShaderProgram.flush();
        this.pendingCopyGlShaderProgramFrames = 0;
        this.pendingWrappedGlShaderProgramFrames = 0;
    }

    public void release() throws VideoFrameProcessingException {
        this.copyShaderProgram.release();
        this.wrappedShaderProgram.release();
    }

    private static final class WrappedShaderProgramInputListener implements GlShaderProgram.InputListener {
        private boolean forwardCalls;
        private GlShaderProgram.InputListener listener;
        public int readyFrameCount;

        public void onFlush() {
        }

        private WrappedShaderProgramInputListener() {
        }

        public void onReadyToAcceptInputFrame() {
            GlShaderProgram.InputListener inputListener = this.listener;
            if (inputListener == null) {
                this.readyFrameCount++;
            }
            if (this.forwardCalls) {
                ((GlShaderProgram.InputListener) Assertions.checkNotNull(inputListener)).onReadyToAcceptInputFrame();
            }
        }

        public void onInputFrameProcessed(GlTextureInfo glTextureInfo) {
            ((GlShaderProgram.InputListener) Assertions.checkNotNull(this.listener)).onInputFrameProcessed(glTextureInfo);
        }

        public void setListener(GlShaderProgram.InputListener inputListener) {
            this.listener = inputListener;
        }

        public void setToForwardingMode(boolean z) {
            Assertions.checkState(!z || this.listener != null);
            this.forwardCalls = z;
        }
    }
}

package androidx.media3.common;

public interface OnInputFrameProcessedListener {
    void onInputFrameProcessed(int i, long j) throws VideoFrameProcessingException;
}

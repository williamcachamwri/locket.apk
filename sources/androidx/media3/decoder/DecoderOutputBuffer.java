package androidx.media3.decoder;

public abstract class DecoderOutputBuffer extends Buffer {
    public boolean shouldBeSkipped;
    public int skippedOutputBufferCount;
    public long timeUs;

    public interface Owner<S extends DecoderOutputBuffer> {
        void releaseOutputBuffer(S s);
    }

    public abstract void release();

    public void clear() {
        super.clear();
        this.timeUs = 0;
        this.skippedOutputBufferCount = 0;
        this.shouldBeSkipped = false;
    }
}

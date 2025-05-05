package androidx.emoji2.text.flatbuffer;

import androidx.media3.muxer.MuxerUtil;
import java.nio.ByteBuffer;

public final class IntVector extends BaseVector {
    public IntVector __assign(int i, ByteBuffer byteBuffer) {
        __reset(i, 4, byteBuffer);
        return this;
    }

    public int get(int i) {
        return this.bb.getInt(__element(i));
    }

    public long getAsUnsigned(int i) {
        return ((long) get(i)) & MuxerUtil.UNSIGNED_INT_MAX_VALUE;
    }
}

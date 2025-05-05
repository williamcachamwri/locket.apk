package androidx.media3.muxer;

import androidx.media3.common.util.Assertions;
import com.google.common.collect.ImmutableList;
import java.nio.ByteBuffer;

public interface AnnexBToAvccConverter {
    public static final AnnexBToAvccConverter DEFAULT = new AnnexBToAvccConverter$$ExternalSyntheticLambda0();

    ByteBuffer process(ByteBuffer byteBuffer);

    static /* synthetic */ ByteBuffer lambda$static$0(ByteBuffer byteBuffer) {
        if (!byteBuffer.hasRemaining()) {
            return byteBuffer;
        }
        Assertions.checkArgument(byteBuffer.position() == 0, "The input buffer should have position set to 0.");
        ImmutableList<ByteBuffer> findNalUnits = AnnexBUtils.findNalUnits(byteBuffer);
        int i = 0;
        for (int i2 = 0; i2 < findNalUnits.size(); i2++) {
            i += ((ByteBuffer) findNalUnits.get(i2)).remaining() + 4;
        }
        ByteBuffer allocate = ByteBuffer.allocate(i);
        for (int i3 = 0; i3 < findNalUnits.size(); i3++) {
            ByteBuffer byteBuffer2 = (ByteBuffer) findNalUnits.get(i3);
            allocate.putInt(byteBuffer2.remaining());
            allocate.put(byteBuffer2);
        }
        allocate.rewind();
        return allocate;
    }
}

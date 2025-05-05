package androidx.media3.muxer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.List;

final class BoxUtils {
    private static final int BOX_SIZE_BYTES = 4;
    private static final int BOX_TYPE_BYTES = 4;

    private BoxUtils() {
    }

    public static ByteBuffer wrapIntoBox(String str, ByteBuffer byteBuffer) {
        return wrapIntoBox(str.getBytes(StandardCharsets.UTF_8), byteBuffer);
    }

    public static ByteBuffer wrapIntoBox(byte[] bArr, ByteBuffer byteBuffer) {
        ByteBuffer allocate = ByteBuffer.allocate(byteBuffer.remaining() + 4 + 4);
        allocate.putInt(byteBuffer.remaining() + 4 + 4);
        allocate.put(bArr, 0, 4);
        allocate.put(byteBuffer);
        allocate.flip();
        return allocate;
    }

    public static ByteBuffer wrapBoxesIntoBox(String str, List<ByteBuffer> list) {
        int i = 8;
        for (int i2 = 0; i2 < list.size(); i2++) {
            i += list.get(i2).remaining();
        }
        ByteBuffer allocate = ByteBuffer.allocate(i);
        allocate.putInt(i);
        allocate.put(str.getBytes(StandardCharsets.UTF_8), 0, 4);
        for (int i3 = 0; i3 < list.size(); i3++) {
            allocate.put(list.get(i3));
        }
        allocate.flip();
        return allocate;
    }

    public static ByteBuffer concatenateBuffers(ByteBuffer... byteBufferArr) {
        int i = 0;
        for (ByteBuffer remaining : byteBufferArr) {
            i += remaining.remaining();
        }
        ByteBuffer allocate = ByteBuffer.allocate(i);
        for (ByteBuffer put : byteBufferArr) {
            allocate.put(put);
        }
        allocate.flip();
        return allocate;
    }
}

package com.facebook.common.memory;

import androidx.core.util.Pools;
import java.nio.ByteBuffer;

public class DecodeBufferHelper implements Pools.Pool<ByteBuffer> {
    private static final int DEFAULT_DECODE_BUFFER_SIZE = 16384;
    public static final DecodeBufferHelper INSTANCE = new DecodeBufferHelper();
    private static final ThreadLocal<ByteBuffer> sBuffer = new ThreadLocal<ByteBuffer>() {
        /* access modifiers changed from: protected */
        public ByteBuffer initialValue() {
            return ByteBuffer.allocate(DecodeBufferHelper.sRecommendedDecodeBufferSize);
        }
    };
    /* access modifiers changed from: private */
    public static int sRecommendedDecodeBufferSize = 16384;

    public boolean release(ByteBuffer byteBuffer) {
        return true;
    }

    public static int getRecommendedDecodeBufferSize() {
        return sRecommendedDecodeBufferSize;
    }

    public static void setRecommendedDecodeBufferSize(int i) {
        sRecommendedDecodeBufferSize = i;
    }

    public ByteBuffer acquire() {
        return sBuffer.get();
    }
}

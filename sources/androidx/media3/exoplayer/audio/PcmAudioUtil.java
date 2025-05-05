package androidx.media3.exoplayer.audio;

import androidx.media3.common.util.Util;
import com.google.common.base.Ascii;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class PcmAudioUtil {
    public static ByteBuffer rampUpVolume(ByteBuffer byteBuffer, int i, int i2, int i3, int i4) {
        ByteBuffer order = ByteBuffer.allocateDirect(byteBuffer.remaining()).order(ByteOrder.nativeOrder());
        int position = byteBuffer.position();
        while (byteBuffer.hasRemaining() && i3 < i4) {
            write32BitIntPcm(order, (int) ((((long) readAs32BitIntPcm(byteBuffer, i)) * ((long) i3)) / ((long) i4)), i);
            if (byteBuffer.position() == position + i2) {
                i3++;
                position = byteBuffer.position();
            }
        }
        order.put(byteBuffer);
        order.flip();
        return order;
    }

    public static int readAs32BitIntPcm(ByteBuffer byteBuffer, int i) {
        if (i == 2) {
            return ((byteBuffer.get() & 255) << Ascii.CAN) | ((byteBuffer.get() & 255) << 16);
        } else if (i == 3) {
            return (byteBuffer.get() & 255) << Ascii.CAN;
        } else {
            if (i == 4) {
                float constrainValue = Util.constrainValue(byteBuffer.getFloat(), -1.0f, 1.0f);
                return constrainValue < 0.0f ? (int) ((-constrainValue) * -2.14748365E9f) : (int) (constrainValue * 2.14748365E9f);
            } else if (i == 21) {
                return ((byteBuffer.get() & 255) << Ascii.CAN) | ((byteBuffer.get() & 255) << 8) | ((byteBuffer.get() & 255) << 16);
            } else if (i == 22) {
                return ((byteBuffer.get() & 255) << Ascii.CAN) | (byteBuffer.get() & 255) | ((byteBuffer.get() & 255) << 8) | ((byteBuffer.get() & 255) << 16);
            } else if (i == 268435456) {
                return ((byteBuffer.get() & 255) << 16) | ((byteBuffer.get() & 255) << Ascii.CAN);
            } else if (i == 1342177280) {
                return ((byteBuffer.get() & 255) << 8) | ((byteBuffer.get() & 255) << Ascii.CAN) | ((byteBuffer.get() & 255) << 16);
            } else if (i == 1610612736) {
                return (byteBuffer.get() & 255) | ((byteBuffer.get() & 255) << Ascii.CAN) | ((byteBuffer.get() & 255) << 16) | ((byteBuffer.get() & 255) << 8);
            } else {
                throw new IllegalStateException();
            }
        }
    }

    public static void write32BitIntPcm(ByteBuffer byteBuffer, int i, int i2) {
        if (i2 == 2) {
            byteBuffer.put((byte) (i >> 16));
            byteBuffer.put((byte) (i >> 24));
        } else if (i2 == 3) {
            byteBuffer.put((byte) (i >> 24));
        } else if (i2 != 4) {
            if (i2 == 21) {
                byteBuffer.put((byte) (i >> 8));
                byteBuffer.put((byte) (i >> 16));
                byteBuffer.put((byte) (i >> 24));
            } else if (i2 == 22) {
                byteBuffer.put((byte) i);
                byteBuffer.put((byte) (i >> 8));
                byteBuffer.put((byte) (i >> 16));
                byteBuffer.put((byte) (i >> 24));
            } else if (i2 == 268435456) {
                byteBuffer.put((byte) (i >> 24));
                byteBuffer.put((byte) (i >> 16));
            } else if (i2 == 1342177280) {
                byteBuffer.put((byte) (i >> 24));
                byteBuffer.put((byte) (i >> 16));
                byteBuffer.put((byte) (i >> 8));
            } else if (i2 == 1610612736) {
                byteBuffer.put((byte) (i >> 24));
                byteBuffer.put((byte) (i >> 16));
                byteBuffer.put((byte) (i >> 8));
                byteBuffer.put((byte) i);
            } else {
                throw new IllegalStateException();
            }
        } else if (i < 0) {
            byteBuffer.putFloat((-((float) i)) / -2.14748365E9f);
        } else {
            byteBuffer.putFloat(((float) i) / 2.14748365E9f);
        }
    }

    private PcmAudioUtil() {
    }
}

package androidx.media3.datasource;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class AesFlushingCipher {
    private final int blockSize;
    private final Cipher cipher;
    private final byte[] flushedBlock;
    private int pendingXorBytes;
    private final byte[] zerosBlock;

    public AesFlushingCipher(int i, byte[] bArr, String str, long j) {
        this(i, bArr, getFNV64Hash(str), j);
    }

    public AesFlushingCipher(int i, byte[] bArr, long j, long j2) {
        try {
            Cipher instance = Cipher.getInstance("AES/CTR/NoPadding");
            this.cipher = instance;
            int blockSize2 = instance.getBlockSize();
            this.blockSize = blockSize2;
            this.zerosBlock = new byte[blockSize2];
            this.flushedBlock = new byte[blockSize2];
            long j3 = j2 / ((long) blockSize2);
            int i2 = (int) (j2 % ((long) blockSize2));
            instance.init(i, new SecretKeySpec(bArr, Util.splitAtFirst(instance.getAlgorithm(), "/")[0]), new IvParameterSpec(getInitializationVector(j, j3)));
            if (i2 != 0) {
                updateInPlace(new byte[i2], 0, i2);
            }
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateInPlace(byte[] bArr, int i, int i2) {
        update(bArr, i, i2, bArr, i);
    }

    public void update(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        int i4 = i;
        do {
            int i5 = this.pendingXorBytes;
            if (i5 > 0) {
                bArr2[i3] = (byte) (bArr[i4] ^ this.flushedBlock[this.blockSize - i5]);
                i3++;
                i4++;
                this.pendingXorBytes = i5 - 1;
                i2--;
            } else {
                int nonFlushingUpdate = nonFlushingUpdate(bArr, i4, i2, bArr2, i3);
                if (i2 != nonFlushingUpdate) {
                    int i6 = i2 - nonFlushingUpdate;
                    int i7 = 0;
                    boolean z = true;
                    Assertions.checkState(i6 < this.blockSize);
                    int i8 = i3 + nonFlushingUpdate;
                    int i9 = this.blockSize - i6;
                    this.pendingXorBytes = i9;
                    if (nonFlushingUpdate(this.zerosBlock, 0, i9, this.flushedBlock, 0) != this.blockSize) {
                        z = false;
                    }
                    Assertions.checkState(z);
                    while (i7 < i6) {
                        bArr2[i8] = this.flushedBlock[i7];
                        i7++;
                        i8++;
                    }
                    return;
                }
                return;
            }
        } while (i2 != 0);
    }

    private int nonFlushingUpdate(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        try {
            return this.cipher.update(bArr, i, i2, bArr2, i3);
        } catch (ShortBufferException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] getInitializationVector(long j, long j2) {
        return ByteBuffer.allocate(16).putLong(j).putLong(j2).array();
    }

    private static long getFNV64Hash(String str) {
        long j = 0;
        if (str == null) {
            return 0;
        }
        for (int i = 0; i < str.length(); i++) {
            long charAt = j ^ ((long) str.charAt(i));
            j = charAt + (charAt << 1) + (charAt << 4) + (charAt << 5) + (charAt << 7) + (charAt << 8) + (charAt << 40);
        }
        return j;
    }
}

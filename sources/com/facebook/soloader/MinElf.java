package com.facebook.soloader;

import androidx.media3.muxer.MuxerUtil;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.ClosedByInterruptException;
import kotlin.UShort;
import okhttp3.internal.ws.WebSocketProtocol;

public final class MinElf {
    public static final int DT_NEEDED = 1;
    public static final int DT_NULL = 0;
    public static final int DT_STRTAB = 5;
    public static final int ELF_MAGIC = 1179403647;
    public static final int PN_XNUM = 65535;
    public static final int PT_DYNAMIC = 2;
    public static final int PT_LOAD = 1;
    private static final String TAG = "MinElf";

    public interface ISA {
        public static final String AARCH64 = "arm64-v8a";
        public static final String ARM = "armeabi-v7a";
        public static final String X86 = "x86";
        public static final String X86_64 = "x86_64";
    }

    public static String[] extract_DT_NEEDED(File file) throws IOException {
        ElfFileChannel elfFileChannel = new ElfFileChannel(file);
        try {
            String[] extract_DT_NEEDED = extract_DT_NEEDED((ElfByteChannel) elfFileChannel);
            elfFileChannel.close();
            return extract_DT_NEEDED;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private static String[] extract_DT_NEEDED_with_retries(ElfFileChannel elfFileChannel) throws IOException {
        int i = 0;
        while (true) {
            try {
                return extract_DT_NEEDED_no_retries(elfFileChannel);
            } catch (ClosedByInterruptException e) {
                i++;
                if (i <= 4) {
                    Thread.interrupted();
                    LogUtil.e(TAG, "retrying extract_DT_NEEDED due to ClosedByInterruptException", e);
                    elfFileChannel.openChannel();
                } else {
                    throw e;
                }
            }
        }
    }

    public static String[] extract_DT_NEEDED(ElfByteChannel elfByteChannel) throws IOException {
        if (elfByteChannel instanceof ElfFileChannel) {
            return extract_DT_NEEDED_with_retries((ElfFileChannel) elfByteChannel);
        }
        return extract_DT_NEEDED_no_retries(elfByteChannel);
    }

    private static String[] extract_DT_NEEDED_no_retries(ElfByteChannel elfByteChannel) throws IOException {
        long j;
        long j2;
        String str;
        long j3;
        String str2;
        long j4;
        long j5;
        long j6;
        long j7;
        long j8;
        long j9;
        long j10;
        ElfByteChannel elfByteChannel2 = elfByteChannel;
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        long j11 = getu32(elfByteChannel2, allocate, 0);
        if (j11 == 1179403647) {
            boolean z = true;
            if (getu8(elfByteChannel2, allocate, 4) != 1) {
                z = false;
            }
            if (getu8(elfByteChannel2, allocate, 5) == 2) {
                allocate.order(ByteOrder.BIG_ENDIAN);
            }
            long r15 = z ? getu32(elfByteChannel2, allocate, 28) : get64(elfByteChannel2, allocate, 32);
            long j12 = z ? (long) getu16(elfByteChannel2, allocate, 44) : (long) getu16(elfByteChannel2, allocate, 56);
            int i = getu16(elfByteChannel2, allocate, z ? 42 : 54);
            if (j12 == WebSocketProtocol.PAYLOAD_SHORT_MAX) {
                long r3 = z ? getu32(elfByteChannel2, allocate, 32) : get64(elfByteChannel2, allocate, 40);
                if (z) {
                    j10 = getu32(elfByteChannel2, allocate, r3 + 28);
                } else {
                    j10 = getu32(elfByteChannel2, allocate, r3 + 44);
                }
                j12 = j10;
            }
            long j13 = r15;
            long j14 = 0;
            while (true) {
                if (j14 >= j12) {
                    j = 0;
                    break;
                }
                if (z) {
                    j9 = getu32(elfByteChannel2, allocate, j13 + 0);
                } else {
                    j9 = getu32(elfByteChannel2, allocate, j13 + 0);
                }
                if (j9 != 2) {
                    j13 += (long) i;
                    j14++;
                } else if (z) {
                    j = getu32(elfByteChannel2, allocate, j13 + 4);
                } else {
                    j = get64(elfByteChannel2, allocate, j13 + 8);
                }
            }
            long j15 = 0;
            if (j != 0) {
                long j16 = j;
                long j17 = 0;
                int i2 = 0;
                while (true) {
                    boolean z2 = z;
                    long r7 = z ? getu32(elfByteChannel2, allocate, j16 + j15) : get64(elfByteChannel2, allocate, j16 + j15);
                    long j18 = j;
                    String str3 = "malformed DT_NEEDED section";
                    if (r7 != 1) {
                        j2 = r7;
                        if (r7 == 5) {
                            if (z2) {
                                j8 = getu32(elfByteChannel2, allocate, j16 + 4);
                            } else {
                                j8 = get64(elfByteChannel2, allocate, j16 + 8);
                            }
                            j17 = j8;
                        }
                    } else if (i2 != Integer.MAX_VALUE) {
                        i2++;
                        j2 = r7;
                    } else {
                        throw new ElfError(str3);
                    }
                    long j19 = 16;
                    j16 += z2 ? 8 : 16;
                    j15 = 0;
                    if (j2 != 0) {
                        z = z2;
                        j = j18;
                    } else if (j17 != 0) {
                        int i3 = 0;
                        while (true) {
                            if (((long) i3) >= j12) {
                                str = str3;
                                j3 = 0;
                                break;
                            }
                            if (z2) {
                                j4 = getu32(elfByteChannel2, allocate, r15 + j15);
                            } else {
                                j4 = getu32(elfByteChannel2, allocate, r15 + j15);
                            }
                            if (j4 == 1) {
                                if (z2) {
                                    j5 = getu32(elfByteChannel2, allocate, r15 + 8);
                                } else {
                                    j5 = get64(elfByteChannel2, allocate, r15 + j19);
                                }
                                if (z2) {
                                    str = str3;
                                    j6 = getu32(elfByteChannel2, allocate, r15 + 20);
                                } else {
                                    str = str3;
                                    j6 = get64(elfByteChannel2, allocate, r15 + 40);
                                }
                                if (j5 <= j17 && j17 < j6 + j5) {
                                    if (z2) {
                                        j7 = getu32(elfByteChannel2, allocate, r15 + 4);
                                    } else {
                                        j7 = get64(elfByteChannel2, allocate, r15 + 8);
                                    }
                                    j3 = j7 + (j17 - j5);
                                }
                            } else {
                                str = str3;
                            }
                            r15 += (long) i;
                            i3++;
                            str3 = str;
                            j19 = 16;
                            j15 = 0;
                        }
                        long j20 = 0;
                        if (j3 != 0) {
                            String[] strArr = new String[i2];
                            long j21 = j18;
                            int i4 = 0;
                            while (true) {
                                long j22 = j21 + j20;
                                long r11 = z2 ? getu32(elfByteChannel2, allocate, j22) : get64(elfByteChannel2, allocate, j22);
                                if (r11 == 1) {
                                    strArr[i4] = getSz(elfByteChannel2, allocate, (z2 ? getu32(elfByteChannel2, allocate, j21 + 4) : get64(elfByteChannel2, allocate, j21 + 8)) + j3);
                                    if (i4 != Integer.MAX_VALUE) {
                                        i4++;
                                        str2 = str;
                                    } else {
                                        throw new ElfError(str);
                                    }
                                } else {
                                    str2 = str;
                                }
                                j21 += z2 ? 8 : 16;
                                if (r11 != 0) {
                                    str = str2;
                                    j20 = 0;
                                } else if (i4 == i2) {
                                    return strArr;
                                } else {
                                    throw new ElfError(str2);
                                }
                            }
                        } else {
                            throw new ElfError("did not find file offset of DT_STRTAB table");
                        }
                    } else {
                        throw new ElfError("Dynamic section string-table not found");
                    }
                }
            } else {
                throw new ElfError("ELF file does not contain dynamic linking information");
            }
        } else {
            throw new ElfError("file is not ELF: magic is 0x" + Long.toHexString(j11) + ", it should be " + Long.toHexString(1179403647));
        }
    }

    private static String getSz(ElfByteChannel elfByteChannel, ByteBuffer byteBuffer, long j) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            long j2 = 1 + j;
            short u8Var = getu8(elfByteChannel, byteBuffer, j);
            if (u8Var == 0) {
                return sb.toString();
            }
            sb.append((char) u8Var);
            j = j2;
        }
    }

    private static void read(ElfByteChannel elfByteChannel, ByteBuffer byteBuffer, int i, long j) throws IOException {
        int read;
        byteBuffer.position(0);
        byteBuffer.limit(i);
        while (byteBuffer.remaining() > 0 && (read = elfByteChannel.read(byteBuffer, j)) != -1) {
            j += (long) read;
        }
        if (byteBuffer.remaining() <= 0) {
            byteBuffer.position(0);
            return;
        }
        throw new ElfError("ELF file truncated");
    }

    private static long get64(ElfByteChannel elfByteChannel, ByteBuffer byteBuffer, long j) throws IOException {
        read(elfByteChannel, byteBuffer, 8, j);
        return byteBuffer.getLong();
    }

    private static long getu32(ElfByteChannel elfByteChannel, ByteBuffer byteBuffer, long j) throws IOException {
        read(elfByteChannel, byteBuffer, 4, j);
        return ((long) byteBuffer.getInt()) & MuxerUtil.UNSIGNED_INT_MAX_VALUE;
    }

    private static int getu16(ElfByteChannel elfByteChannel, ByteBuffer byteBuffer, long j) throws IOException {
        read(elfByteChannel, byteBuffer, 2, j);
        return byteBuffer.getShort() & UShort.MAX_VALUE;
    }

    private static short getu8(ElfByteChannel elfByteChannel, ByteBuffer byteBuffer, long j) throws IOException {
        read(elfByteChannel, byteBuffer, 1, j);
        return (short) (byteBuffer.get() & 255);
    }

    protected static class ElfError extends UnsatisfiedLinkError {
        ElfError(String str) {
            super(str);
        }
    }
}

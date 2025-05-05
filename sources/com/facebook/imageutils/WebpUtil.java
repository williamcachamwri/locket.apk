package com.facebook.imageutils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.UShort;
import kotlin.collections.ArraysKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H\u0002J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\nH\u0002J\u0010\u0010\u0012\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u001e\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0018\u00010\u00142\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J\u001e\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0018\u00010\u00142\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u001e\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0018\u00010\u00142\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u001c\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\u00142\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0010\u0010\u0018\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\f\u0010\u0019\u001a\u00020\r*\u00020\u000fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/facebook/imageutils/WebpUtil;", "", "()V", "VP8L_HEADER", "", "VP8X_HEADER", "VP8_HEADER", "compare", "", "what", "", "with", "get2BytesAsInt", "", "stream", "Ljava/io/InputStream;", "getHeader", "header", "getInt", "getSize", "Lkotlin/Pair;", "getVP8Dimension", "getVP8LDimension", "getVP8XDimension", "read3Bytes", "getNextByteAsInt", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: WebpUtil.kt */
public final class WebpUtil {
    public static final WebpUtil INSTANCE = new WebpUtil();
    private static final String VP8L_HEADER = "VP8L";
    private static final String VP8X_HEADER = "VP8X";
    private static final String VP8_HEADER = "VP8 ";

    private WebpUtil() {
    }

    @JvmStatic
    public static final Pair<Integer, Integer> getSize(InputStream inputStream) {
        Intrinsics.checkNotNullParameter(inputStream, "stream");
        byte[] bArr = new byte[4];
        try {
            inputStream.read(bArr);
            WebpUtil webpUtil = INSTANCE;
            if (!webpUtil.compare(bArr, "RIFF")) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
            webpUtil.getInt(inputStream);
            inputStream.read(bArr);
            if (!webpUtil.compare(bArr, "WEBP")) {
                try {
                    inputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                return null;
            }
            inputStream.read(bArr);
            String header = webpUtil.getHeader(bArr);
            int hashCode = header.hashCode();
            if (hashCode != 2640674) {
                if (hashCode != 2640718) {
                    if (hashCode == 2640730) {
                        if (header.equals(VP8X_HEADER)) {
                            Pair<Integer, Integer> vP8XDimension = webpUtil.getVP8XDimension(inputStream);
                            try {
                                inputStream.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                            return vP8XDimension;
                        }
                    }
                } else if (header.equals(VP8L_HEADER)) {
                    Pair<Integer, Integer> vP8LDimension = webpUtil.getVP8LDimension(inputStream);
                    try {
                        inputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                    return vP8LDimension;
                }
            } else if (header.equals(VP8_HEADER)) {
                Pair<Integer, Integer> vP8Dimension = webpUtil.getVP8Dimension(inputStream);
                try {
                    inputStream.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
                return vP8Dimension;
            }
            try {
                inputStream.close();
            } catch (IOException e6) {
                e6.printStackTrace();
            }
            return null;
        } catch (IOException e7) {
            e7.printStackTrace();
            inputStream.close();
        } catch (Throwable th) {
            try {
                inputStream.close();
            } catch (IOException e8) {
                e8.printStackTrace();
            }
            throw th;
        }
    }

    private final Pair<Integer, Integer> getVP8Dimension(InputStream inputStream) throws IOException {
        inputStream.skip(7);
        int nextByteAsInt = getNextByteAsInt(inputStream);
        int nextByteAsInt2 = getNextByteAsInt(inputStream);
        int nextByteAsInt3 = getNextByteAsInt(inputStream);
        if (nextByteAsInt == 157 && nextByteAsInt2 == 1 && nextByteAsInt3 == 42) {
            return new Pair<>(Integer.valueOf(get2BytesAsInt(inputStream)), Integer.valueOf(get2BytesAsInt(inputStream)));
        }
        return null;
    }

    private final Pair<Integer, Integer> getVP8LDimension(InputStream inputStream) throws IOException {
        getInt(inputStream);
        if (getNextByteAsInt(inputStream) != 47) {
            return null;
        }
        int read = inputStream.read() & 255;
        return new Pair<>(Integer.valueOf(((inputStream.read() & 255) | ((read & 63) << 8)) + 1), Integer.valueOf(((((inputStream.read() & 255) & 15) << 10) | ((inputStream.read() & 255) << 2) | ((read & 192) >> 6)) + 1));
    }

    private final Pair<Integer, Integer> getVP8XDimension(InputStream inputStream) throws IOException {
        inputStream.skip(8);
        return new Pair<>(Integer.valueOf(read3Bytes(inputStream) + 1), Integer.valueOf(read3Bytes(inputStream) + 1));
    }

    private final boolean compare(byte[] bArr, String str) {
        boolean z;
        if (bArr.length != str.length()) {
            return false;
        }
        Iterable indices = ArraysKt.getIndices(bArr);
        if (!(indices instanceof Collection) || !((Collection) indices).isEmpty()) {
            Iterator it = indices.iterator();
            while (it.hasNext()) {
                int nextInt = ((IntIterator) it).nextInt();
                if (((byte) str.charAt(nextInt)) != bArr[nextInt]) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
                if (z) {
                    return false;
                }
            }
        }
        return true;
    }

    private final String getHeader(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            sb.append((char) (UShort.m2725constructorimpl((short) b) & UShort.MAX_VALUE));
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "str.toString()");
        return sb2;
    }

    private final int getInt(InputStream inputStream) throws IOException {
        int nextByteAsInt = getNextByteAsInt(inputStream);
        int nextByteAsInt2 = getNextByteAsInt(inputStream);
        return (getNextByteAsInt(inputStream) << 24) | (getNextByteAsInt(inputStream) << 16) | (nextByteAsInt2 << 8) | nextByteAsInt;
    }

    @JvmStatic
    public static final int get2BytesAsInt(InputStream inputStream) throws IOException {
        Intrinsics.checkNotNullParameter(inputStream, "stream");
        WebpUtil webpUtil = INSTANCE;
        return (webpUtil.getNextByteAsInt(inputStream) << 8) | webpUtil.getNextByteAsInt(inputStream);
    }

    private final int read3Bytes(InputStream inputStream) throws IOException {
        return (getNextByteAsInt(inputStream) << 16) | (getNextByteAsInt(inputStream) << 8) | getNextByteAsInt(inputStream);
    }

    private final int getNextByteAsInt(InputStream inputStream) throws IOException {
        return inputStream.read() & 255;
    }
}

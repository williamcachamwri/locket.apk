package kotlinx.serialization.json.internal;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J \u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0010H\u0002J\b\u0010\u0015\u001a\u00020\u0010H\u0002J\b\u0010\u0016\u001a\u00020\u0010H\u0002J\u001e\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0010J\u0006\u0010\u0018\u001a\u00020\u0019R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lkotlinx/serialization/json/internal/CharsetReader;", "", "inputStream", "Ljava/io/InputStream;", "charset", "Ljava/nio/charset/Charset;", "(Ljava/io/InputStream;Ljava/nio/charset/Charset;)V", "byteBuffer", "Ljava/nio/ByteBuffer;", "decoder", "Ljava/nio/charset/CharsetDecoder;", "hasLeftoverPotentiallySurrogateChar", "", "leftoverChar", "", "doRead", "", "array", "", "offset", "length", "fillByteBuffer", "oneShotReadSlowPath", "read", "release", "", "kotlinx-serialization-json"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CharsetReader.kt */
public final class CharsetReader {
    private final ByteBuffer byteBuffer;
    private final Charset charset;
    private final CharsetDecoder decoder;
    private boolean hasLeftoverPotentiallySurrogateChar;
    private final InputStream inputStream;
    private char leftoverChar;

    public CharsetReader(InputStream inputStream2, Charset charset2) {
        Intrinsics.checkNotNullParameter(inputStream2, "inputStream");
        Intrinsics.checkNotNullParameter(charset2, "charset");
        this.inputStream = inputStream2;
        this.charset = charset2;
        CharsetDecoder onUnmappableCharacter = charset2.newDecoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
        Intrinsics.checkNotNullExpressionValue(onUnmappableCharacter, "charset.newDecoder()\n   …odingErrorAction.REPLACE)");
        this.decoder = onUnmappableCharacter;
        ByteBuffer wrap = ByteBuffer.wrap(ByteArrayPool8k.INSTANCE.take());
        Intrinsics.checkNotNullExpressionValue(wrap, "wrap(ByteArrayPool8k.take())");
        this.byteBuffer = wrap;
        Intrinsics.checkNotNull(wrap, "null cannot be cast to non-null type java.nio.Buffer");
        wrap.flip();
    }

    public final int read(char[] cArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(cArr, "array");
        int i3 = 0;
        if (i2 == 0) {
            return 0;
        }
        if ((i >= 0 && i < cArr.length) && i2 >= 0 && i + i2 <= cArr.length) {
            if (this.hasLeftoverPotentiallySurrogateChar) {
                cArr[i] = this.leftoverChar;
                i++;
                i2--;
                this.hasLeftoverPotentiallySurrogateChar = false;
                if (i2 == 0) {
                    return 1;
                }
                i3 = 1;
            }
            if (i2 != 1) {
                return doRead(cArr, i, i2) + i3;
            }
            int oneShotReadSlowPath = oneShotReadSlowPath();
            if (oneShotReadSlowPath != -1) {
                cArr[i] = (char) oneShotReadSlowPath;
                return i3 + 1;
            } else if (i3 == 0) {
                return -1;
            } else {
                return i3;
            }
        } else {
            throw new IllegalArgumentException(("Unexpected arguments: " + i + ", " + i2 + ", " + cArr.length).toString());
        }
    }

    private final int doRead(char[] cArr, int i, int i2) {
        CharBuffer wrap = CharBuffer.wrap(cArr, i, i2);
        if (wrap.position() != 0) {
            wrap = wrap.slice();
        }
        boolean z = false;
        while (true) {
            CoderResult decode = this.decoder.decode(this.byteBuffer, wrap, z);
            if (decode.isUnderflow()) {
                if (!z && wrap.hasRemaining()) {
                    if (fillByteBuffer() < 0) {
                        if (wrap.position() == 0 && !this.byteBuffer.hasRemaining()) {
                            z = true;
                            break;
                        }
                        this.decoder.reset();
                        z = true;
                    } else {
                        continue;
                    }
                } else {
                    break;
                }
            } else if (decode.isOverflow()) {
                wrap.position();
                break;
            } else {
                decode.throwException();
            }
        }
        if (z) {
            this.decoder.reset();
        }
        if (wrap.position() == 0) {
            return -1;
        }
        return wrap.position();
    }

    private final int fillByteBuffer() {
        this.byteBuffer.compact();
        try {
            int limit = this.byteBuffer.limit();
            int position = this.byteBuffer.position();
            int read = this.inputStream.read(this.byteBuffer.array(), this.byteBuffer.arrayOffset() + position, position <= limit ? limit - position : 0);
            if (read < 0) {
                return read;
            }
            ByteBuffer byteBuffer2 = (ByteBuffer) this.byteBuffer.position(position + read);
            ByteBuffer byteBuffer3 = this.byteBuffer;
            Intrinsics.checkNotNull(byteBuffer3, "null cannot be cast to non-null type java.nio.Buffer");
            byteBuffer3.flip();
            return this.byteBuffer.remaining();
        } finally {
            ByteBuffer byteBuffer4 = this.byteBuffer;
            Intrinsics.checkNotNull(byteBuffer4, "null cannot be cast to non-null type java.nio.Buffer");
            byteBuffer4.flip();
        }
    }

    private final int oneShotReadSlowPath() {
        if (this.hasLeftoverPotentiallySurrogateChar) {
            this.hasLeftoverPotentiallySurrogateChar = false;
            return this.leftoverChar;
        }
        char[] cArr = new char[2];
        int read = read(cArr, 0, 2);
        if (read == -1) {
            return -1;
        }
        if (read == 1) {
            return cArr[0];
        }
        if (read == 2) {
            this.leftoverChar = cArr[1];
            this.hasLeftoverPotentiallySurrogateChar = true;
            return cArr[0];
        }
        throw new IllegalStateException(("Unreachable state: " + read).toString());
    }

    public final void release() {
        ByteArrayPool8k byteArrayPool8k = ByteArrayPool8k.INSTANCE;
        byte[] array = this.byteBuffer.array();
        Intrinsics.checkNotNullExpressionValue(array, "byteBuffer.array()");
        byteArrayPool8k.release(array);
    }
}

package kotlin.io.encoding;

import com.jimmydaddy.imagemarker.base.Constants;
import java.io.IOException;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\r\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u000fH\u0016J \u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\tH\u0002J\b\u0010\u0015\u001a\u00020\u000fH\u0002J \u0010\u0016\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\tH\u0002J\b\u0010\u0017\u001a\u00020\u000fH\u0016J \u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\tH\u0016J\u0010\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\tH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lkotlin/io/encoding/EncodeOutputStream;", "Ljava/io/OutputStream;", "output", "base64", "Lkotlin/io/encoding/Base64;", "(Ljava/io/OutputStream;Lkotlin/io/encoding/Base64;)V", "byteBuffer", "", "byteBufferLength", "", "isClosed", "", "lineLength", "symbolBuffer", "checkOpen", "", "close", "copyIntoByteBuffer", "source", "startIndex", "endIndex", "encodeByteBufferIntoOutput", "encodeIntoOutput", "flush", "write", "offset", "length", "b", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Base64IOStream.kt */
final class EncodeOutputStream extends OutputStream {
    private final Base64 base64;
    private final byte[] byteBuffer;
    private int byteBufferLength;
    private boolean isClosed;
    private int lineLength;
    private final OutputStream output;
    private final byte[] symbolBuffer;

    public EncodeOutputStream(OutputStream outputStream, Base64 base642) {
        Intrinsics.checkNotNullParameter(outputStream, "output");
        Intrinsics.checkNotNullParameter(base642, Constants.BASE64);
        this.output = outputStream;
        this.base64 = base642;
        this.lineLength = base642.isMimeScheme$kotlin_stdlib() ? 76 : -1;
        this.symbolBuffer = new byte[1024];
        this.byteBuffer = new byte[3];
    }

    public void write(int i) {
        checkOpen();
        byte[] bArr = this.byteBuffer;
        int i2 = this.byteBufferLength;
        int i3 = i2 + 1;
        this.byteBufferLength = i3;
        bArr[i2] = (byte) i;
        if (i3 == 3) {
            encodeByteBufferIntoOutput();
        }
    }

    public void write(byte[] bArr, int i, int i2) {
        int i3;
        Intrinsics.checkNotNullParameter(bArr, "source");
        checkOpen();
        if (i < 0 || i2 < 0 || (i3 = i + i2) > bArr.length) {
            throw new IndexOutOfBoundsException("offset: " + i + ", length: " + i2 + ", source size: " + bArr.length);
        } else if (i2 != 0) {
            int i4 = this.byteBufferLength;
            if (i4 < 3) {
                if (i4 != 0) {
                    i += copyIntoByteBuffer(bArr, i, i3);
                    if (this.byteBufferLength != 0) {
                        return;
                    }
                }
                while (i + 3 <= i3) {
                    int min = Math.min((this.base64.isMimeScheme$kotlin_stdlib() ? this.lineLength : this.symbolBuffer.length) / 4, (i3 - i) / 3);
                    int i5 = (min * 3) + i;
                    if (encodeIntoOutput(bArr, i, i5) == min * 4) {
                        i = i5;
                    } else {
                        throw new IllegalStateException("Check failed.".toString());
                    }
                }
                ArraysKt.copyInto(bArr, this.byteBuffer, 0, i, i3);
                this.byteBufferLength = i3 - i;
                return;
            }
            throw new IllegalStateException("Check failed.".toString());
        }
    }

    public void flush() {
        checkOpen();
        this.output.flush();
    }

    public void close() {
        if (!this.isClosed) {
            this.isClosed = true;
            if (this.byteBufferLength != 0) {
                encodeByteBufferIntoOutput();
            }
            this.output.close();
        }
    }

    private final int copyIntoByteBuffer(byte[] bArr, int i, int i2) {
        int min = Math.min(3 - this.byteBufferLength, i2 - i);
        ArraysKt.copyInto(bArr, this.byteBuffer, this.byteBufferLength, i, i + min);
        int i3 = this.byteBufferLength + min;
        this.byteBufferLength = i3;
        if (i3 == 3) {
            encodeByteBufferIntoOutput();
        }
        return min;
    }

    private final void encodeByteBufferIntoOutput() {
        if (encodeIntoOutput(this.byteBuffer, 0, this.byteBufferLength) == 4) {
            this.byteBufferLength = 0;
            return;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    private final int encodeIntoOutput(byte[] bArr, int i, int i2) {
        int encodeIntoByteArray = this.base64.encodeIntoByteArray(bArr, this.symbolBuffer, 0, i, i2);
        if (this.lineLength == 0) {
            this.output.write(Base64.Default.getMimeLineSeparatorSymbols$kotlin_stdlib());
            this.lineLength = 76;
            if (!(encodeIntoByteArray <= 76)) {
                throw new IllegalStateException("Check failed.".toString());
            }
        }
        this.output.write(this.symbolBuffer, 0, encodeIntoByteArray);
        this.lineLength -= encodeIntoByteArray;
        return encodeIntoByteArray;
    }

    private final void checkOpen() {
        if (this.isClosed) {
            throw new IOException("The output stream is closed.");
        }
    }
}

package okio;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.common.C;
import androidx.media3.exoplayer.audio.SilenceSkippingAudioProcessor;
import androidx.media3.extractor.ts.PsExtractor;
import androidx.media3.muxer.MuxerUtil;
import com.google.common.base.Ascii;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.Typography;
import okhttp3.internal.connection.RealConnection;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

@Metadata(d1 = {"\u0000ª\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001a\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0017\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0002\u0001B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0000H\u0016J\u0006\u0010\u0011\u001a\u00020\u0012J\b\u0010\u0013\u001a\u00020\u0000H\u0016J\b\u0010\u0014\u001a\u00020\u0012H\u0016J\u0006\u0010\u0015\u001a\u00020\fJ\u0006\u0010\u0016\u001a\u00020\u0000J$\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\f2\b\b\u0002\u0010\u001b\u001a\u00020\fH\u0007J\u0018\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u001a\u001a\u00020\fJ \u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\fJ\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\b\u0010 \u001a\u00020\u0000H\u0016J\b\u0010!\u001a\u00020\u0000H\u0016J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%H\u0002J\b\u0010&\u001a\u00020#H\u0016J\b\u0010'\u001a\u00020\u0012H\u0016J\u0016\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\fH\u0002¢\u0006\u0002\b+J\u0015\u0010+\u001a\u00020)2\u0006\u0010,\u001a\u00020\fH\u0007¢\u0006\u0002\b-J\b\u0010.\u001a\u00020/H\u0016J\u0018\u00100\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u00101\u001a\u00020\u001dH\u0002J\u000e\u00102\u001a\u00020\u001d2\u0006\u00101\u001a\u00020\u001dJ\u000e\u00103\u001a\u00020\u001d2\u0006\u00101\u001a\u00020\u001dJ\u000e\u00104\u001a\u00020\u001d2\u0006\u00101\u001a\u00020\u001dJ\u0010\u00105\u001a\u00020\f2\u0006\u00106\u001a\u00020)H\u0016J\u0018\u00105\u001a\u00020\f2\u0006\u00106\u001a\u00020)2\u0006\u00107\u001a\u00020\fH\u0016J \u00105\u001a\u00020\f2\u0006\u00106\u001a\u00020)2\u0006\u00107\u001a\u00020\f2\u0006\u00108\u001a\u00020\fH\u0016J\u0010\u00105\u001a\u00020\f2\u0006\u00109\u001a\u00020\u001dH\u0016J\u0018\u00105\u001a\u00020\f2\u0006\u00109\u001a\u00020\u001d2\u0006\u00107\u001a\u00020\fH\u0016J\u0010\u0010:\u001a\u00020\f2\u0006\u0010;\u001a\u00020\u001dH\u0016J\u0018\u0010:\u001a\u00020\f2\u0006\u0010;\u001a\u00020\u001d2\u0006\u00107\u001a\u00020\fH\u0016J\b\u0010<\u001a\u00020=H\u0016J\b\u0010>\u001a\u00020#H\u0016J\u0006\u0010?\u001a\u00020\u001dJ\b\u0010@\u001a\u00020\u0019H\u0016J\b\u0010A\u001a\u00020\u0001H\u0016J\u0018\u0010B\u001a\u00020#2\u0006\u0010\u001a\u001a\u00020\f2\u0006\u00109\u001a\u00020\u001dH\u0016J(\u0010B\u001a\u00020#2\u0006\u0010\u001a\u001a\u00020\f2\u0006\u00109\u001a\u00020\u001d2\u0006\u0010C\u001a\u00020/2\u0006\u0010\u001b\u001a\u00020/H\u0016J\u0010\u0010D\u001a\u00020/2\u0006\u0010E\u001a\u00020FH\u0016J\u0010\u0010D\u001a\u00020/2\u0006\u0010E\u001a\u00020GH\u0016J \u0010D\u001a\u00020/2\u0006\u0010E\u001a\u00020G2\u0006\u0010\u001a\u001a\u00020/2\u0006\u0010\u001b\u001a\u00020/H\u0016J\u0018\u0010D\u001a\u00020\f2\u0006\u0010E\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\fH\u0016J\u0010\u0010H\u001a\u00020\f2\u0006\u0010E\u001a\u00020IH\u0016J\u0012\u0010J\u001a\u00020K2\b\b\u0002\u0010L\u001a\u00020KH\u0007J\b\u0010M\u001a\u00020)H\u0016J\b\u0010N\u001a\u00020GH\u0016J\u0010\u0010N\u001a\u00020G2\u0006\u0010\u001b\u001a\u00020\fH\u0016J\b\u0010O\u001a\u00020\u001dH\u0016J\u0010\u0010O\u001a\u00020\u001d2\u0006\u0010\u001b\u001a\u00020\fH\u0016J\b\u0010P\u001a\u00020\fH\u0016J\u000e\u0010Q\u001a\u00020\u00002\u0006\u0010R\u001a\u00020=J\u0016\u0010Q\u001a\u00020\u00002\u0006\u0010R\u001a\u00020=2\u0006\u0010\u001b\u001a\u00020\fJ \u0010Q\u001a\u00020\u00122\u0006\u0010R\u001a\u00020=2\u0006\u0010\u001b\u001a\u00020\f2\u0006\u0010S\u001a\u00020#H\u0002J\u0010\u0010T\u001a\u00020\u00122\u0006\u0010E\u001a\u00020GH\u0016J\u0018\u0010T\u001a\u00020\u00122\u0006\u0010E\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\fH\u0016J\b\u0010U\u001a\u00020\fH\u0016J\b\u0010V\u001a\u00020/H\u0016J\b\u0010W\u001a\u00020/H\u0016J\b\u0010X\u001a\u00020\fH\u0016J\b\u0010Y\u001a\u00020\fH\u0016J\b\u0010Z\u001a\u00020[H\u0016J\b\u0010\\\u001a\u00020[H\u0016J\u0010\u0010]\u001a\u00020\u001f2\u0006\u0010^\u001a\u00020_H\u0016J\u0018\u0010]\u001a\u00020\u001f2\u0006\u0010\u001b\u001a\u00020\f2\u0006\u0010^\u001a\u00020_H\u0016J\u0012\u0010`\u001a\u00020K2\b\b\u0002\u0010L\u001a\u00020KH\u0007J\b\u0010a\u001a\u00020\u001fH\u0016J\u0010\u0010a\u001a\u00020\u001f2\u0006\u0010\u001b\u001a\u00020\fH\u0016J\b\u0010b\u001a\u00020/H\u0016J\n\u0010c\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010d\u001a\u00020\u001fH\u0016J\u0010\u0010d\u001a\u00020\u001f2\u0006\u0010e\u001a\u00020\fH\u0016J\u0010\u0010f\u001a\u00020#2\u0006\u0010\u001b\u001a\u00020\fH\u0016J\u0010\u0010g\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\fH\u0016J\u0010\u0010h\u001a\u00020/2\u0006\u0010i\u001a\u00020jH\u0016J\u0006\u0010k\u001a\u00020\u001dJ\u0006\u0010l\u001a\u00020\u001dJ\u0006\u0010m\u001a\u00020\u001dJ\r\u0010\r\u001a\u00020\fH\u0007¢\u0006\u0002\bnJ\u0010\u0010o\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\fH\u0016J\u0006\u0010p\u001a\u00020\u001dJ\u000e\u0010p\u001a\u00020\u001d2\u0006\u0010\u001b\u001a\u00020/J\b\u0010q\u001a\u00020rH\u0016J\b\u0010s\u001a\u00020\u001fH\u0016J\u0015\u0010t\u001a\u00020\n2\u0006\u0010u\u001a\u00020/H\u0000¢\u0006\u0002\bvJ\u0010\u0010w\u001a\u00020/2\u0006\u0010x\u001a\u00020FH\u0016J\u0010\u0010w\u001a\u00020\u00002\u0006\u0010x\u001a\u00020GH\u0016J \u0010w\u001a\u00020\u00002\u0006\u0010x\u001a\u00020G2\u0006\u0010\u001a\u001a\u00020/2\u0006\u0010\u001b\u001a\u00020/H\u0016J\u0018\u0010w\u001a\u00020\u00122\u0006\u0010x\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\fH\u0016J\u0010\u0010w\u001a\u00020\u00002\u0006\u0010y\u001a\u00020\u001dH\u0016J \u0010w\u001a\u00020\u00002\u0006\u0010y\u001a\u00020\u001d2\u0006\u0010\u001a\u001a\u00020/2\u0006\u0010\u001b\u001a\u00020/H\u0016J\u0018\u0010w\u001a\u00020\u00002\u0006\u0010x\u001a\u00020z2\u0006\u0010\u001b\u001a\u00020\fH\u0016J\u0010\u0010{\u001a\u00020\f2\u0006\u0010x\u001a\u00020zH\u0016J\u0010\u0010|\u001a\u00020\u00002\u0006\u00106\u001a\u00020/H\u0016J\u0010\u0010}\u001a\u00020\u00002\u0006\u0010~\u001a\u00020\fH\u0016J\u0010\u0010\u001a\u00020\u00002\u0006\u0010~\u001a\u00020\fH\u0016J\u0012\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020/H\u0016J\u0012\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020/H\u0016J\u0011\u0010\u0001\u001a\u00020\u00002\u0006\u0010~\u001a\u00020\fH\u0016J\u0011\u0010\u0001\u001a\u00020\u00002\u0006\u0010~\u001a\u00020\fH\u0016J\u0012\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020/H\u0016J\u0012\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020/H\u0016J\u001a\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020\u001f2\u0006\u0010^\u001a\u00020_H\u0016J,\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020\u001f2\u0007\u0010\u0001\u001a\u00020/2\u0007\u0010\u0001\u001a\u00020/2\u0006\u0010^\u001a\u00020_H\u0016J\u001b\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001b\u001a\u00020\fH\u0007J\u0012\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020\u001fH\u0016J$\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020\u001f2\u0007\u0010\u0001\u001a\u00020/2\u0007\u0010\u0001\u001a\u00020/H\u0016J\u0012\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u0001\u001a\u00020/H\u0016R\u0014\u0010\u0006\u001a\u00020\u00008VX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u0004\u0018\u00010\n8\u0000@\u0000X\u000e¢\u0006\u0002\n\u0000R&\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f8G@@X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0001"}, d2 = {"Lokio/Buffer;", "Lokio/BufferedSource;", "Lokio/BufferedSink;", "", "Ljava/nio/channels/ByteChannel;", "()V", "buffer", "getBuffer", "()Lokio/Buffer;", "head", "Lokio/Segment;", "<set-?>", "", "size", "()J", "setSize$okio", "(J)V", "clear", "", "clone", "close", "completeSegmentByteCount", "copy", "copyTo", "out", "Ljava/io/OutputStream;", "offset", "byteCount", "digest", "Lokio/ByteString;", "algorithm", "", "emit", "emitCompleteSegments", "equals", "", "other", "", "exhausted", "flush", "get", "", "pos", "getByte", "index", "-deprecated_getByte", "hashCode", "", "hmac", "key", "hmacSha1", "hmacSha256", "hmacSha512", "indexOf", "b", "fromIndex", "toIndex", "bytes", "indexOfElement", "targetBytes", "inputStream", "Ljava/io/InputStream;", "isOpen", "md5", "outputStream", "peek", "rangeEquals", "bytesOffset", "read", "sink", "Ljava/nio/ByteBuffer;", "", "readAll", "Lokio/Sink;", "readAndWriteUnsafe", "Lokio/Buffer$UnsafeCursor;", "unsafeCursor", "readByte", "readByteArray", "readByteString", "readDecimalLong", "readFrom", "input", "forever", "readFully", "readHexadecimalUnsignedLong", "readInt", "readIntLe", "readLong", "readLongLe", "readShort", "", "readShortLe", "readString", "charset", "Ljava/nio/charset/Charset;", "readUnsafe", "readUtf8", "readUtf8CodePoint", "readUtf8Line", "readUtf8LineStrict", "limit", "request", "require", "select", "options", "Lokio/Options;", "sha1", "sha256", "sha512", "-deprecated_size", "skip", "snapshot", "timeout", "Lokio/Timeout;", "toString", "writableSegment", "minimumCapacity", "writableSegment$okio", "write", "source", "byteString", "Lokio/Source;", "writeAll", "writeByte", "writeDecimalLong", "v", "writeHexadecimalUnsignedLong", "writeInt", "i", "writeIntLe", "writeLong", "writeLongLe", "writeShort", "s", "writeShortLe", "writeString", "string", "beginIndex", "endIndex", "writeTo", "writeUtf8", "writeUtf8CodePoint", "codePoint", "UnsafeCursor", "okio"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Buffer.kt */
public final class Buffer implements BufferedSource, BufferedSink, Cloneable, ByteChannel {
    public Segment head;
    private long size;

    public Buffer buffer() {
        return this;
    }

    public void close() {
    }

    public final Buffer copyTo(OutputStream outputStream) throws IOException {
        Intrinsics.checkNotNullParameter(outputStream, "out");
        return copyTo$default(this, outputStream, 0, 0, 6, (Object) null);
    }

    public final Buffer copyTo(OutputStream outputStream, long j) throws IOException {
        Intrinsics.checkNotNullParameter(outputStream, "out");
        return copyTo$default(this, outputStream, j, 0, 4, (Object) null);
    }

    public Buffer emit() {
        return this;
    }

    public Buffer emitCompleteSegments() {
        return this;
    }

    public void flush() {
    }

    public Buffer getBuffer() {
        return this;
    }

    public boolean isOpen() {
        return true;
    }

    public final UnsafeCursor readAndWriteUnsafe() {
        return readAndWriteUnsafe$default(this, (UnsafeCursor) null, 1, (Object) null);
    }

    public final UnsafeCursor readUnsafe() {
        return readUnsafe$default(this, (UnsafeCursor) null, 1, (Object) null);
    }

    public final Buffer writeTo(OutputStream outputStream) throws IOException {
        Intrinsics.checkNotNullParameter(outputStream, "out");
        return writeTo$default(this, outputStream, 0, 2, (Object) null);
    }

    public final long size() {
        return this.size;
    }

    public final void setSize$okio(long j) {
        this.size = j;
    }

    public OutputStream outputStream() {
        return new Buffer$outputStream$1(this);
    }

    public boolean exhausted() {
        return this.size == 0;
    }

    public void require(long j) throws EOFException {
        if (this.size < j) {
            throw new EOFException();
        }
    }

    public boolean request(long j) {
        return this.size >= j;
    }

    public BufferedSource peek() {
        return Okio.buffer((Source) new PeekSource(this));
    }

    public InputStream inputStream() {
        return new Buffer$inputStream$1(this);
    }

    public static /* synthetic */ UnsafeCursor readUnsafe$default(Buffer buffer, UnsafeCursor unsafeCursor, int i, Object obj) {
        if ((i & 1) != 0) {
            unsafeCursor = SegmentedByteString.getDEFAULT__new_UnsafeCursor();
        }
        return buffer.readUnsafe(unsafeCursor);
    }

    public static /* synthetic */ UnsafeCursor readAndWriteUnsafe$default(Buffer buffer, UnsafeCursor unsafeCursor, int i, Object obj) {
        if ((i & 1) != 0) {
            unsafeCursor = SegmentedByteString.getDEFAULT__new_UnsafeCursor();
        }
        return buffer.readAndWriteUnsafe(unsafeCursor);
    }

    public static /* synthetic */ Buffer copyTo$default(Buffer buffer, OutputStream outputStream, long j, long j2, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            j = 0;
        }
        long j3 = j;
        if ((i & 4) != 0) {
            j2 = buffer.size - j3;
        }
        return buffer.copyTo(outputStream, j3, j2);
    }

    public final Buffer copyTo(OutputStream outputStream, long j, long j2) throws IOException {
        Intrinsics.checkNotNullParameter(outputStream, "out");
        SegmentedByteString.checkOffsetAndCount(this.size, j, j2);
        if (j2 == 0) {
            return this;
        }
        Segment segment = this.head;
        while (true) {
            Intrinsics.checkNotNull(segment);
            if (j < ((long) (segment.limit - segment.pos))) {
                break;
            }
            j -= (long) (segment.limit - segment.pos);
            segment = segment.next;
        }
        while (j2 > 0) {
            Intrinsics.checkNotNull(segment);
            int i = (int) (((long) segment.pos) + j);
            int min = (int) Math.min((long) (segment.limit - i), j2);
            outputStream.write(segment.data, i, min);
            j2 -= (long) min;
            segment = segment.next;
            j = 0;
        }
        return this;
    }

    public static /* synthetic */ Buffer copyTo$default(Buffer buffer, Buffer buffer2, long j, long j2, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 0;
        }
        return buffer.copyTo(buffer2, j, j2);
    }

    public static /* synthetic */ Buffer copyTo$default(Buffer buffer, Buffer buffer2, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 0;
        }
        return buffer.copyTo(buffer2, j);
    }

    public final Buffer copyTo(Buffer buffer, long j) {
        Intrinsics.checkNotNullParameter(buffer, "out");
        return copyTo(buffer, j, this.size - j);
    }

    public static /* synthetic */ Buffer writeTo$default(Buffer buffer, OutputStream outputStream, long j, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            j = buffer.size;
        }
        return buffer.writeTo(outputStream, j);
    }

    public final Buffer writeTo(OutputStream outputStream, long j) throws IOException {
        Intrinsics.checkNotNullParameter(outputStream, "out");
        SegmentedByteString.checkOffsetAndCount(this.size, 0, j);
        Segment segment = this.head;
        while (j > 0) {
            Intrinsics.checkNotNull(segment);
            int min = (int) Math.min(j, (long) (segment.limit - segment.pos));
            outputStream.write(segment.data, segment.pos, min);
            segment.pos += min;
            long j2 = (long) min;
            this.size -= j2;
            j -= j2;
            if (segment.pos == segment.limit) {
                Segment pop = segment.pop();
                this.head = pop;
                SegmentPool.recycle(segment);
                segment = pop;
            }
        }
        return this;
    }

    public final Buffer readFrom(InputStream inputStream) throws IOException {
        Intrinsics.checkNotNullParameter(inputStream, "input");
        readFrom(inputStream, Long.MAX_VALUE, true);
        return this;
    }

    public final Buffer readFrom(InputStream inputStream, long j) throws IOException {
        Intrinsics.checkNotNullParameter(inputStream, "input");
        if (j >= 0) {
            readFrom(inputStream, j, false);
            return this;
        }
        throw new IllegalArgumentException(("byteCount < 0: " + j).toString());
    }

    private final void readFrom(InputStream inputStream, long j, boolean z) throws IOException {
        while (true) {
            if (j > 0 || z) {
                Segment writableSegment$okio = writableSegment$okio(1);
                int read = inputStream.read(writableSegment$okio.data, writableSegment$okio.limit, (int) Math.min(j, (long) (8192 - writableSegment$okio.limit)));
                if (read == -1) {
                    if (writableSegment$okio.pos == writableSegment$okio.limit) {
                        this.head = writableSegment$okio.pop();
                        SegmentPool.recycle(writableSegment$okio);
                    }
                    if (!z) {
                        throw new EOFException();
                    }
                    return;
                }
                writableSegment$okio.limit += read;
                long j2 = (long) read;
                this.size += j2;
                j -= j2;
            } else {
                return;
            }
        }
    }

    public short readShortLe() throws EOFException {
        return SegmentedByteString.reverseBytes(readShort());
    }

    public int readIntLe() throws EOFException {
        return SegmentedByteString.reverseBytes(readInt());
    }

    public long readLongLe() throws EOFException {
        return SegmentedByteString.reverseBytes(readLong());
    }

    public String readUtf8() {
        return readString(this.size, Charsets.UTF_8);
    }

    public String readUtf8(long j) throws EOFException {
        return readString(j, Charsets.UTF_8);
    }

    public String readString(Charset charset) {
        Intrinsics.checkNotNullParameter(charset, "charset");
        return readString(this.size, charset);
    }

    public String readString(long j, Charset charset) throws EOFException {
        Intrinsics.checkNotNullParameter(charset, "charset");
        int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        if (!(i >= 0 && j <= 2147483647L)) {
            throw new IllegalArgumentException(("byteCount: " + j).toString());
        } else if (this.size < j) {
            throw new EOFException();
        } else if (i == 0) {
            return "";
        } else {
            Segment segment = this.head;
            Intrinsics.checkNotNull(segment);
            if (((long) segment.pos) + j > ((long) segment.limit)) {
                return new String(readByteArray(j), charset);
            }
            int i2 = (int) j;
            String str = new String(segment.data, segment.pos, i2, charset);
            segment.pos += i2;
            this.size -= j;
            if (segment.pos == segment.limit) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            }
            return str;
        }
    }

    public String readUtf8LineStrict() throws EOFException {
        return readUtf8LineStrict(Long.MAX_VALUE);
    }

    public int read(ByteBuffer byteBuffer) throws IOException {
        Intrinsics.checkNotNullParameter(byteBuffer, "sink");
        Segment segment = this.head;
        if (segment == null) {
            return -1;
        }
        int min = Math.min(byteBuffer.remaining(), segment.limit - segment.pos);
        byteBuffer.put(segment.data, segment.pos, min);
        segment.pos += min;
        this.size -= (long) min;
        if (segment.pos == segment.limit) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        }
        return min;
    }

    public Buffer writeUtf8(String str) {
        Intrinsics.checkNotNullParameter(str, TypedValues.Custom.S_STRING);
        return writeUtf8(str, 0, str.length());
    }

    public Buffer writeString(String str, Charset charset) {
        Intrinsics.checkNotNullParameter(str, TypedValues.Custom.S_STRING);
        Intrinsics.checkNotNullParameter(charset, "charset");
        return writeString(str, 0, str.length(), charset);
    }

    public Buffer writeString(String str, int i, int i2, Charset charset) {
        Intrinsics.checkNotNullParameter(str, TypedValues.Custom.S_STRING);
        Intrinsics.checkNotNullParameter(charset, "charset");
        boolean z = true;
        if (i >= 0) {
            if (i2 >= i) {
                if (i2 > str.length()) {
                    z = false;
                }
                if (!z) {
                    throw new IllegalArgumentException(("endIndex > string.length: " + i2 + " > " + str.length()).toString());
                } else if (Intrinsics.areEqual((Object) charset, (Object) Charsets.UTF_8)) {
                    return writeUtf8(str, i, i2);
                } else {
                    String substring = str.substring(i, i2);
                    Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                    byte[] bytes = substring.getBytes(charset);
                    Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
                    return write(bytes, 0, bytes.length);
                }
            } else {
                throw new IllegalArgumentException(("endIndex < beginIndex: " + i2 + " < " + i).toString());
            }
        } else {
            throw new IllegalArgumentException(("beginIndex < 0: " + i).toString());
        }
    }

    public int write(ByteBuffer byteBuffer) throws IOException {
        Intrinsics.checkNotNullParameter(byteBuffer, "source");
        int remaining = byteBuffer.remaining();
        int i = remaining;
        while (i > 0) {
            Segment writableSegment$okio = writableSegment$okio(1);
            int min = Math.min(i, 8192 - writableSegment$okio.limit);
            byteBuffer.get(writableSegment$okio.data, writableSegment$okio.limit, min);
            i -= min;
            writableSegment$okio.limit += min;
        }
        this.size += (long) remaining;
        return remaining;
    }

    public Buffer writeShortLe(int i) {
        return writeShort((int) SegmentedByteString.reverseBytes((short) i));
    }

    public Buffer writeIntLe(int i) {
        return writeInt(SegmentedByteString.reverseBytes(i));
    }

    public Buffer writeLongLe(long j) {
        return writeLong(SegmentedByteString.reverseBytes(j));
    }

    public long indexOf(byte b) {
        return indexOf(b, 0, Long.MAX_VALUE);
    }

    public long indexOf(byte b, long j) {
        return indexOf(b, j, Long.MAX_VALUE);
    }

    public long indexOf(ByteString byteString) throws IOException {
        Intrinsics.checkNotNullParameter(byteString, "bytes");
        return indexOf(byteString, 0);
    }

    public long indexOfElement(ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "targetBytes");
        return indexOfElement(byteString, 0);
    }

    public boolean rangeEquals(long j, ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "bytes");
        return rangeEquals(j, byteString, 0, byteString.size());
    }

    public Timeout timeout() {
        return Timeout.NONE;
    }

    public final ByteString md5() {
        return digest(MessageDigestAlgorithms.MD5);
    }

    public final ByteString sha1() {
        return digest(MessageDigestAlgorithms.SHA_1);
    }

    public final ByteString sha256() {
        return digest(MessageDigestAlgorithms.SHA_256);
    }

    public final ByteString sha512() {
        return digest(MessageDigestAlgorithms.SHA_512);
    }

    private final ByteString digest(String str) {
        MessageDigest instance = MessageDigest.getInstance(str);
        Segment segment = this.head;
        if (segment != null) {
            instance.update(segment.data, segment.pos, segment.limit - segment.pos);
            Segment segment2 = segment.next;
            Intrinsics.checkNotNull(segment2);
            while (segment2 != segment) {
                instance.update(segment2.data, segment2.pos, segment2.limit - segment2.pos);
                segment2 = segment2.next;
                Intrinsics.checkNotNull(segment2);
            }
        }
        byte[] digest = instance.digest();
        Intrinsics.checkNotNullExpressionValue(digest, "digest(...)");
        return new ByteString(digest);
    }

    public final ByteString hmacSha1(ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "key");
        return hmac("HmacSHA1", byteString);
    }

    public final ByteString hmacSha256(ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "key");
        return hmac("HmacSHA256", byteString);
    }

    public final ByteString hmacSha512(ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "key");
        return hmac("HmacSHA512", byteString);
    }

    private final ByteString hmac(String str, ByteString byteString) {
        try {
            Mac instance = Mac.getInstance(str);
            instance.init(new SecretKeySpec(byteString.internalArray$okio(), str));
            Segment segment = this.head;
            if (segment != null) {
                instance.update(segment.data, segment.pos, segment.limit - segment.pos);
                Segment segment2 = segment.next;
                Intrinsics.checkNotNull(segment2);
                while (segment2 != segment) {
                    instance.update(segment2.data, segment2.pos, segment2.limit - segment2.pos);
                    segment2 = segment2.next;
                    Intrinsics.checkNotNull(segment2);
                }
            }
            byte[] doFinal = instance.doFinal();
            Intrinsics.checkNotNullExpressionValue(doFinal, "doFinal(...)");
            return new ByteString(doFinal);
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public String toString() {
        return snapshot().toString();
    }

    public Buffer clone() {
        return copy();
    }

    public final UnsafeCursor readUnsafe(UnsafeCursor unsafeCursor) {
        Intrinsics.checkNotNullParameter(unsafeCursor, "unsafeCursor");
        return okio.internal.Buffer.commonReadUnsafe(this, unsafeCursor);
    }

    public final UnsafeCursor readAndWriteUnsafe(UnsafeCursor unsafeCursor) {
        Intrinsics.checkNotNullParameter(unsafeCursor, "unsafeCursor");
        return okio.internal.Buffer.commonReadAndWriteUnsafe(this, unsafeCursor);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to operator function", replaceWith = @ReplaceWith(expression = "this[index]", imports = {}))
    /* renamed from: -deprecated_getByte  reason: not valid java name */
    public final byte m2050deprecated_getByte(long j) {
        return getByte(j);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "size", imports = {}))
    /* renamed from: -deprecated_size  reason: not valid java name */
    public final long m2051deprecated_size() {
        return this.size;
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u000e\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\bJ\u0006\u0010\u0018\u001a\u00020\bJ\u000e\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\nJ\u000e\u0010\u001b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u00020\n8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0012\u0010\u0013\u001a\u00020\b8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lokio/Buffer$UnsafeCursor;", "Ljava/io/Closeable;", "()V", "buffer", "Lokio/Buffer;", "data", "", "end", "", "offset", "", "readWrite", "", "segment", "Lokio/Segment;", "getSegment$okio", "()Lokio/Segment;", "setSegment$okio", "(Lokio/Segment;)V", "start", "close", "", "expandBuffer", "minByteCount", "next", "resizeBuffer", "newSize", "seek", "okio"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: Buffer.kt */
    public static final class UnsafeCursor implements Closeable {
        public Buffer buffer;
        public byte[] data;
        public int end = -1;
        public long offset = -1;
        public boolean readWrite;
        private Segment segment;
        public int start = -1;

        public final Segment getSegment$okio() {
            return this.segment;
        }

        public final void setSegment$okio(Segment segment2) {
            this.segment = segment2;
        }

        public final int next() {
            long j = this.offset;
            Buffer buffer2 = this.buffer;
            Intrinsics.checkNotNull(buffer2);
            if (j != buffer2.size()) {
                long j2 = this.offset;
                return seek(j2 == -1 ? 0 : j2 + ((long) (this.end - this.start)));
            }
            throw new IllegalStateException("no more bytes".toString());
        }

        public final int seek(long j) {
            Buffer buffer2 = this.buffer;
            if (buffer2 != null) {
                int i = (j > -1 ? 1 : (j == -1 ? 0 : -1));
                if (i < 0 || j > buffer2.size()) {
                    throw new ArrayIndexOutOfBoundsException("offset=" + j + " > size=" + buffer2.size());
                } else if (i == 0 || j == buffer2.size()) {
                    setSegment$okio((Segment) null);
                    this.offset = j;
                    this.data = null;
                    this.start = -1;
                    this.end = -1;
                    return -1;
                } else {
                    long size = buffer2.size();
                    Segment segment2 = buffer2.head;
                    Segment segment3 = buffer2.head;
                    long j2 = 0;
                    if (getSegment$okio() != null) {
                        long j3 = this.offset;
                        int i2 = this.start;
                        Segment segment$okio = getSegment$okio();
                        Intrinsics.checkNotNull(segment$okio);
                        long j4 = j3 - ((long) (i2 - segment$okio.pos));
                        if (j4 > j) {
                            segment3 = getSegment$okio();
                            size = j4;
                        } else {
                            segment2 = getSegment$okio();
                            j2 = j4;
                        }
                    }
                    if (size - j > j - j2) {
                        while (true) {
                            Intrinsics.checkNotNull(segment2);
                            if (j < ((long) (segment2.limit - segment2.pos)) + j2) {
                                break;
                            }
                            j2 += (long) (segment2.limit - segment2.pos);
                            segment2 = segment2.next;
                        }
                    } else {
                        while (size > j) {
                            Intrinsics.checkNotNull(segment3);
                            segment3 = segment3.prev;
                            Intrinsics.checkNotNull(segment3);
                            size -= (long) (segment3.limit - segment3.pos);
                        }
                        j2 = size;
                        segment2 = segment3;
                    }
                    if (this.readWrite) {
                        Intrinsics.checkNotNull(segment2);
                        if (segment2.shared) {
                            Segment unsharedCopy = segment2.unsharedCopy();
                            if (buffer2.head == segment2) {
                                buffer2.head = unsharedCopy;
                            }
                            segment2 = segment2.push(unsharedCopy);
                            Segment segment4 = segment2.prev;
                            Intrinsics.checkNotNull(segment4);
                            segment4.pop();
                        }
                    }
                    setSegment$okio(segment2);
                    this.offset = j;
                    Intrinsics.checkNotNull(segment2);
                    this.data = segment2.data;
                    this.start = segment2.pos + ((int) (j - j2));
                    int i3 = segment2.limit;
                    this.end = i3;
                    return i3 - this.start;
                }
            } else {
                throw new IllegalStateException("not attached to a buffer".toString());
            }
        }

        public final long resizeBuffer(long j) {
            long j2 = j;
            Buffer buffer2 = this.buffer;
            if (buffer2 == null) {
                throw new IllegalStateException("not attached to a buffer".toString());
            } else if (this.readWrite) {
                long size = buffer2.size();
                int i = (j2 > size ? 1 : (j2 == size ? 0 : -1));
                int i2 = 1;
                if (i <= 0) {
                    if (j2 >= 0) {
                        long j3 = size - j2;
                        while (true) {
                            if (j3 <= 0) {
                                break;
                            }
                            Segment segment2 = buffer2.head;
                            Intrinsics.checkNotNull(segment2);
                            Segment segment3 = segment2.prev;
                            Intrinsics.checkNotNull(segment3);
                            long j4 = (long) (segment3.limit - segment3.pos);
                            if (j4 > j3) {
                                segment3.limit -= (int) j3;
                                break;
                            }
                            buffer2.head = segment3.pop();
                            SegmentPool.recycle(segment3);
                            j3 -= j4;
                        }
                        setSegment$okio((Segment) null);
                        this.offset = j2;
                        this.data = null;
                        this.start = -1;
                        this.end = -1;
                    } else {
                        throw new IllegalArgumentException(("newSize < 0: " + j2).toString());
                    }
                } else if (i > 0) {
                    long j5 = j2 - size;
                    boolean z = true;
                    while (j5 > 0) {
                        Segment writableSegment$okio = buffer2.writableSegment$okio(i2);
                        int min = (int) Math.min(j5, (long) (8192 - writableSegment$okio.limit));
                        writableSegment$okio.limit += min;
                        j5 -= (long) min;
                        if (z) {
                            setSegment$okio(writableSegment$okio);
                            this.offset = size;
                            this.data = writableSegment$okio.data;
                            this.start = writableSegment$okio.limit - min;
                            this.end = writableSegment$okio.limit;
                            z = false;
                        }
                        i2 = 1;
                    }
                }
                buffer2.setSize$okio(j2);
                return size;
            } else {
                throw new IllegalStateException("resizeBuffer() only permitted for read/write buffers".toString());
            }
        }

        public final long expandBuffer(int i) {
            boolean z = true;
            if (i > 0) {
                if (i > 8192) {
                    z = false;
                }
                if (z) {
                    Buffer buffer2 = this.buffer;
                    if (buffer2 == null) {
                        throw new IllegalStateException("not attached to a buffer".toString());
                    } else if (this.readWrite) {
                        long size = buffer2.size();
                        Segment writableSegment$okio = buffer2.writableSegment$okio(i);
                        int i2 = 8192 - writableSegment$okio.limit;
                        writableSegment$okio.limit = 8192;
                        long j = (long) i2;
                        buffer2.setSize$okio(size + j);
                        setSegment$okio(writableSegment$okio);
                        this.offset = size;
                        this.data = writableSegment$okio.data;
                        this.start = 8192 - i2;
                        this.end = 8192;
                        return j;
                    } else {
                        throw new IllegalStateException("expandBuffer() only permitted for read/write buffers".toString());
                    }
                } else {
                    throw new IllegalArgumentException(("minByteCount > Segment.SIZE: " + i).toString());
                }
            } else {
                throw new IllegalArgumentException(("minByteCount <= 0: " + i).toString());
            }
        }

        public void close() {
            if (this.buffer != null) {
                this.buffer = null;
                setSegment$okio((Segment) null);
                this.offset = -1;
                this.data = null;
                this.start = -1;
                this.end = -1;
                return;
            }
            throw new IllegalStateException("not attached to a buffer".toString());
        }
    }

    public final Buffer copyTo(Buffer buffer, long j, long j2) {
        Intrinsics.checkNotNullParameter(buffer, "out");
        SegmentedByteString.checkOffsetAndCount(size(), j, j2);
        if (j2 != 0) {
            buffer.setSize$okio(buffer.size() + j2);
            Segment segment = this.head;
            while (true) {
                Intrinsics.checkNotNull(segment);
                if (j < ((long) (segment.limit - segment.pos))) {
                    break;
                }
                j -= (long) (segment.limit - segment.pos);
                segment = segment.next;
            }
            while (j2 > 0) {
                Intrinsics.checkNotNull(segment);
                Segment sharedCopy = segment.sharedCopy();
                sharedCopy.pos += (int) j;
                sharedCopy.limit = Math.min(sharedCopy.pos + ((int) j2), sharedCopy.limit);
                Segment segment2 = buffer.head;
                if (segment2 == null) {
                    sharedCopy.prev = sharedCopy;
                    sharedCopy.next = sharedCopy.prev;
                    buffer.head = sharedCopy.next;
                } else {
                    Intrinsics.checkNotNull(segment2);
                    Segment segment3 = segment2.prev;
                    Intrinsics.checkNotNull(segment3);
                    segment3.push(sharedCopy);
                }
                j2 -= (long) (sharedCopy.limit - sharedCopy.pos);
                segment = segment.next;
                j = 0;
            }
        }
        return this;
    }

    public final long completeSegmentByteCount() {
        long size2 = size();
        if (size2 == 0) {
            return 0;
        }
        Segment segment = this.head;
        Intrinsics.checkNotNull(segment);
        Segment segment2 = segment.prev;
        Intrinsics.checkNotNull(segment2);
        if (segment2.limit < 8192 && segment2.owner) {
            size2 -= (long) (segment2.limit - segment2.pos);
        }
        return size2;
    }

    public byte readByte() throws EOFException {
        if (size() != 0) {
            Segment segment = this.head;
            Intrinsics.checkNotNull(segment);
            int i = segment.pos;
            int i2 = segment.limit;
            int i3 = i + 1;
            byte b = segment.data[i];
            setSize$okio(size() - 1);
            if (i3 == i2) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = i3;
            }
            return b;
        }
        throw new EOFException();
    }

    public final byte getByte(long j) {
        SegmentedByteString.checkOffsetAndCount(size(), j, 1);
        Segment segment = this.head;
        if (segment == null) {
            Intrinsics.checkNotNull((Object) null);
            byte[] bArr = null.data;
            throw null;
        } else if (size() - j < j) {
            long size2 = size();
            while (size2 > j) {
                segment = segment.prev;
                Intrinsics.checkNotNull(segment);
                size2 -= (long) (segment.limit - segment.pos);
            }
            Intrinsics.checkNotNull(segment);
            return segment.data[(int) ((((long) segment.pos) + j) - size2)];
        } else {
            long j2 = 0;
            while (true) {
                long j3 = ((long) (segment.limit - segment.pos)) + j2;
                if (j3 <= j) {
                    segment = segment.next;
                    Intrinsics.checkNotNull(segment);
                    j2 = j3;
                } else {
                    Intrinsics.checkNotNull(segment);
                    return segment.data[(int) ((((long) segment.pos) + j) - j2)];
                }
            }
        }
    }

    public short readShort() throws EOFException {
        if (size() >= 2) {
            Segment segment = this.head;
            Intrinsics.checkNotNull(segment);
            int i = segment.pos;
            int i2 = segment.limit;
            if (i2 - i < 2) {
                return (short) (((readByte() & 255) << 8) | (readByte() & 255));
            }
            byte[] bArr = segment.data;
            int i3 = i + 1;
            int i4 = i3 + 1;
            byte b = ((bArr[i] & 255) << 8) | (bArr[i3] & 255);
            setSize$okio(size() - 2);
            if (i4 == i2) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = i4;
            }
            return (short) b;
        }
        throw new EOFException();
    }

    public int readInt() throws EOFException {
        if (size() >= 4) {
            Segment segment = this.head;
            Intrinsics.checkNotNull(segment);
            int i = segment.pos;
            int i2 = segment.limit;
            if (((long) (i2 - i)) < 4) {
                return ((readByte() & 255) << Ascii.CAN) | ((readByte() & 255) << 16) | ((readByte() & 255) << 8) | (readByte() & 255);
            }
            byte[] bArr = segment.data;
            int i3 = i + 1;
            int i4 = i3 + 1;
            byte b = ((bArr[i] & 255) << Ascii.CAN) | ((bArr[i3] & 255) << 16);
            int i5 = i4 + 1;
            byte b2 = b | ((bArr[i4] & 255) << 8);
            int i6 = i5 + 1;
            byte b3 = b2 | (bArr[i5] & 255);
            setSize$okio(size() - 4);
            if (i6 == i2) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = i6;
            }
            return b3;
        }
        throw new EOFException();
    }

    public long readLong() throws EOFException {
        if (size() >= 8) {
            Segment segment = this.head;
            Intrinsics.checkNotNull(segment);
            int i = segment.pos;
            int i2 = segment.limit;
            if (((long) (i2 - i)) < 8) {
                return ((((long) readInt()) & MuxerUtil.UNSIGNED_INT_MAX_VALUE) << 32) | (MuxerUtil.UNSIGNED_INT_MAX_VALUE & ((long) readInt()));
            }
            byte[] bArr = segment.data;
            int i3 = i + 1;
            int i4 = i3 + 1;
            int i5 = i4 + 1;
            int i6 = i5 + 1;
            int i7 = i6 + 1;
            int i8 = i7 + 1;
            long j = ((((long) bArr[i]) & 255) << 56) | ((((long) bArr[i3]) & 255) << 48) | ((((long) bArr[i4]) & 255) << 40) | ((((long) bArr[i5]) & 255) << 32) | ((((long) bArr[i6]) & 255) << 24) | ((((long) bArr[i7]) & 255) << 16);
            int i9 = i8 + 1;
            int i10 = i9 + 1;
            long j2 = j | ((((long) bArr[i8]) & 255) << 8) | (((long) bArr[i9]) & 255);
            setSize$okio(size() - 8);
            if (i10 == i2) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = i10;
            }
            return j2;
        }
        throw new EOFException();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00a2, code lost:
        if (r2 == false) goto L_0x00a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00a4, code lost:
        r14 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00a6, code lost:
        r14 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00a7, code lost:
        if (r1 >= r14) goto L_0x00e7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00b1, code lost:
        if (size() == 0) goto L_0x00e1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00b3, code lost:
        if (r2 == false) goto L_0x00b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00b5, code lost:
        r1 = "Expected a digit";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00b8, code lost:
        r1 = "Expected a digit or '-'";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00e0, code lost:
        throw new java.lang.NumberFormatException(r1 + " but was 0x" + okio.SegmentedByteString.toHexString(getByte(0)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00e6, code lost:
        throw new java.io.EOFException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00e7, code lost:
        if (r2 == false) goto L_0x00ea;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:?, code lost:
        return -r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:?, code lost:
        return r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long readDecimalLong() throws java.io.EOFException {
        /*
            r18 = this;
            r0 = r18
            long r1 = r18.size()
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 == 0) goto L_0x00ec
            r1 = 0
            r5 = -7
            r2 = r1
            r8 = r3
            r6 = r5
            r5 = r2
        L_0x0013:
            okio.Segment r10 = r0.head
            kotlin.jvm.internal.Intrinsics.checkNotNull(r10)
            byte[] r11 = r10.data
            int r12 = r10.pos
            int r13 = r10.limit
        L_0x001e:
            if (r12 >= r13) goto L_0x0080
            byte r15 = r11[r12]
            r14 = 48
            if (r15 < r14) goto L_0x006e
            r14 = 57
            if (r15 > r14) goto L_0x006e
            int r14 = 48 - r15
            r16 = -922337203685477580(0xf333333333333334, double:-8.390303882365713E246)
            int r16 = (r8 > r16 ? 1 : (r8 == r16 ? 0 : -1))
            if (r16 < 0) goto L_0x0043
            if (r16 != 0) goto L_0x003d
            long r3 = (long) r14
            int r3 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r3 >= 0) goto L_0x003d
            goto L_0x0043
        L_0x003d:
            r3 = 10
            long r8 = r8 * r3
            long r3 = (long) r14
            long r8 = r8 + r3
            goto L_0x0078
        L_0x0043:
            okio.Buffer r1 = new okio.Buffer
            r1.<init>()
            okio.Buffer r1 = r1.writeDecimalLong((long) r8)
            okio.Buffer r1 = r1.writeByte((int) r15)
            if (r2 != 0) goto L_0x0055
            r1.readByte()
        L_0x0055:
            java.lang.NumberFormatException r2 = new java.lang.NumberFormatException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Number too large: "
            r3.<init>(r4)
            java.lang.String r1 = r1.readUtf8()
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.String r1 = r1.toString()
            r2.<init>(r1)
            throw r2
        L_0x006e:
            r3 = 45
            if (r15 != r3) goto L_0x007f
            if (r1 != 0) goto L_0x007f
            r2 = 1
            long r6 = r6 - r2
            r2 = 1
        L_0x0078:
            int r12 = r12 + 1
            int r1 = r1 + 1
            r3 = 0
            goto L_0x001e
        L_0x007f:
            r5 = 1
        L_0x0080:
            if (r12 != r13) goto L_0x008c
            okio.Segment r3 = r10.pop()
            r0.head = r3
            okio.SegmentPool.recycle(r10)
            goto L_0x008e
        L_0x008c:
            r10.pos = r12
        L_0x008e:
            if (r5 != 0) goto L_0x0099
            okio.Segment r3 = r0.head
            if (r3 != 0) goto L_0x0095
            goto L_0x0099
        L_0x0095:
            r3 = 0
            goto L_0x0013
        L_0x0099:
            long r3 = r18.size()
            long r5 = (long) r1
            long r3 = r3 - r5
            r0.setSize$okio(r3)
            if (r2 == 0) goto L_0x00a6
            r14 = 2
            goto L_0x00a7
        L_0x00a6:
            r14 = 1
        L_0x00a7:
            if (r1 >= r14) goto L_0x00e7
            long r3 = r18.size()
            r5 = 0
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 == 0) goto L_0x00e1
            if (r2 == 0) goto L_0x00b8
            java.lang.String r1 = "Expected a digit"
            goto L_0x00ba
        L_0x00b8:
            java.lang.String r1 = "Expected a digit or '-'"
        L_0x00ba:
            java.lang.NumberFormatException r2 = new java.lang.NumberFormatException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.String r3 = " but was 0x"
            java.lang.StringBuilder r1 = r1.append(r3)
            r3 = 0
            byte r3 = r0.getByte(r3)
            java.lang.String r3 = okio.SegmentedByteString.toHexString((byte) r3)
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.String r1 = r1.toString()
            r2.<init>(r1)
            throw r2
        L_0x00e1:
            java.io.EOFException r1 = new java.io.EOFException
            r1.<init>()
            throw r1
        L_0x00e7:
            if (r2 == 0) goto L_0x00ea
            goto L_0x00eb
        L_0x00ea:
            long r8 = -r8
        L_0x00eb:
            return r8
        L_0x00ec:
            java.io.EOFException r1 = new java.io.EOFException
            r1.<init>()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.readDecimalLong():long");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0092, code lost:
        if (r8 != r9) goto L_0x009e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0094, code lost:
        r14.head = r6.pop();
        okio.SegmentPool.recycle(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x009e, code lost:
        r6.pos = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00a0, code lost:
        if (r1 != false) goto L_0x00a6;
     */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0079 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long readHexadecimalUnsignedLong() throws java.io.EOFException {
        /*
            r14 = this;
            long r0 = r14.size()
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto L_0x00b0
            r0 = 0
            r1 = r0
            r4 = r2
        L_0x000d:
            okio.Segment r6 = r14.head
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            byte[] r7 = r6.data
            int r8 = r6.pos
            int r9 = r6.limit
        L_0x0018:
            if (r8 >= r9) goto L_0x0092
            byte r10 = r7[r8]
            r11 = 48
            if (r10 < r11) goto L_0x0027
            r11 = 57
            if (r10 > r11) goto L_0x0027
            int r11 = r10 + -48
            goto L_0x003f
        L_0x0027:
            r11 = 97
            if (r10 < r11) goto L_0x0034
            r11 = 102(0x66, float:1.43E-43)
            if (r10 > r11) goto L_0x0034
            int r11 = r10 + -97
        L_0x0031:
            int r11 = r11 + 10
            goto L_0x003f
        L_0x0034:
            r11 = 65
            if (r10 < r11) goto L_0x0075
            r11 = 70
            if (r10 > r11) goto L_0x0075
            int r11 = r10 + -65
            goto L_0x0031
        L_0x003f:
            r12 = -1152921504606846976(0xf000000000000000, double:-3.105036184601418E231)
            long r12 = r12 & r4
            int r12 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r12 != 0) goto L_0x004f
            r10 = 4
            long r4 = r4 << r10
            long r10 = (long) r11
            long r4 = r4 | r10
            int r8 = r8 + 1
            int r0 = r0 + 1
            goto L_0x0018
        L_0x004f:
            okio.Buffer r0 = new okio.Buffer
            r0.<init>()
            okio.Buffer r0 = r0.writeHexadecimalUnsignedLong((long) r4)
            okio.Buffer r0 = r0.writeByte((int) r10)
            java.lang.NumberFormatException r1 = new java.lang.NumberFormatException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Number too large: "
            r2.<init>(r3)
            java.lang.String r0 = r0.readUtf8()
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L_0x0075:
            if (r0 == 0) goto L_0x0079
            r1 = 1
            goto L_0x0092
        L_0x0079:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Expected leading [0-9a-fA-F] character but was 0x"
            r1.<init>(r2)
            java.lang.String r2 = okio.SegmentedByteString.toHexString((byte) r10)
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0092:
            if (r8 != r9) goto L_0x009e
            okio.Segment r7 = r6.pop()
            r14.head = r7
            okio.SegmentPool.recycle(r6)
            goto L_0x00a0
        L_0x009e:
            r6.pos = r8
        L_0x00a0:
            if (r1 != 0) goto L_0x00a6
            okio.Segment r6 = r14.head
            if (r6 != 0) goto L_0x000d
        L_0x00a6:
            long r1 = r14.size()
            long r6 = (long) r0
            long r1 = r1 - r6
            r14.setSize$okio(r1)
            return r4
        L_0x00b0:
            java.io.EOFException r0 = new java.io.EOFException
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.readHexadecimalUnsignedLong():long");
    }

    public ByteString readByteString() {
        return readByteString(size());
    }

    public ByteString readByteString(long j) throws EOFException {
        if (!(j >= 0 && j <= 2147483647L)) {
            throw new IllegalArgumentException(("byteCount: " + j).toString());
        } else if (size() < j) {
            throw new EOFException();
        } else if (j < 4096) {
            return new ByteString(readByteArray(j));
        } else {
            ByteString snapshot = snapshot((int) j);
            skip(j);
            return snapshot;
        }
    }

    public int select(Options options) {
        Intrinsics.checkNotNullParameter(options, "options");
        int selectPrefix$default = okio.internal.Buffer.selectPrefix$default(this, options, false, 2, (Object) null);
        if (selectPrefix$default == -1) {
            return -1;
        }
        skip((long) options.getByteStrings$okio()[selectPrefix$default].size());
        return selectPrefix$default;
    }

    public void readFully(Buffer buffer, long j) throws EOFException {
        Intrinsics.checkNotNullParameter(buffer, "sink");
        if (size() >= j) {
            buffer.write(this, j);
        } else {
            buffer.write(this, size());
            throw new EOFException();
        }
    }

    public long readAll(Sink sink) throws IOException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        long size2 = size();
        if (size2 > 0) {
            sink.write(this, size2);
        }
        return size2;
    }

    public String readUtf8Line() throws EOFException {
        long indexOf = indexOf((byte) 10);
        if (indexOf != -1) {
            return okio.internal.Buffer.readUtf8Line(this, indexOf);
        }
        if (size() != 0) {
            return readUtf8(size());
        }
        return null;
    }

    public String readUtf8LineStrict(long j) throws EOFException {
        if (j >= 0) {
            long j2 = Long.MAX_VALUE;
            if (j != Long.MAX_VALUE) {
                j2 = j + 1;
            }
            long indexOf = indexOf((byte) 10, 0, j2);
            if (indexOf != -1) {
                return okio.internal.Buffer.readUtf8Line(this, indexOf);
            }
            if (j2 < size() && getByte(j2 - 1) == 13 && getByte(j2) == 10) {
                return okio.internal.Buffer.readUtf8Line(this, j2);
            }
            Buffer buffer = new Buffer();
            copyTo(buffer, 0, Math.min((long) 32, size()));
            throw new EOFException("\\n not found: limit=" + Math.min(size(), j) + " content=" + buffer.readByteString().hex() + Typography.ellipsis);
        }
        throw new IllegalArgumentException(("limit < 0: " + j).toString());
    }

    public int readUtf8CodePoint() throws EOFException {
        byte b;
        int i;
        byte b2;
        if (size() != 0) {
            byte b3 = getByte(0);
            boolean z = false;
            if ((b3 & 128) == 0) {
                b2 = b3 & Byte.MAX_VALUE;
                b = 0;
                i = 1;
            } else if ((b3 & 224) == 192) {
                b2 = b3 & Ascii.US;
                i = 2;
                b = 128;
            } else if ((b3 & 240) == 224) {
                b2 = b3 & 15;
                i = 3;
                b = 2048;
            } else if ((b3 & 248) == 240) {
                b2 = b3 & 7;
                i = 4;
                b = 65536;
            } else {
                skip(1);
                return Utf8.REPLACEMENT_CODE_POINT;
            }
            long j = (long) i;
            if (size() >= j) {
                int i2 = 1;
                while (i2 < i) {
                    long j2 = (long) i2;
                    byte b4 = getByte(j2);
                    if ((b4 & 192) == 128) {
                        b2 = (b2 << 6) | (b4 & Utf8.REPLACEMENT_BYTE);
                        i2++;
                    } else {
                        skip(j2);
                        return Utf8.REPLACEMENT_CODE_POINT;
                    }
                }
                skip(j);
                if (b2 > 1114111) {
                    return Utf8.REPLACEMENT_CODE_POINT;
                }
                if (55296 <= b2 && b2 < 57344) {
                    z = true;
                }
                if (!z && b2 >= b) {
                    return b2;
                }
                return Utf8.REPLACEMENT_CODE_POINT;
            }
            throw new EOFException("size < " + i + ": " + size() + " (to read code point prefixed 0x" + SegmentedByteString.toHexString(b3) + ')');
        }
        throw new EOFException();
    }

    public byte[] readByteArray() {
        return readByteArray(size());
    }

    public byte[] readByteArray(long j) throws EOFException {
        if (!(j >= 0 && j <= 2147483647L)) {
            throw new IllegalArgumentException(("byteCount: " + j).toString());
        } else if (size() >= j) {
            byte[] bArr = new byte[((int) j)];
            readFully(bArr);
            return bArr;
        } else {
            throw new EOFException();
        }
    }

    public int read(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "sink");
        return read(bArr, 0, bArr.length);
    }

    public void readFully(byte[] bArr) throws EOFException {
        Intrinsics.checkNotNullParameter(bArr, "sink");
        int i = 0;
        while (i < bArr.length) {
            int read = read(bArr, i, bArr.length - i);
            if (read != -1) {
                i += read;
            } else {
                throw new EOFException();
            }
        }
    }

    public int read(byte[] bArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(bArr, "sink");
        SegmentedByteString.checkOffsetAndCount((long) bArr.length, (long) i, (long) i2);
        Segment segment = this.head;
        if (segment == null) {
            return -1;
        }
        int min = Math.min(i2, segment.limit - segment.pos);
        ArraysKt.copyInto(segment.data, bArr, i, segment.pos, segment.pos + min);
        segment.pos += min;
        setSize$okio(size() - ((long) min));
        if (segment.pos == segment.limit) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        }
        return min;
    }

    public final void clear() {
        skip(size());
    }

    public void skip(long j) throws EOFException {
        while (j > 0) {
            Segment segment = this.head;
            if (segment != null) {
                int min = (int) Math.min(j, (long) (segment.limit - segment.pos));
                long j2 = (long) min;
                setSize$okio(size() - j2);
                j -= j2;
                segment.pos += min;
                if (segment.pos == segment.limit) {
                    this.head = segment.pop();
                    SegmentPool.recycle(segment);
                }
            } else {
                throw new EOFException();
            }
        }
    }

    public Buffer write(ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        byteString.write$okio(this, 0, byteString.size());
        return this;
    }

    public Buffer write(ByteString byteString, int i, int i2) {
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        byteString.write$okio(this, i, i2);
        return this;
    }

    public Buffer writeUtf8(String str, int i, int i2) {
        char c;
        char charAt;
        Intrinsics.checkNotNullParameter(str, TypedValues.Custom.S_STRING);
        if (i >= 0) {
            if (i2 >= i) {
                if (i2 <= str.length()) {
                    while (i < i2) {
                        char charAt2 = str.charAt(i);
                        if (charAt2 < 128) {
                            Segment writableSegment$okio = writableSegment$okio(1);
                            byte[] bArr = writableSegment$okio.data;
                            int i3 = writableSegment$okio.limit - i;
                            int min = Math.min(i2, 8192 - i3);
                            int i4 = i + 1;
                            bArr[i + i3] = (byte) charAt2;
                            while (true) {
                                i = i4;
                                if (i >= min || (charAt = str.charAt(i)) >= 128) {
                                    int i5 = (i3 + i) - writableSegment$okio.limit;
                                    writableSegment$okio.limit += i5;
                                    setSize$okio(size() + ((long) i5));
                                } else {
                                    i4 = i + 1;
                                    bArr[i + i3] = (byte) charAt;
                                }
                            }
                            int i52 = (i3 + i) - writableSegment$okio.limit;
                            writableSegment$okio.limit += i52;
                            setSize$okio(size() + ((long) i52));
                        } else {
                            if (charAt2 < 2048) {
                                Segment writableSegment$okio2 = writableSegment$okio(2);
                                writableSegment$okio2.data[writableSegment$okio2.limit] = (byte) ((charAt2 >> 6) | 192);
                                writableSegment$okio2.data[writableSegment$okio2.limit + 1] = (byte) ((charAt2 & '?') | 128);
                                writableSegment$okio2.limit += 2;
                                setSize$okio(size() + 2);
                            } else if (charAt2 < 55296 || charAt2 > 57343) {
                                Segment writableSegment$okio3 = writableSegment$okio(3);
                                writableSegment$okio3.data[writableSegment$okio3.limit] = (byte) ((charAt2 >> 12) | 224);
                                writableSegment$okio3.data[writableSegment$okio3.limit + 1] = (byte) ((63 & (charAt2 >> 6)) | 128);
                                writableSegment$okio3.data[writableSegment$okio3.limit + 2] = (byte) ((charAt2 & '?') | 128);
                                writableSegment$okio3.limit += 3;
                                setSize$okio(size() + 3);
                            } else {
                                int i6 = i + 1;
                                if (i6 < i2) {
                                    c = str.charAt(i6);
                                } else {
                                    c = 0;
                                }
                                if (charAt2 <= 56319) {
                                    if (56320 <= c && c < 57344) {
                                        int i7 = (((charAt2 & 1023) << 10) | (c & 1023)) + 0;
                                        Segment writableSegment$okio4 = writableSegment$okio(4);
                                        writableSegment$okio4.data[writableSegment$okio4.limit] = (byte) ((i7 >> 18) | PsExtractor.VIDEO_STREAM_MASK);
                                        writableSegment$okio4.data[writableSegment$okio4.limit + 1] = (byte) (((i7 >> 12) & 63) | 128);
                                        writableSegment$okio4.data[writableSegment$okio4.limit + 2] = (byte) (((i7 >> 6) & 63) | 128);
                                        writableSegment$okio4.data[writableSegment$okio4.limit + 3] = (byte) ((i7 & 63) | 128);
                                        writableSegment$okio4.limit += 4;
                                        setSize$okio(size() + 4);
                                        i += 2;
                                    }
                                }
                                writeByte(63);
                                i = i6;
                            }
                            i++;
                        }
                    }
                    return this;
                }
                throw new IllegalArgumentException(("endIndex > string.length: " + i2 + " > " + str.length()).toString());
            }
            throw new IllegalArgumentException(("endIndex < beginIndex: " + i2 + " < " + i).toString());
        }
        throw new IllegalArgumentException(("beginIndex < 0: " + i).toString());
    }

    public Buffer writeUtf8CodePoint(int i) {
        if (i < 128) {
            writeByte(i);
        } else if (i < 2048) {
            Segment writableSegment$okio = writableSegment$okio(2);
            writableSegment$okio.data[writableSegment$okio.limit] = (byte) ((i >> 6) | 192);
            writableSegment$okio.data[writableSegment$okio.limit + 1] = (byte) ((i & 63) | 128);
            writableSegment$okio.limit += 2;
            setSize$okio(size() + 2);
        } else {
            boolean z = false;
            if (55296 <= i && i < 57344) {
                z = true;
            }
            if (z) {
                writeByte(63);
            } else if (i < 65536) {
                Segment writableSegment$okio2 = writableSegment$okio(3);
                writableSegment$okio2.data[writableSegment$okio2.limit] = (byte) ((i >> 12) | 224);
                writableSegment$okio2.data[writableSegment$okio2.limit + 1] = (byte) (((i >> 6) & 63) | 128);
                writableSegment$okio2.data[writableSegment$okio2.limit + 2] = (byte) ((i & 63) | 128);
                writableSegment$okio2.limit += 3;
                setSize$okio(size() + 3);
            } else if (i <= 1114111) {
                Segment writableSegment$okio3 = writableSegment$okio(4);
                writableSegment$okio3.data[writableSegment$okio3.limit] = (byte) ((i >> 18) | PsExtractor.VIDEO_STREAM_MASK);
                writableSegment$okio3.data[writableSegment$okio3.limit + 1] = (byte) (((i >> 12) & 63) | 128);
                writableSegment$okio3.data[writableSegment$okio3.limit + 2] = (byte) (((i >> 6) & 63) | 128);
                writableSegment$okio3.data[writableSegment$okio3.limit + 3] = (byte) ((i & 63) | 128);
                writableSegment$okio3.limit += 4;
                setSize$okio(size() + 4);
            } else {
                throw new IllegalArgumentException("Unexpected code point: 0x" + SegmentedByteString.toHexString(i));
            }
        }
        return this;
    }

    public Buffer write(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "source");
        return write(bArr, 0, bArr.length);
    }

    public Buffer write(byte[] bArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(bArr, "source");
        long j = (long) i2;
        SegmentedByteString.checkOffsetAndCount((long) bArr.length, (long) i, j);
        int i3 = i2 + i;
        while (i < i3) {
            Segment writableSegment$okio = writableSegment$okio(1);
            int min = Math.min(i3 - i, 8192 - writableSegment$okio.limit);
            int i4 = i + min;
            ArraysKt.copyInto(bArr, writableSegment$okio.data, writableSegment$okio.limit, i, i4);
            writableSegment$okio.limit += min;
            i = i4;
        }
        setSize$okio(size() + j);
        return this;
    }

    public long writeAll(Source source) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        long j = 0;
        while (true) {
            long read = source.read(this, 8192);
            if (read == -1) {
                return j;
            }
            j += read;
        }
    }

    public Buffer write(Source source, long j) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        while (j > 0) {
            long read = source.read(this, j);
            if (read != -1) {
                j -= read;
            } else {
                throw new EOFException();
            }
        }
        return this;
    }

    public Buffer writeByte(int i) {
        Segment writableSegment$okio = writableSegment$okio(1);
        byte[] bArr = writableSegment$okio.data;
        int i2 = writableSegment$okio.limit;
        writableSegment$okio.limit = i2 + 1;
        bArr[i2] = (byte) i;
        setSize$okio(size() + 1);
        return this;
    }

    public Buffer writeShort(int i) {
        Segment writableSegment$okio = writableSegment$okio(2);
        byte[] bArr = writableSegment$okio.data;
        int i2 = writableSegment$okio.limit;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 8) & 255);
        bArr[i3] = (byte) (i & 255);
        writableSegment$okio.limit = i3 + 1;
        setSize$okio(size() + 2);
        return this;
    }

    public Buffer writeInt(int i) {
        Segment writableSegment$okio = writableSegment$okio(4);
        byte[] bArr = writableSegment$okio.data;
        int i2 = writableSegment$okio.limit;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 24) & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i >>> 16) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((i >>> 8) & 255);
        bArr[i5] = (byte) (i & 255);
        writableSegment$okio.limit = i5 + 1;
        setSize$okio(size() + 4);
        return this;
    }

    public Buffer writeLong(long j) {
        Segment writableSegment$okio = writableSegment$okio(8);
        byte[] bArr = writableSegment$okio.data;
        int i = writableSegment$okio.limit;
        int i2 = i + 1;
        bArr[i] = (byte) ((int) ((j >>> 56) & 255));
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((int) ((j >>> 48) & 255));
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((int) ((j >>> 40) & 255));
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((int) ((j >>> 32) & 255));
        int i6 = i5 + 1;
        bArr[i5] = (byte) ((int) ((j >>> 24) & 255));
        int i7 = i6 + 1;
        bArr[i6] = (byte) ((int) ((j >>> 16) & 255));
        int i8 = i7 + 1;
        bArr[i7] = (byte) ((int) ((j >>> 8) & 255));
        bArr[i8] = (byte) ((int) (j & 255));
        writableSegment$okio.limit = i8 + 1;
        setSize$okio(size() + 8);
        return this;
    }

    public Buffer writeDecimalLong(long j) {
        boolean z;
        int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        if (i == 0) {
            return writeByte(48);
        }
        int i2 = 1;
        if (i < 0) {
            j = -j;
            if (j < 0) {
                return writeUtf8("-9223372036854775808");
            }
            z = true;
        } else {
            z = false;
        }
        if (j >= 100000000) {
            i2 = j < MediaPeriodQueue.INITIAL_RENDERER_POSITION_OFFSET_US ? j < RealConnection.IDLE_CONNECTION_HEALTHY_NS ? j < C.NANOS_PER_SECOND ? 9 : 10 : j < 100000000000L ? 11 : 12 : j < 1000000000000000L ? j < 10000000000000L ? 13 : j < 100000000000000L ? 14 : 15 : j < 100000000000000000L ? j < 10000000000000000L ? 16 : 17 : j < 1000000000000000000L ? 18 : 19;
        } else if (j >= 10000) {
            i2 = j < 1000000 ? j < SilenceSkippingAudioProcessor.DEFAULT_MINIMUM_SILENCE_DURATION_US ? 5 : 6 : j < 10000000 ? 7 : 8;
        } else if (j >= 100) {
            i2 = j < 1000 ? 3 : 4;
        } else if (j >= 10) {
            i2 = 2;
        }
        if (z) {
            i2++;
        }
        Segment writableSegment$okio = writableSegment$okio(i2);
        byte[] bArr = writableSegment$okio.data;
        int i3 = writableSegment$okio.limit + i2;
        while (j != 0) {
            long j2 = (long) 10;
            i3--;
            bArr[i3] = okio.internal.Buffer.getHEX_DIGIT_BYTES()[(int) (j % j2)];
            j /= j2;
        }
        if (z) {
            bArr[i3 - 1] = 45;
        }
        writableSegment$okio.limit += i2;
        setSize$okio(size() + ((long) i2));
        return this;
    }

    public Buffer writeHexadecimalUnsignedLong(long j) {
        if (j == 0) {
            return writeByte(48);
        }
        long j2 = (j >>> 1) | j;
        long j3 = j2 | (j2 >>> 2);
        long j4 = j3 | (j3 >>> 4);
        long j5 = j4 | (j4 >>> 8);
        long j6 = j5 | (j5 >>> 16);
        long j7 = j6 | (j6 >>> 32);
        long j8 = j7 - ((j7 >>> 1) & 6148914691236517205L);
        long j9 = ((j8 >>> 2) & 3689348814741910323L) + (j8 & 3689348814741910323L);
        long j10 = ((j9 >>> 4) + j9) & 1085102592571150095L;
        long j11 = j10 + (j10 >>> 8);
        long j12 = j11 + (j11 >>> 16);
        int i = (int) ((((j12 & 63) + ((j12 >>> 32) & 63)) + ((long) 3)) / ((long) 4));
        Segment writableSegment$okio = writableSegment$okio(i);
        byte[] bArr = writableSegment$okio.data;
        int i2 = writableSegment$okio.limit;
        for (int i3 = (writableSegment$okio.limit + i) - 1; i3 >= i2; i3--) {
            bArr[i3] = okio.internal.Buffer.getHEX_DIGIT_BYTES()[(int) (15 & j)];
            j >>>= 4;
        }
        writableSegment$okio.limit += i;
        setSize$okio(size() + ((long) i));
        return this;
    }

    public final Segment writableSegment$okio(int i) {
        boolean z = true;
        if (i < 1 || i > 8192) {
            z = false;
        }
        if (z) {
            Segment segment = this.head;
            if (segment == null) {
                Segment take = SegmentPool.take();
                this.head = take;
                take.prev = take;
                take.next = take;
                return take;
            }
            Intrinsics.checkNotNull(segment);
            Segment segment2 = segment.prev;
            Intrinsics.checkNotNull(segment2);
            if (segment2.limit + i > 8192 || !segment2.owner) {
                return segment2.push(SegmentPool.take());
            }
            return segment2;
        }
        throw new IllegalArgumentException("unexpected capacity".toString());
    }

    public void write(Buffer buffer, long j) {
        Segment segment;
        Intrinsics.checkNotNullParameter(buffer, "source");
        if (buffer != this) {
            SegmentedByteString.checkOffsetAndCount(buffer.size(), 0, j);
            while (j > 0) {
                Segment segment2 = buffer.head;
                Intrinsics.checkNotNull(segment2);
                int i = segment2.limit;
                Segment segment3 = buffer.head;
                Intrinsics.checkNotNull(segment3);
                if (j < ((long) (i - segment3.pos))) {
                    Segment segment4 = this.head;
                    if (segment4 != null) {
                        Intrinsics.checkNotNull(segment4);
                        segment = segment4.prev;
                    } else {
                        segment = null;
                    }
                    if (segment != null && segment.owner) {
                        if ((((long) segment.limit) + j) - ((long) (segment.shared ? 0 : segment.pos)) <= 8192) {
                            Segment segment5 = buffer.head;
                            Intrinsics.checkNotNull(segment5);
                            segment5.writeTo(segment, (int) j);
                            buffer.setSize$okio(buffer.size() - j);
                            setSize$okio(size() + j);
                            return;
                        }
                    }
                    Segment segment6 = buffer.head;
                    Intrinsics.checkNotNull(segment6);
                    buffer.head = segment6.split((int) j);
                }
                Segment segment7 = buffer.head;
                Intrinsics.checkNotNull(segment7);
                long j2 = (long) (segment7.limit - segment7.pos);
                buffer.head = segment7.pop();
                Segment segment8 = this.head;
                if (segment8 == null) {
                    this.head = segment7;
                    segment7.prev = segment7;
                    segment7.next = segment7.prev;
                } else {
                    Intrinsics.checkNotNull(segment8);
                    Segment segment9 = segment8.prev;
                    Intrinsics.checkNotNull(segment9);
                    segment9.push(segment7).compact();
                }
                buffer.setSize$okio(buffer.size() - j2);
                setSize$okio(size() + j2);
                j -= j2;
            }
            return;
        }
        throw new IllegalArgumentException("source == this".toString());
    }

    public long read(Buffer buffer, long j) {
        Intrinsics.checkNotNullParameter(buffer, "sink");
        if (!(j >= 0)) {
            throw new IllegalArgumentException(("byteCount < 0: " + j).toString());
        } else if (size() == 0) {
            return -1;
        } else {
            if (j > size()) {
                j = size();
            }
            buffer.write(this, j);
            return j;
        }
    }

    public long indexOf(byte b, long j, long j2) {
        Segment segment;
        int i;
        long j3;
        long j4 = 0;
        boolean z = false;
        if (0 <= j && j <= j2) {
            z = true;
        }
        if (z) {
            if (j2 > size()) {
                j2 = size();
            }
            if (j == j2 || (segment = this.head) == null) {
                return -1;
            }
            if (size() - j < j) {
                j3 = size();
                while (j3 > j) {
                    segment = segment.prev;
                    Intrinsics.checkNotNull(segment);
                    j3 -= (long) (segment.limit - segment.pos);
                }
                if (segment == null) {
                    return -1;
                }
                while (j3 < j2) {
                    byte[] bArr = segment.data;
                    int min = (int) Math.min((long) segment.limit, (((long) segment.pos) + j2) - j3);
                    i = (int) ((((long) segment.pos) + j) - j3);
                    while (i < min) {
                        if (bArr[i] != b) {
                            i++;
                        }
                    }
                    j3 += (long) (segment.limit - segment.pos);
                    segment = segment.next;
                    Intrinsics.checkNotNull(segment);
                    j = j3;
                }
                return -1;
            }
            while (true) {
                long j5 = ((long) (segment.limit - segment.pos)) + j4;
                if (j5 > j) {
                    break;
                }
                segment = segment.next;
                Intrinsics.checkNotNull(segment);
                j4 = j5;
            }
            if (segment == null) {
                return -1;
            }
            while (j3 < j2) {
                byte[] bArr2 = segment.data;
                int min2 = (int) Math.min((long) segment.limit, (((long) segment.pos) + j2) - j3);
                int i2 = (int) ((((long) segment.pos) + j) - j3);
                while (i < min2) {
                    if (bArr2[i] != b) {
                        i2 = i + 1;
                    }
                }
                j4 = j3 + ((long) (segment.limit - segment.pos));
                segment = segment.next;
                Intrinsics.checkNotNull(segment);
                j = j4;
            }
            return -1;
            return ((long) (i - segment.pos)) + j3;
        }
        throw new IllegalArgumentException(("size=" + size() + " fromIndex=" + j + " toIndex=" + j2).toString());
    }

    public long indexOf(ByteString byteString, long j) throws IOException {
        long j2 = j;
        Intrinsics.checkNotNullParameter(byteString, "bytes");
        if (byteString.size() > 0) {
            long j3 = 0;
            if (j2 >= 0) {
                Segment segment = this.head;
                if (segment != null) {
                    if (size() - j2 < j2) {
                        long size2 = size();
                        while (size2 > j2) {
                            segment = segment.prev;
                            Intrinsics.checkNotNull(segment);
                            size2 -= (long) (segment.limit - segment.pos);
                        }
                        if (segment != null) {
                            byte[] internalArray$okio = byteString.internalArray$okio();
                            byte b = internalArray$okio[0];
                            int size3 = byteString.size();
                            long size4 = (size() - ((long) size3)) + 1;
                            while (size2 < size4) {
                                byte[] bArr = segment.data;
                                long j4 = size2;
                                int min = (int) Math.min((long) segment.limit, (((long) segment.pos) + size4) - size2);
                                long j5 = ((long) segment.pos) + j2;
                                long j6 = j4;
                                for (int i = (int) (j5 - j6); i < min; i++) {
                                    if (bArr[i] == b && okio.internal.Buffer.rangeEquals(segment, i + 1, internalArray$okio, 1, size3)) {
                                        return ((long) (i - segment.pos)) + j6;
                                    }
                                }
                                size2 = j6 + ((long) (segment.limit - segment.pos));
                                segment = segment.next;
                                Intrinsics.checkNotNull(segment);
                                j2 = size2;
                            }
                        }
                    } else {
                        while (true) {
                            long j7 = ((long) (segment.limit - segment.pos)) + j3;
                            if (j7 > j2) {
                                break;
                            }
                            segment = segment.next;
                            Intrinsics.checkNotNull(segment);
                            j3 = j7;
                        }
                        if (segment != null) {
                            byte[] internalArray$okio2 = byteString.internalArray$okio();
                            byte b2 = internalArray$okio2[0];
                            int size5 = byteString.size();
                            long size6 = (size() - ((long) size5)) + 1;
                            while (j3 < size6) {
                                byte[] bArr2 = segment.data;
                                long j8 = size6;
                                int min2 = (int) Math.min((long) segment.limit, (((long) segment.pos) + size6) - j3);
                                for (int i2 = (int) ((((long) segment.pos) + j2) - j3); i2 < min2; i2++) {
                                    if (bArr2[i2] == b2) {
                                        if (okio.internal.Buffer.rangeEquals(segment, i2 + 1, internalArray$okio2, 1, size5)) {
                                            return ((long) (i2 - segment.pos)) + j3;
                                        }
                                    }
                                }
                                j3 += (long) (segment.limit - segment.pos);
                                segment = segment.next;
                                Intrinsics.checkNotNull(segment);
                                j2 = j3;
                                size6 = j8;
                            }
                        }
                    }
                }
                return -1;
            }
            throw new IllegalArgumentException(("fromIndex < 0: " + j2).toString());
        }
        throw new IllegalArgumentException("bytes is empty".toString());
    }

    public long indexOfElement(ByteString byteString, long j) {
        int i;
        long j2;
        int i2;
        int i3;
        Intrinsics.checkNotNullParameter(byteString, "targetBytes");
        long j3 = 0;
        if (j >= 0) {
            Segment segment = this.head;
            if (segment == null) {
                return -1;
            }
            if (size() - j < j) {
                j2 = size();
                while (j2 > j) {
                    segment = segment.prev;
                    Intrinsics.checkNotNull(segment);
                    j2 -= (long) (segment.limit - segment.pos);
                }
                if (segment == null) {
                    return -1;
                }
                if (byteString.size() == 2) {
                    byte b = byteString.getByte(0);
                    byte b2 = byteString.getByte(1);
                    while (j2 < size()) {
                        byte[] bArr = segment.data;
                        i = (int) ((((long) segment.pos) + j) - j2);
                        int i4 = segment.limit;
                        while (i < i4) {
                            byte b3 = bArr[i];
                            if (!(b3 == b || b3 == b2)) {
                                i++;
                            }
                        }
                        j2 += (long) (segment.limit - segment.pos);
                        segment = segment.next;
                        Intrinsics.checkNotNull(segment);
                        j = j2;
                    }
                    return -1;
                }
                byte[] internalArray$okio = byteString.internalArray$okio();
                while (j2 < size()) {
                    byte[] bArr2 = segment.data;
                    i3 = (int) ((((long) segment.pos) + j) - j2);
                    int i5 = segment.limit;
                    while (i3 < i5) {
                        byte b4 = bArr2[i3];
                        for (byte b5 : internalArray$okio) {
                            if (b4 == b5) {
                                i2 = segment.pos;
                                return ((long) (i - i2)) + j2;
                            }
                        }
                        i3++;
                    }
                    j2 += (long) (segment.limit - segment.pos);
                    segment = segment.next;
                    Intrinsics.checkNotNull(segment);
                    j = j2;
                }
                return -1;
            }
            while (true) {
                long j4 = ((long) (segment.limit - segment.pos)) + j3;
                if (j4 > j) {
                    break;
                }
                segment = segment.next;
                Intrinsics.checkNotNull(segment);
                j3 = j4;
            }
            if (segment == null) {
                return -1;
            }
            if (byteString.size() == 2) {
                byte b6 = byteString.getByte(0);
                byte b7 = byteString.getByte(1);
                while (j2 < size()) {
                    byte[] bArr3 = segment.data;
                    int i6 = (int) ((((long) segment.pos) + j) - j2);
                    int i7 = segment.limit;
                    while (i < i7) {
                        byte b8 = bArr3[i];
                        if (!(b8 == b6 || b8 == b7)) {
                            i6 = i + 1;
                        }
                    }
                    j3 = j2 + ((long) (segment.limit - segment.pos));
                    segment = segment.next;
                    Intrinsics.checkNotNull(segment);
                    j = j3;
                }
                return -1;
            }
            byte[] internalArray$okio2 = byteString.internalArray$okio();
            while (j2 < size()) {
                byte[] bArr4 = segment.data;
                int i8 = (int) ((((long) segment.pos) + j) - j2);
                int i9 = segment.limit;
                while (i3 < i9) {
                    byte b9 = bArr4[i3];
                    for (byte b10 : internalArray$okio2) {
                        if (b9 == b10) {
                            i2 = segment.pos;
                            return ((long) (i - i2)) + j2;
                        }
                    }
                    i8 = i3 + 1;
                }
                j3 = j2 + ((long) (segment.limit - segment.pos));
                segment = segment.next;
                Intrinsics.checkNotNull(segment);
                j = j3;
            }
            return -1;
            i2 = segment.pos;
            return ((long) (i - i2)) + j2;
        }
        throw new IllegalArgumentException(("fromIndex < 0: " + j).toString());
    }

    public boolean rangeEquals(long j, ByteString byteString, int i, int i2) {
        Intrinsics.checkNotNullParameter(byteString, "bytes");
        if (j < 0 || i < 0 || i2 < 0 || size() - j < ((long) i2) || byteString.size() - i < i2) {
            return false;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (getByte(((long) i3) + j) != byteString.getByte(i + i3)) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARNING: type inference failed for: r19v0, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r19) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = 1
            if (r0 != r1) goto L_0x0009
            goto L_0x0081
        L_0x0009:
            boolean r3 = r1 instanceof okio.Buffer
            r4 = 0
            if (r3 != 0) goto L_0x0011
        L_0x000e:
            r2 = r4
            goto L_0x0081
        L_0x0011:
            long r5 = r18.size()
            okio.Buffer r1 = (okio.Buffer) r1
            long r7 = r1.size()
            int r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r3 == 0) goto L_0x0020
            goto L_0x000e
        L_0x0020:
            long r5 = r18.size()
            r7 = 0
            int r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r3 != 0) goto L_0x002b
            goto L_0x0081
        L_0x002b:
            okio.Segment r3 = r0.head
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            okio.Segment r1 = r1.head
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            int r5 = r3.pos
            int r6 = r1.pos
            r9 = r7
        L_0x003a:
            long r11 = r18.size()
            int r11 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r11 >= 0) goto L_0x0081
            int r11 = r3.limit
            int r11 = r11 - r5
            int r12 = r1.limit
            int r12 = r12 - r6
            int r11 = java.lang.Math.min(r11, r12)
            long r11 = (long) r11
            r13 = r7
        L_0x004e:
            int r15 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
            if (r15 >= 0) goto L_0x0069
            byte[] r15 = r3.data
            int r16 = r5 + 1
            byte r5 = r15[r5]
            byte[] r15 = r1.data
            int r17 = r6 + 1
            byte r6 = r15[r6]
            if (r5 == r6) goto L_0x0061
            goto L_0x000e
        L_0x0061:
            r5 = 1
            long r13 = r13 + r5
            r5 = r16
            r6 = r17
            goto L_0x004e
        L_0x0069:
            int r13 = r3.limit
            if (r5 != r13) goto L_0x0074
            okio.Segment r3 = r3.next
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            int r5 = r3.pos
        L_0x0074:
            int r13 = r1.limit
            if (r6 != r13) goto L_0x007f
            okio.Segment r1 = r1.next
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            int r6 = r1.pos
        L_0x007f:
            long r9 = r9 + r11
            goto L_0x003a
        L_0x0081:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        Segment segment = this.head;
        if (segment == null) {
            return 0;
        }
        int i = 1;
        do {
            int i2 = segment.limit;
            for (int i3 = segment.pos; i3 < i2; i3++) {
                i = (i * 31) + segment.data[i3];
            }
            segment = segment.next;
            Intrinsics.checkNotNull(segment);
        } while (segment != this.head);
        return i;
    }

    public final Buffer copy() {
        Buffer buffer = new Buffer();
        if (size() != 0) {
            Segment segment = this.head;
            Intrinsics.checkNotNull(segment);
            Segment sharedCopy = segment.sharedCopy();
            buffer.head = sharedCopy;
            sharedCopy.prev = sharedCopy;
            sharedCopy.next = sharedCopy.prev;
            for (Segment segment2 = segment.next; segment2 != segment; segment2 = segment2.next) {
                Segment segment3 = sharedCopy.prev;
                Intrinsics.checkNotNull(segment3);
                Intrinsics.checkNotNull(segment2);
                segment3.push(segment2.sharedCopy());
            }
            buffer.setSize$okio(size());
        }
        return buffer;
    }

    public final ByteString snapshot() {
        if (size() <= 2147483647L) {
            return snapshot((int) size());
        }
        throw new IllegalStateException(("size > Int.MAX_VALUE: " + size()).toString());
    }

    public final ByteString snapshot(int i) {
        if (i == 0) {
            return ByteString.EMPTY;
        }
        SegmentedByteString.checkOffsetAndCount(size(), 0, (long) i);
        Segment segment = this.head;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            Intrinsics.checkNotNull(segment);
            if (segment.limit != segment.pos) {
                i3 += segment.limit - segment.pos;
                i4++;
                segment = segment.next;
            } else {
                throw new AssertionError("s.limit == s.pos");
            }
        }
        byte[][] bArr = new byte[i4][];
        int[] iArr = new int[(i4 * 2)];
        Segment segment2 = this.head;
        int i5 = 0;
        while (i2 < i) {
            Intrinsics.checkNotNull(segment2);
            bArr[i5] = segment2.data;
            i2 += segment2.limit - segment2.pos;
            iArr[i5] = Math.min(i2, i);
            iArr[((Object[]) bArr).length + i5] = segment2.pos;
            segment2.shared = true;
            i5++;
            segment2 = segment2.next;
        }
        return new C0012SegmentedByteString(bArr, iArr);
    }
}

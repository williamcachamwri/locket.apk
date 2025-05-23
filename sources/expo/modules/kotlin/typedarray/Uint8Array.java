package expo.modules.kotlin.typedarray;

import expo.modules.kotlin.jni.JavaScriptTypedArray;
import expo.modules.kotlin.jni.TypedArrayKind;
import expo.modules.kotlin.typedarray.GenericTypedArray;
import java.nio.ByteBuffer;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\u00020\u0004B\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u001e\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\tH\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0018\u0010\u0019J!\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020\tH\u0001J\u0011\u0010 \u001a\u00020!2\u0006\u0010\u001e\u001a\u00020\tH\u0001J\u0011\u0010\"\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\tH\u0001J\u0011\u0010#\u001a\u00020$2\u0006\u0010\u001e\u001a\u00020\tH\u0001J\u0011\u0010%\u001a\u00020&2\u0006\u0010\u001e\u001a\u00020\tH\u0001J\u0011\u0010'\u001a\u00020(2\u0006\u0010\u001e\u001a\u00020\tH\u0001J\u0011\u0010)\u001a\u00020*2\u0006\u0010\u001e\u001a\u00020\tH\u0001J#\u0010+\u001a\u00020\u001b2\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010,\u001a\u00020\u0003H\u0002ø\u0001\u0001¢\u0006\u0004\b-\u0010.J\t\u0010/\u001a\u000200H\u0001J!\u00101\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020\tH\u0001J\u0019\u00102\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\t2\u0006\u0010,\u001a\u00020!H\u0001J\u0019\u00103\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\t2\u0006\u0010,\u001a\u00020\tH\u0001J\u0019\u00104\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\t2\u0006\u0010,\u001a\u00020$H\u0001J\u0019\u00105\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\t2\u0006\u0010,\u001a\u00020&H\u0001J\u0019\u00106\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\t2\u0006\u0010,\u001a\u00020(H\u0001J\u0019\u00107\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\t2\u0006\u0010,\u001a\u00020*H\u0001R\u0012\u0010\b\u001a\u00020\tX\u0005¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u00020\tX\u0005¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u0012\u0010\u000e\u001a\u00020\u000fX\u0005¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0012\u0010\u0012\u001a\u00020\tX\u0005¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u000bR\u0014\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u00068"}, d2 = {"Lexpo/modules/kotlin/typedarray/Uint8Array;", "Lexpo/modules/kotlin/typedarray/TypedArray;", "Lexpo/modules/kotlin/typedarray/GenericTypedArray;", "Lkotlin/UByte;", "Lexpo/modules/kotlin/typedarray/RawTypedArrayHolder;", "rawArray", "Lexpo/modules/kotlin/jni/JavaScriptTypedArray;", "(Lexpo/modules/kotlin/jni/JavaScriptTypedArray;)V", "byteLength", "", "getByteLength", "()I", "byteOffset", "getByteOffset", "kind", "Lexpo/modules/kotlin/jni/TypedArrayKind;", "getKind", "()Lexpo/modules/kotlin/jni/TypedArrayKind;", "length", "getLength", "getRawArray", "()Lexpo/modules/kotlin/jni/JavaScriptTypedArray;", "get", "index", "get-Wa3L5BU", "(I)B", "read", "", "buffer", "", "position", "size", "read2Byte", "", "read4Byte", "read8Byte", "", "readByte", "", "readDouble", "", "readFloat", "", "set", "value", "set-EK-6454", "(IB)V", "toDirectBuffer", "Ljava/nio/ByteBuffer;", "write", "write2Byte", "write4Byte", "write8Byte", "writeByte", "writeDouble", "writeFloat", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ConcreteTypedArrays.kt */
public final class Uint8Array implements TypedArray, GenericTypedArray<UByte>, RawTypedArrayHolder {
    private final JavaScriptTypedArray rawArray;

    public int getByteLength() {
        return this.rawArray.getByteLength();
    }

    public int getByteOffset() {
        return this.rawArray.getByteOffset();
    }

    public TypedArrayKind getKind() {
        return this.rawArray.getKind();
    }

    public int getLength() {
        return this.rawArray.getLength();
    }

    public void read(byte[] bArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(bArr, "buffer");
        this.rawArray.read(bArr, i, i2);
    }

    public short read2Byte(int i) {
        return this.rawArray.read2Byte(i);
    }

    public int read4Byte(int i) {
        return this.rawArray.read4Byte(i);
    }

    public long read8Byte(int i) {
        return this.rawArray.read8Byte(i);
    }

    public byte readByte(int i) {
        return this.rawArray.readByte(i);
    }

    public double readDouble(int i) {
        return this.rawArray.readDouble(i);
    }

    public float readFloat(int i) {
        return this.rawArray.readFloat(i);
    }

    public ByteBuffer toDirectBuffer() {
        return this.rawArray.toDirectBuffer();
    }

    public void write(byte[] bArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(bArr, "buffer");
        this.rawArray.write(bArr, i, i2);
    }

    public void write2Byte(int i, short s) {
        this.rawArray.write2Byte(i, s);
    }

    public void write4Byte(int i, int i2) {
        this.rawArray.write4Byte(i, i2);
    }

    public void write8Byte(int i, long j) {
        this.rawArray.write8Byte(i, j);
    }

    public void writeByte(int i, byte b) {
        this.rawArray.writeByte(i, b);
    }

    public void writeDouble(int i, double d) {
        this.rawArray.writeDouble(i, d);
    }

    public void writeFloat(int i, float f) {
        this.rawArray.writeFloat(i, f);
    }

    public Uint8Array(JavaScriptTypedArray javaScriptTypedArray) {
        Intrinsics.checkNotNullParameter(javaScriptTypedArray, "rawArray");
        this.rawArray = javaScriptTypedArray;
    }

    public /* bridge */ /* synthetic */ Object get(int i) {
        return UByte.m2456boximpl(m2299getWa3L5BU(i));
    }

    public JavaScriptTypedArray getRawArray() {
        return this.rawArray;
    }

    public Iterator<UByte> iterator() {
        return GenericTypedArray.DefaultImpls.iterator(this);
    }

    public /* bridge */ /* synthetic */ void set(int i, Object obj) {
        m2300setEK6454(i, ((UByte) obj).m2512unboximpl());
    }

    /* renamed from: get-Wa3L5BU  reason: not valid java name */
    public byte m2299getWa3L5BU(int i) {
        TypedArray typedArray = this;
        if (i >= 0 && i < typedArray.getLength()) {
            return UByte.m2462constructorimpl(readByte(i * 1));
        }
        throw new IndexOutOfBoundsException();
    }

    /* renamed from: set-EK-6454  reason: not valid java name */
    public void m2300setEK6454(int i, byte b) {
        TypedArray typedArray = this;
        if (i < 0 || i >= typedArray.getLength()) {
            throw new IndexOutOfBoundsException();
        }
        writeByte(i * 1, b);
    }
}

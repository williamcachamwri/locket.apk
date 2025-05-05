package kotlinx.serialization.json.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\u0006¨\u0006\b"}, d2 = {"Lkotlinx/serialization/json/internal/ByteArrayPool8k;", "Lkotlinx/serialization/json/internal/ByteArrayPoolBase;", "()V", "release", "", "array", "", "take", "kotlinx-serialization-json"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ArrayPools.kt */
public final class ByteArrayPool8k extends ByteArrayPoolBase {
    public static final ByteArrayPool8k INSTANCE = new ByteArrayPool8k();

    private ByteArrayPool8k() {
    }

    public final byte[] take() {
        return super.take(8196);
    }

    public final void release(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "array");
        releaseImpl(bArr);
    }
}

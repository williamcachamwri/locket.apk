package com.facebook.imageutils;

import androidx.core.util.Pools;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroidx/core/util/Pools$SynchronizedPool;", "Ljava/nio/ByteBuffer;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: BitmapUtil.kt */
final class BitmapUtil$DECODE_BUFFERS$2 extends Lambda implements Function0<Pools.SynchronizedPool<ByteBuffer>> {
    public static final BitmapUtil$DECODE_BUFFERS$2 INSTANCE = new BitmapUtil$DECODE_BUFFERS$2();

    BitmapUtil$DECODE_BUFFERS$2() {
        super(0);
    }

    public final Pools.SynchronizedPool<ByteBuffer> invoke() {
        return new Pools.SynchronizedPool<>(12);
    }
}

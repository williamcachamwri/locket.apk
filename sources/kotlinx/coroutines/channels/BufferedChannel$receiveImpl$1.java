package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.jvm.functions.Function3;

@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
/* compiled from: BufferedChannel.kt */
public final class BufferedChannel$receiveImpl$1 implements Function3 {
    public static final BufferedChannel$receiveImpl$1 INSTANCE = new BufferedChannel$receiveImpl$1();

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        return invoke((ChannelSegment) obj, ((Number) obj2).intValue(), ((Number) obj3).longValue());
    }

    public final Void invoke(ChannelSegment<E> channelSegment, int i, long j) {
        throw new IllegalStateException("unexpected".toString());
    }
}

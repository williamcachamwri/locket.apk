package com.brentvatne.common.react;

import com.brentvatne.common.react.VideoEventEmitter;
import com.facebook.react.bridge.WritableMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "currentPosition", "", "bufferedDuration", "seekableDuration", "currentPlaybackTime", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoEventEmitter.kt */
final class VideoEventEmitter$addEventEmitters$4 extends Lambda implements Function4<Long, Long, Long, Double, Unit> {
    final /* synthetic */ VideoEventEmitter.EventBuilder $event;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoEventEmitter$addEventEmitters$4(VideoEventEmitter.EventBuilder eventBuilder) {
        super(4);
        this.$event = eventBuilder;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        invoke(((Number) obj).longValue(), ((Number) obj2).longValue(), ((Number) obj3).longValue(), ((Number) obj4).doubleValue());
        return Unit.INSTANCE;
    }

    public final void invoke(long j, long j2, long j3, double d) {
        final long j4 = j;
        final long j5 = j2;
        final long j6 = j3;
        final double d2 = d;
        this.$event.dispatch(EventTypes.EVENT_PROGRESS, new Function1<WritableMap, Unit>() {
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((WritableMap) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(WritableMap writableMap) {
                Intrinsics.checkNotNullParameter(writableMap, "$this$dispatch");
                writableMap.putDouble("currentTime", ((double) j4) / 1000.0d);
                writableMap.putDouble("playableDuration", ((double) j5) / 1000.0d);
                writableMap.putDouble("seekableDuration", ((double) j6) / 1000.0d);
                writableMap.putDouble("currentPlaybackTime", d2);
            }
        });
    }
}

package com.brentvatne.common.react;

import com.brentvatne.common.react.VideoEventEmitter;
import com.facebook.react.bridge.WritableMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "isPlaying", "", "isSeeking", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoEventEmitter.kt */
final class VideoEventEmitter$addEventEmitters$6 extends Lambda implements Function2<Boolean, Boolean, Unit> {
    final /* synthetic */ VideoEventEmitter.EventBuilder $event;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoEventEmitter$addEventEmitters$6(VideoEventEmitter.EventBuilder eventBuilder) {
        super(2);
        this.$event = eventBuilder;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke(((Boolean) obj).booleanValue(), ((Boolean) obj2).booleanValue());
        return Unit.INSTANCE;
    }

    public final void invoke(final boolean z, final boolean z2) {
        this.$event.dispatch(EventTypes.EVENT_PLAYBACK_STATE_CHANGED, new Function1<WritableMap, Unit>() {
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((WritableMap) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(WritableMap writableMap) {
                Intrinsics.checkNotNullParameter(writableMap, "$this$dispatch");
                writableMap.putBoolean("isPlaying", z);
                writableMap.putBoolean("isSeeking", z2);
            }
        });
    }
}

package com.brentvatne.common.react;

import com.brentvatne.common.react.VideoEventEmitter;
import com.facebook.react.bridge.WritableMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\n¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", "bitRateEstimate", "", "height", "", "width", "trackId", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoEventEmitter.kt */
final class VideoEventEmitter$addEventEmitters$5 extends Lambda implements Function4<Long, Integer, Integer, String, Unit> {
    final /* synthetic */ VideoEventEmitter.EventBuilder $event;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoEventEmitter$addEventEmitters$5(VideoEventEmitter.EventBuilder eventBuilder) {
        super(4);
        this.$event = eventBuilder;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        invoke(((Number) obj).longValue(), ((Number) obj2).intValue(), ((Number) obj3).intValue(), (String) obj4);
        return Unit.INSTANCE;
    }

    public final void invoke(long j, int i, int i2, String str) {
        final long j2 = j;
        final int i3 = i2;
        final int i4 = i;
        final String str2 = str;
        this.$event.dispatch(EventTypes.EVENT_BANDWIDTH, new Function1<WritableMap, Unit>() {
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((WritableMap) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(WritableMap writableMap) {
                Intrinsics.checkNotNullParameter(writableMap, "$this$dispatch");
                writableMap.putDouble("bitrate", (double) j2);
                int i = i3;
                if (i > 0) {
                    writableMap.putInt("width", i);
                }
                int i2 = i4;
                if (i2 > 0) {
                    writableMap.putInt("height", i2);
                }
                String str = str2;
                if (str != null) {
                    writableMap.putString("trackId", str);
                }
            }
        });
    }
}

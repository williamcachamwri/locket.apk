package com.brentvatne.common.react;

import androidx.core.app.NotificationCompat;
import com.brentvatne.common.react.VideoEventEmitter;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0018\u0010\u0004\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "adEvent", "", "adData", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoEventEmitter.kt */
final class VideoEventEmitter$addEventEmitters$26 extends Lambda implements Function2<String, Map<String, ? extends String>, Unit> {
    final /* synthetic */ VideoEventEmitter.EventBuilder $event;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoEventEmitter$addEventEmitters$26(VideoEventEmitter.EventBuilder eventBuilder) {
        super(2);
        this.$event = eventBuilder;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((String) obj, (Map<String, String>) (Map) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(final String str, final Map<String, String> map) {
        Intrinsics.checkNotNullParameter(str, "adEvent");
        this.$event.dispatch(EventTypes.EVENT_ON_RECEIVE_AD_EVENT, new Function1<WritableMap, Unit>() {
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((WritableMap) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(WritableMap writableMap) {
                Intrinsics.checkNotNullParameter(writableMap, "$this$dispatch");
                writableMap.putString(NotificationCompat.CATEGORY_EVENT, str);
                WritableMap createMap = Arguments.createMap();
                Map<String, String> map = map;
                if (map != null) {
                    for (Map.Entry next : map.entrySet()) {
                        String str = (String) next.getKey();
                        Intrinsics.checkNotNull(str);
                        createMap.putString(str, (String) next.getValue());
                    }
                }
                Unit unit = Unit.INSTANCE;
                writableMap.putMap("data", createMap);
            }
        });
    }
}

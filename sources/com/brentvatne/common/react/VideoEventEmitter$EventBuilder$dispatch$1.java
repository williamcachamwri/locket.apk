package com.brentvatne.common.react;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.events.Event;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00010\u0001J\u0010\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\u0014J\b\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"com/brentvatne/common/react/VideoEventEmitter$EventBuilder$dispatch$1", "Lcom/facebook/react/uimanager/events/Event;", "getEventData", "Lcom/facebook/react/bridge/WritableMap;", "kotlin.jvm.PlatformType", "getEventName", "", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoEventEmitter.kt */
public final class VideoEventEmitter$EventBuilder$dispatch$1 extends Event<Event<?>> {
    final /* synthetic */ EventTypes $event;
    final /* synthetic */ Function1<WritableMap, Unit> $paramsSetter;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoEventEmitter$EventBuilder$dispatch$1(EventTypes eventTypes, Function1<? super WritableMap, Unit> function1, int i, int i2) {
        super(i, i2);
        this.$event = eventTypes;
        this.$paramsSetter = function1;
    }

    public String getEventName() {
        return ViewProps.TOP + StringsKt.removePrefix(this.$event.getEventName(), (CharSequence) "on");
    }

    /* access modifiers changed from: protected */
    public WritableMap getEventData() {
        WritableMap createMap = Arguments.createMap();
        Function1<WritableMap, Unit> function1 = this.$paramsSetter;
        if (function1 == null) {
            function1 = VideoEventEmitter$EventBuilder$dispatch$1$getEventData$1.INSTANCE;
        }
        function1.invoke(createMap);
        return createMap;
    }
}

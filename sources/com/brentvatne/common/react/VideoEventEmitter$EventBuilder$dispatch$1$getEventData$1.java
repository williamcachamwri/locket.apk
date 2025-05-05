package com.brentvatne.common.react;

import com.facebook.react.bridge.WritableMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lcom/facebook/react/bridge/WritableMap;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoEventEmitter.kt */
final class VideoEventEmitter$EventBuilder$dispatch$1$getEventData$1 extends Lambda implements Function1<WritableMap, Unit> {
    public static final VideoEventEmitter$EventBuilder$dispatch$1$getEventData$1 INSTANCE = new VideoEventEmitter$EventBuilder$dispatch$1$getEventData$1();

    VideoEventEmitter$EventBuilder$dispatch$1$getEventData$1() {
        super(1);
    }

    public final void invoke(WritableMap writableMap) {
        Intrinsics.checkNotNullParameter(writableMap, "$this$null");
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((WritableMap) obj);
        return Unit.INSTANCE;
    }
}

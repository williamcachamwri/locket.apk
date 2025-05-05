package com.brentvatne.common.react;

import com.brentvatne.common.react.VideoEventEmitter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoEventEmitter.kt */
final class VideoEventEmitter$addEventEmitters$9 extends Lambda implements Function0<Unit> {
    final /* synthetic */ VideoEventEmitter.EventBuilder $event;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoEventEmitter$addEventEmitters$9(VideoEventEmitter.EventBuilder eventBuilder) {
        super(0);
        this.$event = eventBuilder;
    }

    public final void invoke() {
        VideoEventEmitter.EventBuilder.dispatch$default(this.$event, EventTypes.EVENT_FULLSCREEN_WILL_PRESENT, (Function1) null, 2, (Object) null);
    }
}

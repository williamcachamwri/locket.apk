package com.brentvatne.common.react;

import com.brentvatne.common.api.Track;
import com.brentvatne.common.react.VideoEventEmitter;
import com.facebook.react.bridge.WritableMap;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "audioTracks", "Ljava/util/ArrayList;", "Lcom/brentvatne/common/api/Track;", "Lkotlin/collections/ArrayList;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoEventEmitter.kt */
final class VideoEventEmitter$addEventEmitters$22 extends Lambda implements Function1<ArrayList<Track>, Unit> {
    final /* synthetic */ VideoEventEmitter.EventBuilder $event;
    final /* synthetic */ VideoEventEmitter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoEventEmitter$addEventEmitters$22(VideoEventEmitter.EventBuilder eventBuilder, VideoEventEmitter videoEventEmitter) {
        super(1);
        this.$event = eventBuilder;
        this.this$0 = videoEventEmitter;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ArrayList<Track>) (ArrayList) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(final ArrayList<Track> arrayList) {
        VideoEventEmitter.EventBuilder eventBuilder = this.$event;
        EventTypes eventTypes = EventTypes.EVENT_AUDIO_TRACKS;
        final VideoEventEmitter videoEventEmitter = this.this$0;
        eventBuilder.dispatch(eventTypes, new Function1<WritableMap, Unit>() {
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((WritableMap) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(WritableMap writableMap) {
                Intrinsics.checkNotNullParameter(writableMap, "$this$dispatch");
                writableMap.putArray("audioTracks", videoEventEmitter.audioTracksToArray(arrayList));
            }
        });
    }
}

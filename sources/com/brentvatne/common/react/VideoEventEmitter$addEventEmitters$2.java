package com.brentvatne.common.react;

import com.brentvatne.common.api.Track;
import com.brentvatne.common.api.VideoTrack;
import com.brentvatne.common.react.VideoEventEmitter;
import com.facebook.react.bridge.WritableMap;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function8;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0016\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00020\n`\u000b2\u0016\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00020\n`\u000b2\u0016\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\tj\b\u0012\u0004\u0012\u00020\u000e`\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\n¢\u0006\u0002\b\u0011"}, d2 = {"<anonymous>", "", "duration", "", "currentPosition", "videoWidth", "", "videoHeight", "audioTracks", "Ljava/util/ArrayList;", "Lcom/brentvatne/common/api/Track;", "Lkotlin/collections/ArrayList;", "textTracks", "videoTracks", "Lcom/brentvatne/common/api/VideoTrack;", "trackId", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoEventEmitter.kt */
final class VideoEventEmitter$addEventEmitters$2 extends Lambda implements Function8<Long, Long, Integer, Integer, ArrayList<Track>, ArrayList<Track>, ArrayList<VideoTrack>, String, Unit> {
    final /* synthetic */ VideoEventEmitter.EventBuilder $event;
    final /* synthetic */ VideoEventEmitter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoEventEmitter$addEventEmitters$2(VideoEventEmitter.EventBuilder eventBuilder, VideoEventEmitter videoEventEmitter) {
        super(8);
        this.$event = eventBuilder;
        this.this$0 = videoEventEmitter;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        invoke(((Number) obj).longValue(), ((Number) obj2).longValue(), ((Number) obj3).intValue(), ((Number) obj4).intValue(), (ArrayList<Track>) (ArrayList) obj5, (ArrayList<Track>) (ArrayList) obj6, (ArrayList<VideoTrack>) (ArrayList) obj7, (String) obj8);
        return Unit.INSTANCE;
    }

    public final void invoke(long j, long j2, int i, int i2, ArrayList<Track> arrayList, ArrayList<Track> arrayList2, ArrayList<VideoTrack> arrayList3, String str) {
        final ArrayList<Track> arrayList4 = arrayList;
        Intrinsics.checkNotNullParameter(arrayList4, "audioTracks");
        final ArrayList<Track> arrayList5 = arrayList2;
        Intrinsics.checkNotNullParameter(arrayList5, "textTracks");
        final ArrayList<VideoTrack> arrayList6 = arrayList3;
        Intrinsics.checkNotNullParameter(arrayList6, "videoTracks");
        VideoEventEmitter.EventBuilder eventBuilder = this.$event;
        EventTypes eventTypes = EventTypes.EVENT_LOAD;
        final VideoEventEmitter videoEventEmitter = this.this$0;
        final long j3 = j;
        final long j4 = j2;
        final int i3 = i;
        final int i4 = i2;
        final String str2 = str;
        eventBuilder.dispatch(eventTypes, new Function1<WritableMap, Unit>() {
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((WritableMap) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(WritableMap writableMap) {
                Intrinsics.checkNotNullParameter(writableMap, "$this$dispatch");
                writableMap.putDouble("duration", ((double) j3) / 1000.0d);
                writableMap.putDouble("currentTime", ((double) j4) / 1000.0d);
                writableMap.putMap("naturalSize", videoEventEmitter.aspectRatioToNaturalSize(i3, i4));
                String str = str2;
                if (str != null) {
                    writableMap.putString("trackId", str);
                }
                writableMap.putArray("videoTracks", videoEventEmitter.videoTracksToArray(arrayList6));
                writableMap.putArray("audioTracks", videoEventEmitter.audioTracksToArray(arrayList4));
                writableMap.putArray("textTracks", videoEventEmitter.textTracksToArray(arrayList5));
                writableMap.putBoolean("canPlayFastForward", true);
                writableMap.putBoolean("canPlaySlowForward", true);
                writableMap.putBoolean("canPlaySlowReverse", true);
                writableMap.putBoolean("canPlayReverse", true);
                writableMap.putBoolean("canPlayFastForward", true);
                writableMap.putBoolean("canStepBackward", true);
                writableMap.putBoolean("canStepForward", true);
            }
        });
    }
}

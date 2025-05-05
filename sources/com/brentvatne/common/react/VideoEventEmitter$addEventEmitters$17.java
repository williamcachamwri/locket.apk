package com.brentvatne.common.react;

import com.brentvatne.common.api.TimedMetadata;
import com.brentvatne.common.react.VideoEventEmitter;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "metadataArrayList", "Ljava/util/ArrayList;", "Lcom/brentvatne/common/api/TimedMetadata;", "Lkotlin/collections/ArrayList;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoEventEmitter.kt */
final class VideoEventEmitter$addEventEmitters$17 extends Lambda implements Function1<ArrayList<TimedMetadata>, Unit> {
    final /* synthetic */ VideoEventEmitter.EventBuilder $event;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoEventEmitter$addEventEmitters$17(VideoEventEmitter.EventBuilder eventBuilder) {
        super(1);
        this.$event = eventBuilder;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ArrayList<TimedMetadata>) (ArrayList) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(final ArrayList<TimedMetadata> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "metadataArrayList");
        if (arrayList.size() != 0) {
            this.$event.dispatch(EventTypes.EVENT_TIMED_METADATA, new Function1<WritableMap, Unit>() {
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((WritableMap) obj);
                    return Unit.INSTANCE;
                }

                public final void invoke(WritableMap writableMap) {
                    Intrinsics.checkNotNullParameter(writableMap, "$this$dispatch");
                    WritableArray createArray = Arguments.createArray();
                    int i = 0;
                    for (Object next : arrayList) {
                        int i2 = i + 1;
                        if (i < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        TimedMetadata timedMetadata = (TimedMetadata) next;
                        WritableMap createMap = Arguments.createMap();
                        createMap.putString("identifier", timedMetadata.getIdentifier());
                        createMap.putString("value", timedMetadata.getValue());
                        createArray.pushMap(createMap);
                        i = i2;
                    }
                    Unit unit = Unit.INSTANCE;
                    writableMap.putArray(TtmlNode.TAG_METADATA, createArray);
                }
            });
        }
    }
}

package com.brentvatne.common.api;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0005¢\u0006\u0002\u0010\u0002J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001H\u0002R*\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000f"}, d2 = {"Lcom/brentvatne/common/api/SideLoadedTextTrackList;", "", "()V", "tracks", "Ljava/util/ArrayList;", "Lcom/brentvatne/common/api/SideLoadedTextTrack;", "Lkotlin/collections/ArrayList;", "getTracks", "()Ljava/util/ArrayList;", "setTracks", "(Ljava/util/ArrayList;)V", "equals", "", "other", "Companion", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SideLoadedTextTrackList.kt */
public final class SideLoadedTextTrackList {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private ArrayList<SideLoadedTextTrack> tracks = new ArrayList<>();

    public final ArrayList<SideLoadedTextTrack> getTracks() {
        return this.tracks;
    }

    public final void setTracks(ArrayList<SideLoadedTextTrack> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.tracks = arrayList;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof SideLoadedTextTrackList)) {
            return false;
        }
        return Intrinsics.areEqual((Object) this.tracks, (Object) ((SideLoadedTextTrackList) obj).tracks);
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lcom/brentvatne/common/api/SideLoadedTextTrackList$Companion;", "", "()V", "parse", "Lcom/brentvatne/common/api/SideLoadedTextTrackList;", "src", "Lcom/facebook/react/bridge/ReadableArray;", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: SideLoadedTextTrackList.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SideLoadedTextTrackList parse(ReadableArray readableArray) {
            if (readableArray == null) {
                return null;
            }
            SideLoadedTextTrackList sideLoadedTextTrackList = new SideLoadedTextTrackList();
            int size = readableArray.size();
            for (int i = 0; i < size; i++) {
                ReadableMap map = readableArray.getMap(i);
                if (map != null) {
                    sideLoadedTextTrackList.getTracks().add(SideLoadedTextTrack.Companion.parse(map));
                }
            }
            return sideLoadedTextTrackList;
        }
    }
}

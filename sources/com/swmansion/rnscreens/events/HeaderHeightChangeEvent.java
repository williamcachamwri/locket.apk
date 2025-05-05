package com.swmansion.rnscreens.events;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u000f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000fB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/swmansion/rnscreens/events/HeaderHeightChangeEvent;", "Lcom/facebook/react/uimanager/events/Event;", "viewId", "", "mHeaderHeight", "", "(ID)V", "dispatch", "", "rctEventEmitter", "Lcom/facebook/react/uimanager/events/RCTEventEmitter;", "getCoalescingKey", "", "getEventName", "", "Companion", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: HeaderHeightChangeEvent.kt */
public final class HeaderHeightChangeEvent extends Event<HeaderHeightChangeEvent> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String EVENT_NAME = "topHeaderHeightChange";
    private final double mHeaderHeight;

    public String getEventName() {
        return EVENT_NAME;
    }

    public HeaderHeightChangeEvent(int i, double d) {
        super(i);
        this.mHeaderHeight = d;
    }

    public short getCoalescingKey() {
        return (short) ((int) this.mHeaderHeight);
    }

    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        Intrinsics.checkNotNullParameter(rCTEventEmitter, "rctEventEmitter");
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble("headerHeight", this.mHeaderHeight);
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), createMap);
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/swmansion/rnscreens/events/HeaderHeightChangeEvent$Companion;", "", "()V", "EVENT_NAME", "", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: HeaderHeightChangeEvent.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}

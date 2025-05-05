package com.reactnativekeyboardcontroller.events;

import androidx.core.app.NotificationCompat;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.events.Event;
import io.sentry.protocol.ViewHierarchyNode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\n\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000eB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0016J\n\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0014J\b\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/reactnativekeyboardcontroller/events/FocusedInputSelectionChangedEvent;", "Lcom/facebook/react/uimanager/events/Event;", "surfaceId", "", "viewId", "event", "Lcom/reactnativekeyboardcontroller/events/FocusedInputSelectionChangedEventData;", "(IILcom/reactnativekeyboardcontroller/events/FocusedInputSelectionChangedEventData;)V", "getCoalescingKey", "", "getEventData", "Lcom/facebook/react/bridge/WritableMap;", "getEventName", "", "Companion", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FocusedInputSelectionChangedEvent.kt */
public final class FocusedInputSelectionChangedEvent extends Event<FocusedInputSelectionChangedEvent> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String EVENT_NAME = "topFocusedInputSelectionChanged";
    private final FocusedInputSelectionChangedEventData event;

    public short getCoalescingKey() {
        return 0;
    }

    public String getEventName() {
        return EVENT_NAME;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FocusedInputSelectionChangedEvent(int i, int i2, FocusedInputSelectionChangedEventData focusedInputSelectionChangedEventData) {
        super(i, i2);
        Intrinsics.checkNotNullParameter(focusedInputSelectionChangedEventData, NotificationCompat.CATEGORY_EVENT);
        this.event = focusedInputSelectionChangedEventData;
    }

    /* access modifiers changed from: protected */
    public WritableMap getEventData() {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("target", this.event.getTarget());
        WritableMap createMap2 = Arguments.createMap();
        WritableMap createMap3 = Arguments.createMap();
        createMap3.putDouble(ViewHierarchyNode.JsonKeys.X, this.event.getStartX());
        createMap3.putDouble(ViewHierarchyNode.JsonKeys.Y, this.event.getStartY());
        createMap3.putInt(ViewProps.POSITION, this.event.getStart());
        Unit unit = Unit.INSTANCE;
        createMap2.putMap("start", createMap3);
        WritableMap createMap4 = Arguments.createMap();
        createMap4.putDouble(ViewHierarchyNode.JsonKeys.X, this.event.getEndX());
        createMap4.putDouble(ViewHierarchyNode.JsonKeys.Y, this.event.getEndY());
        createMap4.putInt(ViewProps.POSITION, this.event.getEnd());
        Unit unit2 = Unit.INSTANCE;
        createMap2.putMap("end", createMap4);
        Unit unit3 = Unit.INSTANCE;
        createMap.putMap("selection", createMap2);
        return createMap;
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/reactnativekeyboardcontroller/events/FocusedInputSelectionChangedEvent$Companion;", "", "()V", "EVENT_NAME", "", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: FocusedInputSelectionChangedEvent.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}

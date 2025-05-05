package com.reactnativekeyboardcontroller.events;

import androidx.core.app.NotificationCompat;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\n\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00132\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0013B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003¢\u0006\u0002\u0010\fJ\b\u0010\r\u001a\u00020\u000eH\u0016J\n\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u000e\u0010\n\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/reactnativekeyboardcontroller/events/KeyboardTransitionEvent;", "Lcom/facebook/react/uimanager/events/Event;", "surfaceId", "", "viewId", "event", "Lcom/reactnativekeyboardcontroller/events/KeyboardTransitionEvent$Companion$EventName;", "height", "", "progress", "duration", "target", "(IILcom/reactnativekeyboardcontroller/events/KeyboardTransitionEvent$Companion$EventName;DDII)V", "getCoalescingKey", "", "getEventData", "Lcom/facebook/react/bridge/WritableMap;", "getEventName", "", "Companion", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: KeyboardTransitionEvent.kt */
public final class KeyboardTransitionEvent extends Event<KeyboardTransitionEvent> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Companion.EventName End = Companion.EventName.End;
    /* access modifiers changed from: private */
    public static final Companion.EventName Interactive = Companion.EventName.Interactive;
    /* access modifiers changed from: private */
    public static final Companion.EventName Move = Companion.EventName.Move;
    /* access modifiers changed from: private */
    public static final Companion.EventName Start = Companion.EventName.Start;
    private final int duration;
    private final Companion.EventName event;
    private final double height;
    private final double progress;
    private final int target;

    public short getCoalescingKey() {
        return 0;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public KeyboardTransitionEvent(int i, int i2, Companion.EventName eventName, double d, double d2, int i3, int i4) {
        super(i, i2);
        Intrinsics.checkNotNullParameter(eventName, NotificationCompat.CATEGORY_EVENT);
        this.event = eventName;
        this.height = d;
        this.progress = d2;
        this.duration = i3;
        this.target = i4;
    }

    public String getEventName() {
        return this.event.getValue();
    }

    /* access modifiers changed from: protected */
    public WritableMap getEventData() {
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble("progress", this.progress);
        createMap.putDouble("height", this.height);
        createMap.putInt("duration", this.duration);
        createMap.putInt("target", this.target);
        return createMap;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0003\u0018\u00002\u00020\u0001:\u0001\rB\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006¨\u0006\u000e"}, d2 = {"Lcom/reactnativekeyboardcontroller/events/KeyboardTransitionEvent$Companion;", "", "()V", "End", "Lcom/reactnativekeyboardcontroller/events/KeyboardTransitionEvent$Companion$EventName;", "getEnd", "()Lcom/reactnativekeyboardcontroller/events/KeyboardTransitionEvent$Companion$EventName;", "Interactive", "getInteractive", "Move", "getMove", "Start", "getStart", "EventName", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: KeyboardTransitionEvent.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/reactnativekeyboardcontroller/events/KeyboardTransitionEvent$Companion$EventName;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "Move", "Start", "End", "Interactive", "react-native-keyboard-controller_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        /* compiled from: KeyboardTransitionEvent.kt */
        public enum EventName {
            Move("topKeyboardMove"),
            Start("topKeyboardMoveStart"),
            End("topKeyboardMoveEnd"),
            Interactive("topKeyboardMoveInteractive");
            
            private final String value;

            public static EnumEntries<EventName> getEntries() {
                return $ENTRIES;
            }

            private EventName(String str) {
                this.value = str;
            }

            public final String getValue() {
                return this.value;
            }

            static {
                EventName[] $values;
                $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
            }
        }

        private Companion() {
        }

        public final EventName getMove() {
            return KeyboardTransitionEvent.Move;
        }

        public final EventName getStart() {
            return KeyboardTransitionEvent.Start;
        }

        public final EventName getEnd() {
            return KeyboardTransitionEvent.End;
        }

        public final EventName getInteractive() {
            return KeyboardTransitionEvent.Interactive;
        }
    }
}

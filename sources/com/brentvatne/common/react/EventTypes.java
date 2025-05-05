package com.brentvatne.common.react;

import com.facebook.react.uimanager.ViewProps;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b \b\u0002\u0018\u0000 \"2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\"B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!¨\u0006#"}, d2 = {"Lcom/brentvatne/common/react/EventTypes;", "", "eventName", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getEventName", "()Ljava/lang/String;", "EVENT_LOAD_START", "EVENT_LOAD", "EVENT_ERROR", "EVENT_PROGRESS", "EVENT_BANDWIDTH", "EVENT_CONTROLS_VISIBILITY_CHANGE", "EVENT_SEEK", "EVENT_END", "EVENT_FULLSCREEN_WILL_PRESENT", "EVENT_FULLSCREEN_DID_PRESENT", "EVENT_FULLSCREEN_WILL_DISMISS", "EVENT_FULLSCREEN_DID_DISMISS", "EVENT_READY", "EVENT_BUFFER", "EVENT_PLAYBACK_STATE_CHANGED", "EVENT_IDLE", "EVENT_TIMED_METADATA", "EVENT_AUDIO_BECOMING_NOISY", "EVENT_AUDIO_FOCUS_CHANGE", "EVENT_PLAYBACK_RATE_CHANGE", "EVENT_VOLUME_CHANGE", "EVENT_AUDIO_TRACKS", "EVENT_TEXT_TRACKS", "EVENT_TEXT_TRACK_DATA_CHANGED", "EVENT_VIDEO_TRACKS", "EVENT_ON_RECEIVE_AD_EVENT", "EVENT_PICTURE_IN_PICTURE_STATUS_CHANGED", "Companion", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoEventEmitter.kt */
public enum EventTypes {
    EVENT_LOAD_START("onVideoLoadStart"),
    EVENT_LOAD("onVideoLoad"),
    EVENT_ERROR("onVideoError"),
    EVENT_PROGRESS("onVideoProgress"),
    EVENT_BANDWIDTH("onVideoBandwidthUpdate"),
    EVENT_CONTROLS_VISIBILITY_CHANGE("onControlsVisibilityChange"),
    EVENT_SEEK("onVideoSeek"),
    EVENT_END("onVideoEnd"),
    EVENT_FULLSCREEN_WILL_PRESENT("onVideoFullscreenPlayerWillPresent"),
    EVENT_FULLSCREEN_DID_PRESENT("onVideoFullscreenPlayerDidPresent"),
    EVENT_FULLSCREEN_WILL_DISMISS("onVideoFullscreenPlayerWillDismiss"),
    EVENT_FULLSCREEN_DID_DISMISS("onVideoFullscreenPlayerDidDismiss"),
    EVENT_READY("onReadyForDisplay"),
    EVENT_BUFFER("onVideoBuffer"),
    EVENT_PLAYBACK_STATE_CHANGED("onVideoPlaybackStateChanged"),
    EVENT_IDLE("onVideoIdle"),
    EVENT_TIMED_METADATA("onTimedMetadata"),
    EVENT_AUDIO_BECOMING_NOISY("onVideoAudioBecomingNoisy"),
    EVENT_AUDIO_FOCUS_CHANGE("onAudioFocusChanged"),
    EVENT_PLAYBACK_RATE_CHANGE("onPlaybackRateChange"),
    EVENT_VOLUME_CHANGE("onVolumeChange"),
    EVENT_AUDIO_TRACKS("onAudioTracks"),
    EVENT_TEXT_TRACKS("onTextTracks"),
    EVENT_TEXT_TRACK_DATA_CHANGED("onTextTrackDataChanged"),
    EVENT_VIDEO_TRACKS("onVideoTracks"),
    EVENT_ON_RECEIVE_AD_EVENT("onReceiveAdEvent"),
    EVENT_PICTURE_IN_PICTURE_STATUS_CHANGED("onPictureInPictureStatusChanged");
    
    public static final Companion Companion = null;
    private final String eventName;

    public static EnumEntries<EventTypes> getEntries() {
        return $ENTRIES;
    }

    private EventTypes(String str) {
        this.eventName = str;
    }

    public final String getEventName() {
        return this.eventName;
    }

    static {
        EventTypes[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004¨\u0006\u0006"}, d2 = {"Lcom/brentvatne/common/react/EventTypes$Companion;", "", "()V", "toMap", "", "", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: VideoEventEmitter.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Map<String, Object> toMap() {
            Map<String, Object> linkedHashMap = new LinkedHashMap<>();
            for (EventTypes eventTypes : ArraysKt.toList((T[]) EventTypes.values())) {
                linkedHashMap.put(ViewProps.TOP + StringsKt.removePrefix(eventTypes.getEventName(), (CharSequence) "on"), MapsKt.hashMapOf(TuplesKt.to("registrationName", eventTypes.getEventName())));
            }
            return linkedHashMap;
        }
    }
}

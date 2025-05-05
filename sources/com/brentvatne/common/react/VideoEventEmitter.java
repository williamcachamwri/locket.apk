package com.brentvatne.common.react;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.brentvatne.common.api.TimedMetadata;
import com.brentvatne.common.api.Track;
import com.brentvatne.common.api.VideoTrack;
import com.brentvatne.exoplayer.ReactExoplayerView;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.google.firebase.analytics.FirebaseAnalytics;
import expo.modules.devlauncher.launcher.manifest.DevLauncherOrientation;
import io.sentry.protocol.Device;
import java.util.ArrayList;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function8;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000´\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0006\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002 \u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001b\u0010\u0001\u001a\u00020\t2\b\u0010\u0001\u001a\u00030\u00012\b\u0010\u0001\u001a\u00030\u0001J\u001a\u0010\u0001\u001a\u00030\u00012\u0006\u0010s\u001a\u00020F2\u0006\u0010t\u001a\u00020FH\u0002J\u001a\u0010\u0001\u001a\u00030\u00012\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000fH\u0002J&\u0010\u0001\u001a\u00030\u00012\u001a\u00107\u001a\u0016\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000fj\n\u0012\u0004\u0012\u00020\u0010\u0018\u0001`\u0011H\u0002J\u001a\u0010\u0001\u001a\u00030\u00012\u000e\u0010v\u001a\n\u0012\u0004\u0012\u00020u\u0018\u00010\u000fH\u0002R5\u0010\u0003\u001a\u001d\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0004X.¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rRI\u0010\u000e\u001a1\u0012'\u0012%\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000fj\n\u0012\u0004\u0012\u00020\u0010\u0018\u0001`\u0011¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\t0\u0004X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000b\"\u0004\b\u0014\u0010\rR5\u0010\u0015\u001a\u001d\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\t0\u0004X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u000b\"\u0004\b\u0018\u0010\rR5\u0010\u0019\u001a\u001d\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\t0\u0004X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u000b\"\u0004\b\u001c\u0010\rR5\u0010\u001d\u001a\u001d\u0012\u0013\u0012\u00110\u001e¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020\t0\u0004X.¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u000b\"\u0004\b!\u0010\rR \u0010\"\u001a\b\u0012\u0004\u0012\u00020\t0#X.¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\\\u0010(\u001aD\u0012\u0013\u0012\u00110*¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(+\u0012%\u0012#\u0012\u0006\u0012\u0004\u0018\u00010*\u0012\u0006\u0012\u0004\u0018\u00010*\u0018\u00010,¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(-\u0012\u0004\u0012\u00020\t0)X.¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R5\u00102\u001a\u001d\u0012\u0013\u0012\u00110*¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(3\u0012\u0004\u0012\u00020\t0\u0004X.¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u000b\"\u0004\b5\u0010\rRI\u00106\u001a1\u0012'\u0012%\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000fj\n\u0012\u0004\u0012\u00020\u0010\u0018\u0001`\u0011¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(7\u0012\u0004\u0012\u00020\t0\u0004X.¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010\u000b\"\u0004\b9\u0010\rRE\u0010:\u001a-\u0012#\u0012!\u0012\u0004\u0012\u00020;0\u000fj\b\u0012\u0004\u0012\u00020;`\u0011¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(<\u0012\u0004\u0012\u00020\t0\u0004X.¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u000b\"\u0004\b>\u0010\rR \u0010?\u001a\b\u0012\u0004\u0012\u00020\t0#X.¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010%\"\u0004\bA\u0010'Rv\u0010B\u001a^\u0012\u0013\u0012\u00110D¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(E\u0012\u0013\u0012\u00110F¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(G\u0012\u0013\u0012\u00110F¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(H\u0012\u0015\u0012\u0013\u0018\u00010*¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(I\u0012\u0004\u0012\u00020\t0CX.¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010K\"\u0004\bL\u0010MR5\u0010N\u001a\u001d\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(O\u0012\u0004\u0012\u00020\t0\u0004X.¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010\u000b\"\u0004\bQ\u0010\rR \u0010R\u001a\b\u0012\u0004\u0012\u00020\t0#X.¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010%\"\u0004\bT\u0010'Rc\u0010U\u001aK\u0012\u0013\u0012\u00110*¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(W\u0012\u0017\u0012\u00150Xj\u0002`Y¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(Z\u0012\u0013\u0012\u00110*¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b([\u0012\u0004\u0012\u00020\t0VX.¢\u0006\u000e\n\u0000\u001a\u0004\b\\\u0010]\"\u0004\b^\u0010_R \u0010`\u001a\b\u0012\u0004\u0012\u00020\t0#X.¢\u0006\u000e\n\u0000\u001a\u0004\ba\u0010%\"\u0004\bb\u0010'R \u0010c\u001a\b\u0012\u0004\u0012\u00020\t0#X.¢\u0006\u000e\n\u0000\u001a\u0004\bd\u0010%\"\u0004\be\u0010'R \u0010f\u001a\b\u0012\u0004\u0012\u00020\t0#X.¢\u0006\u000e\n\u0000\u001a\u0004\bg\u0010%\"\u0004\bh\u0010'R \u0010i\u001a\b\u0012\u0004\u0012\u00020\t0#X.¢\u0006\u000e\n\u0000\u001a\u0004\bj\u0010%\"\u0004\bk\u0010'R \u0010l\u001a\b\u0012\u0004\u0012\u00020\t0#X.¢\u0006\u000e\n\u0000\u001a\u0004\bm\u0010%\"\u0004\bn\u0010'Rû\u0001\u0010o\u001aâ\u0001\u0012\u0013\u0012\u00110D¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(q\u0012\u0013\u0012\u00110D¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(r\u0012\u0013\u0012\u00110F¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(s\u0012\u0013\u0012\u00110F¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(t\u0012#\u0012!\u0012\u0004\u0012\u00020\u00100\u000fj\b\u0012\u0004\u0012\u00020\u0010`\u0011¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0012\u0012#\u0012!\u0012\u0004\u0012\u00020\u00100\u000fj\b\u0012\u0004\u0012\u00020\u0010`\u0011¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(7\u0012#\u0012!\u0012\u0004\u0012\u00020u0\u000fj\b\u0012\u0004\u0012\u00020u`\u0011¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(v\u0012\u0015\u0012\u0013\u0018\u00010*¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(I\u0012\u0004\u0012\u00020\t0pX.¢\u0006\u000e\n\u0000\u001a\u0004\bw\u0010x\"\u0004\by\u0010zR \u0010{\u001a\b\u0012\u0004\u0012\u00020\t0#X.¢\u0006\u000e\n\u0000\u001a\u0004\b|\u0010%\"\u0004\b}\u0010'RM\u0010~\u001a3\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0012\u0014\u0012\u00120\u0005¢\u0006\r\b\u0006\u0012\t\b\u0007\u0012\u0005\b\b(\u0001\u0012\u0004\u0012\u00020\t0)X.¢\u0006\u0010\n\u0000\u001a\u0005\b\u0001\u0010/\"\u0005\b\u0001\u00101R{\u0010\u0001\u001a`\u0012\u0013\u0012\u00110D¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(r\u0012\u0014\u0012\u00120D¢\u0006\r\b\u0006\u0012\t\b\u0007\u0012\u0005\b\b(\u0001\u0012\u0014\u0012\u00120D¢\u0006\r\b\u0006\u0012\t\b\u0007\u0012\u0005\b\b(\u0001\u0012\u0015\u0012\u00130\u0001¢\u0006\r\b\u0006\u0012\t\b\u0007\u0012\u0005\b\b(\u0001\u0012\u0004\u0012\u00020\t0CX.¢\u0006\u0010\n\u0000\u001a\u0005\b\u0001\u0010K\"\u0005\b\u0001\u0010MRN\u0010\u0001\u001a3\u0012\u0013\u0012\u00110D¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(r\u0012\u0014\u0012\u00120D¢\u0006\r\b\u0006\u0012\t\b\u0007\u0012\u0005\b\b(\u0001\u0012\u0004\u0012\u00020\t0)X.¢\u0006\u0010\n\u0000\u001a\u0005\b\u0001\u0010/\"\u0005\b\u0001\u00101RL\u0010\u0001\u001a1\u0012'\u0012%\u0012\u0004\u0012\u00020u\u0018\u00010\u000fj\n\u0012\u0004\u0012\u00020u\u0018\u0001`\u0011¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(v\u0012\u0004\u0012\u00020\t0\u0004X.¢\u0006\u0010\n\u0000\u001a\u0005\b\u0001\u0010\u000b\"\u0005\b\u0001\u0010\rR9\u0010\u0001\u001a\u001e\u0012\u0014\u0012\u00120\u001e¢\u0006\r\b\u0006\u0012\t\b\u0007\u0012\u0005\b\b(\u0001\u0012\u0004\u0012\u00020\t0\u0004X.¢\u0006\u0010\n\u0000\u001a\u0005\b\u0001\u0010\u000b\"\u0005\b\u0001\u0010\r¨\u0006¡\u0001"}, d2 = {"Lcom/brentvatne/common/react/VideoEventEmitter;", "", "()V", "onAudioFocusChanged", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "hasFocus", "", "getOnAudioFocusChanged", "()Lkotlin/jvm/functions/Function1;", "setOnAudioFocusChanged", "(Lkotlin/jvm/functions/Function1;)V", "onAudioTracks", "Ljava/util/ArrayList;", "Lcom/brentvatne/common/api/Track;", "Lkotlin/collections/ArrayList;", "audioTracks", "getOnAudioTracks", "setOnAudioTracks", "onControlsVisibilityChange", "isVisible", "getOnControlsVisibilityChange", "setOnControlsVisibilityChange", "onPictureInPictureStatusChanged", "isActive", "getOnPictureInPictureStatusChanged", "setOnPictureInPictureStatusChanged", "onPlaybackRateChange", "", "rate", "getOnPlaybackRateChange", "setOnPlaybackRateChange", "onReadyForDisplay", "Lkotlin/Function0;", "getOnReadyForDisplay", "()Lkotlin/jvm/functions/Function0;", "setOnReadyForDisplay", "(Lkotlin/jvm/functions/Function0;)V", "onReceiveAdEvent", "Lkotlin/Function2;", "", "adEvent", "", "adData", "getOnReceiveAdEvent", "()Lkotlin/jvm/functions/Function2;", "setOnReceiveAdEvent", "(Lkotlin/jvm/functions/Function2;)V", "onTextTrackDataChanged", "textTrackData", "getOnTextTrackDataChanged", "setOnTextTrackDataChanged", "onTextTracks", "textTracks", "getOnTextTracks", "setOnTextTracks", "onTimedMetadata", "Lcom/brentvatne/common/api/TimedMetadata;", "metadataArrayList", "getOnTimedMetadata", "setOnTimedMetadata", "onVideoAudioBecomingNoisy", "getOnVideoAudioBecomingNoisy", "setOnVideoAudioBecomingNoisy", "onVideoBandwidthUpdate", "Lkotlin/Function4;", "", "bitRateEstimate", "", "height", "width", "trackId", "getOnVideoBandwidthUpdate", "()Lkotlin/jvm/functions/Function4;", "setOnVideoBandwidthUpdate", "(Lkotlin/jvm/functions/Function4;)V", "onVideoBuffer", "isBuffering", "getOnVideoBuffer", "setOnVideoBuffer", "onVideoEnd", "getOnVideoEnd", "setOnVideoEnd", "onVideoError", "Lkotlin/Function3;", "errorString", "Ljava/lang/Exception;", "Lkotlin/Exception;", "exception", "errorCode", "getOnVideoError", "()Lkotlin/jvm/functions/Function3;", "setOnVideoError", "(Lkotlin/jvm/functions/Function3;)V", "onVideoFullscreenPlayerDidDismiss", "getOnVideoFullscreenPlayerDidDismiss", "setOnVideoFullscreenPlayerDidDismiss", "onVideoFullscreenPlayerDidPresent", "getOnVideoFullscreenPlayerDidPresent", "setOnVideoFullscreenPlayerDidPresent", "onVideoFullscreenPlayerWillDismiss", "getOnVideoFullscreenPlayerWillDismiss", "setOnVideoFullscreenPlayerWillDismiss", "onVideoFullscreenPlayerWillPresent", "getOnVideoFullscreenPlayerWillPresent", "setOnVideoFullscreenPlayerWillPresent", "onVideoIdle", "getOnVideoIdle", "setOnVideoIdle", "onVideoLoad", "Lkotlin/Function8;", "duration", "currentPosition", "videoWidth", "videoHeight", "Lcom/brentvatne/common/api/VideoTrack;", "videoTracks", "getOnVideoLoad", "()Lkotlin/jvm/functions/Function8;", "setOnVideoLoad", "(Lkotlin/jvm/functions/Function8;)V", "onVideoLoadStart", "getOnVideoLoadStart", "setOnVideoLoadStart", "onVideoPlaybackStateChanged", "isPlaying", "isSeeking", "getOnVideoPlaybackStateChanged", "setOnVideoPlaybackStateChanged", "onVideoProgress", "bufferedDuration", "seekableDuration", "", "currentPlaybackTime", "getOnVideoProgress", "setOnVideoProgress", "onVideoSeek", "seekTime", "getOnVideoSeek", "setOnVideoSeek", "onVideoTracks", "getOnVideoTracks", "setOnVideoTracks", "onVolumeChange", "volume", "getOnVolumeChange", "setOnVolumeChange", "addEventEmitters", "reactContext", "Lcom/facebook/react/uimanager/ThemedReactContext;", "view", "Lcom/brentvatne/exoplayer/ReactExoplayerView;", "aspectRatioToNaturalSize", "Lcom/facebook/react/bridge/WritableMap;", "audioTracksToArray", "Lcom/facebook/react/bridge/WritableArray;", "textTracksToArray", "videoTracksToArray", "EventBuilder", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoEventEmitter.kt */
public final class VideoEventEmitter {
    public Function1<? super Boolean, Unit> onAudioFocusChanged;
    public Function1<? super ArrayList<Track>, Unit> onAudioTracks;
    public Function1<? super Boolean, Unit> onControlsVisibilityChange;
    public Function1<? super Boolean, Unit> onPictureInPictureStatusChanged;
    public Function1<? super Float, Unit> onPlaybackRateChange;
    public Function0<Unit> onReadyForDisplay;
    public Function2<? super String, ? super Map<String, String>, Unit> onReceiveAdEvent;
    public Function1<? super String, Unit> onTextTrackDataChanged;
    public Function1<? super ArrayList<Track>, Unit> onTextTracks;
    public Function1<? super ArrayList<TimedMetadata>, Unit> onTimedMetadata;
    public Function0<Unit> onVideoAudioBecomingNoisy;
    public Function4<? super Long, ? super Integer, ? super Integer, ? super String, Unit> onVideoBandwidthUpdate;
    public Function1<? super Boolean, Unit> onVideoBuffer;
    public Function0<Unit> onVideoEnd;
    public Function3<? super String, ? super Exception, ? super String, Unit> onVideoError;
    public Function0<Unit> onVideoFullscreenPlayerDidDismiss;
    public Function0<Unit> onVideoFullscreenPlayerDidPresent;
    public Function0<Unit> onVideoFullscreenPlayerWillDismiss;
    public Function0<Unit> onVideoFullscreenPlayerWillPresent;
    public Function0<Unit> onVideoIdle;
    public Function8<? super Long, ? super Long, ? super Integer, ? super Integer, ? super ArrayList<Track>, ? super ArrayList<Track>, ? super ArrayList<VideoTrack>, ? super String, Unit> onVideoLoad;
    public Function0<Unit> onVideoLoadStart;
    public Function2<? super Boolean, ? super Boolean, Unit> onVideoPlaybackStateChanged;
    public Function4<? super Long, ? super Long, ? super Long, ? super Double, Unit> onVideoProgress;
    public Function2<? super Long, ? super Long, Unit> onVideoSeek;
    public Function1<? super ArrayList<VideoTrack>, Unit> onVideoTracks;
    public Function1<? super Float, Unit> onVolumeChange;

    public final Function0<Unit> getOnVideoLoadStart() {
        Function0<Unit> function0 = this.onVideoLoadStart;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onVideoLoadStart");
        return null;
    }

    public final void setOnVideoLoadStart(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.onVideoLoadStart = function0;
    }

    public final Function8<Long, Long, Integer, Integer, ArrayList<Track>, ArrayList<Track>, ArrayList<VideoTrack>, String, Unit> getOnVideoLoad() {
        Function8<? super Long, ? super Long, ? super Integer, ? super Integer, ? super ArrayList<Track>, ? super ArrayList<Track>, ? super ArrayList<VideoTrack>, ? super String, Unit> function8 = this.onVideoLoad;
        if (function8 != null) {
            return function8;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onVideoLoad");
        return null;
    }

    public final void setOnVideoLoad(Function8<? super Long, ? super Long, ? super Integer, ? super Integer, ? super ArrayList<Track>, ? super ArrayList<Track>, ? super ArrayList<VideoTrack>, ? super String, Unit> function8) {
        Intrinsics.checkNotNullParameter(function8, "<set-?>");
        this.onVideoLoad = function8;
    }

    public final Function3<String, Exception, String, Unit> getOnVideoError() {
        Function3<? super String, ? super Exception, ? super String, Unit> function3 = this.onVideoError;
        if (function3 != null) {
            return function3;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onVideoError");
        return null;
    }

    public final void setOnVideoError(Function3<? super String, ? super Exception, ? super String, Unit> function3) {
        Intrinsics.checkNotNullParameter(function3, "<set-?>");
        this.onVideoError = function3;
    }

    public final Function4<Long, Long, Long, Double, Unit> getOnVideoProgress() {
        Function4<? super Long, ? super Long, ? super Long, ? super Double, Unit> function4 = this.onVideoProgress;
        if (function4 != null) {
            return function4;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onVideoProgress");
        return null;
    }

    public final void setOnVideoProgress(Function4<? super Long, ? super Long, ? super Long, ? super Double, Unit> function4) {
        Intrinsics.checkNotNullParameter(function4, "<set-?>");
        this.onVideoProgress = function4;
    }

    public final Function4<Long, Integer, Integer, String, Unit> getOnVideoBandwidthUpdate() {
        Function4<? super Long, ? super Integer, ? super Integer, ? super String, Unit> function4 = this.onVideoBandwidthUpdate;
        if (function4 != null) {
            return function4;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onVideoBandwidthUpdate");
        return null;
    }

    public final void setOnVideoBandwidthUpdate(Function4<? super Long, ? super Integer, ? super Integer, ? super String, Unit> function4) {
        Intrinsics.checkNotNullParameter(function4, "<set-?>");
        this.onVideoBandwidthUpdate = function4;
    }

    public final Function2<Boolean, Boolean, Unit> getOnVideoPlaybackStateChanged() {
        Function2<? super Boolean, ? super Boolean, Unit> function2 = this.onVideoPlaybackStateChanged;
        if (function2 != null) {
            return function2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onVideoPlaybackStateChanged");
        return null;
    }

    public final void setOnVideoPlaybackStateChanged(Function2<? super Boolean, ? super Boolean, Unit> function2) {
        Intrinsics.checkNotNullParameter(function2, "<set-?>");
        this.onVideoPlaybackStateChanged = function2;
    }

    public final Function2<Long, Long, Unit> getOnVideoSeek() {
        Function2<? super Long, ? super Long, Unit> function2 = this.onVideoSeek;
        if (function2 != null) {
            return function2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onVideoSeek");
        return null;
    }

    public final void setOnVideoSeek(Function2<? super Long, ? super Long, Unit> function2) {
        Intrinsics.checkNotNullParameter(function2, "<set-?>");
        this.onVideoSeek = function2;
    }

    public final Function0<Unit> getOnVideoEnd() {
        Function0<Unit> function0 = this.onVideoEnd;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onVideoEnd");
        return null;
    }

    public final void setOnVideoEnd(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.onVideoEnd = function0;
    }

    public final Function0<Unit> getOnVideoFullscreenPlayerWillPresent() {
        Function0<Unit> function0 = this.onVideoFullscreenPlayerWillPresent;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onVideoFullscreenPlayerWillPresent");
        return null;
    }

    public final void setOnVideoFullscreenPlayerWillPresent(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.onVideoFullscreenPlayerWillPresent = function0;
    }

    public final Function0<Unit> getOnVideoFullscreenPlayerDidPresent() {
        Function0<Unit> function0 = this.onVideoFullscreenPlayerDidPresent;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onVideoFullscreenPlayerDidPresent");
        return null;
    }

    public final void setOnVideoFullscreenPlayerDidPresent(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.onVideoFullscreenPlayerDidPresent = function0;
    }

    public final Function0<Unit> getOnVideoFullscreenPlayerWillDismiss() {
        Function0<Unit> function0 = this.onVideoFullscreenPlayerWillDismiss;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onVideoFullscreenPlayerWillDismiss");
        return null;
    }

    public final void setOnVideoFullscreenPlayerWillDismiss(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.onVideoFullscreenPlayerWillDismiss = function0;
    }

    public final Function0<Unit> getOnVideoFullscreenPlayerDidDismiss() {
        Function0<Unit> function0 = this.onVideoFullscreenPlayerDidDismiss;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onVideoFullscreenPlayerDidDismiss");
        return null;
    }

    public final void setOnVideoFullscreenPlayerDidDismiss(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.onVideoFullscreenPlayerDidDismiss = function0;
    }

    public final Function0<Unit> getOnReadyForDisplay() {
        Function0<Unit> function0 = this.onReadyForDisplay;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onReadyForDisplay");
        return null;
    }

    public final void setOnReadyForDisplay(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.onReadyForDisplay = function0;
    }

    public final Function1<Boolean, Unit> getOnVideoBuffer() {
        Function1<? super Boolean, Unit> function1 = this.onVideoBuffer;
        if (function1 != null) {
            return function1;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onVideoBuffer");
        return null;
    }

    public final void setOnVideoBuffer(Function1<? super Boolean, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.onVideoBuffer = function1;
    }

    public final Function1<Boolean, Unit> getOnControlsVisibilityChange() {
        Function1<? super Boolean, Unit> function1 = this.onControlsVisibilityChange;
        if (function1 != null) {
            return function1;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onControlsVisibilityChange");
        return null;
    }

    public final void setOnControlsVisibilityChange(Function1<? super Boolean, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.onControlsVisibilityChange = function1;
    }

    public final Function0<Unit> getOnVideoIdle() {
        Function0<Unit> function0 = this.onVideoIdle;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onVideoIdle");
        return null;
    }

    public final void setOnVideoIdle(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.onVideoIdle = function0;
    }

    public final Function1<ArrayList<TimedMetadata>, Unit> getOnTimedMetadata() {
        Function1<? super ArrayList<TimedMetadata>, Unit> function1 = this.onTimedMetadata;
        if (function1 != null) {
            return function1;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onTimedMetadata");
        return null;
    }

    public final void setOnTimedMetadata(Function1<? super ArrayList<TimedMetadata>, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.onTimedMetadata = function1;
    }

    public final Function0<Unit> getOnVideoAudioBecomingNoisy() {
        Function0<Unit> function0 = this.onVideoAudioBecomingNoisy;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onVideoAudioBecomingNoisy");
        return null;
    }

    public final void setOnVideoAudioBecomingNoisy(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.onVideoAudioBecomingNoisy = function0;
    }

    public final Function1<Boolean, Unit> getOnAudioFocusChanged() {
        Function1<? super Boolean, Unit> function1 = this.onAudioFocusChanged;
        if (function1 != null) {
            return function1;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onAudioFocusChanged");
        return null;
    }

    public final void setOnAudioFocusChanged(Function1<? super Boolean, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.onAudioFocusChanged = function1;
    }

    public final Function1<Float, Unit> getOnPlaybackRateChange() {
        Function1<? super Float, Unit> function1 = this.onPlaybackRateChange;
        if (function1 != null) {
            return function1;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onPlaybackRateChange");
        return null;
    }

    public final void setOnPlaybackRateChange(Function1<? super Float, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.onPlaybackRateChange = function1;
    }

    public final Function1<Float, Unit> getOnVolumeChange() {
        Function1<? super Float, Unit> function1 = this.onVolumeChange;
        if (function1 != null) {
            return function1;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onVolumeChange");
        return null;
    }

    public final void setOnVolumeChange(Function1<? super Float, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.onVolumeChange = function1;
    }

    public final Function1<ArrayList<Track>, Unit> getOnAudioTracks() {
        Function1<? super ArrayList<Track>, Unit> function1 = this.onAudioTracks;
        if (function1 != null) {
            return function1;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onAudioTracks");
        return null;
    }

    public final void setOnAudioTracks(Function1<? super ArrayList<Track>, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.onAudioTracks = function1;
    }

    public final Function1<ArrayList<Track>, Unit> getOnTextTracks() {
        Function1<? super ArrayList<Track>, Unit> function1 = this.onTextTracks;
        if (function1 != null) {
            return function1;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onTextTracks");
        return null;
    }

    public final void setOnTextTracks(Function1<? super ArrayList<Track>, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.onTextTracks = function1;
    }

    public final Function1<ArrayList<VideoTrack>, Unit> getOnVideoTracks() {
        Function1<? super ArrayList<VideoTrack>, Unit> function1 = this.onVideoTracks;
        if (function1 != null) {
            return function1;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onVideoTracks");
        return null;
    }

    public final void setOnVideoTracks(Function1<? super ArrayList<VideoTrack>, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.onVideoTracks = function1;
    }

    public final Function1<String, Unit> getOnTextTrackDataChanged() {
        Function1<? super String, Unit> function1 = this.onTextTrackDataChanged;
        if (function1 != null) {
            return function1;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onTextTrackDataChanged");
        return null;
    }

    public final void setOnTextTrackDataChanged(Function1<? super String, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.onTextTrackDataChanged = function1;
    }

    public final Function2<String, Map<String, String>, Unit> getOnReceiveAdEvent() {
        Function2<? super String, ? super Map<String, String>, Unit> function2 = this.onReceiveAdEvent;
        if (function2 != null) {
            return function2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onReceiveAdEvent");
        return null;
    }

    public final void setOnReceiveAdEvent(Function2<? super String, ? super Map<String, String>, Unit> function2) {
        Intrinsics.checkNotNullParameter(function2, "<set-?>");
        this.onReceiveAdEvent = function2;
    }

    public final Function1<Boolean, Unit> getOnPictureInPictureStatusChanged() {
        Function1<? super Boolean, Unit> function1 = this.onPictureInPictureStatusChanged;
        if (function1 != null) {
            return function1;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onPictureInPictureStatusChanged");
        return null;
    }

    public final void setOnPictureInPictureStatusChanged(Function1<? super Boolean, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.onPictureInPictureStatusChanged = function1;
    }

    public final void addEventEmitters(ThemedReactContext themedReactContext, ReactExoplayerView reactExoplayerView) {
        Intrinsics.checkNotNullParameter(themedReactContext, "reactContext");
        Intrinsics.checkNotNullParameter(reactExoplayerView, "view");
        EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag(themedReactContext, reactExoplayerView.getId());
        int surfaceId = UIManagerHelper.getSurfaceId((Context) themedReactContext);
        if (eventDispatcherForReactTag != null) {
            EventBuilder eventBuilder = new EventBuilder(surfaceId, reactExoplayerView.getId(), eventDispatcherForReactTag);
            setOnVideoLoadStart(new VideoEventEmitter$addEventEmitters$1(eventBuilder));
            setOnVideoLoad(new VideoEventEmitter$addEventEmitters$2(eventBuilder, this));
            setOnVideoError(new VideoEventEmitter$addEventEmitters$3(eventBuilder));
            setOnVideoProgress(new VideoEventEmitter$addEventEmitters$4(eventBuilder));
            setOnVideoBandwidthUpdate(new VideoEventEmitter$addEventEmitters$5(eventBuilder));
            setOnVideoPlaybackStateChanged(new VideoEventEmitter$addEventEmitters$6(eventBuilder));
            setOnVideoSeek(new VideoEventEmitter$addEventEmitters$7(eventBuilder));
            setOnVideoEnd(new VideoEventEmitter$addEventEmitters$8(eventBuilder));
            setOnVideoFullscreenPlayerWillPresent(new VideoEventEmitter$addEventEmitters$9(eventBuilder));
            setOnVideoFullscreenPlayerDidPresent(new VideoEventEmitter$addEventEmitters$10(eventBuilder));
            setOnVideoFullscreenPlayerWillDismiss(new VideoEventEmitter$addEventEmitters$11(eventBuilder));
            setOnVideoFullscreenPlayerDidDismiss(new VideoEventEmitter$addEventEmitters$12(eventBuilder));
            setOnReadyForDisplay(new VideoEventEmitter$addEventEmitters$13(eventBuilder));
            setOnVideoBuffer(new VideoEventEmitter$addEventEmitters$14(eventBuilder));
            setOnControlsVisibilityChange(new VideoEventEmitter$addEventEmitters$15(eventBuilder));
            setOnVideoIdle(new VideoEventEmitter$addEventEmitters$16(eventBuilder));
            setOnTimedMetadata(new VideoEventEmitter$addEventEmitters$17(eventBuilder));
            setOnVideoAudioBecomingNoisy(new VideoEventEmitter$addEventEmitters$18(eventBuilder));
            setOnAudioFocusChanged(new VideoEventEmitter$addEventEmitters$19(eventBuilder));
            setOnPlaybackRateChange(new VideoEventEmitter$addEventEmitters$20(eventBuilder));
            setOnVolumeChange(new VideoEventEmitter$addEventEmitters$21(eventBuilder));
            setOnAudioTracks(new VideoEventEmitter$addEventEmitters$22(eventBuilder, this));
            setOnTextTracks(new VideoEventEmitter$addEventEmitters$23(eventBuilder, this));
            setOnVideoTracks(new VideoEventEmitter$addEventEmitters$24(eventBuilder, this));
            setOnTextTrackDataChanged(new VideoEventEmitter$addEventEmitters$25(eventBuilder));
            setOnReceiveAdEvent(new VideoEventEmitter$addEventEmitters$26(eventBuilder));
            setOnPictureInPictureStatusChanged(new VideoEventEmitter$addEventEmitters$27(eventBuilder));
        }
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J+\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u001b\b\u0002\u0010\f\u001a\u0015\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\t\u0018\u00010\r¢\u0006\u0002\b\u000fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/brentvatne/common/react/VideoEventEmitter$EventBuilder;", "", "surfaceId", "", "viewId", "dispatcher", "Lcom/facebook/react/uimanager/events/EventDispatcher;", "(IILcom/facebook/react/uimanager/events/EventDispatcher;)V", "dispatch", "", "event", "Lcom/brentvatne/common/react/EventTypes;", "paramsSetter", "Lkotlin/Function1;", "Lcom/facebook/react/bridge/WritableMap;", "Lkotlin/ExtensionFunctionType;", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: VideoEventEmitter.kt */
    private static final class EventBuilder {
        private final EventDispatcher dispatcher;
        private final int surfaceId;
        private final int viewId;

        public EventBuilder(int i, int i2, EventDispatcher eventDispatcher) {
            Intrinsics.checkNotNullParameter(eventDispatcher, "dispatcher");
            this.surfaceId = i;
            this.viewId = i2;
            this.dispatcher = eventDispatcher;
        }

        public static /* synthetic */ void dispatch$default(EventBuilder eventBuilder, EventTypes eventTypes, Function1 function1, int i, Object obj) {
            if ((i & 2) != 0) {
                function1 = null;
            }
            eventBuilder.dispatch(eventTypes, function1);
        }

        public final void dispatch(EventTypes eventTypes, Function1<? super WritableMap, Unit> function1) {
            Intrinsics.checkNotNullParameter(eventTypes, NotificationCompat.CATEGORY_EVENT);
            this.dispatcher.dispatchEvent(new VideoEventEmitter$EventBuilder$dispatch$1(eventTypes, function1, this.surfaceId, this.viewId));
        }
    }

    /* access modifiers changed from: private */
    public final WritableArray audioTracksToArray(ArrayList<Track> arrayList) {
        WritableArray createArray = Arguments.createArray();
        if (arrayList != null) {
            int i = 0;
            for (Object next : arrayList) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                Track track = (Track) next;
                WritableMap createMap = Arguments.createMap();
                createMap.putInt(FirebaseAnalytics.Param.INDEX, i);
                createMap.putString("title", track.getTitle());
                String mimeType = track.getMimeType();
                if (mimeType != null) {
                    createMap.putString("type", mimeType);
                }
                String language = track.getLanguage();
                if (language != null) {
                    createMap.putString("language", language);
                }
                if (track.getBitrate() > 0) {
                    createMap.putInt("bitrate", track.getBitrate());
                }
                createMap.putBoolean("selected", track.isSelected());
                createArray.pushMap(createMap);
                i = i2;
            }
        }
        Intrinsics.checkNotNullExpressionValue(createArray, "apply(...)");
        return createArray;
    }

    /* access modifiers changed from: private */
    public final WritableArray videoTracksToArray(ArrayList<VideoTrack> arrayList) {
        WritableArray createArray = Arguments.createArray();
        if (arrayList != null) {
            int i = 0;
            for (Object next : arrayList) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                VideoTrack videoTrack = (VideoTrack) next;
                WritableMap createMap = Arguments.createMap();
                createMap.putInt("width", videoTrack.getWidth());
                createMap.putInt("height", videoTrack.getHeight());
                createMap.putInt("bitrate", videoTrack.getBitrate());
                createMap.putString("codecs", videoTrack.getCodecs());
                createMap.putString("trackId", videoTrack.getTrackId());
                createMap.putInt(FirebaseAnalytics.Param.INDEX, videoTrack.getIndex());
                createMap.putBoolean("selected", videoTrack.isSelected());
                createMap.putInt("rotation", videoTrack.getRotation());
                createArray.pushMap(createMap);
                i = i2;
            }
        }
        Intrinsics.checkNotNullExpressionValue(createArray, "apply(...)");
        return createArray;
    }

    /* access modifiers changed from: private */
    public final WritableArray textTracksToArray(ArrayList<Track> arrayList) {
        WritableArray createArray = Arguments.createArray();
        if (arrayList != null) {
            int i = 0;
            for (Object next : arrayList) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                Track track = (Track) next;
                WritableMap createMap = Arguments.createMap();
                createMap.putInt(FirebaseAnalytics.Param.INDEX, i);
                createMap.putString("title", track.getTitle());
                createMap.putString("type", track.getMimeType());
                createMap.putString("language", track.getLanguage());
                createMap.putBoolean("selected", track.isSelected());
                createArray.pushMap(createMap);
                i = i2;
            }
        }
        Intrinsics.checkNotNullExpressionValue(createArray, "apply(...)");
        return createArray;
    }

    /* access modifiers changed from: private */
    public final WritableMap aspectRatioToNaturalSize(int i, int i2) {
        WritableMap createMap = Arguments.createMap();
        if (i > 0) {
            createMap.putInt("width", i);
        }
        if (i2 > 0) {
            createMap.putInt("height", i2);
        }
        createMap.putString(Device.JsonKeys.ORIENTATION, i > i2 ? DevLauncherOrientation.LANDSCAPE : i < i2 ? DevLauncherOrientation.PORTRAIT : "square");
        Intrinsics.checkNotNullExpressionValue(createMap, "apply(...)");
        return createMap;
    }
}

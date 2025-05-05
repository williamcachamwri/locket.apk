package androidx.media3.exoplayer;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.media.AudioDeviceCallback;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.media.MediaFormat;
import android.media.metrics.LogSessionId;
import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.AuxEffectInfo;
import androidx.media3.common.BasePlayer;
import androidx.media3.common.C;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.Effect;
import androidx.media3.common.FlagSet;
import androidx.media3.common.Format;
import androidx.media3.common.IllegalSeekPositionException;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Metadata;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.PriorityTaskManager;
import androidx.media3.common.SimpleBasePlayer$$ExternalSyntheticLambda23;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoFrameProcessor;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.Cue;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.ConditionVariable;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.AudioBecomingNoisyManager;
import androidx.media3.exoplayer.AudioFocusManager;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.ExoPlayerImplInternal;
import androidx.media3.exoplayer.MediaSourceList;
import androidx.media3.exoplayer.PlayerMessage;
import androidx.media3.exoplayer.StreamVolumeManager;
import androidx.media3.exoplayer.analytics.AnalyticsCollector;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.analytics.DefaultAnalyticsCollector;
import androidx.media3.exoplayer.analytics.MediaMetricsListener;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.audio.AudioRendererEventListener;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.image.ImageOutput;
import androidx.media3.exoplayer.metadata.MetadataOutput;
import androidx.media3.exoplayer.source.MaskingMediaSource;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.ShuffleOrder;
import androidx.media3.exoplayer.source.TimelineWithUpdatedMediaItem;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.text.TextOutput;
import androidx.media3.exoplayer.trackselection.TrackSelectionArray;
import androidx.media3.exoplayer.trackselection.TrackSelector;
import androidx.media3.exoplayer.trackselection.TrackSelectorResult;
import androidx.media3.exoplayer.upstream.BandwidthMeter;
import androidx.media3.exoplayer.video.VideoDecoderOutputBufferRenderer;
import androidx.media3.exoplayer.video.VideoFrameMetadataListener;
import androidx.media3.exoplayer.video.VideoRendererEventListener;
import androidx.media3.exoplayer.video.spherical.CameraMotionListener;
import androidx.media3.exoplayer.video.spherical.SphericalGLSurfaceView;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeoutException;

final class ExoPlayerImpl extends BasePlayer implements ExoPlayer, ExoPlayer.AudioComponent, ExoPlayer.VideoComponent, ExoPlayer.TextComponent, ExoPlayer.DeviceComponent {
    private static final String TAG = "ExoPlayerImpl";
    /* access modifiers changed from: private */
    public final AnalyticsCollector analyticsCollector;
    private final Context applicationContext;
    private final Looper applicationLooper;
    private AudioAttributes audioAttributes;
    private final AudioBecomingNoisyManager audioBecomingNoisyManager;
    /* access modifiers changed from: private */
    public DecoderCounters audioDecoderCounters;
    private final AudioFocusManager audioFocusManager;
    /* access modifiers changed from: private */
    public Format audioFormat;
    private AudioManager audioManager;
    private final CopyOnWriteArraySet<ExoPlayer.AudioOffloadListener> audioOffloadListeners;
    private int audioSessionId;
    private Player.Commands availableCommands;
    private final BandwidthMeter bandwidthMeter;
    private CameraMotionListener cameraMotionListener;
    private final Clock clock;
    private final ComponentListener componentListener;
    private final ConditionVariable constructorFinished;
    /* access modifiers changed from: private */
    public CueGroup currentCueGroup;
    private final long detachSurfaceTimeoutMs;
    /* access modifiers changed from: private */
    public DeviceInfo deviceInfo;
    final TrackSelectorResult emptyTrackSelectorResult;
    private boolean foregroundMode;
    private final FrameMetadataListener frameMetadataListener;
    private boolean hasNotifiedFullWrongThreadWarning;
    private final ExoPlayerImplInternal internalPlayer;
    private boolean isPriorityTaskManagerRegistered;
    /* access modifiers changed from: private */
    public final ListenerSet<Player.Listener> listeners;
    private int maskingPeriodIndex;
    private int maskingWindowIndex;
    private long maskingWindowPositionMs;
    private final long maxSeekToPreviousPositionMs;
    /* access modifiers changed from: private */
    public MediaMetadata mediaMetadata;
    private final MediaSource.Factory mediaSourceFactory;
    private final List<MediaSourceHolderSnapshot> mediaSourceHolderSnapshots;
    private Surface ownedSurface;
    private boolean pauseAtEndOfMediaItems;
    private boolean pendingDiscontinuity;
    private int pendingDiscontinuityReason;
    private int pendingOperationAcks;
    private final Timeline.Period period;
    final Player.Commands permanentAvailableCommands;
    /* access modifiers changed from: private */
    public PlaybackInfo playbackInfo;
    private final HandlerWrapper playbackInfoUpdateHandler;
    private final ExoPlayerImplInternal.PlaybackInfoUpdateListener playbackInfoUpdateListener;
    private boolean playerReleased;
    private MediaMetadata playlistMetadata;
    private ExoPlayer.PreloadConfiguration preloadConfiguration;
    private int priority;
    private PriorityTaskManager priorityTaskManager;
    private final Renderer[] renderers;
    private int repeatMode;
    private final long seekBackIncrementMs;
    private final long seekForwardIncrementMs;
    private SeekParameters seekParameters;
    private boolean shuffleModeEnabled;
    private ShuffleOrder shuffleOrder;
    /* access modifiers changed from: private */
    public boolean skipSilenceEnabled;
    private SphericalGLSurfaceView sphericalGLSurfaceView;
    /* access modifiers changed from: private */
    public MediaMetadata staticAndDynamicMediaMetadata;
    /* access modifiers changed from: private */
    public final StreamVolumeManager streamVolumeManager;
    private final SuitableOutputChecker suitableOutputChecker;
    private final boolean suppressPlaybackOnUnsuitableOutput;
    private SurfaceHolder surfaceHolder;
    /* access modifiers changed from: private */
    public boolean surfaceHolderSurfaceIsVideoOutput;
    private Size surfaceSize;
    private TextureView textureView;
    private boolean throwsWhenUsingWrongThread;
    private final TrackSelector trackSelector;
    private final boolean useLazyPreparation;
    private int videoChangeFrameRateStrategy;
    /* access modifiers changed from: private */
    public DecoderCounters videoDecoderCounters;
    /* access modifiers changed from: private */
    public Format videoFormat;
    private VideoFrameMetadataListener videoFrameMetadataListener;
    /* access modifiers changed from: private */
    public Object videoOutput;
    private int videoScalingMode;
    /* access modifiers changed from: private */
    public VideoSize videoSize;
    private float volume;
    private final WakeLockManager wakeLockManager;
    private final WifiLockManager wifiLockManager;
    private final Player wrappingPlayer;

    /* access modifiers changed from: private */
    public static int getPlayWhenReadyChangeReason(int i) {
        return i == -1 ? 2 : 1;
    }

    static {
        MediaLibraryInfo.registerModule("media3.exoplayer");
    }

    /* JADX WARNING: type inference failed for: r46v0, types: [androidx.media3.common.Player] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x02d5 A[Catch:{ all -> 0x037b }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x02e7 A[Catch:{ all -> 0x037b }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x02f6 A[Catch:{ all -> 0x037b }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x02f8 A[Catch:{ all -> 0x037b }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x030a A[Catch:{ all -> 0x037b }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x030c A[Catch:{ all -> 0x037b }] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ExoPlayerImpl(androidx.media3.exoplayer.ExoPlayer.Builder r45, androidx.media3.common.Player r46) {
        /*
            r44 = this;
            r1 = r44
            r0 = r45
            java.lang.String r2 = " [AndroidXMedia3/1.5.1] ["
            java.lang.String r3 = "Init "
            r44.<init>()
            androidx.media3.common.util.ConditionVariable r4 = new androidx.media3.common.util.ConditionVariable
            r4.<init>()
            r1.constructorFinished = r4
            java.lang.String r5 = "ExoPlayerImpl"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x037b }
            r6.<init>(r3)     // Catch:{ all -> 0x037b }
            int r3 = java.lang.System.identityHashCode(r44)     // Catch:{ all -> 0x037b }
            java.lang.String r3 = java.lang.Integer.toHexString(r3)     // Catch:{ all -> 0x037b }
            java.lang.StringBuilder r3 = r6.append(r3)     // Catch:{ all -> 0x037b }
            java.lang.StringBuilder r2 = r3.append(r2)     // Catch:{ all -> 0x037b }
            java.lang.String r3 = androidx.media3.common.util.Util.DEVICE_DEBUG_INFO     // Catch:{ all -> 0x037b }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x037b }
            java.lang.String r3 = "]"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x037b }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x037b }
            androidx.media3.common.util.Log.i(r5, r2)     // Catch:{ all -> 0x037b }
            android.content.Context r2 = r0.context     // Catch:{ all -> 0x037b }
            android.content.Context r2 = r2.getApplicationContext()     // Catch:{ all -> 0x037b }
            r1.applicationContext = r2     // Catch:{ all -> 0x037b }
            com.google.common.base.Function<androidx.media3.common.util.Clock, androidx.media3.exoplayer.analytics.AnalyticsCollector> r3 = r0.analyticsCollectorFunction     // Catch:{ all -> 0x037b }
            androidx.media3.common.util.Clock r5 = r0.clock     // Catch:{ all -> 0x037b }
            java.lang.Object r3 = r3.apply(r5)     // Catch:{ all -> 0x037b }
            androidx.media3.exoplayer.analytics.AnalyticsCollector r3 = (androidx.media3.exoplayer.analytics.AnalyticsCollector) r3     // Catch:{ all -> 0x037b }
            r1.analyticsCollector = r3     // Catch:{ all -> 0x037b }
            int r5 = r0.priority     // Catch:{ all -> 0x037b }
            r1.priority = r5     // Catch:{ all -> 0x037b }
            androidx.media3.common.PriorityTaskManager r5 = r0.priorityTaskManager     // Catch:{ all -> 0x037b }
            r1.priorityTaskManager = r5     // Catch:{ all -> 0x037b }
            androidx.media3.common.AudioAttributes r5 = r0.audioAttributes     // Catch:{ all -> 0x037b }
            r1.audioAttributes = r5     // Catch:{ all -> 0x037b }
            int r5 = r0.videoScalingMode     // Catch:{ all -> 0x037b }
            r1.videoScalingMode = r5     // Catch:{ all -> 0x037b }
            int r5 = r0.videoChangeFrameRateStrategy     // Catch:{ all -> 0x037b }
            r1.videoChangeFrameRateStrategy = r5     // Catch:{ all -> 0x037b }
            boolean r5 = r0.skipSilenceEnabled     // Catch:{ all -> 0x037b }
            r1.skipSilenceEnabled = r5     // Catch:{ all -> 0x037b }
            long r5 = r0.detachSurfaceTimeoutMs     // Catch:{ all -> 0x037b }
            r1.detachSurfaceTimeoutMs = r5     // Catch:{ all -> 0x037b }
            androidx.media3.exoplayer.ExoPlayerImpl$ComponentListener r15 = new androidx.media3.exoplayer.ExoPlayerImpl$ComponentListener     // Catch:{ all -> 0x037b }
            r14 = 0
            r15.<init>()     // Catch:{ all -> 0x037b }
            r1.componentListener = r15     // Catch:{ all -> 0x037b }
            androidx.media3.exoplayer.ExoPlayerImpl$FrameMetadataListener r13 = new androidx.media3.exoplayer.ExoPlayerImpl$FrameMetadataListener     // Catch:{ all -> 0x037b }
            r13.<init>()     // Catch:{ all -> 0x037b }
            r1.frameMetadataListener = r13     // Catch:{ all -> 0x037b }
            android.os.Handler r6 = new android.os.Handler     // Catch:{ all -> 0x037b }
            android.os.Looper r5 = r0.looper     // Catch:{ all -> 0x037b }
            r6.<init>(r5)     // Catch:{ all -> 0x037b }
            com.google.common.base.Supplier<androidx.media3.exoplayer.RenderersFactory> r5 = r0.renderersFactorySupplier     // Catch:{ all -> 0x037b }
            java.lang.Object r5 = r5.get()     // Catch:{ all -> 0x037b }
            r7 = r5
            androidx.media3.exoplayer.RenderersFactory r7 = (androidx.media3.exoplayer.RenderersFactory) r7     // Catch:{ all -> 0x037b }
            r8 = r6
            r9 = r15
            r10 = r15
            r11 = r15
            r12 = r15
            androidx.media3.exoplayer.Renderer[] r7 = r7.createRenderers(r8, r9, r10, r11, r12)     // Catch:{ all -> 0x037b }
            r1.renderers = r7     // Catch:{ all -> 0x037b }
            int r5 = r7.length     // Catch:{ all -> 0x037b }
            if (r5 <= 0) goto L_0x009b
            r5 = 1
            goto L_0x009c
        L_0x009b:
            r5 = 0
        L_0x009c:
            androidx.media3.common.util.Assertions.checkState(r5)     // Catch:{ all -> 0x037b }
            com.google.common.base.Supplier<androidx.media3.exoplayer.trackselection.TrackSelector> r5 = r0.trackSelectorSupplier     // Catch:{ all -> 0x037b }
            java.lang.Object r5 = r5.get()     // Catch:{ all -> 0x037b }
            r10 = r5
            androidx.media3.exoplayer.trackselection.TrackSelector r10 = (androidx.media3.exoplayer.trackselection.TrackSelector) r10     // Catch:{ all -> 0x037b }
            r1.trackSelector = r10     // Catch:{ all -> 0x037b }
            com.google.common.base.Supplier<androidx.media3.exoplayer.source.MediaSource$Factory> r5 = r0.mediaSourceFactorySupplier     // Catch:{ all -> 0x037b }
            java.lang.Object r5 = r5.get()     // Catch:{ all -> 0x037b }
            androidx.media3.exoplayer.source.MediaSource$Factory r5 = (androidx.media3.exoplayer.source.MediaSource.Factory) r5     // Catch:{ all -> 0x037b }
            r1.mediaSourceFactory = r5     // Catch:{ all -> 0x037b }
            com.google.common.base.Supplier<androidx.media3.exoplayer.upstream.BandwidthMeter> r5 = r0.bandwidthMeterSupplier     // Catch:{ all -> 0x037b }
            java.lang.Object r5 = r5.get()     // Catch:{ all -> 0x037b }
            r9 = r5
            androidx.media3.exoplayer.upstream.BandwidthMeter r9 = (androidx.media3.exoplayer.upstream.BandwidthMeter) r9     // Catch:{ all -> 0x037b }
            r1.bandwidthMeter = r9     // Catch:{ all -> 0x037b }
            boolean r5 = r0.useLazyPreparation     // Catch:{ all -> 0x037b }
            r1.useLazyPreparation = r5     // Catch:{ all -> 0x037b }
            androidx.media3.exoplayer.SeekParameters r5 = r0.seekParameters     // Catch:{ all -> 0x037b }
            r1.seekParameters = r5     // Catch:{ all -> 0x037b }
            r16 = r15
            long r14 = r0.seekBackIncrementMs     // Catch:{ all -> 0x037b }
            r1.seekBackIncrementMs = r14     // Catch:{ all -> 0x037b }
            long r14 = r0.seekForwardIncrementMs     // Catch:{ all -> 0x037b }
            r1.seekForwardIncrementMs = r14     // Catch:{ all -> 0x037b }
            long r14 = r0.maxSeekToPreviousPositionMs     // Catch:{ all -> 0x037b }
            r1.maxSeekToPreviousPositionMs = r14     // Catch:{ all -> 0x037b }
            boolean r5 = r0.pauseAtEndOfMediaItems     // Catch:{ all -> 0x037b }
            r1.pauseAtEndOfMediaItems = r5     // Catch:{ all -> 0x037b }
            android.os.Looper r15 = r0.looper     // Catch:{ all -> 0x037b }
            r1.applicationLooper = r15     // Catch:{ all -> 0x037b }
            androidx.media3.common.util.Clock r14 = r0.clock     // Catch:{ all -> 0x037b }
            r1.clock = r14     // Catch:{ all -> 0x037b }
            if (r46 != 0) goto L_0x00e5
            r5 = r1
            goto L_0x00e7
        L_0x00e5:
            r5 = r46
        L_0x00e7:
            r1.wrappingPlayer = r5     // Catch:{ all -> 0x037b }
            boolean r8 = r0.suppressPlaybackOnUnsuitableOutput     // Catch:{ all -> 0x037b }
            r1.suppressPlaybackOnUnsuitableOutput = r8     // Catch:{ all -> 0x037b }
            androidx.media3.common.util.ListenerSet r11 = new androidx.media3.common.util.ListenerSet     // Catch:{ all -> 0x037b }
            androidx.media3.exoplayer.ExoPlayerImpl$$ExternalSyntheticLambda25 r12 = new androidx.media3.exoplayer.ExoPlayerImpl$$ExternalSyntheticLambda25     // Catch:{ all -> 0x037b }
            r12.<init>(r1)     // Catch:{ all -> 0x037b }
            r11.<init>(r15, r14, r12)     // Catch:{ all -> 0x037b }
            r1.listeners = r11     // Catch:{ all -> 0x037b }
            java.util.concurrent.CopyOnWriteArraySet r11 = new java.util.concurrent.CopyOnWriteArraySet     // Catch:{ all -> 0x037b }
            r11.<init>()     // Catch:{ all -> 0x037b }
            r1.audioOffloadListeners = r11     // Catch:{ all -> 0x037b }
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch:{ all -> 0x037b }
            r11.<init>()     // Catch:{ all -> 0x037b }
            r1.mediaSourceHolderSnapshots = r11     // Catch:{ all -> 0x037b }
            androidx.media3.exoplayer.source.ShuffleOrder$DefaultShuffleOrder r11 = new androidx.media3.exoplayer.source.ShuffleOrder$DefaultShuffleOrder     // Catch:{ all -> 0x037b }
            r12 = 0
            r11.<init>(r12)     // Catch:{ all -> 0x037b }
            r1.shuffleOrder = r11     // Catch:{ all -> 0x037b }
            androidx.media3.exoplayer.ExoPlayer$PreloadConfiguration r11 = androidx.media3.exoplayer.ExoPlayer.PreloadConfiguration.DEFAULT     // Catch:{ all -> 0x037b }
            r1.preloadConfiguration = r11     // Catch:{ all -> 0x037b }
            androidx.media3.exoplayer.trackselection.TrackSelectorResult r11 = new androidx.media3.exoplayer.trackselection.TrackSelectorResult     // Catch:{ all -> 0x037b }
            int r12 = r7.length     // Catch:{ all -> 0x037b }
            androidx.media3.exoplayer.RendererConfiguration[] r12 = new androidx.media3.exoplayer.RendererConfiguration[r12]     // Catch:{ all -> 0x037b }
            r20 = r6
            int r6 = r7.length     // Catch:{ all -> 0x037b }
            androidx.media3.exoplayer.trackselection.ExoTrackSelection[] r6 = new androidx.media3.exoplayer.trackselection.ExoTrackSelection[r6]     // Catch:{ all -> 0x037b }
            r21 = r8
            androidx.media3.common.Tracks r8 = androidx.media3.common.Tracks.EMPTY     // Catch:{ all -> 0x037b }
            r22 = r9
            r9 = 0
            r11.<init>(r12, r6, r8, r9)     // Catch:{ all -> 0x037b }
            r1.emptyTrackSelectorResult = r11     // Catch:{ all -> 0x037b }
            androidx.media3.common.Timeline$Period r6 = new androidx.media3.common.Timeline$Period     // Catch:{ all -> 0x037b }
            r6.<init>()     // Catch:{ all -> 0x037b }
            r1.period = r6     // Catch:{ all -> 0x037b }
            androidx.media3.common.Player$Commands$Builder r6 = new androidx.media3.common.Player$Commands$Builder     // Catch:{ all -> 0x037b }
            r6.<init>()     // Catch:{ all -> 0x037b }
            r8 = 20
            int[] r8 = new int[r8]     // Catch:{ all -> 0x037b }
            r8 = {1, 2, 3, 13, 14, 15, 16, 17, 18, 19, 31, 20, 30, 21, 35, 22, 24, 27, 28, 32} // fill-array     // Catch:{ all -> 0x037b }
            androidx.media3.common.Player$Commands$Builder r6 = r6.addAll((int[]) r8)     // Catch:{ all -> 0x037b }
            boolean r8 = r10.isSetParametersSupported()     // Catch:{ all -> 0x037b }
            r9 = 29
            androidx.media3.common.Player$Commands$Builder r6 = r6.addIf(r9, r8)     // Catch:{ all -> 0x037b }
            boolean r8 = r0.deviceVolumeControlEnabled     // Catch:{ all -> 0x037b }
            r12 = 23
            androidx.media3.common.Player$Commands$Builder r6 = r6.addIf(r12, r8)     // Catch:{ all -> 0x037b }
            boolean r8 = r0.deviceVolumeControlEnabled     // Catch:{ all -> 0x037b }
            r9 = 25
            androidx.media3.common.Player$Commands$Builder r6 = r6.addIf(r9, r8)     // Catch:{ all -> 0x037b }
            boolean r8 = r0.deviceVolumeControlEnabled     // Catch:{ all -> 0x037b }
            r9 = 33
            androidx.media3.common.Player$Commands$Builder r6 = r6.addIf(r9, r8)     // Catch:{ all -> 0x037b }
            boolean r8 = r0.deviceVolumeControlEnabled     // Catch:{ all -> 0x037b }
            r9 = 26
            androidx.media3.common.Player$Commands$Builder r6 = r6.addIf(r9, r8)     // Catch:{ all -> 0x037b }
            boolean r8 = r0.deviceVolumeControlEnabled     // Catch:{ all -> 0x037b }
            r9 = 34
            androidx.media3.common.Player$Commands$Builder r6 = r6.addIf(r9, r8)     // Catch:{ all -> 0x037b }
            androidx.media3.common.Player$Commands r6 = r6.build()     // Catch:{ all -> 0x037b }
            r1.permanentAvailableCommands = r6     // Catch:{ all -> 0x037b }
            androidx.media3.common.Player$Commands$Builder r8 = new androidx.media3.common.Player$Commands$Builder     // Catch:{ all -> 0x037b }
            r8.<init>()     // Catch:{ all -> 0x037b }
            androidx.media3.common.Player$Commands$Builder r6 = r8.addAll((androidx.media3.common.Player.Commands) r6)     // Catch:{ all -> 0x037b }
            r9 = 4
            androidx.media3.common.Player$Commands$Builder r6 = r6.add(r9)     // Catch:{ all -> 0x037b }
            r8 = 10
            androidx.media3.common.Player$Commands$Builder r6 = r6.add(r8)     // Catch:{ all -> 0x037b }
            androidx.media3.common.Player$Commands r6 = r6.build()     // Catch:{ all -> 0x037b }
            r1.availableCommands = r6     // Catch:{ all -> 0x037b }
            r6 = 0
            androidx.media3.common.util.HandlerWrapper r8 = r14.createHandler(r15, r6)     // Catch:{ all -> 0x037b }
            r1.playbackInfoUpdateHandler = r8     // Catch:{ all -> 0x037b }
            androidx.media3.exoplayer.ExoPlayerImpl$$ExternalSyntheticLambda26 r8 = new androidx.media3.exoplayer.ExoPlayerImpl$$ExternalSyntheticLambda26     // Catch:{ all -> 0x037b }
            r8.<init>(r1)     // Catch:{ all -> 0x037b }
            r1.playbackInfoUpdateListener = r8     // Catch:{ all -> 0x037b }
            androidx.media3.exoplayer.PlaybackInfo r6 = androidx.media3.exoplayer.PlaybackInfo.createDummy(r11)     // Catch:{ all -> 0x037b }
            r1.playbackInfo = r6     // Catch:{ all -> 0x037b }
            r3.setPlayer(r5, r15)     // Catch:{ all -> 0x037b }
            int r5 = androidx.media3.common.util.Util.SDK_INT     // Catch:{ all -> 0x037b }
            r6 = 31
            if (r5 >= r6) goto L_0x01b7
            androidx.media3.exoplayer.analytics.PlayerId r5 = new androidx.media3.exoplayer.analytics.PlayerId     // Catch:{ all -> 0x037b }
            java.lang.String r6 = r0.playerName     // Catch:{ all -> 0x037b }
            r5.<init>(r6)     // Catch:{ all -> 0x037b }
            goto L_0x01bf
        L_0x01b7:
            boolean r5 = r0.usePlatformDiagnostics     // Catch:{ all -> 0x037b }
            java.lang.String r6 = r0.playerName     // Catch:{ all -> 0x037b }
            androidx.media3.exoplayer.analytics.PlayerId r5 = androidx.media3.exoplayer.ExoPlayerImpl.Api31.registerMediaMetricsListener(r2, r1, r5, r6)     // Catch:{ all -> 0x037b }
        L_0x01bf:
            r23 = r5
            androidx.media3.exoplayer.ExoPlayerImplInternal r6 = new androidx.media3.exoplayer.ExoPlayerImplInternal     // Catch:{ all -> 0x037b }
            com.google.common.base.Supplier<androidx.media3.exoplayer.LoadControl> r5 = r0.loadControlSupplier     // Catch:{ all -> 0x037b }
            java.lang.Object r5 = r5.get()     // Catch:{ all -> 0x037b }
            r24 = r5
            androidx.media3.exoplayer.LoadControl r24 = (androidx.media3.exoplayer.LoadControl) r24     // Catch:{ all -> 0x037b }
            int r5 = r1.repeatMode     // Catch:{ all -> 0x037b }
            boolean r12 = r1.shuffleModeEnabled     // Catch:{ all -> 0x037b }
            r26 = r14
            androidx.media3.exoplayer.SeekParameters r14 = r1.seekParameters     // Catch:{ all -> 0x037b }
            r27 = r15
            androidx.media3.exoplayer.LivePlaybackSpeedControl r15 = r0.livePlaybackSpeedControl     // Catch:{ all -> 0x037b }
            r28 = r14
            r29 = r15
            long r14 = r0.releaseTimeoutMs     // Catch:{ all -> 0x037b }
            r30 = r4
            boolean r4 = r1.pauseAtEndOfMediaItems     // Catch:{ all -> 0x037b }
            r31 = r2
            boolean r2 = r0.dynamicSchedulingEnabled     // Catch:{ all -> 0x037b }
            r32 = r2
            androidx.media3.exoplayer.PlaybackLooperProvider r2 = r0.playbackLooperProvider     // Catch:{ all -> 0x037b }
            androidx.media3.exoplayer.ExoPlayer$PreloadConfiguration r0 = r1.preloadConfiguration     // Catch:{ all -> 0x037b }
            r33 = r5
            r5 = r6
            r35 = r6
            r34 = r20
            r17 = 0
            r6 = r7
            r7 = r10
            r37 = r8
            r36 = r21
            r8 = r11
            r11 = r22
            r9 = r24
            r38 = r10
            r10 = r11
            r39 = r11
            r11 = r33
            r40 = r13
            r13 = r3
            r21 = r26
            r42 = r14
            r15 = r17
            r17 = r42
            r14 = r28
            r41 = r16
            r15 = r29
            r16 = r17
            r18 = r4
            r19 = r32
            r20 = r27
            r22 = r37
            r24 = r2
            r25 = r0
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r18, r19, r20, r21, r22, r23, r24, r25)     // Catch:{ all -> 0x037b }
            r0 = r35
            r1.internalPlayer = r0     // Catch:{ all -> 0x037b }
            r2 = 1065353216(0x3f800000, float:1.0)
            r1.volume = r2     // Catch:{ all -> 0x037b }
            r2 = 0
            r1.repeatMode = r2     // Catch:{ all -> 0x037b }
            androidx.media3.common.MediaMetadata r4 = androidx.media3.common.MediaMetadata.EMPTY     // Catch:{ all -> 0x037b }
            r1.mediaMetadata = r4     // Catch:{ all -> 0x037b }
            androidx.media3.common.MediaMetadata r4 = androidx.media3.common.MediaMetadata.EMPTY     // Catch:{ all -> 0x037b }
            r1.playlistMetadata = r4     // Catch:{ all -> 0x037b }
            androidx.media3.common.MediaMetadata r4 = androidx.media3.common.MediaMetadata.EMPTY     // Catch:{ all -> 0x037b }
            r1.staticAndDynamicMediaMetadata = r4     // Catch:{ all -> 0x037b }
            r4 = -1
            r1.maskingWindowIndex = r4     // Catch:{ all -> 0x037b }
            int r4 = androidx.media3.common.util.Util.generateAudioSessionIdV21(r31)     // Catch:{ all -> 0x037b }
            r1.audioSessionId = r4     // Catch:{ all -> 0x037b }
            androidx.media3.common.text.CueGroup r4 = androidx.media3.common.text.CueGroup.EMPTY_TIME_ZERO     // Catch:{ all -> 0x037b }
            r1.currentCueGroup = r4     // Catch:{ all -> 0x037b }
            r4 = 1
            r1.throwsWhenUsingWrongThread = r4     // Catch:{ all -> 0x037b }
            r1.addListener(r3)     // Catch:{ all -> 0x037b }
            android.os.Handler r5 = new android.os.Handler     // Catch:{ all -> 0x037b }
            r6 = r27
            r5.<init>(r6)     // Catch:{ all -> 0x037b }
            r7 = r39
            r7.addEventListener(r5, r3)     // Catch:{ all -> 0x037b }
            r3 = r41
            r1.addAudioOffloadListener(r3)     // Catch:{ all -> 0x037b }
            r5 = r45
            long r7 = r5.foregroundModeTimeoutMs     // Catch:{ all -> 0x037b }
            r9 = 0
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 <= 0) goto L_0x0274
            long r7 = r5.foregroundModeTimeoutMs     // Catch:{ all -> 0x037b }
            r0.experimentalSetForegroundModeTimeoutMs(r7)     // Catch:{ all -> 0x037b }
        L_0x0274:
            androidx.media3.exoplayer.AudioBecomingNoisyManager r0 = new androidx.media3.exoplayer.AudioBecomingNoisyManager     // Catch:{ all -> 0x037b }
            android.content.Context r7 = r5.context     // Catch:{ all -> 0x037b }
            r8 = r34
            r0.<init>(r7, r8, r3)     // Catch:{ all -> 0x037b }
            r1.audioBecomingNoisyManager = r0     // Catch:{ all -> 0x037b }
            boolean r7 = r5.handleAudioBecomingNoisy     // Catch:{ all -> 0x037b }
            r0.setEnabled(r7)     // Catch:{ all -> 0x037b }
            androidx.media3.exoplayer.AudioFocusManager r0 = new androidx.media3.exoplayer.AudioFocusManager     // Catch:{ all -> 0x037b }
            android.content.Context r7 = r5.context     // Catch:{ all -> 0x037b }
            r0.<init>(r7, r8, r3)     // Catch:{ all -> 0x037b }
            r1.audioFocusManager = r0     // Catch:{ all -> 0x037b }
            boolean r7 = r5.handleAudioFocus     // Catch:{ all -> 0x037b }
            if (r7 == 0) goto L_0x0294
            androidx.media3.common.AudioAttributes r14 = r1.audioAttributes     // Catch:{ all -> 0x037b }
            goto L_0x0295
        L_0x0294:
            r14 = 0
        L_0x0295:
            r0.setAudioAttributes(r14)     // Catch:{ all -> 0x037b }
            androidx.media3.exoplayer.SuitableOutputChecker r0 = r5.suitableOutputChecker     // Catch:{ all -> 0x037b }
            r1.suitableOutputChecker = r0     // Catch:{ all -> 0x037b }
            if (r0 == 0) goto L_0x02ad
            int r7 = androidx.media3.common.util.Util.SDK_INT     // Catch:{ all -> 0x037b }
            r9 = 35
            if (r7 < r9) goto L_0x02ad
            androidx.media3.exoplayer.ExoPlayerImpl$$ExternalSyntheticLambda27 r6 = new androidx.media3.exoplayer.ExoPlayerImpl$$ExternalSyntheticLambda27     // Catch:{ all -> 0x037b }
            r6.<init>(r1)     // Catch:{ all -> 0x037b }
            r0.enable(r6)     // Catch:{ all -> 0x037b }
            goto L_0x02d0
        L_0x02ad:
            if (r36 == 0) goto L_0x02d0
            int r0 = androidx.media3.common.util.Util.SDK_INT     // Catch:{ all -> 0x037b }
            r7 = 23
            if (r0 < r7) goto L_0x02d0
            java.lang.String r0 = "audio"
            r7 = r31
            java.lang.Object r0 = r7.getSystemService(r0)     // Catch:{ all -> 0x037b }
            android.media.AudioManager r0 = (android.media.AudioManager) r0     // Catch:{ all -> 0x037b }
            r1.audioManager = r0     // Catch:{ all -> 0x037b }
            androidx.media3.exoplayer.ExoPlayerImpl$NoSuitableOutputPlaybackSuppressionAudioDeviceCallback r7 = new androidx.media3.exoplayer.ExoPlayerImpl$NoSuitableOutputPlaybackSuppressionAudioDeviceCallback     // Catch:{ all -> 0x037b }
            r9 = 0
            r7.<init>()     // Catch:{ all -> 0x037b }
            android.os.Handler r10 = new android.os.Handler     // Catch:{ all -> 0x037b }
            r10.<init>(r6)     // Catch:{ all -> 0x037b }
            androidx.media3.exoplayer.ExoPlayerImpl.Api23.registerAudioDeviceCallback(r0, r7, r10)     // Catch:{ all -> 0x037b }
            goto L_0x02d1
        L_0x02d0:
            r9 = 0
        L_0x02d1:
            boolean r0 = r5.deviceVolumeControlEnabled     // Catch:{ all -> 0x037b }
            if (r0 == 0) goto L_0x02e7
            androidx.media3.exoplayer.StreamVolumeManager r0 = new androidx.media3.exoplayer.StreamVolumeManager     // Catch:{ all -> 0x037b }
            android.content.Context r6 = r5.context     // Catch:{ all -> 0x037b }
            androidx.media3.common.AudioAttributes r7 = r1.audioAttributes     // Catch:{ all -> 0x037b }
            int r7 = r7.usage     // Catch:{ all -> 0x037b }
            int r7 = androidx.media3.common.util.Util.getStreamTypeForAudioUsage(r7)     // Catch:{ all -> 0x037b }
            r0.<init>(r6, r8, r3, r7)     // Catch:{ all -> 0x037b }
            r1.streamVolumeManager = r0     // Catch:{ all -> 0x037b }
            goto L_0x02e9
        L_0x02e7:
            r1.streamVolumeManager = r9     // Catch:{ all -> 0x037b }
        L_0x02e9:
            androidx.media3.exoplayer.WakeLockManager r0 = new androidx.media3.exoplayer.WakeLockManager     // Catch:{ all -> 0x037b }
            android.content.Context r3 = r5.context     // Catch:{ all -> 0x037b }
            r0.<init>(r3)     // Catch:{ all -> 0x037b }
            r1.wakeLockManager = r0     // Catch:{ all -> 0x037b }
            int r3 = r5.wakeMode     // Catch:{ all -> 0x037b }
            if (r3 == 0) goto L_0x02f8
            r12 = r4
            goto L_0x02f9
        L_0x02f8:
            r12 = r2
        L_0x02f9:
            r0.setEnabled(r12)     // Catch:{ all -> 0x037b }
            androidx.media3.exoplayer.WifiLockManager r0 = new androidx.media3.exoplayer.WifiLockManager     // Catch:{ all -> 0x037b }
            android.content.Context r3 = r5.context     // Catch:{ all -> 0x037b }
            r0.<init>(r3)     // Catch:{ all -> 0x037b }
            r1.wifiLockManager = r0     // Catch:{ all -> 0x037b }
            int r3 = r5.wakeMode     // Catch:{ all -> 0x037b }
            r5 = 2
            if (r3 != r5) goto L_0x030c
            r12 = r4
            goto L_0x030d
        L_0x030c:
            r12 = r2
        L_0x030d:
            r0.setEnabled(r12)     // Catch:{ all -> 0x037b }
            androidx.media3.exoplayer.StreamVolumeManager r0 = r1.streamVolumeManager     // Catch:{ all -> 0x037b }
            androidx.media3.common.DeviceInfo r0 = createDeviceInfo(r0)     // Catch:{ all -> 0x037b }
            r1.deviceInfo = r0     // Catch:{ all -> 0x037b }
            androidx.media3.common.VideoSize r0 = androidx.media3.common.VideoSize.UNKNOWN     // Catch:{ all -> 0x037b }
            r1.videoSize = r0     // Catch:{ all -> 0x037b }
            androidx.media3.common.util.Size r0 = androidx.media3.common.util.Size.UNKNOWN     // Catch:{ all -> 0x037b }
            r1.surfaceSize = r0     // Catch:{ all -> 0x037b }
            androidx.media3.common.AudioAttributes r0 = r1.audioAttributes     // Catch:{ all -> 0x037b }
            r2 = r38
            r2.setAudioAttributes(r0)     // Catch:{ all -> 0x037b }
            int r0 = r1.audioSessionId     // Catch:{ all -> 0x037b }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x037b }
            r2 = 10
            r1.sendRendererMessage(r4, r2, r0)     // Catch:{ all -> 0x037b }
            int r0 = r1.audioSessionId     // Catch:{ all -> 0x037b }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x037b }
            r1.sendRendererMessage(r5, r2, r0)     // Catch:{ all -> 0x037b }
            androidx.media3.common.AudioAttributes r0 = r1.audioAttributes     // Catch:{ all -> 0x037b }
            r2 = 3
            r1.sendRendererMessage(r4, r2, r0)     // Catch:{ all -> 0x037b }
            int r0 = r1.videoScalingMode     // Catch:{ all -> 0x037b }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x037b }
            r2 = 4
            r1.sendRendererMessage(r5, r2, r0)     // Catch:{ all -> 0x037b }
            int r0 = r1.videoChangeFrameRateStrategy     // Catch:{ all -> 0x037b }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x037b }
            r2 = 5
            r1.sendRendererMessage(r5, r2, r0)     // Catch:{ all -> 0x037b }
            boolean r0 = r1.skipSilenceEnabled     // Catch:{ all -> 0x037b }
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)     // Catch:{ all -> 0x037b }
            r2 = 9
            r1.sendRendererMessage(r4, r2, r0)     // Catch:{ all -> 0x037b }
            r0 = 7
            r2 = r40
            r1.sendRendererMessage(r5, r0, r2)     // Catch:{ all -> 0x037b }
            r0 = 6
            r3 = 8
            r1.sendRendererMessage(r0, r3, r2)     // Catch:{ all -> 0x037b }
            int r0 = r1.priority     // Catch:{ all -> 0x037b }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x037b }
            r2 = 16
            r1.sendRendererMessage(r2, r0)     // Catch:{ all -> 0x037b }
            r30.open()
            return
        L_0x037b:
            r0 = move-exception
            androidx.media3.common.util.ConditionVariable r2 = r1.constructorFinished
            r2.open()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.ExoPlayerImpl.<init>(androidx.media3.exoplayer.ExoPlayer$Builder, androidx.media3.common.Player):void");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$androidx-media3-exoplayer-ExoPlayerImpl  reason: not valid java name */
    public /* synthetic */ void m465lambda$new$0$androidxmedia3exoplayerExoPlayerImpl(Player.Listener listener, FlagSet flagSet) {
        listener.onEvents(this.wrappingPlayer, new Player.Events(flagSet));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$2$androidx-media3-exoplayer-ExoPlayerImpl  reason: not valid java name */
    public /* synthetic */ void m467lambda$new$2$androidxmedia3exoplayerExoPlayerImpl(ExoPlayerImplInternal.PlaybackInfoUpdate playbackInfoUpdate) {
        this.playbackInfoUpdateHandler.post(new ExoPlayerImpl$$ExternalSyntheticLambda16(this, playbackInfoUpdate));
    }

    @Deprecated
    public ExoPlayer.AudioComponent getAudioComponent() {
        verifyApplicationThread();
        return this;
    }

    @Deprecated
    public ExoPlayer.VideoComponent getVideoComponent() {
        verifyApplicationThread();
        return this;
    }

    @Deprecated
    public ExoPlayer.TextComponent getTextComponent() {
        verifyApplicationThread();
        return this;
    }

    @Deprecated
    public ExoPlayer.DeviceComponent getDeviceComponent() {
        verifyApplicationThread();
        return this;
    }

    public boolean isSleepingForOffload() {
        verifyApplicationThread();
        return this.playbackInfo.sleepingForOffload;
    }

    public Looper getPlaybackLooper() {
        return this.internalPlayer.getPlaybackLooper();
    }

    public Looper getApplicationLooper() {
        return this.applicationLooper;
    }

    public Clock getClock() {
        return this.clock;
    }

    public void addAudioOffloadListener(ExoPlayer.AudioOffloadListener audioOffloadListener) {
        this.audioOffloadListeners.add(audioOffloadListener);
    }

    public void removeAudioOffloadListener(ExoPlayer.AudioOffloadListener audioOffloadListener) {
        verifyApplicationThread();
        this.audioOffloadListeners.remove(audioOffloadListener);
    }

    public Player.Commands getAvailableCommands() {
        verifyApplicationThread();
        return this.availableCommands;
    }

    public int getPlaybackState() {
        verifyApplicationThread();
        return this.playbackInfo.playbackState;
    }

    public int getPlaybackSuppressionReason() {
        verifyApplicationThread();
        return this.playbackInfo.playbackSuppressionReason;
    }

    public ExoPlaybackException getPlayerError() {
        verifyApplicationThread();
        return this.playbackInfo.playbackError;
    }

    public void prepare() {
        verifyApplicationThread();
        boolean playWhenReady = getPlayWhenReady();
        int i = 2;
        int updateAudioFocus = this.audioFocusManager.updateAudioFocus(playWhenReady, 2);
        updatePlayWhenReady(playWhenReady, updateAudioFocus, getPlayWhenReadyChangeReason(updateAudioFocus));
        if (this.playbackInfo.playbackState == 1) {
            PlaybackInfo copyWithPlaybackError = this.playbackInfo.copyWithPlaybackError((ExoPlaybackException) null);
            if (copyWithPlaybackError.timeline.isEmpty()) {
                i = 4;
            }
            PlaybackInfo copyWithPlaybackState = copyWithPlaybackError.copyWithPlaybackState(i);
            this.pendingOperationAcks++;
            this.internalPlayer.prepare();
            updatePlaybackInfo(copyWithPlaybackState, 1, false, 5, C.TIME_UNSET, -1, false);
        }
    }

    @Deprecated
    public void prepare(MediaSource mediaSource) {
        verifyApplicationThread();
        setMediaSource(mediaSource);
        prepare();
    }

    @Deprecated
    public void prepare(MediaSource mediaSource, boolean z, boolean z2) {
        verifyApplicationThread();
        setMediaSource(mediaSource, z);
        prepare();
    }

    public void setMediaItems(List<MediaItem> list, boolean z) {
        verifyApplicationThread();
        setMediaSources(createMediaSources(list), z);
    }

    public void setMediaItems(List<MediaItem> list, int i, long j) {
        verifyApplicationThread();
        setMediaSources(createMediaSources(list), i, j);
    }

    public void setMediaSource(MediaSource mediaSource) {
        verifyApplicationThread();
        setMediaSources(Collections.singletonList(mediaSource));
    }

    public void setMediaSource(MediaSource mediaSource, long j) {
        verifyApplicationThread();
        setMediaSources(Collections.singletonList(mediaSource), 0, j);
    }

    public void setMediaSource(MediaSource mediaSource, boolean z) {
        verifyApplicationThread();
        setMediaSources(Collections.singletonList(mediaSource), z);
    }

    public void setMediaSources(List<MediaSource> list) {
        verifyApplicationThread();
        setMediaSources(list, true);
    }

    public void setMediaSources(List<MediaSource> list, boolean z) {
        verifyApplicationThread();
        setMediaSourcesInternal(list, -1, C.TIME_UNSET, z);
    }

    public void setMediaSources(List<MediaSource> list, int i, long j) {
        verifyApplicationThread();
        setMediaSourcesInternal(list, i, j, false);
    }

    public void addMediaItems(int i, List<MediaItem> list) {
        verifyApplicationThread();
        addMediaSources(i, createMediaSources(list));
    }

    public void addMediaSource(MediaSource mediaSource) {
        verifyApplicationThread();
        addMediaSources(Collections.singletonList(mediaSource));
    }

    public void addMediaSource(int i, MediaSource mediaSource) {
        verifyApplicationThread();
        addMediaSources(i, Collections.singletonList(mediaSource));
    }

    public void addMediaSources(List<MediaSource> list) {
        verifyApplicationThread();
        addMediaSources(this.mediaSourceHolderSnapshots.size(), list);
    }

    public void addMediaSources(int i, List<MediaSource> list) {
        verifyApplicationThread();
        boolean z = true;
        Assertions.checkArgument(i >= 0);
        int min = Math.min(i, this.mediaSourceHolderSnapshots.size());
        if (this.mediaSourceHolderSnapshots.isEmpty()) {
            if (this.maskingWindowIndex != -1) {
                z = false;
            }
            setMediaSources(list, z);
            return;
        }
        updatePlaybackInfo(addMediaSourcesInternal(this.playbackInfo, min, list), 0, false, 5, C.TIME_UNSET, -1, false);
    }

    public void removeMediaItems(int i, int i2) {
        verifyApplicationThread();
        Assertions.checkArgument(i >= 0 && i2 >= i);
        int size = this.mediaSourceHolderSnapshots.size();
        int min = Math.min(i2, size);
        if (i < size && i != min) {
            PlaybackInfo removeMediaItemsInternal = removeMediaItemsInternal(this.playbackInfo, i, min);
            updatePlaybackInfo(removeMediaItemsInternal, 0, !removeMediaItemsInternal.periodId.periodUid.equals(this.playbackInfo.periodId.periodUid), 4, getCurrentPositionUsInternal(removeMediaItemsInternal), -1, false);
        }
    }

    public void moveMediaItems(int i, int i2, int i3) {
        verifyApplicationThread();
        Assertions.checkArgument(i >= 0 && i <= i2 && i3 >= 0);
        int size = this.mediaSourceHolderSnapshots.size();
        int min = Math.min(i2, size);
        int min2 = Math.min(i3, size - (min - i));
        if (i < size && i != min && i != min2) {
            Timeline currentTimeline = getCurrentTimeline();
            this.pendingOperationAcks++;
            Util.moveItems(this.mediaSourceHolderSnapshots, i, min, min2);
            Timeline createMaskingTimeline = createMaskingTimeline();
            PlaybackInfo playbackInfo2 = this.playbackInfo;
            PlaybackInfo maskTimelineAndPosition = maskTimelineAndPosition(playbackInfo2, createMaskingTimeline, getPeriodPositionUsAfterTimelineChanged(currentTimeline, createMaskingTimeline, getCurrentWindowIndexInternal(playbackInfo2), getContentPositionInternal(this.playbackInfo)));
            this.internalPlayer.moveMediaSources(i, min, min2, this.shuffleOrder);
            updatePlaybackInfo(maskTimelineAndPosition, 0, false, 5, C.TIME_UNSET, -1, false);
        }
    }

    public void replaceMediaItems(int i, int i2, List<MediaItem> list) {
        verifyApplicationThread();
        boolean z = false;
        Assertions.checkArgument(i >= 0 && i2 >= i);
        int size = this.mediaSourceHolderSnapshots.size();
        if (i <= size) {
            int min = Math.min(i2, size);
            if (canUpdateMediaSourcesWithMediaItems(i, min, list)) {
                updateMediaSourcesWithMediaItems(i, min, list);
                return;
            }
            List<MediaSource> createMediaSources = createMediaSources(list);
            if (this.mediaSourceHolderSnapshots.isEmpty()) {
                if (this.maskingWindowIndex == -1) {
                    z = true;
                }
                setMediaSources(createMediaSources, z);
                return;
            }
            PlaybackInfo removeMediaItemsInternal = removeMediaItemsInternal(addMediaSourcesInternal(this.playbackInfo, min, createMediaSources), i, min);
            updatePlaybackInfo(removeMediaItemsInternal, 0, !removeMediaItemsInternal.periodId.periodUid.equals(this.playbackInfo.periodId.periodUid), 4, getCurrentPositionUsInternal(removeMediaItemsInternal), -1, false);
        }
    }

    public void setShuffleOrder(ShuffleOrder shuffleOrder2) {
        verifyApplicationThread();
        Assertions.checkArgument(shuffleOrder2.getLength() == this.mediaSourceHolderSnapshots.size());
        this.shuffleOrder = shuffleOrder2;
        Timeline createMaskingTimeline = createMaskingTimeline();
        PlaybackInfo maskTimelineAndPosition = maskTimelineAndPosition(this.playbackInfo, createMaskingTimeline, maskWindowPositionMsOrGetPeriodPositionUs(createMaskingTimeline, getCurrentMediaItemIndex(), getCurrentPosition()));
        this.pendingOperationAcks++;
        this.internalPlayer.setShuffleOrder(shuffleOrder2);
        updatePlaybackInfo(maskTimelineAndPosition, 0, false, 5, C.TIME_UNSET, -1, false);
    }

    public void setPauseAtEndOfMediaItems(boolean z) {
        verifyApplicationThread();
        if (this.pauseAtEndOfMediaItems != z) {
            this.pauseAtEndOfMediaItems = z;
            this.internalPlayer.setPauseAtEndOfWindow(z);
        }
    }

    public boolean getPauseAtEndOfMediaItems() {
        verifyApplicationThread();
        return this.pauseAtEndOfMediaItems;
    }

    public void setPlayWhenReady(boolean z) {
        verifyApplicationThread();
        int updateAudioFocus = this.audioFocusManager.updateAudioFocus(z, getPlaybackState());
        updatePlayWhenReady(z, updateAudioFocus, getPlayWhenReadyChangeReason(updateAudioFocus));
    }

    public boolean getPlayWhenReady() {
        verifyApplicationThread();
        return this.playbackInfo.playWhenReady;
    }

    public void setRepeatMode(int i) {
        verifyApplicationThread();
        if (this.repeatMode != i) {
            this.repeatMode = i;
            this.internalPlayer.setRepeatMode(i);
            this.listeners.queueEvent(8, new ExoPlayerImpl$$ExternalSyntheticLambda28(i));
            updateAvailableCommands();
            this.listeners.flushEvents();
        }
    }

    public int getRepeatMode() {
        verifyApplicationThread();
        return this.repeatMode;
    }

    public void setShuffleModeEnabled(boolean z) {
        verifyApplicationThread();
        if (this.shuffleModeEnabled != z) {
            this.shuffleModeEnabled = z;
            this.internalPlayer.setShuffleModeEnabled(z);
            this.listeners.queueEvent(9, new ExoPlayerImpl$$ExternalSyntheticLambda18(z));
            updateAvailableCommands();
            this.listeners.flushEvents();
        }
    }

    public boolean getShuffleModeEnabled() {
        verifyApplicationThread();
        return this.shuffleModeEnabled;
    }

    public void setPreloadConfiguration(ExoPlayer.PreloadConfiguration preloadConfiguration2) {
        verifyApplicationThread();
        if (!this.preloadConfiguration.equals(preloadConfiguration2)) {
            this.preloadConfiguration = preloadConfiguration2;
            this.internalPlayer.setPreloadConfiguration(preloadConfiguration2);
        }
    }

    public ExoPlayer.PreloadConfiguration getPreloadConfiguration() {
        return this.preloadConfiguration;
    }

    public boolean isLoading() {
        verifyApplicationThread();
        return this.playbackInfo.isLoading;
    }

    public void seekTo(int i, long j, int i2, boolean z) {
        verifyApplicationThread();
        if (i != -1) {
            Assertions.checkArgument(i >= 0);
            Timeline timeline = this.playbackInfo.timeline;
            if (timeline.isEmpty() || i < timeline.getWindowCount()) {
                this.analyticsCollector.notifySeekStarted();
                this.pendingOperationAcks++;
                if (isPlayingAd()) {
                    Log.w(TAG, "seekTo ignored because an ad is playing");
                    ExoPlayerImplInternal.PlaybackInfoUpdate playbackInfoUpdate = new ExoPlayerImplInternal.PlaybackInfoUpdate(this.playbackInfo);
                    playbackInfoUpdate.incrementPendingOperationAcks(1);
                    this.playbackInfoUpdateListener.onPlaybackInfoUpdate(playbackInfoUpdate);
                    return;
                }
                PlaybackInfo playbackInfo2 = this.playbackInfo;
                if (playbackInfo2.playbackState == 3 || (this.playbackInfo.playbackState == 4 && !timeline.isEmpty())) {
                    playbackInfo2 = this.playbackInfo.copyWithPlaybackState(2);
                }
                int currentMediaItemIndex = getCurrentMediaItemIndex();
                PlaybackInfo maskTimelineAndPosition = maskTimelineAndPosition(playbackInfo2, timeline, maskWindowPositionMsOrGetPeriodPositionUs(timeline, i, j));
                this.internalPlayer.seekTo(timeline, i, Util.msToUs(j));
                updatePlaybackInfo(maskTimelineAndPosition, 0, true, 1, getCurrentPositionUsInternal(maskTimelineAndPosition), currentMediaItemIndex, z);
            }
        }
    }

    public long getSeekBackIncrement() {
        verifyApplicationThread();
        return this.seekBackIncrementMs;
    }

    public long getSeekForwardIncrement() {
        verifyApplicationThread();
        return this.seekForwardIncrementMs;
    }

    public long getMaxSeekToPreviousPosition() {
        verifyApplicationThread();
        return this.maxSeekToPreviousPositionMs;
    }

    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        verifyApplicationThread();
        if (playbackParameters == null) {
            playbackParameters = PlaybackParameters.DEFAULT;
        }
        if (!this.playbackInfo.playbackParameters.equals(playbackParameters)) {
            PlaybackInfo copyWithPlaybackParameters = this.playbackInfo.copyWithPlaybackParameters(playbackParameters);
            this.pendingOperationAcks++;
            this.internalPlayer.setPlaybackParameters(playbackParameters);
            updatePlaybackInfo(copyWithPlaybackParameters, 0, false, 5, C.TIME_UNSET, -1, false);
        }
    }

    public PlaybackParameters getPlaybackParameters() {
        verifyApplicationThread();
        return this.playbackInfo.playbackParameters;
    }

    public void setSeekParameters(SeekParameters seekParameters2) {
        verifyApplicationThread();
        if (seekParameters2 == null) {
            seekParameters2 = SeekParameters.DEFAULT;
        }
        if (!this.seekParameters.equals(seekParameters2)) {
            this.seekParameters = seekParameters2;
            this.internalPlayer.setSeekParameters(seekParameters2);
        }
    }

    public SeekParameters getSeekParameters() {
        verifyApplicationThread();
        return this.seekParameters;
    }

    public void setForegroundMode(boolean z) {
        verifyApplicationThread();
        if (this.foregroundMode != z) {
            this.foregroundMode = z;
            if (!this.internalPlayer.setForegroundMode(z)) {
                stopInternal(ExoPlaybackException.createForUnexpected(new ExoTimeoutException(2), 1003));
            }
        }
    }

    public void stop() {
        verifyApplicationThread();
        this.audioFocusManager.updateAudioFocus(getPlayWhenReady(), 1);
        stopInternal((ExoPlaybackException) null);
        this.currentCueGroup = new CueGroup(ImmutableList.of(), this.playbackInfo.positionUs);
    }

    public void release() {
        Log.i(TAG, "Release " + Integer.toHexString(System.identityHashCode(this)) + " [AndroidXMedia3/1.5.1] [" + Util.DEVICE_DEBUG_INFO + "] [" + MediaLibraryInfo.registeredModules() + "]");
        verifyApplicationThread();
        this.audioBecomingNoisyManager.setEnabled(false);
        StreamVolumeManager streamVolumeManager2 = this.streamVolumeManager;
        if (streamVolumeManager2 != null) {
            streamVolumeManager2.release();
        }
        this.wakeLockManager.setStayAwake(false);
        this.wifiLockManager.setStayAwake(false);
        this.audioFocusManager.release();
        if (!this.internalPlayer.release()) {
            this.listeners.sendEvent(10, new ExoPlayerImpl$$ExternalSyntheticLambda17());
        }
        this.listeners.release();
        this.playbackInfoUpdateHandler.removeCallbacksAndMessages((Object) null);
        this.bandwidthMeter.removeEventListener(this.analyticsCollector);
        if (this.playbackInfo.sleepingForOffload) {
            this.playbackInfo = this.playbackInfo.copyWithEstimatedPosition();
        }
        if (this.suitableOutputChecker != null && Util.SDK_INT >= 35) {
            this.suitableOutputChecker.disable();
        }
        PlaybackInfo copyWithPlaybackState = this.playbackInfo.copyWithPlaybackState(1);
        this.playbackInfo = copyWithPlaybackState;
        PlaybackInfo copyWithLoadingMediaPeriodId = copyWithPlaybackState.copyWithLoadingMediaPeriodId(copyWithPlaybackState.periodId);
        this.playbackInfo = copyWithLoadingMediaPeriodId;
        copyWithLoadingMediaPeriodId.bufferedPositionUs = copyWithLoadingMediaPeriodId.positionUs;
        this.playbackInfo.totalBufferedDurationUs = 0;
        this.analyticsCollector.release();
        this.trackSelector.release();
        removeSurfaceCallbacks();
        Surface surface = this.ownedSurface;
        if (surface != null) {
            surface.release();
            this.ownedSurface = null;
        }
        if (this.isPriorityTaskManagerRegistered) {
            ((PriorityTaskManager) Assertions.checkNotNull(this.priorityTaskManager)).remove(this.priority);
            this.isPriorityTaskManagerRegistered = false;
        }
        this.currentCueGroup = CueGroup.EMPTY_TIME_ZERO;
        this.playerReleased = true;
    }

    public boolean isReleased() {
        verifyApplicationThread();
        return this.playerReleased;
    }

    public PlayerMessage createMessage(PlayerMessage.Target target) {
        verifyApplicationThread();
        return createMessageInternal(target);
    }

    public int getCurrentPeriodIndex() {
        verifyApplicationThread();
        if (this.playbackInfo.timeline.isEmpty()) {
            return this.maskingPeriodIndex;
        }
        return this.playbackInfo.timeline.getIndexOfPeriod(this.playbackInfo.periodId.periodUid);
    }

    public int getCurrentMediaItemIndex() {
        verifyApplicationThread();
        int currentWindowIndexInternal = getCurrentWindowIndexInternal(this.playbackInfo);
        if (currentWindowIndexInternal == -1) {
            return 0;
        }
        return currentWindowIndexInternal;
    }

    public long getDuration() {
        verifyApplicationThread();
        if (!isPlayingAd()) {
            return getContentDuration();
        }
        MediaSource.MediaPeriodId mediaPeriodId = this.playbackInfo.periodId;
        this.playbackInfo.timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period);
        return Util.usToMs(this.period.getAdDurationUs(mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup));
    }

    public long getCurrentPosition() {
        verifyApplicationThread();
        return Util.usToMs(getCurrentPositionUsInternal(this.playbackInfo));
    }

    public long getBufferedPosition() {
        verifyApplicationThread();
        if (!isPlayingAd()) {
            return getContentBufferedPosition();
        }
        if (this.playbackInfo.loadingMediaPeriodId.equals(this.playbackInfo.periodId)) {
            return Util.usToMs(this.playbackInfo.bufferedPositionUs);
        }
        return getDuration();
    }

    public long getTotalBufferedDuration() {
        verifyApplicationThread();
        return Util.usToMs(this.playbackInfo.totalBufferedDurationUs);
    }

    public boolean isPlayingAd() {
        verifyApplicationThread();
        return this.playbackInfo.periodId.isAd();
    }

    public int getCurrentAdGroupIndex() {
        verifyApplicationThread();
        if (isPlayingAd()) {
            return this.playbackInfo.periodId.adGroupIndex;
        }
        return -1;
    }

    public int getCurrentAdIndexInAdGroup() {
        verifyApplicationThread();
        if (isPlayingAd()) {
            return this.playbackInfo.periodId.adIndexInAdGroup;
        }
        return -1;
    }

    public long getContentPosition() {
        verifyApplicationThread();
        return getContentPositionInternal(this.playbackInfo);
    }

    public long getContentBufferedPosition() {
        verifyApplicationThread();
        if (this.playbackInfo.timeline.isEmpty()) {
            return this.maskingWindowPositionMs;
        }
        if (this.playbackInfo.loadingMediaPeriodId.windowSequenceNumber != this.playbackInfo.periodId.windowSequenceNumber) {
            return this.playbackInfo.timeline.getWindow(getCurrentMediaItemIndex(), this.window).getDurationMs();
        }
        long j = this.playbackInfo.bufferedPositionUs;
        if (this.playbackInfo.loadingMediaPeriodId.isAd()) {
            Timeline.Period periodByUid = this.playbackInfo.timeline.getPeriodByUid(this.playbackInfo.loadingMediaPeriodId.periodUid, this.period);
            long adGroupTimeUs = periodByUid.getAdGroupTimeUs(this.playbackInfo.loadingMediaPeriodId.adGroupIndex);
            j = adGroupTimeUs == Long.MIN_VALUE ? periodByUid.durationUs : adGroupTimeUs;
        }
        return Util.usToMs(periodPositionUsToWindowPositionUs(this.playbackInfo.timeline, this.playbackInfo.loadingMediaPeriodId, j));
    }

    public int getRendererCount() {
        verifyApplicationThread();
        return this.renderers.length;
    }

    public int getRendererType(int i) {
        verifyApplicationThread();
        return this.renderers[i].getTrackType();
    }

    public Renderer getRenderer(int i) {
        verifyApplicationThread();
        return this.renderers[i];
    }

    public TrackSelector getTrackSelector() {
        verifyApplicationThread();
        return this.trackSelector;
    }

    public TrackGroupArray getCurrentTrackGroups() {
        verifyApplicationThread();
        return this.playbackInfo.trackGroups;
    }

    public TrackSelectionArray getCurrentTrackSelections() {
        verifyApplicationThread();
        return new TrackSelectionArray(this.playbackInfo.trackSelectorResult.selections);
    }

    public Tracks getCurrentTracks() {
        verifyApplicationThread();
        return this.playbackInfo.trackSelectorResult.tracks;
    }

    public TrackSelectionParameters getTrackSelectionParameters() {
        verifyApplicationThread();
        return this.trackSelector.getParameters();
    }

    public void setTrackSelectionParameters(TrackSelectionParameters trackSelectionParameters) {
        verifyApplicationThread();
        if (this.trackSelector.isSetParametersSupported() && !trackSelectionParameters.equals(this.trackSelector.getParameters())) {
            this.trackSelector.setParameters(trackSelectionParameters);
            this.listeners.sendEvent(19, new ExoPlayerImpl$$ExternalSyntheticLambda11(trackSelectionParameters));
        }
    }

    public MediaMetadata getMediaMetadata() {
        verifyApplicationThread();
        return this.mediaMetadata;
    }

    public MediaMetadata getPlaylistMetadata() {
        verifyApplicationThread();
        return this.playlistMetadata;
    }

    public void setPlaylistMetadata(MediaMetadata mediaMetadata2) {
        verifyApplicationThread();
        Assertions.checkNotNull(mediaMetadata2);
        if (!mediaMetadata2.equals(this.playlistMetadata)) {
            this.playlistMetadata = mediaMetadata2;
            this.listeners.sendEvent(15, new ExoPlayerImpl$$ExternalSyntheticLambda24(this));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setPlaylistMetadata$7$androidx-media3-exoplayer-ExoPlayerImpl  reason: not valid java name */
    public /* synthetic */ void m468lambda$setPlaylistMetadata$7$androidxmedia3exoplayerExoPlayerImpl(Player.Listener listener) {
        listener.onPlaylistMetadataChanged(this.playlistMetadata);
    }

    public Timeline getCurrentTimeline() {
        verifyApplicationThread();
        return this.playbackInfo.timeline;
    }

    public void setVideoEffects(List<Effect> list) {
        verifyApplicationThread();
        try {
            Class.forName("androidx.media3.effect.PreviewingSingleInputVideoGraph$Factory").getConstructor(new Class[]{VideoFrameProcessor.Factory.class});
            sendRendererMessage(2, 13, list);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            throw new IllegalStateException("Could not find required lib-effect dependencies.", e);
        }
    }

    public void setVideoScalingMode(int i) {
        verifyApplicationThread();
        this.videoScalingMode = i;
        sendRendererMessage(2, 4, Integer.valueOf(i));
    }

    public int getVideoScalingMode() {
        verifyApplicationThread();
        return this.videoScalingMode;
    }

    public void setVideoChangeFrameRateStrategy(int i) {
        verifyApplicationThread();
        if (this.videoChangeFrameRateStrategy != i) {
            this.videoChangeFrameRateStrategy = i;
            sendRendererMessage(2, 5, Integer.valueOf(i));
        }
    }

    public int getVideoChangeFrameRateStrategy() {
        verifyApplicationThread();
        return this.videoChangeFrameRateStrategy;
    }

    public VideoSize getVideoSize() {
        verifyApplicationThread();
        return this.videoSize;
    }

    public Size getSurfaceSize() {
        verifyApplicationThread();
        return this.surfaceSize;
    }

    public void clearVideoSurface() {
        verifyApplicationThread();
        removeSurfaceCallbacks();
        setVideoOutputInternal((Object) null);
        maybeNotifySurfaceSizeChanged(0, 0);
    }

    public void clearVideoSurface(Surface surface) {
        verifyApplicationThread();
        if (surface != null && surface == this.videoOutput) {
            clearVideoSurface();
        }
    }

    public void setVideoSurface(Surface surface) {
        verifyApplicationThread();
        removeSurfaceCallbacks();
        setVideoOutputInternal(surface);
        int i = surface == null ? 0 : -1;
        maybeNotifySurfaceSizeChanged(i, i);
    }

    public void setVideoSurfaceHolder(SurfaceHolder surfaceHolder2) {
        verifyApplicationThread();
        if (surfaceHolder2 == null) {
            clearVideoSurface();
            return;
        }
        removeSurfaceCallbacks();
        this.surfaceHolderSurfaceIsVideoOutput = true;
        this.surfaceHolder = surfaceHolder2;
        surfaceHolder2.addCallback(this.componentListener);
        Surface surface = surfaceHolder2.getSurface();
        if (surface == null || !surface.isValid()) {
            setVideoOutputInternal((Object) null);
            maybeNotifySurfaceSizeChanged(0, 0);
            return;
        }
        setVideoOutputInternal(surface);
        Rect surfaceFrame = surfaceHolder2.getSurfaceFrame();
        maybeNotifySurfaceSizeChanged(surfaceFrame.width(), surfaceFrame.height());
    }

    public void clearVideoSurfaceHolder(SurfaceHolder surfaceHolder2) {
        verifyApplicationThread();
        if (surfaceHolder2 != null && surfaceHolder2 == this.surfaceHolder) {
            clearVideoSurface();
        }
    }

    public void setVideoSurfaceView(SurfaceView surfaceView) {
        SurfaceHolder surfaceHolder2;
        verifyApplicationThread();
        if (surfaceView instanceof VideoDecoderOutputBufferRenderer) {
            removeSurfaceCallbacks();
            setVideoOutputInternal(surfaceView);
            setNonVideoOutputSurfaceHolderInternal(surfaceView.getHolder());
        } else if (surfaceView instanceof SphericalGLSurfaceView) {
            removeSurfaceCallbacks();
            this.sphericalGLSurfaceView = (SphericalGLSurfaceView) surfaceView;
            createMessageInternal(this.frameMetadataListener).setType(10000).setPayload(this.sphericalGLSurfaceView).send();
            this.sphericalGLSurfaceView.addVideoSurfaceListener(this.componentListener);
            setVideoOutputInternal(this.sphericalGLSurfaceView.getVideoSurface());
            setNonVideoOutputSurfaceHolderInternal(surfaceView.getHolder());
        } else {
            if (surfaceView == null) {
                surfaceHolder2 = null;
            } else {
                surfaceHolder2 = surfaceView.getHolder();
            }
            setVideoSurfaceHolder(surfaceHolder2);
        }
    }

    public void clearVideoSurfaceView(SurfaceView surfaceView) {
        SurfaceHolder surfaceHolder2;
        verifyApplicationThread();
        if (surfaceView == null) {
            surfaceHolder2 = null;
        } else {
            surfaceHolder2 = surfaceView.getHolder();
        }
        clearVideoSurfaceHolder(surfaceHolder2);
    }

    public void setVideoTextureView(TextureView textureView2) {
        verifyApplicationThread();
        if (textureView2 == null) {
            clearVideoSurface();
            return;
        }
        removeSurfaceCallbacks();
        this.textureView = textureView2;
        if (textureView2.getSurfaceTextureListener() != null) {
            Log.w(TAG, "Replacing existing SurfaceTextureListener.");
        }
        textureView2.setSurfaceTextureListener(this.componentListener);
        SurfaceTexture surfaceTexture = textureView2.isAvailable() ? textureView2.getSurfaceTexture() : null;
        if (surfaceTexture == null) {
            setVideoOutputInternal((Object) null);
            maybeNotifySurfaceSizeChanged(0, 0);
            return;
        }
        setSurfaceTextureInternal(surfaceTexture);
        maybeNotifySurfaceSizeChanged(textureView2.getWidth(), textureView2.getHeight());
    }

    public void clearVideoTextureView(TextureView textureView2) {
        verifyApplicationThread();
        if (textureView2 != null && textureView2 == this.textureView) {
            clearVideoSurface();
        }
    }

    public void setAudioAttributes(AudioAttributes audioAttributes2, boolean z) {
        verifyApplicationThread();
        if (!this.playerReleased) {
            if (!Util.areEqual(this.audioAttributes, audioAttributes2)) {
                this.audioAttributes = audioAttributes2;
                sendRendererMessage(1, 3, audioAttributes2);
                StreamVolumeManager streamVolumeManager2 = this.streamVolumeManager;
                if (streamVolumeManager2 != null) {
                    streamVolumeManager2.setStreamType(Util.getStreamTypeForAudioUsage(audioAttributes2.usage));
                }
                this.listeners.queueEvent(20, new ExoPlayerImpl$$ExternalSyntheticLambda23(audioAttributes2));
            }
            this.audioFocusManager.setAudioAttributes(z ? audioAttributes2 : null);
            this.trackSelector.setAudioAttributes(audioAttributes2);
            boolean playWhenReady = getPlayWhenReady();
            int updateAudioFocus = this.audioFocusManager.updateAudioFocus(playWhenReady, getPlaybackState());
            updatePlayWhenReady(playWhenReady, updateAudioFocus, getPlayWhenReadyChangeReason(updateAudioFocus));
            this.listeners.flushEvents();
        }
    }

    public AudioAttributes getAudioAttributes() {
        verifyApplicationThread();
        return this.audioAttributes;
    }

    public void setAudioSessionId(int i) {
        verifyApplicationThread();
        if (this.audioSessionId != i) {
            if (i == 0) {
                i = Util.generateAudioSessionIdV21(this.applicationContext);
            }
            this.audioSessionId = i;
            sendRendererMessage(1, 10, Integer.valueOf(i));
            sendRendererMessage(2, 10, Integer.valueOf(i));
            this.listeners.sendEvent(21, new ExoPlayerImpl$$ExternalSyntheticLambda0(i));
        }
    }

    public int getAudioSessionId() {
        verifyApplicationThread();
        return this.audioSessionId;
    }

    public void setAuxEffectInfo(AuxEffectInfo auxEffectInfo) {
        verifyApplicationThread();
        sendRendererMessage(1, 6, auxEffectInfo);
    }

    public void clearAuxEffectInfo() {
        verifyApplicationThread();
        setAuxEffectInfo(new AuxEffectInfo(0, 0.0f));
    }

    public void setPreferredAudioDevice(AudioDeviceInfo audioDeviceInfo) {
        verifyApplicationThread();
        sendRendererMessage(1, 12, audioDeviceInfo);
    }

    public void setVolume(float f) {
        verifyApplicationThread();
        float constrainValue = Util.constrainValue(f, 0.0f, 1.0f);
        if (this.volume != constrainValue) {
            this.volume = constrainValue;
            sendVolumeToRenderers();
            this.listeners.sendEvent(22, new ExoPlayerImpl$$ExternalSyntheticLambda20(constrainValue));
        }
    }

    public float getVolume() {
        verifyApplicationThread();
        return this.volume;
    }

    public boolean getSkipSilenceEnabled() {
        verifyApplicationThread();
        return this.skipSilenceEnabled;
    }

    public void setSkipSilenceEnabled(boolean z) {
        verifyApplicationThread();
        if (this.skipSilenceEnabled != z) {
            this.skipSilenceEnabled = z;
            sendRendererMessage(1, 9, Boolean.valueOf(z));
            this.listeners.sendEvent(23, new ExoPlayerImpl$$ExternalSyntheticLambda21(z));
        }
    }

    public AnalyticsCollector getAnalyticsCollector() {
        verifyApplicationThread();
        return this.analyticsCollector;
    }

    public void addAnalyticsListener(AnalyticsListener analyticsListener) {
        this.analyticsCollector.addListener((AnalyticsListener) Assertions.checkNotNull(analyticsListener));
    }

    public void removeAnalyticsListener(AnalyticsListener analyticsListener) {
        verifyApplicationThread();
        this.analyticsCollector.removeListener((AnalyticsListener) Assertions.checkNotNull(analyticsListener));
    }

    public void setHandleAudioBecomingNoisy(boolean z) {
        verifyApplicationThread();
        if (!this.playerReleased) {
            this.audioBecomingNoisyManager.setEnabled(z);
        }
    }

    public void setPriority(int i) {
        verifyApplicationThread();
        if (this.priority != i) {
            if (this.isPriorityTaskManagerRegistered) {
                PriorityTaskManager priorityTaskManager2 = (PriorityTaskManager) Assertions.checkNotNull(this.priorityTaskManager);
                priorityTaskManager2.add(i);
                priorityTaskManager2.remove(this.priority);
            }
            this.priority = i;
            sendRendererMessage(16, Integer.valueOf(i));
        }
    }

    public void setPriorityTaskManager(PriorityTaskManager priorityTaskManager2) {
        verifyApplicationThread();
        if (!Util.areEqual(this.priorityTaskManager, priorityTaskManager2)) {
            if (this.isPriorityTaskManagerRegistered) {
                ((PriorityTaskManager) Assertions.checkNotNull(this.priorityTaskManager)).remove(this.priority);
            }
            if (priorityTaskManager2 == null || !isLoading()) {
                this.isPriorityTaskManagerRegistered = false;
            } else {
                priorityTaskManager2.add(this.priority);
                this.isPriorityTaskManagerRegistered = true;
            }
            this.priorityTaskManager = priorityTaskManager2;
        }
    }

    public Format getVideoFormat() {
        verifyApplicationThread();
        return this.videoFormat;
    }

    public Format getAudioFormat() {
        verifyApplicationThread();
        return this.audioFormat;
    }

    public DecoderCounters getVideoDecoderCounters() {
        verifyApplicationThread();
        return this.videoDecoderCounters;
    }

    public DecoderCounters getAudioDecoderCounters() {
        verifyApplicationThread();
        return this.audioDecoderCounters;
    }

    public void setVideoFrameMetadataListener(VideoFrameMetadataListener videoFrameMetadataListener2) {
        verifyApplicationThread();
        this.videoFrameMetadataListener = videoFrameMetadataListener2;
        createMessageInternal(this.frameMetadataListener).setType(7).setPayload(videoFrameMetadataListener2).send();
    }

    public void clearVideoFrameMetadataListener(VideoFrameMetadataListener videoFrameMetadataListener2) {
        verifyApplicationThread();
        if (this.videoFrameMetadataListener == videoFrameMetadataListener2) {
            createMessageInternal(this.frameMetadataListener).setType(7).setPayload((Object) null).send();
        }
    }

    public void setCameraMotionListener(CameraMotionListener cameraMotionListener2) {
        verifyApplicationThread();
        this.cameraMotionListener = cameraMotionListener2;
        createMessageInternal(this.frameMetadataListener).setType(8).setPayload(cameraMotionListener2).send();
    }

    public void clearCameraMotionListener(CameraMotionListener cameraMotionListener2) {
        verifyApplicationThread();
        if (this.cameraMotionListener == cameraMotionListener2) {
            createMessageInternal(this.frameMetadataListener).setType(8).setPayload((Object) null).send();
        }
    }

    public CueGroup getCurrentCues() {
        verifyApplicationThread();
        return this.currentCueGroup;
    }

    public void addListener(Player.Listener listener) {
        this.listeners.add((Player.Listener) Assertions.checkNotNull(listener));
    }

    public void removeListener(Player.Listener listener) {
        verifyApplicationThread();
        this.listeners.remove((Player.Listener) Assertions.checkNotNull(listener));
    }

    public void setWakeMode(int i) {
        verifyApplicationThread();
        if (i == 0) {
            this.wakeLockManager.setEnabled(false);
            this.wifiLockManager.setEnabled(false);
        } else if (i == 1) {
            this.wakeLockManager.setEnabled(true);
            this.wifiLockManager.setEnabled(false);
        } else if (i == 2) {
            this.wakeLockManager.setEnabled(true);
            this.wifiLockManager.setEnabled(true);
        }
    }

    public DeviceInfo getDeviceInfo() {
        verifyApplicationThread();
        return this.deviceInfo;
    }

    public int getDeviceVolume() {
        verifyApplicationThread();
        StreamVolumeManager streamVolumeManager2 = this.streamVolumeManager;
        if (streamVolumeManager2 != null) {
            return streamVolumeManager2.getVolume();
        }
        return 0;
    }

    public boolean isDeviceMuted() {
        verifyApplicationThread();
        StreamVolumeManager streamVolumeManager2 = this.streamVolumeManager;
        if (streamVolumeManager2 != null) {
            return streamVolumeManager2.isMuted();
        }
        return false;
    }

    @Deprecated
    public void setDeviceVolume(int i) {
        verifyApplicationThread();
        StreamVolumeManager streamVolumeManager2 = this.streamVolumeManager;
        if (streamVolumeManager2 != null) {
            streamVolumeManager2.setVolume(i, 1);
        }
    }

    public void setDeviceVolume(int i, int i2) {
        verifyApplicationThread();
        StreamVolumeManager streamVolumeManager2 = this.streamVolumeManager;
        if (streamVolumeManager2 != null) {
            streamVolumeManager2.setVolume(i, i2);
        }
    }

    @Deprecated
    public void increaseDeviceVolume() {
        verifyApplicationThread();
        StreamVolumeManager streamVolumeManager2 = this.streamVolumeManager;
        if (streamVolumeManager2 != null) {
            streamVolumeManager2.increaseVolume(1);
        }
    }

    public void increaseDeviceVolume(int i) {
        verifyApplicationThread();
        StreamVolumeManager streamVolumeManager2 = this.streamVolumeManager;
        if (streamVolumeManager2 != null) {
            streamVolumeManager2.increaseVolume(i);
        }
    }

    @Deprecated
    public void decreaseDeviceVolume() {
        verifyApplicationThread();
        StreamVolumeManager streamVolumeManager2 = this.streamVolumeManager;
        if (streamVolumeManager2 != null) {
            streamVolumeManager2.decreaseVolume(1);
        }
    }

    public void decreaseDeviceVolume(int i) {
        verifyApplicationThread();
        StreamVolumeManager streamVolumeManager2 = this.streamVolumeManager;
        if (streamVolumeManager2 != null) {
            streamVolumeManager2.decreaseVolume(i);
        }
    }

    @Deprecated
    public void setDeviceMuted(boolean z) {
        verifyApplicationThread();
        StreamVolumeManager streamVolumeManager2 = this.streamVolumeManager;
        if (streamVolumeManager2 != null) {
            streamVolumeManager2.setMuted(z, 1);
        }
    }

    public void setDeviceMuted(boolean z, int i) {
        verifyApplicationThread();
        StreamVolumeManager streamVolumeManager2 = this.streamVolumeManager;
        if (streamVolumeManager2 != null) {
            streamVolumeManager2.setMuted(z, i);
        }
    }

    public boolean isTunnelingEnabled() {
        verifyApplicationThread();
        for (RendererConfiguration rendererConfiguration : this.playbackInfo.trackSelectorResult.rendererConfigurations) {
            if (rendererConfiguration != null && rendererConfiguration.tunneling) {
                return true;
            }
        }
        return false;
    }

    public void setImageOutput(ImageOutput imageOutput) {
        verifyApplicationThread();
        sendRendererMessage(4, 15, imageOutput);
    }

    /* access modifiers changed from: package-private */
    public void setThrowsWhenUsingWrongThread(boolean z) {
        this.throwsWhenUsingWrongThread = z;
        this.listeners.setThrowsWhenUsingWrongThread(z);
        AnalyticsCollector analyticsCollector2 = this.analyticsCollector;
        if (analyticsCollector2 instanceof DefaultAnalyticsCollector) {
            ((DefaultAnalyticsCollector) analyticsCollector2).setThrowsWhenUsingWrongThread(z);
        }
    }

    private void stopInternal(ExoPlaybackException exoPlaybackException) {
        PlaybackInfo playbackInfo2 = this.playbackInfo;
        PlaybackInfo copyWithLoadingMediaPeriodId = playbackInfo2.copyWithLoadingMediaPeriodId(playbackInfo2.periodId);
        copyWithLoadingMediaPeriodId.bufferedPositionUs = copyWithLoadingMediaPeriodId.positionUs;
        copyWithLoadingMediaPeriodId.totalBufferedDurationUs = 0;
        PlaybackInfo copyWithPlaybackState = copyWithLoadingMediaPeriodId.copyWithPlaybackState(1);
        if (exoPlaybackException != null) {
            copyWithPlaybackState = copyWithPlaybackState.copyWithPlaybackError(exoPlaybackException);
        }
        this.pendingOperationAcks++;
        this.internalPlayer.stop();
        updatePlaybackInfo(copyWithPlaybackState, 0, false, 5, C.TIME_UNSET, -1, false);
    }

    private int getCurrentWindowIndexInternal(PlaybackInfo playbackInfo2) {
        if (playbackInfo2.timeline.isEmpty()) {
            return this.maskingWindowIndex;
        }
        return playbackInfo2.timeline.getPeriodByUid(playbackInfo2.periodId.periodUid, this.period).windowIndex;
    }

    private long getContentPositionInternal(PlaybackInfo playbackInfo2) {
        if (!playbackInfo2.periodId.isAd()) {
            return Util.usToMs(getCurrentPositionUsInternal(playbackInfo2));
        }
        playbackInfo2.timeline.getPeriodByUid(playbackInfo2.periodId.periodUid, this.period);
        if (playbackInfo2.requestedContentPositionUs == C.TIME_UNSET) {
            return playbackInfo2.timeline.getWindow(getCurrentWindowIndexInternal(playbackInfo2), this.window).getDefaultPositionMs();
        }
        return this.period.getPositionInWindowMs() + Util.usToMs(playbackInfo2.requestedContentPositionUs);
    }

    private long getCurrentPositionUsInternal(PlaybackInfo playbackInfo2) {
        long j;
        if (playbackInfo2.timeline.isEmpty()) {
            return Util.msToUs(this.maskingWindowPositionMs);
        }
        if (playbackInfo2.sleepingForOffload) {
            j = playbackInfo2.getEstimatedPositionUs();
        } else {
            j = playbackInfo2.positionUs;
        }
        if (playbackInfo2.periodId.isAd()) {
            return j;
        }
        return periodPositionUsToWindowPositionUs(playbackInfo2.timeline, playbackInfo2.periodId, j);
    }

    private List<MediaSource> createMediaSources(List<MediaItem> list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(this.mediaSourceFactory.createMediaSource(list.get(i)));
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    /* renamed from: handlePlaybackInfo */
    public void m466lambda$new$1$androidxmedia3exoplayerExoPlayerImpl(ExoPlayerImplInternal.PlaybackInfoUpdate playbackInfoUpdate) {
        boolean z;
        this.pendingOperationAcks -= playbackInfoUpdate.operationAcks;
        boolean z2 = true;
        if (playbackInfoUpdate.positionDiscontinuity) {
            this.pendingDiscontinuityReason = playbackInfoUpdate.discontinuityReason;
            this.pendingDiscontinuity = true;
        }
        if (this.pendingOperationAcks == 0) {
            Timeline timeline = playbackInfoUpdate.playbackInfo.timeline;
            if (!this.playbackInfo.timeline.isEmpty() && timeline.isEmpty()) {
                this.maskingWindowIndex = -1;
                this.maskingWindowPositionMs = 0;
                this.maskingPeriodIndex = 0;
            }
            if (!timeline.isEmpty()) {
                List<Timeline> childTimelines = ((PlaylistTimeline) timeline).getChildTimelines();
                Assertions.checkState(childTimelines.size() == this.mediaSourceHolderSnapshots.size());
                for (int i = 0; i < childTimelines.size(); i++) {
                    this.mediaSourceHolderSnapshots.get(i).updateTimeline(childTimelines.get(i));
                }
            }
            boolean z3 = this.pendingDiscontinuity;
            long j = C.TIME_UNSET;
            if (z3) {
                if (playbackInfoUpdate.playbackInfo.periodId.equals(this.playbackInfo.periodId) && playbackInfoUpdate.playbackInfo.discontinuityStartPositionUs == this.playbackInfo.positionUs) {
                    z2 = false;
                }
                if (z2) {
                    if (timeline.isEmpty() || playbackInfoUpdate.playbackInfo.periodId.isAd()) {
                        j = playbackInfoUpdate.playbackInfo.discontinuityStartPositionUs;
                    } else {
                        j = periodPositionUsToWindowPositionUs(timeline, playbackInfoUpdate.playbackInfo.periodId, playbackInfoUpdate.playbackInfo.discontinuityStartPositionUs);
                    }
                }
                z = z2;
            } else {
                z = false;
            }
            this.pendingDiscontinuity = false;
            updatePlaybackInfo(playbackInfoUpdate.playbackInfo, 1, z, this.pendingDiscontinuityReason, j, -1, false);
        }
    }

    private void updatePlaybackInfo(PlaybackInfo playbackInfo2, int i, boolean z, int i2, long j, int i3, boolean z2) {
        PlaybackInfo playbackInfo3 = playbackInfo2;
        int i4 = i2;
        PlaybackInfo playbackInfo4 = this.playbackInfo;
        this.playbackInfo = playbackInfo3;
        boolean z3 = !playbackInfo4.timeline.equals(playbackInfo3.timeline);
        Pair<Boolean, Integer> evaluateMediaItemTransitionReason = evaluateMediaItemTransitionReason(playbackInfo2, playbackInfo4, z, i2, z3, z2);
        boolean booleanValue = ((Boolean) evaluateMediaItemTransitionReason.first).booleanValue();
        int intValue = ((Integer) evaluateMediaItemTransitionReason.second).intValue();
        MediaItem mediaItem = null;
        if (booleanValue) {
            if (!playbackInfo3.timeline.isEmpty()) {
                mediaItem = playbackInfo3.timeline.getWindow(playbackInfo3.timeline.getPeriodByUid(playbackInfo3.periodId.periodUid, this.period).windowIndex, this.window).mediaItem;
            }
            this.staticAndDynamicMediaMetadata = MediaMetadata.EMPTY;
        }
        if (booleanValue || !playbackInfo4.staticMetadata.equals(playbackInfo3.staticMetadata)) {
            this.staticAndDynamicMediaMetadata = this.staticAndDynamicMediaMetadata.buildUpon().populateFromMetadata(playbackInfo3.staticMetadata).build();
        }
        MediaMetadata buildUpdatedMediaMetadata = buildUpdatedMediaMetadata();
        boolean z4 = !buildUpdatedMediaMetadata.equals(this.mediaMetadata);
        this.mediaMetadata = buildUpdatedMediaMetadata;
        boolean z5 = playbackInfo4.playWhenReady != playbackInfo3.playWhenReady;
        boolean z6 = playbackInfo4.playbackState != playbackInfo3.playbackState;
        if (z6 || z5) {
            updateWakeAndWifiLock();
        }
        boolean z7 = playbackInfo4.isLoading != playbackInfo3.isLoading;
        if (z7) {
            updatePriorityTaskManagerForIsLoadingChange(playbackInfo3.isLoading);
        }
        if (z3) {
            this.listeners.queueEvent(0, new ExoPlayerImpl$$ExternalSyntheticLambda1(playbackInfo3, i));
        }
        if (z) {
            this.listeners.queueEvent(11, new ExoPlayerImpl$$ExternalSyntheticLambda6(i4, getPreviousPositionInfo(i4, playbackInfo4, i3), getPositionInfo(j)));
        }
        if (booleanValue) {
            this.listeners.queueEvent(1, new ExoPlayerImpl$$ExternalSyntheticLambda7(mediaItem, intValue));
        }
        if (playbackInfo4.playbackError != playbackInfo3.playbackError) {
            this.listeners.queueEvent(10, new ExoPlayerImpl$$ExternalSyntheticLambda8(playbackInfo3));
            if (playbackInfo3.playbackError != null) {
                this.listeners.queueEvent(10, new ExoPlayerImpl$$ExternalSyntheticLambda9(playbackInfo3));
            }
        }
        if (playbackInfo4.trackSelectorResult != playbackInfo3.trackSelectorResult) {
            this.trackSelector.onSelectionActivated(playbackInfo3.trackSelectorResult.info);
            this.listeners.queueEvent(2, new ExoPlayerImpl$$ExternalSyntheticLambda10(playbackInfo3));
        }
        if (z4) {
            this.listeners.queueEvent(14, new ExoPlayerImpl$$ExternalSyntheticLambda12(this.mediaMetadata));
        }
        if (z7) {
            this.listeners.queueEvent(3, new ExoPlayerImpl$$ExternalSyntheticLambda13(playbackInfo3));
        }
        if (z6 || z5) {
            this.listeners.queueEvent(-1, new ExoPlayerImpl$$ExternalSyntheticLambda14(playbackInfo3));
        }
        if (z6) {
            this.listeners.queueEvent(4, new ExoPlayerImpl$$ExternalSyntheticLambda15(playbackInfo3));
        }
        if (z5 || playbackInfo4.playWhenReadyChangeReason != playbackInfo3.playWhenReadyChangeReason) {
            this.listeners.queueEvent(5, new ExoPlayerImpl$$ExternalSyntheticLambda2(playbackInfo3));
        }
        if (playbackInfo4.playbackSuppressionReason != playbackInfo3.playbackSuppressionReason) {
            this.listeners.queueEvent(6, new ExoPlayerImpl$$ExternalSyntheticLambda3(playbackInfo3));
        }
        if (playbackInfo4.isPlaying() != playbackInfo2.isPlaying()) {
            this.listeners.queueEvent(7, new ExoPlayerImpl$$ExternalSyntheticLambda4(playbackInfo3));
        }
        if (!playbackInfo4.playbackParameters.equals(playbackInfo3.playbackParameters)) {
            this.listeners.queueEvent(12, new ExoPlayerImpl$$ExternalSyntheticLambda5(playbackInfo3));
        }
        updateAvailableCommands();
        this.listeners.flushEvents();
        if (playbackInfo4.sleepingForOffload != playbackInfo3.sleepingForOffload) {
            Iterator<ExoPlayer.AudioOffloadListener> it = this.audioOffloadListeners.iterator();
            while (it.hasNext()) {
                it.next().onSleepingForOffloadChanged(playbackInfo3.sleepingForOffload);
            }
        }
    }

    static /* synthetic */ void lambda$updatePlaybackInfo$13(int i, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, Player.Listener listener) {
        listener.onPositionDiscontinuity(i);
        listener.onPositionDiscontinuity(positionInfo, positionInfo2, i);
    }

    static /* synthetic */ void lambda$updatePlaybackInfo$19(PlaybackInfo playbackInfo2, Player.Listener listener) {
        listener.onLoadingChanged(playbackInfo2.isLoading);
        listener.onIsLoadingChanged(playbackInfo2.isLoading);
    }

    private Player.PositionInfo getPreviousPositionInfo(int i, PlaybackInfo playbackInfo2, int i2) {
        int i3;
        Object obj;
        MediaItem mediaItem;
        int i4;
        Object obj2;
        long j;
        long j2;
        PlaybackInfo playbackInfo3 = playbackInfo2;
        Timeline.Period period2 = new Timeline.Period();
        if (!playbackInfo3.timeline.isEmpty()) {
            Object obj3 = playbackInfo3.periodId.periodUid;
            playbackInfo3.timeline.getPeriodByUid(obj3, period2);
            int i5 = period2.windowIndex;
            int indexOfPeriod = playbackInfo3.timeline.getIndexOfPeriod(obj3);
            Object obj4 = playbackInfo3.timeline.getWindow(i5, this.window).uid;
            mediaItem = this.window.mediaItem;
            obj = obj3;
            i3 = indexOfPeriod;
            obj2 = obj4;
            i4 = i5;
        } else {
            i4 = i2;
            obj2 = null;
            mediaItem = null;
            obj = null;
            i3 = -1;
        }
        if (i == 0) {
            if (playbackInfo3.periodId.isAd()) {
                j2 = period2.getAdDurationUs(playbackInfo3.periodId.adGroupIndex, playbackInfo3.periodId.adIndexInAdGroup);
                j = getRequestedContentPositionUs(playbackInfo2);
                return new Player.PositionInfo(obj2, i4, mediaItem, obj, i3, Util.usToMs(j2), Util.usToMs(j), playbackInfo3.periodId.adGroupIndex, playbackInfo3.periodId.adIndexInAdGroup);
            } else if (playbackInfo3.periodId.nextAdGroupIndex != -1) {
                j2 = getRequestedContentPositionUs(this.playbackInfo);
            } else {
                j2 = period2.positionInWindowUs + period2.durationUs;
            }
        } else if (playbackInfo3.periodId.isAd()) {
            j2 = playbackInfo3.positionUs;
            j = getRequestedContentPositionUs(playbackInfo2);
            return new Player.PositionInfo(obj2, i4, mediaItem, obj, i3, Util.usToMs(j2), Util.usToMs(j), playbackInfo3.periodId.adGroupIndex, playbackInfo3.periodId.adIndexInAdGroup);
        } else {
            j2 = period2.positionInWindowUs + playbackInfo3.positionUs;
        }
        j = j2;
        return new Player.PositionInfo(obj2, i4, mediaItem, obj, i3, Util.usToMs(j2), Util.usToMs(j), playbackInfo3.periodId.adGroupIndex, playbackInfo3.periodId.adIndexInAdGroup);
    }

    private Player.PositionInfo getPositionInfo(long j) {
        int i;
        Object obj;
        MediaItem mediaItem;
        Object obj2;
        int currentMediaItemIndex = getCurrentMediaItemIndex();
        if (!this.playbackInfo.timeline.isEmpty()) {
            Object obj3 = this.playbackInfo.periodId.periodUid;
            this.playbackInfo.timeline.getPeriodByUid(obj3, this.period);
            i = this.playbackInfo.timeline.getIndexOfPeriod(obj3);
            obj2 = this.playbackInfo.timeline.getWindow(currentMediaItemIndex, this.window).uid;
            mediaItem = this.window.mediaItem;
            obj = obj3;
        } else {
            mediaItem = null;
            obj = null;
            i = -1;
            obj2 = null;
        }
        long usToMs = Util.usToMs(j);
        return new Player.PositionInfo(obj2, currentMediaItemIndex, mediaItem, obj, i, usToMs, this.playbackInfo.periodId.isAd() ? Util.usToMs(getRequestedContentPositionUs(this.playbackInfo)) : usToMs, this.playbackInfo.periodId.adGroupIndex, this.playbackInfo.periodId.adIndexInAdGroup);
    }

    private static long getRequestedContentPositionUs(PlaybackInfo playbackInfo2) {
        Timeline.Window window = new Timeline.Window();
        Timeline.Period period2 = new Timeline.Period();
        playbackInfo2.timeline.getPeriodByUid(playbackInfo2.periodId.periodUid, period2);
        if (playbackInfo2.requestedContentPositionUs == C.TIME_UNSET) {
            return playbackInfo2.timeline.getWindow(period2.windowIndex, window).getDefaultPositionUs();
        }
        return period2.getPositionInWindowUs() + playbackInfo2.requestedContentPositionUs;
    }

    private Pair<Boolean, Integer> evaluateMediaItemTransitionReason(PlaybackInfo playbackInfo2, PlaybackInfo playbackInfo3, boolean z, int i, boolean z2, boolean z3) {
        Timeline timeline = playbackInfo3.timeline;
        Timeline timeline2 = playbackInfo2.timeline;
        if (timeline2.isEmpty() && timeline.isEmpty()) {
            return new Pair<>(false, -1);
        }
        int i2 = 3;
        if (timeline2.isEmpty() != timeline.isEmpty()) {
            return new Pair<>(true, 3);
        }
        if (!timeline.getWindow(timeline.getPeriodByUid(playbackInfo3.periodId.periodUid, this.period).windowIndex, this.window).uid.equals(timeline2.getWindow(timeline2.getPeriodByUid(playbackInfo2.periodId.periodUid, this.period).windowIndex, this.window).uid)) {
            if (z && i == 0) {
                i2 = 1;
            } else if (z && i == 1) {
                i2 = 2;
            } else if (!z2) {
                throw new IllegalStateException();
            }
            return new Pair<>(true, Integer.valueOf(i2));
        } else if (z && i == 0 && playbackInfo3.periodId.windowSequenceNumber < playbackInfo2.periodId.windowSequenceNumber) {
            return new Pair<>(true, 0);
        } else {
            if (!z || i != 1 || !z3) {
                return new Pair<>(false, -1);
            }
            return new Pair<>(true, 2);
        }
    }

    private void updateAvailableCommands() {
        Player.Commands commands = this.availableCommands;
        Player.Commands availableCommands2 = Util.getAvailableCommands(this.wrappingPlayer, this.permanentAvailableCommands);
        this.availableCommands = availableCommands2;
        if (!availableCommands2.equals(commands)) {
            this.listeners.queueEvent(13, new ExoPlayerImpl$$ExternalSyntheticLambda22(this));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$updateAvailableCommands$26$androidx-media3-exoplayer-ExoPlayerImpl  reason: not valid java name */
    public /* synthetic */ void m469lambda$updateAvailableCommands$26$androidxmedia3exoplayerExoPlayerImpl(Player.Listener listener) {
        listener.onAvailableCommandsChanged(this.availableCommands);
    }

    private void setMediaSourcesInternal(List<MediaSource> list, int i, long j, boolean z) {
        int i2;
        long j2;
        int i3 = i;
        int currentWindowIndexInternal = getCurrentWindowIndexInternal(this.playbackInfo);
        long currentPosition = getCurrentPosition();
        boolean z2 = true;
        this.pendingOperationAcks++;
        if (!this.mediaSourceHolderSnapshots.isEmpty()) {
            removeMediaSourceHolders(0, this.mediaSourceHolderSnapshots.size());
        }
        List<MediaSourceList.MediaSourceHolder> addMediaSourceHolders = addMediaSourceHolders(0, list);
        Timeline createMaskingTimeline = createMaskingTimeline();
        if (createMaskingTimeline.isEmpty() || i3 < createMaskingTimeline.getWindowCount()) {
            long j3 = j;
            if (z) {
                int firstWindowIndex = createMaskingTimeline.getFirstWindowIndex(this.shuffleModeEnabled);
                j2 = C.TIME_UNSET;
                i2 = firstWindowIndex;
            } else if (i3 == -1) {
                i2 = currentWindowIndexInternal;
                j2 = currentPosition;
            } else {
                i2 = i3;
                j2 = j3;
            }
            PlaybackInfo maskTimelineAndPosition = maskTimelineAndPosition(this.playbackInfo, createMaskingTimeline, maskWindowPositionMsOrGetPeriodPositionUs(createMaskingTimeline, i2, j2));
            int i4 = maskTimelineAndPosition.playbackState;
            if (!(i2 == -1 || maskTimelineAndPosition.playbackState == 1)) {
                i4 = (createMaskingTimeline.isEmpty() || i2 >= createMaskingTimeline.getWindowCount()) ? 4 : 2;
            }
            PlaybackInfo copyWithPlaybackState = maskTimelineAndPosition.copyWithPlaybackState(i4);
            this.internalPlayer.setMediaSources(addMediaSourceHolders, i2, Util.msToUs(j2), this.shuffleOrder);
            if (this.playbackInfo.periodId.periodUid.equals(copyWithPlaybackState.periodId.periodUid) || this.playbackInfo.timeline.isEmpty()) {
                z2 = false;
            }
            updatePlaybackInfo(copyWithPlaybackState, 0, z2, 4, getCurrentPositionUsInternal(copyWithPlaybackState), -1, false);
            return;
        }
        throw new IllegalSeekPositionException(createMaskingTimeline, i3, j);
    }

    private List<MediaSourceList.MediaSourceHolder> addMediaSourceHolders(int i, List<MediaSource> list) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            MediaSourceList.MediaSourceHolder mediaSourceHolder = new MediaSourceList.MediaSourceHolder(list.get(i2), this.useLazyPreparation);
            arrayList.add(mediaSourceHolder);
            this.mediaSourceHolderSnapshots.add(i2 + i, new MediaSourceHolderSnapshot(mediaSourceHolder.uid, mediaSourceHolder.mediaSource));
        }
        this.shuffleOrder = this.shuffleOrder.cloneAndInsert(i, arrayList.size());
        return arrayList;
    }

    private PlaybackInfo addMediaSourcesInternal(PlaybackInfo playbackInfo2, int i, List<MediaSource> list) {
        Timeline timeline = playbackInfo2.timeline;
        this.pendingOperationAcks++;
        List<MediaSourceList.MediaSourceHolder> addMediaSourceHolders = addMediaSourceHolders(i, list);
        Timeline createMaskingTimeline = createMaskingTimeline();
        PlaybackInfo maskTimelineAndPosition = maskTimelineAndPosition(playbackInfo2, createMaskingTimeline, getPeriodPositionUsAfterTimelineChanged(timeline, createMaskingTimeline, getCurrentWindowIndexInternal(playbackInfo2), getContentPositionInternal(playbackInfo2)));
        this.internalPlayer.addMediaSources(i, addMediaSourceHolders, this.shuffleOrder);
        return maskTimelineAndPosition;
    }

    private PlaybackInfo removeMediaItemsInternal(PlaybackInfo playbackInfo2, int i, int i2) {
        int currentWindowIndexInternal = getCurrentWindowIndexInternal(playbackInfo2);
        long contentPositionInternal = getContentPositionInternal(playbackInfo2);
        Timeline timeline = playbackInfo2.timeline;
        int size = this.mediaSourceHolderSnapshots.size();
        boolean z = true;
        this.pendingOperationAcks++;
        removeMediaSourceHolders(i, i2);
        Timeline createMaskingTimeline = createMaskingTimeline();
        PlaybackInfo maskTimelineAndPosition = maskTimelineAndPosition(playbackInfo2, createMaskingTimeline, getPeriodPositionUsAfterTimelineChanged(timeline, createMaskingTimeline, currentWindowIndexInternal, contentPositionInternal));
        if (maskTimelineAndPosition.playbackState == 1 || maskTimelineAndPosition.playbackState == 4 || i >= i2 || i2 != size || currentWindowIndexInternal < maskTimelineAndPosition.timeline.getWindowCount()) {
            z = false;
        }
        if (z) {
            maskTimelineAndPosition = maskTimelineAndPosition.copyWithPlaybackState(4);
        }
        this.internalPlayer.removeMediaSources(i, i2, this.shuffleOrder);
        return maskTimelineAndPosition;
    }

    private void removeMediaSourceHolders(int i, int i2) {
        for (int i3 = i2 - 1; i3 >= i; i3--) {
            this.mediaSourceHolderSnapshots.remove(i3);
        }
        this.shuffleOrder = this.shuffleOrder.cloneAndRemove(i, i2);
    }

    private Timeline createMaskingTimeline() {
        return new PlaylistTimeline(this.mediaSourceHolderSnapshots, this.shuffleOrder);
    }

    private PlaybackInfo maskTimelineAndPosition(PlaybackInfo playbackInfo2, Timeline timeline, Pair<Object, Long> pair) {
        List list;
        int i;
        long j;
        Timeline timeline2 = timeline;
        Pair<Object, Long> pair2 = pair;
        Assertions.checkArgument(timeline.isEmpty() || pair2 != null);
        Timeline timeline3 = playbackInfo2.timeline;
        long contentPositionInternal = getContentPositionInternal(playbackInfo2);
        PlaybackInfo copyWithTimeline = playbackInfo2.copyWithTimeline(timeline);
        if (timeline.isEmpty()) {
            MediaSource.MediaPeriodId dummyPeriodForEmptyTimeline = PlaybackInfo.getDummyPeriodForEmptyTimeline();
            long msToUs = Util.msToUs(this.maskingWindowPositionMs);
            PlaybackInfo copyWithLoadingMediaPeriodId = copyWithTimeline.copyWithNewPosition(dummyPeriodForEmptyTimeline, msToUs, msToUs, msToUs, 0, TrackGroupArray.EMPTY, this.emptyTrackSelectorResult, ImmutableList.of()).copyWithLoadingMediaPeriodId(dummyPeriodForEmptyTimeline);
            copyWithLoadingMediaPeriodId.bufferedPositionUs = copyWithLoadingMediaPeriodId.positionUs;
            return copyWithLoadingMediaPeriodId;
        }
        Object obj = copyWithTimeline.periodId.periodUid;
        boolean z = !obj.equals(((Pair) Util.castNonNull(pair)).first);
        MediaSource.MediaPeriodId mediaPeriodId = z ? new MediaSource.MediaPeriodId(pair2.first) : copyWithTimeline.periodId;
        long longValue = ((Long) pair2.second).longValue();
        long msToUs2 = Util.msToUs(contentPositionInternal);
        if (!timeline3.isEmpty()) {
            msToUs2 -= timeline3.getPeriodByUid(obj, this.period).getPositionInWindowUs();
        }
        if (z || longValue < msToUs2) {
            long j2 = longValue;
            MediaSource.MediaPeriodId mediaPeriodId2 = mediaPeriodId;
            Assertions.checkState(!mediaPeriodId2.isAd());
            TrackGroupArray trackGroupArray = z ? TrackGroupArray.EMPTY : copyWithTimeline.trackGroups;
            MediaSource.MediaPeriodId mediaPeriodId3 = mediaPeriodId2;
            TrackSelectorResult trackSelectorResult = z ? this.emptyTrackSelectorResult : copyWithTimeline.trackSelectorResult;
            if (z) {
                list = ImmutableList.of();
            } else {
                list = copyWithTimeline.staticMetadata;
            }
            PlaybackInfo copyWithLoadingMediaPeriodId2 = copyWithTimeline.copyWithNewPosition(mediaPeriodId3, j2, j2, j2, 0, trackGroupArray, trackSelectorResult, list).copyWithLoadingMediaPeriodId(mediaPeriodId3);
            copyWithLoadingMediaPeriodId2.bufferedPositionUs = j2;
            return copyWithLoadingMediaPeriodId2;
        }
        if (i == 0) {
            int indexOfPeriod = timeline2.getIndexOfPeriod(copyWithTimeline.loadingMediaPeriodId.periodUid);
            if (indexOfPeriod == -1 || timeline2.getPeriod(indexOfPeriod, this.period).windowIndex != timeline2.getPeriodByUid(mediaPeriodId.periodUid, this.period).windowIndex) {
                timeline2.getPeriodByUid(mediaPeriodId.periodUid, this.period);
                if (mediaPeriodId.isAd()) {
                    j = this.period.getAdDurationUs(mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup);
                } else {
                    j = this.period.durationUs;
                }
                copyWithTimeline = copyWithTimeline.copyWithNewPosition(mediaPeriodId, copyWithTimeline.positionUs, copyWithTimeline.positionUs, copyWithTimeline.discontinuityStartPositionUs, j - copyWithTimeline.positionUs, copyWithTimeline.trackGroups, copyWithTimeline.trackSelectorResult, copyWithTimeline.staticMetadata).copyWithLoadingMediaPeriodId(mediaPeriodId);
                copyWithTimeline.bufferedPositionUs = j;
            }
        } else {
            MediaSource.MediaPeriodId mediaPeriodId4 = mediaPeriodId;
            Assertions.checkState(!mediaPeriodId4.isAd());
            long max = Math.max(0, copyWithTimeline.totalBufferedDurationUs - (longValue - msToUs2));
            long j3 = copyWithTimeline.bufferedPositionUs;
            if (copyWithTimeline.loadingMediaPeriodId.equals(copyWithTimeline.periodId)) {
                j3 = longValue + max;
            }
            copyWithTimeline = copyWithTimeline.copyWithNewPosition(mediaPeriodId4, longValue, longValue, longValue, max, copyWithTimeline.trackGroups, copyWithTimeline.trackSelectorResult, copyWithTimeline.staticMetadata);
            copyWithTimeline.bufferedPositionUs = j3;
        }
        return copyWithTimeline;
    }

    private Pair<Object, Long> getPeriodPositionUsAfterTimelineChanged(Timeline timeline, Timeline timeline2, int i, long j) {
        Timeline timeline3 = timeline2;
        boolean isEmpty = timeline.isEmpty();
        long j2 = C.TIME_UNSET;
        int i2 = -1;
        if (isEmpty || timeline2.isEmpty()) {
            boolean z = !timeline.isEmpty() && timeline2.isEmpty();
            if (!z) {
                i2 = i;
            }
            if (!z) {
                j2 = j;
            }
            return maskWindowPositionMsOrGetPeriodPositionUs(timeline2, i2, j2);
        }
        Pair<Object, Long> periodPositionUs = timeline.getPeriodPositionUs(this.window, this.period, i, Util.msToUs(j));
        Object obj = ((Pair) Util.castNonNull(periodPositionUs)).first;
        if (timeline2.getIndexOfPeriod(obj) != -1) {
            return periodPositionUs;
        }
        int resolveSubsequentPeriod = ExoPlayerImplInternal.resolveSubsequentPeriod(this.window, this.period, this.repeatMode, this.shuffleModeEnabled, obj, timeline, timeline2);
        if (resolveSubsequentPeriod != -1) {
            return maskWindowPositionMsOrGetPeriodPositionUs(timeline2, resolveSubsequentPeriod, timeline2.getWindow(resolveSubsequentPeriod, this.window).getDefaultPositionMs());
        }
        return maskWindowPositionMsOrGetPeriodPositionUs(timeline2, -1, C.TIME_UNSET);
    }

    private Pair<Object, Long> maskWindowPositionMsOrGetPeriodPositionUs(Timeline timeline, int i, long j) {
        if (timeline.isEmpty()) {
            this.maskingWindowIndex = i;
            if (j == C.TIME_UNSET) {
                j = 0;
            }
            this.maskingWindowPositionMs = j;
            this.maskingPeriodIndex = 0;
            return null;
        }
        if (i == -1 || i >= timeline.getWindowCount()) {
            i = timeline.getFirstWindowIndex(this.shuffleModeEnabled);
            j = timeline.getWindow(i, this.window).getDefaultPositionMs();
        }
        return timeline.getPeriodPositionUs(this.window, this.period, i, Util.msToUs(j));
    }

    private long periodPositionUsToWindowPositionUs(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, long j) {
        timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period);
        return j + this.period.getPositionInWindowUs();
    }

    private PlayerMessage createMessageInternal(PlayerMessage.Target target) {
        int currentWindowIndexInternal = getCurrentWindowIndexInternal(this.playbackInfo);
        ExoPlayerImplInternal exoPlayerImplInternal = this.internalPlayer;
        Timeline timeline = this.playbackInfo.timeline;
        if (currentWindowIndexInternal == -1) {
            currentWindowIndexInternal = 0;
        }
        return new PlayerMessage(exoPlayerImplInternal, target, timeline, currentWindowIndexInternal, this.clock, this.internalPlayer.getPlaybackLooper());
    }

    /* access modifiers changed from: private */
    public MediaMetadata buildUpdatedMediaMetadata() {
        Timeline currentTimeline = getCurrentTimeline();
        if (currentTimeline.isEmpty()) {
            return this.staticAndDynamicMediaMetadata;
        }
        return this.staticAndDynamicMediaMetadata.buildUpon().populate(currentTimeline.getWindow(getCurrentMediaItemIndex(), this.window).mediaItem.mediaMetadata).build();
    }

    private void removeSurfaceCallbacks() {
        if (this.sphericalGLSurfaceView != null) {
            createMessageInternal(this.frameMetadataListener).setType(10000).setPayload((Object) null).send();
            this.sphericalGLSurfaceView.removeVideoSurfaceListener(this.componentListener);
            this.sphericalGLSurfaceView = null;
        }
        TextureView textureView2 = this.textureView;
        if (textureView2 != null) {
            if (textureView2.getSurfaceTextureListener() != this.componentListener) {
                Log.w(TAG, "SurfaceTextureListener already unset or replaced.");
            } else {
                this.textureView.setSurfaceTextureListener((TextureView.SurfaceTextureListener) null);
            }
            this.textureView = null;
        }
        SurfaceHolder surfaceHolder2 = this.surfaceHolder;
        if (surfaceHolder2 != null) {
            surfaceHolder2.removeCallback(this.componentListener);
            this.surfaceHolder = null;
        }
    }

    /* access modifiers changed from: private */
    public void setSurfaceTextureInternal(SurfaceTexture surfaceTexture) {
        Surface surface = new Surface(surfaceTexture);
        setVideoOutputInternal(surface);
        this.ownedSurface = surface;
    }

    /* access modifiers changed from: private */
    public void setVideoOutputInternal(Object obj) {
        ArrayList<PlayerMessage> arrayList = new ArrayList<>();
        boolean z = false;
        for (Renderer renderer : this.renderers) {
            if (renderer.getTrackType() == 2) {
                arrayList.add(createMessageInternal(renderer).setType(1).setPayload(obj).send());
            }
        }
        Object obj2 = this.videoOutput;
        if (!(obj2 == null || obj2 == obj)) {
            try {
                for (PlayerMessage blockUntilDelivered : arrayList) {
                    blockUntilDelivered.blockUntilDelivered(this.detachSurfaceTimeoutMs);
                }
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            } catch (TimeoutException unused2) {
                z = true;
            }
            Object obj3 = this.videoOutput;
            Surface surface = this.ownedSurface;
            if (obj3 == surface) {
                surface.release();
                this.ownedSurface = null;
            }
        }
        this.videoOutput = obj;
        if (z) {
            stopInternal(ExoPlaybackException.createForUnexpected(new ExoTimeoutException(3), 1003));
        }
    }

    private void setNonVideoOutputSurfaceHolderInternal(SurfaceHolder surfaceHolder2) {
        this.surfaceHolderSurfaceIsVideoOutput = false;
        this.surfaceHolder = surfaceHolder2;
        surfaceHolder2.addCallback(this.componentListener);
        Surface surface = this.surfaceHolder.getSurface();
        if (surface == null || !surface.isValid()) {
            maybeNotifySurfaceSizeChanged(0, 0);
            return;
        }
        Rect surfaceFrame = this.surfaceHolder.getSurfaceFrame();
        maybeNotifySurfaceSizeChanged(surfaceFrame.width(), surfaceFrame.height());
    }

    /* access modifiers changed from: private */
    public void maybeNotifySurfaceSizeChanged(int i, int i2) {
        if (i != this.surfaceSize.getWidth() || i2 != this.surfaceSize.getHeight()) {
            this.surfaceSize = new Size(i, i2);
            this.listeners.sendEvent(24, new ExoPlayerImpl$$ExternalSyntheticLambda19(i, i2));
            sendRendererMessage(2, 14, new Size(i, i2));
        }
    }

    /* access modifiers changed from: private */
    public void sendVolumeToRenderers() {
        sendRendererMessage(1, 2, Float.valueOf(this.volume * this.audioFocusManager.getVolumeMultiplier()));
    }

    /* access modifiers changed from: private */
    public void updatePlayWhenReady(boolean z, int i, int i2) {
        boolean z2 = z && i != -1;
        int computePlaybackSuppressionReason = computePlaybackSuppressionReason(z2, i);
        if (this.playbackInfo.playWhenReady != z2 || this.playbackInfo.playbackSuppressionReason != computePlaybackSuppressionReason || this.playbackInfo.playWhenReadyChangeReason != i2) {
            updatePlaybackInfoForPlayWhenReadyAndSuppressionReasonStates(z2, i2, computePlaybackSuppressionReason);
        }
    }

    /* access modifiers changed from: private */
    public void updatePlaybackInfoForPlayWhenReadyAndSuppressionReasonStates(boolean z, int i, int i2) {
        PlaybackInfo playbackInfo2;
        this.pendingOperationAcks++;
        if (this.playbackInfo.sleepingForOffload) {
            playbackInfo2 = this.playbackInfo.copyWithEstimatedPosition();
        } else {
            playbackInfo2 = this.playbackInfo;
        }
        PlaybackInfo copyWithPlayWhenReady = playbackInfo2.copyWithPlayWhenReady(z, i, i2);
        this.internalPlayer.setPlayWhenReady(z, i, i2);
        updatePlaybackInfo(copyWithPlayWhenReady, 0, false, 5, C.TIME_UNSET, -1, false);
    }

    private int computePlaybackSuppressionReason(boolean z, int i) {
        if (i == 0) {
            return 1;
        }
        if (!this.suppressPlaybackOnUnsuitableOutput) {
            return 0;
        }
        if (z && !hasSupportedAudioOutput()) {
            return 3;
        }
        if (z || this.playbackInfo.playbackSuppressionReason != 3) {
            return 0;
        }
        return 3;
    }

    /* access modifiers changed from: private */
    public boolean hasSupportedAudioOutput() {
        AudioManager audioManager2;
        SuitableOutputChecker suitableOutputChecker2;
        if (Util.SDK_INT >= 35 && (suitableOutputChecker2 = this.suitableOutputChecker) != null) {
            return suitableOutputChecker2.isSelectedOutputSuitableForPlayback();
        }
        if (Util.SDK_INT < 23 || (audioManager2 = this.audioManager) == null) {
            return true;
        }
        return Api23.isSuitableExternalAudioOutputPresentInAudioDeviceInfoList(this.applicationContext, audioManager2.getDevices(2));
    }

    /* access modifiers changed from: private */
    public void updateWakeAndWifiLock() {
        int playbackState = getPlaybackState();
        boolean z = true;
        if (playbackState != 1) {
            if (playbackState == 2 || playbackState == 3) {
                boolean isSleepingForOffload = isSleepingForOffload();
                WakeLockManager wakeLockManager2 = this.wakeLockManager;
                if (!getPlayWhenReady() || isSleepingForOffload) {
                    z = false;
                }
                wakeLockManager2.setStayAwake(z);
                this.wifiLockManager.setStayAwake(getPlayWhenReady());
                return;
            } else if (playbackState != 4) {
                throw new IllegalStateException();
            }
        }
        this.wakeLockManager.setStayAwake(false);
        this.wifiLockManager.setStayAwake(false);
    }

    private void verifyApplicationThread() {
        this.constructorFinished.blockUninterruptible();
        if (Thread.currentThread() != getApplicationLooper().getThread()) {
            String formatInvariant = Util.formatInvariant("Player is accessed on the wrong thread.\nCurrent thread: '%s'\nExpected thread: '%s'\nSee https://developer.android.com/guide/topics/media/issues/player-accessed-on-wrong-thread", Thread.currentThread().getName(), getApplicationLooper().getThread().getName());
            if (!this.throwsWhenUsingWrongThread) {
                Log.w(TAG, formatInvariant, this.hasNotifiedFullWrongThreadWarning ? null : new IllegalStateException());
                this.hasNotifiedFullWrongThreadWarning = true;
                return;
            }
            throw new IllegalStateException(formatInvariant);
        }
    }

    private void sendRendererMessage(int i, Object obj) {
        sendRendererMessage(-1, i, obj);
    }

    private void sendRendererMessage(int i, int i2, Object obj) {
        for (Renderer renderer : this.renderers) {
            if (i == -1 || renderer.getTrackType() == i) {
                createMessageInternal(renderer).setType(i2).setPayload(obj).send();
            }
        }
    }

    private void updatePriorityTaskManagerForIsLoadingChange(boolean z) {
        PriorityTaskManager priorityTaskManager2 = this.priorityTaskManager;
        if (priorityTaskManager2 == null) {
            return;
        }
        if (z && !this.isPriorityTaskManagerRegistered) {
            priorityTaskManager2.add(this.priority);
            this.isPriorityTaskManagerRegistered = true;
        } else if (!z && this.isPriorityTaskManagerRegistered) {
            priorityTaskManager2.remove(this.priority);
            this.isPriorityTaskManagerRegistered = false;
        }
    }

    private boolean canUpdateMediaSourcesWithMediaItems(int i, int i2, List<MediaItem> list) {
        if (i2 - i != list.size()) {
            return false;
        }
        for (int i3 = i; i3 < i2; i3++) {
            if (!this.mediaSourceHolderSnapshots.get(i3).mediaSource.canUpdateMediaItem(list.get(i3 - i))) {
                return false;
            }
        }
        return true;
    }

    private void updateMediaSourcesWithMediaItems(int i, int i2, List<MediaItem> list) {
        this.pendingOperationAcks++;
        this.internalPlayer.updateMediaSourcesWithMediaItems(i, i2, list);
        for (int i3 = i; i3 < i2; i3++) {
            MediaSourceHolderSnapshot mediaSourceHolderSnapshot = this.mediaSourceHolderSnapshots.get(i3);
            mediaSourceHolderSnapshot.updateTimeline(new TimelineWithUpdatedMediaItem(mediaSourceHolderSnapshot.getTimeline(), list.get(i3 - i)));
        }
        updatePlaybackInfo(this.playbackInfo.copyWithTimeline(createMaskingTimeline()), 0, false, 4, C.TIME_UNSET, -1, false);
    }

    /* access modifiers changed from: private */
    public void onSelectedOutputSuitabilityChanged(boolean z) {
        if (!z) {
            updatePlaybackInfoForPlayWhenReadyAndSuppressionReasonStates(this.playbackInfo.playWhenReady, 1, 3);
        } else if (this.playbackInfo.playbackSuppressionReason == 3) {
            updatePlaybackInfoForPlayWhenReadyAndSuppressionReasonStates(this.playbackInfo.playWhenReady, 1, 0);
        }
    }

    /* access modifiers changed from: private */
    public static DeviceInfo createDeviceInfo(StreamVolumeManager streamVolumeManager2) {
        int i = 0;
        DeviceInfo.Builder minVolume = new DeviceInfo.Builder(0).setMinVolume(streamVolumeManager2 != null ? streamVolumeManager2.getMinVolume() : 0);
        if (streamVolumeManager2 != null) {
            i = streamVolumeManager2.getMaxVolume();
        }
        return minVolume.setMaxVolume(i).build();
    }

    private static final class MediaSourceHolderSnapshot implements MediaSourceInfoHolder {
        /* access modifiers changed from: private */
        public final MediaSource mediaSource;
        private Timeline timeline;
        private final Object uid;

        public MediaSourceHolderSnapshot(Object obj, MaskingMediaSource maskingMediaSource) {
            this.uid = obj;
            this.mediaSource = maskingMediaSource;
            this.timeline = maskingMediaSource.getTimeline();
        }

        public Object getUid() {
            return this.uid;
        }

        public Timeline getTimeline() {
            return this.timeline;
        }

        public void updateTimeline(Timeline timeline2) {
            this.timeline = timeline2;
        }
    }

    private final class ComponentListener implements VideoRendererEventListener, AudioRendererEventListener, TextOutput, MetadataOutput, SurfaceHolder.Callback, TextureView.SurfaceTextureListener, SphericalGLSurfaceView.VideoSurfaceListener, AudioFocusManager.PlayerControl, AudioBecomingNoisyManager.EventListener, StreamVolumeManager.Listener, ExoPlayer.AudioOffloadListener {
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }

        private ComponentListener() {
        }

        public void onVideoEnabled(DecoderCounters decoderCounters) {
            DecoderCounters unused = ExoPlayerImpl.this.videoDecoderCounters = decoderCounters;
            ExoPlayerImpl.this.analyticsCollector.onVideoEnabled(decoderCounters);
        }

        public void onVideoDecoderInitialized(String str, long j, long j2) {
            ExoPlayerImpl.this.analyticsCollector.onVideoDecoderInitialized(str, j, j2);
        }

        public void onVideoInputFormatChanged(Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
            Format unused = ExoPlayerImpl.this.videoFormat = format;
            ExoPlayerImpl.this.analyticsCollector.onVideoInputFormatChanged(format, decoderReuseEvaluation);
        }

        public void onDroppedFrames(int i, long j) {
            ExoPlayerImpl.this.analyticsCollector.onDroppedFrames(i, j);
        }

        public void onVideoSizeChanged(VideoSize videoSize) {
            VideoSize unused = ExoPlayerImpl.this.videoSize = videoSize;
            ExoPlayerImpl.this.listeners.sendEvent(25, new ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda7(videoSize));
        }

        public void onRenderedFirstFrame(Object obj, long j) {
            ExoPlayerImpl.this.analyticsCollector.onRenderedFirstFrame(obj, j);
            if (ExoPlayerImpl.this.videoOutput == obj) {
                ExoPlayerImpl.this.listeners.sendEvent(26, new SimpleBasePlayer$$ExternalSyntheticLambda23());
            }
        }

        public void onVideoDecoderReleased(String str) {
            ExoPlayerImpl.this.analyticsCollector.onVideoDecoderReleased(str);
        }

        public void onVideoDisabled(DecoderCounters decoderCounters) {
            ExoPlayerImpl.this.analyticsCollector.onVideoDisabled(decoderCounters);
            Format unused = ExoPlayerImpl.this.videoFormat = null;
            DecoderCounters unused2 = ExoPlayerImpl.this.videoDecoderCounters = null;
        }

        public void onVideoFrameProcessingOffset(long j, int i) {
            ExoPlayerImpl.this.analyticsCollector.onVideoFrameProcessingOffset(j, i);
        }

        public void onVideoCodecError(Exception exc) {
            ExoPlayerImpl.this.analyticsCollector.onVideoCodecError(exc);
        }

        public void onAudioEnabled(DecoderCounters decoderCounters) {
            DecoderCounters unused = ExoPlayerImpl.this.audioDecoderCounters = decoderCounters;
            ExoPlayerImpl.this.analyticsCollector.onAudioEnabled(decoderCounters);
        }

        public void onAudioDecoderInitialized(String str, long j, long j2) {
            ExoPlayerImpl.this.analyticsCollector.onAudioDecoderInitialized(str, j, j2);
        }

        public void onAudioInputFormatChanged(Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
            Format unused = ExoPlayerImpl.this.audioFormat = format;
            ExoPlayerImpl.this.analyticsCollector.onAudioInputFormatChanged(format, decoderReuseEvaluation);
        }

        public void onAudioPositionAdvancing(long j) {
            ExoPlayerImpl.this.analyticsCollector.onAudioPositionAdvancing(j);
        }

        public void onAudioUnderrun(int i, long j, long j2) {
            ExoPlayerImpl.this.analyticsCollector.onAudioUnderrun(i, j, j2);
        }

        public void onAudioDecoderReleased(String str) {
            ExoPlayerImpl.this.analyticsCollector.onAudioDecoderReleased(str);
        }

        public void onAudioDisabled(DecoderCounters decoderCounters) {
            ExoPlayerImpl.this.analyticsCollector.onAudioDisabled(decoderCounters);
            Format unused = ExoPlayerImpl.this.audioFormat = null;
            DecoderCounters unused2 = ExoPlayerImpl.this.audioDecoderCounters = null;
        }

        public void onSkipSilenceEnabledChanged(boolean z) {
            if (ExoPlayerImpl.this.skipSilenceEnabled != z) {
                boolean unused = ExoPlayerImpl.this.skipSilenceEnabled = z;
                ExoPlayerImpl.this.listeners.sendEvent(23, new ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda6(z));
            }
        }

        public void onAudioSinkError(Exception exc) {
            ExoPlayerImpl.this.analyticsCollector.onAudioSinkError(exc);
        }

        public void onAudioCodecError(Exception exc) {
            ExoPlayerImpl.this.analyticsCollector.onAudioCodecError(exc);
        }

        public void onAudioTrackInitialized(AudioSink.AudioTrackConfig audioTrackConfig) {
            ExoPlayerImpl.this.analyticsCollector.onAudioTrackInitialized(audioTrackConfig);
        }

        public void onAudioTrackReleased(AudioSink.AudioTrackConfig audioTrackConfig) {
            ExoPlayerImpl.this.analyticsCollector.onAudioTrackReleased(audioTrackConfig);
        }

        public void onCues(List<Cue> list) {
            ExoPlayerImpl.this.listeners.sendEvent(27, new ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda0(list));
        }

        public void onCues(CueGroup cueGroup) {
            CueGroup unused = ExoPlayerImpl.this.currentCueGroup = cueGroup;
            ExoPlayerImpl.this.listeners.sendEvent(27, new ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda4(cueGroup));
        }

        public void onMetadata(Metadata metadata) {
            ExoPlayerImpl exoPlayerImpl = ExoPlayerImpl.this;
            MediaMetadata unused = exoPlayerImpl.staticAndDynamicMediaMetadata = exoPlayerImpl.staticAndDynamicMediaMetadata.buildUpon().populateFromMetadata(metadata).build();
            MediaMetadata access$1500 = ExoPlayerImpl.this.buildUpdatedMediaMetadata();
            if (!access$1500.equals(ExoPlayerImpl.this.mediaMetadata)) {
                MediaMetadata unused2 = ExoPlayerImpl.this.mediaMetadata = access$1500;
                ExoPlayerImpl.this.listeners.queueEvent(14, new ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda1(this));
            }
            ExoPlayerImpl.this.listeners.queueEvent(28, new ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda2(metadata));
            ExoPlayerImpl.this.listeners.flushEvents();
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onMetadata$4$androidx-media3-exoplayer-ExoPlayerImpl$ComponentListener  reason: not valid java name */
        public /* synthetic */ void m470lambda$onMetadata$4$androidxmedia3exoplayerExoPlayerImpl$ComponentListener(Player.Listener listener) {
            listener.onMediaMetadataChanged(ExoPlayerImpl.this.mediaMetadata);
        }

        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            if (ExoPlayerImpl.this.surfaceHolderSurfaceIsVideoOutput) {
                ExoPlayerImpl.this.setVideoOutputInternal(surfaceHolder.getSurface());
            }
        }

        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            ExoPlayerImpl.this.maybeNotifySurfaceSizeChanged(i2, i3);
        }

        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            if (ExoPlayerImpl.this.surfaceHolderSurfaceIsVideoOutput) {
                ExoPlayerImpl.this.setVideoOutputInternal((Object) null);
            }
            ExoPlayerImpl.this.maybeNotifySurfaceSizeChanged(0, 0);
        }

        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
            ExoPlayerImpl.this.setSurfaceTextureInternal(surfaceTexture);
            ExoPlayerImpl.this.maybeNotifySurfaceSizeChanged(i, i2);
        }

        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            ExoPlayerImpl.this.maybeNotifySurfaceSizeChanged(i, i2);
        }

        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            ExoPlayerImpl.this.setVideoOutputInternal((Object) null);
            ExoPlayerImpl.this.maybeNotifySurfaceSizeChanged(0, 0);
            return true;
        }

        public void onVideoSurfaceCreated(Surface surface) {
            ExoPlayerImpl.this.setVideoOutputInternal(surface);
        }

        public void onVideoSurfaceDestroyed(Surface surface) {
            ExoPlayerImpl.this.setVideoOutputInternal((Object) null);
        }

        public void setVolumeMultiplier(float f) {
            ExoPlayerImpl.this.sendVolumeToRenderers();
        }

        public void executePlayerCommand(int i) {
            ExoPlayerImpl.this.updatePlayWhenReady(ExoPlayerImpl.this.getPlayWhenReady(), i, ExoPlayerImpl.getPlayWhenReadyChangeReason(i));
        }

        public void onAudioBecomingNoisy() {
            ExoPlayerImpl.this.updatePlayWhenReady(false, -1, 3);
        }

        public void onStreamTypeChanged(int i) {
            DeviceInfo access$2500 = ExoPlayerImpl.createDeviceInfo(ExoPlayerImpl.this.streamVolumeManager);
            if (!access$2500.equals(ExoPlayerImpl.this.deviceInfo)) {
                DeviceInfo unused = ExoPlayerImpl.this.deviceInfo = access$2500;
                ExoPlayerImpl.this.listeners.sendEvent(29, new ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda5(access$2500));
            }
        }

        public void onStreamVolumeChanged(int i, boolean z) {
            ExoPlayerImpl.this.listeners.sendEvent(30, new ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda3(i, z));
        }

        public void onSleepingForOffloadChanged(boolean z) {
            ExoPlayerImpl.this.updateWakeAndWifiLock();
        }
    }

    private static final class FrameMetadataListener implements VideoFrameMetadataListener, CameraMotionListener, PlayerMessage.Target {
        public static final int MSG_SET_CAMERA_MOTION_LISTENER = 8;
        public static final int MSG_SET_SPHERICAL_SURFACE_VIEW = 10000;
        public static final int MSG_SET_VIDEO_FRAME_METADATA_LISTENER = 7;
        private CameraMotionListener cameraMotionListener;
        private CameraMotionListener internalCameraMotionListener;
        private VideoFrameMetadataListener internalVideoFrameMetadataListener;
        private VideoFrameMetadataListener videoFrameMetadataListener;

        private FrameMetadataListener() {
        }

        public void handleMessage(int i, Object obj) {
            if (i == 7) {
                this.videoFrameMetadataListener = (VideoFrameMetadataListener) obj;
            } else if (i == 8) {
                this.cameraMotionListener = (CameraMotionListener) obj;
            } else if (i == 10000) {
                SphericalGLSurfaceView sphericalGLSurfaceView = (SphericalGLSurfaceView) obj;
                if (sphericalGLSurfaceView == null) {
                    this.internalVideoFrameMetadataListener = null;
                    this.internalCameraMotionListener = null;
                    return;
                }
                this.internalVideoFrameMetadataListener = sphericalGLSurfaceView.getVideoFrameMetadataListener();
                this.internalCameraMotionListener = sphericalGLSurfaceView.getCameraMotionListener();
            }
        }

        public void onVideoFrameAboutToBeRendered(long j, long j2, Format format, MediaFormat mediaFormat) {
            VideoFrameMetadataListener videoFrameMetadataListener2 = this.internalVideoFrameMetadataListener;
            if (videoFrameMetadataListener2 != null) {
                videoFrameMetadataListener2.onVideoFrameAboutToBeRendered(j, j2, format, mediaFormat);
            }
            VideoFrameMetadataListener videoFrameMetadataListener3 = this.videoFrameMetadataListener;
            if (videoFrameMetadataListener3 != null) {
                videoFrameMetadataListener3.onVideoFrameAboutToBeRendered(j, j2, format, mediaFormat);
            }
        }

        public void onCameraMotion(long j, float[] fArr) {
            CameraMotionListener cameraMotionListener2 = this.internalCameraMotionListener;
            if (cameraMotionListener2 != null) {
                cameraMotionListener2.onCameraMotion(j, fArr);
            }
            CameraMotionListener cameraMotionListener3 = this.cameraMotionListener;
            if (cameraMotionListener3 != null) {
                cameraMotionListener3.onCameraMotion(j, fArr);
            }
        }

        public void onCameraMotionReset() {
            CameraMotionListener cameraMotionListener2 = this.internalCameraMotionListener;
            if (cameraMotionListener2 != null) {
                cameraMotionListener2.onCameraMotionReset();
            }
            CameraMotionListener cameraMotionListener3 = this.cameraMotionListener;
            if (cameraMotionListener3 != null) {
                cameraMotionListener3.onCameraMotionReset();
            }
        }
    }

    private static final class Api31 {
        private Api31() {
        }

        public static PlayerId registerMediaMetricsListener(Context context, ExoPlayerImpl exoPlayerImpl, boolean z, String str) {
            MediaMetricsListener create = MediaMetricsListener.create(context);
            if (create == null) {
                Log.w(ExoPlayerImpl.TAG, "MediaMetricsService unavailable.");
                return new PlayerId(LogSessionId.LOG_SESSION_ID_NONE, str);
            }
            if (z) {
                exoPlayerImpl.addAnalyticsListener(create);
            }
            return new PlayerId(create.getLogSessionId(), str);
        }
    }

    private static final class Api23 {
        private Api23() {
        }

        public static boolean isSuitableExternalAudioOutputPresentInAudioDeviceInfoList(Context context, AudioDeviceInfo[] audioDeviceInfoArr) {
            if (!Util.isWear(context)) {
                return true;
            }
            for (AudioDeviceInfo audioDeviceInfo : audioDeviceInfoArr) {
                if (audioDeviceInfo.getType() == 8 || audioDeviceInfo.getType() == 5 || audioDeviceInfo.getType() == 6 || audioDeviceInfo.getType() == 11 || audioDeviceInfo.getType() == 4 || audioDeviceInfo.getType() == 3) {
                    return true;
                }
                if (Util.SDK_INT >= 26 && audioDeviceInfo.getType() == 22) {
                    return true;
                }
                if (Util.SDK_INT >= 28 && audioDeviceInfo.getType() == 23) {
                    return true;
                }
                if (Util.SDK_INT >= 31 && (audioDeviceInfo.getType() == 26 || audioDeviceInfo.getType() == 27)) {
                    return true;
                }
                if (Util.SDK_INT >= 33 && audioDeviceInfo.getType() == 30) {
                    return true;
                }
            }
            return false;
        }

        public static void registerAudioDeviceCallback(AudioManager audioManager, AudioDeviceCallback audioDeviceCallback, Handler handler) {
            audioManager.registerAudioDeviceCallback(audioDeviceCallback, handler);
        }
    }

    private final class NoSuitableOutputPlaybackSuppressionAudioDeviceCallback extends AudioDeviceCallback {
        private NoSuitableOutputPlaybackSuppressionAudioDeviceCallback() {
        }

        public void onAudioDevicesAdded(AudioDeviceInfo[] audioDeviceInfoArr) {
            if (ExoPlayerImpl.this.hasSupportedAudioOutput() && ExoPlayerImpl.this.playbackInfo.playbackSuppressionReason == 3) {
                ExoPlayerImpl exoPlayerImpl = ExoPlayerImpl.this;
                exoPlayerImpl.updatePlaybackInfoForPlayWhenReadyAndSuppressionReasonStates(exoPlayerImpl.playbackInfo.playWhenReady, 1, 0);
            }
        }

        public void onAudioDevicesRemoved(AudioDeviceInfo[] audioDeviceInfoArr) {
            if (!ExoPlayerImpl.this.hasSupportedAudioOutput()) {
                ExoPlayerImpl exoPlayerImpl = ExoPlayerImpl.this;
                exoPlayerImpl.updatePlaybackInfoForPlayWhenReadyAndSuppressionReasonStates(exoPlayerImpl.playbackInfo.playWhenReady, 1, 3);
            }
        }
    }
}

package com.brentvatne.exoplayer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PictureInPictureParams;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.FragmentTransaction;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.text.Cue;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.HttpDataSource;
import androidx.media3.exoplayer.DefaultLoadControl;
import androidx.media3.exoplayer.DefaultRenderersFactory;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.RenderersFactory;
import androidx.media3.exoplayer.dash.DashUtil;
import androidx.media3.exoplayer.dash.manifest.AdaptationSet;
import androidx.media3.exoplayer.dash.manifest.DashManifest;
import androidx.media3.exoplayer.dash.manifest.Period;
import androidx.media3.exoplayer.dash.manifest.Representation;
import androidx.media3.exoplayer.drm.DefaultDrmSessionManager;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.drm.DrmSessionManager;
import androidx.media3.exoplayer.drm.ExoMediaDrm;
import androidx.media3.exoplayer.drm.FrameworkMediaDrm;
import androidx.media3.exoplayer.drm.HttpMediaDrmCallback;
import androidx.media3.exoplayer.drm.UnsupportedDrmException;
import androidx.media3.exoplayer.ima.ImaAdsLoader;
import androidx.media3.exoplayer.mediacodec.MediaCodecUtil;
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.MergingMediaSource;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.source.ads.AdsLoader;
import androidx.media3.exoplayer.source.ads.AdsMediaSource;
import androidx.media3.exoplayer.trackselection.AdaptiveTrackSelection;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.trackselection.MappingTrackSelector;
import androidx.media3.exoplayer.trackselection.TrackSelection;
import androidx.media3.exoplayer.upstream.BandwidthMeter;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.DefaultAllocator;
import androidx.media3.exoplayer.upstream.DefaultBandwidthMeter;
import androidx.media3.exoplayer.util.EventLogger;
import androidx.media3.extractor.metadata.emsg.EventMessage;
import androidx.media3.extractor.metadata.id3.Id3Frame;
import androidx.media3.extractor.metadata.id3.TextInformationFrame;
import androidx.media3.session.MediaSessionService;
import androidx.media3.ui.LegacyPlayerControlView;
import com.adsbynimbus.render.mraid.HostKt;
import com.brentvatne.common.api.AdsProps;
import com.brentvatne.common.api.BufferConfig;
import com.brentvatne.common.api.BufferingStrategy;
import com.brentvatne.common.api.ControlsConfig;
import com.brentvatne.common.api.DRMProps;
import com.brentvatne.common.api.SideLoadedTextTrack;
import com.brentvatne.common.api.Source;
import com.brentvatne.common.api.SubtitleStyle;
import com.brentvatne.common.api.TimedMetadata;
import com.brentvatne.common.api.Track;
import com.brentvatne.common.api.VideoTrack;
import com.brentvatne.common.react.VideoEventEmitter;
import com.brentvatne.common.toolbox.DebugLog;
import com.brentvatne.react.R;
import com.brentvatne.react.ReactNativeVideoManager;
import com.brentvatne.receiver.AudioBecomingNoisyReceiver;
import com.brentvatne.receiver.BecomingNoisyListener;
import com.brentvatne.receiver.PictureInPictureReceiver;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.uimanager.ThemedReactContext;
import com.google.ads.interactivemedia.v3.api.AdError;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent;
import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.google.ads.interactivemedia.v3.api.ImaSdkFactory;
import com.google.ads.interactivemedia.v3.api.ImaSdkSettings;
import com.google.common.collect.ImmutableList;
import com.locket.Locket.CdnGlideModelLoader$$ExternalSyntheticBackport0;
import io.invertase.firebase.functions.UniversalFirebaseFunctionsModule;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ReactExoplayerView extends FrameLayout implements LifecycleEventListener, Player.Listener, BandwidthMeter.EventListener, BecomingNoisyListener, DrmSessionEventListener, AdEvent.AdEventListener, AdErrorEvent.AdErrorListener {
    private static final CookieManager DEFAULT_COOKIE_MANAGER;
    public static final double DEFAULT_MAX_HEAP_ALLOCATION_PERCENT = 1.0d;
    public static final double DEFAULT_MIN_BUFFER_MEMORY_RESERVE = 0.0d;
    private static final int SHOW_PROGRESS = 1;
    private static final String TAG = "ReactExoplayerView";
    private static final String TAG_EVENT_LOGGER = "RNVExoplayer";
    private ImaAdsLoader adsLoader;
    private final AudioBecomingNoisyReceiver audioBecomingNoisyReceiver;
    private final AudioManager.OnAudioFocusChangeListener audioFocusChangeListener;
    /* access modifiers changed from: private */
    public final AudioManager audioManager;
    private AudioOutput audioOutput = AudioOutput.SPEAKER;
    private String audioTrackType;
    private String audioTrackValue;
    /* access modifiers changed from: private */
    public float audioVolume = 1.0f;
    private final DefaultBandwidthMeter bandwidthMeter;
    /* access modifiers changed from: private */
    public BufferingStrategy.BufferingStrategyEnum bufferingStrategy;
    private CmcdConfiguration.Factory cmcdConfigurationFactory;
    private final ReactExoplayerConfig config;
    private boolean controls;
    private ControlsConfig controlsConfig = new ControlsConfig();
    private EventLogger debugEventLogger = null;
    private boolean disableDisconnectError;
    private boolean disableFocus;
    private boolean enableDebug = false;
    public boolean enterPictureInPictureOnLeave = false;
    protected final VideoEventEmitter eventEmitter;
    /* access modifiers changed from: private */
    public Player.Listener eventListener;
    private ExoPlayerView exoPlayerView;
    private boolean focusable = true;
    private FullScreenPlayerView fullScreenPlayerView;
    /* access modifiers changed from: private */
    public boolean hasAudioFocus = false;
    private boolean hasDrmFailed = false;
    private final String instanceId = String.valueOf(UUID.randomUUID());
    private boolean isBuffering;
    private boolean isFullscreen;
    private boolean isInBackground;
    private boolean isPaused;
    private boolean isSeeking = false;
    private boolean isUsingContentResolution = false;
    private long lastBufferDuration = -1;
    private long lastDuration = -1;
    private long lastPos = -1;
    private boolean loadVideoStarted;
    /* access modifiers changed from: private */
    public float mProgressUpdateInterval = 250.0f;
    private boolean mReportBandwidth = false;
    private final Handler mainHandler;
    private Runnable mainRunnable;
    private int maxBitRate = 0;
    private DataSource.Factory mediaDataSourceFactory;
    /* access modifiers changed from: private */
    public boolean muted = false;
    private PictureInPictureParams.Builder pictureInPictureParamsBuilder;
    private final PictureInPictureReceiver pictureInPictureReceiver;
    private Runnable pipListenerUnsubscribe;
    protected boolean playInBackground = false;
    /* access modifiers changed from: private */
    public View playPauseControlContainer;
    /* access modifiers changed from: private */
    public PlaybackServiceBinder playbackServiceBinder;
    private ServiceConnection playbackServiceConnection;
    /* access modifiers changed from: private */
    public ExoPlayer player;
    /* access modifiers changed from: private */
    public LegacyPlayerControlView playerControlView;
    private boolean playerNeedsSource;
    private boolean preventsDisplaySleepDuringVideoPlayback = true;
    private final Handler progressHandler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            if (message.what == 1) {
                ReactExoplayerView.this.updateProgress();
                sendMessageDelayed(obtainMessage(1), (long) Math.round(ReactExoplayerView.this.mProgressUpdateInterval));
            }
        }
    };
    private float rate = 1.0f;
    private boolean repeat;
    private long resumePosition;
    private int resumeWindow;
    private ArrayList<Integer> rootViewChildrenOriginalVisibility = new ArrayList<>();
    private long seekPosition = -1;
    private boolean selectTrackWhenReady = false;
    private int selectedSpeedIndex = 1;
    private boolean showNotificationControls = false;
    /* access modifiers changed from: private */
    public Source source = new Source();
    private String textTrackType = "disabled";
    private String textTrackValue;
    /* access modifiers changed from: private */
    public final ThemedReactContext themedReactContext;
    private DefaultTrackSelector trackSelector;
    private boolean useCache = false;
    private String videoTrackType;
    private String videoTrackValue;
    private boolean viewHasDropped = false;

    static /* synthetic */ ExoMediaDrm lambda$buildDrmSessionManager$15(FrameworkMediaDrm frameworkMediaDrm, UUID uuid) {
        return frameworkMediaDrm;
    }

    static /* synthetic */ DrmSessionManager lambda$buildMediaSource$16(DrmSessionManager drmSessionManager, MediaItem mediaItem) {
        return drmSessionManager;
    }

    public void onIsLoadingChanged(boolean z) {
    }

    public void onTimelineChanged(Timeline timeline, int i) {
    }

    static {
        CookieManager cookieManager = new CookieManager();
        DEFAULT_COOKIE_MANAGER = cookieManager;
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);
    }

    public void setCmcdConfigurationFactory(CmcdConfiguration.Factory factory) {
        this.cmcdConfigurationFactory = factory;
    }

    /* access modifiers changed from: private */
    public void updateProgress() {
        if (this.player != null) {
            if (this.playerControlView != null && isPlayingAd() && this.controls) {
                this.playerControlView.hide();
            }
            long bufferedPercentage = (((long) this.player.getBufferedPercentage()) * this.player.getDuration()) / 100;
            long duration = this.player.getDuration();
            long currentPosition = this.player.getCurrentPosition();
            if (currentPosition > duration) {
                currentPosition = duration;
            }
            if (this.lastPos != currentPosition || this.lastBufferDuration != bufferedPercentage || this.lastDuration != duration) {
                this.lastPos = currentPosition;
                this.lastBufferDuration = bufferedPercentage;
                this.lastDuration = duration;
                this.eventEmitter.onVideoProgress.invoke(Long.valueOf(currentPosition), Long.valueOf(bufferedPercentage), Long.valueOf(this.player.getDuration()), Double.valueOf(getPositionInFirstPeriodMsForCurrentWindow(currentPosition)));
            }
        }
    }

    public double getPositionInFirstPeriodMsForCurrentWindow(long j) {
        Timeline.Window window = new Timeline.Window();
        if (!this.player.getCurrentTimeline().isEmpty()) {
            this.player.getCurrentTimeline().getWindow(this.player.getCurrentMediaItemIndex(), window);
        }
        return (double) (window.windowStartTimeMs + j);
    }

    public ReactExoplayerView(ThemedReactContext themedReactContext2, ReactExoplayerConfig reactExoplayerConfig) {
        super(themedReactContext2);
        this.themedReactContext = themedReactContext2;
        this.eventEmitter = new VideoEventEmitter();
        this.config = reactExoplayerConfig;
        this.bandwidthMeter = reactExoplayerConfig.getBandwidthMeter();
        if (this.pictureInPictureParamsBuilder == null) {
            this.pictureInPictureParamsBuilder = new PictureInPictureParams.Builder();
        }
        this.mainHandler = new Handler();
        createViews();
        this.audioManager = (AudioManager) themedReactContext2.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        themedReactContext2.addLifecycleEventListener(this);
        this.pipListenerUnsubscribe = PictureInPictureUtil.addLifecycleEventListener(themedReactContext2, this);
        this.audioBecomingNoisyReceiver = new AudioBecomingNoisyReceiver(themedReactContext2);
        this.audioFocusChangeListener = new OnAudioFocusChangedListener(themedReactContext2);
        this.pictureInPictureReceiver = new PictureInPictureReceiver(this, themedReactContext2);
    }

    private boolean isPlayingAd() {
        ExoPlayer exoPlayer = this.player;
        return exoPlayer != null && exoPlayer.isPlayingAd();
    }

    private void createViews() {
        CookieHandler cookieHandler = CookieHandler.getDefault();
        CookieManager cookieManager = DEFAULT_COOKIE_MANAGER;
        if (cookieHandler != cookieManager) {
            CookieHandler.setDefault(cookieManager);
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        ExoPlayerView exoPlayerView2 = new ExoPlayerView(getContext());
        this.exoPlayerView = exoPlayerView2;
        exoPlayerView2.addOnLayoutChangeListener(new ReactExoplayerView$$ExternalSyntheticLambda17(this));
        this.exoPlayerView.setLayoutParams(layoutParams);
        addView(this.exoPlayerView, 0, layoutParams);
        this.exoPlayerView.setFocusable(this.focusable);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createViews$0(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        PictureInPictureUtil.applySourceRectHint(this.themedReactContext, this.pictureInPictureParamsBuilder, this.exoPlayerView);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        cleanupPlaybackService();
        super.onDetachedFromWindow();
    }

    public void onHostResume() {
        if (!this.playInBackground || !this.isInBackground) {
            setPlayWhenReady(!this.isPaused);
        }
        this.isInBackground = false;
    }

    public void onHostPause() {
        boolean z = true;
        this.isInBackground = true;
        Activity currentActivity = this.themedReactContext.getCurrentActivity();
        boolean z2 = Util.SDK_INT >= 24 && currentActivity != null && currentActivity.isInPictureInPictureMode();
        if (Util.SDK_INT < 24 || currentActivity == null || !currentActivity.isInMultiWindowMode()) {
            z = false;
        }
        if (!this.playInBackground && !z2 && !z) {
            setPlayWhenReady(false);
        }
    }

    public void onHostDestroy() {
        cleanUpResources();
    }

    public void cleanUpResources() {
        stopPlayback();
        this.themedReactContext.removeLifecycleEventListener(this);
        releasePlayer();
        this.viewHasDropped = true;
    }

    public void onBandwidthSample(int i, long j, long j2) {
        if (this.mReportBandwidth) {
            ExoPlayer exoPlayer = this.player;
            String str = null;
            int i2 = 0;
            if (exoPlayer == null) {
                this.eventEmitter.onVideoBandwidthUpdate.invoke(Long.valueOf(j2), 0, 0, null);
                return;
            }
            Format videoFormat = exoPlayer.getVideoFormat();
            boolean z = videoFormat != null && (videoFormat.rotationDegrees == 90 || videoFormat.rotationDegrees == 270);
            int i3 = videoFormat != null ? z ? videoFormat.height : videoFormat.width : 0;
            if (videoFormat != null) {
                i2 = z ? videoFormat.width : videoFormat.height;
            }
            if (videoFormat != null) {
                str = videoFormat.id;
            }
            this.eventEmitter.onVideoBandwidthUpdate.invoke(Long.valueOf(j2), Integer.valueOf(i2), Integer.valueOf(i3), str);
        }
    }

    private void togglePlayerControlVisibility() {
        if (this.player != null) {
            reLayoutControls();
            if (this.playerControlView.isVisible()) {
                this.playerControlView.hide();
            } else {
                this.playerControlView.show();
            }
        }
    }

    private void initializePlayerControl() {
        if (this.playerControlView == null) {
            LegacyPlayerControlView legacyPlayerControlView = new LegacyPlayerControlView(getContext());
            this.playerControlView = legacyPlayerControlView;
            legacyPlayerControlView.addVisibilityListener(new LegacyPlayerControlView.VisibilityListener() {
                public void onVisibilityChange(int i) {
                    ReactExoplayerView.this.eventEmitter.onControlsVisibilityChange.invoke(Boolean.valueOf(i == 0));
                }
            });
        }
        this.playerControlView.setPlayer(this.player);
        this.playPauseControlContainer = this.playerControlView.findViewById(R.id.exo_play_pause_container);
        this.exoPlayerView.setOnClickListener(new ReactExoplayerView$$ExternalSyntheticLambda18(this));
        ((ImageButton) this.playerControlView.findViewById(R.id.exo_play)).setOnClickListener(new ReactExoplayerView$$ExternalSyntheticLambda19(this));
        ((ImageButton) this.playerControlView.findViewById(R.id.exo_rew)).setOnClickListener(new ReactExoplayerView$$ExternalSyntheticLambda20(this));
        ((ImageButton) this.playerControlView.findViewById(R.id.exo_ffwd)).setOnClickListener(new ReactExoplayerView$$ExternalSyntheticLambda21(this));
        ((ImageButton) this.playerControlView.findViewById(R.id.exo_pause)).setOnClickListener(new ReactExoplayerView$$ExternalSyntheticLambda22(this));
        ((ImageButton) this.playerControlView.findViewById(R.id.exo_settings)).setOnClickListener(new ReactExoplayerView$$ExternalSyntheticLambda23(this));
        ((ImageButton) this.playerControlView.findViewById(R.id.exo_fullscreen)).setOnClickListener(new ReactExoplayerView$$ExternalSyntheticLambda3(this));
        updateFullScreenButtonVisibility();
        refreshControlsStyles();
        AnonymousClass3 r0 = new Player.Listener() {
            public void onPlaybackStateChanged(int i) {
                View findViewById = ReactExoplayerView.this.playerControlView.findViewById(R.id.exo_play);
                View findViewById2 = ReactExoplayerView.this.playerControlView.findViewById(R.id.exo_pause);
                if (findViewById != null && findViewById.getVisibility() == 8) {
                    findViewById.setVisibility(4);
                }
                if (findViewById2 != null && findViewById2.getVisibility() == 8) {
                    findViewById2.setVisibility(4);
                }
                ReactExoplayerView reactExoplayerView = ReactExoplayerView.this;
                reactExoplayerView.reLayout(reactExoplayerView.playPauseControlContainer);
                ReactExoplayerView.this.player.removeListener(ReactExoplayerView.this.eventListener);
            }

            public void onPlayWhenReadyChanged(boolean z, int i) {
                ReactExoplayerView reactExoplayerView = ReactExoplayerView.this;
                reactExoplayerView.reLayout(reactExoplayerView.playPauseControlContainer);
                ReactExoplayerView.this.player.removeListener(ReactExoplayerView.this.eventListener);
            }
        };
        this.eventListener = r0;
        this.player.addListener(r0);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initializePlayerControl$1(View view) {
        if (!isPlayingAd()) {
            togglePlayerControlVisibility();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initializePlayerControl$2(View view) {
        ExoPlayer exoPlayer = this.player;
        if (exoPlayer != null && exoPlayer.getPlaybackState() == 4) {
            this.player.seekTo(0);
        }
        setPausedModifier(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initializePlayerControl$3(View view) {
        seekTo(this.player.getCurrentPosition() - ((long) this.controlsConfig.getSeekIncrementMS()));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initializePlayerControl$4(View view) {
        seekTo(this.player.getCurrentPosition() + ((long) this.controlsConfig.getSeekIncrementMS()));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initializePlayerControl$5(View view) {
        setPausedModifier(true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initializePlayerControl$6(View view) {
        openSettings();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initializePlayerControl$7(View view) {
        setFullscreen(!this.isFullscreen);
    }

    private void openSettings() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.themedReactContext);
        builder.setTitle(R.string.settings);
        builder.setItems(new String[]{this.themedReactContext.getString(R.string.playback_speed)}, new ReactExoplayerView$$ExternalSyntheticLambda13(this));
        builder.show();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$openSettings$8(DialogInterface dialogInterface, int i) {
        if (i == 0) {
            showPlaybackSpeedOptions();
        }
    }

    private void showPlaybackSpeedOptions() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.themedReactContext);
        builder.setTitle(R.string.select_playback_speed);
        builder.setSingleChoiceItems(new String[]{"0.5x", "1.0x", "1.5x", "2.0x"}, this.selectedSpeedIndex, new ReactExoplayerView$$ExternalSyntheticLambda6(this));
        builder.show();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showPlaybackSpeedOptions$9(DialogInterface dialogInterface, int i) {
        this.selectedSpeedIndex = i;
        setRateModifier(i != 0 ? i != 2 ? i != 3 ? 1.0f : 2.0f : 1.5f : 0.5f);
    }

    private void addPlayerControl() {
        if (this.playerControlView != null) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            this.playerControlView.setLayoutParams(layoutParams);
            int indexOfChild = indexOfChild(this.playerControlView);
            if (indexOfChild != -1) {
                removeViewAt(indexOfChild);
            }
            addView(this.playerControlView, 1, layoutParams);
            reLayout(this.playerControlView);
        }
    }

    /* access modifiers changed from: private */
    public void reLayout(View view) {
        if (view != null) {
            view.measure(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
            view.layout(view.getLeft(), view.getTop(), view.getMeasuredWidth(), view.getMeasuredHeight());
        }
    }

    private void refreshControlsStyles() {
        if (this.playerControlView != null && this.player != null && this.controls) {
            updateLiveContent();
            updatePlayPauseButtons();
            updateButtonVisibility(this.controlsConfig.getHideForward(), R.id.exo_ffwd);
            updateButtonVisibility(this.controlsConfig.getHideRewind(), R.id.exo_rew);
            updateButtonVisibility(this.controlsConfig.getHideNext(), R.id.exo_next);
            updateButtonVisibility(this.controlsConfig.getHidePrevious(), R.id.exo_prev);
            updateViewVisibility(this.playerControlView.findViewById(R.id.exo_fullscreen), this.controlsConfig.getHideFullscreen(), 8);
            updateViewVisibility(this.playerControlView.findViewById(R.id.exo_position), this.controlsConfig.getHidePosition(), 8);
            updateViewVisibility(this.playerControlView.findViewById(R.id.exo_progress), this.controlsConfig.getHideSeekBar(), 4);
            updateViewVisibility(this.playerControlView.findViewById(R.id.exo_duration), this.controlsConfig.getHideDuration(), 8);
            updateViewVisibility(this.playerControlView.findViewById(R.id.exo_settings), this.controlsConfig.getHideSettingButton(), 8);
        }
    }

    private void updateLiveContent() {
        boolean z;
        LinearLayout linearLayout = (LinearLayout) this.playerControlView.findViewById(R.id.exo_live_container);
        TextView textView = (TextView) this.playerControlView.findViewById(R.id.exo_live_label);
        Timeline currentTimeline = this.player.getCurrentTimeline();
        if (!currentTimeline.isEmpty()) {
            Timeline.Window window = new Timeline.Window();
            currentTimeline.getWindow(this.player.getCurrentMediaItemIndex(), window);
            z = window.isLive();
        } else {
            z = false;
        }
        if (!z || this.controlsConfig.getLiveLabel() == null) {
            linearLayout.setVisibility(8);
            return;
        }
        textView.setText(this.controlsConfig.getLiveLabel());
        linearLayout.setVisibility(0);
    }

    private void updatePlayPauseButtons() {
        ImageButton imageButton = (ImageButton) this.playerControlView.findViewById(R.id.exo_play);
        ImageButton imageButton2 = (ImageButton) this.playerControlView.findViewById(R.id.exo_pause);
        if (this.controlsConfig.getHidePlayPause()) {
            this.playPauseControlContainer.setAlpha(0.0f);
            imageButton.setClickable(false);
            imageButton2.setClickable(false);
            return;
        }
        this.playPauseControlContainer.setAlpha(1.0f);
        imageButton.setClickable(true);
        imageButton2.setClickable(true);
    }

    private void updateButtonVisibility(boolean z, int i) {
        ImageButton imageButton = (ImageButton) this.playerControlView.findViewById(i);
        if (z) {
            imageButton.setImageAlpha(0);
            imageButton.setClickable(false);
            return;
        }
        imageButton.setImageAlpha(255);
        imageButton.setClickable(true);
    }

    private void updateViewVisibility(View view, boolean z, int i) {
        if (z) {
            view.setVisibility(i);
        } else if (view.getVisibility() == i) {
            view.setVisibility(0);
        }
    }

    private void reLayoutControls() {
        reLayout(this.exoPlayerView);
        reLayout(this.playerControlView);
    }

    public boolean isUsingVideoABR() {
        String str = this.videoTrackType;
        return str == null || "auto".equals(str);
    }

    public void setDebug(boolean z) {
        this.enableDebug = z;
        refreshDebugState();
    }

    private void refreshDebugState() {
        ExoPlayer exoPlayer = this.player;
        if (exoPlayer != null) {
            if (this.enableDebug) {
                EventLogger eventLogger = new EventLogger(TAG_EVENT_LOGGER);
                this.debugEventLogger = eventLogger;
                this.player.addAnalyticsListener(eventLogger);
                return;
            }
            EventLogger eventLogger2 = this.debugEventLogger;
            if (eventLogger2 != null) {
                exoPlayer.removeAnalyticsListener(eventLogger2);
                this.debugEventLogger = null;
            }
        }
    }

    public void setViewType(int i) {
        this.exoPlayerView.updateSurfaceView(i);
    }

    private class RNVLoadControl extends DefaultLoadControl {
        private final int availableHeapInBytes;
        private final Runtime runtime;
        final /* synthetic */ ReactExoplayerView this$0;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public RNVLoadControl(com.brentvatne.exoplayer.ReactExoplayerView r14, androidx.media3.exoplayer.upstream.DefaultAllocator r15, com.brentvatne.common.api.BufferConfig r16) {
            /*
                r13 = this;
                r10 = r13
                r11 = r14
                r10.this$0 = r11
                int r0 = r16.getMinBufferMs()
                com.brentvatne.common.api.BufferConfig$Companion r1 = com.brentvatne.common.api.BufferConfig.Companion
                int r1 = r1.getBufferConfigPropUnsetInt()
                r2 = 50000(0xc350, float:7.0065E-41)
                if (r0 == r1) goto L_0x0019
                int r0 = r16.getMinBufferMs()
                r3 = r0
                goto L_0x001a
            L_0x0019:
                r3 = r2
            L_0x001a:
                int r0 = r16.getMaxBufferMs()
                com.brentvatne.common.api.BufferConfig$Companion r1 = com.brentvatne.common.api.BufferConfig.Companion
                int r1 = r1.getBufferConfigPropUnsetInt()
                if (r0 == r1) goto L_0x002c
                int r0 = r16.getMaxBufferMs()
                r4 = r0
                goto L_0x002d
            L_0x002c:
                r4 = r2
            L_0x002d:
                int r0 = r16.getBufferForPlaybackMs()
                com.brentvatne.common.api.BufferConfig$Companion r1 = com.brentvatne.common.api.BufferConfig.Companion
                int r1 = r1.getBufferConfigPropUnsetInt()
                if (r0 == r1) goto L_0x003e
                int r0 = r16.getBufferForPlaybackMs()
                goto L_0x0040
            L_0x003e:
                r0 = 2500(0x9c4, float:3.503E-42)
            L_0x0040:
                r5 = r0
                int r0 = r16.getBufferForPlaybackAfterRebufferMs()
                com.brentvatne.common.api.BufferConfig$Companion r1 = com.brentvatne.common.api.BufferConfig.Companion
                int r1 = r1.getBufferConfigPropUnsetInt()
                if (r0 == r1) goto L_0x0052
                int r0 = r16.getBufferForPlaybackAfterRebufferMs()
                goto L_0x0054
            L_0x0052:
                r0 = 5000(0x1388, float:7.006E-42)
            L_0x0054:
                r6 = r0
                r7 = -1
                r8 = 1
                int r0 = r16.getBackBufferDurationMs()
                com.brentvatne.common.api.BufferConfig$Companion r1 = com.brentvatne.common.api.BufferConfig.Companion
                int r1 = r1.getBufferConfigPropUnsetInt()
                if (r0 == r1) goto L_0x0068
                int r0 = r16.getBackBufferDurationMs()
                goto L_0x0069
            L_0x0068:
                r0 = 0
            L_0x0069:
                r9 = r0
                r12 = 0
                r0 = r13
                r1 = r15
                r2 = r3
                r3 = r4
                r4 = r5
                r5 = r6
                r6 = r7
                r7 = r8
                r8 = r9
                r9 = r12
                r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9)
                java.lang.Runtime r0 = java.lang.Runtime.getRuntime()
                r10.runtime = r0
                com.facebook.react.uimanager.ThemedReactContext r0 = r14.themedReactContext
                java.lang.String r1 = "activity"
                java.lang.Object r0 = r0.getSystemService(r1)
                android.app.ActivityManager r0 = (android.app.ActivityManager) r0
                double r1 = r16.getMaxHeapAllocationPercent()
                com.brentvatne.common.api.BufferConfig$Companion r3 = com.brentvatne.common.api.BufferConfig.Companion
                double r3 = r3.getBufferConfigPropUnsetDouble()
                int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
                if (r1 == 0) goto L_0x009d
                double r1 = r16.getMaxHeapAllocationPercent()
                goto L_0x009f
            L_0x009d:
                r1 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            L_0x009f:
                int r0 = r0.getMemoryClass()
                double r3 = (double) r0
                double r3 = r3 * r1
                r0 = 4652218415073722368(0x4090000000000000, double:1024.0)
                double r3 = r3 * r0
                double r3 = r3 * r0
                double r0 = java.lang.Math.floor(r3)
                int r0 = (int) r0
                r10.availableHeapInBytes = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.brentvatne.exoplayer.ReactExoplayerView.RNVLoadControl.<init>(com.brentvatne.exoplayer.ReactExoplayerView, androidx.media3.exoplayer.upstream.DefaultAllocator, com.brentvatne.common.api.BufferConfig):void");
        }

        public boolean shouldContinueLoading(long j, long j2, float f) {
            if (this.this$0.bufferingStrategy == BufferingStrategy.BufferingStrategyEnum.DisableBuffering) {
                return false;
            }
            if (this.this$0.bufferingStrategy == BufferingStrategy.BufferingStrategyEnum.DependingOnMemory) {
                int totalBytesAllocated = getAllocator().getTotalBytesAllocated();
                int i = this.availableHeapInBytes;
                if (i > 0 && totalBytesAllocated >= i) {
                    return false;
                }
                long j3 = j2 / 1000;
                if (((long) (this.this$0.source.getBufferConfig().getMinBufferMemoryReservePercent() != BufferConfig.Companion.getBufferConfigPropUnsetDouble() ? this.this$0.source.getBufferConfig().getMinBufferMemoryReservePercent() : 0.0d)) * this.runtime.maxMemory() > this.runtime.maxMemory() - (this.runtime.totalMemory() - this.runtime.freeMemory()) && j3 > 2000) {
                    return false;
                }
                if (this.runtime.freeMemory() == 0) {
                    DebugLog.w(ReactExoplayerView.TAG, "Free memory reached 0, forcing garbage collection");
                    this.runtime.gc();
                    return false;
                }
            }
            return super.shouldContinueLoading(j, j2, f);
        }
    }

    private void initializePlayer() {
        ReactExoplayerView$$ExternalSyntheticLambda9 reactExoplayerView$$ExternalSyntheticLambda9 = new ReactExoplayerView$$ExternalSyntheticLambda9(this, this.source, this, this.themedReactContext.getCurrentActivity());
        this.mainRunnable = reactExoplayerView$$ExternalSyntheticLambda9;
        this.mainHandler.postDelayed(reactExoplayerView$$ExternalSyntheticLambda9, 1);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initializePlayer$12(Source source2, ReactExoplayerView reactExoplayerView, Activity activity) {
        if (!this.viewHasDropped || source2 != this.source) {
            try {
                if (source2.getUri() != null) {
                    if (this.player == null) {
                        initializePlayerCore(reactExoplayerView);
                    }
                    if (this.source.isLocalAssetFile() || this.source.isAsset() || this.source.getBufferConfig().getCacheSize() <= 0) {
                        this.useCache = false;
                    } else {
                        RNVSimpleCache.INSTANCE.setSimpleCache(getContext(), this.source.getBufferConfig().getCacheSize());
                        this.useCache = true;
                    }
                    if (this.playerNeedsSource) {
                        this.exoPlayerView.updateShutterViewVisibility();
                        this.exoPlayerView.invalidateAspectRatio();
                        Executors.newSingleThreadExecutor().execute(new ReactExoplayerView$$ExternalSyntheticLambda7(this, source2, activity, reactExoplayerView));
                    } else if (source2 == this.source) {
                        initializePlayerSource(source2);
                    }
                }
            } catch (Exception e) {
                reactExoplayerView.playerNeedsSource = true;
                DebugLog.e(TAG, "Failed to initialize Player! 2");
                DebugLog.e(TAG, e.toString());
                e.printStackTrace();
                this.eventEmitter.onVideoError.invoke(e.toString(), e, "1001");
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initializePlayer$11(Source source2, Activity activity, ReactExoplayerView reactExoplayerView) {
        if (this.viewHasDropped && source2 == this.source) {
            return;
        }
        if (activity == null) {
            DebugLog.e(TAG, "Failed to initialize Player!, null activity");
            this.eventEmitter.onVideoError.invoke("Failed to initialize Player!", new Exception("Current Activity is null!"), "1001");
            return;
        }
        activity.runOnUiThread(new ReactExoplayerView$$ExternalSyntheticLambda12(this, source2, reactExoplayerView));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initializePlayer$10(Source source2, ReactExoplayerView reactExoplayerView) {
        if (!this.viewHasDropped || source2 != this.source) {
            try {
                initializePlayerSource(source2);
            } catch (Exception e) {
                reactExoplayerView.playerNeedsSource = true;
                DebugLog.e(TAG, "Failed to initialize Player! 1");
                DebugLog.e(TAG, e.toString());
                e.printStackTrace();
                this.eventEmitter.onVideoError.invoke(e.toString(), e, "1001");
            }
        }
    }

    public void getCurrentPosition(Promise promise) {
        ExoPlayer exoPlayer = this.player;
        if (exoPlayer != null) {
            promise.resolve(Float.valueOf(((float) exoPlayer.getCurrentPosition()) / 1000.0f));
        } else {
            promise.reject("PLAYER_NOT_AVAILABLE", "Player is not initialized.");
        }
    }

    private void initializePlayerCore(ReactExoplayerView reactExoplayerView) {
        DefaultTrackSelector defaultTrackSelector = new DefaultTrackSelector(getContext(), (ExoTrackSelection.Factory) new AdaptiveTrackSelection.Factory());
        reactExoplayerView.trackSelector = defaultTrackSelector;
        DefaultTrackSelector.Parameters.Builder buildUponParameters = this.trackSelector.buildUponParameters();
        int i = this.maxBitRate;
        if (i == 0) {
            i = Integer.MAX_VALUE;
        }
        defaultTrackSelector.setParameters(buildUponParameters.setMaxVideoBitrate(i));
        RNVLoadControl rNVLoadControl = new RNVLoadControl(this, new DefaultAllocator(true, 65536), this.source.getBufferConfig());
        DefaultRenderersFactory forceEnableMediaCodecAsynchronousQueueing = new DefaultRenderersFactory(getContext()).setExtensionRendererMode(0).setEnableDecoderFallback(true).forceEnableMediaCodecAsynchronousQueueing();
        DefaultMediaSourceFactory defaultMediaSourceFactory = new DefaultMediaSourceFactory(this.mediaDataSourceFactory);
        if (this.useCache) {
            defaultMediaSourceFactory.setDataSourceFactory(RNVSimpleCache.INSTANCE.getCacheFactory(buildHttpDataSourceFactory(true)));
        }
        defaultMediaSourceFactory.setLocalAdInsertionComponents(new ReactExoplayerView$$ExternalSyntheticLambda16(this), this.exoPlayerView);
        this.player = new ExoPlayer.Builder(getContext(), (RenderersFactory) forceEnableMediaCodecAsynchronousQueueing).setTrackSelector(reactExoplayerView.trackSelector).setBandwidthMeter(this.bandwidthMeter).setLoadControl(rNVLoadControl).setMediaSourceFactory(defaultMediaSourceFactory).build();
        ReactNativeVideoManager.Companion.getInstance().onInstanceCreated(this.instanceId, this.player);
        refreshDebugState();
        this.player.addListener(reactExoplayerView);
        this.player.setVolume(this.muted ? 0.0f : this.audioVolume * 1.0f);
        this.exoPlayerView.setPlayer(this.player);
        this.audioBecomingNoisyReceiver.setListener(reactExoplayerView);
        this.pictureInPictureReceiver.setListener();
        this.bandwidthMeter.addEventListener(new Handler(), reactExoplayerView);
        setPlayWhenReady(!this.isPaused);
        this.playerNeedsSource = true;
        this.player.setPlaybackParameters(new PlaybackParameters(this.rate, 1.0f));
        changeAudioOutput(this.audioOutput);
        if (this.showNotificationControls) {
            setupPlaybackService();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ AdsLoader lambda$initializePlayerCore$13(MediaItem.AdsConfiguration adsConfiguration) {
        return this.adsLoader;
    }

    private AdsMediaSource initializeAds(MediaSource mediaSource, Source source2) {
        Uri adTagUrl;
        AdsProps adsProps = source2.getAdsProps();
        Uri uri = source2.getUri();
        if (!(adsProps == null || uri == null || (adTagUrl = adsProps.getAdTagUrl()) == null)) {
            this.exoPlayerView.showAds();
            ImaAdsLoader.Builder adErrorListener = new ImaAdsLoader.Builder(this.themedReactContext).setAdEventListener(this).setAdErrorListener(this);
            if (adsProps.getAdLanguage() != null) {
                ImaSdkSettings createImaSdkSettings = ImaSdkFactory.getInstance().createImaSdkSettings();
                createImaSdkSettings.setLanguage(adsProps.getAdLanguage());
                adErrorListener.setImaSdkSettings(createImaSdkSettings);
            }
            ImaAdsLoader build = adErrorListener.build();
            this.adsLoader = build;
            build.setPlayer(this.player);
            if (this.adsLoader != null) {
                return new AdsMediaSource(mediaSource, new DataSpec(adTagUrl), ImmutableList.of(uri, adTagUrl), new DefaultMediaSourceFactory(this.mediaDataSourceFactory).setLocalAdInsertionComponents(new ReactExoplayerView$$ExternalSyntheticLambda15(this), this.exoPlayerView), this.adsLoader, this.exoPlayerView);
            }
        }
        this.exoPlayerView.hideAds();
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ AdsLoader lambda$initializeAds$14(MediaItem.AdsConfiguration adsConfiguration) {
        return this.adsLoader;
    }

    private DrmSessionManager initializePlayerDrm() {
        UUID drmUuid;
        int i;
        DRMProps drmProps = this.source.getDrmProps();
        if (!(drmProps == null || drmProps.getDrmType() == null || (drmUuid = Util.getDrmUuid(drmProps.getDrmType())) == null)) {
            try {
                DebugLog.w(TAG, "drm buildDrmSessionManager");
                return buildDrmSessionManager(drmUuid, drmProps);
            } catch (UnsupportedDrmException e) {
                if (Util.SDK_INT < 18) {
                    i = R.string.error_drm_not_supported;
                } else {
                    i = e.reason == 1 ? R.string.error_drm_unsupported_scheme : R.string.error_drm_unknown;
                }
                this.eventEmitter.onVideoError.invoke(getResources().getString(i), e, "3003");
            }
        }
        return null;
    }

    private void initializePlayerSource(Source source2) {
        ExoPlayer exoPlayer;
        if (source2.getUri() != null) {
            DrmSessionManager initializePlayerDrm = initializePlayerDrm();
            if (initializePlayerDrm != null || source2.getDrmProps() == null || source2.getDrmProps().getDrmType() == null) {
                MediaSource buildMediaSource = buildMediaSource(source2.getUri(), source2.getExtension(), initializePlayerDrm, (long) source2.getCropStartMs(), (long) source2.getCropEndMs());
                MediaSource mediaSource = (MediaSource) CdnGlideModelLoader$$ExternalSyntheticBackport0.m(initializeAds(buildMediaSource, source2), buildMediaSource);
                MediaSource buildTextSource = buildTextSource();
                if (buildTextSource != null) {
                    mediaSource = new MergingMediaSource(mediaSource, buildTextSource);
                }
                while (true) {
                    exoPlayer = this.player;
                    if (exoPlayer != null) {
                        break;
                    }
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        DebugLog.e(TAG, e.toString());
                    }
                }
                int i = this.resumeWindow;
                if (i != -1) {
                    exoPlayer.seekTo(i, this.resumePosition);
                    this.player.setMediaSource(mediaSource, false);
                } else if (source2.getStartPositionMs() > 0) {
                    this.player.setMediaSource(mediaSource, (long) source2.getStartPositionMs());
                } else {
                    this.player.setMediaSource(mediaSource, true);
                }
                this.player.prepare();
                this.playerNeedsSource = false;
                reLayoutControls();
                this.eventEmitter.onVideoLoadStart.invoke();
                this.loadVideoStarted = true;
                finishPlayerInitialization();
                return;
            }
            DebugLog.e(TAG, "Failed to initialize DRM Session Manager Framework!");
        }
    }

    private void finishPlayerInitialization() {
        initializePlayerControl();
        setControls(this.controls);
        applyModifiers();
    }

    private void setupPlaybackService() {
        if (this.showNotificationControls && this.player != null) {
            this.playbackServiceConnection = new ServiceConnection() {
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    ReactExoplayerView.this.playbackServiceBinder = (PlaybackServiceBinder) iBinder;
                    try {
                        Activity currentActivity = ReactExoplayerView.this.themedReactContext.getCurrentActivity();
                        if (currentActivity != null) {
                            ReactExoplayerView.this.playbackServiceBinder.getService().registerPlayer(ReactExoplayerView.this.player, currentActivity.getClass());
                        } else {
                            DebugLog.w(ReactExoplayerView.TAG, "Could not register ExoPlayer: currentActivity is null");
                        }
                    } catch (Exception e) {
                        DebugLog.e(ReactExoplayerView.TAG, "Could not register ExoPlayer: " + e.getMessage());
                    }
                }

                public void onServiceDisconnected(ComponentName componentName) {
                    try {
                        if (ReactExoplayerView.this.playbackServiceBinder != null) {
                            ReactExoplayerView.this.playbackServiceBinder.getService().unregisterPlayer(ReactExoplayerView.this.player);
                        }
                    } catch (Exception unused) {
                    }
                    ReactExoplayerView.this.playbackServiceBinder = null;
                }

                public void onNullBinding(ComponentName componentName) {
                    DebugLog.e(ReactExoplayerView.TAG, "Could not register ExoPlayer");
                }
            };
            Intent intent = new Intent(this.themedReactContext, VideoPlaybackService.class);
            intent.setAction(MediaSessionService.SERVICE_INTERFACE);
            this.themedReactContext.startForegroundService(intent);
            this.themedReactContext.bindService(intent, this.playbackServiceConnection, Build.VERSION.SDK_INT >= 29 ? FragmentTransaction.TRANSIT_FRAGMENT_OPEN : 1);
        }
    }

    private void cleanupPlaybackService() {
        PlaybackServiceBinder playbackServiceBinder2;
        try {
            if (!(this.player == null || (playbackServiceBinder2 = this.playbackServiceBinder) == null)) {
                playbackServiceBinder2.getService().unregisterPlayer(this.player);
            }
            this.playbackServiceBinder = null;
            ServiceConnection serviceConnection = this.playbackServiceConnection;
            if (serviceConnection != null) {
                this.themedReactContext.unbindService(serviceConnection);
            }
        } catch (Exception unused) {
            DebugLog.w(TAG, "Cloud not cleanup playback service");
        }
    }

    private DrmSessionManager buildDrmSessionManager(UUID uuid, DRMProps dRMProps) throws UnsupportedDrmException {
        return buildDrmSessionManager(uuid, dRMProps, 0);
    }

    private DrmSessionManager buildDrmSessionManager(UUID uuid, DRMProps dRMProps, int i) throws UnsupportedDrmException {
        if (Util.SDK_INT < 18) {
            return null;
        }
        try {
            HttpMediaDrmCallback httpMediaDrmCallback = new HttpMediaDrmCallback(dRMProps.getDrmLicenseServer(), buildHttpDataSourceFactory(false));
            String[] drmLicenseHeader = dRMProps.getDrmLicenseHeader();
            for (int i2 = 0; i2 < drmLicenseHeader.length - 1; i2 += 2) {
                httpMediaDrmCallback.setKeyRequestProperty(drmLicenseHeader[i2], drmLicenseHeader[i2 + 1]);
            }
            FrameworkMediaDrm newInstance = FrameworkMediaDrm.newInstance(uuid);
            if (this.hasDrmFailed) {
                newInstance.setPropertyString("securityLevel", "L3");
            }
            return new DefaultDrmSessionManager.Builder().setUuidAndExoMediaDrmProvider(uuid, new ReactExoplayerView$$ExternalSyntheticLambda14(newInstance)).setKeyRequestParameters((Map<String, String>) null).setMultiSession(dRMProps.getMultiDrm()).build(httpMediaDrmCallback);
        } catch (UnsupportedDrmException e) {
            throw e;
        } catch (Exception e2) {
            if (i < 3) {
                return buildDrmSessionManager(uuid, dRMProps, i + 1);
            }
            this.eventEmitter.onVideoError.invoke(e2.toString(), e2, "3006");
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:56:0x015a  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0192  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x019e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private androidx.media3.exoplayer.source.MediaSource buildMediaSource(android.net.Uri r7, java.lang.String r8, androidx.media3.exoplayer.drm.DrmSessionManager r9, long r10, long r12) {
        /*
            r6 = this;
            if (r7 == 0) goto L_0x01bb
            java.lang.String r0 = "rtsp"
            boolean r0 = r0.equals(r8)
            r1 = 3
            if (r0 == 0) goto L_0x000d
            r8 = r1
            goto L_0x002b
        L_0x000d:
            boolean r0 = android.text.TextUtils.isEmpty(r8)
            if (r0 != 0) goto L_0x0023
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "."
            r0.<init>(r2)
            java.lang.StringBuilder r8 = r0.append(r8)
            java.lang.String r8 = r8.toString()
            goto L_0x0027
        L_0x0023:
            java.lang.String r8 = r7.getLastPathSegment()
        L_0x0027:
            int r8 = androidx.media3.common.util.Util.inferContentType((java.lang.String) r8)
        L_0x002b:
            com.brentvatne.exoplayer.ReactExoplayerConfig r0 = r6.config
            boolean r2 = r6.disableDisconnectError
            r0.setDisableDisconnectError(r2)
            androidx.media3.common.MediaItem$Builder r0 = new androidx.media3.common.MediaItem$Builder
            r0.<init>()
            androidx.media3.common.MediaItem$Builder r0 = r0.setUri((android.net.Uri) r7)
            com.brentvatne.common.api.Source r2 = r6.source
            com.brentvatne.common.api.Source$Metadata r2 = r2.getMetadata()
            androidx.media3.common.MediaMetadata r2 = com.brentvatne.exoplayer.ConfigurationUtils.buildCustomMetadata(r2)
            if (r2 == 0) goto L_0x004a
            r0.setMediaMetadata(r2)
        L_0x004a:
            com.brentvatne.common.api.Source r2 = r6.source
            com.brentvatne.common.api.AdsProps r2 = r2.getAdsProps()
            if (r2 == 0) goto L_0x006a
            com.brentvatne.common.api.Source r2 = r6.source
            com.brentvatne.common.api.AdsProps r2 = r2.getAdsProps()
            android.net.Uri r2 = r2.getAdTagUrl()
            if (r2 == 0) goto L_0x006a
            androidx.media3.common.MediaItem$AdsConfiguration$Builder r3 = new androidx.media3.common.MediaItem$AdsConfiguration$Builder
            r3.<init>(r2)
            androidx.media3.common.MediaItem$AdsConfiguration r2 = r3.build()
            r0.setAdsConfiguration(r2)
        L_0x006a:
            com.brentvatne.common.api.Source r2 = r6.source
            com.brentvatne.common.api.BufferConfig r2 = r2.getBufferConfig()
            androidx.media3.common.MediaItem$LiveConfiguration$Builder r2 = com.brentvatne.exoplayer.ConfigurationUtils.getLiveConfiguration(r2)
            androidx.media3.common.MediaItem$LiveConfiguration r2 = r2.build()
            r0.setLiveConfiguration(r2)
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            if (r9 == 0) goto L_0x0088
            com.brentvatne.exoplayer.ReactExoplayerView$$ExternalSyntheticLambda10 r3 = new com.brentvatne.exoplayer.ReactExoplayerView$$ExternalSyntheticLambda10
            r3.<init>(r9)
            goto L_0x008d
        L_0x0088:
            androidx.media3.exoplayer.drm.DefaultDrmSessionManagerProvider r3 = new androidx.media3.exoplayer.drm.DefaultDrmSessionManagerProvider
            r3.<init>()
        L_0x008d:
            r9 = 0
            if (r8 == 0) goto L_0x0145
            r4 = 1
            if (r8 == r4) goto L_0x0134
            r9 = 2
            if (r8 == r9) goto L_0x0114
            if (r8 == r1) goto L_0x0107
            r9 = 4
            if (r8 != r9) goto L_0x00f2
            java.lang.String r8 = "asset"
            java.lang.String r9 = r7.getScheme()
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L_0x00c9
            com.facebook.react.uimanager.ThemedReactContext r8 = r6.themedReactContext     // Catch:{ Exception -> 0x00b4 }
            androidx.media3.datasource.DataSource$Factory r8 = com.brentvatne.exoplayer.DataSourceUtil.buildAssetDataSourceFactory(r8, r7)     // Catch:{ Exception -> 0x00b4 }
            androidx.media3.exoplayer.source.ProgressiveMediaSource$Factory r9 = new androidx.media3.exoplayer.source.ProgressiveMediaSource$Factory     // Catch:{ Exception -> 0x00b4 }
            r9.<init>(r8)     // Catch:{ Exception -> 0x00b4 }
            goto L_0x0156
        L_0x00b4:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r10 = "cannot open input file:"
            r9.<init>(r10)
            java.lang.StringBuilder r7 = r9.append(r7)
            java.lang.String r7 = r7.toString()
            r8.<init>(r7)
            throw r8
        L_0x00c9:
            java.lang.String r8 = "file"
            java.lang.String r7 = r7.getScheme()
            boolean r7 = r8.equals(r7)
            if (r7 != 0) goto L_0x00ea
            boolean r7 = r6.useCache
            if (r7 != 0) goto L_0x00da
            goto L_0x00ea
        L_0x00da:
            androidx.media3.exoplayer.source.ProgressiveMediaSource$Factory r9 = new androidx.media3.exoplayer.source.ProgressiveMediaSource$Factory
            com.brentvatne.exoplayer.RNVSimpleCache r7 = com.brentvatne.exoplayer.RNVSimpleCache.INSTANCE
            androidx.media3.datasource.HttpDataSource$Factory r8 = r6.buildHttpDataSourceFactory(r4)
            androidx.media3.datasource.DataSource$Factory r7 = r7.getCacheFactory(r8)
            r9.<init>(r7)
            goto L_0x0156
        L_0x00ea:
            androidx.media3.exoplayer.source.ProgressiveMediaSource$Factory r9 = new androidx.media3.exoplayer.source.ProgressiveMediaSource$Factory
            androidx.media3.datasource.DataSource$Factory r7 = r6.mediaDataSourceFactory
            r9.<init>(r7)
            goto L_0x0156
        L_0x00f2:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r10 = "Unsupported type: "
            r9.<init>(r10)
            java.lang.StringBuilder r8 = r9.append(r8)
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            throw r7
        L_0x0107:
            java.lang.String r7 = "Exo Player Exception"
            java.lang.String r8 = "RTSP is not enabled!"
            com.brentvatne.common.toolbox.DebugLog.e(r7, r8)
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            r7.<init>(r8)
            throw r7
        L_0x0114:
            androidx.media3.datasource.DataSource$Factory r7 = r6.mediaDataSourceFactory
            boolean r8 = r6.useCache
            if (r8 == 0) goto L_0x0124
            com.brentvatne.exoplayer.RNVSimpleCache r7 = com.brentvatne.exoplayer.RNVSimpleCache.INSTANCE
            androidx.media3.datasource.HttpDataSource$Factory r8 = r6.buildHttpDataSourceFactory(r4)
            androidx.media3.datasource.DataSource$Factory r7 = r7.getCacheFactory(r8)
        L_0x0124:
            androidx.media3.exoplayer.hls.HlsMediaSource$Factory r8 = new androidx.media3.exoplayer.hls.HlsMediaSource$Factory
            r8.<init>((androidx.media3.datasource.DataSource.Factory) r7)
            com.brentvatne.common.api.Source r7 = r6.source
            boolean r7 = r7.getTextTracksAllowChunklessPreparation()
            androidx.media3.exoplayer.hls.HlsMediaSource$Factory r9 = r8.setAllowChunklessPreparation(r7)
            goto L_0x0156
        L_0x0134:
            androidx.media3.exoplayer.smoothstreaming.SsMediaSource$Factory r7 = new androidx.media3.exoplayer.smoothstreaming.SsMediaSource$Factory
            androidx.media3.exoplayer.smoothstreaming.DefaultSsChunkSource$Factory r8 = new androidx.media3.exoplayer.smoothstreaming.DefaultSsChunkSource$Factory
            androidx.media3.datasource.DataSource$Factory r1 = r6.mediaDataSourceFactory
            r8.<init>(r1)
            androidx.media3.datasource.DataSource$Factory r9 = r6.buildDataSourceFactory(r9)
            r7.<init>(r8, r9)
            goto L_0x0155
        L_0x0145:
            androidx.media3.exoplayer.dash.DashMediaSource$Factory r7 = new androidx.media3.exoplayer.dash.DashMediaSource$Factory
            androidx.media3.exoplayer.dash.DefaultDashChunkSource$Factory r8 = new androidx.media3.exoplayer.dash.DefaultDashChunkSource$Factory
            androidx.media3.datasource.DataSource$Factory r1 = r6.mediaDataSourceFactory
            r8.<init>(r1)
            androidx.media3.datasource.DataSource$Factory r9 = r6.buildDataSourceFactory(r9)
            r7.<init>(r8, r9)
        L_0x0155:
            r9 = r7
        L_0x0156:
            androidx.media3.exoplayer.upstream.CmcdConfiguration$Factory r7 = r6.cmcdConfigurationFactory
            if (r7 == 0) goto L_0x0166
            java.util.Objects.requireNonNull(r7)
            com.brentvatne.exoplayer.ReactExoplayerView$$ExternalSyntheticLambda11 r8 = new com.brentvatne.exoplayer.ReactExoplayerView$$ExternalSyntheticLambda11
            r8.<init>(r7)
            androidx.media3.exoplayer.source.MediaSource$Factory r9 = r9.setCmcdConfigurationFactory(r8)
        L_0x0166:
            androidx.media3.common.MediaItem$Builder r7 = r0.setStreamKeys(r2)
            androidx.media3.common.MediaItem r7 = r7.build()
            androidx.media3.exoplayer.source.MediaSource$Factory r8 = r9.setDrmSessionManagerProvider(r3)
            com.brentvatne.exoplayer.ReactExoplayerConfig r9 = r6.config
            com.brentvatne.common.api.Source r0 = r6.source
            int r0 = r0.getMinLoadRetryCount()
            androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy r9 = r9.buildLoadErrorHandlingPolicy(r0)
            androidx.media3.exoplayer.source.MediaSource$Factory r8 = r8.setLoadErrorHandlingPolicy(r9)
            androidx.media3.exoplayer.source.MediaSource r1 = r8.createMediaSource(r7)
            r7 = 0
            int r9 = (r10 > r7 ? 1 : (r10 == r7 ? 0 : -1))
            r2 = 1000(0x3e8, double:4.94E-321)
            if (r9 < 0) goto L_0x019e
            int r0 = (r12 > r7 ? 1 : (r12 == r7 ? 0 : -1))
            if (r0 < 0) goto L_0x019e
            androidx.media3.exoplayer.source.ClippingMediaSource r7 = new androidx.media3.exoplayer.source.ClippingMediaSource
            long r8 = r10 * r2
            long r4 = r12 * r2
            r0 = r7
            r2 = r8
            r0.<init>(r1, r2, r4)
            return r7
        L_0x019e:
            if (r9 < 0) goto L_0x01aa
            androidx.media3.exoplayer.source.ClippingMediaSource r7 = new androidx.media3.exoplayer.source.ClippingMediaSource
            long r2 = r2 * r10
            r4 = -9223372036854775808
            r0 = r7
            r0.<init>(r1, r2, r4)
            return r7
        L_0x01aa:
            int r7 = (r12 > r7 ? 1 : (r12 == r7 ? 0 : -1))
            if (r7 < 0) goto L_0x01ba
            androidx.media3.exoplayer.source.ClippingMediaSource r7 = new androidx.media3.exoplayer.source.ClippingMediaSource
            r8 = 0
            long r4 = r12 * r2
            r0 = r7
            r2 = r8
            r0.<init>(r1, r2, r4)
            return r7
        L_0x01ba:
            return r1
        L_0x01bb:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "Invalid video uri"
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.brentvatne.exoplayer.ReactExoplayerView.buildMediaSource(android.net.Uri, java.lang.String, androidx.media3.exoplayer.drm.DrmSessionManager, long, long):androidx.media3.exoplayer.source.MediaSource");
    }

    private MediaSource buildTextSource() {
        if (this.source.getSideLoadedTextTracks() == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<SideLoadedTextTrack> it = this.source.getSideLoadedTextTracks().getTracks().iterator();
        while (it.hasNext()) {
            SideLoadedTextTrack next = it.next();
            arrayList.add(new MediaItem.SubtitleConfiguration.Builder(next.getUri()).setMimeType(next.getType()).setLanguage(next.getLanguage()).setSelectionFlags(1).setRoleFlags(128).setLabel(next.getTitle()).build());
        }
        return new DefaultMediaSourceFactory(this.mediaDataSourceFactory).createMediaSource(new MediaItem.Builder().setUri(this.source.getUri()).setSubtitleConfigurations(arrayList).build());
    }

    private void releasePlayer() {
        Runnable runnable;
        if (this.player != null) {
            PlaybackServiceBinder playbackServiceBinder2 = this.playbackServiceBinder;
            if (playbackServiceBinder2 != null) {
                playbackServiceBinder2.getService().unregisterPlayer(this.player);
                this.themedReactContext.unbindService(this.playbackServiceConnection);
            }
            updateResumePosition();
            this.player.release();
            this.player.removeListener(this);
            PictureInPictureUtil.applyAutoEnterEnabled(this.themedReactContext, this.pictureInPictureParamsBuilder, false);
            if (this.pipListenerUnsubscribe != null) {
                new Handler().post(this.pipListenerUnsubscribe);
            }
            this.trackSelector = null;
            ReactNativeVideoManager.Companion.getInstance().onInstanceRemoved(this.instanceId, this.player);
            this.player = null;
        }
        ImaAdsLoader imaAdsLoader = this.adsLoader;
        if (imaAdsLoader != null) {
            imaAdsLoader.release();
            this.adsLoader = null;
        }
        this.progressHandler.removeMessages(1);
        this.audioBecomingNoisyReceiver.removeListener();
        this.pictureInPictureReceiver.removeListener();
        this.bandwidthMeter.removeEventListener(this);
        Handler handler = this.mainHandler;
        if (handler != null && (runnable = this.mainRunnable) != null) {
            handler.removeCallbacks(runnable);
            this.mainRunnable = null;
        }
    }

    private static class OnAudioFocusChangedListener implements AudioManager.OnAudioFocusChangeListener {
        private final ThemedReactContext themedReactContext;
        private final ReactExoplayerView view;

        private OnAudioFocusChangedListener(ReactExoplayerView reactExoplayerView, ThemedReactContext themedReactContext2) {
            this.view = reactExoplayerView;
            this.themedReactContext = themedReactContext2;
        }

        public void onAudioFocusChange(int i) {
            Activity currentActivity = this.themedReactContext.getCurrentActivity();
            if (i == -2) {
                this.view.eventEmitter.onAudioFocusChanged.invoke(false);
            } else if (i == -1) {
                this.view.hasAudioFocus = false;
                this.view.eventEmitter.onAudioFocusChanged.invoke(false);
                if (currentActivity != null) {
                    ReactExoplayerView reactExoplayerView = this.view;
                    Objects.requireNonNull(reactExoplayerView);
                    currentActivity.runOnUiThread(new ReactExoplayerView$OnAudioFocusChangedListener$$ExternalSyntheticLambda0(reactExoplayerView));
                }
                this.view.audioManager.abandonAudioFocus(this);
            } else if (i == 1) {
                this.view.hasAudioFocus = true;
                this.view.eventEmitter.onAudioFocusChanged.invoke(true);
            }
            if (this.view.player != null && currentActivity != null) {
                if (i == -3) {
                    if (!this.view.muted) {
                        currentActivity.runOnUiThread(new ReactExoplayerView$OnAudioFocusChangedListener$$ExternalSyntheticLambda1(this));
                    }
                } else if (i == 1 && !this.view.muted) {
                    currentActivity.runOnUiThread(new ReactExoplayerView$OnAudioFocusChangedListener$$ExternalSyntheticLambda2(this));
                }
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onAudioFocusChange$0() {
            this.view.player.setVolume(this.view.audioVolume * 0.8f);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onAudioFocusChange$1() {
            this.view.player.setVolume(this.view.audioVolume * 1.0f);
        }
    }

    private boolean requestAudioFocus() {
        if (this.disableFocus || this.source.getUri() == null || this.hasAudioFocus || this.audioManager.requestAudioFocus(this.audioFocusChangeListener, 3, 1) == 1) {
            return true;
        }
        return false;
    }

    private void setPlayWhenReady(boolean z) {
        ExoPlayer exoPlayer = this.player;
        if (exoPlayer != null) {
            if (z) {
                boolean requestAudioFocus = requestAudioFocus();
                this.hasAudioFocus = requestAudioFocus;
                if (requestAudioFocus) {
                    this.player.setPlayWhenReady(true);
                    return;
                }
                return;
            }
            exoPlayer.setPlayWhenReady(false);
        }
    }

    private void resumePlayback() {
        ExoPlayer exoPlayer = this.player;
        if (exoPlayer != null) {
            if (!exoPlayer.getPlayWhenReady()) {
                setPlayWhenReady(true);
            }
            setKeepScreenOn(this.preventsDisplaySleepDuringVideoPlayback);
        }
    }

    /* access modifiers changed from: private */
    public void pausePlayback() {
        ExoPlayer exoPlayer = this.player;
        if (exoPlayer != null && exoPlayer.getPlayWhenReady()) {
            setPlayWhenReady(false);
        }
        setKeepScreenOn(false);
    }

    private void stopPlayback() {
        onStopPlayback();
        releasePlayer();
    }

    private void onStopPlayback() {
        this.audioManager.abandonAudioFocus(this.audioFocusChangeListener);
    }

    private void updateResumePosition() {
        this.resumeWindow = this.player.getCurrentMediaItemIndex();
        this.resumePosition = this.player.isCurrentMediaItemSeekable() ? Math.max(0, this.player.getCurrentPosition()) : C.TIME_UNSET;
    }

    private void clearResumePosition() {
        this.resumeWindow = -1;
        this.resumePosition = C.TIME_UNSET;
    }

    private DataSource.Factory buildDataSourceFactory(boolean z) {
        return DataSourceUtil.getDefaultDataSourceFactory(this.themedReactContext, z ? this.bandwidthMeter : null, this.source.getHeaders());
    }

    private HttpDataSource.Factory buildHttpDataSourceFactory(boolean z) {
        return DataSourceUtil.getDefaultHttpDataSourceFactory(this.themedReactContext, z ? this.bandwidthMeter : null, this.source.getHeaders());
    }

    public void onAudioBecomingNoisy() {
        this.eventEmitter.onVideoAudioBecomingNoisy.invoke();
    }

    public void onEvents(Player player2, Player.Events events) {
        String str;
        String str2;
        if (events.contains(4) || events.contains(5)) {
            int playbackState = player2.getPlaybackState();
            boolean playWhenReady = player2.getPlayWhenReady();
            String str3 = "onStateChanged: playWhenReady=" + playWhenReady + ", playbackState=";
            this.eventEmitter.onPlaybackRateChange.invoke(Float.valueOf((!playWhenReady || playbackState != 3) ? 0.0f : 1.0f));
            if (playbackState != 1) {
                if (playbackState == 2) {
                    str = str3 + "buffering";
                    onBuffering(true);
                    clearProgressMessageHandler();
                    setKeepScreenOn(this.preventsDisplaySleepDuringVideoPlayback);
                } else if (playbackState == 3) {
                    str2 = str3 + HostKt.READY;
                    this.eventEmitter.onReadyForDisplay.invoke();
                    onBuffering(false);
                    clearProgressMessageHandler();
                    startProgressHandler();
                    videoLoaded();
                    if (this.selectTrackWhenReady && this.isUsingContentResolution) {
                        this.selectTrackWhenReady = false;
                        setSelectedTrack(2, this.videoTrackType, this.videoTrackValue);
                    }
                    LegacyPlayerControlView legacyPlayerControlView = this.playerControlView;
                    if (legacyPlayerControlView != null) {
                        legacyPlayerControlView.show();
                    }
                    setKeepScreenOn(this.preventsDisplaySleepDuringVideoPlayback);
                } else if (playbackState != 4) {
                    str = str3 + "unknown";
                } else {
                    str = str3 + "ended";
                    updateProgress();
                    this.eventEmitter.onVideoEnd.invoke();
                    onStopPlayback();
                    setKeepScreenOn(false);
                }
                DebugLog.d(TAG, str);
            }
            str2 = str3 + "idle";
            this.eventEmitter.onVideoIdle.invoke();
            clearProgressMessageHandler();
            if (!player2.getPlayWhenReady()) {
                setKeepScreenOn(false);
            }
            str = str2;
            DebugLog.d(TAG, str);
        }
    }

    private void startProgressHandler() {
        this.progressHandler.sendEmptyMessage(1);
    }

    private void clearProgressMessageHandler() {
        this.progressHandler.removeMessages(1);
    }

    private void videoLoaded() {
        int i;
        if (!this.player.isPlayingAd() && this.loadVideoStarted) {
            int i2 = 0;
            this.loadVideoStarted = false;
            String str = this.audioTrackType;
            if (str != null) {
                setSelectedAudioTrack(str, this.audioTrackValue);
            }
            String str2 = this.videoTrackType;
            if (str2 != null) {
                setSelectedVideoTrack(str2, this.videoTrackValue);
            }
            String str3 = this.textTrackType;
            if (str3 != null) {
                setSelectedTextTrack(str3, this.textTrackValue);
            }
            Format videoFormat = this.player.getVideoFormat();
            boolean z = videoFormat != null && (videoFormat.rotationDegrees == 90 || videoFormat.rotationDegrees == 270);
            if (videoFormat != null) {
                i = z ? videoFormat.height : videoFormat.width;
            } else {
                i = 0;
            }
            if (videoFormat != null) {
                i2 = z ? videoFormat.width : videoFormat.height;
            }
            int i3 = i2;
            String str4 = videoFormat != null ? videoFormat.id : null;
            long duration = this.player.getDuration();
            long currentPosition = this.player.getCurrentPosition();
            ArrayList<Track> audioTrackInfo = getAudioTrackInfo();
            ArrayList<Track> textTrackInfo = getTextTrackInfo();
            if (this.source.getContentStartTime() != -1) {
                Executors.newSingleThreadExecutor().execute(new ReactExoplayerView$$ExternalSyntheticLambda8(this, duration, currentPosition, i, i3, audioTrackInfo, textTrackInfo, str4));
                return;
            }
            this.eventEmitter.onVideoLoad.invoke(Long.valueOf(duration), Long.valueOf(currentPosition), Integer.valueOf(i), Integer.valueOf(i3), audioTrackInfo, textTrackInfo, getVideoTrackInfo(), str4);
            refreshControlsStyles();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$videoLoaded$17(long j, long j2, int i, int i2, ArrayList arrayList, ArrayList arrayList2, String str) {
        ArrayList<VideoTrack> videoTrackInfoFromManifest = getVideoTrackInfoFromManifest();
        if (videoTrackInfoFromManifest != null) {
            this.isUsingContentResolution = true;
        }
        this.eventEmitter.onVideoLoad.invoke(Long.valueOf(j), Long.valueOf(j2), Integer.valueOf(i), Integer.valueOf(i2), arrayList, arrayList2, videoTrackInfoFromManifest, str);
    }

    private static boolean isTrackSelected(TrackSelection trackSelection, TrackGroup trackGroup, int i) {
        return (trackSelection == null || trackSelection.getTrackGroup() != trackGroup || trackSelection.indexOf(i) == -1) ? false : true;
    }

    private ArrayList<Track> getAudioTrackInfo() {
        ArrayList<Track> arrayList = new ArrayList<>();
        DefaultTrackSelector defaultTrackSelector = this.trackSelector;
        if (defaultTrackSelector == null) {
            return arrayList;
        }
        MappingTrackSelector.MappedTrackInfo currentMappedTrackInfo = defaultTrackSelector.getCurrentMappedTrackInfo();
        int trackRendererIndex = getTrackRendererIndex(1);
        if (!(currentMappedTrackInfo == null || trackRendererIndex == -1)) {
            TrackGroupArray trackGroups = currentMappedTrackInfo.getTrackGroups(trackRendererIndex);
            TrackSelection trackSelection = this.player.getCurrentTrackSelections().get(1);
            for (int i = 0; i < trackGroups.length; i++) {
                TrackGroup trackGroup = trackGroups.get(i);
                Format format = trackGroup.getFormat(0);
                Track exoplayerTrackToGenericTrack = exoplayerTrackToGenericTrack(format, i, trackSelection, trackGroup);
                exoplayerTrackToGenericTrack.setBitrate(format.bitrate == -1 ? 0 : format.bitrate);
                arrayList.add(exoplayerTrackToGenericTrack);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public VideoTrack exoplayerVideoTrackToGenericVideoTrack(Format format, int i) {
        VideoTrack videoTrack = new VideoTrack();
        int i2 = 0;
        videoTrack.setWidth(format.width == -1 ? 0 : format.width);
        videoTrack.setHeight(format.height == -1 ? 0 : format.height);
        if (format.bitrate != -1) {
            i2 = format.bitrate;
        }
        videoTrack.setBitrate(i2);
        videoTrack.setRotation(format.rotationDegrees);
        if (format.codecs != null) {
            videoTrack.setCodecs(format.codecs);
        }
        videoTrack.setTrackId(format.id == null ? String.valueOf(i) : format.id);
        videoTrack.setIndex(i);
        return videoTrack;
    }

    private ArrayList<VideoTrack> getVideoTrackInfo() {
        ArrayList<VideoTrack> arrayList = new ArrayList<>();
        DefaultTrackSelector defaultTrackSelector = this.trackSelector;
        if (defaultTrackSelector == null) {
            return arrayList;
        }
        MappingTrackSelector.MappedTrackInfo currentMappedTrackInfo = defaultTrackSelector.getCurrentMappedTrackInfo();
        int trackRendererIndex = getTrackRendererIndex(2);
        if (!(currentMappedTrackInfo == null || trackRendererIndex == -1)) {
            TrackGroupArray trackGroups = currentMappedTrackInfo.getTrackGroups(trackRendererIndex);
            for (int i = 0; i < trackGroups.length; i++) {
                TrackGroup trackGroup = trackGroups.get(i);
                for (int i2 = 0; i2 < trackGroup.length; i2++) {
                    Format format = trackGroup.getFormat(i2);
                    if (isFormatSupported(format)) {
                        arrayList.add(exoplayerVideoTrackToGenericVideoTrack(format, i2));
                    }
                }
            }
        }
        return arrayList;
    }

    private ArrayList<VideoTrack> getVideoTrackInfoFromManifest() {
        return getVideoTrackInfoFromManifest(0);
    }

    private ArrayList<VideoTrack> getVideoTrackInfoFromManifest(int i) {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        try {
            ArrayList<VideoTrack> arrayList = (ArrayList) newSingleThreadExecutor.submit(new Callable(this.mediaDataSourceFactory.createDataSource(), this.source.getUri(), (long) ((this.source.getContentStartTime() * 1000) - 100)) {
                final DataSource ds;
                final long startTimeUs;
                final Uri uri;
                final /* synthetic */ DataSource val$dataSource;
                final /* synthetic */ Uri val$sourceUri;
                final /* synthetic */ long val$startTime;

                {
                    this.val$dataSource = r2;
                    this.val$sourceUri = r3;
                    this.val$startTime = r4;
                    this.ds = r2;
                    this.uri = r3;
                    this.startTimeUs = r4 * 1000;
                }

                public ArrayList<VideoTrack> call() {
                    int i;
                    ArrayList<VideoTrack> arrayList = new ArrayList<>();
                    try {
                        DashManifest loadManifest = DashUtil.loadManifest(this.ds, this.uri);
                        int periodCount = loadManifest.getPeriodCount();
                        int i2 = 0;
                        while (i2 < periodCount) {
                            Period period = loadManifest.getPeriod(i2);
                            int i3 = 0;
                            while (i3 < period.adaptationSets.size()) {
                                AdaptationSet adaptationSet = period.adaptationSets.get(i3);
                                if (adaptationSet.type != 2) {
                                    i = i2;
                                } else {
                                    int i4 = 0;
                                    boolean z = false;
                                    while (true) {
                                        if (i4 >= adaptationSet.representations.size()) {
                                            i = i2;
                                            break;
                                        }
                                        Representation representation = adaptationSet.representations.get(i4);
                                        Format format = representation.format;
                                        if (ReactExoplayerView.this.isFormatSupported(format)) {
                                            i = i2;
                                            if (representation.presentationTimeOffsetUs <= this.startTimeUs) {
                                                break;
                                            }
                                            arrayList.add(ReactExoplayerView.this.exoplayerVideoTrackToGenericVideoTrack(format, i4));
                                            z = true;
                                        } else {
                                            i = i2;
                                        }
                                        i4++;
                                        i2 = i;
                                    }
                                    if (z) {
                                        return arrayList;
                                    }
                                }
                                i3++;
                                i2 = i;
                            }
                            i2++;
                        }
                        return null;
                    } catch (Exception e) {
                        DebugLog.w(ReactExoplayerView.TAG, "error in getVideoTrackInfoFromManifest:" + e.getMessage());
                        return null;
                    }
                }
            }).get(C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS, TimeUnit.MILLISECONDS);
            if (arrayList == null && i < 1) {
                return getVideoTrackInfoFromManifest(i + 1);
            }
            newSingleThreadExecutor.shutdown();
            return arrayList;
        } catch (Exception e) {
            DebugLog.w(TAG, "error in getVideoTrackInfoFromManifest handling request:" + e.getMessage());
            return null;
        }
    }

    private Track exoplayerTrackToGenericTrack(Format format, int i, TrackSelection trackSelection, TrackGroup trackGroup) {
        Track track = new Track();
        track.setIndex(i);
        if (format.sampleMimeType != null) {
            track.setMimeType(format.sampleMimeType);
        }
        if (format.language != null) {
            track.setLanguage(format.language);
        }
        if (format.label != null) {
            track.setTitle(format.label);
        }
        track.setSelected(isTrackSelected(trackSelection, trackGroup, i));
        return track;
    }

    private ArrayList<Track> getTextTrackInfo() {
        ArrayList<Track> arrayList = new ArrayList<>();
        DefaultTrackSelector defaultTrackSelector = this.trackSelector;
        if (defaultTrackSelector == null) {
            return arrayList;
        }
        MappingTrackSelector.MappedTrackInfo currentMappedTrackInfo = defaultTrackSelector.getCurrentMappedTrackInfo();
        int trackRendererIndex = getTrackRendererIndex(3);
        if (!(currentMappedTrackInfo == null || trackRendererIndex == -1)) {
            TrackSelection trackSelection = this.player.getCurrentTrackSelections().get(2);
            TrackGroupArray trackGroups = currentMappedTrackInfo.getTrackGroups(trackRendererIndex);
            for (int i = 0; i < trackGroups.length; i++) {
                TrackGroup trackGroup = trackGroups.get(i);
                arrayList.add(exoplayerTrackToGenericTrack(trackGroup.getFormat(0), i, trackSelection, trackGroup));
            }
        }
        return arrayList;
    }

    private void onBuffering(boolean z) {
        if (this.isBuffering != z) {
            if (this.isPaused && this.isSeeking && !z) {
                this.eventEmitter.onVideoSeek.invoke(Long.valueOf(this.player.getCurrentPosition()), Long.valueOf(this.seekPosition));
                this.isSeeking = false;
            }
            this.isBuffering = z;
            this.eventEmitter.onVideoBuffer.invoke(Boolean.valueOf(z));
        }
    }

    public void onPositionDiscontinuity(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i) {
        if (i == 1) {
            this.isSeeking = true;
            this.seekPosition = positionInfo2.positionMs;
            if (this.isUsingContentResolution) {
                setSelectedTrack(2, this.videoTrackType, this.videoTrackValue);
            }
        }
        if (this.playerNeedsSource) {
            updateResumePosition();
        }
        if (this.isUsingContentResolution) {
            setSelectedTrack(2, this.videoTrackType, this.videoTrackValue);
            this.selectTrackWhenReady = true;
        }
        if (i == 0 && this.player.getRepeatMode() == 1) {
            updateProgress();
            this.eventEmitter.onVideoEnd.invoke();
        }
    }

    public void onTracksChanged(Tracks tracks) {
        this.eventEmitter.onTextTracks.invoke(getTextTrackInfo());
        this.eventEmitter.onAudioTracks.invoke(getAudioTrackInfo());
        this.eventEmitter.onVideoTracks.invoke(getVideoTrackInfo());
    }

    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        this.eventEmitter.onPlaybackRateChange.invoke(Float.valueOf(playbackParameters.speed));
    }

    public void onVolumeChanged(float f) {
        this.eventEmitter.onVolumeChange.invoke(Float.valueOf(f));
    }

    public void onIsPlayingChanged(boolean z) {
        if (z && this.isSeeking) {
            this.eventEmitter.onVideoSeek.invoke(Long.valueOf(this.player.getCurrentPosition()), Long.valueOf(this.seekPosition));
        }
        PictureInPictureUtil.applyPlayingStatus(this.themedReactContext, this.pictureInPictureParamsBuilder, this.pictureInPictureReceiver, !z);
        this.eventEmitter.onVideoPlaybackStateChanged.invoke(Boolean.valueOf(z), Boolean.valueOf(this.isSeeking));
        if (z) {
            this.isSeeking = false;
        }
    }

    public void onPlayerError(PlaybackException playbackException) {
        String str = "ExoPlaybackException: " + PlaybackException.getErrorCodeName(playbackException.errorCode);
        String str2 = ExifInterface.GPS_MEASUREMENT_2D + playbackException.errorCode;
        int i = playbackException.errorCode;
        if ((i == 6000 || i == 6002 || i == 6004 || i == 6006 || i == 6007) && !this.hasDrmFailed) {
            this.hasDrmFailed = true;
            this.playerNeedsSource = true;
            updateResumePosition();
            initializePlayer();
            setPlayWhenReady(true);
            return;
        }
        this.eventEmitter.onVideoError.invoke(str, playbackException, str2);
        this.playerNeedsSource = true;
        if (isBehindLiveWindow(playbackException)) {
            clearResumePosition();
            ExoPlayer exoPlayer = this.player;
            if (exoPlayer != null) {
                exoPlayer.seekToDefaultPosition();
                this.player.prepare();
                return;
            }
            return;
        }
        updateResumePosition();
    }

    private static boolean isBehindLiveWindow(PlaybackException playbackException) {
        return playbackException.errorCode == 1002;
    }

    public int getTrackRendererIndex(int i) {
        ExoPlayer exoPlayer = this.player;
        if (exoPlayer == null) {
            return -1;
        }
        int rendererCount = exoPlayer.getRendererCount();
        for (int i2 = 0; i2 < rendererCount; i2++) {
            if (this.player.getRendererType(i2) == i) {
                return i2;
            }
        }
        return -1;
    }

    public void onMetadata(Metadata metadata) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < metadata.length(); i++) {
            Metadata.Entry entry = metadata.get(i);
            if (entry instanceof Id3Frame) {
                Id3Frame id3Frame = (Id3Frame) metadata.get(i);
                arrayList.add(new TimedMetadata(id3Frame.id, id3Frame instanceof TextInformationFrame ? ((TextInformationFrame) id3Frame).value : ""));
            } else if (entry instanceof EventMessage) {
                EventMessage eventMessage = (EventMessage) entry;
                arrayList.add(new TimedMetadata(eventMessage.schemeIdUri, eventMessage.value));
            } else {
                DebugLog.d(TAG, "unhandled metadata " + entry);
            }
        }
        this.eventEmitter.onTimedMetadata.invoke(arrayList);
    }

    public void onCues(CueGroup cueGroup) {
        if (!cueGroup.cues.isEmpty() && ((Cue) cueGroup.cues.get(0)).text != null) {
            this.eventEmitter.onTextTrackDataChanged.invoke(((Cue) cueGroup.cues.get(0)).text.toString());
        }
    }

    public void setSrc(Source source2) {
        if (source2.getUri() != null) {
            clearResumePosition();
            boolean isEquals = source2.isEquals(this.source);
            this.hasDrmFailed = false;
            this.source = source2;
            this.mediaDataSourceFactory = DataSourceUtil.getDefaultDataSourceFactory(this.themedReactContext, this.bandwidthMeter, source2.getHeaders());
            if (source2.getCmcdProps() != null) {
                setCmcdConfigurationFactory(new CMCDConfig(source2.getCmcdProps()).toCmcdConfigurationFactory());
            } else {
                setCmcdConfigurationFactory((CmcdConfiguration.Factory) null);
            }
            if (!isEquals) {
                this.playerNeedsSource = true;
                initializePlayer();
                return;
            }
            return;
        }
        clearSrc();
    }

    public void clearSrc() {
        ExoPlayer exoPlayer;
        if (!(this.source.getUri() == null || (exoPlayer = this.player) == null)) {
            exoPlayer.stop();
            this.player.clearMediaItems();
        }
        this.exoPlayerView.hideAds();
        this.source = new Source();
        this.mediaDataSourceFactory = null;
        clearResumePosition();
    }

    public void setProgressUpdateInterval(float f) {
        this.mProgressUpdateInterval = f;
    }

    public void setReportBandwidth(boolean z) {
        this.mReportBandwidth = z;
    }

    public void setResizeModeModifier(int i) {
        ExoPlayerView exoPlayerView2 = this.exoPlayerView;
        if (exoPlayerView2 != null) {
            exoPlayerView2.setResizeMode(i);
        }
    }

    private void applyModifiers() {
        setRepeatModifier(this.repeat);
        setMutedModifier(this.muted);
    }

    public void setRepeatModifier(boolean z) {
        ExoPlayer exoPlayer = this.player;
        if (exoPlayer != null) {
            if (z) {
                exoPlayer.setRepeatMode(1);
            } else {
                exoPlayer.setRepeatMode(0);
            }
        }
        this.repeat = z;
    }

    public void setPreventsDisplaySleepDuringVideoPlayback(boolean z) {
        this.preventsDisplaySleepDuringVideoPlayback = z;
    }

    public void disableTrack(int i) {
        this.trackSelector.setParameters((TrackSelectionParameters) this.trackSelector.getParameters().buildUpon().setRendererDisabled(i, true).build());
    }

    /* JADX WARNING: Removed duplicated region for block: B:120:0x019d A[LOOP:5: B:118:0x0199->B:120:0x019d, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x01af  */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x01c7  */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x01ef  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x01f3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setSelectedTrack(int r18, java.lang.String r19, java.lang.String r20) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r20
            androidx.media3.exoplayer.ExoPlayer r3 = r0.player
            if (r3 != 0) goto L_0x000b
            return
        L_0x000b:
            int r3 = r17.getTrackRendererIndex(r18)
            r4 = -1
            if (r3 != r4) goto L_0x0013
            return
        L_0x0013:
            androidx.media3.exoplayer.trackselection.DefaultTrackSelector r5 = r0.trackSelector
            androidx.media3.exoplayer.trackselection.MappingTrackSelector$MappedTrackInfo r5 = r5.getCurrentMappedTrackInfo()
            if (r5 != 0) goto L_0x001c
            return
        L_0x001c:
            androidx.media3.exoplayer.source.TrackGroupArray r5 = r5.getTrackGroups(r3)
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            r7 = 0
            java.lang.Integer r8 = java.lang.Integer.valueOf(r7)
            r6.add(r8)
            boolean r8 = android.text.TextUtils.isEmpty(r19)
            if (r8 == 0) goto L_0x0036
            java.lang.String r8 = "default"
            goto L_0x0038
        L_0x0036:
            r8 = r19
        L_0x0038:
            java.lang.String r9 = "disabled"
            boolean r9 = r9.equals(r8)
            if (r9 == 0) goto L_0x0044
            r0.disableTrack(r3)
            return
        L_0x0044:
            java.lang.String r9 = "language"
            boolean r9 = r9.equals(r8)
            r11 = 2
            r12 = 1
            if (r9 == 0) goto L_0x006f
            r8 = r7
        L_0x004f:
            int r9 = r5.length
            if (r8 >= r9) goto L_0x006b
            androidx.media3.common.TrackGroup r9 = r5.get(r8)
            androidx.media3.common.Format r9 = r9.getFormat(r7)
            java.lang.String r13 = r9.language
            if (r13 == 0) goto L_0x0068
            java.lang.String r9 = r9.language
            boolean r9 = r9.equals(r2)
            if (r9 == 0) goto L_0x0068
            goto L_0x006c
        L_0x0068:
            int r8 = r8 + 1
            goto L_0x004f
        L_0x006b:
            r8 = r4
        L_0x006c:
            r2 = r4
            goto L_0x0184
        L_0x006f:
            java.lang.String r9 = "title"
            boolean r9 = r9.equals(r8)
            if (r9 == 0) goto L_0x0095
            r8 = r7
        L_0x0079:
            int r9 = r5.length
            if (r8 >= r9) goto L_0x006b
            androidx.media3.common.TrackGroup r9 = r5.get(r8)
            androidx.media3.common.Format r9 = r9.getFormat(r7)
            java.lang.String r13 = r9.label
            if (r13 == 0) goto L_0x0092
            java.lang.String r9 = r9.label
            boolean r9 = r9.equals(r2)
            if (r9 == 0) goto L_0x0092
            goto L_0x006c
        L_0x0092:
            int r8 = r8 + 1
            goto L_0x0079
        L_0x0095:
            java.lang.String r9 = "index"
            boolean r9 = r9.equals(r8)
            if (r9 == 0) goto L_0x00c0
            int r2 = com.brentvatne.common.toolbox.ReactBridgeUtils.safeParseInt(r2, r4)
            if (r2 == r4) goto L_0x006b
            if (r1 != r11) goto L_0x00ba
            int r8 = r5.length
            if (r8 != r12) goto L_0x00ba
            androidx.media3.common.TrackGroup r8 = r5.get(r7)
            int r8 = r8.length
            if (r2 >= r8) goto L_0x00b8
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r6.set(r7, r2)
        L_0x00b8:
            r8 = r7
            goto L_0x006c
        L_0x00ba:
            int r8 = r5.length
            if (r2 >= r8) goto L_0x006b
            r8 = r2
            goto L_0x006c
        L_0x00c0:
            java.lang.String r9 = "resolution"
            boolean r8 = r9.equals(r8)
            if (r8 == 0) goto L_0x0159
            int r2 = com.brentvatne.common.toolbox.ReactBridgeUtils.safeParseInt(r2, r4)
            if (r2 == r4) goto L_0x0156
            r9 = r4
            r8 = r7
        L_0x00d0:
            int r13 = r5.length
            if (r8 >= r13) goto L_0x0154
            androidx.media3.common.TrackGroup r13 = r5.get(r8)
            r16 = r4
            r15 = r7
            r10 = 0
        L_0x00dc:
            int r14 = r13.length
            if (r15 >= r14) goto L_0x0116
            androidx.media3.common.Format r14 = r13.getFormat(r15)
            int r11 = r14.height
            if (r11 != r2) goto L_0x00f3
            java.lang.Integer r9 = java.lang.Integer.valueOf(r15)
            r6.set(r7, r9)
            r10 = r4
            r9 = r8
            r14 = 0
            goto L_0x011a
        L_0x00f3:
            boolean r11 = r0.isUsingContentResolution
            if (r11 == 0) goto L_0x0111
            if (r10 == 0) goto L_0x010a
            int r11 = r14.bitrate
            int r12 = r10.bitrate
            if (r11 > r12) goto L_0x0105
            int r11 = r14.height
            int r12 = r10.height
            if (r11 <= r12) goto L_0x0111
        L_0x0105:
            int r11 = r14.height
            if (r11 >= r2) goto L_0x0111
            goto L_0x010e
        L_0x010a:
            int r11 = r14.height
            if (r11 >= r2) goto L_0x0111
        L_0x010e:
            r10 = r14
            r16 = r15
        L_0x0111:
            int r15 = r15 + 1
            r11 = 2
            r12 = 1
            goto L_0x00dc
        L_0x0116:
            r12 = r7
            r14 = r10
            r10 = r16
        L_0x011a:
            if (r14 != 0) goto L_0x0140
            boolean r11 = r0.isUsingContentResolution
            if (r11 == 0) goto L_0x0140
            if (r12 != 0) goto L_0x0140
            r11 = r7
            r12 = 2147483647(0x7fffffff, float:NaN)
        L_0x0126:
            int r15 = r13.length
            if (r11 >= r15) goto L_0x0140
            androidx.media3.common.Format r15 = r13.getFormat(r11)
            int r4 = r15.height
            if (r4 >= r12) goto L_0x013c
            int r12 = r15.height
            java.lang.Integer r4 = java.lang.Integer.valueOf(r11)
            r6.set(r7, r4)
            r9 = r8
        L_0x013c:
            int r11 = r11 + 1
            r4 = -1
            goto L_0x0126
        L_0x0140:
            if (r14 == 0) goto L_0x014d
            r4 = -1
            if (r10 == r4) goto L_0x014d
            java.lang.Integer r4 = java.lang.Integer.valueOf(r10)
            r6.set(r7, r4)
            r9 = r8
        L_0x014d:
            int r8 = r8 + 1
            r4 = -1
            r11 = 2
            r12 = 1
            goto L_0x00d0
        L_0x0154:
            r8 = r9
            goto L_0x0157
        L_0x0156:
            r8 = -1
        L_0x0157:
            r2 = -1
            goto L_0x0184
        L_0x0159:
            r2 = 3
            if (r1 != r2) goto L_0x017a
            int r2 = androidx.media3.common.util.Util.SDK_INT
            r4 = 18
            if (r2 <= r4) goto L_0x017a
            com.facebook.react.uimanager.ThemedReactContext r2 = r0.themedReactContext
            java.lang.String r4 = "captioning"
            java.lang.Object r2 = r2.getSystemService(r4)
            android.view.accessibility.CaptioningManager r2 = (android.view.accessibility.CaptioningManager) r2
            if (r2 == 0) goto L_0x0156
            boolean r2 = r2.isEnabled()
            if (r2 == 0) goto L_0x0156
            int r2 = r0.getGroupIndexForDefaultLocale(r5)
            r8 = r2
            goto L_0x0157
        L_0x017a:
            r2 = 1
            if (r3 != r2) goto L_0x0182
            int r8 = r0.getGroupIndexForDefaultLocale(r5)
            goto L_0x0157
        L_0x0182:
            r2 = -1
            r8 = -1
        L_0x0184:
            if (r8 != r2) goto L_0x01ec
            r2 = 2
            if (r1 != r2) goto L_0x01ec
            int r2 = r5.length
            if (r2 == 0) goto L_0x01ec
            androidx.media3.common.TrackGroup r2 = r5.get(r7)
            java.util.ArrayList r6 = new java.util.ArrayList
            int r4 = r2.length
            r6.<init>(r4)
            r4 = r7
        L_0x0199:
            int r8 = r2.length
            if (r4 >= r8) goto L_0x01a7
            java.lang.Integer r8 = java.lang.Integer.valueOf(r4)
            r6.add(r8)
            int r4 = r4 + 1
            goto L_0x0199
        L_0x01a7:
            r4 = r7
            r8 = r4
        L_0x01a9:
            int r9 = r6.size()
            if (r4 >= r9) goto L_0x01be
            androidx.media3.common.Format r9 = r2.getFormat(r4)
            boolean r9 = r0.isFormatSupported(r9)
            if (r9 == 0) goto L_0x01bb
            int r8 = r8 + 1
        L_0x01bb:
            int r4 = r4 + 1
            goto L_0x01a9
        L_0x01be:
            int r4 = r6.size()
            r9 = 1
            if (r4 != r9) goto L_0x01c7
        L_0x01c5:
            r8 = r7
            goto L_0x01ec
        L_0x01c7:
            java.util.ArrayList r4 = new java.util.ArrayList
            int r8 = r8 + r9
            r4.<init>(r8)
            r8 = r7
        L_0x01ce:
            int r9 = r6.size()
            if (r8 >= r9) goto L_0x01ea
            androidx.media3.common.Format r9 = r2.getFormat(r8)
            boolean r9 = r0.isFormatSupported(r9)
            if (r9 == 0) goto L_0x01e7
            java.lang.Object r9 = r6.get(r8)
            java.lang.Integer r9 = (java.lang.Integer) r9
            r4.add(r9)
        L_0x01e7:
            int r8 = r8 + 1
            goto L_0x01ce
        L_0x01ea:
            r6 = r4
            goto L_0x01c5
        L_0x01ec:
            r2 = -1
            if (r8 != r2) goto L_0x01f3
            r0.disableTrack(r3)
            return
        L_0x01f3:
            androidx.media3.common.TrackSelectionOverride r2 = new androidx.media3.common.TrackSelectionOverride
            androidx.media3.common.TrackGroup r4 = r5.get(r8)
            r2.<init>((androidx.media3.common.TrackGroup) r4, (java.util.List<java.lang.Integer>) r6)
            androidx.media3.exoplayer.trackselection.DefaultTrackSelector r4 = r0.trackSelector
            androidx.media3.exoplayer.trackselection.DefaultTrackSelector$Parameters r4 = r4.getParameters()
            androidx.media3.exoplayer.trackselection.DefaultTrackSelector$Parameters$Builder r4 = r4.buildUpon()
            r5 = 1
            androidx.media3.exoplayer.trackselection.DefaultTrackSelector$Parameters$Builder r4 = r4.setExceedAudioConstraintsIfNecessary(r5)
            androidx.media3.exoplayer.trackselection.DefaultTrackSelector$Parameters$Builder r4 = r4.setExceedRendererCapabilitiesIfNecessary(r5)
            androidx.media3.exoplayer.trackselection.DefaultTrackSelector$Parameters$Builder r4 = r4.setExceedVideoConstraintsIfNecessary(r5)
            androidx.media3.exoplayer.trackselection.DefaultTrackSelector$Parameters$Builder r3 = r4.setRendererDisabled(r3, r7)
            int r4 = r2.getType()
            androidx.media3.exoplayer.trackselection.DefaultTrackSelector$Parameters$Builder r3 = r3.clearOverridesOfType((int) r4)
            r4 = 2
            if (r1 != r4) goto L_0x0235
            boolean r1 = r17.isUsingVideoABR()
            if (r1 == 0) goto L_0x0235
            int r1 = r0.maxBitRate
            if (r1 != 0) goto L_0x0230
            r10 = 2147483647(0x7fffffff, float:NaN)
            goto L_0x0231
        L_0x0230:
            r10 = r1
        L_0x0231:
            r3.setMaxVideoBitrate((int) r10)
            goto L_0x0238
        L_0x0235:
            r3.addOverride((androidx.media3.common.TrackSelectionOverride) r2)
        L_0x0238:
            androidx.media3.exoplayer.trackselection.DefaultTrackSelector r1 = r0.trackSelector
            androidx.media3.exoplayer.trackselection.DefaultTrackSelector$Parameters r2 = r3.build()
            r1.setParameters((androidx.media3.common.TrackSelectionParameters) r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.brentvatne.exoplayer.ReactExoplayerView.setSelectedTrack(int, java.lang.String, java.lang.String):void");
    }

    /* access modifiers changed from: private */
    public boolean isFormatSupported(Format format) {
        int i = format.width == -1 ? 0 : format.width;
        int i2 = format.height == -1 ? 0 : format.height;
        float f = format.frameRate == -1.0f ? 0.0f : format.frameRate;
        String str = format.sampleMimeType;
        if (str == null) {
            return true;
        }
        try {
            return MediaCodecUtil.getDecoderInfo(str, false, false).isVideoSizeAndRateSupportedV21(i, i2, (double) f);
        } catch (Exception unused) {
            return true;
        }
    }

    private int getGroupIndexForDefaultLocale(TrackGroupArray trackGroupArray) {
        if (trackGroupArray.length == 0) {
            return -1;
        }
        String language = Locale.getDefault().getLanguage();
        String iSO3Language = Locale.getDefault().getISO3Language();
        for (int i = 0; i < trackGroupArray.length; i++) {
            String str = trackGroupArray.get(i).getFormat(0).language;
            if (str != null && (str.equals(language) || str.equals(iSO3Language))) {
                return i;
            }
        }
        return 0;
    }

    public void setSelectedVideoTrack(String str, String str2) {
        this.videoTrackType = str;
        this.videoTrackValue = str2;
        if (!this.loadVideoStarted) {
            setSelectedTrack(2, str, str2);
        }
    }

    public void setSelectedAudioTrack(String str, String str2) {
        this.audioTrackType = str;
        this.audioTrackValue = str2;
        setSelectedTrack(1, str, str2);
    }

    public void setSelectedTextTrack(String str, String str2) {
        this.textTrackType = str;
        this.textTrackValue = str2;
        setSelectedTrack(3, str, str2);
    }

    public void setPausedModifier(boolean z) {
        this.isPaused = z;
        if (this.player == null) {
            return;
        }
        if (!z) {
            resumePlayback();
        } else {
            pausePlayback();
        }
    }

    public void setEnterPictureInPictureOnLeave(boolean z) {
        boolean z2 = z;
        this.enterPictureInPictureOnLeave = z2;
        PictureInPictureUtil.applyAutoEnterEnabled(this.themedReactContext, this.pictureInPictureParamsBuilder, z2);
    }

    /* access modifiers changed from: protected */
    public void setIsInPictureInPicture(boolean z) {
        this.eventEmitter.onPictureInPictureStatusChanged.invoke(Boolean.valueOf(z));
        FullScreenPlayerView fullScreenPlayerView2 = this.fullScreenPlayerView;
        if (fullScreenPlayerView2 == null || !fullScreenPlayerView2.isShowing()) {
            Activity currentActivity = this.themedReactContext.getCurrentActivity();
            if (currentActivity != null) {
                ViewGroup viewGroup = (ViewGroup) currentActivity.getWindow().getDecorView().findViewById(16908290);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
                if (z) {
                    ViewGroup viewGroup2 = (ViewGroup) this.exoPlayerView.getParent();
                    if (viewGroup2 != null) {
                        viewGroup2.removeView(this.exoPlayerView);
                    }
                    for (int i = 0; i < viewGroup.getChildCount(); i++) {
                        if (viewGroup.getChildAt(i) != this.exoPlayerView) {
                            this.rootViewChildrenOriginalVisibility.add(Integer.valueOf(viewGroup.getChildAt(i).getVisibility()));
                            viewGroup.getChildAt(i).setVisibility(8);
                        }
                    }
                    viewGroup.addView(this.exoPlayerView, layoutParams);
                    return;
                }
                viewGroup.removeView(this.exoPlayerView);
                if (!this.rootViewChildrenOriginalVisibility.isEmpty()) {
                    for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                        viewGroup.getChildAt(i2).setVisibility(this.rootViewChildrenOriginalVisibility.get(i2).intValue());
                    }
                    addView(this.exoPlayerView, 0, layoutParams);
                }
            }
        } else if (z) {
            this.fullScreenPlayerView.hideWithoutPlayer();
        }
    }

    public void enterPictureInPictureMode() {
        this.pictureInPictureParamsBuilder.setActions(PictureInPictureUtil.getPictureInPictureActions(this.themedReactContext, this.isPaused, this.pictureInPictureReceiver));
        PictureInPictureUtil.enterPictureInPictureMode(this.themedReactContext, this.pictureInPictureParamsBuilder.setAspectRatio(PictureInPictureUtil.calcPictureInPictureAspectRatio(this.player)).build());
    }

    public void exitPictureInPictureMode() {
        Activity currentActivity = this.themedReactContext.getCurrentActivity();
        if (currentActivity != null) {
            ViewGroup viewGroup = (ViewGroup) currentActivity.getWindow().getDecorView().findViewById(16908290);
            if (!this.rootViewChildrenOriginalVisibility.isEmpty()) {
                if (this.exoPlayerView.getParent().equals(viewGroup)) {
                    viewGroup.removeView(this.exoPlayerView);
                }
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    viewGroup.getChildAt(i).setVisibility(this.rootViewChildrenOriginalVisibility.get(i).intValue());
                }
                this.rootViewChildrenOriginalVisibility.clear();
            }
            if (currentActivity.isInPictureInPictureMode()) {
                currentActivity.moveTaskToBack(false);
            }
        }
    }

    public void setMutedModifier(boolean z) {
        float f;
        this.muted = z;
        ExoPlayer exoPlayer = this.player;
        if (exoPlayer != null) {
            if (z) {
                f = 0.0f;
            } else {
                f = this.audioVolume;
            }
            exoPlayer.setVolume(f);
        }
    }

    private void changeAudioOutput(AudioOutput audioOutput2) {
        if (this.player != null) {
            int streamType = audioOutput2.getStreamType();
            int i = 0;
            this.player.setAudioAttributes(new AudioAttributes.Builder().setUsage(Util.getAudioUsageForStreamType(streamType)).setContentType(Util.getAudioContentTypeForStreamType(streamType)).build(), false);
            AudioManager audioManager2 = (AudioManager) this.themedReactContext.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
            boolean z = audioOutput2 == AudioOutput.SPEAKER;
            if (!z) {
                i = 3;
            }
            audioManager2.setMode(i);
            audioManager2.setSpeakerphoneOn(z);
        }
    }

    public void setAudioOutput(AudioOutput audioOutput2) {
        if (this.audioOutput != audioOutput2) {
            this.audioOutput = audioOutput2;
            changeAudioOutput(audioOutput2);
        }
    }

    public void setVolumeModifier(float f) {
        this.audioVolume = f;
        ExoPlayer exoPlayer = this.player;
        if (exoPlayer != null) {
            exoPlayer.setVolume(f);
        }
    }

    public void seekTo(long j) {
        ExoPlayer exoPlayer = this.player;
        if (exoPlayer != null) {
            exoPlayer.seekTo(j);
        }
    }

    public void setRateModifier(float f) {
        if (f <= 0.0f) {
            DebugLog.w(TAG, "cannot set rate <= 0");
            return;
        }
        this.rate = f;
        if (this.player != null) {
            this.player.setPlaybackParameters(new PlaybackParameters(this.rate, 1.0f));
        }
    }

    public void setMaxBitRateModifier(int i) {
        this.maxBitRate = i;
        if (this.player != null && isUsingVideoABR()) {
            DefaultTrackSelector defaultTrackSelector = this.trackSelector;
            DefaultTrackSelector.Parameters.Builder buildUponParameters = defaultTrackSelector.buildUponParameters();
            int i2 = this.maxBitRate;
            if (i2 == 0) {
                i2 = Integer.MAX_VALUE;
            }
            defaultTrackSelector.setParameters(buildUponParameters.setMaxVideoBitrate(i2));
        }
    }

    public void setPlayInBackground(boolean z) {
        this.playInBackground = z;
    }

    public void setDisableFocus(boolean z) {
        this.disableFocus = z;
    }

    public void setFocusable(boolean z) {
        this.focusable = z;
        this.exoPlayerView.setFocusable(z);
    }

    public void setShowNotificationControls(boolean z) {
        this.showNotificationControls = z;
        ServiceConnection serviceConnection = this.playbackServiceConnection;
        if (serviceConnection == null && z) {
            setupPlaybackService();
        } else if (!z && serviceConnection != null) {
            cleanupPlaybackService();
        }
    }

    public void setBufferingStrategy(BufferingStrategy.BufferingStrategyEnum bufferingStrategyEnum) {
        this.bufferingStrategy = bufferingStrategyEnum;
    }

    public boolean getPreventsDisplaySleepDuringVideoPlayback() {
        return this.preventsDisplaySleepDuringVideoPlayback;
    }

    private void updateFullScreenButtonVisibility() {
        FullScreenPlayerView fullScreenPlayerView2;
        LegacyPlayerControlView legacyPlayerControlView = this.playerControlView;
        if (legacyPlayerControlView != null) {
            ImageButton imageButton = (ImageButton) legacyPlayerControlView.findViewById(R.id.exo_fullscreen);
            if (!this.isFullscreen || (fullScreenPlayerView2 = this.fullScreenPlayerView) == null || fullScreenPlayerView2.isShowing()) {
                imageButton.setVisibility(0);
            } else {
                imageButton.setVisibility(8);
            }
        }
    }

    public void setDisableDisconnectError(boolean z) {
        this.disableDisconnectError = z;
    }

    public void setFullscreen(boolean z) {
        if (z != this.isFullscreen) {
            this.isFullscreen = z;
            if (this.themedReactContext.getCurrentActivity() != null) {
                if (this.isFullscreen) {
                    this.fullScreenPlayerView = new FullScreenPlayerView(getContext(), this.exoPlayerView, this, this.playerControlView, new OnBackPressedCallback(true) {
                        public void handleOnBackPressed() {
                            ReactExoplayerView.this.setFullscreen(false);
                        }
                    }, this.controlsConfig);
                    this.eventEmitter.onVideoFullscreenPlayerWillPresent.invoke();
                    FullScreenPlayerView fullScreenPlayerView2 = this.fullScreenPlayerView;
                    if (fullScreenPlayerView2 != null) {
                        fullScreenPlayerView2.show();
                    }
                    UiThreadUtil.runOnUiThread(new ReactExoplayerView$$ExternalSyntheticLambda4(this));
                } else {
                    this.eventEmitter.onVideoFullscreenPlayerWillDismiss.invoke();
                    FullScreenPlayerView fullScreenPlayerView3 = this.fullScreenPlayerView;
                    if (fullScreenPlayerView3 != null) {
                        fullScreenPlayerView3.dismiss();
                        reLayoutControls();
                        setControls(this.controls);
                    }
                    UiThreadUtil.runOnUiThread(new ReactExoplayerView$$ExternalSyntheticLambda5(this));
                }
                updateFullScreenButtonVisibility();
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setFullscreen$18() {
        this.eventEmitter.onVideoFullscreenPlayerDidPresent.invoke();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setFullscreen$19() {
        this.eventEmitter.onVideoFullscreenPlayerDidDismiss.invoke();
    }

    public void setHideShutterView(boolean z) {
        this.exoPlayerView.setHideShutterView(z);
    }

    public void onDrmKeysLoaded(int i, MediaSource.MediaPeriodId mediaPeriodId) {
        DebugLog.d("DRM Info", "onDrmKeysLoaded");
    }

    public void onDrmSessionAcquired(int i, MediaSource.MediaPeriodId mediaPeriodId, int i2) {
        DebugLog.d("DRM Info", "onDrmSessionAcquired");
    }

    public void onDrmSessionReleased(int i, MediaSource.MediaPeriodId mediaPeriodId) {
        DebugLog.d("DRM Info", "onDrmSessionReleased");
    }

    public void onDrmSessionManagerError(int i, MediaSource.MediaPeriodId mediaPeriodId, Exception exc) {
        DebugLog.d("DRM Info", "onDrmSessionManagerError");
        this.eventEmitter.onVideoError.invoke("onDrmSessionManagerError", exc, "3002");
    }

    public void onDrmKeysRestored(int i, MediaSource.MediaPeriodId mediaPeriodId) {
        DebugLog.d("DRM Info", "onDrmKeysRestored");
    }

    public void onDrmKeysRemoved(int i, MediaSource.MediaPeriodId mediaPeriodId) {
        DebugLog.d("DRM Info", "onDrmKeysRemoved");
    }

    public void setControls(boolean z) {
        this.controls = z;
        if (z) {
            addPlayerControl();
            updateFullScreenButtonVisibility();
        } else {
            int indexOfChild = indexOfChild(this.playerControlView);
            if (indexOfChild != -1) {
                removeViewAt(indexOfChild);
            }
        }
        refreshControlsStyles();
    }

    public void setSubtitleStyle(SubtitleStyle subtitleStyle) {
        this.exoPlayerView.setSubtitleStyle(subtitleStyle);
    }

    public void setShutterColor(Integer num) {
        this.exoPlayerView.setShutterColor(num.intValue());
    }

    public void onAdEvent(AdEvent adEvent) {
        if (adEvent.getAdData() != null) {
            this.eventEmitter.onReceiveAdEvent.invoke(adEvent.getType().name(), adEvent.getAdData());
        } else {
            this.eventEmitter.onReceiveAdEvent.invoke(adEvent.getType().name(), null);
        }
    }

    public void onAdError(AdErrorEvent adErrorEvent) {
        AdError error = adErrorEvent.getError();
        this.eventEmitter.onReceiveAdEvent.invoke("ERROR", CdnGlideModelLoader$$ExternalSyntheticBackport0.m(new Map.Entry[]{new AbstractMap.SimpleEntry("message", error.getMessage()), new AbstractMap.SimpleEntry(UniversalFirebaseFunctionsModule.CODE_KEY, String.valueOf(error.getErrorCode())), new AbstractMap.SimpleEntry("type", String.valueOf(error.getErrorType()))}));
    }

    public void setControlsStyles(ControlsConfig controlsConfig2) {
        this.controlsConfig = controlsConfig2;
        refreshControlsStyles();
    }
}

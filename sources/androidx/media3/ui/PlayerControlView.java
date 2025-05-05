package androidx.media3.ui;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.core.content.res.ResourcesCompat;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.TrackSelectionOverride;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.RepeatModeUtil;
import androidx.media3.common.util.Util;
import androidx.media3.ui.TimeBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;

public class PlayerControlView extends FrameLayout {
    public static final int DEFAULT_REPEAT_TOGGLE_MODES = 0;
    public static final int DEFAULT_SHOW_TIMEOUT_MS = 5000;
    public static final int DEFAULT_TIME_BAR_MIN_UPDATE_INTERVAL_MS = 200;
    private static final int MAX_UPDATE_INTERVAL_MS = 1000;
    public static final int MAX_WINDOWS_FOR_MULTI_WINDOW_TIME_BAR = 100;
    private static final float[] PLAYBACK_SPEEDS = {0.25f, 0.5f, 0.75f, 1.0f, 1.25f, 1.5f, 2.0f};
    private static final int SETTINGS_AUDIO_TRACK_SELECTION_POSITION = 1;
    private static final int SETTINGS_PLAYBACK_SPEED_POSITION = 0;
    private long[] adGroupTimesMs;
    /* access modifiers changed from: private */
    public final View audioTrackButton;
    /* access modifiers changed from: private */
    public final AudioTrackSelectionAdapter audioTrackSelectionAdapter;
    private final float buttonAlphaDisabled;
    private final float buttonAlphaEnabled;
    private final ComponentListener componentListener;
    /* access modifiers changed from: private */
    public final PlayerControlViewLayoutManager controlViewLayoutManager;
    private long currentWindowOffset;
    private final TextView durationView;
    private long[] extraAdGroupTimesMs;
    private boolean[] extraPlayedAdGroups;
    /* access modifiers changed from: private */
    public final View fastForwardButton;
    private final TextView fastForwardButtonTextView;
    /* access modifiers changed from: private */
    public final StringBuilder formatBuilder;
    /* access modifiers changed from: private */
    public final Formatter formatter;
    private final ImageView fullscreenButton;
    private final String fullscreenEnterContentDescription;
    private final Drawable fullscreenEnterDrawable;
    private final String fullscreenExitContentDescription;
    private final Drawable fullscreenExitDrawable;
    private boolean isAttachedToWindow;
    private boolean isFullscreen;
    private final ImageView minimalFullscreenButton;
    private boolean multiWindowTimeBar;
    /* access modifiers changed from: private */
    public boolean needToHideBars;
    /* access modifiers changed from: private */
    public final ImageView nextButton;
    private OnFullScreenModeChangedListener onFullScreenModeChangedListener;
    private final Drawable pauseButtonDrawable;
    private final Timeline.Period period;
    private final Drawable playButtonDrawable;
    /* access modifiers changed from: private */
    public final ImageView playPauseButton;
    /* access modifiers changed from: private */
    public final PlaybackSpeedAdapter playbackSpeedAdapter;
    /* access modifiers changed from: private */
    public final View playbackSpeedButton;
    private boolean[] playedAdGroups;
    /* access modifiers changed from: private */
    public Player player;
    /* access modifiers changed from: private */
    public final TextView positionView;
    /* access modifiers changed from: private */
    public final ImageView previousButton;
    private ProgressUpdateListener progressUpdateListener;
    private final String repeatAllButtonContentDescription;
    private final Drawable repeatAllButtonDrawable;
    private final String repeatOffButtonContentDescription;
    private final Drawable repeatOffButtonDrawable;
    private final String repeatOneButtonContentDescription;
    private final Drawable repeatOneButtonDrawable;
    /* access modifiers changed from: private */
    public final ImageView repeatToggleButton;
    /* access modifiers changed from: private */
    public int repeatToggleModes;
    private final Resources resources;
    /* access modifiers changed from: private */
    public final View rewindButton;
    private final TextView rewindButtonTextView;
    /* access modifiers changed from: private */
    public boolean scrubbing;
    /* access modifiers changed from: private */
    public final SettingsAdapter settingsAdapter;
    /* access modifiers changed from: private */
    public final View settingsButton;
    private final RecyclerView settingsView;
    /* access modifiers changed from: private */
    public final PopupWindow settingsWindow;
    private final int settingsWindowMargin;
    private boolean showMultiWindowTimeBar;
    /* access modifiers changed from: private */
    public boolean showPlayButtonIfSuppressed;
    private int showTimeoutMs;
    /* access modifiers changed from: private */
    public final ImageView shuffleButton;
    private final Drawable shuffleOffButtonDrawable;
    private final String shuffleOffContentDescription;
    private final Drawable shuffleOnButtonDrawable;
    private final String shuffleOnContentDescription;
    /* access modifiers changed from: private */
    public final ImageView subtitleButton;
    /* access modifiers changed from: private */
    public final Drawable subtitleOffButtonDrawable;
    /* access modifiers changed from: private */
    public final String subtitleOffContentDescription;
    /* access modifiers changed from: private */
    public final Drawable subtitleOnButtonDrawable;
    /* access modifiers changed from: private */
    public final String subtitleOnContentDescription;
    /* access modifiers changed from: private */
    public final TextTrackSelectionAdapter textTrackSelectionAdapter;
    private final TimeBar timeBar;
    private int timeBarMinUpdateIntervalMs;
    private final TrackNameProvider trackNameProvider;
    private final Runnable updateProgressAction;
    private final CopyOnWriteArrayList<VisibilityListener> visibilityListeners;
    private final ImageView vrButton;
    private final Timeline.Window window;

    @Deprecated
    public interface OnFullScreenModeChangedListener {
        void onFullScreenModeChanged(boolean z);
    }

    public interface ProgressUpdateListener {
        void onProgressUpdate(long j, long j2);
    }

    @Deprecated
    public interface VisibilityListener {
        void onVisibilityChange(int i);
    }

    private static boolean isHandledMediaKey(int i) {
        return i == 90 || i == 89 || i == 85 || i == 79 || i == 126 || i == 127 || i == 87 || i == 88;
    }

    static {
        MediaLibraryInfo.registerModule("media3.ui");
    }

    public PlayerControlView(Context context) {
        this(context, (AttributeSet) null);
    }

    public PlayerControlView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PlayerControlView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, attributeSet);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PlayerControlView(Context context, AttributeSet attributeSet, int i, AttributeSet attributeSet2) {
        super(context, attributeSet, i);
        int i2;
        boolean z;
        int i3;
        boolean z2;
        boolean z3;
        boolean z4;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        boolean z5;
        boolean z6;
        boolean z7;
        boolean z8;
        int i12;
        int i13;
        int i14;
        PlayerControlView playerControlView;
        int i15;
        int i16;
        int i17;
        int i18;
        ImageView imageView;
        int i19;
        ComponentListener componentListener2;
        boolean z9;
        boolean z10;
        int i20;
        int i21;
        PlayerControlView playerControlView2;
        boolean z11;
        boolean z12;
        Context context2 = context;
        AttributeSet attributeSet3 = attributeSet2;
        int i22 = R.layout.exo_player_control_view;
        int i23 = R.drawable.exo_styled_controls_play;
        int i24 = R.drawable.exo_styled_controls_pause;
        int i25 = R.drawable.exo_styled_controls_next;
        int i26 = R.drawable.exo_styled_controls_simple_fastforward;
        int i27 = R.drawable.exo_styled_controls_previous;
        int i28 = R.drawable.exo_styled_controls_simple_rewind;
        int i29 = R.drawable.exo_styled_controls_fullscreen_exit;
        int i30 = R.drawable.exo_styled_controls_fullscreen_enter;
        int i31 = R.drawable.exo_styled_controls_repeat_off;
        int i32 = R.drawable.exo_styled_controls_repeat_one;
        int i33 = R.drawable.exo_styled_controls_repeat_all;
        int i34 = R.drawable.exo_styled_controls_shuffle_on;
        int i35 = R.drawable.exo_styled_controls_shuffle_off;
        int i36 = R.drawable.exo_styled_controls_subtitle_on;
        int i37 = R.drawable.exo_styled_controls_subtitle_off;
        int i38 = R.drawable.exo_styled_controls_vr;
        this.showPlayButtonIfSuppressed = true;
        this.showTimeoutMs = 5000;
        this.repeatToggleModes = 0;
        this.timeBarMinUpdateIntervalMs = 200;
        if (attributeSet3 != null) {
            int i39 = i33;
            int i40 = i34;
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet3, R.styleable.PlayerControlView, i, 0);
            try {
                int resourceId = obtainStyledAttributes.getResourceId(R.styleable.PlayerControlView_controller_layout_id, i22);
                int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.PlayerControlView_play_icon, i23);
                int resourceId3 = obtainStyledAttributes.getResourceId(R.styleable.PlayerControlView_pause_icon, i24);
                int resourceId4 = obtainStyledAttributes.getResourceId(R.styleable.PlayerControlView_next_icon, i25);
                int resourceId5 = obtainStyledAttributes.getResourceId(R.styleable.PlayerControlView_fastforward_icon, i26);
                int resourceId6 = obtainStyledAttributes.getResourceId(R.styleable.PlayerControlView_previous_icon, i27);
                int resourceId7 = obtainStyledAttributes.getResourceId(R.styleable.PlayerControlView_rewind_icon, i28);
                int resourceId8 = obtainStyledAttributes.getResourceId(R.styleable.PlayerControlView_fullscreen_exit_icon, i29);
                int resourceId9 = obtainStyledAttributes.getResourceId(R.styleable.PlayerControlView_fullscreen_enter_icon, i30);
                int resourceId10 = obtainStyledAttributes.getResourceId(R.styleable.PlayerControlView_repeat_off_icon, i31);
                int resourceId11 = obtainStyledAttributes.getResourceId(R.styleable.PlayerControlView_repeat_one_icon, i32);
                int resourceId12 = obtainStyledAttributes.getResourceId(R.styleable.PlayerControlView_repeat_all_icon, i39);
                int resourceId13 = obtainStyledAttributes.getResourceId(R.styleable.PlayerControlView_shuffle_on_icon, i40);
                int i41 = resourceId;
                int resourceId14 = obtainStyledAttributes.getResourceId(R.styleable.PlayerControlView_shuffle_off_icon, i35);
                int resourceId15 = obtainStyledAttributes.getResourceId(R.styleable.PlayerControlView_subtitle_on_icon, i36);
                int resourceId16 = obtainStyledAttributes.getResourceId(R.styleable.PlayerControlView_subtitle_off_icon, i37);
                int resourceId17 = obtainStyledAttributes.getResourceId(R.styleable.PlayerControlView_vr_icon, i38);
                int i42 = resourceId4;
                playerControlView = this;
                try {
                    playerControlView.showTimeoutMs = obtainStyledAttributes.getInt(R.styleable.PlayerControlView_show_timeout, playerControlView.showTimeoutMs);
                    playerControlView.repeatToggleModes = getRepeatToggleModes(obtainStyledAttributes, playerControlView.repeatToggleModes);
                    boolean z13 = obtainStyledAttributes.getBoolean(R.styleable.PlayerControlView_show_rewind_button, true);
                    boolean z14 = obtainStyledAttributes.getBoolean(R.styleable.PlayerControlView_show_fastforward_button, true);
                    boolean z15 = obtainStyledAttributes.getBoolean(R.styleable.PlayerControlView_show_previous_button, true);
                    boolean z16 = obtainStyledAttributes.getBoolean(R.styleable.PlayerControlView_show_next_button, true);
                    boolean z17 = obtainStyledAttributes.getBoolean(R.styleable.PlayerControlView_show_shuffle_button, false);
                    boolean z18 = obtainStyledAttributes.getBoolean(R.styleable.PlayerControlView_show_subtitle_button, false);
                    boolean z19 = obtainStyledAttributes.getBoolean(R.styleable.PlayerControlView_show_vr_button, false);
                    playerControlView.setTimeBarMinUpdateInterval(obtainStyledAttributes.getInt(R.styleable.PlayerControlView_time_bar_min_update_interval, playerControlView.timeBarMinUpdateIntervalMs));
                    boolean z20 = obtainStyledAttributes.getBoolean(R.styleable.PlayerControlView_animation_enabled, true);
                    obtainStyledAttributes.recycle();
                    i17 = resourceId14;
                    i16 = i41;
                    z = z20;
                    i2 = resourceId6;
                    i9 = resourceId7;
                    i8 = resourceId8;
                    i7 = resourceId9;
                    i6 = resourceId10;
                    i5 = resourceId11;
                    i4 = resourceId12;
                    i3 = resourceId13;
                    i13 = resourceId15;
                    i12 = resourceId16;
                    i18 = resourceId17;
                    z8 = z13;
                    z7 = z14;
                    z6 = z15;
                    z5 = z16;
                    z4 = z17;
                    z3 = z18;
                    z2 = z19;
                    i11 = resourceId2;
                    i10 = resourceId3;
                    i15 = resourceId5;
                    i14 = i42;
                } catch (Throwable th) {
                    th = th;
                    obtainStyledAttributes.recycle();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                obtainStyledAttributes.recycle();
                throw th;
            }
        } else {
            i18 = i38;
            int i43 = i37;
            int i44 = i36;
            int i45 = i25;
            playerControlView = this;
            int i46 = i22;
            int i47 = i35;
            i2 = i27;
            i9 = i28;
            i8 = i29;
            i7 = i30;
            i6 = i31;
            i5 = i32;
            i4 = i33;
            i3 = i34;
            i13 = i44;
            i12 = i43;
            z8 = true;
            z7 = true;
            z6 = true;
            z5 = true;
            z4 = false;
            z3 = false;
            z2 = false;
            z = true;
            i11 = i23;
            i10 = i24;
            i15 = i26;
            i14 = i45;
            int i48 = i47;
            i16 = i46;
            i17 = i48;
        }
        LayoutInflater.from(context).inflate(i16, playerControlView);
        playerControlView.setDescendantFocusability(262144);
        ComponentListener componentListener3 = new ComponentListener();
        playerControlView.componentListener = componentListener3;
        playerControlView.visibilityListeners = new CopyOnWriteArrayList<>();
        playerControlView.period = new Timeline.Period();
        playerControlView.window = new Timeline.Window();
        StringBuilder sb = new StringBuilder();
        playerControlView.formatBuilder = sb;
        int i49 = i15;
        playerControlView.formatter = new Formatter(sb, Locale.getDefault());
        playerControlView.adGroupTimesMs = new long[0];
        playerControlView.playedAdGroups = new boolean[0];
        playerControlView.extraAdGroupTimesMs = new long[0];
        playerControlView.extraPlayedAdGroups = new boolean[0];
        playerControlView.updateProgressAction = new PlayerControlView$$ExternalSyntheticLambda0(playerControlView);
        playerControlView.durationView = (TextView) playerControlView.findViewById(R.id.exo_duration);
        playerControlView.positionView = (TextView) playerControlView.findViewById(R.id.exo_position);
        ImageView imageView2 = (ImageView) playerControlView.findViewById(R.id.exo_subtitle);
        playerControlView.subtitleButton = imageView2;
        if (imageView2 != null) {
            imageView2.setOnClickListener(componentListener3);
        }
        ImageView imageView3 = (ImageView) playerControlView.findViewById(R.id.exo_fullscreen);
        playerControlView.fullscreenButton = imageView3;
        initializeFullscreenButton(imageView3, new PlayerControlView$$ExternalSyntheticLambda1(playerControlView));
        ImageView imageView4 = (ImageView) playerControlView.findViewById(R.id.exo_minimal_fullscreen);
        playerControlView.minimalFullscreenButton = imageView4;
        initializeFullscreenButton(imageView4, new PlayerControlView$$ExternalSyntheticLambda1(playerControlView));
        View findViewById = playerControlView.findViewById(R.id.exo_settings);
        playerControlView.settingsButton = findViewById;
        if (findViewById != null) {
            findViewById.setOnClickListener(componentListener3);
        }
        View findViewById2 = playerControlView.findViewById(R.id.exo_playback_speed);
        playerControlView.playbackSpeedButton = findViewById2;
        if (findViewById2 != null) {
            findViewById2.setOnClickListener(componentListener3);
        }
        View findViewById3 = playerControlView.findViewById(R.id.exo_audio_track);
        playerControlView.audioTrackButton = findViewById3;
        if (findViewById3 != null) {
            findViewById3.setOnClickListener(componentListener3);
        }
        TimeBar timeBar2 = (TimeBar) playerControlView.findViewById(R.id.exo_progress);
        View findViewById4 = playerControlView.findViewById(R.id.exo_progress_placeholder);
        if (timeBar2 != null) {
            playerControlView.timeBar = timeBar2;
            i19 = i2;
            componentListener2 = componentListener3;
            playerControlView2 = playerControlView;
            imageView = imageView2;
            z9 = z6;
            i21 = i14;
            int i50 = i49;
            z10 = z5;
            i20 = i50;
        } else if (findViewById4 != null) {
            DefaultTimeBar defaultTimeBar = r2;
            i19 = i2;
            componentListener2 = componentListener3;
            View view = findViewById4;
            int i51 = i49;
            z10 = z5;
            i20 = i51;
            imageView = imageView2;
            z9 = z6;
            i21 = i14;
            DefaultTimeBar defaultTimeBar2 = new DefaultTimeBar(context, (AttributeSet) null, 0, attributeSet2, R.style.ExoStyledControls_TimeBar);
            DefaultTimeBar defaultTimeBar3 = defaultTimeBar;
            defaultTimeBar3.setId(R.id.exo_progress);
            defaultTimeBar3.setLayoutParams(view.getLayoutParams());
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            View view2 = view;
            int indexOfChild = viewGroup.indexOfChild(view2);
            viewGroup.removeView(view2);
            viewGroup.addView(defaultTimeBar3, indexOfChild);
            playerControlView2 = this;
            playerControlView2.timeBar = defaultTimeBar3;
        } else {
            i19 = i2;
            componentListener2 = componentListener3;
            playerControlView2 = playerControlView;
            imageView = imageView2;
            z9 = z6;
            i21 = i14;
            int i52 = i49;
            z10 = z5;
            i20 = i52;
            playerControlView2.timeBar = null;
        }
        TimeBar timeBar3 = playerControlView2.timeBar;
        ComponentListener componentListener4 = componentListener2;
        if (timeBar3 != null) {
            timeBar3.addListener(componentListener4);
        }
        Resources resources2 = context.getResources();
        playerControlView2.resources = resources2;
        ImageView imageView5 = (ImageView) playerControlView2.findViewById(R.id.exo_play_pause);
        playerControlView2.playPauseButton = imageView5;
        if (imageView5 != null) {
            imageView5.setOnClickListener(componentListener4);
        }
        ImageView imageView6 = (ImageView) playerControlView2.findViewById(R.id.exo_prev);
        playerControlView2.previousButton = imageView6;
        Context context3 = context;
        if (imageView6 != null) {
            imageView6.setImageDrawable(Util.getDrawable(context3, resources2, i19));
            imageView6.setOnClickListener(componentListener4);
        }
        ImageView imageView7 = (ImageView) playerControlView2.findViewById(R.id.exo_next);
        playerControlView2.nextButton = imageView7;
        if (imageView7 != null) {
            imageView7.setImageDrawable(Util.getDrawable(context3, resources2, i21));
            imageView7.setOnClickListener(componentListener4);
        }
        Typeface font = ResourcesCompat.getFont(context3, R.font.roboto_medium_numbers);
        ImageView imageView8 = imageView7;
        ImageView imageView9 = (ImageView) playerControlView2.findViewById(R.id.exo_rew);
        ImageView imageView10 = imageView6;
        TextView textView = (TextView) playerControlView2.findViewById(R.id.exo_rew_with_amount);
        if (imageView9 != null) {
            z11 = z8;
            imageView9.setImageDrawable(Util.getDrawable(context3, resources2, i9));
            playerControlView2.rewindButton = imageView9;
            playerControlView2.rewindButtonTextView = null;
        } else {
            z11 = z8;
            if (textView != null) {
                textView.setTypeface(font);
                playerControlView2.rewindButtonTextView = textView;
                playerControlView2.rewindButton = textView;
            } else {
                playerControlView2.rewindButtonTextView = null;
                playerControlView2.rewindButton = null;
            }
        }
        View view3 = playerControlView2.rewindButton;
        if (view3 != null) {
            view3.setOnClickListener(componentListener4);
        }
        ImageView imageView11 = (ImageView) playerControlView2.findViewById(R.id.exo_ffwd);
        TextView textView2 = (TextView) playerControlView2.findViewById(R.id.exo_ffwd_with_amount);
        if (imageView11 != null) {
            imageView11.setImageDrawable(Util.getDrawable(context3, resources2, i20));
            playerControlView2.fastForwardButton = imageView11;
            playerControlView2.fastForwardButtonTextView = null;
        } else if (textView2 != null) {
            textView2.setTypeface(font);
            playerControlView2.fastForwardButtonTextView = textView2;
            playerControlView2.fastForwardButton = textView2;
        } else {
            playerControlView2.fastForwardButtonTextView = null;
            playerControlView2.fastForwardButton = null;
        }
        View view4 = playerControlView2.fastForwardButton;
        if (view4 != null) {
            view4.setOnClickListener(componentListener4);
        }
        ImageView imageView12 = (ImageView) playerControlView2.findViewById(R.id.exo_repeat_toggle);
        playerControlView2.repeatToggleButton = imageView12;
        if (imageView12 != null) {
            imageView12.setOnClickListener(componentListener4);
        }
        ImageView imageView13 = (ImageView) playerControlView2.findViewById(R.id.exo_shuffle);
        playerControlView2.shuffleButton = imageView13;
        if (imageView13 != null) {
            imageView13.setOnClickListener(componentListener4);
        }
        playerControlView2.buttonAlphaEnabled = ((float) resources2.getInteger(R.integer.exo_media_button_opacity_percentage_enabled)) / 100.0f;
        playerControlView2.buttonAlphaDisabled = ((float) resources2.getInteger(R.integer.exo_media_button_opacity_percentage_disabled)) / 100.0f;
        ImageView imageView14 = (ImageView) playerControlView2.findViewById(R.id.exo_vr);
        playerControlView2.vrButton = imageView14;
        if (imageView14 != null) {
            imageView14.setImageDrawable(Util.getDrawable(context3, resources2, i18));
            playerControlView2.updateButton(false, imageView14);
        }
        PlayerControlViewLayoutManager playerControlViewLayoutManager = new PlayerControlViewLayoutManager(playerControlView2);
        playerControlView2.controlViewLayoutManager = playerControlViewLayoutManager;
        playerControlViewLayoutManager.setAnimationEnabled(z);
        ImageView imageView15 = imageView12;
        SettingsAdapter settingsAdapter2 = new SettingsAdapter(new String[]{resources2.getString(R.string.exo_controls_playback_speed), resources2.getString(R.string.exo_track_selection_title_audio)}, new Drawable[]{Util.getDrawable(context3, resources2, R.drawable.exo_styled_controls_speed), Util.getDrawable(context3, resources2, R.drawable.exo_styled_controls_audiotrack)});
        playerControlView2.settingsAdapter = settingsAdapter2;
        playerControlView2.settingsWindowMargin = resources2.getDimensionPixelSize(R.dimen.exo_settings_offset);
        ImageView imageView16 = imageView14;
        RecyclerView recyclerView = (RecyclerView) LayoutInflater.from(context).inflate(R.layout.exo_styled_settings_list, (ViewGroup) null);
        playerControlView2.settingsView = recyclerView;
        recyclerView.setAdapter(settingsAdapter2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        PopupWindow popupWindow = new PopupWindow(recyclerView, -2, -2, true);
        playerControlView2.settingsWindow = popupWindow;
        if (Util.SDK_INT < 23) {
            z12 = false;
            popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        } else {
            z12 = false;
        }
        popupWindow.setOnDismissListener(componentListener4);
        playerControlView2.needToHideBars = true;
        playerControlView2.trackNameProvider = new DefaultTrackNameProvider(getResources());
        playerControlView2.subtitleOnButtonDrawable = Util.getDrawable(context3, resources2, i13);
        playerControlView2.subtitleOffButtonDrawable = Util.getDrawable(context3, resources2, i12);
        playerControlView2.subtitleOnContentDescription = resources2.getString(R.string.exo_controls_cc_enabled_description);
        playerControlView2.subtitleOffContentDescription = resources2.getString(R.string.exo_controls_cc_disabled_description);
        playerControlView2.textTrackSelectionAdapter = new TextTrackSelectionAdapter();
        playerControlView2.audioTrackSelectionAdapter = new AudioTrackSelectionAdapter();
        playerControlView2.playbackSpeedAdapter = new PlaybackSpeedAdapter(resources2.getStringArray(R.array.exo_controls_playback_speeds), PLAYBACK_SPEEDS);
        playerControlView2.playButtonDrawable = Util.getDrawable(context3, resources2, i11);
        playerControlView2.pauseButtonDrawable = Util.getDrawable(context3, resources2, i10);
        playerControlView2.fullscreenExitDrawable = Util.getDrawable(context3, resources2, i8);
        playerControlView2.fullscreenEnterDrawable = Util.getDrawable(context3, resources2, i7);
        playerControlView2.repeatOffButtonDrawable = Util.getDrawable(context3, resources2, i6);
        playerControlView2.repeatOneButtonDrawable = Util.getDrawable(context3, resources2, i5);
        playerControlView2.repeatAllButtonDrawable = Util.getDrawable(context3, resources2, i4);
        playerControlView2.shuffleOnButtonDrawable = Util.getDrawable(context3, resources2, i3);
        playerControlView2.shuffleOffButtonDrawable = Util.getDrawable(context3, resources2, i17);
        playerControlView2.fullscreenExitContentDescription = resources2.getString(R.string.exo_controls_fullscreen_exit_description);
        playerControlView2.fullscreenEnterContentDescription = resources2.getString(R.string.exo_controls_fullscreen_enter_description);
        playerControlView2.repeatOffButtonContentDescription = resources2.getString(R.string.exo_controls_repeat_off_description);
        playerControlView2.repeatOneButtonContentDescription = resources2.getString(R.string.exo_controls_repeat_one_description);
        playerControlView2.repeatAllButtonContentDescription = resources2.getString(R.string.exo_controls_repeat_all_description);
        playerControlView2.shuffleOnContentDescription = resources2.getString(R.string.exo_controls_shuffle_on_description);
        playerControlView2.shuffleOffContentDescription = resources2.getString(R.string.exo_controls_shuffle_off_description);
        playerControlViewLayoutManager.setShowButton((ViewGroup) playerControlView2.findViewById(R.id.exo_bottom_bar), true);
        playerControlViewLayoutManager.setShowButton(playerControlView2.fastForwardButton, z7);
        playerControlViewLayoutManager.setShowButton(playerControlView2.rewindButton, z11);
        playerControlViewLayoutManager.setShowButton(imageView10, z9);
        playerControlViewLayoutManager.setShowButton(imageView8, z10);
        playerControlViewLayoutManager.setShowButton(imageView13, z4);
        playerControlViewLayoutManager.setShowButton(imageView, z3);
        playerControlViewLayoutManager.setShowButton(imageView16, z2);
        playerControlViewLayoutManager.setShowButton(imageView15, playerControlView2.repeatToggleModes != 0 ? true : z12);
        playerControlView2.addOnLayoutChangeListener(new PlayerControlView$$ExternalSyntheticLambda2(playerControlView2));
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player2) {
        boolean z = true;
        Assertions.checkState(Looper.myLooper() == Looper.getMainLooper());
        if (!(player2 == null || player2.getApplicationLooper() == Looper.getMainLooper())) {
            z = false;
        }
        Assertions.checkArgument(z);
        Player player3 = this.player;
        if (player3 != player2) {
            if (player3 != null) {
                player3.removeListener(this.componentListener);
            }
            this.player = player2;
            if (player2 != null) {
                player2.addListener(this.componentListener);
            }
            updateAll();
        }
    }

    @Deprecated
    public void setShowMultiWindowTimeBar(boolean z) {
        this.showMultiWindowTimeBar = z;
        updateTimeline();
    }

    public void setShowPlayButtonIfPlaybackIsSuppressed(boolean z) {
        this.showPlayButtonIfSuppressed = z;
        updatePlayPauseButton();
    }

    public void setExtraAdGroupMarkers(long[] jArr, boolean[] zArr) {
        boolean z = false;
        if (jArr == null) {
            this.extraAdGroupTimesMs = new long[0];
            this.extraPlayedAdGroups = new boolean[0];
        } else {
            boolean[] zArr2 = (boolean[]) Assertions.checkNotNull(zArr);
            if (jArr.length == zArr2.length) {
                z = true;
            }
            Assertions.checkArgument(z);
            this.extraAdGroupTimesMs = jArr;
            this.extraPlayedAdGroups = zArr2;
        }
        updateTimeline();
    }

    @Deprecated
    public void addVisibilityListener(VisibilityListener visibilityListener) {
        Assertions.checkNotNull(visibilityListener);
        this.visibilityListeners.add(visibilityListener);
    }

    @Deprecated
    public void removeVisibilityListener(VisibilityListener visibilityListener) {
        this.visibilityListeners.remove(visibilityListener);
    }

    public void setProgressUpdateListener(ProgressUpdateListener progressUpdateListener2) {
        this.progressUpdateListener = progressUpdateListener2;
    }

    public void setShowRewindButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.rewindButton, z);
        updateNavigation();
    }

    public void setShowFastForwardButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.fastForwardButton, z);
        updateNavigation();
    }

    public void setShowPreviousButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.previousButton, z);
        updateNavigation();
    }

    public void setShowNextButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.nextButton, z);
        updateNavigation();
    }

    public int getShowTimeoutMs() {
        return this.showTimeoutMs;
    }

    public void setShowTimeoutMs(int i) {
        this.showTimeoutMs = i;
        if (isFullyVisible()) {
            this.controlViewLayoutManager.resetHideCallbacks();
        }
    }

    public int getRepeatToggleModes() {
        return this.repeatToggleModes;
    }

    public void setRepeatToggleModes(int i) {
        this.repeatToggleModes = i;
        Player player2 = this.player;
        boolean z = false;
        if (player2 != null && player2.isCommandAvailable(15)) {
            int repeatMode = this.player.getRepeatMode();
            if (i == 0 && repeatMode != 0) {
                this.player.setRepeatMode(0);
            } else if (i == 1 && repeatMode == 2) {
                this.player.setRepeatMode(1);
            } else if (i == 2 && repeatMode == 1) {
                this.player.setRepeatMode(2);
            }
        }
        PlayerControlViewLayoutManager playerControlViewLayoutManager = this.controlViewLayoutManager;
        ImageView imageView = this.repeatToggleButton;
        if (i != 0) {
            z = true;
        }
        playerControlViewLayoutManager.setShowButton(imageView, z);
        updateRepeatModeButton();
    }

    public boolean getShowShuffleButton() {
        return this.controlViewLayoutManager.getShowButton(this.shuffleButton);
    }

    public void setShowShuffleButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.shuffleButton, z);
        updateShuffleButton();
    }

    public boolean getShowSubtitleButton() {
        return this.controlViewLayoutManager.getShowButton(this.subtitleButton);
    }

    public void setShowSubtitleButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.subtitleButton, z);
    }

    public boolean getShowVrButton() {
        return this.controlViewLayoutManager.getShowButton(this.vrButton);
    }

    public void setShowVrButton(boolean z) {
        this.controlViewLayoutManager.setShowButton(this.vrButton, z);
    }

    public void setVrButtonListener(View.OnClickListener onClickListener) {
        ImageView imageView = this.vrButton;
        if (imageView != null) {
            imageView.setOnClickListener(onClickListener);
            updateButton(onClickListener != null, this.vrButton);
        }
    }

    public void setAnimationEnabled(boolean z) {
        this.controlViewLayoutManager.setAnimationEnabled(z);
    }

    public boolean isAnimationEnabled() {
        return this.controlViewLayoutManager.isAnimationEnabled();
    }

    public void setTimeBarMinUpdateInterval(int i) {
        this.timeBarMinUpdateIntervalMs = Util.constrainValue(i, 16, 1000);
    }

    @Deprecated
    public void setOnFullScreenModeChangedListener(OnFullScreenModeChangedListener onFullScreenModeChangedListener2) {
        this.onFullScreenModeChangedListener = onFullScreenModeChangedListener2;
        boolean z = true;
        updateFullscreenButtonVisibility(this.fullscreenButton, onFullScreenModeChangedListener2 != null);
        ImageView imageView = this.minimalFullscreenButton;
        if (onFullScreenModeChangedListener2 == null) {
            z = false;
        }
        updateFullscreenButtonVisibility(imageView, z);
    }

    public void show() {
        this.controlViewLayoutManager.show();
    }

    public void hide() {
        this.controlViewLayoutManager.hide();
    }

    public void hideImmediately() {
        this.controlViewLayoutManager.hideImmediately();
    }

    public boolean isFullyVisible() {
        return this.controlViewLayoutManager.isFullyVisible();
    }

    public boolean isVisible() {
        return getVisibility() == 0;
    }

    /* access modifiers changed from: package-private */
    public void notifyOnVisibilityChange() {
        Iterator<VisibilityListener> it = this.visibilityListeners.iterator();
        while (it.hasNext()) {
            it.next().onVisibilityChange(getVisibility());
        }
    }

    /* access modifiers changed from: package-private */
    public void updateAll() {
        updatePlayPauseButton();
        updateNavigation();
        updateRepeatModeButton();
        updateShuffleButton();
        updateTrackLists();
        updatePlaybackSpeedList();
        updateTimeline();
    }

    /* access modifiers changed from: private */
    public void updatePlayPauseButton() {
        int i;
        if (isVisible() && this.isAttachedToWindow && this.playPauseButton != null) {
            boolean shouldShowPlayButton = Util.shouldShowPlayButton(this.player, this.showPlayButtonIfSuppressed);
            Drawable drawable = shouldShowPlayButton ? this.playButtonDrawable : this.pauseButtonDrawable;
            if (shouldShowPlayButton) {
                i = R.string.exo_controls_play_description;
            } else {
                i = R.string.exo_controls_pause_description;
            }
            this.playPauseButton.setImageDrawable(drawable);
            this.playPauseButton.setContentDescription(this.resources.getString(i));
            updateButton(shouldEnablePlayPauseButton(), this.playPauseButton);
        }
    }

    /* access modifiers changed from: private */
    public void updateNavigation() {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        if (isVisible() && this.isAttachedToWindow) {
            Player player2 = this.player;
            if (player2 != null) {
                if (!this.showMultiWindowTimeBar || !canShowMultiWindowTimeBar(player2, this.window)) {
                    z4 = player2.isCommandAvailable(5);
                } else {
                    z4 = player2.isCommandAvailable(10);
                }
                z3 = player2.isCommandAvailable(7);
                z2 = player2.isCommandAvailable(11);
                z = player2.isCommandAvailable(12);
                z5 = player2.isCommandAvailable(9);
            } else {
                z4 = false;
                z5 = false;
                z3 = false;
                z2 = false;
                z = false;
            }
            if (z2) {
                updateRewindButton();
            }
            if (z) {
                updateFastForwardButton();
            }
            updateButton(z3, this.previousButton);
            updateButton(z2, this.rewindButton);
            updateButton(z, this.fastForwardButton);
            updateButton(z5, this.nextButton);
            TimeBar timeBar2 = this.timeBar;
            if (timeBar2 != null) {
                timeBar2.setEnabled(z4);
            }
        }
    }

    private void updateRewindButton() {
        Player player2 = this.player;
        int seekBackIncrement = (int) ((player2 != null ? player2.getSeekBackIncrement() : 5000) / 1000);
        TextView textView = this.rewindButtonTextView;
        if (textView != null) {
            textView.setText(String.valueOf(seekBackIncrement));
        }
        View view = this.rewindButton;
        if (view != null) {
            view.setContentDescription(this.resources.getQuantityString(R.plurals.exo_controls_rewind_by_amount_description, seekBackIncrement, new Object[]{Integer.valueOf(seekBackIncrement)}));
        }
    }

    private void updateFastForwardButton() {
        Player player2 = this.player;
        int seekForwardIncrement = (int) ((player2 != null ? player2.getSeekForwardIncrement() : C.DEFAULT_SEEK_FORWARD_INCREMENT_MS) / 1000);
        TextView textView = this.fastForwardButtonTextView;
        if (textView != null) {
            textView.setText(String.valueOf(seekForwardIncrement));
        }
        View view = this.fastForwardButton;
        if (view != null) {
            view.setContentDescription(this.resources.getQuantityString(R.plurals.exo_controls_fastforward_by_amount_description, seekForwardIncrement, new Object[]{Integer.valueOf(seekForwardIncrement)}));
        }
    }

    /* access modifiers changed from: private */
    public void updateRepeatModeButton() {
        ImageView imageView;
        if (isVisible() && this.isAttachedToWindow && (imageView = this.repeatToggleButton) != null) {
            if (this.repeatToggleModes == 0) {
                updateButton(false, imageView);
                return;
            }
            Player player2 = this.player;
            if (player2 == null || !player2.isCommandAvailable(15)) {
                updateButton(false, this.repeatToggleButton);
                this.repeatToggleButton.setImageDrawable(this.repeatOffButtonDrawable);
                this.repeatToggleButton.setContentDescription(this.repeatOffButtonContentDescription);
                return;
            }
            updateButton(true, this.repeatToggleButton);
            int repeatMode = player2.getRepeatMode();
            if (repeatMode == 0) {
                this.repeatToggleButton.setImageDrawable(this.repeatOffButtonDrawable);
                this.repeatToggleButton.setContentDescription(this.repeatOffButtonContentDescription);
            } else if (repeatMode == 1) {
                this.repeatToggleButton.setImageDrawable(this.repeatOneButtonDrawable);
                this.repeatToggleButton.setContentDescription(this.repeatOneButtonContentDescription);
            } else if (repeatMode == 2) {
                this.repeatToggleButton.setImageDrawable(this.repeatAllButtonDrawable);
                this.repeatToggleButton.setContentDescription(this.repeatAllButtonContentDescription);
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateShuffleButton() {
        ImageView imageView;
        String str;
        if (isVisible() && this.isAttachedToWindow && (imageView = this.shuffleButton) != null) {
            Player player2 = this.player;
            if (!this.controlViewLayoutManager.getShowButton(imageView)) {
                updateButton(false, this.shuffleButton);
            } else if (player2 == null || !player2.isCommandAvailable(14)) {
                updateButton(false, this.shuffleButton);
                this.shuffleButton.setImageDrawable(this.shuffleOffButtonDrawable);
                this.shuffleButton.setContentDescription(this.shuffleOffContentDescription);
            } else {
                updateButton(true, this.shuffleButton);
                this.shuffleButton.setImageDrawable(player2.getShuffleModeEnabled() ? this.shuffleOnButtonDrawable : this.shuffleOffButtonDrawable);
                ImageView imageView2 = this.shuffleButton;
                if (player2.getShuffleModeEnabled()) {
                    str = this.shuffleOnContentDescription;
                } else {
                    str = this.shuffleOffContentDescription;
                }
                imageView2.setContentDescription(str);
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateTrackLists() {
        initTrackSelectionAdapter();
        updateButton(this.textTrackSelectionAdapter.getItemCount() > 0, this.subtitleButton);
        updateSettingsButton();
    }

    private void initTrackSelectionAdapter() {
        this.textTrackSelectionAdapter.clear();
        this.audioTrackSelectionAdapter.clear();
        Player player2 = this.player;
        if (player2 != null && player2.isCommandAvailable(30) && this.player.isCommandAvailable(29)) {
            Tracks currentTracks = this.player.getCurrentTracks();
            this.audioTrackSelectionAdapter.init(gatherSupportedTrackInfosOfType(currentTracks, 1));
            if (this.controlViewLayoutManager.getShowButton(this.subtitleButton)) {
                this.textTrackSelectionAdapter.init(gatherSupportedTrackInfosOfType(currentTracks, 3));
            } else {
                this.textTrackSelectionAdapter.init(ImmutableList.of());
            }
        }
    }

    private ImmutableList<TrackInformation> gatherSupportedTrackInfosOfType(Tracks tracks, int i) {
        ImmutableList.Builder builder = new ImmutableList.Builder();
        ImmutableList<Tracks.Group> groups = tracks.getGroups();
        for (int i2 = 0; i2 < groups.size(); i2++) {
            Tracks.Group group = groups.get(i2);
            if (group.getType() == i) {
                for (int i3 = 0; i3 < group.length; i3++) {
                    if (group.isTrackSupported(i3)) {
                        Format trackFormat = group.getTrackFormat(i3);
                        if ((trackFormat.selectionFlags & 2) == 0) {
                            builder.add((Object) new TrackInformation(tracks, i2, i3, this.trackNameProvider.getTrackName(trackFormat)));
                        }
                    }
                }
            }
        }
        return builder.build();
    }

    /* access modifiers changed from: private */
    public void updateTimeline() {
        Timeline timeline;
        int i;
        long j;
        Player player2 = this.player;
        if (player2 != null) {
            boolean z = true;
            this.multiWindowTimeBar = this.showMultiWindowTimeBar && canShowMultiWindowTimeBar(player2, this.window);
            this.currentWindowOffset = 0;
            if (player2.isCommandAvailable(17)) {
                timeline = player2.getCurrentTimeline();
            } else {
                timeline = Timeline.EMPTY;
            }
            if (!timeline.isEmpty()) {
                int currentMediaItemIndex = player2.getCurrentMediaItemIndex();
                boolean z2 = this.multiWindowTimeBar;
                int i2 = z2 ? 0 : currentMediaItemIndex;
                int windowCount = z2 ? timeline.getWindowCount() - 1 : currentMediaItemIndex;
                long j2 = 0;
                i = 0;
                while (true) {
                    if (i2 > windowCount) {
                        break;
                    }
                    if (i2 == currentMediaItemIndex) {
                        this.currentWindowOffset = Util.usToMs(j2);
                    }
                    timeline.getWindow(i2, this.window);
                    if (this.window.durationUs == C.TIME_UNSET) {
                        Assertions.checkState(this.multiWindowTimeBar ^ z);
                        break;
                    }
                    for (int i3 = this.window.firstPeriodIndex; i3 <= this.window.lastPeriodIndex; i3++) {
                        timeline.getPeriod(i3, this.period);
                        int adGroupCount = this.period.getAdGroupCount();
                        for (int removedAdGroupCount = this.period.getRemovedAdGroupCount(); removedAdGroupCount < adGroupCount; removedAdGroupCount++) {
                            long adGroupTimeUs = this.period.getAdGroupTimeUs(removedAdGroupCount);
                            if (adGroupTimeUs == Long.MIN_VALUE) {
                                if (this.period.durationUs == C.TIME_UNSET) {
                                } else {
                                    adGroupTimeUs = this.period.durationUs;
                                }
                            }
                            long positionInWindowUs = adGroupTimeUs + this.period.getPositionInWindowUs();
                            if (positionInWindowUs >= 0) {
                                long[] jArr = this.adGroupTimesMs;
                                if (i == jArr.length) {
                                    int length = jArr.length == 0 ? 1 : jArr.length * 2;
                                    this.adGroupTimesMs = Arrays.copyOf(jArr, length);
                                    this.playedAdGroups = Arrays.copyOf(this.playedAdGroups, length);
                                }
                                this.adGroupTimesMs[i] = Util.usToMs(j2 + positionInWindowUs);
                                this.playedAdGroups[i] = this.period.hasPlayedAdGroup(removedAdGroupCount);
                                i++;
                            }
                        }
                    }
                    j2 += this.window.durationUs;
                    i2++;
                    z = true;
                }
                j = j2;
            } else {
                if (player2.isCommandAvailable(16)) {
                    long contentDuration = player2.getContentDuration();
                    if (contentDuration != C.TIME_UNSET) {
                        j = Util.msToUs(contentDuration);
                        i = 0;
                    }
                }
                j = 0;
                i = 0;
            }
            long usToMs = Util.usToMs(j);
            TextView textView = this.durationView;
            if (textView != null) {
                textView.setText(Util.getStringForTime(this.formatBuilder, this.formatter, usToMs));
            }
            TimeBar timeBar2 = this.timeBar;
            if (timeBar2 != null) {
                timeBar2.setDuration(usToMs);
                int length2 = this.extraAdGroupTimesMs.length;
                int i4 = i + length2;
                long[] jArr2 = this.adGroupTimesMs;
                if (i4 > jArr2.length) {
                    this.adGroupTimesMs = Arrays.copyOf(jArr2, i4);
                    this.playedAdGroups = Arrays.copyOf(this.playedAdGroups, i4);
                }
                System.arraycopy(this.extraAdGroupTimesMs, 0, this.adGroupTimesMs, i, length2);
                System.arraycopy(this.extraPlayedAdGroups, 0, this.playedAdGroups, i, length2);
                this.timeBar.setAdGroupTimesMs(this.adGroupTimesMs, this.playedAdGroups, i4);
            }
            updateProgress();
        }
    }

    /* access modifiers changed from: private */
    public void updateProgress() {
        long j;
        long j2;
        int i;
        if (isVisible() && this.isAttachedToWindow) {
            Player player2 = this.player;
            if (player2 == null || !player2.isCommandAvailable(16)) {
                j2 = 0;
                j = 0;
            } else {
                j2 = this.currentWindowOffset + player2.getContentPosition();
                j = this.currentWindowOffset + player2.getContentBufferedPosition();
            }
            TextView textView = this.positionView;
            if (textView != null && !this.scrubbing) {
                textView.setText(Util.getStringForTime(this.formatBuilder, this.formatter, j2));
            }
            TimeBar timeBar2 = this.timeBar;
            if (timeBar2 != null) {
                timeBar2.setPosition(j2);
                this.timeBar.setBufferedPosition(j);
            }
            ProgressUpdateListener progressUpdateListener2 = this.progressUpdateListener;
            if (progressUpdateListener2 != null) {
                progressUpdateListener2.onProgressUpdate(j2, j);
            }
            removeCallbacks(this.updateProgressAction);
            if (player2 == null) {
                i = 1;
            } else {
                i = player2.getPlaybackState();
            }
            long j3 = 1000;
            if (player2 != null && player2.isPlaying()) {
                TimeBar timeBar3 = this.timeBar;
                long min = Math.min(timeBar3 != null ? timeBar3.getPreferredUpdateDelay() : 1000, 1000 - (j2 % 1000));
                float f = player2.getPlaybackParameters().speed;
                if (f > 0.0f) {
                    j3 = (long) (((float) min) / f);
                }
                postDelayed(this.updateProgressAction, Util.constrainValue(j3, (long) this.timeBarMinUpdateIntervalMs, 1000));
            } else if (i != 4 && i != 1) {
                postDelayed(this.updateProgressAction, 1000);
            }
        }
    }

    /* access modifiers changed from: private */
    public void updatePlaybackSpeedList() {
        Player player2 = this.player;
        if (player2 != null) {
            this.playbackSpeedAdapter.updateSelectedIndex(player2.getPlaybackParameters().speed);
            this.settingsAdapter.setSubTextAtPosition(0, this.playbackSpeedAdapter.getSelectedText());
            updateSettingsButton();
        }
    }

    private void updateSettingsButton() {
        updateButton(this.settingsAdapter.hasSettingsToShow(), this.settingsButton);
    }

    private void updateSettingsWindowSize() {
        this.settingsView.measure(0, 0);
        this.settingsWindow.setWidth(Math.min(this.settingsView.getMeasuredWidth(), getWidth() - (this.settingsWindowMargin * 2)));
        this.settingsWindow.setHeight(Math.min(getHeight() - (this.settingsWindowMargin * 2), this.settingsView.getMeasuredHeight()));
    }

    /* access modifiers changed from: private */
    public void displaySettingsWindow(RecyclerView.Adapter<?> adapter, View view) {
        this.settingsView.setAdapter(adapter);
        updateSettingsWindowSize();
        this.needToHideBars = false;
        this.settingsWindow.dismiss();
        this.needToHideBars = true;
        this.settingsWindow.showAsDropDown(view, (getWidth() - this.settingsWindow.getWidth()) - this.settingsWindowMargin, (-this.settingsWindow.getHeight()) - this.settingsWindowMargin);
    }

    /* access modifiers changed from: private */
    public void setPlaybackSpeed(float f) {
        Player player2 = this.player;
        if (player2 != null && player2.isCommandAvailable(13)) {
            Player player3 = this.player;
            player3.setPlaybackParameters(player3.getPlaybackParameters().withSpeed(f));
        }
    }

    /* access modifiers changed from: package-private */
    public void requestPlayPauseFocus() {
        ImageView imageView = this.playPauseButton;
        if (imageView != null) {
            imageView.requestFocus();
        }
    }

    private void updateButton(boolean z, View view) {
        if (view != null) {
            view.setEnabled(z);
            view.setAlpha(z ? this.buttonAlphaEnabled : this.buttonAlphaDisabled);
        }
    }

    /* access modifiers changed from: private */
    public void seekToTimeBarPosition(Player player2, long j) {
        if (this.multiWindowTimeBar) {
            if (player2.isCommandAvailable(17) && player2.isCommandAvailable(10)) {
                Timeline currentTimeline = player2.getCurrentTimeline();
                int windowCount = currentTimeline.getWindowCount();
                int i = 0;
                while (true) {
                    long durationMs = currentTimeline.getWindow(i, this.window).getDurationMs();
                    if (j < durationMs) {
                        break;
                    } else if (i == windowCount - 1) {
                        j = durationMs;
                        break;
                    } else {
                        j -= durationMs;
                        i++;
                    }
                }
                player2.seekTo(i, j);
            }
        } else if (player2.isCommandAvailable(5)) {
            player2.seekTo(j);
        }
        updateProgress();
    }

    /* access modifiers changed from: private */
    public void onFullscreenButtonClicked(View view) {
        updateIsFullscreen(!this.isFullscreen);
    }

    public void updateIsFullscreen(boolean z) {
        if (this.isFullscreen != z) {
            this.isFullscreen = z;
            updateFullscreenButtonForState(this.fullscreenButton, z);
            updateFullscreenButtonForState(this.minimalFullscreenButton, z);
            OnFullScreenModeChangedListener onFullScreenModeChangedListener2 = this.onFullScreenModeChangedListener;
            if (onFullScreenModeChangedListener2 != null) {
                onFullScreenModeChangedListener2.onFullScreenModeChanged(z);
            }
        }
    }

    private void updateFullscreenButtonForState(ImageView imageView, boolean z) {
        if (imageView != null) {
            if (z) {
                imageView.setImageDrawable(this.fullscreenExitDrawable);
                imageView.setContentDescription(this.fullscreenExitContentDescription);
                return;
            }
            imageView.setImageDrawable(this.fullscreenEnterDrawable);
            imageView.setContentDescription(this.fullscreenEnterContentDescription);
        }
    }

    /* access modifiers changed from: private */
    public void onSettingViewClicked(int i) {
        if (i == 0) {
            displaySettingsWindow(this.playbackSpeedAdapter, (View) Assertions.checkNotNull(this.settingsButton));
        } else if (i == 1) {
            displaySettingsWindow(this.audioTrackSelectionAdapter, (View) Assertions.checkNotNull(this.settingsButton));
        } else {
            this.settingsWindow.dismiss();
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.controlViewLayoutManager.onAttachedToWindow();
        this.isAttachedToWindow = true;
        if (isFullyVisible()) {
            this.controlViewLayoutManager.resetHideCallbacks();
        }
        updateAll();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.controlViewLayoutManager.onDetachedFromWindow();
        this.isAttachedToWindow = false;
        removeCallbacks(this.updateProgressAction);
        this.controlViewLayoutManager.removeHideCallbacks();
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return dispatchMediaKeyEvent(keyEvent) || super.dispatchKeyEvent(keyEvent);
    }

    public boolean dispatchMediaKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        Player player2 = this.player;
        if (player2 == null || !isHandledMediaKey(keyCode)) {
            return false;
        }
        if (keyEvent.getAction() != 0) {
            return true;
        }
        if (keyCode == 90) {
            if (player2.getPlaybackState() == 4 || !player2.isCommandAvailable(12)) {
                return true;
            }
            player2.seekForward();
            return true;
        } else if (keyCode == 89 && player2.isCommandAvailable(11)) {
            player2.seekBack();
            return true;
        } else if (keyEvent.getRepeatCount() != 0) {
            return true;
        } else {
            if (keyCode == 79 || keyCode == 85) {
                Util.handlePlayPauseButtonAction(player2, this.showPlayButtonIfSuppressed);
                return true;
            } else if (keyCode != 87) {
                if (keyCode != 88) {
                    if (keyCode == 126) {
                        Util.handlePlayButtonAction(player2);
                        return true;
                    } else if (keyCode != 127) {
                        return true;
                    } else {
                        Util.handlePauseButtonAction(player2);
                        return true;
                    }
                } else if (!player2.isCommandAvailable(7)) {
                    return true;
                } else {
                    player2.seekToPrevious();
                    return true;
                }
            } else if (!player2.isCommandAvailable(9)) {
                return true;
            } else {
                player2.seekToNext();
                return true;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.controlViewLayoutManager.onLayout(z, i, i2, i3, i4);
    }

    /* access modifiers changed from: private */
    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int i9 = i4 - i2;
        int i10 = i8 - i6;
        if (!(i3 - i == i7 - i5 && i9 == i10) && this.settingsWindow.isShowing()) {
            updateSettingsWindowSize();
            this.settingsWindow.update(view, (getWidth() - this.settingsWindow.getWidth()) - this.settingsWindowMargin, (-this.settingsWindow.getHeight()) - this.settingsWindowMargin, -1, -1);
        }
    }

    private boolean shouldEnablePlayPauseButton() {
        Player player2 = this.player;
        if (player2 == null || !player2.isCommandAvailable(1) || (this.player.isCommandAvailable(17) && this.player.getCurrentTimeline().isEmpty())) {
            return false;
        }
        return true;
    }

    private static boolean canShowMultiWindowTimeBar(Player player2, Timeline.Window window2) {
        Timeline currentTimeline;
        int windowCount;
        if (!player2.isCommandAvailable(17) || (windowCount = currentTimeline.getWindowCount()) <= 1 || windowCount > 100) {
            return false;
        }
        for (int i = 0; i < windowCount; i++) {
            if ((currentTimeline = player2.getCurrentTimeline()).getWindow(i, window2).durationUs == C.TIME_UNSET) {
                return false;
            }
        }
        return true;
    }

    private static void initializeFullscreenButton(View view, View.OnClickListener onClickListener) {
        if (view != null) {
            view.setVisibility(8);
            view.setOnClickListener(onClickListener);
        }
    }

    private static void updateFullscreenButtonVisibility(View view, boolean z) {
        if (view != null) {
            if (z) {
                view.setVisibility(0);
            } else {
                view.setVisibility(8);
            }
        }
    }

    private static int getRepeatToggleModes(TypedArray typedArray, int i) {
        return typedArray.getInt(R.styleable.PlayerControlView_repeat_toggle_modes, i);
    }

    private final class ComponentListener implements Player.Listener, TimeBar.OnScrubListener, View.OnClickListener, PopupWindow.OnDismissListener {
        private ComponentListener() {
        }

        public void onEvents(Player player, Player.Events events) {
            if (events.containsAny(4, 5, 13)) {
                PlayerControlView.this.updatePlayPauseButton();
            }
            if (events.containsAny(4, 5, 7, 13)) {
                PlayerControlView.this.updateProgress();
            }
            if (events.containsAny(8, 13)) {
                PlayerControlView.this.updateRepeatModeButton();
            }
            if (events.containsAny(9, 13)) {
                PlayerControlView.this.updateShuffleButton();
            }
            if (events.containsAny(8, 9, 11, 0, 16, 17, 13)) {
                PlayerControlView.this.updateNavigation();
            }
            if (events.containsAny(11, 0, 13)) {
                PlayerControlView.this.updateTimeline();
            }
            if (events.containsAny(12, 13)) {
                PlayerControlView.this.updatePlaybackSpeedList();
            }
            if (events.containsAny(2, 13)) {
                PlayerControlView.this.updateTrackLists();
            }
        }

        public void onScrubStart(TimeBar timeBar, long j) {
            boolean unused = PlayerControlView.this.scrubbing = true;
            if (PlayerControlView.this.positionView != null) {
                PlayerControlView.this.positionView.setText(Util.getStringForTime(PlayerControlView.this.formatBuilder, PlayerControlView.this.formatter, j));
            }
            PlayerControlView.this.controlViewLayoutManager.removeHideCallbacks();
        }

        public void onScrubMove(TimeBar timeBar, long j) {
            if (PlayerControlView.this.positionView != null) {
                PlayerControlView.this.positionView.setText(Util.getStringForTime(PlayerControlView.this.formatBuilder, PlayerControlView.this.formatter, j));
            }
        }

        public void onScrubStop(TimeBar timeBar, long j, boolean z) {
            boolean unused = PlayerControlView.this.scrubbing = false;
            if (!z && PlayerControlView.this.player != null) {
                PlayerControlView playerControlView = PlayerControlView.this;
                playerControlView.seekToTimeBarPosition(playerControlView.player, j);
            }
            PlayerControlView.this.controlViewLayoutManager.resetHideCallbacks();
        }

        public void onDismiss() {
            if (PlayerControlView.this.needToHideBars) {
                PlayerControlView.this.controlViewLayoutManager.resetHideCallbacks();
            }
        }

        public void onClick(View view) {
            Player access$1600 = PlayerControlView.this.player;
            if (access$1600 != null) {
                PlayerControlView.this.controlViewLayoutManager.resetHideCallbacks();
                if (PlayerControlView.this.nextButton == view) {
                    if (access$1600.isCommandAvailable(9)) {
                        access$1600.seekToNext();
                    }
                } else if (PlayerControlView.this.previousButton == view) {
                    if (access$1600.isCommandAvailable(7)) {
                        access$1600.seekToPrevious();
                    }
                } else if (PlayerControlView.this.fastForwardButton == view) {
                    if (access$1600.getPlaybackState() != 4 && access$1600.isCommandAvailable(12)) {
                        access$1600.seekForward();
                    }
                } else if (PlayerControlView.this.rewindButton == view) {
                    if (access$1600.isCommandAvailable(11)) {
                        access$1600.seekBack();
                    }
                } else if (PlayerControlView.this.playPauseButton == view) {
                    Util.handlePlayPauseButtonAction(access$1600, PlayerControlView.this.showPlayButtonIfSuppressed);
                } else if (PlayerControlView.this.repeatToggleButton == view) {
                    if (access$1600.isCommandAvailable(15)) {
                        access$1600.setRepeatMode(RepeatModeUtil.getNextRepeatMode(access$1600.getRepeatMode(), PlayerControlView.this.repeatToggleModes));
                    }
                } else if (PlayerControlView.this.shuffleButton == view) {
                    if (access$1600.isCommandAvailable(14)) {
                        access$1600.setShuffleModeEnabled(!access$1600.getShuffleModeEnabled());
                    }
                } else if (PlayerControlView.this.settingsButton == view) {
                    PlayerControlView.this.controlViewLayoutManager.removeHideCallbacks();
                    PlayerControlView playerControlView = PlayerControlView.this;
                    playerControlView.displaySettingsWindow(playerControlView.settingsAdapter, PlayerControlView.this.settingsButton);
                } else if (PlayerControlView.this.playbackSpeedButton == view) {
                    PlayerControlView.this.controlViewLayoutManager.removeHideCallbacks();
                    PlayerControlView playerControlView2 = PlayerControlView.this;
                    playerControlView2.displaySettingsWindow(playerControlView2.playbackSpeedAdapter, PlayerControlView.this.playbackSpeedButton);
                } else if (PlayerControlView.this.audioTrackButton == view) {
                    PlayerControlView.this.controlViewLayoutManager.removeHideCallbacks();
                    PlayerControlView playerControlView3 = PlayerControlView.this;
                    playerControlView3.displaySettingsWindow(playerControlView3.audioTrackSelectionAdapter, PlayerControlView.this.audioTrackButton);
                } else if (PlayerControlView.this.subtitleButton == view) {
                    PlayerControlView.this.controlViewLayoutManager.removeHideCallbacks();
                    PlayerControlView playerControlView4 = PlayerControlView.this;
                    playerControlView4.displaySettingsWindow(playerControlView4.textTrackSelectionAdapter, PlayerControlView.this.subtitleButton);
                }
            }
        }
    }

    private class SettingsAdapter extends RecyclerView.Adapter<SettingViewHolder> {
        private final Drawable[] iconIds;
        private final String[] mainTexts;
        private final String[] subTexts;

        public long getItemId(int i) {
            return (long) i;
        }

        public SettingsAdapter(String[] strArr, Drawable[] drawableArr) {
            this.mainTexts = strArr;
            this.subTexts = new String[strArr.length];
            this.iconIds = drawableArr;
        }

        public SettingViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new SettingViewHolder(LayoutInflater.from(PlayerControlView.this.getContext()).inflate(R.layout.exo_styled_settings_list_item, viewGroup, false));
        }

        public void onBindViewHolder(SettingViewHolder settingViewHolder, int i) {
            if (shouldShowSetting(i)) {
                settingViewHolder.itemView.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            } else {
                settingViewHolder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
            }
            settingViewHolder.mainTextView.setText(this.mainTexts[i]);
            if (this.subTexts[i] == null) {
                settingViewHolder.subTextView.setVisibility(8);
            } else {
                settingViewHolder.subTextView.setText(this.subTexts[i]);
            }
            if (this.iconIds[i] == null) {
                settingViewHolder.iconView.setVisibility(8);
            } else {
                settingViewHolder.iconView.setImageDrawable(this.iconIds[i]);
            }
        }

        public int getItemCount() {
            return this.mainTexts.length;
        }

        public void setSubTextAtPosition(int i, String str) {
            this.subTexts[i] = str;
        }

        public boolean hasSettingsToShow() {
            if (shouldShowSetting(1) || shouldShowSetting(0)) {
                return true;
            }
            return false;
        }

        private boolean shouldShowSetting(int i) {
            if (PlayerControlView.this.player == null) {
                return false;
            }
            if (i == 0) {
                return PlayerControlView.this.player.isCommandAvailable(13);
            }
            if (i != 1) {
                return true;
            }
            if (!PlayerControlView.this.player.isCommandAvailable(30) || !PlayerControlView.this.player.isCommandAvailable(29)) {
                return false;
            }
            return true;
        }
    }

    private final class SettingViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public final ImageView iconView;
        /* access modifiers changed from: private */
        public final TextView mainTextView;
        /* access modifiers changed from: private */
        public final TextView subTextView;

        public SettingViewHolder(View view) {
            super(view);
            if (Util.SDK_INT < 26) {
                view.setFocusable(true);
            }
            this.mainTextView = (TextView) view.findViewById(R.id.exo_main_text);
            this.subTextView = (TextView) view.findViewById(R.id.exo_sub_text);
            this.iconView = (ImageView) view.findViewById(R.id.exo_icon);
            view.setOnClickListener(new PlayerControlView$SettingViewHolder$$ExternalSyntheticLambda0(this));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$new$0$androidx-media3-ui-PlayerControlView$SettingViewHolder  reason: not valid java name */
        public /* synthetic */ void m1142lambda$new$0$androidxmedia3uiPlayerControlView$SettingViewHolder(View view) {
            PlayerControlView.this.onSettingViewClicked(getBindingAdapterPosition());
        }
    }

    private final class PlaybackSpeedAdapter extends RecyclerView.Adapter<SubSettingViewHolder> {
        private final String[] playbackSpeedTexts;
        private final float[] playbackSpeeds;
        private int selectedIndex;

        public PlaybackSpeedAdapter(String[] strArr, float[] fArr) {
            this.playbackSpeedTexts = strArr;
            this.playbackSpeeds = fArr;
        }

        public void updateSelectedIndex(float f) {
            int i = 0;
            float f2 = Float.MAX_VALUE;
            int i2 = 0;
            while (true) {
                float[] fArr = this.playbackSpeeds;
                if (i < fArr.length) {
                    float abs = Math.abs(f - fArr[i]);
                    if (abs < f2) {
                        i2 = i;
                        f2 = abs;
                    }
                    i++;
                } else {
                    this.selectedIndex = i2;
                    return;
                }
            }
        }

        public String getSelectedText() {
            return this.playbackSpeedTexts[this.selectedIndex];
        }

        public SubSettingViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new SubSettingViewHolder(LayoutInflater.from(PlayerControlView.this.getContext()).inflate(R.layout.exo_styled_sub_settings_list_item, viewGroup, false));
        }

        public void onBindViewHolder(SubSettingViewHolder subSettingViewHolder, int i) {
            if (i < this.playbackSpeedTexts.length) {
                subSettingViewHolder.textView.setText(this.playbackSpeedTexts[i]);
            }
            if (i == this.selectedIndex) {
                subSettingViewHolder.itemView.setSelected(true);
                subSettingViewHolder.checkView.setVisibility(0);
            } else {
                subSettingViewHolder.itemView.setSelected(false);
                subSettingViewHolder.checkView.setVisibility(4);
            }
            subSettingViewHolder.itemView.setOnClickListener(new PlayerControlView$PlaybackSpeedAdapter$$ExternalSyntheticLambda0(this, i));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onBindViewHolder$0$androidx-media3-ui-PlayerControlView$PlaybackSpeedAdapter  reason: not valid java name */
        public /* synthetic */ void m1141lambda$onBindViewHolder$0$androidxmedia3uiPlayerControlView$PlaybackSpeedAdapter(int i, View view) {
            if (i != this.selectedIndex) {
                PlayerControlView.this.setPlaybackSpeed(this.playbackSpeeds[i]);
            }
            PlayerControlView.this.settingsWindow.dismiss();
        }

        public int getItemCount() {
            return this.playbackSpeedTexts.length;
        }
    }

    private static final class TrackInformation {
        public final Tracks.Group trackGroup;
        public final int trackIndex;
        public final String trackName;

        public TrackInformation(Tracks tracks, int i, int i2, String str) {
            this.trackGroup = (Tracks.Group) tracks.getGroups().get(i);
            this.trackIndex = i2;
            this.trackName = str;
        }

        public boolean isSelected() {
            return this.trackGroup.isTrackSelected(this.trackIndex);
        }
    }

    private final class TextTrackSelectionAdapter extends TrackSelectionAdapter {
        public void onTrackSelection(String str) {
        }

        private TextTrackSelectionAdapter() {
            super();
        }

        public void init(List<TrackInformation> list) {
            boolean z = false;
            int i = 0;
            while (true) {
                if (i >= list.size()) {
                    break;
                } else if (list.get(i).isSelected()) {
                    z = true;
                    break;
                } else {
                    i++;
                }
            }
            if (PlayerControlView.this.subtitleButton != null) {
                ImageView access$3500 = PlayerControlView.this.subtitleButton;
                PlayerControlView playerControlView = PlayerControlView.this;
                access$3500.setImageDrawable(z ? playerControlView.subtitleOnButtonDrawable : playerControlView.subtitleOffButtonDrawable);
                PlayerControlView.this.subtitleButton.setContentDescription(z ? PlayerControlView.this.subtitleOnContentDescription : PlayerControlView.this.subtitleOffContentDescription);
            }
            this.tracks = list;
        }

        public void onBindViewHolderAtZeroPosition(SubSettingViewHolder subSettingViewHolder) {
            boolean z;
            subSettingViewHolder.textView.setText(R.string.exo_track_selection_none);
            int i = 0;
            int i2 = 0;
            while (true) {
                if (i2 >= this.tracks.size()) {
                    z = true;
                    break;
                } else if (((TrackInformation) this.tracks.get(i2)).isSelected()) {
                    z = false;
                    break;
                } else {
                    i2++;
                }
            }
            View view = subSettingViewHolder.checkView;
            if (!z) {
                i = 4;
            }
            view.setVisibility(i);
            subSettingViewHolder.itemView.setOnClickListener(new PlayerControlView$TextTrackSelectionAdapter$$ExternalSyntheticLambda0(this));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onBindViewHolderAtZeroPosition$0$androidx-media3-ui-PlayerControlView$TextTrackSelectionAdapter  reason: not valid java name */
        public /* synthetic */ void m1143lambda$onBindViewHolderAtZeroPosition$0$androidxmedia3uiPlayerControlView$TextTrackSelectionAdapter(View view) {
            if (PlayerControlView.this.player != null && PlayerControlView.this.player.isCommandAvailable(29)) {
                PlayerControlView.this.player.setTrackSelectionParameters(PlayerControlView.this.player.getTrackSelectionParameters().buildUpon().clearOverridesOfType(3).setIgnoredTextSelectionFlags(-3).setPreferredTextLanguage((String) null).setPreferredTextRoleFlags(0).build());
                PlayerControlView.this.settingsWindow.dismiss();
            }
        }

        public void onBindViewHolder(SubSettingViewHolder subSettingViewHolder, int i) {
            super.onBindViewHolder(subSettingViewHolder, i);
            if (i > 0) {
                subSettingViewHolder.checkView.setVisibility(((TrackInformation) this.tracks.get(i + -1)).isSelected() ? 0 : 4);
            }
        }
    }

    private final class AudioTrackSelectionAdapter extends TrackSelectionAdapter {
        private AudioTrackSelectionAdapter() {
            super();
        }

        public void onBindViewHolderAtZeroPosition(SubSettingViewHolder subSettingViewHolder) {
            subSettingViewHolder.textView.setText(R.string.exo_track_selection_auto);
            subSettingViewHolder.checkView.setVisibility(hasSelectionOverride(((Player) Assertions.checkNotNull(PlayerControlView.this.player)).getTrackSelectionParameters()) ? 4 : 0);
            subSettingViewHolder.itemView.setOnClickListener(new PlayerControlView$AudioTrackSelectionAdapter$$ExternalSyntheticLambda0(this));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onBindViewHolderAtZeroPosition$0$androidx-media3-ui-PlayerControlView$AudioTrackSelectionAdapter  reason: not valid java name */
        public /* synthetic */ void m1140lambda$onBindViewHolderAtZeroPosition$0$androidxmedia3uiPlayerControlView$AudioTrackSelectionAdapter(View view) {
            if (PlayerControlView.this.player != null && PlayerControlView.this.player.isCommandAvailable(29)) {
                ((Player) Util.castNonNull(PlayerControlView.this.player)).setTrackSelectionParameters(PlayerControlView.this.player.getTrackSelectionParameters().buildUpon().clearOverridesOfType(1).setTrackTypeDisabled(1, false).build());
                PlayerControlView.this.settingsAdapter.setSubTextAtPosition(1, PlayerControlView.this.getResources().getString(R.string.exo_track_selection_auto));
                PlayerControlView.this.settingsWindow.dismiss();
            }
        }

        private boolean hasSelectionOverride(TrackSelectionParameters trackSelectionParameters) {
            for (int i = 0; i < this.tracks.size(); i++) {
                if (trackSelectionParameters.overrides.containsKey(((TrackInformation) this.tracks.get(i)).trackGroup.getMediaTrackGroup())) {
                    return true;
                }
            }
            return false;
        }

        public void onTrackSelection(String str) {
            PlayerControlView.this.settingsAdapter.setSubTextAtPosition(1, str);
        }

        public void init(List<TrackInformation> list) {
            this.tracks = list;
            TrackSelectionParameters trackSelectionParameters = ((Player) Assertions.checkNotNull(PlayerControlView.this.player)).getTrackSelectionParameters();
            if (list.isEmpty()) {
                PlayerControlView.this.settingsAdapter.setSubTextAtPosition(1, PlayerControlView.this.getResources().getString(R.string.exo_track_selection_none));
            } else if (!hasSelectionOverride(trackSelectionParameters)) {
                PlayerControlView.this.settingsAdapter.setSubTextAtPosition(1, PlayerControlView.this.getResources().getString(R.string.exo_track_selection_auto));
            } else {
                for (int i = 0; i < list.size(); i++) {
                    TrackInformation trackInformation = list.get(i);
                    if (trackInformation.isSelected()) {
                        PlayerControlView.this.settingsAdapter.setSubTextAtPosition(1, trackInformation.trackName);
                        return;
                    }
                }
            }
        }
    }

    private abstract class TrackSelectionAdapter extends RecyclerView.Adapter<SubSettingViewHolder> {
        protected List<TrackInformation> tracks = new ArrayList();

        public abstract void init(List<TrackInformation> list);

        /* access modifiers changed from: protected */
        public abstract void onBindViewHolderAtZeroPosition(SubSettingViewHolder subSettingViewHolder);

        /* access modifiers changed from: protected */
        public abstract void onTrackSelection(String str);

        protected TrackSelectionAdapter() {
        }

        public SubSettingViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new SubSettingViewHolder(LayoutInflater.from(PlayerControlView.this.getContext()).inflate(R.layout.exo_styled_sub_settings_list_item, viewGroup, false));
        }

        public void onBindViewHolder(SubSettingViewHolder subSettingViewHolder, int i) {
            Player access$1600 = PlayerControlView.this.player;
            if (access$1600 != null) {
                if (i == 0) {
                    onBindViewHolderAtZeroPosition(subSettingViewHolder);
                    return;
                }
                boolean z = true;
                TrackInformation trackInformation = this.tracks.get(i - 1);
                TrackGroup mediaTrackGroup = trackInformation.trackGroup.getMediaTrackGroup();
                int i2 = 0;
                if (access$1600.getTrackSelectionParameters().overrides.get(mediaTrackGroup) == null || !trackInformation.isSelected()) {
                    z = false;
                }
                subSettingViewHolder.textView.setText(trackInformation.trackName);
                View view = subSettingViewHolder.checkView;
                if (!z) {
                    i2 = 4;
                }
                view.setVisibility(i2);
                subSettingViewHolder.itemView.setOnClickListener(new PlayerControlView$TrackSelectionAdapter$$ExternalSyntheticLambda0(this, access$1600, mediaTrackGroup, trackInformation));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onBindViewHolder$0$androidx-media3-ui-PlayerControlView$TrackSelectionAdapter  reason: not valid java name */
        public /* synthetic */ void m1144lambda$onBindViewHolder$0$androidxmedia3uiPlayerControlView$TrackSelectionAdapter(Player player, TrackGroup trackGroup, TrackInformation trackInformation, View view) {
            if (player.isCommandAvailable(29)) {
                player.setTrackSelectionParameters(player.getTrackSelectionParameters().buildUpon().setOverrideForType(new TrackSelectionOverride(trackGroup, (List<Integer>) ImmutableList.of(Integer.valueOf(trackInformation.trackIndex)))).setTrackTypeDisabled(trackInformation.trackGroup.getType(), false).build());
                onTrackSelection(trackInformation.trackName);
                PlayerControlView.this.settingsWindow.dismiss();
            }
        }

        public int getItemCount() {
            if (this.tracks.isEmpty()) {
                return 0;
            }
            return this.tracks.size() + 1;
        }

        /* access modifiers changed from: protected */
        public void clear() {
            this.tracks = Collections.emptyList();
        }
    }

    private static class SubSettingViewHolder extends RecyclerView.ViewHolder {
        public final View checkView;
        public final TextView textView;

        public SubSettingViewHolder(View view) {
            super(view);
            if (Util.SDK_INT < 26) {
                view.setFocusable(true);
            }
            this.textView = (TextView) view.findViewById(R.id.exo_text);
            this.checkView = view.findViewById(R.id.exo_check);
        }
    }
}

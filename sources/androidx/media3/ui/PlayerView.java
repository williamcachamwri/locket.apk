package androidx.media3.ui;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.AttachedSurfaceControl;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceControl;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.window.SurfaceSyncGroup;
import androidx.core.content.ContextCompat;
import androidx.media3.common.AdOverlayInfo;
import androidx.media3.common.AdViewProvider;
import androidx.media3.common.ErrorMessageProvider;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.Cue;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.ui.AspectRatioFrameLayout;
import androidx.media3.ui.PlayerControlView;
import com.google.common.collect.ImmutableList;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;

public class PlayerView extends FrameLayout implements AdViewProvider {
    public static final int ARTWORK_DISPLAY_MODE_FILL = 2;
    public static final int ARTWORK_DISPLAY_MODE_FIT = 1;
    public static final int ARTWORK_DISPLAY_MODE_OFF = 0;
    public static final int IMAGE_DISPLAY_MODE_FILL = 1;
    public static final int IMAGE_DISPLAY_MODE_FIT = 0;
    public static final int SHOW_BUFFERING_ALWAYS = 2;
    public static final int SHOW_BUFFERING_NEVER = 0;
    public static final int SHOW_BUFFERING_WHEN_PLAYING = 1;
    private static final int SURFACE_TYPE_NONE = 0;
    private static final int SURFACE_TYPE_SPHERICAL_GL_SURFACE_VIEW = 3;
    private static final int SURFACE_TYPE_SURFACE_VIEW = 1;
    private static final int SURFACE_TYPE_TEXTURE_VIEW = 2;
    private static final int SURFACE_TYPE_VIDEO_DECODER_GL_SURFACE_VIEW = 4;
    private final FrameLayout adOverlayFrameLayout;
    private int artworkDisplayMode;
    private final ImageView artworkView;
    private final View bufferingView;
    private final ComponentListener componentListener;
    private final AspectRatioFrameLayout contentFrame;
    private final PlayerControlView controller;
    private boolean controllerAutoShow;
    /* access modifiers changed from: private */
    public boolean controllerHideDuringAds;
    private boolean controllerHideOnTouch;
    private int controllerShowTimeoutMs;
    /* access modifiers changed from: private */
    public ControllerVisibilityListener controllerVisibilityListener;
    private CharSequence customErrorMessage;
    private Drawable defaultArtwork;
    /* access modifiers changed from: private */
    public boolean enableComposeSurfaceSyncWorkaround;
    private ErrorMessageProvider<? super PlaybackException> errorMessageProvider;
    private final TextView errorMessageView;
    private final Class<?> exoPlayerClazz;
    /* access modifiers changed from: private */
    public FullscreenButtonClickListener fullscreenButtonClickListener;
    private int imageDisplayMode;
    private final Object imageOutput;
    private final ImageView imageView;
    private boolean keepContentOnPlayerReset;
    private PlayerControlView.VisibilityListener legacyControllerVisibilityListener;
    /* access modifiers changed from: private */
    public final Handler mainLooperHandler;
    private final FrameLayout overlayFrameLayout;
    /* access modifiers changed from: private */
    public Player player;
    private final Method setImageOutputMethod;
    private int showBuffering;
    /* access modifiers changed from: private */
    public final View shutterView;
    /* access modifiers changed from: private */
    public final SubtitleView subtitleView;
    /* access modifiers changed from: private */
    public final SurfaceSyncGroupCompatV34 surfaceSyncGroupV34;
    /* access modifiers changed from: private */
    public final View surfaceView;
    private final boolean surfaceViewIgnoresVideoAspectRatio;
    private boolean useController;

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ArtworkDisplayMode {
    }

    public interface ControllerVisibilityListener {
        void onVisibilityChanged(int i);
    }

    public interface FullscreenButtonClickListener {
        void onFullscreenButtonClick(boolean z);
    }

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ImageDisplayMode {
    }

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ShowBuffering {
    }

    private boolean isDpadKey(int i) {
        return i == 19 || i == 270 || i == 22 || i == 271 || i == 20 || i == 269 || i == 21 || i == 268 || i == 23;
    }

    public PlayerView(Context context) {
        this(context, (AttributeSet) null);
    }

    public PlayerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX INFO: finally extract failed */
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PlayerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        int i2;
        boolean z5;
        int i3;
        boolean z6;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        boolean z7;
        int i9;
        boolean z8;
        AnonymousClass1 r3;
        Method method;
        Object obj;
        Class<?> cls;
        boolean z9;
        Context context2 = context;
        AttributeSet attributeSet2 = attributeSet;
        ComponentListener componentListener2 = new ComponentListener();
        this.componentListener = componentListener2;
        this.mainLooperHandler = new Handler(Looper.getMainLooper());
        if (isInEditMode()) {
            this.contentFrame = null;
            this.shutterView = null;
            this.surfaceView = null;
            this.surfaceViewIgnoresVideoAspectRatio = false;
            this.surfaceSyncGroupV34 = null;
            this.imageView = null;
            this.artworkView = null;
            this.subtitleView = null;
            this.bufferingView = null;
            this.errorMessageView = null;
            this.controller = null;
            this.adOverlayFrameLayout = null;
            this.overlayFrameLayout = null;
            this.exoPlayerClazz = null;
            this.setImageOutputMethod = null;
            this.imageOutput = null;
            ImageView imageView2 = new ImageView(context2);
            if (Util.SDK_INT >= 23) {
                configureEditModeLogoV23(context2, getResources(), imageView2);
            } else {
                configureEditModeLogo(context2, getResources(), imageView2);
            }
            addView(imageView2);
            return;
        }
        int i10 = R.layout.exo_player_view;
        if (attributeSet2 != null) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet2, R.styleable.PlayerView, i, 0);
            try {
                boolean hasValue = obtainStyledAttributes.hasValue(R.styleable.PlayerView_shutter_background_color);
                int color = obtainStyledAttributes.getColor(R.styleable.PlayerView_shutter_background_color, 0);
                int resourceId = obtainStyledAttributes.getResourceId(R.styleable.PlayerView_player_layout_id, i10);
                boolean z10 = obtainStyledAttributes.getBoolean(R.styleable.PlayerView_use_artwork, true);
                int i11 = obtainStyledAttributes.getInt(R.styleable.PlayerView_artwork_display_mode, 1);
                int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.PlayerView_default_artwork, 0);
                int i12 = obtainStyledAttributes.getInt(R.styleable.PlayerView_image_display_mode, 0);
                boolean z11 = obtainStyledAttributes.getBoolean(R.styleable.PlayerView_use_controller, true);
                int i13 = obtainStyledAttributes.getInt(R.styleable.PlayerView_surface_type, 1);
                int i14 = obtainStyledAttributes.getInt(R.styleable.PlayerView_resize_mode, 0);
                int i15 = resourceId;
                z4 = obtainStyledAttributes.getInt(R.styleable.PlayerView_show_timeout, 5000);
                boolean z12 = obtainStyledAttributes.getBoolean(R.styleable.PlayerView_hide_on_touch, true);
                z2 = obtainStyledAttributes.getBoolean(R.styleable.PlayerView_auto_show, true);
                int integer = obtainStyledAttributes.getInteger(R.styleable.PlayerView_show_buffering, 0);
                this.keepContentOnPlayerReset = obtainStyledAttributes.getBoolean(R.styleable.PlayerView_keep_content_on_player_reset, this.keepContentOnPlayerReset);
                boolean z13 = obtainStyledAttributes.getBoolean(R.styleable.PlayerView_hide_during_ads, true);
                obtainStyledAttributes.recycle();
                i6 = resourceId2;
                z7 = z12;
                z3 = z13;
                z5 = z10;
                i9 = i15;
                z = z11;
                z6 = hasValue;
                i5 = i14;
                i8 = i12;
                i2 = i11;
                i3 = color;
                i4 = i13;
                i7 = integer;
            } catch (Throwable th) {
                obtainStyledAttributes.recycle();
                throw th;
            }
        } else {
            z4 = true;
            i9 = i10;
            z = true;
            z7 = true;
            i8 = 0;
            i7 = 0;
            i6 = 0;
            i5 = 0;
            i4 = 1;
            z6 = false;
            i3 = 0;
            z5 = true;
            i2 = 1;
            z3 = true;
            z2 = true;
        }
        LayoutInflater.from(context).inflate(i9, this);
        setDescendantFocusability(262144);
        AspectRatioFrameLayout aspectRatioFrameLayout = (AspectRatioFrameLayout) findViewById(R.id.exo_content_frame);
        this.contentFrame = aspectRatioFrameLayout;
        if (aspectRatioFrameLayout != null) {
            setResizeModeRaw(aspectRatioFrameLayout, i5);
        }
        View findViewById = findViewById(R.id.exo_shutter);
        this.shutterView = findViewById;
        if (findViewById != null && z6) {
            findViewById.setBackgroundColor(i3);
        }
        if (aspectRatioFrameLayout == null || i4 == 0) {
            r3 = null;
            this.surfaceView = null;
            z8 = false;
        } else {
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
            if (i4 == 2) {
                this.surfaceView = new TextureView(context2);
            } else if (i4 == 3) {
                try {
                    this.surfaceView = (View) Class.forName("androidx.media3.exoplayer.video.spherical.SphericalGLSurfaceView").getConstructor(new Class[]{Context.class}).newInstance(new Object[]{context});
                    z8 = true;
                    this.surfaceView.setLayoutParams(layoutParams);
                    this.surfaceView.setOnClickListener(componentListener2);
                    this.surfaceView.setClickable(false);
                    aspectRatioFrameLayout.addView(this.surfaceView, 0);
                    r3 = null;
                } catch (Exception e) {
                    throw new IllegalStateException("spherical_gl_surface_view requires an ExoPlayer dependency", e);
                }
            } else if (i4 != 4) {
                SurfaceView surfaceView2 = new SurfaceView(context2);
                if (Util.SDK_INT >= 34) {
                    Api34.setSurfaceLifecycleToFollowsAttachment(surfaceView2);
                }
                this.surfaceView = surfaceView2;
            } else {
                try {
                    this.surfaceView = (View) Class.forName("androidx.media3.exoplayer.video.VideoDecoderGLSurfaceView").getConstructor(new Class[]{Context.class}).newInstance(new Object[]{context});
                } catch (Exception e2) {
                    throw new IllegalStateException("video_decoder_gl_surface_view requires an ExoPlayer dependency", e2);
                }
            }
            z8 = false;
            this.surfaceView.setLayoutParams(layoutParams);
            this.surfaceView.setOnClickListener(componentListener2);
            this.surfaceView.setClickable(false);
            aspectRatioFrameLayout.addView(this.surfaceView, 0);
            r3 = null;
        }
        this.surfaceViewIgnoresVideoAspectRatio = z8;
        this.surfaceSyncGroupV34 = Util.SDK_INT == 34 ? new SurfaceSyncGroupCompatV34() : null;
        this.adOverlayFrameLayout = (FrameLayout) findViewById(R.id.exo_ad_overlay);
        this.overlayFrameLayout = (FrameLayout) findViewById(R.id.exo_overlay);
        this.imageView = (ImageView) findViewById(R.id.exo_image);
        this.imageDisplayMode = i8;
        try {
            cls = Class.forName("androidx.media3.exoplayer.ExoPlayer");
            Class<?> cls2 = Class.forName("androidx.media3.exoplayer.image.ImageOutput");
            method = cls.getMethod("setImageOutput", new Class[]{cls2});
            obj = Proxy.newProxyInstance(cls2.getClassLoader(), new Class[]{cls2}, new PlayerView$$ExternalSyntheticLambda0(this));
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
            cls = null;
            obj = null;
            method = null;
        }
        this.exoPlayerClazz = cls;
        this.setImageOutputMethod = method;
        this.imageOutput = obj;
        ImageView imageView3 = (ImageView) findViewById(R.id.exo_artwork);
        this.artworkView = imageView3;
        this.artworkDisplayMode = !(z5 && i2 != 0 && imageView3 != null) ? 0 : i2;
        if (i6 != 0) {
            this.defaultArtwork = ContextCompat.getDrawable(getContext(), i6);
        }
        SubtitleView subtitleView2 = (SubtitleView) findViewById(R.id.exo_subtitles);
        this.subtitleView = subtitleView2;
        if (subtitleView2 != null) {
            subtitleView2.setUserDefaultStyle();
            subtitleView2.setUserDefaultTextSize();
        }
        View findViewById2 = findViewById(R.id.exo_buffering);
        this.bufferingView = findViewById2;
        if (findViewById2 != null) {
            findViewById2.setVisibility(8);
        }
        this.showBuffering = i7;
        TextView textView = (TextView) findViewById(R.id.exo_error_message);
        this.errorMessageView = textView;
        if (textView != null) {
            textView.setVisibility(8);
        }
        PlayerControlView playerControlView = (PlayerControlView) findViewById(R.id.exo_controller);
        View findViewById3 = findViewById(R.id.exo_controller_placeholder);
        if (playerControlView != null) {
            this.controller = playerControlView;
            z9 = false;
        } else if (findViewById3 != null) {
            z9 = false;
            PlayerControlView playerControlView2 = new PlayerControlView(context2, (AttributeSet) null, 0, attributeSet2);
            this.controller = playerControlView2;
            playerControlView2.setId(R.id.exo_controller);
            playerControlView2.setLayoutParams(findViewById3.getLayoutParams());
            ViewGroup viewGroup = (ViewGroup) findViewById3.getParent();
            int indexOfChild = viewGroup.indexOfChild(findViewById3);
            viewGroup.removeView(findViewById3);
            viewGroup.addView(playerControlView2, indexOfChild);
        } else {
            z9 = false;
            this.controller = null;
        }
        PlayerControlView playerControlView3 = this.controller;
        this.controllerShowTimeoutMs = playerControlView3 != null ? z4 : z9 ? 1 : 0;
        this.controllerHideOnTouch = z7;
        this.controllerAutoShow = z2;
        this.controllerHideDuringAds = z3;
        this.useController = (!z || playerControlView3 == null) ? z9 : true;
        if (playerControlView3 != null) {
            playerControlView3.hideImmediately();
            this.controller.addVisibilityListener(this.componentListener);
        }
        if (z) {
            setClickable(true);
        }
        updateContentDescription();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$androidx-media3-ui-PlayerView  reason: not valid java name */
    public /* synthetic */ Object m1153lambda$new$0$androidxmedia3uiPlayerView(Object obj, Method method, Object[] objArr) throws Throwable {
        if (!method.getName().equals("onImageAvailable")) {
            return null;
        }
        onImageAvailable(objArr[1]);
        return null;
    }

    public static void switchTargetView(Player player2, PlayerView playerView, PlayerView playerView2) {
        if (playerView != playerView2) {
            if (playerView2 != null) {
                playerView2.setPlayer(player2);
            }
            if (playerView != null) {
                playerView.setPlayer((Player) null);
            }
        }
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player2) {
        Assertions.checkState(Looper.myLooper() == Looper.getMainLooper());
        Assertions.checkArgument(player2 == null || player2.getApplicationLooper() == Looper.getMainLooper());
        Player player3 = this.player;
        if (player3 != player2) {
            if (player3 != null) {
                player3.removeListener(this.componentListener);
                if (player3.isCommandAvailable(27)) {
                    View view = this.surfaceView;
                    if (view instanceof TextureView) {
                        player3.clearVideoTextureView((TextureView) view);
                    } else if (view instanceof SurfaceView) {
                        player3.clearVideoSurfaceView((SurfaceView) view);
                    }
                }
                clearImageOutput(player3);
            }
            SubtitleView subtitleView2 = this.subtitleView;
            if (subtitleView2 != null) {
                subtitleView2.setCues((List<Cue>) null);
            }
            this.player = player2;
            if (useController()) {
                this.controller.setPlayer(player2);
            }
            updateBuffering();
            updateErrorMessage();
            updateForCurrentTrackSelections(true);
            if (player2 != null) {
                if (player2.isCommandAvailable(27)) {
                    View view2 = this.surfaceView;
                    if (view2 instanceof TextureView) {
                        player2.setVideoTextureView((TextureView) view2);
                    } else if (view2 instanceof SurfaceView) {
                        player2.setVideoSurfaceView((SurfaceView) view2);
                    }
                    if (!player2.isCommandAvailable(30) || player2.getCurrentTracks().isTypeSupported(2)) {
                        updateAspectRatio();
                    }
                }
                if (this.subtitleView != null && player2.isCommandAvailable(28)) {
                    this.subtitleView.setCues(player2.getCurrentCues().cues);
                }
                player2.addListener(this.componentListener);
                setImageOutput(player2);
                maybeShowController(false);
                return;
            }
            hideController();
        }
    }

    private void setImageOutput(Player player2) {
        Class<?> cls = this.exoPlayerClazz;
        if (cls != null && cls.isAssignableFrom(player2.getClass())) {
            try {
                ((Method) Assertions.checkNotNull(this.setImageOutputMethod)).invoke(player2, new Object[]{Assertions.checkNotNull(this.imageOutput)});
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void clearImageOutput(Player player2) {
        Class<?> cls = this.exoPlayerClazz;
        if (cls != null && cls.isAssignableFrom(player2.getClass())) {
            try {
                ((Method) Assertions.checkNotNull(this.setImageOutputMethod)).invoke(player2, new Object[]{null});
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        View view = this.surfaceView;
        if (view instanceof SurfaceView) {
            view.setVisibility(i);
        }
    }

    public void setResizeMode(int i) {
        Assertions.checkStateNotNull(this.contentFrame);
        this.contentFrame.setResizeMode(i);
    }

    public int getResizeMode() {
        Assertions.checkStateNotNull(this.contentFrame);
        return this.contentFrame.getResizeMode();
    }

    @Deprecated
    public boolean getUseArtwork() {
        return this.artworkDisplayMode != 0;
    }

    @Deprecated
    public void setUseArtwork(boolean z) {
        setArtworkDisplayMode(z ^ true ? 1 : 0);
    }

    public void setArtworkDisplayMode(int i) {
        Assertions.checkState(i == 0 || this.artworkView != null);
        if (this.artworkDisplayMode != i) {
            this.artworkDisplayMode = i;
            updateForCurrentTrackSelections(false);
        }
    }

    public int getArtworkDisplayMode() {
        return this.artworkDisplayMode;
    }

    public Drawable getDefaultArtwork() {
        return this.defaultArtwork;
    }

    public void setDefaultArtwork(Drawable drawable) {
        if (this.defaultArtwork != drawable) {
            this.defaultArtwork = drawable;
            updateForCurrentTrackSelections(false);
        }
    }

    public void setImageDisplayMode(int i) {
        Assertions.checkState(this.imageView != null);
        if (this.imageDisplayMode != i) {
            this.imageDisplayMode = i;
            updateImageViewAspectRatio();
        }
    }

    public int getImageDisplayMode() {
        return this.imageDisplayMode;
    }

    public boolean getUseController() {
        return this.useController;
    }

    public void setUseController(boolean z) {
        boolean z2 = false;
        Assertions.checkState(!z || this.controller != null);
        if (z || hasOnClickListeners()) {
            z2 = true;
        }
        setClickable(z2);
        if (this.useController != z) {
            this.useController = z;
            if (useController()) {
                this.controller.setPlayer(this.player);
            } else {
                PlayerControlView playerControlView = this.controller;
                if (playerControlView != null) {
                    playerControlView.hide();
                    this.controller.setPlayer((Player) null);
                }
            }
            updateContentDescription();
        }
    }

    public void setShutterBackgroundColor(int i) {
        View view = this.shutterView;
        if (view != null) {
            view.setBackgroundColor(i);
        }
    }

    public void setKeepContentOnPlayerReset(boolean z) {
        if (this.keepContentOnPlayerReset != z) {
            this.keepContentOnPlayerReset = z;
            updateForCurrentTrackSelections(false);
        }
    }

    public void setShowBuffering(int i) {
        if (this.showBuffering != i) {
            this.showBuffering = i;
            updateBuffering();
        }
    }

    public void setErrorMessageProvider(ErrorMessageProvider<? super PlaybackException> errorMessageProvider2) {
        if (this.errorMessageProvider != errorMessageProvider2) {
            this.errorMessageProvider = errorMessageProvider2;
            updateErrorMessage();
        }
    }

    public void setCustomErrorMessage(CharSequence charSequence) {
        Assertions.checkState(this.errorMessageView != null);
        this.customErrorMessage = charSequence;
        updateErrorMessage();
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        Player player2 = this.player;
        if (player2 != null && player2.isCommandAvailable(16) && this.player.isPlayingAd()) {
            return super.dispatchKeyEvent(keyEvent);
        }
        boolean isDpadKey = isDpadKey(keyEvent.getKeyCode());
        if (isDpadKey && useController() && !this.controller.isFullyVisible()) {
            maybeShowController(true);
            return true;
        } else if (dispatchMediaKeyEvent(keyEvent) || super.dispatchKeyEvent(keyEvent)) {
            maybeShowController(true);
            return true;
        } else {
            if (isDpadKey && useController()) {
                maybeShowController(true);
            }
            return false;
        }
    }

    public boolean dispatchMediaKeyEvent(KeyEvent keyEvent) {
        return useController() && this.controller.dispatchMediaKeyEvent(keyEvent);
    }

    public boolean isControllerFullyVisible() {
        PlayerControlView playerControlView = this.controller;
        return playerControlView != null && playerControlView.isFullyVisible();
    }

    public void showController() {
        showController(shouldShowControllerIndefinitely());
    }

    public void hideController() {
        PlayerControlView playerControlView = this.controller;
        if (playerControlView != null) {
            playerControlView.hide();
        }
    }

    public int getControllerShowTimeoutMs() {
        return this.controllerShowTimeoutMs;
    }

    public void setControllerShowTimeoutMs(int i) {
        Assertions.checkStateNotNull(this.controller);
        this.controllerShowTimeoutMs = i;
        if (this.controller.isFullyVisible()) {
            showController();
        }
    }

    public boolean getControllerHideOnTouch() {
        return this.controllerHideOnTouch;
    }

    public void setControllerHideOnTouch(boolean z) {
        Assertions.checkStateNotNull(this.controller);
        this.controllerHideOnTouch = z;
        updateContentDescription();
    }

    public boolean getControllerAutoShow() {
        return this.controllerAutoShow;
    }

    public void setControllerAutoShow(boolean z) {
        this.controllerAutoShow = z;
    }

    public void setControllerHideDuringAds(boolean z) {
        this.controllerHideDuringAds = z;
    }

    public void setControllerVisibilityListener(ControllerVisibilityListener controllerVisibilityListener2) {
        this.controllerVisibilityListener = controllerVisibilityListener2;
        if (controllerVisibilityListener2 != null) {
            PlayerControlView.VisibilityListener visibilityListener = null;
            setControllerVisibilityListener((PlayerControlView.VisibilityListener) null);
        }
    }

    public void setControllerAnimationEnabled(boolean z) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setAnimationEnabled(z);
    }

    @Deprecated
    public void setControllerVisibilityListener(PlayerControlView.VisibilityListener visibilityListener) {
        Assertions.checkStateNotNull(this.controller);
        PlayerControlView.VisibilityListener visibilityListener2 = this.legacyControllerVisibilityListener;
        if (visibilityListener2 != visibilityListener) {
            if (visibilityListener2 != null) {
                this.controller.removeVisibilityListener(visibilityListener2);
            }
            this.legacyControllerVisibilityListener = visibilityListener;
            if (visibilityListener != null) {
                this.controller.addVisibilityListener(visibilityListener);
                ControllerVisibilityListener controllerVisibilityListener2 = null;
                setControllerVisibilityListener((ControllerVisibilityListener) null);
            }
        }
    }

    public void setFullscreenButtonClickListener(FullscreenButtonClickListener fullscreenButtonClickListener2) {
        Assertions.checkStateNotNull(this.controller);
        this.fullscreenButtonClickListener = fullscreenButtonClickListener2;
        this.controller.setOnFullScreenModeChangedListener(this.componentListener);
    }

    public void setFullscreenButtonState(boolean z) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.updateIsFullscreen(z);
    }

    @Deprecated
    public void setControllerOnFullScreenModeChangedListener(PlayerControlView.OnFullScreenModeChangedListener onFullScreenModeChangedListener) {
        Assertions.checkStateNotNull(this.controller);
        this.fullscreenButtonClickListener = null;
        this.controller.setOnFullScreenModeChangedListener(onFullScreenModeChangedListener);
    }

    public void setShowRewindButton(boolean z) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setShowRewindButton(z);
    }

    public void setShowFastForwardButton(boolean z) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setShowFastForwardButton(z);
    }

    public void setShowPreviousButton(boolean z) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setShowPreviousButton(z);
    }

    public void setShowNextButton(boolean z) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setShowNextButton(z);
    }

    public void setRepeatToggleModes(int i) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setRepeatToggleModes(i);
    }

    public void setShowShuffleButton(boolean z) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setShowShuffleButton(z);
    }

    public void setShowSubtitleButton(boolean z) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setShowSubtitleButton(z);
    }

    public void setShowVrButton(boolean z) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setShowVrButton(z);
    }

    @Deprecated
    public void setShowMultiWindowTimeBar(boolean z) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setShowMultiWindowTimeBar(z);
    }

    public void setShowPlayButtonIfPlaybackIsSuppressed(boolean z) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setShowPlayButtonIfPlaybackIsSuppressed(z);
    }

    public void setExtraAdGroupMarkers(long[] jArr, boolean[] zArr) {
        Assertions.checkStateNotNull(this.controller);
        this.controller.setExtraAdGroupMarkers(jArr, zArr);
    }

    public void setAspectRatioListener(AspectRatioFrameLayout.AspectRatioListener aspectRatioListener) {
        Assertions.checkStateNotNull(this.contentFrame);
        this.contentFrame.setAspectRatioListener(aspectRatioListener);
    }

    public void setEnableComposeSurfaceSyncWorkaround(boolean z) {
        this.enableComposeSurfaceSyncWorkaround = z;
    }

    public View getVideoSurfaceView() {
        return this.surfaceView;
    }

    public FrameLayout getOverlayFrameLayout() {
        return this.overlayFrameLayout;
    }

    public SubtitleView getSubtitleView() {
        return this.subtitleView;
    }

    public boolean performClick() {
        toggleControllerVisibility();
        return super.performClick();
    }

    public boolean onTrackballEvent(MotionEvent motionEvent) {
        if (!useController() || this.player == null) {
            return false;
        }
        maybeShowController(true);
        return true;
    }

    public void onResume() {
        View view = this.surfaceView;
        if (view instanceof GLSurfaceView) {
            ((GLSurfaceView) view).onResume();
        }
    }

    public void onPause() {
        View view = this.surfaceView;
        if (view instanceof GLSurfaceView) {
            ((GLSurfaceView) view).onPause();
        }
    }

    /* access modifiers changed from: protected */
    public void onContentAspectRatioChanged(AspectRatioFrameLayout aspectRatioFrameLayout, float f) {
        if (aspectRatioFrameLayout != null) {
            aspectRatioFrameLayout.setAspectRatio(f);
        }
    }

    public ViewGroup getAdViewGroup() {
        return (ViewGroup) Assertions.checkStateNotNull(this.adOverlayFrameLayout, "exo_ad_overlay must be present for ad playback");
    }

    public List<AdOverlayInfo> getAdOverlayInfos() {
        ArrayList arrayList = new ArrayList();
        if (this.overlayFrameLayout != null) {
            arrayList.add(new AdOverlayInfo.Builder(this.overlayFrameLayout, 4).setDetailedReason("Transparent overlay does not impact viewability").build());
        }
        if (this.controller != null) {
            arrayList.add(new AdOverlayInfo.Builder(this.controller, 1).build());
        }
        return ImmutableList.copyOf(arrayList);
    }

    @EnsuresNonNullIf(expression = {"controller"}, result = true)
    private boolean useController() {
        if (!this.useController) {
            return false;
        }
        Assertions.checkStateNotNull(this.controller);
        return true;
    }

    private boolean useArtwork() {
        if (this.artworkDisplayMode == 0) {
            return false;
        }
        Assertions.checkStateNotNull(this.artworkView);
        return true;
    }

    /* access modifiers changed from: private */
    public void toggleControllerVisibility() {
        if (useController() && this.player != null) {
            if (!this.controller.isFullyVisible()) {
                maybeShowController(true);
            } else if (this.controllerHideOnTouch) {
                this.controller.hide();
            }
        }
    }

    private void maybeShowController(boolean z) {
        if ((!isPlayingAd() || !this.controllerHideDuringAds) && useController()) {
            boolean z2 = this.controller.isFullyVisible() && this.controller.getShowTimeoutMs() <= 0;
            boolean shouldShowControllerIndefinitely = shouldShowControllerIndefinitely();
            if (z || z2 || shouldShowControllerIndefinitely) {
                showController(shouldShowControllerIndefinitely);
            }
        }
    }

    private boolean shouldShowControllerIndefinitely() {
        Player player2 = this.player;
        if (player2 == null) {
            return true;
        }
        int playbackState = player2.getPlaybackState();
        if (!this.controllerAutoShow || ((this.player.isCommandAvailable(17) && this.player.getCurrentTimeline().isEmpty()) || (playbackState != 1 && playbackState != 4 && ((Player) Assertions.checkNotNull(this.player)).getPlayWhenReady()))) {
            return false;
        }
        return true;
    }

    private void showController(boolean z) {
        if (useController()) {
            this.controller.setShowTimeoutMs(z ? 0 : this.controllerShowTimeoutMs);
            this.controller.show();
        }
    }

    /* access modifiers changed from: private */
    public boolean isPlayingAd() {
        Player player2 = this.player;
        return player2 != null && player2.isCommandAvailable(16) && this.player.isPlayingAd() && this.player.getPlayWhenReady();
    }

    /* access modifiers changed from: private */
    public void updateForCurrentTrackSelections(boolean z) {
        Player player2 = this.player;
        boolean z2 = true;
        boolean z3 = player2 != null && player2.isCommandAvailable(30) && !player2.getCurrentTracks().isEmpty();
        if (!this.keepContentOnPlayerReset && (!z3 || z)) {
            hideArtwork();
            closeShutter();
            hideAndClearImage();
        }
        if (z3) {
            boolean hasSelectedVideoTrack = hasSelectedVideoTrack();
            boolean hasSelectedImageTrack = hasSelectedImageTrack();
            if (!hasSelectedVideoTrack && !hasSelectedImageTrack) {
                closeShutter();
                hideAndClearImage();
            }
            View view = this.shutterView;
            boolean z4 = view != null && view.getVisibility() == 4 && isImageSet();
            if (hasSelectedImageTrack && !hasSelectedVideoTrack && z4) {
                closeShutter();
                showImage();
            } else if (hasSelectedVideoTrack && !hasSelectedImageTrack && z4) {
                hideAndClearImage();
            }
            if (hasSelectedVideoTrack || hasSelectedImageTrack || !useArtwork()) {
                z2 = false;
            }
            if (!z2 || (!setArtworkFromMediaMetadata(player2) && !setDrawableArtwork(this.defaultArtwork))) {
                hideArtwork();
            }
        }
    }

    private boolean setArtworkFromMediaMetadata(Player player2) {
        if (player2 == null || !player2.isCommandAvailable(18)) {
            return false;
        }
        MediaMetadata mediaMetadata = player2.getMediaMetadata();
        if (mediaMetadata.artworkData == null) {
            return false;
        }
        return setDrawableArtwork(new BitmapDrawable(getResources(), BitmapFactory.decodeByteArray(mediaMetadata.artworkData, 0, mediaMetadata.artworkData.length)));
    }

    private boolean setDrawableArtwork(Drawable drawable) {
        if (!(this.artworkView == null || drawable == null)) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            if (intrinsicWidth > 0 && intrinsicHeight > 0) {
                float f = ((float) intrinsicWidth) / ((float) intrinsicHeight);
                ImageView.ScaleType scaleType = ImageView.ScaleType.FIT_XY;
                if (this.artworkDisplayMode == 2) {
                    f = ((float) getWidth()) / ((float) getHeight());
                    scaleType = ImageView.ScaleType.CENTER_CROP;
                }
                onContentAspectRatioChanged(this.contentFrame, f);
                this.artworkView.setScaleType(scaleType);
                this.artworkView.setImageDrawable(drawable);
                this.artworkView.setVisibility(0);
                return true;
            }
        }
        return false;
    }

    private void hideArtwork() {
        ImageView imageView2 = this.artworkView;
        if (imageView2 != null) {
            imageView2.setImageResource(17170445);
            this.artworkView.setVisibility(4);
        }
    }

    /* access modifiers changed from: private */
    public boolean hasSelectedImageTrack() {
        Player player2 = this.player;
        return player2 != null && this.imageOutput != null && player2.isCommandAvailable(30) && player2.getCurrentTracks().isTypeSelected(4);
    }

    private boolean hasSelectedVideoTrack() {
        Player player2 = this.player;
        return player2 != null && player2.isCommandAvailable(30) && player2.getCurrentTracks().isTypeSelected(2);
    }

    private boolean isImageSet() {
        Drawable drawable;
        ImageView imageView2 = this.imageView;
        if (imageView2 == null || (drawable = imageView2.getDrawable()) == null || drawable.getAlpha() == 0) {
            return false;
        }
        return true;
    }

    private void setImage(Drawable drawable) {
        ImageView imageView2 = this.imageView;
        if (imageView2 != null) {
            imageView2.setImageDrawable(drawable);
            updateImageViewAspectRatio();
        }
    }

    private void updateImageViewAspectRatio() {
        Drawable drawable;
        ImageView imageView2 = this.imageView;
        if (imageView2 != null && (drawable = imageView2.getDrawable()) != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            if (intrinsicWidth > 0 && intrinsicHeight > 0) {
                float f = ((float) intrinsicWidth) / ((float) intrinsicHeight);
                ImageView.ScaleType scaleType = ImageView.ScaleType.FIT_XY;
                if (this.imageDisplayMode == 1) {
                    f = ((float) getWidth()) / ((float) getHeight());
                    scaleType = ImageView.ScaleType.CENTER_CROP;
                }
                if (this.imageView.getVisibility() == 0) {
                    onContentAspectRatioChanged(this.contentFrame, f);
                }
                this.imageView.setScaleType(scaleType);
            }
        }
    }

    /* access modifiers changed from: private */
    public void hideAndClearImage() {
        hideImage();
        ImageView imageView2 = this.imageView;
        if (imageView2 != null) {
            imageView2.setImageResource(17170445);
        }
    }

    private void showImage() {
        ImageView imageView2 = this.imageView;
        if (imageView2 != null) {
            imageView2.setVisibility(0);
            updateImageViewAspectRatio();
        }
    }

    /* access modifiers changed from: private */
    public void hideImage() {
        ImageView imageView2 = this.imageView;
        if (imageView2 != null) {
            imageView2.setVisibility(4);
        }
    }

    private void onImageAvailable(Bitmap bitmap) {
        this.mainLooperHandler.post(new PlayerView$$ExternalSyntheticLambda1(this, bitmap));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onImageAvailable$1$androidx-media3-ui-PlayerView  reason: not valid java name */
    public /* synthetic */ void m1154lambda$onImageAvailable$1$androidxmedia3uiPlayerView(Bitmap bitmap) {
        setImage(new BitmapDrawable(getResources(), bitmap));
        if (!hasSelectedVideoTrack()) {
            showImage();
            closeShutter();
        }
    }

    private void closeShutter() {
        View view = this.shutterView;
        if (view != null) {
            view.setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001d, code lost:
        if (r4.player.getPlayWhenReady() == false) goto L_0x0020;
     */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateBuffering() {
        /*
            r4 = this;
            android.view.View r0 = r4.bufferingView
            if (r0 == 0) goto L_0x002b
            androidx.media3.common.Player r0 = r4.player
            r1 = 0
            if (r0 == 0) goto L_0x0020
            int r0 = r0.getPlaybackState()
            r2 = 2
            if (r0 != r2) goto L_0x0020
            int r0 = r4.showBuffering
            r3 = 1
            if (r0 == r2) goto L_0x0021
            if (r0 != r3) goto L_0x0020
            androidx.media3.common.Player r0 = r4.player
            boolean r0 = r0.getPlayWhenReady()
            if (r0 == 0) goto L_0x0020
            goto L_0x0021
        L_0x0020:
            r3 = r1
        L_0x0021:
            android.view.View r0 = r4.bufferingView
            if (r3 == 0) goto L_0x0026
            goto L_0x0028
        L_0x0026:
            r1 = 8
        L_0x0028:
            r0.setVisibility(r1)
        L_0x002b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.ui.PlayerView.updateBuffering():void");
    }

    /* access modifiers changed from: private */
    public void updateErrorMessage() {
        ErrorMessageProvider<? super PlaybackException> errorMessageProvider2;
        TextView textView = this.errorMessageView;
        if (textView != null) {
            CharSequence charSequence = this.customErrorMessage;
            if (charSequence != null) {
                textView.setText(charSequence);
                this.errorMessageView.setVisibility(0);
                return;
            }
            Player player2 = this.player;
            PlaybackException playerError = player2 != null ? player2.getPlayerError() : null;
            if (playerError == null || (errorMessageProvider2 = this.errorMessageProvider) == null) {
                this.errorMessageView.setVisibility(8);
                return;
            }
            this.errorMessageView.setText((CharSequence) errorMessageProvider2.getErrorMessage(playerError).second);
            this.errorMessageView.setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    public void updateContentDescription() {
        PlayerControlView playerControlView = this.controller;
        String str = null;
        if (playerControlView == null || !this.useController) {
            setContentDescription((CharSequence) null);
        } else if (playerControlView.isFullyVisible()) {
            if (this.controllerHideOnTouch) {
                str = getResources().getString(R.string.exo_controls_hide);
            }
            setContentDescription(str);
        } else {
            setContentDescription(getResources().getString(R.string.exo_controls_show));
        }
    }

    /* access modifiers changed from: private */
    public void updateControllerVisibility() {
        if (!isPlayingAd() || !this.controllerHideDuringAds) {
            maybeShowController(false);
        } else {
            hideController();
        }
    }

    /* access modifiers changed from: private */
    public void updateAspectRatio() {
        Player player2 = this.player;
        VideoSize videoSize = player2 != null ? player2.getVideoSize() : VideoSize.UNKNOWN;
        int i = videoSize.width;
        int i2 = videoSize.height;
        float f = 0.0f;
        float f2 = (i2 == 0 || i == 0) ? 0.0f : (((float) i) * videoSize.pixelWidthHeightRatio) / ((float) i2);
        AspectRatioFrameLayout aspectRatioFrameLayout = this.contentFrame;
        if (!this.surfaceViewIgnoresVideoAspectRatio) {
            f = f2;
        }
        onContentAspectRatioChanged(aspectRatioFrameLayout, f);
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        SurfaceSyncGroupCompatV34 surfaceSyncGroupCompatV34;
        super.dispatchDraw(canvas);
        if (Util.SDK_INT == 34 && (surfaceSyncGroupCompatV34 = this.surfaceSyncGroupV34) != null && this.enableComposeSurfaceSyncWorkaround) {
            surfaceSyncGroupCompatV34.maybeMarkSyncReadyAndClear();
        }
    }

    private static void configureEditModeLogoV23(Context context, Resources resources, ImageView imageView2) {
        imageView2.setImageDrawable(Util.getDrawable(context, resources, R.drawable.exo_edit_mode_logo));
        imageView2.setBackgroundColor(resources.getColor(R.color.exo_edit_mode_background_color, (Resources.Theme) null));
    }

    private static void configureEditModeLogo(Context context, Resources resources, ImageView imageView2) {
        imageView2.setImageDrawable(Util.getDrawable(context, resources, R.drawable.exo_edit_mode_logo));
        imageView2.setBackgroundColor(resources.getColor(R.color.exo_edit_mode_background_color));
    }

    private static void setResizeModeRaw(AspectRatioFrameLayout aspectRatioFrameLayout, int i) {
        aspectRatioFrameLayout.setResizeMode(i);
    }

    private final class ComponentListener implements Player.Listener, View.OnClickListener, PlayerControlView.VisibilityListener, PlayerControlView.OnFullScreenModeChangedListener {
        private Object lastPeriodUidWithTracks;
        private final Timeline.Period period = new Timeline.Period();

        public ComponentListener() {
        }

        public void onCues(CueGroup cueGroup) {
            if (PlayerView.this.subtitleView != null) {
                PlayerView.this.subtitleView.setCues(cueGroup.cues);
            }
        }

        public void onVideoSizeChanged(VideoSize videoSize) {
            if (!videoSize.equals(VideoSize.UNKNOWN) && PlayerView.this.player != null && PlayerView.this.player.getPlaybackState() != 1) {
                PlayerView.this.updateAspectRatio();
            }
        }

        public void onSurfaceSizeChanged(int i, int i2) {
            if (Util.SDK_INT == 34 && (PlayerView.this.surfaceView instanceof SurfaceView) && PlayerView.this.enableComposeSurfaceSyncWorkaround) {
                ((SurfaceSyncGroupCompatV34) Assertions.checkNotNull(PlayerView.this.surfaceSyncGroupV34)).postRegister(PlayerView.this.mainLooperHandler, (SurfaceView) PlayerView.this.surfaceView, new PlayerView$ComponentListener$$ExternalSyntheticLambda0(PlayerView.this));
            }
        }

        public void onRenderedFirstFrame() {
            if (PlayerView.this.shutterView != null) {
                PlayerView.this.shutterView.setVisibility(4);
                if (PlayerView.this.hasSelectedImageTrack()) {
                    PlayerView.this.hideImage();
                } else {
                    PlayerView.this.hideAndClearImage();
                }
            }
        }

        public void onTracksChanged(Tracks tracks) {
            Timeline timeline;
            Player player = (Player) Assertions.checkNotNull(PlayerView.this.player);
            if (player.isCommandAvailable(17)) {
                timeline = player.getCurrentTimeline();
            } else {
                timeline = Timeline.EMPTY;
            }
            if (timeline.isEmpty()) {
                this.lastPeriodUidWithTracks = null;
            } else if (!player.isCommandAvailable(30) || player.getCurrentTracks().isEmpty()) {
                Object obj = this.lastPeriodUidWithTracks;
                if (obj != null) {
                    int indexOfPeriod = timeline.getIndexOfPeriod(obj);
                    if (indexOfPeriod == -1 || player.getCurrentMediaItemIndex() != timeline.getPeriod(indexOfPeriod, this.period).windowIndex) {
                        this.lastPeriodUidWithTracks = null;
                    } else {
                        return;
                    }
                }
            } else {
                this.lastPeriodUidWithTracks = timeline.getPeriod(player.getCurrentPeriodIndex(), this.period, true).uid;
            }
            PlayerView.this.updateForCurrentTrackSelections(false);
        }

        public void onPlaybackStateChanged(int i) {
            PlayerView.this.updateBuffering();
            PlayerView.this.updateErrorMessage();
            PlayerView.this.updateControllerVisibility();
        }

        public void onPlayWhenReadyChanged(boolean z, int i) {
            PlayerView.this.updateBuffering();
            PlayerView.this.updateControllerVisibility();
        }

        public void onPositionDiscontinuity(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i) {
            if (PlayerView.this.isPlayingAd() && PlayerView.this.controllerHideDuringAds) {
                PlayerView.this.hideController();
            }
        }

        public void onClick(View view) {
            PlayerView.this.toggleControllerVisibility();
        }

        public void onVisibilityChange(int i) {
            PlayerView.this.updateContentDescription();
            if (PlayerView.this.controllerVisibilityListener != null) {
                PlayerView.this.controllerVisibilityListener.onVisibilityChanged(i);
            }
        }

        public void onFullScreenModeChanged(boolean z) {
            if (PlayerView.this.fullscreenButtonClickListener != null) {
                PlayerView.this.fullscreenButtonClickListener.onFullscreenButtonClick(z);
            }
        }
    }

    private static class Api34 {
        private Api34() {
        }

        public static void setSurfaceLifecycleToFollowsAttachment(SurfaceView surfaceView) {
            surfaceView.setSurfaceLifecycle(2);
        }
    }

    private static final class SurfaceSyncGroupCompatV34 {
        SurfaceSyncGroup surfaceSyncGroup;

        static /* synthetic */ void lambda$postRegister$0() {
        }

        private SurfaceSyncGroupCompatV34() {
        }

        public void postRegister(Handler handler, SurfaceView surfaceView, Runnable runnable) {
            handler.post(new PlayerView$SurfaceSyncGroupCompatV34$$ExternalSyntheticLambda1(this, surfaceView, runnable));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$postRegister$1$androidx-media3-ui-PlayerView$SurfaceSyncGroupCompatV34  reason: not valid java name */
        public /* synthetic */ void m1156lambda$postRegister$1$androidxmedia3uiPlayerView$SurfaceSyncGroupCompatV34(SurfaceView surfaceView, Runnable runnable) {
            AttachedSurfaceControl rootSurfaceControl = surfaceView.getRootSurfaceControl();
            if (rootSurfaceControl != null) {
                SurfaceSyncGroup surfaceSyncGroup2 = new SurfaceSyncGroup("exo-sync-b-334901521");
                this.surfaceSyncGroup = surfaceSyncGroup2;
                Assertions.checkState(surfaceSyncGroup2.add(rootSurfaceControl, new PlayerView$SurfaceSyncGroupCompatV34$$ExternalSyntheticLambda0()));
                runnable.run();
                rootSurfaceControl.applyTransactionOnDraw(new SurfaceControl.Transaction());
            }
        }

        public void maybeMarkSyncReadyAndClear() {
            SurfaceSyncGroup surfaceSyncGroup2 = this.surfaceSyncGroup;
            if (surfaceSyncGroup2 != null) {
                surfaceSyncGroup2.markSyncReady();
                this.surfaceSyncGroup = null;
            }
        }
    }
}

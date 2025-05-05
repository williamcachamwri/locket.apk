package androidx.media3.exoplayer;

import android.content.Context;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Handler;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

final class AudioFocusManager {
    private static final int AUDIOFOCUS_GAIN = 1;
    private static final int AUDIOFOCUS_GAIN_TRANSIENT = 2;
    private static final int AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE = 4;
    private static final int AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK = 3;
    private static final int AUDIOFOCUS_NONE = 0;
    private static final int AUDIO_FOCUS_STATE_HAVE_FOCUS = 2;
    private static final int AUDIO_FOCUS_STATE_LOSS_TRANSIENT = 3;
    private static final int AUDIO_FOCUS_STATE_LOSS_TRANSIENT_DUCK = 4;
    private static final int AUDIO_FOCUS_STATE_NOT_REQUESTED = 0;
    private static final int AUDIO_FOCUS_STATE_NO_FOCUS = 1;
    public static final int PLAYER_COMMAND_DO_NOT_PLAY = -1;
    public static final int PLAYER_COMMAND_PLAY_WHEN_READY = 1;
    public static final int PLAYER_COMMAND_WAIT_FOR_CALLBACK = 0;
    private static final String TAG = "AudioFocusManager";
    private static final float VOLUME_MULTIPLIER_DEFAULT = 1.0f;
    private static final float VOLUME_MULTIPLIER_DUCK = 0.2f;
    private AudioAttributes audioAttributes;
    private AudioFocusRequest audioFocusRequest;
    private int audioFocusState;
    private final Supplier<AudioManager> audioManager;
    private int focusGainToRequest;
    private final AudioFocusListener focusListener;
    private PlayerControl playerControl;
    private boolean rebuildAudioFocusRequest;
    private float volumeMultiplier = 1.0f;

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface PlayerCommand {
    }

    public interface PlayerControl {
        void executePlayerCommand(int i);

        void setVolumeMultiplier(float f);
    }

    public AudioFocusManager(Context context, Handler handler, PlayerControl playerControl2) {
        this.audioManager = Suppliers.memoize(new AudioFocusManager$$ExternalSyntheticLambda0(context));
        this.playerControl = playerControl2;
        this.focusListener = new AudioFocusListener(handler);
        this.audioFocusState = 0;
    }

    static /* synthetic */ AudioManager lambda$new$0(Context context) {
        return (AudioManager) Assertions.checkNotNull((AudioManager) context.getApplicationContext().getSystemService(MimeTypes.BASE_TYPE_AUDIO));
    }

    public float getVolumeMultiplier() {
        return this.volumeMultiplier;
    }

    public void setAudioAttributes(AudioAttributes audioAttributes2) {
        if (!Util.areEqual(this.audioAttributes, audioAttributes2)) {
            this.audioAttributes = audioAttributes2;
            int convertAudioAttributesToFocusGain = convertAudioAttributesToFocusGain(audioAttributes2);
            this.focusGainToRequest = convertAudioAttributesToFocusGain;
            boolean z = true;
            if (!(convertAudioAttributesToFocusGain == 1 || convertAudioAttributesToFocusGain == 0)) {
                z = false;
            }
            Assertions.checkArgument(z, "Automatic handling of audio focus is only available for USAGE_MEDIA and USAGE_GAME.");
        }
    }

    public int updateAudioFocus(boolean z, int i) {
        if (!shouldHandleAudioFocus(i)) {
            abandonAudioFocusIfHeld();
            setAudioFocusState(0);
            return 1;
        } else if (z) {
            return requestAudioFocus();
        } else {
            int i2 = this.audioFocusState;
            if (i2 != 1) {
                return i2 != 3 ? 1 : 0;
            }
            return -1;
        }
    }

    public void release() {
        this.playerControl = null;
        abandonAudioFocusIfHeld();
        setAudioFocusState(0);
    }

    /* access modifiers changed from: package-private */
    public AudioManager.OnAudioFocusChangeListener getFocusListener() {
        return this.focusListener;
    }

    private boolean shouldHandleAudioFocus(int i) {
        return i != 1 && this.focusGainToRequest == 1;
    }

    private int requestAudioFocus() {
        if (this.audioFocusState == 2) {
            return 1;
        }
        if ((Util.SDK_INT >= 26 ? requestAudioFocusV26() : requestAudioFocusDefault()) == 1) {
            setAudioFocusState(2);
            return 1;
        }
        setAudioFocusState(1);
        return -1;
    }

    private void abandonAudioFocusIfHeld() {
        int i = this.audioFocusState;
        if (i != 1 && i != 0) {
            if (Util.SDK_INT >= 26) {
                abandonAudioFocusV26();
            } else {
                abandonAudioFocusDefault();
            }
        }
    }

    private int requestAudioFocusDefault() {
        return this.audioManager.get().requestAudioFocus(this.focusListener, Util.getStreamTypeForAudioUsage(((AudioAttributes) Assertions.checkNotNull(this.audioAttributes)).usage), this.focusGainToRequest);
    }

    private int requestAudioFocusV26() {
        AudioFocusRequest.Builder builder;
        AudioFocusRequest audioFocusRequest2 = this.audioFocusRequest;
        if (audioFocusRequest2 == null || this.rebuildAudioFocusRequest) {
            if (audioFocusRequest2 == null) {
                builder = new AudioFocusRequest.Builder(this.focusGainToRequest);
            } else {
                builder = new AudioFocusRequest.Builder(this.audioFocusRequest);
            }
            this.audioFocusRequest = builder.setAudioAttributes(((AudioAttributes) Assertions.checkNotNull(this.audioAttributes)).getAudioAttributesV21().audioAttributes).setWillPauseWhenDucked(willPauseWhenDucked()).setOnAudioFocusChangeListener(this.focusListener).build();
            this.rebuildAudioFocusRequest = false;
        }
        return this.audioManager.get().requestAudioFocus(this.audioFocusRequest);
    }

    private void abandonAudioFocusDefault() {
        this.audioManager.get().abandonAudioFocus(this.focusListener);
    }

    private void abandonAudioFocusV26() {
        if (this.audioFocusRequest != null) {
            this.audioManager.get().abandonAudioFocusRequest(this.audioFocusRequest);
        }
    }

    private boolean willPauseWhenDucked() {
        AudioAttributes audioAttributes2 = this.audioAttributes;
        return audioAttributes2 != null && audioAttributes2.contentType == 1;
    }

    private static int convertAudioAttributesToFocusGain(AudioAttributes audioAttributes2) {
        if (audioAttributes2 == null) {
            return 0;
        }
        switch (audioAttributes2.usage) {
            case 0:
                Log.w(TAG, "Specify a proper usage in the audio attributes for audio focus handling. Using AUDIOFOCUS_GAIN by default.");
                return 1;
            case 1:
            case 14:
                return 1;
            case 2:
            case 4:
                return 2;
            case 3:
                return 0;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 12:
            case 13:
                break;
            case 11:
                if (audioAttributes2.contentType == 1) {
                    return 2;
                }
                break;
            case 16:
                return 4;
            default:
                Log.w(TAG, "Unidentified audio usage: " + audioAttributes2.usage);
                return 0;
        }
        return 3;
    }

    private void setAudioFocusState(int i) {
        if (this.audioFocusState != i) {
            this.audioFocusState = i;
            float f = i == 4 ? 0.2f : 1.0f;
            if (this.volumeMultiplier != f) {
                this.volumeMultiplier = f;
                PlayerControl playerControl2 = this.playerControl;
                if (playerControl2 != null) {
                    playerControl2.setVolumeMultiplier(f);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void handlePlatformAudioFocusChange(int i) {
        if (i == -3 || i == -2) {
            if (i == -2 || willPauseWhenDucked()) {
                executePlayerCommand(0);
                setAudioFocusState(3);
                return;
            }
            setAudioFocusState(4);
        } else if (i == -1) {
            executePlayerCommand(-1);
            abandonAudioFocusIfHeld();
            setAudioFocusState(1);
        } else if (i != 1) {
            Log.w(TAG, "Unknown focus change type: " + i);
        } else {
            setAudioFocusState(2);
            executePlayerCommand(1);
        }
    }

    private void executePlayerCommand(int i) {
        PlayerControl playerControl2 = this.playerControl;
        if (playerControl2 != null) {
            playerControl2.executePlayerCommand(i);
        }
    }

    private class AudioFocusListener implements AudioManager.OnAudioFocusChangeListener {
        private final Handler eventHandler;

        public AudioFocusListener(Handler handler) {
            this.eventHandler = handler;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onAudioFocusChange$0$androidx-media3-exoplayer-AudioFocusManager$AudioFocusListener  reason: not valid java name */
        public /* synthetic */ void m464lambda$onAudioFocusChange$0$androidxmedia3exoplayerAudioFocusManager$AudioFocusListener(int i) {
            AudioFocusManager.this.handlePlatformAudioFocusChange(i);
        }

        public void onAudioFocusChange(int i) {
            this.eventHandler.post(new AudioFocusManager$AudioFocusListener$$ExternalSyntheticLambda0(this, i));
        }
    }
}

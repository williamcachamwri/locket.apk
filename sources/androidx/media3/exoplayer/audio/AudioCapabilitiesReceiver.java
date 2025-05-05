package androidx.media3.exoplayer.audio;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.media.AudioDeviceCallback;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Handler;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;

public final class AudioCapabilitiesReceiver {
    /* access modifiers changed from: private */
    public AudioAttributes audioAttributes;
    private AudioCapabilities audioCapabilities;
    private final AudioDeviceCallbackV23 audioDeviceCallback;
    /* access modifiers changed from: private */
    public final Context context;
    private final ExternalSurroundSoundSettingObserver externalSurroundSoundSettingObserver;
    private final Handler handler;
    private final BroadcastReceiver hdmiAudioPlugBroadcastReceiver;
    private final Listener listener;
    private boolean registered;
    /* access modifiers changed from: private */
    public AudioDeviceInfoApi23 routedDevice;

    public interface Listener {
        void onAudioCapabilitiesChanged(AudioCapabilities audioCapabilities);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public AudioCapabilitiesReceiver(Context context2, Listener listener2) {
        this(context2, listener2, AudioAttributes.DEFAULT, (AudioDeviceInfo) null);
        AudioDeviceInfo audioDeviceInfo = null;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public AudioCapabilitiesReceiver(Context context2, Listener listener2, AudioAttributes audioAttributes2, AudioDeviceInfo audioDeviceInfo) {
        this(context2, listener2, audioAttributes2, (Util.SDK_INT < 23 || audioDeviceInfo == null) ? null : new AudioDeviceInfoApi23(audioDeviceInfo));
    }

    AudioCapabilitiesReceiver(Context context2, Listener listener2, AudioAttributes audioAttributes2, AudioDeviceInfoApi23 audioDeviceInfoApi23) {
        Context applicationContext = context2.getApplicationContext();
        this.context = applicationContext;
        this.listener = (Listener) Assertions.checkNotNull(listener2);
        this.audioAttributes = audioAttributes2;
        this.routedDevice = audioDeviceInfoApi23;
        Handler createHandlerForCurrentOrMainLooper = Util.createHandlerForCurrentOrMainLooper();
        this.handler = createHandlerForCurrentOrMainLooper;
        ExternalSurroundSoundSettingObserver externalSurroundSoundSettingObserver2 = null;
        this.audioDeviceCallback = Util.SDK_INT >= 23 ? new AudioDeviceCallbackV23() : null;
        this.hdmiAudioPlugBroadcastReceiver = new HdmiAudioPlugBroadcastReceiver();
        Uri externalSurroundSoundGlobalSettingUri = AudioCapabilities.getExternalSurroundSoundGlobalSettingUri();
        this.externalSurroundSoundSettingObserver = externalSurroundSoundGlobalSettingUri != null ? new ExternalSurroundSoundSettingObserver(createHandlerForCurrentOrMainLooper, applicationContext.getContentResolver(), externalSurroundSoundGlobalSettingUri) : externalSurroundSoundSettingObserver2;
    }

    public void setAudioAttributes(AudioAttributes audioAttributes2) {
        this.audioAttributes = audioAttributes2;
        onNewAudioCapabilities(AudioCapabilities.getCapabilitiesInternal(this.context, audioAttributes2, this.routedDevice));
    }

    public void setRoutedDevice(AudioDeviceInfo audioDeviceInfo) {
        AudioDeviceInfoApi23 audioDeviceInfoApi23 = this.routedDevice;
        AudioDeviceInfoApi23 audioDeviceInfoApi232 = null;
        if (!Util.areEqual(audioDeviceInfo, audioDeviceInfoApi23 == null ? null : audioDeviceInfoApi23.audioDeviceInfo)) {
            if (audioDeviceInfo != null) {
                audioDeviceInfoApi232 = new AudioDeviceInfoApi23(audioDeviceInfo);
            }
            this.routedDevice = audioDeviceInfoApi232;
            onNewAudioCapabilities(AudioCapabilities.getCapabilitiesInternal(this.context, this.audioAttributes, audioDeviceInfoApi232));
        }
    }

    public AudioCapabilities register() {
        AudioDeviceCallbackV23 audioDeviceCallbackV23;
        if (this.registered) {
            return (AudioCapabilities) Assertions.checkNotNull(this.audioCapabilities);
        }
        this.registered = true;
        ExternalSurroundSoundSettingObserver externalSurroundSoundSettingObserver2 = this.externalSurroundSoundSettingObserver;
        if (externalSurroundSoundSettingObserver2 != null) {
            externalSurroundSoundSettingObserver2.register();
        }
        if (Util.SDK_INT >= 23 && (audioDeviceCallbackV23 = this.audioDeviceCallback) != null) {
            Api23.registerAudioDeviceCallback(this.context, audioDeviceCallbackV23, this.handler);
        }
        AudioCapabilities capabilitiesInternal = AudioCapabilities.getCapabilitiesInternal(this.context, this.context.registerReceiver(this.hdmiAudioPlugBroadcastReceiver, new IntentFilter("android.media.action.HDMI_AUDIO_PLUG"), (String) null, this.handler), this.audioAttributes, this.routedDevice);
        this.audioCapabilities = capabilitiesInternal;
        return capabilitiesInternal;
    }

    public void unregister() {
        AudioDeviceCallbackV23 audioDeviceCallbackV23;
        if (this.registered) {
            this.audioCapabilities = null;
            if (Util.SDK_INT >= 23 && (audioDeviceCallbackV23 = this.audioDeviceCallback) != null) {
                Api23.unregisterAudioDeviceCallback(this.context, audioDeviceCallbackV23);
            }
            this.context.unregisterReceiver(this.hdmiAudioPlugBroadcastReceiver);
            ExternalSurroundSoundSettingObserver externalSurroundSoundSettingObserver2 = this.externalSurroundSoundSettingObserver;
            if (externalSurroundSoundSettingObserver2 != null) {
                externalSurroundSoundSettingObserver2.unregister();
            }
            this.registered = false;
        }
    }

    /* access modifiers changed from: private */
    public void onNewAudioCapabilities(AudioCapabilities audioCapabilities2) {
        if (this.registered && !audioCapabilities2.equals(this.audioCapabilities)) {
            this.audioCapabilities = audioCapabilities2;
            this.listener.onAudioCapabilitiesChanged(audioCapabilities2);
        }
    }

    private final class HdmiAudioPlugBroadcastReceiver extends BroadcastReceiver {
        private HdmiAudioPlugBroadcastReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if (!isInitialStickyBroadcast()) {
                AudioCapabilitiesReceiver audioCapabilitiesReceiver = AudioCapabilitiesReceiver.this;
                audioCapabilitiesReceiver.onNewAudioCapabilities(AudioCapabilities.getCapabilitiesInternal(context, intent, audioCapabilitiesReceiver.audioAttributes, AudioCapabilitiesReceiver.this.routedDevice));
            }
        }
    }

    private final class ExternalSurroundSoundSettingObserver extends ContentObserver {
        private final ContentResolver resolver;
        private final Uri settingUri;

        public ExternalSurroundSoundSettingObserver(Handler handler, ContentResolver contentResolver, Uri uri) {
            super(handler);
            this.resolver = contentResolver;
            this.settingUri = uri;
        }

        public void register() {
            this.resolver.registerContentObserver(this.settingUri, false, this);
        }

        public void unregister() {
            this.resolver.unregisterContentObserver(this);
        }

        public void onChange(boolean z) {
            AudioCapabilitiesReceiver audioCapabilitiesReceiver = AudioCapabilitiesReceiver.this;
            audioCapabilitiesReceiver.onNewAudioCapabilities(AudioCapabilities.getCapabilitiesInternal(audioCapabilitiesReceiver.context, AudioCapabilitiesReceiver.this.audioAttributes, AudioCapabilitiesReceiver.this.routedDevice));
        }
    }

    private final class AudioDeviceCallbackV23 extends AudioDeviceCallback {
        private AudioDeviceCallbackV23() {
        }

        public void onAudioDevicesAdded(AudioDeviceInfo[] audioDeviceInfoArr) {
            AudioCapabilitiesReceiver audioCapabilitiesReceiver = AudioCapabilitiesReceiver.this;
            audioCapabilitiesReceiver.onNewAudioCapabilities(AudioCapabilities.getCapabilitiesInternal(audioCapabilitiesReceiver.context, AudioCapabilitiesReceiver.this.audioAttributes, AudioCapabilitiesReceiver.this.routedDevice));
        }

        public void onAudioDevicesRemoved(AudioDeviceInfo[] audioDeviceInfoArr) {
            if (Util.contains((Object[]) audioDeviceInfoArr, (Object) AudioCapabilitiesReceiver.this.routedDevice)) {
                AudioDeviceInfoApi23 unused = AudioCapabilitiesReceiver.this.routedDevice = null;
            }
            AudioCapabilitiesReceiver audioCapabilitiesReceiver = AudioCapabilitiesReceiver.this;
            audioCapabilitiesReceiver.onNewAudioCapabilities(AudioCapabilities.getCapabilitiesInternal(audioCapabilitiesReceiver.context, AudioCapabilitiesReceiver.this.audioAttributes, AudioCapabilitiesReceiver.this.routedDevice));
        }
    }

    private static final class Api23 {
        public static void registerAudioDeviceCallback(Context context, AudioDeviceCallback audioDeviceCallback, Handler handler) {
            ((AudioManager) Assertions.checkNotNull((AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO))).registerAudioDeviceCallback(audioDeviceCallback, handler);
        }

        public static void unregisterAudioDeviceCallback(Context context, AudioDeviceCallback audioDeviceCallback) {
            ((AudioManager) Assertions.checkNotNull((AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO))).unregisterAudioDeviceCallback(audioDeviceCallback);
        }

        private Api23() {
        }
    }
}

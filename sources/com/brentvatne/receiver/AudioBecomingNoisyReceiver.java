package com.brentvatne.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.core.content.ContextCompat;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001c\u0010\b\u001a\u00020\t2\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u0006\u0010\f\u001a\u00020\tJ\u000e\u0010\r\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007R\u000e\u0010\u0005\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/brentvatne/receiver/AudioBecomingNoisyReceiver;", "Landroid/content/BroadcastReceiver;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "appContext", "listener", "Lcom/brentvatne/receiver/BecomingNoisyListener;", "onReceive", "", "intent", "Landroid/content/Intent;", "removeListener", "setListener", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioBecomingNoisyReceiver.kt */
public final class AudioBecomingNoisyReceiver extends BroadcastReceiver {
    private final Context appContext;
    private BecomingNoisyListener listener = BecomingNoisyListener.Companion.getNO_OP();

    public AudioBecomingNoisyReceiver(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        this.appContext = applicationContext;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null && Intrinsics.areEqual((Object) "android.media.AUDIO_BECOMING_NOISY", (Object) intent.getAction())) {
            this.listener.onAudioBecomingNoisy();
        }
    }

    public final void setListener(BecomingNoisyListener becomingNoisyListener) {
        Intrinsics.checkNotNullParameter(becomingNoisyListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.listener = becomingNoisyListener;
        ContextCompat.registerReceiver(this.appContext, this, new IntentFilter("android.media.AUDIO_BECOMING_NOISY"), 4);
    }

    public final void removeListener() {
        this.listener = BecomingNoisyListener.Companion.getNO_OP();
        try {
            this.appContext.unregisterReceiver(this);
        } catch (Exception unused) {
        }
    }
}

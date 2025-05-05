package com.brentvatne.receiver;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.core.content.ContextCompat;
import com.brentvatne.exoplayer.ReactExoplayerView;
import com.facebook.react.uimanager.ThemedReactContext;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u001c\u0010\u000b\u001a\u00020\f2\b\u0010\u0004\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0006\u0010\u0010\u001a\u00020\fJ\u0006\u0010\u0011\u001a\u00020\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/brentvatne/receiver/PictureInPictureReceiver;", "Landroid/content/BroadcastReceiver;", "view", "Lcom/brentvatne/exoplayer/ReactExoplayerView;", "context", "Lcom/facebook/react/uimanager/ThemedReactContext;", "(Lcom/brentvatne/exoplayer/ReactExoplayerView;Lcom/facebook/react/uimanager/ThemedReactContext;)V", "getPipActionIntent", "Landroid/app/PendingIntent;", "isPaused", "", "onReceive", "", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "removeListener", "setListener", "Companion", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PictureInPictureReceiver.kt */
public final class PictureInPictureReceiver extends BroadcastReceiver {
    public static final String ACTION_MEDIA_CONTROL = "rnv_media_control";
    public static final int CONTROL_TYPE_PAUSE = 2;
    public static final int CONTROL_TYPE_PLAY = 1;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String EXTRA_CONTROL_TYPE = "rnv_control_type";
    public static final int REQUEST_PAUSE = 2;
    public static final int REQUEST_PLAY = 1;
    private final ThemedReactContext context;
    private final ReactExoplayerView view;

    public PictureInPictureReceiver(ReactExoplayerView reactExoplayerView, ThemedReactContext themedReactContext) {
        Intrinsics.checkNotNullParameter(reactExoplayerView, "view");
        Intrinsics.checkNotNullParameter(themedReactContext, "context");
        this.view = reactExoplayerView;
        this.context = themedReactContext;
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/brentvatne/receiver/PictureInPictureReceiver$Companion;", "", "()V", "ACTION_MEDIA_CONTROL", "", "CONTROL_TYPE_PAUSE", "", "CONTROL_TYPE_PLAY", "EXTRA_CONTROL_TYPE", "REQUEST_PAUSE", "REQUEST_PLAY", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PictureInPictureReceiver.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public void onReceive(Context context2, Intent intent) {
        if (intent != null && Intrinsics.areEqual((Object) intent.getAction(), (Object) ACTION_MEDIA_CONTROL)) {
            int intExtra = intent.getIntExtra(EXTRA_CONTROL_TYPE, 0);
            if (intExtra == 1) {
                this.view.setPausedModifier(false);
            } else if (intExtra == 2) {
                this.view.setPausedModifier(true);
            }
        }
    }

    public final void setListener() {
        ContextCompat.registerReceiver(this.context, this, new IntentFilter(ACTION_MEDIA_CONTROL), 4);
    }

    public final void removeListener() {
        try {
            this.context.unregisterReceiver(this);
        } catch (Exception unused) {
        }
    }

    public final PendingIntent getPipActionIntent(boolean z) {
        int i = 1;
        int i2 = z ? 1 : 2;
        if (!z) {
            i = 2;
        }
        Intent putExtra = new Intent(ACTION_MEDIA_CONTROL).putExtra(EXTRA_CONTROL_TYPE, i);
        Intrinsics.checkNotNullExpressionValue(putExtra, "putExtra(...)");
        putExtra.setPackage(this.context.getPackageName());
        PendingIntent broadcast = PendingIntent.getBroadcast(this.context, i2, putExtra, 201326592);
        Intrinsics.checkNotNullExpressionValue(broadcast, "getBroadcast(...)");
        return broadcast;
    }
}

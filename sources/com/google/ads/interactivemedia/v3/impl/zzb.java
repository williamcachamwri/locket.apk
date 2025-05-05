package com.google.ads.interactivemedia.v3.impl;

import android.app.Activity;
import android.app.Application;
import android.graphics.Rect;
import android.media.AudioManager;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.view.View;
import androidx.media3.common.MimeTypes;
import com.google.ads.interactivemedia.v3.impl.JavaScriptMessage;
import com.google.ads.interactivemedia.v3.impl.data.zza;
import com.google.ads.interactivemedia.v3.impl.data.zzba;
import com.google.ads.interactivemedia.v3.impl.data.zzbb;
import com.google.ads.interactivemedia.v3.internal.zzel;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzb implements zzaz {
    /* access modifiers changed from: private */
    public final zzbi zza;
    /* access modifiers changed from: private */
    public final String zzb;
    private final View zzc;
    private zza zzd = null;
    /* access modifiers changed from: private */
    public Activity zze = null;
    private boolean zzf = false;

    public zzb(String str, zzbi zzbi, View view) {
        this.zzb = str;
        this.zza = zzbi;
        this.zzc = view;
    }

    private final DisplayMetrics zzj() {
        return this.zzc.getContext().getResources().getDisplayMetrics();
    }

    private static zzbb zzk(zzbb zzbb, float f) {
        zzba builder = zzbb.builder();
        builder.left((int) Math.ceil((double) (((float) zzbb.left()) / f)));
        builder.top((int) Math.ceil((double) (((float) zzbb.top()) / f)));
        builder.height((int) Math.ceil((double) (((float) zzbb.height()) / f)));
        builder.width((int) Math.ceil((double) (((float) zzbb.width()) / f)));
        return builder.build();
    }

    public final com.google.ads.interactivemedia.v3.impl.data.zzb zzc(String str, String str2, String str3) {
        double d;
        zzbb zzk = zzk(zzbb.builder().locationOnScreenOfView(this.zzc).build(), zzj().density);
        Rect rect = new Rect();
        boolean globalVisibleRect = this.zzc.getGlobalVisibleRect(rect);
        IBinder windowToken = this.zzc.getWindowToken();
        boolean z = false;
        if (!globalVisibleRect || windowToken == null || !this.zzc.isShown()) {
            rect.set(0, 0, 0, 0);
        }
        zzba builder = zzbb.builder();
        builder.left(rect.left);
        builder.top(rect.top);
        builder.height(rect.height());
        builder.width(rect.width());
        zzbb zzk2 = zzk(builder.build(), zzj().density);
        if (!this.zzc.getGlobalVisibleRect(new Rect()) || !this.zzc.isShown()) {
            z = true;
        }
        AudioManager audioManager = (AudioManager) this.zzc.getContext().getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        if (audioManager != null) {
            d = ((double) audioManager.getStreamVolume(3)) / ((double) audioManager.getStreamMaxVolume(3));
        } else {
            d = 0.0d;
        }
        long currentTimeMillis = System.currentTimeMillis();
        zza builder2 = com.google.ads.interactivemedia.v3.impl.data.zzb.builder();
        builder2.queryId(str);
        builder2.eventId(str2);
        builder2.appState(str3);
        builder2.nativeTime(currentTimeMillis);
        builder2.nativeVolume(d);
        builder2.nativeViewHidden(z);
        builder2.nativeViewBounds(zzk);
        builder2.nativeViewVisibleBounds(zzk2);
        return builder2.build();
    }

    public final void zzf(String str, String str2) {
        this.zza.zzn(new JavaScriptMessage(JavaScriptMessage.MsgChannel.activityMonitor, JavaScriptMessage.MsgType.viewability, this.zzb, zzc(str, str2, "")));
    }

    public final void zzg() {
        Application zzb2;
        if (this.zzf && (zzb2 = zzel.zzb(this.zzc.getContext())) != null) {
            zza zza2 = new zza(this);
            this.zzd = zza2;
            zzb2.registerActivityLifecycleCallbacks(zza2);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzh(boolean z) {
        this.zzf = z;
    }

    public final void zzi() {
        zza zza2;
        Application zzb2 = zzel.zzb(this.zzc.getContext());
        if (zzb2 != null && (zza2 = this.zzd) != null) {
            zzb2.unregisterActivityLifecycleCallbacks(zza2);
        }
    }
}

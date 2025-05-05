package com.google.android.gms.internal.pal;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzcy extends ConnectivityManager.NetworkCallback {
    final /* synthetic */ zzcz zza;

    zzcy(zzcz zzcz) {
        this.zza = zzcz;
    }

    public final void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        synchronized (zzcz.class) {
            this.zza.zza = networkCapabilities;
        }
    }

    public final void onLost(Network network) {
        synchronized (zzcz.class) {
            this.zza.zza = null;
        }
    }
}

package com.google.android.recaptcha.internal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Build;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzbg {
    public zzbg() {
        new ConcurrentHashMap();
        zzb();
    }

    public static final Set zza(Context context) {
        try {
            Set linkedHashSet = new LinkedHashSet();
            Object systemService = context.getSystemService("connectivity");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
            ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities != null && networkCapabilities.hasTransport(1)) {
                linkedHashSet.add(zzqk.TRANSPORT_WIFI);
            }
            if (networkCapabilities != null && networkCapabilities.hasTransport(0)) {
                linkedHashSet.add(zzqk.TRANSPORT_CELLULAR);
            }
            if (networkCapabilities != null && networkCapabilities.hasTransport(4)) {
                linkedHashSet.add(zzqk.TRANSPORT_VPN);
            }
            if (networkCapabilities != null && networkCapabilities.hasTransport(3)) {
                linkedHashSet.add(zzqk.TRANSPORT_ETHERNET);
            }
            if (networkCapabilities != null && networkCapabilities.hasCapability(16)) {
                linkedHashSet.add(zzqk.NET_CAPABILITY_VALIDATED);
            }
            return linkedHashSet;
        } catch (Exception unused) {
            return SetsKt.emptySet();
        }
    }

    private static final Map zzb() {
        Map mutableMapOf = MapsKt.mutableMapOf(TuplesKt.to(0, zzqk.NET_CAPABILITY_MMS), TuplesKt.to(1, zzqk.NET_CAPABILITY_SUPL), TuplesKt.to(2, zzqk.NET_CAPABILITY_DUN), TuplesKt.to(3, zzqk.NET_CAPABILITY_FOTA), TuplesKt.to(4, zzqk.NET_CAPABILITY_IMS), TuplesKt.to(5, zzqk.NET_CAPABILITY_CBS), TuplesKt.to(6, zzqk.NET_CAPABILITY_WIFI_P2P), TuplesKt.to(7, zzqk.NET_CAPABILITY_IA), TuplesKt.to(8, zzqk.NET_CAPABILITY_RCS), TuplesKt.to(9, zzqk.NET_CAPABILITY_XCAP), TuplesKt.to(10, zzqk.NET_CAPABILITY_EIMS), TuplesKt.to(11, zzqk.NET_CAPABILITY_NOT_METERED), TuplesKt.to(12, zzqk.NET_CAPABILITY_INTERNET), TuplesKt.to(13, zzqk.NET_CAPABILITY_NOT_RESTRICTED), TuplesKt.to(14, zzqk.NET_CAPABILITY_TRUSTED), TuplesKt.to(15, zzqk.NET_CAPABILITY_NOT_VPN));
        mutableMapOf.put(17, zzqk.NET_CAPABILITY_CAPTIVE_PORTAL);
        mutableMapOf.put(16, zzqk.NET_CAPABILITY_VALIDATED);
        if (Build.VERSION.SDK_INT >= 28) {
            mutableMapOf.put(18, zzqk.NET_CAPABILITY_NOT_ROAMING);
            mutableMapOf.put(19, zzqk.NET_CAPABILITY_FOREGROUND);
            mutableMapOf.put(20, zzqk.NET_CAPABILITY_NOT_CONGESTED);
            mutableMapOf.put(21, zzqk.NET_CAPABILITY_NOT_SUSPENDED);
        }
        if (Build.VERSION.SDK_INT >= 29) {
            mutableMapOf.put(23, zzqk.NET_CAPABILITY_MCX);
        }
        if (Build.VERSION.SDK_INT >= 30) {
            mutableMapOf.put(25, zzqk.NET_CAPABILITY_TEMPORARILY_NOT_METERED);
        }
        if (Build.VERSION.SDK_INT >= 31) {
            mutableMapOf.put(32, zzqk.NET_CAPABILITY_HEAD_UNIT);
            mutableMapOf.put(29, zzqk.NET_CAPABILITY_ENTERPRISE);
        }
        if (Build.VERSION.SDK_INT >= 33) {
            mutableMapOf.put(35, zzqk.NET_CAPABILITY_PRIORITIZE_BANDWIDTH);
            mutableMapOf.put(34, zzqk.NET_CAPABILITY_PRIORITIZE_LATENCY);
            mutableMapOf.put(33, zzqk.NET_CAPABILITY_MMTEL);
        }
        return mutableMapOf;
    }
}

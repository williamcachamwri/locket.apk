package com.adsbynimbus.request;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import androidx.core.content.ContextCompat;
import com.adsbynimbus.internal.Components;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\t\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028G¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0019\u0010\u0000\u001a\u00020\u0001*\u00020\u00058Á\u0002X\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0006\"\u0019\u0010\u0000\u001a\u00020\u0001*\u00020\u00078Á\u0002X\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\b\"\u0019\u0010\u0000\u001a\u00020\u0001*\u00020\t8À\u0002X\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\n\"\u0019\u0010\u0000\u001a\u00020\u0001*\u00020\u000b8Á\u0002X\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\f\"\u0019\u0010\r\u001a\u00020\u0005*\u00020\u00028À\u0002X\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f\"\u0019\u0010\u0010\u001a\u00020\u0011*\u00020\u00028À\u0002X\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013\"\u0019\u0010\u0014\u001a\u00020\u0011*\u00020\u00028À\u0002X\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0013\"\u0019\u0010\u0016\u001a\u00020\u0017*\u00020\u000b8Á\u0002X\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019\"\u0019\u0010\u001a\u001a\u00020\u000b*\u00020\u00028À\u0002X\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c\"\u0019\u0010\u001d\u001a\u00020\u0011*\u00020\u00018À\u0002X\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f¨\u0006 "}, d2 = {"connectionType", "", "Landroid/content/Context;", "getConnectionType", "(Landroid/content/Context;)B", "Landroid/net/ConnectivityManager;", "(Landroid/net/ConnectivityManager;)B", "Landroid/net/NetworkCapabilities;", "(Landroid/net/NetworkCapabilities;)B", "Landroid/net/NetworkInfo;", "(Landroid/net/NetworkInfo;)B", "Landroid/telephony/TelephonyManager;", "(Landroid/telephony/TelephonyManager;)B", "connectivityManager", "getConnectivityManager", "(Landroid/content/Context;)Landroid/net/ConnectivityManager;", "hasNetworkPermission", "", "getHasNetworkPermission", "(Landroid/content/Context;)Z", "hasPhonePermission", "getHasPhonePermission", "networkTypeCompat", "", "getNetworkTypeCompat", "(Landroid/telephony/TelephonyManager;)I", "telephonyManager", "getTelephonyManager", "(Landroid/content/Context;)Landroid/telephony/TelephonyManager;", "unknown", "getUnknown", "(B)Z", "request_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: ConnectionType.kt */
public final class ConnectionTypeKt {
    public static final boolean getUnknown(byte b) {
        return ArraysKt.contains(new byte[]{0, 3}, b);
    }

    public static final byte getConnectionType(ConnectivityManager connectivityManager) {
        Intrinsics.checkNotNullParameter(connectivityManager, "<this>");
        byte b = 6;
        Byte b2 = null;
        byte b3 = 2;
        if (Components.isApi23()) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities != null) {
                if (networkCapabilities.hasTransport(3)) {
                    b3 = 1;
                } else if (!networkCapabilities.hasTransport(1)) {
                    if (networkCapabilities.hasTransport(0)) {
                        int linkDownstreamBandwidthKbps = networkCapabilities.getLinkDownstreamBandwidthKbps();
                        if (linkDownstreamBandwidthKbps < 1000) {
                            b = 3;
                        } else if (linkDownstreamBandwidthKbps < 10000) {
                            b = 5;
                        }
                        b3 = b;
                    } else {
                        b3 = 0;
                    }
                }
                b2 = Byte.valueOf(b3);
            }
        } else {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                int type = activeNetworkInfo.getType();
                if (type != 0) {
                    if (type == 1) {
                        b = 2;
                    } else if (!(type == 2 || type == 3 || type == 4)) {
                        if (type != 17) {
                            switch (type) {
                                case 6:
                                    break;
                                case 9:
                                    b = 1;
                                    break;
                            }
                        }
                        b = 0;
                    }
                    b2 = Byte.valueOf(b);
                }
                b = 3;
                b2 = Byte.valueOf(b);
            }
        }
        if (b2 != null) {
            return b2.byteValue();
        }
        return 0;
    }

    public static final byte getConnectionType(NetworkCapabilities networkCapabilities) {
        Intrinsics.checkNotNullParameter(networkCapabilities, "<this>");
        byte b = 3;
        if (networkCapabilities.hasTransport(3)) {
            return 1;
        }
        if (networkCapabilities.hasTransport(1)) {
            return 2;
        }
        if (!networkCapabilities.hasTransport(0)) {
            return 0;
        }
        int linkDownstreamBandwidthKbps = networkCapabilities.getLinkDownstreamBandwidthKbps();
        if (linkDownstreamBandwidthKbps >= 1000) {
            b = linkDownstreamBandwidthKbps < 10000 ? (byte) 5 : 6;
        }
        return b;
    }

    public static final byte getConnectionType(NetworkInfo networkInfo) {
        Intrinsics.checkNotNullParameter(networkInfo, "<this>");
        int type = networkInfo.getType();
        if (type == 0) {
            return 3;
        }
        byte b = 2;
        if (type != 1) {
            if (type == 2 || type == 3 || type == 4) {
                return 3;
            }
            b = 0;
            if (type != 17) {
                switch (type) {
                    case 6:
                        return 6;
                    case 9:
                        return 1;
                }
            }
        }
        return b;
    }

    public static final int getNetworkTypeCompat(TelephonyManager telephonyManager) {
        Intrinsics.checkNotNullParameter(telephonyManager, "<this>");
        return Components.isApi29() ? telephonyManager.getDataNetworkType() : telephonyManager.getNetworkType();
    }

    public static final ConnectivityManager getConnectivityManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Object systemService = context.getSystemService("connectivity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        return (ConnectivityManager) systemService;
    }

    public static final TelephonyManager getTelephonyManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Object systemService = context.getSystemService("phone");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.telephony.TelephonyManager");
        return (TelephonyManager) systemService;
    }

    public static final boolean getHasNetworkPermission(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_NETWORK_STATE") == 0;
    }

    public static final boolean getHasPhonePermission(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return ContextCompat.checkSelfPermission(context, "android.permission.READ_PHONE_STATE") == 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00c6, code lost:
        if ((androidx.core.content.ContextCompat.checkSelfPermission(r14, "android.permission.READ_PHONE_STATE") == 0) != false) goto L_0x00ca;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:?, code lost:
        return 6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:?, code lost:
        return 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:?, code lost:
        return 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:?, code lost:
        return 3;
     */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00a1  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00cc  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00cf  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00d4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final byte getConnectionType(android.content.Context r14) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
            java.lang.String r0 = "android.permission.ACCESS_NETWORK_STATE"
            int r0 = androidx.core.content.ContextCompat.checkSelfPermission(r14, r0)
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x0011
            r0 = r1
            goto L_0x0012
        L_0x0011:
            r0 = r2
        L_0x0012:
            java.lang.String r3 = "null cannot be cast to non-null type android.telephony.TelephonyManager"
            java.lang.String r4 = "phone"
            java.lang.String r5 = "android.permission.READ_PHONE_STATE"
            r6 = 5
            r7 = 4
            r8 = 6
            r9 = 2
            r10 = 3
            if (r0 == 0) goto L_0x00fa
            java.lang.String r0 = "connectivity"
            java.lang.Object r0 = r14.getSystemService(r0)
            java.lang.String r11 = "null cannot be cast to non-null type android.net.ConnectivityManager"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r11)
            android.net.ConnectivityManager r0 = (android.net.ConnectivityManager) r0
            boolean r11 = com.adsbynimbus.internal.Components.isApi23()
            r12 = 0
            if (r11 == 0) goto L_0x0070
            android.net.Network r11 = r0.getActiveNetwork()
            android.net.NetworkCapabilities r0 = r0.getNetworkCapabilities(r11)
            if (r0 == 0) goto L_0x009e
            java.lang.String r11 = "getNetworkCapabilities(activeNetwork)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r11)
            boolean r11 = r0.hasTransport(r10)
            if (r11 == 0) goto L_0x004a
            r0 = r1
            goto L_0x006b
        L_0x004a:
            boolean r11 = r0.hasTransport(r1)
            if (r11 == 0) goto L_0x0052
            r0 = r9
            goto L_0x006b
        L_0x0052:
            boolean r11 = r0.hasTransport(r2)
            if (r11 == 0) goto L_0x006a
            int r0 = r0.getLinkDownstreamBandwidthKbps()
            r11 = 1000(0x3e8, float:1.401E-42)
            if (r0 >= r11) goto L_0x0062
            r0 = r10
            goto L_0x006b
        L_0x0062:
            r11 = 10000(0x2710, float:1.4013E-41)
            if (r0 >= r11) goto L_0x0068
            r0 = r6
            goto L_0x006b
        L_0x0068:
            r0 = r8
            goto L_0x006b
        L_0x006a:
            r0 = r2
        L_0x006b:
            java.lang.Byte r0 = java.lang.Byte.valueOf(r0)
            goto L_0x009f
        L_0x0070:
            android.net.NetworkInfo r0 = r0.getActiveNetworkInfo()
            if (r0 == 0) goto L_0x009e
            java.lang.String r11 = "activeNetworkInfo"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r11)
            int r0 = r0.getType()
            if (r0 == 0) goto L_0x0098
            if (r0 == r1) goto L_0x0096
            if (r0 == r9) goto L_0x0098
            if (r0 == r10) goto L_0x0098
            if (r0 == r7) goto L_0x0098
            r11 = 17
            if (r0 == r11) goto L_0x0090
            switch(r0) {
                case 6: goto L_0x0094;
                case 7: goto L_0x0090;
                case 8: goto L_0x0090;
                case 9: goto L_0x0092;
                default: goto L_0x0090;
            }
        L_0x0090:
            r0 = r2
            goto L_0x0099
        L_0x0092:
            r0 = r1
            goto L_0x0099
        L_0x0094:
            r0 = r8
            goto L_0x0099
        L_0x0096:
            r0 = r9
            goto L_0x0099
        L_0x0098:
            r0 = r10
        L_0x0099:
            java.lang.Byte r0 = java.lang.Byte.valueOf(r0)
            goto L_0x009f
        L_0x009e:
            r0 = r12
        L_0x009f:
            if (r0 == 0) goto L_0x00a6
            byte r0 = r0.byteValue()
            goto L_0x00a7
        L_0x00a6:
            r0 = r2
        L_0x00a7:
            java.lang.Byte r0 = java.lang.Byte.valueOf(r0)
            r11 = r0
            java.lang.Number r11 = (java.lang.Number) r11
            byte r11 = r11.byteValue()
            byte[] r13 = new byte[r9]
            r13 = {0, 3} // fill-array
            boolean r11 = kotlin.collections.ArraysKt.contains((byte[]) r13, (byte) r11)
            if (r11 == 0) goto L_0x00c9
            int r5 = androidx.core.content.ContextCompat.checkSelfPermission(r14, r5)
            if (r5 != 0) goto L_0x00c5
            r5 = r1
            goto L_0x00c6
        L_0x00c5:
            r5 = r2
        L_0x00c6:
            if (r5 == 0) goto L_0x00c9
            goto L_0x00ca
        L_0x00c9:
            r1 = r2
        L_0x00ca:
            if (r1 != 0) goto L_0x00cd
            r12 = r0
        L_0x00cd:
            if (r12 == 0) goto L_0x00d4
            byte r2 = r12.byteValue()
            goto L_0x0120
        L_0x00d4:
            java.lang.Object r14 = r14.getSystemService(r4)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r14, r3)
            android.telephony.TelephonyManager r14 = (android.telephony.TelephonyManager) r14
            boolean r0 = com.adsbynimbus.internal.Components.isApi29()
            if (r0 == 0) goto L_0x00e8
            int r14 = r14.getDataNetworkType()
            goto L_0x00ec
        L_0x00e8:
            int r14 = r14.getNetworkType()
        L_0x00ec:
            switch(r14) {
                case 0: goto L_0x00f8;
                case 1: goto L_0x00f6;
                case 2: goto L_0x00f6;
                case 3: goto L_0x00f4;
                case 4: goto L_0x00f6;
                case 5: goto L_0x00f4;
                case 6: goto L_0x00f4;
                case 7: goto L_0x00f6;
                case 8: goto L_0x00f4;
                case 9: goto L_0x00f4;
                case 10: goto L_0x00f4;
                case 11: goto L_0x00f6;
                case 12: goto L_0x00f4;
                case 13: goto L_0x00f2;
                case 14: goto L_0x00f4;
                case 15: goto L_0x00f4;
                case 16: goto L_0x00f4;
                case 17: goto L_0x00f4;
                case 18: goto L_0x00f0;
                case 19: goto L_0x00ef;
                case 20: goto L_0x00f0;
                default: goto L_0x00ef;
            }
        L_0x00ef:
            goto L_0x00f8
        L_0x00f0:
            r2 = r9
            goto L_0x0120
        L_0x00f2:
            r2 = r8
            goto L_0x0120
        L_0x00f4:
            r2 = r6
            goto L_0x0120
        L_0x00f6:
            r2 = r7
            goto L_0x0120
        L_0x00f8:
            r2 = r10
            goto L_0x0120
        L_0x00fa:
            int r0 = androidx.core.content.ContextCompat.checkSelfPermission(r14, r5)
            if (r0 != 0) goto L_0x0101
            goto L_0x0102
        L_0x0101:
            r1 = r2
        L_0x0102:
            if (r1 == 0) goto L_0x0120
            java.lang.Object r14 = r14.getSystemService(r4)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r14, r3)
            android.telephony.TelephonyManager r14 = (android.telephony.TelephonyManager) r14
            boolean r0 = com.adsbynimbus.internal.Components.isApi29()
            if (r0 == 0) goto L_0x0118
            int r14 = r14.getDataNetworkType()
            goto L_0x011c
        L_0x0118:
            int r14 = r14.getNetworkType()
        L_0x011c:
            switch(r14) {
                case 0: goto L_0x00f8;
                case 1: goto L_0x00f6;
                case 2: goto L_0x00f6;
                case 3: goto L_0x00f4;
                case 4: goto L_0x00f6;
                case 5: goto L_0x00f4;
                case 6: goto L_0x00f4;
                case 7: goto L_0x00f6;
                case 8: goto L_0x00f4;
                case 9: goto L_0x00f4;
                case 10: goto L_0x00f4;
                case 11: goto L_0x00f6;
                case 12: goto L_0x00f4;
                case 13: goto L_0x00f2;
                case 14: goto L_0x00f4;
                case 15: goto L_0x00f4;
                case 16: goto L_0x00f4;
                case 17: goto L_0x00f4;
                case 18: goto L_0x00f0;
                case 19: goto L_0x011f;
                case 20: goto L_0x00f0;
                default: goto L_0x011f;
            }
        L_0x011f:
            goto L_0x00f8
        L_0x0120:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.request.ConnectionTypeKt.getConnectionType(android.content.Context):byte");
    }

    public static final byte getConnectionType(TelephonyManager telephonyManager) {
        Intrinsics.checkNotNullParameter(telephonyManager, "<this>");
        switch (Components.isApi29() ? telephonyManager.getDataNetworkType() : telephonyManager.getNetworkType()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return 4;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
            case 16:
            case 17:
                return 5;
            case 13:
                return 6;
            case 18:
            case 20:
                return 2;
            default:
                return 3;
        }
    }
}

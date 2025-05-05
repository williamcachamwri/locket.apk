package com.facebook.react.modules.systeminfo;

import android.content.Context;
import android.os.Build;
import com.facebook.react.R;
import java.util.Locale;

public class AndroidInfoHelpers {
    public static final String DEVICE_LOCALHOST = "localhost";
    public static final String EMULATOR_LOCALHOST = "10.0.2.2";
    public static final String GENYMOTION_LOCALHOST = "10.0.3.2";
    public static final String METRO_HOST_PROP_NAME = "metro.host";
    private static final String TAG = "AndroidInfoHelpers";
    private static String metroHostPropValue;

    private static boolean isRunningOnGenymotion() {
        return Build.FINGERPRINT.contains("vbox");
    }

    private static boolean isRunningOnStockEmulator() {
        return Build.FINGERPRINT.contains("generic") || Build.FINGERPRINT.startsWith("google/sdk_gphone");
    }

    public static String getServerHost(Integer num) {
        return getServerIpAddress(num.intValue());
    }

    public static String getServerHost(Context context) {
        return getServerIpAddress(getDevServerPort(context).intValue());
    }

    public static String getAdbReverseTcpCommand(Integer num) {
        return "adb reverse tcp:" + num + " tcp:" + num;
    }

    public static String getAdbReverseTcpCommand(Context context) {
        return getAdbReverseTcpCommand(getDevServerPort(context));
    }

    public static String getInspectorProxyHost(Context context) {
        return getServerIpAddress(getInspectorProxyPort(context).intValue());
    }

    public static String getFriendlyDeviceName() {
        if (isRunningOnGenymotion()) {
            return Build.MODEL;
        }
        return Build.MODEL + " - " + Build.VERSION.RELEASE + " - API " + Build.VERSION.SDK_INT;
    }

    private static Integer getDevServerPort(Context context) {
        return Integer.valueOf(context.getResources().getInteger(R.integer.react_native_dev_server_port));
    }

    private static Integer getInspectorProxyPort(Context context) {
        return Integer.valueOf(context.getResources().getInteger(R.integer.react_native_dev_server_port));
    }

    private static String getServerIpAddress(int i) {
        String metroHostPropValue2 = getMetroHostPropValue();
        if (metroHostPropValue2.equals("")) {
            if (isRunningOnGenymotion()) {
                metroHostPropValue2 = GENYMOTION_LOCALHOST;
            } else {
                metroHostPropValue2 = isRunningOnStockEmulator() ? EMULATOR_LOCALHOST : DEVICE_LOCALHOST;
            }
        }
        return String.format(Locale.US, "%s:%d", new Object[]{metroHostPropValue2, Integer.valueOf(i)});
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0042, code lost:
        if (r2 == null) goto L_0x0070;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r2.destroy();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x006d, code lost:
        if (r2 == null) goto L_0x0070;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        r1 = metroHostPropValue;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0073, code lost:
        return r1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x006a A[SYNTHETIC, Splitter:B:36:0x006a] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0077 A[SYNTHETIC, Splitter:B:46:0x0077] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x007c A[SYNTHETIC, Splitter:B:50:0x007c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static synchronized java.lang.String getMetroHostPropValue() {
        /*
            java.lang.Class<com.facebook.react.modules.systeminfo.AndroidInfoHelpers> r0 = com.facebook.react.modules.systeminfo.AndroidInfoHelpers.class
            monitor-enter(r0)
            java.lang.String r1 = metroHostPropValue     // Catch:{ all -> 0x0080 }
            if (r1 == 0) goto L_0x0009
            monitor-exit(r0)
            return r1
        L_0x0009:
            r1 = 0
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch:{ Exception -> 0x0059, all -> 0x0054 }
            r3 = 2
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ Exception -> 0x0059, all -> 0x0054 }
            java.lang.String r4 = "/system/bin/getprop"
            r5 = 0
            r3[r5] = r4     // Catch:{ Exception -> 0x0059, all -> 0x0054 }
            java.lang.String r4 = "metro.host"
            r5 = 1
            r3[r5] = r4     // Catch:{ Exception -> 0x0059, all -> 0x0054 }
            java.lang.Process r2 = r2.exec(r3)     // Catch:{ Exception -> 0x0059, all -> 0x0054 }
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ Exception -> 0x004f, all -> 0x004a }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x004f, all -> 0x004a }
            java.io.InputStream r5 = r2.getInputStream()     // Catch:{ Exception -> 0x004f, all -> 0x004a }
            java.lang.String r6 = "UTF-8"
            java.nio.charset.Charset r6 = java.nio.charset.Charset.forName(r6)     // Catch:{ Exception -> 0x004f, all -> 0x004a }
            r4.<init>(r5, r6)     // Catch:{ Exception -> 0x004f, all -> 0x004a }
            r3.<init>(r4)     // Catch:{ Exception -> 0x004f, all -> 0x004a }
            java.lang.String r1 = ""
        L_0x0035:
            java.lang.String r4 = r3.readLine()     // Catch:{ Exception -> 0x0048 }
            if (r4 == 0) goto L_0x003d
            r1 = r4
            goto L_0x0035
        L_0x003d:
            metroHostPropValue = r1     // Catch:{ Exception -> 0x0048 }
            r3.close()     // Catch:{ Exception -> 0x0042 }
        L_0x0042:
            if (r2 == 0) goto L_0x0070
        L_0x0044:
            r2.destroy()     // Catch:{ all -> 0x0080 }
            goto L_0x0070
        L_0x0048:
            r1 = move-exception
            goto L_0x005d
        L_0x004a:
            r3 = move-exception
            r7 = r3
            r3 = r1
            r1 = r7
            goto L_0x0075
        L_0x004f:
            r3 = move-exception
            r7 = r3
            r3 = r1
            r1 = r7
            goto L_0x005d
        L_0x0054:
            r2 = move-exception
            r3 = r1
            r1 = r2
            r2 = r3
            goto L_0x0075
        L_0x0059:
            r2 = move-exception
            r3 = r1
            r1 = r2
            r2 = r3
        L_0x005d:
            java.lang.String r4 = TAG     // Catch:{ all -> 0x0074 }
            java.lang.String r5 = "Failed to query for metro.host prop:"
            com.facebook.common.logging.FLog.w((java.lang.String) r4, (java.lang.String) r5, (java.lang.Throwable) r1)     // Catch:{ all -> 0x0074 }
            java.lang.String r1 = ""
            metroHostPropValue = r1     // Catch:{ all -> 0x0074 }
            if (r3 == 0) goto L_0x006d
            r3.close()     // Catch:{ Exception -> 0x006d }
        L_0x006d:
            if (r2 == 0) goto L_0x0070
            goto L_0x0044
        L_0x0070:
            java.lang.String r1 = metroHostPropValue     // Catch:{ all -> 0x0080 }
            monitor-exit(r0)
            return r1
        L_0x0074:
            r1 = move-exception
        L_0x0075:
            if (r3 == 0) goto L_0x007a
            r3.close()     // Catch:{ Exception -> 0x007a }
        L_0x007a:
            if (r2 == 0) goto L_0x007f
            r2.destroy()     // Catch:{ all -> 0x0080 }
        L_0x007f:
            throw r1     // Catch:{ all -> 0x0080 }
        L_0x0080:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.systeminfo.AndroidInfoHelpers.getMetroHostPropValue():java.lang.String");
    }
}

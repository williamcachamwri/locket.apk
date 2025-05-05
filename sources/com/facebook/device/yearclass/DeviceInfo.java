package com.facebook.device.yearclass;

import android.app.ActivityManager;
import android.content.Context;
import io.sentry.instrumentation.file.SentryFileInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DeviceInfo {
    private static final FileFilter CPU_FILTER = new FileFilter() {
        public boolean accept(File file) {
            String name = file.getName();
            if (!name.startsWith("cpu")) {
                return false;
            }
            for (int i = 3; i < name.length(); i++) {
                if (!Character.isDigit(name.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
    };
    public static final int DEVICEINFO_UNKNOWN = -1;

    public static int getNumberOfCPUCores() {
        try {
            int coresFromFileInfo = getCoresFromFileInfo("/sys/devices/system/cpu/possible");
            if (coresFromFileInfo == -1) {
                coresFromFileInfo = getCoresFromFileInfo("/sys/devices/system/cpu/present");
            }
            return coresFromFileInfo == -1 ? getCoresFromCPUFileList() : coresFromFileInfo;
        } catch (NullPointerException | SecurityException unused) {
            return -1;
        }
    }

    private static int getCoresFromFileInfo(String str) {
        InputStream inputStream = null;
        try {
            FileInputStream create = SentryFileInputStream.Factory.create(new FileInputStream(str), str);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(create));
            String readLine = bufferedReader.readLine();
            bufferedReader.close();
            int coresFromFileString = getCoresFromFileString(readLine);
            if (create != null) {
                try {
                    create.close();
                } catch (IOException unused) {
                }
            }
            return coresFromFileString;
        } catch (IOException unused2) {
            if (inputStream == null) {
                return -1;
            }
            try {
                inputStream.close();
                return -1;
            } catch (IOException unused3) {
                return -1;
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused4) {
                }
            }
            throw th;
        }
    }

    static int getCoresFromFileString(String str) {
        if (str == null || !str.matches("0-[\\d]+$")) {
            return -1;
        }
        return Integer.valueOf(str.substring(2)).intValue() + 1;
    }

    private static int getCoresFromCPUFileList() {
        return new File("/sys/devices/system/cpu/").listFiles(CPU_FILTER).length;
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x0069 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getCPUMaxFreqKHz() {
        /*
            r0 = 0
            r1 = -1
            r2 = r0
            r3 = r1
        L_0x0004:
            int r4 = getNumberOfCPUCores()     // Catch:{ IOException -> 0x0097 }
            if (r2 >= r4) goto L_0x0075
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0097 }
            r4.<init>()     // Catch:{ IOException -> 0x0097 }
            java.lang.String r5 = "/sys/devices/system/cpu/cpu"
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ IOException -> 0x0097 }
            java.lang.StringBuilder r4 = r4.append(r2)     // Catch:{ IOException -> 0x0097 }
            java.lang.String r5 = "/cpufreq/cpuinfo_max_freq"
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ IOException -> 0x0097 }
            java.lang.String r4 = r4.toString()     // Catch:{ IOException -> 0x0097 }
            java.io.File r5 = new java.io.File     // Catch:{ IOException -> 0x0097 }
            r5.<init>(r4)     // Catch:{ IOException -> 0x0097 }
            boolean r4 = r5.exists()     // Catch:{ IOException -> 0x0097 }
            if (r4 == 0) goto L_0x0072
            boolean r4 = r5.canRead()     // Catch:{ IOException -> 0x0097 }
            if (r4 == 0) goto L_0x0072
            r4 = 128(0x80, float:1.794E-43)
            byte[] r6 = new byte[r4]     // Catch:{ IOException -> 0x0097 }
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0097 }
            r7.<init>(r5)     // Catch:{ IOException -> 0x0097 }
            java.io.FileInputStream r5 = io.sentry.instrumentation.file.SentryFileInputStream.Factory.create((java.io.FileInputStream) r7, (java.io.File) r5)     // Catch:{ IOException -> 0x0097 }
            r5.read(r6)     // Catch:{ NumberFormatException -> 0x0069, all -> 0x006d }
            r7 = r0
        L_0x0045:
            byte r8 = r6[r7]     // Catch:{ NumberFormatException -> 0x0069, all -> 0x006d }
            boolean r8 = java.lang.Character.isDigit(r8)     // Catch:{ NumberFormatException -> 0x0069, all -> 0x006d }
            if (r8 == 0) goto L_0x0052
            if (r7 >= r4) goto L_0x0052
            int r7 = r7 + 1
            goto L_0x0045
        L_0x0052:
            java.lang.String r4 = new java.lang.String     // Catch:{ NumberFormatException -> 0x0069, all -> 0x006d }
            r4.<init>(r6, r0, r7)     // Catch:{ NumberFormatException -> 0x0069, all -> 0x006d }
            int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ NumberFormatException -> 0x0069, all -> 0x006d }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ NumberFormatException -> 0x0069, all -> 0x006d }
            int r6 = r4.intValue()     // Catch:{ NumberFormatException -> 0x0069, all -> 0x006d }
            if (r6 <= r3) goto L_0x0069
            int r3 = r4.intValue()     // Catch:{ NumberFormatException -> 0x0069, all -> 0x006d }
        L_0x0069:
            r5.close()     // Catch:{ IOException -> 0x0097 }
            goto L_0x0072
        L_0x006d:
            r0 = move-exception
            r5.close()     // Catch:{ IOException -> 0x0097 }
            throw r0     // Catch:{ IOException -> 0x0097 }
        L_0x0072:
            int r2 = r2 + 1
            goto L_0x0004
        L_0x0075:
            if (r3 != r1) goto L_0x0096
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0097 }
            java.lang.String r2 = "/proc/cpuinfo"
            r0.<init>(r2)     // Catch:{ IOException -> 0x0097 }
            java.io.FileInputStream r0 = io.sentry.instrumentation.file.SentryFileInputStream.Factory.create((java.io.FileInputStream) r0, (java.lang.String) r2)     // Catch:{ IOException -> 0x0097 }
            java.lang.String r2 = "cpu MHz"
            int r2 = parseFileForValue(r2, r0)     // Catch:{ all -> 0x0091 }
            int r2 = r2 * 1000
            if (r2 <= r3) goto L_0x008d
            r3 = r2
        L_0x008d:
            r0.close()     // Catch:{ IOException -> 0x0097 }
            goto L_0x0096
        L_0x0091:
            r2 = move-exception
            r0.close()     // Catch:{ IOException -> 0x0097 }
            throw r2     // Catch:{ IOException -> 0x0097 }
        L_0x0096:
            r1 = r3
        L_0x0097:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.device.yearclass.DeviceInfo.getCPUMaxFreqKHz():int");
    }

    public static long getTotalMemory(Context context) {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        return memoryInfo.totalMem;
    }

    private static int parseFileForValue(String str, FileInputStream fileInputStream) {
        byte[] bArr = new byte[1024];
        try {
            int read = fileInputStream.read(bArr);
            int i = 0;
            while (i < read) {
                byte b = bArr[i];
                if (b == 10 || i == 0) {
                    if (b == 10) {
                        i++;
                    }
                    int i2 = i;
                    while (true) {
                        if (i2 >= read) {
                            continue;
                            break;
                        }
                        int i3 = i2 - i;
                        if (bArr[i2] != str.charAt(i3)) {
                            break;
                        } else if (i3 == str.length() - 1) {
                            return extractValue(bArr, i2);
                        } else {
                            i2++;
                        }
                    }
                }
                i++;
            }
            return -1;
        } catch (IOException | NumberFormatException unused) {
            return -1;
        }
    }

    private static int extractValue(byte[] bArr, int i) {
        byte b;
        while (i < bArr.length && (b = bArr[i]) != 10) {
            if (Character.isDigit(b)) {
                int i2 = i + 1;
                while (i2 < bArr.length && Character.isDigit(bArr[i2])) {
                    i2++;
                }
                return Integer.parseInt(new String(bArr, 0, i, i2 - i));
            }
            i++;
        }
        return -1;
    }
}

package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import java.util.Arrays;
import java.util.HashSet;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzol {
    public static boolean zza(int i) {
        int i2 = i - 1;
        return i2 == 2 || i2 == 4 || i2 == 5 || i2 == 6 || i2 == 7;
    }

    /* JADX WARNING: Removed duplicated region for block: B:51:0x00c9  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0123  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x013f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final int zzb(android.content.Context r14, com.google.ads.interactivemedia.v3.internal.zznt r15) {
        /*
            java.io.File r0 = new java.io.File
            java.io.File r1 = new java.io.File
            android.content.pm.ApplicationInfo r2 = r14.getApplicationInfo()
            java.lang.String r2 = r2.dataDir
            r1.<init>(r2)
            java.lang.String r2 = "lib"
            r0.<init>(r1, r2)
            boolean r1 = r0.exists()
            r2 = 5017(0x1399, float:7.03E-42)
            r3 = 8
            r4 = 7
            r5 = 6
            r6 = 1000(0x3e8, float:1.401E-42)
            r7 = 0
            r8 = 5
            r9 = 3
            r10 = 1
            if (r1 != 0) goto L_0x002c
            java.lang.String r0 = "No lib/"
            r15.zzb(r2, r0)
        L_0x0029:
            r0 = r6
            goto L_0x00c7
        L_0x002c:
            com.google.ads.interactivemedia.v3.internal.zzta r1 = new com.google.ads.interactivemedia.v3.internal.zzta
            java.lang.String r11 = ".*\\.so$"
            r12 = 2
            java.util.regex.Pattern r11 = java.util.regex.Pattern.compile(r11, r12)
            r1.<init>(r11)
            java.io.File[] r0 = r0.listFiles(r1)
            if (r0 == 0) goto L_0x00c0
            int r1 = r0.length
            if (r1 != 0) goto L_0x0043
            goto L_0x00c0
        L_0x0043:
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ IOException -> 0x00b6 }
            r2 = 0
            r0 = r0[r2]     // Catch:{ IOException -> 0x00b6 }
            r1.<init>(r0)     // Catch:{ IOException -> 0x00b6 }
            java.io.FileInputStream r0 = io.sentry.instrumentation.file.SentryFileInputStream.Factory.create((java.io.FileInputStream) r1, (java.io.File) r0)     // Catch:{ IOException -> 0x00b6 }
            r1 = 20
            byte[] r11 = new byte[r1]     // Catch:{ all -> 0x00ac }
            int r13 = r0.read(r11)     // Catch:{ all -> 0x00ac }
            if (r13 != r1) goto L_0x0065
            byte[] r1 = new byte[r12]     // Catch:{ all -> 0x00ac }
            r1 = {0, 0} // fill-array     // Catch:{ all -> 0x00ac }
            byte r13 = r11[r8]     // Catch:{ all -> 0x00ac }
            if (r13 != r12) goto L_0x0069
            zzd(r11, r7, r14, r15)     // Catch:{ all -> 0x00ac }
        L_0x0065:
            r0.close()     // Catch:{ IOException -> 0x00b6 }
            goto L_0x00be
        L_0x0069:
            r12 = 19
            byte r12 = r11[r12]     // Catch:{ all -> 0x00ac }
            r1[r2] = r12     // Catch:{ all -> 0x00ac }
            r2 = 18
            byte r2 = r11[r2]     // Catch:{ all -> 0x00ac }
            r1[r10] = r2     // Catch:{ all -> 0x00ac }
            java.nio.ByteBuffer r1 = java.nio.ByteBuffer.wrap(r1)     // Catch:{ all -> 0x00ac }
            short r1 = r1.getShort()     // Catch:{ all -> 0x00ac }
            if (r1 == r9) goto L_0x00a7
            r2 = 40
            if (r1 == r2) goto L_0x00a2
            r2 = 62
            if (r1 == r2) goto L_0x009d
            r2 = 183(0xb7, float:2.56E-43)
            if (r1 == r2) goto L_0x0098
            r2 = 243(0xf3, float:3.4E-43)
            if (r1 == r2) goto L_0x0093
            zzd(r11, r7, r14, r15)     // Catch:{ all -> 0x00ac }
            goto L_0x0065
        L_0x0093:
            r0.close()     // Catch:{ IOException -> 0x00b6 }
            r0 = r3
            goto L_0x00c7
        L_0x0098:
            r0.close()     // Catch:{ IOException -> 0x00b6 }
            r0 = r5
            goto L_0x00c7
        L_0x009d:
            r0.close()     // Catch:{ IOException -> 0x00b6 }
            r0 = r4
            goto L_0x00c7
        L_0x00a2:
            r0.close()     // Catch:{ IOException -> 0x00b6 }
            r0 = r9
            goto L_0x00c7
        L_0x00a7:
            r0.close()     // Catch:{ IOException -> 0x00b6 }
            r0 = r8
            goto L_0x00c7
        L_0x00ac:
            r1 = move-exception
            r0.close()     // Catch:{ all -> 0x00b1 }
            goto L_0x00b5
        L_0x00b1:
            r0 = move-exception
            r1.addSuppressed(r0)     // Catch:{ IOException -> 0x00b6 }
        L_0x00b5:
            throw r1     // Catch:{ IOException -> 0x00b6 }
        L_0x00b6:
            r0 = move-exception
            java.lang.String r0 = r0.toString()
            zzd(r7, r0, r14, r15)
        L_0x00be:
            r0 = r10
            goto L_0x00c7
        L_0x00c0:
            java.lang.String r0 = "No .so"
            r15.zzb(r2, r0)
            goto L_0x0029
        L_0x00c7:
            if (r0 != r6) goto L_0x0121
            java.lang.String r0 = zzc(r14, r15)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L_0x00da
            java.lang.String r0 = "Empty dev arch"
            zzd(r7, r0, r14, r15)
        L_0x00d8:
            r0 = r10
            goto L_0x0121
        L_0x00da:
            java.lang.String r1 = "i686"
            boolean r1 = r0.equalsIgnoreCase(r1)
            if (r1 != 0) goto L_0x0120
            java.lang.String r1 = "x86"
            boolean r1 = r0.equalsIgnoreCase(r1)
            if (r1 == 0) goto L_0x00eb
            goto L_0x0120
        L_0x00eb:
            java.lang.String r1 = "x86_64"
            boolean r1 = r0.equalsIgnoreCase(r1)
            if (r1 == 0) goto L_0x00f5
            r0 = r4
            goto L_0x0121
        L_0x00f5:
            java.lang.String r1 = "arm64-v8a"
            boolean r1 = r0.equalsIgnoreCase(r1)
            if (r1 == 0) goto L_0x00ff
            r0 = r5
            goto L_0x0121
        L_0x00ff:
            java.lang.String r1 = "armeabi-v7a"
            boolean r1 = r0.equalsIgnoreCase(r1)
            if (r1 != 0) goto L_0x011e
            java.lang.String r1 = "armv71"
            boolean r1 = r0.equalsIgnoreCase(r1)
            if (r1 == 0) goto L_0x0110
            goto L_0x011e
        L_0x0110:
            java.lang.String r1 = "riscv64"
            boolean r1 = r0.equalsIgnoreCase(r1)
            if (r1 == 0) goto L_0x011a
            r0 = r3
            goto L_0x0121
        L_0x011a:
            zzd(r7, r0, r14, r15)
            goto L_0x00d8
        L_0x011e:
            r0 = r9
            goto L_0x0121
        L_0x0120:
            r0 = r8
        L_0x0121:
            if (r0 == r10) goto L_0x013f
            if (r0 == r9) goto L_0x013c
            if (r0 == r8) goto L_0x0139
            if (r0 == r5) goto L_0x0136
            if (r0 == r4) goto L_0x0133
            if (r0 == r3) goto L_0x0130
            java.lang.String r14 = "null"
            goto L_0x0141
        L_0x0130:
            java.lang.String r14 = "RISCV64"
            goto L_0x0141
        L_0x0133:
            java.lang.String r14 = "X86_64"
            goto L_0x0141
        L_0x0136:
            java.lang.String r14 = "ARM64"
            goto L_0x0141
        L_0x0139:
            java.lang.String r14 = "X86"
            goto L_0x0141
        L_0x013c:
            java.lang.String r14 = "ARM7"
            goto L_0x0141
        L_0x013f:
            java.lang.String r14 = "UNSUPPORTED"
        L_0x0141:
            r1 = 5018(0x139a, float:7.032E-42)
            r15.zzb(r1, r14)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.zzol.zzb(android.content.Context, com.google.ads.interactivemedia.v3.internal.zznt):int");
    }

    private static final String zzc(Context context, zznt zznt) {
        HashSet hashSet = new HashSet(Arrays.asList(new String[]{"i686", "armv71"}));
        String zza = zzql.OS_ARCH.zza();
        if (!TextUtils.isEmpty(zza) && hashSet.contains(zza)) {
            return zza;
        }
        try {
            String[] strArr = (String[]) Build.class.getField("SUPPORTED_ABIS").get((Object) null);
            if (strArr != null && strArr.length > 0) {
                return strArr[0];
            }
        } catch (NoSuchFieldException e) {
            zznt.zzc(2024, 0, e);
        } catch (IllegalAccessException e2) {
            zznt.zzc(2024, 0, e2);
        }
        return Build.CPU_ABI != null ? Build.CPU_ABI : Build.CPU_ABI2;
    }

    private static final void zzd(byte[] bArr, String str, Context context, zznt zznt) {
        StringBuilder sb = new StringBuilder("os.arch:");
        sb.append(zzql.OS_ARCH.zza());
        sb.append(";");
        try {
            String[] strArr = (String[]) Build.class.getField("SUPPORTED_ABIS").get((Object) null);
            if (strArr != null) {
                sb.append("supported_abis:");
                sb.append(Arrays.toString(strArr));
                sb.append(";");
            }
        } catch (IllegalAccessException | NoSuchFieldException unused) {
        }
        sb.append("CPU_ABI:");
        sb.append(Build.CPU_ABI);
        sb.append(";CPU_ABI2:");
        sb.append(Build.CPU_ABI2);
        sb.append(";");
        if (bArr != null) {
            sb.append("ELF:");
            sb.append(Arrays.toString(bArr));
            sb.append(";");
        }
        if (str != null) {
            sb.append("dbg:");
            sb.append(str);
            sb.append(";");
        }
        zznt.zzb(4007, sb.toString());
    }
}

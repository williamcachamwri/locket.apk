package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzyt  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzyt {
    public static String zza(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            byte b2 = b & 255;
            sb.append("0123456789abcdef".charAt(b2 / 16));
            sb.append("0123456789abcdef".charAt(b2 % 16));
        }
        return sb.toString();
    }

    public static byte[] zza(String str) {
        if (str.length() % 2 == 0) {
            int length = str.length() / 2;
            byte[] bArr = new byte[length];
            for (int i = 0; i < length; i++) {
                int i2 = i * 2;
                int digit = Character.digit(str.charAt(i2), 16);
                int digit2 = Character.digit(str.charAt(i2 + 1), 16);
                if (digit == -1 || digit2 == -1) {
                    throw new IllegalArgumentException("input is not hexadecimal");
                }
                bArr[i] = (byte) ((digit << 4) + digit2);
            }
            return bArr;
        }
        throw new IllegalArgumentException("Expected a string of even length");
    }
}

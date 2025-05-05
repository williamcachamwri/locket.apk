package com.google.gson.internal.bind.util;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.io.FilenameUtils;

public class ISO8601Utils {
    private static final TimeZone TIMEZONE_UTC = TimeZone.getTimeZone(UTC_ID);
    private static final String UTC_ID = "UTC";

    private ISO8601Utils() {
    }

    public static String format(Date date) {
        return format(date, false, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z) {
        return format(date, z, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z, TimeZone timeZone) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone, Locale.US);
        gregorianCalendar.setTime(date);
        StringBuilder sb = new StringBuilder(19 + (z ? 4 : 0) + (timeZone.getRawOffset() == 0 ? 1 : 6));
        padInt(sb, gregorianCalendar.get(1), 4);
        char c = '-';
        sb.append('-');
        padInt(sb, gregorianCalendar.get(2) + 1, 2);
        sb.append('-');
        padInt(sb, gregorianCalendar.get(5), 2);
        sb.append('T');
        padInt(sb, gregorianCalendar.get(11), 2);
        sb.append(AbstractJsonLexerKt.COLON);
        padInt(sb, gregorianCalendar.get(12), 2);
        sb.append(AbstractJsonLexerKt.COLON);
        padInt(sb, gregorianCalendar.get(13), 2);
        if (z) {
            sb.append(FilenameUtils.EXTENSION_SEPARATOR);
            padInt(sb, gregorianCalendar.get(14), 3);
        }
        int offset = timeZone.getOffset(gregorianCalendar.getTimeInMillis());
        if (offset != 0) {
            int i = offset / 60000;
            int abs = Math.abs(i / 60);
            int abs2 = Math.abs(i % 60);
            if (offset >= 0) {
                c = '+';
            }
            sb.append(c);
            padInt(sb, abs, 2);
            sb.append(AbstractJsonLexerKt.COLON);
            padInt(sb, abs2, 2);
        } else {
            sb.append('Z');
        }
        return sb.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x00d8 A[Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x01c4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x01bc A[Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x01c4 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Date parse(java.lang.String r19, java.text.ParsePosition r20) throws java.text.ParseException {
        /*
            r1 = r19
            r2 = r20
            java.lang.String r0 = "Mismatching time zone indicator: "
            java.lang.String r3 = "GMT"
            java.lang.String r4 = "Invalid time zone indicator '"
            int r5 = r20.getIndex()     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            int r6 = r5 + 4
            int r5 = parseInt(r1, r5, r6)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            r7 = 45
            boolean r8 = checkOffset(r1, r6, r7)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            if (r8 == 0) goto L_0x001e
            int r6 = r6 + 1
        L_0x001e:
            int r8 = r6 + 2
            int r6 = parseInt(r1, r6, r8)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            boolean r9 = checkOffset(r1, r8, r7)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            if (r9 == 0) goto L_0x002c
            int r8 = r8 + 1
        L_0x002c:
            int r9 = r8 + 2
            int r8 = parseInt(r1, r8, r9)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            r10 = 84
            boolean r10 = checkOffset(r1, r9, r10)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            r11 = 0
            r12 = 1
            if (r10 != 0) goto L_0x0053
            int r13 = r19.length()     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            if (r13 > r9) goto L_0x0053
            java.util.GregorianCalendar r0 = new java.util.GregorianCalendar     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            int r6 = r6 - r12
            r0.<init>(r5, r6, r8)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            r0.setLenient(r11)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            r2.setIndex(r9)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            java.util.Date r0 = r0.getTime()     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            return r0
        L_0x0053:
            r13 = 43
            r14 = 90
            if (r10 == 0) goto L_0x00ce
            int r9 = r9 + 1
            int r10 = r9 + 2
            int r9 = parseInt(r1, r9, r10)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            r11 = 58
            boolean r16 = checkOffset(r1, r10, r11)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            if (r16 == 0) goto L_0x006b
            int r10 = r10 + 1
        L_0x006b:
            int r15 = r10 + 2
            int r10 = parseInt(r1, r10, r15)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            boolean r11 = checkOffset(r1, r15, r11)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            if (r11 == 0) goto L_0x0079
            int r15 = r15 + 1
        L_0x0079:
            int r11 = r19.length()     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            if (r11 <= r15) goto L_0x00cb
            char r11 = r1.charAt(r15)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            if (r11 == r14) goto L_0x00cb
            if (r11 == r13) goto L_0x00cb
            if (r11 == r7) goto L_0x00cb
            int r11 = r15 + 2
            int r15 = parseInt(r1, r15, r11)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            r7 = 59
            if (r15 <= r7) goto L_0x0099
            r7 = 63
            if (r15 >= r7) goto L_0x0099
            r15 = 59
        L_0x0099:
            r7 = 46
            boolean r7 = checkOffset(r1, r11, r7)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            if (r7 == 0) goto L_0x00c7
            int r11 = r11 + 1
            int r7 = r11 + 1
            int r7 = indexOfNonDigit(r1, r7)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            int r13 = r11 + 3
            int r13 = java.lang.Math.min(r7, r13)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            int r17 = parseInt(r1, r11, r13)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            int r13 = r13 - r11
            if (r13 == r12) goto L_0x00bd
            r11 = 2
            if (r13 == r11) goto L_0x00ba
            goto L_0x00bf
        L_0x00ba:
            int r17 = r17 * 10
            goto L_0x00bf
        L_0x00bd:
            int r17 = r17 * 100
        L_0x00bf:
            r11 = r17
            r18 = r9
            r9 = r7
            r7 = r18
            goto L_0x00d2
        L_0x00c7:
            r7 = r9
            r9 = r11
            r11 = 0
            goto L_0x00d2
        L_0x00cb:
            r7 = r9
            r9 = r15
            goto L_0x00d0
        L_0x00ce:
            r7 = 0
            r10 = 0
        L_0x00d0:
            r11 = 0
            r15 = 0
        L_0x00d2:
            int r13 = r19.length()     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            if (r13 <= r9) goto L_0x01bc
            char r13 = r1.charAt(r9)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            r12 = 5
            if (r13 != r14) goto L_0x00e5
            java.util.TimeZone r0 = TIMEZONE_UTC     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            r3 = 1
            int r9 = r9 + r3
            goto L_0x018a
        L_0x00e5:
            r14 = 43
            if (r13 == r14) goto L_0x0107
            r14 = 45
            if (r13 != r14) goto L_0x00ee
            goto L_0x0107
        L_0x00ee:
            java.lang.IndexOutOfBoundsException r0 = new java.lang.IndexOutOfBoundsException     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            r3.<init>(r4)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            java.lang.StringBuilder r3 = r3.append(r13)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            java.lang.String r4 = "'"
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            java.lang.String r3 = r3.toString()     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            r0.<init>(r3)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            throw r0     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
        L_0x0107:
            java.lang.String r4 = r1.substring(r9)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            int r13 = r4.length()     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            if (r13 < r12) goto L_0x0112
            goto L_0x0125
        L_0x0112:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            r13.<init>()     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            java.lang.StringBuilder r4 = r13.append(r4)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            java.lang.String r13 = "00"
            java.lang.StringBuilder r4 = r4.append(r13)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            java.lang.String r4 = r4.toString()     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
        L_0x0125:
            int r13 = r4.length()     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            int r9 = r9 + r13
            java.lang.String r13 = "+0000"
            boolean r13 = r4.equals(r13)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            if (r13 != 0) goto L_0x0188
            java.lang.String r13 = "+00:00"
            boolean r13 = r4.equals(r13)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            if (r13 == 0) goto L_0x013b
            goto L_0x0188
        L_0x013b:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            r13.<init>(r3)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            java.lang.StringBuilder r3 = r13.append(r4)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            java.lang.String r3 = r3.toString()     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            java.util.TimeZone r4 = java.util.TimeZone.getTimeZone(r3)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            java.lang.String r13 = r4.getID()     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            boolean r14 = r13.equals(r3)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            if (r14 != 0) goto L_0x0186
            java.lang.String r14 = ":"
            java.lang.String r12 = ""
            java.lang.String r12 = r13.replace(r14, r12)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            boolean r12 = r12.equals(r3)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            if (r12 == 0) goto L_0x0165
            goto L_0x0186
        L_0x0165:
            java.lang.IndexOutOfBoundsException r5 = new java.lang.IndexOutOfBoundsException     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            r6.<init>(r0)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            java.lang.StringBuilder r0 = r6.append(r3)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            java.lang.String r3 = " given, resolves to "
            java.lang.StringBuilder r0 = r0.append(r3)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            java.lang.String r3 = r4.getID()     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            java.lang.StringBuilder r0 = r0.append(r3)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            java.lang.String r0 = r0.toString()     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            r5.<init>(r0)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            throw r5     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
        L_0x0186:
            r0 = r4
            goto L_0x018a
        L_0x0188:
            java.util.TimeZone r0 = TIMEZONE_UTC     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
        L_0x018a:
            java.util.GregorianCalendar r3 = new java.util.GregorianCalendar     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            r3.<init>(r0)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            r0 = 0
            r3.setLenient(r0)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            r0 = 1
            r3.set(r0, r5)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            int r6 = r6 - r0
            r0 = 2
            r3.set(r0, r6)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            r0 = 5
            r3.set(r0, r8)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            r0 = 11
            r3.set(r0, r7)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            r0 = 12
            r3.set(r0, r10)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            r0 = 13
            r3.set(r0, r15)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            r0 = 14
            r3.set(r0, r11)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            r2.setIndex(r9)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            java.util.Date r0 = r3.getTime()     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            return r0
        L_0x01bc:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            java.lang.String r3 = "No time zone indicator"
            r0.<init>(r3)     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
            throw r0     // Catch:{ IndexOutOfBoundsException -> 0x01c6, IllegalArgumentException -> 0x01c4 }
        L_0x01c4:
            r0 = move-exception
            goto L_0x01c7
        L_0x01c6:
            r0 = move-exception
        L_0x01c7:
            if (r1 != 0) goto L_0x01cb
            r1 = 0
            goto L_0x01e0
        L_0x01cb:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "\""
            r3.<init>(r4)
            java.lang.StringBuilder r1 = r3.append(r1)
            r3 = 34
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.String r1 = r1.toString()
        L_0x01e0:
            java.lang.String r3 = r0.getMessage()
            if (r3 == 0) goto L_0x01ec
            boolean r4 = r3.isEmpty()
            if (r4 == 0) goto L_0x0209
        L_0x01ec:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "("
            r3.<init>(r4)
            java.lang.Class r4 = r0.getClass()
            java.lang.String r4 = r4.getName()
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r4 = ")"
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
        L_0x0209:
            java.text.ParseException r4 = new java.text.ParseException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "Failed to parse date ["
            r5.<init>(r6)
            java.lang.StringBuilder r1 = r5.append(r1)
            java.lang.String r5 = "]: "
            java.lang.StringBuilder r1 = r1.append(r5)
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.String r1 = r1.toString()
            int r2 = r20.getIndex()
            r4.<init>(r1, r2)
            r4.initCause(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.util.ISO8601Utils.parse(java.lang.String, java.text.ParsePosition):java.util.Date");
    }

    private static boolean checkOffset(String str, int i, char c) {
        return i < str.length() && str.charAt(i) == c;
    }

    private static int parseInt(String str, int i, int i2) throws NumberFormatException {
        int i3;
        int i4;
        if (i < 0 || i2 > str.length() || i > i2) {
            throw new NumberFormatException(str);
        }
        if (i < i2) {
            i4 = i + 1;
            int digit = Character.digit(str.charAt(i), 10);
            if (digit >= 0) {
                i3 = -digit;
            } else {
                throw new NumberFormatException("Invalid number: " + str.substring(i, i2));
            }
        } else {
            i3 = 0;
            i4 = i;
        }
        while (i4 < i2) {
            int i5 = i4 + 1;
            int digit2 = Character.digit(str.charAt(i4), 10);
            if (digit2 >= 0) {
                i3 = (i3 * 10) - digit2;
                i4 = i5;
            } else {
                throw new NumberFormatException("Invalid number: " + str.substring(i, i2));
            }
        }
        return -i3;
    }

    private static void padInt(StringBuilder sb, int i, int i2) {
        String num = Integer.toString(i);
        for (int length = i2 - num.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(num);
    }

    private static int indexOfNonDigit(String str, int i) {
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt < '0' || charAt > '9') {
                return i;
            }
            i++;
        }
        return str.length();
    }
}

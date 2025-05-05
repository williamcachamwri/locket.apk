package androidx.webkit.internal;

import java.net.URLConnection;

class MimeUtil {
    MimeUtil() {
    }

    public static String getMimeFromFileName(String str) {
        if (str == null) {
            return null;
        }
        String guessContentTypeFromName = URLConnection.guessContentTypeFromName(str);
        if (guessContentTypeFromName != null) {
            return guessContentTypeFromName;
        }
        return guessHardcodedMime(str);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0051, code lost:
        if (r5.equals("mhtml") == false) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String guessHardcodedMime(java.lang.String r5) {
        /*
            r0 = 46
            int r1 = r5.lastIndexOf(r0)
            r2 = 0
            r3 = -1
            if (r1 != r3) goto L_0x000b
            return r2
        L_0x000b:
            r4 = 1
            int r1 = r1 + r4
            java.lang.String r5 = r5.substring(r1)
            java.lang.String r5 = r5.toLowerCase()
            r5.hashCode()
            int r1 = r5.hashCode()
            switch(r1) {
                case 3315: goto L_0x02c6;
                case 3401: goto L_0x02ba;
                case 97669: goto L_0x02ae;
                case 98819: goto L_0x02a2;
                case 102340: goto L_0x0296;
                case 103649: goto L_0x028a;
                case 104085: goto L_0x027e;
                case 105441: goto L_0x0272;
                case 106458: goto L_0x0264;
                case 106479: goto L_0x0256;
                case 108089: goto L_0x0248;
                case 108150: goto L_0x023a;
                case 108272: goto L_0x022c;
                case 108273: goto L_0x021e;
                case 108324: goto L_0x0210;
                case 109961: goto L_0x0202;
                case 109967: goto L_0x01f4;
                case 109973: goto L_0x01e6;
                case 109982: goto L_0x01d8;
                case 110834: goto L_0x01ca;
                case 111030: goto L_0x01bc;
                case 111145: goto L_0x01ae;
                case 114276: goto L_0x019f;
                case 114791: goto L_0x0190;
                case 114833: goto L_0x0181;
                case 117484: goto L_0x0172;
                case 118660: goto L_0x0163;
                case 118807: goto L_0x0154;
                case 120609: goto L_0x0145;
                case 3000872: goto L_0x0137;
                case 3145576: goto L_0x0129;
                case 3213227: goto L_0x011b;
                case 3259225: goto L_0x010d;
                case 3268712: goto L_0x00ff;
                case 3271912: goto L_0x00f1;
                case 3358085: goto L_0x00e3;
                case 3418175: goto L_0x00d5;
                case 3529614: goto L_0x00c6;
                case 3542678: goto L_0x00b7;
                case 3559925: goto L_0x00a8;
                case 3642020: goto L_0x0099;
                case 3645337: goto L_0x008b;
                case 3645340: goto L_0x007d;
                case 3655064: goto L_0x006f;
                case 3678569: goto L_0x0061;
                case 96488848: goto L_0x0054;
                case 103877016: goto L_0x004b;
                case 106703064: goto L_0x003e;
                case 109418142: goto L_0x0030;
                case 114035747: goto L_0x0022;
                default: goto L_0x001f;
            }
        L_0x001f:
            r0 = r3
            goto L_0x02d1
        L_0x0022:
            java.lang.String r0 = "xhtml"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x002c
            goto L_0x001f
        L_0x002c:
            r0 = 49
            goto L_0x02d1
        L_0x0030:
            java.lang.String r0 = "shtml"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x003a
            goto L_0x001f
        L_0x003a:
            r0 = 48
            goto L_0x02d1
        L_0x003e:
            java.lang.String r0 = "pjpeg"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x0047
            goto L_0x001f
        L_0x0047:
            r0 = 47
            goto L_0x02d1
        L_0x004b:
            java.lang.String r1 = "mhtml"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L_0x02d1
            goto L_0x001f
        L_0x0054:
            java.lang.String r0 = "ehtml"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x005d
            goto L_0x001f
        L_0x005d:
            r0 = 45
            goto L_0x02d1
        L_0x0061:
            java.lang.String r0 = "xhtm"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x006b
            goto L_0x001f
        L_0x006b:
            r0 = 44
            goto L_0x02d1
        L_0x006f:
            java.lang.String r0 = "woff"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x0079
            goto L_0x001f
        L_0x0079:
            r0 = 43
            goto L_0x02d1
        L_0x007d:
            java.lang.String r0 = "webp"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x0087
            goto L_0x001f
        L_0x0087:
            r0 = 42
            goto L_0x02d1
        L_0x008b:
            java.lang.String r0 = "webm"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x0095
            goto L_0x001f
        L_0x0095:
            r0 = 41
            goto L_0x02d1
        L_0x0099:
            java.lang.String r0 = "wasm"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x00a4
            goto L_0x001f
        L_0x00a4:
            r0 = 40
            goto L_0x02d1
        L_0x00a8:
            java.lang.String r0 = "tiff"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x00b3
            goto L_0x001f
        L_0x00b3:
            r0 = 39
            goto L_0x02d1
        L_0x00b7:
            java.lang.String r0 = "svgz"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x00c2
            goto L_0x001f
        L_0x00c2:
            r0 = 38
            goto L_0x02d1
        L_0x00c6:
            java.lang.String r0 = "shtm"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x00d1
            goto L_0x001f
        L_0x00d1:
            r0 = 37
            goto L_0x02d1
        L_0x00d5:
            java.lang.String r0 = "opus"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x00df
            goto L_0x001f
        L_0x00df:
            r0 = 36
            goto L_0x02d1
        L_0x00e3:
            java.lang.String r0 = "mpeg"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x00ed
            goto L_0x001f
        L_0x00ed:
            r0 = 35
            goto L_0x02d1
        L_0x00f1:
            java.lang.String r0 = "json"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x00fb
            goto L_0x001f
        L_0x00fb:
            r0 = 34
            goto L_0x02d1
        L_0x00ff:
            java.lang.String r0 = "jpeg"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x0109
            goto L_0x001f
        L_0x0109:
            r0 = 33
            goto L_0x02d1
        L_0x010d:
            java.lang.String r0 = "jfif"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x0117
            goto L_0x001f
        L_0x0117:
            r0 = 32
            goto L_0x02d1
        L_0x011b:
            java.lang.String r0 = "html"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x0125
            goto L_0x001f
        L_0x0125:
            r0 = 31
            goto L_0x02d1
        L_0x0129:
            java.lang.String r0 = "flac"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x0133
            goto L_0x001f
        L_0x0133:
            r0 = 30
            goto L_0x02d1
        L_0x0137:
            java.lang.String r0 = "apng"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x0141
            goto L_0x001f
        L_0x0141:
            r0 = 29
            goto L_0x02d1
        L_0x0145:
            java.lang.String r0 = "zip"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x0150
            goto L_0x001f
        L_0x0150:
            r0 = 28
            goto L_0x02d1
        L_0x0154:
            java.lang.String r0 = "xml"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x015f
            goto L_0x001f
        L_0x015f:
            r0 = 27
            goto L_0x02d1
        L_0x0163:
            java.lang.String r0 = "xht"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x016e
            goto L_0x001f
        L_0x016e:
            r0 = 26
            goto L_0x02d1
        L_0x0172:
            java.lang.String r0 = "wav"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x017d
            goto L_0x001f
        L_0x017d:
            r0 = 25
            goto L_0x02d1
        L_0x0181:
            java.lang.String r0 = "tif"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x018c
            goto L_0x001f
        L_0x018c:
            r0 = 24
            goto L_0x02d1
        L_0x0190:
            java.lang.String r0 = "tgz"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x019b
            goto L_0x001f
        L_0x019b:
            r0 = 23
            goto L_0x02d1
        L_0x019f:
            java.lang.String r0 = "svg"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x01aa
            goto L_0x001f
        L_0x01aa:
            r0 = 22
            goto L_0x02d1
        L_0x01ae:
            java.lang.String r0 = "png"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x01b8
            goto L_0x001f
        L_0x01b8:
            r0 = 21
            goto L_0x02d1
        L_0x01bc:
            java.lang.String r0 = "pjp"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x01c6
            goto L_0x001f
        L_0x01c6:
            r0 = 20
            goto L_0x02d1
        L_0x01ca:
            java.lang.String r0 = "pdf"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x01d4
            goto L_0x001f
        L_0x01d4:
            r0 = 19
            goto L_0x02d1
        L_0x01d8:
            java.lang.String r0 = "ogv"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x01e2
            goto L_0x001f
        L_0x01e2:
            r0 = 18
            goto L_0x02d1
        L_0x01e6:
            java.lang.String r0 = "ogm"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x01f0
            goto L_0x001f
        L_0x01f0:
            r0 = 17
            goto L_0x02d1
        L_0x01f4:
            java.lang.String r0 = "ogg"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x01fe
            goto L_0x001f
        L_0x01fe:
            r0 = 16
            goto L_0x02d1
        L_0x0202:
            java.lang.String r0 = "oga"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x020c
            goto L_0x001f
        L_0x020c:
            r0 = 15
            goto L_0x02d1
        L_0x0210:
            java.lang.String r0 = "mpg"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x021a
            goto L_0x001f
        L_0x021a:
            r0 = 14
            goto L_0x02d1
        L_0x021e:
            java.lang.String r0 = "mp4"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x0228
            goto L_0x001f
        L_0x0228:
            r0 = 13
            goto L_0x02d1
        L_0x022c:
            java.lang.String r0 = "mp3"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x0236
            goto L_0x001f
        L_0x0236:
            r0 = 12
            goto L_0x02d1
        L_0x023a:
            java.lang.String r0 = "mjs"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x0244
            goto L_0x001f
        L_0x0244:
            r0 = 11
            goto L_0x02d1
        L_0x0248:
            java.lang.String r0 = "mht"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x0252
            goto L_0x001f
        L_0x0252:
            r0 = 10
            goto L_0x02d1
        L_0x0256:
            java.lang.String r0 = "m4v"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x0260
            goto L_0x001f
        L_0x0260:
            r0 = 9
            goto L_0x02d1
        L_0x0264:
            java.lang.String r0 = "m4a"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x026e
            goto L_0x001f
        L_0x026e:
            r0 = 8
            goto L_0x02d1
        L_0x0272:
            java.lang.String r0 = "jpg"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x027c
            goto L_0x001f
        L_0x027c:
            r0 = 7
            goto L_0x02d1
        L_0x027e:
            java.lang.String r0 = "ico"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x0288
            goto L_0x001f
        L_0x0288:
            r0 = 6
            goto L_0x02d1
        L_0x028a:
            java.lang.String r0 = "htm"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x0294
            goto L_0x001f
        L_0x0294:
            r0 = 5
            goto L_0x02d1
        L_0x0296:
            java.lang.String r0 = "gif"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x02a0
            goto L_0x001f
        L_0x02a0:
            r0 = 4
            goto L_0x02d1
        L_0x02a2:
            java.lang.String r0 = "css"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x02ac
            goto L_0x001f
        L_0x02ac:
            r0 = 3
            goto L_0x02d1
        L_0x02ae:
            java.lang.String r0 = "bmp"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x02b8
            goto L_0x001f
        L_0x02b8:
            r0 = 2
            goto L_0x02d1
        L_0x02ba:
            java.lang.String r0 = "js"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x02c4
            goto L_0x001f
        L_0x02c4:
            r0 = r4
            goto L_0x02d1
        L_0x02c6:
            java.lang.String r0 = "gz"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x02d0
            goto L_0x001f
        L_0x02d0:
            r0 = 0
        L_0x02d1:
            switch(r0) {
                case 0: goto L_0x0334;
                case 1: goto L_0x0330;
                case 2: goto L_0x032d;
                case 3: goto L_0x0329;
                case 4: goto L_0x0326;
                case 5: goto L_0x0322;
                case 6: goto L_0x031f;
                case 7: goto L_0x031c;
                case 8: goto L_0x0319;
                case 9: goto L_0x0315;
                case 10: goto L_0x0312;
                case 11: goto L_0x0330;
                case 12: goto L_0x030f;
                case 13: goto L_0x0315;
                case 14: goto L_0x030b;
                case 15: goto L_0x0308;
                case 16: goto L_0x0308;
                case 17: goto L_0x0304;
                case 18: goto L_0x0304;
                case 19: goto L_0x0301;
                case 20: goto L_0x031c;
                case 21: goto L_0x02fe;
                case 22: goto L_0x02fb;
                case 23: goto L_0x0334;
                case 24: goto L_0x02f8;
                case 25: goto L_0x02f5;
                case 26: goto L_0x02f2;
                case 27: goto L_0x02ee;
                case 28: goto L_0x02eb;
                case 29: goto L_0x02e8;
                case 30: goto L_0x02e5;
                case 31: goto L_0x0322;
                case 32: goto L_0x031c;
                case 33: goto L_0x031c;
                case 34: goto L_0x02e2;
                case 35: goto L_0x030b;
                case 36: goto L_0x0308;
                case 37: goto L_0x0322;
                case 38: goto L_0x02fb;
                case 39: goto L_0x02f8;
                case 40: goto L_0x02df;
                case 41: goto L_0x02db;
                case 42: goto L_0x02d8;
                case 43: goto L_0x02d5;
                case 44: goto L_0x02f2;
                case 45: goto L_0x0322;
                case 46: goto L_0x0312;
                case 47: goto L_0x031c;
                case 48: goto L_0x0322;
                case 49: goto L_0x02f2;
                default: goto L_0x02d4;
            }
        L_0x02d4:
            return r2
        L_0x02d5:
            java.lang.String r5 = "application/font-woff"
            return r5
        L_0x02d8:
            java.lang.String r5 = "image/webp"
            return r5
        L_0x02db:
            java.lang.String r5 = "video/webm"
            return r5
        L_0x02df:
            java.lang.String r5 = "application/wasm"
            return r5
        L_0x02e2:
            java.lang.String r5 = "application/json"
            return r5
        L_0x02e5:
            java.lang.String r5 = "audio/flac"
            return r5
        L_0x02e8:
            java.lang.String r5 = "image/apng"
            return r5
        L_0x02eb:
            java.lang.String r5 = "application/zip"
            return r5
        L_0x02ee:
            java.lang.String r5 = "text/xml"
            return r5
        L_0x02f2:
            java.lang.String r5 = "application/xhtml+xml"
            return r5
        L_0x02f5:
            java.lang.String r5 = "audio/wav"
            return r5
        L_0x02f8:
            java.lang.String r5 = "image/tiff"
            return r5
        L_0x02fb:
            java.lang.String r5 = "image/svg+xml"
            return r5
        L_0x02fe:
            java.lang.String r5 = "image/png"
            return r5
        L_0x0301:
            java.lang.String r5 = "application/pdf"
            return r5
        L_0x0304:
            java.lang.String r5 = "video/ogg"
            return r5
        L_0x0308:
            java.lang.String r5 = "audio/ogg"
            return r5
        L_0x030b:
            java.lang.String r5 = "video/mpeg"
            return r5
        L_0x030f:
            java.lang.String r5 = "audio/mpeg"
            return r5
        L_0x0312:
            java.lang.String r5 = "multipart/related"
            return r5
        L_0x0315:
            java.lang.String r5 = "video/mp4"
            return r5
        L_0x0319:
            java.lang.String r5 = "audio/x-m4a"
            return r5
        L_0x031c:
            java.lang.String r5 = "image/jpeg"
            return r5
        L_0x031f:
            java.lang.String r5 = "image/x-icon"
            return r5
        L_0x0322:
            java.lang.String r5 = "text/html"
            return r5
        L_0x0326:
            java.lang.String r5 = "image/gif"
            return r5
        L_0x0329:
            java.lang.String r5 = "text/css"
            return r5
        L_0x032d:
            java.lang.String r5 = "image/bmp"
            return r5
        L_0x0330:
            java.lang.String r5 = "text/javascript"
            return r5
        L_0x0334:
            java.lang.String r5 = "application/gzip"
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.webkit.internal.MimeUtil.guessHardcodedMime(java.lang.String):java.lang.String");
    }
}

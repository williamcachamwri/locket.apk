package androidx.media3.common;

import android.net.Uri;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Map;

public final class FileTypes {
    public static final int AC3 = 0;
    public static final int AC4 = 1;
    public static final int ADTS = 2;
    public static final int AMR = 3;
    public static final int AVI = 16;
    public static final int AVIF = 21;
    public static final int BMP = 19;
    private static final String EXTENSION_AAC = ".aac";
    private static final String EXTENSION_AC3 = ".ac3";
    private static final String EXTENSION_AC4 = ".ac4";
    private static final String EXTENSION_ADTS = ".adts";
    private static final String EXTENSION_AMR = ".amr";
    private static final String EXTENSION_AVI = ".avi";
    private static final String EXTENSION_AVIF = ".avif";
    private static final String EXTENSION_BMP = ".bmp";
    private static final String EXTENSION_DIB = ".dib";
    private static final String EXTENSION_EC3 = ".ec3";
    private static final String EXTENSION_FLAC = ".flac";
    private static final String EXTENSION_FLV = ".flv";
    private static final String EXTENSION_HEIC = ".heic";
    private static final String EXTENSION_HEIF = ".heif";
    private static final String EXTENSION_JPEG = ".jpeg";
    private static final String EXTENSION_JPG = ".jpg";
    private static final String EXTENSION_M2P = ".m2p";
    private static final String EXTENSION_MID = ".mid";
    private static final String EXTENSION_MIDI = ".midi";
    private static final String EXTENSION_MP3 = ".mp3";
    private static final String EXTENSION_MP4 = ".mp4";
    private static final String EXTENSION_MPEG = ".mpeg";
    private static final String EXTENSION_MPG = ".mpg";
    private static final String EXTENSION_OPUS = ".opus";
    private static final String EXTENSION_PNG = ".png";
    private static final String EXTENSION_PREFIX_CMF = ".cmf";
    private static final String EXTENSION_PREFIX_M4 = ".m4";
    private static final String EXTENSION_PREFIX_MK = ".mk";
    private static final String EXTENSION_PREFIX_MP4 = ".mp4";
    private static final String EXTENSION_PREFIX_OG = ".og";
    private static final String EXTENSION_PREFIX_TS = ".ts";
    private static final String EXTENSION_PS = ".ps";
    private static final String EXTENSION_SMF = ".smf";
    private static final String EXTENSION_TS = ".ts";
    private static final String EXTENSION_VTT = ".vtt";
    private static final String EXTENSION_WAV = ".wav";
    private static final String EXTENSION_WAVE = ".wave";
    private static final String EXTENSION_WEBM = ".webm";
    private static final String EXTENSION_WEBP = ".webp";
    private static final String EXTENSION_WEBVTT = ".webvtt";
    public static final int FLAC = 4;
    public static final int FLV = 5;
    static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final int HEIF = 20;
    public static final int JPEG = 14;
    public static final int MATROSKA = 6;
    public static final int MIDI = 15;
    public static final int MP3 = 7;
    public static final int MP4 = 8;
    public static final int OGG = 9;
    public static final int PNG = 17;
    public static final int PS = 10;
    public static final int TS = 11;
    public static final int UNKNOWN = -1;
    public static final int WAV = 12;
    public static final int WEBP = 18;
    public static final int WEBVTT = 13;

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
    }

    private FileTypes() {
    }

    public static int inferFileTypeFromResponseHeaders(Map<String, List<String>> map) {
        List list = map.get("Content-Type");
        return inferFileTypeFromMimeType((list == null || list.isEmpty()) ? null : (String) list.get(0));
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int inferFileTypeFromMimeType(java.lang.String r24) {
        /*
            r0 = -1
            if (r24 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.lang.String r1 = androidx.media3.common.MimeTypes.normalizeMimeType(r24)
            r1.hashCode()
            int r2 = r1.hashCode()
            r3 = 21
            r4 = 20
            r5 = 19
            r6 = 18
            r7 = 17
            r8 = 16
            r9 = 15
            r10 = 14
            r11 = 13
            r12 = 12
            r13 = 11
            r14 = 10
            r15 = 9
            r16 = 8
            r17 = 7
            r18 = 6
            r19 = 5
            r20 = 4
            r21 = 3
            r22 = 1
            r23 = 0
            switch(r2) {
                case -2123537834: goto L_0x01da;
                case -1662384011: goto L_0x01cc;
                case -1662384007: goto L_0x01bf;
                case -1662095187: goto L_0x01b1;
                case -1606874997: goto L_0x01a4;
                case -1487656890: goto L_0x0197;
                case -1487464693: goto L_0x018a;
                case -1487464690: goto L_0x017d;
                case -1487394660: goto L_0x016f;
                case -1487018032: goto L_0x0162;
                case -1248337486: goto L_0x0155;
                case -1079884372: goto L_0x0147;
                case -1004728940: goto L_0x013a;
                case -879272239: goto L_0x012d;
                case -879258763: goto L_0x0120;
                case -387023398: goto L_0x0113;
                case -43467528: goto L_0x0106;
                case 13915911: goto L_0x00f8;
                case 187078296: goto L_0x00eb;
                case 187078297: goto L_0x00de;
                case 187078669: goto L_0x00d1;
                case 187090232: goto L_0x00c4;
                case 187091926: goto L_0x00b6;
                case 187099443: goto L_0x00a9;
                case 1331848029: goto L_0x009b;
                case 1503095341: goto L_0x008e;
                case 1504578661: goto L_0x0081;
                case 1504619009: goto L_0x0074;
                case 1504824762: goto L_0x0067;
                case 1504831518: goto L_0x005a;
                case 1505118770: goto L_0x004d;
                case 2039520277: goto L_0x003f;
                default: goto L_0x003c;
            }
        L_0x003c:
            r1 = r0
            goto L_0x01e6
        L_0x003f:
            java.lang.String r2 = "video/x-matroska"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x0049
            goto L_0x003c
        L_0x0049:
            r1 = 31
            goto L_0x01e6
        L_0x004d:
            java.lang.String r2 = "audio/webm"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x0056
            goto L_0x003c
        L_0x0056:
            r1 = 30
            goto L_0x01e6
        L_0x005a:
            java.lang.String r2 = "audio/mpeg"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x0063
            goto L_0x003c
        L_0x0063:
            r1 = 29
            goto L_0x01e6
        L_0x0067:
            java.lang.String r2 = "audio/midi"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x0070
            goto L_0x003c
        L_0x0070:
            r1 = 28
            goto L_0x01e6
        L_0x0074:
            java.lang.String r2 = "audio/flac"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x007d
            goto L_0x003c
        L_0x007d:
            r1 = 27
            goto L_0x01e6
        L_0x0081:
            java.lang.String r2 = "audio/eac3"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x008a
            goto L_0x003c
        L_0x008a:
            r1 = 26
            goto L_0x01e6
        L_0x008e:
            java.lang.String r2 = "audio/3gpp"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x0097
            goto L_0x003c
        L_0x0097:
            r1 = 25
            goto L_0x01e6
        L_0x009b:
            java.lang.String r2 = "video/mp4"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x00a5
            goto L_0x003c
        L_0x00a5:
            r1 = 24
            goto L_0x01e6
        L_0x00a9:
            java.lang.String r2 = "audio/wav"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x00b2
            goto L_0x003c
        L_0x00b2:
            r1 = 23
            goto L_0x01e6
        L_0x00b6:
            java.lang.String r2 = "audio/ogg"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x00c0
            goto L_0x003c
        L_0x00c0:
            r1 = 22
            goto L_0x01e6
        L_0x00c4:
            java.lang.String r2 = "audio/mp4"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x00ce
            goto L_0x003c
        L_0x00ce:
            r1 = r3
            goto L_0x01e6
        L_0x00d1:
            java.lang.String r2 = "audio/amr"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x00db
            goto L_0x003c
        L_0x00db:
            r1 = r4
            goto L_0x01e6
        L_0x00de:
            java.lang.String r2 = "audio/ac4"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x00e8
            goto L_0x003c
        L_0x00e8:
            r1 = r5
            goto L_0x01e6
        L_0x00eb:
            java.lang.String r2 = "audio/ac3"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x00f5
            goto L_0x003c
        L_0x00f5:
            r1 = r6
            goto L_0x01e6
        L_0x00f8:
            java.lang.String r2 = "video/x-flv"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x0103
            goto L_0x003c
        L_0x0103:
            r1 = r7
            goto L_0x01e6
        L_0x0106:
            java.lang.String r2 = "application/webm"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x0110
            goto L_0x003c
        L_0x0110:
            r1 = r8
            goto L_0x01e6
        L_0x0113:
            java.lang.String r2 = "audio/x-matroska"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x011d
            goto L_0x003c
        L_0x011d:
            r1 = r9
            goto L_0x01e6
        L_0x0120:
            java.lang.String r2 = "image/png"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x012a
            goto L_0x003c
        L_0x012a:
            r1 = r10
            goto L_0x01e6
        L_0x012d:
            java.lang.String r2 = "image/bmp"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x0137
            goto L_0x003c
        L_0x0137:
            r1 = r11
            goto L_0x01e6
        L_0x013a:
            java.lang.String r2 = "text/vtt"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x0144
            goto L_0x003c
        L_0x0144:
            r1 = r12
            goto L_0x01e6
        L_0x0147:
            java.lang.String r2 = "video/x-msvideo"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x0152
            goto L_0x003c
        L_0x0152:
            r1 = r13
            goto L_0x01e6
        L_0x0155:
            java.lang.String r2 = "application/mp4"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x015f
            goto L_0x003c
        L_0x015f:
            r1 = r14
            goto L_0x01e6
        L_0x0162:
            java.lang.String r2 = "image/webp"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x016c
            goto L_0x003c
        L_0x016c:
            r1 = r15
            goto L_0x01e6
        L_0x016f:
            java.lang.String r2 = "image/jpeg"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x0179
            goto L_0x003c
        L_0x0179:
            r1 = r16
            goto L_0x01e6
        L_0x017d:
            java.lang.String r2 = "image/heif"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x0187
            goto L_0x003c
        L_0x0187:
            r1 = r17
            goto L_0x01e6
        L_0x018a:
            java.lang.String r2 = "image/heic"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x0194
            goto L_0x003c
        L_0x0194:
            r1 = r18
            goto L_0x01e6
        L_0x0197:
            java.lang.String r2 = "image/avif"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x01a1
            goto L_0x003c
        L_0x01a1:
            r1 = r19
            goto L_0x01e6
        L_0x01a4:
            java.lang.String r2 = "audio/amr-wb"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x01ae
            goto L_0x003c
        L_0x01ae:
            r1 = r20
            goto L_0x01e6
        L_0x01b1:
            java.lang.String r2 = "video/webm"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x01bc
            goto L_0x003c
        L_0x01bc:
            r1 = r21
            goto L_0x01e6
        L_0x01bf:
            java.lang.String r2 = "video/mp2t"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x01ca
            goto L_0x003c
        L_0x01ca:
            r1 = 2
            goto L_0x01e6
        L_0x01cc:
            java.lang.String r2 = "video/mp2p"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x01d7
            goto L_0x003c
        L_0x01d7:
            r1 = r22
            goto L_0x01e6
        L_0x01da:
            java.lang.String r2 = "audio/eac3-joc"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x01e4
            goto L_0x003c
        L_0x01e4:
            r1 = r23
        L_0x01e6:
            switch(r1) {
                case 0: goto L_0x01fe;
                case 1: goto L_0x01fd;
                case 2: goto L_0x01fc;
                case 3: goto L_0x01fb;
                case 4: goto L_0x01fa;
                case 5: goto L_0x01f9;
                case 6: goto L_0x01f8;
                case 7: goto L_0x01f8;
                case 8: goto L_0x01f7;
                case 9: goto L_0x01f6;
                case 10: goto L_0x01f5;
                case 11: goto L_0x01f4;
                case 12: goto L_0x01f3;
                case 13: goto L_0x01f2;
                case 14: goto L_0x01f1;
                case 15: goto L_0x01fb;
                case 16: goto L_0x01fb;
                case 17: goto L_0x01f0;
                case 18: goto L_0x01fe;
                case 19: goto L_0x01ef;
                case 20: goto L_0x01fa;
                case 21: goto L_0x01f5;
                case 22: goto L_0x01ee;
                case 23: goto L_0x01ed;
                case 24: goto L_0x01f5;
                case 25: goto L_0x01fa;
                case 26: goto L_0x01fe;
                case 27: goto L_0x01ec;
                case 28: goto L_0x01eb;
                case 29: goto L_0x01ea;
                case 30: goto L_0x01fb;
                case 31: goto L_0x01fb;
                default: goto L_0x01e9;
            }
        L_0x01e9:
            return r0
        L_0x01ea:
            return r17
        L_0x01eb:
            return r9
        L_0x01ec:
            return r20
        L_0x01ed:
            return r12
        L_0x01ee:
            return r15
        L_0x01ef:
            return r22
        L_0x01f0:
            return r19
        L_0x01f1:
            return r7
        L_0x01f2:
            return r5
        L_0x01f3:
            return r11
        L_0x01f4:
            return r8
        L_0x01f5:
            return r16
        L_0x01f6:
            return r6
        L_0x01f7:
            return r10
        L_0x01f8:
            return r4
        L_0x01f9:
            return r3
        L_0x01fa:
            return r21
        L_0x01fb:
            return r18
        L_0x01fc:
            return r13
        L_0x01fd:
            return r14
        L_0x01fe:
            return r23
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.common.FileTypes.inferFileTypeFromMimeType(java.lang.String):int");
    }

    public static int inferFileTypeFromUri(Uri uri) {
        String lastPathSegment = uri.getLastPathSegment();
        if (lastPathSegment == null) {
            return -1;
        }
        if (lastPathSegment.endsWith(EXTENSION_AC3) || lastPathSegment.endsWith(EXTENSION_EC3)) {
            return 0;
        }
        if (lastPathSegment.endsWith(EXTENSION_AC4)) {
            return 1;
        }
        if (lastPathSegment.endsWith(EXTENSION_ADTS) || lastPathSegment.endsWith(EXTENSION_AAC)) {
            return 2;
        }
        if (lastPathSegment.endsWith(EXTENSION_AMR)) {
            return 3;
        }
        if (lastPathSegment.endsWith(EXTENSION_FLAC)) {
            return 4;
        }
        if (lastPathSegment.endsWith(EXTENSION_FLV)) {
            return 5;
        }
        if (lastPathSegment.endsWith(EXTENSION_MID) || lastPathSegment.endsWith(EXTENSION_MIDI) || lastPathSegment.endsWith(EXTENSION_SMF)) {
            return 15;
        }
        if (lastPathSegment.startsWith(EXTENSION_PREFIX_MK, lastPathSegment.length() - 4) || lastPathSegment.endsWith(EXTENSION_WEBM)) {
            return 6;
        }
        if (lastPathSegment.endsWith(EXTENSION_MP3)) {
            return 7;
        }
        if (lastPathSegment.endsWith(".mp4") || lastPathSegment.startsWith(EXTENSION_PREFIX_M4, lastPathSegment.length() - 4) || lastPathSegment.startsWith(".mp4", lastPathSegment.length() - 5) || lastPathSegment.startsWith(EXTENSION_PREFIX_CMF, lastPathSegment.length() - 5)) {
            return 8;
        }
        if (lastPathSegment.startsWith(EXTENSION_PREFIX_OG, lastPathSegment.length() - 4) || lastPathSegment.endsWith(EXTENSION_OPUS)) {
            return 9;
        }
        if (lastPathSegment.endsWith(EXTENSION_PS) || lastPathSegment.endsWith(EXTENSION_MPEG) || lastPathSegment.endsWith(EXTENSION_MPG) || lastPathSegment.endsWith(EXTENSION_M2P)) {
            return 10;
        }
        if (lastPathSegment.endsWith(".ts") || lastPathSegment.startsWith(".ts", lastPathSegment.length() - 4)) {
            return 11;
        }
        if (lastPathSegment.endsWith(EXTENSION_WAV) || lastPathSegment.endsWith(EXTENSION_WAVE)) {
            return 12;
        }
        if (lastPathSegment.endsWith(EXTENSION_VTT) || lastPathSegment.endsWith(EXTENSION_WEBVTT)) {
            return 13;
        }
        if (lastPathSegment.endsWith(EXTENSION_JPG) || lastPathSegment.endsWith(EXTENSION_JPEG)) {
            return 14;
        }
        if (lastPathSegment.endsWith(EXTENSION_AVI)) {
            return 16;
        }
        if (lastPathSegment.endsWith(EXTENSION_PNG)) {
            return 17;
        }
        if (lastPathSegment.endsWith(EXTENSION_WEBP)) {
            return 18;
        }
        if (lastPathSegment.endsWith(EXTENSION_BMP) || lastPathSegment.endsWith(EXTENSION_DIB)) {
            return 19;
        }
        if (lastPathSegment.endsWith(EXTENSION_HEIC) || lastPathSegment.endsWith(EXTENSION_HEIF)) {
            return 20;
        }
        if (lastPathSegment.endsWith(EXTENSION_AVIF)) {
            return 21;
        }
        return -1;
    }
}

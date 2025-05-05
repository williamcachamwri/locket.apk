package androidx.webkit;

import android.webkit.MimeTypeMap;
import com.adsbynimbus.render.mraid.HostKt;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class URLUtilCompat {
    private static final Pattern DISPOSITION_PATTERN = Pattern.compile("\\s*(\\S+?) # Group 1: parameter name\n\\s*=\\s* # Match equals sign\n(?: # non-capturing group of options\n   '( (?: [^'\\\\] | \\\\. )* )' # Group 2: single-quoted\n | \"( (?: [^\"\\\\] | \\\\. )*  )\" # Group 3: double-quoted\n | ( [^'\"][^;\\s]* ) # Group 4: un-quoted parameter\n)\\s*;? # Optional end semicolon", 4);

    private URLUtilCompat() {
    }

    public static String guessFileName(String str, String str2, String str3) {
        String filenameSuggestion = getFilenameSuggestion(str, str2);
        String suggestExtensionFromMimeType = suggestExtensionFromMimeType(str3);
        if (filenameSuggestion.indexOf(46) < 0) {
            return filenameSuggestion + suggestExtensionFromMimeType;
        }
        return (str3 == null || !extensionDifferentFromMimeType(filenameSuggestion, str3)) ? filenameSuggestion : filenameSuggestion + suggestExtensionFromMimeType;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0013, code lost:
        r0 = r0.getLastPathSegment();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String getFilenameSuggestion(java.lang.String r0, java.lang.String r1) {
        /*
            if (r1 == 0) goto L_0x000d
            java.lang.String r1 = getFilenameFromContentDisposition(r1)
            if (r1 == 0) goto L_0x000d
            java.lang.String r0 = replacePathSeparators(r1)
            return r0
        L_0x000d:
            android.net.Uri r0 = android.net.Uri.parse(r0)
            if (r0 == 0) goto L_0x001e
            java.lang.String r0 = r0.getLastPathSegment()
            if (r0 == 0) goto L_0x001e
            java.lang.String r0 = replacePathSeparators(r0)
            return r0
        L_0x001e:
            java.lang.String r0 = "downloadfile"
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.webkit.URLUtilCompat.getFilenameSuggestion(java.lang.String, java.lang.String):java.lang.String");
    }

    private static String replacePathSeparators(String str) {
        return str.replaceAll("/", "_");
    }

    private static boolean extensionDifferentFromMimeType(String str, String str2) {
        String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(str.substring(str.lastIndexOf(46) + 1));
        if (mimeTypeFromExtension == null || mimeTypeFromExtension.equalsIgnoreCase(str2)) {
            return false;
        }
        return true;
    }

    private static String suggestExtensionFromMimeType(String str) {
        if (str == null) {
            return ".bin";
        }
        String extensionFromMimeType = MimeTypeMap.getSingleton().getExtensionFromMimeType(str);
        if (extensionFromMimeType != null) {
            return "." + extensionFromMimeType;
        }
        if (str.equalsIgnoreCase("text/html")) {
            return ".html";
        }
        if (str.toLowerCase(Locale.ROOT).startsWith("text/")) {
            return ".txt";
        }
        return ".bin";
    }

    public static String getFilenameFromContentDisposition(String str) {
        String str2;
        String[] split = str.trim().split(";", 2);
        String str3 = null;
        if (split.length < 2 || HostKt.INLINE.equalsIgnoreCase(split[0].trim())) {
            return null;
        }
        Matcher matcher = DISPOSITION_PATTERN.matcher(split[1]);
        String str4 = null;
        while (matcher.find()) {
            String group = matcher.group(1);
            if (matcher.group(2) != null) {
                str2 = removeSlashEscapes(matcher.group(2));
            } else if (matcher.group(3) != null) {
                str2 = removeSlashEscapes(matcher.group(3));
            } else {
                str2 = matcher.group(4);
            }
            if (!(group == null || str2 == null)) {
                if ("filename*".equalsIgnoreCase(group)) {
                    str3 = parseExtValueString(str2);
                } else if ("filename".equalsIgnoreCase(group)) {
                    str4 = str2;
                }
            }
        }
        return str3 != null ? str3 : str4;
    }

    private static String removeSlashEscapes(String str) {
        if (str == null) {
            return null;
        }
        return str.replaceAll("\\\\(.)", "$1");
    }

    private static String parseExtValueString(String str) {
        String[] split = str.split("'", 3);
        if (split.length < 3) {
            return null;
        }
        String str2 = split[0];
        try {
            return URLDecoder.decode(encodePlusCharacters(split[2], str2), str2);
        } catch (UnsupportedEncodingException | RuntimeException unused) {
            return null;
        }
    }

    private static String encodePlusCharacters(String str, String str2) {
        Charset forName = Charset.forName(str2);
        StringBuilder sb = new StringBuilder();
        for (byte valueOf : forName.encode("+").array()) {
            sb.append(String.format("%02x", new Object[]{Byte.valueOf(valueOf)}));
        }
        return str.replaceAll("\\+", sb.toString());
    }
}

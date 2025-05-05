package com.facebook.common.media;

import androidx.media3.common.MimeTypes;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\b\u0001\u0010\u0007\u001a\u00020\u0005H\u0002J\u0014\u0010\b\u001a\u0004\u0018\u00010\u00052\b\b\u0001\u0010\u0007\u001a\u00020\u0005H\u0007J\u0012\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u000b\u001a\u00020\u0005H\u0007J\u0012\u0010\f\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005H\u0007J\u0012\u0010\r\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005H\u0007J\u0012\u0010\u000e\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005H\u0007R\u001c\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00048\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/facebook/common/media/MediaUtils;", "", "()V", "ADDITIONAL_ALLOWED_MIME_TYPES", "", "", "extractExtension", "path", "extractMime", "isNonNativeSupportedMimeType", "", "mimeType", "isPhoto", "isThreeD", "isVideo", "fbcore_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: MediaUtils.kt */
public final class MediaUtils {
    public static final Map<String, String> ADDITIONAL_ALLOWED_MIME_TYPES = MapsKt.mapOf(TuplesKt.to("mkv", MimeTypes.VIDEO_MATROSKA), TuplesKt.to("glb", "model/gltf-binary"));
    public static final MediaUtils INSTANCE = new MediaUtils();

    private MediaUtils() {
    }

    @JvmStatic
    public static final boolean isPhoto(String str) {
        if (str != null) {
            return StringsKt.startsWith$default(str, "image/", false, 2, (Object) null);
        }
        return false;
    }

    @JvmStatic
    public static final boolean isVideo(String str) {
        if (str != null) {
            return StringsKt.startsWith$default(str, "video/", false, 2, (Object) null);
        }
        return false;
    }

    @JvmStatic
    public static final boolean isThreeD(String str) {
        return Intrinsics.areEqual((Object) str, (Object) "model/gltf-binary");
    }

    @JvmStatic
    public static final String extractMime(String str) {
        Intrinsics.checkNotNullParameter(str, "path");
        String extractExtension = INSTANCE.extractExtension(str);
        if (extractExtension == null) {
            return null;
        }
        Locale locale = Locale.US;
        Intrinsics.checkNotNullExpressionValue(locale, "US");
        String lowerCase = extractExtension.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
        if (lowerCase == null) {
            return null;
        }
        String mimeTypeFromExtension = MimeTypeMapWrapper.getMimeTypeFromExtension(lowerCase);
        return mimeTypeFromExtension == null ? ADDITIONAL_ALLOWED_MIME_TYPES.get(lowerCase) : mimeTypeFromExtension;
    }

    private final String extractExtension(String str) {
        int lastIndexOf$default = StringsKt.lastIndexOf$default((CharSequence) str, (char) FilenameUtils.EXTENSION_SEPARATOR, 0, false, 6, (Object) null);
        if (lastIndexOf$default < 0 || lastIndexOf$default == str.length() - 1) {
            return null;
        }
        String substring = str.substring(lastIndexOf$default + 1);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
        return substring;
    }

    @JvmStatic
    public static final boolean isNonNativeSupportedMimeType(String str) {
        Intrinsics.checkNotNullParameter(str, "mimeType");
        return ADDITIONAL_ALLOWED_MIME_TYPES.containsValue(str);
    }
}

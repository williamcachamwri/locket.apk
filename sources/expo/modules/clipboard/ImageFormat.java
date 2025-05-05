package expo.modules.clipboard;

import android.graphics.Bitmap;
import androidx.media3.common.MimeTypes;
import expo.modules.kotlin.types.Enumerable;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0010"}, d2 = {"Lexpo/modules/clipboard/ImageFormat;", "", "Lexpo/modules/kotlin/types/Enumerable;", "jsName", "", "(Ljava/lang/String;ILjava/lang/String;)V", "compressFormat", "Landroid/graphics/Bitmap$CompressFormat;", "getCompressFormat", "()Landroid/graphics/Bitmap$CompressFormat;", "getJsName", "()Ljava/lang/String;", "mimeType", "getMimeType", "JPG", "PNG", "expo-clipboard_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ClipboardOptions.kt */
public enum ImageFormat implements Enumerable {
    JPG("jpeg"),
    PNG("png");
    
    private final String jsName;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ClipboardOptions.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                expo.modules.clipboard.ImageFormat[] r0 = expo.modules.clipboard.ImageFormat.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                expo.modules.clipboard.ImageFormat r1 = expo.modules.clipboard.ImageFormat.JPG     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                expo.modules.clipboard.ImageFormat r1 = expo.modules.clipboard.ImageFormat.PNG     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: expo.modules.clipboard.ImageFormat.WhenMappings.<clinit>():void");
        }
    }

    public static EnumEntries<ImageFormat> getEntries() {
        return $ENTRIES;
    }

    private ImageFormat(String str) {
        this.jsName = str;
    }

    public final String getJsName() {
        return this.jsName;
    }

    static {
        ImageFormat[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
    }

    public final Bitmap.CompressFormat getCompressFormat() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return Bitmap.CompressFormat.JPEG;
        }
        if (i == 2) {
            return Bitmap.CompressFormat.PNG;
        }
        throw new NoWhenBranchMatchedException();
    }

    public final String getMimeType() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return "image/jpeg";
        }
        if (i == 2) {
            return MimeTypes.IMAGE_PNG;
        }
        throw new NoWhenBranchMatchedException();
    }
}

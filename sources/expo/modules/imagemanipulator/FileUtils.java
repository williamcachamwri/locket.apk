package expo.modules.imagemanipulator;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0010\u0010\f\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bH\u0002¨\u0006\r"}, d2 = {"Lexpo/modules/imagemanipulator/FileUtils;", "", "()V", "ensureDirExists", "Ljava/io/File;", "dir", "generateRandomOutputPath", "", "context", "Landroid/content/Context;", "compressFormat", "Landroid/graphics/Bitmap$CompressFormat;", "toExtension", "expo-image-manipulator_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FileUtils.kt */
public final class FileUtils {
    public static final FileUtils INSTANCE = new FileUtils();

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: FileUtils.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                android.graphics.Bitmap$CompressFormat[] r0 = android.graphics.Bitmap.CompressFormat.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: expo.modules.imagemanipulator.FileUtils.WhenMappings.<clinit>():void");
        }
    }

    private FileUtils() {
    }

    public final String generateRandomOutputPath(Context context, Bitmap.CompressFormat compressFormat) throws IOException {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(compressFormat, "compressFormat");
        File cacheDir = context.getCacheDir();
        File file = new File(cacheDir + File.separator + "ImageManipulator");
        ensureDirExists(file);
        String str = File.separator;
        UUID randomUUID = UUID.randomUUID();
        return file + str + randomUUID + toExtension(compressFormat);
    }

    private final File ensureDirExists(File file) throws IOException {
        if (file.isDirectory() || file.mkdirs()) {
            return file;
        }
        throw new IOException("Couldn't create directory '" + file + "'");
    }

    private final String toExtension(Bitmap.CompressFormat compressFormat) {
        Bitmap.CompressFormat compressFormat2;
        int i = WhenMappings.$EnumSwitchMapping$0[compressFormat.ordinal()];
        if (i == 1) {
            return ".jpg";
        }
        if (i == 2) {
            return ".png";
        }
        if (Build.VERSION.SDK_INT >= 30) {
            compressFormat2 = Bitmap.CompressFormat.WEBP_LOSSY;
        } else {
            compressFormat2 = Bitmap.CompressFormat.WEBP;
        }
        if (compressFormat == compressFormat2) {
            return ".webp";
        }
        return ".jpg";
    }
}

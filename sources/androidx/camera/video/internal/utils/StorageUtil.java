package androidx.camera.video.internal.utils;

import android.os.StatFs;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0007J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0004H\u0007J\u0010\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000eH\u0007J\u0014\u0010\u000f\u001a\u00020\u00102\n\u0010\u0011\u001a\u00060\u0012j\u0002`\u0013H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Landroidx/camera/video/internal/utils/StorageUtil;", "", "()V", "TAG", "", "formatSize", "bytes", "", "getAvailableBytes", "file", "Ljava/io/File;", "path", "getAvailableBytesForMediaStoreUri", "uri", "Landroid/net/Uri;", "isStorageFullException", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "camera-video_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: StorageUtil.kt */
public final class StorageUtil {
    public static final StorageUtil INSTANCE = new StorageUtil();
    private static final String TAG = "StorageUtil";

    private StorageUtil() {
    }

    @JvmStatic
    public static final long getAvailableBytes(File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        String path = file.getPath();
        Intrinsics.checkNotNullExpressionValue(path, "file.path");
        return getAvailableBytes(path);
    }

    @JvmStatic
    public static final long getAvailableBytes(String str) {
        Intrinsics.checkNotNullParameter(str, "path");
        return new StatFs(str).getAvailableBytes();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0050, code lost:
        if (r0.equals("external") == false) goto L_0x0069;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0059, code lost:
        if (r0.equals("external_primary") != false) goto L_0x005b;
     */
    @kotlin.jvm.JvmStatic
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final long getAvailableBytesForMediaStoreUri(android.net.Uri r3) {
        /*
            java.lang.String r0 = "uri"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = r3.getScheme()
            java.lang.String r1 = "content"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r1)
            if (r0 == 0) goto L_0x0083
            java.util.List r0 = r3.getPathSegments()
            r1 = 0
            java.lang.Object r0 = r0.get(r1)
            java.lang.String r0 = (java.lang.String) r0
            if (r0 == 0) goto L_0x0069
            int r1 = r0.hashCode()
            r2 = -1921573490(0xffffffff8d771d8e, float:-7.6148327E-31)
            if (r1 == r2) goto L_0x0053
            r2 = -1820761141(0xffffffff937963cb, float:-3.147742E-27)
            if (r1 == r2) goto L_0x004a
            r2 = 570410685(0x21ffc6bd, float:1.7332078E-18)
            if (r1 == r2) goto L_0x0033
            goto L_0x0069
        L_0x0033:
            java.lang.String r1 = "internal"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x003c
            goto L_0x0069
        L_0x003c:
            java.io.File r3 = android.os.Environment.getDataDirectory()
            java.lang.String r0 = "getDataDirectory()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r0)
            long r0 = getAvailableBytes((java.io.File) r3)
            goto L_0x0082
        L_0x004a:
            java.lang.String r1 = "external"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x005b
            goto L_0x0069
        L_0x0053:
            java.lang.String r1 = "external_primary"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0069
        L_0x005b:
            java.io.File r3 = android.os.Environment.getExternalStorageDirectory()
            java.lang.String r0 = "getExternalStorageDirectory()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r0)
            long r0 = getAvailableBytes((java.io.File) r3)
            goto L_0x0082
        L_0x0069:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Unknown MediaStore URI: "
            r0.<init>(r1)
            java.lang.StringBuilder r3 = r0.append(r3)
            java.lang.String r3 = r3.toString()
            java.lang.String r0 = "StorageUtil"
            androidx.camera.core.Logger.w(r0, r3)
            r0 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
        L_0x0082:
            return r0
        L_0x0083:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Not a content uri: "
            r0.<init>(r1)
            java.lang.StringBuilder r3 = r0.append(r3)
            java.lang.String r3 = r3.toString()
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r3 = r3.toString()
            r0.<init>(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.video.internal.utils.StorageUtil.getAvailableBytesForMediaStoreUri(android.net.Uri):long");
    }

    @JvmStatic
    public static final String formatSize(long j) {
        int i = 0;
        if (j >= 0) {
            String[] strArr = {"B", "KB", "MB", "GB", "TB"};
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            double d = (double) j;
            double d2 = d;
            while (d2 >= 1024.0d && i < 4) {
                d2 /= 1024.0d;
                i++;
            }
            if (i == 0) {
                return decimalFormat.format(d2) + ' ' + strArr[i];
            }
            StringBuilder sb = new StringBuilder();
            while (-1 < i) {
                double pow = Math.pow(1024.0d, (double) i);
                double floor = Math.floor(d / pow);
                if (floor > 0.0d) {
                    sb.append(decimalFormat.format(floor)).append(" ").append(strArr[i]).append(" ");
                    d -= floor * pow;
                }
                i--;
            }
            return StringsKt.trim((CharSequence) sb).toString();
        }
        throw new IllegalArgumentException("Bytes cannot be negative".toString());
    }

    @JvmStatic
    public static final boolean isStorageFullException(Exception exc) {
        Intrinsics.checkNotNullParameter(exc, "e");
        if (!(exc instanceof FileNotFoundException)) {
            return false;
        }
        String message = exc.getMessage();
        return message != null && StringsKt.contains$default((CharSequence) message, (CharSequence) "No space left on device", false, 2, (Object) null);
    }
}

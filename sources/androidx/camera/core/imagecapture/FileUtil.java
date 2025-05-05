package androidx.camera.core.imagecapture;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Build;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.impl.utils.Exif;
import io.sentry.instrumentation.file.SentryFileInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public final class FileUtil {
    private static final int COPY_BUFFER_SIZE = 1024;
    private static final int NOT_PENDING = 0;
    private static final int PENDING = 1;
    private static final String TEMP_FILE_PREFIX = "CameraX";
    private static final String TEMP_FILE_SUFFIX = ".tmp";

    private FileUtil() {
    }

    static File createTempFile(ImageCapture.OutputFileOptions outputFileOptions) throws ImageCaptureException {
        try {
            File file = outputFileOptions.getFile();
            if (file != null) {
                return new File(file.getParent(), TEMP_FILE_PREFIX + UUID.randomUUID().toString() + getFileExtensionWithDot(file));
            }
            return File.createTempFile(TEMP_FILE_PREFIX, ".tmp");
        } catch (IOException e) {
            throw new ImageCaptureException(1, "Failed to create temp file.", e);
        }
    }

    static void updateFileExif(File file, Exif exif, ImageCapture.OutputFileOptions outputFileOptions, int i) throws ImageCaptureException {
        try {
            Exif createFromFile = Exif.createFromFile(file);
            exif.copyToCroppedImage(createFromFile);
            if (createFromFile.getRotation() == 0 && i != 0) {
                createFromFile.rotate(i);
            }
            ImageCapture.Metadata metadata = outputFileOptions.getMetadata();
            if (metadata.isReversedHorizontal()) {
                createFromFile.flipHorizontally();
            }
            if (metadata.isReversedVertical()) {
                createFromFile.flipVertically();
            }
            if (metadata.getLocation() != null) {
                createFromFile.attachLocation(metadata.getLocation());
            }
            createFromFile.save();
        } catch (IOException e) {
            throw new ImageCaptureException(1, "Failed to update Exif data", e);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0038, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0042, code lost:
        throw new androidx.camera.core.ImageCaptureException(1, "Failed to write to OutputStream.", (java.lang.Throwable) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0043, code lost:
        r3.delete();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0046, code lost:
        throw r4;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x003a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static android.net.Uri moveFileToTarget(java.io.File r3, androidx.camera.core.ImageCapture.OutputFileOptions r4) throws androidx.camera.core.ImageCaptureException {
        /*
            r0 = 0
            boolean r1 = isSaveToMediaStore(r4)     // Catch:{ IOException -> 0x003a }
            if (r1 == 0) goto L_0x000c
            android.net.Uri r0 = copyFileToMediaStore(r3, r4)     // Catch:{ IOException -> 0x003a }
            goto L_0x0034
        L_0x000c:
            boolean r1 = isSaveToOutputStream(r4)     // Catch:{ IOException -> 0x003a }
            if (r1 == 0) goto L_0x0020
            java.io.OutputStream r4 = r4.getOutputStream()     // Catch:{ IOException -> 0x003a }
            java.lang.Object r4 = java.util.Objects.requireNonNull(r4)     // Catch:{ IOException -> 0x003a }
            java.io.OutputStream r4 = (java.io.OutputStream) r4     // Catch:{ IOException -> 0x003a }
            copyFileToOutputStream(r3, r4)     // Catch:{ IOException -> 0x003a }
            goto L_0x0034
        L_0x0020:
            boolean r1 = isSaveToFile(r4)     // Catch:{ IOException -> 0x003a }
            if (r1 == 0) goto L_0x0034
            java.io.File r4 = r4.getFile()     // Catch:{ IOException -> 0x003a }
            java.lang.Object r4 = java.util.Objects.requireNonNull(r4)     // Catch:{ IOException -> 0x003a }
            java.io.File r4 = (java.io.File) r4     // Catch:{ IOException -> 0x003a }
            android.net.Uri r0 = copyFileToFile(r3, r4)     // Catch:{ IOException -> 0x003a }
        L_0x0034:
            r3.delete()
            return r0
        L_0x0038:
            r4 = move-exception
            goto L_0x0043
        L_0x003a:
            androidx.camera.core.ImageCaptureException r4 = new androidx.camera.core.ImageCaptureException     // Catch:{ all -> 0x0038 }
            java.lang.String r1 = "Failed to write to OutputStream."
            r2 = 1
            r4.<init>(r2, r1, r0)     // Catch:{ all -> 0x0038 }
            throw r4     // Catch:{ all -> 0x0038 }
        L_0x0043:
            r3.delete()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.imagecapture.FileUtil.moveFileToTarget(java.io.File, androidx.camera.core.ImageCapture$OutputFileOptions):android.net.Uri");
    }

    private static String getFileExtensionWithDot(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(46);
        return lastIndexOf >= 0 ? name.substring(lastIndexOf) : "";
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0064  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.net.Uri copyFileToMediaStore(java.io.File r6, androidx.camera.core.ImageCapture.OutputFileOptions r7) throws androidx.camera.core.ImageCaptureException {
        /*
            java.lang.String r0 = "Failed to write to MediaStore URI: "
            android.content.ContentResolver r1 = r7.getContentResolver()
            java.lang.Object r1 = java.util.Objects.requireNonNull(r1)
            android.content.ContentResolver r1 = (android.content.ContentResolver) r1
            android.content.ContentValues r2 = r7.getContentValues()
            if (r2 == 0) goto L_0x001c
            android.content.ContentValues r2 = new android.content.ContentValues
            android.content.ContentValues r3 = r7.getContentValues()
            r2.<init>(r3)
            goto L_0x0021
        L_0x001c:
            android.content.ContentValues r2 = new android.content.ContentValues
            r2.<init>()
        L_0x0021:
            r3 = 1
            setContentValuePendingFlag(r2, r3)
            r4 = 0
            r5 = 0
            android.net.Uri r7 = r7.getSaveCollection()     // Catch:{ IOException -> 0x004e, SecurityException -> 0x004c }
            android.net.Uri r7 = r1.insert(r7, r2)     // Catch:{ IOException -> 0x004e, SecurityException -> 0x004c }
            if (r7 == 0) goto L_0x0042
            copyTempFileToUri(r6, r7, r1)     // Catch:{ IOException -> 0x003f, SecurityException -> 0x003d, all -> 0x003a }
            if (r7 == 0) goto L_0x0039
            updateUriPendingStatus(r7, r1, r4)
        L_0x0039:
            return r7
        L_0x003a:
            r6 = move-exception
            r5 = r7
            goto L_0x0062
        L_0x003d:
            r6 = move-exception
            goto L_0x0040
        L_0x003f:
            r6 = move-exception
        L_0x0040:
            r5 = r7
            goto L_0x004f
        L_0x0042:
            androidx.camera.core.ImageCaptureException r6 = new androidx.camera.core.ImageCaptureException     // Catch:{ IOException -> 0x003f, SecurityException -> 0x003d, all -> 0x003a }
            java.lang.String r2 = "Failed to insert a MediaStore URI."
            r6.<init>(r3, r2, r5)     // Catch:{ IOException -> 0x003f, SecurityException -> 0x003d, all -> 0x003a }
            throw r6     // Catch:{ IOException -> 0x003f, SecurityException -> 0x003d, all -> 0x003a }
        L_0x004a:
            r6 = move-exception
            goto L_0x0062
        L_0x004c:
            r6 = move-exception
            goto L_0x004f
        L_0x004e:
            r6 = move-exception
        L_0x004f:
            androidx.camera.core.ImageCaptureException r7 = new androidx.camera.core.ImageCaptureException     // Catch:{ all -> 0x004a }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x004a }
            r2.<init>(r0)     // Catch:{ all -> 0x004a }
            java.lang.StringBuilder r0 = r2.append(r5)     // Catch:{ all -> 0x004a }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x004a }
            r7.<init>(r3, r0, r6)     // Catch:{ all -> 0x004a }
            throw r7     // Catch:{ all -> 0x004a }
        L_0x0062:
            if (r5 == 0) goto L_0x0067
            updateUriPendingStatus(r5, r1, r4)
        L_0x0067:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.imagecapture.FileUtil.copyFileToMediaStore(java.io.File, androidx.camera.core.ImageCapture$OutputFileOptions):android.net.Uri");
    }

    private static Uri copyFileToFile(File file, File file2) throws ImageCaptureException {
        if (file2.exists()) {
            file2.delete();
        }
        if (file.renameTo(file2)) {
            return Uri.fromFile(file2);
        }
        throw new ImageCaptureException(1, "Failed to overwrite the file: " + file2.getAbsolutePath(), (Throwable) null);
    }

    private static void copyTempFileToUri(File file, Uri uri, ContentResolver contentResolver) throws IOException {
        OutputStream openOutputStream = contentResolver.openOutputStream(uri);
        if (openOutputStream != null) {
            try {
                copyFileToOutputStream(file, openOutputStream);
                if (openOutputStream != null) {
                    openOutputStream.close();
                    return;
                }
                return;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        } else {
            throw new FileNotFoundException(uri + " cannot be resolved.");
        }
        throw th;
    }

    private static void copyFileToOutputStream(File file, OutputStream outputStream) throws IOException {
        FileInputStream create = SentryFileInputStream.Factory.create(new FileInputStream(file), file);
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = create.read(bArr);
                if (read > 0) {
                    outputStream.write(bArr, 0, read);
                } else {
                    create.close();
                    return;
                }
            }
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private static void updateUriPendingStatus(Uri uri, ContentResolver contentResolver, int i) {
        if (Build.VERSION.SDK_INT >= 29) {
            ContentValues contentValues = new ContentValues();
            setContentValuePendingFlag(contentValues, i);
            contentResolver.update(uri, contentValues, (String) null, (String[]) null);
        }
    }

    private static void setContentValuePendingFlag(ContentValues contentValues, int i) {
        if (Build.VERSION.SDK_INT >= 29) {
            contentValues.put("is_pending", Integer.valueOf(i));
        }
    }

    private static boolean isSaveToMediaStore(ImageCapture.OutputFileOptions outputFileOptions) {
        return (outputFileOptions.getSaveCollection() == null || outputFileOptions.getContentResolver() == null || outputFileOptions.getContentValues() == null) ? false : true;
    }

    private static boolean isSaveToFile(ImageCapture.OutputFileOptions outputFileOptions) {
        return outputFileOptions.getFile() != null;
    }

    private static boolean isSaveToOutputStream(ImageCapture.OutputFileOptions outputFileOptions) {
        return outputFileOptions.getOutputStream() != null;
    }
}

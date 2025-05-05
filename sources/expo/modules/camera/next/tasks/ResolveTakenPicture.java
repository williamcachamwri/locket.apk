package expo.modules.camera.next.tasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.react.bridge.BaseJavaModule;
import expo.modules.camera.next.PictureOptions;
import expo.modules.kotlin.Promise;
import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ \u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u0014H\u0002J \u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0014H\u0002J\u0010\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u000eH\u0002J\u0012\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0002J\n\u0010\u001d\u001a\u0004\u0018\u00010\u001cH\u0002J\u000e\u0010\u001e\u001a\u00020\u001aH@¢\u0006\u0002\u0010\u001fJ\n\u0010 \u001a\u0004\u0018\u00010\u001cH\u0002J\u0012\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010#\u001a\u00020$H\u0002R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u000e8BX\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006%"}, d2 = {"Lexpo/modules/camera/next/tasks/ResolveTakenPicture;", "", "imageData", "", "promise", "Lexpo/modules/kotlin/Promise;", "options", "Lexpo/modules/camera/next/PictureOptions;", "directory", "Ljava/io/File;", "pictureSavedDelegate", "Lexpo/modules/camera/next/tasks/PictureSavedDelegate;", "([BLexpo/modules/kotlin/Promise;Lexpo/modules/camera/next/PictureOptions;Ljava/io/File;Lexpo/modules/camera/next/tasks/PictureSavedDelegate;)V", "quality", "", "getQuality", "()I", "decodeAndRotateBitmap", "Landroid/graphics/Bitmap;", "angle", "Landroid/graphics/BitmapFactory$Options;", "decodeBitmap", "orientation", "bitmapOptions", "getImageRotation", "onComplete", "", "response", "Landroid/os/Bundle;", "processImage", "resolve", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "skipProcessing", "writeStreamToFile", "", "inputStream", "Ljava/io/ByteArrayOutputStream;", "expo-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ResolveTakenPicture.kt */
public final class ResolveTakenPicture {
    private final File directory;
    private byte[] imageData;
    private PictureOptions options;
    private PictureSavedDelegate pictureSavedDelegate;
    private Promise promise;

    private final int getImageRotation(int i) {
        if (i == 3) {
            return RotationOptions.ROTATE_180;
        }
        if (i != 6) {
            return i != 8 ? 0 : 270;
        }
        return 90;
    }

    public ResolveTakenPicture(byte[] bArr, Promise promise2, PictureOptions pictureOptions, File file, PictureSavedDelegate pictureSavedDelegate2) {
        Intrinsics.checkNotNullParameter(bArr, "imageData");
        Intrinsics.checkNotNullParameter(promise2, BaseJavaModule.METHOD_TYPE_PROMISE);
        Intrinsics.checkNotNullParameter(pictureOptions, "options");
        Intrinsics.checkNotNullParameter(file, "directory");
        Intrinsics.checkNotNullParameter(pictureSavedDelegate2, "pictureSavedDelegate");
        this.imageData = bArr;
        this.promise = promise2;
        this.options = pictureOptions;
        this.directory = file;
        this.pictureSavedDelegate = pictureSavedDelegate2;
    }

    private final int getQuality() {
        return (int) (this.options.getQuality() * ((double) 100));
    }

    public final Object resolve(Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new ResolveTakenPicture$resolve$2(this, (Continuation<? super ResolveTakenPicture$resolve$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0110, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r2, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0114, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0117, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r1, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x011b, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.os.Bundle processImage() {
        /*
            r12 = this;
            expo.modules.camera.next.PictureOptions r0 = r12.options
            boolean r0 = r0.getSkipProcessing()
            if (r0 == 0) goto L_0x000d
            android.os.Bundle r0 = r12.skipProcessing()
            return r0
        L_0x000d:
            r0 = 0
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch:{ Exception -> 0x011c }
            byte[] r2 = r12.imageData     // Catch:{ Exception -> 0x011c }
            r1.<init>(r2)     // Catch:{ Exception -> 0x011c }
            java.io.Closeable r1 = (java.io.Closeable) r1     // Catch:{ Exception -> 0x011c }
            r2 = r1
            java.io.ByteArrayInputStream r2 = (java.io.ByteArrayInputStream) r2     // Catch:{ all -> 0x0115 }
            android.os.Bundle r3 = new android.os.Bundle     // Catch:{ all -> 0x0115 }
            r3.<init>()     // Catch:{ all -> 0x0115 }
            androidx.exifinterface.media.ExifInterface r4 = new androidx.exifinterface.media.ExifInterface     // Catch:{ all -> 0x0115 }
            java.io.InputStream r2 = (java.io.InputStream) r2     // Catch:{ all -> 0x0115 }
            r4.<init>((java.io.InputStream) r2)     // Catch:{ all -> 0x0115 }
            expo.modules.camera.next.PictureOptions r2 = r12.options     // Catch:{ all -> 0x0115 }
            java.util.Map r2 = r2.getAdditionalExif()     // Catch:{ all -> 0x0115 }
            if (r2 == 0) goto L_0x0031
            expo.modules.camera.CameraViewHelper.setExifData(r4, r2)     // Catch:{ all -> 0x0115 }
        L_0x0031:
            java.lang.String r2 = "Orientation"
            r5 = 0
            int r2 = r4.getAttributeInt(r2, r5)     // Catch:{ all -> 0x0115 }
            android.graphics.BitmapFactory$Options r5 = new android.graphics.BitmapFactory$Options     // Catch:{ all -> 0x0115 }
            r5.<init>()     // Catch:{ all -> 0x0115 }
            r6 = 1
            r5.inSampleSize = r6     // Catch:{ all -> 0x0115 }
            kotlin.jvm.internal.Ref$ObjectRef r6 = new kotlin.jvm.internal.Ref$ObjectRef     // Catch:{ all -> 0x0115 }
            r6.<init>()     // Catch:{ all -> 0x0115 }
            r7 = r0
        L_0x0046:
            int r8 = r5.inSampleSize     // Catch:{ all -> 0x0115 }
            expo.modules.camera.next.PictureOptions r9 = r12.options     // Catch:{ all -> 0x0115 }
            int r9 = r9.getMaxDownsampling()     // Catch:{ all -> 0x0115 }
            r10 = 2
            if (r8 > r9) goto L_0x0063
            byte[] r8 = r12.imageData     // Catch:{ OutOfMemoryError -> 0x005a }
            android.graphics.Bitmap r8 = r12.decodeBitmap(r8, r2, r5)     // Catch:{ OutOfMemoryError -> 0x005a }
            r6.element = r8     // Catch:{ OutOfMemoryError -> 0x005a }
            goto L_0x0063
        L_0x005a:
            r7 = move-exception
            int r8 = r5.inSampleSize     // Catch:{ all -> 0x0115 }
            int r8 = r8 * r10
            r5.inSampleSize = r8     // Catch:{ all -> 0x0115 }
            java.lang.Error r7 = (java.lang.Error) r7     // Catch:{ all -> 0x0115 }
            goto L_0x0046
        L_0x0063:
            T r2 = r6.element     // Catch:{ all -> 0x0115 }
            if (r2 != 0) goto L_0x0076
            expo.modules.kotlin.Promise r2 = r12.promise     // Catch:{ all -> 0x0115 }
            java.lang.String r3 = "ERR_CAMERA_OUT_OF_MEMORY"
            java.lang.String r4 = "Cannot allocate enough space to process the taken picture."
            java.lang.Throwable r7 = (java.lang.Throwable) r7     // Catch:{ all -> 0x0115 }
            r2.reject(r3, r4, r7)     // Catch:{ all -> 0x0115 }
            kotlin.io.CloseableKt.closeFinally(r1, r0)     // Catch:{ Exception -> 0x011c }
            return r0
        L_0x0076:
            expo.modules.camera.next.PictureOptions r2 = r12.options     // Catch:{ all -> 0x0115 }
            boolean r2 = r2.getExif()     // Catch:{ all -> 0x0115 }
            if (r2 == 0) goto L_0x0087
            android.os.Bundle r2 = expo.modules.camera.CameraViewHelper.getExifData(r4)     // Catch:{ all -> 0x0115 }
            java.lang.String r5 = "exif"
            r3.putBundle(r5, r2)     // Catch:{ all -> 0x0115 }
        L_0x0087:
            java.lang.String r2 = "width"
            T r5 = r6.element     // Catch:{ all -> 0x0115 }
            android.graphics.Bitmap r5 = (android.graphics.Bitmap) r5     // Catch:{ all -> 0x0115 }
            int r5 = r5.getWidth()     // Catch:{ all -> 0x0115 }
            r3.putInt(r2, r5)     // Catch:{ all -> 0x0115 }
            java.lang.String r2 = "height"
            T r5 = r6.element     // Catch:{ all -> 0x0115 }
            android.graphics.Bitmap r5 = (android.graphics.Bitmap) r5     // Catch:{ all -> 0x0115 }
            int r5 = r5.getHeight()     // Catch:{ all -> 0x0115 }
            r3.putInt(r2, r5)     // Catch:{ all -> 0x0115 }
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x0115 }
            r2.<init>()     // Catch:{ all -> 0x0115 }
            java.io.Closeable r2 = (java.io.Closeable) r2     // Catch:{ all -> 0x0115 }
            r5 = r2
            java.io.ByteArrayOutputStream r5 = (java.io.ByteArrayOutputStream) r5     // Catch:{ all -> 0x010e }
            T r7 = r6.element     // Catch:{ all -> 0x010e }
            android.graphics.Bitmap r7 = (android.graphics.Bitmap) r7     // Catch:{ all -> 0x010e }
            android.graphics.Bitmap$CompressFormat r8 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ all -> 0x010e }
            int r9 = r12.getQuality()     // Catch:{ all -> 0x010e }
            r11 = r5
            java.io.OutputStream r11 = (java.io.OutputStream) r11     // Catch:{ all -> 0x010e }
            r7.compress(r8, r9, r11)     // Catch:{ all -> 0x010e }
            java.lang.String r7 = r12.writeStreamToFile(r5)     // Catch:{ all -> 0x010e }
            T r6 = r6.element     // Catch:{ all -> 0x010e }
            android.graphics.Bitmap r6 = (android.graphics.Bitmap) r6     // Catch:{ all -> 0x010e }
            r6.recycle()     // Catch:{ all -> 0x010e }
            expo.modules.camera.next.PictureOptions r6 = r12.options     // Catch:{ all -> 0x010e }
            boolean r6 = r6.getExif()     // Catch:{ all -> 0x010e }
            if (r6 == 0) goto L_0x00d9
            androidx.exifinterface.media.ExifInterface r6 = new androidx.exifinterface.media.ExifInterface     // Catch:{ all -> 0x010e }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)     // Catch:{ all -> 0x010e }
            r6.<init>((java.lang.String) r7)     // Catch:{ all -> 0x010e }
            expo.modules.camera.CameraViewHelper.addExifData(r6, r4)     // Catch:{ all -> 0x010e }
        L_0x00d9:
            java.io.File r4 = new java.io.File     // Catch:{ all -> 0x010e }
            r4.<init>(r7)     // Catch:{ all -> 0x010e }
            android.net.Uri r4 = android.net.Uri.fromFile(r4)     // Catch:{ all -> 0x010e }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x010e }
            java.lang.String r6 = "toString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r6)     // Catch:{ all -> 0x010e }
            java.lang.String r6 = "uri"
            r3.putString(r6, r4)     // Catch:{ all -> 0x010e }
            expo.modules.camera.next.PictureOptions r4 = r12.options     // Catch:{ all -> 0x010e }
            boolean r4 = r4.getBase64()     // Catch:{ all -> 0x010e }
            if (r4 == 0) goto L_0x0105
            java.lang.String r4 = "base64"
            byte[] r5 = r5.toByteArray()     // Catch:{ all -> 0x010e }
            java.lang.String r5 = android.util.Base64.encodeToString(r5, r10)     // Catch:{ all -> 0x010e }
            r3.putString(r4, r5)     // Catch:{ all -> 0x010e }
        L_0x0105:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x010e }
            kotlin.io.CloseableKt.closeFinally(r2, r0)     // Catch:{ all -> 0x0115 }
            kotlin.io.CloseableKt.closeFinally(r1, r0)     // Catch:{ Exception -> 0x011c }
            return r3
        L_0x010e:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0110 }
        L_0x0110:
            r4 = move-exception
            kotlin.io.CloseableKt.closeFinally(r2, r3)     // Catch:{ all -> 0x0115 }
            throw r4     // Catch:{ all -> 0x0115 }
        L_0x0115:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0117 }
        L_0x0117:
            r3 = move-exception
            kotlin.io.CloseableKt.closeFinally(r1, r2)     // Catch:{ Exception -> 0x011c }
            throw r3     // Catch:{ Exception -> 0x011c }
        L_0x011c:
            r1 = move-exception
            boolean r2 = r1 instanceof android.content.res.Resources.NotFoundException
            java.lang.String r3 = "E_TAKING_PICTURE_FAILED"
            if (r2 == 0) goto L_0x012e
            expo.modules.kotlin.Promise r2 = r12.promise
            java.lang.String r4 = "Documents directory of the app could not be found."
            r5 = r1
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            r2.reject(r3, r4, r5)
            goto L_0x0156
        L_0x012e:
            boolean r2 = r1 instanceof java.io.IOException
            if (r2 == 0) goto L_0x013d
            expo.modules.kotlin.Promise r2 = r12.promise
            java.lang.String r4 = "An unknown I/O exception has occurred."
            r5 = r1
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            r2.reject(r3, r4, r5)
            goto L_0x0156
        L_0x013d:
            boolean r2 = r1 instanceof java.lang.IllegalArgumentException
            if (r2 == 0) goto L_0x014c
            expo.modules.kotlin.Promise r2 = r12.promise
            java.lang.String r4 = "An incompatible parameter has been passed in. "
            r5 = r1
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            r2.reject(r3, r4, r5)
            goto L_0x0156
        L_0x014c:
            expo.modules.kotlin.Promise r2 = r12.promise
            java.lang.String r4 = "An unknown exception has occurred."
            r5 = r1
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            r2.reject(r3, r4, r5)
        L_0x0156:
            r1.printStackTrace()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.camera.next.tasks.ResolveTakenPicture.processImage():android.os.Bundle");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0080, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r2, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0084, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final android.os.Bundle skipProcessing() {
        /*
            r8 = this;
            java.lang.String r0 = "E_TAKING_PICTURE_FAILED"
            r1 = 0
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x0094, Exception -> 0x0085 }
            r2.<init>()     // Catch:{ IOException -> 0x0094, Exception -> 0x0085 }
            java.io.Closeable r2 = (java.io.Closeable) r2     // Catch:{ IOException -> 0x0094, Exception -> 0x0085 }
            r3 = r2
            java.io.ByteArrayOutputStream r3 = (java.io.ByteArrayOutputStream) r3     // Catch:{ all -> 0x007e }
            byte[] r4 = r8.imageData     // Catch:{ all -> 0x007e }
            r3.write(r4)     // Catch:{ all -> 0x007e }
            java.lang.String r3 = r8.writeStreamToFile(r3)     // Catch:{ all -> 0x007e }
            if (r3 == 0) goto L_0x001e
            java.io.File r4 = new java.io.File     // Catch:{ all -> 0x007e }
            r4.<init>(r3)     // Catch:{ all -> 0x007e }
            goto L_0x001f
        L_0x001e:
            r4 = r1
        L_0x001f:
            android.net.Uri r4 = android.net.Uri.fromFile(r4)     // Catch:{ all -> 0x007e }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x007e }
            java.lang.String r5 = "toString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)     // Catch:{ all -> 0x007e }
            androidx.exifinterface.media.ExifInterface r5 = new androidx.exifinterface.media.ExifInterface     // Catch:{ all -> 0x007e }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)     // Catch:{ all -> 0x007e }
            r5.<init>((java.lang.String) r3)     // Catch:{ all -> 0x007e }
            android.os.Bundle r3 = new android.os.Bundle     // Catch:{ all -> 0x007e }
            r3.<init>()     // Catch:{ all -> 0x007e }
            java.lang.String r6 = "uri"
            r3.putString(r6, r4)     // Catch:{ all -> 0x007e }
            java.lang.String r4 = "width"
            java.lang.String r6 = "ImageWidth"
            r7 = -1
            int r6 = r5.getAttributeInt(r6, r7)     // Catch:{ all -> 0x007e }
            r3.putInt(r4, r6)     // Catch:{ all -> 0x007e }
            java.lang.String r4 = "height"
            java.lang.String r6 = "ImageLength"
            int r6 = r5.getAttributeInt(r6, r7)     // Catch:{ all -> 0x007e }
            r3.putInt(r4, r6)     // Catch:{ all -> 0x007e }
            expo.modules.camera.next.PictureOptions r4 = r8.options     // Catch:{ all -> 0x007e }
            boolean r4 = r4.getExif()     // Catch:{ all -> 0x007e }
            if (r4 == 0) goto L_0x0066
            android.os.Bundle r4 = expo.modules.camera.CameraViewHelper.getExifData(r5)     // Catch:{ all -> 0x007e }
            java.lang.String r5 = "exif"
            r3.putBundle(r5, r4)     // Catch:{ all -> 0x007e }
        L_0x0066:
            expo.modules.camera.next.PictureOptions r4 = r8.options     // Catch:{ all -> 0x007e }
            boolean r4 = r4.getBase64()     // Catch:{ all -> 0x007e }
            if (r4 == 0) goto L_0x007a
            java.lang.String r4 = "base64"
            byte[] r5 = r8.imageData     // Catch:{ all -> 0x007e }
            r6 = 2
            java.lang.String r5 = android.util.Base64.encodeToString(r5, r6)     // Catch:{ all -> 0x007e }
            r3.putString(r4, r5)     // Catch:{ all -> 0x007e }
        L_0x007a:
            kotlin.io.CloseableKt.closeFinally(r2, r1)     // Catch:{ IOException -> 0x0094, Exception -> 0x0085 }
            return r3
        L_0x007e:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0080 }
        L_0x0080:
            r4 = move-exception
            kotlin.io.CloseableKt.closeFinally(r2, r3)     // Catch:{ IOException -> 0x0094, Exception -> 0x0085 }
            throw r4     // Catch:{ IOException -> 0x0094, Exception -> 0x0085 }
        L_0x0085:
            r2 = move-exception
            expo.modules.kotlin.Promise r3 = r8.promise
            java.lang.String r4 = "An unknown exception has occurred."
            r5 = r2
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            r3.reject(r0, r4, r5)
            r2.printStackTrace()
            goto L_0x00a2
        L_0x0094:
            r2 = move-exception
            expo.modules.kotlin.Promise r3 = r8.promise
            java.lang.String r4 = "An unknown I/O exception has occurred."
            r5 = r2
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            r3.reject(r0, r4, r5)
            r2.printStackTrace()
        L_0x00a2:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.camera.next.tasks.ResolveTakenPicture.skipProcessing():android.os.Bundle");
    }

    /* access modifiers changed from: private */
    public final void onComplete(Bundle bundle) {
        if (bundle != null) {
            if (this.options.getFastMode()) {
                Bundle bundle2 = new Bundle();
                Integer id = this.options.getId();
                if (id != null) {
                    bundle2.putInt("id", id.intValue());
                    bundle2.putBundle("data", bundle);
                    this.pictureSavedDelegate.onPictureSaved(bundle2);
                    return;
                }
                throw new IllegalArgumentException("Required value was null.".toString());
            }
            this.promise.resolve(bundle);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0028, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r2, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002c, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.String writeStreamToFile(java.io.ByteArrayOutputStream r6) throws java.lang.Exception {
        /*
            r5 = this;
            r0 = 0
            expo.modules.camera.utils.FileSystemUtils r1 = expo.modules.camera.utils.FileSystemUtils.INSTANCE     // Catch:{ IOException -> 0x002d }
            java.io.File r2 = r5.directory     // Catch:{ IOException -> 0x002d }
            java.lang.String r3 = "Camera"
            java.lang.String r4 = ".jpg"
            java.lang.String r1 = r1.generateOutputPath(r2, r3, r4)     // Catch:{ IOException -> 0x002d }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x002d }
            r2.<init>(r1)     // Catch:{ IOException -> 0x002d }
            java.io.FileOutputStream r2 = io.sentry.instrumentation.file.SentryFileOutputStream.Factory.create((java.io.FileOutputStream) r2, (java.lang.String) r1)     // Catch:{ IOException -> 0x002d }
            java.io.Closeable r2 = (java.io.Closeable) r2     // Catch:{ IOException -> 0x002d }
            r3 = r2
            java.io.FileOutputStream r3 = (java.io.FileOutputStream) r3     // Catch:{ all -> 0x0026 }
            java.io.OutputStream r3 = (java.io.OutputStream) r3     // Catch:{ all -> 0x0026 }
            r6.writeTo(r3)     // Catch:{ all -> 0x0026 }
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0026 }
            kotlin.io.CloseableKt.closeFinally(r2, r0)     // Catch:{ IOException -> 0x002d }
            return r1
        L_0x0026:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0028 }
        L_0x0028:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r2, r6)     // Catch:{ IOException -> 0x002d }
            throw r1     // Catch:{ IOException -> 0x002d }
        L_0x002d:
            r6 = move-exception
            r6.printStackTrace()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.camera.next.tasks.ResolveTakenPicture.writeStreamToFile(java.io.ByteArrayOutputStream):java.lang.String");
    }

    private final Bitmap decodeBitmap(byte[] bArr, int i, BitmapFactory.Options options2) {
        if (i != 0) {
            return decodeAndRotateBitmap(bArr, getImageRotation(i), options2);
        }
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options2);
        Intrinsics.checkNotNull(decodeByteArray);
        return decodeByteArray;
    }

    private final Bitmap decodeAndRotateBitmap(byte[] bArr, int i, BitmapFactory.Options options2) {
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options2);
        Matrix matrix = new Matrix();
        matrix.postRotate((float) i);
        Bitmap createBitmap = Bitmap.createBitmap(decodeByteArray, 0, 0, decodeByteArray.getWidth(), decodeByteArray.getHeight(), matrix, true);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        return createBitmap;
    }
}

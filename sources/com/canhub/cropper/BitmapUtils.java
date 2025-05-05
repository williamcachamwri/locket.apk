package com.canhub.cropper;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Environment;
import android.util.Pair;
import androidx.exifinterface.media.ExifInterface;
import com.canhub.cropper.CropException;
import com.canhub.cropper.CropImageView;
import com.canhub.cropper.common.CommonVersionCheck;
import com.canhub.cropper.utils.GetUriForFileKt;
import com.facebook.imagepipeline.common.RotationOptions;
import io.sentry.android.core.SentryLogcatAdapter;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import javax.microedition.khronos.egl.EGL;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import org.apache.commons.io.IOUtils;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\bÀ\u0002\u0018\u00002\u00020\u0001:\u0002ijB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0002J\u0018\u0010)\u001a\u00020\f2\u0006\u0010*\u001a\u00020\f2\u0006\u0010+\u001a\u00020\fH\u0002J(\u0010,\u001a\u00020\f2\u0006\u0010*\u001a\u00020\f2\u0006\u0010+\u001a\u00020\f2\u0006\u0010-\u001a\u00020\f2\u0006\u0010.\u001a\u00020\fH\u0002J\u0012\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u000102H\u0002Jp\u00103\u001a\u0002042\u0006\u0010%\u001a\u00020&2\u0006\u00105\u001a\u00020$2\u0006\u00106\u001a\u00020\u000e2\u0006\u00107\u001a\u00020\f2\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\f2\u0006\u0010;\u001a\u00020\f2\u0006\u0010<\u001a\u00020\f2\u0006\u0010=\u001a\u00020\u00042\u0006\u0010*\u001a\u00020\f2\u0006\u0010+\u001a\u00020\f2\u0006\u0010>\u001a\u0002092\u0006\u0010?\u001a\u000209H\u0002Jx\u00103\u001a\u0002042\u0006\u0010%\u001a\u00020&2\u0006\u00105\u001a\u00020$2\u0006\u00106\u001a\u00020\u000e2\u0006\u00107\u001a\u00020\f2\u0006\u0010@\u001a\u00020\f2\u0006\u0010A\u001a\u00020\f2\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\f2\u0006\u0010;\u001a\u00020\f2\u0006\u0010-\u001a\u00020\f2\u0006\u0010.\u001a\u00020\f2\u0006\u0010>\u001a\u0002092\u0006\u0010?\u001a\u0002092\u0006\u0010<\u001a\u00020\fH\u0002Jp\u00103\u001a\u0002042\u0006\u0010%\u001a\u00020&2\b\u00105\u001a\u0004\u0018\u00010$2\u0006\u00106\u001a\u00020\u000e2\u0006\u00107\u001a\u00020\f2\u0006\u0010@\u001a\u00020\f2\u0006\u0010A\u001a\u00020\f2\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\f2\u0006\u0010;\u001a\u00020\f2\u0006\u0010-\u001a\u00020\f2\u0006\u0010.\u001a\u00020\f2\u0006\u0010>\u001a\u0002092\u0006\u0010?\u001a\u000209JH\u0010B\u001a\u0002042\b\u0010C\u001a\u0004\u0018\u00010\u001b2\u0006\u00106\u001a\u00020\u000e2\u0006\u00107\u001a\u00020\f2\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\f2\u0006\u0010;\u001a\u00020\f2\u0006\u0010>\u001a\u0002092\u0006\u0010?\u001a\u000209JR\u0010D\u001a\u0004\u0018\u00010\u001b2\u0006\u0010C\u001a\u00020\u001b2\u0006\u00106\u001a\u00020\u000e2\u0006\u00107\u001a\u00020\f2\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\f2\u0006\u0010;\u001a\u00020\f2\u0006\u0010E\u001a\u00020F2\u0006\u0010>\u001a\u0002092\u0006\u0010?\u001a\u000209H\u0002JD\u0010G\u001a\u0004\u0018\u00010\u001b2\b\u0010C\u001a\u0004\u0018\u00010\u001b2\u0006\u00106\u001a\u00020\u000e2\u0006\u0010=\u001a\u00020\u00042\u0006\u00107\u001a\u00020\f2\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\f2\u0006\u0010;\u001a\u00020\fH\u0002J\"\u0010H\u001a\u0004\u0018\u00010\u001b2\u0006\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020$2\u0006\u0010L\u001a\u00020MH\u0002J\u0018\u0010N\u001a\u00020M2\u0006\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020$H\u0002J&\u0010O\u001a\u0002042\u0006\u0010%\u001a\u00020&2\u0006\u0010K\u001a\u00020$2\u0006\u0010-\u001a\u00020\f2\u0006\u0010.\u001a\u00020\fJ8\u0010P\u001a\u0002042\u0006\u0010%\u001a\u00020&2\u0006\u0010K\u001a\u00020$2\u0006\u0010=\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\f2\u0006\u0010.\u001a\u00020\f2\u0006\u0010<\u001a\u00020\fH\u0002J \u0010Q\u001a\u0002002\u0006\u0010=\u001a\u00020\u00042\u0006\u0010:\u001a\u00020\f2\u0006\u0010;\u001a\u00020\fH\u0002J\u000e\u0010R\u001a\u00020F2\u0006\u00106\u001a\u00020\u000eJ\u000e\u0010S\u001a\u00020F2\u0006\u00106\u001a\u00020\u000eJ\u000e\u0010T\u001a\u00020F2\u0006\u00106\u001a\u00020\u000eJ6\u0010U\u001a\u00020\u00042\u0006\u00106\u001a\u00020\u000e2\u0006\u0010V\u001a\u00020\f2\u0006\u0010W\u001a\u00020\f2\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\f2\u0006\u0010;\u001a\u00020\fJ\u000e\u0010X\u001a\u00020F2\u0006\u00106\u001a\u00020\u000eJ\u000e\u0010Y\u001a\u00020F2\u0006\u00106\u001a\u00020\u000eJ\u000e\u0010Z\u001a\u00020F2\u0006\u00106\u001a\u00020\u000eJ\u000e\u0010[\u001a\u00020F2\u0006\u00106\u001a\u00020\u000eJ\u000e\u0010\\\u001a\u00020F2\u0006\u00106\u001a\u00020\u000eJ\"\u0010]\u001a\u00020^2\b\u0010C\u001a\u0004\u0018\u00010\u001b2\u0006\u0010%\u001a\u00020&2\b\u0010K\u001a\u0004\u0018\u00010$J\u0018\u0010]\u001a\u00020^2\b\u0010C\u001a\u0004\u0018\u00010\u001b2\u0006\u0010_\u001a\u00020`J(\u0010a\u001a\u00020\u001b2\b\u0010C\u001a\u0004\u0018\u00010\u001b2\u0006\u0010-\u001a\u00020\f2\u0006\u0010.\u001a\u00020\f2\u0006\u0010L\u001a\u00020bJ(\u0010c\u001a\u00020\u001b2\u0006\u0010C\u001a\u00020\u001b2\u0006\u0010d\u001a\u00020\f2\u0006\u0010>\u001a\u0002092\u0006\u0010?\u001a\u000209H\u0002J2\u0010e\u001a\u0004\u0018\u00010$2\u0006\u0010%\u001a\u00020&2\u0006\u0010C\u001a\u00020\u001b2\u0006\u0010'\u001a\u00020(2\u0006\u0010f\u001a\u00020\f2\b\u0010g\u001a\u0004\u0018\u00010$J$\u0010h\u001a\u0004\u0018\u00010$2\u0006\u0010%\u001a\u00020&2\b\u0010C\u001a\u0004\u0018\u00010\u001b2\b\u0010g\u001a\u0004\u0018\u00010$R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fXT¢\u0006\u0002\n\u0000R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\u0013\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\nR\u000e\u0010\u0015\u001a\u00020\u0016XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R.\u0010\u0018\u001a\u0016\u0012\u0004\u0012\u00020\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a\u0018\u00010\u0019X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\u00020\f8BX\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"¨\u0006k"}, d2 = {"Lcom/canhub/cropper/BitmapUtils;", "", "()V", "EMPTY_RECT", "Landroid/graphics/Rect;", "getEMPTY_RECT", "()Landroid/graphics/Rect;", "EMPTY_RECT_F", "Landroid/graphics/RectF;", "getEMPTY_RECT_F", "()Landroid/graphics/RectF;", "IMAGE_MAX_BITMAP_DIMENSION", "", "POINTS", "", "getPOINTS", "()[F", "POINTS2", "getPOINTS2", "RECT", "getRECT", "WRITE_AND_TRUNCATE", "", "mMaxTextureSize", "mStateBitmap", "Landroid/util/Pair;", "Ljava/lang/ref/WeakReference;", "Landroid/graphics/Bitmap;", "getMStateBitmap", "()Landroid/util/Pair;", "setMStateBitmap", "(Landroid/util/Pair;)V", "maxTextureSize", "getMaxTextureSize", "()I", "buildUri", "Landroid/net/Uri;", "context", "Landroid/content/Context;", "compressFormat", "Landroid/graphics/Bitmap$CompressFormat;", "calculateInSampleSizeByMaxTextureSize", "width", "height", "calculateInSampleSizeByReqestedSize", "reqWidth", "reqHeight", "closeSafe", "", "closeable", "Ljava/io/Closeable;", "cropBitmap", "Lcom/canhub/cropper/BitmapUtils$BitmapSampled;", "loadedImageUri", "points", "degreesRotated", "fixAspectRatio", "", "aspectRatioX", "aspectRatioY", "sampleMulti", "rect", "flipHorizontally", "flipVertically", "orgWidth", "orgHeight", "cropBitmapObjectHandleOOM", "bitmap", "cropBitmapObjectWithScale", "scale", "", "cropForRotatedImage", "decodeImage", "resolver", "Landroid/content/ContentResolver;", "uri", "options", "Landroid/graphics/BitmapFactory$Options;", "decodeImageForOption", "decodeSampledBitmap", "decodeSampledBitmapRegion", "fixRectForAspectRatio", "getRectBottom", "getRectCenterX", "getRectCenterY", "getRectFromPoints", "imageWidth", "imageHeight", "getRectHeight", "getRectLeft", "getRectRight", "getRectTop", "getRectWidth", "orientateBitmapByExif", "Lcom/canhub/cropper/BitmapUtils$RotateBitmapResult;", "exif", "Landroidx/exifinterface/media/ExifInterface;", "resizeBitmap", "Lcom/canhub/cropper/CropImageView$RequestSizeOptions;", "rotateAndFlipBitmapInt", "degrees", "writeBitmapToUri", "compressQuality", "customOutputUri", "writeTempStateStoreBitmap", "BitmapSampled", "RotateBitmapResult", "cropper_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: BitmapUtils.kt */
public final class BitmapUtils {
    private static final Rect EMPTY_RECT = new Rect();
    private static final RectF EMPTY_RECT_F = new RectF();
    private static final int IMAGE_MAX_BITMAP_DIMENSION = 2048;
    public static final BitmapUtils INSTANCE = new BitmapUtils();
    private static final float[] POINTS = new float[6];
    private static final float[] POINTS2 = new float[6];
    private static final RectF RECT = new RectF();
    private static final String WRITE_AND_TRUNCATE = "wt";
    private static int mMaxTextureSize;
    private static Pair<String, WeakReference<Bitmap>> mStateBitmap;

    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    /* compiled from: BitmapUtils.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Bitmap.CompressFormat.values().length];
            iArr[Bitmap.CompressFormat.JPEG.ordinal()] = 1;
            iArr[Bitmap.CompressFormat.PNG.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private BitmapUtils() {
    }

    public final Rect getEMPTY_RECT() {
        return EMPTY_RECT;
    }

    public final RectF getEMPTY_RECT_F() {
        return EMPTY_RECT_F;
    }

    public final RectF getRECT() {
        return RECT;
    }

    public final float[] getPOINTS() {
        return POINTS;
    }

    public final float[] getPOINTS2() {
        return POINTS2;
    }

    public final Pair<String, WeakReference<Bitmap>> getMStateBitmap() {
        return mStateBitmap;
    }

    public final void setMStateBitmap(Pair<String, WeakReference<Bitmap>> pair) {
        mStateBitmap = pair;
    }

    public final RotateBitmapResult orientateBitmapByExif(Bitmap bitmap, Context context, Uri uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        ExifInterface exifInterface = null;
        try {
            ContentResolver contentResolver = context.getContentResolver();
            Intrinsics.checkNotNull(uri);
            InputStream openInputStream = contentResolver.openInputStream(uri);
            if (openInputStream != null) {
                ExifInterface exifInterface2 = new ExifInterface(openInputStream);
                try {
                    openInputStream.close();
                } catch (Exception unused) {
                }
                exifInterface = exifInterface2;
            }
        } catch (Exception unused2) {
        }
        return exifInterface != null ? orientateBitmapByExif(bitmap, exifInterface) : new RotateBitmapResult(bitmap, 0, false, false, 12, (DefaultConstructorMarker) null);
    }

    public final RotateBitmapResult orientateBitmapByExif(Bitmap bitmap, ExifInterface exifInterface) {
        Intrinsics.checkNotNullParameter(exifInterface, "exif");
        boolean z = true;
        int attributeInt = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
        int i = attributeInt != 3 ? (attributeInt == 5 || attributeInt == 6 || attributeInt == 7) ? 90 : attributeInt != 8 ? 0 : 270 : RotationOptions.ROTATE_180;
        boolean z2 = attributeInt == 2 || attributeInt == 5;
        if (!(attributeInt == 4 || attributeInt == 7)) {
            z = false;
        }
        return new RotateBitmapResult(bitmap, i, z2, z);
    }

    public final BitmapSampled decodeSampledBitmap(Context context, Uri uri, int i, int i2) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        try {
            ContentResolver contentResolver = context.getContentResolver();
            Intrinsics.checkNotNullExpressionValue(contentResolver, "resolver");
            BitmapFactory.Options decodeImageForOption = decodeImageForOption(contentResolver, uri);
            if (decodeImageForOption.outWidth == -1) {
                if (decodeImageForOption.outHeight == -1) {
                    throw new RuntimeException("File is not a picture");
                }
            }
            decodeImageForOption.inSampleSize = Math.max(calculateInSampleSizeByReqestedSize(decodeImageForOption.outWidth, decodeImageForOption.outHeight, i, i2), calculateInSampleSizeByMaxTextureSize(decodeImageForOption.outWidth, decodeImageForOption.outHeight));
            return new BitmapSampled(decodeImage(contentResolver, uri, decodeImageForOption), decodeImageForOption.inSampleSize);
        } catch (Exception e) {
            throw new CropException.FailedToLoadBitmap(uri, e.getMessage());
        }
    }

    public final BitmapSampled cropBitmapObjectHandleOOM(Bitmap bitmap, float[] fArr, int i, boolean z, int i2, int i3, boolean z2, boolean z3) {
        Intrinsics.checkNotNullParameter(fArr, "points");
        int i4 = 1;
        do {
            try {
                Intrinsics.checkNotNull(bitmap);
                return new BitmapSampled(cropBitmapObjectWithScale(bitmap, fArr, i, z, i2, i3, ((float) 1) / ((float) i4), z2, z3), i4);
            } catch (OutOfMemoryError e) {
                i4 *= 2;
                if (i4 > 8) {
                    throw e;
                }
            }
        } while (i4 > 8);
        throw e;
    }

    private final Bitmap cropBitmapObjectWithScale(Bitmap bitmap, float[] fArr, int i, boolean z, int i2, int i3, float f, boolean z2, boolean z3) {
        Bitmap bitmap2 = bitmap;
        int i4 = i;
        float f2 = f;
        Rect rectFromPoints = getRectFromPoints(fArr, bitmap.getWidth(), bitmap.getHeight(), z, i2, i3);
        Matrix matrix = new Matrix();
        matrix.setRotate((float) i4, ((float) bitmap.getWidth()) / 2.0f, ((float) bitmap.getHeight()) / 2.0f);
        float f3 = z2 ? -f2 : f2;
        if (z3) {
            f2 = -f2;
        }
        matrix.postScale(f3, f2);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, rectFromPoints.left, rectFromPoints.top, rectFromPoints.width(), rectFromPoints.height(), matrix, true);
        if (Intrinsics.areEqual((Object) createBitmap, (Object) bitmap2)) {
            createBitmap = bitmap2.copy(bitmap.getConfig(), false);
        }
        Bitmap bitmap3 = createBitmap;
        return i4 % 90 != 0 ? cropForRotatedImage(bitmap3, fArr, rectFromPoints, i, z, i2, i3) : bitmap3;
    }

    public final BitmapSampled cropBitmap(Context context, Uri uri, float[] fArr, int i, int i2, int i3, boolean z, int i4, int i5, int i6, int i7, boolean z2, boolean z3) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fArr, "points");
        int i8 = 1;
        while (true) {
            try {
                Intrinsics.checkNotNull(uri);
                return cropBitmap(context, uri, fArr, i, i2, i3, z, i4, i5, i6, i7, z2, z3, i8);
            } catch (OutOfMemoryError e) {
                int i9 = i8 * 2;
                if (i9 <= 16) {
                    Context context2 = context;
                    float[] fArr2 = fArr;
                    i8 = i9;
                } else {
                    throw new RuntimeException("Failed to handle OOM by sampling (" + i9 + "): " + uri + IOUtils.LINE_SEPARATOR_WINDOWS + e.getMessage(), e);
                }
            }
        }
    }

    public final float getRectLeft(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "points");
        return Math.min(Math.min(Math.min(fArr[0], fArr[2]), fArr[4]), fArr[6]);
    }

    public final float getRectTop(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "points");
        return Math.min(Math.min(Math.min(fArr[1], fArr[3]), fArr[5]), fArr[7]);
    }

    public final float getRectRight(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "points");
        return Math.max(Math.max(Math.max(fArr[0], fArr[2]), fArr[4]), fArr[6]);
    }

    public final float getRectBottom(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "points");
        return Math.max(Math.max(Math.max(fArr[1], fArr[3]), fArr[5]), fArr[7]);
    }

    public final float getRectWidth(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "points");
        return getRectRight(fArr) - getRectLeft(fArr);
    }

    public final float getRectHeight(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "points");
        return getRectBottom(fArr) - getRectTop(fArr);
    }

    public final float getRectCenterX(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "points");
        return (getRectRight(fArr) + getRectLeft(fArr)) / 2.0f;
    }

    public final float getRectCenterY(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "points");
        return (getRectBottom(fArr) + getRectTop(fArr)) / 2.0f;
    }

    public final Rect getRectFromPoints(float[] fArr, int i, int i2, boolean z, int i3, int i4) {
        Intrinsics.checkNotNullParameter(fArr, "points");
        Rect rect = new Rect(MathKt.roundToInt(Math.max(0.0f, getRectLeft(fArr))), MathKt.roundToInt(Math.max(0.0f, getRectTop(fArr))), MathKt.roundToInt(Math.min((float) i, getRectRight(fArr))), MathKt.roundToInt(Math.min((float) i2, getRectBottom(fArr))));
        if (z) {
            fixRectForAspectRatio(rect, i3, i4);
        }
        return rect;
    }

    private final void fixRectForAspectRatio(Rect rect, int i, int i2) {
        if (i == i2 && rect.width() != rect.height()) {
            if (rect.height() > rect.width()) {
                rect.bottom -= rect.height() - rect.width();
            } else {
                rect.right -= rect.width() - rect.height();
            }
        }
    }

    public final Uri writeTempStateStoreBitmap(Context context, Bitmap bitmap, Uri uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Intrinsics.checkNotNull(bitmap);
            return writeBitmapToUri(context, bitmap, Bitmap.CompressFormat.JPEG, 95, uri);
        } catch (Exception e) {
            SentryLogcatAdapter.w("AIC", "Failed to write bitmap to temp file for image-cropper save instance state", e);
            Uri uri2 = null;
            return null;
        }
    }

    public final Uri writeBitmapToUri(Context context, Bitmap bitmap, Bitmap.CompressFormat compressFormat, int i, Uri uri) throws FileNotFoundException {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Intrinsics.checkNotNullParameter(compressFormat, "compressFormat");
        if (uri == null) {
            uri = buildUri(context, compressFormat);
        }
        OutputStream outputStream = null;
        try {
            ContentResolver contentResolver = context.getContentResolver();
            Intrinsics.checkNotNull(uri);
            outputStream = contentResolver.openOutputStream(uri, WRITE_AND_TRUNCATE);
            bitmap.compress(compressFormat, i, outputStream);
            return uri;
        } finally {
            closeSafe(outputStream);
        }
    }

    private final Uri buildUri(Context context, Bitmap.CompressFormat compressFormat) {
        try {
            int i = WhenMappings.$EnumSwitchMapping$0[compressFormat.ordinal()];
            String str = i != 1 ? i != 2 ? ".webp" : ".png" : ".jpg";
            if (!CommonVersionCheck.INSTANCE.isAtLeastQ29()) {
                return Uri.fromFile(File.createTempFile("cropped", str, context.getCacheDir()));
            }
            try {
                File createTempFile = File.createTempFile("cropped", str, context.getExternalFilesDir(Environment.DIRECTORY_PICTURES));
                Intrinsics.checkNotNullExpressionValue(createTempFile, "file");
                return GetUriForFileKt.getUriForFile(context, createTempFile);
            } catch (Exception e) {
                SentryLogcatAdapter.e("AIC", String.valueOf(e.getMessage()));
                File createTempFile2 = File.createTempFile("cropped", str, context.getCacheDir());
                Intrinsics.checkNotNullExpressionValue(createTempFile2, "file");
                return GetUriForFileKt.getUriForFile(context, createTempFile2);
            }
        } catch (IOException e2) {
            throw new RuntimeException("Failed to create temp file for output image", e2);
        }
    }

    public final Bitmap resizeBitmap(Bitmap bitmap, int i, int i2, CropImageView.RequestSizeOptions requestSizeOptions) {
        Bitmap bitmap2;
        Intrinsics.checkNotNullParameter(requestSizeOptions, "options");
        if (i > 0 && i2 > 0) {
            try {
                if (requestSizeOptions == CropImageView.RequestSizeOptions.RESIZE_FIT || requestSizeOptions == CropImageView.RequestSizeOptions.RESIZE_INSIDE || requestSizeOptions == CropImageView.RequestSizeOptions.RESIZE_EXACT) {
                    if (requestSizeOptions == CropImageView.RequestSizeOptions.RESIZE_EXACT) {
                        Intrinsics.checkNotNull(bitmap);
                        bitmap2 = Bitmap.createScaledBitmap(bitmap, i, i2, false);
                    } else {
                        Intrinsics.checkNotNull(bitmap);
                        float width = (float) bitmap.getWidth();
                        float height = (float) bitmap.getHeight();
                        float max = Math.max(width / ((float) i), height / ((float) i2));
                        if (max <= 1.0f) {
                            if (requestSizeOptions != CropImageView.RequestSizeOptions.RESIZE_FIT) {
                                bitmap2 = null;
                            }
                        }
                        bitmap2 = Bitmap.createScaledBitmap(bitmap, (int) (width / max), (int) (height / max), false);
                    }
                    if (bitmap2 != null) {
                        if (!Intrinsics.areEqual((Object) bitmap2, (Object) bitmap)) {
                            bitmap.recycle();
                        }
                        return bitmap2;
                    }
                }
            } catch (Exception e) {
                SentryLogcatAdapter.w("AIC", "Failed to resize cropped image, return bitmap before resize", e);
            }
        }
        Intrinsics.checkNotNull(bitmap);
        return bitmap;
    }

    private final BitmapSampled cropBitmap(Context context, Uri uri, float[] fArr, int i, int i2, int i3, boolean z, int i4, int i5, int i6, int i7, boolean z2, boolean z3, int i8) {
        int i9;
        int i10;
        int i11;
        int i12 = i;
        Rect rectFromPoints = getRectFromPoints(fArr, i2, i3, z, i4, i5);
        if (i6 > 0) {
            i9 = i6;
        } else {
            i9 = rectFromPoints.width();
        }
        if (i7 > 0) {
            i10 = i7;
        } else {
            i10 = rectFromPoints.height();
        }
        Bitmap bitmap = null;
        try {
            BitmapSampled decodeSampledBitmapRegion = decodeSampledBitmapRegion(context, uri, rectFromPoints, i9, i10, i8);
            bitmap = decodeSampledBitmapRegion.getBitmap();
            i11 = decodeSampledBitmapRegion.getSampleSize();
        } catch (Exception unused) {
            i11 = 1;
        }
        int i13 = i11;
        if (bitmap != null) {
            try {
                Bitmap rotateAndFlipBitmapInt = rotateAndFlipBitmapInt(bitmap, i12, z2, z3);
                try {
                    if (i12 % 90 != 0) {
                        rotateAndFlipBitmapInt = cropForRotatedImage(rotateAndFlipBitmapInt, fArr, rectFromPoints, i, z, i4, i5);
                    }
                    return new BitmapSampled(rotateAndFlipBitmapInt, i13);
                } catch (OutOfMemoryError e) {
                    e = e;
                    bitmap = rotateAndFlipBitmapInt;
                    bitmap.recycle();
                    throw e;
                }
            } catch (OutOfMemoryError e2) {
                e = e2;
                bitmap.recycle();
                throw e;
            }
        } else {
            return cropBitmap(context, uri, fArr, i, z, i4, i5, i8, rectFromPoints, i9, i10, z2, z3);
        }
    }

    private final BitmapSampled cropBitmap(Context context, Uri uri, float[] fArr, int i, boolean z, int i2, int i3, int i4, Rect rect, int i5, int i6, boolean z2, boolean z3) {
        Bitmap decodeImage;
        Uri uri2 = uri;
        float[] fArr2 = fArr;
        Bitmap bitmap = null;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            int calculateInSampleSizeByReqestedSize = i4 * calculateInSampleSizeByReqestedSize(rect.width(), rect.height(), i5, i6);
            options.inSampleSize = calculateInSampleSizeByReqestedSize;
            ContentResolver contentResolver = context.getContentResolver();
            Intrinsics.checkNotNullExpressionValue(contentResolver, "context.contentResolver");
            decodeImage = decodeImage(contentResolver, uri2, options);
            if (decodeImage != null) {
                int length = fArr2.length;
                float[] fArr3 = new float[length];
                System.arraycopy(fArr2, 0, fArr3, 0, fArr2.length);
                for (int i7 = 0; i7 < length; i7++) {
                    fArr3[i7] = fArr3[i7] / ((float) options.inSampleSize);
                }
                bitmap = cropBitmapObjectWithScale(decodeImage, fArr3, i, z, i2, i3, 1.0f, z2, z3);
                if (!Intrinsics.areEqual((Object) bitmap, (Object) decodeImage)) {
                    decodeImage.recycle();
                }
            }
            return new BitmapSampled(bitmap, calculateInSampleSizeByReqestedSize);
        } catch (OutOfMemoryError e) {
            if (bitmap != null) {
                bitmap.recycle();
            }
            throw e;
        } catch (Exception e2) {
            throw new CropException.FailedToLoadBitmap(uri2, e2.getMessage());
        } catch (Throwable th) {
            if (!Intrinsics.areEqual((Object) null, (Object) decodeImage)) {
                decodeImage.recycle();
            }
            throw th;
        }
    }

    private final BitmapFactory.Options decodeImageForOption(ContentResolver contentResolver, Uri uri) throws FileNotFoundException {
        InputStream inputStream;
        try {
            inputStream = contentResolver.openInputStream(uri);
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(inputStream, EMPTY_RECT, options);
                options.inJustDecodeBounds = false;
                closeSafe(inputStream);
                return options;
            } catch (Throwable th) {
                th = th;
                closeSafe(inputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
            closeSafe(inputStream);
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:6|7|8|(3:14|10|11)) */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002b, code lost:
        closeSafe((java.io.Closeable) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0030, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0011, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
        r5.inSampleSize *= 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0019, code lost:
        closeSafe((java.io.Closeable) null);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0013 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final android.graphics.Bitmap decodeImage(android.content.ContentResolver r3, android.net.Uri r4, android.graphics.BitmapFactory.Options r5) throws java.io.FileNotFoundException {
        /*
            r2 = this;
        L_0x0000:
            r0 = 0
            java.io.InputStream r0 = r3.openInputStream(r4)     // Catch:{ OutOfMemoryError -> 0x0013 }
            android.graphics.Rect r1 = EMPTY_RECT     // Catch:{ OutOfMemoryError -> 0x0013 }
            android.graphics.Bitmap r3 = android.graphics.BitmapFactory.decodeStream(r0, r1, r5)     // Catch:{ OutOfMemoryError -> 0x0013 }
            java.io.Closeable r0 = (java.io.Closeable) r0
            r2.closeSafe(r0)
            return r3
        L_0x0011:
            r3 = move-exception
            goto L_0x002b
        L_0x0013:
            int r1 = r5.inSampleSize     // Catch:{ all -> 0x0011 }
            int r1 = r1 * 2
            r5.inSampleSize = r1     // Catch:{ all -> 0x0011 }
            java.io.Closeable r0 = (java.io.Closeable) r0
            r2.closeSafe(r0)
            int r0 = r5.inSampleSize
            r1 = 512(0x200, float:7.175E-43)
            if (r0 > r1) goto L_0x0025
            goto L_0x0000
        L_0x0025:
            com.canhub.cropper.CropException$FailedToDecodeImage r3 = new com.canhub.cropper.CropException$FailedToDecodeImage
            r3.<init>(r4)
            throw r3
        L_0x002b:
            java.io.Closeable r0 = (java.io.Closeable) r0
            r2.closeSafe(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.canhub.cropper.BitmapUtils.decodeImage(android.content.ContentResolver, android.net.Uri, android.graphics.BitmapFactory$Options):android.graphics.Bitmap");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0032, code lost:
        return new com.canhub.cropper.BitmapUtils.BitmapSampled(r7.decodeRegion(r6, r0), r0.inSampleSize);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0033 */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0046 A[Catch:{ Exception -> 0x0051 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.canhub.cropper.BitmapUtils.BitmapSampled decodeSampledBitmapRegion(android.content.Context r4, android.net.Uri r5, android.graphics.Rect r6, int r7, int r8, int r9) {
        /*
            r3 = this;
            android.graphics.BitmapFactory$Options r0 = new android.graphics.BitmapFactory$Options     // Catch:{ Exception -> 0x0051 }
            r0.<init>()     // Catch:{ Exception -> 0x0051 }
            int r1 = r6.width()     // Catch:{ Exception -> 0x0051 }
            int r2 = r6.height()     // Catch:{ Exception -> 0x0051 }
            int r7 = r3.calculateInSampleSizeByReqestedSize(r1, r2, r7, r8)     // Catch:{ Exception -> 0x0051 }
            int r9 = r9 * r7
            r0.inSampleSize = r9     // Catch:{ Exception -> 0x0051 }
            android.content.ContentResolver r4 = r4.getContentResolver()     // Catch:{ Exception -> 0x0051 }
            java.io.InputStream r4 = r4.openInputStream(r5)     // Catch:{ Exception -> 0x0051 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)     // Catch:{ Exception -> 0x0051 }
            r7 = 0
            android.graphics.BitmapRegionDecoder r7 = android.graphics.BitmapRegionDecoder.newInstance(r4, r7)     // Catch:{ Exception -> 0x0051 }
        L_0x0024:
            com.canhub.cropper.BitmapUtils$BitmapSampled r8 = new com.canhub.cropper.BitmapUtils$BitmapSampled     // Catch:{ OutOfMemoryError -> 0x0033 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)     // Catch:{ OutOfMemoryError -> 0x0033 }
            android.graphics.Bitmap r9 = r7.decodeRegion(r6, r0)     // Catch:{ OutOfMemoryError -> 0x0033 }
            int r1 = r0.inSampleSize     // Catch:{ OutOfMemoryError -> 0x0033 }
            r8.<init>(r9, r1)     // Catch:{ OutOfMemoryError -> 0x0033 }
            return r8
        L_0x0033:
            int r8 = r0.inSampleSize     // Catch:{ Exception -> 0x0051 }
            int r8 = r8 * 2
            r0.inSampleSize = r8     // Catch:{ Exception -> 0x0051 }
            int r8 = r0.inSampleSize     // Catch:{ Exception -> 0x0051 }
            r9 = 512(0x200, float:7.175E-43)
            if (r8 <= r9) goto L_0x0024
            java.io.Closeable r4 = (java.io.Closeable) r4     // Catch:{ Exception -> 0x0051 }
            r3.closeSafe(r4)     // Catch:{ Exception -> 0x0051 }
            if (r7 == 0) goto L_0x0049
            r7.recycle()     // Catch:{ Exception -> 0x0051 }
        L_0x0049:
            com.canhub.cropper.BitmapUtils$BitmapSampled r4 = new com.canhub.cropper.BitmapUtils$BitmapSampled
            r5 = 0
            r6 = 1
            r4.<init>(r5, r6)
            return r4
        L_0x0051:
            r4 = move-exception
            com.canhub.cropper.CropException$FailedToLoadBitmap r6 = new com.canhub.cropper.CropException$FailedToLoadBitmap
            java.lang.String r4 = r4.getMessage()
            r6.<init>(r5, r4)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.canhub.cropper.BitmapUtils.decodeSampledBitmapRegion(android.content.Context, android.net.Uri, android.graphics.Rect, int, int, int):com.canhub.cropper.BitmapUtils$BitmapSampled");
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x002c  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0085 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final android.graphics.Bitmap cropForRotatedImage(android.graphics.Bitmap r14, float[] r15, android.graphics.Rect r16, int r17, boolean r18, int r19, int r20) {
        /*
            r13 = this;
            r0 = r14
            r1 = r15
            r2 = r16
            r3 = r17
            int r4 = r3 % 90
            if (r4 == 0) goto L_0x00b7
            double r4 = (double) r3
            double r4 = java.lang.Math.toRadians(r4)
            r6 = 1
            r7 = 0
            r8 = 90
            if (r3 < r8) goto L_0x0026
            r8 = 181(0xb5, float:2.54E-43)
            if (r8 > r3) goto L_0x001f
            r8 = 270(0x10e, float:3.78E-43)
            if (r3 >= r8) goto L_0x001f
            r3 = r6
            goto L_0x0020
        L_0x001f:
            r3 = r7
        L_0x0020:
            if (r3 == 0) goto L_0x0023
            goto L_0x0026
        L_0x0023:
            int r3 = r2.right
            goto L_0x0028
        L_0x0026:
            int r3 = r2.left
        L_0x0028:
            r8 = r7
        L_0x0029:
            int r9 = r1.length
            if (r8 >= r9) goto L_0x0085
            r9 = r1[r8]
            int r10 = r3 + -1
            float r10 = (float) r10
            int r10 = (r9 > r10 ? 1 : (r9 == r10 ? 0 : -1))
            if (r10 < 0) goto L_0x0082
            int r10 = r3 + 1
            float r10 = (float) r10
            int r9 = (r9 > r10 ? 1 : (r9 == r10 ? 0 : -1))
            if (r9 > 0) goto L_0x0082
            double r9 = java.lang.Math.sin(r4)
            int r3 = r2.bottom
            float r3 = (float) r3
            int r8 = r8 + r6
            r6 = r1[r8]
            float r3 = r3 - r6
            double r6 = (double) r3
            double r9 = r9 * r6
            double r6 = java.lang.Math.abs(r9)
            int r7 = (int) r6
            double r9 = java.lang.Math.cos(r4)
            r3 = r1[r8]
            int r6 = r2.top
            float r6 = (float) r6
            float r3 = r3 - r6
            double r11 = (double) r3
            double r9 = r9 * r11
            double r9 = java.lang.Math.abs(r9)
            int r3 = (int) r9
            r6 = r1[r8]
            int r9 = r2.top
            float r9 = (float) r9
            float r6 = r6 - r9
            double r9 = (double) r6
            double r11 = java.lang.Math.sin(r4)
            double r9 = r9 / r11
            double r9 = java.lang.Math.abs(r9)
            int r6 = (int) r9
            int r9 = r2.bottom
            float r9 = (float) r9
            r1 = r1[r8]
            float r9 = r9 - r1
            double r8 = (double) r9
            double r4 = java.lang.Math.cos(r4)
            double r8 = r8 / r4
            double r4 = java.lang.Math.abs(r8)
            int r1 = (int) r4
            goto L_0x0088
        L_0x0082:
            int r8 = r8 + 2
            goto L_0x0029
        L_0x0085:
            r1 = r7
            r3 = r1
            r6 = r3
        L_0x0088:
            int r6 = r6 + r7
            int r1 = r1 + r3
            r2.set(r7, r3, r6, r1)
            r1 = r13
            if (r18 == 0) goto L_0x0097
            r3 = r19
            r4 = r20
            r13.fixRectForAspectRatio(r2, r3, r4)
        L_0x0097:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r14)
            int r3 = r2.left
            int r4 = r2.top
            int r5 = r16.width()
            int r2 = r16.height()
            android.graphics.Bitmap r2 = android.graphics.Bitmap.createBitmap(r14, r3, r4, r5, r2)
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r2)
            if (r3 != 0) goto L_0x00b5
            if (r0 == 0) goto L_0x00b5
            r14.recycle()
        L_0x00b5:
            r0 = r2
            goto L_0x00b8
        L_0x00b7:
            r1 = r13
        L_0x00b8:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.canhub.cropper.BitmapUtils.cropForRotatedImage(android.graphics.Bitmap, float[], android.graphics.Rect, int, boolean, int, int):android.graphics.Bitmap");
    }

    private final int calculateInSampleSizeByReqestedSize(int i, int i2, int i3, int i4) {
        int i5 = 1;
        if (i2 > i4 || i > i3) {
            while ((i2 / 2) / i5 > i4 && (i / 2) / i5 > i3) {
                i5 *= 2;
            }
        }
        return i5;
    }

    private final int calculateInSampleSizeByMaxTextureSize(int i, int i2) {
        if (mMaxTextureSize == 0) {
            mMaxTextureSize = getMaxTextureSize();
        }
        int i3 = 1;
        if (mMaxTextureSize > 0) {
            while (true) {
                int i4 = i2 / i3;
                int i5 = mMaxTextureSize;
                if (i4 <= i5 && i / i3 <= i5) {
                    break;
                }
                i3 *= 2;
            }
        }
        return i3;
    }

    private final Bitmap rotateAndFlipBitmapInt(Bitmap bitmap, int i, boolean z, boolean z2) {
        if (i <= 0 && !z && !z2) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate((float) i);
        int i2 = -1;
        float f = (float) (z ? -1 : 1);
        if (!z2) {
            i2 = 1;
        }
        matrix.postScale(f, (float) i2);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
        if (!Intrinsics.areEqual((Object) createBitmap, (Object) bitmap)) {
            bitmap.recycle();
        }
        Intrinsics.checkNotNullExpressionValue(createBitmap, "{\n            val matrix…      newBitmap\n        }");
        return createBitmap;
    }

    private final int getMaxTextureSize() {
        try {
            EGL egl = EGLContext.getEGL();
            Intrinsics.checkNotNull(egl, "null cannot be cast to non-null type javax.microedition.khronos.egl.EGL10");
            EGL10 egl10 = (EGL10) egl;
            EGLDisplay eglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            egl10.eglInitialize(eglGetDisplay, new int[2]);
            int[] iArr = new int[1];
            egl10.eglGetConfigs(eglGetDisplay, (EGLConfig[]) null, 0, iArr);
            int i = iArr[0];
            EGLConfig[] eGLConfigArr = new EGLConfig[i];
            egl10.eglGetConfigs(eglGetDisplay, eGLConfigArr, i, iArr);
            int[] iArr2 = new int[1];
            int i2 = iArr[0];
            int i3 = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                egl10.eglGetConfigAttrib(eglGetDisplay, eGLConfigArr[i4], 12332, iArr2);
                int i5 = iArr2[0];
                if (i3 < i5) {
                    i3 = i5;
                }
            }
            egl10.eglTerminate(eglGetDisplay);
            return Math.max(i3, 2048);
        } catch (Exception unused) {
            return 2048;
        }
    }

    private final void closeSafe(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/canhub/cropper/BitmapUtils$BitmapSampled;", "", "bitmap", "Landroid/graphics/Bitmap;", "sampleSize", "", "(Landroid/graphics/Bitmap;I)V", "getBitmap", "()Landroid/graphics/Bitmap;", "getSampleSize", "()I", "cropper_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: BitmapUtils.kt */
    public static final class BitmapSampled {
        private final Bitmap bitmap;
        private final int sampleSize;

        public BitmapSampled(Bitmap bitmap2, int i) {
            this.bitmap = bitmap2;
            this.sampleSize = i;
        }

        public final Bitmap getBitmap() {
            return this.bitmap;
        }

        public final int getSampleSize() {
            return this.sampleSize;
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u0001B+\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000f¨\u0006\u0011"}, d2 = {"Lcom/canhub/cropper/BitmapUtils$RotateBitmapResult;", "", "bitmap", "Landroid/graphics/Bitmap;", "degrees", "", "flipHorizontally", "", "flipVertically", "(Landroid/graphics/Bitmap;IZZ)V", "getBitmap", "()Landroid/graphics/Bitmap;", "getDegrees", "()I", "getFlipHorizontally", "()Z", "getFlipVertically", "cropper_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: BitmapUtils.kt */
    public static final class RotateBitmapResult {
        private final Bitmap bitmap;
        private final int degrees;
        private final boolean flipHorizontally;
        private final boolean flipVertically;

        public RotateBitmapResult(Bitmap bitmap2, int i, boolean z, boolean z2) {
            this.bitmap = bitmap2;
            this.degrees = i;
            this.flipHorizontally = z;
            this.flipVertically = z2;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ RotateBitmapResult(Bitmap bitmap2, int i, boolean z, boolean z2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(bitmap2, i, (i2 & 4) != 0 ? false : z, (i2 & 8) != 0 ? false : z2);
        }

        public final Bitmap getBitmap() {
            return this.bitmap;
        }

        public final int getDegrees() {
            return this.degrees;
        }

        public final boolean getFlipHorizontally() {
            return this.flipHorizontally;
        }

        public final boolean getFlipVertically() {
            return this.flipVertically;
        }
    }
}

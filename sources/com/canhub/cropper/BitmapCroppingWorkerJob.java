package com.canhub.cropper;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import com.canhub.cropper.CropImageView;
import java.lang.ref.WeakReference;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001/B©\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0010\u001a\u00020\u000e\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u000e\u0012\u0006\u0010\u0014\u001a\u00020\u000e\u0012\u0006\u0010\u0015\u001a\u00020\u000e\u0012\u0006\u0010\u0016\u001a\u00020\u000e\u0012\u0006\u0010\u0017\u001a\u00020\u0012\u0012\u0006\u0010\u0018\u001a\u00020\u0012\u0012\u0006\u0010\u0019\u001a\u00020\u001a\u0012\u0006\u0010\u001b\u001a\u00020\u001c\u0012\u0006\u0010\u001d\u001a\u00020\u000e\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\u001fJ\u0006\u0010(\u001a\u00020)J\u0019\u0010*\u001a\u00020)2\u0006\u0010+\u001a\u00020,H@ø\u0001\u0000¢\u0006\u0002\u0010-J\u0006\u0010.\u001a\u00020)R\u000e\u0010\u0013\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\u00020!8VX\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'\u0002\u0004\n\u0002\b\u0019¨\u00060"}, d2 = {"Lcom/canhub/cropper/BitmapCroppingWorkerJob;", "Lkotlinx/coroutines/CoroutineScope;", "context", "Landroid/content/Context;", "cropImageViewReference", "Ljava/lang/ref/WeakReference;", "Lcom/canhub/cropper/CropImageView;", "uri", "Landroid/net/Uri;", "bitmap", "Landroid/graphics/Bitmap;", "cropPoints", "", "degreesRotated", "", "orgWidth", "orgHeight", "fixAspectRatio", "", "aspectRatioX", "aspectRatioY", "reqWidth", "reqHeight", "flipHorizontally", "flipVertically", "options", "Lcom/canhub/cropper/CropImageView$RequestSizeOptions;", "saveCompressFormat", "Landroid/graphics/Bitmap$CompressFormat;", "saveCompressQuality", "customOutputUri", "(Landroid/content/Context;Ljava/lang/ref/WeakReference;Landroid/net/Uri;Landroid/graphics/Bitmap;[FIIIZIIIIZZLcom/canhub/cropper/CropImageView$RequestSizeOptions;Landroid/graphics/Bitmap$CompressFormat;ILandroid/net/Uri;)V", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "job", "Lkotlinx/coroutines/Job;", "getUri", "()Landroid/net/Uri;", "cancel", "", "onPostExecute", "result", "Lcom/canhub/cropper/BitmapCroppingWorkerJob$Result;", "(Lcom/canhub/cropper/BitmapCroppingWorkerJob$Result;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "start", "Result", "cropper_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: BitmapCroppingWorkerJob.kt */
public final class BitmapCroppingWorkerJob implements CoroutineScope {
    /* access modifiers changed from: private */
    public final int aspectRatioX;
    /* access modifiers changed from: private */
    public final int aspectRatioY;
    /* access modifiers changed from: private */
    public final Bitmap bitmap;
    /* access modifiers changed from: private */
    public final Context context;
    /* access modifiers changed from: private */
    public final WeakReference<CropImageView> cropImageViewReference;
    /* access modifiers changed from: private */
    public final float[] cropPoints;
    /* access modifiers changed from: private */
    public final Uri customOutputUri;
    /* access modifiers changed from: private */
    public final int degreesRotated;
    /* access modifiers changed from: private */
    public final boolean fixAspectRatio;
    /* access modifiers changed from: private */
    public final boolean flipHorizontally;
    /* access modifiers changed from: private */
    public final boolean flipVertically;
    private Job job = JobKt.Job$default((Job) null, 1, (Object) null);
    /* access modifiers changed from: private */
    public final CropImageView.RequestSizeOptions options;
    /* access modifiers changed from: private */
    public final int orgHeight;
    /* access modifiers changed from: private */
    public final int orgWidth;
    /* access modifiers changed from: private */
    public final int reqHeight;
    /* access modifiers changed from: private */
    public final int reqWidth;
    /* access modifiers changed from: private */
    public final Bitmap.CompressFormat saveCompressFormat;
    /* access modifiers changed from: private */
    public final int saveCompressQuality;
    private final Uri uri;

    public BitmapCroppingWorkerJob(Context context2, WeakReference<CropImageView> weakReference, Uri uri2, Bitmap bitmap2, float[] fArr, int i, int i2, int i3, boolean z, int i4, int i5, int i6, int i7, boolean z2, boolean z3, CropImageView.RequestSizeOptions requestSizeOptions, Bitmap.CompressFormat compressFormat, int i8, Uri uri3) {
        CropImageView.RequestSizeOptions requestSizeOptions2 = requestSizeOptions;
        Bitmap.CompressFormat compressFormat2 = compressFormat;
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(weakReference, "cropImageViewReference");
        Intrinsics.checkNotNullParameter(fArr, "cropPoints");
        Intrinsics.checkNotNullParameter(requestSizeOptions2, "options");
        Intrinsics.checkNotNullParameter(compressFormat2, "saveCompressFormat");
        this.context = context2;
        this.cropImageViewReference = weakReference;
        this.uri = uri2;
        this.bitmap = bitmap2;
        this.cropPoints = fArr;
        this.degreesRotated = i;
        this.orgWidth = i2;
        this.orgHeight = i3;
        this.fixAspectRatio = z;
        this.aspectRatioX = i4;
        this.aspectRatioY = i5;
        this.reqWidth = i6;
        this.reqHeight = i7;
        this.flipHorizontally = z2;
        this.flipVertically = z3;
        this.options = requestSizeOptions2;
        this.saveCompressFormat = compressFormat2;
        this.saveCompressQuality = i8;
        this.customOutputUri = uri3;
    }

    public final Uri getUri() {
        return this.uri;
    }

    public CoroutineContext getCoroutineContext() {
        return Dispatchers.getMain().plus(this.job);
    }

    public final void start() {
        this.job = BuildersKt__Builders_commonKt.launch$default(this, Dispatchers.getDefault(), (CoroutineStart) null, new BitmapCroppingWorkerJob$start$1(this, (Continuation<? super BitmapCroppingWorkerJob$start$1>) null), 2, (Object) null);
    }

    /* access modifiers changed from: private */
    public final Object onPostExecute(Result result, Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getMain(), new BitmapCroppingWorkerJob$onPostExecute$2(this, result, (Continuation<? super BitmapCroppingWorkerJob$onPostExecute$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    public final void cancel() {
        Job.DefaultImpls.cancel$default(this.job, (CancellationException) null, 1, (Object) null);
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0019\b\u0016\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\tB\u0019\b\u0016\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Lcom/canhub/cropper/BitmapCroppingWorkerJob$Result;", "", "bitmap", "Landroid/graphics/Bitmap;", "sampleSize", "", "(Landroid/graphics/Bitmap;I)V", "uri", "Landroid/net/Uri;", "(Landroid/net/Uri;I)V", "error", "Ljava/lang/Exception;", "isSave", "", "(Ljava/lang/Exception;Z)V", "getBitmap", "()Landroid/graphics/Bitmap;", "getError", "()Ljava/lang/Exception;", "()Z", "getSampleSize", "()I", "getUri", "()Landroid/net/Uri;", "cropper_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: BitmapCroppingWorkerJob.kt */
    public static final class Result {
        private final Bitmap bitmap;
        private final Exception error;
        private final boolean isSave;
        private final int sampleSize;
        private final Uri uri;

        public final Bitmap getBitmap() {
            return this.bitmap;
        }

        public final Uri getUri() {
            return this.uri;
        }

        public final Exception getError() {
            return this.error;
        }

        public final boolean isSave() {
            return this.isSave;
        }

        public final int getSampleSize() {
            return this.sampleSize;
        }

        public Result(Bitmap bitmap2, int i) {
            this.bitmap = bitmap2;
            this.uri = null;
            this.error = null;
            this.isSave = false;
            this.sampleSize = i;
        }

        public Result(Uri uri2, int i) {
            this.bitmap = null;
            this.uri = uri2;
            this.error = null;
            this.isSave = true;
            this.sampleSize = i;
        }

        public Result(Exception exc, boolean z) {
            this.bitmap = null;
            this.uri = null;
            this.error = exc;
            this.isSave = z;
            this.sampleSize = 1;
        }
    }
}

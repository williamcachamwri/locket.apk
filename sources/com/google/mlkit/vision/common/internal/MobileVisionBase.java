package com.google.mlkit.vision.common.internal;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.Image;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_vision_common.zzlx;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.odml.image.MlImage;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.MLTask;
import com.google.mlkit.vision.common.InputImage;
import java.io.Closeable;
import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: com.google.mlkit:vision-common@@17.3.0 */
public class MobileVisionBase<DetectionResultT> implements Closeable, LifecycleObserver {
    public static final /* synthetic */ int zza = 0;
    private static final GmsLogger zzb = new GmsLogger("MobileVisionBase", "");
    private final AtomicBoolean zzc = new AtomicBoolean(false);
    private final MLTask zzd;
    private final CancellationTokenSource zze;
    private final Executor zzf;
    private final Task zzg;

    public MobileVisionBase(MLTask<DetectionResultT, InputImage> mLTask, Executor executor) {
        this.zzd = mLTask;
        CancellationTokenSource cancellationTokenSource = new CancellationTokenSource();
        this.zze = cancellationTokenSource;
        this.zzf = executor;
        mLTask.pin();
        this.zzg = mLTask.callAfterLoad(executor, zzb.zza, cancellationTokenSource.getToken()).addOnFailureListener(zzc.zza);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public synchronized void close() {
        if (!this.zzc.getAndSet(true)) {
            this.zze.cancel();
            this.zzd.unpin(this.zzf);
        }
    }

    public synchronized Task<Void> closeWithTask() {
        if (!this.zzc.getAndSet(true)) {
            this.zze.cancel();
            return this.zzd.unpinWithTask(this.zzf);
        }
        return Tasks.forResult(null);
    }

    public synchronized Task<Void> getInitTaskBase() {
        return this.zzg;
    }

    public Task<DetectionResultT> process(Bitmap bitmap, int i) {
        return processBase(InputImage.fromBitmap(bitmap, i));
    }

    public synchronized Task<DetectionResultT> processBase(MlImage mlImage) {
        Preconditions.checkNotNull(mlImage, "MlImage can not be null");
        if (this.zzc.get()) {
            return Tasks.forException(new MlKitException("This detector is already closed!", 14));
        } else if (mlImage.getWidth() < 32 || mlImage.getHeight() < 32) {
            return Tasks.forException(new MlKitException("MlImage width and height should be at least 32!", 3));
        } else {
            mlImage.getInternal().acquire();
            return this.zzd.callAfterLoad(this.zzf, new zzd(this, mlImage), this.zze.getToken()).addOnCompleteListener(new zze(mlImage));
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zza(InputImage inputImage) throws Exception {
        zzlx zze2 = zzlx.zze("detectorTaskWithResource#run");
        zze2.zzb();
        try {
            Object run = this.zzd.run(inputImage);
            zze2.close();
            return run;
        } catch (Throwable th) {
            try {
                Throwable.class.getDeclaredMethod("addSuppressed", new Class[]{Throwable.class}).invoke(th, new Object[]{th});
            } catch (Exception unused) {
            }
        }
        throw th;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzb(MlImage mlImage) throws Exception {
        InputImage convertMlImagetoInputImage = CommonConvertUtils.convertMlImagetoInputImage(mlImage);
        if (convertMlImagetoInputImage != null) {
            return this.zzd.run(convertMlImagetoInputImage);
        }
        throw new MlKitException("Current type of MlImage is not supported.", 13);
    }

    public Task<DetectionResultT> process(Image image, int i) {
        return processBase(InputImage.fromMediaImage(image, i));
    }

    public Task<DetectionResultT> process(Image image, int i, Matrix matrix) {
        return processBase(InputImage.fromMediaImage(image, i, matrix));
    }

    public Task<DetectionResultT> process(ByteBuffer byteBuffer, int i, int i2, int i3, int i4) {
        return processBase(InputImage.fromByteBuffer(byteBuffer, i, i2, i3, i4));
    }

    public synchronized Task<DetectionResultT> processBase(InputImage inputImage) {
        Preconditions.checkNotNull(inputImage, "InputImage can not be null");
        if (this.zzc.get()) {
            return Tasks.forException(new MlKitException("This detector is already closed!", 14));
        } else if (inputImage.getWidth() < 32 || inputImage.getHeight() < 32) {
            return Tasks.forException(new MlKitException("InputImage width and height should be at least 32!", 3));
        } else {
            return this.zzd.callAfterLoad(this.zzf, new zza(this, inputImage), this.zze.getToken());
        }
    }
}

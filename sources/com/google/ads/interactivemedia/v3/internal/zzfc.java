package com.google.ads.interactivemedia.v3.internal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.google.ads.interactivemedia.v3.impl.data.zzbo;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzfc {
    private final zzuv zza;
    private final float zzb;

    public zzfc(ExecutorService executorService, float f) {
        this.zzb = f;
        this.zza = zzvb.zza(executorService);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Bitmap zza(String str, zzbo zzbo) throws Exception {
        Bitmap decodeStream = BitmapFactory.decodeStream(((URLConnection) FirebasePerfUrlConnection.instrument(new URL(str).openConnection())).getInputStream());
        if (decodeStream == null) {
            return null;
        }
        if (zzbo.width != decodeStream.getWidth() || zzbo.height != decodeStream.getHeight()) {
            return decodeStream;
        }
        double d = (double) this.zzb;
        if (Math.copySign(1.0d - d, 1.0d) <= 0.1d || d == 1.0d) {
            return decodeStream;
        }
        return (!Double.isNaN(1.0d) || !Double.isNaN(d)) ? Bitmap.createScaledBitmap(decodeStream, (int) (this.zzb * ((float) decodeStream.getWidth())), (int) (this.zzb * ((float) decodeStream.getHeight())), true) : decodeStream;
    }

    public final Task zzb(String str, zzbo zzbo) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        zzuk.zze(this.zza.zza(new zzfa(this, str, zzbo)), new zzfb(this, taskCompletionSource, str), this.zza);
        return taskCompletionSource.getTask();
    }
}

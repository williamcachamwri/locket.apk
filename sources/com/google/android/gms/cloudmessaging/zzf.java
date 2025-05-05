package com.google.android.gms.cloudmessaging;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.CountDownLatch;

/* compiled from: com.google.android.gms:play-services-cloud-messaging@@17.2.0 */
public final /* synthetic */ class zzf implements OnCompleteListener {
    public final /* synthetic */ CountDownLatch zza;

    public /* synthetic */ zzf(CountDownLatch countDownLatch) {
        this.zza = countDownLatch;
    }

    public final void onComplete(Task task) {
        this.zza.countDown();
    }
}

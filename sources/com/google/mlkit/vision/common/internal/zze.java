package com.google.mlkit.vision.common.internal;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.odml.image.MlImage;

/* compiled from: com.google.mlkit:vision-common@@17.3.0 */
public final /* synthetic */ class zze implements OnCompleteListener {
    public final /* synthetic */ MlImage zza;

    public /* synthetic */ zze(MlImage mlImage) {
        this.zza = mlImage;
    }

    public final void onComplete(Task task) {
        MlImage mlImage = this.zza;
        int i = MobileVisionBase.zza;
        mlImage.close();
    }
}

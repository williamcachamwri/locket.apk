package com.google.ads.interactivemedia.v3.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot;
import com.google.ads.interactivemedia.v3.impl.JavaScriptMessage;
import com.google.ads.interactivemedia.v3.impl.data.zzbe;
import com.google.ads.interactivemedia.v3.internal.zzfk;
import com.google.ads.interactivemedia.v3.internal.zzgi;
import com.google.ads.interactivemedia.v3.internal.zzqm;
import com.google.ads.interactivemedia.v3.internal.zzrp;
import com.google.android.gms.tasks.Task;
import java.util.List;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzax extends ImageView implements View.OnClickListener {
    private final zzbe zza;
    private final zzbi zzb;
    private final String zzc;
    private final List zzd;
    private final zzgi zze;

    private zzax(Context context, zzbi zzbi, zzbe zzbe, String str, List list, zzgi zzgi) {
        super(context);
        this.zzb = zzbi;
        this.zza = zzbe;
        this.zzc = str;
        this.zzd = list;
        this.zze = zzgi;
    }

    public static zzax zza(Context context, zzbi zzbi, zzbe zzbe, Task task, String str, List list, zzgi zzgi) {
        zzax zzax = new zzax(context, zzbi, zzbe, str, list, zzgi);
        zzax.setOnClickListener(zzax);
        task.addOnCompleteListener(new zzaw(zzax));
        return zzax;
    }

    public final void onClick(View view) {
        if (!this.zze.zzb(this.zza.clickThroughUrl())) {
            zzfk.zza("The click was ignored because no browser was available.");
            return;
        }
        for (CompanionAdSlot.ClickListener onCompanionAdClick : this.zzd) {
            onCompanionAdClick.onCompanionAdClick();
        }
    }

    public final void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        String companionId = this.zza.companionId();
        if (!zzqm.zzc(companionId)) {
            this.zzb.zzn(new JavaScriptMessage(JavaScriptMessage.MsgChannel.displayContainer, JavaScriptMessage.MsgType.companionView, this.zzc, zzrp.zze("companionId", companionId)));
        }
    }
}

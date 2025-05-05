package com.adsbynimbus.render;

import android.content.Context;
import com.adsbynimbus.internal.Platform;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcom/adsbynimbus/render/NimbusAdViewDialog;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: BlockingAdController.kt */
final class BlockingAdController$dialog$2 extends Lambda implements Function0<NimbusAdViewDialog> {
    final /* synthetic */ int $closeButtonDelayMillis;
    final /* synthetic */ int $closeDelayAfterComplete;
    final /* synthetic */ BlockingAdController this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BlockingAdController$dialog$2(BlockingAdController blockingAdController, int i, int i2) {
        super(0);
        this.this$0 = blockingAdController;
        this.$closeButtonDelayMillis = i;
        this.$closeDelayAfterComplete = i2;
    }

    public final NimbusAdViewDialog invoke() {
        Object obj = Platform.INSTANCE.getCurrentActivity().get();
        Intrinsics.checkNotNull(obj);
        NimbusAdViewDialog nimbusAdViewDialog = new NimbusAdViewDialog((Context) obj, this.this$0);
        int i = this.$closeButtonDelayMillis;
        int i2 = this.$closeDelayAfterComplete;
        nimbusAdViewDialog.setCloseButtonDelay(i);
        nimbusAdViewDialog.setDelayAfterComplete(i2);
        return nimbusAdViewDialog;
    }
}

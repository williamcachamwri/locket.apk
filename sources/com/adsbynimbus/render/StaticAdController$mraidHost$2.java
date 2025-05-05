package com.adsbynimbus.render;

import com.adsbynimbus.render.mraid.Host;
import com.adsbynimbus.render.mraid.HostKt;
import com.adsbynimbus.render.mraid.Position;
import com.adsbynimbus.render.mraid.Size;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcom/adsbynimbus/render/mraid/Host;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: StaticAdController.kt */
final class StaticAdController$mraidHost$2 extends Lambda implements Function0<Host> {
    final /* synthetic */ StaticAdController this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StaticAdController$mraidHost$2(StaticAdController staticAdController) {
        super(0);
        this.this$0 = staticAdController;
    }

    public final Host invoke() {
        StaticAdController staticAdController = this.this$0;
        return HostKt.mraidHost$default(staticAdController, staticAdController.getAd().isInterstitial() ? HostKt.INTERSTITIAL : HostKt.INLINE, (Size) null, (Position) null, false, 14, (Object) null);
    }
}

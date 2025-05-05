package com.adsbynimbus.render;

import android.app.Activity;
import android.view.View;
import androidx.core.app.NotificationCompat;
import com.adsbynimbus.NimbusAd;
import com.adsbynimbus.NimbusError;
import com.adsbynimbus.internal.Components;
import com.adsbynimbus.internal.Platform;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\u0015\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0000¢\u0006\u0002\b%J\u0015\u0010&\u001a\u00020\"2\u0006\u0010'\u001a\u00020(H\u0000¢\u0006\u0002\b)J\b\u0010*\u001a\u00020\"H\u0016J\b\u0010+\u001a\u00020\"H\u0016J\b\u0010,\u001a\u00020\"H\u0016R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u0004\u0018\u00010\u00018\u0000@\u0000X\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8@X\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u0012\u0010\u000f\u001a\u00020\u00108\u0000@\u0000X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u00020\u00128VX\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0012\u0010\u0015\u001a\u00020\u00058\u0000@\u0000X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0016\u001a\u00020\u00058\u0000@\u0000X\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0017\u001a\u0004\u0018\u00010\u00188VX\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR$\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u00058V@VX\u000e¢\u0006\f\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 ¨\u0006-"}, d2 = {"Lcom/adsbynimbus/render/BlockingAdController;", "Lcom/adsbynimbus/render/AdController;", "ad", "Lcom/adsbynimbus/NimbusAd;", "closeButtonDelayMillis", "", "closeDelayAfterComplete", "(Lcom/adsbynimbus/NimbusAd;II)V", "controller", "dialog", "Lcom/adsbynimbus/render/NimbusAdViewDialog;", "getDialog$render_release", "()Lcom/adsbynimbus/render/NimbusAdViewDialog;", "dialog$delegate", "Lkotlin/Lazy;", "dialogShowing", "", "duration", "", "getDuration", "()F", "requestedVolume", "retries", "view", "Landroid/view/View;", "getView", "()Landroid/view/View;", "value", "volume", "getVolume", "()I", "setVolume", "(I)V", "childError", "", "error", "Lcom/adsbynimbus/NimbusError;", "childError$render_release", "childEvent", "event", "Lcom/adsbynimbus/render/AdEvent;", "childEvent$render_release", "destroy", "start", "stop", "render_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: BlockingAdController.kt */
public final class BlockingAdController extends AdController {
    public final NimbusAd ad;
    public AdController controller;
    private final Lazy dialog$delegate;
    public boolean dialogShowing;
    public int requestedVolume;
    public int retries;

    public BlockingAdController(NimbusAd nimbusAd, int i, int i2) {
        Intrinsics.checkNotNullParameter(nimbusAd, "ad");
        this.ad = nimbusAd;
        this.dialog$delegate = LazyKt.lazy(new BlockingAdController$dialog$2(this, i, i2));
        this.retries = 3;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ BlockingAdController(NimbusAd nimbusAd, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(nimbusAd, i, (i3 & 4) != 0 ? 0 : i2);
    }

    public final NimbusAdViewDialog getDialog$render_release() {
        return (NimbusAdViewDialog) this.dialog$delegate.getValue();
    }

    public View getView() {
        AdController adController = this.controller;
        if (adController != null) {
            return adController.getView();
        }
        return null;
    }

    public void start() {
        Object obj;
        if (this.state != AdState.DESTROYED) {
            AdController adController = this.controller;
            if (adController != null) {
                if (adController != null) {
                    adController.start();
                }
            } else if (this.retries == 0) {
                dispatchError(new NimbusError(NimbusError.ErrorType.CONTROLLER_ERROR, "Error showing interstitial ad", (Throwable) null));
                destroy();
            } else {
                AdController adController2 = this;
                Activity activity = (Activity) Platform.INSTANCE.getCurrentActivity().get();
                boolean z = false;
                if (activity != null) {
                    if (!activity.isFinishing() && !activity.isDestroyed()) {
                        z = true;
                    }
                }
                if (z) {
                    try {
                        Result.Companion companion = Result.Companion;
                        BlockingAdController blockingAdController = this;
                        getDialog$render_release().show();
                        obj = Result.m2444constructorimpl(Unit.INSTANCE);
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.Companion;
                        obj = Result.m2444constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m2451isSuccessimpl(obj)) {
                        this.dialogShowing = true;
                        return;
                    }
                }
                Job unused = BuildersKt__Builders_commonKt.launch$default(Components.getNimbusScope(), (CoroutineContext) null, (CoroutineStart) null, new BlockingAdController$start$2(this, (Continuation<? super BlockingAdController$start$2>) null), 3, (Object) null);
            }
        }
    }

    public void stop() {
        AdController adController = this.controller;
        if (adController != null) {
            adController.stop();
        }
    }

    public void destroy() {
        if (this.state != AdState.DESTROYED) {
            dispatchAdEvent(AdEvent.DESTROYED);
            try {
                Result.Companion companion = Result.Companion;
                BlockingAdController blockingAdController = this;
                AdController adController = this.controller;
                if (adController != null) {
                    adController.destroy();
                }
                this.controller = null;
                if (this.dialogShowing) {
                    getDialog$render_release().dismiss();
                }
                Result.m2444constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                Result.m2444constructorimpl(ResultKt.createFailure(th));
            }
        }
    }

    public int getVolume() {
        AdController adController = this.controller;
        return adController != null ? adController.getVolume() : this.requestedVolume;
    }

    public void setVolume(int i) {
        this.requestedVolume = i;
        AdController adController = this.controller;
        if (adController != null) {
            adController.setVolume(i);
        }
    }

    public float getDuration() {
        AdController adController = this.controller;
        if (adController != null) {
            return adController.getDuration();
        }
        return 0.0f;
    }

    public final void childEvent$render_release(AdEvent adEvent) {
        Intrinsics.checkNotNullParameter(adEvent, NotificationCompat.CATEGORY_EVENT);
        if (adEvent != AdEvent.DESTROYED) {
            dispatchAdEvent(adEvent);
        }
    }

    public final void childError$render_release(NimbusError nimbusError) {
        Intrinsics.checkNotNullParameter(nimbusError, "error");
        dispatchError(nimbusError);
    }
}

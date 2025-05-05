package com.adsbynimbus.render;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import androidx.core.app.NotificationCompat;
import com.adsbynimbus.NimbusAd;
import com.adsbynimbus.NimbusError;
import com.adsbynimbus.VerificationProvider;
import com.adsbynimbus.internal.Logger;
import com.adsbynimbus.render.AdEvent;
import com.adsbynimbus.render.internal.OMInjectedAd;
import com.adsbynimbus.render.internal.OMSession;
import com.iab.omid.library.adsbynimbus.adsession.CreativeType;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u001f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\b&\u0018\u00002\u00020\u0001:\u0001=B\u0005¢\u0006\u0002\u0010\u0002J\u0015\u0010\"\u001a\u00020\u00002\u0006\u0010#\u001a\u00020$H\u0000¢\u0006\u0002\b%J\b\u0010&\u001a\u00020'H&J\u0010\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020*H\u0004J\r\u0010+\u001a\u00020'H\u0000¢\u0006\u0002\b,J\u0010\u0010-\u001a\u00020'2\u0006\u0010.\u001a\u00020/H\u0004J\u001d\u00100\u001a\u00020'2\u0006\u00101\u001a\u00020\u00182\u0006\u00102\u001a\u000203H\u0000¢\u0006\u0002\b4J\u0015\u00105\u001a\u00020'2\u0006\u00106\u001a\u00020\u0011H\u0000¢\u0006\u0002\b7J\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fJ\b\u00108\u001a\u00020'H\u0014J\u0018\u00109\u001a\u00020'2\u0006\u00101\u001a\u00020\u00182\u0006\u00102\u001a\u000203H\u0014J\u0010\u0010:\u001a\u00020'2\u0006\u00106\u001a\u00020\u0011H\u0014J\b\u0010;\u001a\u00020'H\u0016J\b\u0010<\u001a\u00020'H\u0016R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b8G¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\nR\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f8\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u0004\u0018\u00010\u000f8\u0004@\u0004X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0010\u001a\u00020\u00118\u0004@\u0004X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0012\u001a\u00020\u00138\u0004@\u0004X\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0014\u001a\u0004\u0018\u00010\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R$\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u00188V@VX\u000e¢\u0006\f\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001f\u0010\u001e\u001a\u00020\u0011*\b\u0012\u0004\u0012\u00020 0\u001f8Ä\u0002X\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010!¨\u0006>"}, d2 = {"Lcom/adsbynimbus/render/AdController;", "", "()V", "duration", "", "getDuration", "()F", "friendlyObstructions", "", "Landroid/view/View;", "()Ljava/util/Collection;", "listeners", "", "Lcom/adsbynimbus/render/AdController$Listener;", "omSession", "Lcom/adsbynimbus/render/internal/OMSession;", "started", "", "state", "Lcom/adsbynimbus/render/AdState;", "view", "getView", "()Landroid/view/View;", "<anonymous parameter 0>", "", "volume", "getVolume", "()I", "setVolume", "(I)V", "isValid", "Ljava/lang/ref/WeakReference;", "Landroid/app/Activity;", "(Ljava/lang/ref/WeakReference;)Z", "applyOM", "ad", "Lcom/adsbynimbus/NimbusAd;", "applyOM$render_release", "destroy", "", "dispatchAdEvent", "event", "Lcom/adsbynimbus/render/AdEvent;", "dispatchClickDetected", "dispatchClickDetected$render_release", "dispatchError", "error", "Lcom/adsbynimbus/NimbusError;", "dispatchExposureChange", "exposure", "visibleRect", "Landroid/graphics/Rect;", "dispatchExposureChange$render_release", "dispatchViewableChange", "isViewable", "dispatchViewableChange$render_release", "onClickDetected", "onExposureChanged", "onViewableChanged", "start", "stop", "Listener", "render_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: AdController.kt */
public abstract class AdController {
    private final Collection<View> friendlyObstructions = new ArrayList();
    public final Set<Listener> listeners = new CopyOnWriteArraySet();
    protected OMSession omSession;
    protected boolean started = true;
    protected AdState state = AdState.LOADING;
    private final View view;

    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u00012\u00020\u0002¨\u0006\u0003"}, d2 = {"Lcom/adsbynimbus/render/AdController$Listener;", "Lcom/adsbynimbus/render/AdEvent$Listener;", "Lcom/adsbynimbus/NimbusError$Listener;", "render_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: AdController.kt */
    public interface Listener extends AdEvent.Listener, NimbusError.Listener {
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: AdController.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(13:0|1|2|3|4|5|6|7|8|9|10|11|13) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
        static {
            /*
                com.adsbynimbus.render.AdEvent[] r0 = com.adsbynimbus.render.AdEvent.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.adsbynimbus.render.AdEvent r1 = com.adsbynimbus.render.AdEvent.LOADED     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.adsbynimbus.render.AdEvent r1 = com.adsbynimbus.render.AdEvent.IMPRESSION     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.adsbynimbus.render.AdEvent r1 = com.adsbynimbus.render.AdEvent.RESUMED     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.adsbynimbus.render.AdEvent r1 = com.adsbynimbus.render.AdEvent.PAUSED     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                com.adsbynimbus.render.AdEvent r1 = com.adsbynimbus.render.AdEvent.DESTROYED     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.render.AdController.WhenMappings.<clinit>():void");
        }
    }

    public abstract void destroy();

    public float getDuration() {
        return 0.0f;
    }

    public int getVolume() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public void onClickDetected() {
    }

    /* access modifiers changed from: protected */
    public void onExposureChanged(int i, Rect rect) {
        Intrinsics.checkNotNullParameter(rect, "visibleRect");
    }

    /* access modifiers changed from: protected */
    public void onViewableChanged(boolean z) {
    }

    public void start() {
    }

    public void stop() {
    }

    public final Set<Listener> listeners() {
        return this.listeners;
    }

    public View getView() {
        return this.view;
    }

    public void setVolume(int i) {
        Logger.log(2, "This ad controller does not support setting volume.");
    }

    public final Collection<View> friendlyObstructions() {
        return this.friendlyObstructions;
    }

    /* access modifiers changed from: protected */
    public final void dispatchAdEvent(AdEvent adEvent) {
        AdState adState;
        Intrinsics.checkNotNullParameter(adEvent, NotificationCompat.CATEGORY_EVENT);
        int i = WhenMappings.$EnumSwitchMapping$0[adEvent.ordinal()];
        if (i == 1) {
            adState = AdState.READY;
        } else if (i == 2 || i == 3) {
            adState = AdState.RESUMED;
        } else if (i == 4) {
            adState = AdState.PAUSED;
        } else if (i != 5) {
            adState = this.state;
        } else {
            adState = AdState.DESTROYED;
        }
        this.state = adState;
        for (Listener onAdEvent : this.listeners) {
            onAdEvent.onAdEvent(adEvent);
        }
        if (adEvent == AdEvent.DESTROYED) {
            this.listeners.clear();
        }
    }

    /* access modifiers changed from: protected */
    public final void dispatchError(NimbusError nimbusError) {
        Intrinsics.checkNotNullParameter(nimbusError, "error");
        String message = nimbusError.getMessage();
        if (message == null) {
            message = nimbusError.errorType.toString();
        }
        Logger.log(6, message);
        for (Listener onError : this.listeners) {
            onError.onError(nimbusError);
        }
    }

    public final void dispatchClickDetected$render_release() {
        onClickDetected();
    }

    public final void dispatchExposureChange$render_release(int i, Rect rect) {
        Intrinsics.checkNotNullParameter(rect, "visibleRect");
        onExposureChanged(i, rect);
    }

    public final void dispatchViewableChange$render_release(boolean z) {
        onViewableChanged(z);
    }

    /* access modifiers changed from: protected */
    public final boolean isValid(WeakReference<Activity> weakReference) {
        Intrinsics.checkNotNullParameter(weakReference, "<this>");
        Activity activity = (Activity) weakReference.get();
        if (activity == null) {
            return false;
        }
        return !activity.isFinishing() && !activity.isDestroyed();
    }

    public final AdController applyOM$render_release(NimbusAd nimbusAd) {
        Intrinsics.checkNotNullParameter(nimbusAd, "ad");
        AdController adController = this;
        if (nimbusAd instanceof OMInjectedAd) {
            OMInjectedAd oMInjectedAd = (OMInjectedAd) nimbusAd;
            if (oMInjectedAd.isMeasurable()) {
                Set<Listener> set = this.listeners;
                CreativeType creativeType = oMInjectedAd.getCreativeType();
                Iterable<VerificationProvider> providers = oMInjectedAd.getProviders();
                Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(providers, 10));
                for (VerificationProvider verificationResource : providers) {
                    arrayList.add(verificationResource.verificationResource(nimbusAd));
                }
                OMSession oMSession = new OMSession(creativeType, CollectionsKt.toMutableList((List) arrayList), this);
                this.omSession = oMSession;
                set.add(oMSession);
            }
        }
        return this;
    }
}

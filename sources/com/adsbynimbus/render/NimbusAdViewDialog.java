package com.adsbynimbus.render;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import com.adsbynimbus.Nimbus;
import com.adsbynimbus.NimbusAd;
import com.adsbynimbus.NimbusError;
import com.adsbynimbus.internal.Components;
import com.adsbynimbus.render.AdController;
import com.adsbynimbus.render.Renderer;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010!\u001a\u00020\"H\u0007J\u0010\u0010#\u001a\u00020\"2\u0006\u0010$\u001a\u00020%H\u0016J\u0010\u0010&\u001a\u00020\"2\u0006\u0010'\u001a\u00020(H\u0016J\u0012\u0010)\u001a\u00020\"2\b\u0010*\u001a\u0004\u0018\u00010+H\u0014J\u0010\u0010,\u001a\u00020\"2\u0006\u0010-\u001a\u00020.H\u0016JP\u0010/\u001a\u00020\"2\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\u00132\u0006\u00103\u001a\u00020\u00132\u0006\u00104\u001a\u00020\u00132\u0006\u00105\u001a\u00020\u00132\u0006\u00106\u001a\u00020\u00132\u0006\u00107\u001a\u00020\u00132\u0006\u00108\u001a\u00020\u00132\u0006\u00109\u001a\u00020\u0013H\u0016J\b\u0010:\u001a\u00020\"H\u0016J\u000e\u0010;\u001a\u00020\"2\u0006\u0010<\u001a\u00020\u0013J\u000e\u0010=\u001a\u00020\"2\u0006\u0010>\u001a\u00020\u0013J\u000e\u0010?\u001a\u00020\"2\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010@\u001a\u00020\"2\u0006\u0010A\u001a\u00020\u0013J\u0010\u0010B\u001a\u00020\"2\b\u0010C\u001a\u0004\u0018\u00010\u0011J\u0010\u0010D\u001a\u00020\"2\b\u0010C\u001a\u0004\u0018\u00010\u0011J\u000e\u0010E\u001a\u00020\"2\u0006\u0010 \u001a\u00020\u0013R\u0014\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0000@\u0000X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u0004\u0018\u00010\r8\u0000@\u0000X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000e\u001a\u00020\u000f8\u0000@\u0000X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u0004\u0018\u00010\u00118\u0000@\u0000X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0012\u001a\u00020\u00138\u0000@\u0000X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0014\u001a\u00020\u000f8\u0000@\u0000X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0015\u001a\u00020\u00168\u0000@\u0000X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\u00020\u000fXD¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u0004\u0018\u00010\r8\u0000@\u0000X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\u0004\u0018\u00010\u00118\u0000@\u0000X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u0004\u0018\u00010\u001f8\u0000@\u0000X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010 \u001a\u00020\u000f8\u0000@\u0000X\u000e¢\u0006\u0002\n\u0000¨\u0006F"}, d2 = {"Lcom/adsbynimbus/render/NimbusAdViewDialog;", "Landroid/app/Dialog;", "Lcom/adsbynimbus/render/AdController$Listener;", "Lcom/adsbynimbus/render/Renderer$Listener;", "Landroid/view/View$OnLayoutChangeListener;", "context", "Landroid/content/Context;", "parentController", "Lcom/adsbynimbus/render/BlockingAdController;", "(Landroid/content/Context;Lcom/adsbynimbus/render/BlockingAdController;)V", "adFrame", "Landroid/widget/FrameLayout;", "closeButton", "Landroid/widget/ImageView;", "closeButtonDelayMillis", "", "closeDrawable", "Landroid/graphics/drawable/Drawable;", "closeOrientation", "", "delayAfterCompleteTimeout", "dismissOnComplete", "", "loadTimeout", "getLoadTimeout$render_release", "()J", "muteButton", "muteDrawable", "getParentController$render_release", "()Lcom/adsbynimbus/render/BlockingAdController;", "progressBar", "Landroid/widget/ProgressBar;", "staticDismissTimeout", "makeDismissible", "", "onAdEvent", "adEvent", "Lcom/adsbynimbus/render/AdEvent;", "onAdRendered", "controller", "Lcom/adsbynimbus/render/AdController;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onError", "error", "Lcom/adsbynimbus/NimbusError;", "onLayoutChange", "frame", "Landroid/view/View;", "left", "top", "right", "bottom", "oldLeft", "oldTop", "oldRight", "oldBottom", "onStart", "setCloseButtonDelay", "delayMillis", "setDelayAfterComplete", "delaySeconds", "setDismissOnComplete", "setDismissOrientation", "orientation", "setMuteButton", "drawable", "setShowDismissDrawable", "setStaticDismissTimeout", "render_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: NimbusAdViewDialog.kt */
public final class NimbusAdViewDialog extends Dialog implements AdController.Listener, Renderer.Listener, View.OnLayoutChangeListener {
    public FrameLayout adFrame;
    public ImageView closeButton;
    public long closeButtonDelayMillis;
    public Drawable closeDrawable;
    public int closeOrientation;
    public long delayAfterCompleteTimeout;
    public boolean dismissOnComplete;
    private final long loadTimeout = 8000;
    public ImageView muteButton;
    public Drawable muteDrawable;
    private final BlockingAdController parentController;
    public ProgressBar progressBar;
    public long staticDismissTimeout;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: NimbusAdViewDialog.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|3|4|5|6|7|8|9|11) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
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
                com.adsbynimbus.render.AdEvent r1 = com.adsbynimbus.render.AdEvent.COMPLETED     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.adsbynimbus.render.AdEvent r1 = com.adsbynimbus.render.AdEvent.DESTROYED     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.render.NimbusAdViewDialog.WhenMappings.<clinit>():void");
        }
    }

    public final BlockingAdController getParentController$render_release() {
        return this.parentController;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NimbusAdViewDialog(Context context, BlockingAdController blockingAdController) {
        super(context, R.style.NimbusContainer);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(blockingAdController, "parentController");
        this.parentController = blockingAdController;
    }

    public final long getLoadTimeout$render_release() {
        return this.loadTimeout;
    }

    public final void setCloseButtonDelay(int i) {
        this.closeButtonDelayMillis = (long) i;
    }

    public final void setStaticDismissTimeout(int i) {
        this.staticDismissTimeout = (long) i;
    }

    public final void setDismissOnComplete(boolean z) {
        this.dismissOnComplete = z;
    }

    public final void setShowDismissDrawable(Drawable drawable) {
        this.closeDrawable = drawable;
        ImageView imageView = this.closeButton;
        if (imageView != null) {
            imageView.setImageDrawable(drawable);
        }
    }

    public final void setDismissOrientation(int i) {
        this.closeOrientation = i;
        ImageView imageView = this.closeButton;
        FrameLayout.LayoutParams layoutParams = null;
        ViewGroup.LayoutParams layoutParams2 = imageView != null ? imageView.getLayoutParams() : null;
        if (layoutParams2 instanceof FrameLayout.LayoutParams) {
            layoutParams = (FrameLayout.LayoutParams) layoutParams2;
        }
        if (layoutParams != null) {
            layoutParams.gravity = i | 48;
        }
    }

    public final void setMuteButton(Drawable drawable) {
        this.muteDrawable = drawable;
        ImageView imageView = this.muteButton;
        if (imageView != null) {
            imageView.setImageDrawable(drawable);
        }
    }

    public final void setDelayAfterComplete(int i) {
        this.delayAfterCompleteTimeout = (long) i;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCancelable(false);
        setStaticDismissTimeout(BlockingAdRenderer.sStaticDismissTimeout);
        setDismissOnComplete(BlockingAdRenderer.sDismissOnComplete);
        setDismissOrientation(BlockingAdRenderer.sDismissOrientation);
        if (Nimbus.muteDrawable != null) {
            Drawable drawable = Nimbus.muteDrawable;
            Intrinsics.checkNotNull(drawable);
            setMuteButton(drawable.mutate());
        }
        if (Nimbus.closeDrawable != null) {
            Drawable drawable2 = Nimbus.closeDrawable;
            Intrinsics.checkNotNull(drawable2);
            setShowDismissDrawable(drawable2.mutate());
        }
        Window window = getWindow();
        if (window != null) {
            window.setFlags(16777216, 16777216);
            if (Components.isApi28()) {
                window.getAttributes().layoutInDisplayCutoutMode = 1;
            }
            WindowCompat.setDecorFitsSystemWindows(window, false);
            WindowInsetsControllerCompat windowInsetsController = ViewCompat.getWindowInsetsController(window.getDecorView());
            if (windowInsetsController != null) {
                windowInsetsController.setAppearanceLightStatusBars(true);
                windowInsetsController.setSystemBarsBehavior(2);
                windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());
            }
        }
        setContentView(LayoutInflater.from(getContext()).inflate(R.layout.ad_dialog, (ViewGroup) null));
        ImageView imageView = (ImageView) findViewById(R.id.nimbus_close);
        imageView.setOnClickListener(new NimbusAdViewDialog$$ExternalSyntheticLambda1(this));
        Drawable drawable3 = this.closeDrawable;
        if (drawable3 != null) {
            imageView.setImageDrawable(drawable3);
        }
        imageView.setContentDescription(imageView.getContext().getString(R.string.nimbus_dismiss));
        if (this.closeOrientation != 0) {
            Intrinsics.checkNotNullExpressionValue(imageView, "onCreate$lambda$6");
            View view = imageView;
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams != null) {
                ViewGroup.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
                ((FrameLayout.LayoutParams) layoutParams2).gravity = this.closeOrientation;
                view.setLayoutParams(layoutParams2);
            } else {
                throw new NullPointerException("null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
            }
        }
        if (this.closeButtonDelayMillis > 0) {
            imageView.setVisibility(8);
        }
        if (Components.isApi21()) {
            NimbusAdViewDialog$onCreate$3$4 nimbusAdViewDialog$onCreate$3$4 = new NimbusAdViewDialog$onCreate$3$4();
            imageView.setClipToOutline(true);
            imageView.setOutlineProvider(nimbusAdViewDialog$onCreate$3$4);
        }
        this.closeButton = imageView;
        this.progressBar = (ProgressBar) findViewById(R.id.nimbus_loading_indicator);
        NimbusAd nimbusAd = this.parentController.ad;
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.ad_frame);
        frameLayout.addOnLayoutChangeListener(this);
        Renderer.Companion companion = Renderer.Companion;
        Intrinsics.checkNotNullExpressionValue(frameLayout, "it");
        companion.loadAd(nimbusAd, frameLayout, this);
        this.adFrame = frameLayout;
        Job unused = BuildersKt__Builders_commonKt.launch$default(Components.getNimbusScope(), Dispatchers.getMain(), (CoroutineStart) null, new NimbusAdViewDialog$onCreate$6(this, (Continuation<? super NimbusAdViewDialog$onCreate$6>) null), 2, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$6$lambda$2(NimbusAdViewDialog nimbusAdViewDialog, View view) {
        nimbusAdViewDialog.parentController.destroy();
        nimbusAdViewDialog.dismiss();
    }

    public void onStart() {
        super.onStart();
        ImageView imageView = this.closeButton;
        if (imageView != null) {
            if (!(this.closeButtonDelayMillis > 0 && imageView.getVisibility() != 0)) {
                imageView = null;
            }
            if (imageView != null) {
                imageView.postDelayed(new NimbusAdViewDialog$$ExternalSyntheticLambda0(this), this.closeButtonDelayMillis);
            }
        }
    }

    public void onAdRendered(AdController adController) {
        Intrinsics.checkNotNullParameter(adController, "controller");
        BlockingAdController blockingAdController = this.parentController;
        adController.setVolume(blockingAdController.requestedVolume);
        ImageView imageView = (ImageView) findViewById(R.id.nimbus_mute);
        if (imageView != null) {
            Intrinsics.checkNotNullExpressionValue(imageView, "findViewById<ImageView>(R.id.nimbus_mute)");
            imageView.setVisibility(0);
            Drawable drawable = this.muteDrawable;
            if (drawable != null) {
                imageView.setImageDrawable(drawable);
            }
        } else {
            imageView = null;
        }
        this.muteButton = imageView;
        ImageView imageView2 = this.closeButton;
        if (imageView2 != null) {
            adController.friendlyObstructions().add(imageView2);
        }
        blockingAdController.controller = adController;
        adController.listeners().add(this);
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        Object obj;
        Intrinsics.checkNotNullParameter(view, TypedValues.AttributesType.S_FRAME);
        FrameLayout frameLayout = this.adFrame;
        if (frameLayout != null) {
            boolean z = false;
            View childAt = frameLayout.getChildAt(0);
            if (childAt != null) {
                try {
                    Result.Companion companion = Result.Companion;
                    Float valueOf = Float.valueOf(Math.min(((float) view.getWidth()) / ((float) childAt.getWidth()), ((float) view.getHeight()) / ((float) childAt.getHeight())));
                    float floatValue = valueOf.floatValue();
                    if (!Float.isInfinite(floatValue) && !Float.isNaN(floatValue)) {
                        z = true;
                    }
                    Unit unit = null;
                    if (!z) {
                        valueOf = null;
                    }
                    if (valueOf != null) {
                        float floatValue2 = valueOf.floatValue();
                        childAt.setScaleX(floatValue2);
                        childAt.setScaleY(floatValue2);
                        unit = Unit.INSTANCE;
                    }
                    obj = Result.m2444constructorimpl(unit);
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.Companion;
                    obj = Result.m2444constructorimpl(ResultKt.createFailure(th));
                }
                Result.m2443boximpl(obj);
            }
        }
    }

    public void onAdEvent(AdEvent adEvent) {
        Intrinsics.checkNotNullParameter(adEvent, "adEvent");
        this.parentController.childEvent$render_release(adEvent);
        int i = WhenMappings.$EnumSwitchMapping$0[adEvent.ordinal()];
        boolean z = true;
        if (i != 1) {
            ImageView imageView = null;
            if (i == 2) {
                ProgressBar progressBar2 = this.progressBar;
                if (progressBar2 != null) {
                    progressBar2.setVisibility(8);
                }
                if (this.staticDismissTimeout > 0 && Intrinsics.areEqual((Object) StaticAdRenderer.STATIC_AD_TYPE, (Object) this.parentController.ad.type())) {
                    Job unused = BuildersKt__Builders_commonKt.launch$default(Components.getNimbusScope(), (CoroutineContext) null, (CoroutineStart) null, new NimbusAdViewDialog$onAdEvent$1(this, (Continuation<? super NimbusAdViewDialog$onAdEvent$1>) null), 3, (Object) null);
                }
                ImageView imageView2 = this.closeButton;
                if (imageView2 != null) {
                    if (this.delayAfterCompleteTimeout == 0) {
                        z = false;
                    }
                    if (!z) {
                        imageView = imageView2;
                    }
                    if (imageView != null) {
                        if (this.closeButtonDelayMillis > 0) {
                            imageView.removeCallbacks(new NimbusAdViewDialog$$ExternalSyntheticLambda0(this));
                            imageView.postDelayed(new NimbusAdViewDialog$$ExternalSyntheticLambda0(this), this.closeButtonDelayMillis);
                        }
                        if (imageView.getY() - ((float) imageView.getHeight()) < 0.0f || imageView.getX() - ((float) imageView.getWidth()) < 0.0f) {
                            imageView.postDelayed(new NimbusAdViewDialog$$ExternalSyntheticLambda0(this), 5000);
                        }
                    }
                }
            } else if (i != 3) {
                if (i == 4) {
                    this.parentController.destroy();
                }
            } else if (this.delayAfterCompleteTimeout > 0) {
                Job unused2 = BuildersKt__Builders_commonKt.launch$default(Components.getNimbusScope(), Dispatchers.getMain(), (CoroutineStart) null, new NimbusAdViewDialog$onAdEvent$4(this, (Continuation<? super NimbusAdViewDialog$onAdEvent$4>) null), 2, (Object) null);
            } else if (this.dismissOnComplete) {
                this.parentController.destroy();
            } else {
                makeDismissible();
            }
        } else {
            ProgressBar progressBar3 = this.progressBar;
            if (progressBar3 != null) {
                progressBar3.setVisibility(8);
            }
        }
    }

    public void onError(NimbusError nimbusError) {
        Intrinsics.checkNotNullParameter(nimbusError, "error");
        makeDismissible();
        this.parentController.childError$render_release(nimbusError);
        this.parentController.destroy();
    }

    public final void makeDismissible() {
        setCancelable(true);
        ImageView imageView = this.closeButton;
        if (imageView != null) {
            imageView.setVisibility(0);
        }
    }
}

package com.adsbynimbus.ui;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.DialogFragment;
import com.adsbynimbus.NimbusAd;
import com.adsbynimbus.NimbusError;
import com.adsbynimbus.render.AdController;
import com.adsbynimbus.render.AdEvent;
import com.adsbynimbus.render.R;
import com.adsbynimbus.render.Renderer;
import com.adsbynimbus.render.StaticAdRenderer;
import java.util.Set;

@Deprecated
public class NimbusAdViewFragment extends DialogFragment implements AdController.Listener, Renderer.Listener, CompoundButton.OnCheckedChangeListener, View.OnLayoutChangeListener {
    protected NimbusAd ad;
    protected FrameLayout adFrame;
    protected ImageView closeButton;
    protected int closeButtonDelayMillis;
    protected Drawable closeDrawable;
    protected int closeOrientation = GravityCompat.START;
    protected AdController controller;
    protected boolean dismissOnComplete = false;
    private Set<AdController.Listener> listeners;
    protected CheckBox muteButton;
    protected Drawable muteDrawable;
    protected int staticDismissTimeout;
    protected int theme = getTheme();

    public static NimbusAdViewFragment forNimbusAd(NimbusAd nimbusAd) {
        NimbusAdViewFragment nimbusAdViewFragment = new NimbusAdViewFragment();
        nimbusAdViewFragment.ad = nimbusAd;
        return nimbusAdViewFragment;
    }

    public AdController controller() {
        return this.controller;
    }

    public void setCloseButtonDelay(int i) {
        this.closeButtonDelayMillis = i;
    }

    public void setStaticDismissTimeout(int i) {
        this.staticDismissTimeout = i;
    }

    public void setDismissOnComplete(boolean z) {
        this.dismissOnComplete = z;
    }

    public void setShowDismissDrawable(Drawable drawable) {
        this.closeDrawable = drawable;
        ImageView imageView = this.closeButton;
        if (imageView != null) {
            imageView.setImageDrawable(drawable);
        }
    }

    public void setDismissOrientation(int i) {
        this.closeOrientation = i;
        ImageView imageView = this.closeButton;
        if (imageView != null) {
            ((FrameLayout.LayoutParams) imageView.getLayoutParams()).gravity = i | 48;
        }
    }

    public void setMuteButton(Drawable drawable) {
        this.muteDrawable = drawable;
        CheckBox checkBox = this.muteButton;
        if (checkBox != null) {
            checkBox.setButtonDrawable(drawable);
        }
    }

    public void addListeners(Set<AdController.Listener> set) {
        this.listeners = set;
    }

    public void setDialogTheme(int i) {
        this.theme = i;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        return new Dialog(requireActivity(), this.theme);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.ad_dialog, viewGroup, false);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.nimbus_close);
        this.closeButton = imageView;
        imageView.setOnClickListener(new NimbusAdViewFragment$$ExternalSyntheticLambda0(this));
        Drawable drawable = this.closeDrawable;
        if (drawable != null) {
            this.closeButton.setImageDrawable(drawable);
        }
        if (this.closeButtonDelayMillis > 0) {
            this.closeButton.setVisibility(8);
        }
        FrameLayout frameLayout = (FrameLayout) inflate.findViewById(R.id.ad_frame);
        this.adFrame = frameLayout;
        frameLayout.addOnLayoutChangeListener(this);
        return inflate;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onCreateView$0$com-adsbynimbus-ui-NimbusAdViewFragment  reason: not valid java name */
    public /* synthetic */ void m1165lambda$onCreateView$0$comadsbynimbusuiNimbusAdViewFragment(View view) {
        dismiss();
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        FrameLayout frameLayout = this.adFrame;
        View childAt = frameLayout != null ? frameLayout.getChildAt(0) : null;
        if (childAt != null) {
            float min = Math.min(((float) view.getWidth()) / ((float) childAt.getWidth()), ((float) view.getHeight()) / ((float) childAt.getHeight()));
            childAt.setScaleX(min);
            childAt.setScaleY(min);
        }
    }

    public void onStart() {
        super.onStart();
        NimbusAd nimbusAd = this.ad;
        if (nimbusAd != null && this.controller == null) {
            Renderer.loadAd(nimbusAd, this.adFrame, this);
        }
    }

    public void onPause() {
        super.onPause();
        AdController adController = this.controller;
        if (adController != null) {
            adController.stop();
        }
    }

    public void onResume() {
        super.onResume();
        AdController adController = this.controller;
        if (adController != null) {
            adController.start();
        }
    }

    public void onDestroyView() {
        AdController adController = this.controller;
        if (adController != null) {
            adController.destroy();
            this.controller = null;
        }
        super.onDestroyView();
    }

    public void onAdRendered(AdController adController) {
        this.controller = adController;
        adController.listeners().add(this);
        if (this.listeners != null) {
            adController.listeners().addAll(this.listeners);
            this.listeners = null;
        }
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        AdController adController = this.controller;
        if (adController != null) {
            adController.setVolume(z ? 0 : 100);
        }
    }

    /* renamed from: com.adsbynimbus.ui.NimbusAdViewFragment$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$adsbynimbus$render$AdEvent;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.adsbynimbus.render.AdEvent[] r0 = com.adsbynimbus.render.AdEvent.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$adsbynimbus$render$AdEvent = r0
                com.adsbynimbus.render.AdEvent r1 = com.adsbynimbus.render.AdEvent.IMPRESSION     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$adsbynimbus$render$AdEvent     // Catch:{ NoSuchFieldError -> 0x001d }
                com.adsbynimbus.render.AdEvent r1 = com.adsbynimbus.render.AdEvent.COMPLETED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.ui.NimbusAdViewFragment.AnonymousClass1.<clinit>():void");
        }
    }

    public void onAdEvent(AdEvent adEvent) {
        int i = AnonymousClass1.$SwitchMap$com$adsbynimbus$render$AdEvent[adEvent.ordinal()];
        if (i == 1) {
            if (this.staticDismissTimeout > 0 && StaticAdRenderer.STATIC_AD_TYPE.equals(this.ad.type())) {
                this.adFrame.postDelayed(new NimbusAdViewFragment$$ExternalSyntheticLambda1(this), (long) this.staticDismissTimeout);
            }
            if (this.closeButtonDelayMillis > 0) {
                this.adFrame.postDelayed(new NimbusAdViewFragment$$ExternalSyntheticLambda2(this), (long) this.closeButtonDelayMillis);
            }
        } else if (i == 2 && this.dismissOnComplete) {
            dismiss();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onAdEvent$1$com-adsbynimbus-ui-NimbusAdViewFragment  reason: not valid java name */
    public /* synthetic */ void m1164lambda$onAdEvent$1$comadsbynimbusuiNimbusAdViewFragment() {
        ImageView imageView = this.closeButton;
        if (imageView != null) {
            imageView.setVisibility(0);
        }
    }

    public void onError(NimbusError nimbusError) {
        dismissAllowingStateLoss();
    }
}

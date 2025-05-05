package com.adsbynimbus.render.internal;

import android.view.View;
import android.webkit.WebView;
import com.adsbynimbus.render.R;
import com.iab.omid.library.adsbynimbus.adsession.AdSession;
import com.iab.omid.library.adsbynimbus.adsession.AdSessionConfiguration;
import com.iab.omid.library.adsbynimbus.adsession.AdSessionContext;
import com.iab.omid.library.adsbynimbus.adsession.CreativeType;
import com.iab.omid.library.adsbynimbus.adsession.Partner;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lcom/iab/omid/library/adsbynimbus/adsession/AdSession;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: OpenMeasurement.kt */
final class OMSession$adSession$2 extends Lambda implements Function0<AdSession> {
    final /* synthetic */ CreativeType $creativeType;
    final /* synthetic */ OMSession this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OMSession$adSession$2(OMSession oMSession, CreativeType creativeType) {
        super(0);
        this.this$0 = oMSession;
        this.$creativeType = creativeType;
    }

    public final AdSession invoke() {
        AdSessionContext adSessionContext;
        AdSessionConfiguration configuration = this.this$0.getConfiguration();
        if (this.$creativeType == CreativeType.HTML_DISPLAY) {
            Partner partner = OpenMeasurement.partner;
            View view = this.this$0.getController().getView();
            WebView webView = view != null ? (WebView) view.findViewById(R.id.nimbus_web_view) : null;
            Intrinsics.checkNotNull(webView, "null cannot be cast to non-null type android.webkit.WebView");
            adSessionContext = AdSessionContext.createHtmlAdSessionContext(partner, webView, (String) null, "");
        } else {
            adSessionContext = AdSessionContext.createNativeAdSessionContext(OpenMeasurement.partner, OpenMeasurement.getServiceJs(), this.this$0.getVerificationScripts(), (String) null, "");
        }
        AdSession createAdSession = AdSession.createAdSession(configuration, adSessionContext);
        Function1<List<? extends View>, Unit> internalObstructionListener = OpenMeasurement.getInternalObstructionListener();
        if (internalObstructionListener != null) {
            createAdSession.setPossibleObstructionListener(new OMSession$adSession$2$$ExternalSyntheticLambda0(internalObstructionListener));
        }
        return createAdSession;
    }

    /* access modifiers changed from: private */
    public static final void invoke$lambda$2$lambda$1$lambda$0(Function1 function1, String str, List list) {
        Intrinsics.checkNotNullExpressionValue(list, "views");
        function1.invoke(list);
    }
}

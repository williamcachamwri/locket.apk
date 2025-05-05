package com.adsbynimbus;

import com.iab.omid.library.adsbynimbus.adsession.VerificationScriptResource;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\b"}, d2 = {"Lcom/adsbynimbus/VerificationProvider;", "", "verificationMarkup", "", "ad", "Lcom/adsbynimbus/NimbusAd;", "verificationResource", "Lcom/iab/omid/library/adsbynimbus/adsession/VerificationScriptResource;", "render_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: VerificationProviders.kt */
public interface VerificationProvider {
    String verificationMarkup(NimbusAd nimbusAd);

    VerificationScriptResource verificationResource(NimbusAd nimbusAd);
}

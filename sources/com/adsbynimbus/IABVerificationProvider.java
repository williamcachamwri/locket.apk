package com.adsbynimbus;

import com.iab.omid.library.adsbynimbus.adsession.VerificationScriptResource;
import java.net.URL;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/adsbynimbus/IABVerificationProvider;", "Lcom/adsbynimbus/VerificationProvider;", "()V", "verificationUrl", "", "verificationMarkup", "ad", "Lcom/adsbynimbus/NimbusAd;", "verificationResource", "Lcom/iab/omid/library/adsbynimbus/adsession/VerificationScriptResource;", "render_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: VerificationProviders.kt */
public final class IABVerificationProvider implements VerificationProvider {
    public static final IABVerificationProvider INSTANCE = new IABVerificationProvider();
    public static final String verificationUrl = "https://adsbynimbus-public.s3.amazonaws.com/dev/omid_validation_verification_script_v1.js";

    public String verificationMarkup(NimbusAd nimbusAd) {
        Intrinsics.checkNotNullParameter(nimbusAd, "ad");
        return "<script src=\"https://adsbynimbus-public.s3.amazonaws.com/dev/omid_validation_verification_script_v1.js\" type=\"text/javascript\"></script>";
    }

    private IABVerificationProvider() {
    }

    public VerificationScriptResource verificationResource(NimbusAd nimbusAd) {
        Intrinsics.checkNotNullParameter(nimbusAd, "ad");
        VerificationScriptResource createVerificationScriptResourceWithParameters = VerificationScriptResource.createVerificationScriptResourceWithParameters("iabtechlab.com-omid", new URL(verificationUrl), "iabtechlab-Adsbynimbus");
        Intrinsics.checkNotNullExpressionValue(createVerificationScriptResourceWithParameters, "createVerificationScript…b-Adsbynimbus\",\n        )");
        return createVerificationScriptResourceWithParameters;
    }
}

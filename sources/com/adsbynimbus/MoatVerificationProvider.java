package com.adsbynimbus;

import androidx.media3.common.MimeTypes;
import com.adsbynimbus.render.StaticAdRenderer;
import com.iab.omid.library.adsbynimbus.adsession.VerificationScriptResource;
import io.sentry.protocol.SentryStackFrame;
import java.net.URL;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012 \b\u0002\u0010\u0006\u001a\u001a\u0012\u0004\u0012\u00020\b\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\t0\u0007¢\u0006\u0002\u0010\nJ\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\bH\u0016J\u000e\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\bJ\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\bH\u0016J\u000e\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\bR)\u0010\u0006\u001a\u001a\u0012\u0004\u0012\u00020\b\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\t0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000e¨\u0006\u0018"}, d2 = {"Lcom/adsbynimbus/MoatVerificationProvider;", "Lcom/adsbynimbus/VerificationProvider;", "staticKey", "", "videoKey", "nativeKey", "mapping", "Lkotlin/Function1;", "Lcom/adsbynimbus/NimbusAd;", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "getMapping", "()Lkotlin/jvm/functions/Function1;", "getNativeKey", "()Ljava/lang/String;", "getStaticKey", "getVideoKey", "verificationMarkup", "ad", "verificationParams", "nimbusAd", "verificationResource", "Lcom/iab/omid/library/adsbynimbus/adsession/VerificationScriptResource;", "verificationUrl", "render_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: VerificationProviders.kt */
public final class MoatVerificationProvider implements VerificationProvider {
    private final Function1<NimbusAd, Map<String, String>> mapping;
    private final String nativeKey;
    private final String staticKey;
    private final String videoKey;

    public MoatVerificationProvider(String str, String str2, String str3, Function1<? super NimbusAd, ? extends Map<String, String>> function1) {
        Intrinsics.checkNotNullParameter(str, "staticKey");
        Intrinsics.checkNotNullParameter(str2, "videoKey");
        Intrinsics.checkNotNullParameter(str3, "nativeKey");
        Intrinsics.checkNotNullParameter(function1, "mapping");
        this.staticKey = str;
        this.videoKey = str2;
        this.nativeKey = str3;
        this.mapping = function1;
    }

    public final String getStaticKey() {
        return this.staticKey;
    }

    public final String getVideoKey() {
        return this.videoKey;
    }

    public final String getNativeKey() {
        return this.nativeKey;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ MoatVerificationProvider(String str, String str2, String str3, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i & 8) != 0 ? AnonymousClass1.INSTANCE : function1);
    }

    public final Function1<NimbusAd, Map<String, String>> getMapping() {
        return this.mapping;
    }

    public final String verificationUrl(NimbusAd nimbusAd) {
        String str;
        Intrinsics.checkNotNullParameter(nimbusAd, "nimbusAd");
        StringBuilder sb = new StringBuilder("https://z.moatads.com/");
        String type = nimbusAd.type();
        int hashCode = type.hashCode();
        if (hashCode != -1052618729) {
            if (hashCode != -892481938) {
                if (hashCode == 112202875 && type.equals(MimeTypes.BASE_TYPE_VIDEO)) {
                    str = this.videoKey + "/moatvideo.js";
                    return sb.append(str).toString();
                }
            } else if (type.equals(StaticAdRenderer.STATIC_AD_TYPE)) {
                str = this.staticKey + "/moatad.js";
                return sb.append(str).toString();
            }
        } else if (type.equals(SentryStackFrame.JsonKeys.NATIVE)) {
            str = this.nativeKey + "/moatad.js";
            return sb.append(str).toString();
        }
        str = "";
        return sb.append(str).toString();
    }

    public final String verificationParams(NimbusAd nimbusAd) {
        Intrinsics.checkNotNullParameter(nimbusAd, "nimbusAd");
        Set entrySet = this.mapping.invoke(nimbusAd).entrySet();
        if (Intrinsics.areEqual((Object) nimbusAd.type(), (Object) StaticAdRenderer.STATIC_AD_TYPE)) {
            return CollectionsKt.joinToString$default(entrySet, "&amp", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, MoatVerificationProvider$verificationParams$1$1.INSTANCE, 30, (Object) null);
        }
        return CollectionsKt.joinToString$default(entrySet, ",", "{", "}", 0, (CharSequence) null, MoatVerificationProvider$verificationParams$1$2.INSTANCE, 24, (Object) null);
    }

    public String verificationMarkup(NimbusAd nimbusAd) {
        Intrinsics.checkNotNullParameter(nimbusAd, "ad");
        String verificationParams = verificationParams(nimbusAd);
        return "\n            <noscript class=\"MOAT-" + this.staticKey + '?' + verificationParams + "\"></noscript>\n            <script src=\"" + verificationUrl(nimbusAd) + '#' + verificationParams + "\" type=\"text/javascript\"></script>\n        ";
    }

    public VerificationScriptResource verificationResource(NimbusAd nimbusAd) {
        Intrinsics.checkNotNullParameter(nimbusAd, "ad");
        VerificationScriptResource createVerificationScriptResourceWithParameters = VerificationScriptResource.createVerificationScriptResourceWithParameters("moat.com-" + (Intrinsics.areEqual((Object) nimbusAd.type(), (Object) MimeTypes.BASE_TYPE_VIDEO) ? this.videoKey : this.nativeKey), new URL(verificationUrl(nimbusAd)), verificationParams(nimbusAd));
        Intrinsics.checkNotNullExpressionValue(createVerificationScriptResourceWithParameters, "createVerificationScript…ationParams(ad)\n        )");
        return createVerificationScriptResourceWithParameters;
    }
}

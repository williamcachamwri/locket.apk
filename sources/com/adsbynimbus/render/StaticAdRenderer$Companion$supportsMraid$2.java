package com.adsbynimbus.render;

import androidx.webkit.WebViewFeature;
import com.adsbynimbus.render.StaticAdRenderer;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: StaticAdRenderer.kt */
final class StaticAdRenderer$Companion$supportsMraid$2 extends Lambda implements Function0<Boolean> {
    public static final StaticAdRenderer$Companion$supportsMraid$2 INSTANCE = new StaticAdRenderer$Companion$supportsMraid$2();

    StaticAdRenderer$Companion$supportsMraid$2() {
        super(0);
    }

    public final Boolean invoke() {
        Boolean bool;
        StaticAdRenderer.Companion companion = StaticAdRenderer.Companion;
        try {
            Result.Companion companion2 = Result.Companion;
            bool = Result.m2444constructorimpl(Boolean.valueOf(WebViewFeature.isFeatureSupported("WEB_MESSAGE_LISTENER")));
        } catch (Throwable th) {
            Result.Companion companion3 = Result.Companion;
            bool = Result.m2444constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m2450isFailureimpl(bool)) {
            bool = false;
        }
        return (Boolean) bool;
    }
}

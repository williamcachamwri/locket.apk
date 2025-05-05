package com.adsbynimbus;

import java.util.ArrayList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0012\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\f\u0010\b\u001a\u00020\t*\u00020\tH\u0007R\u0018\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002R\u0018\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u00078\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/adsbynimbus/ViewabilityProvider;", "", "()V", "thirdPartyViewabilityEnabled", "", "getThirdPartyViewabilityEnabled$annotations", "verificationProviders", "", "addOmid", "", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ViewabilityProvider.kt */
public final class ViewabilityProvider {
    public static final ViewabilityProvider INSTANCE = new ViewabilityProvider();
    public static boolean thirdPartyViewabilityEnabled = true;
    public static List<Object> verificationProviders = new ArrayList();

    @Deprecated(message = "This property will be removed in a future release", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static /* synthetic */ void getThirdPartyViewabilityEnabled$annotations() {
    }

    private ViewabilityProvider() {
    }

    @Deprecated(message = "This method will be removed in a future release", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public final byte[] addOmid(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return ArraysKt.contains(bArr, (byte) 7) ? bArr : ArraysKt.plus(bArr, (byte) 7);
    }
}

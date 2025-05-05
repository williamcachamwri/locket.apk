package com.adsbynimbus.render.internal;

import android.view.View;
import com.adsbynimbus.Nimbus;
import com.adsbynimbus.VerificationProvider;
import com.adsbynimbus.ViewabilityProvider;
import com.adsbynimbus.internal.PlatformKt;
import com.iab.omid.library.adsbynimbus.Omid;
import com.iab.omid.library.adsbynimbus.adsession.Partner;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u001f\u001a\u00020 *\u00020 H\u0000\"\u0014\u0010\u0000\u001a\u00020\u00018@X\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0003\"=\u0010\u0004\u001a%\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f\"\u0015\u0010\u0010\u001a\u00020\u00018À\u0002X\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0003\"\u0010\u0010\u0012\u001a\u00020\u00138\u0000X\u0004¢\u0006\u0002\n\u0000\"\u001b\u0010\u0014\u001a\u00020\u00158@X\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u0017\" \u0010\u001a\u001a\r\u0012\t\u0012\u00070\u001b¢\u0006\u0002\b\u001c0\u00068À\u0002X\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e¨\u0006!"}, d2 = {"injectRender", "", "getInjectRender", "()Z", "internalObstructionListener", "Lkotlin/Function1;", "", "Landroid/view/View;", "Lkotlin/ParameterName;", "name", "views", "", "getInternalObstructionListener", "()Lkotlin/jvm/functions/Function1;", "setInternalObstructionListener", "(Lkotlin/jvm/functions/Function1;)V", "omSdkIsInitialized", "getOmSdkIsInitialized", "partner", "Lcom/iab/omid/library/adsbynimbus/adsession/Partner;", "serviceJs", "", "getServiceJs", "()Ljava/lang/String;", "serviceJs$delegate", "Lkotlin/Lazy;", "verificationProviders", "Lcom/adsbynimbus/VerificationProvider;", "Lkotlin/internal/NoInfer;", "getVerificationProviders", "()Ljava/util/List;", "applyOM", "Lcom/adsbynimbus/NimbusAd;", "render_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: OpenMeasurement.kt */
public final class OpenMeasurement {
    private static Function1<? super List<? extends View>, Unit> internalObstructionListener;
    public static final Partner partner;
    private static final Lazy serviceJs$delegate = LazyKt.lazy(OpenMeasurement$serviceJs$2.INSTANCE);

    static {
        Partner createPartner = Partner.createPartner(Nimbus.sdkName, "2.26.1");
        Intrinsics.checkNotNullExpressionValue(createPartner, "createPartner(Nimbus.sdkName, Nimbus.version)");
        partner = createPartner;
    }

    public static final boolean getOmSdkIsInitialized() {
        if (!Omid.isActive()) {
            Nimbus nimbus = Nimbus.INSTANCE;
            Omid.activate(PlatformKt.getApplication());
            Unit unit = Unit.INSTANCE;
            return Omid.isActive();
        }
    }

    public static final String getServiceJs() {
        return (String) serviceJs$delegate.getValue();
    }

    public static final List<VerificationProvider> getVerificationProviders() {
        Collection arrayList = new ArrayList();
        for (Object next : ViewabilityProvider.verificationProviders) {
            if (next instanceof VerificationProvider) {
                arrayList.add(next);
            }
        }
        return (List) arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean getInjectRender() {
        /*
            boolean r0 = com.adsbynimbus.Nimbus.getThirdPartyViewabilityEnabled()
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x0034
            java.util.List<java.lang.Object> r0 = com.adsbynimbus.ViewabilityProvider.verificationProviders
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Collection r3 = (java.util.Collection) r3
            java.util.Iterator r0 = r0.iterator()
        L_0x0017:
            boolean r4 = r0.hasNext()
            if (r4 == 0) goto L_0x0029
            java.lang.Object r4 = r0.next()
            boolean r5 = r4 instanceof com.adsbynimbus.VerificationProvider
            if (r5 == 0) goto L_0x0017
            r3.add(r4)
            goto L_0x0017
        L_0x0029:
            java.util.List r3 = (java.util.List) r3
            java.util.Collection r3 = (java.util.Collection) r3
            boolean r0 = r3.isEmpty()
            r0 = r0 ^ r2
            if (r0 == 0) goto L_0x0054
        L_0x0034:
            boolean r0 = com.iab.omid.library.adsbynimbus.Omid.isActive()
            if (r0 != 0) goto L_0x0050
            com.adsbynimbus.Nimbus r0 = com.adsbynimbus.Nimbus.INSTANCE
            android.app.Application r0 = com.adsbynimbus.internal.PlatformKt.getApplication()
            android.content.Context r0 = (android.content.Context) r0
            com.iab.omid.library.adsbynimbus.Omid.activate(r0)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            boolean r0 = com.iab.omid.library.adsbynimbus.Omid.isActive()
            if (r0 == 0) goto L_0x004e
            goto L_0x0050
        L_0x004e:
            r0 = r1
            goto L_0x0051
        L_0x0050:
            r0 = r2
        L_0x0051:
            if (r0 == 0) goto L_0x0054
            r1 = r2
        L_0x0054:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.render.internal.OpenMeasurement.getInjectRender():boolean");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: com.adsbynimbus.NimbusAd} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v12, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v18, resolved type: com.adsbynimbus.NimbusAd} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v19, resolved type: com.adsbynimbus.NimbusAd} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final com.adsbynimbus.NimbusAd applyOM(com.adsbynimbus.NimbusAd r5) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            boolean r0 = getInjectRender()
            if (r0 == 0) goto L_0x0012
            boolean r0 = r5 instanceof com.adsbynimbus.render.internal.OMInjectedAd
            if (r0 == 0) goto L_0x0010
            goto L_0x0012
        L_0x0010:
            r0 = 0
            goto L_0x0013
        L_0x0012:
            r0 = 1
        L_0x0013:
            if (r0 == 0) goto L_0x0017
            r0 = r5
            goto L_0x0018
        L_0x0017:
            r0 = 0
        L_0x0018:
            if (r0 != 0) goto L_0x0063
            kotlin.Result$Companion r0 = kotlin.Result.Companion     // Catch:{ all -> 0x004d }
            java.lang.String r0 = getServiceJs()     // Catch:{ all -> 0x004d }
            java.util.List<java.lang.Object> r1 = com.adsbynimbus.ViewabilityProvider.verificationProviders     // Catch:{ all -> 0x004d }
            java.lang.Iterable r1 = (java.lang.Iterable) r1     // Catch:{ all -> 0x004d }
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x004d }
            r2.<init>()     // Catch:{ all -> 0x004d }
            java.util.Collection r2 = (java.util.Collection) r2     // Catch:{ all -> 0x004d }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x004d }
        L_0x002f:
            boolean r3 = r1.hasNext()     // Catch:{ all -> 0x004d }
            if (r3 == 0) goto L_0x0041
            java.lang.Object r3 = r1.next()     // Catch:{ all -> 0x004d }
            boolean r4 = r3 instanceof com.adsbynimbus.VerificationProvider     // Catch:{ all -> 0x004d }
            if (r4 == 0) goto L_0x002f
            r2.add(r3)     // Catch:{ all -> 0x004d }
            goto L_0x002f
        L_0x0041:
            java.util.List r2 = (java.util.List) r2     // Catch:{ all -> 0x004d }
            com.adsbynimbus.render.internal.OMInjectedAd r1 = new com.adsbynimbus.render.internal.OMInjectedAd     // Catch:{ all -> 0x004d }
            r1.<init>(r5, r0, r2)     // Catch:{ all -> 0x004d }
            java.lang.Object r0 = kotlin.Result.m2444constructorimpl(r1)     // Catch:{ all -> 0x004d }
            goto L_0x0058
        L_0x004d:
            r0 = move-exception
            kotlin.Result$Companion r1 = kotlin.Result.Companion
            java.lang.Object r0 = kotlin.ResultKt.createFailure(r0)
            java.lang.Object r0 = kotlin.Result.m2444constructorimpl(r0)
        L_0x0058:
            boolean r1 = kotlin.Result.m2450isFailureimpl(r0)
            if (r1 == 0) goto L_0x005f
            goto L_0x0060
        L_0x005f:
            r5 = r0
        L_0x0060:
            r0 = r5
            com.adsbynimbus.NimbusAd r0 = (com.adsbynimbus.NimbusAd) r0
        L_0x0063:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.render.internal.OpenMeasurement.applyOM(com.adsbynimbus.NimbusAd):com.adsbynimbus.NimbusAd");
    }

    public static final Function1<List<? extends View>, Unit> getInternalObstructionListener() {
        return internalObstructionListener;
    }

    public static final void setInternalObstructionListener(Function1<? super List<? extends View>, Unit> function1) {
        internalObstructionListener = function1;
    }
}

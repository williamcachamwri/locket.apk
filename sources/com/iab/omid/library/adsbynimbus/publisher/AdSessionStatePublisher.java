package com.iab.omid.library.adsbynimbus.publisher;

import android.webkit.WebView;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.iab.omid.library.adsbynimbus.adsession.AdEvents;
import com.iab.omid.library.adsbynimbus.adsession.AdSessionConfiguration;
import com.iab.omid.library.adsbynimbus.adsession.AdSessionContext;
import com.iab.omid.library.adsbynimbus.adsession.ErrorType;
import com.iab.omid.library.adsbynimbus.adsession.VerificationScriptResource;
import com.iab.omid.library.adsbynimbus.adsession.media.MediaEvents;
import com.iab.omid.library.adsbynimbus.internal.g;
import com.iab.omid.library.adsbynimbus.internal.h;
import com.iab.omid.library.adsbynimbus.utils.c;
import com.iab.omid.library.adsbynimbus.utils.f;
import com.iab.omid.library.adsbynimbus.weakreference.b;
import io.sentry.ProfilingTraceData;
import io.sentry.protocol.App;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class AdSessionStatePublisher {

    /* renamed from: a  reason: collision with root package name */
    private String f109a;
    private b b = new b((WebView) null);
    private AdEvents c;
    private MediaEvents d;
    private a e;
    private long f;

    enum a {
        AD_STATE_IDLE,
        AD_STATE_VISIBLE,
        AD_STATE_NOTVISIBLE
    }

    public AdSessionStatePublisher(String str) {
        a();
        this.f109a = str;
    }

    public void a() {
        this.f = f.b();
        this.e = a.AD_STATE_IDLE;
    }

    public void a(float f2) {
        h.a().a(getWebView(), this.f109a, f2);
    }

    /* access modifiers changed from: package-private */
    public void a(WebView webView) {
        this.b = new b(webView);
    }

    public void a(AdEvents adEvents) {
        this.c = adEvents;
    }

    public void a(AdSessionConfiguration adSessionConfiguration) {
        h.a().a(getWebView(), this.f109a, adSessionConfiguration.toJsonObject());
    }

    public void a(ErrorType errorType, String str) {
        h.a().a(getWebView(), this.f109a, errorType, str);
    }

    public void a(com.iab.omid.library.adsbynimbus.adsession.a aVar, AdSessionContext adSessionContext) {
        a(aVar, adSessionContext, (JSONObject) null);
    }

    /* access modifiers changed from: protected */
    public void a(com.iab.omid.library.adsbynimbus.adsession.a aVar, AdSessionContext adSessionContext, JSONObject jSONObject) {
        String adSessionId = aVar.getAdSessionId();
        JSONObject jSONObject2 = new JSONObject();
        c.a(jSONObject2, "environment", App.TYPE);
        c.a(jSONObject2, "adSessionType", adSessionContext.getAdSessionContextType());
        c.a(jSONObject2, "deviceInfo", com.iab.omid.library.adsbynimbus.utils.b.d());
        c.a(jSONObject2, "deviceCategory", com.iab.omid.library.adsbynimbus.utils.a.a().toString());
        JSONArray jSONArray = new JSONArray();
        jSONArray.put("clid");
        jSONArray.put("vlid");
        c.a(jSONObject2, "supports", jSONArray);
        JSONObject jSONObject3 = new JSONObject();
        c.a(jSONObject3, "partnerName", adSessionContext.getPartner().getName());
        c.a(jSONObject3, "partnerVersion", adSessionContext.getPartner().getVersion());
        c.a(jSONObject2, "omidNativeInfo", jSONObject3);
        JSONObject jSONObject4 = new JSONObject();
        c.a(jSONObject4, "libraryVersion", "1.5.2-Adsbynimbus");
        c.a(jSONObject4, RemoteConfigConstants.RequestFieldKey.APP_ID, g.b().a().getApplicationContext().getPackageName());
        c.a(jSONObject2, App.TYPE, jSONObject4);
        if (adSessionContext.getContentUrl() != null) {
            c.a(jSONObject2, "contentUrl", adSessionContext.getContentUrl());
        }
        if (adSessionContext.getCustomReferenceData() != null) {
            c.a(jSONObject2, "customReferenceData", adSessionContext.getCustomReferenceData());
        }
        JSONObject jSONObject5 = new JSONObject();
        for (VerificationScriptResource next : adSessionContext.getVerificationScriptResources()) {
            c.a(jSONObject5, next.getVendorKey(), next.getVerificationParameters());
        }
        h.a().a(getWebView(), adSessionId, jSONObject2, jSONObject5, jSONObject);
    }

    public void a(MediaEvents mediaEvents) {
        this.d = mediaEvents;
    }

    public void a(String str) {
        a(str, (JSONObject) null);
    }

    public void a(String str, long j) {
        a aVar;
        if (j >= this.f && this.e != (aVar = a.AD_STATE_NOTVISIBLE)) {
            this.e = aVar;
            h.a().b(getWebView(), this.f109a, str);
        }
    }

    public void a(String str, JSONObject jSONObject) {
        h.a().a(getWebView(), this.f109a, str, jSONObject);
    }

    public void a(Date date) {
        if (date != null) {
            JSONObject jSONObject = new JSONObject();
            c.a(jSONObject, "timestamp", Long.valueOf(date.getTime()));
            h.a().a(getWebView(), jSONObject);
        }
    }

    public void a(JSONObject jSONObject) {
        h.a().b(getWebView(), this.f109a, jSONObject);
    }

    public void a(boolean z) {
        if (e()) {
            h.a().c(getWebView(), this.f109a, z ? "foregrounded" : ProfilingTraceData.TRUNCATION_REASON_BACKGROUNDED);
        }
    }

    public void b() {
        this.b.clear();
    }

    public void b(String str, long j) {
        if (j >= this.f) {
            this.e = a.AD_STATE_VISIBLE;
            h.a().b(getWebView(), this.f109a, str);
        }
    }

    public void b(boolean z) {
        if (e()) {
            h.a().a(getWebView(), this.f109a, z ? "locked" : "unlocked");
        }
    }

    public AdEvents c() {
        return this.c;
    }

    public MediaEvents d() {
        return this.d;
    }

    public boolean e() {
        return this.b.get() != null;
    }

    public void f() {
        h.a().a(getWebView(), this.f109a);
    }

    public void g() {
        h.a().b(getWebView(), this.f109a);
    }

    public WebView getWebView() {
        return (WebView) this.b.get();
    }

    public void h() {
        a((JSONObject) null);
    }

    public void i() {
    }
}

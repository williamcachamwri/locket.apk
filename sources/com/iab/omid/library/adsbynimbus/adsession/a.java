package com.iab.omid.library.adsbynimbus.adsession;

import android.view.View;
import com.iab.omid.library.adsbynimbus.internal.c;
import com.iab.omid.library.adsbynimbus.internal.e;
import com.iab.omid.library.adsbynimbus.internal.f;
import com.iab.omid.library.adsbynimbus.internal.i;
import com.iab.omid.library.adsbynimbus.publisher.AdSessionStatePublisher;
import com.iab.omid.library.adsbynimbus.publisher.b;
import com.iab.omid.library.adsbynimbus.utils.g;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.json.JSONObject;

public class a extends AdSession {

    /* renamed from: a  reason: collision with root package name */
    private final AdSessionContext f90a;
    private final AdSessionConfiguration b;
    private final f c;
    private com.iab.omid.library.adsbynimbus.weakreference.a d;
    private AdSessionStatePublisher e;
    private boolean f;
    private boolean g;
    private final String h;
    private boolean i;
    private boolean j;
    private PossibleObstructionListener k;

    a(AdSessionConfiguration adSessionConfiguration, AdSessionContext adSessionContext) {
        this(adSessionConfiguration, adSessionContext, UUID.randomUUID().toString());
    }

    a(AdSessionConfiguration adSessionConfiguration, AdSessionContext adSessionContext, String str) {
        this.c = new f();
        this.f = false;
        this.g = false;
        this.b = adSessionConfiguration;
        this.f90a = adSessionContext;
        this.h = str;
        b((View) null);
        this.e = (adSessionContext.getAdSessionContextType() == AdSessionContextType.HTML || adSessionContext.getAdSessionContextType() == AdSessionContextType.JAVASCRIPT) ? new com.iab.omid.library.adsbynimbus.publisher.a(str, adSessionContext.getWebView()) : new b(str, adSessionContext.getInjectedResourcesMap(), adSessionContext.getOmidJsScriptContent());
        this.e.i();
        c.c().a(this);
        this.e.a(adSessionConfiguration);
    }

    private void a() {
        if (this.i) {
            throw new IllegalStateException("Impression event can only be sent once");
        }
    }

    private void a(View view) {
        Collection<a> b2 = c.c().b();
        if (b2 != null && !b2.isEmpty()) {
            for (a next : b2) {
                if (next != this && next.c() == view) {
                    next.d.clear();
                }
            }
        }
    }

    private void b() {
        if (this.j) {
            throw new IllegalStateException("Loaded event can only be sent once");
        }
    }

    private void b(View view) {
        this.d = new com.iab.omid.library.adsbynimbus.weakreference.a(view);
    }

    public void a(List<com.iab.omid.library.adsbynimbus.weakreference.a> list) {
        if (e()) {
            ArrayList arrayList = new ArrayList();
            for (com.iab.omid.library.adsbynimbus.weakreference.a aVar : list) {
                View view = (View) aVar.get();
                if (view != null) {
                    arrayList.add(view);
                }
            }
            this.k.onPossibleObstructionsDetected(this.h, arrayList);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(JSONObject jSONObject) {
        b();
        getAdSessionStatePublisher().a(jSONObject);
        this.j = true;
    }

    public void addFriendlyObstruction(View view, FriendlyObstructionPurpose friendlyObstructionPurpose, String str) {
        if (!this.g) {
            this.c.a(view, friendlyObstructionPurpose, str);
        }
    }

    public View c() {
        return (View) this.d.get();
    }

    public List<e> d() {
        return this.c.a();
    }

    public boolean e() {
        return this.k != null;
    }

    public void error(ErrorType errorType, String str) {
        if (!this.g) {
            g.a((Object) errorType, "Error type is null");
            g.a(str, "Message is null");
            getAdSessionStatePublisher().a(errorType, str);
            return;
        }
        throw new IllegalStateException("AdSession is finished");
    }

    public boolean f() {
        return this.f && !this.g;
    }

    public void finish() {
        if (!this.g) {
            this.d.clear();
            removeAllFriendlyObstructions();
            this.g = true;
            getAdSessionStatePublisher().f();
            c.c().b(this);
            getAdSessionStatePublisher().b();
            this.e = null;
            this.k = null;
        }
    }

    public boolean g() {
        return this.g;
    }

    public String getAdSessionId() {
        return this.h;
    }

    public AdSessionStatePublisher getAdSessionStatePublisher() {
        return this.e;
    }

    public boolean h() {
        return this.b.isNativeImpressionOwner();
    }

    public boolean i() {
        return this.b.isNativeMediaEventsOwner();
    }

    public boolean j() {
        return this.f;
    }

    /* access modifiers changed from: package-private */
    public void k() {
        a();
        getAdSessionStatePublisher().g();
        this.i = true;
    }

    /* access modifiers changed from: package-private */
    public void l() {
        b();
        getAdSessionStatePublisher().h();
        this.j = true;
    }

    public void registerAdView(View view) {
        if (!this.g && c() != view) {
            b(view);
            getAdSessionStatePublisher().a();
            a(view);
        }
    }

    public void removeAllFriendlyObstructions() {
        if (!this.g) {
            this.c.b();
        }
    }

    public void removeFriendlyObstruction(View view) {
        if (!this.g) {
            this.c.c(view);
        }
    }

    public void setPossibleObstructionListener(PossibleObstructionListener possibleObstructionListener) {
        this.k = possibleObstructionListener;
    }

    public void start() {
        if (!this.f && this.e != null) {
            this.f = true;
            c.c().c(this);
            this.e.a(i.c().b());
            this.e.a(com.iab.omid.library.adsbynimbus.internal.a.a().b());
            this.e.a(this, this.f90a);
        }
    }
}

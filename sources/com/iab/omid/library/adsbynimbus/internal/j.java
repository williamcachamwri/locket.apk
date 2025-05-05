package com.iab.omid.library.adsbynimbus.internal;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.lang.ref.WeakReference;

public class j {
    private static j d = new j();

    /* renamed from: a  reason: collision with root package name */
    private WeakReference<Context> f104a;
    /* access modifiers changed from: private */
    public boolean b = false;
    /* access modifiers changed from: private */
    public boolean c = false;

    class a extends BroadcastReceiver {
        a() {
        }

        public void onReceive(Context context, Intent intent) {
            j jVar;
            boolean a2;
            boolean z;
            if (intent.getAction().equals("android.intent.action.SCREEN_OFF")) {
                jVar = j.this;
                a2 = jVar.c;
                z = true;
            } else if (intent.getAction().equals("android.intent.action.SCREEN_ON")) {
                jVar = j.this;
                a2 = jVar.c;
                z = false;
            } else {
                return;
            }
            jVar.a(z, a2);
            boolean unused = j.this.b = z;
        }
    }

    public static j b() {
        return d;
    }

    public void a() {
        Context context = (Context) this.f104a.get();
        if (context != null) {
            boolean isDeviceLocked = ((KeyguardManager) context.getSystemService("keyguard")).isDeviceLocked();
            a(this.b, isDeviceLocked);
            this.c = isDeviceLocked;
        }
    }

    public void a(Context context) {
        if (context != null) {
            this.f104a = new WeakReference<>(context);
            IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            context.registerReceiver(new a(), intentFilter);
        }
    }

    public void a(boolean z, boolean z2) {
        if ((z2 || z) != (this.c || this.b)) {
            for (com.iab.omid.library.adsbynimbus.adsession.a adSessionStatePublisher : c.c().b()) {
                adSessionStatePublisher.getAdSessionStatePublisher().b(z2 || z);
            }
        }
    }
}

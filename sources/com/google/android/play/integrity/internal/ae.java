package com.google.android.play.integrity.internal;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
public final class ae {

    /* renamed from: a  reason: collision with root package name */
    private static final Map f77a = new HashMap();
    /* access modifiers changed from: private */
    public final Context b;
    /* access modifiers changed from: private */
    public final s c;
    private final String d;
    /* access modifiers changed from: private */
    public final List e = new ArrayList();
    private final Set f = new HashSet();
    /* access modifiers changed from: private */
    public final Object g = new Object();
    /* access modifiers changed from: private */
    public boolean h;
    private final Intent i;
    /* access modifiers changed from: private */
    public final z j;
    private final WeakReference k;
    private final IBinder.DeathRecipient l = new u(this);
    /* access modifiers changed from: private */
    public final AtomicInteger m = new AtomicInteger(0);
    /* access modifiers changed from: private */
    public ServiceConnection n;
    /* access modifiers changed from: private */
    public IInterface o;

    public ae(Context context, s sVar, String str, Intent intent, z zVar, y yVar) {
        this.b = context;
        this.c = sVar;
        this.d = str;
        this.i = intent;
        this.j = zVar;
        this.k = new WeakReference((Object) null);
    }

    public static /* synthetic */ void k(ae aeVar) {
        aeVar.c.d("reportBinderDeath", new Object[0]);
        y yVar = (y) aeVar.k.get();
        if (yVar != null) {
            aeVar.c.d("calling onBinderDied", new Object[0]);
            yVar.a();
        } else {
            aeVar.c.d("%s : Binder has died.", aeVar.d);
            for (t a2 : aeVar.e) {
                a2.a(aeVar.w());
            }
            aeVar.e.clear();
        }
        synchronized (aeVar.g) {
            aeVar.x();
        }
    }

    static /* bridge */ /* synthetic */ void o(ae aeVar, TaskCompletionSource taskCompletionSource) {
        aeVar.f.add(taskCompletionSource);
        taskCompletionSource.getTask().addOnCompleteListener(new v(aeVar, taskCompletionSource));
    }

    static /* bridge */ /* synthetic */ void r(ae aeVar) {
        aeVar.c.d("linkToDeath", new Object[0]);
        try {
            aeVar.o.asBinder().linkToDeath(aeVar.l, 0);
        } catch (RemoteException e2) {
            aeVar.c.c(e2, "linkToDeath failed", new Object[0]);
        }
    }

    static /* bridge */ /* synthetic */ void s(ae aeVar) {
        aeVar.c.d("unlinkToDeath", new Object[0]);
        aeVar.o.asBinder().unlinkToDeath(aeVar.l, 0);
    }

    private final RemoteException w() {
        return new RemoteException(String.valueOf(this.d).concat(" : Binder has died."));
    }

    /* access modifiers changed from: private */
    public final void x() {
        for (TaskCompletionSource trySetException : this.f) {
            trySetException.trySetException(w());
        }
        this.f.clear();
    }

    public final Handler c() {
        Handler handler;
        Map map = f77a;
        synchronized (map) {
            if (!map.containsKey(this.d)) {
                HandlerThread handlerThread = new HandlerThread(this.d, 10);
                handlerThread.start();
                map.put(this.d, new Handler(handlerThread.getLooper()));
            }
            handler = (Handler) map.get(this.d);
        }
        return handler;
    }

    public final IInterface e() {
        return this.o;
    }

    public final void t(t tVar, TaskCompletionSource taskCompletionSource) {
        c().post(new w(this, tVar.c(), taskCompletionSource, tVar));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void u(TaskCompletionSource taskCompletionSource, Task task) {
        synchronized (this.g) {
            this.f.remove(taskCompletionSource);
        }
    }

    public final void v(TaskCompletionSource taskCompletionSource) {
        synchronized (this.g) {
            this.f.remove(taskCompletionSource);
        }
        c().post(new x(this));
    }

    static /* bridge */ /* synthetic */ void q(ae aeVar, t tVar) {
        if (aeVar.o == null && !aeVar.h) {
            aeVar.c.d("Initiate binding to the service.", new Object[0]);
            aeVar.e.add(tVar);
            ad adVar = new ad(aeVar, (ac) null);
            aeVar.n = adVar;
            aeVar.h = true;
            if (!aeVar.b.bindService(aeVar.i, adVar, 1)) {
                aeVar.c.d("Failed to bind to the service.", new Object[0]);
                aeVar.h = false;
                for (t a2 : aeVar.e) {
                    a2.a(new af());
                }
                aeVar.e.clear();
            }
        } else if (aeVar.h) {
            aeVar.c.d("Waiting to bind to the service.", new Object[0]);
            aeVar.e.add(tVar);
        } else {
            tVar.run();
        }
    }
}

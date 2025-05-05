package com.iab.omid.library.adsbynimbus.walking.async;

import android.os.AsyncTask;
import java.util.concurrent.ThreadPoolExecutor;
import org.json.JSONObject;

public abstract class b extends AsyncTask<Object, Void, String> {

    /* renamed from: a  reason: collision with root package name */
    private a f122a;
    protected final C0011b b;

    public interface a {
        void a(b bVar);
    }

    /* renamed from: com.iab.omid.library.adsbynimbus.walking.async.b$b  reason: collision with other inner class name */
    public interface C0011b {
        JSONObject a();

        void a(JSONObject jSONObject);
    }

    public b(C0011b bVar) {
        this.b = bVar;
    }

    public void a(a aVar) {
        this.f122a = aVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void onPostExecute(String str) {
        a aVar = this.f122a;
        if (aVar != null) {
            aVar.a(this);
        }
    }

    public void a(ThreadPoolExecutor threadPoolExecutor) {
        executeOnExecutor(threadPoolExecutor, new Object[0]);
    }
}

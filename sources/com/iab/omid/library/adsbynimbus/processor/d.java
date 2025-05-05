package com.iab.omid.library.adsbynimbus.processor;

import android.view.View;
import android.view.ViewGroup;
import com.iab.omid.library.adsbynimbus.processor.a;
import com.iab.omid.library.adsbynimbus.utils.c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;

public class d implements a {

    /* renamed from: a  reason: collision with root package name */
    private final int[] f108a = new int[2];

    private void a(ViewGroup viewGroup, JSONObject jSONObject, a.C0008a aVar, boolean z) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            aVar.a(viewGroup.getChildAt(i), this, jSONObject, z);
        }
    }

    private void b(ViewGroup viewGroup, JSONObject jSONObject, a.C0008a aVar, boolean z) {
        HashMap hashMap = new HashMap();
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            ArrayList arrayList = (ArrayList) hashMap.get(Float.valueOf(childAt.getZ()));
            if (arrayList == null) {
                arrayList = new ArrayList();
                hashMap.put(Float.valueOf(childAt.getZ()), arrayList);
            }
            arrayList.add(childAt);
        }
        ArrayList arrayList2 = new ArrayList(hashMap.keySet());
        Collections.sort(arrayList2);
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            Iterator it2 = ((ArrayList) hashMap.get((Float) it.next())).iterator();
            while (it2.hasNext()) {
                aVar.a((View) it2.next(), this, jSONObject, z);
            }
        }
    }

    public JSONObject a(View view) {
        if (view == null) {
            return c.a(0, 0, 0, 0);
        }
        int width = view.getWidth();
        int height = view.getHeight();
        view.getLocationOnScreen(this.f108a);
        int[] iArr = this.f108a;
        return c.a(iArr[0], iArr[1], width, height);
    }

    public void a(View view, JSONObject jSONObject, a.C0008a aVar, boolean z, boolean z2) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (z) {
                b(viewGroup, jSONObject, aVar, z2);
            } else {
                a(viewGroup, jSONObject, aVar, z2);
            }
        }
    }
}

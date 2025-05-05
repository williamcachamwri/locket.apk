package io.invertase.firebase.perf;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.perf.FirebasePerformance;
import com.google.firebase.perf.metrics.HttpMetric;
import com.google.firebase.perf.metrics.Trace;
import io.invertase.firebase.common.UniversalFirebaseModule;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class UniversalFirebasePerfModule extends UniversalFirebaseModule {
    private static SparseArray<HttpMetric> httpMetrics = new SparseArray<>();
    private static SparseArray<ScreenTrace> screenTraces = new SparseArray<>();
    private static SparseArray<Trace> traces = new SparseArray<>();

    UniversalFirebasePerfModule(Context context, String str) {
        super(context, str);
    }

    public void onTearDown() {
        super.onTearDown();
        traces.clear();
        httpMetrics.clear();
        screenTraces.clear();
    }

    public Map<String, Object> getConstants() {
        HashMap hashMap = new HashMap();
        hashMap.put("isPerformanceCollectionEnabled", Boolean.valueOf(FirebasePerformance.getInstance().isPerformanceCollectionEnabled()));
        hashMap.put("isInstrumentationEnabled", true);
        return hashMap;
    }

    /* access modifiers changed from: package-private */
    public Task<Boolean> setPerformanceCollectionEnabled(Boolean bool) {
        return Tasks.call(new UniversalFirebasePerfModule$$ExternalSyntheticLambda4(bool));
    }

    /* access modifiers changed from: package-private */
    public Task<Void> startTrace(int i, String str) {
        return Tasks.call(new UniversalFirebasePerfModule$$ExternalSyntheticLambda3(str, i));
    }

    static /* synthetic */ Void lambda$startTrace$1(String str, int i) throws Exception {
        Trace newTrace = FirebasePerformance.getInstance().newTrace(str);
        newTrace.start();
        traces.put(i, newTrace);
        return null;
    }

    /* access modifiers changed from: package-private */
    public Task<Void> stopTrace(int i, Bundle bundle, Bundle bundle2) {
        return Tasks.call(new UniversalFirebasePerfModule$$ExternalSyntheticLambda5(i, bundle, bundle2));
    }

    static /* synthetic */ Void lambda$stopTrace$2(int i, Bundle bundle, Bundle bundle2) throws Exception {
        Trace trace = traces.get(i);
        Set<String> keySet = bundle.keySet();
        Set<String> keySet2 = bundle2.keySet();
        for (String str : keySet) {
            trace.putMetric(str, (long) Double.valueOf(((Double) bundle.get(str)).doubleValue()).intValue());
        }
        for (String str2 : keySet2) {
            trace.putAttribute(str2, (String) Objects.requireNonNull(bundle2.get(str2)));
        }
        trace.stop();
        traces.remove(i);
        return null;
    }

    /* access modifiers changed from: package-private */
    public Task<Void> startScreenTrace(Activity activity, int i, String str) {
        return Tasks.call(new UniversalFirebasePerfModule$$ExternalSyntheticLambda0(activity, str, i));
    }

    static /* synthetic */ Void lambda$startScreenTrace$3(Activity activity, String str, int i) throws Exception {
        ScreenTrace screenTrace = new ScreenTrace(activity, str);
        screenTrace.recordScreenTrace();
        screenTraces.put(i, screenTrace);
        return null;
    }

    /* access modifiers changed from: package-private */
    public Task<Void> stopScreenTrace(int i) {
        return Tasks.call(new UniversalFirebasePerfModule$$ExternalSyntheticLambda1(i));
    }

    static /* synthetic */ Void lambda$stopScreenTrace$4(int i) throws Exception {
        screenTraces.get(i).sendScreenTrace();
        screenTraces.remove(i);
        return null;
    }

    /* access modifiers changed from: package-private */
    public Task<Void> startHttpMetric(int i, String str, String str2) {
        return Tasks.call(new UniversalFirebasePerfModule$$ExternalSyntheticLambda6(str, str2, i));
    }

    static /* synthetic */ Void lambda$startHttpMetric$5(String str, String str2, int i) throws Exception {
        HttpMetric newHttpMetric = FirebasePerformance.getInstance().newHttpMetric(str, str2);
        newHttpMetric.start();
        httpMetrics.put(i, newHttpMetric);
        return null;
    }

    /* access modifiers changed from: package-private */
    public Task<Void> stopHttpMetric(int i, Bundle bundle, Bundle bundle2) {
        return Tasks.call(new UniversalFirebasePerfModule$$ExternalSyntheticLambda2(i, bundle, bundle2));
    }

    static /* synthetic */ Void lambda$stopHttpMetric$6(int i, Bundle bundle, Bundle bundle2) throws Exception {
        HttpMetric httpMetric = httpMetrics.get(i);
        if (bundle.containsKey("httpResponseCode")) {
            httpMetric.setHttpResponseCode((int) bundle.getDouble("httpResponseCode"));
        }
        if (bundle.containsKey("requestPayloadSize")) {
            httpMetric.setRequestPayloadSize((long) ((int) bundle.getDouble("requestPayloadSize")));
        }
        if (bundle.containsKey("responsePayloadSize")) {
            httpMetric.setResponsePayloadSize((long) ((int) bundle.getDouble("responsePayloadSize")));
        }
        if (bundle.containsKey("responseContentType")) {
            httpMetric.setResponseContentType(bundle.getString("responseContentType"));
        }
        for (String str : bundle2.keySet()) {
            httpMetric.putAttribute(str, (String) Objects.requireNonNull(bundle2.getString(str)));
        }
        httpMetric.stop();
        httpMetrics.remove(i);
        return null;
    }
}

package com.locket.Locket.Analytics;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import com.amplitude.api.Amplitude;
import com.amplitude.api.AmplitudeClient;
import com.amplitude.api.Identify;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.locket.Locket.Util;
import io.sentry.android.core.SentryLogcatAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.json.JSONObject;

public class Analytics {
    private static final String AMPLITUDE_API_KEY = "25340d2798ee21af509991697882791d";
    private static final AnalyticsDestination[] DEFAULT_DESTINATIONS = {AnalyticsDestination.FIREBASE, AnalyticsDestination.AMPLITUDE};
    private final AmplitudeClient mAmplitudeInstance;
    private final Context mContext;
    private final FirebaseAnalytics mFirebaseAnalytics;

    public Analytics(Context context) {
        this.mContext = context;
        this.mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
        this.mAmplitudeInstance = Amplitude.getInstance().trackSessionEvents(false).initialize(context, AMPLITUDE_API_KEY);
    }

    public void enableForegroundTracking(Application application) {
        this.mAmplitudeInstance.enableForegroundTracking(application);
    }

    public void setUserProperty(String str, String str2, AnalyticsDestination[] analyticsDestinationArr) {
        if (analyticsDestinationArr == null || analyticsDestinationArr.length <= 0) {
            analyticsDestinationArr = DEFAULT_DESTINATIONS;
        }
        for (AnalyticsDestination analyticsDestination : analyticsDestinationArr) {
            int i = AnonymousClass1.$SwitchMap$com$locket$Locket$Analytics$AnalyticsDestination[analyticsDestination.ordinal()];
            if (i == 1) {
                this.mFirebaseAnalytics.setUserProperty(str, str2);
            } else if (i == 2) {
                Identify identify = new Identify();
                identify.set(str, str2);
                this.mAmplitudeInstance.identify(identify);
            } else {
                throw new IllegalArgumentException("Unknown destination: " + analyticsDestination);
            }
        }
    }

    /* renamed from: com.locket.Locket.Analytics.Analytics$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$locket$Locket$Analytics$AnalyticsDestination;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.locket.Locket.Analytics.AnalyticsDestination[] r0 = com.locket.Locket.Analytics.AnalyticsDestination.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$locket$Locket$Analytics$AnalyticsDestination = r0
                com.locket.Locket.Analytics.AnalyticsDestination r1 = com.locket.Locket.Analytics.AnalyticsDestination.FIREBASE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$locket$Locket$Analytics$AnalyticsDestination     // Catch:{ NoSuchFieldError -> 0x001d }
                com.locket.Locket.Analytics.AnalyticsDestination r1 = com.locket.Locket.Analytics.AnalyticsDestination.AMPLITUDE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.locket.Locket.Analytics.Analytics.AnonymousClass1.<clinit>():void");
        }
    }

    public void logOpenedFromWidget() {
        this.mFirebaseAnalytics.logEvent("opened_from_widget", (Bundle) null);
        JSONObject jSONObject = new JSONObject();
        try {
            boolean z = false;
            int optInt = Util.getAppData(this.mContext).optInt("missed_moments_count", 0);
            jSONObject.put("badge_count", optInt);
            if (optInt > 0) {
                z = true;
            }
            jSONObject.put("badge_visible", z);
        } catch (Exception unused) {
            SentryLogcatAdapter.e("Locket", "Error setting badge_count in logOpenedFromWidget");
        }
        this.mAmplitudeInstance.logEvent("opened_from_widget", jSONObject);
    }

    public void updateActiveWidgetCount(int i) {
        this.mFirebaseAnalytics.setUserProperty("active_widgets_count", String.valueOf(i));
        Identify identify = new Identify();
        identify.set("active_widgets_count", i);
        this.mAmplitudeInstance.identify(identify);
    }

    public void updateCustomWidgetFrameProperties(ArrayList<String> arrayList) {
        List list = (List) arrayList.stream().map(new Analytics$$ExternalSyntheticLambda0()).collect(Collectors.toList());
        String obj = list.toString();
        String valueOf = String.valueOf(list.size());
        setUserProperty("custom_widget_frame_configurations", obj, (AnalyticsDestination[]) null);
        setUserProperty("custom_widget_frame_count", valueOf, (AnalyticsDestination[]) null);
    }

    static /* synthetic */ String lambda$updateCustomWidgetFrameProperties$0(String str) {
        return "widget_custom_frame/" + str;
    }
}

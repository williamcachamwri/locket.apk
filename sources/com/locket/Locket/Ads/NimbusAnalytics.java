package com.locket.Locket.Ads;

import android.util.Log;
import com.amplitude.api.Amplitude;
import com.amplitude.api.AmplitudeClient;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.sentry.android.core.SentryLogcatAdapter;
import java.util.Map;
import org.json.JSONObject;

public class NimbusAnalytics {
    private static final String TAG = "NimbusAnalytics";
    private final AmplitudeClient amplitudeClient = Amplitude.getInstance();

    public void logAdRequested(int i, NimbusAdRequestType nimbusAdRequestType, boolean z, boolean z2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(FirebaseAnalytics.Param.INDEX, i);
            jSONObject.put("type", nimbusAdRequestType.getRawValue());
            jSONObject.put("refresh", z);
            jSONObject.put("offscreen", z2);
        } catch (Exception e) {
            SentryLogcatAdapter.e(TAG, "Error creating properties for ad request", e);
        }
        this.amplitudeClient.logEvent("history_ad_requested", jSONObject);
        Log.d(TAG, "Logged history_ad_requested to Amplitude");
    }

    public void logAdRequestCompleted(int i, NimbusAdRequestType nimbusAdRequestType, boolean z, NimbusAdResponseType nimbusAdResponseType, Integer num, String str, Map<String, Object> map) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(FirebaseAnalytics.Param.INDEX, i);
            jSONObject.put("request_type", nimbusAdRequestType.name());
            jSONObject.put("refresh", z);
            jSONObject.put("response_type", nimbusAdResponseType);
            if (num != null) {
                jSONObject.put("error_code", num);
            }
            if (str != null) {
                jSONObject.put("empty_reason", str);
            }
            if (map != null) {
                for (Map.Entry next : map.entrySet()) {
                    jSONObject.put((String) next.getKey(), next.getValue());
                }
            }
        } catch (Exception e) {
            SentryLogcatAdapter.e(TAG, "Error creating properties for ad request completed", e);
        }
        this.amplitudeClient.logEvent("history_ad_request_completed", jSONObject);
        Log.d(TAG, "Logged history_ad_request_completed to Amplitude");
    }

    public void logAdServed(Map<String, Object> map, Map<String, Object> map2) {
        JSONObject jSONObject = new JSONObject();
        if (map != null) {
            try {
                for (Map.Entry next : map.entrySet()) {
                    jSONObject.put((String) next.getKey(), next.getValue());
                }
            } catch (Exception e) {
                SentryLogcatAdapter.e(TAG, "Error creating properties for ad served", e);
            }
        }
        if (map2 != null) {
            for (Map.Entry next2 : map2.entrySet()) {
                jSONObject.put("rerouted_" + ((String) next2.getKey()), next2.getValue());
            }
        }
        this.amplitudeClient.logEvent("history_ad_served", jSONObject);
        Log.d(TAG, "Logged history_ad_served to Amplitude");
    }

    public void logAdViewed(String str, Map<String, Object> map, String str2, Integer num, String str3, Integer num2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ad_slot_config", str);
            if (map != null) {
                for (Map.Entry next : map.entrySet()) {
                    jSONObject.put((String) next.getKey(), next.getValue());
                }
            }
            if (str2 != null) {
                jSONObject.put("aspect_ratio", str2);
            }
            if (num != null) {
                jSONObject.put(FirebaseAnalytics.Param.INDEX, num);
                jSONObject.put("is_first_in_timeline", num.intValue() == 0);
            }
            if (str3 != null) {
                jSONObject.put("media_type", str3);
            }
            if (num2 != null && num2.intValue() > 0) {
                jSONObject.put("refresh_count", num2);
            }
        } catch (Exception e) {
            SentryLogcatAdapter.e(TAG, "Error creating properties for ad viewed", e);
        }
        this.amplitudeClient.logEvent("viewed_history_ad", jSONObject);
        Log.d(TAG, "Logged viewed_history_ad to Amplitude");
    }

    public void logAdClicked(String str, Map<String, Object> map, String str2, Integer num, String str3, Integer num2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ad_slot_config", str);
            if (map != null) {
                for (Map.Entry next : map.entrySet()) {
                    jSONObject.put((String) next.getKey(), next.getValue());
                }
            }
            if (str2 != null) {
                jSONObject.put("aspect_ratio", str2);
            }
            if (num != null) {
                jSONObject.put(FirebaseAnalytics.Param.INDEX, num);
                jSONObject.put("is_first_in_timeline", num.intValue() == 0);
            }
            if (str3 != null) {
                jSONObject.put("media_type", str3);
            }
            if (num2 != null && num2.intValue() > 0) {
                jSONObject.put("refresh_count", num2);
            }
        } catch (Exception e) {
            SentryLogcatAdapter.e(TAG, "Error creating properties for ad clicked", e);
        }
        this.amplitudeClient.logEvent("clicked_history_ad", jSONObject);
        Log.d(TAG, "Logged clicked_history_ad to Amplitude");
    }
}

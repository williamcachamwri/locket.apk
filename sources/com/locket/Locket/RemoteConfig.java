package com.locket.Locket;

import com.facebook.react.uimanager.ViewProps;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.locket.Locket.Frames.FrameEvent;
import com.locket.Locket.Streaks.StreakConfig;
import io.sentry.android.core.SentryLogcatAdapter;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class RemoteConfig {
    private static FirebaseRemoteConfig config = FirebaseRemoteConfig.getInstance();

    public static boolean androidCdnEnabled() {
        return config.getBoolean("android_cdn_in_app_enabled");
    }

    public static long androidFriendsCacheThresholdMs() {
        return config.getLong("android_friends_cache_threshold_ms");
    }

    public static String androidCdnBaseUrl() {
        return config.getString("android_cdn_base_url");
    }

    public static boolean androidOverlayReceivingEnabled() {
        return config.getBoolean("android_overlay_receiving_enabled");
    }

    public static boolean androidWidgetFrameEventEnabled() {
        return config.getBoolean("android_widget_frame_event_enabled");
    }

    public static List<String> androidOverlayItemsEnabled() {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(config.getString("android_overlay_items_enabled"));
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(jSONArray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            SentryLogcatAdapter.e("Locket", "Error parsing overlay items enabled JSON");
        }
        return arrayList;
    }

    public static StreakConfig androidStreakConfig() {
        try {
            JSONObject jSONObject = new JSONObject(config.getString("streak_config"));
            return new StreakConfig(jSONObject.getBoolean(ViewProps.ENABLED), jSONObject.getBoolean("enable_streak_record"), jSONObject.getInt("minimum_display_count"));
        } catch (JSONException unused) {
            SentryLogcatAdapter.e("Locket", "Error parsing streak_config JSON");
            return new StreakConfig(false, true, 0);
        }
    }

    public static FrameEvent widgetFrameEvent() {
        try {
            if (!androidWidgetFrameEventEnabled()) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(config.getString("widget_frame_event"));
            ArrayList arrayList = new ArrayList();
            if (jSONObject.has("variants") && !jSONObject.isNull("variants")) {
                JSONArray jSONArray = jSONObject.getJSONArray("variants");
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(jSONArray.getString(i));
                }
            }
            return new FrameEvent(jSONObject.getString("uid"), jSONObject.getString("url"), jSONObject.getBoolean("use_local_time_zone"), jSONObject.getString("start_at"), jSONObject.getString("end_at"), arrayList);
        } catch (JSONException unused) {
            SentryLogcatAdapter.e("Locket", "Error parsing widget frame event JSON");
            return null;
        }
    }
}

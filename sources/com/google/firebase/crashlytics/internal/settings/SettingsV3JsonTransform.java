package com.google.firebase.crashlytics.internal.settings;

import com.google.firebase.crashlytics.internal.common.CurrentTimeProvider;
import com.google.firebase.crashlytics.internal.settings.Settings;
import com.tencent.mmkv.MMKV;
import io.sentry.cache.EnvelopeCache;
import org.json.JSONException;
import org.json.JSONObject;

class SettingsV3JsonTransform implements SettingsJsonTransform {
    SettingsV3JsonTransform() {
    }

    public Settings buildFromJson(CurrentTimeProvider currentTimeProvider, JSONObject jSONObject) throws JSONException {
        Settings.SessionData sessionData;
        JSONObject jSONObject2 = jSONObject;
        int optInt = jSONObject2.optInt("settings_version", 0);
        int optInt2 = jSONObject2.optInt("cache_duration", MMKV.ExpireInHour);
        double optDouble = jSONObject2.optDouble("on_demand_upload_rate_per_minute", 10.0d);
        double optDouble2 = jSONObject2.optDouble("on_demand_backoff_base", 1.2d);
        int optInt3 = jSONObject2.optInt("on_demand_backoff_step_duration_seconds", 60);
        if (jSONObject2.has(EnvelopeCache.PREFIX_CURRENT_SESSION_FILE)) {
            sessionData = buildSessionDataFrom(jSONObject2.getJSONObject(EnvelopeCache.PREFIX_CURRENT_SESSION_FILE));
        } else {
            sessionData = buildSessionDataFrom(new JSONObject());
        }
        return new Settings(getExpiresAtFrom(currentTimeProvider, (long) optInt2, jSONObject2), sessionData, buildFeatureFlagDataFrom(jSONObject2.getJSONObject("features")), optInt, optInt2, optDouble, optDouble2, optInt3);
    }

    private static Settings.FeatureFlagData buildFeatureFlagDataFrom(JSONObject jSONObject) {
        return new Settings.FeatureFlagData(jSONObject.optBoolean("collect_reports", true), jSONObject.optBoolean("collect_anrs", false), jSONObject.optBoolean("collect_build_ids", false));
    }

    private static Settings.SessionData buildSessionDataFrom(JSONObject jSONObject) {
        return new Settings.SessionData(jSONObject.optInt("max_custom_exception_events", 8), 4);
    }

    private static long getExpiresAtFrom(CurrentTimeProvider currentTimeProvider, long j, JSONObject jSONObject) {
        if (jSONObject.has("expires_at")) {
            return jSONObject.optLong("expires_at");
        }
        return currentTimeProvider.getCurrentTimeMillis() + (j * 1000);
    }
}

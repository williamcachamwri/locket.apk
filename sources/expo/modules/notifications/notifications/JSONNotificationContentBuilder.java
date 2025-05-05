package expo.modules.notifications.notifications;

import android.content.Context;
import android.graphics.Color;
import expo.modules.notifications.notifications.enums.NotificationPriority;
import expo.modules.notifications.notifications.model.NotificationContent;
import io.sentry.android.core.SentryLogcatAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONNotificationContentBuilder extends NotificationContent.Builder {
    private static final String AUTO_DISMISS_KEY = "autoDismiss";
    private static final String BADGE_KEY = "badge";
    private static final String BODY_KEY = "body";
    private static final String CATEGORY_IDENTIFIER_KEY = "categoryId";
    private static final String COLOR_KEY = "color";
    private static final String PRIORITY_KEY = "priority";
    private static final String SOUND_KEY = "sound";
    private static final String STICKY_KEY = "sticky";
    private static final String SUBTITLE_KEY = "subtitle";
    private static final String TEXT_KEY = "message";
    private static final String TITLE_KEY = "title";
    private static final String VIBRATE_KEY = "vibrate";
    private SoundResolver mSoundResolver;

    public JSONNotificationContentBuilder(Context context) {
        this.mSoundResolver = new SoundResolver(context);
    }

    public NotificationContent.Builder setPayload(JSONObject jSONObject) {
        setTitle(getTitle(jSONObject)).setSubtitle(getSubtitle(jSONObject)).setText(getText(jSONObject)).setBody(getBody(jSONObject)).setPriority(getPriority(jSONObject)).setBadgeCount(getBadgeCount(jSONObject)).setColor(getColor(jSONObject)).setAutoDismiss(getAutoDismiss(jSONObject)).setCategoryId(getCategoryId(jSONObject)).setSticky(getSticky(jSONObject));
        if (shouldPlayDefaultSound(jSONObject)) {
            useDefaultSound();
        } else {
            setSound(getSound(jSONObject));
        }
        if (shouldUseDefaultVibrationPattern(jSONObject)) {
            useDefaultVibrationPattern();
        } else {
            setVibrationPattern(getVibrationPattern(jSONObject));
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public String getTitle(JSONObject jSONObject) {
        try {
            return jSONObject.getString(TITLE_KEY);
        } catch (JSONException unused) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public String getText(JSONObject jSONObject) {
        try {
            return jSONObject.getString("message");
        } catch (JSONException unused) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public String getSubtitle(JSONObject jSONObject) {
        try {
            return jSONObject.getString(SUBTITLE_KEY);
        } catch (JSONException unused) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public Number getBadgeCount(JSONObject jSONObject) {
        try {
            if (jSONObject.has(BADGE_KEY)) {
                return Integer.valueOf(jSONObject.getInt(BADGE_KEY));
            }
            return null;
        } catch (JSONException unused) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public boolean shouldPlayDefaultSound(JSONObject jSONObject) {
        try {
            return jSONObject.getBoolean("sound");
        } catch (JSONException unused) {
            return getSound(jSONObject) == null;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0011, code lost:
        return r2.mSoundResolver.resolve(r3.getString("sound"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0012, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x0007 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.net.Uri getSound(org.json.JSONObject r3) {
        /*
            r2 = this;
            java.lang.String r0 = "sound"
            r1 = 0
            r3.getBoolean(r0)     // Catch:{ JSONException -> 0x0007 }
            return r1
        L_0x0007:
            java.lang.String r3 = r3.getString(r0)     // Catch:{ JSONException -> 0x0012 }
            expo.modules.notifications.notifications.SoundResolver r0 = r2.mSoundResolver     // Catch:{ JSONException -> 0x0012 }
            android.net.Uri r3 = r0.resolve(r3)     // Catch:{ JSONException -> 0x0012 }
            return r3
        L_0x0012:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.notifications.notifications.JSONNotificationContentBuilder.getSound(org.json.JSONObject):android.net.Uri");
    }

    /* access modifiers changed from: protected */
    public JSONObject getBody(JSONObject jSONObject) {
        try {
            return new JSONObject(jSONObject.optString("body"));
        } catch (NullPointerException | JSONException unused) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public boolean shouldUseDefaultVibrationPattern(JSONObject jSONObject) {
        return !jSONObject.optBoolean(VIBRATE_KEY, true);
    }

    /* access modifiers changed from: protected */
    public long[] getVibrationPattern(JSONObject jSONObject) {
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray(VIBRATE_KEY);
            if (optJSONArray == null) {
                return null;
            }
            long[] jArr = new long[optJSONArray.length()];
            for (int i = 0; i < optJSONArray.length(); i++) {
                jArr[i] = optJSONArray.getLong(i);
            }
            return jArr;
        } catch (JSONException e) {
            SentryLogcatAdapter.w("expo-notifications", "Failed to set custom vibration pattern from the notification: " + e.getMessage());
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public NotificationPriority getPriority(JSONObject jSONObject) {
        return NotificationPriority.fromEnumValue(jSONObject.optString("priority"));
    }

    /* access modifiers changed from: protected */
    public Number getColor(JSONObject jSONObject) {
        try {
            if (jSONObject.has("color")) {
                return Integer.valueOf(Color.parseColor(jSONObject.getString("color")));
            }
            return null;
        } catch (IllegalArgumentException unused) {
            SentryLogcatAdapter.e("expo-notifications", "Could not have parsed color passed in notification.");
            return null;
        } catch (JSONException unused2) {
            SentryLogcatAdapter.e("expo-notifications", "Could not have parsed a non-string color value passed in notification.");
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public boolean getAutoDismiss(JSONObject jSONObject) {
        if (!jSONObject.has(AUTO_DISMISS_KEY)) {
            return true;
        }
        try {
            return jSONObject.getBoolean(AUTO_DISMISS_KEY);
        } catch (JSONException unused) {
            SentryLogcatAdapter.e("expo-notifications", "Could not have parsed a boolean autoDismiss value passed in notification, falling back to a default value.");
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public String getCategoryId(JSONObject jSONObject) {
        try {
            return jSONObject.getString(CATEGORY_IDENTIFIER_KEY);
        } catch (JSONException unused) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public boolean getSticky(JSONObject jSONObject) {
        if (!jSONObject.has(STICKY_KEY)) {
            return false;
        }
        try {
            return jSONObject.getBoolean(STICKY_KEY);
        } catch (JSONException unused) {
            SentryLogcatAdapter.e("expo-notifications", "Could not have parsed a boolean sticky value passed in notification, falling back to a default value.");
            return false;
        }
    }
}

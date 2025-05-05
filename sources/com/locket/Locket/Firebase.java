package com.locket.Locket;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.HttpsCallableResult;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.locket.Locket.Streaks.Streak;
import com.locket.Locket.Streaks.StreakConfig;
import com.tencent.mmkv.MMKV;
import io.sentry.android.core.SentryLogcatAdapter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Firebase {
    private static final String TAG = "com.locket.Locket.Firebase";

    public interface CustomOnCompleteListener {
        void onNotModified();

        void onStreakReceived(JSONObject jSONObject);

        void onSuccess(JSONObject jSONObject);
    }

    public static Task<Map<String, Object>> getUserByUidFetchUser(final String str) {
        final MMKV mmkvWithID = MMKV.mmkvWithID("fetchUser");
        final String str2 = "timestamp_" + str;
        String decodeString = mmkvWithID.decodeString(str);
        long decodeLong = mmkvWithID.decodeLong(str2, 0);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        if (!(decodeString == null || decodeLong == 0)) {
            try {
                JSONObject jSONObject = new JSONObject(decodeString);
                if (!(System.currentTimeMillis() - decodeLong > RemoteConfig.androidFriendsCacheThresholdMs())) {
                    HashMap hashMap = new HashMap();
                    Iterator<String> keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        Object opt = jSONObject.opt(next);
                        if (opt == JSONObject.NULL) {
                            opt = null;
                        }
                        hashMap.put(next, opt);
                    }
                    taskCompletionSource.setResult(hashMap);
                    return taskCompletionSource.getTask();
                }
            } catch (JSONException unused) {
                mmkvWithID.removeValueForKey(str);
                mmkvWithID.removeValueForKey(str2);
            }
        }
        HashMap hashMap2 = new HashMap();
        hashMap2.put("user_uid", str);
        return FirebaseFunctions.getInstance().getHttpsCallable("fetchUserV2").call(hashMap2).continueWithTask(new Continuation<HttpsCallableResult, Task<Map<String, Object>>>() {
            public Task<Map<String, Object>> then(Task<HttpsCallableResult> task) throws Exception {
                Map map = (Map) ((Map) task.getResult().getData()).get("data");
                MMKV.this.encode(str, new JSONObject(map).toString());
                MMKV.this.encode(str2, System.currentTimeMillis());
                return Tasks.forResult(map);
            }
        });
    }

    public static void getLatestMomentWithUser(String str, Streak streak, CustomOnCompleteListener customOnCompleteListener) {
        StreakConfig androidStreakConfig = RemoteConfig.androidStreakConfig();
        Gson gson = new Gson();
        try {
            HashMap hashMap = new HashMap();
            if (!TextUtils.isEmpty(str)) {
                hashMap.put("sync_token", str);
            }
            hashMap.put("should_count_missed_moments", true);
            hashMap.put("last_fetch", 1);
            if (androidStreakConfig.shouldFetchStreak(streak)) {
                hashMap.put("fetch_streak", true);
            }
            Log.d(TAG, "Calling getLatestMoment with params: " + hashMap);
            FirebaseFunctions.getInstance().getHttpsCallable("getLatestMomentV2").call(hashMap).addOnCompleteListener(new Firebase$$ExternalSyntheticLambda1(customOnCompleteListener, gson));
        } catch (Exception e) {
            e.printStackTrace();
            SentryLogcatAdapter.e(TAG, "Failed to run getLatestMomentWithUser");
        }
    }

    static /* synthetic */ void lambda$getLatestMomentWithUser$1(CustomOnCompleteListener customOnCompleteListener, Gson gson, Task task) {
        if (!task.isSuccessful()) {
            SentryLogcatAdapter.e(TAG, "Error calling getLatestMoment: " + task.getException());
            return;
        }
        Map map = (Map) ((HttpsCallableResult) task.getResult()).getData();
        if (map == null) {
            SentryLogcatAdapter.e(TAG, "Received null response from getLatestMoment");
            return;
        }
        String str = TAG;
        Log.d(str, "Got result from getLatestMoment: " + map);
        if (Objects.equals(map.get("status"), 304)) {
            Log.d(str, "Got 304 from getLatestMoment (cached moment data is up-to-date)");
            customOnCompleteListener.onNotModified();
            return;
        }
        try {
            Map map2 = (Map) ((List) map.get("data")).get(0);
            String str2 = (String) map2.get("canonical_uid");
            String str3 = (String) map2.get("thumbnail_url");
            String str4 = (String) map2.get("video_url");
            String str5 = (String) map2.get("caption");
            String str6 = (String) map2.get("user");
            Map map3 = (Map) map2.get("date");
            Integer num = (Integer) map3.get("_seconds");
            Integer num2 = (Integer) map3.get("_nanoseconds");
            Integer num3 = (Integer) map.get("missed_moments_count");
            List list = (List) map2.get("overlays");
            if (map.containsKey(Util.STREAK_KEY)) {
                try {
                    try {
                        customOnCompleteListener.onStreakReceived(new JSONObject(gson.toJson((Object) (Map) map.get(Util.STREAK_KEY))));
                    } catch (JsonSyntaxException e) {
                        e = e;
                    }
                } catch (JsonSyntaxException e2) {
                    e = e2;
                    CustomOnCompleteListener customOnCompleteListener2 = customOnCompleteListener;
                    SentryLogcatAdapter.e(TAG, "Error parsing streak from json: " + e);
                    Log.d(TAG, "Calling getUserByUid");
                    getUserByUidFetchUser(str6).addOnCompleteListener(new Firebase$$ExternalSyntheticLambda0(str2, str3, str4, str5, list, num, num2, str6, num3, customOnCompleteListener));
                }
            } else {
                CustomOnCompleteListener customOnCompleteListener3 = customOnCompleteListener;
            }
            Log.d(TAG, "Calling getUserByUid");
            getUserByUidFetchUser(str6).addOnCompleteListener(new Firebase$$ExternalSyntheticLambda0(str2, str3, str4, str5, list, num, num2, str6, num3, customOnCompleteListener));
        } catch (Exception e3) {
            SentryLogcatAdapter.e(TAG, "Error parsing response from getLatestMoment: " + e3);
        }
    }

    static /* synthetic */ void lambda$getLatestMomentWithUser$0(String str, String str2, String str3, String str4, List list, Integer num, Integer num2, String str5, Integer num3, CustomOnCompleteListener customOnCompleteListener, Task task) {
        List list2 = list;
        if (!task.isSuccessful()) {
            SentryLogcatAdapter.e(TAG, "Error calling getUserByUidFetchUser " + task.getException());
            return;
        }
        Map map = (Map) task.getResult();
        if (map == null) {
            SentryLogcatAdapter.e(TAG, "Received null response from getUserByUidFetchUser");
            return;
        }
        String str6 = (String) map.get("first_name");
        String str7 = (String) map.get("last_name");
        String str8 = (String) map.get("profile_picture_url");
        JSONObject jSONObject = new JSONObject();
        String str9 = str;
        try {
            jSONObject.put("canonical_uid", str);
            String str10 = str2;
            jSONObject.put("image", str2);
            String str11 = str3;
            jSONObject.put("video_url", str3);
            String str12 = str4;
            jSONObject.put("caption", str4);
            jSONObject.put("overlays", list2 != null ? new JSONArray(list) : null);
            jSONObject.put("date", new JSONObject().put("seconds", num).put("nanoseconds", num2));
            jSONObject.put("first_name", str6);
            jSONObject.put("last_name", str7);
            jSONObject.put("profile_picture_url", str8);
            jSONObject.put("user", str5);
            jSONObject.put("missed_moments_count", num3);
            customOnCompleteListener.onSuccess(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
            SentryLogcatAdapter.e(TAG, "Error creating json object with result from getLatestMomentWithUser");
        }
    }
}

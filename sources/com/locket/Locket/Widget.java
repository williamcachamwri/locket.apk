package com.locket.Locket;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.locket.Locket.Firebase;
import com.locket.Locket.Overlays.CaptionType;
import io.sentry.android.core.SentryLogcatAdapter;
import org.json.JSONObject;

public class Widget extends AppWidgetProvider {
    public static String ACTION_ALARM_UPDATE = "ALARM_WIDGET_UPDATE";
    public static String ACTION_LAUNCH_MAIN_ACTIVITY = "com.locket.Locket.action.LAUNCH_MAIN_ACTIVITY";
    public static String ACTION_NOTIFICATION_UPDATE = "NOTIFICATION_WIDGET_UPDATE";

    public void onReceive(final Context context, Intent intent) {
        super.onReceive(context, intent);
        Boolean valueOf = Boolean.valueOf(intent.getAction().equals(ACTION_ALARM_UPDATE));
        Boolean valueOf2 = Boolean.valueOf(intent.getAction().equals(ACTION_NOTIFICATION_UPDATE));
        if (valueOf.booleanValue()) {
            Log.d("Locket", "Update from Alarm");
        }
        if (valueOf2.booleanValue()) {
            Log.d("Locket", "Update from Notification");
        }
        if (valueOf.booleanValue() || valueOf2.booleanValue()) {
            final AppWidgetManager instance = AppWidgetManager.getInstance(context);
            final int[] appWidgetIds = instance.getAppWidgetIds(new ComponentName(context.getPackageName(), getClass().getName()));
            Firebase.getLatestMomentWithUser(Util.getAppData(context).optString("canonical_uid"), Util.getStreak(context), new Firebase.CustomOnCompleteListener() {
                public void onSuccess(JSONObject jSONObject) {
                    try {
                        jSONObject.put("missed_moments_count", Util.getAppData(context).optInt("missed_moments_count", 0) + jSONObject.getInt("missed_moments_count"));
                    } catch (Exception e) {
                        SentryLogcatAdapter.e("Locket", "Error parsing response from getLatestMoment: " + e);
                    }
                    Util.setAppData(context, jSONObject);
                    Widget.this.onUpdate(context, instance, appWidgetIds);
                }

                public void onNotModified() {
                    Widget.this.onUpdate(context, instance, appWidgetIds);
                }

                public void onStreakReceived(JSONObject jSONObject) {
                    Util.setStreak(context, jSONObject);
                }
            });
        }
    }

    private boolean isUserOnboarding(Context context) {
        return context.getSharedPreferences("DATA", 0).getBoolean("onboarding", false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:149:0x0357 A[Catch:{ JSONException -> 0x03bb }] */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x035e A[Catch:{ JSONException -> 0x03bb }] */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x0368 A[Catch:{ JSONException -> 0x03bb }] */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x036f A[Catch:{ JSONException -> 0x03bb }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void updateAppWidget(android.content.Context r48, android.appwidget.AppWidgetManager r49, int r50) {
        /*
            r47 = this;
            r1 = r48
            java.lang.String r2 = "icon"
            java.lang.String r3 = "background"
            java.lang.String r4 = "text_color"
            java.lang.String r5 = "text"
            java.lang.String r6 = "overlay_type"
            java.lang.String r7 = "overlay_id"
            java.lang.String r8 = "alt_text"
            java.lang.String r9 = "profile_picture_url"
            java.lang.String r10 = "missed_moments_count"
            java.lang.String r0 = "overlays"
            java.lang.String r11 = "caption"
            java.lang.String r12 = "last_name"
            java.lang.String r13 = "first_name"
            java.lang.String r14 = "phone"
            java.lang.String r15 = "]"
            r16 = r9
            java.lang.String r9 = ", "
            r17 = r10
            java.lang.String r10 = "Locket"
            r18 = r2
            java.lang.String r2 = "Widget height range: ["
            r19 = r3
            java.lang.String r3 = "Widget width range: ["
            r20 = r4
            org.json.JSONObject r4 = com.locket.Locket.Util.getAppData(r48)     // Catch:{ JSONException -> 0x03bb }
            com.locket.Locket.Streaks.Streak r38 = com.locket.Locket.Util.getStreak(r48)     // Catch:{ JSONException -> 0x03bb }
            r21 = r5
            android.os.Bundle r5 = r49.getAppWidgetOptions(r50)     // Catch:{ JSONException -> 0x03bb }
            r22 = r6
            java.lang.String r6 = "appWidgetMinWidth"
            int r6 = r5.getInt(r6)     // Catch:{ JSONException -> 0x03bb }
            r23 = r7
            java.lang.String r7 = "appWidgetMaxHeight"
            int r7 = r5.getInt(r7)     // Catch:{ JSONException -> 0x03bb }
            r24 = r8
            java.lang.String r8 = "appWidgetMaxWidth"
            int r8 = r5.getInt(r8)     // Catch:{ JSONException -> 0x03bb }
            r25 = r0
            java.lang.String r0 = "appWidgetMinHeight"
            int r0 = r5.getInt(r0)     // Catch:{ JSONException -> 0x03bb }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x03bb }
            r5.<init>(r3)     // Catch:{ JSONException -> 0x03bb }
            java.lang.StringBuilder r3 = r5.append(r6)     // Catch:{ JSONException -> 0x03bb }
            java.lang.StringBuilder r3 = r3.append(r9)     // Catch:{ JSONException -> 0x03bb }
            java.lang.StringBuilder r3 = r3.append(r8)     // Catch:{ JSONException -> 0x03bb }
            java.lang.StringBuilder r3 = r3.append(r15)     // Catch:{ JSONException -> 0x03bb }
            java.lang.String r3 = r3.toString()     // Catch:{ JSONException -> 0x03bb }
            android.util.Log.d(r10, r3)     // Catch:{ JSONException -> 0x03bb }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x03bb }
            r3.<init>(r2)     // Catch:{ JSONException -> 0x03bb }
            java.lang.StringBuilder r0 = r3.append(r0)     // Catch:{ JSONException -> 0x03bb }
            java.lang.StringBuilder r0 = r0.append(r9)     // Catch:{ JSONException -> 0x03bb }
            java.lang.StringBuilder r0 = r0.append(r7)     // Catch:{ JSONException -> 0x03bb }
            java.lang.StringBuilder r0 = r0.append(r15)     // Catch:{ JSONException -> 0x03bb }
            java.lang.String r0 = r0.toString()     // Catch:{ JSONException -> 0x03bb }
            android.util.Log.d(r10, r0)     // Catch:{ JSONException -> 0x03bb }
            int r31 = java.lang.Math.min(r6, r7)     // Catch:{ JSONException -> 0x03bb }
            java.lang.String r0 = "image"
            java.lang.String r2 = r4.getString(r0)     // Catch:{ JSONException -> 0x03bb }
            java.lang.String r0 = "no data"
            boolean r0 = r0.equals(r2)     // Catch:{ JSONException -> 0x03bb }
            r3 = 0
            if (r0 == 0) goto L_0x00c5
            com.locket.Locket.EmptyWidgetTask r0 = new com.locket.Locket.EmptyWidgetTask     // Catch:{ JSONException -> 0x03bb }
            android.content.Context r27 = r48.getApplicationContext()     // Catch:{ JSONException -> 0x03bb }
            boolean r30 = r47.isUserOnboarding(r48)     // Catch:{ JSONException -> 0x03bb }
            r26 = r0
            r28 = r49
            r29 = r50
            r26.<init>(r27, r28, r29, r30, r31)     // Catch:{ JSONException -> 0x03bb }
            java.lang.Void[] r1 = new java.lang.Void[r3]     // Catch:{ JSONException -> 0x03bb }
            r0.execute(r1)     // Catch:{ JSONException -> 0x03bb }
            goto L_0x03bf
        L_0x00c5:
            android.content.pm.PackageManager r0 = r48.getPackageManager()     // Catch:{ JSONException -> 0x03bb }
            java.lang.String r5 = r48.getPackageName()     // Catch:{ JSONException -> 0x03bb }
            android.content.Intent r0 = r0.getLaunchIntentForPackage(r5)     // Catch:{ JSONException -> 0x03bb }
            if (r0 != 0) goto L_0x00da
            android.content.Intent r0 = new android.content.Intent     // Catch:{ JSONException -> 0x03bb }
            java.lang.Class<com.locket.Locket.MainActivity> r5 = com.locket.Locket.MainActivity.class
            r0.<init>(r1, r5)     // Catch:{ JSONException -> 0x03bb }
        L_0x00da:
            java.lang.String r5 = ACTION_LAUNCH_MAIN_ACTIVITY     // Catch:{ JSONException -> 0x03bb }
            r0.setAction(r5)     // Catch:{ JSONException -> 0x03bb }
            r5 = 805306368(0x30000000, float:4.656613E-10)
            r0.setFlags(r5)     // Catch:{ JSONException -> 0x03bb }
            android.content.Intent r5 = new android.content.Intent     // Catch:{ JSONException -> 0x03bb }
            java.lang.Class<com.locket.Locket.CameraSplashActivity> r8 = com.locket.Locket.CameraSplashActivity.class
            r5.<init>(r1, r8)     // Catch:{ JSONException -> 0x03bb }
            java.lang.String r8 = "com.locket.Locket.action.CAMERA_SPLASH"
            r5.setAction(r8)     // Catch:{ JSONException -> 0x03bb }
            r8 = 536870912(0x20000000, float:1.0842022E-19)
            r5.setFlags(r8)     // Catch:{ JSONException -> 0x03bb }
            r8 = 2
            android.content.Intent[] r8 = new android.content.Intent[r8]     // Catch:{ JSONException -> 0x03bb }
            r8[r3] = r0     // Catch:{ JSONException -> 0x03bb }
            r9 = 1
            r8[r9] = r5     // Catch:{ JSONException -> 0x03bb }
            r0 = 201326592(0xc000000, float:9.8607613E-32)
            android.app.PendingIntent r5 = android.app.PendingIntent.getActivities(r1, r3, r8, r0)     // Catch:{ JSONException -> 0x03bb }
            boolean r0 = r4.has(r14)     // Catch:{ JSONException -> 0x03bb }
            if (r0 == 0) goto L_0x0110
            java.lang.String r0 = r4.getString(r14)     // Catch:{ JSONException -> 0x03bb }
            r29 = r0
            goto L_0x0112
        L_0x0110:
            r29 = 0
        L_0x0112:
            boolean r0 = r4.has(r13)     // Catch:{ JSONException -> 0x03bb }
            if (r0 == 0) goto L_0x011f
            java.lang.String r0 = r4.getString(r13)     // Catch:{ JSONException -> 0x03bb }
            r30 = r0
            goto L_0x0121
        L_0x011f:
            r30 = 0
        L_0x0121:
            boolean r0 = r4.has(r12)     // Catch:{ JSONException -> 0x03bb }
            if (r0 == 0) goto L_0x012e
            java.lang.String r0 = r4.getString(r12)     // Catch:{ JSONException -> 0x03bb }
            r31 = r0
            goto L_0x0130
        L_0x012e:
            r31 = 0
        L_0x0130:
            boolean r0 = r4.has(r11)     // Catch:{ JSONException -> 0x03bb }
            if (r0 == 0) goto L_0x013d
            java.lang.String r0 = r4.getString(r11)     // Catch:{ JSONException -> 0x03bb }
            r27 = r0
            goto L_0x013f
        L_0x013d:
            r27 = 0
        L_0x013f:
            r0 = r25
            boolean r11 = r4.isNull(r0)     // Catch:{ JSONException -> 0x03bb }
            if (r11 != 0) goto L_0x034b
            boolean r11 = com.locket.Locket.RemoteConfig.androidOverlayReceivingEnabled()     // Catch:{ JSONException -> 0x03bb }
            if (r11 == 0) goto L_0x034b
            org.json.JSONArray r11 = r4.getJSONArray(r0)     // Catch:{ Exception -> 0x032e }
            java.util.List r12 = com.locket.Locket.RemoteConfig.androidOverlayItemsEnabled()     // Catch:{ Exception -> 0x032e }
            java.util.ArrayList r13 = new java.util.ArrayList     // Catch:{ Exception -> 0x032e }
            r13.<init>()     // Catch:{ Exception -> 0x032e }
            r14 = r3
        L_0x015b:
            int r0 = r11.length()     // Catch:{ Exception -> 0x032e }
            java.lang.String r15 = "data"
            java.lang.String r8 = "type"
            if (r14 >= r0) goto L_0x01a2
            org.json.JSONObject r0 = r11.getJSONObject(r14)     // Catch:{ JSONException -> 0x0185 }
            org.json.JSONObject r15 = r0.getJSONObject(r15)     // Catch:{ JSONException -> 0x0185 }
            boolean r26 = r15.isNull(r8)     // Catch:{ JSONException -> 0x0185 }
            if (r26 != 0) goto L_0x0178
            java.lang.String r8 = r15.getString(r8)     // Catch:{ JSONException -> 0x0185 }
            goto L_0x0179
        L_0x0178:
            r8 = 0
        L_0x0179:
            if (r8 == 0) goto L_0x019f
            boolean r8 = r12.contains(r8)     // Catch:{ JSONException -> 0x0185 }
            if (r8 == 0) goto L_0x019f
            r13.add(r0)     // Catch:{ JSONException -> 0x0185 }
            goto L_0x019f
        L_0x0185:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ Exception -> 0x032e }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x032e }
            r8.<init>()     // Catch:{ Exception -> 0x032e }
            java.lang.String r15 = "Error parsing overlay: "
            java.lang.StringBuilder r8 = r8.append(r15)     // Catch:{ Exception -> 0x032e }
            java.lang.StringBuilder r0 = r8.append(r0)     // Catch:{ Exception -> 0x032e }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x032e }
            io.sentry.android.core.SentryLogcatAdapter.e(r10, r0)     // Catch:{ Exception -> 0x032e }
        L_0x019f:
            int r14 = r14 + 1
            goto L_0x015b
        L_0x01a2:
            boolean r0 = r13.isEmpty()     // Catch:{ Exception -> 0x032e }
            if (r0 != 0) goto L_0x0328
            java.lang.Object r0 = r13.get(r3)     // Catch:{ Exception -> 0x032e }
            org.json.JSONObject r0 = (org.json.JSONObject) r0     // Catch:{ Exception -> 0x032e }
            r11 = r24
            boolean r12 = r0.isNull(r11)     // Catch:{ Exception -> 0x032e }
            if (r12 != 0) goto L_0x01bb
            java.lang.String r11 = r0.getString(r11)     // Catch:{ Exception -> 0x032e }
            goto L_0x01bc
        L_0x01bb:
            r11 = 0
        L_0x01bc:
            r12 = r23
            boolean r13 = r0.isNull(r12)     // Catch:{ Exception -> 0x032e }
            if (r13 != 0) goto L_0x01c9
            java.lang.String r12 = r0.getString(r12)     // Catch:{ Exception -> 0x032e }
            goto L_0x01ca
        L_0x01c9:
            r12 = 0
        L_0x01ca:
            r13 = r22
            boolean r14 = r0.isNull(r13)     // Catch:{ Exception -> 0x032e }
            if (r14 != 0) goto L_0x01d7
            java.lang.String r13 = r0.getString(r13)     // Catch:{ Exception -> 0x032e }
            goto L_0x01d8
        L_0x01d7:
            r13 = 0
        L_0x01d8:
            com.locket.Locket.Overlays.OverlayType r13 = com.locket.Locket.Overlays.OverlayType.getByType(r13)     // Catch:{ Exception -> 0x032e }
            boolean r14 = r0.isNull(r15)     // Catch:{ Exception -> 0x032e }
            if (r14 != 0) goto L_0x01e7
            org.json.JSONObject r0 = r0.getJSONObject(r15)     // Catch:{ Exception -> 0x032e }
            goto L_0x01e8
        L_0x01e7:
            r0 = 0
        L_0x01e8:
            if (r0 == 0) goto L_0x0328
            r14 = r21
            boolean r21 = r0.isNull(r14)     // Catch:{ Exception -> 0x032e }
            if (r21 != 0) goto L_0x01f9
            java.lang.String r14 = r0.getString(r14)     // Catch:{ Exception -> 0x032e }
            r44 = r14
            goto L_0x01fb
        L_0x01f9:
            r44 = 0
        L_0x01fb:
            r14 = r20
            boolean r20 = r0.isNull(r14)     // Catch:{ Exception -> 0x032e }
            if (r20 != 0) goto L_0x020a
            java.lang.String r14 = r0.getString(r14)     // Catch:{ Exception -> 0x032e }
            r45 = r14
            goto L_0x020c
        L_0x020a:
            r45 = 0
        L_0x020c:
            r14 = r19
            boolean r19 = r0.isNull(r14)     // Catch:{ Exception -> 0x032e }
            if (r19 != 0) goto L_0x0219
            org.json.JSONObject r14 = r0.getJSONObject(r14)     // Catch:{ Exception -> 0x032e }
            goto L_0x021a
        L_0x0219:
            r14 = 0
        L_0x021a:
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ Exception -> 0x032e }
            r3.<init>()     // Catch:{ Exception -> 0x032e }
            if (r14 == 0) goto L_0x0262
            java.lang.String r9 = "colors"
            boolean r9 = r14.isNull(r9)     // Catch:{ Exception -> 0x032e }
            if (r9 != 0) goto L_0x0248
            java.lang.String r9 = "colors"
            org.json.JSONArray r9 = r14.getJSONArray(r9)     // Catch:{ Exception -> 0x032e }
            r34 = r7
            r1 = 0
        L_0x0232:
            int r7 = r9.length()     // Catch:{ Exception -> 0x0326 }
            if (r1 >= r7) goto L_0x024a
            boolean r7 = r9.isNull(r1)     // Catch:{ Exception -> 0x0326 }
            if (r7 != 0) goto L_0x0245
            java.lang.String r7 = r9.getString(r1)     // Catch:{ Exception -> 0x0326 }
            r3.add(r7)     // Catch:{ Exception -> 0x0326 }
        L_0x0245:
            int r1 = r1 + 1
            goto L_0x0232
        L_0x0248:
            r34 = r7
        L_0x024a:
            java.lang.String r1 = "material_blur"
            boolean r1 = r14.isNull(r1)     // Catch:{ Exception -> 0x0326 }
            if (r1 != 0) goto L_0x0259
            java.lang.String r1 = "material_blur"
            java.lang.String r1 = r14.getString(r1)     // Catch:{ Exception -> 0x0326 }
            goto L_0x025a
        L_0x0259:
            r1 = 0
        L_0x025a:
            com.locket.Locket.Overlays.Background r7 = new com.locket.Locket.Overlays.Background     // Catch:{ Exception -> 0x0326 }
            r7.<init>(r3, r1)     // Catch:{ Exception -> 0x0326 }
            r40 = r7
            goto L_0x0266
        L_0x0262:
            r34 = r7
            r40 = 0
        L_0x0266:
            r1 = r18
            boolean r3 = r0.isNull(r1)     // Catch:{ Exception -> 0x0326 }
            if (r3 != 0) goto L_0x02ba
            org.json.JSONObject r1 = r0.getJSONObject(r1)     // Catch:{ Exception -> 0x0326 }
            java.lang.String r3 = "color"
            boolean r3 = r1.isNull(r3)     // Catch:{ Exception -> 0x0326 }
            if (r3 != 0) goto L_0x0281
            java.lang.String r3 = "color"
            java.lang.String r3 = r1.getString(r3)     // Catch:{ Exception -> 0x0326 }
            goto L_0x0282
        L_0x0281:
            r3 = 0
        L_0x0282:
            boolean r7 = r1.isNull(r15)     // Catch:{ Exception -> 0x0326 }
            if (r7 != 0) goto L_0x028d
            java.lang.String r7 = r1.getString(r15)     // Catch:{ Exception -> 0x0326 }
            goto L_0x028e
        L_0x028d:
            r7 = 0
        L_0x028e:
            java.lang.String r9 = "source"
            boolean r9 = r1.isNull(r9)     // Catch:{ Exception -> 0x0326 }
            if (r9 != 0) goto L_0x029d
            java.lang.String r9 = "source"
            java.lang.String r9 = r1.getString(r9)     // Catch:{ Exception -> 0x0326 }
            goto L_0x029e
        L_0x029d:
            r9 = 0
        L_0x029e:
            com.locket.Locket.Overlays.IconSource r9 = com.locket.Locket.Overlays.IconSource.getBySource(r9)     // Catch:{ Exception -> 0x0326 }
            boolean r14 = r1.isNull(r8)     // Catch:{ Exception -> 0x0326 }
            if (r14 != 0) goto L_0x02ad
            java.lang.String r1 = r1.getString(r8)     // Catch:{ Exception -> 0x0326 }
            goto L_0x02ae
        L_0x02ad:
            r1 = 0
        L_0x02ae:
            com.locket.Locket.Overlays.IconType r1 = com.locket.Locket.Overlays.IconType.getByIconType(r1)     // Catch:{ Exception -> 0x0326 }
            com.locket.Locket.Overlays.Icon r14 = new com.locket.Locket.Overlays.Icon     // Catch:{ Exception -> 0x0326 }
            r14.<init>(r3, r7, r9, r1)     // Catch:{ Exception -> 0x0326 }
            r41 = r14
            goto L_0x02bc
        L_0x02ba:
            r41 = 0
        L_0x02bc:
            java.lang.String r1 = "payload"
            boolean r1 = r0.isNull(r1)     // Catch:{ Exception -> 0x0326 }
            if (r1 != 0) goto L_0x02cb
            java.lang.String r1 = "payload"
            org.json.JSONObject r1 = r0.getJSONObject(r1)     // Catch:{ Exception -> 0x0326 }
            goto L_0x02cc
        L_0x02cb:
            r1 = 0
        L_0x02cc:
            java.lang.String r3 = "max_lines"
            boolean r3 = r0.isNull(r3)     // Catch:{ Exception -> 0x0326 }
            if (r3 != 0) goto L_0x02dd
            java.lang.String r3 = "max_lines"
            int r3 = r0.getInt(r3)     // Catch:{ Exception -> 0x0326 }
            r42 = r3
            goto L_0x02df
        L_0x02dd:
            r42 = 0
        L_0x02df:
            boolean r3 = r0.isNull(r8)     // Catch:{ Exception -> 0x0326 }
            if (r3 != 0) goto L_0x02ea
            java.lang.String r0 = r0.getString(r8)     // Catch:{ Exception -> 0x0326 }
            goto L_0x02eb
        L_0x02ea:
            r0 = 0
        L_0x02eb:
            com.locket.Locket.Overlays.CaptionType r46 = com.locket.Locket.Overlays.CaptionType.getByType(r0)     // Catch:{ Exception -> 0x0326 }
            if (r46 == 0) goto L_0x0317
            if (r1 == 0) goto L_0x0317
            int[] r0 = com.locket.Locket.Widget.AnonymousClass2.$SwitchMap$com$locket$Locket$Overlays$CaptionType     // Catch:{ Exception -> 0x0326 }
            int r3 = r46.ordinal()     // Catch:{ Exception -> 0x0326 }
            r0 = r0[r3]     // Catch:{ Exception -> 0x0326 }
            r3 = 1
            if (r0 == r3) goto L_0x02ff
            goto L_0x0317
        L_0x02ff:
            java.lang.String r0 = "date"
            double r0 = r1.getDouble(r0)     // Catch:{ Exception -> 0x0326 }
            r7 = 4652007308841189376(0x408f400000000000, double:1000.0)
            double r0 = r0 * r7
            long r0 = java.lang.Math.round(r0)     // Catch:{ Exception -> 0x0326 }
            com.locket.Locket.Overlays.CaptionPayload.TimePayload r3 = new com.locket.Locket.Overlays.CaptionPayload.TimePayload     // Catch:{ Exception -> 0x0326 }
            r3.<init>(r0)     // Catch:{ Exception -> 0x0326 }
            r43 = r3
            goto L_0x0319
        L_0x0317:
            r43 = 0
        L_0x0319:
            com.locket.Locket.Overlays.OverlayCaptionData r0 = new com.locket.Locket.Overlays.OverlayCaptionData     // Catch:{ Exception -> 0x0326 }
            r39 = r0
            r39.<init>(r40, r41, r42, r43, r44, r45, r46)     // Catch:{ Exception -> 0x0326 }
            com.locket.Locket.Overlays.MomentOverlay r1 = new com.locket.Locket.Overlays.MomentOverlay     // Catch:{ Exception -> 0x0326 }
            r1.<init>(r11, r0, r12, r13)     // Catch:{ Exception -> 0x0326 }
            goto L_0x032b
        L_0x0326:
            r0 = move-exception
            goto L_0x0331
        L_0x0328:
            r34 = r7
            r1 = 0
        L_0x032b:
            r35 = r1
            goto L_0x034f
        L_0x032e:
            r0 = move-exception
            r34 = r7
        L_0x0331:
            r0.printStackTrace()     // Catch:{ JSONException -> 0x03bb }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x03bb }
            r1.<init>()     // Catch:{ JSONException -> 0x03bb }
            java.lang.String r3 = "Error parsing overlays: "
            java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ JSONException -> 0x03bb }
            java.lang.StringBuilder r0 = r1.append(r0)     // Catch:{ JSONException -> 0x03bb }
            java.lang.String r0 = r0.toString()     // Catch:{ JSONException -> 0x03bb }
            io.sentry.android.core.SentryLogcatAdapter.e(r10, r0)     // Catch:{ JSONException -> 0x03bb }
            goto L_0x034d
        L_0x034b:
            r34 = r7
        L_0x034d:
            r35 = 0
        L_0x034f:
            r1 = r17
            boolean r0 = r4.has(r1)     // Catch:{ JSONException -> 0x03bb }
            if (r0 == 0) goto L_0x035e
            int r0 = r4.getInt(r1)     // Catch:{ JSONException -> 0x03bb }
            r28 = r0
            goto L_0x0360
        L_0x035e:
            r28 = 0
        L_0x0360:
            r1 = r16
            boolean r0 = r4.has(r1)     // Catch:{ JSONException -> 0x03bb }
            if (r0 == 0) goto L_0x036f
            java.lang.String r0 = r4.getString(r1)     // Catch:{ JSONException -> 0x03bb }
            r32 = r0
            goto L_0x0371
        L_0x036f:
            r32 = 0
        L_0x0371:
            android.widget.RemoteViews r0 = new android.widget.RemoteViews     // Catch:{ JSONException -> 0x03bb }
            java.lang.String r1 = r48.getPackageName()     // Catch:{ JSONException -> 0x03bb }
            int r3 = com.locket.Locket.R.layout.widget     // Catch:{ JSONException -> 0x03bb }
            r0.<init>(r1, r3)     // Catch:{ JSONException -> 0x03bb }
            int r1 = com.locket.Locket.R.id.locket_image_view     // Catch:{ JSONException -> 0x03bb }
            r0.setOnClickPendingIntent(r1, r5)     // Catch:{ JSONException -> 0x03bb }
            java.lang.String r1 = "Begin: Fetch Image Resource"
            android.util.Log.d(r10, r1)     // Catch:{ JSONException -> 0x03bb }
            java.lang.String r1 = com.locket.Locket.Util.getImageUrl(r2)     // Catch:{ JSONException -> 0x03bb }
            java.lang.String r37 = com.locket.Locket.Util.getWidgetFrame(r48)     // Catch:{ JSONException -> 0x03bb }
            com.locket.Locket.Frames.FrameEvent r36 = com.locket.Locket.RemoteConfig.widgetFrameEvent()     // Catch:{ JSONException -> 0x03bb }
            com.locket.Locket.ImageDownloaderTask r2 = new com.locket.Locket.ImageDownloaderTask     // Catch:{ JSONException -> 0x03bb }
            android.content.Context r22 = r48.getApplicationContext()     // Catch:{ JSONException -> 0x03bb }
            int r24 = com.locket.Locket.R.id.locket_image_view     // Catch:{ JSONException -> 0x03bb }
            r21 = r2
            r23 = r0
            r25 = r49
            r26 = r50
            r33 = r6
            r21.<init>(r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38)     // Catch:{ JSONException -> 0x03bb }
            java.lang.String[] r1 = new java.lang.String[]{r1}     // Catch:{ JSONException -> 0x03bb }
            r2.execute(r1)     // Catch:{ JSONException -> 0x03bb }
            java.lang.String r1 = "End: Fetch Image Resource"
            android.util.Log.d(r10, r1)     // Catch:{ JSONException -> 0x03bb }
            r1 = r49
            r2 = r50
            r1.updateAppWidget(r2, r0)     // Catch:{ JSONException -> 0x03bb }
            goto L_0x03bf
        L_0x03bb:
            r0 = move-exception
            r0.printStackTrace()
        L_0x03bf:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.locket.Locket.Widget.updateAppWidget(android.content.Context, android.appwidget.AppWidgetManager, int):void");
    }

    /* renamed from: com.locket.Locket.Widget$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$locket$Locket$Overlays$CaptionType;

        static {
            int[] iArr = new int[CaptionType.values().length];
            $SwitchMap$com$locket$Locket$Overlays$CaptionType = iArr;
            try {
                iArr[CaptionType.TIME.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] iArr) {
        for (int updateAppWidget : iArr) {
            updateAppWidget(context, appWidgetManager, updateAppWidget);
        }
    }

    public void onEnabled(Context context) {
        Log.d("Locket", "onEnabled: Start Alarm");
        new AppWidgetAlarm(context.getApplicationContext()).startAlarm();
    }

    public void onDisabled(Context context) {
        Log.d("Locket", "onDisabled: Stop Alarm");
        if (AppWidgetManager.getInstance(context).getAppWidgetIds(new ComponentName(context.getPackageName(), getClass().getName())).length == 0) {
            new AppWidgetAlarm(context.getApplicationContext()).stopAlarm();
        }
    }

    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int i, Bundle bundle) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, i, bundle);
        updateAppWidget(context, appWidgetManager, i);
    }
}

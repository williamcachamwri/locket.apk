package com.locket.Locket;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.locket.Locket.Streaks.Streak;
import com.locket.Locket.Widgets.WidgetSize;
import expo.modules.contacts.Columns;
import io.sentry.android.core.SentryLogcatAdapter;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.HttpUrl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Util {
    private static final String APP_DATA_KEY = "appData";
    public static final String IMAGE_BASE_PATH = "https://firebasestorage.googleapis.com/";
    public static final String IMAGE_BASE_PATH_WITH_PORT = "https://firebasestorage.googleapis.com:443/";
    public static final String STREAK_KEY = "streak";
    public static final String STREAK_VISIBLE_KEY = "streakVisible";
    private static final String WIDGET_FRAME_KEY = "selectedWidgetFrame";

    public static int getWidgetCount(Context context, AppWidgetManager appWidgetManager) {
        return appWidgetManager.getAppWidgetIds(new ComponentName(context.getPackageName(), Widget.class.getName())).length;
    }

    public static void sendWidgetUpdateIntent(Context context) {
        Intent intent = new Intent(context, Widget.class);
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        intent.putExtra("appWidgetIds", AppWidgetManager.getInstance(context).getAppWidgetIds(new ComponentName(context, Widget.class)));
        context.sendBroadcast(intent);
    }

    public static float dpToPx(Resources resources, int i) {
        return ((float) i) * resources.getDisplayMetrics().density;
    }

    public static Bitmap getContactPhoto(Context context, String str) {
        if (context.checkSelfPermission("android.permission.READ_CONTACTS") != 0) {
            SentryLogcatAdapter.w("Locket", "Can't search for contact photo since contacts permission hasn't been granted");
            return null;
        }
        ContentResolver contentResolver = context.getContentResolver();
        Cursor query = contentResolver.query(Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(str)), new String[]{Columns.DISPLAY_NAME, Columns.ID}, (String) null, (String[]) null, (String) null);
        String str2 = null;
        if (query != null) {
            while (query.moveToNext()) {
                str2 = query.getString(query.getColumnIndexOrThrow(Columns.ID));
            }
            query.close();
        }
        if (str2 == null) {
            return null;
        }
        try {
            InputStream openContactPhotoInputStream = ContactsContract.Contacts.openContactPhotoInputStream(contentResolver, ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.parseLong(str2)));
            if (openContactPhotoInputStream == null) {
                return null;
            }
            Bitmap decodeStream = BitmapFactory.decodeStream(openContactPhotoInputStream);
            openContactPhotoInputStream.close();
            return decodeStream;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject getAppData(Context context) {
        String string = context.getSharedPreferences("DATA", 0).getString(APP_DATA_KEY, "{\"image\":'no data'}");
        JSONObject jSONObject = new JSONObject();
        try {
            return new JSONObject(string);
        } catch (JSONException e) {
            e.printStackTrace();
            return jSONObject;
        }
    }

    public static void setAppData(Context context, JSONObject jSONObject) {
        SharedPreferences.Editor edit = context.getSharedPreferences("DATA", 0).edit();
        edit.putString(APP_DATA_KEY, jSONObject.toString());
        edit.apply();
    }

    public static JSONArray getSharedPreferenceArray(Context context, String str) {
        String string = context.getSharedPreferences("DATA", 0).getString(str, HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
        Log.d("Util", "value: " + string);
        JSONArray jSONArray = new JSONArray();
        try {
            return new JSONArray(string);
        } catch (JSONException e) {
            e.printStackTrace();
            return jSONArray;
        }
    }

    public static String getImageUrl(String str) {
        String str2;
        if (!RemoteConfig.androidCdnEnabled()) {
            return str;
        }
        String androidCdnBaseUrl = RemoteConfig.androidCdnBaseUrl();
        if (str.startsWith(IMAGE_BASE_PATH)) {
            str2 = str.substring(39);
        } else {
            str2 = str.startsWith(IMAGE_BASE_PATH_WITH_PORT) ? str.substring(43) : null;
        }
        return str2 != null ? androidCdnBaseUrl.concat(str2) : str;
    }

    public static WidgetSize getWidgetSize(int i) {
        if (i < 148) {
            return WidgetSize.SM;
        }
        if (i < 250) {
            return WidgetSize.MD;
        }
        return WidgetSize.LG;
    }

    public static String getWidgetFrame(Context context) {
        return context.getSharedPreferences("DATA", 0).getString(WIDGET_FRAME_KEY, (String) null);
    }

    public static Streak getStreak(Context context) {
        try {
            return (Streak) new Gson().fromJson(context.getSharedPreferences("DATA", 0).getString(STREAK_KEY, (String) null), Streak.class);
        } catch (JsonSyntaxException e) {
            Log.d("Locket", "Failed to parse streak data: " + e.getMessage());
            return null;
        }
    }

    public static void setStreak(Context context, JSONObject jSONObject) {
        SharedPreferences.Editor edit = context.getSharedPreferences("DATA", 0).edit();
        edit.putString(STREAK_KEY, jSONObject.toString());
        edit.apply();
    }
}

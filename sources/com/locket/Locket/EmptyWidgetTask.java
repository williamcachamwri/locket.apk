package com.locket.Locket;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.RemoteViews;
import androidx.media3.common.C;
import com.locket.Locket.Widgets.WidgetSize;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EmptyWidgetTask extends AsyncTask<Void, Void, Result> {
    private static final String TAG = "EmptyWidgetTask";
    private final int mAppWidgetId;
    private final AppWidgetManager mAppWidgetManager;
    private final Context mContext;
    private final boolean mIsUserOnboarding;
    private final WidgetSize mLayoutSize;
    private final int mWidgetSizeDp;

    public EmptyWidgetTask(Context context, AppWidgetManager appWidgetManager, int i, boolean z, int i2) {
        this.mContext = context;
        this.mAppWidgetManager = appWidgetManager;
        this.mAppWidgetId = i;
        this.mIsUserOnboarding = z;
        this.mWidgetSizeDp = i2;
        this.mLayoutSize = Util.getWidgetSize(i2);
    }

    /* access modifiers changed from: protected */
    public Integer getProfilePhotoSize() {
        int i;
        if (this.mLayoutSize == WidgetSize.SM) {
            i = R.dimen.empty_widget_photo_size_sm;
        } else if (this.mLayoutSize == WidgetSize.MD) {
            i = R.dimen.empty_widget_photo_size_md;
        } else {
            i = R.dimen.empty_widget_photo_size_lg;
        }
        return Integer.valueOf((int) this.mContext.getResources().getDimension(i));
    }

    /* access modifiers changed from: protected */
    public Result doInBackground(Void... voidArr) {
        Bitmap bitmap;
        Bitmap bitmap2;
        Log.d(TAG, "Begin: doInBackground");
        Bitmap bitmap3 = null;
        try {
            bitmap = ImageUtils.getRoundedBitmap(this.mContext, R.drawable.bg_widget_empty, this.mWidgetSizeDp);
            try {
                String findFriendOrOutgoingFriendRequestPhotoUrl = findFriendOrOutgoingFriendRequestPhotoUrl();
                if (findFriendOrOutgoingFriendRequestPhotoUrl != null) {
                    bitmap3 = ImageUtils.getCircularBitmap(this.mContext, findFriendOrOutgoingFriendRequestPhotoUrl, getProfilePhotoSize().intValue());
                }
                Log.d(TAG, "roundedProfilePhoto: " + bitmap3);
            } catch (Exception e) {
                e = e;
                Bitmap bitmap4 = bitmap;
                bitmap2 = null;
                bitmap3 = bitmap4;
                e.printStackTrace();
                Bitmap bitmap5 = bitmap2;
                bitmap = bitmap3;
                bitmap3 = bitmap5;
                Log.d(TAG, "End: doInBackground");
                return new Result(bitmap, bitmap3);
            }
        } catch (Exception e2) {
            e = e2;
            bitmap2 = null;
            e.printStackTrace();
            Bitmap bitmap52 = bitmap2;
            bitmap = bitmap3;
            bitmap3 = bitmap52;
            Log.d(TAG, "End: doInBackground");
            return new Result(bitmap, bitmap3);
        }
        Log.d(TAG, "End: doInBackground");
        return new Result(bitmap, bitmap3);
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Result result) {
        RemoteViews remoteViews;
        float dimension;
        String str;
        Log.d(TAG, "Begin: onPostExecute");
        if (this.mLayoutSize == WidgetSize.SM) {
            Log.d(TAG, "Using sm layout");
            remoteViews = new RemoteViews(this.mContext.getPackageName(), R.layout.widget_empty_sm);
            dimension = this.mContext.getResources().getDimension(R.dimen.empty_widget_label_font_size_sm);
        } else if (this.mLayoutSize == WidgetSize.MD) {
            Log.d(TAG, "Using md layout");
            remoteViews = new RemoteViews(this.mContext.getPackageName(), R.layout.widget_empty);
            dimension = this.mContext.getResources().getDimension(R.dimen.empty_widget_label_font_size_md);
        } else {
            Log.d(TAG, "Using lg layout");
            remoteViews = new RemoteViews(this.mContext.getPackageName(), R.layout.widget_empty_lg);
            dimension = this.mContext.getResources().getDimension(R.dimen.empty_widget_label_font_size_lg);
        }
        float f = dimension;
        remoteViews.setImageViewBitmap(R.id.locket_widget_empty_bg, result.background);
        if (result.profilePhoto != null) {
            remoteViews.setImageViewBitmap(R.id.locket_profile_photo_image, result.profilePhoto);
            remoteViews.setViewVisibility(R.id.locket_profile_photo_image, 0);
            remoteViews.setViewVisibility(R.id.locket_profile_placeholder_icon, 8);
        } else {
            remoteViews.setViewVisibility(R.id.locket_profile_placeholder_icon, 0);
            remoteViews.setViewVisibility(R.id.locket_profile_photo_image, 8);
        }
        if (this.mIsUserOnboarding) {
            str = this.mContext.getResources().getString(R.string.tap_to_set_up);
        } else {
            str = this.mContext.getResources().getString(R.string.no_pics_yet);
        }
        remoteViews.setImageViewBitmap(R.id.locket_widget_empty_text, ImageUtils.getTextBitmap(this.mContext, str, R.font.proxima_soft_bold, f, 0, -1, -1, 2));
        Intent launchIntentForPackage = this.mContext.getPackageManager().getLaunchIntentForPackage(this.mContext.getPackageName());
        if (launchIntentForPackage == null) {
            launchIntentForPackage = new Intent(this.mContext, MainActivity.class);
        }
        launchIntentForPackage.setAction(Widget.ACTION_LAUNCH_MAIN_ACTIVITY);
        launchIntentForPackage.setFlags(805306368);
        Intent intent = new Intent(this.mContext, CameraSplashActivity.class);
        intent.setAction(CameraSplashActivity.ACTION_CAMERA_SPLASH);
        intent.setFlags(C.BUFFER_FLAG_LAST_SAMPLE);
        remoteViews.setOnClickPendingIntent(R.id.locket_widget_empty_container, PendingIntent.getActivities(this.mContext, 0, new Intent[]{launchIntentForPackage, intent}, 201326592));
        this.mAppWidgetManager.updateAppWidget(this.mAppWidgetId, remoteViews);
        Log.d(TAG, "End: onPostExecute");
    }

    private String findFriendOrOutgoingFriendRequestPhotoUrl() {
        EmptyWidgetTask$$ExternalSyntheticLambda0 emptyWidgetTask$$ExternalSyntheticLambda0 = new EmptyWidgetTask$$ExternalSyntheticLambda0();
        JSONArray sharedPreferenceArray = Util.getSharedPreferenceArray(this.mContext, "friendsObjects");
        Log.d(TAG, "friends: " + sharedPreferenceArray);
        String str = (String) emptyWidgetTask$$ExternalSyntheticLambda0.apply(sharedPreferenceArray);
        if (str != null) {
            return str;
        }
        JSONArray sharedPreferenceArray2 = Util.getSharedPreferenceArray(this.mContext, "outgoingFriendRequestsObjects");
        Log.d(TAG, "outgoingFriendRequests: " + sharedPreferenceArray2);
        return (String) emptyWidgetTask$$ExternalSyntheticLambda0.apply(sharedPreferenceArray2);
    }

    static /* synthetic */ String lambda$findFriendOrOutgoingFriendRequestPhotoUrl$0(JSONArray jSONArray) {
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject.has("profile_picture_url")) {
                    return jSONObject.getString("profile_picture_url");
                }
                i++;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    static final class Result {
        Bitmap background;
        Bitmap profilePhoto;

        public Result(Bitmap bitmap, Bitmap bitmap2) {
            this.background = bitmap;
            this.profilePhoto = bitmap2;
        }
    }
}

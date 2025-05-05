package com.locket.Locket;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.RemoteViews;
import com.locket.Locket.Frames.FrameEvent;
import com.locket.Locket.Overlays.MomentOverlay;
import com.locket.Locket.Streaks.Streak;
import io.sentry.android.core.SentryLogcatAdapter;
import io.sentry.instrumentation.file.SentryFileOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class ImageDownloaderTask extends AsyncTask<String, Void, ImageResult> {
    private static final int CORNER_RADIUS_DP = 24;
    private static final String LAST_IMAGE_BITMAP_FILE = "locket_cache.png";
    private final int mAppWidgetId;
    private final AppWidgetManager mAppWidgetManager;
    private final File mCacheDir;
    private final String mCaption;
    private final Context mContext;
    private final String mFirstName;
    private final int mImageViewId;
    private final String mLastName;
    private final int mMissedMomentsCount;
    private final MomentOverlay mOverlay;
    private final String mProfilePictureUrl;
    private final FrameEvent mRemoteFrameEvent;
    private final Streak mStreak;
    private final String mUserPhoneNumber;
    private final RemoteViews mWidget;
    private final String mWidgetFrame;
    private final int mWidgetMaxHeight;
    private final int mWidgetMaxWidth;

    public ImageDownloaderTask(Context context, RemoteViews remoteViews, int i, AppWidgetManager appWidgetManager, int i2, String str, int i3, String str2, String str3, String str4, String str5, int i4, int i5, MomentOverlay momentOverlay, FrameEvent frameEvent, String str6, Streak streak) {
        this.mContext = context;
        this.mWidget = remoteViews;
        this.mImageViewId = i;
        this.mAppWidgetManager = appWidgetManager;
        this.mAppWidgetId = i2;
        this.mCacheDir = context.getCacheDir();
        this.mCaption = str;
        this.mMissedMomentsCount = i3;
        this.mFirstName = str3;
        this.mLastName = str4;
        this.mProfilePictureUrl = str5;
        this.mUserPhoneNumber = str2;
        this.mWidgetMaxWidth = i4;
        this.mWidgetMaxHeight = i5;
        this.mOverlay = momentOverlay;
        this.mRemoteFrameEvent = frameEvent;
        this.mWidgetFrame = str6;
        this.mStreak = streak;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x00a6 A[Catch:{ Exception -> 0x012c }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x010a A[Catch:{ Exception -> 0x012c }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0117 A[Catch:{ Exception -> 0x012c }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0125 A[Catch:{ Exception -> 0x012c }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.locket.Locket.ImageDownloaderTask.ImageResult doInBackground(java.lang.String... r14) {
        /*
            r13 = this;
            java.lang.String r0 = "Frame URL: "
            java.lang.String r1 = "Begin: doInBackground"
            java.lang.String r2 = "Locket"
            android.util.Log.d(r2, r1)
            r1 = 0
            android.content.Context r3 = r13.mContext     // Catch:{ Exception -> 0x012c }
            android.content.res.Resources r3 = r3.getResources()     // Catch:{ Exception -> 0x012c }
            r4 = 24
            float r3 = com.locket.Locket.Util.dpToPx(r3, r4)     // Catch:{ Exception -> 0x012c }
            int r3 = (int) r3     // Catch:{ Exception -> 0x012c }
            android.content.Context r4 = r13.mContext     // Catch:{ Exception -> 0x012c }
            android.content.res.Resources r4 = r4.getResources()     // Catch:{ Exception -> 0x012c }
            int r5 = r13.mWidgetMaxWidth     // Catch:{ Exception -> 0x012c }
            int r6 = r13.mWidgetMaxHeight     // Catch:{ Exception -> 0x012c }
            int r5 = java.lang.Math.min(r5, r6)     // Catch:{ Exception -> 0x012c }
            float r4 = com.locket.Locket.Util.dpToPx(r4, r5)     // Catch:{ Exception -> 0x012c }
            int r4 = (int) r4     // Catch:{ Exception -> 0x012c }
            android.content.Context r5 = r13.mContext     // Catch:{ Exception -> 0x012c }
            com.bumptech.glide.RequestManager r5 = com.bumptech.glide.Glide.with((android.content.Context) r5)     // Catch:{ Exception -> 0x012c }
            com.bumptech.glide.RequestBuilder r5 = r5.asBitmap()     // Catch:{ Exception -> 0x012c }
            r6 = 0
            r14 = r14[r6]     // Catch:{ Exception -> 0x012c }
            com.bumptech.glide.RequestBuilder r14 = r5.load((java.lang.String) r14)     // Catch:{ Exception -> 0x012c }
            r5 = 2
            com.bumptech.glide.load.Transformation[] r7 = new com.bumptech.glide.load.Transformation[r5]     // Catch:{ Exception -> 0x012c }
            com.bumptech.glide.load.resource.bitmap.FitCenter r8 = new com.bumptech.glide.load.resource.bitmap.FitCenter     // Catch:{ Exception -> 0x012c }
            r8.<init>()     // Catch:{ Exception -> 0x012c }
            r7[r6] = r8     // Catch:{ Exception -> 0x012c }
            com.bumptech.glide.load.resource.bitmap.RoundedCorners r8 = new com.bumptech.glide.load.resource.bitmap.RoundedCorners     // Catch:{ Exception -> 0x012c }
            r8.<init>(r3)     // Catch:{ Exception -> 0x012c }
            r9 = 1
            r7[r9] = r8     // Catch:{ Exception -> 0x012c }
            com.bumptech.glide.request.BaseRequestOptions r14 = r14.transform((com.bumptech.glide.load.Transformation<android.graphics.Bitmap>[]) r7)     // Catch:{ Exception -> 0x012c }
            com.bumptech.glide.RequestBuilder r14 = (com.bumptech.glide.RequestBuilder) r14     // Catch:{ Exception -> 0x012c }
            com.bumptech.glide.request.FutureTarget r14 = r14.submit(r4, r4)     // Catch:{ Exception -> 0x012c }
            java.lang.Object r14 = r14.get()     // Catch:{ Exception -> 0x012c }
            android.graphics.Bitmap r14 = (android.graphics.Bitmap) r14     // Catch:{ Exception -> 0x012c }
            java.lang.String r7 = r13.mUserPhoneNumber     // Catch:{ Exception -> 0x012c }
            if (r7 == 0) goto L_0x007d
            android.content.Context r8 = r13.mContext     // Catch:{ Exception -> 0x012c }
            android.graphics.Bitmap r7 = com.locket.Locket.Util.getContactPhoto(r8, r7)     // Catch:{ Exception -> 0x012c }
            if (r7 == 0) goto L_0x0097
            android.content.Context r8 = r13.mContext     // Catch:{ Exception -> 0x012c }
            android.content.res.Resources r8 = r8.getResources()     // Catch:{ Exception -> 0x012c }
            int r10 = com.locket.Locket.R.dimen.widget_profile_photo_size     // Catch:{ Exception -> 0x012c }
            float r8 = r8.getDimension(r10)     // Catch:{ Exception -> 0x012c }
            int r8 = (int) r8     // Catch:{ Exception -> 0x012c }
            android.content.Context r10 = r13.mContext     // Catch:{ Exception -> 0x012c }
            android.graphics.Bitmap r7 = com.locket.Locket.ImageUtils.getCircularBitmap((android.content.Context) r10, (android.graphics.Bitmap) r7, (int) r8)     // Catch:{ Exception -> 0x012c }
            goto L_0x0098
        L_0x007d:
            java.lang.String r7 = r13.mProfilePictureUrl     // Catch:{ Exception -> 0x012c }
            if (r7 == 0) goto L_0x0097
            android.content.Context r7 = r13.mContext     // Catch:{ Exception -> 0x012c }
            android.content.res.Resources r7 = r7.getResources()     // Catch:{ Exception -> 0x012c }
            int r8 = com.locket.Locket.R.dimen.widget_profile_photo_size     // Catch:{ Exception -> 0x012c }
            float r7 = r7.getDimension(r8)     // Catch:{ Exception -> 0x012c }
            int r7 = (int) r7     // Catch:{ Exception -> 0x012c }
            android.content.Context r8 = r13.mContext     // Catch:{ Exception -> 0x012c }
            java.lang.String r10 = r13.mProfilePictureUrl     // Catch:{ Exception -> 0x012c }
            android.graphics.Bitmap r7 = com.locket.Locket.ImageUtils.getCircularBitmap((android.content.Context) r8, (java.lang.String) r10, (int) r7)     // Catch:{ Exception -> 0x012c }
            goto L_0x0098
        L_0x0097:
            r7 = r1
        L_0x0098:
            com.locket.Locket.Frames.FrameEvent r8 = r13.mRemoteFrameEvent     // Catch:{ Exception -> 0x012c }
            if (r8 == 0) goto L_0x010a
            java.time.ZonedDateTime r10 = java.time.ZonedDateTime.now()     // Catch:{ Exception -> 0x012c }
            boolean r8 = r8.enabled(r10)     // Catch:{ Exception -> 0x012c }
            if (r8 == 0) goto L_0x010a
            int r8 = r13.mWidgetMaxWidth     // Catch:{ Exception -> 0x012c }
            int r10 = r13.mWidgetMaxHeight     // Catch:{ Exception -> 0x012c }
            int r8 = java.lang.Math.min(r8, r10)     // Catch:{ Exception -> 0x012c }
            com.locket.Locket.Widgets.WidgetSize r8 = com.locket.Locket.Util.getWidgetSize(r8)     // Catch:{ Exception -> 0x012c }
            android.content.Context r10 = r13.mContext     // Catch:{ Exception -> 0x012c }
            android.content.res.Resources r10 = r10.getResources()     // Catch:{ Exception -> 0x012c }
            android.util.DisplayMetrics r10 = r10.getDisplayMetrics()     // Catch:{ Exception -> 0x012c }
            int r10 = r10.densityDpi     // Catch:{ Exception -> 0x012c }
            int r11 = r13.mMissedMomentsCount     // Catch:{ Exception -> 0x012c }
            if (r11 <= 0) goto L_0x00c4
            r11 = r9
            goto L_0x00c5
        L_0x00c4:
            r11 = r6
        L_0x00c5:
            com.locket.Locket.Frames.FrameEvent r12 = r13.mRemoteFrameEvent     // Catch:{ Exception -> 0x012c }
            java.lang.String r8 = r12.getImage(r11, r8, r10)     // Catch:{ Exception -> 0x012c }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x012c }
            r10.<init>(r0)     // Catch:{ Exception -> 0x012c }
            java.lang.StringBuilder r0 = r10.append(r8)     // Catch:{ Exception -> 0x012c }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x012c }
            android.util.Log.d(r2, r0)     // Catch:{ Exception -> 0x012c }
            android.content.Context r0 = r13.mContext     // Catch:{ Exception -> 0x012c }
            com.bumptech.glide.RequestManager r0 = com.bumptech.glide.Glide.with((android.content.Context) r0)     // Catch:{ Exception -> 0x012c }
            com.bumptech.glide.RequestBuilder r0 = r0.asBitmap()     // Catch:{ Exception -> 0x012c }
            com.bumptech.glide.RequestBuilder r0 = r0.load((java.lang.String) r8)     // Catch:{ Exception -> 0x012c }
            com.bumptech.glide.load.Transformation[] r5 = new com.bumptech.glide.load.Transformation[r5]     // Catch:{ Exception -> 0x012c }
            com.bumptech.glide.load.resource.bitmap.FitCenter r8 = new com.bumptech.glide.load.resource.bitmap.FitCenter     // Catch:{ Exception -> 0x012c }
            r8.<init>()     // Catch:{ Exception -> 0x012c }
            r5[r6] = r8     // Catch:{ Exception -> 0x012c }
            com.bumptech.glide.load.resource.bitmap.RoundedCorners r6 = new com.bumptech.glide.load.resource.bitmap.RoundedCorners     // Catch:{ Exception -> 0x012c }
            r6.<init>(r3)     // Catch:{ Exception -> 0x012c }
            r5[r9] = r6     // Catch:{ Exception -> 0x012c }
            com.bumptech.glide.request.BaseRequestOptions r0 = r0.transform((com.bumptech.glide.load.Transformation<android.graphics.Bitmap>[]) r5)     // Catch:{ Exception -> 0x012c }
            com.bumptech.glide.RequestBuilder r0 = (com.bumptech.glide.RequestBuilder) r0     // Catch:{ Exception -> 0x012c }
            com.bumptech.glide.request.FutureTarget r0 = r0.submit(r4, r4)     // Catch:{ Exception -> 0x012c }
            java.lang.Object r0 = r0.get()     // Catch:{ Exception -> 0x012c }
            android.graphics.Bitmap r0 = (android.graphics.Bitmap) r0     // Catch:{ Exception -> 0x012c }
            goto L_0x010b
        L_0x010a:
            r0 = r1
        L_0x010b:
            java.lang.String r5 = r13.mWidgetFrame     // Catch:{ Exception -> 0x012c }
            if (r5 == 0) goto L_0x0125
            java.lang.String r6 = "none"
            boolean r5 = r5.equals(r6)     // Catch:{ Exception -> 0x012c }
            if (r5 != 0) goto L_0x0125
            float r3 = (float) r3     // Catch:{ Exception -> 0x012c }
            java.lang.String r5 = r13.mWidgetFrame     // Catch:{ Exception -> 0x012c }
            java.lang.String[] r5 = com.locket.Locket.Frames.FrameColors.getColors(r5)     // Catch:{ Exception -> 0x012c }
            r6 = 10
            android.graphics.Bitmap r3 = com.locket.Locket.ImageUtils.createGradientBorder(r4, r4, r3, r6, r5)     // Catch:{ Exception -> 0x012c }
            goto L_0x0126
        L_0x0125:
            r3 = r1
        L_0x0126:
            com.locket.Locket.ImageDownloaderTask$ImageResult r4 = new com.locket.Locket.ImageDownloaderTask$ImageResult     // Catch:{ Exception -> 0x012c }
            r4.<init>(r14, r7, r0, r3)     // Catch:{ Exception -> 0x012c }
            return r4
        L_0x012c:
            r14 = move-exception
            r14.printStackTrace()
            java.lang.String r14 = "End: doInBackground"
            android.util.Log.d(r2, r14)
            com.locket.Locket.ImageDownloaderTask$ImageResult r14 = new com.locket.Locket.ImageDownloaderTask$ImageResult
            android.graphics.Bitmap r0 = r13.loadBitmapFromCache()
            r14.<init>(r0, r1, r1, r1)
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.locket.Locket.ImageDownloaderTask.doInBackground(java.lang.String[]):com.locket.Locket.ImageDownloaderTask$ImageResult");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x03ea  */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x03f5  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x0405  */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x0410  */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x0427  */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x043b  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0119 A[SYNTHETIC, Splitter:B:39:0x0119] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x025d  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x02e4  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0346  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x035a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onPostExecute(com.locket.Locket.ImageDownloaderTask.ImageResult r28) {
        /*
            r27 = this;
            r1 = r27
            r0 = r28
            java.lang.String r2 = "Begin: onPostExecute"
            java.lang.String r3 = "Locket"
            android.util.Log.d(r3, r2)
            android.graphics.Bitmap r2 = r0.moment
            android.graphics.Bitmap r4 = r0.framePhoto
            android.graphics.Bitmap r5 = r0.remoteFramePhoto
            android.graphics.Bitmap r0 = r0.profilePhoto
            java.lang.String r6 = "ImageDownloaderTask"
            if (r2 == 0) goto L_0x001f
            android.widget.RemoteViews r7 = r1.mWidget
            int r8 = r1.mImageViewId
            r7.setImageViewBitmap(r8, r2)
            goto L_0x002d
        L_0x001f:
            java.lang.String r7 = "No moment bitmap available"
            io.sentry.android.core.SentryLogcatAdapter.e(r6, r7)
            android.widget.RemoteViews r7 = r1.mWidget
            int r8 = r1.mImageViewId
            int r9 = com.locket.Locket.R.drawable.no_pics_placeholder
            r7.setImageViewResource(r8, r9)
        L_0x002d:
            r7 = 4
            r8 = 8
            r9 = 0
            if (r0 == 0) goto L_0x0050
            android.widget.RemoteViews r10 = r1.mWidget
            int r11 = com.locket.Locket.R.id.locket_profile_image_view
            r10.setImageViewBitmap(r11, r0)
            android.widget.RemoteViews r0 = r1.mWidget
            int r10 = com.locket.Locket.R.id.locket_profile_view
            r0.setViewVisibility(r10, r9)
            android.widget.RemoteViews r0 = r1.mWidget
            int r10 = com.locket.Locket.R.id.locket_profile_image_border
            r0.setViewVisibility(r10, r9)
            android.widget.RemoteViews r0 = r1.mWidget
            int r10 = com.locket.Locket.R.id.locket_profile_placeholder_bg
            r0.setViewVisibility(r10, r8)
            goto L_0x00bd
        L_0x0050:
            java.lang.String r0 = r1.mFirstName
            if (r0 == 0) goto L_0x00b1
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x00b1
            java.lang.String r0 = r1.mLastName
            if (r0 == 0) goto L_0x00b1
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x00b1
            java.lang.String r0 = "No profile photo available, falling back to initials..."
            android.util.Log.d(r6, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r10 = r1.mFirstName
            char r10 = r10.charAt(r9)
            java.lang.StringBuilder r0 = r0.append(r10)
            java.lang.String r10 = ""
            java.lang.StringBuilder r0 = r0.append(r10)
            java.lang.String r10 = r1.mLastName
            char r10 = r10.charAt(r9)
            java.lang.StringBuilder r0 = r0.append(r10)
            java.lang.String r0 = r0.toString()
            android.content.Context r10 = r1.mContext
            r11 = 32
            android.graphics.Bitmap r0 = com.locket.Locket.ImageUtils.getProfilePlaceholderBitmap(r10, r0, r11)
            android.widget.RemoteViews r10 = r1.mWidget
            int r11 = com.locket.Locket.R.id.locket_profile_image_view
            r10.setImageViewBitmap(r11, r0)
            android.widget.RemoteViews r0 = r1.mWidget
            int r10 = com.locket.Locket.R.id.locket_profile_view
            r0.setViewVisibility(r10, r9)
            android.widget.RemoteViews r0 = r1.mWidget
            int r10 = com.locket.Locket.R.id.locket_profile_image_border
            r0.setViewVisibility(r10, r8)
            android.widget.RemoteViews r0 = r1.mWidget
            int r10 = com.locket.Locket.R.id.locket_profile_placeholder_bg
            r0.setViewVisibility(r10, r9)
            goto L_0x00bd
        L_0x00b1:
            java.lang.String r0 = "No user initials available for profile photo placeholder"
            io.sentry.android.core.SentryLogcatAdapter.w((java.lang.String) r6, (java.lang.String) r0)
            android.widget.RemoteViews r0 = r1.mWidget
            int r10 = com.locket.Locket.R.id.locket_profile_view
            r0.setViewVisibility(r10, r7)
        L_0x00bd:
            com.locket.Locket.Overlays.MomentOverlay r0 = r1.mOverlay
            java.lang.String r10 = "..."
            java.lang.String r11 = "setBackgroundResource"
            if (r0 == 0) goto L_0x0261
            boolean r0 = com.locket.Locket.RemoteConfig.androidOverlayReceivingEnabled()
            if (r0 == 0) goto L_0x0261
            com.locket.Locket.Overlays.MomentOverlay r0 = r1.mOverlay
            com.locket.Locket.Overlays.OverlayCaptionData r0 = r0.getData()
            com.locket.Locket.Overlays.MomentOverlay r12 = r1.mOverlay
            com.locket.Locket.Overlays.OverlayType r12 = r12.getOverlayType()
            com.locket.Locket.Overlays.OverlayType r13 = com.locket.Locket.Overlays.OverlayType.CAPTION
            r14 = 2
            if (r12 != r13) goto L_0x0114
            com.locket.Locket.Overlays.CaptionType r12 = r0.getType()
            int[] r13 = com.locket.Locket.ImageDownloaderTask.AnonymousClass1.$SwitchMap$com$locket$Locket$Overlays$CaptionType
            int r12 = r12.ordinal()
            r12 = r13[r12]
            r13 = 1
            if (r12 == r13) goto L_0x010d
            if (r12 == r14) goto L_0x00f3
            r13 = 3
            if (r12 == r13) goto L_0x00f3
            if (r12 == r7) goto L_0x00f3
            goto L_0x0114
        L_0x00f3:
            com.locket.Locket.Overlays.Icon r7 = r0.getIcon()
            if (r7 == 0) goto L_0x0106
            com.locket.Locket.Overlays.IconType r12 = r7.getType()
            com.locket.Locket.Overlays.IconType r13 = com.locket.Locket.Overlays.IconType.EMOJI
            if (r12 != r13) goto L_0x0106
            java.lang.String r7 = r7.getData()
            goto L_0x0107
        L_0x0106:
            r7 = 0
        L_0x0107:
            java.lang.String r12 = r0.getText()
            r13 = r9
            goto L_0x0117
        L_0x010d:
            java.lang.String r12 = r0.getText()
            java.lang.String r7 = "🕒"
            goto L_0x0117
        L_0x0114:
            r13 = r9
            r7 = 0
            r12 = 0
        L_0x0117:
            if (r12 == 0) goto L_0x025d
            java.lang.String r16 = r0.getTextColor()     // Catch:{ Exception -> 0x0250 }
            if (r16 == 0) goto L_0x0128
            java.lang.String r16 = r0.getTextColor()     // Catch:{ Exception -> 0x0250 }
            int r16 = com.locket.Locket.ImageUtils.parseRGBAColor(r16)     // Catch:{ Exception -> 0x0250 }
            goto L_0x012a
        L_0x0128:
            r16 = -1
        L_0x012a:
            r22 = r16
            if (r7 == 0) goto L_0x013b
            java.lang.String r15 = "%s %s"
            java.lang.Object[] r7 = new java.lang.Object[]{r7, r12}     // Catch:{ Exception -> 0x0250 }
            java.lang.String r7 = java.lang.String.format(r15, r7)     // Catch:{ Exception -> 0x0250 }
            r18 = r7
            goto L_0x013d
        L_0x013b:
            r18 = r12
        L_0x013d:
            int r24 = r0.getMaxLines()     // Catch:{ Exception -> 0x0250 }
            android.content.Context r7 = r1.mContext     // Catch:{ Exception -> 0x0250 }
            int r19 = com.locket.Locket.R.font.proxima_soft_semibold     // Catch:{ Exception -> 0x0250 }
            r20 = 1095761920(0x41500000, float:13.0)
            r21 = 2
            int r15 = r1.mWidgetMaxWidth     // Catch:{ Exception -> 0x0250 }
            int r8 = r1.mWidgetMaxHeight     // Catch:{ Exception -> 0x0250 }
            int r23 = java.lang.Math.min(r15, r8)     // Catch:{ Exception -> 0x0250 }
            r17 = r7
            android.graphics.Bitmap r7 = com.locket.Locket.ImageUtils.getTextBitmap(r17, r18, r19, r20, r21, r22, r23, r24)     // Catch:{ Exception -> 0x0250 }
            com.locket.Locket.Overlays.Background r8 = r0.getBackground()     // Catch:{ Exception -> 0x0250 }
            if (r8 == 0) goto L_0x0170
            com.locket.Locket.Overlays.Background r8 = r0.getBackground()     // Catch:{ Exception -> 0x0250 }
            java.util.List r8 = r8.getColors()     // Catch:{ Exception -> 0x0250 }
            if (r8 == 0) goto L_0x0170
            com.locket.Locket.Overlays.Background r0 = r0.getBackground()     // Catch:{ Exception -> 0x0250 }
            java.util.List r15 = r0.getColors()     // Catch:{ Exception -> 0x0250 }
            goto L_0x0171
        L_0x0170:
            r15 = 0
        L_0x0171:
            if (r15 == 0) goto L_0x01fc
            boolean r0 = r15.isEmpty()     // Catch:{ Exception -> 0x0250 }
            if (r0 != 0) goto L_0x01fc
            int r0 = r7.getWidth()     // Catch:{ Exception -> 0x0250 }
            int r8 = r7.getHeight()     // Catch:{ Exception -> 0x0250 }
            int r13 = r15.size()     // Catch:{ Exception -> 0x0250 }
            int[] r9 = new int[r13]     // Catch:{ Exception -> 0x0250 }
            r18 = r3
            r14 = 0
        L_0x018a:
            int r3 = r15.size()     // Catch:{ Exception -> 0x024e }
            if (r14 >= r3) goto L_0x019f
            java.lang.Object r3 = r15.get(r14)     // Catch:{ Exception -> 0x024e }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Exception -> 0x024e }
            int r3 = com.locket.Locket.ImageUtils.parseRGBAColor(r3)     // Catch:{ Exception -> 0x024e }
            r9[r14] = r3     // Catch:{ Exception -> 0x024e }
            int r14 = r14 + 1
            goto L_0x018a
        L_0x019f:
            float r0 = (float) r0     // Catch:{ Exception -> 0x024e }
            android.content.Context r3 = r1.mContext     // Catch:{ Exception -> 0x024e }
            android.content.res.Resources r3 = r3.getResources()     // Catch:{ Exception -> 0x024e }
            r14 = 7
            float r3 = com.locket.Locket.Util.dpToPx(r3, r14)     // Catch:{ Exception -> 0x024e }
            r15 = 1073741824(0x40000000, float:2.0)
            float r3 = r3 * r15
            float r0 = r0 + r3
            int r0 = (int) r0     // Catch:{ Exception -> 0x024e }
            float r3 = (float) r8     // Catch:{ Exception -> 0x024e }
            android.content.Context r8 = r1.mContext     // Catch:{ Exception -> 0x024e }
            android.content.res.Resources r8 = r8.getResources()     // Catch:{ Exception -> 0x024e }
            float r8 = com.locket.Locket.Util.dpToPx(r8, r14)     // Catch:{ Exception -> 0x024e }
            float r8 = r8 * r15
            float r3 = r3 + r8
            int r3 = (int) r3     // Catch:{ Exception -> 0x024e }
            r8 = 17
            r14 = 2
            if (r13 < r14) goto L_0x01d2
            android.content.Context r13 = r1.mContext     // Catch:{ Exception -> 0x024e }
            android.content.res.Resources r13 = r13.getResources()     // Catch:{ Exception -> 0x024e }
            float r8 = com.locket.Locket.Util.dpToPx(r13, r8)     // Catch:{ Exception -> 0x024e }
            android.graphics.Bitmap r0 = com.locket.Locket.ImageUtils.getGradientBitmap(r0, r3, r9, r8)     // Catch:{ Exception -> 0x024e }
            goto L_0x01e3
        L_0x01d2:
            r13 = 0
            r9 = r9[r13]     // Catch:{ Exception -> 0x024e }
            android.content.Context r13 = r1.mContext     // Catch:{ Exception -> 0x024e }
            android.content.res.Resources r13 = r13.getResources()     // Catch:{ Exception -> 0x024e }
            float r8 = com.locket.Locket.Util.dpToPx(r13, r8)     // Catch:{ Exception -> 0x024e }
            android.graphics.Bitmap r0 = com.locket.Locket.ImageUtils.getSolidColorBitmap(r0, r3, r9, r8)     // Catch:{ Exception -> 0x024e }
        L_0x01e3:
            android.widget.RemoteViews r3 = r1.mWidget     // Catch:{ Exception -> 0x024e }
            int r8 = com.locket.Locket.R.id.locket_overlay_gradient_bg_view     // Catch:{ Exception -> 0x024e }
            r3.setImageViewBitmap(r8, r0)     // Catch:{ Exception -> 0x024e }
            android.widget.RemoteViews r0 = r1.mWidget     // Catch:{ Exception -> 0x024e }
            int r3 = com.locket.Locket.R.id.locket_overlay_gradient_bg_view     // Catch:{ Exception -> 0x024e }
            r8 = 0
            r0.setViewVisibility(r3, r8)     // Catch:{ Exception -> 0x024e }
            android.widget.RemoteViews r0 = r1.mWidget     // Catch:{ Exception -> 0x024e }
            int r3 = com.locket.Locket.R.id.locket_overlay_text_view     // Catch:{ Exception -> 0x024e }
            int r8 = com.locket.Locket.R.color.amp_transparent     // Catch:{ Exception -> 0x024e }
            r0.setInt(r3, r11, r8)     // Catch:{ Exception -> 0x024e }
            goto L_0x0215
        L_0x01fc:
            r18 = r3
            android.widget.RemoteViews r0 = r1.mWidget     // Catch:{ Exception -> 0x024e }
            int r3 = com.locket.Locket.R.id.locket_overlay_gradient_bg_view     // Catch:{ Exception -> 0x024e }
            r8 = 8
            r0.setViewVisibility(r3, r8)     // Catch:{ Exception -> 0x024e }
            android.widget.RemoteViews r0 = r1.mWidget     // Catch:{ Exception -> 0x024e }
            int r3 = com.locket.Locket.R.id.locket_overlay_text_view     // Catch:{ Exception -> 0x024e }
            if (r13 == 0) goto L_0x0210
            int r8 = com.locket.Locket.R.drawable.bg_overlay_dark     // Catch:{ Exception -> 0x024e }
            goto L_0x0212
        L_0x0210:
            int r8 = com.locket.Locket.R.drawable.bg_overlay_light     // Catch:{ Exception -> 0x024e }
        L_0x0212:
            r0.setInt(r3, r11, r8)     // Catch:{ Exception -> 0x024e }
        L_0x0215:
            java.lang.String r0 = "Overlay present, removing caption views..."
            android.util.Log.d(r6, r0)     // Catch:{ Exception -> 0x024e }
            android.widget.RemoteViews r0 = r1.mWidget     // Catch:{ Exception -> 0x024e }
            int r3 = com.locket.Locket.R.id.locket_caption_view     // Catch:{ Exception -> 0x024e }
            r8 = 8
            r0.setViewVisibility(r3, r8)     // Catch:{ Exception -> 0x024e }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x024e }
            r0.<init>()     // Catch:{ Exception -> 0x024e }
            java.lang.String r3 = "Setting overlay text to "
            java.lang.StringBuilder r0 = r0.append(r3)     // Catch:{ Exception -> 0x024e }
            java.lang.StringBuilder r0 = r0.append(r12)     // Catch:{ Exception -> 0x024e }
            java.lang.StringBuilder r0 = r0.append(r10)     // Catch:{ Exception -> 0x024e }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x024e }
            android.util.Log.d(r6, r0)     // Catch:{ Exception -> 0x024e }
            android.widget.RemoteViews r0 = r1.mWidget     // Catch:{ Exception -> 0x024e }
            int r3 = com.locket.Locket.R.id.locket_overlay_text_view     // Catch:{ Exception -> 0x024e }
            r0.setImageViewBitmap(r3, r7)     // Catch:{ Exception -> 0x024e }
            android.widget.RemoteViews r0 = r1.mWidget     // Catch:{ Exception -> 0x024e }
            int r3 = com.locket.Locket.R.id.locket_overlay_text_view     // Catch:{ Exception -> 0x024e }
            r7 = 0
            r0.setViewVisibility(r3, r7)     // Catch:{ Exception -> 0x024e }
            goto L_0x02de
        L_0x024e:
            r0 = move-exception
            goto L_0x0253
        L_0x0250:
            r0 = move-exception
            r18 = r3
        L_0x0253:
            java.lang.String r3 = "Error: Could not set overlay bitmaps"
            io.sentry.android.core.SentryLogcatAdapter.e(r6, r3)
            r0.printStackTrace()
            goto L_0x02de
        L_0x025d:
            r18 = r3
            goto L_0x02de
        L_0x0261:
            r18 = r3
            java.lang.String r0 = r1.mCaption
            if (r0 == 0) goto L_0x02c2
            java.lang.String r0 = "No overlay, removing overlay views..."
            android.util.Log.d(r6, r0)
            android.widget.RemoteViews r0 = r1.mWidget
            int r3 = com.locket.Locket.R.id.locket_overlay_gradient_bg_view
            r7 = 8
            r0.setViewVisibility(r3, r7)
            android.widget.RemoteViews r0 = r1.mWidget
            int r3 = com.locket.Locket.R.id.locket_overlay_text_view
            r0.setViewVisibility(r3, r7)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r3 = "Setting widget caption to "
            r0.<init>(r3)
            java.lang.String r3 = r1.mCaption
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.StringBuilder r0 = r0.append(r10)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r6, r0)
            android.content.Context r0 = r1.mContext
            java.lang.String r3 = r1.mCaption
            int r21 = com.locket.Locket.R.font.proxima_soft_semibold
            r22 = 1095761920(0x41500000, float:13.0)
            r23 = 2
            r24 = -1
            int r6 = r1.mWidgetMaxWidth
            int r7 = r1.mWidgetMaxHeight
            int r25 = java.lang.Math.min(r6, r7)
            r26 = 2
            r19 = r0
            r20 = r3
            android.graphics.Bitmap r0 = com.locket.Locket.ImageUtils.getTextBitmap(r19, r20, r21, r22, r23, r24, r25, r26)
            android.widget.RemoteViews r3 = r1.mWidget
            int r6 = com.locket.Locket.R.id.locket_caption_view
            r3.setImageViewBitmap(r6, r0)
            android.widget.RemoteViews r0 = r1.mWidget
            int r3 = com.locket.Locket.R.id.locket_caption_view
            r6 = 0
            r0.setViewVisibility(r3, r6)
            goto L_0x02de
        L_0x02c2:
            java.lang.String r0 = "No overlay or captions, removing all views..."
            android.util.Log.d(r6, r0)
            android.widget.RemoteViews r0 = r1.mWidget
            int r3 = com.locket.Locket.R.id.locket_caption_view
            r6 = 8
            r0.setViewVisibility(r3, r6)
            android.widget.RemoteViews r0 = r1.mWidget
            int r3 = com.locket.Locket.R.id.locket_overlay_gradient_bg_view
            r0.setViewVisibility(r3, r6)
            android.widget.RemoteViews r0 = r1.mWidget
            int r3 = com.locket.Locket.R.id.locket_overlay_text_view
            r0.setViewVisibility(r3, r6)
        L_0x02de:
            int r0 = r1.mMissedMomentsCount
            r3 = 9
            if (r0 <= 0) goto L_0x0346
            java.lang.String r6 = "#332500"
            if (r0 <= r3) goto L_0x0316
            android.content.Context r0 = r1.mContext
            java.lang.String r20 = "9+"
            int r21 = com.locket.Locket.R.font.proxima_soft_bold
            r22 = 1099431936(0x41880000, float:17.0)
            r23 = 1
            int r24 = android.graphics.Color.parseColor(r6)
            int r6 = r1.mWidgetMaxWidth
            int r7 = r1.mWidgetMaxHeight
            int r25 = java.lang.Math.min(r6, r7)
            r26 = 2
            r19 = r0
            android.graphics.Bitmap r0 = com.locket.Locket.ImageUtils.getTextBitmap(r19, r20, r21, r22, r23, r24, r25, r26)
            android.widget.RemoteViews r6 = r1.mWidget
            int r7 = com.locket.Locket.R.id.locket_missed_moments_badge_max
            r6.setImageViewBitmap(r7, r0)
            android.widget.RemoteViews r0 = r1.mWidget
            int r6 = com.locket.Locket.R.id.locket_missed_moments_badge_max
            r7 = 0
            r0.setViewVisibility(r6, r7)
            goto L_0x0356
        L_0x0316:
            android.content.Context r7 = r1.mContext
            java.lang.String r20 = java.lang.Integer.toString(r0)
            int r21 = com.locket.Locket.R.font.proxima_soft_bold
            r22 = 1099431936(0x41880000, float:17.0)
            r23 = 1
            int r24 = android.graphics.Color.parseColor(r6)
            int r0 = r1.mWidgetMaxWidth
            int r6 = r1.mWidgetMaxHeight
            int r25 = java.lang.Math.min(r0, r6)
            r26 = 2
            r19 = r7
            android.graphics.Bitmap r0 = com.locket.Locket.ImageUtils.getTextBitmap(r19, r20, r21, r22, r23, r24, r25, r26)
            android.widget.RemoteViews r6 = r1.mWidget
            int r7 = com.locket.Locket.R.id.locket_missed_moments_badge
            r6.setImageViewBitmap(r7, r0)
            android.widget.RemoteViews r0 = r1.mWidget
            int r6 = com.locket.Locket.R.id.locket_missed_moments_badge
            r7 = 0
            r0.setViewVisibility(r6, r7)
            goto L_0x0356
        L_0x0346:
            android.widget.RemoteViews r0 = r1.mWidget
            int r6 = com.locket.Locket.R.id.locket_missed_moments_badge
            r7 = 8
            r0.setViewVisibility(r6, r7)
            android.widget.RemoteViews r0 = r1.mWidget
            int r6 = com.locket.Locket.R.id.locket_missed_moments_badge_max
            r0.setViewVisibility(r6, r7)
        L_0x0356:
            com.locket.Locket.Streaks.Streak r0 = r1.mStreak
            if (r0 == 0) goto L_0x03ea
            com.locket.Locket.Streaks.StreakManager r0 = new com.locket.Locket.Streaks.StreakManager
            android.content.Context r6 = r1.mContext
            r0.<init>(r6)
            com.locket.Locket.Streaks.Streak r6 = r1.mStreak
            boolean r0 = r0.shouldShowOnWidget(r6)
            if (r0 == 0) goto L_0x03e0
            com.locket.Locket.Streaks.Streak r0 = r1.mStreak
            int r0 = r0.getDisplayCount()
            r6 = 999(0x3e7, float:1.4E-42)
            if (r0 <= r6) goto L_0x0376
            int r3 = com.locket.Locket.R.drawable.bg_streak_four_digits
            goto L_0x0384
        L_0x0376:
            r6 = 99
            if (r0 <= r6) goto L_0x037d
            int r3 = com.locket.Locket.R.drawable.bg_streak_three_digits
            goto L_0x0384
        L_0x037d:
            if (r0 <= r3) goto L_0x0382
            int r3 = com.locket.Locket.R.drawable.bg_streak_two_digits
            goto L_0x0384
        L_0x0382:
            int r3 = com.locket.Locket.R.drawable.bg_streak_one_digit
        L_0x0384:
            android.widget.RemoteViews r6 = r1.mWidget
            int r7 = com.locket.Locket.R.id.locket_streak_frame
            r6.setInt(r7, r11, r3)
            com.locket.Locket.Streaks.Streak r3 = r1.mStreak
            boolean r3 = r3.isDailySendCompleted()
            if (r3 == 0) goto L_0x0399
            java.lang.String r3 = "#E6FFFFFF"
            r6 = 1063675494(0x3f666666, float:0.9)
            goto L_0x039e
        L_0x0399:
            java.lang.String r3 = "#8CFFFFFF"
            r6 = 1057803469(0x3f0ccccd, float:0.55)
        L_0x039e:
            r7 = 1132396544(0x437f0000, float:255.0)
            float r6 = r6 * r7
            int r6 = java.lang.Math.round(r6)
            android.widget.RemoteViews r7 = r1.mWidget
            int r8 = com.locket.Locket.R.id.locket_streak_icon
            java.lang.String r9 = "setImageAlpha"
            r7.setInt(r8, r9, r6)
            android.content.Context r6 = r1.mContext
            java.lang.String r20 = java.lang.Integer.toString(r0)
            int r21 = com.locket.Locket.R.font.proxima_soft_bold
            r22 = 1099431936(0x41880000, float:17.0)
            r23 = 1
            int r24 = android.graphics.Color.parseColor(r3)
            int r0 = r1.mWidgetMaxWidth
            int r3 = r1.mWidgetMaxHeight
            int r25 = java.lang.Math.min(r0, r3)
            r26 = 1
            r19 = r6
            android.graphics.Bitmap r0 = com.locket.Locket.ImageUtils.getTextBitmap(r19, r20, r21, r22, r23, r24, r25, r26)
            android.widget.RemoteViews r3 = r1.mWidget
            int r6 = com.locket.Locket.R.id.locket_streak_text
            r3.setImageViewBitmap(r6, r0)
            android.widget.RemoteViews r0 = r1.mWidget
            int r3 = com.locket.Locket.R.id.locket_streak_frame
            r6 = 0
            r0.setViewVisibility(r3, r6)
            r6 = 8
            goto L_0x03f3
        L_0x03e0:
            android.widget.RemoteViews r0 = r1.mWidget
            int r3 = com.locket.Locket.R.id.locket_streak_frame
            r6 = 8
            r0.setViewVisibility(r3, r6)
            goto L_0x03f3
        L_0x03ea:
            r6 = 8
            android.widget.RemoteViews r0 = r1.mWidget
            int r3 = com.locket.Locket.R.id.locket_streak_frame
            r0.setViewVisibility(r3, r6)
        L_0x03f3:
            if (r5 == 0) goto L_0x0405
            android.widget.RemoteViews r0 = r1.mWidget
            int r3 = com.locket.Locket.R.id.locket_frame_view
            r6 = 0
            r0.setViewVisibility(r3, r6)
            android.widget.RemoteViews r0 = r1.mWidget
            int r3 = com.locket.Locket.R.id.locket_remote_frame
            r0.setImageViewBitmap(r3, r5)
            goto L_0x040e
        L_0x0405:
            android.widget.RemoteViews r0 = r1.mWidget
            int r3 = com.locket.Locket.R.id.locket_frame_view
            r5 = 8
            r0.setViewVisibility(r3, r5)
        L_0x040e:
            if (r4 == 0) goto L_0x0427
            android.widget.RemoteViews r0 = r1.mWidget
            int r3 = com.locket.Locket.R.id.locket_frame_view
            r5 = 0
            r0.setViewVisibility(r3, r5)
            android.widget.RemoteViews r0 = r1.mWidget
            int r3 = com.locket.Locket.R.id.locket_frame
            r0.setViewVisibility(r3, r5)
            android.widget.RemoteViews r0 = r1.mWidget
            int r3 = com.locket.Locket.R.id.locket_frame
            r0.setImageViewBitmap(r3, r4)
            goto L_0x0430
        L_0x0427:
            android.widget.RemoteViews r0 = r1.mWidget
            int r3 = com.locket.Locket.R.id.locket_frame
            r4 = 8
            r0.setViewVisibility(r3, r4)
        L_0x0430:
            android.appwidget.AppWidgetManager r0 = r1.mAppWidgetManager
            int r3 = r1.mAppWidgetId
            android.widget.RemoteViews r4 = r1.mWidget
            r0.updateAppWidget(r3, r4)
            if (r2 == 0) goto L_0x0443
            com.locket.Locket.ImageDownloaderTask$$ExternalSyntheticLambda0 r0 = new com.locket.Locket.ImageDownloaderTask$$ExternalSyntheticLambda0
            r0.<init>(r1, r2)
            android.os.AsyncTask.execute(r0)
        L_0x0443:
            java.lang.String r0 = "End: onPostExecute"
            r2 = r18
            android.util.Log.d(r2, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.locket.Locket.ImageDownloaderTask.onPostExecute(com.locket.Locket.ImageDownloaderTask$ImageResult):void");
    }

    /* renamed from: com.locket.Locket.ImageDownloaderTask$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$locket$Locket$Overlays$CaptionType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.locket.Locket.Overlays.CaptionType[] r0 = com.locket.Locket.Overlays.CaptionType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$locket$Locket$Overlays$CaptionType = r0
                com.locket.Locket.Overlays.CaptionType r1 = com.locket.Locket.Overlays.CaptionType.TIME     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$locket$Locket$Overlays$CaptionType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.locket.Locket.Overlays.CaptionType r1 = com.locket.Locket.Overlays.CaptionType.STATIC_CONTENT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$locket$Locket$Overlays$CaptionType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.locket.Locket.Overlays.CaptionType r1 = com.locket.Locket.Overlays.CaptionType.STANDARD     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$locket$Locket$Overlays$CaptionType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.locket.Locket.Overlays.CaptionType r1 = com.locket.Locket.Overlays.CaptionType.STATUS     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.locket.Locket.ImageDownloaderTask.AnonymousClass1.<clinit>():void");
        }
    }

    private String cacheLocation() {
        return this.mCacheDir + "/locket_cache.png";
    }

    /* access modifiers changed from: private */
    /* renamed from: saveBitmapToCache */
    public void lambda$onPostExecute$0(Bitmap bitmap) {
        Log.d("Locket", "Begin: saveBitmapToCache");
        Log.d("Locket", "Saving bitmap to " + cacheLocation());
        try {
            String cacheLocation = cacheLocation();
            FileOutputStream create = SentryFileOutputStream.Factory.create(new FileOutputStream(cacheLocation), cacheLocation);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, create);
            create.flush();
            create.close();
        } catch (Exception e) {
            SentryLogcatAdapter.e("Locket", "Error: Could not save bitmap to cache");
            e.printStackTrace();
        }
        Log.d("Locket", "End: saveBitmapToCache");
    }

    private Bitmap loadBitmapFromCache() {
        Log.d("Locket", "Begin: loadBitmapFromCache");
        Log.d("Locket", "Loading bitmap from " + cacheLocation());
        try {
            if (!new File(cacheLocation()).exists()) {
                return null;
            }
            return BitmapFactory.decodeFile(cacheLocation());
        } catch (Exception unused) {
            return null;
        }
    }

    static final class ImageResult {
        Bitmap framePhoto;
        Bitmap moment;
        Bitmap profilePhoto;
        Bitmap remoteFramePhoto;

        public ImageResult(Bitmap bitmap, Bitmap bitmap2, Bitmap bitmap3, Bitmap bitmap4) {
            this.moment = bitmap;
            this.profilePhoto = bitmap2;
            this.remoteFramePhoto = bitmap3;
            this.framePhoto = bitmap4;
        }
    }
}

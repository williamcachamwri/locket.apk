package com.reactnativecommunity.cameraroll;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.provider.MediaStore;
import android.text.TextUtils;
import androidx.media3.common.MimeTypes;
import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.module.annotations.ReactModule;
import expo.modules.contacts.Columns;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import me.leolin.shortcutbadger.impl.NewHtcHomeBadger;

@ReactModule(name = "RNCCameraRoll")
public class CameraRollModule extends NativeCameraRollModuleSpec {
    private static final String ASSET_TYPE_ALL = "All";
    private static final String ASSET_TYPE_PHOTOS = "Photos";
    private static final String ASSET_TYPE_VIDEOS = "Videos";
    private static final String ERROR_UNABLE_TO_DELETE = "E_UNABLE_TO_DELETE";
    private static final String ERROR_UNABLE_TO_FILTER = "E_UNABLE_TO_FILTER";
    private static final String ERROR_UNABLE_TO_LOAD = "E_UNABLE_TO_LOAD";
    private static final String ERROR_UNABLE_TO_LOAD_PERMISSION = "E_UNABLE_TO_LOAD_PERMISSION";
    private static final String ERROR_UNABLE_TO_SAVE = "E_UNABLE_TO_SAVE";
    private static final String INCLUDE_FILENAME = "filename";
    private static final String INCLUDE_FILE_EXTENSION = "fileExtension";
    private static final String INCLUDE_FILE_SIZE = "fileSize";
    private static final String INCLUDE_IMAGE_SIZE = "imageSize";
    private static final String INCLUDE_LOCATION = "location";
    private static final String INCLUDE_ORIENTATION = "orientation";
    private static final String INCLUDE_PLAYABLE_DURATION = "playableDuration";
    public static final String NAME = "RNCCameraRoll";
    /* access modifiers changed from: private */
    public static final String[] PROJECTION = {Columns.ID, "mime_type", "bucket_display_name", "datetaken", "date_added", "date_modified", "width", "height", "_size", "_data", "orientation"};
    private static final String SELECTION_BUCKET = "bucket_display_name = ?";

    public String getName() {
        return NAME;
    }

    public CameraRollModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void saveToCameraRoll(String str, ReadableMap readableMap, Promise promise) {
        new SaveToCameraRoll(getReactApplicationContext(), Uri.parse(str), readableMap, promise).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private static class SaveToCameraRoll extends GuardedAsyncTask<Void, Void> {
        private final Context mContext;
        private final ReadableMap mOptions;
        private final Promise mPromise;
        private final Uri mUri;

        public SaveToCameraRoll(ReactContext reactContext, Uri uri, ReadableMap readableMap, Promise promise) {
            super(reactContext);
            this.mContext = reactContext;
            this.mUri = uri;
            this.mPromise = promise;
            this.mOptions = readableMap;
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Removed duplicated region for block: B:106:0x01fa A[SYNTHETIC, Splitter:B:106:0x01fa] */
        /* JADX WARNING: Removed duplicated region for block: B:111:0x0205 A[SYNTHETIC, Splitter:B:111:0x0205] */
        /* JADX WARNING: Removed duplicated region for block: B:118:0x0213 A[SYNTHETIC, Splitter:B:118:0x0213] */
        /* JADX WARNING: Removed duplicated region for block: B:123:0x021e A[SYNTHETIC, Splitter:B:123:0x021e] */
        /* JADX WARNING: Removed duplicated region for block: B:130:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void doInBackgroundGuarded(java.lang.Void... r18) {
            /*
                r17 = this;
                r1 = r17
                java.lang.String r0 = "is_pending"
                java.lang.String r2 = "Could not close output channel"
                java.lang.String r3 = "Could not close input channel"
                java.lang.String r4 = "ReactNative"
                java.io.File r5 = new java.io.File
                android.net.Uri r6 = r1.mUri
                java.lang.String r6 = r6.getPath()
                r5.<init>(r6)
                android.net.Uri r6 = r1.mUri
                java.lang.String r6 = r6.toString()
                java.lang.String r6 = com.reactnativecommunity.cameraroll.Utils.getMimeType(r6)
                java.lang.String r7 = "video"
                r8 = 1
                r9 = 0
                if (r6 == 0) goto L_0x002d
                boolean r10 = r6.contains(r7)
                if (r10 == 0) goto L_0x002d
                r10 = r8
                goto L_0x002e
            L_0x002d:
                r10 = r9
            L_0x002e:
                java.lang.Boolean r10 = java.lang.Boolean.valueOf(r10)
                com.facebook.react.bridge.ReadableMap r12 = r1.mOptions     // Catch:{ IOException -> 0x01ef, all -> 0x01e9 }
                java.lang.String r13 = "album"
                java.lang.String r12 = r12.getString(r13)     // Catch:{ IOException -> 0x01ef, all -> 0x01e9 }
                boolean r13 = android.text.TextUtils.isEmpty(r12)     // Catch:{ IOException -> 0x01ef, all -> 0x01e9 }
                if (r13 != 0) goto L_0x0042
                r13 = r8
                goto L_0x0043
            L_0x0042:
                r13 = r9
            L_0x0043:
                int r14 = android.os.Build.VERSION.SDK_INT     // Catch:{ IOException -> 0x01ef, all -> 0x01e9 }
                r15 = 29
                java.lang.String r11 = "E_UNABLE_TO_LOAD"
                if (r14 < r15) goto L_0x00e8
                android.content.ContentValues r7 = new android.content.ContentValues     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                r7.<init>()     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                if (r13 == 0) goto L_0x0070
                java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                r13.<init>()     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                java.lang.String r14 = android.os.Environment.DIRECTORY_DCIM     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                java.lang.StringBuilder r13 = r13.append(r14)     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                java.lang.String r14 = java.io.File.separator     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                java.lang.StringBuilder r13 = r13.append(r14)     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                java.lang.StringBuilder r12 = r13.append(r12)     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                java.lang.String r12 = r12.toString()     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                java.lang.String r13 = "relative_path"
                r7.put(r13, r12)     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
            L_0x0070:
                java.lang.String r12 = "mime_type"
                r7.put(r12, r6)     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                java.lang.String r6 = "_display_name"
                java.lang.String r12 = r5.getName()     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                r7.put(r6, r12)     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                java.lang.Integer r6 = java.lang.Integer.valueOf(r8)     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                r7.put(r0, r6)     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                android.content.Context r6 = r1.mContext     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                android.content.ContentResolver r6 = r6.getContentResolver()     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                boolean r8 = r10.booleanValue()     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                if (r8 == 0) goto L_0x0098
                android.net.Uri r8 = android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                android.net.Uri r8 = r6.insert(r8, r7)     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                goto L_0x009e
            L_0x0098:
                android.net.Uri r8 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                android.net.Uri r8 = r6.insert(r8, r7)     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
            L_0x009e:
                if (r8 != 0) goto L_0x00a7
                com.facebook.react.bridge.Promise r10 = r1.mPromise     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                java.lang.String r12 = "ContentResolver#insert() returns null, insert failed"
                r10.reject((java.lang.String) r11, (java.lang.String) r12)     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
            L_0x00a7:
                java.io.OutputStream r10 = r6.openOutputStream(r8)     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                java.io.FileInputStream r11 = new java.io.FileInputStream     // Catch:{ IOException -> 0x00db, all -> 0x00d8 }
                r11.<init>(r5)     // Catch:{ IOException -> 0x00db, all -> 0x00d8 }
                java.io.FileInputStream r5 = io.sentry.instrumentation.file.SentryFileInputStream.Factory.create((java.io.FileInputStream) r11, (java.io.File) r5)     // Catch:{ IOException -> 0x00db, all -> 0x00d8 }
                android.os.FileUtils.copy(r5, r10)     // Catch:{ IOException -> 0x00d4, all -> 0x00d0 }
                r7.clear()     // Catch:{ IOException -> 0x00d4, all -> 0x00d0 }
                java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ IOException -> 0x00d4, all -> 0x00d0 }
                r7.put(r0, r9)     // Catch:{ IOException -> 0x00d4, all -> 0x00d0 }
                r9 = 0
                r6.update(r8, r7, r9, r9)     // Catch:{ IOException -> 0x00d4, all -> 0x00d0 }
                com.facebook.react.bridge.Promise r0 = r1.mPromise     // Catch:{ IOException -> 0x00d4, all -> 0x00d0 }
                java.lang.String r6 = r8.toString()     // Catch:{ IOException -> 0x00d4, all -> 0x00d0 }
                r0.resolve(r6)     // Catch:{ IOException -> 0x00d4, all -> 0x00d0 }
                goto L_0x01ce
            L_0x00d0:
                r0 = move-exception
                r11 = r5
                goto L_0x0210
            L_0x00d4:
                r0 = move-exception
                r11 = r5
                goto L_0x01f3
            L_0x00d8:
                r0 = move-exception
                r5 = r0
                goto L_0x00e0
            L_0x00db:
                r0 = move-exception
                goto L_0x00e5
            L_0x00dd:
                r0 = move-exception
                r5 = r0
                r10 = 0
            L_0x00e0:
                r11 = 0
                goto L_0x0211
            L_0x00e3:
                r0 = move-exception
                r10 = 0
            L_0x00e5:
                r11 = 0
                goto L_0x01f3
            L_0x00e8:
                if (r13 == 0) goto L_0x0106
                com.facebook.react.bridge.ReadableMap r0 = r1.mOptions     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                java.lang.String r6 = "type"
                java.lang.String r0 = r0.getString(r6)     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                boolean r0 = r7.equals(r0)     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                if (r0 == 0) goto L_0x00ff
                java.lang.String r0 = android.os.Environment.DIRECTORY_MOVIES     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                java.io.File r0 = android.os.Environment.getExternalStoragePublicDirectory(r0)     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                goto L_0x010c
            L_0x00ff:
                java.lang.String r0 = android.os.Environment.DIRECTORY_PICTURES     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                java.io.File r0 = android.os.Environment.getExternalStoragePublicDirectory(r0)     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                goto L_0x010c
            L_0x0106:
                java.lang.String r0 = android.os.Environment.DIRECTORY_DCIM     // Catch:{ IOException -> 0x01ef, all -> 0x01e9 }
                java.io.File r0 = android.os.Environment.getExternalStoragePublicDirectory(r0)     // Catch:{ IOException -> 0x01ef, all -> 0x01e9 }
            L_0x010c:
                if (r13 == 0) goto L_0x0128
                java.io.File r6 = new java.io.File     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                r6.<init>(r0, r12)     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                boolean r0 = r6.exists()     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                if (r0 != 0) goto L_0x0127
                boolean r0 = r6.mkdirs()     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                if (r0 != 0) goto L_0x0127
                com.facebook.react.bridge.Promise r0 = r1.mPromise     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                java.lang.String r5 = "Album Directory not created. Did you request WRITE_EXTERNAL_STORAGE?"
                r0.reject((java.lang.String) r11, (java.lang.String) r5)     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                return
            L_0x0127:
                r0 = r6
            L_0x0128:
                boolean r6 = r0.isDirectory()     // Catch:{ IOException -> 0x01ef, all -> 0x01e9 }
                if (r6 != 0) goto L_0x0136
                com.facebook.react.bridge.Promise r0 = r1.mPromise     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                java.lang.String r5 = "External media storage directory not available"
                r0.reject((java.lang.String) r11, (java.lang.String) r5)     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                return
            L_0x0136:
                java.io.File r6 = new java.io.File     // Catch:{ IOException -> 0x01ef, all -> 0x01e9 }
                java.lang.String r7 = r5.getName()     // Catch:{ IOException -> 0x01ef, all -> 0x01e9 }
                r6.<init>(r0, r7)     // Catch:{ IOException -> 0x01ef, all -> 0x01e9 }
                java.lang.String r7 = r5.getName()     // Catch:{ IOException -> 0x01ef, all -> 0x01e9 }
                r10 = 46
                int r11 = r7.indexOf(r10)     // Catch:{ IOException -> 0x01ef, all -> 0x01e9 }
                if (r11 < 0) goto L_0x015e
                int r11 = r7.lastIndexOf(r10)     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                java.lang.String r11 = r7.substring(r9, r11)     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                int r10 = r7.lastIndexOf(r10)     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                java.lang.String r7 = r7.substring(r10)     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                r10 = r7
                r7 = r11
                goto L_0x0160
            L_0x015e:
                java.lang.String r10 = ""
            L_0x0160:
                r11 = r9
            L_0x0161:
                boolean r12 = r6.createNewFile()     // Catch:{ IOException -> 0x01ef, all -> 0x01e9 }
                if (r12 != 0) goto L_0x018b
                java.io.File r6 = new java.io.File     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                r12.<init>()     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                java.lang.StringBuilder r12 = r12.append(r7)     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                java.lang.String r13 = "_"
                java.lang.StringBuilder r12 = r12.append(r13)     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                int r13 = r11 + 1
                java.lang.StringBuilder r11 = r12.append(r11)     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                java.lang.StringBuilder r11 = r11.append(r10)     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                java.lang.String r11 = r11.toString()     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                r6.<init>(r0, r11)     // Catch:{ IOException -> 0x00e3, all -> 0x00dd }
                r11 = r13
                goto L_0x0161
            L_0x018b:
                java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ IOException -> 0x01ef, all -> 0x01e9 }
                r0.<init>(r5)     // Catch:{ IOException -> 0x01ef, all -> 0x01e9 }
                java.io.FileInputStream r5 = io.sentry.instrumentation.file.SentryFileInputStream.Factory.create((java.io.FileInputStream) r0, (java.io.File) r5)     // Catch:{ IOException -> 0x01ef, all -> 0x01e9 }
                java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x01e4, all -> 0x01df }
                r0.<init>(r6)     // Catch:{ IOException -> 0x01e4, all -> 0x01df }
                java.io.FileOutputStream r10 = io.sentry.instrumentation.file.SentryFileOutputStream.Factory.create((java.io.FileOutputStream) r0, (java.io.File) r6)     // Catch:{ IOException -> 0x01e4, all -> 0x01df }
                r0 = r10
                java.io.FileOutputStream r0 = (java.io.FileOutputStream) r0     // Catch:{ IOException -> 0x00d4, all -> 0x00d0 }
                java.nio.channels.FileChannel r11 = r10.getChannel()     // Catch:{ IOException -> 0x00d4, all -> 0x00d0 }
                java.nio.channels.FileChannel r12 = r5.getChannel()     // Catch:{ IOException -> 0x00d4, all -> 0x00d0 }
                r13 = 0
                java.nio.channels.FileChannel r0 = r5.getChannel()     // Catch:{ IOException -> 0x00d4, all -> 0x00d0 }
                long r15 = r0.size()     // Catch:{ IOException -> 0x00d4, all -> 0x00d0 }
                r11.transferFrom(r12, r13, r15)     // Catch:{ IOException -> 0x00d4, all -> 0x00d0 }
                r5.close()     // Catch:{ IOException -> 0x00d4, all -> 0x00d0 }
                r10.close()     // Catch:{ IOException -> 0x00d4, all -> 0x00d0 }
                android.content.Context r0 = r1.mContext     // Catch:{ IOException -> 0x00d4, all -> 0x00d0 }
                java.lang.String[] r7 = new java.lang.String[r8]     // Catch:{ IOException -> 0x00d4, all -> 0x00d0 }
                java.lang.String r6 = r6.getAbsolutePath()     // Catch:{ IOException -> 0x00d4, all -> 0x00d0 }
                r7[r9] = r6     // Catch:{ IOException -> 0x00d4, all -> 0x00d0 }
                com.reactnativecommunity.cameraroll.CameraRollModule$SaveToCameraRoll$$ExternalSyntheticLambda0 r6 = new com.reactnativecommunity.cameraroll.CameraRollModule$SaveToCameraRoll$$ExternalSyntheticLambda0     // Catch:{ IOException -> 0x00d4, all -> 0x00d0 }
                r6.<init>(r1)     // Catch:{ IOException -> 0x00d4, all -> 0x00d0 }
                r8 = 0
                android.media.MediaScannerConnection.scanFile(r0, r7, r8, r6)     // Catch:{ IOException -> 0x00d4, all -> 0x00d0 }
            L_0x01ce:
                if (r5 == 0) goto L_0x01d9
                r5.close()     // Catch:{ IOException -> 0x01d4 }
                goto L_0x01d9
            L_0x01d4:
                r0 = move-exception
                r5 = r0
                com.facebook.common.logging.FLog.e((java.lang.String) r4, (java.lang.String) r3, (java.lang.Throwable) r5)
            L_0x01d9:
                if (r10 == 0) goto L_0x020e
                r10.close()     // Catch:{ IOException -> 0x0209 }
                goto L_0x020e
            L_0x01df:
                r0 = move-exception
                r8 = 0
                r11 = r5
                r10 = r8
                goto L_0x0210
            L_0x01e4:
                r0 = move-exception
                r8 = 0
                r11 = r5
                r10 = r8
                goto L_0x01f3
            L_0x01e9:
                r0 = move-exception
                r8 = 0
                r5 = r0
                r10 = r8
                r11 = r10
                goto L_0x0211
            L_0x01ef:
                r0 = move-exception
                r8 = 0
                r10 = r8
                r11 = r10
            L_0x01f3:
                com.facebook.react.bridge.Promise r5 = r1.mPromise     // Catch:{ all -> 0x020f }
                r5.reject((java.lang.Throwable) r0)     // Catch:{ all -> 0x020f }
                if (r11 == 0) goto L_0x0203
                r11.close()     // Catch:{ IOException -> 0x01fe }
                goto L_0x0203
            L_0x01fe:
                r0 = move-exception
                r5 = r0
                com.facebook.common.logging.FLog.e((java.lang.String) r4, (java.lang.String) r3, (java.lang.Throwable) r5)
            L_0x0203:
                if (r10 == 0) goto L_0x020e
                r10.close()     // Catch:{ IOException -> 0x0209 }
                goto L_0x020e
            L_0x0209:
                r0 = move-exception
                r3 = r0
                com.facebook.common.logging.FLog.e((java.lang.String) r4, (java.lang.String) r2, (java.lang.Throwable) r3)
            L_0x020e:
                return
            L_0x020f:
                r0 = move-exception
            L_0x0210:
                r5 = r0
            L_0x0211:
                if (r11 == 0) goto L_0x021c
                r11.close()     // Catch:{ IOException -> 0x0217 }
                goto L_0x021c
            L_0x0217:
                r0 = move-exception
                r6 = r0
                com.facebook.common.logging.FLog.e((java.lang.String) r4, (java.lang.String) r3, (java.lang.Throwable) r6)
            L_0x021c:
                if (r10 == 0) goto L_0x0227
                r10.close()     // Catch:{ IOException -> 0x0222 }
                goto L_0x0227
            L_0x0222:
                r0 = move-exception
                r3 = r0
                com.facebook.common.logging.FLog.e((java.lang.String) r4, (java.lang.String) r2, (java.lang.Throwable) r3)
            L_0x0227:
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.reactnativecommunity.cameraroll.CameraRollModule.SaveToCameraRoll.doInBackgroundGuarded(java.lang.Void[]):void");
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$doInBackgroundGuarded$0(String str, Uri uri) {
            if (uri != null) {
                this.mPromise.resolve(uri.toString());
            } else {
                this.mPromise.reject(CameraRollModule.ERROR_UNABLE_TO_SAVE, "Could not add image to gallery");
            }
        }
    }

    @ReactMethod
    public void getPhotos(ReadableMap readableMap, Promise promise) {
        ReadableMap readableMap2 = readableMap;
        new GetMediaTask(getReactApplicationContext(), readableMap2.getInt("first"), readableMap2.hasKey(TtmlNode.ANNOTATION_POSITION_AFTER) ? readableMap2.getString(TtmlNode.ANNOTATION_POSITION_AFTER) : null, readableMap2.hasKey("groupName") ? readableMap2.getString("groupName") : null, readableMap2.hasKey("mimeTypes") ? readableMap2.getArray("mimeTypes") : null, readableMap2.hasKey("assetType") ? readableMap2.getString("assetType") : ASSET_TYPE_PHOTOS, readableMap2.hasKey("fromTime") ? (long) readableMap2.getDouble("fromTime") : 0, readableMap2.hasKey("toTime") ? (long) readableMap2.getDouble("toTime") : 0, readableMap2.hasKey("include") ? readableMap2.getArray("include") : null, promise).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private static class GetMediaTask extends GuardedAsyncTask<Void, Void> {
        @Nullable
        private final String mAfter;
        private final String mAssetType;
        private final Context mContext;
        private final int mFirst;
        private final long mFromTime;
        @Nullable
        private final String mGroupName;
        private final Set<String> mInclude;
        @Nullable
        private final ReadableArray mMimeTypes;
        private final Promise mPromise;
        private final long mToTime;

        private GetMediaTask(ReactContext reactContext, int i, @Nullable String str, @Nullable String str2, @Nullable ReadableArray readableArray, String str3, long j, long j2, @Nullable ReadableArray readableArray2, Promise promise) {
            super(reactContext);
            this.mContext = reactContext;
            this.mFirst = i;
            this.mAfter = str;
            this.mGroupName = str2;
            this.mMimeTypes = readableArray;
            this.mPromise = promise;
            this.mAssetType = str3;
            this.mFromTime = j;
            this.mToTime = j2;
            this.mInclude = createSetFromIncludeArray(readableArray2);
        }

        private static Set<String> createSetFromIncludeArray(@Nullable ReadableArray readableArray) {
            HashSet hashSet = new HashSet();
            if (readableArray == null) {
                return hashSet;
            }
            for (int i = 0; i < readableArray.size(); i++) {
                String string = readableArray.getString(i);
                if (string != null) {
                    hashSet.add(string);
                }
            }
            return hashSet;
        }

        /* access modifiers changed from: protected */
        public void doInBackgroundGuarded(Void... voidArr) {
            Cursor cursor;
            StringBuilder sb = new StringBuilder("1");
            ArrayList arrayList = new ArrayList();
            if (!TextUtils.isEmpty(this.mGroupName)) {
                sb.append(" AND bucket_display_name = ?");
                arrayList.add(this.mGroupName);
            }
            if (this.mAssetType.equals(CameraRollModule.ASSET_TYPE_PHOTOS)) {
                sb.append(" AND media_type = 1");
            } else if (this.mAssetType.equals(CameraRollModule.ASSET_TYPE_VIDEOS)) {
                sb.append(" AND media_type = 3");
            } else if (this.mAssetType.equals(CameraRollModule.ASSET_TYPE_ALL)) {
                sb.append(" AND media_type IN (3,1)");
            } else {
                this.mPromise.reject(CameraRollModule.ERROR_UNABLE_TO_FILTER, "Invalid filter option: '" + this.mAssetType + "'. Expected one of 'Photos', 'Videos' or 'All'.");
                return;
            }
            ReadableArray readableArray = this.mMimeTypes;
            int i = 0;
            if (readableArray != null && readableArray.size() > 0) {
                sb.append(" AND mime_type IN (");
                for (int i2 = 0; i2 < this.mMimeTypes.size(); i2++) {
                    sb.append("?,");
                    arrayList.add(this.mMimeTypes.getString(i2));
                }
                sb.replace(sb.length() - 1, sb.length(), ")");
            }
            long j = this.mFromTime;
            if (j > 0) {
                sb.append(" AND (datetaken > ? OR ( datetaken IS NULL AND date_added> ? ))");
                arrayList.add(this.mFromTime + "");
                arrayList.add((j / 1000) + "");
            }
            long j2 = this.mToTime;
            if (j2 > 0) {
                sb.append(" AND (datetaken <= ? OR ( datetaken IS NULL AND date_added <= ? ))");
                arrayList.add(this.mToTime + "");
                arrayList.add((j2 / 1000) + "");
            }
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            ContentResolver contentResolver = this.mContext.getContentResolver();
            try {
                if (Build.VERSION.SDK_INT >= 30) {
                    Bundle bundle = new Bundle();
                    bundle.putString("android:query-arg-sql-selection", sb.toString());
                    bundle.putStringArray("android:query-arg-sql-selection-args", (String[]) arrayList.toArray(new String[arrayList.size()]));
                    bundle.putString("android:query-arg-sql-sort-order", "date_added DESC, date_modified DESC");
                    bundle.putInt("android:query-arg-limit", this.mFirst + 1);
                    if (!TextUtils.isEmpty(this.mAfter)) {
                        bundle.putInt("android:query-arg-offset", Integer.parseInt(this.mAfter));
                    }
                    cursor = contentResolver.query(MediaStore.Files.getContentUri("external"), CameraRollModule.PROJECTION, bundle, (CancellationSignal) null);
                } else {
                    String str = "limit=" + (this.mFirst + 1);
                    if (!TextUtils.isEmpty(this.mAfter)) {
                        str = "limit=" + this.mAfter + "," + (this.mFirst + 1);
                    }
                    cursor = contentResolver.query(MediaStore.Files.getContentUri("external").buildUpon().encodedQuery(str).build(), CameraRollModule.PROJECTION, sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]), "date_added DESC, date_modified DESC");
                }
                if (cursor == null) {
                    this.mPromise.reject(CameraRollModule.ERROR_UNABLE_TO_LOAD, "Could not get media");
                    return;
                }
                CameraRollModule.putEdges(contentResolver, cursor, writableNativeMap, this.mFirst, this.mInclude);
                int i3 = this.mFirst;
                if (!TextUtils.isEmpty(this.mAfter)) {
                    i = Integer.parseInt(this.mAfter);
                }
                CameraRollModule.putPageInfo(cursor, writableNativeMap, i3, i);
                cursor.close();
                this.mPromise.resolve(writableNativeMap);
            } catch (SecurityException e) {
                this.mPromise.reject(CameraRollModule.ERROR_UNABLE_TO_LOAD_PERMISSION, "Could not get media: need READ_EXTERNAL_STORAGE permission", (Throwable) e);
            } catch (Throwable th) {
                cursor.close();
                this.mPromise.resolve(writableNativeMap);
                throw th;
            }
        }
    }

    @ReactMethod
    public void getAlbums(ReadableMap readableMap, Promise promise) {
        Cursor query;
        WritableNativeArray writableNativeArray;
        String string = readableMap.hasKey("assetType") ? readableMap.getString("assetType") : ASSET_TYPE_ALL;
        StringBuilder sb = new StringBuilder("1");
        ArrayList arrayList = new ArrayList();
        if (string.equals(ASSET_TYPE_PHOTOS)) {
            sb.append(" AND media_type = 1");
        } else if (string.equals(ASSET_TYPE_VIDEOS)) {
            sb.append(" AND media_type = 3");
        } else if (string.equals(ASSET_TYPE_ALL)) {
            sb.append(" AND media_type IN (3,1)");
        } else {
            promise.reject(ERROR_UNABLE_TO_FILTER, "Invalid filter option: '" + string + "'. Expected one of 'Photos', 'Videos' or 'All'.");
            return;
        }
        try {
            query = getReactApplicationContext().getContentResolver().query(MediaStore.Files.getContentUri("external"), new String[]{"bucket_display_name"}, sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]), (String) null);
            if (query == null) {
                promise.reject(ERROR_UNABLE_TO_LOAD, "Could not get media");
                return;
            }
            writableNativeArray = new WritableNativeArray();
            if (query.moveToFirst()) {
                HashMap hashMap = new HashMap();
                do {
                    int columnIndex = query.getColumnIndex("bucket_display_name");
                    if (columnIndex >= 0) {
                        String string2 = query.getString(columnIndex);
                        if (string2 != null) {
                            Integer num = (Integer) hashMap.get(string2);
                            if (num == null) {
                                hashMap.put(string2, 1);
                            } else {
                                hashMap.put(string2, Integer.valueOf(num.intValue() + 1));
                            }
                        }
                    } else {
                        throw new IndexOutOfBoundsException();
                    }
                } while (query.moveToNext());
                for (Map.Entry entry : hashMap.entrySet()) {
                    WritableNativeMap writableNativeMap = new WritableNativeMap();
                    writableNativeMap.putString("title", (String) entry.getKey());
                    writableNativeMap.putInt(NewHtcHomeBadger.COUNT, ((Integer) entry.getValue()).intValue());
                    writableNativeArray.pushMap(writableNativeMap);
                }
            }
            query.close();
            promise.resolve(writableNativeArray);
        } catch (Exception e) {
            promise.reject(ERROR_UNABLE_TO_LOAD, "Could not get media", (Throwable) e);
        } catch (Throwable th) {
            query.close();
            promise.resolve(writableNativeArray);
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public static void putPageInfo(Cursor cursor, WritableMap writableMap, int i, int i2) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putBoolean("has_next_page", i < cursor.getCount());
        if (i < cursor.getCount()) {
            writableNativeMap.putString("end_cursor", Integer.toString(i2 + i));
        }
        writableMap.putMap("page_info", writableNativeMap);
    }

    /* access modifiers changed from: private */
    public static void putEdges(ContentResolver contentResolver, Cursor cursor, WritableMap writableMap, int i, Set<String> set) {
        int i2;
        WritableNativeArray writableNativeArray;
        Cursor cursor2 = cursor;
        Set<String> set2 = set;
        WritableNativeArray writableNativeArray2 = new WritableNativeArray();
        cursor.moveToFirst();
        int columnIndex = cursor2.getColumnIndex("mime_type");
        int columnIndex2 = cursor2.getColumnIndex("bucket_display_name");
        int columnIndex3 = cursor2.getColumnIndex("datetaken");
        int columnIndex4 = cursor2.getColumnIndex("date_added");
        int columnIndex5 = cursor2.getColumnIndex("date_modified");
        int columnIndex6 = cursor2.getColumnIndex("width");
        int columnIndex7 = cursor2.getColumnIndex("height");
        int columnIndex8 = cursor2.getColumnIndex("_size");
        int columnIndex9 = cursor2.getColumnIndex("_data");
        int columnIndex10 = cursor2.getColumnIndex("orientation");
        boolean contains = set2.contains("location");
        boolean contains2 = set2.contains("filename");
        boolean contains3 = set2.contains(INCLUDE_FILE_SIZE);
        boolean contains4 = set2.contains(INCLUDE_FILE_EXTENSION);
        boolean contains5 = set2.contains(INCLUDE_IMAGE_SIZE);
        boolean contains6 = set2.contains(INCLUDE_PLAYABLE_DURATION);
        boolean contains7 = set2.contains("orientation");
        int i3 = i;
        int i4 = 0;
        while (i4 < i3 && !cursor.isAfterLast()) {
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            WritableNativeMap writableNativeMap2 = new WritableNativeMap();
            WritableNativeMap writableNativeMap3 = writableNativeMap2;
            WritableNativeMap writableNativeMap4 = writableNativeMap;
            int i5 = i4;
            WritableNativeArray writableNativeArray3 = writableNativeArray2;
            if (putImageInfo(contentResolver, cursor, writableNativeMap2, columnIndex6, columnIndex7, columnIndex8, columnIndex9, columnIndex10, columnIndex, contains2, contains3, contains4, contains5, contains6, contains7)) {
                Cursor cursor3 = cursor;
                WritableNativeMap writableNativeMap5 = writableNativeMap3;
                putBasicNodeInfo(cursor3, writableNativeMap5, columnIndex, columnIndex2, columnIndex3, columnIndex4, columnIndex5);
                putLocationInfo(cursor3, writableNativeMap5, columnIndex9, contains, columnIndex, contentResolver);
                writableNativeMap4.putMap("node", writableNativeMap5);
                writableNativeArray = writableNativeArray3;
                writableNativeArray.pushMap(writableNativeMap4);
                i2 = i5;
            } else {
                writableNativeArray = writableNativeArray3;
                i2 = i5 - 1;
            }
            cursor.moveToNext();
            i4 = i2 + 1;
            Cursor cursor4 = cursor;
            i3 = i;
            writableNativeArray2 = writableNativeArray;
        }
        writableMap.putArray("edges", writableNativeArray2);
    }

    private static void putBasicNodeInfo(Cursor cursor, WritableMap writableMap, int i, int i2, int i3, int i4, int i5) {
        writableMap.putString("type", cursor.getString(i));
        writableMap.putString("group_name", cursor.getString(i2));
        long j = cursor.getLong(i3);
        if (j == 0) {
            j = cursor.getLong(i4) * 1000;
        }
        writableMap.putDouble("timestamp", ((double) j) / 1000.0d);
        writableMap.putDouble("modified", (double) cursor.getLong(i5));
    }

    private static boolean putImageInfo(ContentResolver contentResolver, Cursor cursor, WritableMap writableMap, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        Cursor cursor2 = cursor;
        int i7 = i4;
        int i8 = i5;
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        Uri parse = Uri.parse("file://" + cursor2.getString(i7));
        writableNativeMap.putString("uri", parse.toString());
        String string = cursor2.getString(i6);
        boolean z7 = string != null && string.startsWith(MimeTypes.BASE_TYPE_VIDEO);
        boolean putImageSize = putImageSize(contentResolver, cursor, writableNativeMap, i, i2, i5, parse, z7, z4);
        boolean putPlayableDuration = putPlayableDuration(contentResolver, writableNativeMap, parse, z7, z5);
        if (z) {
            writableNativeMap.putString("filename", new File(cursor2.getString(i7)).getName());
        } else {
            writableNativeMap.putNull("filename");
        }
        if (z2) {
            writableNativeMap.putDouble(INCLUDE_FILE_SIZE, (double) cursor2.getLong(i3));
        } else {
            writableNativeMap.putNull(INCLUDE_FILE_SIZE);
        }
        if (z3) {
            writableNativeMap.putString("extension", Utils.getExtension(string));
        } else {
            writableNativeMap.putNull("extension");
        }
        if (!z6) {
            writableNativeMap.putNull("orientation");
        } else if (cursor2.isNull(i8)) {
            writableNativeMap.putInt("orientation", cursor2.getInt(i8));
        } else {
            writableNativeMap.putInt("orientation", 0);
        }
        writableMap.putMap("image", writableNativeMap);
        if (!putImageSize || !putPlayableDuration) {
            return false;
        }
        return true;
    }

    private static boolean putPlayableDuration(ContentResolver contentResolver, WritableMap writableMap, Uri uri, boolean z, boolean z2) {
        AssetFileDescriptor assetFileDescriptor;
        writableMap.putNull(INCLUDE_PLAYABLE_DURATION);
        boolean z3 = true;
        if (z2 && z) {
            boolean z4 = false;
            Integer num = null;
            try {
                assetFileDescriptor = contentResolver.openAssetFileDescriptor(uri, "r");
            } catch (FileNotFoundException e) {
                FLog.e(ReactConstants.TAG, "Could not open asset file " + uri.toString(), (Throwable) e);
                z3 = false;
                assetFileDescriptor = null;
            }
            if (assetFileDescriptor != null) {
                MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                try {
                    mediaMetadataRetriever.setDataSource(assetFileDescriptor.getFileDescriptor());
                } catch (RuntimeException unused) {
                }
                try {
                    num = Integer.valueOf(Integer.parseInt(mediaMetadataRetriever.extractMetadata(9)) / 1000);
                    z4 = z3;
                } catch (NumberFormatException e2) {
                    FLog.e(ReactConstants.TAG, "Number format exception occurred while trying to fetch video metadata for " + uri.toString(), (Throwable) e2);
                }
                try {
                    mediaMetadataRetriever.release();
                } catch (Exception unused2) {
                }
                z3 = z4;
            }
            if (assetFileDescriptor != null) {
                try {
                    assetFileDescriptor.close();
                } catch (IOException unused3) {
                }
            }
            if (num != null) {
                writableMap.putInt(INCLUDE_PLAYABLE_DURATION, num.intValue());
            }
        }
        return z3;
    }

    private static boolean putImageSize(ContentResolver contentResolver, Cursor cursor, WritableMap writableMap, int i, int i2, int i3, Uri uri, boolean z, boolean z2) {
        int i4;
        AssetFileDescriptor assetFileDescriptor;
        boolean z3;
        Cursor cursor2 = cursor;
        WritableMap writableMap2 = writableMap;
        int i5 = i3;
        writableMap2.putNull("width");
        writableMap2.putNull("height");
        boolean z4 = true;
        if (!z2) {
            return true;
        }
        int i6 = cursor2.getInt(i);
        int i7 = cursor2.getInt(i2);
        if (i6 <= 0 || i7 <= 0) {
            boolean z5 = false;
            try {
                assetFileDescriptor = contentResolver.openAssetFileDescriptor(uri, "r");
                z3 = true;
            } catch (FileNotFoundException e) {
                FLog.e(ReactConstants.TAG, "Could not open asset file " + uri.toString(), (Throwable) e);
                z3 = false;
                assetFileDescriptor = null;
            }
            if (assetFileDescriptor != null) {
                if (z) {
                    MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                    try {
                        mediaMetadataRetriever.setDataSource(assetFileDescriptor.getFileDescriptor());
                    } catch (RuntimeException unused) {
                    }
                    try {
                        i6 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(18));
                        i7 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(19));
                        z5 = z3;
                    } catch (NumberFormatException e2) {
                        FLog.e(ReactConstants.TAG, "Number format exception occurred while trying to fetch video metadata for " + uri.toString(), (Throwable) e2);
                    }
                    try {
                        mediaMetadataRetriever.release();
                    } catch (Exception unused2) {
                    }
                    z4 = z5;
                } else {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeFileDescriptor(assetFileDescriptor.getFileDescriptor(), (Rect) null, options);
                    int i8 = options.outWidth;
                    i7 = options.outHeight;
                    i6 = i8;
                    z4 = z3;
                }
                try {
                    assetFileDescriptor.close();
                } catch (IOException e3) {
                    FLog.e(ReactConstants.TAG, "Can't close media descriptor " + uri.toString(), (Throwable) e3);
                }
            } else {
                z4 = z3;
            }
        }
        if (!cursor2.isNull(i5) && (i4 = cursor2.getInt(i5)) >= 0 && i4 % RotationOptions.ROTATE_180 != 0) {
            int i9 = i7;
            i7 = i6;
            i6 = i9;
        }
        writableMap2.putInt("width", i6);
        writableMap2.putInt("height", i7);
        return z4;
    }

    private static void putLocationInfo(Cursor cursor, WritableMap writableMap, int i, boolean z, int i2, ContentResolver contentResolver) {
        AssetFileDescriptor assetFileDescriptor;
        writableMap.putNull("location");
        if (z) {
            try {
                String string = cursor.getString(i2);
                if (string != null && string.startsWith(MimeTypes.BASE_TYPE_VIDEO)) {
                    Uri parse = Uri.parse("file://" + cursor.getString(i));
                    try {
                        assetFileDescriptor = contentResolver.openAssetFileDescriptor(parse, "r");
                    } catch (FileNotFoundException e) {
                        FLog.e(ReactConstants.TAG, "Could not open asset file " + parse.toString(), (Throwable) e);
                        assetFileDescriptor = null;
                    }
                    if (assetFileDescriptor != null) {
                        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                        try {
                            mediaMetadataRetriever.setDataSource(assetFileDescriptor.getFileDescriptor());
                        } catch (RuntimeException unused) {
                        }
                        try {
                            String extractMetadata = mediaMetadataRetriever.extractMetadata(23);
                            if (extractMetadata != null) {
                                String replaceAll = extractMetadata.replaceAll("/", "");
                                WritableNativeMap writableNativeMap = new WritableNativeMap();
                                writableNativeMap.putDouble("latitude", Double.parseDouble(replaceAll.split("[+]|[-]")[1]));
                                writableNativeMap.putDouble("longitude", Double.parseDouble(replaceAll.split("[+]|[-]")[2]));
                                writableMap.putMap("location", writableNativeMap);
                            }
                        } catch (NumberFormatException e2) {
                            FLog.e(ReactConstants.TAG, "Number format exception occurred while trying to fetch video metadata for " + parse.toString(), (Throwable) e2);
                        }
                        try {
                            mediaMetadataRetriever.release();
                        } catch (Exception unused2) {
                        }
                    }
                    if (assetFileDescriptor != null) {
                        try {
                            assetFileDescriptor.close();
                        } catch (IOException unused3) {
                        }
                    }
                } else {
                    ExifInterface exifInterface = new ExifInterface(cursor.getString(i));
                    float[] fArr = new float[2];
                    if (exifInterface.getLatLong(fArr)) {
                        double d = (double) fArr[0];
                        WritableNativeMap writableNativeMap2 = new WritableNativeMap();
                        writableNativeMap2.putDouble("longitude", (double) fArr[1]);
                        writableNativeMap2.putDouble("latitude", d);
                        writableMap.putMap("location", writableNativeMap2);
                    }
                }
            } catch (IOException e3) {
                FLog.e(ReactConstants.TAG, "Could not read the metadata", (Throwable) e3);
            }
        }
    }

    @ReactMethod
    public void deletePhotos(ReadableArray readableArray, Promise promise) {
        if (readableArray.size() == 0) {
            promise.reject(ERROR_UNABLE_TO_DELETE, "Need at least one URI to delete");
        } else {
            new DeletePhotos(getReactApplicationContext(), readableArray, promise).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    @ReactMethod
    public void getPhotoByInternalID(String str, ReadableMap readableMap, Promise promise) {
        promise.reject("CameraRoll:getPhotoByInternalID", "getPhotoByInternalID is not supported on Android");
    }

    private static class DeletePhotos extends GuardedAsyncTask<Void, Void> {
        private final Context mContext;
        private final Promise mPromise;
        private final ReadableArray mUris;

        public DeletePhotos(ReactContext reactContext, ReadableArray readableArray, Promise promise) {
            super(reactContext);
            this.mContext = reactContext;
            this.mUris = readableArray;
            this.mPromise = promise;
        }

        /* access modifiers changed from: protected */
        public void doInBackgroundGuarded(Void... voidArr) {
            ContentResolver contentResolver = this.mContext.getContentResolver();
            String[] strArr = {Columns.ID};
            String str = "?";
            for (int i = 1; i < this.mUris.size(); i++) {
                str = str + ", ?";
            }
            String str2 = "_data IN (" + str + ")";
            Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            String[] strArr2 = new String[this.mUris.size()];
            int i2 = 0;
            for (int i3 = 0; i3 < this.mUris.size(); i3++) {
                strArr2[i3] = Uri.parse(this.mUris.getString(i3)).getPath();
            }
            Cursor query = contentResolver.query(uri, strArr, str2, strArr2, (String) null);
            while (query.moveToNext()) {
                if (contentResolver.delete(ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, query.getLong(query.getColumnIndexOrThrow(Columns.ID))), (String) null, (String[]) null) == 1) {
                    i2++;
                }
            }
            query.close();
            if (i2 == this.mUris.size()) {
                this.mPromise.resolve(true);
            } else {
                this.mPromise.reject(CameraRollModule.ERROR_UNABLE_TO_DELETE, "Could not delete all media, only deleted " + i2 + " photos.");
            }
        }
    }
}

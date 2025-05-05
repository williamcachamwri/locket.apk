package cl.json;

import android.app.Application;
import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import androidx.core.content.FileProvider;
import androidx.media3.common.MimeTypes;
import com.facebook.react.bridge.ReactContext;
import java.io.File;
import java.util.ArrayList;

public class RNSharePathUtil {
    private static final ArrayList<String> authorities = new ArrayList<>();

    public static void compileAuthorities(ReactContext reactContext) {
        ArrayList<String> arrayList = authorities;
        if (arrayList.size() == 0) {
            Application application = (Application) reactContext.getApplicationContext();
            if (application instanceof ShareApplication) {
                arrayList.add(((ShareApplication) application).getFileProviderAuthority());
            }
            arrayList.add(reactContext.getPackageName() + ".rnshare.fileprovider");
        }
    }

    public static Uri compatUriFromFile(ReactContext reactContext, File file) {
        compileAuthorities(reactContext);
        String authority = Uri.fromFile(file).getAuthority();
        if (!TextUtils.isEmpty(authority) && authorities.contains(authority)) {
            return Uri.fromFile(file);
        }
        if (file.getAbsolutePath().startsWith("content://")) {
            return Uri.fromFile(file);
        }
        Uri uri = null;
        int i = 0;
        while (true) {
            ArrayList<String> arrayList = authorities;
            if (i >= arrayList.size()) {
                break;
            }
            try {
                uri = FileProvider.getUriForFile(reactContext, arrayList.get(i), file);
                if (uri != null) {
                    break;
                }
                i++;
            } catch (Exception e) {
                System.out.println("RNSharePathUtil::compatUriFromFile ERROR " + e.getMessage());
            }
        }
        return uri;
    }

    public static String getRealPathFromURI(Context context, Uri uri, Boolean bool) {
        Uri uri2 = null;
        if (DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {
                String[] split = DocumentsContract.getDocumentId(uri).split(":");
                String str = split[0];
                if ("primary".equalsIgnoreCase(str) || "0".equalsIgnoreCase(str)) {
                    return "" + (bool.booleanValue() ? context.getCacheDir() : context.getExternalCacheDir()) + "/" + split[1];
                } else if ("raw".equalsIgnoreCase(str)) {
                    return "" + split[1];
                } else {
                    if (!TextUtils.isEmpty(str)) {
                        return "/storage/" + str + "/" + split[1];
                    }
                }
            } else if (isDownloadsDocument(uri)) {
                String documentId = DocumentsContract.getDocumentId(uri);
                if (documentId.startsWith("raw:")) {
                    return "" + documentId.replaceFirst("raw:", "");
                }
                return "" + getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId).longValue()), (String) null, (String[]) null);
            } else if (isMediaDocument(uri)) {
                String[] split2 = DocumentsContract.getDocumentId(uri).split(":");
                String str2 = split2[0];
                if ("image".equals(str2)) {
                    uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if (MimeTypes.BASE_TYPE_VIDEO.equals(str2)) {
                    uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if (MimeTypes.BASE_TYPE_AUDIO.equals(str2)) {
                    uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                } else if ("raw".equalsIgnoreCase(str2)) {
                    return "" + split2[1];
                }
                return "" + getDataColumn(context, uri2, "_id=?", new String[]{split2[1]});
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            if (isGooglePhotosUri(uri)) {
                return uri.getLastPathSegment();
            }
            return "" + getDataColumn(context, uri, (String) null, (String[]) null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0045  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getDataColumn(android.content.Context r10, android.net.Uri r11, java.lang.String r12, java.lang.String[] r13) {
        /*
            java.lang.String r0 = "_data"
            java.lang.String[] r4 = new java.lang.String[]{r0}
            r8 = 0
            androidx.loader.content.CursorLoader r9 = new androidx.loader.content.CursorLoader     // Catch:{ Exception -> 0x0036, all -> 0x0034 }
            r7 = 0
            r1 = r9
            r2 = r10
            r3 = r11
            r5 = r12
            r6 = r13
            r1.<init>(r2, r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x0036, all -> 0x0034 }
            android.database.Cursor r10 = r9.loadInBackground()     // Catch:{ Exception -> 0x0036, all -> 0x0034 }
            if (r10 == 0) goto L_0x002e
            boolean r11 = r10.moveToFirst()     // Catch:{ Exception -> 0x002c }
            if (r11 == 0) goto L_0x002e
            int r11 = r10.getColumnIndexOrThrow(r0)     // Catch:{ Exception -> 0x002c }
            java.lang.String r11 = r10.getString(r11)     // Catch:{ Exception -> 0x002c }
            if (r10 == 0) goto L_0x002b
            r10.close()
        L_0x002b:
            return r11
        L_0x002c:
            r11 = move-exception
            goto L_0x0038
        L_0x002e:
            if (r10 == 0) goto L_0x0033
            r10.close()
        L_0x0033:
            return r8
        L_0x0034:
            r11 = move-exception
            goto L_0x0043
        L_0x0036:
            r11 = move-exception
            r10 = r8
        L_0x0038:
            r11.printStackTrace()     // Catch:{ all -> 0x0041 }
            if (r10 == 0) goto L_0x0040
            r10.close()
        L_0x0040:
            return r8
        L_0x0041:
            r11 = move-exception
            r8 = r10
        L_0x0043:
            if (r8 == 0) goto L_0x0048
            r8.close()
        L_0x0048:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: cl.json.RNSharePathUtil.getDataColumn(android.content.Context, android.net.Uri, java.lang.String, java.lang.String[]):java.lang.String");
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
}

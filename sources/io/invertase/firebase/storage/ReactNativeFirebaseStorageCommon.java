package io.invertase.firebase.storage;

import android.net.Uri;
import android.os.Environment;
import android.webkit.MimeTypeMap;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageException;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import io.invertase.firebase.app.ReactNativeFirebaseApp;
import io.invertase.firebase.common.ReactNativeFirebaseModule;
import io.invertase.firebase.common.SharedUtils;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;

class ReactNativeFirebaseStorageCommon {
    private static final String CODE_BUCKET_NOT_FOUND = "bucket-not-found";
    static final String CODE_CANCELLED = "cancelled";
    private static final String CODE_FILE_NOT_FOUND = "file-not-found";
    private static final String CODE_NON_MATCHING_CHECKSUM = "non-matching-checksum";
    private static final String CODE_OBJECT_NOT_FOUND = "object-not-found";
    private static final String CODE_PROJECT_NOT_FOUND = "project-not-found";
    private static final String CODE_QUOTA_EXCEEDED = "quota-exceeded";
    private static final String CODE_RETRY_LIMIT_EXCEEDED = "retry-limit-exceeded";
    private static final String CODE_UNAUTHENTICATED = "unauthenticated";
    private static final String CODE_UNAUTHORIZED = "unauthorized";
    private static final String KEY_BUCKET = "bucket";
    private static final String KEY_CACHE_CONTROL = "cacheControl";
    private static final String KEY_CONTENT_DISPOSITION = "contentDisposition";
    private static final String KEY_CONTENT_ENCODING = "contentEncoding";
    private static final String KEY_CONTENT_LANG = "contentLanguage";
    private static final String KEY_CONTENT_TYPE = "contentType";
    private static final String KEY_CUSTOM_META = "customMetadata";
    private static final String KEY_FULL_PATH = "fullPath";
    private static final String KEY_GENERATION = "generation";
    private static final String KEY_MD5_HASH = "md5Hash";
    private static final String KEY_META_GENERATION = "metageneration";
    private static final String KEY_NAME = "name";
    private static final String KEY_SIZE = "size";
    private static final String KEY_TIME_CREATED = "timeCreated";
    private static final String KEY_UPDATED = "updated";
    static final String STATUS_CANCELLED = "cancelled";
    static final String STATUS_ERROR = "error";
    private static final String STATUS_PAUSED = "paused";
    private static final String STATUS_RUNNING = "running";
    private static final String STATUS_SUCCESS = "success";
    private static final String STATUS_UNKNOWN = "unknown";

    ReactNativeFirebaseStorageCommon() {
    }

    static String getTaskStatus(@Nullable StorageTask<?> storageTask) {
        if (storageTask == null) {
            return "unknown";
        }
        if (storageTask.isInProgress()) {
            return STATUS_RUNNING;
        }
        if (storageTask.isPaused()) {
            return STATUS_PAUSED;
        }
        if (storageTask.isSuccessful() || storageTask.isComplete()) {
            return "success";
        }
        if (storageTask.isCanceled()) {
            return "cancelled";
        }
        if (storageTask.getException() != null) {
            return "error";
        }
        return "unknown";
    }

    static StorageMetadata buildMetadataFromMap(ReadableMap readableMap, @Nullable Uri uri, @Nullable StorageMetadata storageMetadata) {
        StorageMetadata.Builder builder = new StorageMetadata.Builder();
        String str = null;
        if (readableMap != null) {
            if (readableMap.hasKey(KEY_CUSTOM_META) || storageMetadata != null) {
                HashMap hashMap = new HashMap();
                ReadableMap map = readableMap.getMap(KEY_CUSTOM_META);
                HashMap hashMap2 = new HashMap();
                if (storageMetadata != null) {
                    for (String next : storageMetadata.getCustomMetadataKeys()) {
                        hashMap.put(next, storageMetadata.getCustomMetadata(next));
                        hashMap2.put(next, storageMetadata.getCustomMetadata(next));
                    }
                }
                if (map != null) {
                    hashMap.putAll(map.toHashMap());
                }
                for (Map.Entry entry : hashMap.entrySet()) {
                    builder.setCustomMetadata((String) entry.getKey(), (String) entry.getValue());
                    if (map == null || !map.hasKey((String) entry.getKey())) {
                        builder.setCustomMetadata((String) entry.getKey(), (String) null);
                    }
                }
            }
            if (readableMap.hasKey(KEY_CACHE_CONTROL)) {
                builder.setCacheControl(readableMap.getString(KEY_CACHE_CONTROL));
            }
            if (readableMap.hasKey(KEY_CONTENT_ENCODING)) {
                builder.setContentEncoding(readableMap.getString(KEY_CONTENT_ENCODING));
            }
            if (readableMap.hasKey(KEY_CONTENT_LANG)) {
                builder.setContentLanguage(readableMap.getString(KEY_CONTENT_LANG));
            }
            if (readableMap.hasKey(KEY_CONTENT_DISPOSITION)) {
                builder.setContentDisposition(readableMap.getString(KEY_CONTENT_DISPOSITION));
            }
        }
        if (readableMap != null && readableMap.hasKey("contentType")) {
            builder.setContentType(readableMap.getString("contentType"));
        } else if (uri != null) {
            String scheme = uri.getScheme();
            if (scheme != null && scheme.equals("content")) {
                str = ReactNativeFirebaseApp.getApplicationContext().getContentResolver().getType(uri);
            }
            if (str == null) {
                str = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(uri.toString()).toLowerCase(Locale.ROOT));
            }
            if (str != null) {
                builder.setContentType(str);
            }
        }
        return builder.build();
    }

    static WritableMap getMetadataAsMap(@Nullable StorageMetadata storageMetadata) {
        if (storageMetadata == null) {
            return null;
        }
        WritableMap createMap = Arguments.createMap();
        createMap.putString(KEY_BUCKET, storageMetadata.getBucket());
        createMap.putString(KEY_GENERATION, storageMetadata.getGeneration());
        createMap.putString(KEY_META_GENERATION, storageMetadata.getMetadataGeneration());
        createMap.putString(KEY_FULL_PATH, storageMetadata.getPath());
        createMap.putString("name", storageMetadata.getName());
        createMap.putDouble(KEY_SIZE, (double) storageMetadata.getSizeBytes());
        createMap.putString(KEY_TIME_CREATED, SharedUtils.timestampToUTC(storageMetadata.getCreationTimeMillis() / 1000));
        createMap.putString(KEY_UPDATED, SharedUtils.timestampToUTC(storageMetadata.getUpdatedTimeMillis() / 1000));
        createMap.putString(KEY_MD5_HASH, storageMetadata.getMd5Hash());
        if (storageMetadata.getCacheControl() != null && storageMetadata.getCacheControl().length() > 0) {
            createMap.putString(KEY_CACHE_CONTROL, storageMetadata.getCacheControl());
        }
        if (storageMetadata.getContentLanguage() != null && storageMetadata.getContentLanguage().length() > 0) {
            createMap.putString(KEY_CONTENT_LANG, storageMetadata.getContentLanguage());
        }
        if (storageMetadata.getContentDisposition() != null && storageMetadata.getContentDisposition().length() > 0) {
            createMap.putString(KEY_CONTENT_DISPOSITION, storageMetadata.getContentDisposition());
        }
        if (storageMetadata.getContentEncoding() != null && storageMetadata.getContentEncoding().length() > 0) {
            createMap.putString(KEY_CONTENT_ENCODING, storageMetadata.getContentEncoding());
        }
        if (storageMetadata.getContentType() != null && storageMetadata.getContentType().length() > 0) {
            createMap.putString("contentType", storageMetadata.getContentType());
        }
        if (storageMetadata.getCustomMetadataKeys().size() > 0) {
            WritableMap createMap2 = Arguments.createMap();
            for (String next : storageMetadata.getCustomMetadataKeys()) {
                createMap2.putString(next, storageMetadata.getCustomMetadata(next));
            }
            createMap.putMap(KEY_CUSTOM_META, createMap2);
        }
        return createMap;
    }

    static WritableMap getListResultAsMap(ListResult listResult) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("nextPageToken", listResult.getPageToken());
        WritableArray createArray = Arguments.createArray();
        WritableArray createArray2 = Arguments.createArray();
        for (StorageReference path : listResult.getItems()) {
            createArray.pushString(path.getPath());
        }
        for (StorageReference path2 : listResult.getPrefixes()) {
            createArray2.pushString(path2.getPath());
        }
        createMap.putArray(FirebaseAnalytics.Param.ITEMS, createArray);
        createMap.putArray("prefixes", createArray2);
        return createMap;
    }

    static String[] getExceptionCodeAndMessage(@Nullable Exception exc) {
        String str;
        String str2;
        String str3 = "unknown";
        if (exc != null) {
            str = exc.getMessage();
            if (exc instanceof StorageException) {
                StorageException storageException = (StorageException) exc;
                Throwable cause = storageException.getCause();
                int errorCode = storageException.getErrorCode();
                if (errorCode == -13040) {
                    str2 = "cancelled";
                    str = "User cancelled the operation.";
                } else if (errorCode == -13031) {
                    str2 = CODE_NON_MATCHING_CHECKSUM;
                    str = "File on the client does not match the checksum of the file received by the server.";
                } else if (errorCode == -13030) {
                    str2 = CODE_RETRY_LIMIT_EXCEEDED;
                    str = "The maximum time limit on an operation (upload, download, delete, etc.) has been exceeded.";
                } else if (errorCode == -13021) {
                    str2 = CODE_UNAUTHORIZED;
                    str = "User is not authorized to perform the desired action.";
                } else if (errorCode != -13020) {
                    switch (errorCode) {
                        case StorageException.ERROR_QUOTA_EXCEEDED:
                            str2 = CODE_QUOTA_EXCEEDED;
                            str = "Quota on your Firebase Storage bucket has been exceeded.";
                            break;
                        case StorageException.ERROR_PROJECT_NOT_FOUND:
                            str2 = CODE_PROJECT_NOT_FOUND;
                            str = "No project is configured for Firebase Storage.";
                            break;
                        case StorageException.ERROR_BUCKET_NOT_FOUND:
                            str2 = CODE_BUCKET_NOT_FOUND;
                            str = "No bucket is configured for Firebase Storage.";
                            break;
                        case StorageException.ERROR_OBJECT_NOT_FOUND:
                            str = "No object exists at the desired reference.";
                            str2 = CODE_OBJECT_NOT_FOUND;
                            break;
                        default:
                            str2 = str3;
                            break;
                    }
                } else {
                    str2 = CODE_UNAUTHENTICATED;
                    str = "User is unauthenticated. Authenticate and try again.";
                }
                if (str2.equals(str3) && cause != null) {
                    if (cause.getMessage().contains("No such file or directory")) {
                        str3 = CODE_FILE_NOT_FOUND;
                        str = "The local file specified does not exist on the device.";
                    } else if (cause.getMessage().contains("Not Found.  Could not get object")) {
                        str = "No object exists at the desired reference.";
                        str3 = CODE_OBJECT_NOT_FOUND;
                    } else {
                        str = cause.getMessage();
                    }
                }
                str3 = str2;
            }
        } else {
            str = "An unknown error has occurred.";
        }
        return new String[]{str3, str};
    }

    static void promiseRejectStorageException(Promise promise, @Nullable Exception exc) {
        String[] exceptionCodeAndMessage = getExceptionCodeAndMessage(exc);
        ReactNativeFirebaseModule.rejectPromiseWithCodeAndMessage(promise, exceptionCodeAndMessage[0], exceptionCodeAndMessage[1]);
    }

    static boolean isExternalStorageWritable() {
        boolean z;
        boolean z2;
        String externalStorageState = Environment.getExternalStorageState();
        if ("mounted".equals(externalStorageState)) {
            z2 = true;
        } else if ("mounted_ro".equals(externalStorageState)) {
            z = false;
            z2 = true;
            if (!z2 && z) {
                return true;
            }
        } else {
            z2 = false;
        }
        z = z2;
        return !z2 ? false : false;
    }
}

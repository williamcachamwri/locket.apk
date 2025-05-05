package io.invertase.firebase.firestore;

import android.util.Base64;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.Blob;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.MetadataChanges;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SnapshotMetadata;
import io.invertase.firebase.common.RCTConvertFirebase;
import io.sentry.android.core.SentryLogcatAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nullable;

public class ReactNativeFirebaseFirestoreSerialize {
    private static final String CHANGE_ADDED = "a";
    private static final String CHANGE_MODIFIED = "m";
    private static final String CHANGE_REMOVED = "r";
    private static final int INT_ARRAY = 10;
    private static final int INT_BLOB = 14;
    private static final int INT_BOOLEAN_FALSE = 6;
    private static final int INT_BOOLEAN_TRUE = 5;
    private static final int INT_DOCUMENTID = 4;
    private static final int INT_DOUBLE = 7;
    private static final int INT_FIELDVALUE = 15;
    private static final int INT_GEOPOINT = 12;
    private static final int INT_INTEGER = 17;
    private static final int INT_NAN = 0;
    private static final int INT_NEGATIVE_INFINITY = 1;
    private static final int INT_NEGATIVE_ZERO = 18;
    private static final int INT_NULL = 3;
    private static final int INT_OBJECT = 16;
    private static final int INT_POSITIVE_INFINITY = 2;
    private static final int INT_REFERENCE = 11;
    private static final int INT_STRING = 8;
    private static final int INT_STRING_EMPTY = 9;
    private static final int INT_TIMESTAMP = 13;
    private static final int INT_UNKNOWN = -999;
    private static final String KEY_CHANGES = "changes";
    private static final String KEY_DATA = "data";
    private static final String KEY_DOCUMENTS = "documents";
    private static final String KEY_DOC_CHANGE_DOCUMENT = "doc";
    private static final String KEY_DOC_CHANGE_NEW_INDEX = "ni";
    private static final String KEY_DOC_CHANGE_OLD_INDEX = "oi";
    private static final String KEY_DOC_CHANGE_TYPE = "type";
    private static final String KEY_EXISTS = "exists";
    private static final String KEY_META = "metadata";
    private static final String KEY_OPTIONS = "options";
    private static final String KEY_PATH = "path";
    private static final String TAG = "FirestoreSerialize";
    private static final String TYPE = "type";

    /* access modifiers changed from: package-private */
    public static WritableMap snapshotToWritableMap(String str, String str2, DocumentSnapshot documentSnapshot) {
        WritableArray createArray = Arguments.createArray();
        WritableMap createMap = Arguments.createMap();
        SnapshotMetadata metadata = documentSnapshot.getMetadata();
        createArray.pushBoolean(metadata.isFromCache());
        createArray.pushBoolean(metadata.hasPendingWrites());
        createMap.putArray("metadata", createArray);
        createMap.putString(KEY_PATH, documentSnapshot.getReference().getPath());
        createMap.putBoolean(KEY_EXISTS, documentSnapshot.exists());
        DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior = ReactNativeFirebaseFirestoreCommon.getServerTimestampBehavior(str, str2);
        if (documentSnapshot.exists() && documentSnapshot.getData(serverTimestampBehavior) != null) {
            createMap.putMap("data", objectMapToWritable(documentSnapshot.getData(serverTimestampBehavior)));
        }
        return createMap;
    }

    /* access modifiers changed from: package-private */
    public static WritableMap snapshotToWritableMap(String str, String str2, String str3, QuerySnapshot querySnapshot, @Nullable MetadataChanges metadataChanges) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("source", str3);
        WritableArray createArray = Arguments.createArray();
        WritableArray createArray2 = Arguments.createArray();
        List<DocumentChange> documentChanges = querySnapshot.getDocumentChanges();
        if (metadataChanges == null || metadataChanges == MetadataChanges.EXCLUDE) {
            createMap.putBoolean("excludesMetadataChanges", true);
            createMap.putArray(KEY_CHANGES, documentChangesToWritableArray(str, str2, documentChanges, (List<DocumentChange>) null));
        } else {
            createMap.putBoolean("excludesMetadataChanges", false);
            createMap.putArray(KEY_CHANGES, documentChangesToWritableArray(str, str2, querySnapshot.getDocumentChanges(MetadataChanges.INCLUDE), documentChanges));
        }
        SnapshotMetadata metadata = querySnapshot.getMetadata();
        for (DocumentSnapshot snapshotToWritableMap : querySnapshot.getDocuments()) {
            createArray2.pushMap(snapshotToWritableMap(str, str2, snapshotToWritableMap));
        }
        createMap.putArray(KEY_DOCUMENTS, createArray2);
        createArray.pushBoolean(metadata.isFromCache());
        createArray.pushBoolean(metadata.hasPendingWrites());
        createMap.putArray("metadata", createArray);
        return createMap;
    }

    private static WritableArray documentChangesToWritableArray(String str, String str2, List<DocumentChange> list, @Nullable List<DocumentChange> list2) {
        boolean z;
        WritableArray createArray = Arguments.createArray();
        boolean z2 = list2 != null;
        for (DocumentChange next : list) {
            if (z2) {
                int hashCode = next.hashCode();
                DocumentChange documentChange = null;
                for (DocumentChange next2 : list2) {
                    if (next2.hashCode() == hashCode) {
                        documentChange = next2;
                    }
                }
                if (documentChange == null) {
                    z = true;
                    createArray.pushMap(documentChangeToWritableMap(str, str2, next, z));
                }
            }
            z = false;
            createArray.pushMap(documentChangeToWritableMap(str, str2, next, z));
        }
        return createArray;
    }

    private static WritableMap documentChangeToWritableMap(String str, String str2, DocumentChange documentChange, boolean z) {
        WritableMap createMap = Arguments.createMap();
        createMap.putBoolean("isMetadataChange", z);
        int i = AnonymousClass1.$SwitchMap$com$google$firebase$firestore$DocumentChange$Type[documentChange.getType().ordinal()];
        if (i == 1) {
            createMap.putString("type", "a");
        } else if (i == 2) {
            createMap.putString("type", CHANGE_MODIFIED);
        } else if (i == 3) {
            createMap.putString("type", CHANGE_REMOVED);
        }
        createMap.putMap(KEY_DOC_CHANGE_DOCUMENT, snapshotToWritableMap(str, str2, documentChange.getDocument()));
        createMap.putInt(KEY_DOC_CHANGE_NEW_INDEX, documentChange.getNewIndex());
        createMap.putInt(KEY_DOC_CHANGE_OLD_INDEX, documentChange.getOldIndex());
        return createMap;
    }

    /* renamed from: io.invertase.firebase.firestore.ReactNativeFirebaseFirestoreSerialize$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$DocumentChange$Type;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.google.firebase.firestore.DocumentChange$Type[] r0 = com.google.firebase.firestore.DocumentChange.Type.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$firebase$firestore$DocumentChange$Type = r0
                com.google.firebase.firestore.DocumentChange$Type r1 = com.google.firebase.firestore.DocumentChange.Type.ADDED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$DocumentChange$Type     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.firebase.firestore.DocumentChange$Type r1 = com.google.firebase.firestore.DocumentChange.Type.MODIFIED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$DocumentChange$Type     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.firebase.firestore.DocumentChange$Type r1 = com.google.firebase.firestore.DocumentChange.Type.REMOVED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.invertase.firebase.firestore.ReactNativeFirebaseFirestoreSerialize.AnonymousClass1.<clinit>():void");
        }
    }

    private static WritableMap objectMapToWritable(Map<String, Object> map) {
        WritableMap createMap = Arguments.createMap();
        for (Map.Entry next : map.entrySet()) {
            createMap.putArray((String) next.getKey(), buildTypeMap(next.getValue()));
        }
        return createMap;
    }

    private static WritableArray objectArrayToWritable(Object[] objArr) {
        WritableArray createArray = Arguments.createArray();
        for (Object buildTypeMap : objArr) {
            createArray.pushArray(buildTypeMap(buildTypeMap));
        }
        return createArray;
    }

    private static WritableArray buildTypeMap(Object obj) {
        WritableArray createArray = Arguments.createArray();
        if (obj == null) {
            createArray.pushInt(3);
            return createArray;
        } else if (obj instanceof Boolean) {
            if (((Boolean) obj).booleanValue()) {
                createArray.pushInt(5);
            } else {
                createArray.pushInt(6);
            }
            return createArray;
        } else if (obj instanceof Integer) {
            createArray.pushInt(7);
            createArray.pushDouble(((Integer) obj).doubleValue());
            return createArray;
        } else if (obj instanceof Double) {
            Double d = (Double) obj;
            if (Double.isInfinite(d.doubleValue())) {
                if (d.equals(Double.valueOf(Double.NEGATIVE_INFINITY))) {
                    createArray.pushInt(1);
                    return createArray;
                } else if (d.equals(Double.valueOf(Double.POSITIVE_INFINITY))) {
                    createArray.pushInt(2);
                    return createArray;
                }
            }
            if (Double.isNaN(d.doubleValue())) {
                createArray.pushInt(0);
                return createArray;
            }
            createArray.pushInt(7);
            createArray.pushDouble(d.doubleValue());
            return createArray;
        } else if (obj instanceof Float) {
            createArray.pushInt(7);
            createArray.pushDouble(((Float) obj).doubleValue());
            return createArray;
        } else if (obj instanceof Long) {
            createArray.pushInt(7);
            createArray.pushDouble(((Long) obj).doubleValue());
            return createArray;
        } else if (obj instanceof String) {
            if (obj == "") {
                createArray.pushInt(9);
            } else {
                createArray.pushInt(8);
                createArray.pushString((String) obj);
            }
            return createArray;
        } else if (Map.class.isAssignableFrom(obj.getClass())) {
            createArray.pushInt(16);
            createArray.pushMap(objectMapToWritable((Map) obj));
            return createArray;
        } else if (List.class.isAssignableFrom(obj.getClass())) {
            createArray.pushInt(10);
            List list = (List) obj;
            createArray.pushArray(objectArrayToWritable(list.toArray(new Object[list.size()])));
            return createArray;
        } else if (obj instanceof DocumentReference) {
            createArray.pushInt(11);
            createArray.pushString(((DocumentReference) obj).getPath());
            return createArray;
        } else if (obj instanceof Timestamp) {
            createArray.pushInt(13);
            WritableArray createArray2 = Arguments.createArray();
            Timestamp timestamp = (Timestamp) obj;
            createArray2.pushDouble((double) timestamp.getSeconds());
            createArray2.pushInt(timestamp.getNanoseconds());
            createArray.pushArray(createArray2);
            return createArray;
        } else if (obj instanceof GeoPoint) {
            createArray.pushInt(12);
            WritableArray createArray3 = Arguments.createArray();
            GeoPoint geoPoint = (GeoPoint) obj;
            createArray3.pushDouble(geoPoint.getLatitude());
            createArray3.pushDouble(geoPoint.getLongitude());
            createArray.pushArray(createArray3);
            return createArray;
        } else if (obj instanceof Blob) {
            createArray.pushInt(14);
            createArray.pushString(Base64.encodeToString(((Blob) obj).toBytes(), 2));
            return createArray;
        } else {
            SentryLogcatAdapter.w(TAG, "Unknown object of type " + obj.getClass());
            createArray.pushInt(INT_UNKNOWN);
            return createArray;
        }
    }

    public static Map<String, Object> parseReadableMap(FirebaseFirestore firebaseFirestore, @Nullable ReadableMap readableMap) {
        HashMap hashMap = new HashMap();
        if (readableMap == null) {
            return hashMap;
        }
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            hashMap.put(nextKey, parseTypeMap(firebaseFirestore, readableMap.getArray(nextKey)));
        }
        return hashMap;
    }

    static List<Object> parseReadableArray(FirebaseFirestore firebaseFirestore, @Nullable ReadableArray readableArray) {
        ArrayList arrayList = new ArrayList();
        if (readableArray == null) {
            return arrayList;
        }
        for (int i = 0; i < readableArray.size(); i++) {
            arrayList.add(parseTypeMap(firebaseFirestore, readableArray.getArray(i)));
        }
        return arrayList;
    }

    static Object parseTypeMap(FirebaseFirestore firebaseFirestore, ReadableArray readableArray) {
        switch (readableArray.getInt(0)) {
            case 0:
                return Double.valueOf(Double.NaN);
            case 1:
                return Double.valueOf(Double.NEGATIVE_INFINITY);
            case 2:
                return Double.valueOf(Double.POSITIVE_INFINITY);
            case 4:
                return FieldPath.documentId();
            case 5:
                return true;
            case 6:
                return false;
            case 7:
                return Double.valueOf(readableArray.getDouble(1));
            case 8:
                return readableArray.getString(1);
            case 9:
                return "";
            case 10:
                return parseReadableArray(firebaseFirestore, readableArray.getArray(1));
            case 11:
                return firebaseFirestore.document((String) Objects.requireNonNull(readableArray.getString(1)));
            case 12:
                ReadableArray array = readableArray.getArray(1);
                return new GeoPoint(((ReadableArray) Objects.requireNonNull(array)).getDouble(0), array.getDouble(1));
            case 13:
                ReadableArray array2 = readableArray.getArray(1);
                return new Timestamp((long) ((ReadableArray) Objects.requireNonNull(array2)).getDouble(0), array2.getInt(1));
            case 14:
                return Blob.fromBytes(Base64.decode(readableArray.getString(1), 2));
            case 15:
                ReadableArray array3 = readableArray.getArray(1);
                String string = ((ReadableArray) Objects.requireNonNull(array3)).getString(0);
                if (((String) Objects.requireNonNull(string)).equals("timestamp")) {
                    return FieldValue.serverTimestamp();
                }
                if (((String) Objects.requireNonNull(string)).equals("increment")) {
                    return FieldValue.increment(array3.getDouble(1));
                }
                if (((String) Objects.requireNonNull(string)).equals("delete")) {
                    return FieldValue.delete();
                }
                if (((String) Objects.requireNonNull(string)).equals("array_union")) {
                    return FieldValue.arrayUnion(parseReadableArray(firebaseFirestore, array3.getArray(1)).toArray());
                }
                if (((String) Objects.requireNonNull(string)).equals("array_remove")) {
                    return FieldValue.arrayRemove(parseReadableArray(firebaseFirestore, array3.getArray(1)).toArray());
                }
                break;
            case 16:
                break;
            case 17:
                return Long.valueOf((long) readableArray.getDouble(1));
            case 18:
                return Double.valueOf(-0.0d);
            default:
                return null;
        }
        return parseReadableMap(firebaseFirestore, readableArray.getMap(1));
    }

    /* access modifiers changed from: package-private */
    public static List<Object> parseDocumentBatches(FirebaseFirestore firebaseFirestore, ReadableArray readableArray) {
        ArrayList arrayList = new ArrayList(readableArray.size());
        for (int i = 0; i < readableArray.size(); i++) {
            HashMap hashMap = new HashMap();
            ReadableMap map = readableArray.getMap(i);
            hashMap.put("type", map.getString("type"));
            hashMap.put(KEY_PATH, map.getString(KEY_PATH));
            if (map.hasKey("data")) {
                hashMap.put("data", parseReadableMap(firebaseFirestore, map.getMap("data")));
            }
            if (map.hasKey(KEY_OPTIONS)) {
                hashMap.put(KEY_OPTIONS, RCTConvertFirebase.toHashMap(map.getMap(KEY_OPTIONS)));
            }
            arrayList.add(hashMap);
        }
        return arrayList;
    }
}

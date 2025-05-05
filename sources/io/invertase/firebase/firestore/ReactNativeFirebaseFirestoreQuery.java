package io.invertase.firebase.firestore;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.MetadataChanges;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;
import io.invertase.firebase.common.RCTConvertFirebase;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;

public class ReactNativeFirebaseFirestoreQuery {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    String appName;
    String databaseId;
    Query query;

    ReactNativeFirebaseFirestoreQuery(String str, String str2, Query query2, ReadableArray readableArray, ReadableArray readableArray2, ReadableMap readableMap) {
        this.appName = str;
        this.query = query2;
        applyFilters(readableArray);
        applyOrders(readableArray2);
        applyOptions(readableMap);
    }

    public Task<WritableMap> get(Executor executor, Source source) {
        return Tasks.call(executor, new ReactNativeFirebaseFirestoreQuery$$ExternalSyntheticLambda0(this, source));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ WritableMap lambda$get$0(Source source) throws Exception {
        return ReactNativeFirebaseFirestoreSerialize.snapshotToWritableMap(this.appName, this.databaseId, "get", (QuerySnapshot) Tasks.await(this.query.get(source)), (MetadataChanges) null);
    }

    private void applyFilters(ReadableArray readableArray) {
        for (int i = 0; i < readableArray.size(); i++) {
            ReadableMap map = readableArray.getMap(i);
            if (map.hasKey("fieldPath")) {
                FieldPath of = FieldPath.of((String[]) Objects.requireNonNull((String[]) ((ReadableArray) Objects.requireNonNull(((ReadableMap) Objects.requireNonNull(map)).getArray("fieldPath"))).toArrayList().toArray(new String[0])));
                String string = map.getString("operator");
                Object parseTypeMap = ReactNativeFirebaseFirestoreSerialize.parseTypeMap(this.query.getFirestore(), (ReadableArray) Objects.requireNonNull(map.getArray("value")));
                String str = (String) Objects.requireNonNull(string);
                str.hashCode();
                char c = 65535;
                switch (str.hashCode()) {
                    case -2081783184:
                        if (str.equals("LESS_THAN_OR_EQUAL")) {
                            c = 0;
                            break;
                        }
                        break;
                    case -1986339279:
                        if (str.equals("NOT_IN")) {
                            c = 1;
                            break;
                        }
                        break;
                    case -1112834937:
                        if (str.equals("LESS_THAN")) {
                            c = 2;
                            break;
                        }
                        break;
                    case -466714638:
                        if (str.equals("ARRAY_CONTAINS_ANY")) {
                            c = 3;
                            break;
                        }
                        break;
                    case 2341:
                        if (str.equals("IN")) {
                            c = 4;
                            break;
                        }
                        break;
                    case 66219796:
                        if (str.equals("EQUAL")) {
                            c = 5;
                            break;
                        }
                        break;
                    case 67210597:
                        if (str.equals("ARRAY_CONTAINS")) {
                            c = 6;
                            break;
                        }
                        break;
                    case 972152550:
                        if (str.equals("GREATER_THAN")) {
                            c = 7;
                            break;
                        }
                        break;
                    case 989027057:
                        if (str.equals("GREATER_THAN_OR_EQUAL")) {
                            c = 8;
                            break;
                        }
                        break;
                    case 1022422664:
                        if (str.equals("NOT_EQUAL")) {
                            c = 9;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        this.query = this.query.whereLessThanOrEqualTo((FieldPath) Objects.requireNonNull(of), Objects.requireNonNull(parseTypeMap));
                        break;
                    case 1:
                        this.query = this.query.whereNotIn((FieldPath) Objects.requireNonNull(of), (List<? extends Object>) (List) Objects.requireNonNull((List) parseTypeMap));
                        break;
                    case 2:
                        this.query = this.query.whereLessThan((FieldPath) Objects.requireNonNull(of), Objects.requireNonNull(parseTypeMap));
                        break;
                    case 3:
                        this.query = this.query.whereArrayContainsAny((FieldPath) Objects.requireNonNull(of), (List<? extends Object>) (List) Objects.requireNonNull((List) parseTypeMap));
                        break;
                    case 4:
                        this.query = this.query.whereIn((FieldPath) Objects.requireNonNull(of), (List<? extends Object>) (List) Objects.requireNonNull((List) parseTypeMap));
                        break;
                    case 5:
                        this.query = this.query.whereEqualTo((FieldPath) Objects.requireNonNull(of), parseTypeMap);
                        break;
                    case 6:
                        this.query = this.query.whereArrayContains((FieldPath) Objects.requireNonNull(of), Objects.requireNonNull(parseTypeMap));
                        break;
                    case 7:
                        this.query = this.query.whereGreaterThan((FieldPath) Objects.requireNonNull(of), Objects.requireNonNull(parseTypeMap));
                        break;
                    case 8:
                        this.query = this.query.whereGreaterThanOrEqualTo((FieldPath) Objects.requireNonNull(of), Objects.requireNonNull(parseTypeMap));
                        break;
                    case 9:
                        this.query = this.query.whereNotEqualTo((FieldPath) Objects.requireNonNull(of), parseTypeMap);
                        break;
                }
            } else if (map.hasKey("operator") && map.hasKey("queries")) {
                this.query = this.query.where(applyFilterQueries(map));
            }
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00d7, code lost:
        if (r1.equals("LESS_THAN_OR_EQUAL") == false) goto L_0x0068;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.firebase.firestore.Filter applyFilterQueries(com.facebook.react.bridge.ReadableMap r8) {
        /*
            r7 = this;
            java.lang.String r0 = "fieldPath"
            boolean r1 = r8.hasKey(r0)
            java.lang.String r2 = "operator"
            r3 = 0
            if (r1 == 0) goto L_0x011d
            java.lang.Object r1 = java.util.Objects.requireNonNull(r8)
            com.facebook.react.bridge.ReadableMap r1 = (com.facebook.react.bridge.ReadableMap) r1
            java.lang.String r1 = r1.getString(r2)
            java.lang.Object r1 = java.util.Objects.requireNonNull(r1)
            java.lang.String r1 = (java.lang.String) r1
            com.facebook.react.bridge.ReadableMap r0 = r8.getMap(r0)
            java.lang.Object r0 = java.util.Objects.requireNonNull(r0)
            com.facebook.react.bridge.ReadableMap r0 = (com.facebook.react.bridge.ReadableMap) r0
            java.lang.String r2 = "_segments"
            com.facebook.react.bridge.ReadableArray r0 = r0.getArray(r2)
            java.lang.Object r0 = java.util.Objects.requireNonNull(r0)
            com.facebook.react.bridge.ReadableArray r0 = (com.facebook.react.bridge.ReadableArray) r0
            int r2 = r0.size()
            java.lang.String[] r4 = new java.lang.String[r2]
            r5 = r3
        L_0x0038:
            if (r5 >= r2) goto L_0x0043
            java.lang.String r6 = r0.getString(r5)
            r4[r5] = r6
            int r5 = r5 + 1
            goto L_0x0038
        L_0x0043:
            com.google.firebase.firestore.FieldPath r0 = com.google.firebase.firestore.FieldPath.of(r4)
            java.lang.String r2 = "value"
            com.facebook.react.bridge.ReadableArray r8 = r8.getArray(r2)
            com.google.firebase.firestore.Query r2 = r7.query
            com.google.firebase.firestore.FirebaseFirestore r2 = r2.getFirestore()
            java.lang.Object r8 = java.util.Objects.requireNonNull(r8)
            com.facebook.react.bridge.ReadableArray r8 = (com.facebook.react.bridge.ReadableArray) r8
            java.lang.Object r8 = io.invertase.firebase.firestore.ReactNativeFirebaseFirestoreSerialize.parseTypeMap(r2, r8)
            r1.hashCode()
            int r2 = r1.hashCode()
            r4 = -1
            switch(r2) {
                case -2081783184: goto L_0x00d1;
                case -1986339279: goto L_0x00c6;
                case -1112834937: goto L_0x00bb;
                case -466714638: goto L_0x00b0;
                case 2341: goto L_0x00a5;
                case 66219796: goto L_0x009a;
                case 67210597: goto L_0x008f;
                case 972152550: goto L_0x0084;
                case 989027057: goto L_0x0078;
                case 1022422664: goto L_0x006b;
                default: goto L_0x0068;
            }
        L_0x0068:
            r3 = r4
            goto L_0x00da
        L_0x006b:
            java.lang.String r2 = "NOT_EQUAL"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x0074
            goto L_0x0068
        L_0x0074:
            r3 = 9
            goto L_0x00da
        L_0x0078:
            java.lang.String r2 = "GREATER_THAN_OR_EQUAL"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x0081
            goto L_0x0068
        L_0x0081:
            r3 = 8
            goto L_0x00da
        L_0x0084:
            java.lang.String r2 = "GREATER_THAN"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x008d
            goto L_0x0068
        L_0x008d:
            r3 = 7
            goto L_0x00da
        L_0x008f:
            java.lang.String r2 = "ARRAY_CONTAINS"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x0098
            goto L_0x0068
        L_0x0098:
            r3 = 6
            goto L_0x00da
        L_0x009a:
            java.lang.String r2 = "EQUAL"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x00a3
            goto L_0x0068
        L_0x00a3:
            r3 = 5
            goto L_0x00da
        L_0x00a5:
            java.lang.String r2 = "IN"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x00ae
            goto L_0x0068
        L_0x00ae:
            r3 = 4
            goto L_0x00da
        L_0x00b0:
            java.lang.String r2 = "ARRAY_CONTAINS_ANY"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x00b9
            goto L_0x0068
        L_0x00b9:
            r3 = 3
            goto L_0x00da
        L_0x00bb:
            java.lang.String r2 = "LESS_THAN"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x00c4
            goto L_0x0068
        L_0x00c4:
            r3 = 2
            goto L_0x00da
        L_0x00c6:
            java.lang.String r2 = "NOT_IN"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x00cf
            goto L_0x0068
        L_0x00cf:
            r3 = 1
            goto L_0x00da
        L_0x00d1:
            java.lang.String r2 = "LESS_THAN_OR_EQUAL"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x00da
            goto L_0x0068
        L_0x00da:
            switch(r3) {
                case 0: goto L_0x0118;
                case 1: goto L_0x0111;
                case 2: goto L_0x010c;
                case 3: goto L_0x0105;
                case 4: goto L_0x00fe;
                case 5: goto L_0x00f9;
                case 6: goto L_0x00f4;
                case 7: goto L_0x00ef;
                case 8: goto L_0x00ea;
                case 9: goto L_0x00e5;
                default: goto L_0x00dd;
            }
        L_0x00dd:
            java.lang.Error r8 = new java.lang.Error
            java.lang.String r0 = "Invalid operator"
            r8.<init>(r0)
            throw r8
        L_0x00e5:
            com.google.firebase.firestore.Filter r8 = com.google.firebase.firestore.Filter.notEqualTo((com.google.firebase.firestore.FieldPath) r0, (java.lang.Object) r8)
            return r8
        L_0x00ea:
            com.google.firebase.firestore.Filter r8 = com.google.firebase.firestore.Filter.greaterThanOrEqualTo((com.google.firebase.firestore.FieldPath) r0, (java.lang.Object) r8)
            return r8
        L_0x00ef:
            com.google.firebase.firestore.Filter r8 = com.google.firebase.firestore.Filter.greaterThan((com.google.firebase.firestore.FieldPath) r0, (java.lang.Object) r8)
            return r8
        L_0x00f4:
            com.google.firebase.firestore.Filter r8 = com.google.firebase.firestore.Filter.arrayContains((com.google.firebase.firestore.FieldPath) r0, (java.lang.Object) r8)
            return r8
        L_0x00f9:
            com.google.firebase.firestore.Filter r8 = com.google.firebase.firestore.Filter.equalTo((com.google.firebase.firestore.FieldPath) r0, (java.lang.Object) r8)
            return r8
        L_0x00fe:
            java.util.List r8 = (java.util.List) r8
            com.google.firebase.firestore.Filter r8 = com.google.firebase.firestore.Filter.inArray((com.google.firebase.firestore.FieldPath) r0, (java.util.List<? extends java.lang.Object>) r8)
            return r8
        L_0x0105:
            java.util.List r8 = (java.util.List) r8
            com.google.firebase.firestore.Filter r8 = com.google.firebase.firestore.Filter.arrayContainsAny((com.google.firebase.firestore.FieldPath) r0, (java.util.List<? extends java.lang.Object>) r8)
            return r8
        L_0x010c:
            com.google.firebase.firestore.Filter r8 = com.google.firebase.firestore.Filter.lessThan((com.google.firebase.firestore.FieldPath) r0, (java.lang.Object) r8)
            return r8
        L_0x0111:
            java.util.List r8 = (java.util.List) r8
            com.google.firebase.firestore.Filter r8 = com.google.firebase.firestore.Filter.notInArray((com.google.firebase.firestore.FieldPath) r0, (java.util.List<? extends java.lang.Object>) r8)
            return r8
        L_0x0118:
            com.google.firebase.firestore.Filter r8 = com.google.firebase.firestore.Filter.lessThanOrEqualTo((com.google.firebase.firestore.FieldPath) r0, (java.lang.Object) r8)
            return r8
        L_0x011d:
            java.lang.Object r0 = java.util.Objects.requireNonNull(r8)
            com.facebook.react.bridge.ReadableMap r0 = (com.facebook.react.bridge.ReadableMap) r0
            java.lang.String r0 = r0.getString(r2)
            java.lang.Object r8 = java.util.Objects.requireNonNull(r8)
            com.facebook.react.bridge.ReadableMap r8 = (com.facebook.react.bridge.ReadableMap) r8
            java.lang.String r1 = "queries"
            com.facebook.react.bridge.ReadableArray r8 = r8.getArray(r1)
            java.lang.Object r8 = java.util.Objects.requireNonNull(r8)
            com.facebook.react.bridge.ReadableArray r8 = (com.facebook.react.bridge.ReadableArray) r8
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            int r2 = r8.size()
            r4 = r3
        L_0x0143:
            if (r4 >= r2) goto L_0x0153
            com.facebook.react.bridge.ReadableMap r5 = r8.getMap(r4)
            com.google.firebase.firestore.Filter r5 = r7.applyFilterQueries(r5)
            r1.add(r5)
            int r4 = r4 + 1
            goto L_0x0143
        L_0x0153:
            java.lang.String r8 = "AND"
            boolean r8 = r0.equals(r8)
            if (r8 == 0) goto L_0x0168
            com.google.firebase.firestore.Filter[] r8 = new com.google.firebase.firestore.Filter[r3]
            java.lang.Object[] r8 = r1.toArray(r8)
            com.google.firebase.firestore.Filter[] r8 = (com.google.firebase.firestore.Filter[]) r8
            com.google.firebase.firestore.Filter r8 = com.google.firebase.firestore.Filter.and(r8)
            return r8
        L_0x0168:
            java.lang.String r8 = "OR"
            boolean r8 = r0.equals(r8)
            if (r8 == 0) goto L_0x017d
            com.google.firebase.firestore.Filter[] r8 = new com.google.firebase.firestore.Filter[r3]
            java.lang.Object[] r8 = r1.toArray(r8)
            com.google.firebase.firestore.Filter[] r8 = (com.google.firebase.firestore.Filter[]) r8
            com.google.firebase.firestore.Filter r8 = com.google.firebase.firestore.Filter.or(r8)
            return r8
        L_0x017d:
            java.lang.Error r8 = new java.lang.Error
            java.lang.String r0 = "Missing 'Filter' instance return"
            r8.<init>(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.invertase.firebase.firestore.ReactNativeFirebaseFirestoreQuery.applyFilterQueries(com.facebook.react.bridge.ReadableMap):com.google.firebase.firestore.Filter");
    }

    private void applyOrders(ReadableArray readableArray) {
        Iterator<Object> it = RCTConvertFirebase.toArrayList(readableArray).iterator();
        while (it.hasNext()) {
            Map map = (Map) it.next();
            if (map.get("fieldPath") instanceof List) {
                this.query = this.query.orderBy((FieldPath) Objects.requireNonNull(FieldPath.of((String[]) ((ArrayList) map.get("fieldPath")).toArray(new String[0]))), Query.Direction.valueOf((String) map.get("direction")));
            } else {
                this.query = this.query.orderBy((String) Objects.requireNonNull((String) map.get("fieldPath")), Query.Direction.valueOf((String) map.get("direction")));
            }
        }
    }

    private void applyOptions(ReadableMap readableMap) {
        if (readableMap.hasKey("limit")) {
            this.query = this.query.limit((long) readableMap.getInt("limit"));
        }
        if (readableMap.hasKey("limitToLast")) {
            this.query = this.query.limitToLast((long) readableMap.getInt("limitToLast"));
        }
        if (readableMap.hasKey("startAt")) {
            this.query = this.query.startAt((Object[]) Objects.requireNonNull(ReactNativeFirebaseFirestoreSerialize.parseReadableArray(this.query.getFirestore(), readableMap.getArray("startAt")).toArray()));
        }
        if (readableMap.hasKey("startAfter")) {
            this.query = this.query.startAfter((Object[]) Objects.requireNonNull(ReactNativeFirebaseFirestoreSerialize.parseReadableArray(this.query.getFirestore(), readableMap.getArray("startAfter")).toArray()));
        }
        if (readableMap.hasKey("endAt")) {
            this.query = this.query.endAt((Object[]) Objects.requireNonNull(ReactNativeFirebaseFirestoreSerialize.parseReadableArray(this.query.getFirestore(), readableMap.getArray("endAt")).toArray()));
        }
        if (readableMap.hasKey("endBefore")) {
            this.query = this.query.endBefore((Object[]) Objects.requireNonNull(ReactNativeFirebaseFirestoreSerialize.parseReadableArray(this.query.getFirestore(), readableMap.getArray("endBefore")).toArray()));
        }
    }
}

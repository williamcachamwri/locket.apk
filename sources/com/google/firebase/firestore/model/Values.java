package com.google.firebase.firestore.model;

import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Util;
import com.google.firestore.v1.ArrayValue;
import com.google.firestore.v1.ArrayValueOrBuilder;
import com.google.firestore.v1.MapValue;
import com.google.firestore.v1.Value;
import com.google.protobuf.ByteString;
import com.google.protobuf.NullValue;
import com.google.protobuf.Timestamp;
import com.google.type.LatLng;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Values {
    public static final Value MAX_VALUE;
    public static final Value MAX_VALUE_TYPE;
    public static Value MIN_ARRAY = ((Value) Value.newBuilder().setArrayValue(ArrayValue.getDefaultInstance()).build());
    public static Value MIN_BOOLEAN = ((Value) Value.newBuilder().setBooleanValue(false).build());
    public static Value MIN_BYTES = ((Value) Value.newBuilder().setBytesValue(ByteString.EMPTY).build());
    public static Value MIN_GEO_POINT = ((Value) Value.newBuilder().setGeoPointValue(LatLng.newBuilder().setLatitude(-90.0d).setLongitude(-180.0d)).build());
    public static Value MIN_MAP = ((Value) Value.newBuilder().setMapValue(MapValue.getDefaultInstance()).build());
    public static Value MIN_NUMBER = ((Value) Value.newBuilder().setDoubleValue(Double.NaN).build());
    public static Value MIN_REFERENCE = refValue(DatabaseId.EMPTY, DocumentKey.empty());
    public static Value MIN_STRING = ((Value) Value.newBuilder().setStringValue("").build());
    public static Value MIN_TIMESTAMP = ((Value) Value.newBuilder().setTimestampValue(Timestamp.newBuilder().setSeconds(Long.MIN_VALUE)).build());
    public static final Value MIN_VALUE;
    private static final Value MIN_VECTOR_VALUE;
    public static final Value NAN_VALUE = ((Value) Value.newBuilder().setDoubleValue(Double.NaN).build());
    public static final Value NULL_VALUE;
    public static final String TYPE_KEY = "__type__";
    public static final int TYPE_ORDER_ARRAY = 9;
    public static final int TYPE_ORDER_BLOB = 6;
    public static final int TYPE_ORDER_BOOLEAN = 1;
    public static final int TYPE_ORDER_GEOPOINT = 8;
    public static final int TYPE_ORDER_MAP = 11;
    public static final int TYPE_ORDER_MAX_VALUE = Integer.MAX_VALUE;
    public static final int TYPE_ORDER_NULL = 0;
    public static final int TYPE_ORDER_NUMBER = 2;
    public static final int TYPE_ORDER_REFERENCE = 7;
    public static final int TYPE_ORDER_SERVER_TIMESTAMP = 4;
    public static final int TYPE_ORDER_STRING = 5;
    public static final int TYPE_ORDER_TIMESTAMP = 3;
    public static final int TYPE_ORDER_VECTOR = 10;
    public static final String VECTOR_MAP_VECTORS_KEY = "value";
    public static final Value VECTOR_VALUE_TYPE;

    static {
        Value value = (Value) Value.newBuilder().setNullValue(NullValue.NULL_VALUE).build();
        NULL_VALUE = value;
        MIN_VALUE = value;
        Value value2 = (Value) Value.newBuilder().setStringValue("__max__").build();
        MAX_VALUE_TYPE = value2;
        MAX_VALUE = (Value) Value.newBuilder().setMapValue(MapValue.newBuilder().putFields(TYPE_KEY, value2)).build();
        Value value3 = (Value) Value.newBuilder().setStringValue("__vector__").build();
        VECTOR_VALUE_TYPE = value3;
        MIN_VECTOR_VALUE = (Value) Value.newBuilder().setMapValue(MapValue.newBuilder().putFields(TYPE_KEY, value3).putFields("value", (Value) Value.newBuilder().setArrayValue(ArrayValue.newBuilder()).build())).build();
    }

    /* renamed from: com.google.firebase.firestore.model.Values$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase;

        /* JADX WARNING: Can't wrap try/catch for region: R(22:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|(3:21|22|24)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(24:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|24) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.google.firestore.v1.Value$ValueTypeCase[] r0 = com.google.firestore.v1.Value.ValueTypeCase.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase = r0
                com.google.firestore.v1.Value$ValueTypeCase r1 = com.google.firestore.v1.Value.ValueTypeCase.NULL_VALUE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.firestore.v1.Value$ValueTypeCase r1 = com.google.firestore.v1.Value.ValueTypeCase.BOOLEAN_VALUE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.firestore.v1.Value$ValueTypeCase r1 = com.google.firestore.v1.Value.ValueTypeCase.INTEGER_VALUE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.firestore.v1.Value$ValueTypeCase r1 = com.google.firestore.v1.Value.ValueTypeCase.DOUBLE_VALUE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.firestore.v1.Value$ValueTypeCase r1 = com.google.firestore.v1.Value.ValueTypeCase.TIMESTAMP_VALUE     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.firestore.v1.Value$ValueTypeCase r1 = com.google.firestore.v1.Value.ValueTypeCase.STRING_VALUE     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.google.firestore.v1.Value$ValueTypeCase r1 = com.google.firestore.v1.Value.ValueTypeCase.BYTES_VALUE     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.google.firestore.v1.Value$ValueTypeCase r1 = com.google.firestore.v1.Value.ValueTypeCase.REFERENCE_VALUE     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase     // Catch:{ NoSuchFieldError -> 0x006c }
                com.google.firestore.v1.Value$ValueTypeCase r1 = com.google.firestore.v1.Value.ValueTypeCase.GEO_POINT_VALUE     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.google.firestore.v1.Value$ValueTypeCase r1 = com.google.firestore.v1.Value.ValueTypeCase.ARRAY_VALUE     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase     // Catch:{ NoSuchFieldError -> 0x0084 }
                com.google.firestore.v1.Value$ValueTypeCase r1 = com.google.firestore.v1.Value.ValueTypeCase.MAP_VALUE     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.model.Values.AnonymousClass1.<clinit>():void");
        }
    }

    public static int typeOrder(Value value) {
        switch (AnonymousClass1.$SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[value.getValueTypeCase().ordinal()]) {
            case 1:
                return 0;
            case 2:
                return 1;
            case 3:
            case 4:
                return 2;
            case 5:
                return 3;
            case 6:
                return 5;
            case 7:
                return 6;
            case 8:
                return 7;
            case 9:
                return 8;
            case 10:
                return 9;
            case 11:
                if (ServerTimestamps.isServerTimestamp(value)) {
                    return 4;
                }
                if (isMaxValue(value)) {
                    return Integer.MAX_VALUE;
                }
                return isVectorValue(value) ? 10 : 11;
            default:
                throw Assert.fail("Invalid value type: " + value.getValueTypeCase(), new Object[0]);
        }
    }

    public static boolean equals(Value value, Value value2) {
        int typeOrder;
        if (value == value2) {
            return true;
        }
        if (value == null || value2 == null || (typeOrder = typeOrder(value)) != typeOrder(value2)) {
            return false;
        }
        if (typeOrder == 2) {
            return numberEquals(value, value2);
        }
        if (typeOrder == 4) {
            return ServerTimestamps.getLocalWriteTime(value).equals(ServerTimestamps.getLocalWriteTime(value2));
        }
        if (typeOrder == Integer.MAX_VALUE) {
            return true;
        }
        switch (typeOrder) {
            case 9:
                return arrayEquals(value, value2);
            case 10:
            case 11:
                return objectEquals(value, value2);
            default:
                return value.equals(value2);
        }
    }

    private static boolean numberEquals(Value value, Value value2) {
        if (value.getValueTypeCase() == Value.ValueTypeCase.INTEGER_VALUE && value2.getValueTypeCase() == Value.ValueTypeCase.INTEGER_VALUE) {
            if (value.getIntegerValue() == value2.getIntegerValue()) {
                return true;
            }
            return false;
        } else if (value.getValueTypeCase() != Value.ValueTypeCase.DOUBLE_VALUE || value2.getValueTypeCase() != Value.ValueTypeCase.DOUBLE_VALUE) {
            return false;
        } else {
            if (Double.doubleToLongBits(value.getDoubleValue()) == Double.doubleToLongBits(value2.getDoubleValue())) {
                return true;
            }
            return false;
        }
    }

    private static boolean arrayEquals(Value value, Value value2) {
        ArrayValue arrayValue = value.getArrayValue();
        ArrayValue arrayValue2 = value2.getArrayValue();
        if (arrayValue.getValuesCount() != arrayValue2.getValuesCount()) {
            return false;
        }
        for (int i = 0; i < arrayValue.getValuesCount(); i++) {
            if (!equals(arrayValue.getValues(i), arrayValue2.getValues(i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean objectEquals(Value value, Value value2) {
        MapValue mapValue = value.getMapValue();
        MapValue mapValue2 = value2.getMapValue();
        if (mapValue.getFieldsCount() != mapValue2.getFieldsCount()) {
            return false;
        }
        for (Map.Entry next : mapValue.getFieldsMap().entrySet()) {
            if (!equals((Value) next.getValue(), mapValue2.getFieldsMap().get(next.getKey()))) {
                return false;
            }
        }
        return true;
    }

    public static boolean contains(ArrayValueOrBuilder arrayValueOrBuilder, Value value) {
        for (Value equals : arrayValueOrBuilder.getValuesList()) {
            if (equals(equals, value)) {
                return true;
            }
        }
        return false;
    }

    public static int compare(Value value, Value value2) {
        int typeOrder = typeOrder(value);
        int typeOrder2 = typeOrder(value2);
        if (typeOrder != typeOrder2) {
            return Util.compareIntegers(typeOrder, typeOrder2);
        }
        if (typeOrder != Integer.MAX_VALUE) {
            switch (typeOrder) {
                case 0:
                    break;
                case 1:
                    return Util.compareBooleans(value.getBooleanValue(), value2.getBooleanValue());
                case 2:
                    return compareNumbers(value, value2);
                case 3:
                    return compareTimestamps(value.getTimestampValue(), value2.getTimestampValue());
                case 4:
                    return compareTimestamps(ServerTimestamps.getLocalWriteTime(value), ServerTimestamps.getLocalWriteTime(value2));
                case 5:
                    return value.getStringValue().compareTo(value2.getStringValue());
                case 6:
                    return Util.compareByteStrings(value.getBytesValue(), value2.getBytesValue());
                case 7:
                    return compareReferences(value.getReferenceValue(), value2.getReferenceValue());
                case 8:
                    return compareGeoPoints(value.getGeoPointValue(), value2.getGeoPointValue());
                case 9:
                    return compareArrays(value.getArrayValue(), value2.getArrayValue());
                case 10:
                    return compareVectors(value.getMapValue(), value2.getMapValue());
                case 11:
                    return compareMaps(value.getMapValue(), value2.getMapValue());
                default:
                    throw Assert.fail("Invalid value type: " + typeOrder, new Object[0]);
            }
        }
        return 0;
    }

    public static int lowerBoundCompare(Value value, boolean z, Value value2, boolean z2) {
        int compare = compare(value, value2);
        if (compare != 0) {
            return compare;
        }
        if (!z || z2) {
            return (z || !z2) ? 0 : 1;
        }
        return -1;
    }

    public static int upperBoundCompare(Value value, boolean z, Value value2, boolean z2) {
        int compare = compare(value, value2);
        if (compare != 0) {
            return compare;
        }
        if (!z || z2) {
            return (z || !z2) ? 0 : -1;
        }
        return 1;
    }

    private static int compareNumbers(Value value, Value value2) {
        if (value.getValueTypeCase() == Value.ValueTypeCase.DOUBLE_VALUE) {
            double doubleValue = value.getDoubleValue();
            if (value2.getValueTypeCase() == Value.ValueTypeCase.DOUBLE_VALUE) {
                return Util.compareDoubles(doubleValue, value2.getDoubleValue());
            }
            if (value2.getValueTypeCase() == Value.ValueTypeCase.INTEGER_VALUE) {
                return Util.compareMixed(doubleValue, value2.getIntegerValue());
            }
        } else if (value.getValueTypeCase() == Value.ValueTypeCase.INTEGER_VALUE) {
            long integerValue = value.getIntegerValue();
            if (value2.getValueTypeCase() == Value.ValueTypeCase.INTEGER_VALUE) {
                return Util.compareLongs(integerValue, value2.getIntegerValue());
            }
            if (value2.getValueTypeCase() == Value.ValueTypeCase.DOUBLE_VALUE) {
                return Util.compareMixed(value2.getDoubleValue(), integerValue) * -1;
            }
        }
        throw Assert.fail("Unexpected values: %s vs %s", value, value2);
    }

    private static int compareTimestamps(Timestamp timestamp, Timestamp timestamp2) {
        int compareLongs = Util.compareLongs(timestamp.getSeconds(), timestamp2.getSeconds());
        if (compareLongs != 0) {
            return compareLongs;
        }
        return Util.compareIntegers(timestamp.getNanos(), timestamp2.getNanos());
    }

    private static int compareReferences(String str, String str2) {
        String[] split = str.split("/", -1);
        String[] split2 = str2.split("/", -1);
        int min = Math.min(split.length, split2.length);
        for (int i = 0; i < min; i++) {
            int compareTo = split[i].compareTo(split2[i]);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return Util.compareIntegers(split.length, split2.length);
    }

    private static int compareGeoPoints(LatLng latLng, LatLng latLng2) {
        int compareDoubles = Util.compareDoubles(latLng.getLatitude(), latLng2.getLatitude());
        return compareDoubles == 0 ? Util.compareDoubles(latLng.getLongitude(), latLng2.getLongitude()) : compareDoubles;
    }

    private static int compareArrays(ArrayValue arrayValue, ArrayValue arrayValue2) {
        int min = Math.min(arrayValue.getValuesCount(), arrayValue2.getValuesCount());
        for (int i = 0; i < min; i++) {
            int compare = compare(arrayValue.getValues(i), arrayValue2.getValues(i));
            if (compare != 0) {
                return compare;
            }
        }
        return Util.compareIntegers(arrayValue.getValuesCount(), arrayValue2.getValuesCount());
    }

    private static int compareMaps(MapValue mapValue, MapValue mapValue2) {
        Iterator it = new TreeMap(mapValue.getFieldsMap()).entrySet().iterator();
        Iterator it2 = new TreeMap(mapValue2.getFieldsMap()).entrySet().iterator();
        while (it.hasNext() && it2.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Map.Entry entry2 = (Map.Entry) it2.next();
            int compareTo = ((String) entry.getKey()).compareTo((String) entry2.getKey());
            if (compareTo != 0) {
                return compareTo;
            }
            int compare = compare((Value) entry.getValue(), (Value) entry2.getValue());
            if (compare != 0) {
                return compare;
            }
        }
        return Util.compareBooleans(it.hasNext(), it2.hasNext());
    }

    private static int compareVectors(MapValue mapValue, MapValue mapValue2) {
        Map<String, Value> fieldsMap = mapValue.getFieldsMap();
        Map<String, Value> fieldsMap2 = mapValue2.getFieldsMap();
        ArrayValue arrayValue = fieldsMap.get("value").getArrayValue();
        ArrayValue arrayValue2 = fieldsMap2.get("value").getArrayValue();
        int compareIntegers = Util.compareIntegers(arrayValue.getValuesCount(), arrayValue2.getValuesCount());
        if (compareIntegers != 0) {
            return compareIntegers;
        }
        return compareArrays(arrayValue, arrayValue2);
    }

    public static String canonicalId(Value value) {
        StringBuilder sb = new StringBuilder();
        canonifyValue(sb, value);
        return sb.toString();
    }

    private static void canonifyValue(StringBuilder sb, Value value) {
        switch (AnonymousClass1.$SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[value.getValueTypeCase().ordinal()]) {
            case 1:
                sb.append("null");
                return;
            case 2:
                sb.append(value.getBooleanValue());
                return;
            case 3:
                sb.append(value.getIntegerValue());
                return;
            case 4:
                sb.append(value.getDoubleValue());
                return;
            case 5:
                canonifyTimestamp(sb, value.getTimestampValue());
                return;
            case 6:
                sb.append(value.getStringValue());
                return;
            case 7:
                sb.append(Util.toDebugString(value.getBytesValue()));
                return;
            case 8:
                canonifyReference(sb, value);
                return;
            case 9:
                canonifyGeoPoint(sb, value.getGeoPointValue());
                return;
            case 10:
                canonifyArray(sb, value.getArrayValue());
                return;
            case 11:
                canonifyObject(sb, value.getMapValue());
                return;
            default:
                throw Assert.fail("Invalid value type: " + value.getValueTypeCase(), new Object[0]);
        }
    }

    private static void canonifyTimestamp(StringBuilder sb, Timestamp timestamp) {
        sb.append(String.format("time(%s,%s)", new Object[]{Long.valueOf(timestamp.getSeconds()), Integer.valueOf(timestamp.getNanos())}));
    }

    private static void canonifyGeoPoint(StringBuilder sb, LatLng latLng) {
        sb.append(String.format("geo(%s,%s)", new Object[]{Double.valueOf(latLng.getLatitude()), Double.valueOf(latLng.getLongitude())}));
    }

    private static void canonifyReference(StringBuilder sb, Value value) {
        Assert.hardAssert(isReferenceValue(value), "Value should be a ReferenceValue", new Object[0]);
        sb.append(DocumentKey.fromName(value.getReferenceValue()));
    }

    private static void canonifyObject(StringBuilder sb, MapValue mapValue) {
        ArrayList<String> arrayList = new ArrayList<>(mapValue.getFieldsMap().keySet());
        Collections.sort(arrayList);
        sb.append("{");
        boolean z = true;
        for (String str : arrayList) {
            if (!z) {
                sb.append(",");
            } else {
                z = false;
            }
            sb.append(str).append(":");
            canonifyValue(sb, mapValue.getFieldsOrThrow(str));
        }
        sb.append("}");
    }

    private static void canonifyArray(StringBuilder sb, ArrayValue arrayValue) {
        sb.append("[");
        for (int i = 0; i < arrayValue.getValuesCount(); i++) {
            canonifyValue(sb, arrayValue.getValues(i));
            if (i != arrayValue.getValuesCount() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
    }

    public static boolean isInteger(Value value) {
        return value != null && value.getValueTypeCase() == Value.ValueTypeCase.INTEGER_VALUE;
    }

    public static boolean isDouble(Value value) {
        return value != null && value.getValueTypeCase() == Value.ValueTypeCase.DOUBLE_VALUE;
    }

    public static boolean isNumber(Value value) {
        return isInteger(value) || isDouble(value);
    }

    public static boolean isArray(Value value) {
        return value != null && value.getValueTypeCase() == Value.ValueTypeCase.ARRAY_VALUE;
    }

    public static boolean isReferenceValue(Value value) {
        return value != null && value.getValueTypeCase() == Value.ValueTypeCase.REFERENCE_VALUE;
    }

    public static boolean isNullValue(Value value) {
        return value != null && value.getValueTypeCase() == Value.ValueTypeCase.NULL_VALUE;
    }

    public static boolean isNanValue(Value value) {
        return value != null && Double.isNaN(value.getDoubleValue());
    }

    public static boolean isMapValue(Value value) {
        return value != null && value.getValueTypeCase() == Value.ValueTypeCase.MAP_VALUE;
    }

    public static Value refValue(DatabaseId databaseId, DocumentKey documentKey) {
        return (Value) Value.newBuilder().setReferenceValue(String.format("projects/%s/databases/%s/documents/%s", new Object[]{databaseId.getProjectId(), databaseId.getDatabaseId(), documentKey.toString()})).build();
    }

    public static Value getLowerBound(Value value) {
        switch (AnonymousClass1.$SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[value.getValueTypeCase().ordinal()]) {
            case 1:
                return NULL_VALUE;
            case 2:
                return MIN_BOOLEAN;
            case 3:
            case 4:
                return MIN_NUMBER;
            case 5:
                return MIN_TIMESTAMP;
            case 6:
                return MIN_STRING;
            case 7:
                return MIN_BYTES;
            case 8:
                return MIN_REFERENCE;
            case 9:
                return MIN_GEO_POINT;
            case 10:
                return MIN_ARRAY;
            case 11:
                if (isVectorValue(value)) {
                    return MIN_VECTOR_VALUE;
                }
                return MIN_MAP;
            default:
                throw new IllegalArgumentException("Unknown value type: " + value.getValueTypeCase());
        }
    }

    public static Value getUpperBound(Value value) {
        switch (AnonymousClass1.$SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[value.getValueTypeCase().ordinal()]) {
            case 1:
                return MIN_BOOLEAN;
            case 2:
                return MIN_NUMBER;
            case 3:
            case 4:
                return MIN_TIMESTAMP;
            case 5:
                return MIN_STRING;
            case 6:
                return MIN_BYTES;
            case 7:
                return MIN_REFERENCE;
            case 8:
                return MIN_GEO_POINT;
            case 9:
                return MIN_ARRAY;
            case 10:
                return MIN_VECTOR_VALUE;
            case 11:
                if (isVectorValue(value)) {
                    return MIN_MAP;
                }
                return MAX_VALUE;
            default:
                throw new IllegalArgumentException("Unknown value type: " + value.getValueTypeCase());
        }
    }

    public static boolean isMaxValue(Value value) {
        return MAX_VALUE_TYPE.equals(value.getMapValue().getFieldsMap().get(TYPE_KEY));
    }

    public static boolean isVectorValue(Value value) {
        return VECTOR_VALUE_TYPE.equals(value.getMapValue().getFieldsMap().get(TYPE_KEY));
    }
}

package com.google.firebase.firestore.index;

import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.Values;
import com.google.firestore.v1.ArrayValue;
import com.google.firestore.v1.MapValue;
import com.google.firestore.v1.Value;
import com.google.protobuf.Timestamp;
import com.google.type.LatLng;
import java.util.Map;

public class FirestoreIndexValueWriter {
    public static final int DOCUMENT_NAME_OFFSET = 5;
    public static final int INDEX_TYPE_ARRAY = 50;
    public static final int INDEX_TYPE_BLOB = 30;
    public static final int INDEX_TYPE_BOOLEAN = 10;
    public static final int INDEX_TYPE_GEOPOINT = 45;
    public static final int INDEX_TYPE_MAP = 55;
    public static final int INDEX_TYPE_NAN = 13;
    public static final int INDEX_TYPE_NULL = 5;
    public static final int INDEX_TYPE_NUMBER = 15;
    public static final int INDEX_TYPE_REFERENCE = 37;
    public static final int INDEX_TYPE_REFERENCE_SEGMENT = 60;
    public static final int INDEX_TYPE_STRING = 25;
    public static final int INDEX_TYPE_TIMESTAMP = 20;
    public static final int INDEX_TYPE_VECTOR = 53;
    public static final FirestoreIndexValueWriter INSTANCE = new FirestoreIndexValueWriter();
    public static final int NOT_TRUNCATED = 2;

    private FirestoreIndexValueWriter() {
    }

    public void writeIndexValue(Value value, DirectionalIndexByteEncoder directionalIndexByteEncoder) {
        writeIndexValueAux(value, directionalIndexByteEncoder);
        directionalIndexByteEncoder.writeInfinity();
    }

    /* renamed from: com.google.firebase.firestore.index.FirestoreIndexValueWriter$1  reason: invalid class name */
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
                com.google.firestore.v1.Value$ValueTypeCase r1 = com.google.firestore.v1.Value.ValueTypeCase.DOUBLE_VALUE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.firestore.v1.Value$ValueTypeCase r1 = com.google.firestore.v1.Value.ValueTypeCase.INTEGER_VALUE     // Catch:{ NoSuchFieldError -> 0x0033 }
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
                com.google.firestore.v1.Value$ValueTypeCase r1 = com.google.firestore.v1.Value.ValueTypeCase.MAP_VALUE     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$com$google$firestore$v1$Value$ValueTypeCase     // Catch:{ NoSuchFieldError -> 0x0084 }
                com.google.firestore.v1.Value$ValueTypeCase r1 = com.google.firestore.v1.Value.ValueTypeCase.ARRAY_VALUE     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.index.FirestoreIndexValueWriter.AnonymousClass1.<clinit>():void");
        }
    }

    private void writeIndexValueAux(Value value, DirectionalIndexByteEncoder directionalIndexByteEncoder) {
        switch (AnonymousClass1.$SwitchMap$com$google$firestore$v1$Value$ValueTypeCase[value.getValueTypeCase().ordinal()]) {
            case 1:
                writeValueTypeLabel(directionalIndexByteEncoder, 5);
                return;
            case 2:
                writeValueTypeLabel(directionalIndexByteEncoder, 10);
                directionalIndexByteEncoder.writeLong(value.getBooleanValue() ? 1 : 0);
                return;
            case 3:
                double doubleValue = value.getDoubleValue();
                if (Double.isNaN(doubleValue)) {
                    writeValueTypeLabel(directionalIndexByteEncoder, 13);
                    return;
                }
                writeValueTypeLabel(directionalIndexByteEncoder, 15);
                if (doubleValue == -0.0d) {
                    directionalIndexByteEncoder.writeDouble(0.0d);
                    return;
                } else {
                    directionalIndexByteEncoder.writeDouble(doubleValue);
                    return;
                }
            case 4:
                writeValueTypeLabel(directionalIndexByteEncoder, 15);
                directionalIndexByteEncoder.writeDouble((double) value.getIntegerValue());
                return;
            case 5:
                Timestamp timestampValue = value.getTimestampValue();
                writeValueTypeLabel(directionalIndexByteEncoder, 20);
                directionalIndexByteEncoder.writeLong(timestampValue.getSeconds());
                directionalIndexByteEncoder.writeLong((long) timestampValue.getNanos());
                return;
            case 6:
                writeIndexString(value.getStringValue(), directionalIndexByteEncoder);
                writeTruncationMarker(directionalIndexByteEncoder);
                return;
            case 7:
                writeValueTypeLabel(directionalIndexByteEncoder, 30);
                directionalIndexByteEncoder.writeBytes(value.getBytesValue());
                writeTruncationMarker(directionalIndexByteEncoder);
                return;
            case 8:
                writeIndexEntityRef(value.getReferenceValue(), directionalIndexByteEncoder);
                return;
            case 9:
                LatLng geoPointValue = value.getGeoPointValue();
                writeValueTypeLabel(directionalIndexByteEncoder, 45);
                directionalIndexByteEncoder.writeDouble(geoPointValue.getLatitude());
                directionalIndexByteEncoder.writeDouble(geoPointValue.getLongitude());
                return;
            case 10:
                if (Values.isMaxValue(value)) {
                    writeValueTypeLabel(directionalIndexByteEncoder, Integer.MAX_VALUE);
                    return;
                } else if (Values.isVectorValue(value)) {
                    writeIndexVector(value.getMapValue(), directionalIndexByteEncoder);
                    return;
                } else {
                    writeIndexMap(value.getMapValue(), directionalIndexByteEncoder);
                    writeTruncationMarker(directionalIndexByteEncoder);
                    return;
                }
            case 11:
                writeIndexArray(value.getArrayValue(), directionalIndexByteEncoder);
                writeTruncationMarker(directionalIndexByteEncoder);
                return;
            default:
                throw new IllegalArgumentException("unknown index value type " + value.getValueTypeCase());
        }
    }

    private void writeIndexString(String str, DirectionalIndexByteEncoder directionalIndexByteEncoder) {
        writeValueTypeLabel(directionalIndexByteEncoder, 25);
        writeUnlabeledIndexString(str, directionalIndexByteEncoder);
    }

    private void writeUnlabeledIndexString(String str, DirectionalIndexByteEncoder directionalIndexByteEncoder) {
        directionalIndexByteEncoder.writeString(str);
    }

    private void writeIndexVector(MapValue mapValue, DirectionalIndexByteEncoder directionalIndexByteEncoder) {
        Map<String, Value> fieldsMap = mapValue.getFieldsMap();
        writeValueTypeLabel(directionalIndexByteEncoder, 53);
        int valuesCount = fieldsMap.get("value").getArrayValue().getValuesCount();
        writeValueTypeLabel(directionalIndexByteEncoder, 15);
        directionalIndexByteEncoder.writeLong((long) valuesCount);
        writeIndexString("value", directionalIndexByteEncoder);
        writeIndexValueAux(fieldsMap.get("value"), directionalIndexByteEncoder);
    }

    private void writeIndexMap(MapValue mapValue, DirectionalIndexByteEncoder directionalIndexByteEncoder) {
        writeValueTypeLabel(directionalIndexByteEncoder, 55);
        for (Map.Entry next : mapValue.getFieldsMap().entrySet()) {
            writeIndexString((String) next.getKey(), directionalIndexByteEncoder);
            writeIndexValueAux((Value) next.getValue(), directionalIndexByteEncoder);
        }
    }

    private void writeIndexArray(ArrayValue arrayValue, DirectionalIndexByteEncoder directionalIndexByteEncoder) {
        writeValueTypeLabel(directionalIndexByteEncoder, 50);
        for (Value writeIndexValueAux : arrayValue.getValuesList()) {
            writeIndexValueAux(writeIndexValueAux, directionalIndexByteEncoder);
        }
    }

    private void writeIndexEntityRef(String str, DirectionalIndexByteEncoder directionalIndexByteEncoder) {
        writeValueTypeLabel(directionalIndexByteEncoder, 37);
        ResourcePath fromString = ResourcePath.fromString(str);
        int length = fromString.length();
        for (int i = 5; i < length; i++) {
            String segment = fromString.getSegment(i);
            writeValueTypeLabel(directionalIndexByteEncoder, 60);
            writeUnlabeledIndexString(segment, directionalIndexByteEncoder);
        }
    }

    private void writeValueTypeLabel(DirectionalIndexByteEncoder directionalIndexByteEncoder, int i) {
        directionalIndexByteEncoder.writeLong((long) i);
    }

    private void writeTruncationMarker(DirectionalIndexByteEncoder directionalIndexByteEncoder) {
        directionalIndexByteEncoder.writeLong(2);
    }
}

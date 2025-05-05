package com.google.firebase.firestore.model;

import com.google.firebase.firestore.model.mutation.FieldMask;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.v1.MapValue;
import com.google.firestore.v1.Value;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public final class ObjectValue implements Cloneable {
    private final Map<String, Object> overlayMap;
    private Value partialValue;

    public static ObjectValue fromMap(Map<String, Value> map) {
        return new ObjectValue((Value) Value.newBuilder().setMapValue(MapValue.newBuilder().putAllFields(map)).build());
    }

    public ObjectValue(Value value) {
        this.overlayMap = new HashMap();
        Assert.hardAssert(value.getValueTypeCase() == Value.ValueTypeCase.MAP_VALUE, "ObjectValues should be backed by a MapValue", new Object[0]);
        Assert.hardAssert(!ServerTimestamps.isServerTimestamp(value), "ServerTimestamps should not be used as an ObjectValue", new Object[0]);
        this.partialValue = value;
    }

    public ObjectValue() {
        this((Value) Value.newBuilder().setMapValue(MapValue.getDefaultInstance()).build());
    }

    public Map<String, Value> getFieldsMap() {
        return buildProto().getMapValue().getFieldsMap();
    }

    public FieldMask getFieldMask() {
        return extractFieldMask(buildProto().getMapValue());
    }

    private FieldMask extractFieldMask(MapValue mapValue) {
        HashSet hashSet = new HashSet();
        for (Map.Entry next : mapValue.getFieldsMap().entrySet()) {
            FieldPath fromSingleSegment = FieldPath.fromSingleSegment((String) next.getKey());
            if (Values.isMapValue((Value) next.getValue())) {
                Set<FieldPath> mask = extractFieldMask(((Value) next.getValue()).getMapValue()).getMask();
                if (mask.isEmpty()) {
                    hashSet.add(fromSingleSegment);
                } else {
                    for (FieldPath append : mask) {
                        hashSet.add((FieldPath) fromSingleSegment.append(append));
                    }
                }
            } else {
                hashSet.add(fromSingleSegment);
            }
        }
        return FieldMask.fromSet(hashSet);
    }

    public Value get(FieldPath fieldPath) {
        return extractNestedValue(buildProto(), fieldPath);
    }

    private Value extractNestedValue(Value value, FieldPath fieldPath) {
        if (fieldPath.isEmpty()) {
            return value;
        }
        for (int i = 0; i < fieldPath.length() - 1; i++) {
            value = value.getMapValue().getFieldsOrDefault(fieldPath.getSegment(i), (Value) null);
            if (!Values.isMapValue(value)) {
                return null;
            }
        }
        return value.getMapValue().getFieldsOrDefault(fieldPath.getLastSegment(), (Value) null);
    }

    private Value buildProto() {
        synchronized (this.overlayMap) {
            MapValue applyOverlay = applyOverlay(FieldPath.EMPTY_PATH, this.overlayMap);
            if (applyOverlay != null) {
                this.partialValue = (Value) Value.newBuilder().setMapValue(applyOverlay).build();
                this.overlayMap.clear();
            }
        }
        return this.partialValue;
    }

    public void delete(FieldPath fieldPath) {
        Assert.hardAssert(!fieldPath.isEmpty(), "Cannot delete field for empty path on ObjectValue", new Object[0]);
        setOverlay(fieldPath, (Value) null);
    }

    public void set(FieldPath fieldPath, Value value) {
        Assert.hardAssert(!fieldPath.isEmpty(), "Cannot set field for empty path on ObjectValue", new Object[0]);
        setOverlay(fieldPath, value);
    }

    public void setAll(Map<FieldPath, Value> map) {
        for (Map.Entry next : map.entrySet()) {
            FieldPath fieldPath = (FieldPath) next.getKey();
            if (next.getValue() == null) {
                delete(fieldPath);
            } else {
                set(fieldPath, (Value) next.getValue());
            }
        }
    }

    private void setOverlay(FieldPath fieldPath, Value value) {
        Map<String, Object> hashMap;
        Map<String, Object> map = this.overlayMap;
        for (int i = 0; i < fieldPath.length() - 1; i++) {
            String segment = fieldPath.getSegment(i);
            Object obj = map.get(segment);
            if (obj instanceof Map) {
                hashMap = (Map) obj;
            } else {
                if (obj instanceof Value) {
                    Value value2 = (Value) obj;
                    if (value2.getValueTypeCase() == Value.ValueTypeCase.MAP_VALUE) {
                        HashMap hashMap2 = new HashMap(value2.getMapValue().getFieldsMap());
                        map.put(segment, hashMap2);
                        map = hashMap2;
                    }
                }
                hashMap = new HashMap<>();
                map.put(segment, hashMap);
            }
            map = hashMap;
        }
        map.put(fieldPath.getLastSegment(), value);
    }

    private MapValue applyOverlay(FieldPath fieldPath, Map<String, Object> map) {
        MapValue.Builder builder;
        Value extractNestedValue = extractNestedValue(this.partialValue, fieldPath);
        if (Values.isMapValue(extractNestedValue)) {
            builder = (MapValue.Builder) extractNestedValue.getMapValue().toBuilder();
        } else {
            builder = MapValue.newBuilder();
        }
        boolean z = false;
        for (Map.Entry next : map.entrySet()) {
            String str = (String) next.getKey();
            Object value = next.getValue();
            if (value instanceof Map) {
                MapValue applyOverlay = applyOverlay((FieldPath) fieldPath.append(str), (Map) value);
                if (applyOverlay != null) {
                    builder.putFields(str, (Value) Value.newBuilder().setMapValue(applyOverlay).build());
                }
            } else if (value instanceof Value) {
                builder.putFields(str, (Value) value);
            } else if (builder.containsFields(str)) {
                Assert.hardAssert(value == null, "Expected entry to be a Map, a Value or null", new Object[0]);
                builder.removeFields(str);
            }
            z = true;
        }
        if (z) {
            return (MapValue) builder.build();
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ObjectValue) {
            return Values.equals(buildProto(), ((ObjectValue) obj).buildProto());
        }
        return false;
    }

    public int hashCode() {
        return buildProto().hashCode();
    }

    public String toString() {
        return "ObjectValue{internalValue=" + Values.canonicalId(buildProto()) + AbstractJsonLexerKt.END_OBJ;
    }

    public ObjectValue clone() {
        return new ObjectValue(buildProto());
    }
}

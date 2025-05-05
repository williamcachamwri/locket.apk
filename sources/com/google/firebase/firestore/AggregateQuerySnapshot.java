package com.google.firebase.firestore;

import com.google.firebase.firestore.AggregateField;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firestore.v1.Value;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

public class AggregateQuerySnapshot {
    private final Map<String, Value> data;
    private final AggregateQuery query;

    AggregateQuerySnapshot(AggregateQuery aggregateQuery, Map<String, Value> map) {
        Preconditions.checkNotNull(aggregateQuery);
        this.query = aggregateQuery;
        this.data = map;
    }

    static AggregateQuerySnapshot createWithCount(AggregateQuery aggregateQuery, long j) {
        return new AggregateQuerySnapshot(aggregateQuery, Collections.singletonMap(AggregateField.count().getAlias(), (Value) Value.newBuilder().setIntegerValue(j).build()));
    }

    public AggregateQuery getQuery() {
        return this.query;
    }

    public long getCount() {
        return get(AggregateField.count());
    }

    public Object get(AggregateField aggregateField) {
        return getInternal(aggregateField);
    }

    public long get(AggregateField.CountAggregateField countAggregateField) {
        Long l = getLong(countAggregateField);
        if (l != null) {
            return l.longValue();
        }
        throw new IllegalArgumentException("RunAggregationQueryResponse alias " + countAggregateField.getAlias() + " is null");
    }

    public Double get(AggregateField.AverageAggregateField averageAggregateField) {
        return getDouble(averageAggregateField);
    }

    public Double getDouble(AggregateField aggregateField) {
        Number number = (Number) getTypedValue(aggregateField, Number.class);
        if (number != null) {
            return Double.valueOf(number.doubleValue());
        }
        return null;
    }

    public Long getLong(AggregateField aggregateField) {
        Number number = (Number) getTypedValue(aggregateField, Number.class);
        if (number != null) {
            return Long.valueOf(number.longValue());
        }
        return null;
    }

    private Object getInternal(AggregateField aggregateField) {
        if (this.data.containsKey(aggregateField.getAlias())) {
            return new UserDataWriter(this.query.getQuery().firestore, DocumentSnapshot.ServerTimestampBehavior.DEFAULT).convertValue(this.data.get(aggregateField.getAlias()));
        }
        throw new IllegalArgumentException("'" + aggregateField.getOperator() + "(" + aggregateField.getFieldPath() + ")' was not requested in the aggregation query.");
    }

    private <T> T getTypedValue(AggregateField aggregateField, Class<T> cls) {
        return castTypedValue(getInternal(aggregateField), aggregateField, cls);
    }

    private <T> T castTypedValue(Object obj, AggregateField aggregateField, Class<T> cls) {
        if (obj == null) {
            return null;
        }
        if (cls.isInstance(obj)) {
            return cls.cast(obj);
        }
        throw new RuntimeException("AggregateField '" + aggregateField.getAlias() + "' is not a " + cls.getName());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AggregateQuerySnapshot)) {
            return false;
        }
        AggregateQuerySnapshot aggregateQuerySnapshot = (AggregateQuerySnapshot) obj;
        if (!this.query.equals(aggregateQuerySnapshot.query) || !this.data.equals(aggregateQuerySnapshot.data)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.query, this.data});
    }
}

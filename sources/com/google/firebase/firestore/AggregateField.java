package com.google.firebase.firestore;

import java.util.Objects;
import me.leolin.shortcutbadger.impl.NewHtcHomeBadger;

public abstract class AggregateField {
    private final String alias;
    private final FieldPath fieldPath;
    private final String operator;

    private AggregateField(FieldPath fieldPath2, String str) {
        this.fieldPath = fieldPath2;
        this.operator = str;
        this.alias = str + (fieldPath2 == null ? "" : "_" + fieldPath2);
    }

    public String getFieldPath() {
        FieldPath fieldPath2 = this.fieldPath;
        return fieldPath2 == null ? "" : fieldPath2.toString();
    }

    public String getAlias() {
        return this.alias;
    }

    public String getOperator() {
        return this.operator;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AggregateField)) {
            return false;
        }
        AggregateField aggregateField = (AggregateField) obj;
        FieldPath fieldPath2 = this.fieldPath;
        if (fieldPath2 == null || aggregateField.fieldPath == null) {
            if (fieldPath2 == null && aggregateField.fieldPath == null) {
                return true;
            }
            return false;
        } else if (!this.operator.equals(aggregateField.getOperator()) || !getFieldPath().equals(aggregateField.getFieldPath())) {
            return false;
        } else {
            return true;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{getOperator(), getFieldPath()});
    }

    public static CountAggregateField count() {
        return new CountAggregateField();
    }

    public static SumAggregateField sum(String str) {
        return new SumAggregateField(FieldPath.fromDotSeparatedPath(str));
    }

    public static SumAggregateField sum(FieldPath fieldPath2) {
        return new SumAggregateField(fieldPath2);
    }

    public static AverageAggregateField average(String str) {
        return new AverageAggregateField(FieldPath.fromDotSeparatedPath(str));
    }

    public static AverageAggregateField average(FieldPath fieldPath2) {
        return new AverageAggregateField(fieldPath2);
    }

    public static class CountAggregateField extends AggregateField {
        private CountAggregateField() {
            super((FieldPath) null, NewHtcHomeBadger.COUNT);
        }
    }

    public static class SumAggregateField extends AggregateField {
        private SumAggregateField(FieldPath fieldPath) {
            super(fieldPath, "sum");
        }
    }

    public static class AverageAggregateField extends AggregateField {
        private AverageAggregateField(FieldPath fieldPath) {
            super(fieldPath, "average");
        }
    }
}

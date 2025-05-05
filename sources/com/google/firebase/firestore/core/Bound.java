package com.google.firebase.firestore.core;

import com.google.firebase.firestore.core.OrderBy;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.v1.Value;
import java.util.List;

public final class Bound {
    private final boolean inclusive;
    private final List<Value> position;

    public Bound(List<Value> list, boolean z) {
        this.position = list;
        this.inclusive = z;
    }

    public List<Value> getPosition() {
        return this.position;
    }

    public boolean isInclusive() {
        return this.inclusive;
    }

    public String positionString() {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Value next : this.position) {
            if (!z) {
                sb.append(",");
            }
            sb.append(Values.canonicalId(next));
            z = false;
        }
        return sb.toString();
    }

    public boolean sortsBeforeDocument(List<OrderBy> list, Document document) {
        int compareToDocument = compareToDocument(list, document);
        if (this.inclusive) {
            if (compareToDocument <= 0) {
                return true;
            }
        } else if (compareToDocument < 0) {
            return true;
        }
        return false;
    }

    public boolean sortsAfterDocument(List<OrderBy> list, Document document) {
        int compareToDocument = compareToDocument(list, document);
        if (this.inclusive) {
            if (compareToDocument >= 0) {
                return true;
            }
        } else if (compareToDocument > 0) {
            return true;
        }
        return false;
    }

    private int compareToDocument(List<OrderBy> list, Document document) {
        int i;
        Assert.hardAssert(this.position.size() <= list.size(), "Bound has more components than query's orderBy", new Object[0]);
        int i2 = 0;
        for (int i3 = 0; i3 < this.position.size(); i3++) {
            OrderBy orderBy = list.get(i3);
            Value value = this.position.get(i3);
            if (orderBy.field.equals(FieldPath.KEY_PATH)) {
                Assert.hardAssert(Values.isReferenceValue(value), "Bound has a non-key value where the key path is being used %s", value);
                i = DocumentKey.fromName(value.getReferenceValue()).compareTo(document.getKey());
            } else {
                Value field = document.getField(orderBy.getField());
                Assert.hardAssert(field != null, "Field should exist since document matched the orderBy already.", new Object[0]);
                i = Values.compare(value, field);
            }
            if (orderBy.getDirection().equals(OrderBy.Direction.DESCENDING)) {
                i *= -1;
            }
            i2 = i;
            if (i2 != 0) {
                break;
            }
        }
        return i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Bound bound = (Bound) obj;
        if (this.inclusive != bound.inclusive || !this.position.equals(bound.position)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((this.inclusive ? 1 : 0) * true) + this.position.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Bound(inclusive=");
        sb.append(this.inclusive);
        sb.append(", position=");
        for (int i = 0; i < this.position.size(); i++) {
            if (i > 0) {
                sb.append(" and ");
            }
            sb.append(Values.canonicalId(this.position.get(i)));
        }
        sb.append(")");
        return sb.toString();
    }
}

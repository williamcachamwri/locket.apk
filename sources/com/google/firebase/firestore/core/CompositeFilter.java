package com.google.firebase.firestore.core;

import android.text.TextUtils;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.util.Function;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompositeFilter extends Filter {
    private final List<Filter> filters;
    private List<FieldFilter> memoizedFlattenedFilters;
    private final Operator operator;

    public enum Operator {
        AND("and"),
        OR("or");
        
        private final String text;

        private Operator(String str) {
            this.text = str;
        }

        public String toString() {
            return this.text;
        }
    }

    public CompositeFilter(List<Filter> list, Operator operator2) {
        this.filters = new ArrayList(list);
        this.operator = operator2;
    }

    public List<Filter> getFilters() {
        return Collections.unmodifiableList(this.filters);
    }

    public Operator getOperator() {
        return this.operator;
    }

    public List<FieldFilter> getFlattenedFilters() {
        List<FieldFilter> list = this.memoizedFlattenedFilters;
        if (list != null) {
            return Collections.unmodifiableList(list);
        }
        this.memoizedFlattenedFilters = new ArrayList();
        for (Filter flattenedFilters : this.filters) {
            this.memoizedFlattenedFilters.addAll(flattenedFilters.getFlattenedFilters());
        }
        return Collections.unmodifiableList(this.memoizedFlattenedFilters);
    }

    public boolean isConjunction() {
        return this.operator == Operator.AND;
    }

    public boolean isDisjunction() {
        return this.operator == Operator.OR;
    }

    public boolean isFlatConjunction() {
        return isFlat() && isConjunction();
    }

    public boolean isFlat() {
        for (Filter filter : this.filters) {
            if (filter instanceof CompositeFilter) {
                return false;
            }
        }
        return true;
    }

    public CompositeFilter withAddedFilters(List<Filter> list) {
        ArrayList arrayList = new ArrayList(this.filters);
        arrayList.addAll(list);
        return new CompositeFilter(arrayList, this.operator);
    }

    private FieldFilter findFirstMatchingFilter(Function<FieldFilter, Boolean> function) {
        for (FieldFilter next : getFlattenedFilters()) {
            if (function.apply(next).booleanValue()) {
                return next;
            }
        }
        return null;
    }

    public boolean matches(Document document) {
        if (isConjunction()) {
            for (Filter matches : this.filters) {
                if (!matches.matches(document)) {
                    return false;
                }
            }
            return true;
        }
        for (Filter matches2 : this.filters) {
            if (matches2.matches(document)) {
                return true;
            }
        }
        return false;
    }

    public String getCanonicalId() {
        StringBuilder sb = new StringBuilder();
        if (isFlatConjunction()) {
            for (Filter canonicalId : this.filters) {
                sb.append(canonicalId.getCanonicalId());
            }
            return sb.toString();
        }
        sb.append(this.operator.toString() + "(");
        sb.append(TextUtils.join(",", this.filters));
        sb.append(")");
        return sb.toString();
    }

    public String toString() {
        return getCanonicalId();
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof CompositeFilter)) {
            return false;
        }
        CompositeFilter compositeFilter = (CompositeFilter) obj;
        if (this.operator != compositeFilter.operator || !this.filters.equals(compositeFilter.filters)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((1147 + this.operator.hashCode()) * 31) + this.filters.hashCode();
    }
}

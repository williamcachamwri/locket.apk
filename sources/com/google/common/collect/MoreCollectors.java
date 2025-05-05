package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collector;
import javax.annotation.CheckForNull;
import kotlin.text.Typography;

@ElementTypesAreNonnullByDefault
public final class MoreCollectors {
    private static final Object NULL_PLACEHOLDER = new Object();
    private static final Collector<Object, ?, Object> ONLY_ELEMENT = Collector.of(new MoreCollectors$$ExternalSyntheticLambda0(), new MoreCollectors$$ExternalSyntheticLambda4(), new MoreCollectors$$ExternalSyntheticLambda2(), new MoreCollectors$$ExternalSyntheticLambda5(), new Collector.Characteristics[]{Collector.Characteristics.UNORDERED});
    private static final Collector<Object, ?, Optional<Object>> TO_OPTIONAL = Collector.of(new MoreCollectors$$ExternalSyntheticLambda0(), new MoreCollectors$$ExternalSyntheticLambda1(), new MoreCollectors$$ExternalSyntheticLambda2(), new MoreCollectors$$ExternalSyntheticLambda3(), new Collector.Characteristics[]{Collector.Characteristics.UNORDERED});

    public static <T> Collector<T, ?, Optional<T>> toOptional() {
        return TO_OPTIONAL;
    }

    static /* synthetic */ void lambda$static$0(ToOptionalState toOptionalState, Object obj) {
        if (obj == null) {
            obj = NULL_PLACEHOLDER;
        }
        toOptionalState.add(obj);
    }

    static /* synthetic */ Object lambda$static$1(ToOptionalState toOptionalState) {
        Object element = toOptionalState.getElement();
        if (element == NULL_PLACEHOLDER) {
            return null;
        }
        return element;
    }

    public static <T> Collector<T, ?, T> onlyElement() {
        return ONLY_ELEMENT;
    }

    private static final class ToOptionalState {
        static final int MAX_EXTRAS = 4;
        @CheckForNull
        Object element = null;
        List<Object> extras = Collections.emptyList();

        ToOptionalState() {
        }

        /* access modifiers changed from: package-private */
        public IllegalArgumentException multiples(boolean z) {
            StringBuilder append = new StringBuilder("expected one element but was: <").append(this.element);
            for (Object append2 : this.extras) {
                append.append(", ").append(append2);
            }
            if (z) {
                append.append(", ...");
            }
            append.append(Typography.greater);
            throw new IllegalArgumentException(append.toString());
        }

        /* access modifiers changed from: package-private */
        public void add(Object obj) {
            Preconditions.checkNotNull(obj);
            if (this.element == null) {
                this.element = obj;
            } else if (this.extras.isEmpty()) {
                ArrayList arrayList = new ArrayList(4);
                this.extras = arrayList;
                arrayList.add(obj);
            } else if (this.extras.size() < 4) {
                this.extras.add(obj);
            } else {
                throw multiples(true);
            }
        }

        /* access modifiers changed from: package-private */
        public ToOptionalState combine(ToOptionalState toOptionalState) {
            if (this.element == null) {
                return toOptionalState;
            }
            if (toOptionalState.element == null) {
                return this;
            }
            if (this.extras.isEmpty()) {
                this.extras = new ArrayList();
            }
            this.extras.add(toOptionalState.element);
            this.extras.addAll(toOptionalState.extras);
            if (this.extras.size() <= 4) {
                return this;
            }
            List<Object> list = this.extras;
            list.subList(4, list.size()).clear();
            throw multiples(true);
        }

        /* access modifiers changed from: package-private */
        public Optional<Object> getOptional() {
            if (this.extras.isEmpty()) {
                return Optional.ofNullable(this.element);
            }
            throw multiples(false);
        }

        /* access modifiers changed from: package-private */
        public Object getElement() {
            if (this.element == null) {
                throw new NoSuchElementException();
            } else if (this.extras.isEmpty()) {
                return this.element;
            } else {
                throw multiples(false);
            }
        }
    }

    private MoreCollectors() {
    }
}

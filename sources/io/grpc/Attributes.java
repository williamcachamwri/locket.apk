package io.grpc;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

public final class Attributes {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Attributes EMPTY;
    private static final IdentityHashMap<Key<?>, Object> EMPTY_MAP;
    /* access modifiers changed from: private */
    public final IdentityHashMap<Key<?>, Object> data;

    static {
        IdentityHashMap<Key<?>, Object> identityHashMap = new IdentityHashMap<>();
        EMPTY_MAP = identityHashMap;
        EMPTY = new Attributes(identityHashMap);
    }

    private Attributes(IdentityHashMap<Key<?>, Object> identityHashMap) {
        this.data = identityHashMap;
    }

    @Nullable
    public <T> T get(Key<T> key) {
        return this.data.get(key);
    }

    @Deprecated
    public Set<Key<?>> keys() {
        return Collections.unmodifiableSet(this.data.keySet());
    }

    /* access modifiers changed from: package-private */
    public Set<Key<?>> keysForTest() {
        return Collections.unmodifiableSet(this.data.keySet());
    }

    @Deprecated
    public static Builder newBuilder(Attributes attributes) {
        Preconditions.checkNotNull(attributes, "base");
        return new Builder();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder();
    }

    public static final class Key<T> {
        private final String debugString;

        private Key(String str) {
            this.debugString = str;
        }

        public String toString() {
            return this.debugString;
        }

        @Deprecated
        public static <T> Key<T> of(String str) {
            return new Key<>(str);
        }

        public static <T> Key<T> create(String str) {
            return new Key<>(str);
        }
    }

    public String toString() {
        return this.data.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0033  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r7) {
        /*
            r6 = this;
            r0 = 1
            if (r6 != r7) goto L_0x0004
            return r0
        L_0x0004:
            r1 = 0
            if (r7 == 0) goto L_0x005c
            java.lang.Class r2 = r6.getClass()
            java.lang.Class r3 = r7.getClass()
            if (r2 == r3) goto L_0x0012
            goto L_0x005c
        L_0x0012:
            io.grpc.Attributes r7 = (io.grpc.Attributes) r7
            java.util.IdentityHashMap<io.grpc.Attributes$Key<?>, java.lang.Object> r2 = r6.data
            int r2 = r2.size()
            java.util.IdentityHashMap<io.grpc.Attributes$Key<?>, java.lang.Object> r3 = r7.data
            int r3 = r3.size()
            if (r2 == r3) goto L_0x0023
            return r1
        L_0x0023:
            java.util.IdentityHashMap<io.grpc.Attributes$Key<?>, java.lang.Object> r2 = r6.data
            java.util.Set r2 = r2.entrySet()
            java.util.Iterator r2 = r2.iterator()
        L_0x002d:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x005b
            java.lang.Object r3 = r2.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.util.IdentityHashMap<io.grpc.Attributes$Key<?>, java.lang.Object> r4 = r7.data
            java.lang.Object r5 = r3.getKey()
            boolean r4 = r4.containsKey(r5)
            if (r4 != 0) goto L_0x0046
            return r1
        L_0x0046:
            java.lang.Object r4 = r3.getValue()
            java.util.IdentityHashMap<io.grpc.Attributes$Key<?>, java.lang.Object> r5 = r7.data
            java.lang.Object r3 = r3.getKey()
            java.lang.Object r3 = r5.get(r3)
            boolean r3 = com.google.common.base.Objects.equal(r4, r3)
            if (r3 != 0) goto L_0x002d
            return r1
        L_0x005b:
            return r0
        L_0x005c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.Attributes.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int i = 0;
        for (Map.Entry next : this.data.entrySet()) {
            i += Objects.hashCode(next.getKey(), next.getValue());
        }
        return i;
    }

    public static final class Builder {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private Attributes base;
        private IdentityHashMap<Key<?>, Object> newdata;

        static {
            Class<Attributes> cls = Attributes.class;
        }

        private Builder(Attributes attributes) {
            this.base = attributes;
        }

        private IdentityHashMap<Key<?>, Object> data(int i) {
            if (this.newdata == null) {
                this.newdata = new IdentityHashMap<>(i);
            }
            return this.newdata;
        }

        public <T> Builder set(Key<T> key, T t) {
            data(1).put(key, t);
            return this;
        }

        public <T> Builder discard(Key<T> key) {
            if (this.base.data.containsKey(key)) {
                IdentityHashMap identityHashMap = new IdentityHashMap(this.base.data);
                identityHashMap.remove(key);
                this.base = new Attributes(identityHashMap);
            }
            IdentityHashMap<Key<?>, Object> identityHashMap2 = this.newdata;
            if (identityHashMap2 != null) {
                identityHashMap2.remove(key);
            }
            return this;
        }

        public Builder setAll(Attributes attributes) {
            data(attributes.data.size()).putAll(attributes.data);
            return this;
        }

        public Attributes build() {
            if (this.newdata != null) {
                for (Map.Entry entry : this.base.data.entrySet()) {
                    if (!this.newdata.containsKey(entry.getKey())) {
                        this.newdata.put((Key) entry.getKey(), entry.getValue());
                    }
                }
                this.base = new Attributes(this.newdata);
                this.newdata = null;
            }
            return this.base;
        }
    }
}

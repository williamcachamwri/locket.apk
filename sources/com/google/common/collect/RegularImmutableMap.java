package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import javax.annotation.CheckForNull;
import kotlin.UShort;

@ElementTypesAreNonnullByDefault
final class RegularImmutableMap<K, V> extends ImmutableMap<K, V> {
    private static final byte ABSENT = -1;
    private static final int BYTE_MASK = 255;
    private static final int BYTE_MAX_SIZE = 128;
    static final ImmutableMap<Object, Object> EMPTY = new RegularImmutableMap((Object) null, new Object[0], 0);
    private static final int SHORT_MASK = 65535;
    private static final int SHORT_MAX_SIZE = 32768;
    private static final long serialVersionUID = 0;
    final transient Object[] alternatingKeysAndValues;
    @CheckForNull
    private final transient Object hashTable;
    private final transient int size;

    /* access modifiers changed from: package-private */
    public boolean isPartialView() {
        return false;
    }

    static <K, V> RegularImmutableMap<K, V> create(int i, Object[] objArr) {
        return create(i, objArr, (ImmutableMap.Builder) null);
    }

    static <K, V> RegularImmutableMap<K, V> create(int i, Object[] objArr, ImmutableMap.Builder<K, V> builder) {
        if (i == 0) {
            return (RegularImmutableMap) EMPTY;
        }
        if (i == 1) {
            CollectPreconditions.checkEntryNotNull(Objects.requireNonNull(objArr[0]), Objects.requireNonNull(objArr[1]));
            return new RegularImmutableMap<>((Object) null, objArr, 1);
        }
        Preconditions.checkPositionIndex(i, objArr.length >> 1);
        Object createHashTable = createHashTable(objArr, i, ImmutableSet.chooseTableSize(i), 0);
        if (createHashTable instanceof Object[]) {
            Object[] objArr2 = (Object[]) createHashTable;
            ImmutableMap.Builder.DuplicateKey duplicateKey = (ImmutableMap.Builder.DuplicateKey) objArr2[2];
            if (builder != null) {
                builder.duplicateKey = duplicateKey;
                Object obj = objArr2[0];
                int intValue = ((Integer) objArr2[1]).intValue();
                objArr = Arrays.copyOf(objArr, intValue * 2);
                createHashTable = obj;
                i = intValue;
            } else {
                throw duplicateKey.exception();
            }
        }
        return new RegularImmutableMap<>(createHashTable, objArr, i);
    }

    @CheckForNull
    private static Object createHashTable(Object[] objArr, int i, int i2, int i3) {
        ImmutableMap.Builder.DuplicateKey duplicateKey = null;
        if (i == 1) {
            CollectPreconditions.checkEntryNotNull(Objects.requireNonNull(objArr[i3]), Objects.requireNonNull(objArr[i3 ^ 1]));
            return null;
        }
        int i4 = i2 - 1;
        int i5 = 0;
        if (i2 <= 128) {
            byte[] bArr = new byte[i2];
            Arrays.fill(bArr, (byte) -1);
            int i6 = 0;
            while (i5 < i) {
                int i7 = (i5 * 2) + i3;
                int i8 = (i6 * 2) + i3;
                Object requireNonNull = Objects.requireNonNull(objArr[i7]);
                Object requireNonNull2 = Objects.requireNonNull(objArr[i7 ^ 1]);
                CollectPreconditions.checkEntryNotNull(requireNonNull, requireNonNull2);
                int smear = Hashing.smear(requireNonNull.hashCode());
                while (true) {
                    int i9 = smear & i4;
                    byte b = bArr[i9] & 255;
                    if (b == 255) {
                        bArr[i9] = (byte) i8;
                        if (i6 < i5) {
                            objArr[i8] = requireNonNull;
                            objArr[i8 ^ 1] = requireNonNull2;
                        }
                        i6++;
                    } else if (requireNonNull.equals(objArr[b])) {
                        byte b2 = b ^ 1;
                        duplicateKey = new ImmutableMap.Builder.DuplicateKey(requireNonNull, requireNonNull2, Objects.requireNonNull(objArr[b2]));
                        objArr[b2] = requireNonNull2;
                        break;
                    } else {
                        smear = i9 + 1;
                    }
                }
                i5++;
            }
            return i6 == i ? bArr : new Object[]{bArr, Integer.valueOf(i6), duplicateKey};
        } else if (i2 <= 32768) {
            short[] sArr = new short[i2];
            Arrays.fill(sArr, -1);
            int i10 = 0;
            while (i5 < i) {
                int i11 = (i5 * 2) + i3;
                int i12 = (i10 * 2) + i3;
                Object requireNonNull3 = Objects.requireNonNull(objArr[i11]);
                Object requireNonNull4 = Objects.requireNonNull(objArr[i11 ^ 1]);
                CollectPreconditions.checkEntryNotNull(requireNonNull3, requireNonNull4);
                int smear2 = Hashing.smear(requireNonNull3.hashCode());
                while (true) {
                    int i13 = smear2 & i4;
                    short s = sArr[i13] & UShort.MAX_VALUE;
                    if (s == 65535) {
                        sArr[i13] = (short) i12;
                        if (i10 < i5) {
                            objArr[i12] = requireNonNull3;
                            objArr[i12 ^ 1] = requireNonNull4;
                        }
                        i10++;
                    } else if (requireNonNull3.equals(objArr[s])) {
                        short s2 = s ^ 1;
                        duplicateKey = new ImmutableMap.Builder.DuplicateKey(requireNonNull3, requireNonNull4, Objects.requireNonNull(objArr[s2]));
                        objArr[s2] = requireNonNull4;
                        break;
                    } else {
                        smear2 = i13 + 1;
                    }
                }
                i5++;
            }
            return i10 == i ? sArr : new Object[]{sArr, Integer.valueOf(i10), duplicateKey};
        } else {
            int[] iArr = new int[i2];
            Arrays.fill(iArr, -1);
            int i14 = 0;
            while (i5 < i) {
                int i15 = (i5 * 2) + i3;
                int i16 = (i14 * 2) + i3;
                Object requireNonNull5 = Objects.requireNonNull(objArr[i15]);
                Object requireNonNull6 = Objects.requireNonNull(objArr[i15 ^ 1]);
                CollectPreconditions.checkEntryNotNull(requireNonNull5, requireNonNull6);
                int smear3 = Hashing.smear(requireNonNull5.hashCode());
                while (true) {
                    int i17 = smear3 & i4;
                    int i18 = iArr[i17];
                    if (i18 == -1) {
                        iArr[i17] = i16;
                        if (i14 < i5) {
                            objArr[i16] = requireNonNull5;
                            objArr[i16 ^ 1] = requireNonNull6;
                        }
                        i14++;
                    } else if (requireNonNull5.equals(objArr[i18])) {
                        int i19 = i18 ^ 1;
                        duplicateKey = new ImmutableMap.Builder.DuplicateKey(requireNonNull5, requireNonNull6, Objects.requireNonNull(objArr[i19]));
                        objArr[i19] = requireNonNull6;
                        break;
                    } else {
                        smear3 = i17 + 1;
                    }
                }
                i5++;
            }
            return i14 == i ? iArr : new Object[]{iArr, Integer.valueOf(i14), duplicateKey};
        }
    }

    @CheckForNull
    static Object createHashTableOrThrow(Object[] objArr, int i, int i2, int i3) {
        Object createHashTable = createHashTable(objArr, i, i2, i3);
        if (!(createHashTable instanceof Object[])) {
            return createHashTable;
        }
        throw ((ImmutableMap.Builder.DuplicateKey) ((Object[]) createHashTable)[2]).exception();
    }

    private RegularImmutableMap(@CheckForNull Object obj, Object[] objArr, int i) {
        this.hashTable = obj;
        this.alternatingKeysAndValues = objArr;
        this.size = i;
    }

    public int size() {
        return this.size;
    }

    @CheckForNull
    public V get(@CheckForNull Object obj) {
        V v = get(this.hashTable, this.alternatingKeysAndValues, this.size, 0, obj);
        if (v == null) {
            return null;
        }
        return v;
    }

    @CheckForNull
    static Object get(@CheckForNull Object obj, Object[] objArr, int i, int i2, @CheckForNull Object obj2) {
        if (obj2 == null) {
            return null;
        }
        if (i == 1) {
            if (Objects.requireNonNull(objArr[i2]).equals(obj2)) {
                return Objects.requireNonNull(objArr[i2 ^ 1]);
            }
            return null;
        } else if (obj == null) {
            return null;
        } else {
            if (obj instanceof byte[]) {
                byte[] bArr = (byte[]) obj;
                int length = bArr.length - 1;
                int smear = Hashing.smear(obj2.hashCode());
                while (true) {
                    int i3 = smear & length;
                    byte b = bArr[i3] & 255;
                    if (b == 255) {
                        return null;
                    }
                    if (obj2.equals(objArr[b])) {
                        return objArr[b ^ 1];
                    }
                    smear = i3 + 1;
                }
            } else if (obj instanceof short[]) {
                short[] sArr = (short[]) obj;
                int length2 = sArr.length - 1;
                int smear2 = Hashing.smear(obj2.hashCode());
                while (true) {
                    int i4 = smear2 & length2;
                    short s = sArr[i4] & UShort.MAX_VALUE;
                    if (s == 65535) {
                        return null;
                    }
                    if (obj2.equals(objArr[s])) {
                        return objArr[s ^ 1];
                    }
                    smear2 = i4 + 1;
                }
            } else {
                int[] iArr = (int[]) obj;
                int length3 = iArr.length - 1;
                int smear3 = Hashing.smear(obj2.hashCode());
                while (true) {
                    int i5 = smear3 & length3;
                    int i6 = iArr[i5];
                    if (i6 == -1) {
                        return null;
                    }
                    if (obj2.equals(objArr[i6])) {
                        return objArr[i6 ^ 1];
                    }
                    smear3 = i5 + 1;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public ImmutableSet<Map.Entry<K, V>> createEntrySet() {
        return new EntrySet(this, this.alternatingKeysAndValues, 0, this.size);
    }

    static class EntrySet<K, V> extends ImmutableSet<Map.Entry<K, V>> {
        /* access modifiers changed from: private */
        public final transient Object[] alternatingKeysAndValues;
        /* access modifiers changed from: private */
        public final transient int keyOffset;
        private final transient ImmutableMap<K, V> map;
        /* access modifiers changed from: private */
        public final transient int size;

        /* access modifiers changed from: package-private */
        public boolean isPartialView() {
            return true;
        }

        EntrySet(ImmutableMap<K, V> immutableMap, Object[] objArr, int i, int i2) {
            this.map = immutableMap;
            this.alternatingKeysAndValues = objArr;
            this.keyOffset = i;
            this.size = i2;
        }

        public UnmodifiableIterator<Map.Entry<K, V>> iterator() {
            return asList().iterator();
        }

        /* access modifiers changed from: package-private */
        public int copyIntoArray(Object[] objArr, int i) {
            return asList().copyIntoArray(objArr, i);
        }

        /* access modifiers changed from: package-private */
        public ImmutableList<Map.Entry<K, V>> createAsList() {
            return new ImmutableList<Map.Entry<K, V>>() {
                public boolean isPartialView() {
                    return true;
                }

                public Map.Entry<K, V> get(int i) {
                    Preconditions.checkElementIndex(i, EntrySet.this.size);
                    int i2 = i * 2;
                    return new AbstractMap.SimpleImmutableEntry(Objects.requireNonNull(EntrySet.this.alternatingKeysAndValues[EntrySet.this.keyOffset + i2]), Objects.requireNonNull(EntrySet.this.alternatingKeysAndValues[i2 + (EntrySet.this.keyOffset ^ 1)]));
                }

                public int size() {
                    return EntrySet.this.size;
                }

                /* access modifiers changed from: package-private */
                public Object writeReplace() {
                    return super.writeReplace();
                }
            };
        }

        public boolean contains(@CheckForNull Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value == null || !value.equals(this.map.get(key))) {
                return false;
            }
            return true;
        }

        public int size() {
            return this.size;
        }

        /* access modifiers changed from: package-private */
        public Object writeReplace() {
            return super.writeReplace();
        }
    }

    /* access modifiers changed from: package-private */
    public ImmutableSet<K> createKeySet() {
        return new KeySet(this, new KeysOrValuesAsList(this.alternatingKeysAndValues, 0, this.size));
    }

    static final class KeysOrValuesAsList extends ImmutableList<Object> {
        private final transient Object[] alternatingKeysAndValues;
        private final transient int offset;
        private final transient int size;

        /* access modifiers changed from: package-private */
        public boolean isPartialView() {
            return true;
        }

        KeysOrValuesAsList(Object[] objArr, int i, int i2) {
            this.alternatingKeysAndValues = objArr;
            this.offset = i;
            this.size = i2;
        }

        public Object get(int i) {
            Preconditions.checkElementIndex(i, this.size);
            return Objects.requireNonNull(this.alternatingKeysAndValues[(i * 2) + this.offset]);
        }

        public int size() {
            return this.size;
        }

        /* access modifiers changed from: package-private */
        public Object writeReplace() {
            return super.writeReplace();
        }
    }

    static final class KeySet<K> extends ImmutableSet<K> {
        private final transient ImmutableList<K> list;
        private final transient ImmutableMap<K, ?> map;

        /* access modifiers changed from: package-private */
        public boolean isPartialView() {
            return true;
        }

        KeySet(ImmutableMap<K, ?> immutableMap, ImmutableList<K> immutableList) {
            this.map = immutableMap;
            this.list = immutableList;
        }

        public UnmodifiableIterator<K> iterator() {
            return asList().iterator();
        }

        /* access modifiers changed from: package-private */
        public int copyIntoArray(Object[] objArr, int i) {
            return asList().copyIntoArray(objArr, i);
        }

        public ImmutableList<K> asList() {
            return this.list;
        }

        public boolean contains(@CheckForNull Object obj) {
            return this.map.get(obj) != null;
        }

        public int size() {
            return this.map.size();
        }

        /* access modifiers changed from: package-private */
        public Object writeReplace() {
            return super.writeReplace();
        }
    }

    /* access modifiers changed from: package-private */
    public ImmutableCollection<V> createValues() {
        return new KeysOrValuesAsList(this.alternatingKeysAndValues, 1, this.size);
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return super.writeReplace();
    }
}

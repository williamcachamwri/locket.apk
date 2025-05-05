package com.google.common.hash;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.hash.BloomFilterStrategies;
import com.google.common.math.DoubleMath;
import com.google.common.primitives.SignedBytes;
import com.google.common.primitives.UnsignedBytes;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.RoundingMode;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
public final class BloomFilter<T> implements Predicate<T>, Serializable {
    private static final long serialVersionUID = 912559;
    /* access modifiers changed from: private */
    public final BloomFilterStrategies.LockFreeBitArray bits;
    /* access modifiers changed from: private */
    public final Funnel<? super T> funnel;
    /* access modifiers changed from: private */
    public final int numHashFunctions;
    /* access modifiers changed from: private */
    public final Strategy strategy;

    interface Strategy extends Serializable {
        <T> boolean mightContain(@ParametricNullness T t, Funnel<? super T> funnel, int i, BloomFilterStrategies.LockFreeBitArray lockFreeBitArray);

        int ordinal();

        <T> boolean put(@ParametricNullness T t, Funnel<? super T> funnel, int i, BloomFilterStrategies.LockFreeBitArray lockFreeBitArray);
    }

    private BloomFilter(BloomFilterStrategies.LockFreeBitArray lockFreeBitArray, int i, Funnel<? super T> funnel2, Strategy strategy2) {
        boolean z = true;
        Preconditions.checkArgument(i > 0, "numHashFunctions (%s) must be > 0", i);
        Preconditions.checkArgument(i > 255 ? false : z, "numHashFunctions (%s) must be <= 255", i);
        this.bits = (BloomFilterStrategies.LockFreeBitArray) Preconditions.checkNotNull(lockFreeBitArray);
        this.numHashFunctions = i;
        this.funnel = (Funnel) Preconditions.checkNotNull(funnel2);
        this.strategy = (Strategy) Preconditions.checkNotNull(strategy2);
    }

    public BloomFilter<T> copy() {
        return new BloomFilter<>(this.bits.copy(), this.numHashFunctions, this.funnel, this.strategy);
    }

    public boolean mightContain(@ParametricNullness T t) {
        return this.strategy.mightContain(t, this.funnel, this.numHashFunctions, this.bits);
    }

    @Deprecated
    public boolean apply(@ParametricNullness T t) {
        return mightContain(t);
    }

    public boolean put(@ParametricNullness T t) {
        return this.strategy.put(t, this.funnel, this.numHashFunctions, this.bits);
    }

    public double expectedFpp() {
        return Math.pow(((double) this.bits.bitCount()) / ((double) bitSize()), (double) this.numHashFunctions);
    }

    public long approximateElementCount() {
        double bitSize = (double) this.bits.bitSize();
        return DoubleMath.roundToLong(((-Math.log1p(-(((double) this.bits.bitCount()) / bitSize))) * bitSize) / ((double) this.numHashFunctions), RoundingMode.HALF_UP);
    }

    /* access modifiers changed from: package-private */
    public long bitSize() {
        return this.bits.bitSize();
    }

    public boolean isCompatible(BloomFilter<T> bloomFilter) {
        Preconditions.checkNotNull(bloomFilter);
        return this != bloomFilter && this.numHashFunctions == bloomFilter.numHashFunctions && bitSize() == bloomFilter.bitSize() && this.strategy.equals(bloomFilter.strategy) && this.funnel.equals(bloomFilter.funnel);
    }

    public void putAll(BloomFilter<T> bloomFilter) {
        Preconditions.checkNotNull(bloomFilter);
        Preconditions.checkArgument(this != bloomFilter, "Cannot combine a BloomFilter with itself.");
        int i = this.numHashFunctions;
        int i2 = bloomFilter.numHashFunctions;
        Preconditions.checkArgument(i == i2, "BloomFilters must have the same number of hash functions (%s != %s)", i, i2);
        Preconditions.checkArgument(bitSize() == bloomFilter.bitSize(), "BloomFilters must have the same size underlying bit arrays (%s != %s)", bitSize(), bloomFilter.bitSize());
        Preconditions.checkArgument(this.strategy.equals(bloomFilter.strategy), "BloomFilters must have equal strategies (%s != %s)", (Object) this.strategy, (Object) bloomFilter.strategy);
        Preconditions.checkArgument(this.funnel.equals(bloomFilter.funnel), "BloomFilters must have equal funnels (%s != %s)", (Object) this.funnel, (Object) bloomFilter.funnel);
        this.bits.putAll(bloomFilter.bits);
    }

    public boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BloomFilter)) {
            return false;
        }
        BloomFilter bloomFilter = (BloomFilter) obj;
        if (this.numHashFunctions != bloomFilter.numHashFunctions || !this.funnel.equals(bloomFilter.funnel) || !this.bits.equals(bloomFilter.bits) || !this.strategy.equals(bloomFilter.strategy)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.numHashFunctions), this.funnel, this.strategy, this.bits);
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel2, int i, double d) {
        return create(funnel2, (long) i, d);
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel2, long j, double d) {
        return create(funnel2, j, d, BloomFilterStrategies.MURMUR128_MITZ_64);
    }

    static <T> BloomFilter<T> create(Funnel<? super T> funnel2, long j, double d, Strategy strategy2) {
        Preconditions.checkNotNull(funnel2);
        int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        boolean z = true;
        Preconditions.checkArgument(i >= 0, "Expected insertions (%s) must be >= 0", j);
        Preconditions.checkArgument(d > 0.0d, "False positive probability (%s) must be > 0.0", (Object) Double.valueOf(d));
        if (d >= 1.0d) {
            z = false;
        }
        Preconditions.checkArgument(z, "False positive probability (%s) must be < 1.0", (Object) Double.valueOf(d));
        Preconditions.checkNotNull(strategy2);
        if (i == 0) {
            j = 1;
        }
        long optimalNumOfBits = optimalNumOfBits(j, d);
        try {
            return new BloomFilter<>(new BloomFilterStrategies.LockFreeBitArray(optimalNumOfBits), optimalNumOfHashFunctions(j, optimalNumOfBits), funnel2, strategy2);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Could not create BloomFilter of " + optimalNumOfBits + " bits", e);
        }
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel2, int i) {
        return create(funnel2, (long) i);
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel2, long j) {
        return create(funnel2, j, 0.03d);
    }

    static int optimalNumOfHashFunctions(long j, long j2) {
        return Math.max(1, (int) Math.round((((double) j2) / ((double) j)) * Math.log(2.0d)));
    }

    static long optimalNumOfBits(long j, double d) {
        if (d == 0.0d) {
            d = Double.MIN_VALUE;
        }
        return (long) ((((double) (-j)) * Math.log(d)) / (Math.log(2.0d) * Math.log(2.0d)));
    }

    private Object writeReplace() {
        return new SerialForm(this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializedForm");
    }

    private static class SerialForm<T> implements Serializable {
        private static final long serialVersionUID = 1;
        final long[] data;
        final Funnel<? super T> funnel;
        final int numHashFunctions;
        final Strategy strategy;

        SerialForm(BloomFilter<T> bloomFilter) {
            this.data = BloomFilterStrategies.LockFreeBitArray.toPlainArray(bloomFilter.bits.data);
            this.numHashFunctions = bloomFilter.numHashFunctions;
            this.funnel = bloomFilter.funnel;
            this.strategy = bloomFilter.strategy;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return new BloomFilter(new BloomFilterStrategies.LockFreeBitArray(this.data), this.numHashFunctions, this.funnel, this.strategy);
        }
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeByte(SignedBytes.checkedCast((long) this.strategy.ordinal()));
        dataOutputStream.writeByte(UnsignedBytes.checkedCast((long) this.numHashFunctions));
        dataOutputStream.writeInt(this.bits.data.length());
        for (int i = 0; i < this.bits.data.length(); i++) {
            dataOutputStream.writeLong(this.bits.data.get(i));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0045, code lost:
        r11 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0046, code lost:
        r0 = r10;
        r10 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004f, code lost:
        r11 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0050, code lost:
        r10 = -1;
        r2 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x007b, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x007c, code lost:
        throw r10;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x007b A[ExcHandler: IOException (r10v1 'e' java.io.IOException A[CUSTOM_DECLARE]), Splitter:B:1:0x000b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T> com.google.common.hash.BloomFilter<T> readFrom(java.io.InputStream r10, com.google.common.hash.Funnel<? super T> r11) throws java.io.IOException {
        /*
            java.lang.String r0 = "InputStream"
            com.google.common.base.Preconditions.checkNotNull(r10, r0)
            java.lang.String r0 = "Funnel"
            com.google.common.base.Preconditions.checkNotNull(r11, r0)
            r0 = -1
            java.io.DataInputStream r1 = new java.io.DataInputStream     // Catch:{ IOException -> 0x007b, Exception -> 0x004f }
            r1.<init>(r10)     // Catch:{ IOException -> 0x007b, Exception -> 0x004f }
            byte r10 = r1.readByte()     // Catch:{ IOException -> 0x007b, Exception -> 0x004f }
            byte r2 = r1.readByte()     // Catch:{ IOException -> 0x007b, Exception -> 0x004a }
            int r2 = com.google.common.primitives.UnsignedBytes.toInt(r2)     // Catch:{ IOException -> 0x007b, Exception -> 0x004a }
            int r0 = r1.readInt()     // Catch:{ IOException -> 0x007b, Exception -> 0x0045 }
            com.google.common.hash.BloomFilterStrategies[] r3 = com.google.common.hash.BloomFilterStrategies.values()     // Catch:{ IOException -> 0x007b, Exception -> 0x0045 }
            r3 = r3[r10]     // Catch:{ IOException -> 0x007b, Exception -> 0x0045 }
            com.google.common.hash.BloomFilterStrategies$LockFreeBitArray r4 = new com.google.common.hash.BloomFilterStrategies$LockFreeBitArray     // Catch:{ IOException -> 0x007b, Exception -> 0x0045 }
            long r5 = (long) r0     // Catch:{ IOException -> 0x007b, Exception -> 0x0045 }
            r7 = 64
            long r5 = com.google.common.math.LongMath.checkedMultiply(r5, r7)     // Catch:{ IOException -> 0x007b, Exception -> 0x0045 }
            r4.<init>((long) r5)     // Catch:{ IOException -> 0x007b, Exception -> 0x0045 }
            r5 = 0
        L_0x0033:
            if (r5 >= r0) goto L_0x003f
            long r6 = r1.readLong()     // Catch:{ IOException -> 0x007b, Exception -> 0x0045 }
            r4.putData(r5, r6)     // Catch:{ IOException -> 0x007b, Exception -> 0x0045 }
            int r5 = r5 + 1
            goto L_0x0033
        L_0x003f:
            com.google.common.hash.BloomFilter r1 = new com.google.common.hash.BloomFilter     // Catch:{ IOException -> 0x007b, Exception -> 0x0045 }
            r1.<init>(r4, r2, r11, r3)     // Catch:{ IOException -> 0x007b, Exception -> 0x0045 }
            return r1
        L_0x0045:
            r11 = move-exception
            r9 = r0
            r0 = r10
            r10 = r9
            goto L_0x0052
        L_0x004a:
            r11 = move-exception
            r2 = r0
            r0 = r10
            r10 = r2
            goto L_0x0052
        L_0x004f:
            r11 = move-exception
            r10 = r0
            r2 = r10
        L_0x0052:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "Unable to deserialize BloomFilter from InputStream. strategyOrdinal: "
            r1.<init>(r3)
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.String r1 = " numHashFunctions: "
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r1 = " dataLength: "
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r10 = r0.append(r10)
            java.lang.String r10 = r10.toString()
            java.io.IOException r0 = new java.io.IOException
            r0.<init>(r10, r11)
            throw r0
        L_0x007b:
            r10 = move-exception
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.hash.BloomFilter.readFrom(java.io.InputStream, com.google.common.hash.Funnel):com.google.common.hash.BloomFilter");
    }
}

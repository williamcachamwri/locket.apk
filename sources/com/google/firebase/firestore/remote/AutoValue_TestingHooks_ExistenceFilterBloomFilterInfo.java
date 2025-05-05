package com.google.firebase.firestore.remote;

import com.google.firebase.firestore.remote.TestingHooks;

final class AutoValue_TestingHooks_ExistenceFilterBloomFilterInfo extends TestingHooks.ExistenceFilterBloomFilterInfo {
    private final boolean applied;
    private final int bitmapLength;
    private final BloomFilter bloomFilter;
    private final int hashCount;
    private final int padding;

    AutoValue_TestingHooks_ExistenceFilterBloomFilterInfo(BloomFilter bloomFilter2, boolean z, int i, int i2, int i3) {
        this.bloomFilter = bloomFilter2;
        this.applied = z;
        this.hashCount = i;
        this.bitmapLength = i2;
        this.padding = i3;
    }

    /* access modifiers changed from: package-private */
    public BloomFilter bloomFilter() {
        return this.bloomFilter;
    }

    /* access modifiers changed from: package-private */
    public boolean applied() {
        return this.applied;
    }

    /* access modifiers changed from: package-private */
    public int hashCount() {
        return this.hashCount;
    }

    /* access modifiers changed from: package-private */
    public int bitmapLength() {
        return this.bitmapLength;
    }

    /* access modifiers changed from: package-private */
    public int padding() {
        return this.padding;
    }

    public String toString() {
        return "ExistenceFilterBloomFilterInfo{bloomFilter=" + this.bloomFilter + ", applied=" + this.applied + ", hashCount=" + this.hashCount + ", bitmapLength=" + this.bitmapLength + ", padding=" + this.padding + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TestingHooks.ExistenceFilterBloomFilterInfo)) {
            return false;
        }
        TestingHooks.ExistenceFilterBloomFilterInfo existenceFilterBloomFilterInfo = (TestingHooks.ExistenceFilterBloomFilterInfo) obj;
        BloomFilter bloomFilter2 = this.bloomFilter;
        if (bloomFilter2 != null ? bloomFilter2.equals(existenceFilterBloomFilterInfo.bloomFilter()) : existenceFilterBloomFilterInfo.bloomFilter() == null) {
            if (this.applied == existenceFilterBloomFilterInfo.applied() && this.hashCount == existenceFilterBloomFilterInfo.hashCount() && this.bitmapLength == existenceFilterBloomFilterInfo.bitmapLength() && this.padding == existenceFilterBloomFilterInfo.padding()) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        BloomFilter bloomFilter2 = this.bloomFilter;
        return (((((((((bloomFilter2 == null ? 0 : bloomFilter2.hashCode()) ^ 1000003) * 1000003) ^ (this.applied ? 1231 : 1237)) * 1000003) ^ this.hashCount) * 1000003) ^ this.bitmapLength) * 1000003) ^ this.padding;
    }
}

package com.google.firebase.firestore.remote;

import com.google.firebase.firestore.remote.TestingHooks;

final class AutoValue_TestingHooks_ExistenceFilterMismatchInfo extends TestingHooks.ExistenceFilterMismatchInfo {
    private final TestingHooks.ExistenceFilterBloomFilterInfo bloomFilter;
    private final String databaseId;
    private final int existenceFilterCount;
    private final int localCacheCount;
    private final String projectId;

    AutoValue_TestingHooks_ExistenceFilterMismatchInfo(int i, int i2, String str, String str2, TestingHooks.ExistenceFilterBloomFilterInfo existenceFilterBloomFilterInfo) {
        this.localCacheCount = i;
        this.existenceFilterCount = i2;
        if (str != null) {
            this.projectId = str;
            if (str2 != null) {
                this.databaseId = str2;
                this.bloomFilter = existenceFilterBloomFilterInfo;
                return;
            }
            throw new NullPointerException("Null databaseId");
        }
        throw new NullPointerException("Null projectId");
    }

    /* access modifiers changed from: package-private */
    public int localCacheCount() {
        return this.localCacheCount;
    }

    /* access modifiers changed from: package-private */
    public int existenceFilterCount() {
        return this.existenceFilterCount;
    }

    /* access modifiers changed from: package-private */
    public String projectId() {
        return this.projectId;
    }

    /* access modifiers changed from: package-private */
    public String databaseId() {
        return this.databaseId;
    }

    /* access modifiers changed from: package-private */
    public TestingHooks.ExistenceFilterBloomFilterInfo bloomFilter() {
        return this.bloomFilter;
    }

    public String toString() {
        return "ExistenceFilterMismatchInfo{localCacheCount=" + this.localCacheCount + ", existenceFilterCount=" + this.existenceFilterCount + ", projectId=" + this.projectId + ", databaseId=" + this.databaseId + ", bloomFilter=" + this.bloomFilter + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TestingHooks.ExistenceFilterMismatchInfo)) {
            return false;
        }
        TestingHooks.ExistenceFilterMismatchInfo existenceFilterMismatchInfo = (TestingHooks.ExistenceFilterMismatchInfo) obj;
        if (this.localCacheCount == existenceFilterMismatchInfo.localCacheCount() && this.existenceFilterCount == existenceFilterMismatchInfo.existenceFilterCount() && this.projectId.equals(existenceFilterMismatchInfo.projectId()) && this.databaseId.equals(existenceFilterMismatchInfo.databaseId())) {
            TestingHooks.ExistenceFilterBloomFilterInfo existenceFilterBloomFilterInfo = this.bloomFilter;
            if (existenceFilterBloomFilterInfo == null) {
                if (existenceFilterMismatchInfo.bloomFilter() == null) {
                    return true;
                }
            } else if (existenceFilterBloomFilterInfo.equals(existenceFilterMismatchInfo.bloomFilter())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((((((this.localCacheCount ^ 1000003) * 1000003) ^ this.existenceFilterCount) * 1000003) ^ this.projectId.hashCode()) * 1000003) ^ this.databaseId.hashCode()) * 1000003;
        TestingHooks.ExistenceFilterBloomFilterInfo existenceFilterBloomFilterInfo = this.bloomFilter;
        return hashCode ^ (existenceFilterBloomFilterInfo == null ? 0 : existenceFilterBloomFilterInfo.hashCode());
    }
}

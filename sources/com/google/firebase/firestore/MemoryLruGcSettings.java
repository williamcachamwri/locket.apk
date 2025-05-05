package com.google.firebase.firestore;

import com.facebook.common.statfs.StatFsHelper;

public final class MemoryLruGcSettings implements MemoryGarbageCollectorSettings {
    private long sizeBytes;

    public static class Builder {
        private long sizeBytes;

        private Builder() {
            this.sizeBytes = StatFsHelper.DEFAULT_DISK_RED_LEVEL_IN_BYTES;
        }

        public MemoryLruGcSettings build() {
            return new MemoryLruGcSettings(this.sizeBytes);
        }

        public Builder setSizeBytes(long j) {
            this.sizeBytes = j;
            return this;
        }
    }

    private MemoryLruGcSettings(long j) {
        this.sizeBytes = j;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public long getSizeBytes() {
        return this.sizeBytes;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this.sizeBytes == ((MemoryLruGcSettings) obj).sizeBytes) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        long j = this.sizeBytes;
        return (int) (j ^ (j >>> 32));
    }

    public String toString() {
        return "MemoryLruGcSettings{cacheSize=" + getSizeBytes() + "}";
    }
}

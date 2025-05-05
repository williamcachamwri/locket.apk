package com.google.firebase.firestore;

import com.facebook.common.statfs.StatFsHelper;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public final class PersistentCacheSettings implements LocalCacheSettings {
    private final long sizeBytes;

    public static Builder newBuilder() {
        return new Builder();
    }

    private PersistentCacheSettings(long j) {
        this.sizeBytes = j;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this.sizeBytes == ((PersistentCacheSettings) obj).sizeBytes) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        long j = this.sizeBytes;
        return (int) (j ^ (j >>> 32));
    }

    public String toString() {
        return "PersistentCacheSettings{sizeBytes=" + this.sizeBytes + AbstractJsonLexerKt.END_OBJ;
    }

    public long getSizeBytes() {
        return this.sizeBytes;
    }

    public static class Builder {
        private long sizeBytes;

        private Builder() {
            this.sizeBytes = StatFsHelper.DEFAULT_DISK_RED_LEVEL_IN_BYTES;
        }

        public Builder setSizeBytes(long j) {
            this.sizeBytes = j;
            return this;
        }

        public PersistentCacheSettings build() {
            return new PersistentCacheSettings(this.sizeBytes);
        }
    }
}

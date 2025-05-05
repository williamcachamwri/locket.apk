package com.google.firebase.perf.util;

import org.apache.commons.io.FileUtils;

public enum StorageUnit {
    TERABYTES(FileUtils.ONE_TB) {
        public long convert(long j, StorageUnit storageUnit) {
            return storageUnit.toTerabytes(j);
        }
    },
    GIGABYTES(FileUtils.ONE_GB) {
        public long convert(long j, StorageUnit storageUnit) {
            return storageUnit.toGigabytes(j);
        }
    },
    MEGABYTES(1048576) {
        public long convert(long j, StorageUnit storageUnit) {
            return storageUnit.toMegabytes(j);
        }
    },
    KILOBYTES(1024) {
        public long convert(long j, StorageUnit storageUnit) {
            return storageUnit.toKilobytes(j);
        }
    },
    BYTES(1) {
        public long convert(long j, StorageUnit storageUnit) {
            return storageUnit.toBytes(j);
        }
    };
    
    long numBytes;

    public abstract long convert(long j, StorageUnit storageUnit);

    private StorageUnit(long j) {
        this.numBytes = j;
    }

    public long toBytes(long j) {
        return j * this.numBytes;
    }

    public long toKilobytes(long j) {
        return (j * this.numBytes) / KILOBYTES.numBytes;
    }

    public long toMegabytes(long j) {
        return (j * this.numBytes) / MEGABYTES.numBytes;
    }

    public long toGigabytes(long j) {
        return (j * this.numBytes) / GIGABYTES.numBytes;
    }

    public long toTerabytes(long j) {
        return (j * this.numBytes) / TERABYTES.numBytes;
    }
}

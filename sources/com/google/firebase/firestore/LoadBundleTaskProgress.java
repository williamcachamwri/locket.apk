package com.google.firebase.firestore;

import com.google.firebase.firestore.bundle.BundleMetadata;

public final class LoadBundleTaskProgress {
    static final LoadBundleTaskProgress INITIAL = new LoadBundleTaskProgress(0, 0, 0, 0, (Exception) null, TaskState.SUCCESS);
    private final long bytesLoaded;
    private final int documentsLoaded;
    private final Exception exception;
    private final TaskState taskState;
    private final long totalBytes;
    private final int totalDocuments;

    public enum TaskState {
        ERROR,
        RUNNING,
        SUCCESS
    }

    public LoadBundleTaskProgress(int i, int i2, long j, long j2, Exception exc, TaskState taskState2) {
        this.documentsLoaded = i;
        this.totalDocuments = i2;
        this.bytesLoaded = j;
        this.totalBytes = j2;
        this.taskState = taskState2;
        this.exception = exc;
    }

    public static LoadBundleTaskProgress forInitial(BundleMetadata bundleMetadata) {
        return new LoadBundleTaskProgress(0, bundleMetadata.getTotalDocuments(), 0, bundleMetadata.getTotalBytes(), (Exception) null, TaskState.RUNNING);
    }

    public static LoadBundleTaskProgress forSuccess(BundleMetadata bundleMetadata) {
        return new LoadBundleTaskProgress(bundleMetadata.getTotalDocuments(), bundleMetadata.getTotalDocuments(), bundleMetadata.getTotalBytes(), bundleMetadata.getTotalBytes(), (Exception) null, TaskState.SUCCESS);
    }

    public int getDocumentsLoaded() {
        return this.documentsLoaded;
    }

    public int getTotalDocuments() {
        return this.totalDocuments;
    }

    public long getBytesLoaded() {
        return this.bytesLoaded;
    }

    public long getTotalBytes() {
        return this.totalBytes;
    }

    public TaskState getTaskState() {
        return this.taskState;
    }

    public Exception getException() {
        return this.exception;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LoadBundleTaskProgress loadBundleTaskProgress = (LoadBundleTaskProgress) obj;
        if (this.documentsLoaded != loadBundleTaskProgress.documentsLoaded || this.totalDocuments != loadBundleTaskProgress.totalDocuments || this.bytesLoaded != loadBundleTaskProgress.bytesLoaded || this.totalBytes != loadBundleTaskProgress.totalBytes || this.taskState != loadBundleTaskProgress.taskState) {
            return false;
        }
        Exception exc = this.exception;
        Exception exc2 = loadBundleTaskProgress.exception;
        if (exc != null) {
            return exc.equals(exc2);
        }
        if (exc2 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        long j = this.bytesLoaded;
        long j2 = this.totalBytes;
        int hashCode = ((((((((this.documentsLoaded * 31) + this.totalDocuments) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + this.taskState.hashCode()) * 31;
        Exception exc = this.exception;
        return hashCode + (exc != null ? exc.hashCode() : 0);
    }
}

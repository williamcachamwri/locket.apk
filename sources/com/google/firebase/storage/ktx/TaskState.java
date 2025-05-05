package com.google.firebase.storage.ktx;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b'\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0002\u0004\u0005B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0006"}, d2 = {"Lcom/google/firebase/storage/ktx/TaskState;", "T", "", "()V", "InProgress", "Paused", "com.google.firebase-firebase-storage"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Deprecated(message = "Migrate to use the KTX API from the main module: https://firebase.google.com/docs/android/kotlin-migration.", replaceWith = @ReplaceWith(expression = "", imports = {}))
/* compiled from: TaskState.kt */
public abstract class TaskState<T> {
    public /* synthetic */ TaskState(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private TaskState() {
    }

    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0001¢\u0006\u0002\u0010\u0004R\u0013\u0010\u0003\u001a\u00028\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lcom/google/firebase/storage/ktx/TaskState$InProgress;", "T", "Lcom/google/firebase/storage/ktx/TaskState;", "snapshot", "(Ljava/lang/Object;)V", "getSnapshot", "()Ljava/lang/Object;", "Ljava/lang/Object;", "com.google.firebase-firebase-storage"}, k = 1, mv = {1, 8, 0}, xi = 48)
    @Deprecated(message = "Migrate to use the KTX API from the main module: https://firebase.google.com/docs/android/kotlin-migration.", replaceWith = @ReplaceWith(expression = "", imports = {}))
    /* compiled from: TaskState.kt */
    public static final class InProgress<T> extends TaskState<T> {
        private final T snapshot;

        public InProgress(T t) {
            super((DefaultConstructorMarker) null);
            this.snapshot = t;
        }

        public final T getSnapshot() {
            return this.snapshot;
        }
    }

    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0001¢\u0006\u0002\u0010\u0004R\u0013\u0010\u0003\u001a\u00028\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lcom/google/firebase/storage/ktx/TaskState$Paused;", "T", "Lcom/google/firebase/storage/ktx/TaskState;", "snapshot", "(Ljava/lang/Object;)V", "getSnapshot", "()Ljava/lang/Object;", "Ljava/lang/Object;", "com.google.firebase-firebase-storage"}, k = 1, mv = {1, 8, 0}, xi = 48)
    @Deprecated(message = "Migrate to use the KTX API from the main module: https://firebase.google.com/docs/android/kotlin-migration.", replaceWith = @ReplaceWith(expression = "", imports = {}))
    /* compiled from: TaskState.kt */
    public static final class Paused<T> extends TaskState<T> {
        private final T snapshot;

        public Paused(T t) {
            super((DefaultConstructorMarker) null);
            this.snapshot = t;
        }

        public final T getSnapshot() {
            return this.snapshot;
        }
    }
}

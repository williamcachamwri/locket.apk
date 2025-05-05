package androidx.camera.core.internal.utils;

import androidx.camera.core.internal.utils.RingBuffer;
import java.util.ArrayDeque;

public class ArrayRingBuffer<T> implements RingBuffer<T> {
    private static final String TAG = "ZslRingBuffer";
    private final ArrayDeque<T> mBuffer;
    private final Object mLock;
    final RingBuffer.OnRemoveCallback<T> mOnRemoveCallback;
    private final int mRingBufferCapacity;

    public ArrayRingBuffer(int i) {
        this(i, (RingBuffer.OnRemoveCallback) null);
    }

    public ArrayRingBuffer(int i, RingBuffer.OnRemoveCallback<T> onRemoveCallback) {
        this.mLock = new Object();
        this.mRingBufferCapacity = i;
        this.mBuffer = new ArrayDeque<>(i);
        this.mOnRemoveCallback = onRemoveCallback;
    }

    public void enqueue(T t) {
        Object dequeue;
        synchronized (this.mLock) {
            dequeue = this.mBuffer.size() >= this.mRingBufferCapacity ? dequeue() : null;
            this.mBuffer.addFirst(t);
        }
        RingBuffer.OnRemoveCallback<T> onRemoveCallback = this.mOnRemoveCallback;
        if (onRemoveCallback != null && dequeue != null) {
            onRemoveCallback.onRemove(dequeue);
        }
    }

    public T dequeue() {
        T removeLast;
        synchronized (this.mLock) {
            removeLast = this.mBuffer.removeLast();
        }
        return removeLast;
    }

    public int getMaxCapacity() {
        return this.mRingBufferCapacity;
    }

    public boolean isEmpty() {
        boolean isEmpty;
        synchronized (this.mLock) {
            isEmpty = this.mBuffer.isEmpty();
        }
        return isEmpty;
    }
}

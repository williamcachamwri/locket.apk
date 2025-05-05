package androidx.media3.session;

import android.os.Handler;
import androidx.collection.ArrayMap;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import com.google.common.util.concurrent.AbstractFuture;
import java.util.ArrayList;

final class SequencedFutureManager {
    private static final String TAG = "SequencedFutureManager";
    private boolean isReleased;
    private final Object lock = new Object();
    private int nextSequenceNumber;
    private Runnable pendingLazyReleaseCallback;
    private Handler releaseCallbackHandler;
    private final ArrayMap<Integer, SequencedFuture<?>> seqToFutureMap = new ArrayMap<>();

    public int obtainNextSequenceNumber() {
        int i;
        synchronized (this.lock) {
            i = this.nextSequenceNumber;
            this.nextSequenceNumber = i + 1;
        }
        return i;
    }

    public <T> SequencedFuture<T> createSequencedFuture(T t) {
        SequencedFuture<T> create;
        synchronized (this.lock) {
            int obtainNextSequenceNumber = obtainNextSequenceNumber();
            create = SequencedFuture.create(obtainNextSequenceNumber, t);
            if (this.isReleased) {
                create.setWithTheValueOfResultWhenClosed();
            } else {
                this.seqToFutureMap.put(Integer.valueOf(obtainNextSequenceNumber), create);
            }
        }
        return create;
    }

    public <T> void setFutureResult(int i, T t) {
        synchronized (this.lock) {
            SequencedFuture remove = this.seqToFutureMap.remove(Integer.valueOf(i));
            if (remove != null) {
                if (remove.getResultWhenClosed().getClass() == t.getClass()) {
                    remove.set(t);
                } else {
                    Log.w(TAG, "Type mismatch, expected " + remove.getResultWhenClosed().getClass() + ", but was " + t.getClass());
                }
            }
            if (this.pendingLazyReleaseCallback != null && this.seqToFutureMap.isEmpty()) {
                release();
            }
        }
    }

    public void release() {
        ArrayList<SequencedFuture> arrayList;
        synchronized (this.lock) {
            this.isReleased = true;
            arrayList = new ArrayList<>(this.seqToFutureMap.values());
            this.seqToFutureMap.clear();
            if (this.pendingLazyReleaseCallback != null) {
                ((Handler) Assertions.checkNotNull(this.releaseCallbackHandler)).post(this.pendingLazyReleaseCallback);
                this.pendingLazyReleaseCallback = null;
                this.releaseCallbackHandler = null;
            }
        }
        for (SequencedFuture withTheValueOfResultWhenClosed : arrayList) {
            withTheValueOfResultWhenClosed.setWithTheValueOfResultWhenClosed();
        }
    }

    public void lazyRelease(long j, Runnable runnable) {
        synchronized (this.lock) {
            Handler createHandlerForCurrentLooper = Util.createHandlerForCurrentLooper();
            this.releaseCallbackHandler = createHandlerForCurrentLooper;
            this.pendingLazyReleaseCallback = runnable;
            if (this.seqToFutureMap.isEmpty()) {
                release();
            } else {
                createHandlerForCurrentLooper.postDelayed(new SequencedFutureManager$$ExternalSyntheticLambda0(this), j);
            }
        }
    }

    public static final class SequencedFuture<T> extends AbstractFuture<T> {
        private final T resultWhenClosed;
        private final int sequenceNumber;

        private SequencedFuture(int i, T t) {
            this.sequenceNumber = i;
            this.resultWhenClosed = t;
        }

        public boolean set(T t) {
            return super.set(t);
        }

        public void setWithTheValueOfResultWhenClosed() {
            set(this.resultWhenClosed);
        }

        public int getSequenceNumber() {
            return this.sequenceNumber;
        }

        public T getResultWhenClosed() {
            return this.resultWhenClosed;
        }

        public static <T> SequencedFuture<T> create(int i, T t) {
            return new SequencedFuture<>(i, t);
        }
    }
}

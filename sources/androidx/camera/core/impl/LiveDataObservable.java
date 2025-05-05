package androidx.camera.core.impl;

import android.os.SystemClock;
import androidx.camera.core.impl.Observable;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

public final class LiveDataObservable<T> implements Observable<T> {
    final MutableLiveData<Result<T>> mLiveData = new MutableLiveData<>();
    private final Map<Observable.Observer<? super T>, LiveDataObserverAdapter<T>> mObservers = new HashMap();

    public void postValue(T t) {
        this.mLiveData.postValue(Result.fromValue(t));
    }

    public void postError(Throwable th) {
        this.mLiveData.postValue(Result.fromError(th));
    }

    public LiveData<Result<T>> getLiveData() {
        return this.mLiveData;
    }

    public ListenableFuture<T> fetchData() {
        return CallbackToFutureAdapter.getFuture(new LiveDataObservable$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$fetchData$1$androidx-camera-core-impl-LiveDataObservable  reason: not valid java name */
    public /* synthetic */ Object m183lambda$fetchData$1$androidxcameracoreimplLiveDataObservable(CallbackToFutureAdapter.Completer completer) throws Exception {
        CameraXExecutors.mainThreadExecutor().execute(new LiveDataObservable$$ExternalSyntheticLambda1(this, completer));
        return this + " [fetch@" + SystemClock.uptimeMillis() + "]";
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$fetchData$0$androidx-camera-core-impl-LiveDataObservable  reason: not valid java name */
    public /* synthetic */ void m182lambda$fetchData$0$androidxcameracoreimplLiveDataObservable(CallbackToFutureAdapter.Completer completer) {
        Result value = this.mLiveData.getValue();
        if (value == null) {
            completer.setException(new IllegalStateException("Observable has not yet been initialized with a value."));
        } else if (value.completedSuccessfully()) {
            completer.set(value.getValue());
        } else {
            Preconditions.checkNotNull(value.getError());
            completer.setException(value.getError());
        }
    }

    public void addObserver(Executor executor, Observable.Observer<? super T> observer) {
        synchronized (this.mObservers) {
            LiveDataObserverAdapter liveDataObserverAdapter = this.mObservers.get(observer);
            if (liveDataObserverAdapter != null) {
                liveDataObserverAdapter.disable();
            }
            LiveDataObserverAdapter liveDataObserverAdapter2 = new LiveDataObserverAdapter(executor, observer);
            this.mObservers.put(observer, liveDataObserverAdapter2);
            CameraXExecutors.mainThreadExecutor().execute(new LiveDataObservable$$ExternalSyntheticLambda3(this, liveDataObserverAdapter, liveDataObserverAdapter2));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$addObserver$2$androidx-camera-core-impl-LiveDataObservable  reason: not valid java name */
    public /* synthetic */ void m181lambda$addObserver$2$androidxcameracoreimplLiveDataObservable(LiveDataObserverAdapter liveDataObserverAdapter, LiveDataObserverAdapter liveDataObserverAdapter2) {
        if (liveDataObserverAdapter != null) {
            this.mLiveData.removeObserver(liveDataObserverAdapter);
        }
        this.mLiveData.observeForever(liveDataObserverAdapter2);
    }

    public void removeObserver(Observable.Observer<? super T> observer) {
        synchronized (this.mObservers) {
            LiveDataObserverAdapter remove = this.mObservers.remove(observer);
            if (remove != null) {
                remove.disable();
                CameraXExecutors.mainThreadExecutor().execute(new LiveDataObservable$$ExternalSyntheticLambda2(this, remove));
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$removeObserver$3$androidx-camera-core-impl-LiveDataObservable  reason: not valid java name */
    public /* synthetic */ void m184lambda$removeObserver$3$androidxcameracoreimplLiveDataObservable(LiveDataObserverAdapter liveDataObserverAdapter) {
        this.mLiveData.removeObserver(liveDataObserverAdapter);
    }

    public static final class Result<T> {
        private final Throwable mError;
        private final T mValue;

        private Result(T t, Throwable th) {
            this.mValue = t;
            this.mError = th;
        }

        static <T> Result<T> fromValue(T t) {
            return new Result<>(t, (Throwable) null);
        }

        static <T> Result<T> fromError(Throwable th) {
            return new Result<>((Object) null, (Throwable) Preconditions.checkNotNull(th));
        }

        public boolean completedSuccessfully() {
            return this.mError == null;
        }

        public T getValue() {
            if (completedSuccessfully()) {
                return this.mValue;
            }
            throw new IllegalStateException("Result contains an error. Does not contain a value.");
        }

        public Throwable getError() {
            return this.mError;
        }

        public String toString() {
            String str;
            StringBuilder sb = new StringBuilder("[Result: <");
            if (completedSuccessfully()) {
                str = "Value: " + this.mValue;
            } else {
                str = "Error: " + this.mError;
            }
            return sb.append(str).append(">]").toString();
        }
    }

    private static final class LiveDataObserverAdapter<T> implements Observer<Result<T>> {
        final AtomicBoolean mActive = new AtomicBoolean(true);
        final Executor mExecutor;
        final Observable.Observer<? super T> mObserver;

        LiveDataObserverAdapter(Executor executor, Observable.Observer<? super T> observer) {
            this.mExecutor = executor;
            this.mObserver = observer;
        }

        /* access modifiers changed from: package-private */
        public void disable() {
            this.mActive.set(false);
        }

        public void onChanged(Result<T> result) {
            this.mExecutor.execute(new LiveDataObservable$LiveDataObserverAdapter$$ExternalSyntheticLambda0(this, result));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onChanged$0$androidx-camera-core-impl-LiveDataObservable$LiveDataObserverAdapter  reason: not valid java name */
        public /* synthetic */ void m185lambda$onChanged$0$androidxcameracoreimplLiveDataObservable$LiveDataObserverAdapter(Result result) {
            if (this.mActive.get()) {
                if (result.completedSuccessfully()) {
                    this.mObserver.onNewData(result.getValue());
                    return;
                }
                Preconditions.checkNotNull(result.getError());
                this.mObserver.onError(result.getError());
            }
        }
    }
}

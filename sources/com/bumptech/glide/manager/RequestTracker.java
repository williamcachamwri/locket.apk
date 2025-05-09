package com.bumptech.glide.manager;

import android.util.Log;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.util.Util;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.WeakHashMap;

public class RequestTracker {
    private static final String TAG = "RequestTracker";
    private boolean isPaused;
    private final Set<Request> pendingRequests = new HashSet();
    private final Set<Request> requests = Collections.newSetFromMap(new WeakHashMap());

    public void runRequest(Request request) {
        this.requests.add(request);
        if (!this.isPaused) {
            request.begin();
            return;
        }
        request.clear();
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "Paused, delaying request");
        }
        this.pendingRequests.add(request);
    }

    /* access modifiers changed from: package-private */
    public void addRequest(Request request) {
        this.requests.add(request);
    }

    public boolean clearAndRemove(Request request) {
        boolean z = true;
        if (request == null) {
            return true;
        }
        boolean remove = this.requests.remove(request);
        if (!this.pendingRequests.remove(request) && !remove) {
            z = false;
        }
        if (z) {
            request.clear();
        }
        return z;
    }

    public boolean isPaused() {
        return this.isPaused;
    }

    public void pauseRequests() {
        this.isPaused = true;
        for (T t : Util.getSnapshot(this.requests)) {
            if (t.isRunning()) {
                t.pause();
                this.pendingRequests.add(t);
            }
        }
    }

    public void pauseAllRequests() {
        this.isPaused = true;
        for (T t : Util.getSnapshot(this.requests)) {
            if (t.isRunning() || t.isComplete()) {
                t.clear();
                this.pendingRequests.add(t);
            }
        }
    }

    public void resumeRequests() {
        this.isPaused = false;
        for (T t : Util.getSnapshot(this.requests)) {
            if (!t.isComplete() && !t.isRunning()) {
                t.begin();
            }
        }
        this.pendingRequests.clear();
    }

    public void clearRequests() {
        for (T clearAndRemove : Util.getSnapshot(this.requests)) {
            clearAndRemove(clearAndRemove);
        }
        this.pendingRequests.clear();
    }

    public void restartRequests() {
        for (T t : Util.getSnapshot(this.requests)) {
            if (!t.isComplete() && !t.isCleared()) {
                t.clear();
                if (!this.isPaused) {
                    t.begin();
                } else {
                    this.pendingRequests.add(t);
                }
            }
        }
    }

    public String toString() {
        return super.toString() + "{numRequests=" + this.requests.size() + ", isPaused=" + this.isPaused + "}";
    }
}

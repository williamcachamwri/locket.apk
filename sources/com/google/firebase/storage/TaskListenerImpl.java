package com.google.firebase.storage;

import android.app.Activity;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.StorageTask.ProvideError;
import com.google.firebase.storage.internal.ActivityLifecycleListener;
import com.google.firebase.storage.internal.SmartHandler;
import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

class TaskListenerImpl<ListenerTypeT, ResultT extends StorageTask.ProvideError> {
    private final HashMap<ListenerTypeT, SmartHandler> handlerMap = new HashMap<>();
    private final Queue<ListenerTypeT> listenerQueue = new ConcurrentLinkedQueue();
    private OnRaise<ListenerTypeT, ResultT> onRaise;
    private int targetStates;
    private StorageTask<ResultT> task;

    interface OnRaise<ListenerTypeT, ResultT> {
        void raise(ListenerTypeT listenertypet, ResultT resultt);
    }

    public TaskListenerImpl(StorageTask<ResultT> storageTask, int i, OnRaise<ListenerTypeT, ResultT> onRaise2) {
        this.task = storageTask;
        this.targetStates = i;
        this.onRaise = onRaise2;
    }

    public int getListenerCount() {
        return Math.max(this.listenerQueue.size(), this.handlerMap.size());
    }

    public void addListener(Activity activity, Executor executor, ListenerTypeT listenertypet) {
        boolean z;
        SmartHandler smartHandler;
        Preconditions.checkNotNull(listenertypet);
        synchronized (this.task.getSyncObject()) {
            boolean z2 = true;
            z = (this.task.getInternalState() & this.targetStates) != 0;
            this.listenerQueue.add(listenertypet);
            smartHandler = new SmartHandler(executor);
            this.handlerMap.put(listenertypet, smartHandler);
            if (activity != null) {
                if (activity.isDestroyed()) {
                    z2 = false;
                }
                Preconditions.checkArgument(z2, "Activity is already destroyed!");
                ActivityLifecycleListener.getInstance().runOnActivityStopped(activity, listenertypet, new TaskListenerImpl$$ExternalSyntheticLambda1(this, listenertypet));
            }
        }
        if (z) {
            smartHandler.callBack(new TaskListenerImpl$$ExternalSyntheticLambda2(this, listenertypet, this.task.snapState()));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$addListener$1$com-google-firebase-storage-TaskListenerImpl  reason: not valid java name */
    public /* synthetic */ void m852lambda$addListener$1$comgooglefirebasestorageTaskListenerImpl(Object obj, StorageTask.ProvideError provideError) {
        this.onRaise.raise(obj, provideError);
    }

    public void onInternalStateChanged() {
        if ((this.task.getInternalState() & this.targetStates) != 0) {
            ResultT snapState = this.task.snapState();
            for (Object next : this.listenerQueue) {
                SmartHandler smartHandler = this.handlerMap.get(next);
                if (smartHandler != null) {
                    smartHandler.callBack(new TaskListenerImpl$$ExternalSyntheticLambda0(this, next, snapState));
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onInternalStateChanged$2$com-google-firebase-storage-TaskListenerImpl  reason: not valid java name */
    public /* synthetic */ void m853lambda$onInternalStateChanged$2$comgooglefirebasestorageTaskListenerImpl(Object obj, StorageTask.ProvideError provideError) {
        this.onRaise.raise(obj, provideError);
    }

    /* renamed from: removeListener */
    public void m851lambda$addListener$0$comgooglefirebasestorageTaskListenerImpl(ListenerTypeT listenertypet) {
        Preconditions.checkNotNull(listenertypet);
        synchronized (this.task.getSyncObject()) {
            this.handlerMap.remove(listenertypet);
            this.listenerQueue.remove(listenertypet);
            ActivityLifecycleListener.getInstance().removeCookie(listenertypet);
        }
    }
}

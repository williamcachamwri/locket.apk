package com.google.common.util.concurrent;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Preconditions;
import com.google.common.collect.Queues;
import com.google.firebase.messaging.Constants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.logging.Level;

@ElementTypesAreNonnullByDefault
final class ListenerCallQueue<L> {
    /* access modifiers changed from: private */
    public static final LazyLogger logger = new LazyLogger(ListenerCallQueue.class);
    private final List<PerListenerQueue<L>> listeners = Collections.synchronizedList(new ArrayList());

    interface Event<L> {
        void call(L l);
    }

    ListenerCallQueue() {
    }

    public void addListener(L l, Executor executor) {
        Preconditions.checkNotNull(l, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        Preconditions.checkNotNull(executor, "executor");
        this.listeners.add(new PerListenerQueue(l, executor));
    }

    public void enqueue(Event<L> event) {
        enqueueHelper(event, event);
    }

    public void enqueue(Event<L> event, String str) {
        enqueueHelper(event, str);
    }

    private void enqueueHelper(Event<L> event, Object obj) {
        Preconditions.checkNotNull(event, NotificationCompat.CATEGORY_EVENT);
        Preconditions.checkNotNull(obj, Constants.ScionAnalytics.PARAM_LABEL);
        synchronized (this.listeners) {
            for (PerListenerQueue<L> add : this.listeners) {
                add.add(event, obj);
            }
        }
    }

    public void dispatch() {
        for (int i = 0; i < this.listeners.size(); i++) {
            this.listeners.get(i).dispatch();
        }
    }

    private static final class PerListenerQueue<L> implements Runnable {
        final Executor executor;
        boolean isThreadScheduled;
        final Queue<Object> labelQueue = Queues.newArrayDeque();
        final L listener;
        final Queue<Event<L>> waitQueue = Queues.newArrayDeque();

        PerListenerQueue(L l, Executor executor2) {
            this.listener = Preconditions.checkNotNull(l);
            this.executor = (Executor) Preconditions.checkNotNull(executor2);
        }

        /* access modifiers changed from: package-private */
        public synchronized void add(Event<L> event, Object obj) {
            this.waitQueue.add(event);
            this.labelQueue.add(obj);
        }

        /* access modifiers changed from: package-private */
        public void dispatch() {
            boolean z;
            synchronized (this) {
                if (!this.isThreadScheduled) {
                    z = true;
                    this.isThreadScheduled = true;
                } else {
                    z = false;
                }
            }
            if (z) {
                try {
                    this.executor.execute(this);
                } catch (Exception e) {
                    synchronized (this) {
                        this.isThreadScheduled = false;
                        ListenerCallQueue.logger.get().log(Level.SEVERE, "Exception while running callbacks for " + this.listener + " on " + this.executor, e);
                        throw e;
                    }
                }
            }
        }

        /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
            java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
            	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
            	at java.util.ArrayList.get(ArrayList.java:435)
            	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
            	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
            	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
            	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
            	at jadx.core.dex.visitors.regions.RegionMaker.processHandlersOutBlocks(RegionMaker.java:1008)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:978)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
            */
        public void run() {
            /*
                r9 = this;
            L_0x0000:
                r0 = 0
                r1 = 1
                monitor-enter(r9)     // Catch:{ all -> 0x0061 }
                boolean r2 = r9.isThreadScheduled     // Catch:{ all -> 0x0054 }
                com.google.common.base.Preconditions.checkState(r2)     // Catch:{ all -> 0x0054 }
                java.util.Queue<com.google.common.util.concurrent.ListenerCallQueue$Event<L>> r2 = r9.waitQueue     // Catch:{ all -> 0x0054 }
                java.lang.Object r2 = r2.poll()     // Catch:{ all -> 0x0054 }
                com.google.common.util.concurrent.ListenerCallQueue$Event r2 = (com.google.common.util.concurrent.ListenerCallQueue.Event) r2     // Catch:{ all -> 0x0054 }
                java.util.Queue<java.lang.Object> r3 = r9.labelQueue     // Catch:{ all -> 0x0054 }
                java.lang.Object r3 = r3.poll()     // Catch:{ all -> 0x0054 }
                if (r2 != 0) goto L_0x001f
                r9.isThreadScheduled = r0     // Catch:{ all -> 0x0054 }
                monitor-exit(r9)     // Catch:{ all -> 0x001c }
                return
            L_0x001c:
                r1 = move-exception
                r2 = r0
                goto L_0x0058
            L_0x001f:
                monitor-exit(r9)     // Catch:{ all -> 0x0054 }
                L r4 = r9.listener     // Catch:{ Exception -> 0x0026 }
                r2.call(r4)     // Catch:{ Exception -> 0x0026 }
                goto L_0x0000
            L_0x0026:
                r2 = move-exception
                com.google.common.util.concurrent.LazyLogger r4 = com.google.common.util.concurrent.ListenerCallQueue.logger     // Catch:{ all -> 0x0061 }
                java.util.logging.Logger r4 = r4.get()     // Catch:{ all -> 0x0061 }
                java.util.logging.Level r5 = java.util.logging.Level.SEVERE     // Catch:{ all -> 0x0061 }
                java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0061 }
                r6.<init>()     // Catch:{ all -> 0x0061 }
                java.lang.String r7 = "Exception while executing callback: "
                java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ all -> 0x0061 }
                L r7 = r9.listener     // Catch:{ all -> 0x0061 }
                java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ all -> 0x0061 }
                java.lang.String r7 = " "
                java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ all -> 0x0061 }
                java.lang.StringBuilder r3 = r6.append(r3)     // Catch:{ all -> 0x0061 }
                java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0061 }
                r4.log(r5, r3, r2)     // Catch:{ all -> 0x0061 }
                goto L_0x0000
            L_0x0054:
                r2 = move-exception
                r8 = r2
                r2 = r1
                r1 = r8
            L_0x0058:
                monitor-exit(r9)     // Catch:{ all -> 0x005f }
                throw r1     // Catch:{ all -> 0x005a }
            L_0x005a:
                r1 = move-exception
                r8 = r2
                r2 = r1
                r1 = r8
                goto L_0x0062
            L_0x005f:
                r1 = move-exception
                goto L_0x0058
            L_0x0061:
                r2 = move-exception
            L_0x0062:
                if (r1 == 0) goto L_0x006c
                monitor-enter(r9)
                r9.isThreadScheduled = r0     // Catch:{ all -> 0x0069 }
                monitor-exit(r9)     // Catch:{ all -> 0x0069 }
                goto L_0x006c
            L_0x0069:
                r0 = move-exception
                monitor-exit(r9)     // Catch:{ all -> 0x0069 }
                throw r0
            L_0x006c:
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.ListenerCallQueue.PerListenerQueue.run():void");
        }
    }
}

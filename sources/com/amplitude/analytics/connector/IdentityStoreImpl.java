package com.amplitude.analytics.connector;

import com.amplitude.analytics.connector.IdentityStore;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u000e\u001a\u00020\n2\u0016\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\n0\tj\u0002`\u000bH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0004H\u0016J \u0010\u0013\u001a\u00020\n2\u0016\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\n0\tj\u0002`\u000bH\u0016J\u0010\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0004H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R$\u0010\u0007\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\n0\tj\u0002`\u000b0\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/amplitude/analytics/connector/IdentityStoreImpl;", "Lcom/amplitude/analytics/connector/IdentityStore;", "()V", "identity", "Lcom/amplitude/analytics/connector/Identity;", "identityLock", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "listeners", "", "Lkotlin/Function1;", "", "Lcom/amplitude/analytics/connector/IdentityListener;", "listenersLock", "", "addIdentityListener", "listener", "editIdentity", "Lcom/amplitude/analytics/connector/IdentityStore$Editor;", "getIdentity", "removeIdentityListener", "setIdentity", "analytics-connector_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* compiled from: IdentityStore.kt */
public final class IdentityStoreImpl implements IdentityStore {
    private Identity identity = new Identity((String) null, (String) null, (Map) null, 7, (DefaultConstructorMarker) null);
    private final ReentrantReadWriteLock identityLock = new ReentrantReadWriteLock(true);
    private final Set<Function1<Identity, Unit>> listeners = new LinkedHashSet();
    private final Object listenersLock = new Object();

    public IdentityStore.Editor editIdentity() {
        return new IdentityStoreImpl$editIdentity$1(getIdentity(), this);
    }

    /*  JADX ERROR: StackOverflow in pass: MarkFinallyVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public void setIdentity(com.amplitude.analytics.connector.Identity r7) {
        /*
            r6 = this;
            java.lang.String r0 = "identity"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            com.amplitude.analytics.connector.Identity r0 = r6.getIdentity()
            java.util.concurrent.locks.ReentrantReadWriteLock r1 = r6.identityLock
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r2 = r1.readLock()
            int r3 = r1.getWriteHoldCount()
            r4 = 0
            if (r3 != 0) goto L_0x001b
            int r3 = r1.getReadHoldCount()
            goto L_0x001c
        L_0x001b:
            r3 = r4
        L_0x001c:
            r5 = r4
        L_0x001d:
            if (r5 >= r3) goto L_0x0025
            r2.unlock()
            int r5 = r5 + 1
            goto L_0x001d
        L_0x0025:
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r1 = r1.writeLock()
            r1.lock()
            r6.identity = r7     // Catch:{ all -> 0x0065 }
            kotlin.Unit r5 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0065 }
        L_0x0030:
            if (r4 >= r3) goto L_0x0038
            r2.lock()
            int r4 = r4 + 1
            goto L_0x0030
        L_0x0038:
            r1.unlock()
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r0)
            if (r0 != 0) goto L_0x0064
            java.lang.Object r0 = r6.listenersLock
            monitor-enter(r0)
            java.util.Set<kotlin.jvm.functions.Function1<com.amplitude.analytics.connector.Identity, kotlin.Unit>> r1 = r6.listeners     // Catch:{ all -> 0x0061 }
            java.lang.Iterable r1 = (java.lang.Iterable) r1     // Catch:{ all -> 0x0061 }
            java.util.Set r1 = kotlin.collections.CollectionsKt.toSet(r1)     // Catch:{ all -> 0x0061 }
            monitor-exit(r0)
            java.util.Iterator r0 = r1.iterator()
        L_0x0051:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0064
            java.lang.Object r1 = r0.next()
            kotlin.jvm.functions.Function1 r1 = (kotlin.jvm.functions.Function1) r1
            r1.invoke(r7)
            goto L_0x0051
        L_0x0061:
            r7 = move-exception
            monitor-exit(r0)
            throw r7
        L_0x0064:
            return
        L_0x0065:
            r7 = move-exception
        L_0x0066:
            if (r4 >= r3) goto L_0x006e
            r2.lock()
            int r4 = r4 + 1
            goto L_0x0066
        L_0x006e:
            r1.unlock()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amplitude.analytics.connector.IdentityStoreImpl.setIdentity(com.amplitude.analytics.connector.Identity):void");
    }

    public Identity getIdentity() {
        ReentrantReadWriteLock.ReadLock readLock = this.identityLock.readLock();
        readLock.lock();
        try {
            return this.identity;
        } finally {
            readLock.unlock();
        }
    }

    public void addIdentityListener(Function1<? super Identity, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        synchronized (this.listenersLock) {
            this.listeners.add(function1);
        }
    }

    public void removeIdentityListener(Function1<? super Identity, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        synchronized (this.listenersLock) {
            this.listeners.remove(function1);
        }
    }
}

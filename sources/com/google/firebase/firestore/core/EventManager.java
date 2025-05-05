package com.google.firebase.firestore.core;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenSource;
import com.google.firebase.firestore.core.SyncEngine;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Util;
import io.grpc.Status;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class EventManager implements SyncEngine.SyncEngineCallback {
    private OnlineState onlineState = OnlineState.UNKNOWN;
    private final Map<Query, QueryListenersInfo> queries;
    private final Set<EventListener<Void>> snapshotsInSyncListeners = new HashSet();
    private final SyncEngine syncEngine;

    public static class ListenOptions {
        public boolean includeDocumentMetadataChanges;
        public boolean includeQueryMetadataChanges;
        public ListenSource source = ListenSource.DEFAULT;
        public boolean waitForSyncWhenOnline;
    }

    private enum ListenerRemovalAction {
        TERMINATE_LOCAL_LISTEN_AND_REQUIRE_WATCH_DISCONNECTION,
        TERMINATE_LOCAL_LISTEN_ONLY,
        REQUIRE_WATCH_DISCONNECTION_ONLY,
        NO_ACTION_REQUIRED
    }

    private enum ListenerSetupAction {
        INITIALIZE_LOCAL_LISTEN_AND_REQUIRE_WATCH_CONNECTION,
        INITIALIZE_LOCAL_LISTEN_ONLY,
        REQUIRE_WATCH_CONNECTION_ONLY,
        NO_ACTION_REQUIRED
    }

    private static class QueryListenersInfo {
        /* access modifiers changed from: private */
        public final List<QueryListener> listeners = new ArrayList();
        /* access modifiers changed from: private */
        public int targetId;
        /* access modifiers changed from: private */
        public ViewSnapshot viewSnapshot;

        QueryListenersInfo() {
        }

        /* access modifiers changed from: package-private */
        public boolean hasRemoteListeners() {
            for (QueryListener listensToRemoteStore : this.listeners) {
                if (listensToRemoteStore.listensToRemoteStore()) {
                    return true;
                }
            }
            return false;
        }
    }

    public EventManager(SyncEngine syncEngine2) {
        this.syncEngine = syncEngine2;
        this.queries = new HashMap();
        syncEngine2.setCallback(this);
    }

    public int addQueryListener(QueryListener queryListener) {
        Query query = queryListener.getQuery();
        ListenerSetupAction listenerSetupAction = ListenerSetupAction.NO_ACTION_REQUIRED;
        QueryListenersInfo queryListenersInfo = this.queries.get(query);
        if (queryListenersInfo == null) {
            queryListenersInfo = new QueryListenersInfo();
            this.queries.put(query, queryListenersInfo);
            listenerSetupAction = queryListener.listensToRemoteStore() ? ListenerSetupAction.INITIALIZE_LOCAL_LISTEN_AND_REQUIRE_WATCH_CONNECTION : ListenerSetupAction.INITIALIZE_LOCAL_LISTEN_ONLY;
        } else if (!queryListenersInfo.hasRemoteListeners() && queryListener.listensToRemoteStore()) {
            listenerSetupAction = ListenerSetupAction.REQUIRE_WATCH_CONNECTION_ONLY;
        }
        queryListenersInfo.listeners.add(queryListener);
        Assert.hardAssert(!queryListener.onOnlineStateChanged(this.onlineState), "onOnlineStateChanged() shouldn't raise an event for brand-new listeners.", new Object[0]);
        if (queryListenersInfo.viewSnapshot != null && queryListener.onViewSnapshot(queryListenersInfo.viewSnapshot)) {
            raiseSnapshotsInSyncEvent();
        }
        int i = AnonymousClass1.$SwitchMap$com$google$firebase$firestore$core$EventManager$ListenerSetupAction[listenerSetupAction.ordinal()];
        if (i == 1) {
            int unused = queryListenersInfo.targetId = this.syncEngine.listen(query, true);
        } else if (i == 2) {
            int unused2 = queryListenersInfo.targetId = this.syncEngine.listen(query, false);
        } else if (i == 3) {
            this.syncEngine.listenToRemoteStore(query);
        }
        return queryListenersInfo.targetId;
    }

    public void removeQueryListener(QueryListener queryListener) {
        ListenerRemovalAction listenerRemovalAction;
        Query query = queryListener.getQuery();
        QueryListenersInfo queryListenersInfo = this.queries.get(query);
        ListenerRemovalAction listenerRemovalAction2 = ListenerRemovalAction.NO_ACTION_REQUIRED;
        if (queryListenersInfo != null) {
            queryListenersInfo.listeners.remove(queryListener);
            if (queryListenersInfo.listeners.isEmpty()) {
                if (queryListener.listensToRemoteStore()) {
                    listenerRemovalAction = ListenerRemovalAction.TERMINATE_LOCAL_LISTEN_AND_REQUIRE_WATCH_DISCONNECTION;
                } else {
                    listenerRemovalAction = ListenerRemovalAction.TERMINATE_LOCAL_LISTEN_ONLY;
                }
                listenerRemovalAction2 = listenerRemovalAction;
            } else if (!queryListenersInfo.hasRemoteListeners() && queryListener.listensToRemoteStore()) {
                listenerRemovalAction2 = ListenerRemovalAction.REQUIRE_WATCH_DISCONNECTION_ONLY;
            }
            int i = AnonymousClass1.$SwitchMap$com$google$firebase$firestore$core$EventManager$ListenerRemovalAction[listenerRemovalAction2.ordinal()];
            if (i == 1) {
                this.queries.remove(query);
                this.syncEngine.stopListening(query, true);
            } else if (i == 2) {
                this.queries.remove(query);
                this.syncEngine.stopListening(query, false);
            } else if (i == 3) {
                this.syncEngine.stopListeningToRemoteStore(query);
            }
        }
    }

    /* renamed from: com.google.firebase.firestore.core.EventManager$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$core$EventManager$ListenerRemovalAction;
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$core$EventManager$ListenerSetupAction;

        /* JADX WARNING: Can't wrap try/catch for region: R(15:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|16|17|18|20) */
        /* JADX WARNING: Can't wrap try/catch for region: R(17:0|1|2|3|5|6|7|9|10|11|13|14|15|16|17|18|20) */
        /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0039 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0043 */
        static {
            /*
                com.google.firebase.firestore.core.EventManager$ListenerRemovalAction[] r0 = com.google.firebase.firestore.core.EventManager.ListenerRemovalAction.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$firebase$firestore$core$EventManager$ListenerRemovalAction = r0
                r1 = 1
                com.google.firebase.firestore.core.EventManager$ListenerRemovalAction r2 = com.google.firebase.firestore.core.EventManager.ListenerRemovalAction.TERMINATE_LOCAL_LISTEN_AND_REQUIRE_WATCH_DISCONNECTION     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$google$firebase$firestore$core$EventManager$ListenerRemovalAction     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.firebase.firestore.core.EventManager$ListenerRemovalAction r3 = com.google.firebase.firestore.core.EventManager.ListenerRemovalAction.TERMINATE_LOCAL_LISTEN_ONLY     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$com$google$firebase$firestore$core$EventManager$ListenerRemovalAction     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.firebase.firestore.core.EventManager$ListenerRemovalAction r4 = com.google.firebase.firestore.core.EventManager.ListenerRemovalAction.REQUIRE_WATCH_DISCONNECTION_ONLY     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                com.google.firebase.firestore.core.EventManager$ListenerSetupAction[] r3 = com.google.firebase.firestore.core.EventManager.ListenerSetupAction.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$com$google$firebase$firestore$core$EventManager$ListenerSetupAction = r3
                com.google.firebase.firestore.core.EventManager$ListenerSetupAction r4 = com.google.firebase.firestore.core.EventManager.ListenerSetupAction.INITIALIZE_LOCAL_LISTEN_AND_REQUIRE_WATCH_CONNECTION     // Catch:{ NoSuchFieldError -> 0x0039 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0039 }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0039 }
            L_0x0039:
                int[] r1 = $SwitchMap$com$google$firebase$firestore$core$EventManager$ListenerSetupAction     // Catch:{ NoSuchFieldError -> 0x0043 }
                com.google.firebase.firestore.core.EventManager$ListenerSetupAction r3 = com.google.firebase.firestore.core.EventManager.ListenerSetupAction.INITIALIZE_LOCAL_LISTEN_ONLY     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r1[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$core$EventManager$ListenerSetupAction     // Catch:{ NoSuchFieldError -> 0x004d }
                com.google.firebase.firestore.core.EventManager$ListenerSetupAction r1 = com.google.firebase.firestore.core.EventManager.ListenerSetupAction.REQUIRE_WATCH_CONNECTION_ONLY     // Catch:{ NoSuchFieldError -> 0x004d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004d }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004d }
            L_0x004d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.core.EventManager.AnonymousClass1.<clinit>():void");
        }
    }

    public void addSnapshotsInSyncListener(EventListener<Void> eventListener) {
        this.snapshotsInSyncListeners.add(eventListener);
        eventListener.onEvent(null, (FirebaseFirestoreException) null);
    }

    public void removeSnapshotsInSyncListener(EventListener<Void> eventListener) {
        this.snapshotsInSyncListeners.remove(eventListener);
    }

    private void raiseSnapshotsInSyncEvent() {
        for (EventListener<Void> onEvent : this.snapshotsInSyncListeners) {
            onEvent.onEvent(null, (FirebaseFirestoreException) null);
        }
    }

    public void onViewSnapshots(List<ViewSnapshot> list) {
        boolean z = false;
        for (ViewSnapshot next : list) {
            QueryListenersInfo queryListenersInfo = this.queries.get(next.getQuery());
            if (queryListenersInfo != null) {
                for (QueryListener onViewSnapshot : queryListenersInfo.listeners) {
                    if (onViewSnapshot.onViewSnapshot(next)) {
                        z = true;
                    }
                }
                ViewSnapshot unused = queryListenersInfo.viewSnapshot = next;
            }
        }
        if (z) {
            raiseSnapshotsInSyncEvent();
        }
    }

    public void onError(Query query, Status status) {
        QueryListenersInfo queryListenersInfo = this.queries.get(query);
        if (queryListenersInfo != null) {
            for (QueryListener onError : queryListenersInfo.listeners) {
                onError.onError(Util.exceptionFromStatus(status));
            }
        }
        this.queries.remove(query);
    }

    public void handleOnlineStateChange(OnlineState onlineState2) {
        this.onlineState = onlineState2;
        boolean z = false;
        for (QueryListenersInfo access$000 : this.queries.values()) {
            for (QueryListener onOnlineStateChanged : access$000.listeners) {
                if (onOnlineStateChanged.onOnlineStateChanged(onlineState2)) {
                    z = true;
                }
            }
        }
        if (z) {
            raiseSnapshotsInSyncEvent();
        }
    }
}

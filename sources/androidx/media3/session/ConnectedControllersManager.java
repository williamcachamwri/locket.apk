package androidx.media3.session;

import androidx.collection.ArrayMap;
import androidx.media3.common.Player;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.session.MediaSession;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.atomic.AtomicBoolean;

final class ConnectedControllersManager<T> {
    private final ArrayMap<T, MediaSession.ControllerInfo> controllerInfoMap = new ArrayMap<>();
    private final ArrayMap<MediaSession.ControllerInfo, ConnectedControllerRecord<T>> controllerRecords = new ArrayMap<>();
    private final Object lock = new Object();
    private final WeakReference<MediaSessionImpl> sessionImpl;

    public interface AsyncCommand {
        ListenableFuture<Void> run();
    }

    public ConnectedControllersManager(MediaSessionImpl mediaSessionImpl) {
        this.sessionImpl = new WeakReference<>(mediaSessionImpl);
    }

    public void addController(T t, MediaSession.ControllerInfo controllerInfo, SessionCommands sessionCommands, Player.Commands commands) {
        synchronized (this.lock) {
            MediaSession.ControllerInfo controller = getController(t);
            if (controller == null) {
                this.controllerInfoMap.put(t, controllerInfo);
                this.controllerRecords.put(controllerInfo, new ConnectedControllerRecord(t, new SequencedFutureManager(), sessionCommands, commands));
            } else {
                ConnectedControllerRecord connectedControllerRecord = (ConnectedControllerRecord) Assertions.checkStateNotNull(this.controllerRecords.get(controller));
                connectedControllerRecord.sessionCommands = sessionCommands;
                connectedControllerRecord.playerCommands = commands;
            }
        }
    }

    public void updateCommandsFromSession(MediaSession.ControllerInfo controllerInfo, SessionCommands sessionCommands, Player.Commands commands) {
        synchronized (this.lock) {
            ConnectedControllerRecord connectedControllerRecord = this.controllerRecords.get(controllerInfo);
            if (connectedControllerRecord != null) {
                connectedControllerRecord.sessionCommands = sessionCommands;
                connectedControllerRecord.playerCommands = commands;
            }
        }
    }

    public Player.Commands getAvailablePlayerCommands(MediaSession.ControllerInfo controllerInfo) {
        synchronized (this.lock) {
            ConnectedControllerRecord connectedControllerRecord = this.controllerRecords.get(controllerInfo);
            if (connectedControllerRecord == null) {
                return null;
            }
            Player.Commands commands = connectedControllerRecord.playerCommands;
            return commands;
        }
    }

    public void removeController(T t) {
        MediaSession.ControllerInfo controller = getController(t);
        if (controller != null) {
            removeController(controller);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0024, code lost:
        if (r0 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002a, code lost:
        if (r0.isReleased() == false) goto L_0x002d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        androidx.media3.common.util.Util.postOrRun(r0.getApplicationHandler(), new androidx.media3.session.ConnectedControllersManager$$ExternalSyntheticLambda0(r0, r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0017, code lost:
        r1.sequencedFutureManager.release();
        r0 = (androidx.media3.session.MediaSessionImpl) r4.sessionImpl.get();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void removeController(androidx.media3.session.MediaSession.ControllerInfo r5) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.lock
            monitor-enter(r0)
            androidx.collection.ArrayMap<androidx.media3.session.MediaSession$ControllerInfo, androidx.media3.session.ConnectedControllersManager$ConnectedControllerRecord<T>> r1 = r4.controllerRecords     // Catch:{ all -> 0x003a }
            java.lang.Object r1 = r1.remove(r5)     // Catch:{ all -> 0x003a }
            androidx.media3.session.ConnectedControllersManager$ConnectedControllerRecord r1 = (androidx.media3.session.ConnectedControllersManager.ConnectedControllerRecord) r1     // Catch:{ all -> 0x003a }
            if (r1 != 0) goto L_0x000f
            monitor-exit(r0)     // Catch:{ all -> 0x003a }
            return
        L_0x000f:
            androidx.collection.ArrayMap<T, androidx.media3.session.MediaSession$ControllerInfo> r2 = r4.controllerInfoMap     // Catch:{ all -> 0x003a }
            T r3 = r1.controllerKey     // Catch:{ all -> 0x003a }
            r2.remove(r3)     // Catch:{ all -> 0x003a }
            monitor-exit(r0)     // Catch:{ all -> 0x003a }
            androidx.media3.session.SequencedFutureManager r0 = r1.sequencedFutureManager
            r0.release()
            java.lang.ref.WeakReference<androidx.media3.session.MediaSessionImpl> r0 = r4.sessionImpl
            java.lang.Object r0 = r0.get()
            androidx.media3.session.MediaSessionImpl r0 = (androidx.media3.session.MediaSessionImpl) r0
            if (r0 == 0) goto L_0x0039
            boolean r1 = r0.isReleased()
            if (r1 == 0) goto L_0x002d
            goto L_0x0039
        L_0x002d:
            android.os.Handler r1 = r0.getApplicationHandler()
            androidx.media3.session.ConnectedControllersManager$$ExternalSyntheticLambda0 r2 = new androidx.media3.session.ConnectedControllersManager$$ExternalSyntheticLambda0
            r2.<init>(r0, r5)
            androidx.media3.common.util.Util.postOrRun(r1, r2)
        L_0x0039:
            return
        L_0x003a:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x003a }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.session.ConnectedControllersManager.removeController(androidx.media3.session.MediaSession$ControllerInfo):void");
    }

    static /* synthetic */ void lambda$removeController$0(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo) {
        if (!mediaSessionImpl.isReleased()) {
            mediaSessionImpl.onDisconnectedOnHandler(controllerInfo);
        }
    }

    public ImmutableList<MediaSession.ControllerInfo> getConnectedControllers() {
        ImmutableList<MediaSession.ControllerInfo> copyOf;
        synchronized (this.lock) {
            copyOf = ImmutableList.copyOf(this.controllerInfoMap.values());
        }
        return copyOf;
    }

    public boolean isConnected(MediaSession.ControllerInfo controllerInfo) {
        boolean z;
        synchronized (this.lock) {
            z = this.controllerRecords.get(controllerInfo) != null;
        }
        return z;
    }

    public SequencedFutureManager getSequencedFutureManager(MediaSession.ControllerInfo controllerInfo) {
        ConnectedControllerRecord connectedControllerRecord;
        synchronized (this.lock) {
            connectedControllerRecord = this.controllerRecords.get(controllerInfo);
        }
        if (connectedControllerRecord != null) {
            return connectedControllerRecord.sequencedFutureManager;
        }
        return null;
    }

    public SequencedFutureManager getSequencedFutureManager(T t) {
        ConnectedControllerRecord connectedControllerRecord;
        synchronized (this.lock) {
            MediaSession.ControllerInfo controller = getController(t);
            connectedControllerRecord = controller != null ? this.controllerRecords.get(controller) : null;
        }
        if (connectedControllerRecord != null) {
            return connectedControllerRecord.sequencedFutureManager;
        }
        return null;
    }

    public boolean isSessionCommandAvailable(MediaSession.ControllerInfo controllerInfo, SessionCommand sessionCommand) {
        ConnectedControllerRecord connectedControllerRecord;
        synchronized (this.lock) {
            connectedControllerRecord = this.controllerRecords.get(controllerInfo);
        }
        return connectedControllerRecord != null && connectedControllerRecord.sessionCommands.contains(sessionCommand);
    }

    public boolean isSessionCommandAvailable(MediaSession.ControllerInfo controllerInfo, int i) {
        ConnectedControllerRecord connectedControllerRecord;
        synchronized (this.lock) {
            connectedControllerRecord = this.controllerRecords.get(controllerInfo);
        }
        return connectedControllerRecord != null && connectedControllerRecord.sessionCommands.contains(i);
    }

    public boolean isPlayerCommandAvailable(MediaSession.ControllerInfo controllerInfo, int i) {
        ConnectedControllerRecord connectedControllerRecord;
        synchronized (this.lock) {
            connectedControllerRecord = this.controllerRecords.get(controllerInfo);
        }
        MediaSessionImpl mediaSessionImpl = (MediaSessionImpl) this.sessionImpl.get();
        return connectedControllerRecord != null && connectedControllerRecord.playerCommands.contains(i) && mediaSessionImpl != null && mediaSessionImpl.getPlayerWrapper().getAvailableCommands().contains(i);
    }

    public MediaSession.ControllerInfo getController(T t) {
        MediaSession.ControllerInfo controllerInfo;
        synchronized (this.lock) {
            controllerInfo = this.controllerInfoMap.get(t);
        }
        return controllerInfo;
    }

    public void addToCommandQueue(MediaSession.ControllerInfo controllerInfo, int i, AsyncCommand asyncCommand) {
        synchronized (this.lock) {
            ConnectedControllerRecord connectedControllerRecord = this.controllerRecords.get(controllerInfo);
            if (connectedControllerRecord != null) {
                connectedControllerRecord.commandQueuePlayerCommands = connectedControllerRecord.commandQueuePlayerCommands.buildUpon().add(i).build();
                connectedControllerRecord.commandQueue.add(asyncCommand);
            }
        }
    }

    public void flushCommandQueue(MediaSession.ControllerInfo controllerInfo) {
        synchronized (this.lock) {
            ConnectedControllerRecord connectedControllerRecord = this.controllerRecords.get(controllerInfo);
            if (connectedControllerRecord != null) {
                Player.Commands commands = connectedControllerRecord.commandQueuePlayerCommands;
                connectedControllerRecord.commandQueuePlayerCommands = Player.Commands.EMPTY;
                connectedControllerRecord.commandQueue.add(new ConnectedControllersManager$$ExternalSyntheticLambda2(this, controllerInfo, commands));
                if (!connectedControllerRecord.commandQueueIsFlushing) {
                    connectedControllerRecord.commandQueueIsFlushing = true;
                    flushCommandQueue(connectedControllerRecord);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$flushCommandQueue$1$androidx-media3-session-ConnectedControllersManager  reason: not valid java name */
    public /* synthetic */ ListenableFuture m911lambda$flushCommandQueue$1$androidxmedia3sessionConnectedControllersManager(MediaSession.ControllerInfo controllerInfo, Player.Commands commands) {
        MediaSessionImpl mediaSessionImpl = (MediaSessionImpl) this.sessionImpl.get();
        if (mediaSessionImpl != null) {
            mediaSessionImpl.onPlayerInteractionFinishedOnHandler(controllerInfo, commands);
        }
        return Futures.immediateVoidFuture();
    }

    private void flushCommandQueue(ConnectedControllerRecord<T> connectedControllerRecord) {
        MediaSessionImpl mediaSessionImpl = (MediaSessionImpl) this.sessionImpl.get();
        if (mediaSessionImpl != null) {
            AtomicBoolean atomicBoolean = new AtomicBoolean(true);
            while (atomicBoolean.get()) {
                atomicBoolean.set(false);
                AsyncCommand poll = connectedControllerRecord.commandQueue.poll();
                if (poll == null) {
                    connectedControllerRecord.commandQueueIsFlushing = false;
                    return;
                }
                AtomicBoolean atomicBoolean2 = new AtomicBoolean(true);
                Util.postOrRun(mediaSessionImpl.getApplicationHandler(), mediaSessionImpl.callWithControllerForCurrentRequestSet(getController(connectedControllerRecord.controllerKey), new ConnectedControllersManager$$ExternalSyntheticLambda1(this, poll, atomicBoolean2, connectedControllerRecord, atomicBoolean)));
                atomicBoolean2.set(false);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$flushCommandQueue$3$androidx-media3-session-ConnectedControllersManager  reason: not valid java name */
    public /* synthetic */ void m913lambda$flushCommandQueue$3$androidxmedia3sessionConnectedControllersManager(AsyncCommand asyncCommand, AtomicBoolean atomicBoolean, ConnectedControllerRecord connectedControllerRecord, AtomicBoolean atomicBoolean2) {
        asyncCommand.run().addListener(new ConnectedControllersManager$$ExternalSyntheticLambda3(this, atomicBoolean, connectedControllerRecord, atomicBoolean2), MoreExecutors.directExecutor());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$flushCommandQueue$2$androidx-media3-session-ConnectedControllersManager  reason: not valid java name */
    public /* synthetic */ void m912lambda$flushCommandQueue$2$androidxmedia3sessionConnectedControllersManager(AtomicBoolean atomicBoolean, ConnectedControllerRecord connectedControllerRecord, AtomicBoolean atomicBoolean2) {
        synchronized (this.lock) {
            if (!atomicBoolean.get()) {
                flushCommandQueue(connectedControllerRecord);
            } else {
                atomicBoolean2.set(true);
            }
        }
    }

    private static final class ConnectedControllerRecord<T> {
        public final Deque<AsyncCommand> commandQueue = new ArrayDeque();
        public boolean commandQueueIsFlushing;
        public Player.Commands commandQueuePlayerCommands = Player.Commands.EMPTY;
        public final T controllerKey;
        public Player.Commands playerCommands;
        public final SequencedFutureManager sequencedFutureManager;
        public SessionCommands sessionCommands;

        public ConnectedControllerRecord(T t, SequencedFutureManager sequencedFutureManager2, SessionCommands sessionCommands2, Player.Commands commands) {
            this.controllerKey = t;
            this.sequencedFutureManager = sequencedFutureManager2;
            this.sessionCommands = sessionCommands2;
            this.playerCommands = commands;
        }
    }
}

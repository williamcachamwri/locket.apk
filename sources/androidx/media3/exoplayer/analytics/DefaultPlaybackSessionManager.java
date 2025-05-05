package androidx.media3.exoplayer.analytics;

import android.util.Base64;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.analytics.PlaybackSessionManager;
import androidx.media3.exoplayer.source.MediaSource;
import com.google.common.base.Supplier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

public final class DefaultPlaybackSessionManager implements PlaybackSessionManager {
    public static final Supplier<String> DEFAULT_SESSION_ID_GENERATOR = new DefaultPlaybackSessionManager$$ExternalSyntheticLambda0();
    private static final Random RANDOM = new Random();
    private static final int SESSION_ID_LENGTH = 12;
    private String currentSessionId;
    private Timeline currentTimeline;
    private long lastRemovedCurrentWindowSequenceNumber;
    private PlaybackSessionManager.Listener listener;
    /* access modifiers changed from: private */
    public final Timeline.Period period;
    private final Supplier<String> sessionIdGenerator;
    private final HashMap<String, SessionDescriptor> sessions;
    /* access modifiers changed from: private */
    public final Timeline.Window window;

    public DefaultPlaybackSessionManager() {
        this(DEFAULT_SESSION_ID_GENERATOR);
    }

    public DefaultPlaybackSessionManager(Supplier<String> supplier) {
        this.sessionIdGenerator = supplier;
        this.window = new Timeline.Window();
        this.period = new Timeline.Period();
        this.sessions = new HashMap<>();
        this.currentTimeline = Timeline.EMPTY;
        this.lastRemovedCurrentWindowSequenceNumber = -1;
    }

    public void setListener(PlaybackSessionManager.Listener listener2) {
        this.listener = listener2;
    }

    public synchronized String getSessionForMediaPeriodId(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId) {
        return getOrAddSession(timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period).windowIndex, mediaPeriodId).sessionId;
    }

    public synchronized boolean belongsToSession(AnalyticsListener.EventTime eventTime, String str) {
        SessionDescriptor sessionDescriptor = this.sessions.get(str);
        if (sessionDescriptor == null) {
            return false;
        }
        sessionDescriptor.maybeSetWindowSequenceNumber(eventTime.windowIndex, eventTime.mediaPeriodId);
        return sessionDescriptor.belongsToSession(eventTime.windowIndex, eventTime.mediaPeriodId);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0119, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00e3  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00f5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void updateSessions(androidx.media3.exoplayer.analytics.AnalyticsListener.EventTime r25) {
        /*
            r24 = this;
            r1 = r24
            r0 = r25
            monitor-enter(r24)
            androidx.media3.exoplayer.analytics.PlaybackSessionManager$Listener r2 = r1.listener     // Catch:{ all -> 0x011a }
            androidx.media3.common.util.Assertions.checkNotNull(r2)     // Catch:{ all -> 0x011a }
            androidx.media3.common.Timeline r2 = r0.timeline     // Catch:{ all -> 0x011a }
            boolean r2 = r2.isEmpty()     // Catch:{ all -> 0x011a }
            if (r2 == 0) goto L_0x0014
            monitor-exit(r24)
            return
        L_0x0014:
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r2 = r0.mediaPeriodId     // Catch:{ all -> 0x011a }
            if (r2 == 0) goto L_0x0046
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r2 = r0.mediaPeriodId     // Catch:{ all -> 0x011a }
            long r2 = r2.windowSequenceNumber     // Catch:{ all -> 0x011a }
            long r4 = r24.getMinWindowSequenceNumber()     // Catch:{ all -> 0x011a }
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 >= 0) goto L_0x0026
            monitor-exit(r24)
            return
        L_0x0026:
            java.util.HashMap<java.lang.String, androidx.media3.exoplayer.analytics.DefaultPlaybackSessionManager$SessionDescriptor> r2 = r1.sessions     // Catch:{ all -> 0x011a }
            java.lang.String r3 = r1.currentSessionId     // Catch:{ all -> 0x011a }
            java.lang.Object r2 = r2.get(r3)     // Catch:{ all -> 0x011a }
            androidx.media3.exoplayer.analytics.DefaultPlaybackSessionManager$SessionDescriptor r2 = (androidx.media3.exoplayer.analytics.DefaultPlaybackSessionManager.SessionDescriptor) r2     // Catch:{ all -> 0x011a }
            if (r2 == 0) goto L_0x0046
            long r3 = r2.windowSequenceNumber     // Catch:{ all -> 0x011a }
            r5 = -1
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 != 0) goto L_0x0046
            int r2 = r2.windowIndex     // Catch:{ all -> 0x011a }
            int r3 = r0.windowIndex     // Catch:{ all -> 0x011a }
            if (r2 == r3) goto L_0x0046
            monitor-exit(r24)
            return
        L_0x0046:
            int r2 = r0.windowIndex     // Catch:{ all -> 0x011a }
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r3 = r0.mediaPeriodId     // Catch:{ all -> 0x011a }
            androidx.media3.exoplayer.analytics.DefaultPlaybackSessionManager$SessionDescriptor r2 = r1.getOrAddSession(r2, r3)     // Catch:{ all -> 0x011a }
            java.lang.String r3 = r1.currentSessionId     // Catch:{ all -> 0x011a }
            if (r3 != 0) goto L_0x0058
            java.lang.String r3 = r2.sessionId     // Catch:{ all -> 0x011a }
            r1.currentSessionId = r3     // Catch:{ all -> 0x011a }
        L_0x0058:
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r3 = r0.mediaPeriodId     // Catch:{ all -> 0x011a }
            r4 = 1
            if (r3 == 0) goto L_0x00db
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r3 = r0.mediaPeriodId     // Catch:{ all -> 0x011a }
            boolean r3 = r3.isAd()     // Catch:{ all -> 0x011a }
            if (r3 == 0) goto L_0x00db
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r10 = new androidx.media3.exoplayer.source.MediaSource$MediaPeriodId     // Catch:{ all -> 0x011a }
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r3 = r0.mediaPeriodId     // Catch:{ all -> 0x011a }
            java.lang.Object r3 = r3.periodUid     // Catch:{ all -> 0x011a }
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r5 = r0.mediaPeriodId     // Catch:{ all -> 0x011a }
            long r5 = r5.windowSequenceNumber     // Catch:{ all -> 0x011a }
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r7 = r0.mediaPeriodId     // Catch:{ all -> 0x011a }
            int r7 = r7.adGroupIndex     // Catch:{ all -> 0x011a }
            r10.<init>(r3, r5, r7)     // Catch:{ all -> 0x011a }
            int r3 = r0.windowIndex     // Catch:{ all -> 0x011a }
            androidx.media3.exoplayer.analytics.DefaultPlaybackSessionManager$SessionDescriptor r3 = r1.getOrAddSession(r3, r10)     // Catch:{ all -> 0x011a }
            boolean r5 = r3.isCreated     // Catch:{ all -> 0x011a }
            if (r5 != 0) goto L_0x00db
            boolean unused = r3.isCreated = r4     // Catch:{ all -> 0x011a }
            androidx.media3.common.Timeline r5 = r0.timeline     // Catch:{ all -> 0x011a }
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r6 = r0.mediaPeriodId     // Catch:{ all -> 0x011a }
            java.lang.Object r6 = r6.periodUid     // Catch:{ all -> 0x011a }
            androidx.media3.common.Timeline$Period r7 = r1.period     // Catch:{ all -> 0x011a }
            r5.getPeriodByUid(r6, r7)     // Catch:{ all -> 0x011a }
            androidx.media3.common.Timeline$Period r5 = r1.period     // Catch:{ all -> 0x011a }
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r6 = r0.mediaPeriodId     // Catch:{ all -> 0x011a }
            int r6 = r6.adGroupIndex     // Catch:{ all -> 0x011a }
            long r5 = r5.getAdGroupTimeUs(r6)     // Catch:{ all -> 0x011a }
            long r5 = androidx.media3.common.util.Util.usToMs(r5)     // Catch:{ all -> 0x011a }
            androidx.media3.common.Timeline$Period r7 = r1.period     // Catch:{ all -> 0x011a }
            long r7 = r7.getPositionInWindowMs()     // Catch:{ all -> 0x011a }
            long r5 = r5 + r7
            r7 = 0
            long r11 = java.lang.Math.max(r7, r5)     // Catch:{ all -> 0x011a }
            androidx.media3.exoplayer.analytics.AnalyticsListener$EventTime r15 = new androidx.media3.exoplayer.analytics.AnalyticsListener$EventTime     // Catch:{ all -> 0x011a }
            long r6 = r0.realtimeMs     // Catch:{ all -> 0x011a }
            androidx.media3.common.Timeline r8 = r0.timeline     // Catch:{ all -> 0x011a }
            int r9 = r0.windowIndex     // Catch:{ all -> 0x011a }
            androidx.media3.common.Timeline r13 = r0.currentTimeline     // Catch:{ all -> 0x011a }
            int r14 = r0.currentWindowIndex     // Catch:{ all -> 0x011a }
            androidx.media3.exoplayer.source.MediaSource$MediaPeriodId r5 = r0.currentMediaPeriodId     // Catch:{ all -> 0x011a }
            r16 = r5
            long r4 = r0.currentPlaybackPositionMs     // Catch:{ all -> 0x011a }
            r20 = r2
            r21 = r3
            long r2 = r0.totalBufferedDurationMs     // Catch:{ all -> 0x011a }
            r22 = r4
            r4 = r16
            r16 = r22
            r5 = r15
            r0 = r15
            r15 = r4
            r18 = r2
            r5.<init>(r6, r8, r9, r10, r11, r13, r14, r15, r16, r18)     // Catch:{ all -> 0x011a }
            androidx.media3.exoplayer.analytics.PlaybackSessionManager$Listener r2 = r1.listener     // Catch:{ all -> 0x011a }
            java.lang.String r3 = r21.sessionId     // Catch:{ all -> 0x011a }
            r2.onSessionCreated(r0, r3)     // Catch:{ all -> 0x011a }
            goto L_0x00dd
        L_0x00db:
            r20 = r2
        L_0x00dd:
            boolean r0 = r20.isCreated     // Catch:{ all -> 0x011a }
            if (r0 != 0) goto L_0x00f5
            r0 = r20
            r2 = 1
            boolean unused = r0.isCreated = r2     // Catch:{ all -> 0x011a }
            androidx.media3.exoplayer.analytics.PlaybackSessionManager$Listener r2 = r1.listener     // Catch:{ all -> 0x011a }
            java.lang.String r3 = r0.sessionId     // Catch:{ all -> 0x011a }
            r4 = r25
            r2.onSessionCreated(r4, r3)     // Catch:{ all -> 0x011a }
            goto L_0x00f9
        L_0x00f5:
            r4 = r25
            r0 = r20
        L_0x00f9:
            java.lang.String r2 = r0.sessionId     // Catch:{ all -> 0x011a }
            java.lang.String r3 = r1.currentSessionId     // Catch:{ all -> 0x011a }
            boolean r2 = r2.equals(r3)     // Catch:{ all -> 0x011a }
            if (r2 == 0) goto L_0x0118
            boolean r2 = r0.isActive     // Catch:{ all -> 0x011a }
            if (r2 != 0) goto L_0x0118
            r2 = 1
            boolean unused = r0.isActive = r2     // Catch:{ all -> 0x011a }
            androidx.media3.exoplayer.analytics.PlaybackSessionManager$Listener r2 = r1.listener     // Catch:{ all -> 0x011a }
            java.lang.String r0 = r0.sessionId     // Catch:{ all -> 0x011a }
            r2.onSessionActive(r4, r0)     // Catch:{ all -> 0x011a }
        L_0x0118:
            monitor-exit(r24)
            return
        L_0x011a:
            r0 = move-exception
            monitor-exit(r24)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.analytics.DefaultPlaybackSessionManager.updateSessions(androidx.media3.exoplayer.analytics.AnalyticsListener$EventTime):void");
    }

    public synchronized void updateSessionsWithTimelineChange(AnalyticsListener.EventTime eventTime) {
        Assertions.checkNotNull(this.listener);
        Timeline timeline = this.currentTimeline;
        this.currentTimeline = eventTime.timeline;
        Iterator<SessionDescriptor> it = this.sessions.values().iterator();
        while (it.hasNext()) {
            SessionDescriptor next = it.next();
            if (!next.tryResolvingToNewTimeline(timeline, this.currentTimeline) || next.isFinishedAtEventTime(eventTime)) {
                it.remove();
                if (next.isCreated) {
                    if (next.sessionId.equals(this.currentSessionId)) {
                        clearCurrentSession(next);
                    }
                    this.listener.onSessionFinished(eventTime, next.sessionId, false);
                }
            }
        }
        updateCurrentSession(eventTime);
    }

    public synchronized void updateSessionsWithDiscontinuity(AnalyticsListener.EventTime eventTime, int i) {
        Assertions.checkNotNull(this.listener);
        boolean z = i == 0;
        Iterator<SessionDescriptor> it = this.sessions.values().iterator();
        while (it.hasNext()) {
            SessionDescriptor next = it.next();
            if (next.isFinishedAtEventTime(eventTime)) {
                it.remove();
                if (next.isCreated) {
                    boolean equals = next.sessionId.equals(this.currentSessionId);
                    boolean z2 = z && equals && next.isActive;
                    if (equals) {
                        clearCurrentSession(next);
                    }
                    this.listener.onSessionFinished(eventTime, next.sessionId, z2);
                }
            }
        }
        updateCurrentSession(eventTime);
    }

    public synchronized String getActiveSessionId() {
        return this.currentSessionId;
    }

    public synchronized void finishAllSessions(AnalyticsListener.EventTime eventTime) {
        PlaybackSessionManager.Listener listener2;
        String str = this.currentSessionId;
        if (str != null) {
            clearCurrentSession((SessionDescriptor) Assertions.checkNotNull(this.sessions.get(str)));
        }
        Iterator<SessionDescriptor> it = this.sessions.values().iterator();
        while (it.hasNext()) {
            SessionDescriptor next = it.next();
            it.remove();
            if (next.isCreated && (listener2 = this.listener) != null) {
                listener2.onSessionFinished(eventTime, next.sessionId, false);
            }
        }
    }

    @RequiresNonNull({"listener"})
    private void updateCurrentSession(AnalyticsListener.EventTime eventTime) {
        if (eventTime.timeline.isEmpty()) {
            String str = this.currentSessionId;
            if (str != null) {
                clearCurrentSession((SessionDescriptor) Assertions.checkNotNull(this.sessions.get(str)));
                return;
            }
            return;
        }
        SessionDescriptor sessionDescriptor = this.sessions.get(this.currentSessionId);
        SessionDescriptor orAddSession = getOrAddSession(eventTime.windowIndex, eventTime.mediaPeriodId);
        this.currentSessionId = orAddSession.sessionId;
        updateSessions(eventTime);
        if (eventTime.mediaPeriodId != null && eventTime.mediaPeriodId.isAd()) {
            if (sessionDescriptor == null || sessionDescriptor.windowSequenceNumber != eventTime.mediaPeriodId.windowSequenceNumber || sessionDescriptor.adMediaPeriodId == null || sessionDescriptor.adMediaPeriodId.adGroupIndex != eventTime.mediaPeriodId.adGroupIndex || sessionDescriptor.adMediaPeriodId.adIndexInAdGroup != eventTime.mediaPeriodId.adIndexInAdGroup) {
                this.listener.onAdPlaybackStarted(eventTime, getOrAddSession(eventTime.windowIndex, new MediaSource.MediaPeriodId(eventTime.mediaPeriodId.periodUid, eventTime.mediaPeriodId.windowSequenceNumber)).sessionId, orAddSession.sessionId);
            }
        }
    }

    private void clearCurrentSession(SessionDescriptor sessionDescriptor) {
        if (sessionDescriptor.windowSequenceNumber != -1) {
            this.lastRemovedCurrentWindowSequenceNumber = sessionDescriptor.windowSequenceNumber;
        }
        this.currentSessionId = null;
    }

    /* access modifiers changed from: private */
    public long getMinWindowSequenceNumber() {
        SessionDescriptor sessionDescriptor = this.sessions.get(this.currentSessionId);
        if (sessionDescriptor == null || sessionDescriptor.windowSequenceNumber == -1) {
            return this.lastRemovedCurrentWindowSequenceNumber + 1;
        }
        return sessionDescriptor.windowSequenceNumber;
    }

    private SessionDescriptor getOrAddSession(int i, MediaSource.MediaPeriodId mediaPeriodId) {
        int i2;
        SessionDescriptor sessionDescriptor = null;
        long j = Long.MAX_VALUE;
        for (SessionDescriptor next : this.sessions.values()) {
            next.maybeSetWindowSequenceNumber(i, mediaPeriodId);
            if (next.belongsToSession(i, mediaPeriodId)) {
                long access$100 = next.windowSequenceNumber;
                if (access$100 == -1 || access$100 < j) {
                    sessionDescriptor = next;
                    j = access$100;
                } else if (!(i2 != 0 || ((SessionDescriptor) Util.castNonNull(sessionDescriptor)).adMediaPeriodId == null || next.adMediaPeriodId == null)) {
                    sessionDescriptor = next;
                }
            }
        }
        if (sessionDescriptor != null) {
            return sessionDescriptor;
        }
        String str = this.sessionIdGenerator.get();
        SessionDescriptor sessionDescriptor2 = new SessionDescriptor(str, i, mediaPeriodId);
        this.sessions.put(str, sessionDescriptor2);
        return sessionDescriptor2;
    }

    /* access modifiers changed from: private */
    public static String generateDefaultSessionId() {
        byte[] bArr = new byte[12];
        RANDOM.nextBytes(bArr);
        return Base64.encodeToString(bArr, 10);
    }

    private final class SessionDescriptor {
        /* access modifiers changed from: private */
        public MediaSource.MediaPeriodId adMediaPeriodId;
        /* access modifiers changed from: private */
        public boolean isActive;
        /* access modifiers changed from: private */
        public boolean isCreated;
        /* access modifiers changed from: private */
        public final String sessionId;
        /* access modifiers changed from: private */
        public int windowIndex;
        /* access modifiers changed from: private */
        public long windowSequenceNumber;

        public SessionDescriptor(String str, int i, MediaSource.MediaPeriodId mediaPeriodId) {
            long j;
            this.sessionId = str;
            this.windowIndex = i;
            if (mediaPeriodId == null) {
                j = -1;
            } else {
                j = mediaPeriodId.windowSequenceNumber;
            }
            this.windowSequenceNumber = j;
            if (mediaPeriodId != null && mediaPeriodId.isAd()) {
                this.adMediaPeriodId = mediaPeriodId;
            }
        }

        public boolean tryResolvingToNewTimeline(Timeline timeline, Timeline timeline2) {
            int resolveWindowIndexToNewTimeline = resolveWindowIndexToNewTimeline(timeline, timeline2, this.windowIndex);
            this.windowIndex = resolveWindowIndexToNewTimeline;
            if (resolveWindowIndexToNewTimeline == -1) {
                return false;
            }
            MediaSource.MediaPeriodId mediaPeriodId = this.adMediaPeriodId;
            if (mediaPeriodId == null) {
                return true;
            }
            if (timeline2.getIndexOfPeriod(mediaPeriodId.periodUid) != -1) {
                return true;
            }
            return false;
        }

        public boolean belongsToSession(int i, MediaSource.MediaPeriodId mediaPeriodId) {
            if (mediaPeriodId == null) {
                return i == this.windowIndex;
            }
            if (this.adMediaPeriodId == null) {
                if (mediaPeriodId.isAd() || mediaPeriodId.windowSequenceNumber != this.windowSequenceNumber) {
                    return false;
                }
                return true;
            } else if (mediaPeriodId.windowSequenceNumber == this.adMediaPeriodId.windowSequenceNumber && mediaPeriodId.adGroupIndex == this.adMediaPeriodId.adGroupIndex && mediaPeriodId.adIndexInAdGroup == this.adMediaPeriodId.adIndexInAdGroup) {
                return true;
            } else {
                return false;
            }
        }

        public void maybeSetWindowSequenceNumber(int i, MediaSource.MediaPeriodId mediaPeriodId) {
            if (this.windowSequenceNumber == -1 && i == this.windowIndex && mediaPeriodId != null && mediaPeriodId.windowSequenceNumber >= DefaultPlaybackSessionManager.this.getMinWindowSequenceNumber()) {
                this.windowSequenceNumber = mediaPeriodId.windowSequenceNumber;
            }
        }

        public boolean isFinishedAtEventTime(AnalyticsListener.EventTime eventTime) {
            if (eventTime.mediaPeriodId == null) {
                if (this.windowIndex != eventTime.windowIndex) {
                    return true;
                }
                return false;
            } else if (this.windowSequenceNumber == -1) {
                return false;
            } else {
                if (eventTime.mediaPeriodId.windowSequenceNumber > this.windowSequenceNumber) {
                    return true;
                }
                if (this.adMediaPeriodId == null) {
                    return false;
                }
                int indexOfPeriod = eventTime.timeline.getIndexOfPeriod(eventTime.mediaPeriodId.periodUid);
                int indexOfPeriod2 = eventTime.timeline.getIndexOfPeriod(this.adMediaPeriodId.periodUid);
                if (eventTime.mediaPeriodId.windowSequenceNumber < this.adMediaPeriodId.windowSequenceNumber || indexOfPeriod < indexOfPeriod2) {
                    return false;
                }
                if (indexOfPeriod > indexOfPeriod2) {
                    return true;
                }
                if (eventTime.mediaPeriodId.isAd()) {
                    int i = eventTime.mediaPeriodId.adGroupIndex;
                    int i2 = eventTime.mediaPeriodId.adIndexInAdGroup;
                    if (i > this.adMediaPeriodId.adGroupIndex) {
                        return true;
                    }
                    if (i != this.adMediaPeriodId.adGroupIndex || i2 <= this.adMediaPeriodId.adIndexInAdGroup) {
                        return false;
                    }
                    return true;
                } else if (eventTime.mediaPeriodId.nextAdGroupIndex == -1 || eventTime.mediaPeriodId.nextAdGroupIndex > this.adMediaPeriodId.adGroupIndex) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        private int resolveWindowIndexToNewTimeline(Timeline timeline, Timeline timeline2, int i) {
            if (i < timeline.getWindowCount()) {
                timeline.getWindow(i, DefaultPlaybackSessionManager.this.window);
                for (int i2 = DefaultPlaybackSessionManager.this.window.firstPeriodIndex; i2 <= DefaultPlaybackSessionManager.this.window.lastPeriodIndex; i2++) {
                    int indexOfPeriod = timeline2.getIndexOfPeriod(timeline.getUidOfPeriod(i2));
                    if (indexOfPeriod != -1) {
                        return timeline2.getPeriod(indexOfPeriod, DefaultPlaybackSessionManager.this.period).windowIndex;
                    }
                }
                return -1;
            } else if (i < timeline2.getWindowCount()) {
                return i;
            } else {
                return -1;
            }
        }
    }
}
